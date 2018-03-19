package mx.gob.sat.siat.feagace.vista.propuestas.consulta.helper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;
import mx.gob.sat.siat.feagace.negocio.util.constantes.BusinessUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilPropuestas;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtilPropuestas;
import mx.gob.sat.siat.feagace.vista.model.AnexoProrrogaOficioConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.AnexoProrrogaOrdenConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.FlujoProrrogaOficioConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.FlujoProrrogaOrdenConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.OficioConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.OrdenConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.PromocionConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.PromocionOficioConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.ProrrogaOficioConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.ProrrogaOrdenConsultaDTO;

import org.springframework.stereotype.Component;

@Component
public class ConsultarReimprimirDocumentosHelper {

    /**
     * Convierte el objeto de dominio AgaceOrden a OrdenConsulta para presentar
     * en la vista.
     * 
     * @param listAgaceOrden
     * @return
     */
    public List<OrdenConsultaDTO> convertToOrdenConsulta(List<AgaceOrden> listAgaceOrden, String rfc) {
        List<OrdenConsultaDTO> listOrdenConsulta = new ArrayList<OrdenConsultaDTO>();
        for (AgaceOrden agaceOrden : listAgaceOrden) {
            OrdenConsultaDTO ordenConsultaDTO = new OrdenConsultaDTO();
            ordenConsultaDTO.setIdOrden(agaceOrden.getIdOrden());
            ordenConsultaDTO.setIdPropuesta(agaceOrden.getIdPropuesta());
            ordenConsultaDTO.setIdRegistro(agaceOrden.getIdRegistroPropuesta());
            ordenConsultaDTO.setNumeroOrden(agaceOrden.getNumeroOrden());
            ordenConsultaDTO.setRutaArchivo(RutaArchivosUtilPropuestas.armarRutaDocOrden(agaceOrden));
            ordenConsultaDTO.setNombreArchivo(CargaArchivoUtilPropuestas.getNombreArchivoOrdenPdf(agaceOrden));
            ordenConsultaDTO.setIdMetodo(agaceOrden.getIdMetodo());
            ordenConsultaDTO.setMetodo(TipoMetodoEnum.getById(agaceOrden.getIdMetodo().longValue()).getDescripcion());
            ordenConsultaDTO.setMetodoSiglas(TipoMetodoEnum.getById(agaceOrden.getIdMetodo().longValue()).getSiglas());
            ordenConsultaDTO.setRfc(agaceOrden.getFecetContribuyente().getRfc());
            ordenConsultaDTO.setNombreContribuyente(agaceOrden.getFecetContribuyente().getNombre());
            ordenConsultaDTO.setEstatus(agaceOrden.getFececEstatus().getDescripcion());
            ordenConsultaDTO.setPlazoRestante(agaceOrden.getDiasRestantesPlazo());
            ordenConsultaDTO.setIdFirmante(agaceOrden.getIdFirmante());
            ordenConsultaDTO.setIdAuditor(agaceOrden.getIdAuditor());
            ordenConsultaDTO.setIdContribuyente(agaceOrden.getIdContribuyente());
            listOrdenConsulta.add(ordenConsultaDTO);
        }
        return listOrdenConsulta;
    }

    /**
     * Convierte el objeto de dominio FecetPromocion a PromocionConsultaDTO para
     * presentar en la vista.
     * 
     * @param listFecetPromocion
     * @param
     * @return
     */
    public List<PromocionConsultaDTO> convertToPromocionConsulta(List<FecetPromocion> listFecetPromocion, OrdenConsultaDTO ordenConsulta) {
        List<PromocionConsultaDTO> listPromocionConsulta = new ArrayList<PromocionConsultaDTO>();
        String metodoOrdenStr = BusinessUtil.getTipoPromocionOrdenPorMetodo(ordenConsulta.getIdMetodo());
        for (FecetPromocion fecetPromocion : listFecetPromocion) {
            PromocionConsultaDTO promocionConsultaDTO = new PromocionConsultaDTO();
            promocionConsultaDTO.setIdPromocion(fecetPromocion.getIdPromocion());
            promocionConsultaDTO.setTipoPromocionStr(metodoOrdenStr);
            promocionConsultaDTO.setFechaEnvio(fecetPromocion.getFechaCarga());
            promocionConsultaDTO.setNombreArchivo(fecetPromocion.getNombreArchivo());
            promocionConsultaDTO.setRutaArchivo(fecetPromocion.getRutaArchivo());
            promocionConsultaDTO.setDocumentosContribuyente(fecetPromocion.getContadorPruebasAlegatos());
            promocionConsultaDTO.setNombreAcuse(fecetPromocion.getNombreAcuse());
            promocionConsultaDTO.setRutaAcuse(fecetPromocion.getRutaAcuse());
            promocionConsultaDTO.setExtemporaneo(fecetPromocion.getExtemporanea() != null && fecetPromocion.getExtemporanea().equals("1") ? true : false);
            // Regla no definida en C.U.
            // Cuando el campo idAsociado es null la presentacion fue realizada
            // por el contribuyente.
            if (fecetPromocion.getAsociado() != null && fecetPromocion.getAsociado().getNombre() != null) {
                promocionConsultaDTO.setPresentadoPor(fecetPromocion.getAsociado().getNombre());
            } else {
                promocionConsultaDTO.setPresentadoPor(ordenConsulta.getNombreContribuyente());
            }
            listPromocionConsulta.add(promocionConsultaDTO);
        }
        return listPromocionConsulta;

    }

