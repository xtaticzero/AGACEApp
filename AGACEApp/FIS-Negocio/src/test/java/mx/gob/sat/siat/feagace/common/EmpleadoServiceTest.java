/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.common;

import java.util.List;
import java.util.Map;
import mx.gob.sat.siat.base.BaseTest;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
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
public class EmpleadoServiceTest extends BaseTest {

    @Autowired
    @Qualifier("empleadoService")
    private EmpleadoService empleadoService;

    private String rfc;

    @Before
    public void init() {
        rfc = "AUGY800716AJ4";
        //rfc = "AUG6AJ4";
    }

    @Test
    public void verificarEmpleado() {
        try {
            EmpleadoDTO empleado = new EmpleadoDTO();
            empleado.setRfc(rfc);
            EmpleadoDTO empleadoTmp = empleadoService.getEmpleadoCompleto(rfc);
            logger.debug("====================================================");
            logger.debug(empleadoTmp);

            for (Map.Entry<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>> entrySet : empleadoTmp.getSubordinados().entrySet()) {
                TipoEmpleadoEnum key = entrySet.getKey();
                Map<TipoEmpleadoEnum, List<EmpleadoDTO>> value = entrySet.getValue();
                logger.debug("Relacion empleado " + key.name() + " " + key.getDescripcion());
                for (Map.Entry<TipoEmpleadoEnum, List<EmpleadoDTO>> entryint : value.entrySet()) {
                    TipoEmpleadoEnum keyIn = entryint.getKey();
                    List<EmpleadoDTO> valueIn = entryint.getValue();
                    logger.debug("Relacion Subordinado " + keyIn.name() + " " + keyIn.getDescripcion());
                    for (EmpleadoDTO subordinado : valueIn) {
                        logger.debug("Subordinado " + subordinado);
                    }
                }

            }

            logger.debug("====================================================");

            for (TipoEmpleadoEnum tipoResponsable : TipoEmpleadoEnum.values()) {
                for (TipoEmpleadoEnum tipoSubordinado : TipoEmpleadoEnum.values()) {
                    List<EmpleadoDTO> lstEmpleado = empleadoService.getLstSubordinadosPorRelacionEmpleado(empleadoTmp, tipoResponsable, tipoSubordinado);

                    if (lstEmpleado != null) {
                        logger.debug("=============================================");
                        logger.debug("RElacion Empleado : " + tipoResponsable.name() + "-" + tipoSubordinado.name());
                        for (EmpleadoDTO empl : lstEmpleado) {
                            logger.debug(empl);
                        }
                    } else {
                        logger.warn("=============================================");
                        logger.warn("No exiaten registros de esta relacion");
                        logger.warn("RElacion Empleado : " + tipoResponsable.name() + "-" + tipoSubordinado.name());
                    }
                }
            }
        } catch (EmpleadoServiceException ex) {
            logger.error(ex.getMessage());
        }
    }
}
