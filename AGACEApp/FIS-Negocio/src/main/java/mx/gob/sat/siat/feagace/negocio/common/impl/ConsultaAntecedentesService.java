package mx.gob.sat.siat.feagace.negocio.common.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.mx.gob.sat.ArrayOfRevisionesSUIEFI;
import org.mx.gob.sat.ArrayOfRevisionesSUII;
import org.mx.gob.sat.RevisionesSUIEFI;
import org.mx.gob.sat.RevisionesSUII;
import org.mx.gob.sat.service.Service1SoapSuiefiService;
import org.springframework.beans.factory.annotation.Autowired;

import mx.gob.sat.siat.buzon.exception.BuzonNoDisponibleException;
import mx.gob.sat.siat.buzon.model.MedioComunicacion;
import mx.gob.sat.siat.buzon.service.BuzonTributarioService;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetImpuestoDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetInsumoDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatus;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececArace;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuestoDescripcion;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.ConstantesError;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.propuestas.PropuestasServiceAbstract;
import mx.gob.sat.siat.feagace.negocio.util.constantes.DateUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.FormatosFechasEnum;
import mx.gob.sat.siat.feagaff.model.ConsultaAgaffAgaceDto;
import mx.gob.sat.siat.feagaff.service.ConsultaPropuestaOrdenWSService;

public class ConsultaAntecedentesService extends PropuestasServiceAbstract {

    /**
     * Serial
     */
    private static final long serialVersionUID = -8342376609494165591L;

    @Autowired
    private transient FecetPropuestaDao fecetPropuestaDao;
    @Autowired
    private transient ConsultaPropuestaOrdenWSService consultaPropuestaOrdenWSService;
    @Autowired
    private transient Service1SoapSuiefiService service1SoapSuiefiService;
    @Autowired
    private transient BuzonTributarioService buzonTributarioService;
    @Autowired
    private transient FecetInsumoDao fecetInsumoDao;
    @Autowired
    private transient FecetImpuestoDao fecetImpuestoDao;

    private static final String HEADER_FECHA_REGISTRO = "FECHA DE REGISTRO";
    private static final String INSUMO = "INSUMOS";
    private static final String INSERTANDO = "Insertando";

    private List<FecetImpuestoDescripcion> impuestos;

