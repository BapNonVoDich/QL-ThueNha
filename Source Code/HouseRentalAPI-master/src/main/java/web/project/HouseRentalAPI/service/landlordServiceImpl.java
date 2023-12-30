package web.project.HouseRentalAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import web.project.HouseRentalAPI.dto.LoginRequest;
import web.project.HouseRentalAPI.models.landlord;
import web.project.HouseRentalAPI.models.property;
import web.project.HouseRentalAPI.models.tenant;
import web.project.HouseRentalAPI.repositories.landlordRepository;
import web.project.HouseRentalAPI.repositories.propertyRepository;
import web.project.HouseRentalAPI.repositories.tenantRepository;

import java.util.List;
import java.util.Optional;


@Service
public class landlordServiceImpl implements landlordService {

    @Autowired
    landlordRepository landlordRepository;
    @Autowired
    tenantRepository tenantRepository;
    @Autowired
    propertyRepository propertyRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<landlord> getAlllandlord() {
        return landlordRepository.findAll();
    }

    @Override
    public String addLandlord(landlord userl) {
        if (landlordRepository.existsByEmail(userl.getEmail())) {
            return new String("email đã tồn tại");
        }
        if (landlordRepository.existsByPhone(userl.getPhone())) {
            return new String("Số điện thoại đã tồn tại");
        }
        String encryptedPassword = passwordEncoder.encode(userl.getPassword());
        userl.setPassword(encryptedPassword);
        landlordRepository.save(userl);
        return new String("Thành công");
    }

    @Override
    public landlord loginLandlord(LoginRequest request) {
        return landlordRepository.findByEmail(request.getEmail()).get();
    }

    @Override
    public landlord getLandlordById(String id) {
        Optional<landlord> landlordOptional = landlordRepository.findById(id);
        return landlordOptional.get();
    }

    @Override
    public List<tenant> getAllTenants(String id) {
        if (!landlordRepository.existsById(id)) {
            return null;
        }
        return tenantRepository.findByLandlordId(id);
    }

    @Override
    public List<property> getAllProperty(String id) {
        if (!landlordRepository.existsById(id)) {
            return null;
        }
        List<property> properties = propertyRepository.findByOwnerId(id);
        return properties;
    }

    @Override
    public Integer getTotalRent(String id) {
        if (!landlordRepository.existsById(id)) {
            return null;
        }
        List<tenant> tenants = tenantRepository.findByLandlordId(id);
        int totalRent = 0;
        for (tenant singleTenant : tenants) {
            totalRent += singleTenant.getRentDue();
        }
        return totalRent;
    }

    @Override
    public String deleteUserById(String id) {
        if (!landlordRepository.existsById(id)) {
            return "Landlord not found";
        }

        landlordRepository.deleteById(id);
        return "Landlord deleted successfully";
    }

    @Override
    public boolean existsLandlord(String email) {
        return landlordRepository.existsByEmail(email);
    }

    @Override
    public boolean confirmPassword(LoginRequest request) {
        return passwordEncoder.matches(request.getPassword(), landlordRepository.findByEmail(request.getEmail()).get().getPassword());
    }


}
