package ni.factorizacion.parcial20240608.domain.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ni.factorizacion.parcial20240608.domain.entities.Token;

@Data
@NoArgsConstructor
public class TokenDto {

    private String token;

    public TokenDto(Token token) {
        this.token = token.getContent();
    }
}
