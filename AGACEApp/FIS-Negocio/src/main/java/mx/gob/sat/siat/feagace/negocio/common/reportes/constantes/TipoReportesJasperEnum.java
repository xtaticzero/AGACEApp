/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.common.reportes.constantes;

/**
 *
 * @author Ing Emmanuel Estrada Gonzalez
 */
public enum TipoReportesJasperEnum {

    PDF(1, "pdf"),
    XLS(2, "xls"),
    XLSX(3, "xlsx"),
    DOCX(4, "docx"),
    PPTX(5, "pptx"),
    ODS(6, "ods"),
    ODT(7, "odt"),
    CSV(8, "csv"),
    RTF(9, "rtf"),
    TXT(10, "txt"),
    HTML(11, "html"),
    XML(12, "xml");
    
    /**
     * Identificador
     */
    private final int id;

    /**
     * Descripci&oacute;n
     */
    private final String descripcion;

    /**
     * constructor ABCEnum
     *
     * @param descripcion del elemento
     * @param id identificador del elemento
     */
    TipoReportesJasperEnum(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    /**
     * Regresa el identificador del enum
     *
     * @return identificador.
     */
    public int getId() {
        return id;
    }

    /**
     * Regresa la descipcion del enum
     *
     * @return
     */
    public String getDescripcion() {
        return descripcion;
    }

}
