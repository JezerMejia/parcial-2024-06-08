package ni.factorizacion.parcial20240608.domain.dtos;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import ni.factorizacion.parcial20240608.domain.entities.History;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@SuperBuilder
public class HistorySimpleDto {
    private LocalDateTime LocalDateTime;
    private String reason;

    static public HistorySimpleDto from(History history){
        return HistorySimpleDto.builder()
                .LocalDateTime(history.getDate())
                .reason(history.getReason())
                .build();
    }


}
