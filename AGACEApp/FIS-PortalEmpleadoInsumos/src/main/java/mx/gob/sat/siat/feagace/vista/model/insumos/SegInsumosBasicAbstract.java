package mx.gob.sat.siat.feagace.vista.model.insumos;

import java.io.Serializable;

import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

public class SegInsumosBasicAbstract implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean muestraSeccion = true;
    private boolean muestraEstatus;
    private boolean muestraMotivoRechazo;
    private boolean muestraInsumosRetroalimentados;
    private boolean atenderRetroalimentacion;
    private boolean muestraHistoricoRechazo;
    private Boolean mostrarTablaArchivosRetro;
    private Boolean mostrarTablaArchivosRetroRechazos;
    private Integer idEstatusSeleccionado;
    private String folioSeleccionado;
    private String txtMotivoRetro;
    private String mensajeAntecedentes;

    private FecetInsumo insumoSeleccionado;
    
    public void setMuestraHistoricoRechazo(boolean muestraHistoricoRechazo) {
        this.muestraHistoricoRechazo = muestraHistoricoRechazo;
    }

    public boolean isMuestraHistoricoRechazo() {
        if (getInsumoSeleccionado() == null) {
            this.muestraHistoricoRechazo = false;
            return muestraHistoricoRechazo;
        }
        if (getInsumoSeleccionado().getFececEstatus().getIdEstatus().equals(Constantes.INSUMO_RECHAZADO)) {
            this.muestraHistoricoRechazo = true;
        } else {
            this.muestraHistoricoRechazo = false;
        }
        return muestraHistoricoRechazo;
    }

    public void setMostrarTablaArchivosRetro(Boolean mostrarTablaArchivosRetro) {
        this.mostrarTablaArchivosRetro = mostrarTablaArchivosRetro;
    }

    public Boolean getMostrarTablaArchivosRetro() {
        return mostrarTablaArchivosRetro;
    }

    public void setMostrarTablaArchivosRetroRechazos(Boolean mostrarTablaArchivosRetroRechazos) {
        this.mostrarTablaArchivosRetroRechazos = mostrarTablaArchivosRetroRechazos;
    }

    public Boolean getMostrarTablaArchivosRetroRechazos() {
        return mostrarTablaArchivosRetroRechazos;
    }

    public void setMuestraSeccion(boolean muestraSeccion) {
        this.muestraSeccion = muestraSeccion;
    }

    public boolean isMuestraSeccion() {
        return muestraSeccion;
    }

    public void setMuestraEstatus(boolean muestraEstatus) {
        this.muestraEstatus = muestraEstatus;
    }

    public boolean isMuestraEstatus() {
        return muestraEstatus;
    }

    public void setMuestraMotivoRechazo(boolean muestraMotivoRechazo) {
        this.muestraMotivoRechazo = muestraMotivoRechazo;
    }

    public boolean isMuestraMotivoRechazo() {
        return muestraMotivoRechazo;
    }

    public void setMuestraInsumosRetroalimentados(boolean muestraInsumosRetroalimentados) {
        this.muestraInsumosRetroalimentados = muestraInsumosRetroalimentados;
    }

    public boolean isMuestraInsumosRetroalimentados() {
        return muestraInsumosRetroalimentados;
    }

    public void setFolioSeleccionado(String folioSeleccionado) {
        this.folioSeleccionado = folioSeleccionado;
    }

    public String getFolioSeleccionado() {
        return folioSeleccionado;
    }

    public void setIdEstatusSeleccionado(Integer idEstatusSeleccionado) {
        this.idEstatusSeleccionado = idEstatusSeleccionado;
    }

    public Integer getIdEstatusSeleccionado() {
        return idEstatusSeleccionado;
    }

    public boolean isAtenderRetroalimentacion() {
        return atenderRetroalimentacion;
    }

    public void setAtenderRetroalimentacion(boolean atenderRetroalimentacion) {
        this.atenderRetroalimentacion = atenderRetroalimentacion;
    }

    public String getTxtMotivoRetro() {
        return txtMotivoRetro;
    }

    public void setTxtMotivoRetro(String txtMotivoRetro) {
        this.txtMotivoRetro = txtMotivoRetro;
    }

    public String getMensajeAntecedentes() {
        return mensajeAntecedentes;
    }

    public void setMensajeAntecedentes(String mensajeAntecedentes) {
        this.mensajeAntecedentes = mensajeAntecedentes;
    }
    
    public void setInsumoSeleccionado(FecetInsumo insumoSeleccionado) {
        this.insumoSeleccionado = insumoSeleccionado;
    }

    public FecetInsumo getInsumoSeleccionado() {
        return insumoSeleccionado;
    }
}
