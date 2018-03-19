package mx.gob.sat.siat.feagace.vista.ordenes.carga;

import java.io.IOException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.FileUploadEvent;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececAgteAduanal;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececAuditor;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececRepLegal;
import mx.gob.sat.siat.feagace.modelo.dto.common.NotificacionDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilOrdenes;
import mx.gob.sat.siat.feagace.vista.util.Expresiones;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;
import mx.gob.sat.siat.feagace.vista.util.MetodosGenericos;
import mx.gob.sat.siat.feagace.vista.validador.RfcValidadorOpcional;

@ManagedBean(name = "cargarOrdenMB")
@SessionScoped
public class CargarOrdenMB extends CargaOrdenMBSubAbstract {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final String FORM_ASIGNA_AGENTE = "formAsignar:rfcAgenteRep";
    private static final String FORM_ASIGNA_REP = "formAsignar:rfcRep";
    private static final String AGENTE_ADUANAL = "Agente Aduanal";
    private static final String REPRESENTANTE_LEGAL = "Representante Legal";
    
    @PostConstruct
    public void init() {
        limpiarCampos();
        setListaOrdenes(new ArrayList<AgaceOrden>());
        setListaEnviarOrdenes(new ArrayList<AgaceOrden>());
        cargarMetodos();
        getListaAuditor();
        setMostrarCargarArchivo(false);
        LOGGER.info("Panel Links" + FORM_PANEL_LINKS);
    }
    
    public void getListaAuditor() {
        getListaOrdenes().clear();
        setListaOrdenes(getCargarOrdenService().getListaAuditor(getRfcAuditor()));
    }

    private void cargarMetodos() {
        setListaMetodos(getCargarOrdenService().getMetodos());
    }

    public void actualizarAltaPrioridad() {
        if (getFeceaAgaceOrdenCaptura().isPrioridadAlta()) {
            setMostrarMotivoPrioridadAlta(false);
            getFeceaAgaceOrdenCaptura().setPrioridad(true);
        } else {
            setMostrarMotivoPrioridadAlta(true);
            getFeceaAgaceOrdenCaptura().setPrioridad(false);
        }
    }

    private String configuraMetodoEEHO() {
        setMostrarEscritoHechos(true);
        setMostrarCartaInvitacion(false);
        setMostrarRevisionElectronica(false);
        setDeshabilitarComboInicialesGabinete(true);
        setMostrarGabinete(false);
        setSeleccionGabinete("");

        return "EEHO" + getLocalArace();
    }

    private String configuraMetodoEUCA() {
        setMostrarEscritoHechos(false);
        setMostrarCartaInvitacion(true);
        setMostrarRevisionElectronica(false);
        setDeshabilitarComboInicialesGabinete(true);
        setMostrarGabinete(false);
        setMostrarMotivoPrioridadAlta(true);
        setDeshabilitarPrioridadAlta(true);
        getFeceaAgaceOrdenCaptura().setPrioridadAlta(false);
        getFeceaAgaceOrdenCaptura().setPrioridad(false);
        setSeleccionGabinete("");

        return "EUCA" + getLocalArace();
    }

    public void mostrarSeleccionMetodo() {
        String gabineteLocal = "";
        final Long numero3L = 3L;
        final Long numero4L = 4L;
        setDeshabilitarPrioridadAlta(false);

        if (getFeceaAgaceOrdenCaptura().getIdMetodo().equals(BigDecimal.ONE)) {
            gabineteLocal = configuraMetodoEEHO();
        } else if (getFeceaAgaceOrdenCaptura().getIdMetodo().equals(BigDecimal.valueOf(2L))) {
            setMostrarEscritoHechos(false);
            setMostrarCartaInvitacion(false);
            setMostrarRevisionElectronica(false);
            setDeshabilitarComboInicialesGabinete(false);
            setMostrarGabinete(true);
        } else if (getFeceaAgaceOrdenCaptura().getIdMetodo().equals(BigDecimal.valueOf(numero3L))) {

            gabineteLocal = configuraMetodoEUCA();
        } else if (getFeceaAgaceOrdenCaptura().getIdMetodo().equals(BigDecimal.valueOf(numero4L))) {
            setMostrarEscritoHechos(false);
            setMostrarCartaInvitacion(false);
            setMostrarRevisionElectronica(true);
            setDeshabilitarComboInicialesGabinete(true);
            setMostrarGabinete(false);
            gabineteLocal = "EREE" + getLocalArace();
            setSeleccionGabinete("");
        } else if (getFeceaAgaceOrdenCaptura().getIdMetodo().equals(BigDecimal.valueOf(-1L))) {
            setMostrarEscritoHechos(false);
            setMostrarCartaInvitacion(false);
            setMostrarRevisionElectronica(false);
            setDeshabilitarComboInicialesGabinete(true);
            setMostrarGabinete(false);
        }

        setLocalConMascara(gabineteLocal);
        this.getFeceaAgaceOrdenCaptura().setFeceaMetodo(buscarMetodo(getFeceaAgaceOrdenCaptura().getIdMetodo()));
    }

