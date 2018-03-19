package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.Serializable;

import java.util.List;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class PruebasPericialesDocsVO extends BaseModel implements Serializable  {
    @SuppressWarnings("compatibility:-2477782544696401364")
    private static final long serialVersionUID = 1L;

    private AgaceOrden ordenSeleccionado;
    private  List<FecetDocPruebasPericiales> listaDocsPruebasPericiales;
    private PerfilContribuyenteVO perfil;
    private FecetPruebasPericiales pruebasPericiales;

    public void setOrdenSeleccionado(AgaceOrden ordenSeleccionado) {
        this.ordenSeleccionado = ordenSeleccionado;
    }

    public AgaceOrden getOrdenSeleccionado() {
        return ordenSeleccionado;
    }

    public void setListaDocsPruebasPericiales(List<FecetDocPruebasPericiales> listaDocsPruebasPericiales) {
        this.listaDocsPruebasPericiales = listaDocsPruebasPericiales;
    }

    public List<FecetDocPruebasPericiales> getListaDocsPruebasPericiales() {
        return listaDocsPruebasPericiales;
    }

    public void setPerfil(PerfilContribuyenteVO perfil) {
        this.perfil = perfil;
    }

    public PerfilContribuyenteVO getPerfil() {
        return perfil;
    }

    public void setPruebasPericiales(FecetPruebasPericiales pruebasPericiales) {
        this.pruebasPericiales = pruebasPericiales;
    }

    public FecetPruebasPericiales getPruebasPericiales() {
        return pruebasPericiales;
    }
}
