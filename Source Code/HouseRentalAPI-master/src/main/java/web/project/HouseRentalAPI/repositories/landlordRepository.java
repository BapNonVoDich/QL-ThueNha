package web.project.HouseRentalAPI.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import web.project.HouseRentalAPI.models.landlord;

import java.util.Optional;

@Repository
public interface landlordRepository extends MongoRepository<landlord, String> {
    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    Optional<landlord> findByEmail(String email);

}
