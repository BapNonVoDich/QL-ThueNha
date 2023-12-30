package web.project.HouseRentalAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.project.HouseRentalAPI.models.landlord;
import web.project.HouseRentalAPI.models.property;
import web.project.HouseRentalAPI.repositories.landlordRepository;
import web.project.HouseRentalAPI.repositories.propertyRepository;
import web.project.HouseRentalAPI.repositories.tenantRepository;

import java.util.List;
import java.util.Optional;

@Service
public class propertyServiceImpl implements propertyService {
    @Autowired
    propertyRepository propertyRepository;
    @Autowired
    tenantRepository tenantRepository;
    @Autowired
    landlordRepository landlordRepository;
    @Autowired
    tenantService tenantService;

    @Override
    public property getProperty(String id) {
        return propertyRepository.findById(id).get();
    }

    @Override
    public String createProperty(property property) {
        landlord updateLandlord = landlordRepository.findById(property.getOwnerId()).orElse(null);
        if (updateLandlord != null) {
            updateLandlord.getOwnedPropertyIds().add(property.getId());
            landlordRepository.save(updateLandlord);
        }
        propertyRepository.save(property);
        return "thành công";
    }

    @Override
    public List<property> getallProperty() {
        return propertyRepository.findAll();
    }

    @Override
    public List<property> searchProperty(String query) {
        return propertyRepository.findByNameContainingIgnoreCaseOrCityContainingIgnoreCaseOrTypeContainingIgnoreCase(query, query, query);
    }

    @Override
    public String deleteProperty(String id) {
        if (!propertyRepository.existsById(id)) {
            return "Không tìm thấy tài sản";
        }
        Optional<property> propertyOptional = propertyRepository.findById(id);

        //Update landlord's propertyId field
        landlord updateLandlord = landlordRepository.findById(propertyOptional.get().getOwnerId()).orElse(null);
        if (updateLandlord != null) {
            updateLandlord.getOwnedPropertyIds().remove(id);
            landlordRepository.save(updateLandlord);
        }
        //delete tenant of property
        tenantService.deleteTenant(propertyOptional.get().getTenantId());
        propertyRepository.deleteById(id);

        return "Xoá tài sản thành công!";
    }

}
