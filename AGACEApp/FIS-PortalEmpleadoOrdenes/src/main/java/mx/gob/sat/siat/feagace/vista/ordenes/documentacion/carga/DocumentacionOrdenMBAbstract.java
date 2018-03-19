package mx.gob.sat.siat.feagace.vista.ordenes.documentacion.carga;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.context.RequestContext;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.common.SolicitudContribuyenteAnexoVO;
import mx.gob.sat.siat.feagace.modelo.dto.common.SolicitudContribuyenteDocVO;
import mx.gob.sat.siat.feagace.modelo.dto.common.SolicitudContribuyenteVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorDocumentoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosRechazoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCambioMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetTipoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocumentoOrdenModel;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PapelesTrabajo;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorOficiosEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TiposOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.vista.ordenes.documentacion.helper.DoctosOrdenAttributeHelper;
import mx.gob.sat.siat.feagace.vista.ordenes.documentacion.helper.DoctosOrdenDTOHelper;
import mx.gob.sat.siat.feagace.vista.ordenes.documentacion.helper.DoctosOrdenLstHelper;
import mx.gob.sat.siat.feagace.vista.ordenes.documentacion.helper.DoctosOrdenLstOficioAnexoHelper;
import mx.gob.sat.siat.feagace.vista.ordenes.documentacion.helper.DoctosOrdenLstOficioHelper;
import mx.gob.sat.siat.feagace.vista.ordenes.documentacion.helper.DoctosOrdenStreamedHelper;

public abstract class DocumentacionOrdenMBAbstract extends DocumentacionOrdenAtributeMBAbstract {

    private static final long serialVersionUID = 1L;

    public DocumentacionOrdenMBAbstract() {
        setAttributeHelper(new DoctosOrdenAttributeHelper());
        setDtoHelper(new DoctosOrdenDTOHelper());
        setLstHelper(new DoctosOrdenLstHelper());
        setLstOficioHelper(new DoctosOrdenLstOficioHelper());
        setLstOficioAnexoHelper(new DoctosOrdenLstOficioAnexoHelper());
        setStreamedHelper(new DoctosOrdenStreamedHelper());
    }

    public void init() {
        getAttributeHelper().setProrrogasVisible(true);

        if (getAttributeHelper().getRecargarInformacion()) {
            getAttributeHelper().setFechaCargaPapelTrabajo(new Date());
            getAttributeHelper().setNumeroDocumentoPapelTrabajo(0);
            getAttributeHelper().setNumeroDocumentoPapelTrabajoOficio(0);

            if (getAttributeHelper().getFieldsetActivoPapelTrabajo() != null) {
                RequestContext context = RequestContext.getCurrentInstance();
                if (!getAttributeHelper().getFieldsetActivoPapelTrabajo().equals("")) {
                    StringBuilder idFielset = new StringBuilder();
                    idFielset.append("PF('").append(getAttributeHelper().getFieldsetActivoPapelTrabajo())
                            .append("').toggle();");
                    context.execute(idFielset.toString());
                }
            }
            getAttributeHelper().setAutoridadCompulsada("");
            getAttributeHelper().setFieldsetActivoPapelTrabajo("");
            getAttributeHelper().setIdTipoOficio(BigDecimal.ZERO);
            Calendar fecha = Calendar.getInstance();
            fecha.add(Calendar.DAY_OF_YEAR, 1);
            getAttributeHelper().setFechaActual(getPlazosService().primerDiaHabil(fecha.getTime()));
            getAttributeHelper().setFechaReactivarPlazo(new Date());

            if (!getDtoHelper().getOrdenSeleccionada().getFeceaMetodo().getIdMetodo().equals(Constantes.EHO)
                    && !getDtoHelper().getOrdenSeleccionada().getFeceaMetodo().getIdMetodo().equals(Constantes.REE)) {
                getAttributeHelper().setProrrogasVisible(true);
            } else {
                getAttributeHelper().setProrrogasVisible(false);
            }            
            
            getLstHelper().setListaPromociones(getSeguimientoOrdenesService()
                    .getPromocionContadorPruebasAlegatos(getDtoHelper().getOrdenSeleccionada()));
            getLstOficioHelper().setListaOficiosFirmados(getSeguimientoOrdenesService()
                    .getOficiosFirmados(getDtoHelper().getOrdenSeleccionada().getIdOrden()));
            getLstOficioHelper().setListaOficiosRechazados(getSeguimientoOrdenesService()
                    .getOficiosRechazados(getDtoHelper().getOrdenSeleccionada().getIdOrden()));
            
            limpiarOficiosCargados();
            cargaSolicitudContribuyente();
            llenarRfcANombreOficio(getLstOficioHelper().getListaOficiosFirmados());
            llenarRfcANombreOficio( getLstOficioHelper().getListaOficiosRechazados());
            cargarTipoOficioSegunTab();
            cargarPapelesTrabajoOrden();
            cargarPapelesTrabajoOficio();
            cargarTabsSeguimiento();
            configurarReactivarPlazos();            
            cargarOpcionesCambioMetodo();
            inicializaTabOficios();
            getAttributeHelper().setRecargarInformacion(false);
            obtenRepresentanteLegal();
            obtenApoderadoLegal();
            obtenApoderadoLegalRepresentanteLegal();
            obtenAgenteAduanal();
            setEmpleadoLogueado(getSeguimientoOrdenesService().obtenerDatosAuditor(getRFCSession()));
        }
    }
    
