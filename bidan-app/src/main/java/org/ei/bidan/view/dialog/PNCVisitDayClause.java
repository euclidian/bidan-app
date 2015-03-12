package org.ei.bidan.view.dialog;

import org.ei.bidan.view.contract.ServiceProvidedDTO;

public class PNCVisitDayClause implements FilterClause<ServiceProvidedDTO> {
    private int visitDay;

    public PNCVisitDayClause(int visitDay) {
        this.visitDay = visitDay;
    }

    @Override
    public boolean filter(ServiceProvidedDTO service) {
        return service.day() == visitDay;
    }
}
