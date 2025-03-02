package business.country;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    @Id
    private long id;
    private String name;
    private boolean active;
    private String pathImg;
    @Version
    private int version;
}