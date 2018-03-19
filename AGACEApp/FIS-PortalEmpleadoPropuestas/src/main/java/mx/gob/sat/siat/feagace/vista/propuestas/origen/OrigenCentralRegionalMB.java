package mx.gob.sat.siat.feagace.vista.propuestas.origen;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececConcepto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesPropuestas;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilPropuestas;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtilPropuestas;

@SessionScoped
@ManagedBean(name = "origenCentralRegionalMB")
public class OrigenCentralRegionalMB extends OrigenCentralRegionalMBAbstract {

    private static final long serialVersionUID = 1L;

    public String registrarInsumoComite() {
        String siguientePantalla = "";
        if (getListaHelper().getListaImpuestos() != null && getListaHelper().getListaImpuestos().size() > 0) {
            getAtrributeHelper().getPropuesta().setFecetContribuyente(getAtrributeHelper().getContribuyente());
            
            if (getAtrributeHelper().getPropuesta().getIdMetodo().intValue() == Constantes.ENTERO_DOS
                    && getAtrributeHelper().getPropuesta().getPrioridadSugerida().equals("2")) {
                getAtrributeHelper().setFechaInformeComReg(null);
            }
            
            getAtrributeHelper().getPropuesta().setFechaInforme(getAtrributeHelper().getFechaInformeComReg());
            getAtrributeHelper().getPropuesta().setFechaPresentacion(getAtrributeHelper().getFechaPresentacionComReg());
            getAtrributeHelper().getPropuesta().setIdEstatus(new BigDecimal(TipoEstatusEnum.PROPUESTA_CONFIRMADO_POR_REGIONAL.getId()));

            if (!getAtrributeHelper().getPropuesta().getIdMetodo().equals(new BigDecimal(2))) {
                getAtrributeHelper().getPropuesta().setIdRevision(null);
            }
            eliminarImpuestos();
            try {
                getOrigenCentralRegionalServ().actualizaPropuestaRegistrar(getAtrributeHelper().getPropuesta());
                siguientePantalla = registrarInsumo();
            } catch (NegocioException e) {
                logger.error(e.getMessage());
            }
        } else {
            addErrorMessage(null, "Debes agregar un impuesto", "");
        }
        return siguientePantalla;
    }

    public String registrarInsumoComplementar() {
        String siguientePantalla = "";
        if (getListaHelper().getListaImpuestos() != null && getListaHelper().getListaImpuestos().size() > 0) {
            getAtrributeHelper().getPropuesta().setFecetContribuyente(getAtrributeHelper().getContribuyente());
            if (getAtrributeHelper().getPropuesta().getIdMetodo().intValue() == Constantes.ENTERO_DOS
                    && getAtrributeHelper().getPropuesta().getPrioridadSugerida().equals("2")) {
                getAtrributeHelper().setFechaInformeComReg(null);
            }
            getAtrributeHelper().getPropuesta().setFechaInforme(getAtrributeHelper().getFechaInformeComReg());
            getAtrributeHelper().getPropuesta().setFechaPresentacion(getAtrributeHelper().getFechaPresentacionComReg());
            getAtrributeHelper().getPropuesta().setIdEstatus(new BigDecimal(TipoEstatusEnum.PROPUESTA_CONFIRMADO_POR_REGIONAL.getId()));

            if (!getAtrributeHelper().getPropuesta().getIdMetodo().equals(new BigDecimal(2))) {
                getAtrributeHelper().getPropuesta().setIdRevision(null);
            }
            eliminarImpuestos();
            try {
                getOrigenCentralRegionalServ().actualizaPropuestaComplementar(getAtrributeHelper().getPropuesta());
            } catch (NegocioException e) {
                logger.error(e.getMessage());
            }
            siguientePantalla = registrarInsumo();
        } else {
            addErrorMessage(null, "Debes agregar un impuesto", "");
        }
        return siguientePantalla;
    }

