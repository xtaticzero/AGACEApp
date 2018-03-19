package mx.gob.sat.siat.feagace.negocio.insumos;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mx.gob.sat.siat.base.BaseTest;
import org.junit.Ignore;

public class SeguimientoInsumosServiceTest extends BaseTest {

    @Autowired
    private SeguimientoInsumosService seguimientoInsumosService;

    @Test
    @Ignore
    public void getFecetRechazoByInsumo() {
        System.out.println(seguimientoInsumosService
                .getFecetRechazoByInsumo(new BigDecimal(10003)));
    }
}
