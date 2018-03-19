package mx.gob.sat.siat.feagace.buzon.service;

import mx.gob.sat.siat.base.BaseTest;
import mx.gob.sat.siat.buzon.exception.BuzonNoDisponibleException;
import mx.gob.sat.siat.buzon.exception.CorreoNoRegistradoException;
import mx.gob.sat.siat.buzon.service.BuzonTributarioService;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BuzonTributarioServiceTest extends BaseTest {

    @Autowired
    private BuzonTributarioService buzonTributarioService;

    @Test
    public void obtenerCorreos() {
        try {
            Assert.assertNotNull(buzonTributarioService
                    .obtenerCorreos("YRM911028F3A"));
        } catch (BuzonNoDisponibleException e) {
            Assert.fail();
        } catch (CorreoNoRegistradoException e) {
            Assert.fail();
        }
    }

    @Test
    public void obtenerMediosComunicacion() {
        try {
            Assert.assertNotNull(buzonTributarioService
                    .obtenerMediosComunicacion("YRM911028F3A"));
        } catch (BuzonNoDisponibleException e) {
            Assert.fail();
        }
    }

}
