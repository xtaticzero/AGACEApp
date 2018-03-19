package mx.gob.sat.siat.feagace.negocio.helper;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Component;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececTipoInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececPrioridad;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.ReporteIncorrectoDto;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.util.ExpresionesUtil;

@Component
public class CargaMasivaInsumosHelper implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(CargaMasivaInsumosHelper.class);

    private static final String INFO_FORMATO = "El RFC ingresado no cumple con la estructura de un RFC v\u00E1lido";
    private static final String MSJ_CAMPO_NO_VALIDO = "El campo no es un valor valido";
    private static final String DATE_PATTERN = "^(?:(?:0?[1-9]|1\\d|2[0-8])(\\/|-)(?:0?[1-9]|1[0-2]))(\\/|-)(?:[1-9]\\d\\d\\d|\\d[1-9]\\d\\d|\\d\\d[1-9]\\d|\\d\\d\\d[1-9])$|^(?:(?:31(\\/|-)(?:0?[13578]|1[02]))|(?:(?:29|30)(\\/|-)(?:0?[1,3-9]|1[0-2])))(\\/|-)(?:[1-9]\\d\\d\\d|\\d[1-9]\\d\\d|\\d\\d[1-9]\\d|\\d\\d\\d[1-9])$|^(29(\\/|-)0?2)(\\/|-)(?:(?:0[48]00|[13579][26]00|[2468][048]00)|(?:\\d\\d)?(?:0[48]|[2468][048]|[13579][26]))$";
    private static final String MSJ_ARCHIVO_NO_VALIDO = "El archivo no se encuentra en el folio de carga: ";
    private static final String MSJ_ARCHIVO_DUPLICADO = "El archivo se encuentra duplicado;";
    private static final String REGISTRO_DUPLICADO_FORMATO = "El registro esta duplicado en el formato de carga";
    private static final String PAY = " | ";
    private static final Pattern PATTERN_FECHA = Pattern.compile(DATE_PATTERN);
    private final SimpleDateFormat formatoDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");

    public void validarRFCComposicion(String rfcContribuyente, FecetInsumo insumo) {
        if (rfcContribuyente == null || rfcContribuyente.isEmpty()) {
            insumo.setDescripcionRechazo("Es obligatorio ingresar el RFC.");
        } else if ((rfcContribuyente.length() != ExpresionesUtil.RFCM_LONGITUD && rfcContribuyente.length() != ExpresionesUtil.RFC_LONGITUD)) {
            insumo.setDescripcionRechazo("El RFC debe estar a 12 (Persona Moral) o 13 (Persona F\u00EDsica) posiciones");
        } else if (rfcContribuyente.length() == ExpresionesUtil.RFCM_LONGITUD) {
            validarRFCMoral(rfcContribuyente, insumo);
        } else if (rfcContribuyente.length() == ExpresionesUtil.RFC_LONGITUD) {
            validarRFC(rfcContribuyente, insumo);
        }
    }

    public String valorCelda(HSSFCell cell) {
        String valorCelda = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_BOOLEAN:
                    valorCelda = "";
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    BigDecimal valor = BigDecimal.valueOf(cell.getNumericCellValue());
                    valorCelda = valor.intValue() + "";
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

    public String obtenerMensajeAntecedente(List<String> listaAntecedentes) {
        StringBuilder texto = new StringBuilder();
        short index = 0;
        for (String antecedente : listaAntecedentes) {
            if (!texto.toString().isEmpty()) {
                texto.append("\n");
            }
            texto.append(++index).append(".- ").append(antecedente);
        }
        return texto.toString();
    }

    public boolean validaUnidadAdministrativa(String posibleUnidad, FecetInsumo insumo, List<FececUnidadAdministrativa> listaUnidadAdministrativa) {
        BigDecimal unidad;
        try {
            if (posibleUnidad == null || posibleUnidad.isEmpty()) {
                insumo.setDescripcionRechazo("Es obligatorio ingresar la Unidad Administrativa");
                return false;
            }
            unidad = new BigDecimal(posibleUnidad.trim());
        } catch (Exception e) {
            insumo.setDescripcionRechazo(MSJ_CAMPO_NO_VALIDO);
            return insumo.getIdUnidadAdministrativa() != null;
        }

        if ((unidad != BigDecimal.ZERO)) {
            for (FececUnidadAdministrativa fececUnidadAdministrativa : listaUnidadAdministrativa) {
                if (fececUnidadAdministrativa.getIdUnidadAdministrativa() != null && fececUnidadAdministrativa.getIdUnidadAdministrativa().intValue() == unidad.intValue()) {
                    insumo.setFececUnidadAdministrativa(fececUnidadAdministrativa);
                    insumo.setIdUnidadAdministrativa(insumo.getFececUnidadAdministrativa().getIdUnidadAdministrativa());
                    insumo.setIdArace(insumo.getIdUnidadAdministrativa());
                    break;
                }
            }
            if (insumo.getIdUnidadAdministrativa() == null) {
                insumo.setDescripcionRechazo("Unidad Administrativa no encontrada.");
            }
        } else {
            insumo.setDescripcionRechazo(MSJ_CAMPO_NO_VALIDO);
        }
        return insumo.getIdUnidadAdministrativa() != null;
    }

    public boolean validaSubprograma(String posibleSubprograma, FecetInsumo insumo, List<FececSubprograma> subprogramas) {
        if (!posibleSubprograma.isEmpty()) {
            for (FececSubprograma fececSubprograma : subprogramas) {
                if (fececSubprograma.getClave().equals(posibleSubprograma.trim())) {
                    insumo.setIdSubprograma(fececSubprograma.getIdSubprograma());
                    insumo.setFececSubprograma(fececSubprograma);
                    break;
                }
            }
            if (insumo.getIdSubprograma() == null) {
                insumo.setDescripcionRechazo("Subprograma no encontrado.");
            }
        } else {
            insumo.setDescripcionRechazo("Es obligatorio ingresar el Subprograma");
        }
        return insumo.getIdSubprograma() != null;
    }

    public boolean validaTipoInsumo(String posibleTipoInsumo, FecetInsumo insumo, List<FececTipoInsumo> tipoInsumos) {
        if (!posibleTipoInsumo.isEmpty()) {
            for (FececTipoInsumo tipoInsumo : tipoInsumos) {
                if (tipoInsumo.getIdTipoInsumo().toString().equals(posibleTipoInsumo.trim())) {
                    insumo.setFececTipoInsumo(tipoInsumo);
                    break;
                }
            }
            if (insumo.getFececTipoInsumo() == null) {
                insumo.setDescripcionRechazo("Tipo Insumo no encontrado.");
            }
        } else {
            insumo.setDescripcionRechazo("Es obligatorio ingresar el Tipo Insumo");
        }

        return insumo.getFececTipoInsumo() != null;
    }

    public boolean validaSector(String posibleSector, FecetInsumo insumo, List<FececSector> sectores) {
        if (!posibleSector.isEmpty()) {
            for (FececSector fececSector : sectores) {
                if (fececSector.getIdSector().toString().equals(posibleSector.trim())) {
                    insumo.setIdSector(fececSector.getIdSector());
                    insumo.setFececSector(fececSector);
                    break;
                }
            }
            if (insumo.getIdSector() == null) {
                insumo.setDescripcionRechazo("Sector no encontrado.");
            }
        } else {
            insumo.setDescripcionRechazo("Es obligatorio ingresar el Sector");
        }
        return insumo.getIdSector() != null;
    }

    public boolean validaPrioridad(String posiblePrioridad, FecetInsumo insumo, List<FececPrioridad> prioridades) {
        if (!posiblePrioridad.isEmpty()) {
            for (FececPrioridad prioridad : prioridades) {
                if (prioridad.getIdPrioridad().toString().equals(posiblePrioridad.trim())) {
                    insumo.setIdPrioridad(prioridad.getIdPrioridad());
                    break;
                }
            }
            if (insumo.getIdPrioridad() == null) {
                insumo.setDescripcionRechazo("Prioridad no encontrada.");
            }
        } else {
            insumo.setDescripcionRechazo("Es obligatorio ingresar la Prioridad");
        }
        return insumo.getIdPrioridad() != null;
    }

    public boolean validaFormatoFecha(String fecha, FecetInsumo insumo, boolean isFechaInicio) {
        if (!fecha.isEmpty()) {
            Matcher matcher = PATTERN_FECHA.matcher(fecha);
            if (matcher.matches()) {
                try {
                    Date fechaParse = formatoDDMMYYYY.parse(fecha);
                    if (isFechaInicio) {
                        insumo.setFechaInicioPeriodo(fechaParse);
                        if (fechaParse.compareTo(obtenerFechaHoy()) > 0) {
                            insumo.setDescripcionRechazo("La fecha inicio no puede ser mayor a la actual");
                        }
                    } else {
                        insumo.setFechaFinPeriodo(fechaParse);
                        if (fechaParse.compareTo(obtenerFechaHoy()) > 0) {
                            insumo.setDescripcionRechazo("La fecha fin no puede ser mayor a la actual");
                        }
                    }
                } catch (ParseException e) {
                    diferenciarErrorTipoFechaInvalida(insumo, isFechaInicio, fecha);
                }
            } else {
                diferenciarErrorTipoFechaInvalida(insumo, isFechaInicio, fecha);
            }
        } else {
            diferenciarErrorTipoFechaObligatoria(insumo, isFechaInicio);
        }
        return isFechaInicio ? insumo.getFechaInicioPeriodo() != null : insumo.getFechaFinPeriodo() != null;
    }

    private void diferenciarErrorTipoFechaInvalida(FecetInsumo insumo, boolean isFechaInicio, String fecha) {
        if (isFechaInicio) {
            LOGGER.error("Error al convertir fecha inicio periodo: ".concat(fecha).concat("."));
            insumo.setDescripcionRechazo("Fecha Inicio inv\u00E1lida, sólo se permiten n\u00FAmeros con el formato: dd/mm/aaaa. ");
        } else {
            LOGGER.error("Error al convertir fecha fin periodo: ".concat(fecha).concat("."));
            insumo.setDescripcionRechazo("Fecha Fin inv\u00E1lida, sólo se permiten n\u00FAmeros con el formato: dd/mm/aaaa. ");
        }
    }

    private void diferenciarErrorTipoFechaObligatoria(FecetInsumo insumo, boolean isFechaInicio) {
        if (isFechaInicio) {
            LOGGER.error("Error fecha obligatoria.");
            insumo.setDescripcionRechazo("Es obligatorio ingresar la Fecha Inicio");
        } else {
            LOGGER.error("Error fecha obligatoria.");
            insumo.setDescripcionRechazo("Es obligatorio ingresar la Fecha Fin");
        }
    }

    public boolean validaPeriodoCorrecto(FecetInsumo insumo) {
        boolean resultado = true;
        if (insumo.getFechaInicioPeriodo() == null) {
            resultado = false;
            insumo.setDescripcionRechazo("Se requiere fecha inicio valida");
        } else if (insumo.getFechaInicioPeriodo().compareTo(insumo.getFechaFinPeriodo()) > 0) {
            resultado = false;
            insumo.setDescripcionRechazo("La fecha inicio no puede ser mayor a la fecha fin");
        }
        return resultado;
    }

    public boolean validarDocumentos(String documentos, FecetInsumo insumo, String rutaDocumetos) {
        if (documentos.isEmpty()) {
            insumo.setDescripcionRechazo("El n\u00FAmero de documentos por registro debe ser de 1 a 50.");
        } else {
            insumo.setListaDocumentos(new ArrayList<FecetDocExpInsumo>());
            List<String> archivosList = Arrays.asList(Pattern.compile("\\|").split(documentos));
            File archivoFisico;
            FecetDocExpInsumo expediente;
            int indiceArchivo = 0;
            for (String archivo : archivosList) {
                archivoFisico = new File(rutaDocumetos.concat(archivo.trim()));
                if (archivoFisico.exists() && archivoFisico.isFile()) {
                    expediente = new FecetDocExpInsumo();
                    expediente.setBlnActivo(true);
                    expediente.setFechaCreacion(new Date());
                    expediente.setNombre(archivo.trim());
                    expediente.setRutaArchivo(rutaDocumetos.concat(archivo.trim()));
                    if (existeDocumento(expediente, insumo)) {
                        insumo.setDescripcionRechazo(MSJ_ARCHIVO_DUPLICADO.concat(archivo));
                        break;
                    } else {
                        expediente.setNumDocRegistro(new BigDecimal(++indiceArchivo));
                        insumo.getListaDocumentos().add(expediente);
                    }
                } else {
                    insumo.setDescripcionRechazo(MSJ_ARCHIVO_NO_VALIDO.concat(archivo));
                    break;
                }
            }
        }
        return insumo.getDescripcionRechazo() == null;
    }

    public boolean existeInsumoFormato(FecetInsumo insumo, List<FecetInsumo> listRegistrosCorrectos) {
        boolean validacionUno, validacionDos;
        for (FecetInsumo fecetInsumo : listRegistrosCorrectos) {
            validacionUno = fecetInsumo.getFecetContribuyente().getRfc().equals(insumo.getFecetContribuyente().getRfc())
                    && fecetInsumo.getIdUnidadAdministrativa().equals(insumo.getIdUnidadAdministrativa())
                    && fecetInsumo.getIdSubprograma().equals(insumo.getIdSubprograma());
            validacionDos = fecetInsumo.getIdSector().equals(insumo.getIdSector())
                    && fecetInsumo.getIdPrioridad().equals(insumo.getIdPrioridad())
                    && fecetInsumo.getFechaInicioPeriodo().compareTo(insumo.getFechaInicioPeriodo()) == 0
                    && fecetInsumo.getFechaFinPeriodo().compareTo(insumo.getFechaFinPeriodo()) == 0;
            if (validacionUno && validacionDos) {
                insumo.setDescripcionRechazo(REGISTRO_DUPLICADO_FORMATO);
                break;
            }
        }
        return insumo.getDescripcionRechazo() != null;
    }

    public void checkArgument(boolean condicion) throws NegocioException {
        if (condicion) {
            throw new NegocioException("Error de validacion");
        }
    }

    private void validarRFC(String rfcContribuyente, FecetInsumo insumo) {
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(ExpresionesUtil.RFC_PATTERN_1BLOQUE);
        matcher = pattern.matcher(rfcContribuyente);
        if (!matcher.matches()) {
            insumo.setDescripcionRechazo(INFO_FORMATO);
        } else {
            pattern = Pattern.compile(ExpresionesUtil.RFC_PATTERN_2BLOQUE);
            matcher = pattern.matcher(rfcContribuyente);
            if (!matcher.matches()) {
                insumo.setDescripcionRechazo(INFO_FORMATO);
            } else {
                pattern = Pattern.compile(ExpresionesUtil.RFC_PATTERN_3BLOQUE);
                matcher = pattern.matcher(rfcContribuyente);
                if (!matcher.matches()) {
                    insumo.setDescripcionRechazo(INFO_FORMATO);
                } else {
                    pattern = Pattern.compile(ExpresionesUtil.RFC_PATTERN_4BLOQUE);
                    matcher = pattern.matcher(rfcContribuyente);
                    if (!matcher.matches()) {
                        insumo.setDescripcionRechazo(INFO_FORMATO);
                    }
                }
            }
        }
    }

    private void validarRFCMoral(String rfcContribuyente, FecetInsumo insumo) {
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(ExpresionesUtil.RFCM_PATTERN_1BLOQUE);
        matcher = pattern.matcher(rfcContribuyente);
        if (!matcher.matches()) {
            insumo.setDescripcionRechazo(INFO_FORMATO);
        } else {
            pattern = Pattern.compile(ExpresionesUtil.RFCM_PATTERN_2BLOQUE);
            matcher = pattern.matcher(rfcContribuyente);
            if (!matcher.matches()) {
                insumo.setDescripcionRechazo(INFO_FORMATO);
            } else {
                pattern = Pattern.compile(ExpresionesUtil.RFCM_PATTERN_3BLOQUE);
                matcher = pattern.matcher(rfcContribuyente);
                if (!matcher.matches()) {
                    insumo.setDescripcionRechazo(INFO_FORMATO);
                } else {
                    pattern = Pattern.compile(ExpresionesUtil.RFCM_PATTERN_4BLOQUE);
                    matcher = pattern.matcher(rfcContribuyente);
                    if (!matcher.matches()) {
                        insumo.setDescripcionRechazo(INFO_FORMATO);
                    }
                }
            }
        }
    }

    private boolean existeDocumento(FecetDocExpInsumo expediente, FecetInsumo insumo) {
        boolean resultado = false;
        for (FecetDocExpInsumo expedienteInsumo : insumo.getListaDocumentos()) {
            if (expedienteInsumo.getNombre().equals(expediente.getNombre())) {
                resultado = true;
                break;
            }
        }
        return resultado;
    }

    private Date obtenerFechaHoy() {
        Calendar fecha = Calendar.getInstance();
        fecha.setTime(new Date());
        fecha.set(Calendar.HOUR_OF_DAY, 0);
        fecha.set(Calendar.MINUTE, 0);
        fecha.set(Calendar.SECOND, 0);
        fecha.set(Calendar.MILLISECOND, 0);
        return fecha.getTime();
    }

    public ReporteIncorrectoDto llenarRegistroReporte(FecetInsumo insumo) {
        ReporteIncorrectoDto registro = new ReporteIncorrectoDto();
        registro.setRfc(insumo.getFecetContribuyente().getRfc());
        registro.setUnidadAdministrativa(insumo.getIdUnidadAdministrativa().toString());
        registro.setSubprograma(insumo.getFececSubprograma().getClave());
        registro.setTipoInsumo(insumo.getFececTipoInsumo().getIdTipoInsumo().toString());
        registro.setSector(insumo.getIdSector().toString());
        registro.setPrioridad(insumo.getIdPrioridad().toString());
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(insumo.getFechaInicioPeriodo());
        registro.setFechaInicio(reportDate);
        reportDate = df.format(insumo.getFechaFinPeriodo());
        registro.setFechaFin(reportDate);
        String documentoInsumo = "";
        int pay = 1;
        for (FecetDocExpInsumo documento : insumo.getListaDocumentos()) {
            documentoInsumo = documentoInsumo.concat(documento.getNombre());
            if (pay < insumo.getListaDocumentos().size()) {
                documentoInsumo = documentoInsumo.concat(PAY);
            }
            ++pay;
        }
        registro.setDocumento(documentoInsumo);
        return registro;
    }

}
