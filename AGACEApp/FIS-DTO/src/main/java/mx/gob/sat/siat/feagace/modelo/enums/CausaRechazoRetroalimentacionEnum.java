/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.enums;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public enum CausaRechazoRetroalimentacionEnum {

    CAMBIO_METODO(2L, "Solicitud de cambio de método de revisión"),
    CAMBIO_PERIODO(3L, "Solicitud de cambio de periodo de revisión"),
    OBSERVACIONES_RELACIONADAS_CON_PROPUESTA(4L, "Observaciones relacionadas con la propuesta de fiscalización y que no deriven en la improcedencia del asunto"),
    CAMBIO_METODO_REVISION(7L, "Solicitud de cambio de método de revisión derivado de la consideración del área operativa, por razones de capacidad instalada, estrategia operativa, etc."),
    CAMBIO_PERIODO_REVISION(8L, "Solicitud de cambio de periodo de revisión derivado de la consideración del área operativa, por razones de capacidad instalada, estrategia operativa, etc."),
    INCLUSION_IMPUESTOS_NO_CONTEMPLADOS(9L, "Solicitud de inclusión de impuestos no contemplados en la propuesta original o en su caso disminución de los mismos por estrategia del área operativa.");

    /**
     * ID
     */
    private final long id;

    /**
     * DESCRIPCION
     */
    private final String descripcion;

    private CausaRechazoRetroalimentacionEnum(long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public long getId() {
        return id;
    }
}
