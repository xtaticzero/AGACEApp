package mx.gob.sat.siat.feagace.negocio.propuestas.asignar.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas.FececMotivoSuplenciaDAO;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.AsignarSuplenciaAFirmanteDAO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.AsignarSuplenciaAFirmanteModel;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetSuplenciaDTO;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;
import mx.gob.sat.siat.feagace.negocio.common.NotificacionService;
import mx.gob.sat.siat.feagace.negocio.exception.AsignarSuplenciaAFirmanteCargaDisponiblesException;
import mx.gob.sat.siat.feagace.negocio.exception.AsignarSuplenciaAFirmanteUpdateException;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteEmpleadoException;
import mx.gob.sat.siat.feagace.negocio.propuestas.PropuestasServiceAbstract;
import mx.gob.sat.siat.feagace.negocio.propuestas.asignar.AsignarSuplenciaAFirmanteService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.NombreMes;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.Common;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;

@Service("asignarSuplenciaAFirmanteService")
public class AsignarSuplenciaAFirmanteServiceImpl extends PropuestasServiceAbstract
        implements AsignarSuplenciaAFirmanteService {

    private static final long serialVersionUID = 1L;
    @Autowired
    private transient AsignarSuplenciaAFirmanteDAO asignarSuplenciaAFirmanteDAO;
    @Autowired
    private transient FececMotivoSuplenciaDAO fececMotivoSuplenciaDAO;
    @Autowired
    private transient NotificacionService notificacionService;

    public List<FecetSuplenciaDTO> findListaSuplencia(List<EmpleadoDTO> listaEmp) {

        StringBuffer valor = new StringBuffer();
        for (int i = 0; i < listaEmp.size(); i++) {
            valor.append(listaEmp.get(i).getIdEmpleado().intValue()).append(",");
            if (i + Constantes.INDICE_UNO == listaEmp.size()) {
                valor.append(listaEmp.get(i).getIdEmpleado().intValue());
            }
        }

        return obtieneNombreFirmante(asignarSuplenciaAFirmanteDAO.findListaSuplencia(valor));
    }

    private List<FecetSuplenciaDTO> obtieneNombreFirmante(List<FecetSuplenciaDTO> findListaSuplencia) {
        for (FecetSuplenciaDTO lista : findListaSuplencia) {
            try {
                lista.setNombreFirmanteASuplir(getEmpleadoCompleto(lista.getIdFirmanteASuplir().intValue()).getNombreCompleto());
                lista.setNombreFirmanteSuplente(getEmpleadoCompleto(lista.getIdFrimanteSuplente().intValue()).getNombreCompleto());
            } catch (EmpleadoServiceException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return findListaSuplencia;
    }

    private FecetSuplenciaDTO obtieneNombreFirmante(FecetSuplenciaDTO findListaSuplencia) {
        try {
            findListaSuplencia.setNombreFirmanteASuplir(getEmpleadoCompleto(findListaSuplencia.getIdFirmanteASuplir().intValue()).getNombreCompleto());
            findListaSuplencia.setNombreFirmanteSuplente(getEmpleadoCompleto(findListaSuplencia.getIdFrimanteSuplente().intValue()).getNombreCompleto());
        } catch (EmpleadoServiceException e) {
            logger.error(e.getMessage(), e);
        }
        return findListaSuplencia;
    }

    @Override
    public void cargaFirmantesSuplentesDisponibles(AsignarSuplenciaAFirmanteModel asignarSuplenciaAFirmanteModel,
            EmpleadoDTO empleado, List<EmpleadoDTO> listEmp) throws AsignarSuplenciaAFirmanteCargaDisponiblesException {
        if (asignarSuplenciaAFirmanteDAO.buscarSuplenciaRegistradaFirmanteSuplente(
                asignarSuplenciaAFirmanteModel.getFirmanteASuplir().getIdEmpleado(),
                asignarSuplenciaAFirmanteModel.getNuevaSuplencia().getFechaInicio(),
                asignarSuplenciaAFirmanteModel.getNuevaSuplencia().getFechaFin()) == null) {
            if (asignarSuplenciaAFirmanteDAO.buscarSuplenciaRegistradaFirmanteASuplir(asignarSuplenciaAFirmanteModel) == null) {
                asignarSuplenciaAFirmanteModel.setCombolistaFirmanteSuplente(buscaSuplencia(asignarSuplenciaAFirmanteModel, listEmp));
            } else {
                throw new AsignarSuplenciaAFirmanteCargaDisponiblesException(Constantes.MSJ_SUPLENCIA_YA_EXISTE);
            }
        } else {
            throw new AsignarSuplenciaAFirmanteCargaDisponiblesException(Constantes.MSJ_FIRMANTE_ACTIVO_SUPLENCIA);
        }

    }

    public List<EmpleadoDTO> buscaSuplencia(AsignarSuplenciaAFirmanteModel asignarSuplenciaAFirmanteModel,
            List<EmpleadoDTO> listEmp) {
        for (EmpleadoDTO empleado : listEmp) {
            BigDecimal respuesta = asignarSuplenciaAFirmanteDAO.cargaFirmantesSuplentesDisponibles(
                    empleado.getIdEmpleado(), asignarSuplenciaAFirmanteModel.getNuevaSuplencia().getFechaInicio(),
                    asignarSuplenciaAFirmanteModel.getNuevaSuplencia().getFechaFin());
            logger.error(respuesta + "regreso valor");
            if (respuesta != null) {
                listEmp.remove(empleado);
            }
        }

        return listEmp;

    }

    @Override
    @PistaAuditoria
    public void eliminarSuplencia(String idSuplencia, String estatus) throws AsignarSuplenciaAFirmanteUpdateException {
        String operacion = "eliminada";
        if (estatus.equalsIgnoreCase(Constantes.ESTADO_REGISTRADA)) {
            asignarSuplenciaAFirmanteDAO.cancelarSuplencia("3", idSuplencia);
        } else if (estatus.equalsIgnoreCase(Constantes.ESTADO_ACTIVA)) {
            asignarSuplenciaAFirmanteDAO.cancelarSuplencia("4", idSuplencia);
        } else {
            throw new AsignarSuplenciaAFirmanteUpdateException("estado de suplencia incorrecto");
        }
        try {
            FecetSuplenciaDTO suplencia = obtieneNombreFirmante(asignarSuplenciaAFirmanteDAO.findSuplencia(new BigDecimal(idSuplencia)));

            notificacionService.enviarNotificacion(obtenerDatos(suplencia, operacion), ReportType.AVISO_ASIGNACION_SUPLENCIA, obtenerToCorreos(suplencia));
        } catch (BusinessException e) {
            logger.error("No se pudo enviar el correo de notificacion [{}]", e.getCause(), e);
        }

    }

    @Override
    public void obtenerEmpleadoASuplir(BigDecimal idEmpleado,
            AsignarSuplenciaAFirmanteModel asignarSuplenciaAFirmanteModel) throws NoExisteEmpleadoException {
        EmpleadoDTO emp = null;
        try {
            emp = getEmpleadoCompleto(idEmpleado.intValue());
        } catch (EmpleadoServiceException e) {
            logger.error(e.getMessage(), e);
        }

        if (emp != null) {
            asignarSuplenciaAFirmanteModel.setFirmanteASuplir(emp);
            asignarSuplenciaAFirmanteModel.setListaMotivoSuplencia(fececMotivoSuplenciaDAO.findAll());
        } else {
            throw new NoExisteEmpleadoException("No existe empleado");
        }
    }

    @Override
    public void obtenerMotivosSuplencia(AsignarSuplenciaAFirmanteModel asignarSuplenciaAFirmanteModel)
            throws AsignarSuplenciaAFirmanteUpdateException {
        if (asignarSuplenciaAFirmanteModel != null) {
            asignarSuplenciaAFirmanteModel.setListaMotivoSuplencia(fececMotivoSuplenciaDAO.findAll());
        } else {
            throw new AsignarSuplenciaAFirmanteUpdateException("No existe empleado");
        }
    }

    @Override
    public boolean buscarSuplenciaRegistrada(AsignarSuplenciaAFirmanteModel asignarSuplenciaAFirmanteModel) {

        if (asignarSuplenciaAFirmanteDAO.buscarSuplenciaRegistradaFirmanteSuplente(
                asignarSuplenciaAFirmanteModel.getNuevaSuplencia().getIdFirmanteASuplir(),
                asignarSuplenciaAFirmanteModel.getNuevaSuplencia().getFechaInicio(),
                asignarSuplenciaAFirmanteModel.getNuevaSuplencia().getFechaFin()) != null) {
            return false;
        }

        return true;
    }

    public boolean suplenteTieneSuplencia(AsignarSuplenciaAFirmanteModel asignarSuplenciaAFirmanteModel) {

        if (asignarSuplenciaAFirmanteDAO.buscarSuplenciaRegistradaFirmanteSuplente(
                asignarSuplenciaAFirmanteModel.getNuevaSuplencia().getIdFrimanteSuplente(),
                asignarSuplenciaAFirmanteModel.getNuevaSuplencia().getFechaInicio(),
                asignarSuplenciaAFirmanteModel.getNuevaSuplencia().getFechaFin()) != null) {
            return false;
        }

        return true;
    }

    @Override
    public boolean buscarSuplenciaRegistradaSuplir(AsignarSuplenciaAFirmanteModel asignarSuplenciaAFirmanteModel) {

        if (asignarSuplenciaAFirmanteDAO
                .buscarSuplenciaRegistradaFirmanteASuplir(asignarSuplenciaAFirmanteModel) != null) {
            return false;
        }

        return true;
    }

    @Override
    public void obtenerEmpleadoSuplente(BigDecimal idEmpleado,
            AsignarSuplenciaAFirmanteModel asignarSuplenciaAFirmanteModel) throws NoExisteEmpleadoException {
        EmpleadoDTO emp = null;
        try {
            emp = this.getEmpleadoCompleto(idEmpleado.intValue());
        } catch (EmpleadoServiceException e) {
            logger.error(e.getMessage());
        }

        if (emp != null) {
            asignarSuplenciaAFirmanteModel.setFirmanteSuplente(emp);

        } else {
            throw new NoExisteEmpleadoException("No existe empleado");
        }
    }

    @Override
    @PistaAuditoria
    public void almacenarSuplencia(AsignarSuplenciaAFirmanteModel asignarSuplenciaAFirmanteModel)
            throws AsignarSuplenciaAFirmanteUpdateException {
        String operacion = "programada";
        FecetSuplenciaDTO suplencia = asignarSuplenciaAFirmanteModel.getNuevaSuplencia();
        asignarSuplenciaAFirmanteDAO.insertar(suplencia);
        try {
            notificacionService.enviarNotificacion(obtenerDatos(suplencia, operacion),
                    ReportType.AVISO_ASIGNACION_SUPLENCIA, obtenerToCorreos(suplencia));
        } catch (BusinessException e) {
            logger.error("No se pudo enviar el correo de notificacion [{}]", e.getCause(), e);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public Map<String, String> obtenerDatos(FecetSuplenciaDTO suplencia, String operacion) {
        Map<String, String> datos = new HashMap<String, String>();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        datos.put("fechaInicio", df.format(suplencia.getFechaInicio()));
        datos.put("fechaFin", df.format(suplencia.getFechaFin()));
        datos.put("firmanteASuplir", suplencia.getNombreFirmanteASuplir());
        datos.put("firmanteSuplente", suplencia.getNombreFirmanteSuplente());
        datos.put("operacion", operacion);
        datos.put(Common.YEAR.toString(), "" + Calendar.getInstance().get(Calendar.YEAR));
        datos.put(Common.DAY.toString(), "" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datos.put(Common.MONTH.toString(), NombreMes.obtenerNombre(Calendar.getInstance().get(Calendar.MONTH)));
        datos.put(Common.HOUR.toString(), new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime()));
        datos.put(Common.SUBJECT.toString(), "Aviso de suplencia");
        return datos;
    }

    private Set<String> obtenerToCorreos(FecetSuplenciaDTO suplencia) {
        Set<String> correos = new TreeSet<String>();
        try {
            EmpleadoDTO fececEmpleadoASuplir = getEmpleadoCompleto(suplencia.getIdFirmanteASuplir().intValue());
            EmpleadoDTO fececEmpleadoSuplente = getEmpleadoCompleto(suplencia.getIdFrimanteSuplente().intValue());
            correos.add(fececEmpleadoASuplir.getCorreo());
            correos.add(fececEmpleadoSuplente.getCorreo());
        } catch (EmpleadoServiceException e) {
            logger.error("Error al obtener el correo del auditor {}: {}", e.getMessage(), e);
        }
        return correos;
    }

    public List<EmpleadoDTO> getEmpleadoFirmante(String rfc) throws NegocioException {

        List<EmpleadoDTO> listEmpleado = null;

        try {
            if (validarExistenciaTipoEmpleado(getEmpleadoCompleto(rfc), TipoEmpleadoEnum.CONSULTOR_INSUMOS)) {
                EmpleadoDTO empleado = getEmpleadoCompleto(rfc);

                Map<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>> subor = empleado.getSubordinados();

                Map<TipoEmpleadoEnum, List<EmpleadoDTO>> suborFirma = subor.get(TipoEmpleadoEnum.CONSULTOR_INSUMOS);

                listEmpleado = suborFirma.get(TipoEmpleadoEnum.FIRMANTE);
            } else {
                throw new NegocioException("No se pudo obtener la informacion del usuario logueado");
            }
        } catch (EmpleadoServiceException e) {
            logger.error("[{}]", e);
            throw new NegocioException("No se pudo obtener la informacion del usuario logueado", e);
        }

        return listEmpleado;

    }

    @Override
    public void actualizaEstatusSuplencias() {
        logger.info("Probando scheduler");
    }

    public void setAsignarSuplenciaAFirmanteDAO(AsignarSuplenciaAFirmanteDAO asignarSuplenciaAFirmanteDAO) {
        this.asignarSuplenciaAFirmanteDAO = asignarSuplenciaAFirmanteDAO;
    }

    public AsignarSuplenciaAFirmanteDAO getAsignarSuplenciaAFirmanteDAO() {
        return asignarSuplenciaAFirmanteDAO;
    }

    public void setFececMotivoSuplenciaDAO(FececMotivoSuplenciaDAO fececMotivoSuplenciaDAO) {
        this.fececMotivoSuplenciaDAO = fececMotivoSuplenciaDAO;
    }

    public FececMotivoSuplenciaDAO getFececMotivoSuplenciaDAO() {
        return fececMotivoSuplenciaDAO;
    }

}
