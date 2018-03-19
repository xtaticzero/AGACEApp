package mx.gob.sat.siat.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AcuerdoConclusivoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String rfcContribuyente;
    private String numeroOrden;
    private String numeroAcuerdoConclusivo;
    private Date fechaRegistroAcuerdoConclusivo;
    private Date fechaFinAcuerdoConclusivo;

    public String getRfcContribuyente() {
        return rfcContribuyente;
    }

    public void setRfcContribuyente(String rfcContribuyente) {
        this.rfcContribuyente = rfcContribuyente;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public String getNumeroAcuerdoConclusivo() {
        return numeroAcuerdoConclusivo;
    }

    public void setNumeroAcuerdoConclusivo(String numeroAcuerdoConclusivo) {
        this.numeroAcuerdoConclusivo = numeroAcuerdoConclusivo;
    }

    public Date getFechaRegistroAcuerdoConclusivo() {
        return (fechaRegistroAcuerdoConclusivo != null) ? (Date) fechaRegistroAcuerdoConclusivo.clone() : null;
    }

    public void setFechaRegistroAcuerdoConclusivo(Date fechaRegistroAcuerdoConclusivo) {
        this.fechaRegistroAcuerdoConclusivo = fechaRegistroAcuerdoConclusivo != null ? new Date(fechaRegistroAcuerdoConclusivo.getTime()) : null;
    }

    public Date getFechaFinAcuerdoConclusivo() {
        return (fechaFinAcuerdoConclusivo != null) ? (Date) fechaFinAcuerdoConclusivo.clone() : null;
    }

    public void setFechaFinAcuerdoConclusivo(Date fechaFinAcuerdoConclusivo) {
        this.fechaFinAcuerdoConclusivo = fechaFinAcuerdoConclusivo != null ? new Date(fechaFinAcuerdoConclusivo.getTime()) : null;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
