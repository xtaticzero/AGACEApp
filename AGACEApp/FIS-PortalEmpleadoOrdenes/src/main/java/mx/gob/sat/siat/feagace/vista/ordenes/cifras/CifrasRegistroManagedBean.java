package mx.gob.sat.siat.feagace.vista.ordenes.cifras;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.validator.ValidatorException;

import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.UploadedFile;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececConcepto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FeceaCifraImpuestoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FeceaCifraTipoCifraDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FeceaImpuestoConceptoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FececTipoParcialidadDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCifraDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocCifraDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetParcialidadCifraDTO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.vista.ordenes.cifras.helper.CifrasAttributeHelper;
import mx.gob.sat.siat.feagace.vista.ordenes.cifras.helper.CifrasColumasDetalleHelper;
import mx.gob.sat.siat.feagace.vista.ordenes.documentacion.carga.managedbean.DocumentacionOrdenMB;

@ManagedBean(name = "cifrasManagedBean")
@ViewScoped
public class CifrasRegistroManagedBean extends CifrasManagedBean {

    private static final long serialVersionUID = 1L;

    private static final int NUM_2 = 2;
    private static final int NUM_3 = 3;
    private static final int NUM_4 = 4;
    private static final int PARCIALIDAD_3 = 3;
    private static final int PARCIALIDAD_6 = 6;
    private static final int PARCIALIDAD_12 = 12;

    private List<FeceaCifraTipoCifraDTO> tipoCifras;

    private List<FececTipoImpuesto> impuestos;

    private List<FececConcepto> conceptos;

    private List<FececTipoParcialidadDTO> parcialidades;

    private List<FecetParcialidadCifraDTO> listaParcialidades;

    @ManagedProperty(value = "#{documentacionOrdenMB}")
    private DocumentacionOrdenMB documentacionOrdenMB;

    @PostConstruct
    public void init() {
        BigDecimal idOrden = documentacionOrdenMB.getDtoHelper().getOrdenSeleccionada().getIdOrden();
        setCifrasAttributeHelper(new CifrasAttributeHelper());
        setColumnaDetalleHelper(new CifrasColumasDetalleHelper());
        impuestos = getCifrasService().getCatalogoImpuesto();
        parcialidades = getCifrasService().obtenerParcialidades();
        setTotalCifras(getCifrasService().obtenerEncabezadoCifras(idOrden));
        obtenerTotalEncabezado();
    }

    public void onRowSelect(SelectEvent event) {
        BigDecimal idOrden = documentacionOrdenMB.getDtoHelper().getOrdenSeleccionada().getIdOrden();
        setImpuestosSeleccionados(
                getCifrasService().obtenerCifrasPorOrdenCifraTipoCifra(idOrden, 
                        getEncabezadoCifraSeleccionada().getTipoCifra().getIdCifra()));
        if (getImpuestosSeleccionados() != null && !getImpuestosSeleccionados().isEmpty()) {
            getCifrasAttributeHelper().setTablaImpuestosVisible(true);
        }
    }

    public List<FeceaCifraTipoCifraDTO> getTipoCifras() {
        return tipoCifras;
    }
        
    public void obtenerHistoricoEncabezado() {
        BigDecimal idOrden = documentacionOrdenMB.getDtoHelper().getOrdenSeleccionada().getIdOrden();
        obtenerHistoricoEncabezado(idOrden);
    }
    
    public void mostrarDetalleHistorico() {
        BigDecimal idOrden = documentacionOrdenMB.getDtoHelper().getOrdenSeleccionada().getIdOrden();
        mostrarDetalleHistorico(idOrden);
    }

    public void setTipoCifras(List<FeceaCifraTipoCifraDTO> tipoCifras) {
        this.tipoCifras = tipoCifras;
    }

    public void onTabChange(TabChangeEvent event) {
        BigDecimal idOrden = documentacionOrdenMB.getDtoHelper().getOrdenSeleccionada().getIdOrden();
        String indiceActivo = ((AccordionPanel) event.getComponent()).getActiveIndex();
        int tipoCifra = Integer.parseInt(indiceActivo) + 1;
        tipoCifras = getCifrasService().obtenerCifrasPorTipo(tipoCifra);
        limpiarPantalla();
        limpiarFormulario();
        configurarPantalla(tipoCifra);
        obtenerListaCifras(idOrden, new BigDecimal(tipoCifra));
        getCifrasAttributeHelper().setTablaImpuestosVisible(false);
        setTabSeleccionado(tipoCifra);
    }

