package mx.gob.sat.siat.feagace.negocio.common.carga.impl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececCausaProgramacionDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececConceptoDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececPrioridadDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececSectorDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececSubprogramaDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.insumos.FececTipoImpuestoDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FececRevisionDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas.FececTipoPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FeceaMetodoDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececCausaProgramacion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececConcepto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececPrioridad;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.CargaMasivaPropuestasDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.carga.CargaMasivaXExcelService;
import mx.gob.sat.siat.feagace.negocio.helper.CargaMasivaPropuestaHelper;
import mx.gob.sat.siat.feagace.negocio.propuestas.carga.CargaMasivaPropuestasService;
import mx.gob.sat.siat.feagace.negocio.util.carga.constantes.TipoListaCargaPropuestaEnum;
import mx.gob.sat.siat.feagace.negocio.util.carga.exception.CargaRegistroXExcelException;
import mx.gob.sat.siat.feagace.negocio.util.comparator.CargaMasivaComparador;
import mx.gob.sat.siat.feagace.negocio.util.validacion.ValidacionParametrosUtil;

@Service("cargaMasivaXExcelService")
public class CargaMasivaXExcelServiceImpl extends BaseBusinessServices implements CargaMasivaXExcelService {

    private static final long serialVersionUID = 1413534387417455427L;
    private static final String MSJ_CAMPO_NO_VALIDO = "El campo no es un valor valido";

    @Autowired
    private transient FececSubprogramaDao fececSubprogramaDao;
    @Autowired
    private transient FeceaMetodoDao feceaMetodoDao;
    @Autowired
    private transient FececTipoPropuestaDao fececTipoPropuestaDao;
    @Autowired
    private transient FececCausaProgramacionDao fececCausaProgramacionDao;
    @Autowired
    private transient FececRevisionDao fececRevisionDao;
    @Autowired
    private transient FececSectorDao fececSectorDao;
    @Autowired
    private transient FececTipoImpuestoDao fececTipoImpuestoDao;
    @Autowired
    private transient FececConceptoDao fececConceptoDao;
    @Autowired
    private transient CargaMasivaPropuestasService cargaMasivaPropuestasService;
    @Autowired
    private CargaMasivaPropuestaHelper cargaMasivaPropuestaHelper;
    @Autowired
    private transient FececPrioridadDao fececPrioridadDao;

    private List<FececMetodo> listaMetodo;
    private List<FececRevision> listaRevision;
    private List<FececSector> listaSector;
    private List<FececSubprograma> listaSubprograma;
    private List<FececTipoPropuesta> listaTipoPropuesta;
    private List<FececTipoImpuesto> listaTipoImpuesto;
    private List<FececCausaProgramacion> listaCausaProgramacion;
    private List<FececPrioridad> listaPrioridad;
    private Map<BigDecimal, List<FececConcepto>> listaConcepto;
    private List<CargaMasivaPropuestasDTO> listaErrores;

    private Workbook initLayoutXLS(InputStream layoutXLS) throws IOException, CargaRegistroXExcelException {
        try {
            if (layoutXLS != null) {
                return new HSSFWorkbook(layoutXLS);
            }
            throw new CargaRegistroXExcelException(ERROR_CARGA_ARCHIVO);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new CargaRegistroXExcelException(ERROR_CARGA_ARCHIVO, e);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new CargaRegistroXExcelException(ERROR_CARGA_ARCHIVO, e);
        }

    }

