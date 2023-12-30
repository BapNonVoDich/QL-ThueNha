package web.project.HouseRentalAPI.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import web.project.HouseRentalAPI.models.property;

import java.util.List;


public interface propertyRepository extends MongoRepository<property, String> {
    List<property> findByOwnerId(String id);

    List<property> findByNameContainingIgnoreCaseOrCityContainingIgnoreCaseOrTypeContainingIgnoreCase(String name, String city, String type);
}
