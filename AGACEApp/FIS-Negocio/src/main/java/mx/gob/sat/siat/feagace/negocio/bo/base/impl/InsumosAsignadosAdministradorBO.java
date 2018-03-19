package mx.gob.sat.siat.feagace.negocio.bo.base.impl;

import mx.gob.sat.siat.feagace.negocio.bo.base.BO;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mx.gob.sat.siat.feagace.modelo.dto.insumos.ContadorAsignadosAdministrador;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.ContadorAsignadosAdministradorEstado;

public class InsumosAsignadosAdministradorBO extends
        BO<InsumosAsignadosAdministradorBO> {

    private Map<String, List<ContadorAsignadosAdministrador>> data;
    private Iterator<ContadorAsignadosAdministrador> iterator;
    private List<ContadorAsignadosAdministradorEstado> contadorAsignadosAdministradorEstados;

    public List<ContadorAsignadosAdministradorEstado> getContadorAsignadosAdministradorEstados() {
        return contadorAsignadosAdministradorEstados;
    }

    public void setContadorAsignadosAdministradorEstados(
            List<ContadorAsignadosAdministradorEstado> contadorAsignadosAdministradorEstados) {
        this.contadorAsignadosAdministradorEstados = contadorAsignadosAdministradorEstados;
    }

    public Iterator<ContadorAsignadosAdministrador> getIterator() {
        return iterator;
    }

    public void setIterator(Iterator<ContadorAsignadosAdministrador> iterator) {
        this.iterator = iterator;
    }

    public Map<String, List<ContadorAsignadosAdministrador>> getData() {
        return data;
    }

    public void setData(Map<String, List<ContadorAsignadosAdministrador>> data) {
        this.data = data;
    }

}