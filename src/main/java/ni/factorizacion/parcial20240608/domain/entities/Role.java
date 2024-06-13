package ni.factorizacion.parcial20240608.domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Role {
    @ManyToMany(cascade = {CascadeType.ALL})
    List<User> users;
    @Id
    @Length(max = 4)
    private String id;
    @NotEmpty
    private String name;

    public Role(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
