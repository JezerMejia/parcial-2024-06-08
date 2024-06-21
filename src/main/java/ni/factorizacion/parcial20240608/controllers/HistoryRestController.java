package ni.factorizacion.parcial20240608.controllers;


import jakarta.validation.Valid;
import ni.factorizacion.parcial20240608.domain.dtos.GeneralResponse;
import ni.factorizacion.parcial20240608.domain.dtos.HistorySimpleDto;
import ni.factorizacion.parcial20240608.domain.dtos.SaveHistoryDto;
import ni.factorizacion.parcial20240608.domain.entities.History;
import ni.factorizacion.parcial20240608.domain.entities.User;
import ni.factorizacion.parcial20240608.services.HistoryService;
import ni.factorizacion.parcial20240608.services.UserService;
import ni.factorizacion.parcial20240608.types.ControlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/user/")
public class HistoryRestController {
    @Autowired
    private HistoryService historyService;
    @Autowired
    private UserService userService;

    @GetMapping("/record")
    @PreAuthorize("hasAuthority('PTNT')")
    public ResponseEntity<GeneralResponse<List<HistorySimpleDto>>> getAllHistories(
            @RequestParam("userIdentifier") String userIdentifier,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        User user = userService.findByEmail(userIdentifier);
        if (user == null) {
            user = userService.findByUsername(userIdentifier);
        }
        if (user == null) {
            return GeneralResponse.error404("No user found");
        }

        var histories = historyService.findAll(user, startDate, endDate);

        if (histories.isEmpty()) {
            return GeneralResponse.ok("No Histories found", histories);
        }
        return GeneralResponse.ok("Found Histories", histories);
    }

    @PostMapping("/record")
    @PreAuthorize("hasAuthority('DOCT') or hasAuthority('RECP')")
    public ResponseEntity<GeneralResponse<Void>> addHistoryEntry(
            @RequestParam("userIdentifier") String userIdentifier,
            @RequestParam("reason") String reason) {
        User user = userService.findByEmail(userIdentifier);
        if (user == null) {
            user = userService.findByUsername(userIdentifier);
        }
        if (user == null) {
            return GeneralResponse.error404("No user found");
        }

        try {
            historyService.addHistoryEntry(user, reason);
            return GeneralResponse.ok("Added to medical history", null);
        } catch (ControlException e) {
            e.printStackTrace();
            return GeneralResponse.error500("Error while adding history entry");
        }
    }

    @PutMapping(path = "/{uuid}")
    public ResponseEntity<GeneralResponse<History>> updateUser(@PathVariable("uuid") String uuid, @RequestBody @Valid SaveHistoryDto dto) throws Exception {
        historyService.updateHistory(uuid, dto);
        return GeneralResponse.ok("History Updated", null);
    }

    @DeleteMapping(path = "/{uuid}")
    public ResponseEntity<GeneralResponse<History>> removeResidence(@PathVariable("uuid") String uuid) throws Exception {
        historyService.removeHistory(uuid);
        return GeneralResponse.ok("History Deleted", null);
    }

    @PostMapping(value = "/getCountEntriesByPatient")
    @PreAuthorize("hasAuthority('DOCT') or hasAuthority('RECP')")
    public ResponseEntity<GeneralResponse<Integer>> getCountByPatient(@RequestBody String userIdentifier) {
        User user = userService.findByEmail(userIdentifier);
        if (user == null) {
            user = userService.findByUsername(userIdentifier);
        }
        if (user == null) {
            return GeneralResponse.error404("No user found");
        }

        return GeneralResponse.ok("Found Histories", historyService.countHistoriesByUser(user));
    }

}