    @Override
    public Map<TipoListaCargaPropuestaEnum, List<CargaMasivaPropuestasDTO>> validarPropuestasDeExcel(
            InputStream archivoExcel, EmpleadoDTO empleado, String folioCarga) throws CargaRegistroXExcelException {
        Map<TipoListaCargaPropuestaEnum, List<CargaMasivaPropuestasDTO>> listaRegistrosExcel = new HashMap<TipoListaCargaPropuestaEnum, List<CargaMasivaPropuestasDTO>>();
        HSSFWorkbook workbook = null;
        listaMetodo = obtenerListaMetodo();
        listaRevision = obtenerListaRevision();
        listaSector = obtenerListaSector(new BigDecimal (Constantes.ENTERO_DIECINUEVE));
        listaSubprograma = obtenerListaSubPrograma(new BigDecimal(Constantes.ENTERO_DIECINUEVE));
        listaTipoPropuesta = obtenerListaTipoPropuesta();
        listaTipoImpuesto = obtenerListaImpuesto();
        listaCausaProgramacion = obtenerListaCausaProgramacion();
        listaConcepto = obtenerListaConcepto(listaTipoImpuesto);
        listaPrioridad = obtenerListaPrioridad();

        try {
            workbook = (HSSFWorkbook) initLayoutXLS(archivoExcel);
            Iterator<Row> rows = cargaMasivaPropuestasService.obtenerHojaExcel(workbook).rowIterator();
            rows.next();

            while (rows.hasNext()) {
                listaErrores = new ArrayList<CargaMasivaPropuestasDTO>();
                CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO1 = new CargaMasivaPropuestasDTO();
                cargaMasivaPropuestasDTO1.setFecetPropuesta(new FecetPropuesta());
                cargaMasivaPropuestasDTO1.getFecetPropuesta().setEmpleadoDto(empleado);
                HSSFRow row = (HSSFRow) rows.next();
                cargaMasivaPropuestasDTO1.setRow(row.getRowNum());
                cargaMasivaPropuestasDTO1 = validaColumnasEstaticasPropuestas(row, cargaMasivaPropuestasDTO1,
                        folioCarga);

                if (cargaMasivaPropuestasDTO1 != null && listaErrores.isEmpty()) {
                    if (listaRegistrosExcel
                            .containsKey(TipoListaCargaPropuestaEnum.PROPUESTAS_CARGADAS_CORRECTAMENTE)) {
                        List<CargaMasivaPropuestasDTO> listaPropuestasCorrectas = listaRegistrosExcel
                                .get(TipoListaCargaPropuestaEnum.PROPUESTAS_CARGADAS_CORRECTAMENTE);
                        boolean validarRepeticion = false;
                        for (CargaMasivaPropuestasDTO prop : listaPropuestasCorrectas) {
                            CargaMasivaComparador registro1 = new CargaMasivaComparador();
                            if (registro1.compare(prop, cargaMasivaPropuestasDTO1) == 0) {
                                validarRepeticion = true;
                                cargaMasivaPropuestasDTO1.setCell(0);
                                cargaMasivaPropuestasDTO1
                                        .setDescripcionError("El registro esta duplicado en el formato de carga");
                                break;
                            }
                        }

                        if (!validarRepeticion) {
                            cargaMasivaPropuestasDTO1.setCell(Constantes.RFC_CONTIBUYENTE);
                            cargaMasivaPropuestasService.validaAntecedentes(cargaMasivaPropuestasDTO1);
                            cargaMasivaPropuestasDTO1.getFecetPropuesta().setRfcCreacion(empleado.getRfc());
                            cargaMasivaPropuestasDTO1.getFecetPropuesta().setOrigenPropuestaId(new BigDecimal(2));
                            listaPropuestasCorrectas.add(cargaMasivaPropuestasDTO1);
                            listaRegistrosExcel.remove(TipoListaCargaPropuestaEnum.PROPUESTAS_CARGADAS_CORRECTAMENTE);
                            listaRegistrosExcel.put(TipoListaCargaPropuestaEnum.PROPUESTAS_CARGADAS_CORRECTAMENTE,
                                    listaPropuestasCorrectas);
                        } else {
                            if (listaRegistrosExcel
                                    .containsKey(TipoListaCargaPropuestaEnum.PROPUESTAS_CARGADAS_INCORRECTAMENTE)) {
                                List<CargaMasivaPropuestasDTO> listaPropuestasIncorrectas = listaRegistrosExcel
                                        .get(TipoListaCargaPropuestaEnum.PROPUESTAS_CARGADAS_INCORRECTAMENTE);
                                listaPropuestasIncorrectas.add(cargaMasivaPropuestasDTO1);

                                listaRegistrosExcel
                                        .remove(TipoListaCargaPropuestaEnum.PROPUESTAS_CARGADAS_INCORRECTAMENTE);
                                listaRegistrosExcel.put(TipoListaCargaPropuestaEnum.PROPUESTAS_CARGADAS_INCORRECTAMENTE,
                                        listaPropuestasIncorrectas);
                            } else {
                                List<CargaMasivaPropuestasDTO> listaPropuestasInorrectas = new ArrayList<CargaMasivaPropuestasDTO>();
                                listaPropuestasInorrectas.add(cargaMasivaPropuestasDTO1);
                                listaRegistrosExcel.put(TipoListaCargaPropuestaEnum.PROPUESTAS_CARGADAS_INCORRECTAMENTE,
                                        listaPropuestasInorrectas);
                            }
                        }
                    } else {
                        List<CargaMasivaPropuestasDTO> listaPropuestasCorrectas = new ArrayList<CargaMasivaPropuestasDTO>();
                        cargaMasivaPropuestasDTO1.setCell(Constantes.RFC_CONTIBUYENTE);
                        cargaMasivaPropuestasService.validaAntecedentes(cargaMasivaPropuestasDTO1);
                        cargaMasivaPropuestasDTO1.getFecetPropuesta().setRfcCreacion(empleado.getRfc());
                        cargaMasivaPropuestasDTO1.getFecetPropuesta().setOrigenPropuestaId(new BigDecimal(2));
                        listaPropuestasCorrectas.add(cargaMasivaPropuestasDTO1);
                        listaRegistrosExcel.put(TipoListaCargaPropuestaEnum.PROPUESTAS_CARGADAS_CORRECTAMENTE,
                                listaPropuestasCorrectas);

                    }
                } else {
                    if (cargaMasivaPropuestasDTO1 != null && listaRegistrosExcel
                            .containsKey(TipoListaCargaPropuestaEnum.PROPUESTAS_CARGADAS_INCORRECTAMENTE)) {
                        List<CargaMasivaPropuestasDTO> listaPropuestasIncorrectas = listaRegistrosExcel
                                .get(TipoListaCargaPropuestaEnum.PROPUESTAS_CARGADAS_INCORRECTAMENTE);
                        for (CargaMasivaPropuestasDTO carga : listaErrores) {
                            listaPropuestasIncorrectas.add(carga);
                        }
                        listaRegistrosExcel.remove(TipoListaCargaPropuestaEnum.PROPUESTAS_CARGADAS_INCORRECTAMENTE);
                        listaRegistrosExcel.put(TipoListaCargaPropuestaEnum.PROPUESTAS_CARGADAS_INCORRECTAMENTE,
                                listaPropuestasIncorrectas);
                    } else {
                        if (cargaMasivaPropuestasDTO1 != null) {
                            List<CargaMasivaPropuestasDTO> listaPropuestasInorrectas = new ArrayList<CargaMasivaPropuestasDTO>();
                            for (CargaMasivaPropuestasDTO carga : listaErrores) {
                                listaPropuestasInorrectas.add(carga);
                            }
                            listaRegistrosExcel.put(TipoListaCargaPropuestaEnum.PROPUESTAS_CARGADAS_INCORRECTAMENTE,
                                    listaPropuestasInorrectas);
                        }
                    }
                }
            }

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return listaRegistrosExcel;

    }

    private CargaMasivaPropuestasDTO validaColumnasEstaticasPropuestas(HSSFRow row,
            CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO, String folioCarga) {

        boolean renglonValido = false;

        Iterator<Cell> cellIterator = row.cellIterator();

        try {
            while (cellIterator.hasNext()) {
                Cell celda = cellIterator.next();
                if (celda != null && celda.getCellType() != Cell.CELL_TYPE_BLANK) {
                    renglonValido = true;
                    break;
                }
            }
        } catch (Exception ex) {
            renglonValido = false;
        }

        if (renglonValido) {
            HSSFCell cell = row.getCell(Constantes.RFC_CONTIBUYENTE);
            cargaMasivaPropuestasDTO.setCell(Constantes.RFC_CONTIBUYENTE);
            cargaMasivaPropuestasDTO.setDescripcionError("");
            // Valida Unidad Administrativa
            if (cell != null && cell.getCellType() == Cell.CELL_TYPE_STRING) {
                cargaMasivaPropuestasDTO.setRfcContribuyente(cell.getStringCellValue().trim().toUpperCase());
                cargaMasivaPropuestasService.validaRFC(cargaMasivaPropuestasDTO);
            } else {
                cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
                cargaMasivaPropuestasDTO.setValida(false);
            }
            llenarErrorLista(row.getRowNum(), Constantes.RFC_CONTIBUYENTE,
                    cargaMasivaPropuestasDTO.getDescripcionError(), listaErrores);

            // Valida Unidad Administrativa
            cell = row.getCell(Constantes.UNIDAD_ADMINISTRATIVA);
            cargaMasivaPropuestasDTO.setCell(Constantes.UNIDAD_ADMINISTRATIVA);
            if (cell == null || (cell.getCellType() == Cell.CELL_TYPE_BLANK)) {
                cargaMasivaPropuestaHelper.validaUnidadAdministrativa(BigDecimal.ZERO, cargaMasivaPropuestasDTO);
            } else {
                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    cargaMasivaPropuestaHelper.validaUnidadAdministrativa(new BigDecimal(cell.getNumericCellValue()),
                            cargaMasivaPropuestasDTO);
                } else {
                    cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
                }
            }

            llenarErrorLista(row.getRowNum(), Constantes.UNIDAD_ADMINISTRATIVA,
                    cargaMasivaPropuestasDTO.getDescripcionError(), listaErrores);

            // Valida Metodo
            cell = row.getCell(Constantes.METODO);
            cargaMasivaPropuestasDTO.setCell(Constantes.METODO);
            if (cell != null && cell.getCellType() == Cell.CELL_TYPE_STRING) {
                cargaMasivaPropuestaHelper.validaMetodo(cell.getStringCellValue(), cargaMasivaPropuestasDTO,
                        listaMetodo);
            } else {
                cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
                cargaMasivaPropuestasDTO.setValida(false);
            }
            llenarErrorLista(row.getRowNum(), Constantes.METODO, cargaMasivaPropuestasDTO.getDescripcionError(),
                    listaErrores);
            // Valida Tipo Revision
            cell = row.getCell(Constantes.TIPO_REVISION);
            if (cell != null && cell.getCellType() == Cell.CELL_TYPE_STRING) {
                cargaMasivaPropuestasDTO.setCell(Constantes.TIPO_REVISION);
                cargaMasivaPropuestaHelper.validaTipoRevision(cell.getStringCellValue(), cargaMasivaPropuestasDTO,
                        listaRevision);
            } else {
                cargaMasivaPropuestaHelper.campoOpcionalTipoRevision(cell, cargaMasivaPropuestasDTO);
            }
            llenarErrorLista(row.getRowNum(), Constantes.TIPO_REVISION, cargaMasivaPropuestasDTO.getDescripcionError(),
                    listaErrores);
            // Valida Fechas Constantes.FECHA_PRESENTACION_COMITE
            cargaMasivaPropuestasDTO.setDescripcionError("");
            cell = row.getCell(Constantes.PRIORIDAD);
            if (!valorCeldaPrioridad(cell)) {
                cargaMasivaPropuestasDTO.setCell(Constantes.PRIORIDAD);
                cargaMasivaPropuestasDTO.setValida(false);
            } else {
                cargaMasivaPropuestaHelper.validaFechaPresentacion(obtieneFechas(row), cargaMasivaPropuestasDTO,
                        cell.getNumericCellValue(), listaErrores, row.getRowNum(), listaPrioridad);
            }
            llenarErrorLista(row.getRowNum(), cargaMasivaPropuestasDTO.getCell(),
                    cargaMasivaPropuestasDTO.getDescripcionError(), listaErrores);
            // Valida Subprograma

            cell = row.getCell(Constantes.SUBPROGRAMA);
            cargaMasivaPropuestasDTO.setCell(Constantes.SUBPROGRAMA);
            if (cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                cargaMasivaPropuestaHelper.validaSubprograma(cell.getStringCellValue(), cargaMasivaPropuestasDTO,
                        listaSubprograma);
            } else {
                cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
                cargaMasivaPropuestasDTO.setValida(false);
            }
            llenarErrorLista(row.getRowNum(), Constantes.SUBPROGRAMA, cargaMasivaPropuestasDTO.getDescripcionError(),
                    listaErrores);

            // Valida TipoPropuesta
            cell = row.getCell(Constantes.TIPO_PROPUESTA);
            cargaMasivaPropuestasDTO.setCell(Constantes.TIPO_PROPUESTA);
            if (cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                cargaMasivaPropuestaHelper.validaTipoPropuesta(new BigDecimal(cell.getNumericCellValue()),
                        cargaMasivaPropuestasDTO, listaTipoPropuesta);
            } else {
                cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
                cargaMasivaPropuestasDTO.setValida(false);
            }
            llenarErrorLista(row.getRowNum(), Constantes.TIPO_PROPUESTA, cargaMasivaPropuestasDTO.getDescripcionError(),
                    listaErrores);

            // Valida CausaProgramacion
            cell = row.getCell(Constantes.CAUSA_PROGRAMACION);
            cargaMasivaPropuestasDTO.setCell(Constantes.CAUSA_PROGRAMACION);
            if (cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                cargaMasivaPropuestaHelper.validaProgramacion(new BigDecimal(cell.getNumericCellValue()),
                        cargaMasivaPropuestasDTO, listaCausaProgramacion);
            } else {
                cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
                cargaMasivaPropuestasDTO.setValida(false);
            }

            llenarErrorLista(row.getRowNum(), Constantes.CAUSA_PROGRAMACION,
                    cargaMasivaPropuestasDTO.getDescripcionError(), listaErrores);

            // Valida Sector
            cell = row.getCell(Constantes.SECTOR);
            cargaMasivaPropuestasDTO.setCell(Constantes.SECTOR);
            if (cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                cargaMasivaPropuestaHelper.validaSector(new BigDecimal(cell.getNumericCellValue()),
                        cargaMasivaPropuestasDTO, listaSector);
            } else {
                cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
                cargaMasivaPropuestasDTO.setValida(false);
            }
            llenarErrorLista(row.getRowNum(), Constantes.SECTOR, cargaMasivaPropuestasDTO.getDescripcionError(),
                    listaErrores);

            validaColumnasEstaticasPropuestasAux(row, cargaMasivaPropuestasDTO, folioCarga);
            validaColumnasDinamicasPropuestas(row, cargaMasivaPropuestasDTO);
        } else {
            return null;
        }
        return cargaMasivaPropuestasDTO;
    }

