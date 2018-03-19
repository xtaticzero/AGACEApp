package mx.gob.sat.siat.feagace.vista.ordenes;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.negocio.ordenes.ProrrogaService;
import mx.gob.sat.siat.feagace.vista.common.AbstractManagedBean;

@ManagedBean(name = "prorrogaMB")
@SessionScoped
public class ProrrogaMB extends AbstractManagedBean {

    @ManagedProperty(value = "#{prorrogaService}")
    private ProrrogaService prorrogaService;
    private AgaceOrden ordenSeleccionada;
    private Integer sizeUltimaProrroga;
    private Integer sizeUltimaExtemporaneo;

    private List<FecetProrrogaOrden> ultimaProrroga = new ArrayList<FecetProrrogaOrden>();

    public void init() {
        if (ordenSeleccionada != null) {
            mostrarUltimaProrroga();
        }
    }

    public void mostrarUltimaProrroga() {

        ultimaProrroga = prorrogaService.getUltimaProrroga(ordenSeleccionada);
        this.setSizeUltimaProrroga(ultimaProrroga.size());

    }

    public void actualizarEstadoProroga() {
        FecetProrrogaOrden prorroga = new FecetProrrogaOrden();
        prorrogaService.actualizarPorEstadoYFechaMaxPro(prorroga, ordenSeleccionada);

    }

    public ProrrogaService getProrrogaService() {
        return prorrogaService;
    }

    public void setProrrogaService(ProrrogaService prorrogaService) {
        this.prorrogaService = prorrogaService;
    }

    public AgaceOrden getOrdenSeleccionada() {
        return ordenSeleccionada;
    }

    public void setOrdenSeleccionada(AgaceOrden ordenSeleccionada) {
        this.ordenSeleccionada = ordenSeleccionada;
    }

    public List<FecetProrrogaOrden> getUltimaProrroga() {
        return ultimaProrroga;
    }

    public void setUltimaProrroga(List<FecetProrrogaOrden> ultimaProrroga) {
        this.ultimaProrroga = ultimaProrroga;
    }

    public Integer getSizeUltimaProrroga() {
        return sizeUltimaProrroga;
    }

    public void setSizeUltimaProrroga(Integer sizeUltimaProrroga) {
        this.sizeUltimaProrroga = sizeUltimaProrroga;
    }

    public Integer getSizeUltimaExtemporaneo() {
        return sizeUltimaExtemporaneo;
    }

    public void setSizeUltimaExtemporaneo(Integer sizeUltimaExtemporaneo) {
        this.sizeUltimaExtemporaneo = sizeUltimaExtemporaneo;
    }

}