    public void limpiarPantalla() {
        BigDecimal idOrden = documentacionOrdenMB.getDtoHelper().getOrdenSeleccionada().getIdOrden();
        setListaEncabezadoCifras(new ArrayList<FecetCifraDTO>());
        getCifrasAttributeHelper().setTablaImpuestosVisible(false);
        getCifrasAttributeHelper().setEsActualizacion(false);
        setTotalCifras(getCifrasService().obtenerEncabezadoCifras(idOrden));
        obtenerTotalEncabezado();
        obtenerListaCifras(idOrden, new BigDecimal(getTabSeleccionado()));
    }

    public void selectionChange(final AjaxBehaviorEvent event) {
        cargarCatalogoConceptos();
    }

    public void cargarCatalogoConceptos() {
        if (getCifrasAttributeHelper().getTipoImpuesto() != null) {
            conceptos = getCifrasService().getConceptoByImpuesto(getCifrasAttributeHelper().getTipoImpuesto().getIdTipoImpuesto());
        } else {
            conceptos = new ArrayList<FececConcepto>();
        }
    }

    public void cancelarActualizacion() {
        limpiarPantalla();
        limpiarFormulario();
    }

    public void limpiarFormulario() {
        getCifrasAttributeHelper().getTipoCifra().setIdCifra(SINOPCION);
        getCifrasAttributeHelper().setDerivaAntecedente(null);
        getCifrasAttributeHelper().getTipoImpuesto().setIdTipoImpuesto(SINOPCION);
        getCifrasAttributeHelper().getConcepto().setIdConcepto(-1);
        getCifrasAttributeHelper().setFechaPago(null);
        getCifrasAttributeHelper().setImporteImpuesto(null);
        getCifrasAttributeHelper().setActualizaciones(null);
        getCifrasAttributeHelper().setMultas(null);
        getCifrasAttributeHelper().setRecargos(null);
        getCifrasAttributeHelper().setTotal(Constantes.BIG_DECIMAL_CERO);
        getCifrasAttributeHelper().setPagoParcialidades(null);
        getCifrasAttributeHelper().getTipoParcialidad().setIdParcialidad(SINOPCION);
        getCifrasAttributeHelper().setNumeroParcialidades(SINOPCION);
        getCifrasAttributeHelper().setMontoParcialidad(null);
        getCifrasAttributeHelper().setListaDocumentosCifra(new ArrayList<FecetDocCifraDTO>());
        getCifrasAttributeHelper().setObservaciones(null);
        getCifrasAttributeHelper().setParcialidadesVisible(false);
        getCifrasAttributeHelper().setTablaImpuestosVisible(false);
        getCifrasAttributeHelper().setExisteCifra(false);
    }

    public void verificaExistenciaCifraImpuestoConcepto(final AjaxBehaviorEvent event) {
        if (getCifrasService().existeCifra(documentacionOrdenMB.getDtoHelper().getOrdenSeleccionada().getIdOrden(),
                getCifrasAttributeHelper().getTipoCifra().getIdCifra(), getCifrasAttributeHelper().getTipoImpuesto().getIdTipoImpuesto(),
                getCifrasAttributeHelper().getConcepto().getIdConcepto())) {
            getCifrasAttributeHelper().setExisteCifra(true);
        }
        else {
            getCifrasAttributeHelper().setExisteCifra(false);
        }
    }

    public void calculamensualidad(final AjaxBehaviorEvent event) {
        BigDecimal montoParcialidad = new BigDecimal(((UIOutput) event.getSource()).getValue().toString());
        if (getCifrasAttributeHelper().getTotal() != null) {
            montoParcialidad = getCifrasAttributeHelper().getTotal().divide(montoParcialidad, 2, RoundingMode.HALF_UP);
            getCifrasAttributeHelper().setMontoParcialidad(montoParcialidad);
            if (getCifrasAttributeHelper().getFechaPago() != null) {
                calcularParcialidades(getCifrasAttributeHelper().getFechaPago());
            }
        }
    }

