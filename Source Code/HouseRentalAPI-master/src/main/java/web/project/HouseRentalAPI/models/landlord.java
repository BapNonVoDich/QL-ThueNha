package web.project.HouseRentalAPI.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "owner")
@Data
@Document(collection = "Owner")
@AllArgsConstructor
@NoArgsConstructor
public class landlord extends person {
    private List<String> ownedPropertyIds = new ArrayList<>();
    private List<String> tenantsId = new ArrayList<>();

    // Constructor without the ownedPropertyIds parameter
    public landlord(String id, String name, String email, String password, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }
}
