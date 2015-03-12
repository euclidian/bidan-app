package org.ei.bidan.service;

import org.ei.bidan.domain.ServiceProvided;
import org.ei.bidan.repository.AllServicesProvided;

import java.util.List;

public class ServiceProvidedService {
    private AllServicesProvided allServiceProvided;

    public ServiceProvidedService(AllServicesProvided allServicesProvided) {
        this.allServiceProvided = allServicesProvided;
    }

    public List<ServiceProvided> findByEntityIdAndServiceNames(String entityId, String... names) {
        return allServiceProvided.findByEntityIdAndServiceNames(entityId, names);
    }

    public void add(ServiceProvided serviceProvided) {
        allServiceProvided.add(serviceProvided);
    }
}
