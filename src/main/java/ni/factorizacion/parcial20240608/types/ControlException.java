package ni.factorizacion.parcial20240608.types;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ControlException extends Exception {
    private HttpStatus status;

    public ControlException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