    private CargaMasivaPropuestasDTO validaColumnasEstaticasPropuestasAux(HSSFRow row,
            CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO, String folioCarga) {
        HSSFCell cell = row.getCell(Constantes.FECHA_INICIO_PERIODO);
        HSSFCell cellFechaFin = row.getCell(Constantes.FECHA_FIN_PERIODO);
        boolean validarFechaInicio = false;
        boolean validarFechaFinal = false;

        cargaMasivaPropuestasDTO.setDescripcionError("");
        if (cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
            cargaMasivaPropuestaHelper.obetenerFecha(cell.getStringCellValue(), cargaMasivaPropuestasDTO,
                    Constantes.FECHA_INICIO_PERIODO);
        } else {
            cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
        }
        llenarErrorLista(row.getRowNum(), Constantes.FECHA_INICIO_PERIODO,
                cargaMasivaPropuestasDTO.getDescripcionError(), listaErrores);
        if (cargaMasivaPropuestasDTO.getDescripcionError().equals("")) {
            validarFechaInicio = true;
        }

        cargaMasivaPropuestasDTO.setDescripcionError("");
        if (cellFechaFin != null && cellFechaFin.getCellType() == HSSFCell.CELL_TYPE_STRING) {
            cargaMasivaPropuestaHelper.obetenerFecha(cellFechaFin.getStringCellValue(), cargaMasivaPropuestasDTO,
                    Constantes.FECHA_FIN_PERIODO);
        } else {
            cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
        }
        llenarErrorLista(row.getRowNum(), Constantes.FECHA_FIN_PERIODO, cargaMasivaPropuestasDTO.getDescripcionError(),
                listaErrores);
        if (cargaMasivaPropuestasDTO.getDescripcionError().equals("")) {
            validarFechaFinal = true;
        }

        if (validarFechaInicio && validarFechaFinal) {
            cargaMasivaPropuestasDTO.setDescripcionError("");
            cargaMasivaPropuestaHelper.validaPeriodoPropuesto(valorCelda(cell), valorCelda(cellFechaFin),
                    cargaMasivaPropuestasDTO);
            llenarErrorLista(row.getRowNum(), cargaMasivaPropuestasDTO.getCell(),
                    cargaMasivaPropuestasDTO.getDescripcionError(), listaErrores);
        }
        cargaMasivaPropuestasDTO.setDescripcionError("");
        cell = row.getCell(Constantes.PRIORIDAD);
        if (cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
            cargaMasivaPropuestasDTO.setCell(Constantes.PRIORIDAD);
            cargaMasivaPropuestaHelper.validaPrioridadSugerida(cell.getNumericCellValue(), cargaMasivaPropuestasDTO,
                    listaPrioridad);
        } else {
            cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
        }

        llenarErrorLista(row.getRowNum(), Constantes.PRIORIDAD, cargaMasivaPropuestasDTO.getDescripcionError(),
                listaErrores);

        cargaMasivaPropuestasDTO.setDescripcionError("");
        cell = row.getCell(Constantes.DOCUMENTOS);
        cargaMasivaPropuestasDTO.setCell(Constantes.DOCUMENTOS);
        if (null != cell && cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
            List<String> archivosList = Arrays.asList(Pattern.compile("\\|").split(cell.getStringCellValue()));
            cargaMasivaPropuestasDTO.setValida(
                    cargaMasivaPropuestaHelper.validaDocumentos(archivosList, cargaMasivaPropuestasDTO, folioCarga));
        } else {
            cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
            cargaMasivaPropuestasDTO.setValida(false);
        }
        llenarErrorLista(row.getRowNum(), Constantes.DOCUMENTOS, cargaMasivaPropuestasDTO.getDescripcionError(),
                listaErrores);

        return cargaMasivaPropuestasDTO;
    }

