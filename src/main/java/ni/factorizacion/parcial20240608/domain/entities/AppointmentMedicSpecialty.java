package ni.factorizacion.parcial20240608.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class AppointmentMedicSpecialty {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private User medic;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Appointment appointment;

    /**
     * Una especialidad por cita médica por cada médico
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Specialty specialty;
}