    public void cargaSolicitudContribuyente(){
        //OBTENER PRORROGAS 
        getLstHelper().setListaProrroga(getSeguimientoOrdenesService()
                .getProrrogaPorOrdenEstatusPendienteAuditor(getDtoHelper().getOrdenSeleccionada().getIdOrden()));
        getLstHelper().setListaSolicitudContribuyente(
                getDtoHelper().llenaObjetoSolicitudContProrroga(getLstHelper().getListaProrroga()));

        getLstHelper().setListaSolicitudContribuyenteConcentrado(getDtoHelper().llenaObjetoSolicitudContFull(
                getLstHelper().getListaSolicitudContribuyenteConcentrado(),
                getLstHelper().getListaSolicitudContribuyente()));

        //OBTENER PRORROGAS HISTORICO
        getLstHelper().setListaProrrogaHistorico(getSeguimientoOrdenesService()
                .getHistoricoProrrogaOrden(getDtoHelper().getOrdenSeleccionada().getIdOrden()));
        
        getLstHelper().setListaSolicitudContribuyenteHistorico(
                getDtoHelper().llenaObjetoSolicitudContProrroga(getLstHelper().getListaProrrogaHistorico()));
        
        getLstHelper().setListaSolicitudContribuyenteConcentradoHistorico(getDtoHelper().llenaObjetoSolicitudContFull(
                getLstHelper().getListaSolicitudContribuyenteConcentradoHistorico(),
                getLstHelper().getListaSolicitudContribuyenteHistorico()));
        
        //OBTENER PRUEBAS PERICIALES
        getLstHelper().setListaPruebasPericiales(
                getSeguimientoOrdenesService().getPruebaPericialPorOrdenEstatusPendienteAuditor(
                        getDtoHelper().getOrdenSeleccionada().getIdOrden()));
        
        getLstHelper().setListaSolicitudContribuyente(
                getDtoHelper().llenaObjetoSolicitudContPruebasPeri(getLstHelper().getListaPruebasPericiales()));
        
        getLstHelper().setListaSolicitudContribuyenteConcentrado(getDtoHelper().llenaObjetoSolicitudContFull(
                getLstHelper().getListaSolicitudContribuyenteConcentrado(),
                getLstHelper().getListaSolicitudContribuyente()));            
        
      //OBTENER PRUEBAS PERICIALES HISTORICO
        getLstHelper().setListaPruebasPericialesHistorico(
                getSeguimientoOrdenesService().getHistoricoPruebasPericiales(
                        getDtoHelper().getOrdenSeleccionada().getIdOrden()));
        
        getLstHelper().setListaSolicitudContribuyenteHistorico(
                getDtoHelper().llenaObjetoSolicitudContPruebasPeri(getLstHelper().getListaPruebasPericialesHistorico()));
        
        getLstHelper().setListaSolicitudContribuyenteConcentradoHistorico(getDtoHelper().llenaObjetoSolicitudContFull(
                getLstHelper().getListaSolicitudContribuyenteConcentradoHistorico(),
                getLstHelper().getListaSolicitudContribuyenteHistorico()));
        
        
    }

    public void cargarTipoOficioSegunTab() {
        Map<String, BigDecimal> oficio = new HashMap<String, BigDecimal>();
        oficio.put("fltSegundoRequerimeiento", new BigDecimal(Constantes.ENTERO_TRES));
        oficio.put("fltConclusionRevision", new BigDecimal(Constantes.ENTERO_DIEZ));
        oficio.put("fltRequerimientoReincidencia", new BigDecimal(Constantes.ENTERO_CUATRO));
        oficio.put("fltConclusionSinObservaciones", new BigDecimal(Constantes.ENTERO_CATORCE));
        oficio.put("fltAvisoCircunstancial", new BigDecimal(Constantes.ENTERO_VEINTIUNO));
        oficio.put("fltObservacionesRevisionEscritorio", new BigDecimal(Constantes.ENTERO_CINCO));
        oficio.put("fltResolucionDefinitiva", new BigDecimal(Constantes.ENTERO_QUINCE));
        oficio.put("fltPruebasDesahogo", new BigDecimal(Constantes.ENTERO_OCHO));
        oficio.put("fltPruebasPericiales", new BigDecimal(Constantes.ENTERO_DIECIOCHO));
        oficio.put("flt2daUnicaCartaInvitacion", new BigDecimal(Constantes.ENTERO_SEIS));
        oficio.put("flt2daCartaInvitacionMasiva", new BigDecimal(Constantes.ENTERO_SIETE));
        oficio.put("fltConclusionMCAUCA", new BigDecimal(Constantes.ENTERO_DIECISIETE));
        oficio.put("fltCambioMetodoUCAMCA", new BigDecimal(Constantes.ENTERO_DIECINUEVE));
        oficio.put("fltCompulsaInternacional", new BigDecimal(Constantes.ENTERO_DOS));
        oficio.put("fltMedidaApremio", new BigDecimal(-1));
        oficio.put("fltMulta", new BigDecimal(Constantes.ENTERO_NUEVE));
        oficio.put("fltAvisoContribuyente", new BigDecimal(Constantes.ENTERO_TRECE));
       
        getAttributeHelper().setTipoOficioEnum(oficio);
    }   
    
