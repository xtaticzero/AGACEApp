package mx.gob.sat.siat.feagace.vista.insumos.consulta.ug.ordenes.helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PapelesTrabajo;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorEstatusOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.CifrasOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;
import mx.gob.sat.siat.feagace.negocio.bo.consulta.ConsultaEjecutivaOrdenesBO;
import mx.gob.sat.siat.feagace.negocio.consulta.ordenes.filtro.FiltroConsultaOrdenes;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilOrdenes;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtilOrdenes;
import mx.gob.sat.siat.feagace.negocio.util.constantes.SemaforoEnum;
import mx.gob.sat.siat.feagace.vista.model.ordenes.OrdenConsultaDTO;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

public class ConsultaGeneralOrdenesHelper extends ConsultaGeneralOrdenesBooleanHelper {

    private static final long serialVersionUID = 2196785919740403683L;

    private transient ConsultaEjecutivaOrdenesBO consultaOrdenesBO;
    private FiltroConsultaOrdenes filtro;

    private AgaceOrden ordenSeleccionada;
    private FecetPropuesta propuestaSeleccionada;
    private String idRegistroPropuestaSeleccionada;

    private Integer idUnidadAdminSeleccionada;
    private AraceDTO unidadAdminSeleccionada;
    private List<AgaceOrden> lstOrdenesResult;
    private transient Map.Entry<AgrupadorEstatusOrdenesEnum, Integer> grupoEstatusSeleccionado;
    private transient Map.Entry<EmpleadoDTO, Integer> empleadoSeleccionado;
    private transient Map.Entry<EmpleadoDTO, Integer> empleadoFiltradoSeleccionado;
    private transient Map.Entry<TipoMetodoEnum, Integer> metodoSeleccionado;
    private transient Map.Entry<SemaforoEnum, Integer> semaforoSeleccionado;
    private transient Map.Entry<SemaforoEnum, Integer> semaforoFiltradoSeleccionado;
    private transient CifrasOrdenesEnum cifraSeleccionada;
    private transient BigDecimal cifraDesde;
    private transient BigDecimal cifraHasta;
    
    private FecetDocOrden docSeleccionadoPdf;
    private FecetOficio oficioSeleccionadoPdf;
    private FecetDocExpediente docExpedienteSeleccionado;
    private PapelesTrabajo papelTrabajoSeleccionado;
    private transient StreamedContent archivoDescargaPdfOrden;
    private transient StreamedContent archivoDescargaPdfOficio;
    private transient StreamedContent archivoDescargaDocExp;
    private transient StreamedContent archivoDescargaPapelTrabajo;

    public Integer getIdUnidadAdminSeleccionada() {
        return idUnidadAdminSeleccionada;
    }

    public void setIdUnidadAdminSeleccionada(Integer idUnidadAdminSeleccionada) {
        this.idUnidadAdminSeleccionada = idUnidadAdminSeleccionada;
    }

    public ConsultaEjecutivaOrdenesBO getConsultaOrdenesBO() {
        return consultaOrdenesBO;
    }

    public void setConsultaOrdenesBO(ConsultaEjecutivaOrdenesBO consultaOrdenesBO) {
        this.consultaOrdenesBO = consultaOrdenesBO;
    }

    public AraceDTO getUnidadAdminSeleccionada() {
        return unidadAdminSeleccionada;
    }

    public void setUnidadAdminSeleccionada(AraceDTO unidadAdminSeleccionada) {
        this.unidadAdminSeleccionada = unidadAdminSeleccionada;
    }

