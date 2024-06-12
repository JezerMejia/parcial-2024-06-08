package ni.factorizacion.parcial20240608.services;

import ni.factorizacion.parcial20240608.domain.dtos.*;
import ni.factorizacion.parcial20240608.domain.entities.*;
import ni.factorizacion.parcial20240608.types.ControlException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClinicService {
    List<ScheduleSimpleDto> getAllMedicsAppointments();

}