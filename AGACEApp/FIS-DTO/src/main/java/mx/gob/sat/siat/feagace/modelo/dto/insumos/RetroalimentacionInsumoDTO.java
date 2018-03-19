/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dto.insumos;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececPrioridad;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececMotivo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.PrioridadDTO;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class RetroalimentacionInsumoDTO extends BaseModel {


    private static final long serialVersionUID = 7687992111070474938L;
    private static final int NUM_5 = 5;
    private static final int NUM_97 = 97;

    private BigDecimal idRetroalimentacionInsumo;
    private BigDecimal idInsumo;
    private BigDecimal idMotivo;
    private boolean idMotivoNull;
    private String motivoAciace;
    private String motivoSubadministrador;
    private Date fechaCreacion;
    private String rfcRetroalimentacion;
    private Date fechaRetroalimentacion;
    private String estatus;
    private String rfcRechazo;
    private Date fechaRechazo;
    private String descripcionRechazo;
    private FececMotivo fececMotivo;
    private BigDecimal idUnidadAdministrativa;
    private BigDecimal idSubprograma;
    private BigDecimal idSector;
    private Date fechaInicioPeriodo;
    private Date fechaFinPeriodo;
    private BigDecimal idPrioridad;
    private PrioridadDTO prioridadDto;
    private String valorPrioridad;
    private transient InputStream archivo;
    private String descripcionSubprograma;
    private String descripcionSector;
    private BigDecimal numeroSolicitudes;
    private BigDecimal numeroSolicitudesRetro;
    private String nombreUnidadAdministrativa;
    private FececSector sector;
    private FececSubprograma subprograma;
    private FececPrioridad prioridad;
    private List<FecetDocretroinsumo> lstDocsRetroalimentacion;
    private List<FecetDocretroinsumo> lstDocsSolicitudRetroalimentacion;

    public RetroalimentacionInsumoDTO() {
        idMotivoNull = true;
    }

    public BigDecimal getIdRetroalimentacionInsumo() {
        return idRetroalimentacionInsumo;
    }

    public void setIdRetroalimentacionInsumo(BigDecimal idRetroalimentacionInsumo) {
        this.idRetroalimentacionInsumo = idRetroalimentacionInsumo;
    }

    public BigDecimal getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(BigDecimal idInsumo) {
        this.idInsumo = idInsumo;
    }

    public BigDecimal getIdMotivo() {
        return idMotivo;
    }

    public void setIdMotivo(BigDecimal idMotivo) {
        this.idMotivo = idMotivo;
    }

    public boolean isIdMotivoNull() {
        return idMotivoNull;
    }

    public void setIdMotivoNull(boolean idMotivoNull) {
        this.idMotivoNull = idMotivoNull;
    }

    public String getMotivoAciace() {
        return motivoAciace;
    }

    public void setMotivoAciace(String motivoAciace) {
        this.motivoAciace = motivoAciace;
    }

    public String getMotivoSubadministrador() {
        return motivoSubadministrador;
    }

    public void setMotivoSubadministrador(String motivoSubadministrador) {
        this.motivoSubadministrador = motivoSubadministrador;
    }

    public Date getFechaCreacion() {
        return (fechaCreacion != null) ? (Date) fechaCreacion.clone() : null;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion != null ? new Date(fechaCreacion.getTime()) : null;
    }

    public String getRfcRetroalimentacion() {
        return rfcRetroalimentacion;
    }

    public void setRfcRetroalimentacion(String rfcRetroalimentacion) {
        this.rfcRetroalimentacion = rfcRetroalimentacion;
    }

    public Date getFechaRetroalimentacion() {
        return (fechaRetroalimentacion != null) ? (Date) fechaRetroalimentacion.clone() : null;
    }

    public void setFechaRetroalimentacion(Date fechaRetroalimentacion) {
        this.fechaRetroalimentacion = fechaRetroalimentacion != null ? new Date(fechaRetroalimentacion.getTime()) : null;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getRfcRechazo() {
        return rfcRechazo;
    }

    public void setRfcRechazo(String rfcRechazo) {
        this.rfcRechazo = rfcRechazo;
    }

    public Date getFechaRechazo() {
        return (fechaRechazo != null) ? (Date) fechaRechazo.clone() : null;
    }

    public void setFechaRechazo(Date fechaRechazo) {
        this.fechaRechazo = fechaRechazo != null ? new Date(fechaRechazo.getTime()) : null;
    }

    public String getDescripcionRechazo() {
        return descripcionRechazo;
    }

    public void setDescripcionRechazo(String descripcionRechazo) {
        this.descripcionRechazo = descripcionRechazo;
    }

    public FececMotivo getFececMotivo() {
        return fececMotivo;
    }

    public void setFececMotivo(FececMotivo fececMotivo) {
        this.fececMotivo = fececMotivo;
    }

    public BigDecimal getIdUnidadAdministrativa() {
        return idUnidadAdministrativa;
    }

    public void setIdUnidadAdministrativa(BigDecimal idUnidadAdministrativa) {
        this.idUnidadAdministrativa = idUnidadAdministrativa;
    }

    public BigDecimal getIdSubprograma() {
        return idSubprograma;
    }

    public void setIdSubprograma(BigDecimal idSubprograma) {
        this.idSubprograma = idSubprograma;
    }

    public BigDecimal getIdSector() {
        return idSector;
    }

    public void setIdSector(BigDecimal idSector) {
        this.idSector = idSector;
    }

    public Date getFechaInicioPeriodo() {
        return (fechaInicioPeriodo != null) ? (Date) fechaInicioPeriodo.clone() : null;
    }

    public void setFechaInicioPeriodo(Date fechaInicioPeriodo) {
        this.fechaInicioPeriodo = fechaInicioPeriodo != null ? new Date(fechaInicioPeriodo.getTime()) : null;
    }

    public Date getFechaFinPeriodo() {
        return (fechaFinPeriodo != null) ? (Date) fechaFinPeriodo.clone() : null;
    }

    public void setFechaFinPeriodo(Date fechaFinPeriodo) {
        this.fechaFinPeriodo = fechaFinPeriodo != null ? new Date(fechaFinPeriodo.getTime()) : null;
    }

    public BigDecimal getIdPrioridad() {
        return idPrioridad;
    }

    public void setIdPrioridad(BigDecimal idPrioridad) {
        this.idPrioridad = idPrioridad;
    }

    public String getValorPrioridad() {
        return valorPrioridad;
    }

    public void setValorPrioridad(String valorPrioridad) {
        this.valorPrioridad = valorPrioridad;
    }

    public InputStream getArchivo() {
        return archivo;
    }

    public void setArchivo(InputStream archivo) {
        this.archivo = archivo;
    }

    public String getDescripcionSubprograma() {
        return descripcionSubprograma;
    }

    public void setDescripcionSubprograma(String descripcionSubprograma) {
        this.descripcionSubprograma = descripcionSubprograma;
    }

    public String getDescripcionSector() {
        return descripcionSector;
    }

    public void setDescripcionSector(String descripcionSector) {
        this.descripcionSector = descripcionSector;
    }

    public BigDecimal getNumeroSolicitudes() {
        return numeroSolicitudes;
    }

    public void setNumeroSolicitudes(BigDecimal numeroSolicitudes) {
        this.numeroSolicitudes = numeroSolicitudes;
    }

    public BigDecimal getNumeroSolicitudesRetro() {
        return numeroSolicitudesRetro;
    }

    public void setNumeroSolicitudesRetro(BigDecimal numeroSolicitudesRetro) {
        this.numeroSolicitudesRetro = numeroSolicitudesRetro;
    }

    public String getNombreUnidadAdministrativa() {
        return nombreUnidadAdministrativa;
    }

    public void setNombreUnidadAdministrativa(String nombreUnidadAdministrativa) {
        this.nombreUnidadAdministrativa = nombreUnidadAdministrativa;
    }

    public List<FecetDocretroinsumo> getLstDocsRetroalimentacion() {
        return lstDocsRetroalimentacion;
    }

    public void setLstDocsRetroalimentacion(List<FecetDocretroinsumo> lstDocsRetroalimentacion) {
        this.lstDocsRetroalimentacion = lstDocsRetroalimentacion;
    }

    public PrioridadDTO getPrioridadDto() {
        return prioridadDto;
    }

    public void setPrioridadDto(PrioridadDTO prioridadDto) {
        this.prioridadDto = prioridadDto;
    }

    public List<FecetDocretroinsumo> getLstDocsSolicitudRetroalimentacion() {
        return lstDocsSolicitudRetroalimentacion;
    }

    public void setLstDocsSolicitudRetroalimentacion(List<FecetDocretroinsumo> lstDocsSolicitudRetroalimentacion) {
        this.lstDocsSolicitudRetroalimentacion = lstDocsSolicitudRetroalimentacion;
    }

    public FececSector getSector() {
        return sector;
    }

    public void setSector(FececSector sector) {
        this.sector = sector;
    }

    public FececSubprograma getSubprograma() {
        return subprograma;
    }

    public void setSubprograma(FececSubprograma subprograma) {
        this.subprograma = subprograma;
    }

    public FececPrioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(FececPrioridad prioridad) {
        this.prioridad = prioridad;
    }

    public int getNoDocsRetroalimentacion() {
        if (lstDocsRetroalimentacion != null && !lstDocsRetroalimentacion.isEmpty()) {
            return lstDocsRetroalimentacion.size();
        }
        return 0;
    }

    public int getNoDocsSolicitudRetroalimentacion() {
        if (lstDocsSolicitudRetroalimentacion != null && !lstDocsSolicitudRetroalimentacion.isEmpty()) {
            return lstDocsSolicitudRetroalimentacion.size();
        }
        return 0;
    }

    @Override
    public int hashCode() {
        int hash = NUM_5;
        hash = NUM_97 * hash + (this.idRetroalimentacionInsumo != null ? this.idRetroalimentacionInsumo.hashCode() : 0);
        hash = 97 * hash + (this.idInsumo != null ? this.idInsumo.hashCode() : 0);
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
        final RetroalimentacionInsumoDTO other = (RetroalimentacionInsumoDTO) obj;
        if (this.idRetroalimentacionInsumo != other.idRetroalimentacionInsumo
                && (this.idRetroalimentacionInsumo == null || !this.idRetroalimentacionInsumo.equals(other.idRetroalimentacionInsumo))) {
            return false;
        }
        if (this.idInsumo != other.idInsumo && (this.idInsumo == null || !this.idInsumo.equals(other.idInsumo))) {
            return false;
        }
        return true;
    }

}