    public String registrarInsumo() {
        String siguientePantalla = "";
        try {
            for (FecetImpuesto impuestoItem : getListaHelper().getListaImpuestos()) {
                if (impuestoItem.getIdImpuesto() == null) {
                    FecetImpuesto impuesto = new FecetImpuesto();
                    impuesto.setIdPropuesta(getAtrributeHelper().getPropuesta().getIdPropuesta());
                    impuesto.setIdTipoImpuesto(impuestoItem.getIdTipoImpuesto());
                    impuesto.setMonto(impuestoItem.getMonto());
                    impuesto.setIdConcepto(impuestoItem.getIdConcepto());
                    impuesto.setPeriodoInicial(impuestoItem.getPeriodoInicial());
                    impuesto.setPeriodoFinal(impuestoItem.getPeriodoFinal());
                    getRegistrarPropuestaIndividualService().insertarImpuesto(impuesto);
                }
            }
            for (FecetDocExpediente docExpItem : getListaHelper().getListaDocumento()) {
                docExpItem.setIdPropuesta(getAtrributeHelper().getPropuesta().getIdPropuesta());
                getAtrributeHelper().getPropuesta().setFecetContribuyente(getAtrributeHelper().getContribuyente());
                docExpItem.setRutaArchivo(
                        RutaArchivosUtilPropuestas.armarRutaDestinoPropuesta(getAtrributeHelper().getPropuesta()) + docExpItem.getNombre());
                getRegistrarPropuestaIndividualService().insertarDocumento(docExpItem);
                try {
                    CargaArchivoUtilPropuestas.guardarArchivo(docExpItem.getArchivo(), docExpItem.getRutaArchivo(),
                            docExpItem.getNombre());
                } catch (IOException e) {
                    logger.info("No se pudo guardar archivo");
                }
            }

            if (isComplementado()) {
                addMessage(null,
                        "El registro " + getAtrributeHelper().getPropuesta().getIdRegistro()
                        + " ha sido actualizado; se enviará al área correspondiente para su validaci\u00f3n.",
                        "");
            } else {
                addMessage(null,
                        "El registro " + getAtrributeHelper().getPropuesta().getIdRegistro()
                        + " ha sido confirmado por el regional y ser\u00e1 enviado al área correspondiente para su validaci\u00f3n.",
                        "");
            }
            getAtrributeHelper().setFechaInformeComReg(null);
            getAtrributeHelper().setFechaPresentacionComReg(null);
            setAccion(false);
            setComplementado(false);
            siguientePantalla = "indexOrigenCentralRegional";

        } catch (Exception e) {
            logger.error("Error: " + e);
        }
        return siguientePantalla;
    }

    public void regresarComplemento() {
        setComplementado(false);

        try {
            getAtrributeHelper().setPropuesta(getOrigenCentralRegionalServ().obtienePropuestaByidPropuesta(getAtrributeHelper().getIdPropuestaSeleccionada()));

        } catch (NegocioException e) {
            logger.error("Error al obtener los datos" + e);
        }
        cargaImpuestos();
    }

    public String consultaContribuyente() {
        try {

            getAtrributeHelper().setPropuesta(getOrigenCentralRegionalServ().obtienePropuestaByidPropuesta(getAtrributeHelper().getIdPropuestaSeleccionada()));
            getAtrributeHelper().getPropuesta().setEmpleadoDto(getAtrributeHelper().getEmpleadoDTO());            
            tipoPropuesta();
            getAtrributeHelper().setContribuyente(getOrigenCentralRegionalServ().getContribuyente(getAtrributeHelper().getPropuesta().getIdContribuyente()));
            getAtrributeHelper().setImpuestoVO(new FecetImpuesto());
            getAtrributeHelper().setTipoImpuestoSeleccionado(new FececTipoImpuesto());
            getListaHelper().setListaDocumento(new ArrayList<FecetDocExpediente>());
            getAtrributeHelper().setFechaActual(new Date());
            setComplementado(false);
            cargaValidacionMetodo();
            setAccion(true);
            cargaCombos();
        } catch (NegocioException e) {
            logger.error("Error al obtener los datos" + e);
        }
        return "registrarCentralRegional";
    }

    public void tipoPropuesta() {
        BigDecimal tipo = getAtrributeHelper().getPropuesta().getIdTipoPropuesta();
        logger.debug(tipo + " tipo de propuesta");
        if (tipo.intValue() == Constantes.ENTERO_UNO) {
            setTipoPropuesta(false);
        } else {
            setTipoPropuesta(true);
        }
    }

    public void eliminarImpuestos() {

        for (FecetImpuesto imp : getListaHelper().getToRemoveImpuesto()) {
            getValidarRetroalimentarPropuestaService().descartarImpuesto(imp);
        }

    }

    public void descartarImpuesto() {
        try {
            List<FecetImpuesto> toRemove = new ArrayList<FecetImpuesto>();
            for (FecetImpuesto impuestoBorrado : getListaHelper().getListaImpuestos()) {
                if (getAtrributeHelper().getImpuestoSeleccionado().equals(impuestoBorrado)) {
                    toRemove.add(impuestoBorrado);
                    getListaHelper().getListaImpuestos().remove(impuestoBorrado);
                    break;
                }
            }
            getListaHelper().getToRemoveImpuesto().addAll(toRemove);
            if (getListaHelper().getListaImpuestos().isEmpty()) {
                setActivarImpuesto(false);
            }

            addMessage(null, "Se elimin\u00f3 el impuesto correctamente.", "");
        } catch (Exception e) {
            addErrorMessage(null, getMessageResourceString("mensaje.problema"),
                    "No se pudo descartar el impuesto");
            logger.error("No se pudo cargar la información de propuestas por validar", e);
        }
    }

