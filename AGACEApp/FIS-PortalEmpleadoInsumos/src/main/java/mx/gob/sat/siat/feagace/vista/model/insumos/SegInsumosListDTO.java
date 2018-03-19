package mx.gob.sat.siat.feagace.vista.model.insumos;

import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FecetDocumento;

import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRechazoInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRetroalimentacionInsumo;

public class SegInsumosListDTO extends SegInsumosListAbstract {

    private static final long serialVersionUID = 1L;

    private List<FecetRechazoInsumo> rechazoInsumos;
    private List<FecetRetroalimentacionInsumo> retroalimentacionInsumo;
    private List<FecetInsumo> insumoCreado;
    private List<FecetInsumo> insumoPorRetroAlimentar;
    private List<FecetInsumo> insumoRechazado;
    private List<FecetInsumo> listaInsumoSeleccionado;
    private List<List<FecetInsumo>> insumos;
    private List<FecetInsumo> detalleOficio;
    private List<FecetDocExpInsumo> listaDocumento;
    private List<FecetDocumento> listaDocumentoJustificacion;
    

    public void setRechazoInsumos(List<FecetRechazoInsumo> rechazoInsumos) {
        this.rechazoInsumos = rechazoInsumos;
    }

    public List<FecetRechazoInsumo> getRechazoInsumos() {
        return rechazoInsumos;
    }

    public void setRetroalimentacionInsumo(List<FecetRetroalimentacionInsumo> retroalimentacionInsumo) {
        this.retroalimentacionInsumo = retroalimentacionInsumo;
    }

    public List<FecetRetroalimentacionInsumo> getRetroalimentacionInsumo() {
        return retroalimentacionInsumo;
    }

    public void setInsumoCreado(List<FecetInsumo> insumoCreado) {
        this.insumoCreado = insumoCreado;
    }

    public List<FecetInsumo> getInsumoCreado() {
        return insumoCreado;
    }

    public void setInsumoPorRetroAlimentar(List<FecetInsumo> insumoPorRetroAlimentar) {
        this.insumoPorRetroAlimentar = insumoPorRetroAlimentar;
    }

    public List<FecetInsumo> getInsumoPorRetroAlimentar() {
        return insumoPorRetroAlimentar;
    }

    public void setInsumoRechazado(List<FecetInsumo> insumoRechazado) {
        this.insumoRechazado = insumoRechazado;
    }

    public List<FecetInsumo> getInsumoRechazado() {
        return insumoRechazado;
    }

    public void setListaInsumoSeleccionado(List<FecetInsumo> listaInsumoSeleccionado) {
        this.listaInsumoSeleccionado = listaInsumoSeleccionado;
    }

    public List<FecetInsumo> getListaInsumoSeleccionado() {
        return listaInsumoSeleccionado;
    }

    public void setInsumos(List<List<FecetInsumo>> insumos) {
        this.insumos = insumos;
    }

    public List<List<FecetInsumo>> getInsumos() {
        return insumos;
    }

    public void setDetalleOficio(List<FecetInsumo> detalleOficio) {
        this.detalleOficio = detalleOficio;
    }

    public List<FecetInsumo> getDetalleOficio() {
        return detalleOficio;
    }

    public void setListaDocumento(List<FecetDocExpInsumo> listaDocumento) {
        this.listaDocumento = listaDocumento;
    }

    public List<FecetDocExpInsumo> getListaDocumento() {
        return listaDocumento;
    }

    public List<FecetDocumento> getListaDocumentoJustificacion() {
        return listaDocumentoJustificacion;
    }

    public void setListaDocumentoJustificacion(List<FecetDocumento> listaDocumentoJustificacion) {
        this.listaDocumentoJustificacion = listaDocumentoJustificacion;
    }
    
}
