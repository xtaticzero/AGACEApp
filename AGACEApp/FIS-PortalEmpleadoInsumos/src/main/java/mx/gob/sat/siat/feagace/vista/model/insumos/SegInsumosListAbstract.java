package mx.gob.sat.siat.feagace.vista.model.insumos;

import java.io.Serializable;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececTipoInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetContadorInsumos;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetContadorInsumosRechazados;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocrechazoinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocretroinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRetroalimentacionInsumo;

public class SegInsumosListAbstract implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private List<FececSector> listaSector;
    private List<FececSubprograma> listaSubprograma;
    private List<FececTipoInsumo> listaTipoInsumo;
    private List<FecetRetroalimentacionInsumo> insumosRetroalimentadosContador;
    private List<FecetContadorInsumos> insumosRetroalimentadosRechazosContador;
    private List<FecetContadorInsumos> listaContadoresInsumoRetroPendiente;
    private List<FecetContadorInsumosRechazados> listaContadorRechazo;
    private List<FecetDocretroinsumo> listaDocretroInsumoCargados;
    private List<FecetRetroalimentacionInsumo> listaDocretroRechazoInsumoCargados;
    private List<FecetDocretroinsumo> listaDocretroPendientes;
    private List<FecetDocrechazoinsumo> listaDocRechazoInsumo;
    private List<FecetInsumo> filtrados;
    
    public void setListaSector(List<FececSector> listaSector) {
        this.listaSector = listaSector;
    }

    public List<FececSector> getListaSector() {
        return listaSector;
    }

    public void setListaSubprograma(List<FececSubprograma> listaSubprograma) {
        this.listaSubprograma = listaSubprograma;
    }

    public List<FececSubprograma> getListaSubprograma() {
        return listaSubprograma;
    }

    public void setInsumosRetroalimentadosContador(
            List<FecetRetroalimentacionInsumo> insumosRetroalimentadosContador) {
        this.insumosRetroalimentadosContador = insumosRetroalimentadosContador;
    }

    public List<FecetRetroalimentacionInsumo> getInsumosRetroalimentadosContador() {
        return insumosRetroalimentadosContador;
    }

    public void setInsumosRetroalimentadosRechazosContador(
            List<FecetContadorInsumos> insumosRetroalimentadosRechazosContador) {
        this.insumosRetroalimentadosRechazosContador = insumosRetroalimentadosRechazosContador;
    }

    public List<FecetContadorInsumos> getInsumosRetroalimentadosRechazosContador() {
        return insumosRetroalimentadosRechazosContador;
    }

    public void setListaContadoresInsumoRetroPendiente(
            List<FecetContadorInsumos> listaContadoresInsumoRetroPendiente) {
        this.listaContadoresInsumoRetroPendiente = listaContadoresInsumoRetroPendiente;
    }

    public List<FecetContadorInsumos> getListaContadoresInsumoRetroPendiente() {
        return listaContadoresInsumoRetroPendiente;
    }

    public void setListaContadorRechazo(List<FecetContadorInsumosRechazados> listaContadorRechazo) {
        this.listaContadorRechazo = listaContadorRechazo;
    }

    public List<FecetContadorInsumosRechazados> getListaContadorRechazo() {
        return listaContadorRechazo;
    }

    public void setListaDocretroInsumoCargados(List<FecetDocretroinsumo> listaDocretroInsumoCargados) {
        this.listaDocretroInsumoCargados = listaDocretroInsumoCargados;
    }

    public List<FecetDocretroinsumo> getListaDocretroInsumoCargados() {
        return listaDocretroInsumoCargados;
    }

    public void setListaDocretroRechazoInsumoCargados(
            List<FecetRetroalimentacionInsumo> listaDocretroRechazoInsumoCargados) {
        this.listaDocretroRechazoInsumoCargados = listaDocretroRechazoInsumoCargados;
    }

    public List<FecetRetroalimentacionInsumo> getListaDocretroRechazoInsumoCargados() {
        return listaDocretroRechazoInsumoCargados;
    }

    public void setListaDocretroPendientes(List<FecetDocretroinsumo> listaDocretroPendientes) {
        this.listaDocretroPendientes = listaDocretroPendientes;
    }

    public List<FecetDocretroinsumo> getListaDocretroPendientes() {
        return listaDocretroPendientes;
    }

    public void setListaDocRechazoInsumo(List<FecetDocrechazoinsumo> listaDocRechazoInsumo) {
        this.listaDocRechazoInsumo = listaDocRechazoInsumo;
    }

    public List<FecetDocrechazoinsumo> getListaDocRechazoInsumo() {
        return listaDocRechazoInsumo;
    }

    public final List<FecetInsumo> getFiltrados() {
        return filtrados;
    }

    public final void setFiltrados(List<FecetInsumo> filtrados) {
        this.filtrados = filtrados;
    }

    public List<FececTipoInsumo> getListaTipoInsumo() {
        return listaTipoInsumo;
    }

    public void setListaTipoInsumo(List<FececTipoInsumo> listaTipoInsumo) {
        this.listaTipoInsumo = listaTipoInsumo;
    }
}
