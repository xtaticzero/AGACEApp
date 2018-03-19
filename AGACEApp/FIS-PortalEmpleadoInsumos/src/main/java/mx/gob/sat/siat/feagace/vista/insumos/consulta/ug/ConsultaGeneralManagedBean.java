package mx.gob.sat.siat.feagace.vista.insumos.consulta.ug;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FiltroPropuestas;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorEstatusPropuestasEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAraceEnum;
import mx.gob.sat.siat.feagace.negocio.bo.consulta.ConsultaEjecutivaPropuestasBO;
import mx.gob.sat.siat.feagace.negocio.consulta.general.ConsultaGeneralInsumosService;
import mx.gob.sat.siat.feagace.negocio.consulta.general.ConsultaGeneralOrdenesService;
import mx.gob.sat.siat.feagace.negocio.consulta.general.ConsultaGeneralPropuestasService;
import mx.gob.sat.siat.feagace.negocio.consulta.propuestas.ConsultaEjecutivaPropuestasService;
import mx.gob.sat.siat.feagace.vista.common.AbstractManagedBean;
import mx.gob.sat.siat.feagace.vista.helper.ItemCombo;
import mx.gob.sat.siat.feagace.vista.insumos.helper.ConsultaGeneralHelper;
import mx.gob.sat.siat.feagace.vista.util.ComparatorXClaveItemFiltro;
import mx.gob.sat.siat.feagace.vista.util.ComparatorXDescripcionItemFiltro;

@ManagedBean(name = "consultaUsuarioGeneralMB")
@ViewScoped
public class ConsultaGeneralManagedBean extends AbstractManagedBean {

    private static final long serialVersionUID = -7020095440232680432L;

    public static final int CERO = 0;
    public static final int UNO = 1;
    public static final int DOS = 2;
    public static final int TRES = 3;
    public static final int UNONEG = -1;
    public static final int DOSNEG = -2;
    public static final int PLAZO_MAXIMO = 20;
    public static final int PLAZO_INICIAL = -1;
    public static final String DESCRIPCION_PLAZO = " d\u00eda";

    private int tipoConsulta;

    private int idArace;

    private transient ConsultaGeneralHelper helper;

    private List<FececUnidadAdministrativa> listaUnidadesAdmon;

    private List<AraceDTO> unidadesAdmon;

    private List<TipoAraceEnum> listaUnidadesAdministrativas;

    private List<TipoAraceEnum> listaUnidadesRegistro;

    private transient ConsultaEjecutivaPropuestasBO consultaPropuestasBO;

    private transient FiltroPropuestas filtro;

    private Map<AgrupadorEstatusPropuestasEnum, Integer> detalleXEstatus;

    private Map<EmpleadoDTO, Integer> detalleXEmpleado;

    private String nombreArchivoDescarga;
    private String rutaArchivoDescarga;

    private static final String ERROR_DESCARGA_ARCHIVO = "error.descarga.archivo";

    @ManagedProperty(value = "#{consultaGeneralInsumosService}")
    private transient ConsultaGeneralInsumosService consultaGeneralInsumosService;

    @ManagedProperty(value = "#{consultaGeneralPropuestasService}")
    private transient ConsultaGeneralPropuestasService consultaGeneralPropuestasService;

    @ManagedProperty(value = "#{consultaGeneralOrdenesService}")
    private transient ConsultaGeneralOrdenesService consultaGeneralOrdenesService;

    @ManagedProperty(value = "#{consultaEjecutivaPropuestasService}")
    private transient ConsultaEjecutivaPropuestasService consultaEjecutivaPropuestasService;

    public void init() {
        setHelper(new ConsultaGeneralHelper());
    }

