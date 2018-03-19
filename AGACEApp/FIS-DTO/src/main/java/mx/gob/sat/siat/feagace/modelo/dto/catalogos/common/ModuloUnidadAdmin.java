/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dto.catalogos.common;

import java.math.BigDecimal;
import java.util.Date;
import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class ModuloUnidadAdmin extends BaseModel {

    private static final long serialVersionUID = -2714284036906497988L;
    private BigDecimal idModuloUnidad;
    private BigDecimal unidadGeneral;
    private FececModulos modulo;
    private AraceDTO unidadAdministrativa;
    private Date fechaInicio;
    private Date fechaFin;
    private Boolean blnActivo;

    public BigDecimal getIdModuloUnidad() {
        return idModuloUnidad;
    }

    public void setIdModuloUnidad(BigDecimal idModuloUnidad) {
        this.idModuloUnidad = idModuloUnidad;
    }

    public BigDecimal getUnidadGeneral() {
        return unidadGeneral;
    }

    public void setUnidadGeneral(BigDecimal unidadGeneral) {
        this.unidadGeneral = unidadGeneral;
    }

    public FececModulos getModulo() {
        return modulo;
    }

    public void setModulo(FececModulos modulo) {
        this.modulo = modulo;
    }

    public AraceDTO getUnidadAdministrativa() {
        return unidadAdministrativa;
    }

    public void setUnidadAdministrativa(AraceDTO unidadAdministrativa) {
        this.unidadAdministrativa = unidadAdministrativa;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Boolean getBlnActivo() {
        return blnActivo;
    }

    public void setBlnActivo(Boolean blnActivo) {
        this.blnActivo = blnActivo;
    }

    @Override
    public boolean equals(Object obj) {
        try {
            if (obj != null) {
                ModuloUnidadAdmin oblTmp = (ModuloUnidadAdmin) obj;

                if (unidadGeneral != null && oblTmp.getUnidadGeneral() != null && (unidadGeneral.intValue() == oblTmp.unidadGeneral.intValue())) {
                    boolean flgModulo = this.modulo != null && oblTmp.getModulo() != null && (this.modulo.getIdModulo() != null && oblTmp.getModulo().getIdModulo() != null) && (modulo.getIdModulo().intValue() == oblTmp.getModulo().getIdModulo().intValue());
                    if (flgModulo) {
                        return this.unidadAdministrativa != null && oblTmp.getUnidadAdministrativa() != null && (this.unidadAdministrativa.getIdArace()!= null && oblTmp.getUnidadAdministrativa().getIdArace()!= null) && (unidadAdministrativa.getIdArace().intValue() == oblTmp.getUnidadAdministrativa().getIdArace());
                    }
                    return flgModulo;

                }

            }

        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + (this.idModuloUnidad != null ? this.idModuloUnidad.hashCode() : 0);
        hash = 17 * hash + (this.unidadGeneral != null ? this.unidadGeneral.hashCode() : 0);
        hash = 17 * hash + (this.modulo != null ? this.modulo.hashCode() : 0);
        hash = 17 * hash + (this.unidadAdministrativa != null ? this.unidadAdministrativa.hashCode() : 0);
        hash = 17 * hash + (this.fechaInicio != null ? this.fechaInicio.hashCode() : 0);
        hash = 17 * hash + (this.fechaFin != null ? this.fechaFin.hashCode() : 0);
        hash = 17 * hash + (this.blnActivo != null ? this.blnActivo.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "ModuloUnidadAdmin{" + "idModuloUnidad=" + idModuloUnidad + ", unidadGeneral=" + unidadGeneral + ", modulo=" + modulo + ", unidadAdministrativa=" + unidadAdministrativa + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", blnActivo=" + blnActivo + '}';
    }

}
