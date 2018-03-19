package mx.gob.sat.siat.feagace.vista.model.insumos;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetReasignacionInsumo;

public class AsignarInsumoSubadminDTO extends AsignarInsumoComplexAbstract {

    private static final long serialVersionUID = 1L;

    private FecetInsumo insumoSeleccionado;
    private FecetDocExpInsumo expedienteSeleccionado;
    private EmpleadoDTO administrador;
    private FecetReasignacionInsumo reasignacionInsumoSeleccionada;
    private FecetInsumo seleccionReasignacionInsumoSeleccionado;
    private EmpleadoDTO fececEmpleadoAdministradorSeleccionado;
    private EmpleadoDTO fececEmpleadoSubadministradorSeleccionado;
    private String rfcSubAdminSeleccionado;
    private String administradorSeleccionado;
    private String motivoReasignacion;
    private String motivoRechazo;
    private String mensajeReasignacion;
    private int numeroDeDocumentos;
    private String rutaArchivo;
    private String nombreArchivo;

    public void setInsumoSeleccionado(FecetInsumo insumoSeleccionado) {
        this.insumoSeleccionado = insumoSeleccionado;
    }

    public FecetInsumo getInsumoSeleccionado() {
        return insumoSeleccionado;
    }

    public void setExpedienteSeleccionado(FecetDocExpInsumo expedienteSeleccionado) {
        this.expedienteSeleccionado = expedienteSeleccionado;
    }

    public FecetDocExpInsumo getExpedienteSeleccionado() {
        return expedienteSeleccionado;
    }

    public void setAdministrador(EmpleadoDTO administrador) {
        this.administrador = administrador;
    }

    public EmpleadoDTO getAdministrador() {
        return administrador;
    }

    public void setReasignacionInsumoSeleccionada(FecetReasignacionInsumo reasignacionInsumoSeleccionada) {
        this.reasignacionInsumoSeleccionada = reasignacionInsumoSeleccionada;
    }

    public FecetReasignacionInsumo getReasignacionInsumoSeleccionada() {
        return reasignacionInsumoSeleccionada;
    }

    public void setSeleccionReasignacionInsumoSeleccionado(FecetInsumo seleccionReasignacionInsumoSeleccionado) {
        this.seleccionReasignacionInsumoSeleccionado = seleccionReasignacionInsumoSeleccionado;
    }

    public FecetInsumo getSeleccionReasignacionInsumoSeleccionado() {
        return seleccionReasignacionInsumoSeleccionado;
    }

    public void setFececEmpleadoAdministradorSeleccionado(EmpleadoDTO fececEmpleadoAdministradorSeleccionado) {
        this.fececEmpleadoAdministradorSeleccionado = fececEmpleadoAdministradorSeleccionado;
    }

    public EmpleadoDTO getFececEmpleadoAdministradorSeleccionado() {
        return fececEmpleadoAdministradorSeleccionado;
    }

    public void setFececEmpleadoSubadministradorSeleccionado(EmpleadoDTO fececEmpleadoSubadministradorSeleccionado) {
        this.fececEmpleadoSubadministradorSeleccionado = fececEmpleadoSubadministradorSeleccionado;
    }

    public EmpleadoDTO getFececEmpleadoSubadministradorSeleccionado() {
        return fececEmpleadoSubadministradorSeleccionado;
    }

    public final String getRfcSubAdminSeleccionado() {
        return rfcSubAdminSeleccionado;
    }

    public final void setRfcSubAdminSeleccionado(String rfcSubAdminSeleccionado) {
        this.rfcSubAdminSeleccionado = rfcSubAdminSeleccionado;
    }

    public final String getAdministradorSeleccionado() {
        return administradorSeleccionado;
    }

    public final void setAdministradorSeleccionado(String administradorSeleccionado) {
        this.administradorSeleccionado = administradorSeleccionado;
    }

    public final String getMotivoReasignacion() {
        return motivoReasignacion;
    }

    public final void setMotivoReasignacion(String motivoReasignacion) {
        this.motivoReasignacion = motivoReasignacion;
    }

    public final String getMotivoRechazo() {
        return motivoRechazo;
    }

    public final void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    public final String getMensajeReasignacion() {
        return mensajeReasignacion;
    }

    public final void setMensajeReasignacion(String mensajeReasignacion) {
        this.mensajeReasignacion = mensajeReasignacion;
    }

    public int getNumeroDeDocumentos() {
        return numeroDeDocumentos;
    }

    public void setNumeroDeDocumentos(int numeroDeDocumentos) {
        this.numeroDeDocumentos = numeroDeDocumentos;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

}