    /**
     * Convierte el objeto de dominio FecetProrrogaOrden a
     * ProrrogaOrdenConsultaDTO para presentar en la vista.
     * 
     * @param listFecetProrrogaOrden
     * @return
     */
    public List<ProrrogaOrdenConsultaDTO> convertoToProrrogaOrdenConsulta(List<FecetProrrogaOrden> listFecetProrrogaOrden, OrdenConsultaDTO ordenConsulta) {
        List<ProrrogaOrdenConsultaDTO> listProrrogaOrdenConsulta = new ArrayList<ProrrogaOrdenConsultaDTO>();
        for (FecetProrrogaOrden fecetProrrogaOrden : listFecetProrrogaOrden) {
            ProrrogaOrdenConsultaDTO prorrogaOrdenConsultaDTO = new ProrrogaOrdenConsultaDTO();
            prorrogaOrdenConsultaDTO.setIdProrroga(fecetProrrogaOrden.getIdProrrogaOrden());
            prorrogaOrdenConsultaDTO.setFechaSolicitud(fecetProrrogaOrden.getFechaCarga());
            prorrogaOrdenConsultaDTO.setNombreAcuse(fecetProrrogaOrden.getNombreAcuse());
            prorrogaOrdenConsultaDTO.setRutaAcuse(fecetProrrogaOrden.getRutaAcuse());
            prorrogaOrdenConsultaDTO.setTotalDocumentosContribuyente(fecetProrrogaOrden.getTotalDocumentosContribuyente());
            prorrogaOrdenConsultaDTO.setNombreResolucion(fecetProrrogaOrden.getNombreResolucion());
            prorrogaOrdenConsultaDTO.setRutaResolucion(fecetProrrogaOrden.getRutaResolucion());
            prorrogaOrdenConsultaDTO.setIdAuditor(fecetProrrogaOrden.getIdAuditor());
            prorrogaOrdenConsultaDTO.setIdFirmante(fecetProrrogaOrden.getIdFirmante());
            // Regla no definida en C.U.
            // Cuando el campo idAsociado es null la presentacion fue realizada
            // por el contribuyente.
            if (fecetProrrogaOrden.getFecetAsociado() != null && fecetProrrogaOrden.getFecetAsociado().getNombre() != null) {
                prorrogaOrdenConsultaDTO.setPresentadoPor(fecetProrrogaOrden.getFecetAsociado().getNombre());
            } else {
                prorrogaOrdenConsultaDTO.setPresentadoPor(ordenConsulta.getNombreContribuyente());
            }
            prorrogaOrdenConsultaDTO.setEstatus(fecetProrrogaOrden.getFececEstatus() != null ? fecetProrrogaOrden.getFececEstatus().getDescripcion() : null);
            listProrrogaOrdenConsulta.add(prorrogaOrdenConsultaDTO);
        }
        return listProrrogaOrdenConsulta;
    }

