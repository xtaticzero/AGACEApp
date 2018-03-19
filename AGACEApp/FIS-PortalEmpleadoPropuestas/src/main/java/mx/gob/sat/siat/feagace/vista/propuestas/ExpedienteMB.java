package mx.gob.sat.siat.feagace.vista.propuestas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.math.BigDecimal;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececCausaProgramacion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.ContribuyenteService;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteContribuyenteException;
import mx.gob.sat.siat.feagace.negocio.propuestas.FecetPropuestaService;
import mx.gob.sat.siat.feagace.negocio.propuestas.PropuestasRechazadasVerifDoctoService;
import mx.gob.sat.siat.feagace.negocio.propuestas.RegistrarPropuestaIndividualService;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 * Clase que maneja las peticiones de la pagina de expediente para visualizar la
 * informacion del conbtriuyente
 *
 * @author Luis Rodriguez
 * @version 1.0
 *
 */
@ManagedBean(name = "expediente")
@SessionScoped
public class ExpedienteMB extends BaseManagedBean {

    private static final long serialVersionUID = 639201261819761540L;

    @ManagedProperty(value = "#{contribuyenteService}")
    private transient ContribuyenteService contribuyenteService;

    @ManagedProperty(value = "#{registrarPropuestaIndividualService}")
    private transient RegistrarPropuestaIndividualService registrarPropuestaIndividualService;

    @ManagedProperty(value = "#{fecetPropuestaService}")
    private transient FecetPropuestaService fecetPropuestaService;

    @ManagedProperty(value = "#{propuestasRechazadasVerifDoctoService}")
    private transient PropuestasRechazadasVerifDoctoService propuestasRechazadasVerifDoctoService;

    private String rfcContribuyente;
    private String idPropuesta;
    private String idRegistro;
    private FecetContribuyente contribuyente;
    private List<FececMetodo> listaMetodo;
    private List<FececSubprograma> listaSubprograma;
    private List<FececTipoPropuesta> listaTipoPropuesta;
    private List<FececRevision> listaTipoRevision;
    private List<FececCausaProgramacion> listaCausaProgramacion;
    private List<FecetImpuesto> listaImpuestos;
    private List<FecetDocExpediente> listaDocumentosTabla;
    private FecetPropuesta propuesta = new FecetPropuesta();
    private FecetDocExpediente documentoSeleccionado;
    private StreamedContent documentoSeleccionDescarga;
    private boolean tipoAccionPropuesta;
    private BigDecimal presuntiva;
    private boolean esOrg;

    /**
     * Metodo que carga los datos del contribuyente de la propuesta para ser
     * mostradas en la pagina de expediente en el modulo de validar y firmar las
     * propuestas
     *
     *
     * @return el valor de la cadena en donde se va a redireccionar la pagina
     * despues de cargar losdatos
     */
    public String cargarDatosContribuyente() {
        logger.info("Voy a cargar los datos del contribuyente...");
        logger.info("Se va a consultar el rfc [{}]", rfcContribuyente);
        try {
            propuesta = fecetPropuestaService.obtenerPropuestaByidPropuesta(new BigDecimal(idPropuesta));
            isTipoPropuesta();

        } catch (NegocioException e) {
            logger.error("Error al consultar los datos de la propuesta: {}", e.getMessage());
        }
        try {
            contribuyente = fecetPropuestaService.getContribuyente(propuesta.getIdContribuyente());
        } catch (NoExisteContribuyenteException e) {
            logger.error("Error al consultar los datos del contribuyente: {}", e.getMessage());
        }
        try {
            listaImpuestos = fecetPropuestaService.getImpuestosByPropuesta(propuesta.getIdPropuesta());
            BigDecimal presuntivaTmp = BigDecimal.ZERO;
            if (listaImpuestos != null) {
                for (FecetImpuesto imp : listaImpuestos) {
                    presuntivaTmp = presuntivaTmp.add(imp.getMonto());
                }
                propuesta.setPresuntiva(presuntivaTmp);
            }

        } catch (NegocioException e) {
            logger.error("Error al consultar los impuestos de la propuesta: {}", e.getMessage());
        }
        listaDocumentosTabla
                = propuestasRechazadasVerifDoctoService.buscarDoctosExpedienteProp(new BigDecimal(idPropuesta));
        if (propuesta.getIdMetodo().longValue() == TipoMetodoEnum.ORG.getId()) {
            esOrg = true;
        }else{
            esOrg = false;
        }
        return "expedientePropuesta?faces-redirect=true";
    }

    public void isTipoPropuesta() {
        BigDecimal tipo = propuesta.getIdTipoPropuesta();
        if (tipo.intValue() == Constantes.ENTERO_UNO) {
            setTipoAccionPropuesta(false);
        } else {
            setTipoAccionPropuesta(true);
        }
    }

    public BigDecimal getTotalMonto() {
        BigDecimal totalImpuesto = BigDecimal.ZERO;
        if (getListaImpuestos() != null) {
            for (FecetImpuesto imp : getListaImpuestos()) {
                totalImpuesto = totalImpuesto.add(imp.getMonto());
            }
        }
        return totalImpuesto;
    }

    /**
     * Metodo que se utiliza para descargar los archivos
     *
     * @param path ruta en donde se encuentra el archivo
     * @param nombreDescarga nombre del archivo
     * @return objeto de tipo <code>StreamedContent</code>
     */
    public StreamedContent getDescargaArchivo(final String path, final String nombreDescarga) {
        StreamedContent archivo = null;
        if (path != null && nombreDescarga != null) {
            try {
                archivo
                        = new DefaultStreamedContent(new FileInputStream(PropuestaVistaCargaArchivosUtil.limpiarPathArchivo(path)), PropuestaVistaCargaArchivosUtil.obtenContentTypeArchivo(nombreDescarga),
                                PropuestaVistaCargaArchivosUtil.aplicarCodificacionTexto(nombreDescarga));
            } catch (FileNotFoundException e) {
                logger.error("No se pudo descargar el archivo. [{}]", e.getMessage());
                addErrorMessage(null, "No se encontro el documento seleccionado", "");
            }
        } else {
            addErrorMessage(null, "No se encontro el documento seleccionado", "");
        }
        return archivo;
    }

