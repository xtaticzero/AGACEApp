package mx.gob.sat.siat.feagace.vista.helper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.CargaMasivaPropuestasDTO;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.vista.enumunidadadmon.UnidadAdministrativaEnum;
import mx.gob.sat.siat.feagace.vista.util.ConstantesPropuestasMasivas;

@Component
public class ValidaFechasCargaMasivaHelper implements Serializable {

    private static final long serialVersionUID = 1L;

    public CargaMasivaPropuestasDTO validaFechaPresentacion(Map<String, String> fechas,
            CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO, double prioridadSugerida) {
        Date fecha = null;
        Map<TipoEmpleadoEnum, BigDecimal> unidadesCorreo = new HashMap<TipoEmpleadoEnum, BigDecimal>();
        BigDecimal unidadAdminEmpleado = new BigDecimal(cargaMasivaPropuestasDTO.getFecetPropuesta().getEmpleadoDto()
                .getDetalleEmpleado().get(0).getCentral().getIdArace());
        BigDecimal unidadAdminSeleccionada = cargaMasivaPropuestasDTO.getUnidadAdministrativa();
        if (unidadAdminEmpleado.equals(Constantes.ACPPCE)) {
            if (unidadAdminSeleccionada.equals(UnidadAdministrativaEnum.ACAOCE.getIdUnidad())
                    || unidadAdminSeleccionada.equals(UnidadAdministrativaEnum.ACOECE.getIdUnidad())) {
                if (cargaMasivaPropuestasDTO.getMetodoString().equals(ConstantesPropuestasMasivas.ORG)) {
                    if (fmt(prioridadSugerida).equals("1")) {
                        fecha = isFechaValida(Constantes.FECHA_INFORME_COMITE,
                                fechas.get(String.valueOf(Constantes.FECHA_INFORME_COMITE)), cargaMasivaPropuestasDTO);
                        if (fecha != null && validaFechaIniPeriodo(fecha, cargaMasivaPropuestasDTO,
                                Constantes.FECHA_INFORME_COMITE)) {
                            unidadesCorreo.put(TipoEmpleadoEnum.CENTRAL,
                                    cargaMasivaPropuestasDTO.getUnidadAdministrativa());
                            cargaMasivaPropuestasDTO.setSegundoCaracter(Constantes.I);
                            cargaMasivaPropuestasDTO.getFecetPropuesta().setFechaInforme(fecha);
                            cargaMasivaPropuestasDTO.setValida(true);
                        }
                    } else if (!fmt(prioridadSugerida).equals("1")) {
                        fecha = isFechaValida(Constantes.FECHA_PRESENTACION_COMITE,
                                fechas.get(String.valueOf(Constantes.FECHA_PRESENTACION_COMITE)),
                                cargaMasivaPropuestasDTO);
                        if (fecha != null && validaFechaIniPeriodo(fecha, cargaMasivaPropuestasDTO,
                                Constantes.FECHA_PRESENTACION_COMITE)) {
                            unidadesCorreo.put(TipoEmpleadoEnum.CENTRAL,
                                    cargaMasivaPropuestasDTO.getUnidadAdministrativa());
                            cargaMasivaPropuestasDTO.setSegundoCaracter(Constantes.P);
                            cargaMasivaPropuestasDTO.getFecetPropuesta().setFechaPresentacion(fecha);
                            cargaMasivaPropuestasDTO.setValida(true);
                        }
                    }
                } else {
                    fecha = isFechaValida(Constantes.FECHA_INFORME_COMITE,
                            fechas.get(String.valueOf(Constantes.FECHA_INFORME_COMITE)), cargaMasivaPropuestasDTO);
                    if (fecha != null && validaFechaIniPeriodo(fecha, cargaMasivaPropuestasDTO,
                            Constantes.FECHA_INFORME_COMITE)) {
                        unidadesCorreo.put(TipoEmpleadoEnum.CENTRAL,
                                cargaMasivaPropuestasDTO.getUnidadAdministrativa());
                        cargaMasivaPropuestasDTO.setSegundoCaracter(Constantes.I);
                        cargaMasivaPropuestasDTO.getFecetPropuesta().setFechaInforme(fecha);
                        cargaMasivaPropuestasDTO.setValida(true);
                    }
                }
            } else if (contieneUnidadAdace(unidadAdminSeleccionada)) {
                fecha = isFechaValida(Constantes.FECHA_INFORME_COMITE,
                        fechas.get(String.valueOf(Constantes.FECHA_INFORME_COMITE)), cargaMasivaPropuestasDTO);
                cargaMasivaPropuestasDTO.setExisteProgramador(true);
                if (fecha != null
                        && validaFechaIniPeriodo(fecha, cargaMasivaPropuestasDTO, Constantes.FECHA_INFORME_COMITE)) {
                    unidadesCorreo.put(TipoEmpleadoEnum.CENTRAL, cargaMasivaPropuestasDTO.getUnidadAdministrativa());
                    cargaMasivaPropuestasDTO.setSegundoCaracter(Constantes.I);
                    cargaMasivaPropuestasDTO.getFecetPropuesta().setFechaInforme(fecha);
                    cargaMasivaPropuestasDTO.setValida(true);

                }
            }

        } else if (contieneUnidadAdace(unidadAdminEmpleado)) {
            if (cargaMasivaPropuestasDTO.getMetodoString().equals(ConstantesPropuestasMasivas.ORG)) {
                if (fmt(prioridadSugerida).equals("1")) {
                    fecha = isFechaValida(Constantes.FECHA_INFORME_COMITE_REGIONAL,
                            fechas.get(String.valueOf(Constantes.FECHA_INFORME_COMITE_REGIONAL)),
                            cargaMasivaPropuestasDTO);
                    if (fecha != null && validaFechaIniPeriodo(fecha, cargaMasivaPropuestasDTO,
                            Constantes.FECHA_INFORME_COMITE_REGIONAL)) {
                        unidadesCorreo.put(TipoEmpleadoEnum.CENTRAL,
                                cargaMasivaPropuestasDTO.getUnidadAdministrativa());
                        cargaMasivaPropuestasDTO.setSegundoCaracter(Constantes.I);
                        cargaMasivaPropuestasDTO.getFecetPropuesta().setFechaInforme(fecha);
                        cargaMasivaPropuestasDTO.setValida(true);
                    }
                } else if (!fmt(prioridadSugerida).equals("1")) {
                    fecha = isFechaValida(Constantes.FECHA_PRESENTACION_COMITE_REGIONAL,
                            fechas.get(String.valueOf(Constantes.FECHA_PRESENTACION_COMITE_REGIONAL)),
                            cargaMasivaPropuestasDTO);
                    if (fecha != null && validaFechaIniPeriodo(fecha, cargaMasivaPropuestasDTO,
                            Constantes.FECHA_PRESENTACION_COMITE_REGIONAL)) {
                        unidadesCorreo.put(TipoEmpleadoEnum.CENTRAL,
                                cargaMasivaPropuestasDTO.getUnidadAdministrativa());
                        cargaMasivaPropuestasDTO.setSegundoCaracter(Constantes.P);
                        cargaMasivaPropuestasDTO.getFecetPropuesta().setFechaPresentacion(fecha);
                        cargaMasivaPropuestasDTO.setValida(true);
                    }
                }
            } else {
                fecha = isFechaValida(Constantes.FECHA_INFORME_COMITE_REGIONAL,
                        fechas.get(String.valueOf(Constantes.FECHA_INFORME_COMITE_REGIONAL)), cargaMasivaPropuestasDTO);
                if (fecha != null && validaFechaIniPeriodo(fecha, cargaMasivaPropuestasDTO,
                        Constantes.FECHA_INFORME_COMITE_REGIONAL)) {
                    unidadesCorreo.put(TipoEmpleadoEnum.CENTRAL, cargaMasivaPropuestasDTO.getUnidadAdministrativa());
                    cargaMasivaPropuestasDTO.setSegundoCaracter(Constantes.I);
                    cargaMasivaPropuestasDTO.getFecetPropuesta().setFechaInforme(fecha);
                    cargaMasivaPropuestasDTO.setValida(true);
                }
            }
        }
        cargaMasivaPropuestasDTO.setUnidadesCorreo(unidadesCorreo);
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
            if (unidadAdminSeleccionada.equals(UnidadAdministrativaEnum.ACAOCE.getIdUnidad())
                    || unidadAdminSeleccionada.equals(UnidadAdministrativaEnum.ACOECE.getIdUnidad())) {
                if (validaFechaCapturada(fechas.get(String.valueOf(Constantes.FECHA_INFORME_COMITE_REGIONAL_CI)))) {
                    fecha = isFechaValida(Constantes.FECHA_INFORME_COMITE_CI,
                            fechas.get(String.valueOf(Constantes.FECHA_INFORME_COMITE_CI)), cargaMasivaPropuestasDTO);
                    if (fecha != null && validaFechaIniPeriodo(fecha, cargaMasivaPropuestasDTO,
                            Constantes.FECHA_INFORME_COMITE_CI)) {
                        unidadesCorreo.put(TipoEmpleadoEnum.CENTRAL,
                                cargaMasivaPropuestasDTO.getUnidadAdministrativa());
                        cargaMasivaPropuestasDTO.setSegundoCaracter(Constantes.I);
                        cargaMasivaPropuestasDTO.getFecetPropuesta().setFechaInforme(fecha);
                        cargaMasivaPropuestasDTO.setValida(true);
                    }
                } else {
                    cargaMasivaPropuestasDTO.setCell(Constantes.FECHA_INFORME_COMITE_REGIONAL_CI);
                    cargaMasivaPropuestasDTO.setDescripcionError(ConstantesPropuestasMasivas.MSJ_CAMPO_NO_VALIDO);
                    cargaMasivaPropuestasDTO.setValida(false);
                }
            } else if (contieneUnidadAdace(unidadAdminSeleccionada)) {
                if (validaFechaCapturada(fechas.get(String.valueOf(Constantes.FECHA_INFORME_COMITE_REGIONAL_CI)))) {
                    fecha = isFechaValida(Constantes.FECHA_INFORME_COMITE_CI,
                            fechas.get(String.valueOf(Constantes.FECHA_INFORME_COMITE_CI)), cargaMasivaPropuestasDTO);
                    if (fecha != null && validaFechaIniPeriodo(fecha, cargaMasivaPropuestasDTO,
                            Constantes.FECHA_INFORME_COMITE_CI)) {
                        cargaMasivaPropuestasDTO.setExisteProgramador(true);
                        unidadesCorreo.put(TipoEmpleadoEnum.CENTRAL,
                                cargaMasivaPropuestasDTO.getUnidadAdministrativa());
                        cargaMasivaPropuestasDTO.setSegundoCaracter(Constantes.I);
                        cargaMasivaPropuestasDTO.getFecetPropuesta().setFechaInforme(fecha);
                        cargaMasivaPropuestasDTO.setValida(true);
                    }
                } else {
                    cargaMasivaPropuestasDTO.setCell(Constantes.FECHA_INFORME_COMITE_REGIONAL_CI);
                    cargaMasivaPropuestasDTO.setDescripcionError(ConstantesPropuestasMasivas.MSJ_CAMPO_NO_VALIDO);
                    cargaMasivaPropuestasDTO.setValida(false);
                }
            }

        } else if (contieneUnidadAdace(unidadAdminEmpleado)) {
            if (validaFechaCapturada(fechas.get(String.valueOf(Constantes.FECHA_INFORME_COMITE_CI)))) {
                fecha = isFechaValida(Constantes.FECHA_INFORME_COMITE_REGIONAL_CI,
                        fechas.get(String.valueOf(Constantes.FECHA_INFORME_COMITE_REGIONAL_CI)),
                        cargaMasivaPropuestasDTO);
                if (fecha != null
                        && validaFechaIniPeriodo(fecha, cargaMasivaPropuestasDTO, Constantes.FECHA_INFORME_COMITE_CI)) {
                    unidadesCorreo.put(TipoEmpleadoEnum.CENTRAL, cargaMasivaPropuestasDTO.getUnidadAdministrativa());
                    cargaMasivaPropuestasDTO.setSegundoCaracter(Constantes.I);
                    cargaMasivaPropuestasDTO.getFecetPropuesta().setFechaInforme(fecha);
                    cargaMasivaPropuestasDTO.setValida(true);
                }
            } else {
                cargaMasivaPropuestasDTO.setCell(Constantes.FECHA_INFORME_COMITE_CI);
                cargaMasivaPropuestasDTO.setDescripcionError(ConstantesPropuestasMasivas.MSJ_CAMPO_NO_VALIDO);
                cargaMasivaPropuestasDTO.setValida(false);
            }
        }
        cargaMasivaPropuestasDTO.setUnidadesCorreo(unidadesCorreo);
        return cargaMasivaPropuestasDTO;
    }

    private static Date isFechaValida(int numCelda, String fecha, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO) {
        Pattern pattern = Pattern.compile(ConstantesPropuestasMasivas.DATE_PATTERN);
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

    public boolean contieneUnidadAdace(BigDecimal unidad) {
        if (!unidad.equals(UnidadAdministrativaEnum.ACAOCE.getIdUnidad())
                && !unidad.equals(UnidadAdministrativaEnum.ACOECE.getIdUnidad())) {
            for (UnidadAdministrativaEnum c : UnidadAdministrativaEnum.values()) {
                if (c.getIdUnidad().equals(unidad)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean validaFechaCapturada(String fecha) {

        boolean isValida = false;

        if (fecha != null && fecha.isEmpty()) {
            isValida = true;
        }

        return isValida;
    }

    public static String fmt(double d) {
        if (d == (long) d) {
            return String.format("%d", (long) d);
        } else {
            return String.format("%s", d);
        }
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

}
