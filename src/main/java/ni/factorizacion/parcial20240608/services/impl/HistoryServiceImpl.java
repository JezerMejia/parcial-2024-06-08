package ni.factorizacion.parcial20240608.services.impl;

import ni.factorizacion.parcial20240608.domain.dtos.HistorySimpleDto;
import ni.factorizacion.parcial20240608.domain.dtos.SaveHistoryDto;
import ni.factorizacion.parcial20240608.domain.entities.History;
import ni.factorizacion.parcial20240608.repositories.HistoryRepository;
import ni.factorizacion.parcial20240608.repositories.UserRepository;
import ni.factorizacion.parcial20240608.services.HistoryService;
import ni.factorizacion.parcial20240608.types.ControlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private HistoryRepository repository;

    public List<HistorySimpleDto> getAll() {
        return repository.findAll().stream().map(HistorySimpleDto::from).toList();
    }

    @Override
    public Optional<History> findByDate(LocalDateTime dateTime){
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

}
