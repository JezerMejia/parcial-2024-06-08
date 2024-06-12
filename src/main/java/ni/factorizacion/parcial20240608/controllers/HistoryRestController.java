package ni.factorizacion.parcial20240608.controllers;


import jakarta.validation.Valid;
import ni.factorizacion.parcial20240608.domain.dtos.GeneralResponse;
import ni.factorizacion.parcial20240608.domain.dtos.HistorySimpleDto;
import ni.factorizacion.parcial20240608.domain.dtos.SaveHistoryDto;
import ni.factorizacion.parcial20240608.domain.entities.History;
import ni.factorizacion.parcial20240608.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history/")
public class HistoryRestController {
    @Autowired
    private HistoryService service;
    @GetMapping
    //@PreAuthorize("hasAuthority('PTNT')")
    public ResponseEntity<GeneralResponse<List<HistorySimpleDto>>> getAllHistories() {
        var histories = service.getAll();
        if (histories.isEmpty()) {
            return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "No Histories found", histories);
        }
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "Found Histories", histories);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<GeneralResponse<History>> saveResidence(@Valid @RequestBody SaveHistoryDto historyDto) throws Exception {
        service.saveHistory(historyDto);
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "Residence saved", null);
    }

    @PutMapping(path = "/{uuid}")
    public ResponseEntity<GeneralResponse<History>> updateUser(@PathVariable("uuid") String uuid, @RequestBody @Valid SaveHistoryDto dto) throws Exception {
        service.updateHistory(uuid, dto);
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "History Updated", null);
    }

    @DeleteMapping(path = "/{uuid}")
    public ResponseEntity<GeneralResponse<History>> removeResidence(@PathVariable("uuid") String uuid) throws Exception {
        service.removeHistory(uuid);
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "History Deleted", null);
    }


}
