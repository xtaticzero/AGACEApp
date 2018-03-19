package mx.gob.sat.siat.feagace.negocio.helper;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Component;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececCausaProgramacion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececConcepto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececPrioridad;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.CargaMasivaPropuestasDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAraceEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.ConstantesError;
import mx.gob.sat.siat.feagace.negocio.util.validacion.ValidacionParametrosUtil;

@Component
public class ValidaColumnasMCAHelper implements Serializable {
    
    private static final long serialVersionUID = -6811555975601822218L;
    
    
    private static final String MSJ_CAMPO_NO_VALIDO = "El campo no es un valor valido";
    private static final String METODO_MCA = "MCA";
    private static final String DATE_PATTERN = "^(?:(?:0?[1-9]|1\\d|2[0-8])(\\/|-)(?:0?[1-9]|1[0-2]))(\\/|-)(?:[1-9]\\d\\d\\d|\\d[1-9]\\d\\d|\\d\\d[1-9]\\d|\\d\\d\\d[1-9])$|^(?:(?:31(\\/|-)(?:0?[13578]|1[02]))|(?:(?:29|30)(\\/|-)(?:0?[1,3-9]|1[0-2])))(\\/|-)(?:[1-9]\\d\\d\\d|\\d[1-9]\\d\\d|\\d\\d[1-9]\\d|\\d\\d\\d[1-9])$|^(29(\\/|-)0?2)(\\/|-)(?:(?:0[48]00|[13579][26]00|[2468][048]00)|(?:\\d\\d)?(?:0[48]|[2468][048]|[13579][26]))$";
    private static final String MSJ_ARCHIVO_NO_VALIDO = "El archivo no se encuentra en el folio de carga";
    private static final String MSJ_ARCHIVO_DUPLICADO = "El archivo se encuentra duplicado.";
    private static final String MSJ_CAMPO_OBLIGATORIO = "El campo es un valor obligatorio";
    private static final String MSJ_CAMPO_ENTEROS = "El monto del impuesto \u00fanicamente permitir\u00e1 valores numericos";
    private static final String MSJ_CAMPO_MAYOR_CERO = "El monto del impuesto debe ser mayor a cero";
    private static final String MSJ_CAMPO_IMPUESTO_DUPLICADO = "No se puede adjuntar un impuesto duplicado, favor de verificar";
    private static final String MSJ_IMPUESTO_VALIDO = "Impuesto correcto";
    private static final BigDecimal IMPUESTO_NA = new BigDecimal(18L);
    private static final String MSJ_IMPUESTO_INVALIDO = "Impuesto incorrecto";
    private static final String MSJ_ERROR_IMPUESTO_NA = "El campo debe contener un impuesto diferente a N/A";
    private static final String NA = "N/A";
    private static final String MSJ_FECHA_COMITE_ERROR = "El campo no es un valor valido. Esta fecha no es la requerida";
    private static final String UNCHECKED = "unchecked";
    
