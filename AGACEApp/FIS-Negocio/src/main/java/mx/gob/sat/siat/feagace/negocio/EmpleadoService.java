package mx.gob.sat.siat.feagace.negocio;

import java.math.BigDecimal;
import java.util.Date;

import java.util.List;
import java.util.Map;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;

import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.ws.empleado.bean.FechasHabileseInhabiles;
import mx.gob.sat.siat.ws.empleado.bean.INPC;
import mx.gob.sat.siat.ws.empleado.bean.SalarioMinimo;
import mx.gob.sat.siat.ws.empleado.bean.Vacacion;

public interface EmpleadoService {

    String MSG_ERROR_CONSULTA_CLIENTE = "error.consulta.cliente";
    String MSG_ERROR_EMPLEADO_NO_EXISTE = "no.existente";
    String MSG_ERROR_TIPO_EMPLEADO_UNIDADADMIN = "no.existen.usuarios.en.unidad.admin";
    String MSG_ERROR_UNUDAD_ADMINISTRATIVA_NO_EXISTE = "arace.no.existente";
    String MSG_ERROR_TIPO_EMPLEADO_NO_EXISTE = "tipo.empleado.no.existente";
    String MSG_ERROR_SIN_SUBORDINADOS = "sin.coincidencias";
    String MSG_ERROR_METODO_SERVICIO = "metodo.servicio";

    //Nuevos Metodos 
    List<Vacacion> traeVacaciones(Integer periodo) throws EmpleadoServiceException;

    List<FechasHabileseInhabiles> getCatalogoDiasHabilesInhabiles(Date fechaInicio, Date fechaFin) throws EmpleadoServiceException;

    SalarioMinimo traeSalario() throws EmpleadoServiceException;

    List<INPC> traeINPC(Integer periodo) throws EmpleadoServiceException;

    List<EmpleadoDTO> getEmpleadosXUnidadAdmva(Integer claveALAF,
            Integer clavePerfil,
            ClvSubModulosAgace claveModulo) throws EmpleadoServiceException;

    EmpleadoDTO getEmpleadoCompleto(Integer numeroEmpleado) throws EmpleadoServiceException;

    EmpleadoDTO getInformacionEmpleado(String rfcEmpleado) throws EmpleadoServiceException;

    EmpleadoDTO getEmpleadoCompleto(String rfc) throws EmpleadoServiceException;

    Map<Integer, TipoEmpleadoEnum> getMapTipoEmpleado();

    List<EmpleadoDTO> informacionEmpleados(Integer[] numeroEmpleados) throws EmpleadoServiceException;

    List<AraceDTO> getUnidadesAdministrativas(EmpleadoDTO empCompleto) throws EmpleadoServiceException;

    List<AraceDTO> getUnidadesAdministrativasXGeneral(EmpleadoDTO empledo) throws EmpleadoServiceException;

    Map<Integer, AraceDTO> getMapAraceDTOFromLstUnidadAdmin(EmpleadoDTO empleado) throws EmpleadoServiceException;

    Map<Integer, AraceDTO> getMapAraceDTOFromLstUnidadAdmin(List<AraceDTO> lstUnidadAdmin, EmpleadoDTO empleado) throws EmpleadoServiceException;

    //Viejos Metodos
    List<String> getLstCorreosNotificacionXTipoEmpleadoUnidaAdmin(TipoEmpleadoEnum tipoEmpleado,
            BigDecimal idArace,
            ClvSubModulosAgace clvModulo) throws EmpleadoServiceException;

    List<EmpleadoDTO> getLstSubordinadosPorRelacionEmpleado(EmpleadoDTO empleadoDTO,
            TipoEmpleadoEnum tipoEmpleadoPatron,
            TipoEmpleadoEnum tipoEmpleadoSubordinado) throws EmpleadoServiceException;

    void completarLstAracesDetalle(EmpleadoDTO empleadoDto, Map<Integer, AraceDTO> mapUnidadesAdmin, List<AraceDTO> lstUnidadAdmin);

    void completarAracesDetalle(Map<Integer, AraceDTO> mapUnidadesAdmin, List<AraceDTO> lstUnidadAdmin);
    
    boolean validarExistenciaTipoEmpleado(EmpleadoDTO empleado, TipoEmpleadoEnum tipoEmpleado) throws EmpleadoServiceException;

}
