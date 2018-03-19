/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.insumos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import javax.faces.bean.ManagedProperty;

import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetReasignacionInsumo;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteEmpleadoException;
import mx.gob.sat.siat.feagace.negocio.insumos.ConsultaAsignacionInsumoService;
import mx.gob.sat.siat.feagace.negocio.insumos.ReasignacionInsumosService;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.AsignacionInsumoCentral;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.SolicitudRetroalimentacionInsumo;
import mx.gob.sat.siat.feagace.vista.common.AbstractManagedBean;
import mx.gob.sat.siat.feagace.vista.model.insumos.AsignarInsumoSubadminDTO;

/**
 * @author sergio.vaca
 *
 */
public class AbstractAsignarInsumoSubadminMB extends AbstractManagedBean {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final int RFC_DESTINO_POSICION = 0;
    private static final int RFC_ORIGEN_POSICION = 1;
    private static final int UNIDAD_ADMINISTRATIVA_POSICION = 2;

    @ManagedProperty(value = "#{consultaAsignacionInsumoService}")
    private transient ConsultaAsignacionInsumoService consultaAsignacionInsumoService;
    @ManagedProperty(value = "#{reasignacionInsumosService}")
    private transient ReasignacionInsumosService reasignacionInsumosService;

    private AsignarInsumoSubadminDTO asignarInsumoSubadminDTO;

    private StreamedContent archivoDescargaExpediente;

