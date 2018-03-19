package mx.gob.sat.siat.feagace.vista.helper;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.helper.BaseHelper;
import mx.gob.sat.siat.feagace.modelo.dto.common.ReportePistaAuditoriaExternaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.ReportePistaAuditoriaInternaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReporteInsumosDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReporteOrdenesDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReportePropuestasDTO;


@Component
public class ValidarReportesMapHelper extends BaseHelper implements Serializable {

    private static final long serialVersionUID = 1L;
    private NumberFormat formatoImporte = NumberFormat.getCurrencyInstance(new Locale("es", "MX"));
    private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/M/yyyy");
    private static final String FECHAREGISTRO = "fechaRegistro";
    private static final String RFCCONTRIBUYENTE = "rfcContribuyente";
    private static final String RAZONSOCIAL = "razonSocial";
    private static final String ESTATUS = "estatus";
    private static final String SUBPROGRAMA = "subprograma";
    private static final String SECTOR = "sector";
    private static final String ACTIVIDADPREPODERANTE = "actividadPrepoderante";
    private static final String METODO = "metodo";
    private static final String UNIDADADMINISTRATIVA = "unidadAdministrativa";
    private static final String FIRMANTE = "firmante";
    private static final String AUDITOR = "auditor";
    private static final String IDREGISTRO = "idRegistro";
    private static final String FECHAPERIODOPROPUESTOINICIO = "fechaPeriodoPropuestoInicio";
    private static final String FECHAPERIODOPROPUESTOFIN = "fechaPeriodoPropuestoFin";
    private static final String NO_APLICA = "N/A";

    public List<ReporteInsumosDTO> amparadoPPEEInsumos(List<ReporteInsumosDTO> listaReporte) {
        List<ReporteInsumosDTO> lista = new ArrayList<ReporteInsumosDTO>();
        if (listaReporte != null && listaReporte.size() > 0) {
            for (ReporteInsumosDTO dto : listaReporte) {
                ReporteInsumosDTO obj = new ReporteInsumosDTO();
                if (dto.getContribuyente().isAmparado() || dto.getContribuyente().isPpee()) {
                    obj.setIdRegistro(null);
                    obj.setEntidad(null);
                    obj.setActividadPrepoderante(null);
                    obj.setSector(null);
                    obj.setEstatus(null);
                    obj.setSubprograma(null);
                    obj.setFechaAprobacion(null);
                    obj.setFechaPeriodoPropuestoFin(null);
                    obj.setFechaPeriodoPropuestoInicio(null);
                    obj.setFechaRechazo(null);
                    obj.setFechaRegistro(null);
                    obj.setRazonSocial(dto.getRazonSocial());
                    obj.setContribuyente(dto.getContribuyente());
                    lista.add(obj);
                } else {
                    lista.add(dto);
                }
            }
        } else {
            lista = null;
        }
        return lista;
    }

    public List<Map<String, ?>> listaInsumos(List<ReporteInsumosDTO> lista) {
        List<Map<String, ?>> insumos = new ArrayList<Map<String, ?>>();
        for (ReporteInsumosDTO dto : lista) {
            Map<String, Object> mapDTO = new HashMap<String, Object>();
            mapDTO.put(IDREGISTRO, dto.getIdRegistro() != null ? dto.getIdRegistro() : "");
            mapDTO.put(FECHAREGISTRO,
                       dto.getFechaRegistro() != null ? this.getFormatoFecha().format(dto.getFechaRegistro()) : "");
            mapDTO.put(FECHAPERIODOPROPUESTOINICIO,
                       dto.getFechaPeriodoPropuestoInicio() != null ? this.getFormatoFecha().format(dto.getFechaPeriodoPropuestoInicio()) :
                       "");
            mapDTO.put(FECHAPERIODOPROPUESTOFIN,
                       dto.getFechaPeriodoPropuestoFin() != null ? this.getFormatoFecha().format(dto.getFechaPeriodoPropuestoFin()) :
                       "");
            mapDTO.put(RFCCONTRIBUYENTE, dto.getRfcContribuyente() != null ? dto.getRfcContribuyente() : "");
            mapDTO.put(RAZONSOCIAL, dto.getRazonSocial() != null ? dto.getRazonSocial() : "");
            mapDTO.put("estado", dto.getEntidad() != null ? dto.getEntidad() : "");
            mapDTO.put(ESTATUS, dto.getEstatus() != null ? dto.getEstatus() : "");
            mapDTO.put("fechaAprobacion",
                       dto.getFechaAprobacion() != null ? this.getFormatoFecha().format(dto.getFechaAprobacion()) :
                       "");
            mapDTO.put("fechaRechazo",
                       dto.getFechaRechazo() != null ? this.getFormatoFecha().format(dto.getFechaRechazo()) : "");
            mapDTO.put("motivoRechazo", dto.getMotivoRechazo() != null ? dto.getMotivoRechazo() : "");
            mapDTO.put(SUBPROGRAMA, dto.getSubprograma() != null ? dto.getSubprograma() : "");
            mapDTO.put(SECTOR, dto.getSector() != null ? dto.getSector() : "");
            mapDTO.put(ACTIVIDADPREPODERANTE,
                       dto.getActividadPrepoderante() != null ? dto.getActividadPrepoderante() : "");
            insumos.add(mapDTO);
        }
        return insumos;
    }

