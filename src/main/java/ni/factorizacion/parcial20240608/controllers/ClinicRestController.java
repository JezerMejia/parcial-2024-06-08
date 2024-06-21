package ni.factorizacion.parcial20240608.controllers;

import ni.factorizacion.parcial20240608.domain.dtos.AppointmentDto;
import ni.factorizacion.parcial20240608.domain.dtos.GeneralResponse;
import ni.factorizacion.parcial20240608.domain.entities.Appointment;
import ni.factorizacion.parcial20240608.domain.entities.User;
import ni.factorizacion.parcial20240608.services.ClinicService;
import ni.factorizacion.parcial20240608.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clinic")
public class ClinicRestController {

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private UserService userService;

    @GetMapping("/schedule")
    public ResponseEntity<GeneralResponse<List<AppointmentDto>>> getAllMyAppointments() {
        User user = userService.findUserAuthenticated();

        List<Appointment> appointmentList = clinicService.findByMedic(user);
        if (appointmentList.isEmpty()) {
            return GeneralResponse.ok("No appointments found", List.of());
        }

        List<AppointmentDto> appointmentDtoList = appointmentList.stream().map(AppointmentDto::from).toList();

        return GeneralResponse.ok("Appointments found", appointmentDtoList);
    }

}