    public void agregarImpuesto() {

        if (validarImpuesto()) {
            getAtrributeHelper().getImpuestoVO().setFececTipoImpuesto(buscarDescripcionImpuesto(getAtrributeHelper().getImpuestoVO().getIdTipoImpuesto()));
            FececConcepto con = new FececConcepto();
            con.setDescripcion(buscarDescripcionConcepto(getAtrributeHelper().getImpuestoVO().getIdConcepto()));
            getAtrributeHelper().getImpuestoVO().setFececConcepto(con);

            if (!getAtrributeHelper().getImpuestoVO().getFececTipoImpuesto().getIdTipoImpuesto()
                    .equals(ConstantesPropuestas.TIPO_IMPUESTO_NO_APLICA)) {
                getListaHelper().getListaImpuestos().add(getAtrributeHelper().getImpuestoVO());
            } else {
                if (getListaHelper().getListaImpuestos().isEmpty()) {
                    getListaHelper().getListaImpuestos().add(getAtrributeHelper().getImpuestoVO());
                    setActivarImpuesto(true);
                } else {
                    addErrorMessage("formInsumo:btnAgregarImpuesto",
                            "Para poder declarar una propuesta sin impuestos, debe eliminar los impuestos que declaro previamente.",
                            "");
                }
            }

            getAtrributeHelper().setImpuestoVO(new FecetImpuesto());
            getAtrributeHelper().getImpuestoVO().setFececConcepto(null);
            getAtrributeHelper().getImpuestoVO().setMonto(null);
            getAtrributeHelper().getImpuestoVO().setIdTipoImpuesto(BigDecimal.ZERO);
            getAtrributeHelper().getImpuestoVO().setPeriodoFinal(null);
            setActivarMontoImpuesto(false);

        }
    }

    /**
     * Metodo que permite capturar un impuesto en cero
     *
     */
    public void agregarImpuestoCero() {
        if (getAtrributeHelper().getImpuestoVO().getIdTipoImpuesto() != null
                && getAtrributeHelper().getImpuestoVO().getIdTipoImpuesto().compareTo(BigDecimal.ZERO) > 0
                && getAtrributeHelper().getImpuestoVO().getIdTipoImpuesto().equals(ConstantesPropuestas.TIPO_IMPUESTO_NO_APLICA)) {
            getAtrributeHelper().getImpuestoVO().setMonto(new BigDecimal("0.0"));
            getAtrributeHelper().getImpuestoVO().setPeriodoFinal(getAtrributeHelper().getPropuesta().getFechaFinPeriodo());
            getAtrributeHelper().getImpuestoVO().setPeriodoInicial(getAtrributeHelper().getPropuesta().getFechaInicioPeriodo());
            cargaConceptoImpuesto(getAtrributeHelper().getImpuestoVO().getIdTipoImpuesto());
            setActivarMontoImpuesto(true);
        } else {
            cargaConceptoImpuesto(getAtrributeHelper().getImpuestoVO().getIdTipoImpuesto());
            getAtrributeHelper().getImpuestoVO().setMonto(null);
            getAtrributeHelper().getImpuestoVO().setPeriodoFinal(null);
            getAtrributeHelper().getImpuestoVO().setPeriodoInicial(null);
            setActivarMontoImpuesto(false);
        }
    }

    public BigDecimal getTotalMonto() {
        BigDecimal totalImpuesto = BigDecimal.ZERO;
        if (getListaHelper().getListaImpuestos() != null) {
            for (FecetImpuesto imp : getListaHelper().getListaImpuestos()) {
                totalImpuesto = totalImpuesto.add(imp.getMonto());
            }
        }
        return totalImpuesto;
    }

    public void cargaArchivoExpediente(final FileUploadEvent event) {
        if (validaArchivoCargaInsumoPropuesta(event.getFile())) {
            try {
                getAtrributeHelper().setArchivoCarga(event.getFile());
                FecetDocExpediente documento = new FecetDocExpediente();
                documento.setFechaCreacion(new Date());
                documento.setNombre(CargaArchivoUtilPropuestas.limpiarPathArchivo(
                        CargaArchivoUtilPropuestas.aplicarCodificacionTexto(getAtrributeHelper().getArchivoCarga().getFileName())));
                documento.setArchivo(getAtrributeHelper().getArchivoCarga().getInputstream());
                if (!validaNombreArchivoExp(getListaHelper().getListaDocumento(), documento.getNombre())) {
                    getListaHelper().getListaDocumento().add(documento);
                    addMessage(null, Constantes.ARCHIVO_CARGADO, "");
                } else {
                    addErrorMessage(null, "El archivo ya fue cargado", "");
                }

            } catch (IOException e) {
                logger.error("No se pudo adjuntar el documento ", e);
            }
        }
    }

