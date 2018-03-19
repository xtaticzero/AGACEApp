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
public enum TipoMetodoEnum {

    EHO(1L, "Art. 152 de la L.A. (EHO)", "EHO"),
    ORG(2L, "Gabinete (ORG)", "ORG"),
    UCA(3L, "Unica Carta Invitación (UCA)", "UCA"),
    REE(4L, "Revisión Electrónica (REE)", "REE"),
    MCA(5L, "Masiva Carta Invitación (MCA)", "MCA"),
	INS(6L, "INSUMOS", "INS");
	

    /**
     * ID_METODO
     */
    private final long id;

    /**
     * DESCRIPCION
     */
    private final String descripcion;

    private final String siglas;

    private TipoMetodoEnum(long id, String descripcion, String siglas) {
        this.id = id;
        this.descripcion = descripcion;
        this.siglas = siglas;

    }

    public long getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getSiglas() {
        return siglas;
    }

    public static TipoMetodoEnum getById(Long id) {
        for (TipoMetodoEnum metodo : values()) {
            if (metodo.id == id) {
                return metodo;
            }
        }
        return null;
    }

}