    public List<ReportePropuestasDTO> amparadoPPEEPropuestas(List<ReportePropuestasDTO> listaReporte) {
        List<ReportePropuestasDTO> lista = new ArrayList<ReportePropuestasDTO>();
        if (listaReporte != null && listaReporte.size() > 0) {
            for (ReportePropuestasDTO dto : listaReporte) {
                ReportePropuestasDTO obj = new ReportePropuestasDTO();
                if (dto.getContribuyente().isAmparado() || dto.getContribuyente().isPpee()) {
                    obj.setRegistroId(null);
                    obj.setFechaInicioPeriodo(null);
                    obj.setFechaFinPeriodo(null);
                    obj.setRfcContribuyente(null);
                    obj.setMetodo(null);
                    obj.setEstatus(null);
                    obj.setPresuntiva(null);
                    obj.setTipoRevision(null);
                    obj.setRegimen(null);
                    obj.setSubprograma(null);
                    obj.setSector(null);
                    obj.setActividadPrepoderante(null);
                    obj.setCausaProgramacion(null);
                    obj.setUnidadAdministrativa(null);
                    obj.setFirmante(null);
                    obj.setAuditor(null);
                    obj.setRazonSocial(dto.getRazonSocial());
                    obj.setContribuyente(dto.getContribuyente());
                    lista.add(obj);
                } else {
                    lista.add(dto);
                }
            }
        }
        return lista;
    }

    public List<Map<String, ?>> listaPropuestas(List<ReportePropuestasDTO> lista) {
        List<Map<String, ?>> propuestas = new ArrayList<Map<String, ?>>();
        if (lista != null && lista.size() > 0) {
            for (ReportePropuestasDTO dto : lista) {
                Map<String, Object> mapDTO = new HashMap<String, Object>();
                mapDTO.put(IDREGISTRO, dto.getRegistroId() != null ? dto.getRegistroId() : "");
                mapDTO.put(FECHAPERIODOPROPUESTOINICIO,
                           dto.getFechaInicioPeriodo() != null ? this.getFormatoFecha().format(dto.getFechaInicioPeriodo()) :
                           "");
                mapDTO.put(FECHAPERIODOPROPUESTOFIN,
                           dto.getFechaFinPeriodo() != null ? this.getFormatoFecha().format(dto.getFechaFinPeriodo()) :
                           "");
                mapDTO.put(RFCCONTRIBUYENTE, dto.getRfcContribuyente() != null ? dto.getRfcContribuyente() : "");
                mapDTO.put(RAZONSOCIAL, dto.getRazonSocial() != null ? dto.getRazonSocial() : "");
                mapDTO.put(ESTATUS, dto.getEstatus() != null ? dto.getEstatus() : "");
                mapDTO.put(SUBPROGRAMA, dto.getSubprograma() != null ? dto.getSubprograma() : "");
                mapDTO.put(SECTOR, dto.getSector() != null ? dto.getSector() : "");
                mapDTO.put(ACTIVIDADPREPODERANTE,
                           dto.getActividadPrepoderante() != null ? dto.getActividadPrepoderante() : "");
                mapDTO.put(METODO, dto.getMetodo() != null ? dto.getMetodo() : "");
                mapDTO.put("tipoRevision", dto.getTipoRevision() != null ? dto.getTipoRevision() : "");
                mapDTO.put("causaProgramacion", dto.getCausaProgramacion() != null ? dto.getCausaProgramacion() : "");
                mapDTO.put("regimen", dto.getRegimen() != null ? dto.getRegimen() : "");
                mapDTO.put(UNIDADADMINISTRATIVA,
                           dto.getUnidadAdministrativa() != null ? dto.getUnidadAdministrativa() : "");
                mapDTO.put(FIRMANTE, dto.getFirmante() != null ? dto.getFirmante() : "");
                mapDTO.put(AUDITOR, dto.getAuditor() != null ? dto.getAuditor() : "");
                mapDTO.put("presuntiva",
                           dto.getPresuntiva() != null ? this.getFormatoImporte().format(dto.getPresuntiva()) : "");
                propuestas.add(mapDTO);
            }
        }
        return propuestas;
    }

