package mx.gob.sat.siat.feagace.modelo.dto.reportes;

import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;

public class TipoReportesDTO extends BaseModel {

    @SuppressWarnings("compatibility:3899550258733233270")
    private static final long serialVersionUID = 1L;

    private boolean mostrarGerencial;
    private boolean mostrarEjecutivo;
    private String tituloReporte;
    private String archivoJasper;
    private EmpleadoDTO fececEmpleado;
    private String moduloReporte;

    public void setMostrarGerencial(boolean mostrarGerencial) {
        this.mostrarGerencial = mostrarGerencial;
    }

    public boolean isMostrarGerencial() {
        return mostrarGerencial;
    }

    public void setMostrarEjecutivo(boolean mostrarEjecutivo) {
        this.mostrarEjecutivo = mostrarEjecutivo;
    }

    public boolean isMostrarEjecutivo() {
        return mostrarEjecutivo;
    }

    public void setTituloReporte(String tituloReporte) {
        this.tituloReporte = tituloReporte;
    }

    public String getTituloReporte() {
        return tituloReporte;
    }

    public void setArchivoJasper(String archivoJasper) {
        this.archivoJasper = archivoJasper;
    }

    public String getArchivoJasper() {
        return archivoJasper;
    }

    public void setFececEmpleado(EmpleadoDTO fececEmpleado) {
        this.fececEmpleado = fececEmpleado;
    }

    public EmpleadoDTO getFececEmpleado() {
        return fececEmpleado;
    }

    public void setModuloReporte(String moduloReporte) {
        this.moduloReporte = moduloReporte;
    }

    public String getModuloReporte() {
        return moduloReporte;
    }
}
