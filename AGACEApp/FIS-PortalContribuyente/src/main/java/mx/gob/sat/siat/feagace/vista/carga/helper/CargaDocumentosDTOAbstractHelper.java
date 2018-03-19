package mx.gob.sat.siat.feagace.vista.carga.helper;

import java.io.Serializable;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAlegatoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;

public class CargaDocumentosDTOAbstractHelper implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private FecetProrrogaOficio prorrogaOficioSeleccionada;
    private FecetDocProrrogaOficio docProrrogaOficioSeleccionado;
    private List<FecetPromocion> listaPromocionesCargadas;
    private List<FecetAlegato> listaPruebasAlegatosCargadas;
    private List<FecetPromocionOficio> listaHistoricoPromocionesOficio;
    private List<FecetProrrogaOficio> listaProrrogaOficio;
    private List<FecetDocProrrogaOficio> listaDocProrrogaOficio;
    private List<FecetAlegatoOficio> listaHistoricoPruebasAlegatos;
    private List<FecetPromocionOficio> listaPromocionesOficioCargadas;
    private List<FecetAlegatoOficio> listaPruebasAlegatosOficioCargadas;
    
    public void setProrrogaOficioSeleccionada(FecetProrrogaOficio prorrogaOficioSeleccionada) {
        this.prorrogaOficioSeleccionada = prorrogaOficioSeleccionada;
    }

    public FecetProrrogaOficio getProrrogaOficioSeleccionada() {
        return prorrogaOficioSeleccionada;
    }

    public void setListaDocProrrogaOficio(List<FecetDocProrrogaOficio> listaDocProrrogaOficio) {
        this.listaDocProrrogaOficio = listaDocProrrogaOficio;
    }

    public List<FecetDocProrrogaOficio> getListaDocProrrogaOficio() {
        return listaDocProrrogaOficio;
    }

    public void setDocProrrogaOficioSeleccionado(FecetDocProrrogaOficio docProrrogaOficioSeleccionado) {
        this.docProrrogaOficioSeleccionado = docProrrogaOficioSeleccionado;
    }

    public FecetDocProrrogaOficio getDocProrrogaOficioSeleccionado() {
        return docProrrogaOficioSeleccionado;
    }
    
    public void setListaProrrogaOficio(List<FecetProrrogaOficio> listaProrrogaOficio) {
        this.listaProrrogaOficio = listaProrrogaOficio;
    }

    public List<FecetProrrogaOficio> getListaProrrogaOficio() {
        return listaProrrogaOficio;
    }

    public void setListaHistoricoPruebasAlegatos(List<FecetAlegatoOficio> listaHistoricoPruebasAlegatos) {
        this.listaHistoricoPruebasAlegatos = listaHistoricoPruebasAlegatos;
    }

    public List<FecetAlegatoOficio> getListaHistoricoPruebasAlegatos() {
        return listaHistoricoPruebasAlegatos;
    }
    
    public void setListaHistoricoPromocionesOficio(List<FecetPromocionOficio> listaHistoricoPromocionesOficio) {
        this.listaHistoricoPromocionesOficio = listaHistoricoPromocionesOficio;
    }

    public List<FecetPromocionOficio> getListaHistoricoPromocionesOficio() {
        return listaHistoricoPromocionesOficio;
    }
    
    public void setListaPromocionesOficioCargadas(List<FecetPromocionOficio> listaPromocionesOficioCargadas) {
        this.listaPromocionesOficioCargadas = listaPromocionesOficioCargadas;
    }

    public List<FecetPromocionOficio> getListaPromocionesOficioCargadas() {
        return listaPromocionesOficioCargadas;
    }

    public void setListaPruebasAlegatosOficioCargadas(List<FecetAlegatoOficio> listaPruebasAlegatosOficioCargadas) {
        this.listaPruebasAlegatosOficioCargadas = listaPruebasAlegatosOficioCargadas;
    }

    public List<FecetAlegatoOficio> getListaPruebasAlegatosOficioCargadas() {
        return listaPruebasAlegatosOficioCargadas;
    }
    
    public void setListaPromocionesCargadas(List<FecetPromocion> listaPromocionesCargadas) {
        this.listaPromocionesCargadas = listaPromocionesCargadas;
    }

    public List<FecetPromocion> getListaPromocionesCargadas() {
        return listaPromocionesCargadas;
    }

    public void setListaPruebasAlegatosCargadas(List<FecetAlegato> listaPruebasAlegatosCargadas) {
        this.listaPruebasAlegatosCargadas = listaPruebasAlegatosCargadas;
    }

    public List<FecetAlegato> getListaPruebasAlegatosCargadas() {
        return listaPruebasAlegatosCargadas;
    }

}
