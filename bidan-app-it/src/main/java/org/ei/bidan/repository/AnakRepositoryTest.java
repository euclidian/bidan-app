package org.ei.bidan.repository;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import org.ei.bidan.bidan.domain.Anak;
import org.ei.bidan.bidan.repository.AnakRepository;
import org.ei.bidan.bidan.domain.Ibu;
import org.ei.bidan.bidan.domain.KartuIbu;
import org.ei.bidan.bidan.repository.IbuRepository;
import org.ei.bidan.bidan.repository.KartuIbuRepository;
import org.ei.bidan.domain.Alert;
import org.ei.bidan.util.Session;
import org.ei.drishti.dto.AlertStatus;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import static java.util.Arrays.asList;

import static org.ei.bidan.util.EasyMap.create;
import static org.ei.bidan.util.EasyMap.mapOf;

/**
 * Created by Dimas Ciputra on 4/7/15.
 */
public class AnakRepositoryTest extends AndroidTestCase {
    private AnakRepository anakRepository;
    private IbuRepository ibuRepository;
    private KartuIbuRepository kartuIbuRepository;
    private TimelineEventRepository timelineEventRepository;
    private AlertRepository alertRepository;

    private static final Map<String, String> EXTRA_DETAILS = mapOf("some-key", "some-value");

    @Override
    protected void setUp() throws Exception {
        timelineEventRepository = new TimelineEventRepository();
        ibuRepository = new IbuRepository();
        kartuIbuRepository = new KartuIbuRepository();
        alertRepository = new AlertRepository();
        anakRepository = new AnakRepository();

        Session session = new Session().setPassword("password").setRepositoryName("drishti.db" + new Date().getTime());
        new Repository(new RenamingDelegatingContext(getContext(), "test_"), session, anakRepository, timelineEventRepository, alertRepository, ibuRepository, kartuIbuRepository);
    }

    public void testShouldInsertAnakForExistingIbu() throws Exception {
        anakRepository.add(new Anak("CASE A", "CASE X", "2012-06-09", "female", EXTRA_DETAILS));
        assertEquals(asList(new Anak("CASE A", "CASE X", "2012-06-09", "female", EXTRA_DETAILS)), anakRepository.all());
    }

    public void testShouldUpdateChild() throws Exception {
        Anak child = new Anak("CASE A", "CASE X", "2012-06-09", "female", EXTRA_DETAILS).setIsClosed(false);
        anakRepository.add(child);
        child.setIsClosed(true);

        anakRepository.update(child);
        assertEquals(child, anakRepository.find("CASE A"));
    }

    public void testShouldCloseChild() throws Exception {
        Anak child = new Anak("CASE A", "CASE X", "2012-06-09", "female", EXTRA_DETAILS).setIsClosed(false);
        anakRepository.add(child);

        anakRepository.close("CASE A");
        assertTrue(anakRepository.find("CASE A").isClosed());
    }

    public void testShouldFetchAllOpenChildren() throws Exception {
        Anak firstChild = new Anak("CASE A", "CASE X", "2012-06-09", "female", EXTRA_DETAILS);
        Anak secondChild = new Anak("CASE B", "CASE X", "2012-06-10", "female", EXTRA_DETAILS);
        Anak closedChild = new Anak("CASE C", "CASE X", "2012-06-10", "female", EXTRA_DETAILS).setIsClosed(true);
        anakRepository.add(firstChild);
        anakRepository.add(secondChild);
        anakRepository.add(closedChild);

        List<Anak> children = anakRepository.all();

        assertTrue(children.contains(firstChild));
        assertTrue(children.contains(secondChild));
        assertFalse(children.contains(closedChild));
    }

