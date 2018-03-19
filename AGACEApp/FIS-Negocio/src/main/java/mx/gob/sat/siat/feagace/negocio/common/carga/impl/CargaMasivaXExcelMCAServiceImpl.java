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
import mx.gob.sat.siat.feagace.modelo.dto.common.DetalleEmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.CargaMasivaPropuestasDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAraceEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.carga.CargaMasivaXExcelMCAService;
import mx.gob.sat.siat.feagace.negocio.helper.ValidaColumnasMCAHelper;
import mx.gob.sat.siat.feagace.negocio.propuestas.carga.CargaMasivaPropuestasService;
import mx.gob.sat.siat.feagace.negocio.util.carga.constantes.TipoListaCargaPropuestaEnum;
import mx.gob.sat.siat.feagace.negocio.util.carga.exception.CargaRegistroXExcelException;
import mx.gob.sat.siat.feagace.negocio.util.comparator.CargaMasivaComparador;

@Service("cargaMasivaXExcelMCAService")
public class CargaMasivaXExcelMCAServiceImpl extends BaseBusinessServices implements CargaMasivaXExcelMCAService {

    private static final long serialVersionUID = 4474365902434425330L;
    
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
    private CargaMasivaPropuestasService cargaMasivaPropuestasService;
    @Autowired
    private ValidaColumnasMCAHelper validaColumnasMCAHelper;
    @Autowired
    private transient FececPrioridadDao fececPrioridadDao;
    
    private static final String MSJ_CAMPO_NO_VALIDO = "El campo no es un valor valido";
    
    private static final String MSJ_REGISTRO_DUPLICADO = "El registro esta duplicado en el formato de carga";

