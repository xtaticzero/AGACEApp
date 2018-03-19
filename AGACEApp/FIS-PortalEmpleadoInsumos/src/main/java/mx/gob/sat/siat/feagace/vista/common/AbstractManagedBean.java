package mx.gob.sat.siat.feagace.vista.common;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import mx.gob.sat.siat.base.constante.ConstantesSesion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoUnidadesAdminXGeneral;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.model.UploadedFile;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.bo.base.impl.ValidaMediosContactoBO;
import mx.gob.sat.siat.feagace.negocio.common.ConsultaAntecedentesInsumoService;
import mx.gob.sat.siat.feagace.negocio.common.ConsultaAntecedentesPropuestasService;
import mx.gob.sat.siat.feagace.negocio.common.ConsultaMediosContactoService;
import mx.gob.sat.siat.feagace.negocio.common.NotificacionService;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteEmpleadoException;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesPropuestas;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;
import mx.gob.sat.siat.modelo.dto.AccesoUsr;
import org.primefaces.context.RequestContext;

public class AbstractManagedBean extends AccesoUsuarioMBAbstract {

    private static final long serialVersionUID = 1L;

    private static final long N_4196000L = 4194304L;

    protected static final String ERROR_LOGGEO = "No se pudo obtener la informacion del usuario logueado";
    private static final String LEYENDA = "Se encontraron: ";
    private static final String LABEL_REGISTROS_CONSULTA_ANTECEDENTES = "lbl.propuestas.consultaAntecedentesSiRegistros";
    private static final String LABEL_REGISTROS_CONSULTA_NO_ANTECEDENTES = "lbl.propuestas.consultaAntecedentesNoRegistros";
    private static final String LABEL_REGISTROS_CONSULTA_PROPUESTAS_ANTECEDENTES = "lbl.propuestas.consultaPropuestaAntecedentes";
    private static final String LABEL_REGISTROS_CONSULTA_INSUMOS_ANTECEDENTES = "lbl.propuestas.consultaInsumosAntecedentes";

    protected static final String ERROR_ARCHIVO_INVALIDO = "Archivo inv\u00E1lido";
    public static final String ERROR_ARCHIVO_REPETIDO = "Archivo ya existente en la lista de documentos, verifique por favor";
    public static final String ERROR_ARCHIVO_REPETIDO_CARGADO = "El archivo ya fue cargado";
    public static final String ERROR_ARCHIVO_CARGADO = "Ya se cargo un archivo de Resoluci\u00F3n";
    public static final String ERROR_NO_SELECCION_ELEMENTO = "Debe seleccionar por lo menos un elemento de la lista";

    @ManagedProperty(value = "#{consultaMediosContactoService}")
    private transient ConsultaMediosContactoService consultaMediosContactoService;
    /**
     * notificacionService es la referencia del servicio del correo electronico
     * para enviar notificaciones a empleados SAT.
     */
    @ManagedProperty(value = "#{notificacionService}")
    private transient NotificacionService notificacionService;

    /**
     * consultaAntecedentes es la referencia del servicio de consulta de
     * antedentes del Contribuyente.
     */
    @ManagedProperty(value = "#{consultaAntecedentesService}")
    private transient ConsultaAntecedentesPropuestasService consultaAntecedentesService;

    @ManagedProperty(value = "#{consultaAntecedentesInsumoService}")
    private transient ConsultaAntecedentesInsumoService consultaAntecedentesInsumoService;