    public void obtenRepresentanteLegal() {
        getDtoHelper().setColaboradoresDTO(new ColaboradorDocumentoDTO());
        ColaboradorVO repLegal = new ColaboradorVO();
        repLegal.setTipoAsociado(Constantes.ID_REPRESENTANTE_LEGAL); 
        getDtoHelper().getColaboradoresDTO().setRepresentanteLegal(
        (getAsociadosService().cargaColaborador(repLegal,getDtoHelper().getOrdenSeleccionada())));
        cargarDocRepresentanteLegal();
    }
    
    public void obtenApoderadoLegalRepresentanteLegal() {        
        ColaboradorVO apoderadoLegalRepLegal = new ColaboradorVO();
        apoderadoLegalRepLegal.setTipoAsociado(Constantes.ID_APODERADO_LEGAL_REPRESENTANTE_LEGAL); 
        getDtoHelper().getColaboradoresDTO().setApoderadoLegalRepresentanteLegal(
        (getAsociadosService().cargaColaborador(apoderadoLegalRepLegal,getDtoHelper().getOrdenSeleccionada())));
        cargarDocApoderadoLegalRepresentanteLegal();
    }
    
    public void obtenAgenteAduanal(){
        ColaboradorVO agenteAduanal = new ColaboradorVO();
        agenteAduanal.setTipoAsociado(Constantes.ID_AGENTE_ADUANAL); 
        getDtoHelper().getColaboradoresDTO().setAgenteAduanal(
        (getAsociadosService().cargaColaborador(agenteAduanal,getDtoHelper().getOrdenSeleccionada())));
            
    }
    
    public void obtenApoderadoLegal() {        
        ColaboradorVO apoderadoLegal = new ColaboradorVO();
        apoderadoLegal.setTipoAsociado(Constantes.ID_APODERADO_LEGAL); 
        getDtoHelper().getColaboradoresDTO().setApoderadoLegal(
        (getAsociadosService().obtenerApoderadoLegalContribuyente(getDtoHelper().getOrdenSeleccionada().getFecetContribuyente().getRfc(),apoderadoLegal)));
        cargarDocApoderadoLegal();
    }
    
    private void cargarDocApoderadoLegal(){
        if(getDtoHelper().getColaboradoresDTO().getApoderadoLegal().getAsociado()!=null){
            getDtoHelper().getColaboradoresDTO().setDocumentosAL(
                    getFecetDocAsociadoService().obtenerDocumentosPorAsociado(
                            getDtoHelper().getColaboradoresDTO().getApoderadoLegal().getAsociado().getIdAsociado()));
          
        }else{
            getDtoHelper().getColaboradoresDTO().setDocumentosAL(new ArrayList<FecetDocAsociado>());
        }
    }
    
    
    
    private void cargarDocRepresentanteLegal(){
        if(getDtoHelper().getColaboradoresDTO().getRepresentanteLegal().getAsociado()!=null){
            getDtoHelper().getColaboradoresDTO().setDocumentosRepresentante(
                    getFecetDocAsociadoService().obtenerDocumentosPorAsociado(
                            getDtoHelper().getColaboradoresDTO().getRepresentanteLegal().getAsociado().getIdAsociado()));
          
        }else{
            getDtoHelper().getColaboradoresDTO().setDocumentosRepresentante(new ArrayList<FecetDocAsociado>());
        }
    }
    
    private void cargarDocApoderadoLegalRepresentanteLegal(){
        if(getDtoHelper().getColaboradoresDTO().getApoderadoLegalRepresentanteLegal().getAsociado()!=null){
            getDtoHelper().getColaboradoresDTO().setDocumentosALRL(
                    getFecetDocAsociadoService().obtenerDocumentosPorAsociado(
                            getDtoHelper().getColaboradoresDTO().getApoderadoLegalRepresentanteLegal().getAsociado().getIdAsociado()));
          
        }else{
            getDtoHelper().getColaboradoresDTO().setDocumentosALRL(new ArrayList<FecetDocAsociado>());
        }
    }
    
   
    
    
    public void cargarPapelesTrabajoOrden(){
        getLstHelper().setListaPapelesTrabajo(new ArrayList<DocumentoOrdenModel>());
        List<PapelesTrabajo> listaPapelTrabajo = getConsultarPapelesTrabajoService().getPapelesByIdPropuestaOrIdOrden(
                getDtoHelper().getOrdenSeleccionada().getIdPropuesta(),
                getDtoHelper().getOrdenSeleccionada().getIdOrden());
        if (listaPapelTrabajo != null) {
            for (PapelesTrabajo papel : listaPapelTrabajo) {
                DocumentoOrdenModel documento = new DocumentoOrdenModel();
                documento.setPapelesTrabajo(papel);
                getLstHelper().getListaPapelesTrabajo().add(documento);
            }
        }
    }

