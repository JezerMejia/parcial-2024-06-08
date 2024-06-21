package ni.factorizacion.parcial20240608.services.impl;

import ni.factorizacion.parcial20240608.domain.dtos.input.SaveHistoryDto;
import ni.factorizacion.parcial20240608.domain.entities.History;
import ni.factorizacion.parcial20240608.domain.entities.User;
import ni.factorizacion.parcial20240608.repositories.HistoryRepository;
import ni.factorizacion.parcial20240608.repositories.UserRepository;
import ni.factorizacion.parcial20240608.services.HistoryService;
import ni.factorizacion.parcial20240608.types.ControlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private HistoryRepository repository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<History> findAll(User user, LocalDate startDate, LocalDate endDate) {
        return repository.findAll().stream()
                .filter(history -> history.getPatient().equals(user))
                .filter(history -> {
                    LocalDate historyDate = LocalDate.from(history.getDate());
                    return (startDate == null || !historyDate.isBefore(startDate)) &&
                            (endDate == null || !historyDate.isAfter(endDate));
                })
                .sorted((h1, h2) -> h2.getDate().compareTo(h1.getDate()))
                .toList();
    }

    @Override
    public Optional<History> findByDate(LocalDateTime dateTime) {
        return repository.findByDate(dateTime);
    }

    @Override
    public void saveHistory(SaveHistoryDto dto) throws ControlException {
        Optional<History> found = this.findByDate(dto.getDateTime());
        if (found.isPresent()) {
            throw new ControlException(HttpStatus.CONFLICT, "Residence already exists");
        }
        History history = new History();
        history.setDate(dto.getDateTime());
        history.setReason(dto.getReason());
        repository.save(history);
    }

    @Override
    public void addHistoryEntry(User user, String reason) throws ControlException {
        History history = new History();
        history.setDate(LocalDateTime.now());
        history.setReason(reason);
        history.setPatient(user);

        repository.save(history);
    }

    @Override
    public void updateHistory(String uuid, SaveHistoryDto dto) throws ControlException {
        Optional<History> found = repository.findById(UUID.fromString(uuid));
        if (found.isEmpty()) {
            throw new ControlException(HttpStatus.CONFLICT, "History does not exist");
        }
        History history = found.get();
        history.setDate(dto.getDateTime());
        history.setReason(dto.getReason());
        repository.save(history);
    }

    @Override
    public void removeHistory(String uuid) throws ControlException {
        boolean exist = repository.existsById(UUID.fromString(uuid));
        if (!exist) {
            throw new IllegalStateException(
                    "History with the id +" + uuid + " does not exist");
        }
        repository.deleteById(UUID.fromString(uuid));
    }

    @Override
    public Integer countHistoriesByUser(User user) {
        return repository.countByPatient(user);
    }

    @Override
    public List<History> findHistoriesByUser(User user) {
        return repository.findByPatient(user);
    }
}