    public void buscarRfcContribuyente() {
        accionValidaCampos();
    }

    public void buscarRfcAgente() {
        if (getRfcAsignarAgente() != null && !"".equals(getRfcAsignarAgente())) {
            if (validacionOpcional(getRfcAsignarAgente().toUpperCase(), FORM_ASIGNA_AGENTE)) {
                setNombreAsignarAgente(
                        getRfcContribuyente(getRfcAsignarAgente().toUpperCase(), FORM_ASIGNA_AGENTE));
                if (getNombreAsignarAgente() == null || "".equals(getNombreAsignarAgente())) {
                    setNombreAsignarAgente("");
                    setGuardarRepAgente(true);
                } else {
                    setGuardarRepAgente(false);
                }

            } else {
                setNombreAsignarAgente("");
            }
        } else {

            setGuardarRepAgente(true);
        }
    }

    public void buscarRfcRepresentante() {
        if (getRfcAsignarRepresentante() != null && getRfcAsignarRepresentante().length() > 0) {
            if (validacionOpcional(getRfcAsignarRepresentante().toUpperCase(), FORM_ASIGNA_REP)) {
                setNombreAsignarRepresentante(
                        getRfcContribuyente(getRfcAsignarRepresentante().toUpperCase(), FORM_ASIGNA_REP));
                if (getNombreAsignarRepresentante() == null || "".equals(getNombreAsignarRepresentante())) {
                    setNombreAsignarRepresentante("");
                    setGuardarRepAgente(true);
                } else {
                    setGuardarRepAgente(false);
                }
            } else {
                setNombreAsignarRepresentante("");
            }

        } else {
            setGuardarRepAgente(true);
        }
    }

    public void buscarRfcAsignar() {
        if (getTipoAsignar().equals(AGENTE_ADUANAL)) {
            buscarRfcAgente();
        }

        if (getTipoAsignar().equals(REPRESENTANTE_LEGAL)) {
            buscarRfcRepresentante();
        }
    }

    private boolean validacionOpcional(String rfcIngresado, String componente) {
        RfcValidadorOpcional rfcValidadorOpcional = new RfcValidadorOpcional();

        setGuardarRepAgente(true);
        if (rfcValidadorOpcional.validaLongitud(rfcIngresado, componente)
                && (rfcIngresado != null && !rfcIngresado.equals(""))) {
            if (rfcIngresado.length() == Expresiones.RFC_LONGITUD) {
                setNombreAsignarRepresentante("");
                return rfcValidadorOpcional.validaRFCFisica(rfcIngresado, componente);
            } else if (rfcIngresado.length() == Expresiones.RFCM_LONGITUD) {
                setNombreAsignarRepresentante("");
                return rfcValidadorOpcional.validaRFCMoral(rfcIngresado, componente);
            }
        }
        return false;
    }

    public void asignarAgenteRepresentante() {
        if (getTipoAsignar().equals(AGENTE_ADUANAL)) {
            getFeceaAgaceOrdenCaptura().getFececAgteAduanal().setNombre(getNombreAsignarAgente());
            getFeceaAgaceOrdenCaptura().getFececAgteAduanal().setRfc(getRfcAsignarAgente());
            setGuardarRepAgente(true);
            FacesUtil.addInfoMessage(null, "Se ha asignado el Agente Aduanal");
        }

        if (getTipoAsignar().equals(REPRESENTANTE_LEGAL)) {
            getFeceaAgaceOrdenCaptura().getFececRepLegal().setNombre(getNombreAsignarRepresentante());
            getFeceaAgaceOrdenCaptura().getFececRepLegal().setRfc(getRfcAsignarRepresentante());
            setGuardarRepAgente(true);
            FacesUtil.addInfoMessage(null, "Se ha asignado el Representante Legal");
        }
    }

