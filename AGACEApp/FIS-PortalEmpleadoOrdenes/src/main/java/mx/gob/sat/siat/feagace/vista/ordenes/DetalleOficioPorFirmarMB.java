package mx.gob.sat.siat.feagace.vista.ordenes;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mx.gob.sat.siat.buzon.model.MedioComunicacion;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCambioMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCompulsas;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.ConsultaMediosContactoService;
import mx.gob.sat.siat.feagace.negocio.common.ContribuyenteService;
import mx.gob.sat.siat.feagace.negocio.ordenes.FirmanteSeguimientoService;
import mx.gob.sat.siat.feagace.negocio.ordenes.SeguimientoOrdenesService;
import mx.gob.sat.siat.feagace.vista.ordenes.documentacion.DocumentacionFirmanteMBAbstract;

@ManagedBean(name = "detalleOficioPorFirmarMB")
@ViewScoped
public class DetalleOficioPorFirmarMB extends DocumentacionFirmanteMBAbstract {

    @SuppressWarnings("compatibility:5034576086081270544")
    private static final long serialVersionUID = 17654234L;

    private static final BigDecimal ID_OFICIO_CAMBIO_METODO = new BigDecimal(19L);

    private FecetContribuyente contribuyente;

    @ManagedProperty(value = "#{contribuyenteService}")
    private transient ContribuyenteService contribuyenteService;

    @ManagedProperty(value = "#{consultaMediosContactoService}")
    private transient ConsultaMediosContactoService mediosService;

    @ManagedProperty(value = "#{seguimientoOrdenesService}")
    private transient SeguimientoOrdenesService seguimientoService;

    @ManagedProperty(value = "#{firmanteSeguimientoService}")
    private transient FirmanteSeguimientoService firmanteSeguimientoService;

    private transient List<FecetOficio> oficios;

    private transient List<FecetOficioAnexos> anexos;

    private transient List<MedioComunicacion> medios;

    private FecetCompulsas compulsa;

    private FecetCambioMetodo cambioMetodo;

    private String tituloOficio;

    public DetalleOficioPorFirmarMB() {
        super();
    }

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String rfc = params.get("identificador1");
        BigDecimal idOficio = new BigDecimal(params.get("identificador2"));
        BigDecimal idOrden = new BigDecimal(params.get("identificador3"));
        BigDecimal idTipoOficio = new BigDecimal(params.get("identificador4"));
        setTituloOficio(params.get("identificador5"));
        try {
            this.compulsa = idTipoOficio.equals(Constantes.ID_OFICIO_COMPULSA_TERCEROS) ? firmanteSeguimientoService.obtenerDatosEncabezado(idOficio) : null;
            this.cambioMetodo = idTipoOficio.equals(ID_OFICIO_CAMBIO_METODO) ? firmanteSeguimientoService.obtenerDatosEncabezadoCambioMetodo(idOrden) : null;
            medios = getMediosService().consultaMediosContacto(rfc);
            checkMedios();
            anexos = getSeguimientoService().getAnexosOficio(idOficio);
            oficios = getFirmanteSeguimientoService().getDetalleOficioPendienteFirma(idOficio);
        } catch (Exception e) {
            logger.error("No se encontro el contribuyente " + e.getMessage(), e);
        }
    }

    private void checkMedios() {
        if (!medios.isEmpty() && medios.get(0).getTipoMedio() == 0) {
            medios.clear();
        }
    }

    public FecetContribuyente getContribuyente() {
        return contribuyente;
    }

    public void setContribuyente(FecetContribuyente contribuyente) {
        this.contribuyente = contribuyente;
    }

    public ContribuyenteService getContribuyenteService() {
        return contribuyenteService;
    }

    public void setContribuyenteService(ContribuyenteService contribuyenteService) {
        this.contribuyenteService = contribuyenteService;
    }

    public List<MedioComunicacion> getMedios() {
        return medios;
    }

    public void setMedios(List<MedioComunicacion> medios) {
        this.medios = medios;
    }

    public ConsultaMediosContactoService getMediosService() {
        return mediosService;
    }

    public void setMediosService(ConsultaMediosContactoService mediosService) {
        this.mediosService = mediosService;
    }

    public List<FecetOficio> getOficios() {
        return oficios;
    }

    public void setOficios(List<FecetOficio> oficios) {
        this.oficios = oficios;
    }

    public List<FecetOficioAnexos> getAnexos() {
        return anexos;
    }

    public void setAnexos(List<FecetOficioAnexos> anexos) {
        this.anexos = anexos;
    }

    public FirmanteSeguimientoService getFirmanteSeguimientoService() {
        return firmanteSeguimientoService;
    }

    public void setFirmanteSeguimientoService(FirmanteSeguimientoService firmanteSeguimientoService) {
        this.firmanteSeguimientoService = firmanteSeguimientoService;
    }

    public SeguimientoOrdenesService getSeguimientoService() {
        return seguimientoService;
    }

    public void setSeguimientoService(SeguimientoOrdenesService seguimientoService) {
        this.seguimientoService = seguimientoService;
    }

    public FecetCompulsas getCompulsa() {
        return compulsa;
    }

    public void setCompulsa(FecetCompulsas compulsa) {
        this.compulsa = compulsa;
    }

    public FecetCambioMetodo getCambioMetodo() {
        return cambioMetodo;
    }

    public void setCambioMetodo(FecetCambioMetodo cambioMetodo) {
        this.cambioMetodo = cambioMetodo;
    }

    public String getTituloOficio() {
        return tituloOficio;
    }

    public void setTituloOficio(String tituloOficio) {
        this.tituloOficio = tituloOficio;
    }
}