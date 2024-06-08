package ni.factorizacion.parcial20240608.domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Specialty {
    @Id
    @Length(max = 4)
    private String id;

    @NotEmpty
    private String name;

    @OneToMany(mappedBy = "specialty", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<AppointmentMedicSpecialty> appointmentMedicSpecialty = new HashSet<>();
}
