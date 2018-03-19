/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.propuestas;

import mx.gob.sat.siat.feagace.negocio.propuestas.validar.ValidarRetroalimentarPropuestaService;
import mx.gob.sat.siat.base.BaseTest;
import mx.gob.sat.siat.base.excepcion.ValidarRetroalimentarPropuestaException;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class ValidarYRetroalimentarTest extends BaseTest {

    @Autowired
    private ValidarRetroalimentarPropuestaService validarRetroalimentarPropuestaService;

    @Test
    @Ignore
    public void obtenerCorreos() {
        try {

            Assert.assertNotNull(validarRetroalimentarPropuestaService
                    .buscaPropuestasPorValidar("MUSC8304282A2"));
            
            Assert.assertNotNull(validarRetroalimentarPropuestaService
                    .buscaPropuestasMarcadasPendientes("MUSC8304282A2"));
            
        } catch (ValidarRetroalimentarPropuestaException e) {
            System.err.println(e.getMessage());
        }
    }
}
