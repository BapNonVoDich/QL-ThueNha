package web.project.HouseRentalAPI.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Table(name = "notice")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Notice")
public class notice {
    @Id
    private String id;
    private String title;
    private String content;
    private String to;
    private String from;
    private boolean isActive;
}