    public String cancelarAsignacion() {
        cancelarAsignarRepAgente();
        return "indexPruebas?faces-redirect=true";
    }

    public void limpiarDatos() {
        cancelarAsignarRepAgente();
        if (getTipoAsignar().equals(AGENTE_ADUANAL)) {
            FacesUtil.addInfoMessage(null, "La información se ha borrado �xitosamente");
        }
        if (getTipoAsignar().equals(REPRESENTANTE_LEGAL)) {
            FacesUtil.addInfoMessage(null, "La información se ha borrado �xitosamente");
        }
    }

    public void cancelarAsignarRepAgente() {
        if (getTipoAsignar().equals(AGENTE_ADUANAL)) {
            cancelarAsignarAgente();
        }

        if (getTipoAsignar().equals(REPRESENTANTE_LEGAL)) {
            cancelarAsignarRepresentante();
        }
    }

    public String regresarAsignarRepLegalAgtAduanal() {
        if (getTipoAsignar().equals(AGENTE_ADUANAL)) {
            setNombreAsignarAgente(getFeceaAgaceOrdenCaptura().getFececAgteAduanal().getNombre());
            setRfcAsignarAgente(getFeceaAgaceOrdenCaptura().getFececAgteAduanal().getRfc());
        }

        if (getTipoAsignar().equals(REPRESENTANTE_LEGAL)) {
            setNombreAsignarRepresentante(getFeceaAgaceOrdenCaptura().getFececRepLegal().getNombre());
            setRfcAsignarRepresentante(getFeceaAgaceOrdenCaptura().getFececRepLegal().getRfc());
        }

        setGuardarRepAgente(true);
        return "indexPruebas?faces-redirect=true";
    }

    public String muestraDatoRepresentante() {
        setMostrarDatosAgente(false);
        setMostrarDatosRepresentante(true);
        return "asignarRepresentanteOAgenteAlContribuyente?faces-redirect=true";
    }

    public String muestraDatosAgente() {
        setMostrarDatosAgente(true);
        setMostrarDatosRepresentante(false);
        return "asignarRepresentanteOAgenteAlContribuyente?faces-redirect=true";
    }

    public void borrarArchivoLista() {

        if (CargaArchivoUtilOrdenes.borrarArchivoCargado(getOrdenSeleccionDescarga())) {
            getCargarOrdenService().borrarOrdenAuditor(getOrdenSeleccionDescarga());
            init();
            FacesUtil.addInfoMessage(null, FacesUtil.getMessageResourceString("mensaje.exito.borrar"), "");
        } else {
            FacesUtil.addErrorMessage(null, "No se pudo descartar la orden", "");
        }

    }

    public void guardarOrdenLista() {
        if (validaCapturaCampos(getFeceaAgaceOrdenCaptura()) && validaformulario()) {
            getFeceaAgaceOrdenCaptura().setNumeroOrden(getLocalConMascara() + getCampoClaveOrden());

            try {
                if (cargarArchivoOrden()) {
                    this.getFeceaAgaceOrdenCaptura().setIdOrden(getCargarOrdenService().guardarOrden(this.getFeceaAgaceOrdenCaptura()));
                    getListaAuditor();

                    FacesUtil.addInfoMessage(null, "Se guardo la orden exitósamente", "");

                    this.limpiarCampos();
                } else {
                    FacesUtil.addErrorMessage(null, "La orden ya fue registrada", "");
                }
            } catch (NegocioException e) {
                FacesUtil.addErrorMessage(null, "No se pudo guardar el registro", "");
                LOGGER.error("Error al guardar el registro: ", e);
            }
        }
    }

    public void enviarListaSeleccionOrdenes() {
        enviarListaOrdenes(getListaEnviarOrdenes());
    }

    public void enviarListaOrdenesCompleta() {
        enviarListaOrdenes(getListaOrdenes());
    }

