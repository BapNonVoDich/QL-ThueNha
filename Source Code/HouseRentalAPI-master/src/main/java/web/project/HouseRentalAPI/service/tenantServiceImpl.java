package web.project.HouseRentalAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import web.project.HouseRentalAPI.dto.LoginRequest;
import web.project.HouseRentalAPI.models.*;

import web.project.HouseRentalAPI.repositories.*;


import java.util.List;
import java.util.Optional;

@Service
public class tenantServiceImpl implements tenantService {
    @Autowired
    tenantRepository tenantRepository;
    @Autowired
    landlordRepository landlordRepository;
    @Autowired
    propertyRepository propertyRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public String addTenant(tenant user) {
        if (tenantRepository.existsByEmail(user.getEmail())) {
            return new String("email đã tồn tại");
        }
        if (tenantRepository.existsByPhone(user.getPhone())) {
            return new String("Số điện thoại đã tồn tại");
        }
        //mã hoá mk và lưu tenant
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        property updateproperty=propertyRepository.findById(user.getPropertyId()).get();
        tenantRepository.save(user);
        //thêm tenantid vào property

        updateproperty.setTenantId(tenantRepository.findByEmail(user.getEmail()).get().getId());
        System.out.println(updateproperty);
        propertyRepository.save(updateproperty);

        return new String("Thành công");
    }

    @Override
    public tenant loginTenant(LoginRequest user) {
        return tenantRepository.findByEmail(user.getEmail()).get();
    }

    @Override
    public tenant getTenantByID(String id) {
        return tenantRepository.findById(id).get();
    }

    @Override
    public List<tenant> getAllUsers() {
        return tenantRepository.findAll();
    }

    @Override
    public List<tenant> searchTenantByName(String name) {
        return tenantRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public String deleteTenant(String id) {
        if (!tenantRepository.existsById(id)) {
            return "Tenant not found";
        }
        Optional<tenant> tenantOptional = tenantRepository.findById(id);

        //Update landlord's propertyId field
        landlord updateLandlord = landlordRepository.findById(tenantOptional.get().getLandlordId()).orElse(null);
        if (updateLandlord != null) {
            updateLandlord.getTenantsId().remove(id);
            landlordRepository.save(updateLandlord);
        }

        property updateProperty = propertyRepository.findById(tenantOptional.get().getPropertyId()).orElse(null);
        if (updateProperty != null) {
            updateProperty.setTenantId("");
            propertyRepository.save(updateProperty);
        }

        tenantRepository.deleteById(id);
        return "Tenant deleted successfully";
    }

    @Override
    public String rentPaid(String id) {
        if (!tenantRepository.existsById(id)) {
            return "Tenant not found";
        }
        Optional<tenant> tenantOptional = tenantRepository.findById(id);
        Optional<property> propertyOptional=propertyRepository.findById(tenantOptional.get().getPropertyId());
        if (tenantOptional.isPresent()) {
            tenant updateTenant = tenantOptional.get();
            if(updateTenant.getRentDue()==0){
                updateTenant.setRentPaid(false);
                updateTenant.setRentDue(propertyOptional.get().getRent());
            }
            else{
                updateTenant.setRentPaid(true);
                updateTenant.setRentDue(0);
            }
            tenantRepository.save(updateTenant);
        }
        return"đã trả phí";
    }

    @Override
    public boolean existsTenant(String email) {
        return tenantRepository.existsByEmail(email);
    }

    @Override
    public boolean confirmPassword(LoginRequest request) {
        return passwordEncoder.matches(request.getPassword(), tenantRepository.findByEmail(request.getEmail()).get().getPassword());
    }
}