    public List<ReporteOrdenesDTO> amparadoPPEEOrdenes(List<ReporteOrdenesDTO> listaReporte) {
        List<ReporteOrdenesDTO> lista = new ArrayList<ReporteOrdenesDTO>();
        if (listaReporte != null && listaReporte.size() > 0) {
            for (ReporteOrdenesDTO dto : listaReporte) {
                ReporteOrdenesDTO obj = new ReporteOrdenesDTO();
                if (dto.getContribuyente().isAmparado() || dto.getContribuyente().isPpee()) {
                    obj.setNumeroOrden(null);
                    obj.setFechaInicioPeriodo(null);
                    obj.setFechaFinPeriodo(null);
                    obj.setRfcContribuyente(null);
                    obj.setMetodo(null);
                    obj.setSubprograma(null);
                    obj.setSector(null);
                    obj.setActividadPrepoderante(null);
                    obj.setUnidadAdministrativa(null);
                    obj.setFirmante(null);
                    obj.setAuditor(null);
                    obj.setEstatus(null);
                    obj.setFechaRegistro(null);
                    obj.setFechaFirma(null);
                    obj.setFechaEnvioNotificacion(null);
                    obj.setRazonSocial(dto.getRazonSocial());
                    obj.setContribuyente(dto.getContribuyente());
                    lista.add(obj);
                } else {
                    lista.add(dto);
                }
            }
        }
        return lista;
    }

    public List<Map<String, ?>> listaOrdenes(List<ReporteOrdenesDTO> lista) {
        List<Map<String, ?>> ordenes = new ArrayList<Map<String, ?>>();
        if (lista != null && lista.size() > 0) {
            for (ReporteOrdenesDTO dto : lista) {
                Map<String, Object> mapDTO = new HashMap<String, Object>();
                mapDTO.put("numeroOrden", dto.getNumeroOrden() != null ? dto.getNumeroOrden() : "");
                mapDTO.put(RFCCONTRIBUYENTE, dto.getRfcContribuyente() != null ? dto.getRfcContribuyente() : "");
                mapDTO.put(RAZONSOCIAL, dto.getRazonSocial() != null ? dto.getRazonSocial() : "");
                mapDTO.put(ESTATUS, dto.getEstatus() != null ? dto.getEstatus() : "");
                mapDTO.put(SUBPROGRAMA, dto.getSubprograma() != null ? dto.getSubprograma() : "");
                mapDTO.put(SECTOR, dto.getSector() != null ? dto.getSector() : "");
                mapDTO.put(ACTIVIDADPREPODERANTE,
                           dto.getActividadPrepoderante() != null ? dto.getActividadPrepoderante() : "");
                mapDTO.put(METODO, dto.getMetodo() != null ? dto.getMetodo() : "");
                mapDTO.put(UNIDADADMINISTRATIVA,
                           dto.getUnidadAdministrativa() != null ? dto.getUnidadAdministrativa() : "");
                mapDTO.put(FIRMANTE, dto.getFirmante() != null ? dto.getFirmante() : "");
                mapDTO.put(AUDITOR, dto.getAuditor() != null ? dto.getAuditor() : "");
                mapDTO.put(FECHAREGISTRO,
                           dto.getFechaRegistro() != null ? this.getFormatoFecha().format(dto.getFechaRegistro()) :
                           "");
                mapDTO.put("fechaFirma",
                           dto.getFechaFirma() != null ? this.getFormatoFecha().format(dto.getFechaFirma()) : "");
                mapDTO.put("fechaEnvioNotificacion",
                           dto.getFechaEnvioNotificacion() != null ? this.getFormatoFecha().format(dto.getFechaEnvioNotificacion()) :
                           "");
                ordenes.add(mapDTO);
            }
        }
        return ordenes;
    }