    public void testShouldFetchChildrenByTheirOwnCaseId() throws Exception {
        anakRepository.add(new Anak("CASE A", "CASE X", "2012-06-09", "female", EXTRA_DETAILS));
        anakRepository.add(new Anak("CASE B", "CASE X", "2012-06-10", "female", EXTRA_DETAILS));
        anakRepository.add(new Anak("CASE C", "CASE X", "2012-06-10", "female", EXTRA_DETAILS).setIsClosed(true));

        assertEquals(new Anak("CASE A", "CASE X", "2012-06-09", "female", EXTRA_DETAILS), anakRepository.find("CASE A"));
        assertEquals(new Anak("CASE B", "CASE X","2012-06-10", "female", EXTRA_DETAILS), anakRepository.find("CASE B"));
        assertEquals(new Anak("CASE C", "CASE X", "2012-06-10", "female", EXTRA_DETAILS).setIsClosed(true), anakRepository.find("CASE C"));
    }

    public void testShouldCountChildren() throws Exception {
        assertEquals(0, anakRepository.count());

        anakRepository.add(new Anak("CASE A", "CASE X", "2012-06-09", "female", EXTRA_DETAILS));
        assertEquals(1, anakRepository.count());

        anakRepository.add(new Anak("CASE B", "CASE X", "2012-06-09", "female", EXTRA_DETAILS));
        assertEquals(2, anakRepository.count());

        anakRepository.add(new Anak("CASE C", "CASE X", "2012-06-09", "female", EXTRA_DETAILS).setIsClosed(true));
        assertEquals(2, anakRepository.count());

        anakRepository.close("CASE B");
        assertEquals(1, anakRepository.count());
    }

    public void testShouldMarkAsClosedWhenAChildIsClosed() throws Exception {
        Anak firstChild = new Anak("CASE A", "CASE X", "2012-06-09", "female", EXTRA_DETAILS);
        Anak secondChild = new Anak("CASE B", "CASE X", "2012-06-10", "female", EXTRA_DETAILS);

        anakRepository.add(firstChild);
        anakRepository.add(secondChild);
        alertRepository.createAlert(new Alert("CASE A", "Ante Natal Care - Normal", "ANC 1", AlertStatus.normal, "2012-01-01", "2012-01-11"));
        alertRepository.createAlert(new Alert("CASE B", "Ante Natal Care - Normal", "ANC 1", AlertStatus.normal, "2012-01-01", "2012-01-11"));

        anakRepository.close("CASE A");

        assertEquals(firstChild.setIsClosed(true), anakRepository.find(firstChild.getCaseId()));
        assertEquals(secondChild, anakRepository.find(secondChild.getCaseId()));
    }

    public void testShouldUpdateMotherDetails() throws Exception {
        Map<String, String> details = mapOf("some-key", "some-value");
        Anak child = new Anak("CASE A", "CASE X", "2012-06-09", "female", details);
        anakRepository.add(child);

        Map<String, String> newDetails = create("some-key", "some-new-value").put("some-other-key", "blah").map();
        anakRepository.updateDetails("CASE A", newDetails);

        Anak expectedChildWithNewDetails = new Anak("CASE A", "CASE X","2012-06-09", "female", newDetails);
        assertEquals(asList(expectedChildWithNewDetails), anakRepository.all());
    }

    public void testShouldFetchAllChildrenWithECAndMother() throws Exception {
        Map<String, String> ecDetails = create("wifeAge", "26")
                .put("caste", "others")
                .put("economicStatus", "apl")
                .map();
        Map<String, String> childDetails = create("weight", "3")
                .put("name", "chinnu")
                .put("isChildHighRisk", "yes")
                .map();
        KartuIbu ki = new KartuIbu("ec id 1", ecDetails);
        Ibu mother = new Ibu("mother id 1", "ec id 1", "2013-01-01").withDetails(Collections.<String, String>emptyMap());
        Anak child = new Anak("child id 1", "mother id 1", "2013-01-02", "female", childDetails).withIbu(mother).withKI(ki);
        anakRepository.add(child);
        ibuRepository.add(mother);
        kartuIbuRepository.add(ki);

        List<Anak> children = anakRepository.allAnakWithIbuAndKI();

        assertTrue(children.contains(child));
    }

}
