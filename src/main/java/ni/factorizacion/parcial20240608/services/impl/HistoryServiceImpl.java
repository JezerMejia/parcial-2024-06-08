package ni.factorizacion.parcial20240608.services.impl;

import ni.factorizacion.parcial20240608.domain.dtos.HistorySimpleDto;
import ni.factorizacion.parcial20240608.domain.entities.History;
import ni.factorizacion.parcial20240608.repositories.HistoryRepository;
import ni.factorizacion.parcial20240608.repositories.UserRepository;
import ni.factorizacion.parcial20240608.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
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

}