    private void llenarErrorLista(int fila, int columna, String descripcion, List<CargaMasivaPropuestasDTO> errores) {
        if (!descripcion.equals("")) {
            CargaMasivaPropuestasDTO cargaMasivaError = new CargaMasivaPropuestasDTO();
            cargaMasivaError.setCell(columna);
            cargaMasivaError.setRow(fila);
            cargaMasivaError.setDescripcionError(descripcion);
            errores.add(cargaMasivaError);
        }
    }

    @Override
    public List<FececMetodo> obtenerListaMetodo() {
        return feceaMetodoDao.findAll();
    }

    @Override
    public List<FececRevision> obtenerListaRevision() {
        return fececRevisionDao.findAll();
    }

    @Override
    public List<FececSubprograma> obtenerListaSubPrograma(BigDecimal idGeneral) {
        return fececSubprogramaDao.findActivos(idGeneral );
    }

    @Override
    public List<FececTipoPropuesta> obtenerListaTipoPropuesta() {
        return fececTipoPropuestaDao.findAll();
    }

    @Override
    public List<FececCausaProgramacion> obtenerListaCausaProgramacion() {
        return fececCausaProgramacionDao.findActivos();
    }

    @Override
    public List<FececSector> obtenerListaSector(BigDecimal idGeneral) {
        return fececSectorDao.findActivos(idGeneral);
    }