    @SuppressWarnings("unchecked")
    @Override
    public Map<TipoListaCargaPropuestaEnum, List<CargaMasivaPropuestasDTO>> validarMCADeExcel(InputStream archivoExcel,
            EmpleadoDTO empleado, String folioCargaDoc, String rfcSesion) throws CargaRegistroXExcelException {

        List<CargaMasivaPropuestasDTO> regsitrosCorrectos = new ArrayList<CargaMasivaPropuestasDTO>();
        List<CargaMasivaPropuestasDTO> regsitrosIncorrectos = new ArrayList<CargaMasivaPropuestasDTO>();
        Map<TipoListaCargaPropuestaEnum, List<CargaMasivaPropuestasDTO>> listasValidacion = new HashMap<TipoListaCargaPropuestaEnum, List<CargaMasivaPropuestasDTO>>();

        try {
            HSSFWorkbook workbook;
            CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO = new CargaMasivaPropuestasDTO();

            cargaMasivaPropuestasDTO.setFecetPropuesta(new FecetPropuesta());
            cargaMasivaPropuestasDTO.getFecetPropuesta().setEmpleadoDto(empleado);
            Map<Integer, Object> catalogos = cargaListaCatalogos();
            Map<FececTipoImpuesto, List<FececConcepto>> conceptosImpuestos = obtenerListaConcepto(
                    (List<FececTipoImpuesto>) catalogos.get(Constantes.ENTERO_SEIS));

            workbook = (HSSFWorkbook) initLayoutXLS(archivoExcel);
            Iterator<Row> rows = cargaMasivaPropuestasService.obtenerHojaExcel(workbook).rowIterator();
            rows.next();

            while (rows.hasNext()) {
                CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO1 = new CargaMasivaPropuestasDTO();
                List<CargaMasivaPropuestasDTO> lstErrores = new ArrayList<CargaMasivaPropuestasDTO>();

                if (empleado != null) {
                    cargaMasivaPropuestasDTO1.setFecetPropuesta(new FecetPropuesta());
                    cargaMasivaPropuestasDTO1.getFecetPropuesta().setEmpleadoDto(empleado);
                }

                HSSFRow row = (HSSFRow) rows.next();
                cargaMasivaPropuestasDTO1.setRow(row.getRowNum());

                if (!validaColumnasVacias(row)) {
                    validaColumnasEstaticasMCA(row, cargaMasivaPropuestasDTO1, catalogos, folioCargaDoc, lstErrores);
                    validaColumnasDinamicasMCA(row, cargaMasivaPropuestasDTO1,
                            conceptosImpuestos, catalogos, lstErrores);

                    if (lstErrores == null || lstErrores.isEmpty()) {
                        boolean isRegistroDuplicado = false;

                        for (CargaMasivaPropuestasDTO prop : regsitrosCorrectos) {
                            CargaMasivaComparador registro1 = new CargaMasivaComparador();
                            if (registro1.compare(cargaMasivaPropuestasDTO1, prop) == 0) {
                              cargaMasivaPropuestasDTO1.setCell(Constantes.RFC_CONTIBUYENTE);
                              cargaMasivaPropuestasDTO1.setValida(false);
                              cargaMasivaPropuestasDTO1.setDescripcionError(MSJ_REGISTRO_DUPLICADO);
                              lstErrores.add(cargaMasivaPropuestasDTO1);
                              isRegistroDuplicado = true;
                              break;
                            }
                        }

                        if (!isRegistroDuplicado) {
                            cargaMasivaPropuestasDTO1.setCell(Constantes.RFC_CONTIBUYENTE);
                            cargaMasivaPropuestasService.validaAntecedentes(cargaMasivaPropuestasDTO1);
                        }

                        if (lstErrores.isEmpty()) {

                            cargaMasivaPropuestasDTO1.getFecetPropuesta().setRfcCreacion(rfcSesion);
                            cargaMasivaPropuestasDTO1.getFecetPropuesta()
                                    .setOrigenPropuestaId(new BigDecimal(Constantes.ENTERO_TRES));
                            regsitrosCorrectos.add(cargaMasivaPropuestasDTO1);
                        } else {
                            regsitrosIncorrectos.addAll(lstErrores);
                        }
                    } else {
                        regsitrosIncorrectos.addAll(lstErrores);
                    }  
                }
                
            }

            listasValidacion.put(TipoListaCargaPropuestaEnum.PROPUESTAS_CARGADAS_INCORRECTAMENTE, regsitrosIncorrectos);
            listasValidacion.put(TipoListaCargaPropuestaEnum.PROPUESTAS_CARGADAS_CORRECTAMENTE, regsitrosCorrectos);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return listasValidacion;
    }
    
    @Override
    public List<FececMetodo> obtenerListaMetodo(){
        return feceaMetodoDao.findAll();
    }
    @Override
    public List<FececRevision> obtenerListaRevision(){
        return fececRevisionDao.findAll();      
    }
    @Override
    public List<FececSubprograma> obtenerListaSubPrograma(BigDecimal idGeneral){        
        return fececSubprogramaDao.findActivos(idGeneral);
    }
    @Override
    public List<FececTipoPropuesta> obtenerListaTipoPropuesta(){        
        return fececTipoPropuestaDao.findAll();
    }
    @Override
    public List<FececCausaProgramacion> obtenerListaCausaProgramacion(){        
        return fececCausaProgramacionDao.findActivos();
    }
    @Override
    public List<FececSector> obtenerListaSector(BigDecimal idGeneral){      
        return fececSectorDao.findActivos(idGeneral);
    }

    @Override
    public List<FececTipoImpuesto> obtenerListaImpuesto(){      
        return fececTipoImpuestoDao.findAll();
    }
    
    @Override
    public Map< FececTipoImpuesto,List<FececConcepto>> obtenerListaConcepto(List<FececTipoImpuesto> listaTipoImpuesto){     
        Map< FececTipoImpuesto,List<FececConcepto>> relacionImpuestoConcepto = new HashMap < FececTipoImpuesto,List<FececConcepto>>();
        
        if(listaTipoImpuesto!=null && !listaTipoImpuesto.isEmpty()){
            for( FececTipoImpuesto tipoImpuesto : listaTipoImpuesto ){
                List<FececConcepto> listaConcepto =  fececConceptoDao.findRelacionImpuestoConcepto(tipoImpuesto.getIdTipoImpuesto());               
                relacionImpuestoConcepto.put(tipoImpuesto, listaConcepto);
            }
        }
        return relacionImpuestoConcepto;
    }
    
    public Workbook initLayoutXLS(InputStream layoutXLS) throws IOException,  CargaRegistroXExcelException{
        
        try {
            if(layoutXLS!=null){
                return new HSSFWorkbook(layoutXLS);
            }            
            throw new CargaRegistroXExcelException(ERROR_CARGA_ARCHIVO);            
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new CargaRegistroXExcelException(ERROR_CARGA_ARCHIVO,e);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new CargaRegistroXExcelException(ERROR_CARGA_ARCHIVO,e);
        }
    }
    
    private void validaColumnasEstaticasMCA(HSSFRow row, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO, Map<Integer, Object> catalogos, String folioCargaDoc, List<CargaMasivaPropuestasDTO> lstErrores) {
        
        try {
            String cellValue = "";
            Double cellValueNumeric = 0.0;
            
            HSSFCell cell = row.getCell(Constantes.RFC_CONTIBUYENTE);
            cargaMasivaPropuestasDTO.setCell(Constantes.RFC_CONTIBUYENTE);
            if (cell != null && cell.getCellType() == Cell.CELL_TYPE_STRING) {
                cargaMasivaPropuestasDTO.setRfcContribuyente(cell.getStringCellValue().trim().toUpperCase());
                cargaMasivaPropuestasService.validaRFC(cargaMasivaPropuestasDTO);
            } else {
                cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
                cargaMasivaPropuestasDTO.setValida(false);
            }
            llenarListaErrores(lstErrores, cargaMasivaPropuestasDTO);
            
            cell = row.getCell(Constantes.UNIDAD_ADMINISTRATIVA);
            cargaMasivaPropuestasDTO.setCell(Constantes.UNIDAD_ADMINISTRATIVA);
            if (cell != null && cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                cellValueNumeric = row.getCell(Constantes.UNIDAD_ADMINISTRATIVA).getNumericCellValue();
                if (!validaUnidadEmpleado(cargaMasivaPropuestasDTO)) {
                    validaColumnasMCAHelper.validaUnidadAdministrativa(new BigDecimal(cellValueNumeric), cargaMasivaPropuestasDTO);
                } else {
                    if (validaUnidadEmpleadoCorrecta(cargaMasivaPropuestasDTO, new BigDecimal(cell.getNumericCellValue()))) {
                        validaColumnasMCAHelper.validaUnidadAdministrativa(BigDecimal.ZERO, cargaMasivaPropuestasDTO); 
                    } else {
                        cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
                        cargaMasivaPropuestasDTO.setValida(false);  
                    }
                }
                
            }  else {
                if (cell != null && cell.getCellType() == Cell.CELL_TYPE_STRING) {
                    cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
                    cargaMasivaPropuestasDTO.setValida(false);
                } else {
                    if (validaUnidadEmpleado(cargaMasivaPropuestasDTO)) {
                        validaColumnasMCAHelper.validaUnidadAdministrativa(BigDecimal.ZERO, cargaMasivaPropuestasDTO);
                    } else {
                        cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
                        cargaMasivaPropuestasDTO.setValida(false);   
                    }    
                }
            }
            llenarListaErrores(lstErrores, cargaMasivaPropuestasDTO);

            cell = row.getCell(Constantes.METODO);
            cargaMasivaPropuestasDTO.setCell(Constantes.METODO);
            if (cell != null && cell.getCellType() == Cell.CELL_TYPE_STRING) {
                cellValue = row.getCell(Constantes.METODO).getStringCellValue();
                validaColumnasMCAHelper.validaMetodoCI(cellValue, cargaMasivaPropuestasDTO, catalogos);
            } else {
                cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
                cargaMasivaPropuestasDTO.setValida(false);
            }
            llenarListaErrores(lstErrores, cargaMasivaPropuestasDTO);

            if (cargaMasivaPropuestasDTO.getUnidadAdministrativa() != null) {
                validaColumnasMCAHelper.validaFechaPresentacionCI(obtieneFechasCI(row), cargaMasivaPropuestasDTO);
                llenarListaErrores(lstErrores, cargaMasivaPropuestasDTO);
            }
            
            cell = row.getCell(Constantes.SUBPROGRAMA_CI);
            cargaMasivaPropuestasDTO.setCell(Constantes.SUBPROGRAMA_CI);
            if (cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                cellValue = row.getCell(Constantes.SUBPROGRAMA_CI).getStringCellValue().trim();
                cargaMasivaPropuestasDTO.setValida(validaColumnasMCAHelper
                        .validaSubprograma(cellValue, cargaMasivaPropuestasDTO, catalogos));
            } else {
                cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
                cargaMasivaPropuestasDTO.setValida(false);
            }
            llenarListaErrores(lstErrores, cargaMasivaPropuestasDTO);

            cell = row.getCell(Constantes.SECTOR_CI);
            cargaMasivaPropuestasDTO.setCell(Constantes.SECTOR_CI);
            if (cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                cellValueNumeric = row.getCell(Constantes.SECTOR_CI).getNumericCellValue();
                cargaMasivaPropuestasDTO.setValida(validaColumnasMCAHelper.validaSector(new BigDecimal(cellValueNumeric),
                        cargaMasivaPropuestasDTO, catalogos));
            } else {
                cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
                cargaMasivaPropuestasDTO.setValida(false);
            }
            llenarListaErrores(lstErrores, cargaMasivaPropuestasDTO);
                
            
            cell = row.getCell(Constantes.TIPO_PROPUESTA_CI);
            cargaMasivaPropuestasDTO.setCell(Constantes.TIPO_PROPUESTA_CI);
            if (cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                cellValueNumeric = row.getCell(Constantes.TIPO_PROPUESTA_CI).getNumericCellValue();
                cargaMasivaPropuestasDTO.setValida(validaColumnasMCAHelper
                        .validaTipoPropuesta(new BigDecimal(cellValueNumeric), cargaMasivaPropuestasDTO, catalogos));
            } else {
                cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
                cargaMasivaPropuestasDTO.setValida(false);
            }
            llenarListaErrores(lstErrores, cargaMasivaPropuestasDTO);
            
            cell = row.getCell(Constantes.CAUSA_PROGRAMACION_CI);
            cargaMasivaPropuestasDTO.setCell(Constantes.CAUSA_PROGRAMACION_CI);
            if (cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                cellValueNumeric = row.getCell(Constantes.CAUSA_PROGRAMACION_CI).getNumericCellValue();
                cargaMasivaPropuestasDTO.setValida(validaColumnasMCAHelper
                        .validaCausaProgramacion(new BigDecimal(cellValueNumeric), cargaMasivaPropuestasDTO, catalogos));
            } else {
                cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
                cargaMasivaPropuestasDTO.setValida(false);
            }
            llenarListaErrores(lstErrores, cargaMasivaPropuestasDTO);

            validaColumnasEstaticasPropuestasAuxCI(row, cargaMasivaPropuestasDTO, folioCargaDoc, lstErrores, catalogos);
            
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }  
    }
    
    @SuppressWarnings("unchecked")
    private CargaMasivaPropuestasDTO validaColumnasEstaticasPropuestasAuxCI(HSSFRow row,
            CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO, String folioCargaDoc, List<CargaMasivaPropuestasDTO> lstErrores, Map<Integer, Object> catalogos) {
        List<CargaMasivaPropuestasDTO> lstErroresTmp = new ArrayList<CargaMasivaPropuestasDTO>();
        HSSFCell cell = row.getCell(Constantes.FECHA_INICIO_PERIODO_CI);
        HSSFCell cellFechaFin = row.getCell(Constantes.FECHA_FIN_PERIODO_CI);
        List<FececPrioridad> lstPrioridad = (List<FececPrioridad>) catalogos.get(Constantes.ENTERO_SIETE);

        validaColumnasMCAHelper.validaPeriodoPropuestoCI(valorCelda(cell), valorCelda(cellFechaFin), cargaMasivaPropuestasDTO, lstErroresTmp);
        if (lstErroresTmp != null && !lstErroresTmp.isEmpty()) {
            lstErrores.addAll(lstErroresTmp);   
        }
        
        cell = row.getCell(Constantes.PRIORIDAD_CI);
        cargaMasivaPropuestasDTO.setCell(Constantes.PRIORIDAD_CI);
        if (cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_STRING
                || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
            cargaMasivaPropuestasDTO.setValida(false);
            cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
        } else {
            validaColumnasMCAHelper.validaPrioridadSugerida(cell.getNumericCellValue(), cargaMasivaPropuestasDTO, lstPrioridad);
        }
        llenarListaErrores(lstErrores, cargaMasivaPropuestasDTO);
        
        cell = row.getCell(Constantes.DOCUMENTOS_CI);
        cargaMasivaPropuestasDTO.setCell(Constantes.DOCUMENTOS_CI);
        if (null != cell && cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
            List<String> archivosList = Arrays.asList(Pattern.compile("\\|").split(cell.getStringCellValue()));
            cargaMasivaPropuestasDTO.setValida(
                    validaColumnasMCAHelper.validaDocumentosCI(archivosList, cargaMasivaPropuestasDTO, folioCargaDoc));
        } else {
            cargaMasivaPropuestasDTO.setValida(false);
            cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
        }
        llenarListaErrores(lstErrores, cargaMasivaPropuestasDTO);
        
        return cargaMasivaPropuestasDTO;
    }
    
    @SuppressWarnings("unchecked")
    private void validaColumnasDinamicasMCA(HSSFRow row, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO
            , Map<FececTipoImpuesto, List<FececConcepto>> conceptosImpuestos, Map<Integer, Object> catalogos, List<CargaMasivaPropuestasDTO> lstErrores) {
        
        List<FececTipoImpuesto> lstImpuestos = (List<FececTipoImpuesto>) catalogos.get(Constantes.ENTERO_SEIS);
        List<CargaMasivaPropuestasDTO> lstErroresImpuestos = new ArrayList<CargaMasivaPropuestasDTO>();
        validaColumnasMCAHelper.validaImpuestoCI(row, cargaMasivaPropuestasDTO, conceptosImpuestos, lstImpuestos,
                lstErroresImpuestos);

        if (lstErroresImpuestos != null && !lstErroresImpuestos.isEmpty()) {
            lstErrores.addAll(lstErroresImpuestos);
        } else {
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
        }
    }
        
    private static String valorCelda(HSSFCell cell) {
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
    
    private static Map<String, String> obtieneFechasCI(HSSFRow row) {
        Map<String, String> fechas = new HashMap<String, String>();
        fechas.put(String.valueOf(Constantes.FECHA_INFORME_COMITE_CI),
                valorCelda(row.getCell(Constantes.FECHA_INFORME_COMITE_CI)));
        fechas.put(String.valueOf(Constantes.FECHA_INFORME_COMITE_REGIONAL_CI),
                valorCelda(row.getCell(Constantes.FECHA_INFORME_COMITE_REGIONAL_CI)));
        return fechas;
    }
    
    private Map<Integer, Object> cargaListaCatalogos() {
        Map<Integer, Object> mapCatalogos = new HashMap<Integer, Object>();
        mapCatalogos.put(Constantes.ENTERO_UNO, obtenerListaMetodo());
        mapCatalogos.put(Constantes.ENTERO_DOS, obtenerListaSector(new BigDecimal(Constantes.ENTERO_DIECINUEVE)));
        mapCatalogos.put(Constantes.ENTERO_TRES, obtenerListaSubPrograma(new BigDecimal(Constantes.ENTERO_DIECINUEVE)));
        mapCatalogos.put(Constantes.ENTERO_CUATRO, obtenerListaCausaProgramacion());
        mapCatalogos.put(Constantes.ENTERO_CINCO, obtenerListaTipoPropuesta());
        mapCatalogos.put(Constantes.ENTERO_SEIS, obtenerListaImpuesto());
        mapCatalogos.put(Constantes.ENTERO_SIETE, fececPrioridadDao.findActivos());
        
        return mapCatalogos;
    }
    
    private void llenarListaErrores(List<CargaMasivaPropuestasDTO> lstErrores, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO) {
        
        if (!cargaMasivaPropuestasDTO.isValida()) {
            CargaMasivaPropuestasDTO errorTmp1 = new CargaMasivaPropuestasDTO();
            errorTmp1.setCell(cargaMasivaPropuestasDTO.getCell());
            errorTmp1.setRow(cargaMasivaPropuestasDTO.getRow());
            errorTmp1.setDescripcionError(cargaMasivaPropuestasDTO.getDescripcionError());
            lstErrores.add(errorTmp1);
        }
    }
    
    private boolean validaColumnasVacias(HSSFRow row) {
        
        int columnasOcupadas = row.getLastCellNum();
        int contInicial = Constantes.RFC_CONTIBUYENTE;
        int numeroColumnasLlenas = 0;
        
        while (contInicial < columnasOcupadas) {
            HSSFCell columna = row.getCell(contInicial);
            if (columna != null && columna.getCellType() != Cell.CELL_TYPE_BLANK) {
                numeroColumnasLlenas++;
            }
            contInicial++;
        }
        
        
        if (numeroColumnasLlenas > 0) {
            return false;
        }
        
        return true;
    }
    
    private boolean validaUnidadEmpleado(CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO) {
        
        List<DetalleEmpleadoDTO> detalleEmpleadoLogueado = cargaMasivaPropuestasDTO.getFecetPropuesta().getEmpleadoDto().getDetalleEmpleado();
        if (detalleEmpleadoLogueado != null && !detalleEmpleadoLogueado.isEmpty()) {
            Integer unidadEmpleado = detalleEmpleadoLogueado.get(0).getCentral().getIdArace();
            
            if (unidadEmpleado.longValue() != TipoAraceEnum.ACPPCE.getId()) {
                return true;
            }
        }
        return false;
    }
    
    private boolean validaUnidadEmpleadoCorrecta(CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO, BigDecimal idUnidadAdministrativa) {
        List<DetalleEmpleadoDTO> detalleEmpleadoLogueado = cargaMasivaPropuestasDTO.getFecetPropuesta().getEmpleadoDto().getDetalleEmpleado();
        if (detalleEmpleadoLogueado != null && !detalleEmpleadoLogueado.isEmpty()) {
            Integer unidadEmpleado = detalleEmpleadoLogueado.get(0).getCentral().getIdArace();
            
            if (unidadEmpleado.longValue() == idUnidadAdministrativa.intValue()) {
                return true;
            }
        }
        return false;
    }
}