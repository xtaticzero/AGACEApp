package mx.gob.sat.siat.feagace.vista.helper;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.primefaces.model.UploadedFile;
import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.CargaMasivaPropuestasDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.propuestas.carga.CargaMasivaPropuestasService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilPropuestas;
import mx.gob.sat.siat.feagace.vista.util.ConstantesPropuestasMasivas;

@Component
public class CargaMasivaPropuestasMBHelper extends BaseManagedBean {

    @SuppressWarnings("compatibility:4272142618530344670")
    private static final long serialVersionUID = 1L;

    public void validaPrioridadSugerida(double prioridad, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO, CargaMasivaPropuestasService cargaMasivaPropuestasService) {

        String prioridadSugerida = fmt(prioridad);

        if (!prioridadSugerida.trim().equals("")) {
            if (!validaPrioridadSugerida(prioridadSugerida.trim(), cargaMasivaPropuestasService)) {
                cargaMasivaPropuestasDTO.getFecetPropuesta().setPrioridadSugerida(prioridadSugerida.trim());
                cargaMasivaPropuestasDTO.setDescripcionError(ConstantesPropuestasMasivas.MSJ_CAMPO_NO_VALIDO);
                cargaMasivaPropuestasDTO.setValida(false);
            } else {
                cargaMasivaPropuestasDTO.getFecetPropuesta().setPrioridadSugerida(prioridadSugerida.trim());
                cargaMasivaPropuestasDTO.setValida(true);
            }
        } else {
            cargaMasivaPropuestasDTO.getFecetPropuesta().setPrioridadSugerida(prioridadSugerida.trim());
            cargaMasivaPropuestasDTO.setDescripcionError(ConstantesPropuestasMasivas.MSJ_CAMPO_NO_VALIDO);
            cargaMasivaPropuestasDTO.setValida(false);
        }
    }

    public CargaMasivaPropuestasDTO validaPeriodoPropuesto(String cellFechaIni, String cellFechaFin,
            CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO) {
        if (cellFechaIni != null && cellFechaFin != null) {
            Date fechaIni = obetenerFecha(cellFechaIni, cargaMasivaPropuestasDTO, Constantes.FECHA_INICIO_PERIODO);
            if (fechaIni != null) {
                Date fechaFin = obetenerFecha(cellFechaFin, cargaMasivaPropuestasDTO, Constantes.FECHA_FIN_PERIODO);
                if (fechaFin != null) {
                    if ((fechaIni.compareTo(fechaFin) <= 0)) {
                        if ((fechaIni.compareTo(getSystemDate()) <= 0) && (fechaFin.compareTo(getSystemDate()) <= 0)) {
                            cargaMasivaPropuestasDTO.setValida(true);
                            cargaMasivaPropuestasDTO.getFecetPropuesta().setFechaInicioPeriodo(fechaIni);
                            cargaMasivaPropuestasDTO.getFecetPropuesta().setFechaFinPeriodo(fechaFin);
                            return cargaMasivaPropuestasDTO;
                        } else {
                            cargaMasivaPropuestasDTO.setValida(false);
                            cargaMasivaPropuestasDTO.setCell(Constantes.FECHA_FIN_PERIODO);
                            cargaMasivaPropuestasDTO
                                    .setDescripcionError("La fecha fin no puede ser mayor o igual a la actual");
                            return cargaMasivaPropuestasDTO;
                        }

                    } else {
                        cargaMasivaPropuestasDTO.setValida(false);
                        cargaMasivaPropuestasDTO.setCell(Constantes.FECHA_INICIO_PERIODO);
                        cargaMasivaPropuestasDTO
                                .setDescripcionError("La fecha de Inicio no puede ser mayor que la de Fin.");
                        return cargaMasivaPropuestasDTO;
                    }
                }
            }

        }
        return cargaMasivaPropuestasDTO;
    }

