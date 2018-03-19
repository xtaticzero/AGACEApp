/**
 * 
 */
package mx.gob.sat.siat.feagace.modelo.enums;

import java.math.BigDecimal;

/**
 * @author sergio.vaca
 *
 */
public enum AccionesFuncionarioEnum {
    ASIGNADAS_POR_FIRMANTE(BigDecimal.valueOf(1), "Asignadas por Firmante"),
    RETROALIMENTAR(BigDecimal.valueOf(2), "Retroalimentar"),
    VALIDACION_FIRMANTE(BigDecimal.valueOf(3), "En validacion de Firmante"),
    VALIDACION_EMISION_ORDENES_7(BigDecimal.valueOf(4), "En validacion de Emision de Ordenes"),
    CANCELAR(BigDecimal.valueOf(5), "Cancelar"),
    TRANSFERIR(BigDecimal.valueOf(6), "Tranferir"),
    RECHAZAR(BigDecimal.valueOf(7), "Rechazar"),
    FIRMA_ORDEN(BigDecimal.valueOf(8), "Firma de la orden"),
    VALIDACION_EMISION_ORDENES_12(BigDecimal.valueOf(9), "En validacion de Emision de Ordenes"),
    RETROALIMENTACION_NO_APROBADA(BigDecimal.valueOf(10), "Retroalimentacion No Aprobada"),
    RETROALIMENTACION_APROBADA(BigDecimal.valueOf(11), "Retroalimentacion Aprobada"),
    RETROALIMENTACION_ATENDIDA(BigDecimal.valueOf(12), "Retroalimentacion Atendida"),
    APROBACION_ORDEN(BigDecimal.valueOf(13), "Aprobacion Orden"),
    NO_APROBACION_ORDEN(BigDecimal.valueOf(14), "No Aprobacion Orden"),
    APROBACION_CANCELACION(BigDecimal.valueOf(15), "Aprobacion Cancelacion"),
    NO_APROBACION_CANCELACION(BigDecimal.valueOf(16), "No Aprobacion Cancelacion"),
    APROBACION_TRANSFERENCIA(BigDecimal.valueOf(17), "Aprobacion Transferencia"),
    NO_APROBACION_TRANSFERENCIA(BigDecimal.valueOf(18), "No Aprobacion Transferencia"),
    APROBACION_RECHAZO(BigDecimal.valueOf(19), "Aprobacion Rechazo"),
    NO_APROBACION_RECHAZO(BigDecimal.valueOf(20), "No Aprobacion Rechazo"),
    APROBACION_VALIDACION(BigDecimal.valueOf(21), "Aprobacion de Validacion"),
    NO_APROBACION_VALIDACION(BigDecimal.valueOf(22), "No Aprobacion de Validacion"),
    VALIDACION_EMISION_ORDENES_11(BigDecimal.valueOf(23), "En validacion de Emision de Ordenes");
    
    private BigDecimal idAccion;
    private String descripcion;
    
    AccionesFuncionarioEnum(BigDecimal idAccion, String descripcion) {
        this.idAccion = idAccion;
        this.descripcion = descripcion;
    }

    public BigDecimal getIdAccion() {
        return idAccion;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    public static AccionesFuncionarioEnum parse(int idAccion){
        for(AccionesFuncionarioEnum accionesFuncionarioEnum:AccionesFuncionarioEnum.values()){
            if(accionesFuncionarioEnum.getIdAccion().intValue() == idAccion){
               return accionesFuncionarioEnum;
            }
        }
        return null;
    }
}
