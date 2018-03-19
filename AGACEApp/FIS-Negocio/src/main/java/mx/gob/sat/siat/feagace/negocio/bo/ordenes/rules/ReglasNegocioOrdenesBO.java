package mx.gob.sat.siat.feagace.negocio.bo.ordenes.rules;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetProrrogaOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.negocio.bo.base.BO;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;

public class ReglasNegocioOrdenesBO extends BO<ReglasNegocioOrdenesBO> {

    private AgaceOrden orden;
    private FecetOficio oficio;
    private FecetProrrogaOrdenDao fecetProrrogaOrdenDao;
    private FecetOficioDao fecetOficioDao;
    private List<FecetOficio> listaOficiosConPlazoOVencidos;

    private List<FecetOficio> listaOfPrincipal;
    private List<FecetOficioAnexos> listaAnexosPrincipal;

    private List<FecetOficio> listaOfDependiente1;
    private List<FecetOficioAnexos> listaAnexosDependiente1;

    private List<FecetOficio> listaOfDependiente2;
    private List<FecetOficioAnexos> listaAnexosDependiente2;

    private List<FecetOficio> listaOfDependiente3;
    private List<FecetOficioAnexos> listaAnexosDependiente3;

    private FecetContribuyente contribuyente;
    private ColaboradorVO representanteLegal;
    private Integer tipoCompulsa;

    private BigDecimal tipoOficio;

    private List<FecetPromocion> promociones;

    private String autoridadCompulsada;
    
    public ReglasNegocioOrdenesBO() {
        super();
    }

    public void setOrden(final AgaceOrden orden) {
        this.orden = orden;
    }

    public AgaceOrden getOrden() {
        return orden;
    }

    public void setFecetProrrogaOrdenDao(FecetProrrogaOrdenDao fecetProrrogaOrdenDao) {
        this.fecetProrrogaOrdenDao = fecetProrrogaOrdenDao;
    }

    public FecetProrrogaOrdenDao getFecetProrrogaOrdenDao() {
        return fecetProrrogaOrdenDao;
    }

    public void setFecetOficioDao(FecetOficioDao fecetOficioDao) {
        this.fecetOficioDao = fecetOficioDao;
    }

    public FecetOficioDao getFecetOficioDao() {
        return fecetOficioDao;
    }

    public void setListaOficiosConPlazoOVencidos(final List<FecetOficio> listaOficiosConPlazoOVencidos) {
        this.listaOficiosConPlazoOVencidos = listaOficiosConPlazoOVencidos;
    }

    public List<FecetOficio> getListaOficiosConPlazoOVencidos() {
        return listaOficiosConPlazoOVencidos;
    }

    public void setListaOfPrincipal(final List<FecetOficio> listaOfPrincipal) {
        this.listaOfPrincipal = listaOfPrincipal;
    }

    public List<FecetOficio> getListaOfPrincipal() {
        return listaOfPrincipal;
    }

    public void setListaAnexosPrincipal(final List<FecetOficioAnexos> listaAnexosPrincipal) {
        this.listaAnexosPrincipal = listaAnexosPrincipal;
    }

    public List<FecetOficioAnexos> getListaAnexosPrincipal() {
        return listaAnexosPrincipal;
    }

    public void setListaOfDependiente1(final List<FecetOficio> listaOfDependiente1) {
        this.listaOfDependiente1 = listaOfDependiente1;
    }

    public List<FecetOficio> getListaOfDependiente1() {
        return listaOfDependiente1;
    }

    public void setListaAnexosDependiente1(final List<FecetOficioAnexos> listaAnexosDependiente1) {
        this.listaAnexosDependiente1 = listaAnexosDependiente1;
    }

    public List<FecetOficioAnexos> getListaAnexosDependiente1() {
        return listaAnexosDependiente1;
    }

    public void setListaOfDependiente2(final List<FecetOficio> listaOfDependiente2) {
        this.listaOfDependiente2 = listaOfDependiente2;
    }

    public List<FecetOficio> getListaOfDependiente2() {
        return listaOfDependiente2;
    }

    public void setListaAnexosDependiente2(final List<FecetOficioAnexos> listaAnexosDependiente2) {
        this.listaAnexosDependiente2 = listaAnexosDependiente2;
    }

    public List<FecetOficioAnexos> getListaAnexosDependiente2() {
        return listaAnexosDependiente2;
    }

    public void setListaOfDependiente3(final List<FecetOficio> listaOfDependiente3) {
        this.listaOfDependiente3 = listaOfDependiente3;
    }

    public List<FecetOficio> getListaOfDependiente3() {
        return listaOfDependiente3;
    }

    public void setListaAnexosDependiente3(final List<FecetOficioAnexos> listaAnexosDependiente3) {
        this.listaAnexosDependiente3 = listaAnexosDependiente3;
    }

    public List<FecetOficioAnexos> getListaAnexosDependiente3() {
        return listaAnexosDependiente3;
    }

    public void setOficio(final FecetOficio oficio) {
        this.oficio = oficio;
    }

    public FecetOficio getOficio() {
        return oficio;
    }

    public void setTipoCompulsa(final Integer tipoCompulsa) {
        this.tipoCompulsa = tipoCompulsa;
    }

    public Integer getTipoCompulsa() {
        return tipoCompulsa;
    }

    public void setContribuyente(final FecetContribuyente contribuyente) {
        this.contribuyente = contribuyente;
    }

    public FecetContribuyente getContribuyente() {
        return contribuyente;
    }

    public void setRepresentanteLegal(final ColaboradorVO representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    public ColaboradorVO getRepresentanteLegal() {
        return representanteLegal;
    }

    public List<FecetPromocion> getPromociones() {
        return promociones;
    }

    public void setPromociones(List<FecetPromocion> promociones) {
        this.promociones = promociones;
    }

    public BigDecimal getTipoOficio() {
        return tipoOficio;
    }

    public void setTipoOficio(BigDecimal tipoOficio) {
        this.tipoOficio = tipoOficio;
    }

    public String getAutoridadCompulsada() {
        return autoridadCompulsada;
    }

    public void setAutoridadCompulsada(String autoridadCompulsada) {
        this.autoridadCompulsada = autoridadCompulsada;
    }
    
    

}
