package ni.factorizacion.parcial20240608.controllers;


import jakarta.validation.Valid;
import ni.factorizacion.parcial20240608.domain.dtos.GeneralResponse;
import ni.factorizacion.parcial20240608.domain.dtos.HistorySimpleDto;
import ni.factorizacion.parcial20240608.domain.dtos.SaveHistoryDto;
import ni.factorizacion.parcial20240608.domain.entities.History;
import ni.factorizacion.parcial20240608.services.HistoryService;
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
    private HistoryService service;

    @GetMapping("/record")
    @PreAuthorize("hasAuthority('PTNT')")
    public ResponseEntity<GeneralResponse<List<HistorySimpleDto>>> getAllHistories(
            @RequestParam("user") String user,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        var histories = service.getAll(user, startDate, endDate);

        if (histories.isEmpty()) {
            return GeneralResponse.ok("No Histories found", histories);
        }
        return GeneralResponse.ok( "Found Histories", histories);
    }

    @PostMapping(consumes = "application/json")
    @PreAuthorize("hasAuthority('DOCT') and hasAuthority('RECP')")
    public ResponseEntity<GeneralResponse<Void>> addHistoryEntry(
            @RequestParam("userIdentifier") String userIdentifier,
            @RequestParam("reason") String reason) {

        try {
            service.addHistoryEntry(userIdentifier, reason);
            return GeneralResponse.ok("Added to medical history", null);
        } catch (ControlException e) {
            e.printStackTrace();
            return GeneralResponse.error500("Error");
        }
    }

    @PutMapping(path = "/{uuid}")
    public ResponseEntity<GeneralResponse<History>> updateUser(@PathVariable("uuid") String uuid, @RequestBody @Valid SaveHistoryDto dto) throws Exception {
        service.updateHistory(uuid, dto);
        return GeneralResponse.ok("History Updated", null);
    }

    @DeleteMapping(path = "/{uuid}")
    public ResponseEntity<GeneralResponse<History>> removeResidence(@PathVariable("uuid") String uuid) throws Exception {
        service.removeHistory(uuid);
        return GeneralResponse.ok( "History Deleted", null);
    }


}
