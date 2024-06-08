package ni.factorizacion.parcial20240608.controllers;

import ni.factorizacion.parcial20240608.domain.dtos.AppointmentSimpleDto;
import ni.factorizacion.parcial20240608.domain.dtos.GeneralResponse;
import ni.factorizacion.parcial20240608.domain.dtos.HistorySimpleDto;
import ni.factorizacion.parcial20240608.services.AppointmentService;
import ni.factorizacion.parcial20240608.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clinic/")
public class ClinicRestController {

    @Autowired
    private AppointmentService service;

    @GetMapping("/clinic/schedule")
    public ResponseEntity<GeneralResponse<List<AppointmentSimpleDto>>> getAllAppointments() {
        var appointment = service.getAll();
        if (appointment.isEmpty()) {
            return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "No Appointments found", appointment);
        }
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "Found Appointment", appointment);
    }
}
