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
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatus;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececEmpleado;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class Insumo extends BaseModel {

    private static final long serialVersionUID = 5396166451145432902L;

    private BigDecimal idInsumo;
    private BigDecimal idContribuyente;
    private BigDecimal idArace;
    private BigDecimal idSubprograma;
    private BigDecimal idSector;
    private String idRegistro;
    private Date fechaInicioPeriodo;
    private Date fechaFinPeriodo;
    private Boolean prioridad;
    private Date fechaCreacion;
    private Date fechaBaja;
    private String rfcCreacion;
    private String rfcAdministrador;
    private String rfcSubadministrador;
    private BigDecimal idEstatus;
    private BigDecimal idPrioridad;
    private transient InputStream archivo;
    private int sizeListaDocumentos;
    private FececSector fececSector;
    private FececSubprograma fececSubprograma;
    private FececEstatus fececEstatus;
    private FececEmpleado fececEmpleado;
    private FecetReasignacionInsumo fecetReasignacionInsumo;
    private AraceDTO arace;
    private transient FececUnidadAdministrativa fececUnidadAdministrativa;
    private FecetContribuyente fecetContribuyente;
    private List<FecetDocExpInsumo> listaDocumentos;
    private boolean selected;

    private Date fechaNotifCont;
    private Date fechaSurteEfectos;
    private Integer diasRestantesPlazo;
    private Boolean diasHabiles;
    private Boolean suspencionPlazo;
    private Integer diasRestantesDocumentos;
    private Integer semaforo;

    public BigDecimal getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(BigDecimal idInsumo) {
        this.idInsumo = idInsumo;
    }

    public BigDecimal getIdContribuyente() {
        return idContribuyente;
    }

    public void setIdContribuyente(BigDecimal idContribuyente) {
        this.idContribuyente = idContribuyente;
    }

    public BigDecimal getIdArace() {
        return idArace;
    }

    public void setIdArace(BigDecimal idArace) {
        this.idArace = idArace;
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

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Date getFechaInicioPeriodo() {
        return (fechaInicioPeriodo != null) ? (Date) fechaInicioPeriodo.clone() : null;
    }

    public void setFechaInicioPeriodo(Date fechaInicioPeriodo) {
        this.fechaInicioPeriodo = fechaInicioPeriodo != null ? new Date(fechaInicioPeriodo.getTime())
                : null;
    }

    public Date getFechaFinPeriodo() {
        return (fechaFinPeriodo != null) ? (Date) fechaFinPeriodo.clone() : null;
    }

    public void setFechaFinPeriodo(Date fechaFinPeriodo) {
        this.fechaFinPeriodo = fechaFinPeriodo != null ? new Date(fechaFinPeriodo.getTime())
                : null;
    }

    public Boolean getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Boolean prioridad) {
        this.prioridad = prioridad;
    }

    public Date getFechaCreacion() {
        return (fechaCreacion != null) ? (Date) fechaCreacion.clone() : null;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion != null ? new Date(fechaCreacion.getTime())
                : null;
    }

    public Date getFechaBaja() {
        return (fechaBaja != null) ? (Date) fechaBaja.clone() : null;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja != null ? new Date(fechaBaja.getTime())
                : null;
    }

    public String getRfcCreacion() {
        return rfcCreacion;
    }

    public void setRfcCreacion(String rfcCreacion) {
        this.rfcCreacion = rfcCreacion;
    }

    public String getRfcAdministrador() {
        return rfcAdministrador;
    }

    public void setRfcAdministrador(String rfcAdministrador) {
        this.rfcAdministrador = rfcAdministrador;
    }

    public String getRfcSubadministrador() {
        return rfcSubadministrador;
    }

    public void setRfcSubadministrador(String rfcSubadministrador) {
        this.rfcSubadministrador = rfcSubadministrador;
    }

    public BigDecimal getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(BigDecimal idEstatus) {
        this.idEstatus = idEstatus;
    }

    public BigDecimal getIdPrioridad() {
        return idPrioridad;
    }

    public void setIdPrioridad(BigDecimal idPrioridad) {
        this.idPrioridad = idPrioridad;
    }

    public InputStream getArchivo() {
        return archivo;
    }

    public void setArchivo(InputStream archivo) {
        this.archivo = archivo;
    }

    public int getSizeListaDocumentos() {
        return sizeListaDocumentos;
    }

    public void setSizeListaDocumentos(int sizeListaDocumentos) {
        this.sizeListaDocumentos = sizeListaDocumentos;
    }

    public FececSector getFececSector() {
        return fececSector;
    }

    public void setFececSector(FececSector fececSector) {
        this.fececSector = fececSector;
    }

    public FececSubprograma getFececSubprograma() {
        return fececSubprograma;
    }

    public void setFececSubprograma(FececSubprograma fececSubprograma) {
        this.fececSubprograma = fececSubprograma;
    }

    public FececEstatus getFececEstatus() {
        return fececEstatus;
    }

    public void setFececEstatus(FececEstatus fececEstatus) {
        this.fececEstatus = fececEstatus;
    }

    public FececEmpleado getFececEmpleado() {
        return fececEmpleado;
    }

    public void setFececEmpleado(FececEmpleado fececEmpleado) {
        this.fececEmpleado = fececEmpleado;
    }

    public FecetReasignacionInsumo getFecetReasignacionInsumo() {
        return fecetReasignacionInsumo;
    }

    public void setFecetReasignacionInsumo(FecetReasignacionInsumo fecetReasignacionInsumo) {
        this.fecetReasignacionInsumo = fecetReasignacionInsumo;
    }

    public AraceDTO getArace() {
        return arace;
    }

    public void setArace(AraceDTO arace) {
        this.arace = arace;
    }

    public FececUnidadAdministrativa getFececUnidadAdministrativa() {
        return fececUnidadAdministrativa;
    }

    public void setFececUnidadAdministrativa(FececUnidadAdministrativa fececUnidadAdministrativa) {
        this.fececUnidadAdministrativa = fececUnidadAdministrativa;
    }

    public FecetContribuyente getFecetContribuyente() {
        return fecetContribuyente;
    }

    public void setFecetContribuyente(FecetContribuyente fecetContribuyente) {
        this.fecetContribuyente = fecetContribuyente;
    }

    public List<FecetDocExpInsumo> getListaDocumentos() {
        return listaDocumentos;
    }

    public void setListaDocumentos(List<FecetDocExpInsumo> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Date getFechaNotifCont() {
        return (fechaNotifCont != null) ? (Date) fechaNotifCont.clone() : null;
    }

    public void setFechaNotifCont(Date fechaNotifCont) {
        this.fechaNotifCont = fechaNotifCont != null ? new Date(fechaNotifCont.getTime())
                : null;
    }

    public Date getFechaSurteEfectos() {
        return (fechaSurteEfectos != null) ? (Date) fechaSurteEfectos.clone() : null;
    }

    public void setFechaSurteEfectos(Date fechaSurteEfectos) {
        this.fechaSurteEfectos = fechaSurteEfectos != null ? new Date(fechaSurteEfectos.getTime())
                : null;
    }

    public Integer getDiasRestantesPlazo() {
        return diasRestantesPlazo;
    }

    public void setDiasRestantesPlazo(Integer diasRestantesPlazo) {
        this.diasRestantesPlazo = diasRestantesPlazo;
    }

    public Boolean getDiasHabiles() {
        return diasHabiles;
    }

    public void setDiasHabiles(Boolean diasHabiles) {
        this.diasHabiles = diasHabiles;
    }

    public Boolean getSuspencionPlazo() {
        return suspencionPlazo;
    }

    public void setSuspencionPlazo(Boolean suspencionPlazo) {
        this.suspencionPlazo = suspencionPlazo;
    }

    public Integer getDiasRestantesDocumentos() {
        return diasRestantesDocumentos;
    }

    public void setDiasRestantesDocumentos(Integer diasRestantesDocumentos) {
        this.diasRestantesDocumentos = diasRestantesDocumentos;
    }

    public Integer getSemaforo() {
        return semaforo;
    }

    public void setSemaforo(Integer semaforo) {
        this.semaforo = semaforo;
    }

}
