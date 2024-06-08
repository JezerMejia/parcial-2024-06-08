package ni.factorizacion.parcial20240608.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import ni.factorizacion.parcial20240608.domain.dtos.PrescriptionSimpleDto;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class Prescription {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @NotEmpty
    private String dose;

    @NotEmpty
    private String medicine;

    @NotEmpty
    private LocalDateTime endDate;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Appointment appointment;


}
