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
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Component;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececCausaProgramacion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececConcepto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececPrioridad;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.CargaMasivaPropuestasDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAraceEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

@Component
public class CargaMasivaPropuestaHelper implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String DATE_PATTERN = "^(?:(?:0?[1-9]|1\\d|2[0-8])(\\/|-)(?:0?[1-9]|1[0-2]))(\\/|-)(?:[1-9]\\d\\d\\d|\\d[1-9]\\d\\d|\\d\\d[1-9]\\d|\\d\\d\\d[1-9])$|^(?:(?:31(\\/|-)(?:0?[13578]|1[02]))|(?:(?:29|30)(\\/|-)(?:0?[1,3-9]|1[0-2])))(\\/|-)(?:[1-9]\\d\\d\\d|\\d[1-9]\\d\\d|\\d\\d[1-9]\\d|\\d\\d\\d[1-9])$|^(29(\\/|-)0?2)(\\/|-)(?:(?:0[48]00|[13579][26]00|[2468][048]00)|(?:\\d\\d)?(?:0[48]|[2468][048]|[13579][26]))$";
    private static final BigDecimal IMPUESTO_NA = new BigDecimal(18L);
    private static final String MSJ_CAMPO_NO_VALIDO = "El campo no es un valor valido";
    private static final String MSJ_CAMPO_IMPUESTO_DUPLICADO = "No se puede adjuntar un impuesto duplicado, favor de verificar";
    private static final String MSJ_ERROR_IMPUESTO_NA = "El campo debe contener un impuesto diferente a N/A";
    private static final String MSJ_IMPUESTO_VALIDO = "Impuesto correcto";
    private static final String MSJ_IMPUESTO_INVALIDO = "Impuesto incorrecto";

    public CargaMasivaPropuestasDTO validaUnidadAdministrativa(BigDecimal cell,
            CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO) {
        boolean unidadValida = true;
        cargaMasivaPropuestasDTO.setDescripcionError("");
        BigDecimal unidadEmpleado = new BigDecimal(cargaMasivaPropuestasDTO.getFecetPropuesta().getEmpleadoDto()
                .getDetalleEmpleado().get(0).getCentral().getIdArace());
        if (unidadEmpleado.equals(new BigDecimal(TipoAraceEnum.ACPPCE.getId()))) {
            if (cell.equals(new BigDecimal(TipoAraceEnum.ACAOCE.getId()))) {
                cargaMasivaPropuestasDTO.setUnidadAdministrativa(new BigDecimal(TipoAraceEnum.ACAOCE.getId()));
            } else if (cell.equals(new BigDecimal(TipoAraceEnum.ACOECE.getId()))) {
                cargaMasivaPropuestasDTO.setUnidadAdministrativa(new BigDecimal(TipoAraceEnum.ACOECE.getId()));
            } else if (cell.equals(new BigDecimal(TipoAraceEnum.ADACE_PACIFICO_NORTE.getId()))) {
                cargaMasivaPropuestasDTO
                        .setUnidadAdministrativa(new BigDecimal(TipoAraceEnum.ADACE_PACIFICO_NORTE.getId()));
            } else if (cell.equals(new BigDecimal(TipoAraceEnum.ADACE_NORTE_CENTRO.getId()))) {
                cargaMasivaPropuestasDTO
                        .setUnidadAdministrativa(new BigDecimal(TipoAraceEnum.ADACE_NORTE_CENTRO.getId()));
            } else if (cell.equals(new BigDecimal(TipoAraceEnum.ADACE_NOROESTE.getId()))) {
                cargaMasivaPropuestasDTO.setUnidadAdministrativa(new BigDecimal(TipoAraceEnum.ADACE_NOROESTE.getId()));
            } else if (cell.equals(new BigDecimal(TipoAraceEnum.ADACE_OCCIDENTE.getId()))) {
                cargaMasivaPropuestasDTO.setUnidadAdministrativa(new BigDecimal(TipoAraceEnum.ADACE_OCCIDENTE.getId()));
            } else if (cell.equals(new BigDecimal(TipoAraceEnum.ADACE_CENTRO.getId()))) {
                cargaMasivaPropuestasDTO.setUnidadAdministrativa(new BigDecimal(TipoAraceEnum.ADACE_CENTRO.getId()));
            } else if (cell.equals(new BigDecimal(TipoAraceEnum.ADACE_SUR.getId()))) {
                cargaMasivaPropuestasDTO.setUnidadAdministrativa(new BigDecimal(TipoAraceEnum.ADACE_SUR.getId()));
            } else {
                unidadValida = false;
                cargaMasivaPropuestasDTO.setDescripcionError("Unidad Administrativa no encontrada");
            }
        } else if (contieneUnidad(cell) && (cell.equals(BigDecimal.ZERO) || unidadEmpleado.equals(cell))) {
            cargaMasivaPropuestasDTO.setUnidadAdministrativa(unidadEmpleado);
        } else {
            unidadValida = false;
            cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
        }
        cargaMasivaPropuestasDTO.setValida(unidadValida);
        return cargaMasivaPropuestasDTO;
    }

    public boolean contieneUnidad(BigDecimal unidadSeleccionada) {
        return !unidadSeleccionada.equals(new BigDecimal(TipoAraceEnum.ACAOCE.getId()))
                && !unidadSeleccionada.equals(new BigDecimal(TipoAraceEnum.ACOECE.getId()));
    }

    public CargaMasivaPropuestasDTO validaMetodo(String cell, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO,
            List<FececMetodo> listaMetodo) {
        boolean metodoValido = false;
        cargaMasivaPropuestasDTO.setDescripcionError("");
        for (FececMetodo metodo : listaMetodo) {
            if (metodo.getAbreviatura().equals(cell) && !TipoMetodoEnum.MCA.getSiglas().equals(cell)) {
                metodoValido = true;
                cargaMasivaPropuestasDTO.getFecetPropuesta().setIdMetodo(metodo.getIdMetodo());
                cargaMasivaPropuestasDTO.setMetodoString(metodo.getAbreviatura());
                break;
            }
        }
        if (!metodoValido) {
            cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
        }
        cargaMasivaPropuestasDTO.setValida(metodoValido);
        return cargaMasivaPropuestasDTO;
    }

    public CargaMasivaPropuestasDTO validaTipoRevision(String cell, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO,
            List<FececRevision> listaRevision) {
        boolean revisionValido = false;
        cargaMasivaPropuestasDTO.setDescripcionError("");
        if (cargaMasivaPropuestasDTO.getFecetPropuesta().getIdMetodo() != null && cargaMasivaPropuestasDTO
                .getFecetPropuesta().getIdMetodo().equals(new BigDecimal(TipoMetodoEnum.ORG.getId()))) {
            for (FececRevision revision : listaRevision) {
                if (revision.getDescripcion().equals(cell)) {
                    revisionValido = true;
                    cargaMasivaPropuestasDTO.setValida(revisionValido);
                    cargaMasivaPropuestasDTO.getFecetPropuesta().setIdRevision(revision.getIdRevision());
                    cargaMasivaPropuestasDTO.setTipoRevision(revision.getDescripcion());
                    break;
                }
            }
            if (!revisionValido) {
                cargaMasivaPropuestasDTO.setValida(revisionValido);
                cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
            }
        } else {
            cargaMasivaPropuestasDTO.setValida(revisionValido);
            cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
        }

        return cargaMasivaPropuestasDTO;
    }

    public CargaMasivaPropuestasDTO campoOpcionalTipoRevision(HSSFCell celda,
            CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO) {
        boolean revisionValido = false;

        cargaMasivaPropuestasDTO.setDescripcionError("");
        if (cargaMasivaPropuestasDTO.getFecetPropuesta().getIdMetodo() != null && !cargaMasivaPropuestasDTO
                .getFecetPropuesta().getIdMetodo().equals(new BigDecimal(TipoMetodoEnum.ORG.getId()))) {
            if (celda != null && celda.getCellType() == Cell.CELL_TYPE_BLANK) {
                revisionValido = true;
            } else {
                if (celda == null) {
                    revisionValido = true;
                } else {
                    revisionValido = false;
                    cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
                }
            }
        } else {
            cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
        }
        cargaMasivaPropuestasDTO.setValida(revisionValido);
        return cargaMasivaPropuestasDTO;
    }

    public CargaMasivaPropuestasDTO validaFechaPresentacion(Map<String, String> fechas,
            CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO, double prioridadSugerida,
            List<CargaMasivaPropuestasDTO> listaErrores, int fila, List<FececPrioridad> listaPrioridad) {
        Date fecha = null;
        Map<TipoEmpleadoEnum, BigDecimal> unidadesCorreo = new HashMap<TipoEmpleadoEnum, BigDecimal>();
        BigDecimal unidadAdminEmpleado = new BigDecimal(cargaMasivaPropuestasDTO.getFecetPropuesta().getEmpleadoDto()
                .getDetalleEmpleado().get(0).getCentral().getIdArace());
        BigDecimal unidadAdminSeleccionada = cargaMasivaPropuestasDTO.getUnidadAdministrativa();
        cargaMasivaPropuestasDTO.setDescripcionError("");
        List<Integer> listaFechas = new ArrayList<Integer>();

        cargaMasivaPropuestasDTO.getFecetPropuesta().getIdMetodo();
        cargaMasivaPropuestasDTO.getMetodoString();
        if (unidadAdminSeleccionada == null || cargaMasivaPropuestasDTO.getMetodoString() == null || cargaMasivaPropuestasDTO.getMetodoString().equals("")) {
            return cargaMasivaPropuestasDTO;
        }

        if (unidadAdminEmpleado.equals(Constantes.ACPPCE)) {
            if (unidadAdminSeleccionada.equals(new BigDecimal(TipoAraceEnum.ACAOCE.getId()))
                    || unidadAdminSeleccionada.equals(new BigDecimal(TipoAraceEnum.ACOECE.getId()))) {
                if (cargaMasivaPropuestasDTO.getMetodoString().equals("ORG")) {
                    Integer prioridad = (int) prioridadSugerida;
                    if (prioridad == 1) {
                        fecha = isFechaValida(Constantes.FECHA_INFORME_COMITE,
                                fechas.get(String.valueOf(Constantes.FECHA_INFORME_COMITE)), cargaMasivaPropuestasDTO);
                        listaFechas = new ArrayList<Integer>();
                        listaFechas.add(Constantes.FECHA_PRESENTACION_COMITE);
                        listaFechas.add(Constantes.FECHA_INFORME_COMITE_REGIONAL);
                        listaFechas.add(Constantes.FECHA_PRESENTACION_COMITE_REGIONAL);
                        verificarFechaInformeCentral(fechas, listaErrores, fila, listaFechas);

                        if (fecha != null && validaFechaIniPeriodo(fecha, cargaMasivaPropuestasDTO,
                                Constantes.FECHA_INFORME_COMITE)) {
                            unidadesCorreo.put(TipoEmpleadoEnum.CONSULTOR_INSUMOS,
                                    cargaMasivaPropuestasDTO.getUnidadAdministrativa());
                            cargaMasivaPropuestasDTO.setSegundoCaracter(Constantes.I);
                            cargaMasivaPropuestasDTO.getFecetPropuesta().setFechaInforme(fecha);
                            cargaMasivaPropuestasDTO.setValida(true);
                        }
                    } else if (prioridad > 1) {
                        fecha = isFechaValida(Constantes.FECHA_PRESENTACION_COMITE,
                                fechas.get(String.valueOf(Constantes.FECHA_PRESENTACION_COMITE)),
                                cargaMasivaPropuestasDTO);

                        listaFechas = new ArrayList<Integer>();
                        listaFechas.add(Constantes.FECHA_INFORME_COMITE);
                        listaFechas.add(Constantes.FECHA_INFORME_COMITE_REGIONAL);
                        listaFechas.add(Constantes.FECHA_PRESENTACION_COMITE_REGIONAL);
                        verificarFechaInformeCentral(fechas, listaErrores, fila, listaFechas);

                        if (fecha != null && validaFechaIniPeriodo(fecha, cargaMasivaPropuestasDTO,
                                Constantes.FECHA_PRESENTACION_COMITE)) {
                            unidadesCorreo.put(TipoEmpleadoEnum.CONSULTOR_INSUMOS,
                                    cargaMasivaPropuestasDTO.getUnidadAdministrativa());
                            cargaMasivaPropuestasDTO.setSegundoCaracter(Constantes.P);
                            cargaMasivaPropuestasDTO.getFecetPropuesta().setFechaPresentacion(fecha);
                            cargaMasivaPropuestasDTO.setValida(true);
                        }
                    }
                } else if (cargaMasivaPropuestasDTO.getMetodoString().equals("EHO")
                        || cargaMasivaPropuestasDTO.getMetodoString().equals("REE")
                        || cargaMasivaPropuestasDTO.getMetodoString().equals("UCA")) {
                    fecha = isFechaValida(Constantes.FECHA_INFORME_COMITE,
                            fechas.get(String.valueOf(Constantes.FECHA_INFORME_COMITE)), cargaMasivaPropuestasDTO);

                    listaFechas = new ArrayList<Integer>();
                    listaFechas.add(Constantes.FECHA_PRESENTACION_COMITE);
                    listaFechas.add(Constantes.FECHA_INFORME_COMITE_REGIONAL);
                    listaFechas.add(Constantes.FECHA_PRESENTACION_COMITE_REGIONAL);
                    verificarFechaInformeCentral(fechas, listaErrores, fila, listaFechas);

                    if (fecha != null && validaFechaIniPeriodo(fecha, cargaMasivaPropuestasDTO,
                            Constantes.FECHA_INFORME_COMITE)) {
                        unidadesCorreo.put(TipoEmpleadoEnum.CONSULTOR_INSUMOS,
                                cargaMasivaPropuestasDTO.getUnidadAdministrativa());
                        cargaMasivaPropuestasDTO.setSegundoCaracter(Constantes.I);
                        cargaMasivaPropuestasDTO.getFecetPropuesta().setFechaInforme(fecha);
                        cargaMasivaPropuestasDTO.setValida(true);
                    }
                }
            } else if (contieneUnidadAdace(unidadAdminSeleccionada)) {
                fecha = isFechaValida(Constantes.FECHA_INFORME_COMITE,
                        fechas.get(String.valueOf(Constantes.FECHA_INFORME_COMITE)), cargaMasivaPropuestasDTO);

                listaFechas = new ArrayList<Integer>();
                listaFechas.add(Constantes.FECHA_PRESENTACION_COMITE);
                listaFechas.add(Constantes.FECHA_INFORME_COMITE_REGIONAL);
                listaFechas.add(Constantes.FECHA_PRESENTACION_COMITE_REGIONAL);
                verificarFechaInformeCentral(fechas, listaErrores, fila, listaFechas);

                cargaMasivaPropuestasDTO.setExisteProgramador(true);
                if (fecha != null
                        && validaFechaIniPeriodo(fecha, cargaMasivaPropuestasDTO, Constantes.FECHA_INFORME_COMITE)) {
                    unidadesCorreo.put(TipoEmpleadoEnum.CONSULTOR_INSUMOS, cargaMasivaPropuestasDTO.getUnidadAdministrativa());
                    cargaMasivaPropuestasDTO.setSegundoCaracter(Constantes.I);
                    cargaMasivaPropuestasDTO.getFecetPropuesta().setFechaInforme(fecha);
                    cargaMasivaPropuestasDTO.setValida(true);

                }
            }

        } else if (contieneUnidadAdace(unidadAdminEmpleado)) {
            if (cargaMasivaPropuestasDTO.getMetodoString().equals("ORG")) {
                Integer prioridad = (int) prioridadSugerida;
                if (prioridad == 1) {
                    fecha = isFechaValida(Constantes.FECHA_INFORME_COMITE_REGIONAL,
                            fechas.get(String.valueOf(Constantes.FECHA_INFORME_COMITE_REGIONAL)),
                            cargaMasivaPropuestasDTO);
                    listaFechas = new ArrayList<Integer>();
                    listaFechas.add(Constantes.FECHA_PRESENTACION_COMITE);
                    listaFechas.add(Constantes.FECHA_INFORME_COMITE);
                    listaFechas.add(Constantes.FECHA_PRESENTACION_COMITE_REGIONAL);
                    verificarFechaInformeCentral(fechas, listaErrores, fila, listaFechas);

                    if (fecha != null && validaFechaIniPeriodo(fecha, cargaMasivaPropuestasDTO,
                            Constantes.FECHA_INFORME_COMITE_REGIONAL)) {
                        unidadesCorreo.put(TipoEmpleadoEnum.CONSULTOR_INSUMOS,
                                cargaMasivaPropuestasDTO.getUnidadAdministrativa());
                        cargaMasivaPropuestasDTO.setSegundoCaracter(Constantes.I);
                        cargaMasivaPropuestasDTO.getFecetPropuesta().setFechaInforme(fecha);
                        cargaMasivaPropuestasDTO.setValida(true);
                    }
                } else if (prioridad > 1) {
                    fecha = isFechaValida(Constantes.FECHA_PRESENTACION_COMITE_REGIONAL,
                            fechas.get(String.valueOf(Constantes.FECHA_PRESENTACION_COMITE_REGIONAL)),
                            cargaMasivaPropuestasDTO);

                    listaFechas = new ArrayList<Integer>();
                    listaFechas.add(Constantes.FECHA_PRESENTACION_COMITE);
                    listaFechas.add(Constantes.FECHA_INFORME_COMITE);
                    listaFechas.add(Constantes.FECHA_INFORME_COMITE_REGIONAL);
                    verificarFechaInformeCentral(fechas, listaErrores, fila, listaFechas);

                    if (fecha != null && validaFechaIniPeriodo(fecha, cargaMasivaPropuestasDTO,
                            Constantes.FECHA_PRESENTACION_COMITE_REGIONAL)) {
                        unidadesCorreo.put(TipoEmpleadoEnum.CONSULTOR_INSUMOS,
                                cargaMasivaPropuestasDTO.getUnidadAdministrativa());
                        cargaMasivaPropuestasDTO.setSegundoCaracter(Constantes.P);
                        cargaMasivaPropuestasDTO.getFecetPropuesta().setFechaPresentacion(fecha);
                        cargaMasivaPropuestasDTO.setValida(true);
                    }
                }
            } else if (cargaMasivaPropuestasDTO.getMetodoString().equals("EHO")
                    || cargaMasivaPropuestasDTO.getMetodoString().equals("REE")
                    || cargaMasivaPropuestasDTO.getMetodoString().equals("UCA")) {
                fecha = isFechaValida(Constantes.FECHA_INFORME_COMITE_REGIONAL,
                        fechas.get(String.valueOf(Constantes.FECHA_INFORME_COMITE_REGIONAL)), cargaMasivaPropuestasDTO);

                listaFechas = new ArrayList<Integer>();
                listaFechas.add(Constantes.FECHA_PRESENTACION_COMITE);
                listaFechas.add(Constantes.FECHA_INFORME_COMITE);
                listaFechas.add(Constantes.FECHA_PRESENTACION_COMITE_REGIONAL);
                verificarFechaInformeCentral(fechas, listaErrores, fila, listaFechas);

                if (fecha != null && validaFechaIniPeriodo(fecha, cargaMasivaPropuestasDTO,
                        Constantes.FECHA_INFORME_COMITE_REGIONAL)) {
                    unidadesCorreo.put(TipoEmpleadoEnum.CONSULTOR_INSUMOS, cargaMasivaPropuestasDTO.getUnidadAdministrativa());
                    cargaMasivaPropuestasDTO.setSegundoCaracter(Constantes.I);
                    cargaMasivaPropuestasDTO.getFecetPropuesta().setFechaInforme(fecha);
                    cargaMasivaPropuestasDTO.setValida(true);
                }
            }
        }
        cargaMasivaPropuestasDTO.setUnidadesCorreo(unidadesCorreo);

        return cargaMasivaPropuestasDTO;
    }

    public String fmt(double d) {
        if (d == (long) d) {
            return String.format("%d", (long) d);
        } else {
            return String.format("%s", d);
        }
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

    private void verificarFechaInformeCentral(Map<String, String> fechas, List<CargaMasivaPropuestasDTO> listaErrores,
            int fila, List<Integer> listaFechas) {

        for (int i = 0; i < listaFechas.size(); i++) {
            switch (listaFechas.get(i)) {
                case Constantes.FECHA_PRESENTACION_COMITE:
                    llenarErrorLista(fila, Constantes.FECHA_PRESENTACION_COMITE,
                            validarContenidoFecha(fechas.get(String.valueOf(Constantes.FECHA_PRESENTACION_COMITE))),
                            listaErrores);
                    break;

                case Constantes.FECHA_INFORME_COMITE:
                    llenarErrorLista(fila, Constantes.FECHA_INFORME_COMITE,
                            validarContenidoFecha(fechas.get(String.valueOf(Constantes.FECHA_INFORME_COMITE))),
                            listaErrores);
                    break;

                case Constantes.FECHA_PRESENTACION_COMITE_REGIONAL:
                    llenarErrorLista(fila, Constantes.FECHA_PRESENTACION_COMITE_REGIONAL,
                            validarContenidoFecha(
                                    fechas.get(String.valueOf(Constantes.FECHA_PRESENTACION_COMITE_REGIONAL))),
                            listaErrores);
                    break;

                case Constantes.FECHA_INFORME_COMITE_REGIONAL:
                    llenarErrorLista(fila, Constantes.FECHA_INFORME_COMITE_REGIONAL,
                            validarContenidoFecha(fechas.get(String.valueOf(Constantes.FECHA_INFORME_COMITE_REGIONAL))),
                            listaErrores);
                    break;

                default:

                    break;
            }
        }
    }

    private String validarContenidoFecha(String fecha) {
        if (fecha == null || fecha.equals("")) {
            return "";
        }
        return "El campo no es un valor valido. Esta fecha no es la requerida.";
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

    private static java.util.Date getSystemDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        return cal.getTime();
    }

    public CargaMasivaPropuestasDTO validaSubprograma(String cell, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO,
            List<FececSubprograma> listaSubprograma) {

        boolean subProgramaValido = false;
        cargaMasivaPropuestasDTO.setDescripcionError("");
        for (FececSubprograma subprograma : listaSubprograma) {
            if (subprograma.getClave().equals(cell)) {
                subProgramaValido = true;
                cargaMasivaPropuestasDTO.getFecetPropuesta().setIdSubprograma(subprograma.getIdSubprograma());
                break;
            }
        }
        if (!subProgramaValido) {
            cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
        }
        cargaMasivaPropuestasDTO.setValida(subProgramaValido);

        return cargaMasivaPropuestasDTO;
    }

    public CargaMasivaPropuestasDTO validaSector(BigDecimal cell, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO,
            List<FececSector> listaSector) {

        boolean sectorValido = false;
        cargaMasivaPropuestasDTO.setDescripcionError("");
        for (FececSector sector : listaSector) {
            if (sector.getIdSector().equals(cell)) {
                sectorValido = true;
                cargaMasivaPropuestasDTO.getFecetPropuesta().setIdSector(sector.getIdSector());
                break;
            }
        }
        if (!sectorValido) {
            cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
        }
        cargaMasivaPropuestasDTO.setValida(sectorValido);
        return cargaMasivaPropuestasDTO;
    }

    public CargaMasivaPropuestasDTO validaTipoPropuesta(BigDecimal cell,
            CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO, List<FececTipoPropuesta> listaTipoPropuesta) {

        boolean tipoPropuestaValido = false;
        cargaMasivaPropuestasDTO.setDescripcionError("");
        for (FececTipoPropuesta tipoPropuesta : listaTipoPropuesta) {
            if (tipoPropuesta.getIdTipoPropuesta().equals(cell)) {
                tipoPropuestaValido = true;
                cargaMasivaPropuestasDTO.getFecetPropuesta().setIdTipoPropuesta(tipoPropuesta.getIdTipoPropuesta());
                break;
            }
        }
        if (!tipoPropuestaValido) {
            cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
        }
        cargaMasivaPropuestasDTO.setValida(tipoPropuestaValido);
        return cargaMasivaPropuestasDTO;
    }

    public CargaMasivaPropuestasDTO validaProgramacion(BigDecimal cell,
            CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO, List<FececCausaProgramacion> listaProgramacion) {

        boolean programacionValido = false;
        cargaMasivaPropuestasDTO.setDescripcionError("");
        for (FececCausaProgramacion programacion : listaProgramacion) {
            if (programacion.getIdCausaProgramacion().equals(cell)) {
                programacionValido = true;
                cargaMasivaPropuestasDTO.getFecetPropuesta()
                        .setIdCausaProgramacion(programacion.getIdCausaProgramacion());
                break;
            }
        }
        if (!programacionValido) {
            cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_NO_VALIDO);
        }
        cargaMasivaPropuestasDTO.setValida(programacionValido);
        return cargaMasivaPropuestasDTO;
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

    public Date obetenerFecha(String cell, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO, int num) {
        Pattern pattern = Pattern.compile(DATE_PATTERN);
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

    public void validaPrioridadSugerida(double prioridad, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO,
            List<FececPrioridad> listaPrioridad) {

        String prioridadSugerida = fmt(prioridad);

        if (!prioridadSugerida.trim().equals("")) {
            if (!validaPrioridadSugerida(prioridadSugerida.trim(), listaPrioridad)) {
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

    private boolean validaPrioridadSugerida(String prioridadSugerida, List<FececPrioridad> listaPrioridad) {

        boolean isPrioridadValida = false;

        int prioridad = Integer.parseInt(prioridadSugerida);

        for (FececPrioridad fececPrioridad : listaPrioridad) {
            if (fececPrioridad.getIdPrioridad() == prioridad) {
                return true;
            }
        }

        return isPrioridadValida;
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
                        .setDescripcionError("El archivo no se encuentra en el folio de carga" + " " + nombreArchivo);
                return false;
            }
        }
        cargaMasivaPropuestasDTO.getFecetPropuesta().setListaDocumentos(expedienteList);
        return true;
    }

    private boolean validaDuplicidadFile(List<FecetDocExpediente> expedienteList, FecetDocExpediente docExpediente,
            CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO) {

        for (FecetDocExpediente expediente : expedienteList) {
            if (expediente.getNombre().equals(docExpediente.getNombre())) {
                cargaMasivaPropuestasDTO
                        .setDescripcionError("El archivo se encuentra duplicado." + " " + docExpediente.getNombre());
                return false;
            }
        }

        return true;
    }

    public void validaImpuestoNA(List<FecetImpuesto> listaImpuestos, FecetImpuesto impuestoNuevo,
            CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO) {
        cargaMasivaPropuestasDTO.setDescripcionError("");
        if (listaImpuestos == null || listaImpuestos.isEmpty()) {
            cargaMasivaPropuestasDTO.setValida(true);
            cargaMasivaPropuestasDTO.setDescripcionAddImpuesto(MSJ_IMPUESTO_VALIDO);
        } else {
            for (FecetImpuesto impuestoAnterior : listaImpuestos) {
                if (impuestoAnterior.getIdTipoImpuesto().equals(IMPUESTO_NA)
                        && impuestoNuevo.getIdTipoImpuesto().equals(IMPUESTO_NA)) {

                    cargaMasivaPropuestasDTO.setDescripcionAddImpuesto(MSJ_IMPUESTO_INVALIDO);
                    cargaMasivaPropuestasDTO.setDescripcionError(MSJ_CAMPO_IMPUESTO_DUPLICADO);
                    cargaMasivaPropuestasDTO.setValida(false);
                    break;

                } else if (impuestoAnterior.getIdTipoImpuesto().equals(IMPUESTO_NA)
                        && !impuestoNuevo.getIdTipoImpuesto().equals(IMPUESTO_NA)) {

                    cargaMasivaPropuestasDTO.setDescripcionAddImpuesto(MSJ_IMPUESTO_INVALIDO);
                    cargaMasivaPropuestasDTO.setDescripcionError(MSJ_ERROR_IMPUESTO_NA);
                    cargaMasivaPropuestasDTO.setValida(false);
                    break;

                } else if (!impuestoAnterior.getIdTipoImpuesto().equals(IMPUESTO_NA)
                        && impuestoNuevo.getIdTipoImpuesto().equals(IMPUESTO_NA)) {

                    cargaMasivaPropuestasDTO.setDescripcionAddImpuesto(MSJ_IMPUESTO_INVALIDO);
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

    public boolean validaConceptosRepetidos(List<FecetImpuesto> lista, FecetImpuesto impuesto) {
        for (FecetImpuesto imp : lista) {
            if (imp.getIdTipoImpuesto().equals(impuesto.getIdTipoImpuesto())
                    && imp.getIdConcepto().equals(impuesto.getIdConcepto())) {
                return true;
            }
        }
        return false;
    }

    public FececTipoImpuesto validaImpuesto(String cell, List<FececTipoImpuesto> listaImpuesto,
            CargaMasivaPropuestasDTO cargaMasivaPropuestaDTO) {

        for (FececTipoImpuesto impuesto : listaImpuesto) {
            if (impuesto.getAbreviatura().equals(cell)) {
                return impuesto;
            }
        }
        cargaMasivaPropuestaDTO.setDescripcionError("No existe el impuesto seleccionado");
        return new FececTipoImpuesto();
    }

    public FececConcepto validaConcepto(BigDecimal tipoImpuesto, BigDecimal idConcepto,
            Map<BigDecimal, List<FececConcepto>> listaConcepto, CargaMasivaPropuestasDTO cargaMasivaPropuestaDTO) {

        if (tipoImpuesto != null && listaConcepto.containsKey(tipoImpuesto)) {
            List<FececConcepto> listaRelacionConcepto = listaConcepto.get(tipoImpuesto);
            for (FececConcepto concepto : listaRelacionConcepto) {
                if (new BigDecimal(concepto.getIdConcepto()).equals(idConcepto)) {
                    return concepto;
                }
            }
        }
        cargaMasivaPropuestaDTO.setDescripcionError("No existe el concepto seleccionado");
        return new FececConcepto();
    }

}
