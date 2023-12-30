package web.project.HouseRentalAPI.service;

import web.project.HouseRentalAPI.models.property;

import java.util.List;

public interface propertyService {
    Object getProperty(String id);

    String createProperty(property property);

    List<property> getallProperty();

    List<property> searchProperty(String query);

    Object deleteProperty(String id);
}
