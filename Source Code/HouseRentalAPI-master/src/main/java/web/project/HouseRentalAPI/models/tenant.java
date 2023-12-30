package web.project.HouseRentalAPI.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Table(name = "tenants")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Tenant")
public class tenant extends person {
    private boolean rentPaid = false;
    private int rentDue;
    private String landlordId;
    private String propertyId;
}