    public void cargarPapelesTrabajoOficio(BigDecimal idTipoOficio) {
        getLstHelper().setListaPapelesTrabajoOficio(new ArrayList<DocumentoOrdenModel>());
        List<PapelesTrabajo> listaPapelTrabajo = getConsultarPapelesTrabajoService()
                .getPapelesByIdOfcio(getDtoHelper().getOrdenSeleccionada().getIdOrden(), idTipoOficio);
        if (listaPapelTrabajo != null) {
            for (PapelesTrabajo papel : listaPapelTrabajo) {
                DocumentoOrdenModel documento = new DocumentoOrdenModel();
                documento.setPapelesTrabajo(papel);
                getLstHelper().getListaPapelesTrabajoOficio().add(documento);
            }
        }
    }

    public void cargarPapelesTrabajoOficio() {
        getLstHelper().setListaPapelesTrabajoOficio(new ArrayList<DocumentoOrdenModel>());
    }

    public void inicializaTabOficios() {
        if (getAttributeHelper().getTabViewOficios() != null) {
            getAttributeHelper().getTabViewOficios().setActiveIndex(0);
        }
    }

    public void configurarReactivarPlazos() {
        visualizarIntegrarExpediente();
        visualizarReactivarPlazoREE();
        visualizarReactivarPlazoAcuerdoConclusivo();
    }