    @Override
    public List<FececTipoImpuesto> obtenerListaImpuesto() {
        return fececTipoImpuestoDao.findAll();
    }

    @Override
    public List<FececPrioridad> obtenerListaPrioridad() {
        return fececPrioridadDao.findActivos();
    }

    @Override
    public Map<BigDecimal, List<FececConcepto>> obtenerListaConcepto(List<FececTipoImpuesto> listaTipoImpuesto) {
        Map<BigDecimal, List<FececConcepto>> relacionImpuestoConcepto = new HashMap<BigDecimal, List<FececConcepto>>();
        if (listaTipoImpuesto != null && !listaTipoImpuesto.isEmpty()) {
            for (FececTipoImpuesto tipoImpuesto : listaTipoImpuesto) {
                relacionImpuestoConcepto.put(tipoImpuesto.getIdTipoImpuesto(),
                        fececConceptoDao.findRelacionImpuestoConcepto(tipoImpuesto.getIdTipoImpuesto()));
            }
        }
        return relacionImpuestoConcepto;
    }

    private boolean valorCeldaPrioridad(HSSFCell celda) {

        boolean valida = false;

        if (celda != null) {
            switch (celda.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                valida = false;
                break;
            case Cell.CELL_TYPE_STRING:
                valida = false;
                break;
            case Cell.CELL_TYPE_BLANK:
                valida = false;
                break;
            case Cell.CELL_TYPE_NUMERIC:
                valida = true;
                break;
            default:
                valida = false;
                break;
            }
        }

        return valida;
    }