    public CargaMasivaPropuestasDTO validaPeriodoPropuestoCI(String cellFechaIni, String cellFechaFin,
            CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO) {
        if (cellFechaIni != null && cellFechaFin != null) {
            Date fechaIni = obetenerFecha(cellFechaIni, cargaMasivaPropuestasDTO, Constantes.FECHA_INICIO_PERIODO_CI);
            if (fechaIni != null) {
                Date fechaFin = obetenerFecha(cellFechaFin, cargaMasivaPropuestasDTO, Constantes.FECHA_FIN_PERIODO_CI);
                if (fechaFin != null) {
                    if (fechaIni.compareTo(fechaFin) < 0) {
                        if (fechaIni.compareTo(getSystemDate()) < 0 && fechaFin.compareTo(getSystemDate()) < 0) {
                            cargaMasivaPropuestasDTO.setValida(true);
                            cargaMasivaPropuestasDTO.getFecetPropuesta().setFechaInicioPeriodo(fechaIni);
                            cargaMasivaPropuestasDTO.getFecetPropuesta().setFechaFinPeriodo(fechaFin);
                            return cargaMasivaPropuestasDTO;
                        } else {
                            cargaMasivaPropuestasDTO.setValida(false);
                            cargaMasivaPropuestasDTO.setCell(Constantes.FECHA_FIN_PERIODO_CI);
                            cargaMasivaPropuestasDTO
                                    .setDescripcionError("La fecha fin no puede ser mayor o igual a la actual");
                            return cargaMasivaPropuestasDTO;
                        }

                    } else {
                        cargaMasivaPropuestasDTO.setValida(false);
                        cargaMasivaPropuestasDTO.setCell(Constantes.FECHA_INICIO_PERIODO_CI);
                        cargaMasivaPropuestasDTO
                                .setDescripcionError("La fecha de inicio no puede ser mayor o igual que la de fin.");
                        return cargaMasivaPropuestasDTO;
                    }
                }
            }

        }
        return cargaMasivaPropuestasDTO;
    }

    private Date obetenerFecha(String cell, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO, int num) {
        Pattern pattern = Pattern.compile(ConstantesPropuestasMasivas.DATE_PATTERN);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaComite = null;
        if (cell != null) {
            Matcher matcher = pattern.matcher(cell);
            if (matcher.matches()) {
                try {
                    fechaComite = df.parse(cell);
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

    private static java.util.Date getSystemDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        return cal.getTime();
    }

    public boolean validaDocumentos(List<String> archivosList, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO,
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
                } else {
                    return false;
                }
            } else {
                cargaMasivaPropuestasDTO
                        .setDescripcionError(ConstantesPropuestasMasivas.MSJ_ARCHIVO_NO_VALIDO + " " + nombreArchivo);
                return false;
            }
        }
        cargaMasivaPropuestasDTO.getFecetPropuesta().setListaDocumentos(expedienteList);
        return true;
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
                } else {
                    return false;
                }
            } else {
                cargaMasivaPropuestasDTO
                        .setDescripcionError(ConstantesPropuestasMasivas.MSJ_ARCHIVO_NO_VALIDO + " " + nombreArchivo);
                return false;
            }
        }
        cargaMasivaPropuestasDTO.getFecetPropuesta().setListaDocumentos(expedienteList);
        return true;
    }

    public Boolean validateSizeFile(final UploadedFile archivo) {
        if (archivo.getSize() > 0L && archivo.getSize() <= Constantes.FILE_SIZE) {
            return true;
        } else {
            if (archivo.getSize() >= Constantes.FILE_SIZE) {
                addErrorMessage(null, ConstantesPropuestasMasivas.MSJ_ERROR_ARCHIVO,
                        ConstantesPropuestasMasivas.MSJ_ARCHIVO_GRANDE);
            }
        }
        return false;
    }

    public static boolean validaExistenciaFolioDoc(String folioDoc) {
        String directorioFolio = Constantes.DIRECTORIO_CARGA_DOCUMENTOS + folioDoc + "/";
        return CargaArchivoUtilPropuestas.verificarExistenciaDirectorio(directorioFolio);
    }

    private boolean validaPrioridadSugerida(String prioridadSugerida, CargaMasivaPropuestasService cargaMasivaPropuestasService) {

        boolean isPrioridadValida = false;

        if (cargaMasivaPropuestasService.validaPrioridad(prioridadSugerida) != null && !cargaMasivaPropuestasService.validaPrioridad(prioridadSugerida).isEmpty()) {
            isPrioridadValida = true;
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
                        ConstantesPropuestasMasivas.MSJ_ARCHIVO_DUPLICADO + " " + docExpediente.getNombre());
                return false;
            }
        }

        return true;
    }
}
