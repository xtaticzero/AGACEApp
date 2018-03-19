package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class ProrrogaDocsVO extends BaseModel implements Serializable  {

    @SuppressWarnings("compatibility:-8758051474282395387")
    private static final long serialVersionUID = 1L;
    private AgaceOrden ordenSeleccionado;
    private  List<FecetDocProrrogaOrden> listaDocsProrrogasOrden;
    private PerfilContribuyenteVO perfil;
    private String jsonFirmado;
    private FecetOficio oficioSeleccionado;
    private  List<FecetDocProrrogaOficio> listaDocsProrrogasOficio;
   
    private FecetProrrogaOrden prorrogaOrden;
    private FecetProrrogaOficio prorrogaOficio;
    
    private BigDecimal idOrdenAuditadaCompulsa;
   

   

    public void setPerfil(PerfilContribuyenteVO perfil) {
        this.perfil = perfil;
    }

    public PerfilContribuyenteVO getPerfil() {
        return perfil;
    }

    public void setJsonFirmado(String jsonFirmado) {
        this.jsonFirmado = jsonFirmado;
    }

    public String getJsonFirmado() {
        return jsonFirmado;
    }

    public void setOrdenSeleccionado(AgaceOrden ordenSeleccionado) {
        this.ordenSeleccionado = ordenSeleccionado;
    }

    public AgaceOrden getOrdenSeleccionado() {
        return ordenSeleccionado;
    }

    public void setListaDocsProrrogasOrden(List<FecetDocProrrogaOrden> listaDocsProrrogasOrden) {
        this.listaDocsProrrogasOrden = listaDocsProrrogasOrden;
    }

    public List<FecetDocProrrogaOrden> getListaDocsProrrogasOrden() {
        return listaDocsProrrogasOrden;
    }

    public void setOficioSeleccionado(FecetOficio oficioSeleccionado) {
        this.oficioSeleccionado = oficioSeleccionado;
    }

    public FecetOficio getOficioSeleccionado() {
        return oficioSeleccionado;
    }

    public void setListaDocsProrrogasOficio(List<FecetDocProrrogaOficio> listaDocsProrrogasOficio) {
        this.listaDocsProrrogasOficio = listaDocsProrrogasOficio;
    }

    public List<FecetDocProrrogaOficio> getListaDocsProrrogasOficio() {
        return listaDocsProrrogasOficio;
    }

    public void setProrrogaOrden(FecetProrrogaOrden prorrogaOrden) {
        this.prorrogaOrden = prorrogaOrden;
    }

    public FecetProrrogaOrden getProrrogaOrden() {
        return prorrogaOrden;
    }

    public void setProrrogaOficio(FecetProrrogaOficio prorrogaOficio) {
        this.prorrogaOficio = prorrogaOficio;
    }

    public FecetProrrogaOficio getProrrogaOficio() {
        return prorrogaOficio;
    }

    public void setIdOrdenAuditadaCompulsa(BigDecimal idOrdenAuditadaCompulsa) {
        this.idOrdenAuditadaCompulsa = idOrdenAuditadaCompulsa;
    }

    public BigDecimal getIdOrdenAuditadaCompulsa() {
        return idOrdenAuditadaCompulsa;
    }
}