    public static String valorCelda(HSSFCell cell) {
        String valorCelda = "";
        if (cell != null) {
            switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                valorCelda = "";
                break;
            case Cell.CELL_TYPE_NUMERIC:
                valorCelda = "";
                break;
            case Cell.CELL_TYPE_STRING:
                valorCelda = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                valorCelda = "";
                break;
            case Cell.CELL_TYPE_ERROR:
                valorCelda = "";
                break;
            case Cell.CELL_TYPE_FORMULA:
                valorCelda = "";
                break;
            default:
                valorCelda = "";
                break;
            }
        }
        return valorCelda;
    }

    public static Map<String, String> obtieneFechas(HSSFRow row) {
        Map<String, String> fechas = new HashMap<String, String>();
        fechas.put(String.valueOf(Constantes.FECHA_PRESENTACION_COMITE),
                valorCelda(row.getCell(Constantes.FECHA_PRESENTACION_COMITE)));
        fechas.put(String.valueOf(Constantes.FECHA_INFORME_COMITE),
                valorCelda(row.getCell(Constantes.FECHA_INFORME_COMITE)));
        fechas.put(String.valueOf(Constantes.FECHA_PRESENTACION_COMITE_REGIONAL),
                valorCelda(row.getCell(Constantes.FECHA_PRESENTACION_COMITE_REGIONAL)));
        fechas.put(String.valueOf(Constantes.FECHA_INFORME_COMITE_REGIONAL),
                valorCelda(row.getCell(Constantes.FECHA_INFORME_COMITE_REGIONAL)));
        return fechas;
    }

    private CargaMasivaPropuestasDTO validaColumnasDinamicasPropuestas(HSSFRow row,
            CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO) {

        validaImpuesto(row, cargaMasivaPropuestasDTO, listaTipoImpuesto, listaConcepto);

        BigDecimal presuntivo = Constantes.BIG_DECIMAL_CERO;
        if (cargaMasivaPropuestasDTO.getFecetImpuestos().size() > 1) {
            for (FecetImpuesto imp : cargaMasivaPropuestasDTO.getFecetImpuestos()) {
                presuntivo = presuntivo.add(imp.getMonto());
            }
        } else {
            presuntivo = cargaMasivaPropuestasDTO.getFecetImpuestos().size() > 0
                    ? cargaMasivaPropuestasDTO.getFecetImpuestos().get(0).getMonto() : Constantes.BIG_DECIMAL_CERO;
        }
        if (presuntivo != Constantes.BIG_DECIMAL_CERO) {
            DecimalFormat decimalFormat = new DecimalFormat("'$' ###,###,###.00");
            cargaMasivaPropuestasDTO.setPresuntivaFormat(decimalFormat.format(presuntivo));
            cargaMasivaPropuestasDTO.setPresuntivo(presuntivo);
        }

        return cargaMasivaPropuestasDTO;
    }

    public CargaMasivaPropuestasDTO validaImpuesto(HSSFRow row, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO,
            List<FececTipoImpuesto> listaTipoImpuesto, Map<BigDecimal, List<FececConcepto>> listaConcepto) {
        FecetImpuesto fecetImpuesto;
        List<FecetImpuesto> listaImpuestos = new ArrayList<FecetImpuesto>();
        int contInicial = Constantes.ENTERO_DIECISEIS;
        int contFinal = row.getLastCellNum();

        while (contInicial <= contFinal) {
            HSSFCell impuesto = row.getCell(contInicial);
            HSSFCell concepto = row.getCell(contInicial + 1);
            HSSFCell monto = row.getCell(contInicial + 2);

            boolean[] condiciones = new boolean[] {
                    (impuesto != null && impuesto.getCellType() == HSSFCell.CELL_TYPE_STRING),
                    (monto != null && monto.getCellType() == HSSFCell.CELL_TYPE_NUMERIC),
                    (concepto != null && concepto.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) };

            if (ValidacionParametrosUtil.seCumplenTodasLasCondicion(condiciones)) {
                cargaMasivaPropuestasDTO.setDescripcionError("");
                FececTipoImpuesto fececTipoImpuesto = cargaMasivaPropuestaHelper
                        .validaImpuesto(impuesto.getStringCellValue(), listaTipoImpuesto, cargaMasivaPropuestasDTO);
                llenarErrorLista(row.getRowNum(), contInicial, cargaMasivaPropuestasDTO.getDescripcionError(),
                        listaErrores);
                cargaMasivaPropuestasDTO.setDescripcionError("");
                FececConcepto fececConcepto = cargaMasivaPropuestaHelper.validaConcepto(
                        fececTipoImpuesto.getIdTipoImpuesto(), new BigDecimal(concepto.getNumericCellValue()),
                        listaConcepto, cargaMasivaPropuestasDTO);
                llenarErrorLista(row.getRowNum(), contInicial + 1, cargaMasivaPropuestasDTO.getDescripcionError(),
                        listaErrores);
                String montoStrt = cargaMasivaPropuestaHelper.fmt(monto.getNumericCellValue());
                if (isNumeric(montoStrt, cargaMasivaPropuestasDTO, impuesto)) {
                    if (validaImpuestoAplicable(impuesto, monto, cargaMasivaPropuestasDTO, contInicial)) {
                        fecetImpuesto = new FecetImpuesto();
                        fecetImpuesto.setIdTipoImpuesto(fececTipoImpuesto.getIdTipoImpuesto());
                        fecetImpuesto.setMonto(BigDecimal.valueOf(monto.getNumericCellValue()));
                        fecetImpuesto.setIdConcepto(new BigDecimal(fececConcepto.getIdConcepto()));
                        cargaMasivaPropuestaHelper.validaImpuestoNA(listaImpuestos, fecetImpuesto,
                                cargaMasivaPropuestasDTO);

                        if (cargaMasivaPropuestasDTO.getDescripcionAddImpuesto().equals("Impuesto correcto")) {
                            if (cargaMasivaPropuestaHelper.validaConceptosRepetidos(listaImpuestos, fecetImpuesto)) {
                                cargaMasivaPropuestasDTO.setCell(contInicial + 1);
                                cargaMasivaPropuestasDTO.setDescripcionError(
                                        "No se puede adjuntar un impuesto duplicado, favor de verificar");
                                llenarErrorLista(row.getRowNum(), cargaMasivaPropuestasDTO.getCell(),
                                        cargaMasivaPropuestasDTO.getDescripcionError(), listaErrores);
                            }
                            listaImpuestos.add(fecetImpuesto);
                        } else {
                            cargaMasivaPropuestasDTO.setCell(contInicial);
                            llenarErrorLista(row.getRowNum(), cargaMasivaPropuestasDTO.getCell(),
                                    cargaMasivaPropuestasDTO.getDescripcionError(), listaErrores);
                        }
                    } else {
                        llenarErrorLista(row.getRowNum(), cargaMasivaPropuestasDTO.getCell(),
                                cargaMasivaPropuestasDTO.getDescripcionError(), listaErrores);
                    }
                } else {
                    cargaMasivaPropuestasDTO.setCell(contInicial + 2);
                    llenarErrorLista(row.getRowNum(), cargaMasivaPropuestasDTO.getCell(),
                            cargaMasivaPropuestasDTO.getDescripcionError(), listaErrores);
                }
            } else {
                boolean[] condicionesBlancos = new boolean[] {
                        (impuesto == null || impuesto.getCellType() == HSSFCell.CELL_TYPE_BLANK),
                        (monto == null || monto.getCellType() == HSSFCell.CELL_TYPE_BLANK),
                        (concepto == null || concepto.getCellType() == HSSFCell.CELL_TYPE_BLANK) };

                if (!ValidacionParametrosUtil.seCumplenTodasLasCondicion(condicionesBlancos)) {
                    validarImpuesto(row, cargaMasivaPropuestasDTO, impuesto, concepto, monto, contInicial);
                }
            }
            contInicial = contInicial + Constantes.ENTERO_TRES;
        }

        cargaMasivaPropuestasDTO.setFecetImpuestos(listaImpuestos);
        return cargaMasivaPropuestasDTO;
    }

    private CargaMasivaPropuestasDTO validarImpuesto(HSSFRow row, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO,
            HSSFCell impuesto, HSSFCell concepto, HSSFCell monto, int contInicial) {
        cargaMasivaPropuestasDTO.setDescripcionError("");
        FececTipoImpuesto fececTipoImpuesto = null;
        if ((impuesto == null || impuesto.getCellType() == HSSFCell.CELL_TYPE_BLANK)) {
            cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
            cargaMasivaPropuestasDTO.setCell(contInicial);
        } else {
            if (impuesto.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                fececTipoImpuesto = cargaMasivaPropuestaHelper.validaImpuesto(impuesto.getStringCellValue(),
                        listaTipoImpuesto, cargaMasivaPropuestasDTO);
            } else {
                cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
                cargaMasivaPropuestasDTO.setCell(contInicial);
            }
        }
        llenarErrorLista(row.getRowNum(), cargaMasivaPropuestasDTO.getCell(),
                cargaMasivaPropuestasDTO.getDescripcionError(), listaErrores);
        cargaMasivaPropuestasDTO.setDescripcionError("");
        if ((concepto == null || concepto.getCellType() == HSSFCell.CELL_TYPE_BLANK)) {
            cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
            cargaMasivaPropuestasDTO.setCell(contInicial + 1);
        } else {
            if ((impuesto != null && impuesto.getCellType() == HSSFCell.CELL_TYPE_STRING)) {
                cargaMasivaPropuestaHelper.validaConcepto(fececTipoImpuesto.getIdTipoImpuesto(),
                        new BigDecimal(concepto.getNumericCellValue()), listaConcepto, cargaMasivaPropuestasDTO);
            } else {
                cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
                cargaMasivaPropuestasDTO.setCell(contInicial + 1);
            }

        }
        llenarErrorLista(row.getRowNum(), cargaMasivaPropuestasDTO.getCell(),
                cargaMasivaPropuestasDTO.getDescripcionError(), listaErrores);
        cargaMasivaPropuestasDTO.setDescripcionError("");
        if ((monto == null || monto.getCellType() == HSSFCell.CELL_TYPE_BLANK)) {
            cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
            cargaMasivaPropuestasDTO.setCell(contInicial + 2);
        } else {
            if ((monto.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)) {
                String montoStrt = cargaMasivaPropuestaHelper.fmt(monto.getNumericCellValue());
                isNumeric(montoStrt, cargaMasivaPropuestasDTO, impuesto);
                cargaMasivaPropuestasDTO.setCell(contInicial + 2);
            } else {
                cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
                cargaMasivaPropuestasDTO.setCell(contInicial + 2);
            }
        }
        llenarErrorLista(row.getRowNum(), cargaMasivaPropuestasDTO.getCell(),
                cargaMasivaPropuestasDTO.getDescripcionError(), listaErrores);
        return cargaMasivaPropuestasDTO;
    }

    private boolean isNumeric(String cadena, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO, HSSFCell impuesto) {
        boolean isMontoValido = false;
        cargaMasivaPropuestasDTO.setDescripcionError("");
        if (!impuesto.getStringCellValue().equals("N/A")) {
            try {
                Double monto = Double.parseDouble(cadena);
                if (monto <= 0) {
                    cargaMasivaPropuestasDTO.setDescripcionError("El monto del impuesto debe ser mayor a cero");
                } else {
                    isMontoValido = true;
                }
            } catch (NumberFormatException nfe) {
                cargaMasivaPropuestasDTO.setDescripcionError("El monto del impuesto únicamente permitirá enteros");
            }
        } else {
            isMontoValido = true;
        }

        return isMontoValido;
    }

    private boolean validaImpuestoAplicable(HSSFCell impuesto, HSSFCell monto,
            CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO, int contInicial) {

        boolean isImpuestoAplicable = false;
        cargaMasivaPropuestasDTO.setDescripcionError("");
        if (monto != null && impuesto != null) {
            String montoStr = cargaMasivaPropuestaHelper.fmt(monto.getNumericCellValue());
            Double montoTmp = Double.parseDouble(montoStr);
            String impuestoTmp = impuesto.getStringCellValue();

            if (!impuestoTmp.equals("N/A")) {
                if (montoTmp > 0) {
                    isImpuestoAplicable = true;
                } else {
                    cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
                    cargaMasivaPropuestasDTO.setCell(contInicial + 2);
                    isImpuestoAplicable = false;
                }
            } else {
                if (montoTmp == 0) {
                    isImpuestoAplicable = true;
                } else {
                    cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
                    cargaMasivaPropuestasDTO.setCell(contInicial + 2);
                    isImpuestoAplicable = false;
                }
            }
        }

        return isImpuestoAplicable;
    }

}
