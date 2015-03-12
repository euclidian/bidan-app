package org.ei.bidan.util;

import org.ei.bidan.domain.TimelineEvent;

import java.util.Comparator;

public class TimelineEventComparator implements Comparator<TimelineEvent> {
    @Override
    public int compare(TimelineEvent timelineEvent, TimelineEvent anotherTimelineEvent) {
        if (timelineEvent.referenceDate().isAfter(anotherTimelineEvent.referenceDate())) {
            return -1;
        }
        if (timelineEvent.referenceDate().isBefore(anotherTimelineEvent.referenceDate())) {
            return 1;
        }
        return 0;
    }
}
