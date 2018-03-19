package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.util.List;

import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececMotivoSuplencia;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececEmpleado;


public class AsignarSuplenciaAFirmanteModel extends BaseModel {
    private static final long serialVersionUID = 1L;

    private List<FecetSuplenciaDTO> listaSuplencias;
    private List<EmpleadoDTO> comboFirmanteASuplir;
    private List<EmpleadoDTO> combolistaFirmanteSuplente;
    private List<FececMotivoSuplencia> listaMotivoSuplencia;
    private FecetSuplenciaDTO nuevaSuplencia;
    private boolean checkboxValue;
    private EmpleadoDTO firmanteASuplir;
    private EmpleadoDTO firmanteSuplente;

    public void setListaSuplencias(List<FecetSuplenciaDTO> listaSuplencias) {
        this.listaSuplencias = listaSuplencias;
    }

    public List<FecetSuplenciaDTO> getListaSuplencias() {
        return listaSuplencias;
    }


    public void setListaMotivoSuplencia(List<FececMotivoSuplencia> listaMotivoSuplencia) {
        this.listaMotivoSuplencia = listaMotivoSuplencia;
    }

    public List<FececMotivoSuplencia> getListaMotivoSuplencia() {
        return listaMotivoSuplencia;
    }

    public void setCheckboxValue(boolean checkboxValue) {
        this.checkboxValue = checkboxValue;
    }

    public boolean isCheckboxValue() {
        return checkboxValue;
    }

   
    public void setNuevaSuplencia(FecetSuplenciaDTO nuevaSuplencia) {
        this.nuevaSuplencia = nuevaSuplencia;
    }

    public FecetSuplenciaDTO getNuevaSuplencia() {
        return nuevaSuplencia;
    }

    public void setComboFirmanteASuplir(List<EmpleadoDTO> comboFirmanteASuplir) {
        this.comboFirmanteASuplir = comboFirmanteASuplir;
    }

    public List<EmpleadoDTO> getComboFirmanteASuplir() {
        return comboFirmanteASuplir;
    }

    public void setCombolistaFirmanteSuplente(List<EmpleadoDTO> combolistaFirmanteSuplente) {
        this.combolistaFirmanteSuplente = combolistaFirmanteSuplente;
    }

    public List<EmpleadoDTO> getCombolistaFirmanteSuplente() {
        return combolistaFirmanteSuplente;
    }

    public void setFirmanteASuplir(EmpleadoDTO firmanteASuplir) {
        this.firmanteASuplir = firmanteASuplir;
    }

    public EmpleadoDTO getFirmanteASuplir() {
        return firmanteASuplir;
    }

    public void setFirmanteSuplente(EmpleadoDTO firmanteSuplente) {
        this.firmanteSuplente = firmanteSuplente;
    }

    public EmpleadoDTO getFirmanteSuplente() {
        return firmanteSuplente;
    }
}
