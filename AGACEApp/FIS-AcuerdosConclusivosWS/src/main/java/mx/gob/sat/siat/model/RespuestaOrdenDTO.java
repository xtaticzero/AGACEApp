package mx.gob.sat.siat.model;

import java.io.Serializable;
import java.util.Date;

public class RespuestaOrdenDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String numeroOrden;
    private String rfcContribuyente;
    private String nombreContribuyente;
    private String estatus;
    private Date vencimiento;
    private String tipoOrden;
    private String unidadAdministrativa;
    private Date ejercicioInicio;
    private Date ejercicioFin;
    private String actividadPreponderante;

    /**
     * @return the numeroOrden
     */
    public String getNumeroOrden() {
        return numeroOrden;
    }

    /**
     * @param numeroOrden
     *            the numeroOrden to set
     */
    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    /**
     * @return the rfcContribuyente
     */
    public String getRfcContribuyente() {
        return rfcContribuyente;
    }

    /**
     * @param rfcContribuyente
     *            the rfcContribuyente to set
     */
    public void setRfcContribuyente(String rfcContribuyente) {
        this.rfcContribuyente = rfcContribuyente;
    }

    /**
     * @return the nombreContribuyente
     */
    public String getNombreContribuyente() {
        return nombreContribuyente;
    }

    /**
     * @param nombreContribuyente
     *            the nombreContribuyente to set
     */
    public void setNombreContribuyente(String nombreContribuyente) {
        this.nombreContribuyente = nombreContribuyente;
    }

    /**
     * @return the estatus
     */
    public String getEstatus() {
        return estatus;
    }

    /**
     * @param estatus
     *            the estatus to set
     */
    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    /**
     * @return the vencimiento
     */
    public Date getVencimiento() {
        return (vencimiento != null) ? (Date) vencimiento.clone() : null;
    }

    /**
     * @param vencimiento
     *            the vencimiento to set
     */
    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento != null ? new Date(vencimiento.getTime()) : null;
    }

    /**
     * @return the tipoOrden
     */
    public String getTipoOrden() {
        return tipoOrden;
    }

    /**
     * @param tipoOrden
     *            the tipoOrden to set
     */
    public void setTipoOrden(String tipoOrden) {
        this.tipoOrden = tipoOrden;
    }

    /**
     * @return the unidadAdministrativa
     */
    public String getUnidadAdministrativa() {
        return unidadAdministrativa;
    }

    /**
     * @param unidadAdministrativa
     *            the unidadAdministrativa to set
     */
    public void setUnidadAdministrativa(String unidadAdministrativa) {
        this.unidadAdministrativa = unidadAdministrativa;
    }

    /**
     * @return the ejercicioInicio
     */
    public Date getEjercicioInicio() {
        return (ejercicioInicio != null) ? (Date) ejercicioInicio.clone() : null;
    }

    /**
     * @param ejercicioInicio
     *            the ejercicioInicio to set
     */
    public void setEjercicioInicio(Date ejercicioInicio) {
        this.ejercicioInicio = ejercicioInicio != null ? new Date(ejercicioInicio.getTime()) : null;
    }

    /**
     * @return the ejercicioFin
     */
    public Date getEjercicioFin() {
        return (ejercicioFin != null) ? (Date) ejercicioFin.clone() : null;
    }

    /**
     * @param ejercicioFin
     *            the ejercicioFin to set
     */
    public void setEjercicioFin(Date ejercicioFin) {
        this.ejercicioFin = ejercicioFin != null ? new Date(ejercicioFin.getTime()) : null;
    }

    /**
     * @return the actividadPreponderante
     */
    public String getActividadPreponderante() {
        return actividadPreponderante;
    }

    /**
     * @param actividadPreponderante
     *            the actividadPreponderante to set
     */
    public void setActividadPreponderante(String actividadPreponderante) {
        this.actividadPreponderante = actividadPreponderante;
    }

}
