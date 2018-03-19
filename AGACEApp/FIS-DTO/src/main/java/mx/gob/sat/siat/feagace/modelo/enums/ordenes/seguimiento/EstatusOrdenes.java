package mx.gob.sat.siat.feagace.modelo.enums.ordenes.seguimiento;

import java.math.BigDecimal;

public enum EstatusOrdenes {

    CREADA_PENDIENTE_FIRMA(101, "Orden Creada Pendiente de Firma"),
    ENVIADO_NOTIFICACION_CONTRIBUYENTE(102, "Registro Firmado y Enviado a Notificación del Contribuyente"),
    NOTIFICADO_AL_CONTRIBUYENTE(103, "Registro notificado al contribuyente"),
    ERROR_AL_NOTIFICAR_AL_CONTRIBUYENTE(104, "Error al notificar al contribuyente"),
    PLAZO_SUSPENDIDO_POR_ACUERDO_CONCLUSIVO(105, "Plazo de la Orden Suspendido por Acuerdo Conclusivo"),
    REACTIVACION_PLAZO_ACUERDO_CONCLUSIVO(106, "Reactivacion de Plazo de Acuerdo Conclusivo"),
    REACTIVACION_PLAZO_COMPULSAS(107, "Reactivacion de Plazo de Compulsas"),
    PLAZO_SUSPENDIDO_POR_COMPULSA_REE(108, "Plazo de la Orden Suspendido por Compulsa (REE)"),
    EN_ESPERA_EMISION_RESOLUCION_DEFINITIVA_ORG(109, "Orden en Espera de Emision de Resolucion Definitiva(ORG)"),
    CONCLUIDA(110, "Orden Concluida"),
    CONCLUIDA_POR_CAMBIO_METODO(111, "Orden Concluida por Cambio de Metodo"),
    CREADA_POR_CAMBIO_METODO(112, "Orden Creada por Cambio de Metodo");

    private final int idEstatus;
    private final String nombre;

    private EstatusOrdenes(int idEstatus, String nombre) {
        this.idEstatus = idEstatus;
        this.nombre = nombre;
    }

    public EstatusOrdenes parse(int idEstatus) {
        for (EstatusOrdenes estatus : EstatusOrdenes.values()) {
            if (estatus.getIdEstatus() == idEstatus) {
                return estatus;
            }
        }
        return null;
    }

    public int getIdEstatus() {
        return idEstatus;
    }

    public BigDecimal getBigIdEstatus() {
        return BigDecimal.valueOf(idEstatus);
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