    public List<Map<String, ?>> listaVacia() {
        List<Map<String, ?>> lista = new ArrayList<Map<String, ?>>();
        Map<String, Object> mapDTO = new HashMap<String, Object>();
        lista.add(mapDTO);
        return lista;
    }
    
    public List<Map<String, ?>> listaImagenesGraficas(List<ByteArrayInputStream> graficas) {
        List<Map<String, ?>> lista = new ArrayList<Map<String, ?>>();
        for (InputStream in : graficas) {
            Map<String, Object> mapDTO = new HashMap<String, Object>();
            mapDTO.put("imagenGrafica", in);
            lista.add(mapDTO);
        }
        return lista;
    }
    
    
    public String crearRutaFinal(String separador,String rfc) {
        StringBuilder path = new StringBuilder();
        if (System.getProperty("os.name").indexOf("Windows") == -1) {
            path.append(separador);
        }
        path.append("resources");
        path.append(separador);
        path.append("graficas");
        path.append(separador);
        path.append(rfc);
        
        return path.toString();
    }

    public List<Map<String, ?>> listaPistaAudit(List<ReportePistaAuditoriaInternaDTO> listRepInterno) {
        List<Map<String, ?>> pistaAudit = new ArrayList<Map<String, ?>>();
        for (ReportePistaAuditoriaInternaDTO dto : listRepInterno) {
            Map<String, Object> mapDto = new HashMap<String, Object>();
            mapDto.put("fechaYHora", dto.getFechaYHora());
            mapDto.put("rfcUsuario", dto.getRfcUsuario());
            mapDto.put("nomUsuario", dto.getNomUsuario());
            mapDto.put("ipMaquina", dto.getIpMaquina());
            mapDto.put("nomMaquina", dto.getNomMaquina());
            mapDto.put("modIngreso", dto.getModIngreso());
            mapDto.put("operRealizada", dto.getOperRealizada());
            pistaAudit.add(mapDto);
        }
        return pistaAudit;
    }
    
    public List<Map<String, ?>> listaPistaAuditExt(List<ReportePistaAuditoriaExternaDTO> listRepExterno) {
        List<Map<String, ?>> pistaAudit = new ArrayList<Map<String, ?>>();
        for (ReportePistaAuditoriaExternaDTO dto : listRepExterno) {
            Map<String, Object> mapDto = new HashMap<String, Object>();
            mapDto.put("fechaYHora", dto.getFechaYHora());
            mapDto.put("rfcContribuyente", dto.getRfcContribuyente() != null ? dto.getRfcContribuyente() : NO_APLICA);
            mapDto.put("rfcRepLegal", dto.getRfcRepLegal() != null ? dto.getRfcRepLegal() : NO_APLICA);
            mapDto.put("rfcApodLegal", dto.getRfcApodLegal() != null ? dto.getRfcApodLegal() : NO_APLICA);
            mapDto.put("rfcAgentAduanal", dto.getRfcAgentAduanal() != null ? dto.getRfcAgentAduanal() : NO_APLICA);
            mapDto.put("rfcApodLegalRepLegal", dto.getRfcApodLegalRepLegal() != null ? dto.getRfcApodLegalRepLegal() : NO_APLICA);
            mapDto.put("ipMaquina", dto.getIpMaquina());
            mapDto.put("modIngreso", dto.getModIngreso());
            mapDto.put("operRealizada", dto.getOperRealizada());
            pistaAudit.add(mapDto);
        }
        return pistaAudit;
    }

    public void setFormatoFecha(SimpleDateFormat formatoFecha) {
        this.formatoFecha = formatoFecha;
    }

    public SimpleDateFormat getFormatoFecha() {
        return formatoFecha;
    }

    public void setFormatoImporte(NumberFormat formatoImporte) {
        this.formatoImporte = formatoImporte;
    }

    public NumberFormat getFormatoImporte() {
        return formatoImporte;
    }
}