    protected List<FecetPropuesta> consultaSUIEFI(String rfc, Date periodoInicial, Date periodoFinal)
            throws NegocioException {

        List<FecetPropuesta> propuestas = new ArrayList<FecetPropuesta>();
        try {

            ArrayOfRevisionesSUIEFI revisionesSUIEFI;

            revisionesSUIEFI = service1SoapSuiefiService.feObtenRevisionesSUIEFI(rfc,
                    DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY, periodoInicial),
                    DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY, periodoFinal),
                    Constantes.UNO);
            logger.info("----- Termina feObtenRevisionesSUIEFI revisiones size: [{}]",
                    revisionesSUIEFI != null && revisionesSUIEFI.getRevisionesSUIEFI() != null
                            ? revisionesSUIEFI.getRevisionesSUIEFI().size() : revisionesSUIEFI);
            if (revisionesSUIEFI != null && revisionesSUIEFI.getRevisionesSUIEFI() != null
                    && revisionesSUIEFI.getRevisionesSUIEFI().size() > 0) {
                FecetPropuesta fecetPropuesta;
                FecetContribuyente fecetContribuyente;
                FececMetodo feceaMetodo;
                for (RevisionesSUIEFI revisionSUIEFI : revisionesSUIEFI.getRevisionesSUIEFI()) {
                    if (revisionSUIEFI.getRFC() == null || revisionSUIEFI.getRFC().trim().equals("")) {
                        continue;
                    }
                    fecetPropuesta = new FecetPropuesta();
                    fecetPropuesta.setIdRegistro(revisionSUIEFI.getNumOrden());
                    fecetContribuyente = new FecetContribuyente();
                    fecetContribuyente.setRfc(revisionSUIEFI.getRFC());
                    fecetPropuesta.setFecetContribuyente(fecetContribuyente);
                    feceaMetodo = new FececMetodo();
                    feceaMetodo.setAbreviatura(revisionSUIEFI.getMetodo());
                    fecetPropuesta.setFeceaMetodo(feceaMetodo);
                    FececEstatus fececEstatus = new FececEstatus();
                    fececEstatus.setDescripcion(revisionSUIEFI.getEstatus());
                    fecetPropuesta.setFececEstatus(fececEstatus);
                    fecetPropuesta.setIdPropuesta(new BigDecimal(revisionSUIEFI.getOrigen()));
                    fecetPropuesta.setInformePresentacion(revisionSUIEFI.getTipo());
                    String periodo = revisionSUIEFI.getPeriodos();
                    if (periodo != null && !periodo.equals("")) {
                        String periodos[] = null;
                        if (periodo.contains("-")) {
                            periodos = periodo.split("\\s-\\s");
                        } else if (periodo.contains("_")) {
                            periodos = periodo.split("\\s_\\s");
                        }
                        if (periodos != null && periodos.length == 2) {

                            fecetPropuesta.setFechaInicioPeriodo(DateUtil.getDateToFormattedString(
                                    FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY, periodos[0].trim()));

                            fecetPropuesta.setFechaFinPeriodo(DateUtil.getDateToFormattedString(
                                    FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY, periodos[1].trim()));

                        } else {
                            fecetPropuesta.setFechaInicioPeriodo(null);
                            fecetPropuesta.setFechaFinPeriodo(null);
                        }
                    }

                    fecetPropuesta.setFechaCreacion(DateUtil.getDateToFormattedString(
                            FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY, revisionSUIEFI.getFechaEmision()));

                    fecetPropuesta.setFechaBaja(DateUtil.getDateToFormattedString(
                            FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY, revisionSUIEFI.getFechaTermino()));

                    propuestas.add(fecetPropuesta);
                }
            }
        } catch (Exception e) {
            throw new NegocioException("No se pudo validar la informaci\u00f3n en los sistemas: SUIEFI", e);
        }
        return propuestas;
    }

    protected List<FecetPropuesta> consultaSICSEP(String rfc, Date periodoInicial, Date periodoFinal)
            throws NegocioException {

        List<FecetPropuesta> propuestas = new ArrayList<FecetPropuesta>();
        ArrayOfRevisionesSUII revisionesSUII;
        try {
            revisionesSUII = service1SoapSuiefiService.feObtenRevisionesSUII(rfc,
                    DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY, periodoInicial),
                    DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY, periodoFinal),
                    Constantes.UNO);
            logger.info("----- Termina feObtenRevisionesSUII revisiones size: [{}]",
                    revisionesSUII != null && revisionesSUII.getRevisionesSUII() != null
                            ? revisionesSUII.getRevisionesSUII().size() : revisionesSUII);
            if (revisionesSUII != null && revisionesSUII.getRevisionesSUII() != null
                    && revisionesSUII.getRevisionesSUII().size() > 0) {
                FecetPropuesta fecetPropuesta;
                FecetContribuyente fecetContribuyente;
                FececMetodo feceaMetodo;
                for (RevisionesSUII revisionSUII : revisionesSUII.getRevisionesSUII()) {
                    if (revisionSUII.getRFC() == null || revisionSUII.getRFC().trim().equals("")) {
                        continue;
                    }
                    fecetPropuesta = new FecetPropuesta();
                    fecetPropuesta.setIdRegistro(revisionSUII.getNumOrden());
                    fecetContribuyente = new FecetContribuyente();
                    fecetContribuyente.setRfc(revisionSUII.getRFC());
                    fecetPropuesta.setFecetContribuyente(fecetContribuyente);
                    fecetPropuesta.setIdPropuesta(new BigDecimal(revisionSUII.getOrigen()));
                    feceaMetodo = new FececMetodo();
                    feceaMetodo.setAbreviatura(revisionSUII.getMetodo());
                    fecetPropuesta.setFeceaMetodo(feceaMetodo);
                    propuestas.add(fecetPropuesta);
                    FececEstatus fececEstatus = new FececEstatus();
                    fececEstatus.setDescripcion(revisionSUII.getEstatus());
                    fecetPropuesta.setFececEstatus(fececEstatus);
                    fecetPropuesta.setInformePresentacion(revisionSUII.getTipo());

                    String periodo = revisionSUII.getPeriodos();
                    if (periodo != null && !periodo.equals("")) {
                        String periodos[] = null;
                        if (periodo.contains("-")) {
                            periodos = periodo.split("\\s-\\s");
                        } else if (periodo.contains("_")) {
                            periodos = periodo.split("\\s_\\s");
                        }
                        if (periodos != null && periodos.length == 2) {

                            fecetPropuesta.setFechaInicioPeriodo(DateUtil.getDateToFormattedString(
                                    FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY, periodos[0].trim()));

                            fecetPropuesta.setFechaFinPeriodo(DateUtil.getDateToFormattedString(
                                    FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY, periodos[1].trim()));

                        } else {
                            fecetPropuesta.setFechaInicioPeriodo(null);
                            fecetPropuesta.setFechaFinPeriodo(null);
                        }
                    }

                    fecetPropuesta.setFechaCreacion(DateUtil.getDateToFormattedString(
                            FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY, revisionSUII.getFechaEmision()));

                    fecetPropuesta.setFechaBaja(DateUtil.getDateToFormattedString(
                            FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY, revisionSUII.getFechaTermino()));

                }
            }
        } catch (Exception e) {
            throw new NegocioException(Constantes.NO_VALIDA_SICSEP, e);
        }
        return propuestas;
    }

    protected List<FecetPropuesta> consultaAGAFF(String rfc, Date periodoInicial, Date periodoFinal) throws NegocioException {
        List<FecetPropuesta> listaAgaff = new ArrayList<FecetPropuesta>();

        try {

            List<ConsultaAgaffAgaceDto> consAgaff = consultaPropuestaOrdenWSService.consultaPropuestaOrden(rfc,
                    periodoInicial, periodoFinal);

            if (consAgaff != null && !consAgaff.isEmpty()) {
                logger.info("--- Lista Agaff size: [{}]", consAgaff.size());
                for (ConsultaAgaffAgaceDto consultaAgaffDto : consAgaff) {
                    FecetPropuesta fecetPropuesta = new FecetPropuesta();
                    FecetContribuyente fecetContribuyente = new FecetContribuyente();
                    FececMetodo fececMetodo = new FececMetodo();
                    FececEstatus fececEstatus = new FececEstatus();
                    fecetContribuyente.setRfc(consultaAgaffDto.getRfc());
                    fecetPropuesta.setIdRegistro(consultaAgaffDto.getNumeroOrdenPropuesta());
                    fececMetodo.setAbreviatura(consultaAgaffDto.getMetodo());
                    fecetPropuesta.setInformePresentacion(consultaAgaffDto.getOrigen());
                    fecetPropuesta.setRfcSubadministrador(consultaAgaffDto.getTipo());
                    try {
                        fecetPropuesta.setFechaInicioPeriodo(
                                consultaAgaffDto.getPeriodoInicial().toGregorianCalendar().getTime());
                        fecetPropuesta
                                .setFechaFinPeriodo(consultaAgaffDto.getPeriodoFinal().toGregorianCalendar().getTime());
                        fecetPropuesta
                                .setFechaCreacion(consultaAgaffDto.getFechaEmision().toGregorianCalendar().getTime());
                    } catch (Exception e) {
                        logger.error("[{}]", ConstantesError.ERROR_FECHAS_AGAFF);
                    }
                    fececEstatus.setDescripcion(consultaAgaffDto.getEstatus());
                    fecetPropuesta.setFecetContribuyente(fecetContribuyente);
                    fecetPropuesta.setFeceaMetodo(fececMetodo);
                    fecetPropuesta.setFececEstatus(fececEstatus);
                    listaAgaff.add(fecetPropuesta);
                }
            }
        } catch (Exception e) {
            throw new NegocioException(Constantes.NO_VALIDA_AGAFF, e);
        }
        return listaAgaff;
    }

    protected List<FecetPropuesta> consultaAGACE(String rfc, Date periodoInicial, Date periodoFinal)
            throws NegocioException {
        List<FecetPropuesta> propuestas = null;
        List<AraceDTO> unidadesAdmin;
        try {
            propuestas = fecetPropuestaDao.buscarAntecedentes(periodoInicial, periodoFinal, rfc);

            if (propuestas != null && !propuestas.isEmpty()) {
                unidadesAdmin = new ArrayList<AraceDTO>();

                for (FecetPropuesta propuesta : propuestas) {
                    for (AraceDTO unidad : unidadesAdmin) {
                        if (unidad.getIdArace().equals(propuesta.getIdArace().intValue())) {
                            FececArace unidadPropuesta = new FececArace();
                            unidadPropuesta.setIdArace(new BigDecimal(unidad.getIdArace()));
                            unidadPropuesta.setNombre(unidad.getNombre());
                            propuesta.setFececArace(unidadPropuesta);
                            break;
                        }
                    }
                }
            }

            logger.info("--- Propuestas Agace size: [{}]", null != propuestas ? propuestas.size() : null);
        } catch (Exception e) {
            throw new NegocioException(ConstantesError.ERROR_CONSULTAR_ANTECEDENTES_AGACE, e);
        }
        return propuestas;
    }

    protected List<FecetPropuesta> consultaAGACEPropuestas(String rfc, FecetPropuesta dto) throws NegocioException {
        List<FecetPropuesta> propuestas = null;
        StringBuilder estatus = new StringBuilder();
        estatus.append(Constantes.ID50);
        estatus.append(",");
        estatus.append(Constantes.ID64);
        estatus.append(",");
        estatus.append(Constantes.ID65);
        estatus.append(",");
        estatus.append(Constantes.ID66);
        estatus.append(",");
        estatus.append(Constantes.ID67);
        try {
            propuestas = fecetPropuestaDao.buscarAntecedentesPropuestas(rfc, dto, estatus.toString());            
            List<AraceDTO> araces = new ArrayList<AraceDTO>();

            for (FecetPropuesta propuesta : propuestas) {
                for (AraceDTO arace : araces) {
                    if (arace.getIdArace() == propuesta.getIdArace().intValue()) {
                        FececUnidadAdministrativa unidad = new FececUnidadAdministrativa();
                        unidad.setIdUnidadAdministrativa(new BigDecimal(arace.getIdArace()));
                        unidad.setNombre(arace.getNombre());
                        unidad.setDescripcion(arace.getSede());
                        propuesta.setUnidadAdministrativa(unidad);
                        break;
                    }
                }
            }
            logger.info("--- Propuestas Agace size:" + propuestas.size());
        } catch (Exception e) {
            throw new NegocioException(ConstantesError.ERROR_CONSULTAR_ANTECEDENTES_AGACE, e);
        }
        return propuestas;
    }

    protected List<FecetPropuesta> consultaAGACEPropuestasPeriodoExacto(String rfc, FecetPropuesta dto)
            throws NegocioException {
        List<FecetPropuesta> propuestas = null;
        try {
            propuestas = fecetPropuestaDao.buscarAntecedentesPropuestas(rfc, dto);
            logger.info("--- Propuestas Agace size:" + propuestas.size());
        } catch (Exception e) {
            throw new NegocioException(ConstantesError.ERROR_CONSULTAR_ANTECEDENTES_AGACE, e);
        }
        return propuestas;
    }

    protected List<FecetInsumo> consultaAGACEInsumos(String rfc, FecetPropuesta dto) throws NegocioException {

        List<FecetInsumo> insumos = null;

        try {
            insumos = fecetInsumoDao.buscarAntecedentesInsumos(rfc, dto);

            List<AraceDTO> araces = new ArrayList<AraceDTO>();

            for (FecetInsumo insumo : insumos) {
                for (AraceDTO arace : araces) {
                    if (arace.getIdArace() == insumo.getIdArace().intValue()) {
                        FececUnidadAdministrativa unidad = new FececUnidadAdministrativa();
                        unidad.setIdUnidadAdministrativa(new BigDecimal(arace.getIdArace()));
                        unidad.setNombre(arace.getNombre());
                        unidad.setDescripcion(arace.getSede());
                        insumo.setFececUnidadAdministrativa(unidad);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            throw new NegocioException("Error al consultar en AGACE Insumos", e);
        }

        return insumos;
    }

    protected List<FecetInsumo> consultaAGACEInsumosPeriodoExacto(String rfc, FecetPropuesta dto)
            throws NegocioException {

        List<FecetInsumo> insumos = null;

        try {
            insumos = fecetInsumoDao.buscarAntecedentesInsumosPeriodoExacto(rfc, dto);
        } catch (Exception e) {
            throw new NegocioException("Error al consultar en AGACE Insumos", e);
        }

        return insumos;
    }

    protected HSSFWorkbook creaExcel(List<FecetPropuesta> revisionesAgace, List<FecetPropuesta> revisionesAgaff,
            List<FecetPropuesta> revisionesSUIEFIs, List<FecetPropuesta> revisionesSICSEP,
            List<FecetInsumo> revisionesInsumos, boolean muestraInsumo) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Reporte");

        int count = 0;
        int totalImpuestos = 0;

        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);

        count = insertaCabecera(Constantes.SUIEFI, sheet, count, style, totalImpuestos);
        if (revisionesSUIEFIs.size() > 0) {
            count = insertListFecetPropuestas(revisionesSUIEFIs, sheet, count, Constantes.SUIEFI);
        }
        count = count + 1;

        count = insertaCabecera(Constantes.SICSEP, sheet, count, style, totalImpuestos);
        if (revisionesSICSEP.size() > 0) {
            count = insertListFecetPropuestas(revisionesSICSEP, sheet, count, Constantes.SICSEP);
        }
        count = count + 1;

        count = insertaCabecera(Constantes.AGAFF, sheet, count, style, totalImpuestos);
        if (revisionesAgaff.size() > 0) {
            count = insertListFecetPropuestas(revisionesAgaff, sheet, count, Constantes.AGAFF);
        }
        count = count + 1;

        if (muestraInsumo) {
            count = insertaCabecera(INSUMO, sheet, count, style, totalImpuestos);
            if (revisionesInsumos.size() > 0) {
                count = insertListFecetInsumos(revisionesInsumos, sheet, count);
                logger.info(String.valueOf(count));
            }
        } else {
            totalImpuestos = cuentaImpuestos(revisionesAgace);
            count = insertaCabecera(Constantes.AGACE, sheet, count, style, totalImpuestos);
            if (revisionesAgace.size() > 0) {
                count = insertListFecetPropuestas(revisionesAgace, sheet, count, Constantes.AGACE);
                logger.info(String.valueOf(count));
            }
        }

        return workbook;
    }

    private int cuentaImpuestos(List<FecetPropuesta> revisionesAgace) {
        impuestos = new ArrayList<FecetImpuestoDescripcion>();
        Map<BigDecimal, FecetImpuestoDescripcion> listaPropuestas = new HashMap<BigDecimal, FecetImpuestoDescripcion>();
        List<Integer> lstImpXPropuesta = new ArrayList<Integer>();

        int contador = 0;
        for (FecetPropuesta propuesta : revisionesAgace) {
            impuestos.addAll(fecetImpuestoDao.traeImpuestoDescripcion(propuesta.getIdPropuesta()));
        }

        for (FecetPropuesta propuesta : revisionesAgace) {
            int impuestosXPropuesta = 0;
            for (FecetImpuestoDescripcion impuesto : impuestos) {
                if (propuesta.getIdPropuesta().equals(impuesto.getFecetImpuesto().getIdPropuesta())
                        && !listaPropuestas.containsKey(impuesto.getFecetImpuesto().getIdPropuesta())) {
                    impuestosXPropuesta++;
                    listaPropuestas.put(impuesto.getFecetImpuesto().getIdPropuesta(), impuesto);
                } else if (propuesta.getIdPropuesta().equals(impuesto.getFecetImpuesto().getIdPropuesta())
                        && listaPropuestas.containsKey(impuesto.getFecetImpuesto().getIdPropuesta())) {
                    impuestosXPropuesta++;
                }
            }
            lstImpXPropuesta.add(impuestosXPropuesta);
        }
        if (!lstImpXPropuesta.isEmpty()) {
            Collections.sort(lstImpXPropuesta);
            contador = lstImpXPropuesta.get(lstImpXPropuesta.size() - Constantes.UNO);
        }

        return contador;
    }

    private int insertaCabecera(String titulo, HSSFSheet sheet, final int count, HSSFCellStyle style,
            int totalImpuestos) {
        Row row = sheet.createRow(count);
        int contador = count;
        Cell cell = row.createCell(Constantes.CERO);
        cell.setCellValue(titulo);
        cell.setCellStyle(style);

        contador++;
        row = sheet.createRow(contador);
        cell = row.createCell(Constantes.CERO);
        cell.setCellValue(Constantes.RFC);
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.UNO);
        if (titulo.equals(Constantes.AGACE)) {
            cell.setCellValue(Constantes.HEADER_NUM_PROPUESTA);
        } else if (titulo.equals(INSUMO)) {
            cell.setCellValue(Constantes.HEADER_NUM_INSUMO);
        } else {
            cell.setCellValue(Constantes.NUM_ORDEN_PROP);
        }
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.DOS);
        cell.setCellValue(Constantes.METODOSTR);
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.TRES);
        if (titulo.equals(Constantes.AGACE)) {
            cell.setCellValue(Constantes.HEADER_UNIDAD_PROPUESTA);
        } else if (titulo.equals(INSUMO)) {
            cell.setCellValue(Constantes.HEADER_UNIDAD_INSUMO);
        } else {
            cell.setCellValue(Constantes.ORIGEN);
        }
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.CUATRO);
        cell.setCellValue(Constantes.PERIODO_INICIAL);
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.CINCO);
        cell.setCellValue(Constantes.PERIODO_FINAL);
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.SEIS);
        if (titulo.equals(Constantes.AGACE) || titulo.equals(INSUMO)) {
            cell.setCellValue(HEADER_FECHA_REGISTRO);
        } else {
            cell.setCellValue(Constantes.FECHA_EMISION);
        }
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.SIETE);
        cell.setCellValue(Constantes.ESTATUS);
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.OCHO);
        if (titulo.equals(Constantes.AGACE) || titulo.equals(INSUMO)) {
            cell.setCellValue(Constantes.HEADER_SUBPROGRAMA);
        } else {
            cell.setCellValue(Constantes.TIPO);
        }
        cell.setCellStyle(style);

        if (titulo.equals(Constantes.AGACE)) {
            int contadorInicial = Constantes.ENTERO_DIEZ;
            int numImpuesto = 0;

            cell = row.createCell(Constantes.ENTERO_NUEVE);
            cell.setCellValue("PRESUNTIVA");
            cell.setCellStyle(style);

            for (int i = 0; i < totalImpuestos; i++) {
                numImpuesto++;
                cell = row.createCell(contadorInicial);
                cell.setCellValue(Constantes.HEADER_IMPUESTO + numImpuesto);
                cell.setCellStyle(style);
                contadorInicial++;

                cell = row.createCell(contadorInicial);
                cell.setCellValue(Constantes.HEADER_CONCEPTO_IMPUESTO + numImpuesto);
                cell.setCellStyle(style);
                contadorInicial++;

                cell = row.createCell(contadorInicial);
                cell.setCellValue(Constantes.HEADER_MONTO_IMPUESTO + numImpuesto);
                cell.setCellStyle(style);
                contadorInicial++;
            }
        }

        contador++;

        return contador;
    }

    private int insertListFecetPropuestas(List<FecetPropuesta> fecetPropuestas, HSSFSheet sheet, int count,
            String titulo) {
        int contador = count;

        for (FecetPropuesta fecetPropuesta : fecetPropuestas) {
            int contadorImpuestoInicial = Constantes.ENTERO_DIEZ;
            Row row = sheet.createRow(contador);

            Cell cell = row.createCell(Constantes.CERO);
            cell.setCellValue((fecetPropuesta.getFecetContribuyente().getRfc() != null)
                    ? fecetPropuesta.getFecetContribuyente().getRfc() : "");
            sheet.autoSizeColumn((short) Constantes.CERO);

            cell = row.createCell(Constantes.UNO);
            cell.setCellValue((fecetPropuesta.getIdRegistro() != null) ? fecetPropuesta.getIdRegistro() : "");
            sheet.autoSizeColumn((short) Constantes.UNO);

            cell = row.createCell(Constantes.DOS);
            cell.setCellValue((fecetPropuesta.getFeceaMetodo() != null
                    && fecetPropuesta.getFeceaMetodo().getAbreviatura() != null)
                            ? fecetPropuesta.getFeceaMetodo().getAbreviatura() : "");
            sheet.autoSizeColumn((short) Constantes.DOS);

            cell = row.createCell(Constantes.TRES);
            if (Constantes.AGAFF.equals(titulo)) {
                cell.setCellValue((fecetPropuesta.getInformePresentacion() != null)
                        ? fecetPropuesta.getInformePresentacion() : "");
            } else if (Constantes.AGACE.equals(titulo)) {
                cell.setCellValue((fecetPropuesta.getFececArace() != null)
                        ? "" + fecetPropuesta.getFececArace().getNombre() : "");
            } else {
                cell.setCellValue(
                        (fecetPropuesta.getIdPropuesta() != null) ? "" + fecetPropuesta.getIdPropuesta() : "");
            }
            sheet.autoSizeColumn((short) Constantes.TRES);

            cell = row.createCell(Constantes.CUATRO);
            cell.setCellValue((fecetPropuesta.getFechaInicioPeriodo() != null) ? DateUtil.getFormattedDate(
                    FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY, fecetPropuesta.getFechaInicioPeriodo()) : "");
            sheet.autoSizeColumn((short) Constantes.CUATRO);

            cell = row.createCell(Constantes.CINCO);
            cell.setCellValue((fecetPropuesta.getFechaFinPeriodo() != null) ? DateUtil.getFormattedDate(
                    FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY, fecetPropuesta.getFechaFinPeriodo()) : "");
            sheet.autoSizeColumn((short) Constantes.CINCO);

            cell = row.createCell(Constantes.SEIS);
            cell.setCellValue((fecetPropuesta.getFechaCreacion() != null) ? DateUtil.getFormattedDate(
                    FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY, fecetPropuesta.getFechaCreacion()) : "");
            sheet.autoSizeColumn((short) Constantes.SEIS);

            cell = row.createCell(Constantes.SIETE);
            cell.setCellValue((fecetPropuesta.getFececEstatus() != null
                    && fecetPropuesta.getFececEstatus().getDescripcion() != null)
                            ? fecetPropuesta.getFececEstatus().getDescripcion().toUpperCase() : "");
            sheet.autoSizeColumn((short) Constantes.SIETE);

            cell = row.createCell(Constantes.OCHO);
            if (titulo.equals(Constantes.AGAFF)) {
                cell.setCellValue(
                        fecetPropuesta.getRfcSubadministrador() != null ? fecetPropuesta.getRfcSubadministrador() : "");
            } else if (titulo.equals(Constantes.AGACE)) {
                cell.setCellValue((fecetPropuesta.getFececSubprograma() != null)
                        ? fecetPropuesta.getFececSubprograma().getDescripcion() : "");
            } else {
                cell.setCellValue(
                        fecetPropuesta.getInformePresentacion() != null ? fecetPropuesta.getInformePresentacion() : "");
            }
            sheet.autoSizeColumn((short) Constantes.OCHO);
            insertaImpuestos(titulo, cell, row, fecetPropuesta, sheet, contadorImpuestoInicial);
            contador++;
        }
        return contador;
    }

    private void insertaImpuestos(String titulo, Cell cell, Row row, FecetPropuesta propuesta, HSSFSheet sheet,
            int contadorImpuestoInicial) {
        int contadorImpuestoInicialTmp = contadorImpuestoInicial;
        Cell cellTmp;
        HSSFSheet sheetTmp = sheet;
        if (titulo.equals(Constantes.AGACE)) {
            BigDecimal presuntiva = BigDecimal.ZERO;
            BigDecimal presuntivaConvertida;
            for (FecetImpuestoDescripcion impuestoPropuesta : impuestos) {
                if (impuestoPropuesta.getFecetImpuesto().getIdPropuesta().equals(propuesta.getIdPropuesta())) {
                    logger.info(INSERTANDO + " Impuesto --- " + contadorImpuestoInicialTmp);
                    logger.info(cell.toString());
                    logger.info(String.valueOf(contadorImpuestoInicialTmp));
                    cellTmp = row.createCell(contadorImpuestoInicialTmp);
                    cellTmp.setCellValue(impuestoPropuesta.getFececTipoImpuesto().getDescripcion());
                    sheetTmp.autoSizeColumn((short) contadorImpuestoInicialTmp);
                    contadorImpuestoInicialTmp++;

                    logger.error(INSERTANDO + " Concepto --- " + contadorImpuestoInicialTmp);
                    cellTmp = row.createCell(contadorImpuestoInicialTmp);
                    cellTmp.setCellValue(impuestoPropuesta.getFecetImpuesto().getFececConcepto().getDescripcion());
                    sheetTmp.autoSizeColumn((short) contadorImpuestoInicialTmp);
                    contadorImpuestoInicialTmp++;

                    logger.error(INSERTANDO + " Monto --- " + contadorImpuestoInicialTmp);
                    cellTmp = row.createCell(contadorImpuestoInicialTmp);
                    cellTmp.setCellValue(impuestoPropuesta.getFecetImpuesto().getMonto().doubleValue());
                    sheetTmp.autoSizeColumn((short) contadorImpuestoInicialTmp);
                    contadorImpuestoInicialTmp++;

                    presuntivaConvertida = impuestoPropuesta.getFecetImpuesto().getMonto();
                    presuntiva = presuntiva.add(presuntivaConvertida);
                }
                logger.info(INSERTANDO + " Presuntiva --- " + contadorImpuestoInicialTmp);
                logger.info(cell.toString());
                logger.info(String.valueOf(contadorImpuestoInicialTmp));
                cellTmp = row.createCell(Constantes.ENTERO_NUEVE);
                cellTmp.setCellValue(presuntiva.doubleValue());
                sheetTmp.autoSizeColumn((short) Constantes.ENTERO_NUEVE);
            }
        }
    }

    private int insertListFecetInsumos(List<FecetInsumo> fecetInsumos, HSSFSheet sheet, int count) {

        int contador = count;

        for (FecetInsumo fecetInsumo : fecetInsumos) {
            Row row = sheet.createRow(contador);

            Cell cell = row.createCell(Constantes.CERO);
            cell.setCellValue((fecetInsumo.getFecetContribuyente().getRfc() != null)
                    ? fecetInsumo.getFecetContribuyente().getRfc() : "");
            sheet.autoSizeColumn((short) Constantes.CERO);

            cell = row.createCell(Constantes.UNO);
            cell.setCellValue((fecetInsumo.getIdRegistro() != null) ? fecetInsumo.getIdRegistro() : "");
            sheet.autoSizeColumn((short) Constantes.UNO);

            cell = row.createCell(Constantes.DOS);
            cell.setCellValue("N/A");
            sheet.autoSizeColumn((short) Constantes.DOS);

            cell = row.createCell(Constantes.TRES);
            cell.setCellValue((fecetInsumo.getFececUnidadAdministrativa() != null)
                    ? "" + fecetInsumo.getFececUnidadAdministrativa().getNombre() : "");
            sheet.autoSizeColumn((short) Constantes.TRES);

            cell = row.createCell(Constantes.CUATRO);
            cell.setCellValue((fecetInsumo.getFechaInicioPeriodo() != null) ? DateUtil.getFormattedDate(
                    FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY, fecetInsumo.getFechaInicioPeriodo()) : "");
            sheet.autoSizeColumn((short) Constantes.CUATRO);

            cell = row.createCell(Constantes.CINCO);
            cell.setCellValue((fecetInsumo.getFechaFinPeriodo() != null) ? DateUtil.getFormattedDate(
                    FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY, fecetInsumo.getFechaFinPeriodo()) : "");
            sheet.autoSizeColumn((short) Constantes.CINCO);

            cell = row.createCell(Constantes.SEIS);
            cell.setCellValue((fecetInsumo.getFechaCreacion() != null) ? DateUtil.getFormattedDate(
                    FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY, fecetInsumo.getFechaCreacion()) : "");
            sheet.autoSizeColumn((short) Constantes.SEIS);

            cell = row.createCell(Constantes.SIETE);
            cell.setCellValue(
                    (fecetInsumo.getFececEstatus() != null && fecetInsumo.getFececEstatus().getDescripcion() != null)
                            ? fecetInsumo.getFececEstatus().getDescripcion().toUpperCase() : "");
            sheet.autoSizeColumn((short) Constantes.SIETE);

            cell = row.createCell(Constantes.OCHO);
            cell.setCellValue((fecetInsumo.getFececSubprograma() != null)
                    ? fecetInsumo.getFececSubprograma().getDescripcion() : "");
            sheet.autoSizeColumn((short) Constantes.OCHO);
            contador++;
        }
        return contador;
    }

    public String msgMediosContacto(String rfc) {
        String msg = "";
        try {
            List<MedioComunicacion> listMedioCom = buzonTributarioService.obtenerMediosComunicacion(rfc);
            if (listMedioCom != null && listMedioCom.size() > 0) {
                for (MedioComunicacion medioComunicacion : listMedioCom) {
                    if (medioComunicacion.getDescMedio() != null && !medioComunicacion.getDescMedio().trim().equals("")
                            && medioComunicacion.getDescMedio().toUpperCase().contains(Constantes.CORREO)) {
                        logger.info("--- Descripcion medio comunicacion: [{}]", medioComunicacion.getDescMedio());
                        if (medioComunicacion.getMedio() != null && !medioComunicacion.getMedio().trim().equals("")) {
                            logger.info("--- Medio comunicacion: [{}]", medioComunicacion.getMedio());
                            msg = Constantes.MSG_CON_MEDIO;
                        } else {
                            msg = MessageFormat.format(Constantes.MSG_SIN_MEDIO, rfc);
                        }
                    } else {
                        msg = MessageFormat.format(Constantes.MSG_SIN_MEDIO, rfc);
                    }
                }
            } else {
                msg = MessageFormat.format(Constantes.MSG_SIN_MEDIO, rfc);
            }
        } catch (BuzonNoDisponibleException e) {
            logger.error(Constantes.BUZON_NO_DISPONIBLE);
            msg = Constantes.BUZON_NO_DISPONIBLE;
            return msg;
        }
        return msg;
    }

    public List<FecetImpuestoDescripcion> getImpuestos(BigDecimal idPropuesta) {
        return fecetImpuestoDao.traeImpuestoDescripcion(idPropuesta);
    }

    public List<FecetImpuestoDescripcion> getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(List<FecetImpuestoDescripcion> impuestos) {
        this.impuestos = impuestos;
    }
}