    protected AccesoUsr getUsuario() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        AccesoUsr accesoUsr = null;
        try {
            accesoUsr = (AccesoUsr) session.getAttribute("accesoEF");
            if (accesoUsr == null) {
                accesoUsr = (AccesoUsr) session.getAttribute("acceso");
            }
        } catch (Exception e) {
            logger.error("No se pudo obtener la sesion ", e);
        }
        return accesoUsr;
    }

    public EmpleadoDTO obtenerEmpleadoAcceso(String rfc, TipoEmpleadoEnum tipoEmpleado) throws NoExisteEmpleadoException {
        try {
            EmpleadoDTO empleado = getAccesoEmpleado(rfc);
            if (getEmpleadoService().validarExistenciaTipoEmpleado(empleado, tipoEmpleado)) {
                getSession().setAttribute(ConstantesSesion.EMPLEADO_FIS_INTEGRAL, empleado);
                return empleado;
            }
        } catch (EmpleadoServiceException e) {
            logger.error("Error al obtener el empleado", e);
        }
        throw new NoExisteEmpleadoException("empleado.no.existente");

    }

    public void informeErrorSession(Exception e) {
        try {
            HttpSession session;
            session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute("mensaje", e);
            ServletContext dir = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            FacesContext.getCurrentInstance().getExternalContext().redirect(dir.getContextPath() + "/faces/error/indexError.jsf");
        } catch (IOException f) {
            logger.error("No se pudo redireccionar a la pagina de error");
        }
    }

    public ValidaMediosContactoBO validaMediosContacto(String rfc) {
        ValidaMediosContactoBO validaMediosContactoBO = new ValidaMediosContactoBO();
        validaMediosContactoBO.setRfc(rfc);
        validaMediosContactoBO = getConsultaMediosContactoService().validaMediosContacto(validaMediosContactoBO);
        return validaMediosContactoBO;
    }

    private Boolean validaTamanoArchivo(final UploadedFile archivo) {
        boolean resultado = false;
        if (validaTamanioArchivo(archivo)) {
            resultado = true;
        } else {
            addErrorMessage("formInsumo:fulExpediente", "Error al cargar el archivo.", "Error al cargar el archivo. Debe ser mayor a 1 KB y menor que 4 MB.");
        }
        return resultado;
    }

    public Boolean validaArchivoCargaInsumoPropuesta(final UploadedFile archivo) {
        if (archivo.getFileName().endsWith(Constantes.ARCHIVO_WORD_DESPUES_2007) || archivo.getFileName().endsWith(Constantes.ARCHIVO_PDF)
                || archivo.getFileName().endsWith(Constantes.ARCHIVO_EXCEL_DESPUES_2007) || archivo.getFileName().endsWith(Constantes.ARCHIVO_ZIP)) {
            if (validaTamanoArchivo(archivo)) {
                return true;
            }
        } else {
            addErrorMessage("formInsumo:fulExpediente", "Error al cargar el archivo.", "Error al cargar el archivo. Solo se aceptan archivos WORD, EXCEL versi\u00f3n 2007 o superior, PDF o formato ZIP");
        }
        return false;
    }

    public boolean validaFormatoArchivoCargaMasiva(final UploadedFile archivo) {
        return (archivo.getFileName().endsWith(Constantes.ARCHIVO_WORD_DESPUES_2007) || archivo.getFileName().endsWith(Constantes.ARCHIVO_PDF)
                || archivo.getFileName().endsWith(Constantes.ARCHIVO_EXCEL_DESPUES_2007) || archivo.getFileName().endsWith(Constantes.ARCHIVO_ZIP));
    }

    public boolean validaTamanioArchivo(final UploadedFile archivo) {
        return (archivo.getSize() > 0L && archivo.getSize() <= N_4196000L);
    }

    public List<String> verificarAntecedentes(final String rfc, final Date periodoInicial, final Date periodoFinal) {
        FecetPropuesta propuesta = new FecetPropuesta();
        propuesta.setFechaInicioPeriodo(periodoInicial);
        propuesta.setFechaFinPeriodo(periodoFinal);
        return verificarAntecedentesPropuestas(rfc, propuesta);
    }

    public List<String> verificarAntecedentes(final String rfc, final Date periodoInicial, final Date periodoFinal, final BigDecimal subprograma,
            final BigDecimal unidadAdministrativa, final BigDecimal idInsumo) {
        FecetPropuesta propuesta = new FecetPropuesta();
        propuesta.setFechaInicioPeriodo(periodoInicial);
        propuesta.setFechaFinPeriodo(periodoFinal);
        propuesta.setIdSubprograma(subprograma);
        propuesta.setIdArace(unidadAdministrativa);
        propuesta.setIdInsumo(idInsumo);
        return verificarAntecedentesInsumos(rfc, propuesta);
    }

    public List<String> verificarAntecedentesPropuestas(String rfc, FecetPropuesta propuesta) {
        List<String> listaDatosEncontrados = new ArrayList<String>();
        List<FecetPropuesta> propuestasSICSEP;
        List<FecetPropuesta> propuestasSUIEFI;
        List<FecetPropuesta> propuestasAGAFF;
        List<FecetPropuesta> propuestasAGACE;

        String mensaje;
        Date periodoInicial = propuesta.getFechaInicioPeriodo();
        Date periodoFinal = propuesta.getFechaFinPeriodo();
        try {
            propuestasSICSEP = consultaAntecedentesService.consultaSICSEP(rfc, periodoInicial, periodoFinal);
            if (propuestasSICSEP != null && propuestasSICSEP.size() > 0) {
                mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_ANTECEDENTES);
                listaDatosEncontrados.add(String.format(mensaje, ConstantesPropuestas.SICSEP));
            }
        } catch (NegocioException e) {
            mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_NO_ANTECEDENTES);
            listaDatosEncontrados.add(String.format(mensaje, ConstantesPropuestas.SICSEP));
        }

        try {
            propuestasSUIEFI = consultaAntecedentesService.consultaSUIEFI(rfc, periodoInicial, periodoFinal);
            if (propuestasSUIEFI != null && propuestasSUIEFI.size() > 0) {
                mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_ANTECEDENTES);
                listaDatosEncontrados.add(String.format(mensaje, ConstantesPropuestas.SIUIEFI));
            }
        } catch (NegocioException e) {
            mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_NO_ANTECEDENTES);
            listaDatosEncontrados.add(String.format(mensaje, ConstantesPropuestas.SIUIEFI));
        }

        try {
            propuestasAGAFF = consultaAntecedentesService.consultaAGAFF(rfc, periodoInicial, periodoFinal);
            if (propuestasAGAFF != null && propuestasAGAFF.size() > 0) {
                mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_ANTECEDENTES);
                listaDatosEncontrados.add(String.format(mensaje, ConstantesPropuestas.AGAFF_DESCRIPCION));
            }
        } catch (Exception e) {
            mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_NO_ANTECEDENTES);
            listaDatosEncontrados.add(String.format(mensaje, ConstantesPropuestas.AGAFF_DESCRIPCION));
        }

        try {
            propuestasAGACE = consultaAntecedentesService.consultaAGACEPropuestasPeriodoExacto(rfc, propuesta);
            if (propuestasAGACE != null && !propuestasAGACE.isEmpty()) {
                FecetPropuesta propuestaBD = propuestasAGACE.get(0);
                mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_PROPUESTAS_ANTECEDENTES);
                listaDatosEncontrados.add(String.format(mensaje, propuestaBD.getIdRegistro()));
            }

            List<FecetInsumo> insumos = consultaAntecedentesService.consultaAGACEInsumosPeriodoExacto(rfc, propuesta);
            if (insumos != null && !insumos.isEmpty()) {
                mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_INSUMOS_ANTECEDENTES);
                listaDatosEncontrados.add(mensaje);
            }

        } catch (NegocioException e) {
            mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_NO_ANTECEDENTES);
            listaDatosEncontrados.add(String.format(mensaje, ConstantesPropuestas.AGACE_DESCRIPCION));
        }

        return listaDatosEncontrados;
    }

    public List<String> verificarAntecedentesInsumos(String rfc, FecetPropuesta propuesta) {
        List<String> listaDatosEncontrados = new ArrayList<String>();
        List<FecetPropuesta> propuestasSICSEP;
        List<FecetPropuesta> propuestasSUIEFI;
        List<FecetPropuesta> propuestasAGAFF;
        List<FecetPropuesta> propuestasAGACE;

        String mensaje;

        Date periodoInicial = propuesta.getFechaInicioPeriodo();
        Date periodoFinal = propuesta.getFechaFinPeriodo();
        try {
            propuestasSICSEP = consultaAntecedentesInsumoService.consultaSICSEP(rfc, periodoInicial, periodoFinal);
            if (propuestasSICSEP != null && propuestasSICSEP.size() > 0) {
                mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_ANTECEDENTES);
                listaDatosEncontrados.add(String.format(mensaje, ConstantesPropuestas.SICSEP));
            }
        } catch (NegocioException e) {
            mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_NO_ANTECEDENTES);
            listaDatosEncontrados.add(String.format(mensaje, ConstantesPropuestas.SICSEP));
        }

        try {
            propuestasSUIEFI = consultaAntecedentesInsumoService.consultaSUIEFI(rfc, periodoInicial, periodoFinal);
            if (propuestasSUIEFI != null && propuestasSUIEFI.size() > 0) {
                mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_ANTECEDENTES);
                listaDatosEncontrados.add(String.format(mensaje, ConstantesPropuestas.SIUIEFI));
            }
        } catch (NegocioException e) {
            mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_NO_ANTECEDENTES);
            listaDatosEncontrados.add(String.format(mensaje, ConstantesPropuestas.SIUIEFI));
        }

        try {
            propuestasAGAFF = consultaAntecedentesInsumoService.consultaAGAFF(rfc, periodoInicial, periodoFinal);
            if (propuestasAGAFF != null && propuestasAGAFF.size() > 0) {
                mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_ANTECEDENTES);
                listaDatosEncontrados.add(String.format(mensaje, ConstantesPropuestas.AGAFF_DESCRIPCION));
            }
        } catch (Exception e) {
            mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_NO_ANTECEDENTES);
            listaDatosEncontrados.add(String.format(mensaje, ConstantesPropuestas.AGAFF_DESCRIPCION));
        }

        try {
            propuestasAGACE = consultaAntecedentesInsumoService.consultaAGACEPropuestasPeriodoExacto(rfc, propuesta);
            if (propuestasAGACE != null && !propuestasAGACE.isEmpty()) {
                FecetPropuesta propuestaBD = propuestasAGACE.get(0);
                mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_PROPUESTAS_ANTECEDENTES);
                listaDatosEncontrados.add(String.format(mensaje, propuestaBD.getIdRegistro()));
            }

            List<FecetInsumo> insumos = consultaAntecedentesInsumoService.consultaAGACEInsumosPeriodoExacto(rfc, propuesta);
            if (insumos != null && !insumos.isEmpty()) {
                mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_INSUMOS_ANTECEDENTES);
                listaDatosEncontrados.add(mensaje);
            }

        } catch (NegocioException e) {
            mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_NO_ANTECEDENTES);
            listaDatosEncontrados.add(String.format(mensaje, ConstantesPropuestas.AGACE_DESCRIPCION));
        }

        return listaDatosEncontrados;
    }

    public List<String> validaAntecedentes(final String rfc, final Date fechaInicial, final Date fechaFinal, final BigDecimal estatus) {
        List<String> reporte = new ArrayList<String>();
        try {
            List<FecetPropuesta> antecedentesAgace = consultaAntecedentesService.consultaAGACE(rfc, fechaInicial, fechaFinal);
            if (antecedentesAgace != null && antecedentesAgace.size() > 0) {
                reporte.add(LEYENDA + antecedentesAgace.size() + " antecedentes en el sistema AGACE.");
            }

        } catch (NegocioException e) {
            reporte.add("Existio un error al consultar los antecedentes en AGACE.");
        }

        try {
            List<FecetPropuesta> antecedentesAgaff = consultaAntecedentesService.consultaAGAFF(rfc, fechaInicial, fechaFinal);
            if (antecedentesAgaff != null && antecedentesAgaff.size() > 0) {
                reporte.add(LEYENDA + antecedentesAgaff.size() + " antecedentes en el sistema AGAFF.");
            }

        } catch (NegocioException e) {
            reporte.add("Existio un error al consultar los antecedentes en AGAFF.");
        }

        try {
            List<FecetPropuesta> antecedentesSicsep = consultaAntecedentesService.consultaSICSEP(rfc, fechaInicial, fechaFinal);
            if (antecedentesSicsep != null && antecedentesSicsep.size() > 0) {
                reporte.add(LEYENDA + antecedentesSicsep.size() + " antecedentes en el sistema SICSEP.");
            }
        } catch (NegocioException e) {
            reporte.add("Existio un error al consultar los antecedentes en SICSEP.");
        }

        try {
            List<FecetPropuesta> antecedentesSuiefi = consultaAntecedentesService.consultaSUIEFI(rfc, fechaInicial, fechaFinal);
            if (antecedentesSuiefi != null && antecedentesSuiefi.size() > 0) {
                reporte.add(LEYENDA + antecedentesSuiefi.size() + " antecedentes en el sistema SUIEFI.");
            }
        } catch (NegocioException e) {
            reporte.add("Existio un error al consultar los antecedentes en SUIEFI.");
        }

        return reporte;
    }

    public void limpiaFiltros(final String componente) {
        try {
            DataTable dataTable;
            String[] widgetVar = componente.split(":");
            if (widgetVar != null && widgetVar.length > 1) {
                dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(componente);

                if (!dataTable.getFilters().isEmpty()) {
                    logger.info("dataTable.getFilters().isEmpty() :" + dataTable.getFilters().isEmpty());

                    dataTable.setFilteredValue(null);
                    dataTable.reset();
                }
            } else {
                RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("PF('" + componente + "').clearFilters()");
            }

        } catch (Exception e) {
            logger.error("no pudo limpiar" + e.getMessage());
        }

    }
    
    public Map<BigDecimal, GrupoUnidadesAdminXGeneral> obtenerGrupoUnidadesAdminXGeneral(EmpleadoDTO empleadoDTO){
        Map<BigDecimal, GrupoUnidadesAdminXGeneral> grupoUnidadesAdminXGeneral = (Map<BigDecimal, GrupoUnidadesAdminXGeneral>) getSession().getAttribute(ConstantesSesion.MAP_GRUPOS_UNIDADES_ADMIN);
            
        if (grupoUnidadesAdminXGeneral == null || grupoUnidadesAdminXGeneral.isEmpty()) {
            grupoUnidadesAdminXGeneral = obtenerMapGruposDeUnidades(empleadoDTO);
            getSession().setAttribute(ConstantesSesion.MAP_GRUPOS_UNIDADES_ADMIN, grupoUnidadesAdminXGeneral);
        }
        return grupoUnidadesAdminXGeneral;
    }

    public void setConsultaMediosContactoService(ConsultaMediosContactoService consultaMediosContactoService) {
        this.consultaMediosContactoService = consultaMediosContactoService;
    }

    public ConsultaMediosContactoService getConsultaMediosContactoService() {
        return consultaMediosContactoService;
    }

    public NotificacionService getNotificacionService() {
        return notificacionService;
    }

    public void setNotificacionService(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    public void setConsultaAntecedentesService(ConsultaAntecedentesPropuestasService consultaAntecedentesService) {
        this.consultaAntecedentesService = consultaAntecedentesService;
    }

    public ConsultaAntecedentesPropuestasService getConsultaAntecedentesService() {
        return consultaAntecedentesService;
    }

    public ConsultaAntecedentesInsumoService getConsultaAntecedentesInsumoService() {
        return consultaAntecedentesInsumoService;
    }

    public void setConsultaAntecedentesInsumoService(ConsultaAntecedentesInsumoService consultaAntecedentesInsumoService) {
        this.consultaAntecedentesInsumoService = consultaAntecedentesInsumoService;
    }

}