    protected void enviarCorreoAprobarReasignacionInsumo(FecetReasignacionInsumo reasignacionInsumo) {
        Set<String> destinatarios = new TreeSet<String>();
        FecetInsumo insumo = getReasignacionInsumosService().getReasignacionInsumoByInsumo(reasignacionInsumo.getIdInsumo());

        getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, Constantes.ACIACE, destinatarios, ClvSubModulosAgace.INSUMOS);
        getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_ASIGNADOR_INSUMOS, Constantes.ACIACE, destinatarios, ClvSubModulosAgace.INSUMOS);
        getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, insumo.getIdArace(), destinatarios, ClvSubModulosAgace.INSUMOS);
        getNotificacionService().obtenerCorreoEmpleado(insumo.getRfcAdministrador(), Constantes.USUARIO_ASIGNADOR_INSUMOS, destinatarios, ClvSubModulosAgace.INSUMOS);

        String nombre = "";
        try {
            EmpleadoDTO empleado = obtenerEmpleadoAcceso(reasignacionInsumo.getRfcAdministradorDestino(), TipoEmpleadoEnum.ASIGNADOR_INSUMOS);
            nombre = empleado.getNombreCompleto();
        } catch (NoExisteEmpleadoException e) {
            logger.error("Error al obtener empleado", e);
        }
        Map<String, String> data = getNotificacionService().obtenerDatosCorreo(new BigDecimal(Constantes.ENTERO_DOCE));
        data.put(SolicitudRetroalimentacionInsumo.NOMBRE_ADM_DESTINO.toString(), nombre);
        data.put(SolicitudRetroalimentacionInsumo.ID_REGISTRO.toString(), insumo.getIdRegistro());
        data.put("Id Registro", insumo.getIdRegistro());
        llenarCamposCorreo(destinatarios, data, ReportType.AVISOS_INSUMO_GENERICO);

    }

    protected void enviarCorreoRechazoReasignacionInsumo(FecetReasignacionInsumo reasignacionInsumo) {
        Set<String> destinatarios = new TreeSet<String>();
        FecetInsumo insumo = getReasignacionInsumosService().getReasignacionInsumoByInsumo(reasignacionInsumo.getIdInsumo());

        getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, Constantes.ACIACE, destinatarios, ClvSubModulosAgace.INSUMOS);
        getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_ASIGNADOR_INSUMOS, Constantes.ACIACE, destinatarios, ClvSubModulosAgace.INSUMOS);
        getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, insumo.getIdArace(), destinatarios, ClvSubModulosAgace.INSUMOS);
        getNotificacionService().obtenerCorreoEmpleado(insumo.getRfcAdministrador(), Constantes.USUARIO_ASIGNADOR_INSUMOS, destinatarios, ClvSubModulosAgace.INSUMOS);

        String nombre = "";
        try {
            EmpleadoDTO empleado = obtenerEmpleadoAcceso(reasignacionInsumo.getRfcAdministradorDestino(), TipoEmpleadoEnum.ASIGNADOR_INSUMOS);
            nombre = empleado.getNombreCompleto();
        } catch (NoExisteEmpleadoException e) {
            logger.error("Error al obtener empleado", e);
        }

        final Map<String, String> data = getNotificacionService().obtenerDatosCorreo(new BigDecimal(Constantes.ENTERO_ONCE));
        data.put(SolicitudRetroalimentacionInsumo.NOMBRE_ADM_DESTINO.toString(), nombre);
        data.put(SolicitudRetroalimentacionInsumo.ID_REGISTRO.toString(), insumo.getIdRegistro());
        data.put("Id Registro", insumo.getIdRegistro());
        llenarCamposCorreo(destinatarios, data, ReportType.AVISOS_INSUMO_GENERICO);

    }

    protected void enviarCorreoReasignacionInsumoAdmin(List<FecetReasignacionInsumo> reasignaciones) {
        Set<String> destinatarios = new TreeSet<String>();
        Set<String> destinatariosInsumos = new TreeSet<String>();
        getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, Constantes.ACIACE, destinatarios, ClvSubModulosAgace.INSUMOS);
        getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_ASIGNADOR_INSUMOS, Constantes.ACIACE, destinatarios, ClvSubModulosAgace.INSUMOS);
        Map<String, List<FecetInsumo>> administradores = obtenerAdministradores(reasignaciones);
        Map<String, String> data = getNotificacionService().obtenerDatosCorreo(new BigDecimal(Constantes.ENTERO_DIEZ));
        String rfcs[] = null;
        StringBuilder table = new StringBuilder();
        StringBuilder folios = new StringBuilder();
        for (Entry<String, List<FecetInsumo>> entry : administradores.entrySet()) {
            rfcs = entry.getKey().split("-");
            destinatariosInsumos.clear();
            destinatariosInsumos.addAll(destinatarios);
            getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, new BigDecimal(rfcs[UNIDAD_ADMINISTRATIVA_POSICION]), destinatariosInsumos, ClvSubModulosAgace.INSUMOS);
            getNotificacionService().obtenerCorreoEmpleado(rfcs[RFC_DESTINO_POSICION], Constantes.USUARIO_ASIGNADOR_INSUMOS, destinatariosInsumos, ClvSubModulosAgace.INSUMOS);
            table.setLength(0);
            folios.setLength(0);
            crearTablaNotificacion(entry.getValue(), table, folios);
            data.put(SolicitudRetroalimentacionInsumo.NOMBRE_ADM_ORIGEN.toString(), obtenerNombreAdministradorOrigen(rfcs));
            data.put(AsignacionInsumoCentral.ID_REGISTRO.toString(), table.toString());
            data.put(SolicitudRetroalimentacionInsumo.ID_REGISTRO_ESPACIO.toString(), folios.toString());
            llenarCamposCorreo(destinatariosInsumos, data, ReportType.INSUMO_TABLA_RESUMEN);
        }
    }

    protected void enviarCorreoReasignacionInsumoSubAdmin(List<FecetInsumo> insumosSeleccionados) {
        Set<String> destinatarios = new TreeSet<String>();
        getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, Constantes.ACIACE, destinatarios, ClvSubModulosAgace.INSUMOS);
        getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_ASIGNADOR_INSUMOS, Constantes.ACIACE, destinatarios, ClvSubModulosAgace.INSUMOS);
        getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_VALIDADOR_INSUMOS, Constantes.ACIACE, destinatarios, ClvSubModulosAgace.INSUMOS);
        getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, insumosSeleccionados.get(0).getIdArace(), destinatarios, ClvSubModulosAgace.INSUMOS);

        getNotificacionService().obtenerCorreoEmpleado(insumosSeleccionados.get(0).getRfcAdministrador(), Constantes.USUARIO_ASIGNADOR_INSUMOS, destinatarios, ClvSubModulosAgace.INSUMOS);
        getNotificacionService().obtenerCorreoEmpleado(insumosSeleccionados.get(0).getRfcSubadministrador(), Constantes.USUARIO_VALIDADOR_INSUMOS, destinatarios, ClvSubModulosAgace.INSUMOS);

        Map<String, String> data = getNotificacionService().obtenerDatosCorreo(new BigDecimal(Constantes.ENTERO_NUEVE));
        StringBuilder table = new StringBuilder();
        StringBuilder folios = new StringBuilder();
        crearTablaNotificacion(insumosSeleccionados, table, folios);
        data.put(AsignacionInsumoCentral.ID_REGISTRO.toString(), table.toString());
        data.put(SolicitudRetroalimentacionInsumo.ID_REGISTRO_ESPACIO.toString(), folios.toString());
        llenarCamposCorreo(destinatarios, data, ReportType.INSUMO_TABLA_RESUMEN);
    }

    protected void llenarCamposCorreo(Set<String> destinatarios, Map<String, String> data, ReportType type) {
        try {
            getNotificacionService().enviarNotificacionGenerico(data, type, destinatarios);
        } catch (BusinessException ex) {
            logger.error("Error al enviar el correo [{}]", ex.getCause());
        }
    }

    public void setConsultaAsignacionInsumoService(ConsultaAsignacionInsumoService consultaAsignacionInsumoService) {
        this.consultaAsignacionInsumoService = consultaAsignacionInsumoService;
    }

    public ConsultaAsignacionInsumoService getConsultaAsignacionInsumoService() {
        return consultaAsignacionInsumoService;
    }

    public void setReasignacionInsumosService(ReasignacionInsumosService reasignacionInsumosService) {
        this.reasignacionInsumosService = reasignacionInsumosService;
    }

    public ReasignacionInsumosService getReasignacionInsumosService() {
        return reasignacionInsumosService;
    }

    public void setAsignarInsumoSubadminDTO(AsignarInsumoSubadminDTO asignarInsumoSubadminDTO) {
        this.asignarInsumoSubadminDTO = asignarInsumoSubadminDTO;
    }

    public AsignarInsumoSubadminDTO getAsignarInsumoSubadminDTO() {
        return asignarInsumoSubadminDTO;
    }

    public void setArchivoDescargaExpediente(StreamedContent archivoDescargaExpediente) {
        this.archivoDescargaExpediente = archivoDescargaExpediente;
    }

    public StreamedContent getArchivoDescargaExpediente() {
        archivoDescargaExpediente = getDescargaArchivo(
                getAsignarInsumoSubadminDTO().getExpedienteSeleccionado().getRutaArchivo()
                + getAsignarInsumoSubadminDTO().getExpedienteSeleccionado().getNombre(),
                getAsignarInsumoSubadminDTO().getExpedienteSeleccionado().getNombre());

        return archivoDescargaExpediente;
    }

    public StreamedContent getArchivoDescarga() {
        return getDescargaArchivo(getAsignarInsumoSubadminDTO().getRutaArchivo() + getAsignarInsumoSubadminDTO().getNombreArchivo(), getAsignarInsumoSubadminDTO().getNombreArchivo());
    }

    private void crearTablaNotificacion(List<FecetInsumo> insumos, StringBuilder table, StringBuilder folios) {
        table.append(Constantes.TABLA_SUPERIOR);
        for (FecetInsumo insumo : insumos) {
            table.append(Constantes.TABLA_UNO);
            table.append(insumo.getIdRegistro());
            table.append(Constantes.TABLA_DOS);
            if (!folios.toString().isEmpty()) {
                folios.append(", ");
            }
            folios.append(insumo.getIdRegistro());
        }

        table.append(Constantes.TABLA_INFERIOR);
    }

    private Map<String, List<FecetInsumo>> obtenerAdministradores(List<FecetReasignacionInsumo> reasignaciones) {
        Map<String, List<FecetInsumo>> administradores = new HashMap<String, List<FecetInsumo>>();
        FecetInsumo insumo = null;
        String par;
        for (FecetReasignacionInsumo reasignacion : reasignaciones) {
            insumo = getReasignacionInsumosService().getReasignacionInsumoByInsumo(reasignacion.getIdInsumo());
            par = String.format("%s-%s-%s", reasignacion.getRfcAdministradorDestino(), reasignacion.getRfcAdministradorOrigen(), String.valueOf(insumo.getIdArace()));
            if (administradores.get(par) == null) {
                administradores.put(par, new ArrayList<FecetInsumo>());
            }
            administradores.get(par).add(insumo);
        }
        return administradores;
    }

    private String obtenerNombreAdministradorOrigen(String[] rfcs) {
        String nombre = "";
        try {
            EmpleadoDTO empleado = obtenerEmpleadoAcceso(rfcs[RFC_ORIGEN_POSICION], TipoEmpleadoEnum.ASIGNADOR_INSUMOS);
            nombre = empleado.getNombreCompleto();
        } catch (NoExisteEmpleadoException e) {
            logger.error("Error al obtener empleado", e);
        }
        return nombre;
    }

}
