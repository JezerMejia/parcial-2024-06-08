package ni.factorizacion.parcial20240608.domain.dtos.output;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import ni.factorizacion.parcial20240608.domain.entities.History;

@Data
@SuperBuilder
public class HistorySimpleDto {
    private String dateTime;
    private String reason;

    static public HistorySimpleDto from(History history){
        return HistorySimpleDto.builder()
                .dateTime(history.getDate().toString())
                .reason(history.getReason())
                .build();
    }


}
