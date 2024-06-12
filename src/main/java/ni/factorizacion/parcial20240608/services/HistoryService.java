package ni.factorizacion.parcial20240608.services;

import ni.factorizacion.parcial20240608.domain.dtos.HistorySimpleDto;
import ni.factorizacion.parcial20240608.domain.dtos.SaveHistoryDto;
import ni.factorizacion.parcial20240608.domain.entities.History;
import ni.factorizacion.parcial20240608.types.ControlException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface HistoryService {
    List<HistorySimpleDto> getAll(String user);
    Optional<History> findByDate(LocalDateTime dateTime);
    void saveHistory(SaveHistoryDto dto) throws ControlException;
    void updateHistory(String uuid, SaveHistoryDto dto) throws ControlException;
    void removeHistory(String uuid) throws ControlException;
}