    private void enviarListaOrdenes(final List<AgaceOrden> lista) {

        if (lista != null) {
            if (!lista.isEmpty()) {
                getCargarOrdenService().enviarOrdenes(lista);

                /**
                 * ++++++++++++++++++++++++ INICIO - CODIGO PARA ENVIO DE CORREO
                 * +++++++++++++++++++++++++++++++
                 */
                for (AgaceOrden agaceOrden : lista) {
                    NotificacionDTO notificacion = new NotificacionDTO();
                    notificacion.setAsunto(FacesUtil.getMessageResourceString("email.notificacion.envio.orden"));
                    notificacion.setFolioCarga(agaceOrden.getIdOrden().toString());
                    notificacion.setFechaTramite(agaceOrden.getFechaCreacion().toString());
                    notificacion.setMetodo(MetodosGenericos.acentuar(agaceOrden.getFeceaMetodo().getNombre()));
                    notificacion.setOrden(agaceOrden.getNumeroOrden());
                    //AJUSTAR DATOS
                    notificacion.setTipoCorreo(1);
                    notificacion.setCorreoPor(1);
                    notificacion.setCorreoPorValor(getLocalArace());
                    LOGGER.error("ENTRO A ENVIAR CORREO EN MB");

                }
                /**
                 * +++++++++++++++++++++++++++++ FIN - CODIGO PARA ENVIO DE
                 * CORREO +++++++++++++++++++++++++++++
                 */

                FacesUtil.addInfoMessage(null, "Se enviaron la(s) orden(es) exitósamente", "");
                init();
            } else {
                if (getListaOrdenes().isEmpty()) {
                    FacesUtil.addErrorMessage(null, "Se debe de registrar al menos una orden", "");
                } else {
                    FacesUtil.addErrorMessage(null,
                            FacesUtil.getMessageResourceString("error.lista.sin.seleccion"), "");
                }

            }
        } else {
            FacesUtil.addErrorMessage(null, FacesUtil.getMessageResourceString("error.lista.vacia"), "");
        }

    }

    private boolean cargarArchivoOrden() throws NegocioException {
        try {
            if (getArchivoOrden() != null) {
                if (getArchivoOrden().getFileName().endsWith(Constantes.ARCHIVO_WORD_DESPUES_2007)) {
                    asignarArchivoOrden();
                    getCargarOrdenService().subirArchivoServidor(getFeceaAgaceOrdenCaptura());
                    return true;
                } else {
                    FacesUtil.addErrorMessage(null, "Archivo inv�lido, solo se aceptan archivos .docx", "");
                }
            } else {
                FacesUtil.addErrorMessage(null, FacesUtil.getMessageResourceString("error.archivo.vacio"), "");
            }
        } catch (NegocioException e) {
            LOGGER.error("No se pudo cargar el archivo ", e);
        }

        return false;
    }

    private void asignarArchivoOrden() {
        try {
            this.getFeceaAgaceOrdenCaptura().setArchivoOrden(getArchivoOrden().getInputstream());
        } catch (IOException e) {
            FacesUtil.addErrorMessage(null, "No pudo asignar el archivo", "");
            LOGGER.error("No pudo asignar el archivo ", e);
        }
    }

    private FececMetodo buscarMetodo(final BigDecimal idSeleccionado) {
        FececMetodo dto = new FececMetodo();
        for (FececMetodo metodo : getListaMetodos()) {
            if (metodo.getIdMetodo().equals(idSeleccionado)) {
                dto.setAbreviatura(metodo.getAbreviatura());
                dto.setNombre(metodo.getNombre());
                dto.setIdMetodo(metodo.getIdMetodo());
            }
        }

        return dto;
    }
    
    private void accionValidaCampos() {
        if (validaCapturaCampos(getFeceaAgaceOrdenCaptura())) {
            setMostrarCargarArchivo(true);
        } else {
            setMostrarCargarArchivo(false);
        }
    }
    
    private boolean validaCapturaCampos(final AgaceOrden orden) {
        boolean formularioValido = false;
        if (validaCapturaCamposParte1(orden) && validaCapturaCamposParte2(orden)) {
            formularioValido = true;
        }
        return formularioValido;
    }
    
    private boolean validaCapturaCamposParte2(final AgaceOrden orden) {
        if (validaNombreContribuyente(orden) && validaDescripcionPrioridadAlta()) {
            return true;
        }
        return false;
    }
    
    private boolean validaCapturaCamposParte1(final AgaceOrden orden) {
        if (validarMetodoOrden(orden) && validaRfcContribuyente(orden)) {
            return true;
        }
        return false;
    }   
    