    /**
     * Convierte el objeto de dominio FecetOficio a OficioConsultaDTO para
     * presentar en la vista.
     * 
     * @param listFecetOficio
     * @return
     */
    public List<OficioConsultaDTO> convertToOficioConsultaDTO(List<FecetOficio> listFecetOficio, OrdenConsultaDTO ordenConsultaDTO) {
        List<OficioConsultaDTO> listOficioConsulta = new ArrayList<OficioConsultaDTO>();
        for (FecetOficio fecetOficio : listFecetOficio) {
            OficioConsultaDTO oficioConsultaDTO = new OficioConsultaDTO();
            oficioConsultaDTO.setIdOficio(fecetOficio.getIdOficio());
            oficioConsultaDTO.setIdTipoOficio(fecetOficio.getFecetTipoOficio().getIdTipoOficio());
            oficioConsultaDTO.setTipoOficio(fecetOficio.getFecetTipoOficio().getNombre());
            oficioConsultaDTO.setNombreArchivo(fecetOficio.getNombreArchivo());
            oficioConsultaDTO.setRutaArchivo(fecetOficio.getRutaArchivo());
            oficioConsultaDTO.setNombreAcuseNyv(fecetOficio.getNombreAcuseNyv());
            oficioConsultaDTO.setRutaAcuseNyv(fecetOficio.getRutaAcuseNyv());
            oficioConsultaDTO.setFechaNotificacion(fecetOficio.getFecetDetalleNyV() != null ? fecetOficio.getFecetDetalleNyV().getFecNotificacionNyV() : null);
            oficioConsultaDTO.setAnexos(fecetOficio.getTotalAnexosOficio());
            oficioConsultaDTO.setFechaCarga(fecetOficio.getFechaCreacion());
            oficioConsultaDTO.setFechaFirma(fecetOficio.getFechaFirma());
            oficioConsultaDTO.setOficiosDependientes(fecetOficio.getTotalOficiosDependientes());
            oficioConsultaDTO.setPresentadoPor(ordenConsultaDTO.getFirmante() != null ? ordenConsultaDTO.getFirmante().getNombre() : null);
            listOficioConsulta.add(oficioConsultaDTO);
        }
        return listOficioConsulta;
    }

    /**
     * Convierte el objeto de dominio FecetPromocionOficio a
     * PromocionOficioConsultaDTO para presentar en la vista.
     * 
     * @param listFecetPromocionOficio
     * @return
     */
    public List<PromocionOficioConsultaDTO> convertToPromocionOficioConsultaDTO(List<FecetPromocionOficio> listFecetPromocionOficio, BigDecimal idTipo,
            OrdenConsultaDTO ordenConsulta) {
        List<PromocionOficioConsultaDTO> listPromocionOficioConsulta = new ArrayList<PromocionOficioConsultaDTO>();
        String tipoPromocionStr = BusinessUtil.getTipoPromocionPorTipoOficioMetodo(idTipo);
        for (FecetPromocionOficio fecetPromocionOficio : listFecetPromocionOficio) {
            PromocionOficioConsultaDTO promocionOficioConsultaDTO = new PromocionOficioConsultaDTO();
            promocionOficioConsultaDTO.setIdPromocionOficio(fecetPromocionOficio.getIdPromocionOficio());
            promocionOficioConsultaDTO.setTipoPromocionStr(tipoPromocionStr);
            promocionOficioConsultaDTO.setFechaEnvio(fecetPromocionOficio.getFechaCarga());
            promocionOficioConsultaDTO.setNombreArchivo(fecetPromocionOficio.getNombreArchivo());
            promocionOficioConsultaDTO.setRutaArchivo(fecetPromocionOficio.getRutaArchivo());
            promocionOficioConsultaDTO.setRutaAcuse(fecetPromocionOficio.getRutaAcuse());
            promocionOficioConsultaDTO.setNombreAcuse(fecetPromocionOficio.getNombreAcuse());
            promocionOficioConsultaDTO.setExtemporaneo(fecetPromocionOficio.getExtemporanea() != null && fecetPromocionOficio.getExtemporanea().equals("1") ? true : false);
            // Regla no definida en C.U.
            // Cuando el campo idAsociado es null la presentacion fue realizada
            // por el contribuyente.
            if (fecetPromocionOficio.getAsociado() != null && fecetPromocionOficio.getAsociado().getNombre() != null) {
                promocionOficioConsultaDTO.setPresentadoPor(fecetPromocionOficio.getAsociado().getNombre());
            } else {
                promocionOficioConsultaDTO.setPresentadoPor(ordenConsulta.getNombreContribuyente());
            }
            promocionOficioConsultaDTO.setDocumentosContribuyente(fecetPromocionOficio.getContadorPruebasAlegatos());
            listPromocionOficioConsulta.add(promocionOficioConsultaDTO);
        }
        return listPromocionOficioConsulta;
    }