    public void redirectConsulta(final AjaxBehaviorEvent event) {
        try {
            switch (tipoConsulta) {
                case UNO:
                    FacesContext.getCurrentInstance().getExternalContext().redirect("insumos/inicio.xhtml");
                    break;
                case DOS:
                    FacesContext.getCurrentInstance().getExternalContext().redirect("propuestas/inicio.xhtml");
                    break;
                case TRES:
                    logger.info("redirect ConsultaGeneralOrdenesManagedBean");
                    FacesContext.getCurrentInstance().getExternalContext().redirect("ordenes/consultaXEstatus.xhtml");
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public int getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(int tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public List<FececUnidadAdministrativa> getListaUnidadesAdmon() {
        return listaUnidadesAdmon;
    }

    public void setListaUnidadesAdmon(List<FececUnidadAdministrativa> listaUnidadesAdmon) {
        this.listaUnidadesAdmon = listaUnidadesAdmon;
    }

    public ConsultaGeneralHelper getHelper() {
        return helper;
    }

    public void setHelper(ConsultaGeneralHelper helper) {
        this.helper = helper;
    }

    public List<AraceDTO> getUnidadesAdmon() {
        return unidadesAdmon;
    }

    public void setUnidadesAdmon(List<AraceDTO> unidadesAdmon) {
        this.unidadesAdmon = unidadesAdmon;
    }

    public ConsultaGeneralInsumosService getConsultaGeneralInsumosService() {
        return consultaGeneralInsumosService;
    }

    public void setConsultaGeneralInsumosService(ConsultaGeneralInsumosService consultaGeneralInsumosService) {
        this.consultaGeneralInsumosService = consultaGeneralInsumosService;
    }

    public ConsultaGeneralPropuestasService getConsultaGeneralPropuestasService() {
        return consultaGeneralPropuestasService;
    }

    public void setConsultaGeneralPropuestasService(ConsultaGeneralPropuestasService consultaGeneralPropuestasService) {
        this.consultaGeneralPropuestasService = consultaGeneralPropuestasService;
    }

    public ConsultaGeneralOrdenesService getConsultaGeneralOrdenesService() {
        return consultaGeneralOrdenesService;
    }

    public void setConsultaGeneralOrdenesService(ConsultaGeneralOrdenesService consultaGeneralOrdenesService) {
        this.consultaGeneralOrdenesService = consultaGeneralOrdenesService;
    }

    public ConsultaEjecutivaPropuestasService getConsultaEjecutivaPropuestasService() {
        return consultaEjecutivaPropuestasService;
    }

    public void setConsultaEjecutivaPropuestasService(
            ConsultaEjecutivaPropuestasService consultaEjecutivaPropuestasService) {
        this.consultaEjecutivaPropuestasService = consultaEjecutivaPropuestasService;
    }

    public int getIdArace() {
        return idArace;
    }

    public void setIdArace(int idArace) {
        this.idArace = idArace;
    }

    public List<TipoAraceEnum> getListaUnidadesAdministrativas() {
        return listaUnidadesAdministrativas;
    }

    public void setListaUnidadesAdministrativas(List<TipoAraceEnum> listaUnidadesAdministrativas) {
        this.listaUnidadesAdministrativas = listaUnidadesAdministrativas;
    }

    public ConsultaEjecutivaPropuestasBO getConsultaPropuestasBO() {
        return consultaPropuestasBO;
    }

    public void setConsultaPropuestasBO(ConsultaEjecutivaPropuestasBO consultaPropuestasBO) {
        this.consultaPropuestasBO = consultaPropuestasBO;
    }

    public FiltroPropuestas getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroPropuestas filtro) {
        this.filtro = filtro;
    }

    public Map<AgrupadorEstatusPropuestasEnum, Integer> getDetalleXEstatus() {
        return detalleXEstatus;
    }

    public void setDetalleXEstatus(Map<AgrupadorEstatusPropuestasEnum, Integer> detalleXEstatus) {
        this.detalleXEstatus = detalleXEstatus;
    }

    public Map<EmpleadoDTO, Integer> getDetalleXEmpleado() {
        return detalleXEmpleado;
    }

    public void setDetalleXEmpleado(Map<EmpleadoDTO, Integer> detalleXEmpleado) {
        this.detalleXEmpleado = detalleXEmpleado;
    }

    public List<TipoAraceEnum> getListaUnidadesRegistro() {
        return listaUnidadesRegistro;
    }

    public void setListaUnidadesRegistro(List<TipoAraceEnum> listaUnidadesRegistro) {
        this.listaUnidadesRegistro = listaUnidadesRegistro;
    }

    public List<TipoAraceEnum> obtenerUnidadesAdministrativas() {
        List<TipoAraceEnum> listaUnidadAdmonEnum = new ArrayList<TipoAraceEnum>();
        listaUnidadAdmonEnum.add(TipoAraceEnum.ACPPCE);
        listaUnidadAdmonEnum.add(TipoAraceEnum.ACAOCE);
        listaUnidadAdmonEnum.add(TipoAraceEnum.ACOECE);
        listaUnidadAdmonEnum.add(TipoAraceEnum.ADACE_CENTRO);
        listaUnidadAdmonEnum.add(TipoAraceEnum.ADACE_NOROESTE);
        listaUnidadAdmonEnum.add(TipoAraceEnum.ADACE_NORTE_CENTRO);
        listaUnidadAdmonEnum.add(TipoAraceEnum.ADACE_OCCIDENTE);
        listaUnidadAdmonEnum.add(TipoAraceEnum.ADACE_PACIFICO_NORTE);
        listaUnidadAdmonEnum.add(TipoAraceEnum.ADACE_SUR);
        return listaUnidadAdmonEnum;
    }

    public List<TipoAraceEnum> obtenerUnidadesRegistro() {
        List<TipoAraceEnum> listaUnidadAdmonEnum = new ArrayList<TipoAraceEnum>();
        listaUnidadAdmonEnum.add(TipoAraceEnum.ACPPCE);
        listaUnidadAdmonEnum.add(TipoAraceEnum.ACIACE);
        return listaUnidadAdmonEnum;
    }

    public Set<ItemCombo> getLstPrioridadFiltro() {
        Set<ItemCombo> lstPrioridadFiltro = new TreeSet<ItemCombo>(new ComparatorXDescripcionItemFiltro());

        if (isListValid(consultaPropuestasBO.getLstPropuestasResult())) {
            for (FecetPropuesta propuesta : consultaPropuestasBO.getLstPropuestasResult()) {
                if (propuesta.getPrioridadSugerida() != null) {
                    lstPrioridadFiltro.add(new ItemCombo(propuesta.getPrioridadSugerida(), Integer.valueOf(propuesta.getPrioridadSugerida())));
                }
            }
        }

        return lstPrioridadFiltro;
    }

    public Set<ItemCombo> getLstMetodosFiltro() {
        Set<ItemCombo> lstMetodosFiltro = new TreeSet<ItemCombo>(new ComparatorXClaveItemFiltro());

        if (isListValid(consultaPropuestasBO.getLstPropuestasResult())) {
            for (FecetPropuesta propuesta : consultaPropuestasBO.getLstPropuestasResult()) {
                lstMetodosFiltro.add(new ItemCombo(propuesta.getFeceaMetodo().getAbreviatura(), propuesta.getFeceaMetodo().getIdMetodo().intValue()));
            }
        }

        return lstMetodosFiltro;
    }

    public Set<ItemCombo> getLstUnidadAdministrativaFiltro() {
        Set<ItemCombo> lstUnidadAdministrativaFiltro = new TreeSet<ItemCombo>(new ComparatorXClaveItemFiltro());

        if (isListValid(consultaPropuestasBO.getLstPropuestasResult())) {
            for (FecetPropuesta propuesta : consultaPropuestasBO.getLstPropuestasResult()) {
                lstUnidadAdministrativaFiltro.add(new ItemCombo(propuesta.getFececArace().getNombre(), propuesta.getFececArace().getIdArace().intValue()));
            }
        }

        return lstUnidadAdministrativaFiltro;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public boolean filterByPresuntiva(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if (filterText == null || filterText.equals("")) {
            return true;
        }

        if (value == null) {
            return false;
        }

        try {
            return ((Comparable) (((BigDecimal) value).intValue())).compareTo(Integer.valueOf(filterText)) >= 0;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public Set<ItemCombo> getLstEstatusFiltro() {
        Set<ItemCombo> lstEstatusFiltro = new TreeSet<ItemCombo>(new ComparatorXClaveItemFiltro());

        if (isListValid(consultaPropuestasBO.getLstPropuestasResult())) {
            for (FecetPropuesta propuesta : consultaPropuestasBO.getLstPropuestasResult()) {
                lstEstatusFiltro.add(new ItemCombo(propuesta.getEstatusXGrupo().getDescripcion(), propuesta.getEstatusXGrupo().getIdGrupo()));
            }
        }

        return lstEstatusFiltro;
    }

    public boolean isListValid(List<?> lista) {
        return lista != null && !lista.isEmpty();
    }

    public String getNombreArchivoDescarga() {
        return nombreArchivoDescarga;
    }

    public void setNombreArchivoDescarga(String nombreArchivoDescarga) {
        this.nombreArchivoDescarga = nombreArchivoDescarga;
    }

    public String getRutaArchivoDescarga() {
        return rutaArchivoDescarga;
    }

    public void setRutaArchivoDescarga(String rutaArchivoDescarga) {
        this.rutaArchivoDescarga = rutaArchivoDescarga;
    }

    public StreamedContent getDescargaDocumento() {
        logger.debug("Se descarga docto...");
        try {
            if (rutaArchivoDescarga != null && !rutaArchivoDescarga.isEmpty()) {

                int indice = rutaArchivoDescarga.lastIndexOf('/');
                if ((rutaArchivoDescarga.length() - 1) == indice) {
                    rutaArchivoDescarga = rutaArchivoDescarga.concat(nombreArchivoDescarga);
                }

                indice = rutaArchivoDescarga.lastIndexOf('\\');
                if ((rutaArchivoDescarga.length() - 1) == indice) {
                    rutaArchivoDescarga = rutaArchivoDescarga.concat(nombreArchivoDescarga);
                }

                return new DefaultStreamedContent(new FileInputStream(rutaArchivoDescarga),
                        "application/octet-stream",
                        nombreArchivoDescarga != null ? nombreArchivoDescarga : "archivo");
            } else {
                addErrorMessage(getMessageResourceString(ERROR_DESCARGA_ARCHIVO, nombreArchivoDescarga));
                return null;
            }
        } catch (Exception fne) {
            addErrorMessage(getMessageResourceString(ERROR_DESCARGA_ARCHIVO, nombreArchivoDescarga));
            logger.error(fne.getMessage(), fne);
            return null;
        }
    }
    
    public List<ItemCombo> getLstPalzosParaConcluir() {
        List<ItemCombo> lstPlazos = new ArrayList<ItemCombo>();
        for (int i = PLAZO_INICIAL; i <= PLAZO_MAXIMO; i++) {
            ItemCombo item = new ItemCombo();
            item.setValor(i);

            switch (i) {
                case PLAZO_INICIAL:
                    item.setDescripcion("Sin atender");
                    break;
                case 0:
                    item.setDescripcion("Plazo vencido");
                    break;
                case UNO:
                    item.setDescripcion(i + DESCRIPCION_PLAZO);
                    break;
                default:
                    item.setDescripcion(i + DESCRIPCION_PLAZO + "s");
                    break;
            }

            lstPlazos.add(item);
        }
        return lstPlazos;
    }

}