    public void limpiarCampos() {
        FececAuditor auditor = getAuditorLogueado();
        setRfcAuditor(auditor.getRfc());
        setLocalArace(auditor.getIdArace().toString());

        setRfcAsignarAgente("");
        setRfcAsignarRepresentante("");
        setNombreAsignarAgente("");
        setNombreAsignarRepresentante("");

        final Date fechaActual = new Date();
        setFeceaAgaceOrdenCaptura(new AgaceOrden());
        getFeceaAgaceOrdenCaptura().setFeceaMetodo(new FececMetodo());
        getFeceaAgaceOrdenCaptura().setFechaCreacion(fechaActual);
        //AJUSTAR DATOS
        getFeceaAgaceOrdenCaptura().setPrioridad(false);
        getFeceaAgaceOrdenCaptura().setPrioridadAlta(false);
        //AJUSTAR DATOS
        cancelarAsignarAgente();
        cancelarAsignarRepresentante();

        setMostrarEscritoHechos(false);
        setMostrarCartaInvitacion(false);
        setMostrarRevisionElectronica(false);
        setMostrarMotivoPrioridadAlta(true);
        setDeshabilitarComboInicialesGabinete(true);
        setMostrarGabinete(false);

        setMostrarDatosAgente(false);
        setMostrarDatosRepresentante(false);
        setArchivoOrden(null);

        setOrdenSeleccionDescarga(new AgaceOrden());
        getOrdenSeleccionDescarga().setFeceaMetodo(new FececMetodo());
        getOrdenSeleccionDescarga().setFechaCreacion(fechaActual);

        setSeleccionGabinete("");
        setListaGabinetes(cargaListaGabinetes());

        setCampoClaveOrden("");

        setLocalConMascara("");

        setDeshabilitarPrioridadAlta(false);
        setMostrarRechazarOrden(false);

        setOrdenesArchivoCargado(new ArrayList<FecetPromocion>());

        setGuardarRepAgente(true);
    }
    
    private List<String> cargaListaGabinetes() {
        List<String> lista = new ArrayList<String>();

        lista.add("CGA");
        lista.add("COM");
        lista.add("CPA");
        lista.add("GPF");
        lista.add("GRM");
        lista.add("GCD");
        lista.add("GCM");
        lista.add("GAD");
        lista.add("GIF");
        lista.add("GIM");
        lista.add("GDV");
        lista.add("GRF");
        lista.add("CGE");
        lista.add("CGR");
        lista.add("CVM");
        lista.add("CVV");
        lista.add("PPA");
        lista.add("GCS");

        return lista;
    }
    
    public void cancelarAsignarRepresentante() {
        getFeceaAgaceOrdenCaptura().setFececRepLegal(new FececRepLegal());
        getFeceaAgaceOrdenCaptura().getFececRepLegal().setNombre("");
        getFeceaAgaceOrdenCaptura().getFececRepLegal().setRfc(null);
        setNombreAsignarRepresentante("");
        setRfcAsignarRepresentante("");
        setGuardarRepAgente(true);
    }

    public void cancelarAsignarAgente() {
        getFeceaAgaceOrdenCaptura().setFececAgteAduanal(new FececAgteAduanal());
        getFeceaAgaceOrdenCaptura().getFececAgteAduanal().setNombre("");
        getFeceaAgaceOrdenCaptura().getFececAgteAduanal().setRfc(null);
        setNombreAsignarAgente("");
        setRfcAsignarAgente("");
        setGuardarRepAgente(true);
    }

    public void mostrarRechazo() {
        setMostrarRechazarOrden(true);
    }

    public void ocultarRechazo() {
        setMostrarRechazarOrden(false);
    }

    public void seleccionComboGabinete() {
       setLocalConMascara("E" + getSeleccionGabinete() + getLocalArace());
    }

    protected boolean validaCapturaMetodo(final AgaceOrden orden) {
        boolean camposCorrectos = true;

        if (orden.getIdMetodo().equals(BigDecimal.valueOf(2L))
                && (getSeleccionGabinete() == null || "".equals(getSeleccionGabinete()))) {
            camposCorrectos = false;
            FacesUtil.addErrorMessage("formOrdenes:cmbInicialesGabinete",
                    FacesUtil.getMessageResourceString(CAMPO_OBLIGATORIO));
        }

        if (getCampoClaveOrden().equals("")) {
            final String formHomoClave = "formOrdenes:txtHomoClaves";
            final Long numero3L = 3L;
            final Long numero4L = 4L;
            camposCorrectos = false;

            if (orden.getIdMetodo().equals(BigDecimal.ONE)) {
                FacesUtil.addErrorMessage(formHomoClave, FacesUtil.getMessageResourceString(CAMPO_OBLIGATORIO));
            } else if (orden.getIdMetodo().equals(BigDecimal.valueOf(2L))) {
                FacesUtil.addErrorMessage(formHomoClave, FacesUtil.getMessageResourceString(CAMPO_OBLIGATORIO));
            } else if (orden.getIdMetodo().equals(BigDecimal.valueOf(numero3L))) {
                FacesUtil.addErrorMessage(formHomoClave, FacesUtil.getMessageResourceString(CAMPO_OBLIGATORIO));
            } else if (orden.getIdMetodo().equals(BigDecimal.valueOf(numero4L))) {
                FacesUtil.addErrorMessage(formHomoClave, FacesUtil.getMessageResourceString(CAMPO_OBLIGATORIO));
            }
        }

        return camposCorrectos;
    }