    /**
     * Convierte el objeto de domicio FecetProrrogaOficio a
     * ProrrogaOficioConsultaDTO para presentar en vista.
     * 
     * @param listFecetProrrogaOficio
     * @return
     */
    public List<ProrrogaOficioConsultaDTO> convertToProrrogaOficioConsultaDTO(List<FecetProrrogaOficio> listFecetProrrogaOficio, OficioConsultaDTO oficio,OrdenConsultaDTO ordenConsulta) {
        List<ProrrogaOficioConsultaDTO> listProrrogaOficioConsulta = new ArrayList<ProrrogaOficioConsultaDTO>();
        for (FecetProrrogaOficio fecetProrrogaOficio : listFecetProrrogaOficio) {
            ProrrogaOficioConsultaDTO prorrogaOficioConsultaDTO = new ProrrogaOficioConsultaDTO();
            prorrogaOficioConsultaDTO.setIdProrroga(fecetProrrogaOficio.getIdProrrogaOficio());
            prorrogaOficioConsultaDTO.setFechaSolicitud(fecetProrrogaOficio.getFechaCarga());
            prorrogaOficioConsultaDTO.setNombreAcuse(fecetProrrogaOficio.getNombreAcuse());
            prorrogaOficioConsultaDTO.setRutaAcuse(fecetProrrogaOficio.getRutaAcuse());
            prorrogaOficioConsultaDTO.setTotalDocumentosContribuyente(fecetProrrogaOficio.getTotalDocumentosContribuyente());
            prorrogaOficioConsultaDTO.setNombreResolucion(fecetProrrogaOficio.getNombreResolucion());
            prorrogaOficioConsultaDTO.setRutaResolucion(fecetProrrogaOficio.getRutaResolucion());
            prorrogaOficioConsultaDTO.setEstatus(fecetProrrogaOficio.getFececEstatus() != null ? fecetProrrogaOficio.getFececEstatus().getDescripcion() : null);
            if (fecetProrrogaOficio.getFecetAsociado() != null && fecetProrrogaOficio.getFecetAsociado().getNombre() != null) {
                prorrogaOficioConsultaDTO.setPresentadoPor(fecetProrrogaOficio.getFecetAsociado().getNombre());
            } else {
                prorrogaOficioConsultaDTO.setPresentadoPor(ordenConsulta.getNombreContribuyente());
            }
            prorrogaOficioConsultaDTO.setIdAuditor(fecetProrrogaOficio.getIdAuditor());
            prorrogaOficioConsultaDTO.setIdFirmante(fecetProrrogaOficio.getIdFirmante());
            listProrrogaOficioConsulta.add(prorrogaOficioConsultaDTO);
        }

        return listProrrogaOficioConsulta;

    }

    /**
     * Convierte el objeto de domicio FecetFlujoProrrogaOficio a
     * FlujoProrrogaOficioConsultaDTO para presentar en vista.
     * @param fecetFlujoProrrogaOficio
     * @return
     */
    public FlujoProrrogaOficioConsultaDTO convertToFlujoProrrogaOficioConsultaDTO(FecetFlujoProrrogaOficio fecetFlujoProrrogaOficio) {
        FlujoProrrogaOficioConsultaDTO flujoProrrogaOficioConsultaDTO = new FlujoProrrogaOficioConsultaDTO();
        flujoProrrogaOficioConsultaDTO.setIdFlujoProrrogaOficio(fecetFlujoProrrogaOficio.getIdFlujoProrrogaOficio());
        flujoProrrogaOficioConsultaDTO.setMotivo(fecetFlujoProrrogaOficio.getFececEstatus().getDescripcion());
        flujoProrrogaOficioConsultaDTO.setTotalAnexos(fecetFlujoProrrogaOficio.getTotalAnexos());
        return flujoProrrogaOficioConsultaDTO;
    }
    public FlujoProrrogaOrdenConsultaDTO convertToFlujoProrrogaOrdenConsultaDTO(FecetFlujoProrrogaOrden fecetFlujoProrrogaOrden){
        FlujoProrrogaOrdenConsultaDTO flujoProrrogaOrdenConsultaDTO = new FlujoProrrogaOrdenConsultaDTO();
        flujoProrrogaOrdenConsultaDTO.setIdFlujoProrrogaOrden(fecetFlujoProrrogaOrden.getIdFlujoProrrogaOrden());
        flujoProrrogaOrdenConsultaDTO.setMotivo(fecetFlujoProrrogaOrden.getFececEstatus().getDescripcion());
        flujoProrrogaOrdenConsultaDTO.setTotalAnexos(fecetFlujoProrrogaOrden.getTotalAnexos());
        return flujoProrrogaOrdenConsultaDTO;
        
    }
    