    public void limpiarOficiosCargados() {
        getDtoHelper().setOfRechazadoSeleccionado(null);
        getDtoHelper().setAnexoOficioSeleccionado(null);
        getDtoHelper().setAnexoRechazoOficioSeleccionado(null);
        getDtoHelper().setFecetFlujoProrrogaOrden(new FecetFlujoProrrogaOrden());
        getDtoHelper().setFecetFlujoProrrogaOrdenRechazo(new FecetFlujoProrrogaOrden());
        getDtoHelper().setAnexoProrrogaOrdenSeleccionadoRechazo(new FecetAnexosProrrogaOrden());
        getDtoHelper().setFecetCambioMetodo(new FecetCambioMetodo());
        getDtoHelper().setSolicitudContribuyenteVOSeleccionada(new SolicitudContribuyenteVO());
        getDtoHelper().setSolicitudContribuyenteAnexoSeleccionado(new SolicitudContribuyenteAnexoVO());
        getDtoHelper().setSolicitudContribuyenteResolucionRechazoSeleccionado(new SolicitudContribuyenteAnexoVO());
        getDtoHelper().setSolicitudContribuyenteResolucionSeleccionado(new SolicitudContribuyenteAnexoVO());
        getDtoHelper().setSolicitudContribuyenteAnexoRechazoSeleccionado(new SolicitudContribuyenteAnexoVO());
        getAttributeHelper().setJustificacion(null);
        getAttributeHelper().setJustificacionRechazo(null);

        getLstHelper().setListaAnexosRechazoOficio(new ArrayList<FecetAnexosRechazoOficio>());
        getLstHelper().setListaAnexosProrrogaOrden(new ArrayList<FecetAnexosProrrogaOrden>());
        getLstHelper().setListaAnexosProrrogaOrdenRechazo(new ArrayList<FecetAnexosProrrogaOrden>());
        getLstHelper().setListaResolucionProrrogaOrden(new ArrayList<FecetAnexosProrrogaOrden>());
        getLstHelper().setListaResolucionProrrogaOrdenRechazo(new ArrayList<FecetAnexosProrrogaOrden>());
        getLstHelper().setListaMetodosCambioMetodo(new ArrayList<FececMetodo>());
        getLstHelper().setListaSolicitudContribuyente(new ArrayList<SolicitudContribuyenteVO>());
        getLstHelper().setListaSolicitudContribuyenteAnexoRechazoVO(new ArrayList<SolicitudContribuyenteAnexoVO>());
        getLstHelper().setListaSolicitudContribuyenteAnexoVO(new ArrayList<SolicitudContribuyenteAnexoVO>());
        getLstHelper().setListaSolicitudContribuyenteConcentrado(new ArrayList<SolicitudContribuyenteVO>());
        getLstHelper().setListaSolicitudContribuyenteConcentradoHistorico(new ArrayList<SolicitudContribuyenteVO>());
        getLstHelper().setListaSolicitudContribuyenteDocVO(new ArrayList<SolicitudContribuyenteDocVO>());
        getLstHelper().setListaSolicitudContribuyenteHistorico(new ArrayList<SolicitudContribuyenteVO>());
        getLstHelper().setListaSolicitudContribuyenteResolucionRechazoVO(new ArrayList<SolicitudContribuyenteAnexoVO>());
        getLstHelper().setListaSolicitudContribuyenteResolucionVO(new ArrayList<SolicitudContribuyenteAnexoVO>());

        getLstOficioHelper().setListaOf2aCartaInv(new ArrayList<FecetOficio>());
        getLstOficioHelper().setListaOf2aCartaInvitacionMasiva(new ArrayList<FecetOficio>());
        getLstOficioHelper().setListaOfConclusionUCAMCA(new ArrayList<FecetOficio>());
        getLstOficioHelper().setListaOfCambioMetodoUCAMCA(new ArrayList<FecetOficio>());
        getLstOficioHelper().setListaOfCompInternacional(new ArrayList<FecetOficio>());
        getLstOficioHelper().setListaOfSegundoRequerimiento(new ArrayList<FecetOficio>());
        getLstOficioHelper().setListaOfMulta(new ArrayList<FecetOficio>());
        getLstOficioHelper().setListaOfBajaPadron(new ArrayList<FecetOficio>());
        getLstOficioHelper().setListaOfResolucionDefinitiva(new ArrayList<FecetOficio>());
        getLstOficioHelper().setListaOfPruebasPericiales(new ArrayList<FecetOficio>());
        getLstOficioHelper().setListaOfAvisoContribuyente(new ArrayList<FecetOficio>());
        getLstOficioHelper().setListaOfPruebasDesahogo(new ArrayList<FecetOficio>());
        getLstOficioHelper().setListaOfConclusionRevision(new ArrayList<FecetOficio>());
        getLstOficioHelper().setListaOfCancelacionOrden(new ArrayList<FecetOficio>());
        getLstOficioHelper().setListaOfSinObservaciones(new ArrayList<FecetOficio>());
        getLstOficioHelper().setListaOfConObservaciones(new ArrayList<FecetOficio>());
        getLstOficioHelper().setListaOfRequerimientoReincidencia(new ArrayList<FecetOficio>());
        getLstOficioHelper().setListaOficiosDependientes(new ArrayList<FecetOficio>());
        getLstOficioHelper().setListaOfAvisoCircunstancial(new ArrayList<FecetOficio>());
        getLstOficioHelper().setListaOfMedidasApremio(new ArrayList<FecetOficio>());
        getLstOficioHelper().setListaOfMultaOrden(new ArrayList<FecetOficio>());
        getLstOficioHelper().setListaOfAvisoContribuyenteOrden(new ArrayList<FecetOficio>());
        
        getLstOficioAnexoHelper().setListaAnexos2aCartaInv(new ArrayList<FecetOficioAnexos>());
        getLstOficioAnexoHelper().setListaAnexos2aCartaInvitacionMasiva(new ArrayList<FecetOficioAnexos>());
        getLstOficioAnexoHelper().setListaAnexosConclusionUCAMCA(new ArrayList<FecetOficioAnexos>());
        getLstOficioAnexoHelper().setListaAnexosCambioMetodoUCAMCA(new ArrayList<FecetOficioAnexos>());
        getLstOficioAnexoHelper().setListaAnexosCompInternacional(new ArrayList<FecetOficioAnexos>());
        getLstOficioAnexoHelper().setListaAnexosSegundoRequerimiento(new ArrayList<FecetOficioAnexos>());
        getLstOficioAnexoHelper().setListaAnexosMulta(new ArrayList<FecetOficioAnexos>());
        getLstOficioAnexoHelper().setListaAnexosBajaPadron(new ArrayList<FecetOficioAnexos>());
        getLstOficioAnexoHelper().setListaAnexosResolucionDefinitiva(new ArrayList<FecetOficioAnexos>());
        getLstOficioAnexoHelper().setListaAnexosPruebasPericiales(new ArrayList<FecetOficioAnexos>());
        getLstOficioAnexoHelper().setListaAnexosAvisoContribuyente(new ArrayList<FecetOficioAnexos>());
        getLstOficioAnexoHelper().setListaAnexosPruebasDesahogo(new ArrayList<FecetOficioAnexos>());
        getLstOficioAnexoHelper().setListaAnexosConclusionRevision(new ArrayList<FecetOficioAnexos>());
        getLstOficioAnexoHelper().setListaAnexosCancelacionOrden(new ArrayList<FecetOficioAnexos>());
        getLstOficioAnexoHelper().setListaAnexosSinObservaciones(new ArrayList<FecetOficioAnexos>());
        getLstOficioAnexoHelper().setListaAnexosConObservaciones(new ArrayList<FecetOficioAnexos>());
        getLstOficioAnexoHelper().setListaAnexosRequerimientoReincidencia(new ArrayList<FecetOficioAnexos>());
        getLstOficioAnexoHelper().setListaAnexosOficio(new ArrayList<FecetOficioAnexos>());
        getLstOficioAnexoHelper().setListaAnexosAvisoCircunstancial(new ArrayList<FecetOficioAnexos>());
        getLstOficioAnexoHelper().setListaAnexosMedidasApremio(new ArrayList<FecetOficioAnexos>());
        getLstOficioAnexoHelper().setListaAnexosMultaOrden(new ArrayList<FecetOficioAnexos>());
        getLstOficioAnexoHelper().setListaAnexosAvisoContribuyenteOrden(new ArrayList<FecetOficioAnexos>());
        
        getStreamedHelper().setOf2aCartaInvitacion(null);
        getStreamedHelper().setOf2aCartaInvitacionMasiva(null);
        getStreamedHelper().setOfConclusionUCAMCA(null);
        getStreamedHelper().setOfCambioMetodoUCAMCA(null);
        getStreamedHelper().setOfCompInternacional(null);
        getStreamedHelper().setOfSegundoRequerimiento(null);
        getStreamedHelper().setOfMulta(null);
        getStreamedHelper().setOfBajaPadron(null);
        getStreamedHelper().setOfResolucionDefinitiva(null);
        getStreamedHelper().setOfPruebasPericiales(null);
        getStreamedHelper().setOfAvisoContribuyente(null);
        getStreamedHelper().setOfPruebasDesahogo(null);
        getStreamedHelper().setOfConclusionRevision(null);
        getStreamedHelper().setOfCancelacionOrden(null);
        getStreamedHelper().setOfSinObservaciones(null);
        getStreamedHelper().setOfConObservaciones(null);
        getStreamedHelper().setOfRequerimientoReincidencia(null);
        getStreamedHelper().setArchivoDescargaAcuse(null);
        getStreamedHelper().setArchivoDescargaAcusePromocion(null);
        getStreamedHelper().setOfAvisoCircunstancial(null);

    }