    /**
     * Metodo que es utilizado para redireccionar a la pagina anterior
     *
     * @return cadena que contiene la ruta de la pagina anterior
     */
    public String regresar() {
        return "validarOrdenes?faces-redirect=true";
    }

    public void setRfcContribuyente(String rfcContribuyente) {
        this.rfcContribuyente = rfcContribuyente;
    }

    public String getRfcContribuyente() {
        return rfcContribuyente;
    }

    public void setContribuyente(FecetContribuyente contribuyente) {
        this.contribuyente = contribuyente;
    }

    public FecetContribuyente getContribuyente() {
        return contribuyente;
    }

    public void setListaMetodo(List<FececMetodo> listaMetodo) {
        this.listaMetodo = listaMetodo;
    }

    public List<FececMetodo> getListaMetodo() {
        return listaMetodo;
    }

    public void setPropuesta(FecetPropuesta propuesta) {
        this.propuesta = propuesta;
    }

    public FecetPropuesta getPropuesta() {
        return propuesta;
    }

    public void setContribuyenteService(ContribuyenteService contribuyenteService) {
        this.contribuyenteService = contribuyenteService;
    }

    public ContribuyenteService getContribuyenteService() {
        return contribuyenteService;
    }

    public void setRegistrarPropuestaIndividualService(RegistrarPropuestaIndividualService registrarPropuestaIndividualService) {
        this.registrarPropuestaIndividualService = registrarPropuestaIndividualService;
    }

    public RegistrarPropuestaIndividualService getRegistrarPropuestaIndividualService() {
        return registrarPropuestaIndividualService;
    }

    public void setFecetPropuestaService(FecetPropuestaService fecetPropuestaService) {
        this.fecetPropuestaService = fecetPropuestaService;
    }

    public FecetPropuestaService getFecetPropuestaService() {
        return fecetPropuestaService;
    }

    public void setIdPropuesta(String idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public String getIdPropuesta() {
        return idPropuesta;
    }

    public void setListaSubprograma(List<FececSubprograma> listaSubprograma) {
        this.listaSubprograma = listaSubprograma;
    }

    public List<FececSubprograma> getListaSubprograma() {
        return listaSubprograma;
    }

    public void setListaTipoPropuesta(List<FececTipoPropuesta> listaTipoPropuesta) {
        this.listaTipoPropuesta = listaTipoPropuesta;
    }

    public List<FececTipoPropuesta> getListaTipoPropuesta() {
        return listaTipoPropuesta;
    }

    public void setListaTipoRevision(List<FececRevision> listaTipoRevision) {
        this.listaTipoRevision = listaTipoRevision;
    }

    public List<FececRevision> getListaTipoRevision() {
        return listaTipoRevision;
    }

    public void setListaCausaProgramacion(List<FececCausaProgramacion> listaCausaProgramacion) {
        this.listaCausaProgramacion = listaCausaProgramacion;
    }

    public List<FececCausaProgramacion> getListaCausaProgramacion() {
        return listaCausaProgramacion;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setListaImpuestos(List<FecetImpuesto> listaImpuestos) {
        this.listaImpuestos = listaImpuestos;
    }

    public List<FecetImpuesto> getListaImpuestos() {
        return listaImpuestos;
    }

    public void setListaDocumentosTabla(List<FecetDocExpediente> listaDocumentosTabla) {
        this.listaDocumentosTabla = listaDocumentosTabla;
    }

    public List<FecetDocExpediente> getListaDocumentosTabla() {
        return listaDocumentosTabla;
    }

    public void setPropuestasRechazadasVerifDoctoService(PropuestasRechazadasVerifDoctoService propuestasRechazadasVerifDoctoService) {
        this.propuestasRechazadasVerifDoctoService = propuestasRechazadasVerifDoctoService;
    }

    public PropuestasRechazadasVerifDoctoService getPropuestasRechazadasVerifDoctoService() {
        return propuestasRechazadasVerifDoctoService;
    }

    public void setDocumentoSeleccionado(FecetDocExpediente documentoSeleccionado) {
        this.documentoSeleccionado = documentoSeleccionado;
    }

    public FecetDocExpediente getDocumentoSeleccionado() {
        return documentoSeleccionado;
    }

    public void setDocumentoSeleccionDescarga(StreamedContent documentoSeleccionDescarga) {
        this.documentoSeleccionDescarga = documentoSeleccionDescarga;
    }

    public StreamedContent getDocumentoSeleccionDescarga() {
        documentoSeleccionDescarga
                = getDescargaArchivo(this.documentoSeleccionado.getRutaArchivo(), this.documentoSeleccionado.getNombre());
        return documentoSeleccionDescarga;
    }

    public BigDecimal getPresuntiva() {
        return presuntiva;
    }

    public void setPresuntiva(BigDecimal presuntiva) {
        this.presuntiva = presuntiva;
    }

    public boolean isTipoAccionPropuesta() {
        return tipoAccionPropuesta;
    }

    public void setTipoAccionPropuesta(boolean tipoAccionPropuesta) {
        this.tipoAccionPropuesta = tipoAccionPropuesta;
    }

    public boolean isEsOrg() {
        return esOrg;
    }

    public void setEsOrg(boolean esOrg) {
        this.esOrg = esOrg;
    }
    
}