    public void sumaTotal(final AjaxBehaviorEvent event) {
        BigDecimal actualizaciones = getCifrasAttributeHelper().getActualizaciones() != null ? getCifrasAttributeHelper().getActualizaciones()
                : Constantes.BIG_DECIMAL_CERO;
        BigDecimal importe = getCifrasAttributeHelper().getImporteImpuesto() != null ? getCifrasAttributeHelper().getImporteImpuesto()
                : Constantes.BIG_DECIMAL_CERO;
        BigDecimal multas = getCifrasAttributeHelper().getMultas() != null ? getCifrasAttributeHelper().getMultas() : Constantes.BIG_DECIMAL_CERO;
        BigDecimal recargos = getCifrasAttributeHelper().getRecargos() != null ? getCifrasAttributeHelper().getRecargos() : Constantes.BIG_DECIMAL_CERO;
        getCifrasAttributeHelper().setTotal(Constantes.BIG_DECIMAL_CERO);
        getCifrasAttributeHelper().setTotal(actualizaciones.add(importe).add(multas).add(recargos));
        limpiarParcialidades();
    }

    public void valueChange(final AjaxBehaviorEvent event) {
        if (getCifrasAttributeHelper().getPagoParcialidades().equals(BigDecimal.ONE)) {
            getCifrasAttributeHelper().setParcialidadesVisible(true);
        } else {
            getCifrasAttributeHelper().setParcialidadesVisible(false);
        }
    }