    protected boolean validarMetodoOrden(final AgaceOrden orden) {
        boolean banderaMetodoValido = true;

        if (orden.getIdMetodo() == null || orden.getIdMetodo().equals(BigDecimal.valueOf(-1L))) {
            FacesUtil.addErrorMessage("formOrdenes:cmbMetodo", FacesUtil.getMessageResourceString(CAMPO_OBLIGATORIO));
            banderaMetodoValido = false;
        } else {
            if (!validaCapturaMetodo(orden)) {
                banderaMetodoValido = false;
            }
        }

        return banderaMetodoValido;
    }

    protected boolean validaRfcContribuyente(final AgaceOrden orden) {
        boolean banderaRFCContribuyenteValido = true;

        //AJUSTAR DATOS A OBJ
        FacesUtil.addErrorMessage(FORM_RFC, FacesUtil.getMessageResourceString(CAMPO_OBLIGATORIO));
        banderaRFCContribuyenteValido = false;

        return banderaRFCContribuyenteValido;
    }

    protected boolean validaNombreContribuyente(final AgaceOrden orden) {
        boolean banderaNombreContribuyenteValido = true;
        //AJUSTAR DATOS OBJ
        banderaNombreContribuyenteValido = false;
        FacesUtil.addErrorMessage(FORM_RFC, FacesUtil.getMessageResourceString(CAMPO_OBLIGATORIO));

        return banderaNombreContribuyenteValido;
    }

    protected boolean validaformulario() {
        boolean validaFormulario = true;
        if (getArchivoOrden() == null) {
            validaFormulario = false;
            FacesUtil.addErrorMessage(null, FacesUtil.getMessageResourceString("error.archivo.vacio"));
        }
        return validaFormulario;
    }

    protected boolean validaDescripcionPrioridadAlta() {
        boolean banderaDescripcion = true;
        if ((!isMostrarMotivoPrioridadAlta() && getFeceaAgaceOrdenCaptura().getPrioridad())) {
            banderaDescripcion = false;
            FacesUtil.addErrorMessage(FORM_PRIORIDAD, FacesUtil.getMessageResourceString(CAMPO_OBLIGATORIO));
        }

        return banderaDescripcion;
    }
    
    public void handleFileUpload(FileUploadEvent event) {
        boolean isFileCargado = true;
        final Date fechaActual = new Date();
        FecetPromocion promoTemp = new FecetPromocion();
        final Long tamanioMaximoMB = 4196000L;

        if (isFileCargado && validaArchivoCarga(event.getFile(), 1L)) {
            setArchivoOrden(event.getFile());
            if (getArchivoOrden().getSize() > 0L && getArchivoOrden().getSize() <= tamanioMaximoMB) {
                setOrdenesArchivoCargado(new ArrayList<FecetPromocion>());
                promoTemp.setNombreArchivo(CargaArchivoUtilOrdenes.limpiarPathArchivo(CargaArchivoUtilOrdenes.aplicarCodificacionTexto(getArchivoOrden().getFileName())));
                promoTemp.setFechaCarga(fechaActual);
                getOrdenesArchivoCargado().add(promoTemp);
                FacesUtil.addInfoMessage(null,
                        FacesUtil.getMessageResourceString("label.archivo.guardado.exitosamente"),
                        "");
            } else {
                setArchivoOrden(null);
                if (getArchivoOrden().getSize() >= tamanioMaximoMB) {                   
                    FacesUtil.addErrorMessage(null, "Error al cargar el archivo. El archivo es demasiado grande");
                } else {                    
                    FacesUtil.addErrorMessage(null, "Error al cargar el archivo. El archivo es demasiado chico");
                }
            }
        }
    }


}