    private void visualizarIntegrarExpediente() {
        getAttributeHelper().setVisualizarIntegrarExpediente(
                getGenerarOficioService().validarVisualizarIntegraExpediente(getDtoHelper().getOrdenSeleccionada()));
    }

    public void visualizarReactivarPlazoREE() {
        getAttributeHelper().setVisualizarReactivarPlazoREE(
                getPlazosService().suspensionOrdenReactivacion(getDtoHelper().getOrdenSeleccionada()));
        if (getAttributeHelper().getVisualizarReactivarPlazoREE()) {
            getLstHelper().setListaSuspensiones(getSuspensionService()
                    .buscarSuspensionesOrdenReactivacion(getDtoHelper().getOrdenSeleccionada().getId()));
        }
    }

    private void visualizarReactivarPlazoAcuerdoConclusivo() {
        getAttributeHelper().setVisualizarReactivarPlazoAcuerdoConclusivo(getGenerarOficioService()
                .validarVisualizarReactivarPlazoAcuerdo(getDtoHelper().getOrdenSeleccionada()));
    }

    private void cargarTabsSeguimiento() {
        deshabilitaTabsOficios();
        if (getSeguimientoOrdenesService()
                .getProrrogasPendientesAprobPorOrden(getDtoHelper().getOrdenSeleccionada().getIdOrden()).size() <= 0) {
            List<TiposOficiosOrdenesEnum> tiposOficiosMostrar = getGenerarOficioService()
                    .obtenerOficiosGenerar(getDtoHelper().getOrdenSeleccionada());
            getAttributeHelper().setVisualizarTabCompulsaTercero(
                    puedeGenerarOficio(tiposOficiosMostrar, TiposOficiosOrdenesEnum.COMPULSA_TERCEROS));
            getAttributeHelper().setVisualizarTabCompulsaInternacional(
                    puedeGenerarOficio(tiposOficiosMostrar, TiposOficiosOrdenesEnum.COMPULSA_INTERNACIONAL));
            getAttributeHelper().setVisualizarTabOtrasAutoridades(
                    puedeGenerarOficio(tiposOficiosMostrar, TiposOficiosOrdenesEnum.OTRAS_AUTORIDADES));
            getAttributeHelper().setListCompulsas(new ArrayList<FecetTipoOficio>());
            FecetTipoOficio oficio;
            if (getPlazosService().validarPlazoFinCompulsa(getDtoHelper().getOrdenSeleccionada(),
                    TiposOficiosOrdenesEnum.OTRAS_AUTORIDADES)) {
                oficio = new FecetTipoOficio();
                oficio.setNombre(TiposOficiosOrdenesEnum.OTRAS_AUTORIDADES.getNombre());
                oficio.setDescripcion("1");
                getAttributeHelper().getListCompulsas().add(oficio);
            }
            if (getPlazosService().validarPlazoFinCompulsa(getDtoHelper().getOrdenSeleccionada(),
                    TiposOficiosOrdenesEnum.COMPULSA_TERCEROS)) {
                oficio = new FecetTipoOficio();
                oficio.setNombre(TiposOficiosOrdenesEnum.COMPULSA_TERCEROS.getNombre());
                oficio.setDescripcion("2");
                getAttributeHelper().getListCompulsas().add(oficio);
            }
            if (getPlazosService().validarPlazoFinCompulsa(getDtoHelper().getOrdenSeleccionada(),
                    TiposOficiosOrdenesEnum.COMPULSA_INTERNACIONAL)) {
                oficio = new FecetTipoOficio();
                oficio.setNombre(TiposOficiosOrdenesEnum.COMPULSA_INTERNACIONAL.getNombre());
                oficio.setDescripcion("3");
                getAttributeHelper().getListCompulsas().add(oficio);
            }
            if (getSeguimientoOrdenesService()
                    .getProrrogasPendientesAprobacionPorOficio(getLstOficioHelper().getListaOficiosFirmados())) {                
                getAttributeHelper().setVisualizarTabsOficios(!tiposOficiosMostrar.isEmpty());
                getAttributeHelper().setVisualizarTabSegundoRequerimiento(
                        puedeGenerarOficio(tiposOficiosMostrar, TiposOficiosOrdenesEnum.SEGUNDO_REQUERIMIENTO));
                getAttributeHelper().setVisualizarTabConclusionRevision(puedeGenerarOficio(tiposOficiosMostrar,
                        TiposOficiosOrdenesEnum.CONCLUSION_REVISION_ESCRITOS_IMP));
                getAttributeHelper().setVisualizarTabConclusionSinObservaciones(
                        puedeGenerarOficio(tiposOficiosMostrar, TiposOficiosOrdenesEnum.SIN_OBSERVACIONES));
                getAttributeHelper().setVisualizarTabObservacionesRevisionEscritorio(puedeGenerarOficio(
                        tiposOficiosMostrar, TiposOficiosOrdenesEnum.OBSERVACIONES_REVISION_ESCRITORIO));
                getAttributeHelper().setVisualizarTabRequerimientoReincidencia(
                        puedeGenerarOficio(tiposOficiosMostrar, TiposOficiosOrdenesEnum.REQUERIMIENTO_REINCIDENCIA));
                getAttributeHelper().setVisualizarTabPruebasDesahogo(
                        puedeGenerarOficio(tiposOficiosMostrar, TiposOficiosOrdenesEnum.PRUEBAS_DESAHOGO));
                getAttributeHelper().setVisualizarTabPruebasPericiales(
                        puedeGenerarOficio(tiposOficiosMostrar, TiposOficiosOrdenesEnum.PRUEBAS_PERICIALES_DESAHOGO));
                getAttributeHelper().setVisualizarTab2aUnicaCartaInvitacion(puedeGenerarOficio(tiposOficiosMostrar,
                        TiposOficiosOrdenesEnum.SEGUNDA_UNICA_CARTA_INVITACION));
                getAttributeHelper().setVisualizarTab2aCartaInvitacionMasiva(puedeGenerarOficio(tiposOficiosMostrar,
                        TiposOficiosOrdenesEnum.SEGUNDA_UNICA_CARTA_INVITACION_MASIVA));
                getAttributeHelper().setVisualizarTabConclusionUCAMCA(
                        puedeGenerarOficio(tiposOficiosMostrar, TiposOficiosOrdenesEnum.CONCLUSION));
                getAttributeHelper().setVisualizarTabCambioMetodoUCAMCA(
                        puedeGenerarOficio(tiposOficiosMostrar, TiposOficiosOrdenesEnum.CAMBIO_METODO));
                getAttributeHelper().setVisualizarTabAvisoCircunstancial(
                        puedeGenerarOficio(tiposOficiosMostrar, TiposOficiosOrdenesEnum.AVISO_CIRCUNSTANCIAL));
                getAttributeHelper().setVisualizarTabMedidasApremio(
                        puedeGenerarOficio(tiposOficiosMostrar, TiposOficiosOrdenesEnum.MEDIDAS_DE_APREMIO));
                getAttributeHelper().setVisualizarTabAvisoContribuyente(
                        puedeGenerarOficio(tiposOficiosMostrar, TiposOficiosOrdenesEnum.AVISO_AL_CONTRIBUYENTE));
                getAttributeHelper().setVisualizarTabMulta(
                        puedeGenerarOficio(tiposOficiosMostrar, TiposOficiosOrdenesEnum.MULTA));
                
                if (!getPlazosService().suspensionOrden(getDtoHelper().getOrdenSeleccionada())) {
                    getAttributeHelper().setVisualizarTabResolucionDefinitiva(
                            puedeGenerarOficio(tiposOficiosMostrar, TiposOficiosOrdenesEnum.RESOLUCION_DEFINITIVA));
                }
                if (getAttributeHelper().isVisualizarTabMedidasApremio()) {
                    getLstHelper().setListaOficiosMedidasApremio(
                            getGenerarOficioService().obtenerOficiosGenerarPorIdAgrupacionEstatus(                                    
                                    AgrupadorOficiosEnum.MEDIDAS_APREMIO.getIdAgrupadorOficios(), 
                                    getDtoHelper().getOrdenSeleccionada().getId()));
                    if (getLstHelper().getListaOficiosMedidasApremio() == null || 
                            getLstHelper().getListaOficiosMedidasApremio().isEmpty()) {
                        getAttributeHelper().setVisualizarTabMedidasApremio(false);
                    }
                }
            }
        }
    }

