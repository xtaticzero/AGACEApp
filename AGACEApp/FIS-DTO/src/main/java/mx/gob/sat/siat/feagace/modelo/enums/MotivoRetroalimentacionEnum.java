/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.enums;

import java.math.BigDecimal;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public enum MotivoRetroalimentacionEnum {

    SOLICITUD_CAMBIO_METODO_REVISION(new BigDecimal(2L), "Solicitud de cambio de método de revisión"),
    SOLICITUD_CAMBIO_PERIODO_REVISION(new BigDecimal(3L), "Solicitud de cambio de periodo de revisión"),
    OBSERVACIONES_RELACIONADAS_CON_LA_PROPUESTA_FISCALIZACION(new BigDecimal(4L), "Observaciones relacionadas con la propuesta de fiscalización y que no deriven en la improcedencia del asunto"),
    SOLICITUD_CAMBIO_METODO_RAZONES_CAPACIDAD_INSTALADA(new BigDecimal(7L), "Solicitud de cambio de método de revisión derivado de la consideración del área operativa, por razones de capacidad instalada, estrategia operativa, etc."),
    SOLICITUD_CAMBIO_PERIODO_RAZONES_CAPACIDAD_INSTALADA(new BigDecimal(8L), "Solicitud de cambio de periodo de revisión derivado de la consideración del área operativa, por razones de capacidad instalada, estrategia operativa, etc."),
    SOLICITUD_INCLUSION_IMPUESTOS_NO_CONTEMPLADOS(new BigDecimal(9L), "Solicitud de inclusión de impuestos no contemplados en la propuesta original o en su caso disminución de los mismos por estrategia del área operativa.");

    /**
     * ID
     */
    private final BigDecimal id;

    /**
     * DESCRIPCION
     */
    private final String descripcion;

    private MotivoRetroalimentacionEnum(BigDecimal id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public BigDecimal getId() {
        return id;
    }
}