    public List<AnexoProrrogaOrdenConsultaDTO> convertToAnexoProrrogaOrdenConsultaDTO(List<FecetAnexosProrrogaOrden> listFecetAnexosProrrogaOrden){
        List<AnexoProrrogaOrdenConsultaDTO> listAnexoProrrogaOrdenConsultaDTOs = new ArrayList<AnexoProrrogaOrdenConsultaDTO>();
        for(FecetAnexosProrrogaOrden fecetAnexosProrrogaOrden: listFecetAnexosProrrogaOrden){
            AnexoProrrogaOrdenConsultaDTO anexoProrrogaOrdenConsultaDTO  = new AnexoProrrogaOrdenConsultaDTO();
            anexoProrrogaOrdenConsultaDTO.setIdFlujoProrrogaOrden(fecetAnexosProrrogaOrden.getIdFlujoProrrogaOrden());
            anexoProrrogaOrdenConsultaDTO.setFechaCreacion(fecetAnexosProrrogaOrden.getFechaCreacion());
            anexoProrrogaOrdenConsultaDTO.setRutaArchivo(fecetAnexosProrrogaOrden.getRutaArchivo());
            anexoProrrogaOrdenConsultaDTO.setNombreArchivo(fecetAnexosProrrogaOrden.getNombreArchivo());
            anexoProrrogaOrdenConsultaDTO.setTipoAnexo(fecetAnexosProrrogaOrden.getTipoAnexo());
            listAnexoProrrogaOrdenConsultaDTOs.add(anexoProrrogaOrdenConsultaDTO);
        }
        return listAnexoProrrogaOrdenConsultaDTOs;       
    }
    
    public List<AnexoProrrogaOficioConsultaDTO> convertToAnexoProrrogaOficioConsultaDTO(List<FecetAnexosProrrogaOficio> listAnexosProrrogaOficios){
        List<AnexoProrrogaOficioConsultaDTO> listAnexoProrrogaOficioConsultaDTOs  = new ArrayList<AnexoProrrogaOficioConsultaDTO>();
        for(FecetAnexosProrrogaOficio fecetAnexosProrrogaOficio:listAnexosProrrogaOficios){
            AnexoProrrogaOficioConsultaDTO anexoProrrogaOficioConsultaDTO = new AnexoProrrogaOficioConsultaDTO();
            anexoProrrogaOficioConsultaDTO.setIdFlujoProrrogaOficio(fecetAnexosProrrogaOficio.getIdFlujoProrrogaOficio());
            anexoProrrogaOficioConsultaDTO.setFechaCreacion(fecetAnexosProrrogaOficio.getFechaCreacion());
            anexoProrrogaOficioConsultaDTO.setRutaArchivo(fecetAnexosProrrogaOficio.getRutaArchivo());
            anexoProrrogaOficioConsultaDTO.setNombreArchivo(fecetAnexosProrrogaOficio.getNombreArchivo());
            anexoProrrogaOficioConsultaDTO.setTipoAnexo(fecetAnexosProrrogaOficio.getTipoAnexo());
            listAnexoProrrogaOficioConsultaDTOs.add(anexoProrrogaOficioConsultaDTO);
         }
        return listAnexoProrrogaOficioConsultaDTOs;
        
    }
    

    /**
     * Elimina las ordenes repetidas que continen la lista que recibe como
     * parametro
     * 
     * @param listOrden
     * @return
     */
    public List<AgaceOrden> eliminarOrdenesRepetidas(List<AgaceOrden> listOrden) {
        List<BigDecimal> listIdOrdenes = new ArrayList<BigDecimal>();
        List<AgaceOrden> registros = new ArrayList<AgaceOrden>();
        for (AgaceOrden orden : listOrden) {
            if (!listIdOrdenes.contains(orden.getIdOrden())) {
                listIdOrdenes.add(orden.getIdOrden());
                registros.add(orden);
            }
        }
        return registros;
    }
}