    public boolean puedeGenerarOficio(List<TiposOficiosOrdenesEnum> listaOficios, TiposOficiosOrdenesEnum oficio) {
        for (TiposOficiosOrdenesEnum tiposOficios : listaOficios) {
            if (tiposOficios == oficio) {
                return true;
            }
        }
        return false;
    }

    private void deshabilitaTabsOficios() {
        getAttributeHelper().setVisualizarTabsOficios(false);
        getAttributeHelper().setVisualizarTabSegundoRequerimiento(false);
        getAttributeHelper().setVisualizarTabConclusionRevision(false);
        getAttributeHelper().setVisualizarTabCompulsaTercero(false);
        getAttributeHelper().setVisualizarTabCompulsaInternacional(false);
        getAttributeHelper().setVisualizarTabConclusionSinObservaciones(false);
        getAttributeHelper().setVisualizarTabObservacionesRevisionEscritorio(false);
        getAttributeHelper().setVisualizarTabRequerimientoReincidencia(false);
        getAttributeHelper().setVisualizarTabOtrasAutoridades(false);
        getAttributeHelper().setVisualizarTabResolucionDefinitiva(false);
        getAttributeHelper().setVisualizarTabPruebasDesahogo(false);
        getAttributeHelper().setVisualizarTabPruebasPericiales(false);
        getAttributeHelper().setVisualizarTab2aUnicaCartaInvitacion(false);
        getAttributeHelper().setVisualizarTab2aCartaInvitacionMasiva(false);
        getAttributeHelper().setVisualizarTabConclusionUCAMCA(false);
        getAttributeHelper().setVisualizarTabCambioMetodoUCAMCA(false);
        getAttributeHelper().setVisualizarTabAvisoContribuyente(false);
        getAttributeHelper().setVisualizarTabMulta(false);
    }

