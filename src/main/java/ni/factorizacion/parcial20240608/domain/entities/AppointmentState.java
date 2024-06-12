package ni.factorizacion.parcial20240608.domain.entities;

public enum AppointmentState {
    APPROVAL_PENDING,
    RUNNING_PENDING,
    RUNNING,
    ENDED,
    REJECTED,
    CANCELED;

    public String toString() {
        switch (this) {
            case APPROVAL_PENDING -> {
                return "Pendiente de aprobación";
            }
            case RUNNING_PENDING -> {
                return "Pendiente de ejecución";
            }
            case RUNNING -> {
                return "En ejecución";
            }
            case ENDED -> {
                return "Finalizada";
            }
            case REJECTED -> {
                return "Rechazada";
            }
            case CANCELED -> {
                return "Cancelada";
            }
        }
        return this.name();
    }
}
