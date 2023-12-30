package web.project.HouseRentalAPI.service;

import web.project.HouseRentalAPI.dto.LoginRequest;
import web.project.HouseRentalAPI.models.tenant;

import java.util.List;

public interface tenantService {
    Object addTenant(tenant user);

    Object loginTenant(LoginRequest user);

    Object getTenantByID(String id);

    List<tenant> getAllUsers();

    List<tenant> searchTenantByName(String name);

    Object deleteTenant(String id);

    Object rentPaid(String id);

    boolean existsTenant(String email);

    boolean confirmPassword(LoginRequest request);
}
