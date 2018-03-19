/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.ws.empleado.client.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import mx.gob.sat.siat.ws.empleado.bean.Empleado;
import mx.gob.sat.siat.ws.empleado.bean.EmpleadoCompleto;
import mx.gob.sat.siat.ws.empleado.bean.FechasHabileseInhabiles;
import mx.gob.sat.siat.ws.empleado.bean.INPC;
import mx.gob.sat.siat.ws.empleado.bean.SalarioMinimo;
import mx.gob.sat.siat.ws.empleado.bean.UnidadAdministrativa;
import mx.gob.sat.siat.ws.empleado.bean.Vacacion;
import mx.gob.sat.siat.ws.empleado.client.EmpleadoUnitedServiceClient;
import mx.gob.sat.siat.ws.empleado.constantes.TipoModuloEnum;
import mx.gob.sat.siat.ws.empleado.constantes.TipoSistemaFiscalizacion;
import mx.gob.sat.siat.ws.empleado.exception.EmpleadoServiceClientException;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class EmpleadoUnitedServiceMockClientImpl implements Serializable, EmpleadoUnitedServiceClient {

    private static final long serialVersionUID = -8791030345803608839L;

    @Override
    public List<Vacacion> traeVacaciones(Integer periodo) throws EmpleadoServiceClientException {
        throw new EmpleadoServiceClientException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<FechasHabileseInhabiles> getCatalogoDiasHabilesInhabiles(Date fechaInicio, Date fechaFin) throws EmpleadoServiceClientException {
        throw new EmpleadoServiceClientException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SalarioMinimo traeSalario() throws EmpleadoServiceClientException {
        throw new EmpleadoServiceClientException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<INPC> traeINPC(Integer anio) throws EmpleadoServiceClientException {
        throw new EmpleadoServiceClientException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Empleado> getEmpleadosXUnidadAdmva(TipoSistemaFiscalizacion tipoModulo, Integer claveALAF, Integer clavePerfil, Integer claveModulo) throws EmpleadoServiceClientException {
        throw new EmpleadoServiceClientException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmpleadoCompleto getEmpleadoCompleto(TipoSistemaFiscalizacion tipoModulo, String rfc, Integer numeroEmpleado) throws EmpleadoServiceClientException {
        throw new EmpleadoServiceClientException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Empleado> informacionEmpleados(Integer[] numeroEmpleados, TipoSistemaFiscalizacion tipoModulo) throws EmpleadoServiceClientException {
        throw new EmpleadoServiceClientException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UnidadAdministrativa> getUnidadesAdministrativas(TipoSistemaFiscalizacion tipoModulo) throws EmpleadoServiceClientException {
        throw new EmpleadoServiceClientException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Empleado getEmpleado(Integer numeroEmpleado, String empleado, TipoSistemaFiscalizacion tipoModulo) throws EmpleadoServiceClientException {
        throw new EmpleadoServiceClientException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TipoSistemaFiscalizacion getTipoFiscalizacion() {
        return TipoSistemaFiscalizacion.getTipoSistemaFiscalizacion(TipoModuloEnum.AGACE.getIdModulo());
    }

    @Override
    public List<UnidadAdministrativa> getUnidadesAdministrativasPorGeneral(Integer idAdmGral) throws EmpleadoServiceClientException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmpleadoCompleto getInformacionEmpleado(TipoSistemaFiscalizacion tipoSistema, String rfc, Integer numeroEmpleado) throws EmpleadoServiceClientException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
