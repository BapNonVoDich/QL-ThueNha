package web.project.HouseRentalAPI.service;

import web.project.HouseRentalAPI.dto.LoginRequest;
import web.project.HouseRentalAPI.models.landlord;
import web.project.HouseRentalAPI.models.tenant;

import java.util.List;

public interface landlordService {
    List<landlord> getAlllandlord();

    String addLandlord(landlord userl);

    landlord loginLandlord(LoginRequest request);

    landlord getLandlordById(String id);

    List<tenant> getAllTenants(String id);

    Object getAllProperty(String id);

    Object getTotalRent(String id);

    Object deleteUserById(String id);

    boolean existsLandlord(String email);

    boolean confirmPassword(LoginRequest email);
}
