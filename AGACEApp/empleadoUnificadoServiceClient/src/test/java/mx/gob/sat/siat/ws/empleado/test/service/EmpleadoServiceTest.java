/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.ws.empleado.test.service;

import java.util.Date;
import java.util.List;
import mx.gob.sat.siat.ws.empleado.bean.DetalleEmpleado;
import mx.gob.sat.siat.ws.empleado.bean.Empleado;
import mx.gob.sat.siat.ws.empleado.bean.EmpleadoCompleto;
import mx.gob.sat.siat.ws.empleado.bean.FechasHabileseInhabiles;
import mx.gob.sat.siat.ws.empleado.bean.INPC;
import mx.gob.sat.siat.ws.empleado.bean.SalarioMinimo;
import mx.gob.sat.siat.ws.empleado.bean.UnidadAdministrativa;
import mx.gob.sat.siat.ws.empleado.bean.Vacacion;
import mx.gob.sat.siat.ws.empleado.client.EmpleadoUnitedServiceClient;
import mx.gob.sat.siat.ws.empleado.client.impl.EmpleadoClientHelper;
import mx.gob.sat.siat.ws.empleado.constantes.TipoSistemaFiscalizacion;
import mx.gob.sat.siat.ws.empleado.exception.EmpleadoServiceClientException;
import mx.gob.sat.siat.ws.empleado.test.base.BaseTest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class EmpleadoServiceTest extends BaseTest {

    @Autowired
    private EmpleadoUnitedServiceClient empleadoUnitedServiceClient;

    private String rfcAgace;
    private Integer anioPeriodo;
    private Integer unidadAdministrativa;
    private Integer clavePerfil;
    private Integer claveModulo;
    private Integer numeroEmpleado;
    private Date fechaInicio;
    private Date fechaFin;
    private Integer[] numerosEmpleado;
    private TipoSistemaFiscalizacion tipoFiscalizacion;

    @Before
    public void init() {
        //Servicio
        assert empleadoUnitedServiceClient != null;
        
        //Parametros
        anioPeriodo = 2016;
        rfcAgace = "AUGY800716AJ4";
        numeroEmpleado = 45332;
        fechaInicio = EmpleadoClientHelper.getDateFromDDMMYYYY("10/01/2015");
        fechaFin = EmpleadoClientHelper.getDateFromDDMMYYYY("10/12/2016");
        tipoFiscalizacion = empleadoUnitedServiceClient.getTipoFiscalizacion();
        unidadAdministrativa = 1;
        clavePerfil = 2;
        claveModulo = 1002;
        numerosEmpleado = new Integer[]{47847};        
    }

    @Test
    @Ignore
    public void traeVacacionesTest() {
        try {
            logger.info("traeVacacionesTest");
            List<Vacacion> lstVacaciones = empleadoUnitedServiceClient.traeVacaciones(anioPeriodo);

            logger.info("Periodo : " + anioPeriodo);
            for (Vacacion vac : lstVacaciones) {
                logger.info("Dis de vacaciones : " + vac);
            }
        } catch (EmpleadoServiceClientException ex) {
            logger.error(ex.getMessage(), ex);
        }

    }

    @Test
    @Ignore
    public void getCatalogoDiasHabilesInhabilesTest() {
        try {
            logger.info("getCatalogoDiasHabilesInhabilesTest");
            List<FechasHabileseInhabiles> lstDias = empleadoUnitedServiceClient.getCatalogoDiasHabilesInhabiles(fechaInicio, fechaFin);

            for (FechasHabileseInhabiles dia : lstDias) {
                logger.info("Dia Inhabil : " + dia);
            }

        } catch (EmpleadoServiceClientException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    @Test
    @Ignore
    public void traeSalarioTest() {
        try {
            logger.info("traeSalarioTest");
            SalarioMinimo salarioMinimo = empleadoUnitedServiceClient.traeSalario();
            logger.info("Salario Minimo : " + salarioMinimo);
        } catch (EmpleadoServiceClientException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    @Test
    @Ignore
    public void traeINPCTest() {
        try {
            logger.info("traeINPCTest");
            List<INPC> lstInpc = empleadoUnitedServiceClient.traeINPC(anioPeriodo);
            for (INPC inpc : lstInpc) {
                logger.info("Salario Minimo : " + inpc);
            }
        } catch (EmpleadoServiceClientException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    @Test
    @Ignore
    public void getEmpleadosXUnidadAdmvaTest() {
        try {
            logger.info("getEmpleadosXUnidadAdmvaTest");
            List<Empleado> lstEmpleados = empleadoUnitedServiceClient.getEmpleadosXUnidadAdmva(tipoFiscalizacion, unidadAdministrativa, clavePerfil, claveModulo);
            for (Empleado empleado : lstEmpleados) {
                logger.info("Empleado : " + empleado);
            }
        } catch (EmpleadoServiceClientException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    @Test
    @Ignore
    public void informacionEmpleadosTest() {
        try {
            logger.info("informacionEmpleadosTest");
            List<Empleado> lstEmpleados = empleadoUnitedServiceClient.informacionEmpleados(numerosEmpleado, tipoFiscalizacion);

            for (Empleado col : lstEmpleados) {
                logger.info(col);
            }

        } catch (EmpleadoServiceClientException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    @Test
    @Ignore
    public void getUnidadesAdministrativasTest() {

        try {
            logger.info("getUnidadesAdministrativas");
            List<UnidadAdministrativa> lstUnidades = empleadoUnitedServiceClient.getUnidadesAdministrativas(tipoFiscalizacion);
            if (lstUnidades != null) {
                for (UnidadAdministrativa unidadAdmin : lstUnidades) {
                    logger.info(" unidad: " + unidadAdmin);
                }
            }
        } catch (EmpleadoServiceClientException ex) {
            logger.error(ex.getMessage(), ex);
        }

    }
    
    @Test
    @Ignore
    public void getUnidadesAdministrativasPorGeneralTest() {

        try {
            logger.info("getUnidadesAdministrativasPorGeneralTest");
            EmpleadoCompleto empleadosCompleto = empleadoUnitedServiceClient.getEmpleadoCompleto(tipoFiscalizacion, rfcAgace, null);
            
            List<UnidadAdministrativa> lstUnidades = empleadoUnitedServiceClient.getUnidadesAdministrativasPorGeneral(empleadosCompleto.getEmpleado().getIdAdmGral());
            if (lstUnidades != null) {
                for (UnidadAdministrativa unidadAdmin : lstUnidades) {
                    logger.info(" unidad: " + unidadAdmin);
                }
            }
        } catch (EmpleadoServiceClientException ex) {
            logger.error(ex.getMessage(), ex);
        }

    }

    @Test
    @Ignore
    public void getEmpleadoTest() {
        try {
            logger.info("getEmpleadoTest");
            Empleado empleado = empleadoUnitedServiceClient.getEmpleado(null, rfcAgace, tipoFiscalizacion);

            logger.info(" getEmpleado : " + empleado);

        } catch (EmpleadoServiceClientException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
    
    @Test
    public void getInformacionEmpleadoTest() {
        try {
            logger.info("getInformacionEmpleadoTest");            
            EmpleadoCompleto empleadosCompleto = empleadoUnitedServiceClient.getInformacionEmpleado(tipoFiscalizacion, rfcAgace, 23232);
            logger.info("Empleado Completo empleado : " + empleadosCompleto.getEmpleado());
            logger.info("******************LstSubordinados**********************");
            for (Empleado col : empleadosCompleto.getListaSubordinados()) {
                logger.info(col);
            }
            logger.info("******************ListaJefesSuperiores**********************");
            for (Empleado col : empleadosCompleto.getListaJefesSuperiores()) {
                logger.info(col);
            }
            logger.info("******************ListaDetalleEmpleado**********************");
            for (DetalleEmpleado col : empleadosCompleto.getListaDetalleEmpleado()) {
                logger.info(col);
            }
        } catch (EmpleadoServiceClientException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
    
    @Test
    @Ignore
    public void getIDServicio(){
        try {
            logger.info("El tipo de servicio es:");
            logger.info(empleadoUnitedServiceClient.getTipoFiscalizacion().getIdSistemaFiscalizacion());
            logger.info(empleadoUnitedServiceClient.getTipoFiscalizacion().getDescripcion());
        } catch (Exception e) {
            logger.error(e);
        }
    }

}
