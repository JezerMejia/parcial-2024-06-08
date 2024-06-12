package ni.factorizacion.parcial20240608.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
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

    @NotEmpty
    private String reason;

    /**
     * Fecha en la que la cita médica fue solicitada
     */
    @NotNull
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime requestDate;

    /**
     * Fecha de realización/inicio de la cita médica
     */
    @NotNull
    private LocalDateTime startDate;
    /**
     * Fecha aproximada en la que finalizará la cita médica
     */
    private LocalDateTime approxEndDate;

    /**
     * Fecha en la que la cita médica finalizó
     */
    private LocalDateTime endDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AppointmentState status;

    @OneToMany(mappedBy = "appointment", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<AppointmentMedicSpecialty> appointmentMedicSpecialty;

    @OneToMany(mappedBy = "appointment", fetch = FetchType.LAZY)
    private List<Prescription> prescriptions;
}