    public CargaMasivaPropuestasDTO validaUnidadAdministrativa(BigDecimal cell,
            CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO) {
        boolean unidadValida = true; 
        BigDecimal unidadEmpleado = new BigDecimal(cargaMasivaPropuestasDTO.getFecetPropuesta().getEmpleadoDto()
                .getDetalleEmpleado().get(0).getCentral().getIdArace());
        if (unidadEmpleado.equals(Constantes.ACPPCE)) {
            if (cell.longValue() == TipoAraceEnum.ACAOCE.getId()) {
                cargaMasivaPropuestasDTO.setUnidadAdministrativa(new BigDecimal(TipoAraceEnum.ACAOCE.getId()));
            } else if (cell.longValue() == TipoAraceEnum.ACOECE.getId()) {
                cargaMasivaPropuestasDTO.setUnidadAdministrativa(new BigDecimal(TipoAraceEnum.ACOECE.getId()));
            } else if ((cell.longValue() == TipoAraceEnum.ADACE_PACIFICO_NORTE.getId())) {
                cargaMasivaPropuestasDTO.setUnidadAdministrativa(new BigDecimal(TipoAraceEnum.ADACE_PACIFICO_NORTE.getId()));
            } else if ((cell.longValue() == TipoAraceEnum.ADACE_NORTE_CENTRO.getId())) {
                cargaMasivaPropuestasDTO.setUnidadAdministrativa(new BigDecimal(TipoAraceEnum.ADACE_NORTE_CENTRO.getId()));
            } else if ((cell.longValue() == TipoAraceEnum.ADACE_NOROESTE.getId())) {
                cargaMasivaPropuestasDTO.setUnidadAdministrativa(new BigDecimal(TipoAraceEnum.ADACE_NOROESTE.getId()));
            } else if ((cell.longValue() == TipoAraceEnum.ADACE_OCCIDENTE.getId())) {
                cargaMasivaPropuestasDTO.setUnidadAdministrativa(new BigDecimal(TipoAraceEnum.ADACE_OCCIDENTE.getId()));
            } else if ((cell.longValue() == TipoAraceEnum.ADACE_CENTRO.getId())) {
                cargaMasivaPropuestasDTO.setUnidadAdministrativa(new BigDecimal(TipoAraceEnum.ADACE_CENTRO.getId()));
            } else if ((cell.longValue() == TipoAraceEnum.ADACE_SUR.getId())) {
                cargaMasivaPropuestasDTO.setUnidadAdministrativa(new BigDecimal(TipoAraceEnum.ADACE_SUR.getId()));
            } else {
                unidadValida = false;
                cargaMasivaPropuestasDTO.setDescripcionError("Unidad Administrativa no encontrada");
            }
        } else if (contieneUnidad(cell)) {
            cargaMasivaPropuestasDTO.setUnidadAdministrativa(unidadEmpleado);
        } else {
            unidadValida = false;
            cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);

        }
        cargaMasivaPropuestasDTO.setValida(unidadValida);
        return cargaMasivaPropuestasDTO;
    }
    
    @SuppressWarnings(UNCHECKED)
    public CargaMasivaPropuestasDTO validaMetodoCI(String metodo,
            CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO, Map<Integer, Object> catalogos) {
        
        String metodoTmp = metodo.trim();
        if (!metodo.equals(METODO_MCA)) {
            cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
            cargaMasivaPropuestasDTO.setValida(false);
            return cargaMasivaPropuestasDTO;
        } else {
            
            for (FececMetodo metodoXBuscar : (List<FececMetodo>) catalogos.get(Constantes.ENTERO_UNO)) {
                if (metodoXBuscar.getAbreviatura().equals(metodoTmp)) {
                    cargaMasivaPropuestasDTO.getFecetPropuesta().setIdMetodo(metodoXBuscar.getIdMetodo());
                    cargaMasivaPropuestasDTO.setMetodoString(metodoTmp);
                    cargaMasivaPropuestasDTO.setValida(true);
                    break;
                }
            }
            
            if (cargaMasivaPropuestasDTO.getFecetPropuesta().getIdMetodo() == null) {
                cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
                cargaMasivaPropuestasDTO.setValida(false);
            }
        }
        return cargaMasivaPropuestasDTO;
    }
    
    public CargaMasivaPropuestasDTO validaFechaPresentacionCI(Map<String, String> fechas,
            CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO) {
        Date fecha = null;
        Map<TipoEmpleadoEnum, BigDecimal> unidadesCorreo = new HashMap<TipoEmpleadoEnum, BigDecimal>();
        BigDecimal unidadAdminEmpleado = new BigDecimal(cargaMasivaPropuestasDTO.getFecetPropuesta().getEmpleadoDto()
                .getDetalleEmpleado().get(0).getCentral().getIdArace());
        BigDecimal unidadAdminSeleccionada = cargaMasivaPropuestasDTO.getUnidadAdministrativa();
        if (unidadAdminEmpleado.equals(Constantes.ACPPCE)) {
            if (unidadAdminSeleccionada.equals(new BigDecimal(TipoAraceEnum.ACAOCE.getId()))
                    || unidadAdminSeleccionada.equals(new BigDecimal(TipoAraceEnum.ACOECE.getId()))) {
                if (validaFechaCapturada(fechas.get(String.valueOf(Constantes.FECHA_INFORME_COMITE_REGIONAL_CI)))) {
                    fecha = isFechaValida(Constantes.FECHA_INFORME_COMITE_CI,
                            fechas.get(String.valueOf(Constantes.FECHA_INFORME_COMITE_CI)), cargaMasivaPropuestasDTO);
                    if (fecha != null && validaFechaIniPeriodo(fecha, cargaMasivaPropuestasDTO,
                            Constantes.FECHA_INFORME_COMITE_CI)) {
                        unidadesCorreo.put(TipoEmpleadoEnum.CONSULTOR_INSUMOS,
                                cargaMasivaPropuestasDTO.getUnidadAdministrativa());
                        cargaMasivaPropuestasDTO.setSegundoCaracter(Constantes.I);
                        cargaMasivaPropuestasDTO.getFecetPropuesta().setFechaInforme(fecha);
                        cargaMasivaPropuestasDTO.setValida(true);
                    }
                } else {
                    cargaMasivaPropuestasDTO.setCell(Constantes.FECHA_INFORME_COMITE_REGIONAL_CI);
                    cargaMasivaPropuestasDTO.setDescripcionError(MSJ_FECHA_COMITE_ERROR);
                    cargaMasivaPropuestasDTO.setValida(false);
                }
            } else if (contieneUnidadAdace(unidadAdminSeleccionada)) {
                if (validaFechaCapturada(fechas.get(String.valueOf(Constantes.FECHA_INFORME_COMITE_REGIONAL_CI)))) {
                    fecha = isFechaValida(Constantes.FECHA_INFORME_COMITE_CI,
                            fechas.get(String.valueOf(Constantes.FECHA_INFORME_COMITE_CI)), cargaMasivaPropuestasDTO);
                    if (fecha != null && validaFechaIniPeriodo(fecha, cargaMasivaPropuestasDTO,
                            Constantes.FECHA_INFORME_COMITE_CI)) {
                        cargaMasivaPropuestasDTO.setExisteProgramador(true);
                        unidadesCorreo.put(TipoEmpleadoEnum.CONSULTOR_INSUMOS,
                                cargaMasivaPropuestasDTO.getUnidadAdministrativa());
                        cargaMasivaPropuestasDTO.setSegundoCaracter(Constantes.I);
                        cargaMasivaPropuestasDTO.getFecetPropuesta().setFechaInforme(fecha);
                        cargaMasivaPropuestasDTO.setValida(true);
                    }
                } else {
                    cargaMasivaPropuestasDTO.setCell(Constantes.FECHA_INFORME_COMITE_REGIONAL_CI);
                    cargaMasivaPropuestasDTO.setDescripcionError(MSJ_FECHA_COMITE_ERROR);
                    cargaMasivaPropuestasDTO.setValida(false);
                }
            }

        } else if (contieneUnidadAdace(unidadAdminEmpleado)) {
            if (validaFechaCapturada(fechas.get(String.valueOf(Constantes.FECHA_INFORME_COMITE_CI)))) {
                fecha = isFechaValida(Constantes.FECHA_INFORME_COMITE_REGIONAL_CI,
                        fechas.get(String.valueOf(Constantes.FECHA_INFORME_COMITE_REGIONAL_CI)),
                        cargaMasivaPropuestasDTO);
                if (fecha != null
                        && validaFechaIniPeriodo(fecha, cargaMasivaPropuestasDTO, Constantes.FECHA_INFORME_COMITE_REGIONAL_CI)) {
                    unidadesCorreo.put(TipoEmpleadoEnum.CONSULTOR_INSUMOS, cargaMasivaPropuestasDTO.getUnidadAdministrativa());
                    cargaMasivaPropuestasDTO.setSegundoCaracter(Constantes.I);
                    cargaMasivaPropuestasDTO.getFecetPropuesta().setFechaInforme(fecha);
                    cargaMasivaPropuestasDTO.setValida(true);
                }
            } else {
                cargaMasivaPropuestasDTO.setCell(Constantes.FECHA_INFORME_COMITE_CI);
                cargaMasivaPropuestasDTO.setDescripcionError(MSJ_FECHA_COMITE_ERROR);
                cargaMasivaPropuestasDTO.setValida(false);
            }
        }
        cargaMasivaPropuestasDTO.setUnidadesCorreo(unidadesCorreo);
        return cargaMasivaPropuestasDTO;
    }
    
    @SuppressWarnings(UNCHECKED)
    public boolean validaSubprograma(String subprograma, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO, Map<Integer, Object> catalogos) {
        
        boolean subprogramaValido = false;
        
        for (FececSubprograma subprogramaXBuscar : (List<FececSubprograma>) catalogos.get(Constantes.ENTERO_TRES)) {
            if (subprogramaXBuscar.getClave().equals(subprograma)) {
                subprogramaValido = true;
                cargaMasivaPropuestasDTO.getFecetPropuesta().setIdSubprograma(subprogramaXBuscar.getIdSubprograma());
                break;
            }
        }
        
        if (cargaMasivaPropuestasDTO.getFecetPropuesta().getIdSubprograma() == null) {
            cargaMasivaPropuestasDTO.setDescripcionError(Constantes.SUBPROGRAMA_INCORRECTO);
            subprogramaValido = false;
        }
        
       return subprogramaValido;
    }
    
    @SuppressWarnings(UNCHECKED)
    public boolean validaSector(BigDecimal sector, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO, Map<Integer, Object> catalogos) {
        
        boolean sectorValido = false;
        
        for (FececSector sectorXBuscar : (List<FececSector>) catalogos.get(Constantes.ENTERO_DOS)) {
            if (sectorXBuscar.getIdSector().equals(sector)) {
                cargaMasivaPropuestasDTO.getFecetPropuesta().setIdSector(sectorXBuscar.getIdSector());
                sectorValido = true;
                break;
            }
        }
        
        if (cargaMasivaPropuestasDTO.getFecetPropuesta().getIdSector() == null) {
            cargaMasivaPropuestasDTO.setDescripcionError(ConstantesError.ERROR_EXISTENCIA_SECTOR);
            sectorValido = false;
        }
        
        return sectorValido;
    }
    
    @SuppressWarnings(UNCHECKED)
    public boolean validaTipoPropuesta(BigDecimal tipoPropuesta, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO, Map<Integer, Object> catalogos) {
        
        boolean tipoPropuestaValido = false;
        
        for (FececTipoPropuesta tipoPropuestaXBuscar : (List<FececTipoPropuesta>) catalogos.get(Constantes.ENTERO_CINCO)) {
            if (tipoPropuestaXBuscar.getIdTipoPropuesta().equals(tipoPropuesta)) {
                cargaMasivaPropuestasDTO.getFecetPropuesta().setIdTipoPropuesta(tipoPropuestaXBuscar.getIdTipoPropuesta());
                tipoPropuestaValido = true;
                break;
            }
        }
        
        if (cargaMasivaPropuestasDTO.getFecetPropuesta().getIdTipoPropuesta() == null) {
            cargaMasivaPropuestasDTO.setDescripcionError(ConstantesError.ERROR_EXISTENCIA_PROPUESTA);
            tipoPropuestaValido = false;
        }
        
        return tipoPropuestaValido;
    }
    
    @SuppressWarnings(UNCHECKED)
    public boolean validaCausaProgramacion(BigDecimal causaProgramacion, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO, Map<Integer, Object> catalogos) {
        
        boolean causaValida = false;
        
        for (FececCausaProgramacion causaProgramacionXBuscar : (List<FececCausaProgramacion>) catalogos.get(Constantes.ENTERO_CUATRO)) {
            if (causaProgramacionXBuscar.getIdCausaProgramacion().equals(causaProgramacion)) {
                cargaMasivaPropuestasDTO.getFecetPropuesta().setIdCausaProgramacion(causaProgramacionXBuscar.getIdCausaProgramacion());
                causaValida = true;
                break;
            }
        }
        
        if (cargaMasivaPropuestasDTO.getFecetPropuesta().getIdCausaProgramacion() == null) {
            cargaMasivaPropuestasDTO.setDescripcionError(ConstantesError.ERROR_EXISTENCIA_PROGRAMACION);
            causaValida = false;
        }
        
        return causaValida;
    }
    
    public CargaMasivaPropuestasDTO validaPeriodoPropuestoCI(String cellFechaIni, String cellFechaFin,
            CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO, List<CargaMasivaPropuestasDTO> lstErrores) {
        if (validaFechasPerirodos(cellFechaIni, cellFechaFin, cargaMasivaPropuestasDTO, lstErrores)) {
            Date fechaIni = obetenerFecha(cellFechaIni, cargaMasivaPropuestasDTO, Constantes.FECHA_INICIO_PERIODO_CI);
            if (fechaIni != null) {
                Date fechaFin = obetenerFecha(cellFechaFin, cargaMasivaPropuestasDTO, Constantes.FECHA_FIN_PERIODO_CI);
                if (fechaFin != null) {
                    if (fechaIni.compareTo(fechaFin) <= 0) {
                        if (fechaIni.compareTo(getSystemDate()) <= 0 && fechaFin.compareTo(getSystemDate()) <= 0) {
                            cargaMasivaPropuestasDTO.setValida(true);
                            cargaMasivaPropuestasDTO.getFecetPropuesta().setFechaInicioPeriodo(fechaIni);
                            cargaMasivaPropuestasDTO.getFecetPropuesta().setFechaFinPeriodo(fechaFin);
                            return cargaMasivaPropuestasDTO;
                        } else {
                            cargaMasivaPropuestasDTO.setValida(false);
                            cargaMasivaPropuestasDTO.setCell(Constantes.FECHA_FIN_PERIODO_CI);
                            cargaMasivaPropuestasDTO
                                    .setDescripcionError("La fecha fin no puede ser mayor a la actual");
                            llenarListaErrores(lstErrores, cargaMasivaPropuestasDTO);
                            return cargaMasivaPropuestasDTO;
                        }

                    } else {
                        cargaMasivaPropuestasDTO.setValida(false);
                        cargaMasivaPropuestasDTO.setCell(Constantes.FECHA_INICIO_PERIODO_CI);
                        cargaMasivaPropuestasDTO
                                .setDescripcionError("La fecha de inicio no puede ser mayor que la de fin.");
                        llenarListaErrores(lstErrores, cargaMasivaPropuestasDTO);
                        return cargaMasivaPropuestasDTO;
                    }
                }
            }

        }
        return cargaMasivaPropuestasDTO;
    }
    
    public void validaPrioridadSugerida(double prioridad, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO, List<FececPrioridad> lstPrioridad) {

        String prioridadSugerida = fmt(prioridad);

        if (!prioridadSugerida.trim().equals("")) {
            if (!validaPrioridadSugerida(prioridadSugerida.trim(), lstPrioridad)) {
                cargaMasivaPropuestasDTO.getFecetPropuesta().setPrioridadSugerida(prioridadSugerida.trim());
                cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
                cargaMasivaPropuestasDTO.setValida(false);
            } else {
                cargaMasivaPropuestasDTO.getFecetPropuesta().setPrioridadSugerida(prioridadSugerida.trim());
                cargaMasivaPropuestasDTO.setValida(true);
            }
        } else {
            cargaMasivaPropuestasDTO.getFecetPropuesta().setPrioridadSugerida(prioridadSugerida.trim());
            cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
            cargaMasivaPropuestasDTO.setValida(false);
        }
    }
    
    public boolean validaDocumentosCI(List<String> archivosList, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO,
            String folioCargaDoc) {
        List<FecetDocExpediente> expedienteList = new ArrayList<FecetDocExpediente>();
        String directorioFolio = Constantes.DIRECTORIO_CARGA_DOCUMENTOS + folioCargaDoc + "/";
        for (String nombreArchivo : archivosList) {
            FecetDocExpediente docExpediente = new FecetDocExpediente();
            String ruta = directorioFolio + nombreArchivo;
            File file = new File(ruta);
            if (file.exists()) {
                docExpediente.setNombre(nombreArchivo);
                docExpediente.setRutaArchivo(ruta);
                docExpediente.setFechaCreacion(new Date());
                if (validaDuplicidadFile(expedienteList, docExpediente, cargaMasivaPropuestasDTO)) {
                    expedienteList.add(docExpediente);
                    cargaMasivaPropuestasDTO.setValida(true);
                } else {
                    cargaMasivaPropuestasDTO.setValida(false);
                    return false;
                }
            } else {
                cargaMasivaPropuestasDTO.setValida(false);
                cargaMasivaPropuestasDTO
                        .setDescripcionError(MSJ_ARCHIVO_NO_VALIDO + " " + nombreArchivo);
                return false;
            }
        }
        cargaMasivaPropuestasDTO.getFecetPropuesta().setListaDocumentos(expedienteList);
        return true;
    }
    
    public CargaMasivaPropuestasDTO validaImpuestoCI(HSSFRow row, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO,
            Map<FececTipoImpuesto, List<FececConcepto>> conceptosImpuestos, List<FececTipoImpuesto> lstImpuestos, List<CargaMasivaPropuestasDTO> lstErrores) {
        FecetImpuesto fecetImpuesto;
        List<FecetImpuesto> listaImpuestos = new ArrayList<FecetImpuesto>();
        int contInicial = Constantes.ENTERO_TRECE;
        int columnasOcupadas = row.getLastCellNum();
        
        while (contInicial < columnasOcupadas) {
            HSSFCell impuesto = row.getCell(contInicial);
            HSSFCell concepto = row.getCell(contInicial + 1);
            HSSFCell monto = row.getCell(contInicial + 2);

            boolean[] condiciones = new boolean[] {
                    (impuesto != null && impuesto.getCellType() == HSSFCell.CELL_TYPE_STRING
                            && impuesto.getCellType() != HSSFCell.CELL_TYPE_BLANK),
                    (monto != null && monto.getCellType() == HSSFCell.CELL_TYPE_NUMERIC
                            && monto.getCellType() != HSSFCell.CELL_TYPE_BLANK),
                    (concepto != null && concepto.getCellType() == HSSFCell.CELL_TYPE_NUMERIC
                            && concepto.getCellType() != HSSFCell.CELL_TYPE_BLANK) };

            if (ValidacionParametrosUtil.seCumplenTodasLasCondicion(condiciones)) {
                if (!validaImpuestoExistente(impuesto.getStringCellValue(), lstImpuestos).equals(BigDecimal.ZERO)) {
                    if (validaConcepto(concepto, impuesto, cargaMasivaPropuestasDTO,
                            contInicial, conceptosImpuestos, lstImpuestos)) {
                        String montoStrt = fmt(monto.getNumericCellValue());
                        if (isNumeric(montoStrt, cargaMasivaPropuestasDTO, impuesto)) {
                            if (cargaMasivaPropuestasDTO.isValida()) {
                                try {
                                    if (validaImpuestoAplicable(impuesto, monto, cargaMasivaPropuestasDTO, contInicial)) {
                                        fecetImpuesto = new FecetImpuesto();
                                        fecetImpuesto.setIdTipoImpuesto(
                                                validaImpuesto(impuesto.getStringCellValue(), lstImpuestos));
                                        if (fecetImpuesto.getIdTipoImpuesto() != null) {
                                            fecetImpuesto.setMonto(BigDecimal.valueOf(monto.getNumericCellValue()));
                                            fecetImpuesto.setIdConcepto(new BigDecimal(concepto.getNumericCellValue()));

                                            validaImpuestoNA(listaImpuestos, fecetImpuesto, cargaMasivaPropuestasDTO);
                                            if (cargaMasivaPropuestasDTO.getDescripcionAddImpuesto()
                                                    .equals(MSJ_IMPUESTO_VALIDO)) {
                                                if (validaConceptosRepetidos(listaImpuestos, fecetImpuesto)) {
                                                    cargaMasivaPropuestasDTO.setCell(contInicial + 1);
                                                    cargaMasivaPropuestasDTO.setDescripcionError(
                                                            MSJ_CAMPO_IMPUESTO_DUPLICADO);
                                                    cargaMasivaPropuestasDTO.setValida(false);
                                                    llenarListaErrores(lstErrores, cargaMasivaPropuestasDTO);
                                                    contInicial = monto.getColumnIndex() + 1;
                                                }
                                                listaImpuestos.add(fecetImpuesto);
                                            } else {
                                                cargaMasivaPropuestasDTO.setCell(contInicial);
                                                llenarListaErrores(lstErrores, cargaMasivaPropuestasDTO);
                                                contInicial = monto.getColumnIndex() + 1;
                                            }
                                        } else {
                                            cargaMasivaPropuestasDTO.setCell(contInicial);
                                            cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
                                            cargaMasivaPropuestasDTO.setValida(false);
                                            llenarListaErrores(lstErrores, cargaMasivaPropuestasDTO);
                                            contInicial = monto.getColumnIndex() + 1;  
                                        }
                                    } else {
                                        cargaMasivaPropuestasDTO.setValida(false);
                                        llenarListaErrores(lstErrores, cargaMasivaPropuestasDTO);
                                        contInicial = monto.getColumnIndex() + 1;
                                    }
                                } catch (Exception e) {
                                    llenarListaErrores(lstErrores, cargaMasivaPropuestasDTO);
                                    contInicial = monto.getColumnIndex() + 1;
                                }
                            } else {

                                break;
                            }

                        } else {
                            cargaMasivaPropuestasDTO.setCell(contInicial + 2);
                            cargaMasivaPropuestasDTO.setValida(false);
                            llenarListaErrores(lstErrores, cargaMasivaPropuestasDTO);
                            contInicial = monto.getColumnIndex() + 1;
                        }
                    } else {
                        llenarListaErrores(lstErrores, cargaMasivaPropuestasDTO);
                        validaTipoColumnaMonto(monto, cargaMasivaPropuestasDTO, impuesto, contInicial);
                        llenarListaErrores(lstErrores, cargaMasivaPropuestasDTO);
                        contInicial = monto.getColumnIndex() + 1;
                    }  
                } else {
                    cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
                    cargaMasivaPropuestasDTO.setCell(contInicial);
                    cargaMasivaPropuestasDTO.setValida(false);
                    llenarListaErrores(lstErrores, cargaMasivaPropuestasDTO);
                    cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
                    cargaMasivaPropuestasDTO.setCell(contInicial + 1);
                    cargaMasivaPropuestasDTO.setValida(false);
                    llenarListaErrores(lstErrores, cargaMasivaPropuestasDTO);
                    validaTipoColumnaMonto(monto, cargaMasivaPropuestasDTO, impuesto, contInicial);
                    llenarListaErrores(lstErrores, cargaMasivaPropuestasDTO);
                }
            } else if (listaImpuestos.isEmpty()) {
                boolean conceptoVacio = (concepto == null || concepto.getCellType() == HSSFCell.CELL_TYPE_BLANK);
                boolean montoVacio = (monto == null || monto.getCellType() == HSSFCell.CELL_TYPE_BLANK);
                boolean impuestoVacio = (impuesto == null || impuesto.getCellType() == HSSFCell.CELL_TYPE_BLANK);
                if (conceptoVacio && montoVacio && impuestoVacio) {
                    if (validaMontoNulo(monto)) {
                        contInicial = contInicial + Constantes.ENTERO_TRES;
                    } else {
                        contInicial = monto.getColumnIndex() + 1;
                    }
                } else {
                    validaTipoColumnaImpuesto(impuesto, cargaMasivaPropuestasDTO, contInicial, lstImpuestos);
                    llenarListaErrores(lstErrores, cargaMasivaPropuestasDTO);
                    if (!cargaMasivaPropuestasDTO.isValida()) {
                      cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
                      cargaMasivaPropuestasDTO.setCell(contInicial + 1);
                      cargaMasivaPropuestasDTO.setValida(false);
                      llenarListaErrores(lstErrores, cargaMasivaPropuestasDTO);
                    } else {
                        validaTipoColumnaConcepto(impuesto, concepto, cargaMasivaPropuestasDTO, contInicial, lstImpuestos, conceptosImpuestos);
                        llenarListaErrores(lstErrores, cargaMasivaPropuestasDTO);
                    }
                    validaTipoColumnaMonto(monto, cargaMasivaPropuestasDTO, impuesto, contInicial);
                    llenarListaErrores(lstErrores, cargaMasivaPropuestasDTO);
                    contInicial = monto.getColumnIndex() + 1;
                }
            } else {
                cargaMasivaPropuestasDTO.setValida(true);
                break;
            }
            if (!validaMontoNulo(monto)) {
                contInicial = monto.getColumnIndex() + 1;
            }
        } // While
        cargaMasivaPropuestasDTO.setFecetImpuestos(listaImpuestos);
        return cargaMasivaPropuestasDTO;
    }
    
    private boolean contieneUnidad(BigDecimal unidadSeleccionada) {
        return (!(unidadSeleccionada.longValue() == (TipoAraceEnum.ACAOCE.getId()))
                && !(unidadSeleccionada.longValue() == (TipoAraceEnum.ACOECE.getId()))
                && unidadSeleccionada.longValue() == 0);
    }
    
    private boolean validaFechaCapturada(String fecha) {

        boolean isValida = false;

        if (fecha != null && fecha.isEmpty()) {
            isValida = true;
        }

        return isValida;
    }
    
    private static Date isFechaValida(int numCelda, String fecha, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO) {
        Pattern pattern = Pattern.compile(DATE_PATTERN);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaComite = null;
        if (fecha != null) {
            Matcher matcher = pattern.matcher(fecha);
            if (matcher.matches()) {
                try {
                    fechaComite = df.parse(fecha);
                    return fechaComite;
                } catch (ParseException e) {
                    cargaMasivaPropuestasDTO.setValida(false);
                }
            }
        }
        cargaMasivaPropuestasDTO.setCell(numCelda);
        cargaMasivaPropuestasDTO.setValida(false);
        cargaMasivaPropuestasDTO
                .setDescripcionError("El campo no es un valor valido, el formato de la fecha debe ser: DD/MM/YYYY");
        return fechaComite;
    }
    
    private boolean validaFechaIniPeriodo(Date fechaPresentacion, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO,
            int celda) {

        if (fechaPresentacion.compareTo(getSystemDate()) < 0) {
            cargaMasivaPropuestasDTO.setCell(celda);
            cargaMasivaPropuestasDTO.setValida(false);
            cargaMasivaPropuestasDTO.setDescripcionError(
                    "La fecha de Presentaci\u00f3n o Informe no puede ser menor a la fecha actual");
            return false;
        }

        return true;
    }
    
    private static java.util.Date getSystemDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        return cal.getTime();
    }
    
    public boolean contieneUnidadAdace(BigDecimal unidad) {
        if (!unidad.equals(new BigDecimal(TipoAraceEnum.ACAOCE.getId()))
                && !unidad.equals(new BigDecimal(TipoAraceEnum.ACOECE.getId()))) {
            for (TipoAraceEnum c : TipoAraceEnum.values()) {
                if (new BigDecimal(c.getId()).equals(unidad)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private Date obetenerFecha(String cell, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO, int num) {
        Pattern pattern = Pattern.compile(DATE_PATTERN);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaComite = null;
        if (cell != null) {
            Matcher matcher = pattern.matcher(cell);
            if (matcher.matches()) {
                try {
                    fechaComite = df.parse(cell);
                    cargaMasivaPropuestasDTO.setValida(true);
                    return fechaComite;
                } catch (ParseException e) {
                    cargaMasivaPropuestasDTO.setValida(false);
                }
            }
        }
        cargaMasivaPropuestasDTO.setValida(false);
        cargaMasivaPropuestasDTO.setCell(num);
        cargaMasivaPropuestasDTO
                .setDescripcionError("El campo no es un valor valido, el formato de la fecha debe ser: DD/MM/YYYY");
        return fechaComite;
    }
    
    private boolean validaPrioridadSugerida(String prioridadSugerida, List<FececPrioridad> lstPrioridad) {

        boolean isPrioridadValida = false;
        
        for (FececPrioridad prioridadXBuscar : lstPrioridad) {
            if (prioridadSugerida.equals(String.valueOf(prioridadXBuscar.getIdPrioridad()))) {
                isPrioridadValida = true;
            }
        }

        return isPrioridadValida;
    }
    
    private String fmt(double d) {
        if (d == (long) d) {
            return String.format("%d", (long) d);
        } else {
            return String.format("%s", d);
        }
    }
    
    private boolean validaDuplicidadFile(List<FecetDocExpediente> expedienteList, FecetDocExpediente docExpediente,
            CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO) {

        for (FecetDocExpediente expediente : expedienteList) {
            if (expediente.getNombre().equals(docExpediente.getNombre())) {
                cargaMasivaPropuestasDTO.setDescripcionError(
                        MSJ_ARCHIVO_DUPLICADO + " " + docExpediente.getNombre());
                return false;
            }
        }

        return true;
    }
    
    private boolean validaConcepto(HSSFCell concepto, HSSFCell impuesto,
            CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO, int contInicial,
            Map<FececTipoImpuesto, List<FececConcepto>> conceptosImpuestos, List<FececTipoImpuesto> lstImpuestos) {

        boolean isValido = false;
        BigDecimal idImpuesto = BigDecimal.ZERO;
        FececTipoImpuesto impuestoAValidar = null;
        
        for (FececTipoImpuesto impuestoXBuscar : lstImpuestos) {
            if (impuestoXBuscar.getAbreviatura().equals(impuesto.getStringCellValue().trim())) {
                idImpuesto = impuestoXBuscar.getIdTipoImpuesto();
                impuestoAValidar = impuestoXBuscar;
                break;
            }
        }
        
        if (!idImpuesto.equals(BigDecimal.ZERO)) {
            for (FececConcepto conceptoXBuscar : conceptosImpuestos.get(impuestoAValidar)) {
               if (conceptoXBuscar.getIdConcepto() == (concepto.getNumericCellValue())) {
                   isValido = true;
                   break;
               }
            }
            
            if (!isValido) {
                cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
                cargaMasivaPropuestasDTO.setCell(contInicial + 1);
                cargaMasivaPropuestasDTO.setValida(false);
            }
            
        } else {
            cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
            cargaMasivaPropuestasDTO.setCell(contInicial);
            cargaMasivaPropuestasDTO.setValida(false);
        }

        return isValido;
    }
    
    private static boolean isNumeric(String cadena, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO,
            HSSFCell impuesto) {
        boolean isMontoValido = false;

        if (!impuesto.getStringCellValue().equals(NA)) {
            try {
                Double monto = Double.parseDouble(cadena);
                if (monto <= 0) {
                    cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_MAYOR_CERO);
                } else {
                    isMontoValido = true;
                }
            } catch (NumberFormatException nfe) {
                cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_ENTEROS);
            }
        } else {
            isMontoValido = true;
        }

        return isMontoValido;
    }
    
    private boolean validaImpuestoAplicable(HSSFCell impuesto, HSSFCell monto,
            CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO, int contInicial) {

        boolean isImpuestoAplicable = false;

        if (monto != null && impuesto != null) {
            String montoStr = fmt(monto.getNumericCellValue());
            Double montoTmp = Double.parseDouble(montoStr);
            String impuestoTmp = impuesto.getStringCellValue();

            if (!impuestoTmp.equals(NA)) {
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
    
    private void validaImpuestoNA(List<FecetImpuesto> listaImpuestos, FecetImpuesto impuestoNuevo,
            CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO) {

        if (listaImpuestos == null || listaImpuestos.isEmpty()) {
            cargaMasivaPropuestasDTO.setValida(true);
            cargaMasivaPropuestasDTO.setDescripcionAddImpuesto(MSJ_IMPUESTO_VALIDO);
        } else {
            for (FecetImpuesto impuestoAnterior : listaImpuestos) {
                if (impuestoAnterior.getIdTipoImpuesto().equals(IMPUESTO_NA)
                        && impuestoNuevo.getIdTipoImpuesto().equals(IMPUESTO_NA)) {

                    cargaMasivaPropuestasDTO
                            .setDescripcionAddImpuesto(MSJ_IMPUESTO_INVALIDO);
                    cargaMasivaPropuestasDTO
                            .setDescripcionError(MSJ_CAMPO_IMPUESTO_DUPLICADO);
                    cargaMasivaPropuestasDTO.setValida(false);
                    break;

                } else if (impuestoAnterior.getIdTipoImpuesto().equals(IMPUESTO_NA)
                        && !impuestoNuevo.getIdTipoImpuesto().equals(IMPUESTO_NA)) {

                    cargaMasivaPropuestasDTO
                            .setDescripcionAddImpuesto(MSJ_IMPUESTO_INVALIDO);
                    cargaMasivaPropuestasDTO.setDescripcionError(MSJ_ERROR_IMPUESTO_NA);
                    cargaMasivaPropuestasDTO.setValida(false);
                    break;

                } else if (!impuestoAnterior.getIdTipoImpuesto().equals(IMPUESTO_NA)
                        && impuestoNuevo.getIdTipoImpuesto().equals(IMPUESTO_NA)) {

                    cargaMasivaPropuestasDTO
                            .setDescripcionAddImpuesto(MSJ_IMPUESTO_INVALIDO);
                    cargaMasivaPropuestasDTO.setDescripcionError(MSJ_ERROR_IMPUESTO_NA);
                    cargaMasivaPropuestasDTO.setValida(false);
                    break;

                } else if (!impuestoAnterior.getIdTipoImpuesto().equals(IMPUESTO_NA)
                        && !impuestoNuevo.getIdTipoImpuesto().equals(IMPUESTO_NA)) {

                    cargaMasivaPropuestasDTO.setValida(true);
                    cargaMasivaPropuestasDTO.setDescripcionAddImpuesto(MSJ_IMPUESTO_VALIDO);
                }
            }
        }
    }
    
    private static boolean validaConceptosRepetidos(List<FecetImpuesto> lista, FecetImpuesto impuesto) {
        for (FecetImpuesto imp : lista) {
            if (imp.getIdTipoImpuesto().equals(impuesto.getIdTipoImpuesto())
                    && imp.getIdConcepto().equals(impuesto.getIdConcepto())) {
                return true;
            }
        }
        return false;
    }
    
    private BigDecimal validaImpuesto(String impuesto, List<FececTipoImpuesto> lstImpuestosValidos) {
        
        for (FececTipoImpuesto impuestoXBuscar : lstImpuestosValidos) {
            if (impuestoXBuscar.getAbreviatura().equals(impuesto.trim())) {
                return impuestoXBuscar.getIdTipoImpuesto();
            }
        }
        
        return null;
    }
    
    private static void llenarListaErrores(List<CargaMasivaPropuestasDTO> lstErrores, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO) {
        
        if (!cargaMasivaPropuestasDTO.isValida()) {
            CargaMasivaPropuestasDTO errorTmp1 = new CargaMasivaPropuestasDTO();
            errorTmp1.setCell(cargaMasivaPropuestasDTO.getCell());
            errorTmp1.setRow(cargaMasivaPropuestasDTO.getRow());
            errorTmp1.setDescripcionError(cargaMasivaPropuestasDTO.getDescripcionError());
            lstErrores.add(errorTmp1);
        }
    }
    
    private boolean validaMontoNulo(HSSFCell monto) {

        if (monto == null) {
            return true;
        }

        return false;
    }
    
    private boolean validaFechasPerirodos(String cellFechaIni, String cellFechaFin,
            CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO, List<CargaMasivaPropuestasDTO> lstErrores) {
        
        boolean periodoValido = true;
        
        if (cellFechaIni.equals("") && cellFechaFin.equals("")) {
            cargaMasivaPropuestasDTO.setCell(Constantes.FECHA_INICIO_PERIODO_CI);
            cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
            cargaMasivaPropuestasDTO.setValida(false);
            llenarListaErrores(lstErrores, cargaMasivaPropuestasDTO);
            cargaMasivaPropuestasDTO.setCell(Constantes.FECHA_FIN_PERIODO_CI);
            cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
            cargaMasivaPropuestasDTO.setValida(false);
            llenarListaErrores(lstErrores, cargaMasivaPropuestasDTO);
            periodoValido = false;
        } 
        
        if (!(cellFechaIni.equals("")) && (cellFechaFin.equals(""))) {
            cargaMasivaPropuestasDTO.setCell(Constantes.FECHA_FIN_PERIODO_CI);
            cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
            cargaMasivaPropuestasDTO.setValida(false);
            llenarListaErrores(lstErrores, cargaMasivaPropuestasDTO);
            periodoValido = false;
        }
        
        if ((cellFechaIni.equals("")) && !(cellFechaFin.equals(""))) {
            cargaMasivaPropuestasDTO.setCell(Constantes.FECHA_INICIO_PERIODO_CI);
            cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
            cargaMasivaPropuestasDTO.setValida(false);
            llenarListaErrores(lstErrores, cargaMasivaPropuestasDTO);
            periodoValido = false;
        } 
        
        if (!(cellFechaIni.equals("")) && !(cellFechaFin.equals(""))) {
            obetenerFecha(cellFechaIni, cargaMasivaPropuestasDTO, Constantes.FECHA_INICIO_PERIODO_CI);
            llenarListaErrores(lstErrores, cargaMasivaPropuestasDTO);
            obetenerFecha(cellFechaFin, cargaMasivaPropuestasDTO, Constantes.FECHA_FIN_PERIODO_CI);
            llenarListaErrores(lstErrores, cargaMasivaPropuestasDTO);
        }
        
        return periodoValido;
    }
    
    private void validaTipoColumnaImpuesto(HSSFCell impuesto, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO,
            int contInicial, List<FececTipoImpuesto> lstImpuestos) {
        
        if ((impuesto == null || impuesto.getCellType() == HSSFCell.CELL_TYPE_BLANK)) {
            cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_OBLIGATORIO);
            cargaMasivaPropuestasDTO.setCell(contInicial);
            cargaMasivaPropuestasDTO.setValida(false);
        } else if (impuesto.getCellType() == HSSFCell.CELL_TYPE_STRING) {
            if (validaImpuestoExistente(impuesto.getStringCellValue().trim(), lstImpuestos).equals(BigDecimal.ZERO)) {
                cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_OBLIGATORIO);
                cargaMasivaPropuestasDTO.setCell(contInicial);
                cargaMasivaPropuestasDTO.setValida(false);
            } else {
                cargaMasivaPropuestasDTO.setValida(true);
            }
        } else {
            cargaMasivaPropuestasDTO.setCell(contInicial);
            cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
            cargaMasivaPropuestasDTO.setValida(false);
            
        }
        
       
    }
    
    private void validaTipoColumnaMonto(HSSFCell monto, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO, HSSFCell impuesto, int contInicial) {
        if ((monto == null || monto.getCellType() == HSSFCell.CELL_TYPE_BLANK)) {
            cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_OBLIGATORIO);
            cargaMasivaPropuestasDTO.setCell(contInicial + 2);
            cargaMasivaPropuestasDTO.setValida(false);
        } else if (monto.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
            String montoStrt = fmt(monto.getNumericCellValue());
            if (!isNumeric(montoStrt, cargaMasivaPropuestasDTO, impuesto)) {
                cargaMasivaPropuestasDTO.setCell(contInicial + 2);
                cargaMasivaPropuestasDTO.setValida(false);
            } else {
                cargaMasivaPropuestasDTO.setValida(true);
            }
        } else {
            cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
            cargaMasivaPropuestasDTO.setCell(contInicial + 2);
            cargaMasivaPropuestasDTO.setValida(false);
        }
    }
    
    private void validaTipoColumnaConcepto(HSSFCell impuesto, HSSFCell concepto, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO,
            int contInicial, List<FececTipoImpuesto> lstImpuestos,  Map<FececTipoImpuesto, List<FececConcepto>> conceptosImpuestos) {
        
        if (concepto == null || concepto.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
            cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_OBLIGATORIO);
            cargaMasivaPropuestasDTO.setCell(contInicial + 1);
            cargaMasivaPropuestasDTO.setValida(false);
        } else if (concepto.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            if (!validaImpuestoExistente(impuesto.getStringCellValue().trim(), lstImpuestos).equals(BigDecimal.ZERO)) {
                if (!validaConceptoAsociado(conceptosImpuestos, concepto, lstImpuestos, impuesto.getStringCellValue().trim())) {
                    cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
                    cargaMasivaPropuestasDTO.setCell(contInicial + 1);
                    cargaMasivaPropuestasDTO.setValida(false); 
                } else {
                    cargaMasivaPropuestasDTO.setValida(true);
                }
            }
        } else {
            cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
            cargaMasivaPropuestasDTO.setCell(contInicial + 1);
            cargaMasivaPropuestasDTO.setValida(false);
        }
        
        
        
    }
    
    private BigDecimal validaImpuestoExistente(String impuesto, List<FececTipoImpuesto> lstImpuestos) {
        
        BigDecimal idImpuesto = BigDecimal.ZERO;
        
        for (FececTipoImpuesto impuestoXBuscar : lstImpuestos) {
            if (impuestoXBuscar.getAbreviatura().equals(impuesto.trim())) {
                idImpuesto = impuestoXBuscar.getIdTipoImpuesto();
                break;
            }
        }
        
        return idImpuesto;
    }
    
    private boolean validaConceptoAsociado(Map<FececTipoImpuesto, List<FececConcepto>> conceptosImpuestos, HSSFCell concepto, List<FececTipoImpuesto> lstImpuestos, String impuesto) {
        
        
        boolean isValido = false;
        BigDecimal idImpuesto = BigDecimal.ZERO;
        FececTipoImpuesto impuestoAValidar = new FececTipoImpuesto();
        
        for (FececTipoImpuesto impuestoXBuscar : lstImpuestos) {
            if (impuestoXBuscar.getAbreviatura().equals(impuesto.trim())) {
                idImpuesto = impuestoXBuscar.getIdTipoImpuesto();
                impuestoAValidar = impuestoXBuscar;
                break;
            }
        }
        
        if (!idImpuesto.equals(BigDecimal.ZERO)) {
            for (FececConcepto conceptoXBuscar : conceptosImpuestos.get(impuestoAValidar)) {
               if (conceptoXBuscar.getIdConcepto() == (concepto.getNumericCellValue())) {
                   isValido = true;
                   break;
               }
            }
            
            if (!isValido) {
                return isValido;
            }
            
        } else {
            return isValido;
        }        
        return isValido;
    }
}