    public FiltroConsultaOrdenes getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroConsultaOrdenes filtro) {
        this.filtro = filtro;
    }

    public List<AgaceOrden> getLstOrdenesResult() {
        return lstOrdenesResult;
    }

    public void setLstOrdenesResult(List<AgaceOrden> lstOrdenesResult) {
        this.lstOrdenesResult = lstOrdenesResult;
    }

    public Map.Entry<AgrupadorEstatusOrdenesEnum, Integer> getGrupoEstatusSeleccionado() {
        return grupoEstatusSeleccionado;
    }

    public void setGrupoEstatusSeleccionado(Map.Entry<AgrupadorEstatusOrdenesEnum, Integer> grupoEstatusSeleccionado) {
        this.grupoEstatusSeleccionado = grupoEstatusSeleccionado;
    }

    public Map.Entry<EmpleadoDTO, Integer> getEmpleadoSeleccionado() {
        return empleadoSeleccionado;
    }

    public void setEmpleadoSeleccionado(Map.Entry<EmpleadoDTO, Integer> empleadoSeleccionado) {
        this.empleadoSeleccionado = empleadoSeleccionado;
    }

    public Map.Entry<EmpleadoDTO, Integer> getEmpleadoFiltradoSeleccionado() {
        return empleadoFiltradoSeleccionado;
    }

    public void setEmpleadoFiltradoSeleccionado(Map.Entry<EmpleadoDTO, Integer> empleadoFiltradoSeleccionado) {
        this.empleadoFiltradoSeleccionado = empleadoFiltradoSeleccionado;
    }

    public Map.Entry<TipoMetodoEnum, Integer> getMetodoSeleccionado() {
        return metodoSeleccionado;
    }

    public void setMetodoSeleccionado(Map.Entry<TipoMetodoEnum, Integer> metodoSeleccionado) {
        this.metodoSeleccionado = metodoSeleccionado;
    }

    public Map.Entry<SemaforoEnum, Integer> getSemaforoSeleccionado() {
        return semaforoSeleccionado;
    }

    public void setSemaforoSeleccionado(Map.Entry<SemaforoEnum, Integer> semaforoSeleccionado) {
        this.semaforoSeleccionado = semaforoSeleccionado;
    }

    public Map.Entry<SemaforoEnum, Integer> getSemaforoFiltradoSeleccionado() {
        return semaforoFiltradoSeleccionado;
    }

    public void setSemaforoFiltradoSeleccionado(Map.Entry<SemaforoEnum, Integer> semaforoFiltradoSeleccionado) {
        this.semaforoFiltradoSeleccionado = semaforoFiltradoSeleccionado;
    }

    public CifrasOrdenesEnum getCifraSeleccionada() {
        return cifraSeleccionada;
    }

    public void setCifraSeleccionada(CifrasOrdenesEnum cifraSeleccionada) {
        this.cifraSeleccionada = cifraSeleccionada;
    }

    public BigDecimal getCifraDesde() {
        return cifraDesde;
    }

    public void setCifraDesde(BigDecimal cifraDesde) {
        this.cifraDesde = cifraDesde;
    }

    public BigDecimal getCifraHasta() {
        return cifraHasta;
    }

    public void setCifraHasta(BigDecimal cifraHasta) {
        this.cifraHasta = cifraHasta;
    }
    
    public AgaceOrden getOrdenSeleccionada() {
        return ordenSeleccionada;
    }

    public void setOrdenSeleccionada(AgaceOrden ordenSeleccionada) {
        this.ordenSeleccionada = ordenSeleccionada;
    }

    public FecetPropuesta getPropuestaSeleccionada() {
        return propuestaSeleccionada;
    }

    public void setPropuestaSeleccionada(FecetPropuesta propuestaSeleccionada) {
        this.propuestaSeleccionada = propuestaSeleccionada;
    }

    public String getIdRegistroPropuestaSeleccionada() {
        return idRegistroPropuestaSeleccionada;
    }

    public void setIdRegistroPropuestaSeleccionada(String idRegistroPropuestaSeleccionada) {
        this.idRegistroPropuestaSeleccionada = idRegistroPropuestaSeleccionada;
    }

    public FecetDocOrden getDocSeleccionadoPdf() {
        return docSeleccionadoPdf;
    }

    public void setDocSeleccionadoPdf(FecetDocOrden docSeleccionadoPdf) {
        this.docSeleccionadoPdf = docSeleccionadoPdf;
    }

    public FecetOficio getOficioSeleccionadoPdf() {
        return oficioSeleccionadoPdf;
    }

    public void setOficioSeleccionadoPdf(FecetOficio oficioSeleccionadoPdf) {
        this.oficioSeleccionadoPdf = oficioSeleccionadoPdf;
    }

    public FecetDocExpediente getDocExpedienteSeleccionado() {
        return docExpedienteSeleccionado;
    }

    public void setDocExpedienteSeleccionado(FecetDocExpediente docExpedienteSeleccionado) {
        this.docExpedienteSeleccionado = docExpedienteSeleccionado;
    }

    public PapelesTrabajo getPapelTrabajoSeleccionado() {
        return papelTrabajoSeleccionado;
    }

    public void setPapelTrabajoSeleccionado(PapelesTrabajo papelTrabajoSeleccionado) {
        this.papelTrabajoSeleccionado = papelTrabajoSeleccionado;
    }

    public StreamedContent getArchivoDescargaPdfOrden() {
        archivoDescargaPdfOrden = getArchivoDescarga(docSeleccionadoPdf.getRutaArchivo(), docSeleccionadoPdf.getNombreArchivo());
        return archivoDescargaPdfOrden;
    }

    public void setArchivoDescargaPdfOrden(StreamedContent archivoDescargaPdfOrden) {
        this.archivoDescargaPdfOrden = archivoDescargaPdfOrden;
    }

    public StreamedContent getArchivoDescargaPdfOficio() {
        archivoDescargaPdfOficio = getArchivoDescarga(oficioSeleccionadoPdf.getRutaArchivo(), oficioSeleccionadoPdf.getNombreArchivo());
        return archivoDescargaPdfOficio;
    }

    public void setArchivoDescargaPdfOficio(StreamedContent archivoDescargaPdfOficio) {
        this.archivoDescargaPdfOficio = archivoDescargaPdfOficio;
    }

    public StreamedContent getArchivoDescargaDocExp() {
        archivoDescargaDocExp = getArchivoDescarga(docExpedienteSeleccionado.getRutaArchivo(), docExpedienteSeleccionado.getNombre());
        return archivoDescargaDocExp;
    }

    public void setArchivoDescargaDocExp(StreamedContent archivoDescargaDocExp) {
        this.archivoDescargaDocExp = archivoDescargaDocExp;
    }

    public StreamedContent getArchivoDescargaPapelTrabajo() {
        archivoDescargaPapelTrabajo = getArchivoDescarga(papelTrabajoSeleccionado.getRutaArchivo(), papelTrabajoSeleccionado.getNombreArchivo());
        return archivoDescargaPapelTrabajo;
    }

    public void setArchivoDescargaPapelTrabajo(StreamedContent archivoDescargaPapelTrabajo) {
        this.archivoDescargaPapelTrabajo = archivoDescargaPapelTrabajo;
    }

    public void ordenarListaResult(List<AgaceOrden> listaOrdenes, Logger logger) {
        if (listaOrdenes != null) {
            try {
                Collections.sort(listaOrdenes, new Comparator<AgaceOrden>() {
                    @Override
                    public int compare(AgaceOrden propOrg, AgaceOrden propCom) {
                        return propOrg.getPrioridadSugerida().compareTo(propCom.getPrioridadSugerida());
                    }
                });
            } catch (Exception e) {
                logger.error("existe error al ordenar la lista de ordenes");
            }
        }
    }
    
    public void setPropiedadesHelper(ConsultaGeneralOrdenesHelper helperTmp) {
        if (helperTmp != null) {
            this.cifraDesde = helperTmp.getCifraDesde();
            this.cifraHasta = helperTmp.getCifraHasta();
            this.cifraSeleccionada = helperTmp.getCifraSeleccionada();
            this.consultaOrdenesBO = helperTmp.getConsultaOrdenesBO();
            this.empleadoFiltradoSeleccionado = helperTmp.getEmpleadoFiltradoSeleccionado();
            this.empleadoSeleccionado = helperTmp.getEmpleadoSeleccionado();
            this.filtro = helperTmp.getFiltro();
            this.grupoEstatusSeleccionado = helperTmp.getGrupoEstatusSeleccionado();
            this.lstOrdenesResult = helperTmp.getLstOrdenesResult();
            this.metodoSeleccionado = helperTmp.getMetodoSeleccionado();
            this.ordenSeleccionada = helperTmp.getOrdenSeleccionada();
            this.semaforoFiltradoSeleccionado = helperTmp.getSemaforoFiltradoSeleccionado();
            this.semaforoSeleccionado = helperTmp.getSemaforoSeleccionado();
            this.unidadAdminSeleccionada = helperTmp.getUnidadAdminSeleccionada();
        }
    }
    
    public OrdenConsultaDTO convertToOrdenConsulta(AgaceOrden agaceOrden) {
        OrdenConsultaDTO ordenConsultaDTO = new OrdenConsultaDTO();
        ordenConsultaDTO.setIdOrden(agaceOrden.getIdOrden());
        ordenConsultaDTO.setIdPropuesta(agaceOrden.getIdPropuesta());
        ordenConsultaDTO.setIdRegistro(agaceOrden.getIdRegistroPropuesta());
        ordenConsultaDTO.setNumeroOrden(agaceOrden.getNumeroOrden());
        ordenConsultaDTO.setRutaArchivo(RutaArchivosUtilOrdenes.armarRutaDocOrden(agaceOrden));
        ordenConsultaDTO.setNombreArchivo(CargaArchivoUtilOrdenes.getNombreArchivoOrdenPdf(agaceOrden));
        ordenConsultaDTO.setIdMetodo(agaceOrden.getIdMetodo());
        ordenConsultaDTO.setMetodo(TipoMetodoEnum.getById(agaceOrden.getIdMetodo().longValue()).getDescripcion());
        ordenConsultaDTO.setMetodoSiglas(TipoMetodoEnum.getById(agaceOrden.getIdMetodo().longValue()).getSiglas());
        ordenConsultaDTO.setRfc(agaceOrden.getFecetContribuyente().getRfc());
        ordenConsultaDTO.setNombreContribuyente(agaceOrden.getFecetContribuyente().getNombre());
        ordenConsultaDTO.setEstatus(agaceOrden.getFececEstatus().getDescripcion());
        ordenConsultaDTO.setPlazoRestante(agaceOrden.getDiasRestantesPlazo());
        ordenConsultaDTO.setDescripcionPlazoRestante(agaceOrden.getDescripcionPlazoRestante());
        ordenConsultaDTO.setIdFirmante(agaceOrden.getIdFirmante());
        ordenConsultaDTO.setIdAuditor(agaceOrden.getIdAuditor());
        ordenConsultaDTO.setIdContribuyente(agaceOrden.getIdContribuyente());
        return ordenConsultaDTO;
    }
    
    public StreamedContent getArchivoDescarga(final String url, final String nombreDescarga) {
        StreamedContent archivo = null;
        if (url != null && nombreDescarga != null) {
            try {
                archivo = new DefaultStreamedContent(new FileInputStream(CargaArchivoUtilOrdenes.limpiarPathArchivo(url)),
                        CargaArchivoUtilOrdenes.obtenContentTypeArchivo(nombreDescarga), CargaArchivoUtilOrdenes.aplicarCodificacionTexto(nombreDescarga));
            } catch (FileNotFoundException e) {
                FacesUtil.addErrorMessage(null, "No se encontro el documento seleccionado", "");
            }
        } else {
            FacesUtil.addErrorMessage(null, "No se encontro el documento seleccionado", "");
        }
        return archivo;
    }

}