    public void validarTipoCifra(FacesContext context, UIComponent component, Object value) {
        if (Integer.parseInt(value.toString()) == -1) {
            FacesMessage msg = new FacesMessage(getMessageResourceString("label.cifras.detalle.tipo.error"));
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    public void validarTipoImpuesto(FacesContext context, UIComponent component, Object value) {
        if (Integer.parseInt(value.toString()) == -1) {
            FacesMessage msg = new FacesMessage(getMessageResourceString("label.cifras.detalle.tipoImpuesto.error"));
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    public void validarConcepto(FacesContext context, UIComponent component, Object value) {
        if (Integer.parseInt(value.toString()) == -1) {
            FacesMessage msg = new FacesMessage(getMessageResourceString("label.cifras.detalle.concepto.error"));
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    public void validarTipoParcialidad(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            FacesMessage msg = new FacesMessage(getMessageResourceString("label.cifras.detalle.tipoParcialidad.error"));
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    public void validarTipoMensualidad(FacesContext context, UIComponent component, Object value) {
        if (Integer.parseInt(value.toString()) == -1) {
            FacesMessage msg = new FacesMessage(getMessageResourceString("label.cifras.detalle.tipoMensualidad.error"));
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    public void validarParcialidades(FacesContext context, UIComponent component, Object value) {
        if (Integer.parseInt(value.toString()) == -1) {
            FacesMessage msg = new FacesMessage(getMessageResourceString("label.cifras.detalle.parcialildades.error"));
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    public void cargaDocumentacionCifras(final FileUploadEvent event) {
        if (validaArchivoCarga(event.getFile(), Constantes.DOCUMENTO_CARGADO_WORD)) {
            getCifrasAttributeHelper().setDocumentoCifras(event.getFile());
            cargarTablaDocumentoCifra();
        }
    }

    public void cargarTablaDocumentoCifra() {
        getCifrasAttributeHelper().setListaDocumentosCifra(
                cargarTablaDocumento(getCifrasAttributeHelper().getDocumentoCifras(), getCifrasAttributeHelper().getListaDocumentosCifra()));
    }

    public List<FecetDocCifraDTO> cargarTablaDocumento(UploadedFile file, List<FecetDocCifraDTO> documentos) {
        if (file != null) {
            try {
                FecetDocCifraDTO documento = new FecetDocCifraDTO();
                documento.setFechaCreacion(new Timestamp(Calendar.getInstance().getTimeInMillis()));
                documento.setRutaArchivo(file.getFileName());
                documento.setNombre(file.getFileName());
                documento.setDocumento(file.getInputstream());
                documentos.add(documento);
                addMessage(null, Constantes.CARGA_EXITOSA_DOC, "");
            } catch (Exception e) {
                logger.error(Constantes.FALLA_CARGA + e.getCause(), e);
                addErrorMessage(null, Constantes.FALLA_CARGA, "");
            }
        }
        return documentos;
    }

    public void agregarCifra() {        
        FecetCifraDTO cifraDTO = new FecetCifraDTO();
        FeceaCifraTipoCifraDTO tipoCifra = new FeceaCifraTipoCifraDTO();
        cifraDTO.setCifras(new ArrayList<FeceaCifraImpuestoDTO>());
        cifraDTO.setFechaInicio(Calendar.getInstance().getTime());
        tipoCifra.setIdCifra(getCifrasAttributeHelper().getTipoCifra().getIdCifra());
        for (FeceaCifraTipoCifraDTO tipo : tipoCifras) {
            if (tipoCifra.getIdCifra().equals(tipo.getIdCifra())) {
                tipoCifra.setDescripcion(tipo.getDescripcion());
                break;
            }
        }
        cifraDTO.setTipoCifra(tipoCifra);
        FeceaCifraImpuestoDTO cifra = creaCifraImpuesto();
        cifra.setTipoCifra(tipoCifra);
        cifraDTO.getCifras().add(cifra);
        cifraDTO.setCifra(getTabSeleccionado());
        getCifrasService().insertarCifra(cifraDTO, 
                documentacionOrdenMB.getDtoHelper().getOrdenSeleccionada());
        limpiarPantalla();
        limpiarFormulario();
    }

    public FeceaCifraImpuestoDTO creaCifraImpuesto() {
        FeceaCifraImpuestoDTO cifra = new FeceaCifraImpuestoDTO();
        FececTipoImpuesto impuesto = new FececTipoImpuesto();
        FececConcepto concepto = new FececConcepto();
        cifra.setIdCifraImpuesto(getCifrasAttributeHelper().getIdCifraImpuestoConcepto());
        cifra.setActualizaciones(getCifrasAttributeHelper().getActualizaciones());
        cifra.setFechaPago(getCifrasAttributeHelper().getFechaPago());
        cifra.setImporte(getCifrasAttributeHelper().getImporteImpuesto());
        cifra.setListaDocumentosCifra(getCifrasAttributeHelper().getListaDocumentosCifra());
        cifra.setMultas(getCifrasAttributeHelper().getMultas());
        cifra.setObservaciones(getCifrasAttributeHelper().getObservaciones());
        cifra.setRecargos(getCifrasAttributeHelper().getRecargos());
        cifra.setTotal(getCifrasAttributeHelper().getTotal());
        cifra.setImpuestoConcepto(new FeceaImpuestoConceptoDTO());
        cifra.setDerivaAntecedente(getCifrasAttributeHelper().getDerivaAntecedente());
        impuesto.setIdTipoImpuesto(getCifrasAttributeHelper().getTipoImpuesto().getIdTipoImpuesto());
        for (FececTipoImpuesto imp : impuestos) {
            if (impuesto.getIdTipoImpuesto().equals(imp.getIdTipoImpuesto())) {
                impuesto.setDescripcion(imp.getDescripcion());
                break;
            }
        }
        concepto.setIdConcepto(getCifrasAttributeHelper().getConcepto().getIdConcepto());
        for (FececConcepto conp : conceptos) {
            if (concepto.getIdConcepto() == conp.getIdConcepto()) {
                concepto.setDescripcion(conp.getDescripcion());
                break;
            }
        }
        cifra.getImpuestoConcepto().setImpuesto(impuesto);
        cifra.getImpuestoConcepto().setConcepto(concepto);
        cifra.setFechaInicio(Calendar.getInstance().getTime());
        if (getCifrasAttributeHelper().getPagoParcialidades() != null && getCifrasAttributeHelper().getPagoParcialidades().equals(BigDecimal.ONE)) {
            cifra.setParcialidad(new FecetParcialidadCifraDTO());
            FececTipoParcialidadDTO tipoParcialidad = new FececTipoParcialidadDTO();
            tipoParcialidad.setIdParcialidad(getCifrasAttributeHelper().getTipoParcialidad().getIdParcialidad());
            cifra.getParcialidad().setTipoParcialidad(tipoParcialidad);
            cifra.getParcialidad().setNumeroParcialidades(getCifrasAttributeHelper().getNumeroParcialidades());
            cifra.getParcialidad().setMontoTotal(getCifrasAttributeHelper().getMontoParcialidad());
            cifra.getParcialidad().setFechaInicio(Calendar.getInstance().getTime());
        }
        return cifra;
    }

    public void cargarDatos() {
        List<FeceaCifraImpuestoDTO> cifras = getCifrasService().obtenerCifrasPorOrdenCifraImpuestoConcepto(
                documentacionOrdenMB.getDtoHelper().getOrdenSeleccionada().getIdOrden(), getCifrasAttributeHelper().getTipoCifra().getIdCifra(),
                getCifrasAttributeHelper().getTipoImpuesto().getIdTipoImpuesto(), new BigDecimal(getCifrasAttributeHelper().getConcepto().getIdConcepto()));
        FeceaCifraImpuestoDTO cifra = cifras.get(0);
        mapearDatosActualizacion(cifra);
    }
    
    public void cancelarActualizarCifra() {
        limpiarPantalla();
        limpiarFormulario();
    }

    public void cargarDatosModificar() {
        mapearDatosActualizacion(getImpuestoSeleccionado());
    }

    public void mapearDatosActualizacion(FeceaCifraImpuestoDTO cifra) {
        limpiarPantalla();
        limpiarFormulario();
        getCifrasAttributeHelper().setTipoCifra(cifra.getTipoCifra());
        getCifrasAttributeHelper().setTipoImpuesto(cifra.getImpuestoConcepto().getImpuesto());
        cargarCatalogoConceptos();
        getCifrasAttributeHelper().setConcepto(cifra.getImpuestoConcepto().getConcepto());
        getCifrasAttributeHelper().setFechaPago(cifra.getFechaPago());
        getCifrasAttributeHelper().setImporteImpuesto(cifra.getImporte());
        getCifrasAttributeHelper().setActualizaciones(cifra.getActualizaciones());
        getCifrasAttributeHelper().setMultas(cifra.getMultas());
        getCifrasAttributeHelper().setRecargos(cifra.getRecargos());
        getCifrasAttributeHelper().setTotal(cifra.getTotal());
        getCifrasAttributeHelper().setDerivaAntecedente(cifra.getDerivaAntecedente());
        FecetParcialidadCifraDTO parcialidad = cifra.getParcialidad();
        if (parcialidad != null) {
            if (parcialidad.getTipoParcialidad() != null) {
                getCifrasAttributeHelper().setParcialidadesVisible(true);
                getCifrasAttributeHelper().setTipoParcialidad(parcialidad.getTipoParcialidad());
                getCifrasAttributeHelper().setNumeroParcialidades(parcialidad.getNumeroParcialidades());
                getCifrasAttributeHelper().setMontoParcialidad(parcialidad.getMontoTotal());
                getCifrasAttributeHelper().setPagoParcialidades(BigDecimal.ONE);
                calcularParcialidades(cifra.getFechaPago());
            }
        } else {
            getCifrasAttributeHelper().setPagoParcialidades(new BigDecimal(2));
        }
        getCifrasAttributeHelper().setListaDocumentosCifra(cifra.getListaDocumentosCifra());
        getCifrasAttributeHelper().setExisteCifra(false);
        getCifrasAttributeHelper().setEsActualizacion(true);
        getCifrasAttributeHelper().setObservaciones(cifra.getObservaciones());
        getCifrasAttributeHelper().setIdCifraImpuestoConcepto(cifra.getIdCifraImpuesto());
    }

    public void actualizarCifra() {
        FecetCifraDTO cifraDTO = new FecetCifraDTO();
        ArrayList<FeceaCifraImpuestoDTO> listaImpuestosDTO = new ArrayList<FeceaCifraImpuestoDTO>();
        FeceaCifraTipoCifraDTO tipoCifra = new FeceaCifraTipoCifraDTO();
        tipoCifra.setIdCifra(getCifrasAttributeHelper().getTipoCifra().getIdCifra());
        for (FeceaCifraTipoCifraDTO tipo : tipoCifras) {
            if (tipoCifra.getIdCifra().equals(tipo.getIdCifra())) {
                tipoCifra.setDescripcion(tipo.getDescripcion());
                break;
            }
        }
        cifraDTO.setTipoCifra(tipoCifra);
        cifraDTO.setFechaPago(getCifrasAttributeHelper().getFechaPago());
        cifraDTO.setFechaInicio(Calendar.getInstance().getTime());
        cifraDTO.setTotal(Constantes.BIG_DECIMAL_CERO);
        cifraDTO.setCifras(new ArrayList<FeceaCifraImpuestoDTO>());
        listaImpuestosDTO.add(creaCifraImpuesto());
        cifraDTO.setCifras(listaImpuestosDTO);
        cifraDTO.setCifra(getTabSeleccionado());
        getCifrasService().actualizarCifra(documentacionOrdenMB.getDtoHelper().
                getOrdenSeleccionada(), cifraDTO);
        limpiarPantalla();
        limpiarFormulario();
        addMessage("msgGuardar", getMessageResourceString("titulo.cifras.confirmacion.actualizar.cifra.exito"));
    }

    public void eliminarDocumento() {
        eliminarDocumentoIdenticoLista(getCifrasAttributeHelper().getListaDocumentosCifra());
    }

    public void eliminarDocumentoIdenticoLista(List<FecetDocCifraDTO> listaAnexos) {
        for (Iterator<FecetDocCifraDTO> iter = listaAnexos.iterator(); iter.hasNext();) {
            FecetDocCifraDTO documento = iter.next();
            if (documento == getCifrasAttributeHelper().getDocumentoSeleccionado()) {
                iter.remove();
            }
        }
    }

    public void eliminarCifraLista() {
        for (Iterator<FecetCifraDTO> iter = getListaEncabezadoCifras().iterator(); iter.hasNext();) {
            FecetCifraDTO cifraDTO = iter.next();
            if (cifraDTO.equals(getEncabezadoCifraSeleccionada())) {
                iter.remove();
            }
        }
    }

    public void eliminarCifra() {
        BigDecimal idOrden = documentacionOrdenMB.getDtoHelper().getOrdenSeleccionada().getIdOrden();
        getCifrasService().eliminaCifra(getEncabezadoCifraSeleccionada(), idOrden);
        limpiarPantalla();
        limpiarFormulario();
    }

    public void eliminarImpuesto() {
        getCifrasService().eliminaImpuesto(getImpuestoSeleccionado());
        limpiarPantalla();
        limpiarFormulario();
    }

    public void calcularParcialidades(Date fechaInicial) {
        setListaParcialidades(new ArrayList<FecetParcialidadCifraDTO>());
        BigDecimal parcialidad = getCifrasAttributeHelper().getTipoParcialidad().getIdParcialidad();
        Calendar fechaAlta = Calendar.getInstance();
        fechaAlta.setTime(fechaInicial);
        int tipoTiempo = 0;
        switch (parcialidad.intValue()) {
        case 1:
            tipoTiempo = 1;
            break;
        case NUM_2:
            tipoTiempo = PARCIALIDAD_3;
            break;
        case NUM_3:
            tipoTiempo = PARCIALIDAD_6;
            break;
        case NUM_4:
            tipoTiempo = PARCIALIDAD_12;
            break;
        default:
            break;
        }
        for (int i = 1; i <= getCifrasAttributeHelper().getNumeroParcialidades().intValue(); i++) {
            if (i > 1) {
                fechaAlta.add(Calendar.MONTH, tipoTiempo);
            }
            FecetParcialidadCifraDTO parcialidadDTO = new FecetParcialidadCifraDTO();
            parcialidadDTO.setFechaAlta(fechaAlta.getTime());
            parcialidadDTO.setMontoTotal(getCifrasAttributeHelper().getMontoParcialidad());
            parcialidadDTO.setIdParcialidadHistorico(new BigDecimal(i));
            getListaParcialidades().add(parcialidadDTO);
        }
    }

    public void limpiarParcialidades() {
        getCifrasAttributeHelper().setPagoParcialidades(null);
        getCifrasAttributeHelper().getTipoParcialidad().setIdParcialidad(SINOPCION);
        getCifrasAttributeHelper().setNumeroParcialidades(SINOPCION);
        getCifrasAttributeHelper().setMontoParcialidad(null);
        setListaParcialidades(new ArrayList<FecetParcialidadCifraDTO>());
        getCifrasAttributeHelper().setParcialidadesVisible(false);
    }

    public List<FececTipoImpuesto> getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(List<FececTipoImpuesto> impuestos) {
        this.impuestos = impuestos;
    }

    public List<FececConcepto> getConceptos() {
        return conceptos;
    }

    public void setConceptos(List<FececConcepto> conceptos) {
        this.conceptos = conceptos;
    }

    public List<FececTipoParcialidadDTO> getParcialidades() {
        return parcialidades;
    }

    public void setParcialidades(List<FececTipoParcialidadDTO> parcialidades) {
        this.parcialidades = parcialidades;
    }

    public DocumentacionOrdenMB getDocumentacionOrdenMB() {
        return documentacionOrdenMB;
    }

    public void setDocumentacionOrdenMB(DocumentacionOrdenMB documentacionOrdenMB) {
        this.documentacionOrdenMB = documentacionOrdenMB;
    }

    public List<FecetParcialidadCifraDTO> getListaParcialidades() {
        return listaParcialidades;
    }

    public void setListaParcialidades(List<FecetParcialidadCifraDTO> listaParcialidades) {
        this.listaParcialidades = listaParcialidades;
    }

}