    private void cargarOpcionesCambioMetodo() {
        if ((this.getDtoHelper().getOrdenSeleccionada().getIdMetodo().equals(Constantes.UCA)
                || this.getDtoHelper().getOrdenSeleccionada().getIdMetodo().equals(Constantes.MCA))
                && getAttributeHelper().getVisualizarTabCambioMetodoUCAMCA()) {
            getLstHelper().setListaMetodosCambioMetodo(this.getSeguimientoOrdenesService().getOpcionesCambioMetodo());
        }
    }

    public String navegaMenu() {
        return "/faces/firmante/validarFirmarDocSeguimiento/indexValidarFirmarDocumentos.jsf?faces-redirect=true";
    }

    
    private void llenarRfcANombreOficio(List<FecetOficio> listaOficio){ 
        if(listaOficio!=null){
            for(FecetOficio oficio : listaOficio){                                        
                TiposOficiosOrdenesEnum oficioEnum = TiposOficiosOrdenesEnum.parse(oficio.getFecetTipoOficio().getIdTipoOficio().intValue());
                
                if (oficioEnum!=null) {    
                    StringBuilder nombre = new StringBuilder();
                    
                    if(oficio.getIdOficioPrincipal()!=null){
                        FecetOficio oficioPrincipal = getSeguimientoOrdenesService().obtenerOficioById(oficio);
                        if(oficioPrincipal!=null){
                            nombre.append(oficio.getFecetTipoOficio().getNombre());
                            nombre.append(llenarNombreOficio(oficioPrincipal));
                        }
                    }
                    else{                        
                        nombre.append(llenarNombreOficioCompulsa(oficio));
                    }
                    oficio.getFecetTipoOficio().setNombre(nombre.toString());        
                }
            }
        }
    }
        
    private String llenarNombreOficio(FecetOficio oficio){
        StringBuilder nombre = new StringBuilder();
        
        if(oficio.getRfcCompulsado()!=null && !oficio.getRfcCompulsado().equals("")){
            nombre.append(" (").append(oficio.getFecetTipoOficio().getNombre()).append(" ").append(oficio.getRfcCompulsado()).append(" )");      
        }
        if(oficio.getNombreCompulsado()!=null && !oficio.getNombreCompulsado().equals("")){
            nombre.append(" (").append(oficio.getFecetTipoOficio().getNombre()).append(" ").append(oficio.getNombreCompulsado()).append(" )");      
        }
        
        return nombre.toString();
    }
    
    private String llenarNombreOficioCompulsa(FecetOficio oficio){
        StringBuilder nombre = new StringBuilder(oficio.getFecetTipoOficio().getNombre());
        
        if(oficio.getRfcCompulsado()!=null && !oficio.getRfcCompulsado().equals("")){
            nombre.append(" (").append(oficio.getRfcCompulsado()).append(" )");      
        }
        if(oficio.getNombreCompulsado()!=null && !oficio.getNombreCompulsado().equals("")){
            nombre.append(" (").append(oficio.getNombreCompulsado()).append(" )");      
        }
        
        return nombre.toString();
    }       
}
