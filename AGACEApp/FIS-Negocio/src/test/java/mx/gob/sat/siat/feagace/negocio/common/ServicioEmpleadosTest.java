/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.common;

import java.util.Set;
import java.util.TreeSet;
import mx.gob.sat.siat.base.BaseTest;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.negocio.EmpleadoService;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class ServicioEmpleadosTest extends BaseTest {
    
    @Autowired
    @Qualifier("empleadoService")
    private EmpleadoService empleadoService;
    
    private EmpleadoDTO empleado;
    
    @Before
    public void init() {
        empleado = new EmpleadoDTO();
        empleado.setRfc("IAPJ8510147C8");
        //empleado.setRfc("FIMA63");
    }
    
    @Test
    //@Ignore
    public void getSubordinados() {
        try {
            assert empleadoService != null;
            empleado = empleadoService.getEmpleadoCompleto(empleado.getRfc());
            Set<String> lstRfcs;
            //lstRfcs = empleadoService.getAllRfcsEmpleados(ClvSubModulosAgace.INSUMOS);
            lstRfcs = new TreeSet<String>();
            if (lstRfcs != null) {
                for (String rfc : lstRfcs) {
                    System.err.println(rfc);
                }
            }
            logger.debug(empleado);
        } catch (EmpleadoServiceException ex) {
            logger.error(ex.getMessage());
        }
        
    }
    
}
