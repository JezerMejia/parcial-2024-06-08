package ni.factorizacion.parcial20240608.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Role {
    @Id
    @Length(max = 4)
    private String id;

    @NotEmpty
    private String name;

    @ManyToMany(cascade = { CascadeType.ALL })
    Set<User> users = new HashSet<>();
}
