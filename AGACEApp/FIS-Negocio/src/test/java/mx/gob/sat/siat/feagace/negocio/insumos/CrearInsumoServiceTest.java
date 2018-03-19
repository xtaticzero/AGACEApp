package mx.gob.sat.siat.feagace.negocio.insumos;

import java.math.BigDecimal;
import mx.gob.sat.siat.base.BaseTest;
import org.junit.Ignore;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CrearInsumoServiceTest extends BaseTest {

    @Autowired
    private CrearInsumoService crearInsumoService;

    @Test
    @Ignore
    public void getCatalogoSector() {
        System.out.println(crearInsumoService.getCatalogoSector(new BigDecimal (19)));
    }

}