    public String buscarDescripcionConcepto(final BigDecimal idConcepto) {
        String descripcionConcepto = "";
        for (FececConcepto impuesto : getListaHelper().getListConcepto()) {
            if (impuesto.getIdConcepto() == idConcepto.intValue()) {
                descripcionConcepto = impuesto.getDescripcion();
                break;
            }
        }
        return descripcionConcepto;
    }

    public boolean validaNombreArchivoExp(List<FecetDocExpediente> listaDocumento, String nombre) {
        for (FecetDocExpediente fecetDocExpediente : getListaHelper().getListaDocumentosTabla()) {
            if (fecetDocExpediente.getNombre().equals(nombre)) {
                return true;
            }
        }

        for (FecetDocExpediente fecetDocExpediente : listaDocumento) {
            if (fecetDocExpediente.getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    public void descartarDocumento() {
        List<FecetDocExpediente> toRemove = new ArrayList<FecetDocExpediente>();
        for (FecetDocExpediente documento : getListaHelper().getListaDocumento()) {
            if (getAtrributeHelper().getDocumentoSeleccionado().equals(documento)) {
                toRemove.add(documento);
                break;
            }
        }
        getListaHelper().getListaDocumento().removeAll(toRemove);
        addMessage(null, "Se descart\u00f3 el documento correctamente.", "");
    }

    public void cargaValidacionMetodo() {
        if (getAtrributeHelper().getPropuesta().getIdMetodo() != null) {
            if (getAtrributeHelper().getPropuesta().getIdMetodo().intValue() == Constantes.ENTERO_DOS
                    && getAtrributeHelper().getPropuesta().getPrioridadSugerida().equals(Constantes.CADENA_UNO)) {
                setPresenta(false);
                setInforme(true);
            } else if (getAtrributeHelper().getPropuesta().getIdMetodo().intValue() == Constantes.ENTERO_DOS
                    && !getAtrributeHelper().getPropuesta().getPrioridadSugerida().equals(Constantes.CADENA_UNO)) {
                setPresenta(true);
                setInforme(false);
            } else if (getAtrributeHelper().getPropuesta().getIdMetodo().intValue() == Constantes.ENTERO_UNO
                    || getAtrributeHelper().getPropuesta().getIdMetodo().intValue() == Constantes.ENTERO_TRES
                    || getAtrributeHelper().getPropuesta().getIdMetodo().intValue() == Constantes.ENTERO_CUATRO) {
                setInforme(true);
                setPresenta(false);
            } else if (getAtrributeHelper().getPropuesta().getIdMetodo().intValue() == Constantes.ENTERO_CINCO) {
                setInforme(true);
                setPresenta(false);
            }
        }

    }

    public void validaFormularioRegistrar() {
        cargaValidacionMetodo();
        comparaMetodo();
        String formularioMostrar = "PF('registroCentralProp').show();";
        validaFormularioCompleto(formularioMostrar);
    }

    public void comparaMetodo() {

        if (getAtrributeHelper().getPropuesta().getIdMetodo() != null) {
            for (FececMetodo metodoComp : getListaHelper().getListaMetodoRevision()) {
                if (getAtrributeHelper().getPropuesta().getIdMetodo().intValue() == metodoComp.getIdMetodo().intValue()) {
                    getAtrributeHelper().getPropuesta().setFeceaMetodo(metodoComp);
                    break;
                }
            }
        }

    }
    
    public StreamedContent getDescargaExpediente() {

        try {

            if (getAtrributeHelper().getDocumentoSeleccionado().getRutaArchivo() != null
                    && !getAtrributeHelper().getDocumentoSeleccionado().getRutaArchivo().isEmpty()) {
                return new DefaultStreamedContent(
                        new FileInputStream(getAtrributeHelper().getDocumentoSeleccionado().getRutaArchivo()),
                        "application/octet-stream", getAtrributeHelper().getDocumentoSeleccionado().getNombre());
            } else {
                addErrorMessage(null,"No se encontr\u00f3 el documento seleccionado",
                        getAtrributeHelper().getDocumentoSeleccionado().getNombre());
                return null;

            }

        } catch (Exception fne) {
            addErrorMessage(null,"No se encontr\u00f3 el documento seleccionado",
                    getAtrributeHelper().getDocumentoSeleccionado().getNombre());
            logger.error(fne.getMessage(), fne);
            return null;
        }
    }

}
