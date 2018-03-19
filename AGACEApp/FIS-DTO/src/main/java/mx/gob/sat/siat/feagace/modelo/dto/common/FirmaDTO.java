package mx.gob.sat.siat.feagace.modelo.dto.common;

import java.io.Serializable;
import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FirmaDTO extends BaseModel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private String cadena;
    private String firma;
    private Date fechaFirma;
    private Object objectoFirma;
    private String rfcFirmado;
    private String rfcSuplir;
    private Long centroCosto;
    private Long numeroEmpleado;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public Date getFechaFirma() {
        return (fechaFirma != null) ? (Date) fechaFirma.clone() : null;
    }

    public void setFechaFirma(Date fechaFirma) {
        this.fechaFirma = (fechaFirma != null) ? (Date) fechaFirma.clone() : null;
    }
    
    public void setObjectoFirma(Object objectoFirma) {
        this.objectoFirma = objectoFirma;
    }

    public Object getObjectoFirma() {
        return objectoFirma;
    }

    public String getRfcFirmado() {
        return rfcFirmado;
    }

    public void setRfcFirmado(String rfcFirmado) {
        this.rfcFirmado = rfcFirmado;
    }

    public String getRfcSuplir() {
        return rfcSuplir;
    }

    public void setRfcSuplir(String rfcSuplir) {
        this.rfcSuplir = rfcSuplir;
    }

    public Long getCentroCosto() {
        return centroCosto;
    }

    public void setCentroCosto(Long centroCosto) {
        this.centroCosto = centroCosto;
    }

    public Long getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(Long numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }
}
