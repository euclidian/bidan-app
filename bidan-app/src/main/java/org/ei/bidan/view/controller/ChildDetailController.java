package org.ei.bidan.view.controller;

import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.ocpsoft.pretty.time.PrettyTime;

import org.ei.bidan.view.contract.ChildDetail;
import org.ei.bidan.AllConstants;
import org.ei.bidan.domain.Child;
import org.ei.bidan.domain.EligibleCouple;
import org.ei.bidan.domain.Mother;
import org.ei.bidan.repository.AllBeneficiaries;
import org.ei.bidan.repository.AllEligibleCouples;
import org.ei.bidan.repository.AllTimelineEvents;
import org.ei.bidan.util.DateUtil;
import org.ei.bidan.util.TimelineEventComparator;
import org.ei.bidan.view.activity.CameraLaunchActivity;
import org.ei.bidan.view.contract.*;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.ei.bidan.AllConstants.CHILD_TYPE;
import static org.ei.bidan.AllConstants.ENTITY_ID;

public class ChildDetailController {
    private final Context context;
    private final String caseId;
    private final AllEligibleCouples allEligibleCouples;
    private final AllBeneficiaries allBeneficiaries;
    private final AllTimelineEvents allTimelineEvents;

    public ChildDetailController(Context context, String caseId, AllEligibleCouples allEligibleCouples, AllBeneficiaries allBeneficiaries, AllTimelineEvents allTimelineEvents) {
        this.context = context;
        this.caseId = caseId;
        this.allEligibleCouples = allEligibleCouples;
        this.allBeneficiaries = allBeneficiaries;
        this.allTimelineEvents = allTimelineEvents;
    }

    public String get() {
        Child child = allBeneficiaries.findChild(caseId);
        Mother mother = allBeneficiaries.findMother(child.motherCaseId());
        EligibleCouple couple = allEligibleCouples.findByCaseID(mother.ecCaseId());

        LocalDate deliveryDate = LocalDate.parse(child.dateOfBirth());
        String photoPath = isBlank(child.photoPath()) ?
                (AllConstants.FEMALE_GENDER.equalsIgnoreCase(child.gender())
                        ? AllConstants.DEFAULT_GIRL_INFANT_IMAGE_PLACEHOLDER_PATH
                        : AllConstants.DEFAULT_BOY_INFANT_IMAGE_PLACEHOLDER_PATH)
                : child.photoPath();

        ChildDetail detail = new ChildDetail(caseId, mother.thayiCardNumber(),
                new CoupleDetails(couple.wifeName(), couple.husbandName(), couple.ecNumber(), couple.isOutOfArea()),
                new LocationDetails(couple.village(), couple.subCenter()),
                new BirthDetails(deliveryDate.toString(), calculateAge(deliveryDate), child.gender()), photoPath)
                .addTimelineEvents(getEvents())
                .addExtraDetails(child.details());

        return new Gson().toJson(detail);
    }

    public void takePhoto() {
        Intent intent = new Intent(context, CameraLaunchActivity.class);
        intent.putExtra(AllConstants.TYPE, CHILD_TYPE);
        intent.putExtra(ENTITY_ID, caseId);
        context.startActivity(intent);
    }

    private List<TimelineEvent> getEvents() {
        List<org.ei.bidan.domain.TimelineEvent> events = allTimelineEvents.forCase(caseId);
        List<TimelineEvent> timelineEvents = new ArrayList<TimelineEvent>();

        Collections.sort(events, new TimelineEventComparator());

        for (org.ei.bidan.domain.TimelineEvent event : events) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-MM-YYYY");
            timelineEvents.add(new TimelineEvent(event.type(), event.title(), new String[]{event.detail1(), event.detail2()}, event.referenceDate().toString(dateTimeFormatter)));
        }

        return timelineEvents;
    }

    private String calculateAge(LocalDate deliveryDate) {
        PrettyTime time = new PrettyTime(DateUtil.today().toDate());
        return time.format(deliveryDate.toDate()).replaceAll(" ago", "");
    }
}
