package web.project.HouseRentalAPI.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance
public abstract class person {
    @Id
    protected String id;
    protected String name;
    @Indexed(unique = true)
    protected String email;
    protected String password;
    protected String phone;
}
