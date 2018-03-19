/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.common;


import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class FecetContribuyente extends BaseModel {


    @SuppressWarnings("compatibility:-2452723711235506996")
    private static final long serialVersionUID = 1L;

    /**
     * This attribute maps to the column ID_CONTRIBUYENTE in the FECET_CONTRIBUYENTE table.
     */
    private BigDecimal idContribuyente;

    /**
     * This attribute maps to the column RFC in the FECET_CONTRIBUYENTE table.
     */
    private String rfc;

    /**
     * This attribute maps to the column NOMBRE in the FECET_CONTRIBUYENTE table.
     */
    private String nombre;

    /**
     * This attribute maps to the column REGIMEN in the FECET_CONTRIBUYENTE table.
     */
    private String regimen;

    /**
     * This attribute maps to the column SITUACION in the FECET_CONTRIBUYENTE table.
     */
    private String situacion;

    /**
     * This attribute maps to the column TIPO in the FECET_CONTRIBUYENTE table.
     */
    private String tipo;

    /**
     * This attribute maps to the column SITUACION_DOMICILIO in the FECET_CONTRIBUYENTE table.
     */
    private String situacionDomicilio;

    /**
     * This attribute maps to the column DOMICILIO_FISCAL in the FECET_CONTRIBUYENTE table.
     */
    private String domicilioFiscal;

    /**
     * This attribute maps to the column ACTIVIDAD_PREPONDERANTE in the FECET_CONTRIBUYENTE table.
     */
    private String actividadPreponderante;

    /**
     * This attribute maps to the column ENTIDAD in the FECET_CONTRIBUYENTE table.
     */
    private String entidad;

    private transient List<RepLegalVO> repLegal;

    private static final int VEINTINUEVE = 29;
    private static final int TRES = 3;

    /**
     * Method 'FecetContribuyente'
     *
     */
    public FecetContribuyente() {
    }

    /**
     * Method 'getIdContribuyente'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdContribuyente() {
        return idContribuyente;
    }

    /**
     * Method 'setIdContribuyente'
     *
     * @param idContribuyente
     */
    public void setIdContribuyente(final BigDecimal idContribuyente) {
        this.idContribuyente = idContribuyente;
    }

    /**
     * Method 'getRfc'
     *
     * @return String
     */
    public String getRfc() {
        return rfc;
    }

    /**
     * Method 'setRfc'
     *
     * @param rfc
     */
    public void setRfc(final String rfc) {
        this.rfc = rfc;
    }

    /**
     * Method 'getNombre'
     *
     * @return String
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Method 'setNombre'
     *
     * @param nombre
     */
    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    /**
     * Method 'getRegimen'
     *
     * @return String
     */
    public String getRegimen() {
        return regimen;
    }

    /**
     * Method 'setRegimen'
     *
     * @param regimen
     */
    public void setRegimen(final String regimen) {
        this.regimen = regimen;
    }

    /**
     * Method 'getSituacion'
     *
     * @return String
     */
    public String getSituacion() {
        return situacion;
    }

    /**
     * Method 'setSituacion'
     *
     * @param situacion
     */
    public void setSituacion(final String situacion) {
        this.situacion = situacion;
    }

    /**
     * Method 'getTipo'
     *
     * @return String
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Method 'setTipo'
     *
     * @param tipo
     */
    public void setTipo(final String tipo) {
        this.tipo = tipo;
    }

    /**
     * Method 'getSituacionDomicilio'
     *
     * @return String
     */
    public String getSituacionDomicilio() {
        return situacionDomicilio;
    }

    /**
     * Method 'setSituacionDomicilio'
     *
     * @param situacionDomicilio
     */
    public void setSituacionDomicilio(final String situacionDomicilio) {
        this.situacionDomicilio = situacionDomicilio;
    }

    /**
     * Method 'getDomicilioFiscal'
     *
     * @return String
     */
    public String getDomicilioFiscal() {
        return domicilioFiscal;
    }

    /**
     * Method 'setDomicilioFiscal'
     *
     * @param domicilioFiscal
     */
    public void setDomicilioFiscal(final String domicilioFiscal) {
        this.domicilioFiscal = domicilioFiscal;
    }

    /**
     * Method 'getActividadPreponderante'
     *
     * @return String
     */
    public String getActividadPreponderante() {
        return actividadPreponderante;
    }

    /**
     * Method 'setActividadPreponderante'
     *
     * @param actividadPreponderante
     */
    public void setActividadPreponderante(final String actividadPreponderante) {
        this.actividadPreponderante = actividadPreponderante;
    }

    /**
     * Method 'getEntidad'
     *
     * @return String
     */
    public String getEntidad() {
        return entidad;
    }

    /**
     * Method 'setEntidad'
     *
     * @param entidad
     */
    public void setEntidad(final String entidad) {
        this.entidad = entidad;
    }

    /**
     * Method 'equals'
     *
     * @param other
     * @return boolean
     */
    public boolean equalsParte1(Object other) {
        if (other == null) {
            return false;
        }

        if (other == this) {
            return true;
        }

        if (!(other instanceof FecetContribuyente)) {
            return false;
        }
        return true;
    }

    public boolean equalsParte2(Object other) {
        final FecetContribuyente cast = (FecetContribuyente)other;
        if (idContribuyente == null ? cast.idContribuyente != idContribuyente :
            !idContribuyente.equals(cast.idContribuyente)) {
            return false;
        }
        return true;
    }

    public boolean equalsParte3(Object other) {
        final FecetContribuyente cast = (FecetContribuyente)other;
        if (rfc == null ? cast.rfc != rfc : !rfc.equals(cast.rfc)) {
            return false;
        }
        return true;
    }

    public boolean equalsParte4(Object other) {
        final FecetContribuyente cast = (FecetContribuyente)other;
        if (nombre == null ? cast.nombre != nombre : !nombre.equals(cast.nombre)) {
            return false;
        }
        return true;
    }

    public boolean equalsParte5(Object other) {
        final FecetContribuyente cast = (FecetContribuyente)other;
        if (regimen == null ? cast.regimen != regimen : !regimen.equals(cast.regimen)) {
            return false;
        }
        return true;
    }

    public boolean equalsParte6(Object other) {
        final FecetContribuyente cast = (FecetContribuyente)other;
        if (situacion == null ? cast.situacion != situacion : !situacion.equals(cast.situacion)) {
            return false;
        }
        return true;
    }

    public boolean equalsParte7(Object other) {
        final FecetContribuyente cast = (FecetContribuyente)other;
        if (tipo == null ? cast.tipo != tipo : !tipo.equals(cast.tipo)) {
            return false;
        }
        return true;
    }

    public boolean equalsParte8(Object other) {
        final FecetContribuyente cast = (FecetContribuyente)other;
        if (situacionDomicilio == null ? cast.situacionDomicilio != situacionDomicilio :
            !situacionDomicilio.equals(cast.situacionDomicilio)) {
            return false;
        }
        return true;
    }

    public boolean equalsParte9(Object other) {
        final FecetContribuyente cast = (FecetContribuyente)other;
        if (domicilioFiscal == null ? cast.domicilioFiscal != domicilioFiscal :
            !domicilioFiscal.equals(cast.domicilioFiscal)) {
            return false;
        }
        return true;
    }

    public boolean equalsParte10(Object other) {
        final FecetContribuyente cast = (FecetContribuyente)other;
        if (actividadPreponderante == null ? cast.actividadPreponderante != actividadPreponderante :
            !actividadPreponderante.equals(cast.actividadPreponderante)) {
            return false;
        }
        return true;
    }

    public boolean equalsParte11(Object other) {
        final FecetContribuyente cast = (FecetContribuyente)other;
        if (entidad == null ? cast.entidad != entidad : !entidad.equals(cast.entidad)) {
            return false;
        }
        return true;
    }

    public boolean equalsALL(Object other) {
        if (equalsFirstPart(other) && equalsSecondPart(other)) {
            return true;
        }
        return false;
    }

    public boolean equalsFirstPart(Object other) {
        final FecetContribuyente cast = (FecetContribuyente)other;
        if (equalsParte3(cast) && equalsParte4(cast) && equalsParte5(cast) && equalsParte6(cast)) {
            return true;
        }
        return false;
    }

    public boolean equalsSecondPart(Object other) {
        final FecetContribuyente cast = (FecetContribuyente)other;
        boolean equal = (equalsParte7(cast) && equalsParte8(cast) && equalsParte9(cast));
        if (equal && equalsParte10(cast) && equalsParte11(cast)) {
            return true;
        }
        return false;
    }

    /**
     * Method 'hashCode'
     *
     * @return int
     */
    public int hashCodeParte1() {
        int hashCode = 0;
        final int posicion = 29;
        if (idContribuyente != null) {
            hashCode = posicion * hashCode + idContribuyente.hashCode();
        }

        if (rfc != null) {
            hashCode = posicion * hashCode + rfc.hashCode();
        }

        if (nombre != null) {
            hashCode = posicion * hashCode + nombre.hashCode();
        }

        if (regimen != null) {
            hashCode = posicion * hashCode + regimen.hashCode();
        }

        if (situacion != null) {
            hashCode = posicion * hashCode + situacion.hashCode();
        }
        return hashCode;
    }

    public int hashCodeParte2() {
        int hashCode = 0;
        final int posicion = 29;
        if (tipo != null) {
            hashCode = posicion * hashCode + tipo.hashCode();
        }

        if (situacionDomicilio != null) {
            hashCode = posicion * hashCode + situacionDomicilio.hashCode();
        }

        if (domicilioFiscal != null) {
            hashCode = posicion * hashCode + domicilioFiscal.hashCode();
        }

        if (actividadPreponderante != null) {
            hashCode = posicion * hashCode + actividadPreponderante.hashCode();
        }

        if (entidad != null) {
            hashCode = posicion * hashCode + entidad.hashCode();
        }

        return hashCode;
    }

    /**
     * Method 'createPk'
     *
     * @return FecetContribuyentePk
     */
    public FecetContribuyentePk createPk() {
        return new FecetContribuyentePk(idContribuyente);
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FecetContribuyente: ");
        ret.append("idContribuyente=" + idContribuyente);
        ret.append(", rfc=" + rfc);
        ret.append(", nombre=" + nombre);
        ret.append(", regimen=" + regimen);
        ret.append(", situacion=" + situacion);
        ret.append(", tipo=" + tipo);
        ret.append(", situacionDomicilio=" + situacionDomicilio);
        ret.append(", domicilioFiscal=" + domicilioFiscal);
        ret.append(", actividadPreponderante=" + actividadPreponderante);
        ret.append(", entidad=" + entidad);
        return ret.toString();
    }

    public void setRepLegal(List<RepLegalVO> repLegal) {
        this.repLegal = repLegal;
    }

    public List<RepLegalVO> getRepLegal() {
        return repLegal;
    }

    @Override
    public int hashCode() {
        int hash = TRES;
        hash = VEINTINUEVE * hash + (this.idContribuyente != null ? this.idContribuyente.hashCode() : 0);
        hash = VEINTINUEVE * hash + (this.rfc != null ? this.rfc.hashCode() : 0);
        hash = VEINTINUEVE * hash + (this.nombre != null ? this.nombre.hashCode() : 0);
        hash = VEINTINUEVE * hash + (this.regimen != null ? this.regimen.hashCode() : 0);
        hash = VEINTINUEVE * hash + (this.situacion != null ? this.situacion.hashCode() : 0);
        hash = VEINTINUEVE * hash + (this.tipo != null ? this.tipo.hashCode() : 0);
        hash = VEINTINUEVE * hash + (this.situacionDomicilio != null ? this.situacionDomicilio.hashCode() : 0);
        hash = VEINTINUEVE * hash + (this.domicilioFiscal != null ? this.domicilioFiscal.hashCode() : 0);
        hash = VEINTINUEVE * hash + (this.actividadPreponderante != null ? this.actividadPreponderante.hashCode() : 0);
        hash = VEINTINUEVE * hash + (this.entidad != null ? this.entidad.hashCode() : 0);
        hash = VEINTINUEVE * hash + (this.repLegal != null ? this.repLegal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FecetContribuyente other = (FecetContribuyente) obj;
        if ((this.rfc == null) ? (other.rfc != null) : !this.rfc.equals(other.rfc)) {
            return false;
        }
        if ((this.nombre == null) ? (other.nombre != null) : !this.nombre.equals(other.nombre)) {
            return false;
        }
        if ((this.regimen == null) ? (other.regimen != null) : !this.regimen.equals(other.regimen)) {
            return false;
        }
        if ((this.situacion == null) ? (other.situacion != null) : !this.situacion.equals(other.situacion)) {
            return false;
        }
        if ((this.tipo == null) ? (other.tipo != null) : !this.tipo.equals(other.tipo)) {
            return false;
        }
        if ((this.situacionDomicilio == null) ? (other.situacionDomicilio != null) : !this.situacionDomicilio.equals(other.situacionDomicilio)) {
            return false;
        }
        if ((this.domicilioFiscal == null) ? (other.domicilioFiscal != null) : !this.domicilioFiscal.equals(other.domicilioFiscal)) {
            return false;
        }
        if ((this.actividadPreponderante == null) ? (other.actividadPreponderante != null) : !this.actividadPreponderante.equals(other.actividadPreponderante)) {
            return false;
        }
        if ((this.entidad == null) ? (other.entidad != null) : !this.entidad.equals(other.entidad)) {
            return false;
        }
        return true;
    }

}
