/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.consulta.propuestas;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FiltroPropuestas;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorEstatusPropuestasEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.negocio.bo.consulta.ConsultaEjecutivaPropuestasBO;
import mx.gob.sat.siat.feagace.negocio.consulta.propuestas.filtro.FiltroConsultaPropuestas;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.consulta.ConsultaEjecutivaPropuestasServiceException;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public interface ConsultaEjecutivaPropuestasService {

    String MSG_ERROR_REGISTRO_EMPLEADO = "registro.empleado";
    String MSG_ERROR_USUARIO_SIN_PRIVILEGIOS = "sin.privilegios";
    String MSG_ERROR_PARAMETRO_FILTRO = "parametros.filtro";

    ConsultaEjecutivaPropuestasBO getAccesoEmpleadoAConsultaPropuestas(EmpleadoDTO empleado) throws ConsultaEjecutivaPropuestasServiceException;

    ConsultaEjecutivaPropuestasBO consultarPropuestas(ConsultaEjecutivaPropuestasBO consultaBO, FiltroConsultaPropuestas filtroDeBusqueda) throws ConsultaEjecutivaPropuestasServiceException;

    ConsultaEjecutivaPropuestasBO consultarPropuestasXEstatusoGrupo(ConsultaEjecutivaPropuestasBO consultaBO, FiltroConsultaPropuestas filtroDeBusqueda, AgrupadorEstatusPropuestasEnum grupoSeleccionado) throws ConsultaEjecutivaPropuestasServiceException;

    ConsultaEjecutivaPropuestasBO consultarPropuestasXEmpleadoSubordinado(ConsultaEjecutivaPropuestasBO consultaBO, EmpleadoDTO empleadoSeleccionado) throws ConsultaEjecutivaPropuestasServiceException;

    HSSFWorkbook creaExcel(List<FecetPropuesta> listaPropuestas);

    void consultarEstatusPropuestasSuper(ConsultaEjecutivaPropuestasBO consultaBO, FiltroPropuestas filtroDeBusqueda);

    boolean validarExistenciaTipoEmpleado(EmpleadoDTO empleado, TipoEmpleadoEnum tipoEmpleado) throws EmpleadoServiceException;

}
