package ni.factorizacion.parcial20240608.services;

import ni.factorizacion.parcial20240608.domain.dtos.HistorySimpleDto;
import ni.factorizacion.parcial20240608.domain.dtos.SaveHistoryDto;
import ni.factorizacion.parcial20240608.domain.entities.History;
import ni.factorizacion.parcial20240608.domain.entities.User;
import ni.factorizacion.parcial20240608.types.ControlException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface HistoryService {
    List<HistorySimpleDto> findAll(User user, LocalDate startDate, LocalDate endDate);

    Optional<History> findByDate(LocalDateTime dateTime);

    void addHistoryEntry(User user, String reason) throws ControlException;

    void saveHistory(SaveHistoryDto dto) throws ControlException;

    void updateHistory(String uuid, SaveHistoryDto dto) throws ControlException;

    void removeHistory(String uuid) throws ControlException;

    Integer countHistoriesByUser(User user);

    List<History> findHistoriesByUser(User user);
}
