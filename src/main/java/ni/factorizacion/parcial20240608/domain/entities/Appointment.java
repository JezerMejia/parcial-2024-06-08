package ni.factorizacion.parcial20240608.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @NotNull
    @ManyToOne
    private User patient;

    @OneToMany(mappedBy = "appointment", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<AppointmentMedicSpecialty> appointmentMedicSpecialty = new HashSet<>();

    @OneToMany(mappedBy = "appointment", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Prescription> prescriptions;
}
