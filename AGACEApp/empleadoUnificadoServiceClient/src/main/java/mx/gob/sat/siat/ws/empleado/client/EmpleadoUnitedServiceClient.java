/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.ws.empleado.client;

import java.util.Date;
import java.util.List;
import mx.gob.sat.siat.ws.empleado.bean.Empleado;
import mx.gob.sat.siat.ws.empleado.bean.EmpleadoCompleto;
import mx.gob.sat.siat.ws.empleado.bean.FechasHabileseInhabiles;
import mx.gob.sat.siat.ws.empleado.bean.INPC;
import mx.gob.sat.siat.ws.empleado.bean.SalarioMinimo;
import mx.gob.sat.siat.ws.empleado.bean.UnidadAdministrativa;
import mx.gob.sat.siat.ws.empleado.bean.Vacacion;
import mx.gob.sat.siat.ws.empleado.constantes.TipoSistemaFiscalizacion;
import mx.gob.sat.siat.ws.empleado.exception.EmpleadoServiceClientException;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public interface EmpleadoUnitedServiceClient {

    List<Vacacion> traeVacaciones(Integer periodo) throws EmpleadoServiceClientException;

    List<FechasHabileseInhabiles> getCatalogoDiasHabilesInhabiles(Date fechaInicio, Date fechaFin) throws EmpleadoServiceClientException;

    SalarioMinimo traeSalario() throws EmpleadoServiceClientException;

    List<INPC> traeINPC(Integer anio) throws EmpleadoServiceClientException;

    List<Empleado> getEmpleadosXUnidadAdmva(TipoSistemaFiscalizacion tipoModulo,
            Integer claveALAF,
            Integer clavePerfil,
            Integer claveModulo) throws EmpleadoServiceClientException;

    List<Empleado> informacionEmpleados(
            Integer[] numeroEmpleados,
            TipoSistemaFiscalizacion tipoModulo) throws EmpleadoServiceClientException;

    List<UnidadAdministrativa> getUnidadesAdministrativas(TipoSistemaFiscalizacion tipoModulo) throws EmpleadoServiceClientException;

    List<UnidadAdministrativa> getUnidadesAdministrativasPorGeneral(Integer idAdmGral) throws EmpleadoServiceClientException;

    Empleado getEmpleado(
            Integer numeroEmpleado,
            String empleado,
            TipoSistemaFiscalizacion tipoModulo) throws EmpleadoServiceClientException;

    EmpleadoCompleto getEmpleadoCompleto(
            TipoSistemaFiscalizacion tipoSistema,
            String rfc,
            Integer numeroEmpleado) throws EmpleadoServiceClientException;

    EmpleadoCompleto getInformacionEmpleado(
            TipoSistemaFiscalizacion tipoSistema,
            String rfc,
            Integer numeroEmpleado) throws EmpleadoServiceClientException;

    TipoSistemaFiscalizacion getTipoFiscalizacion();
}
