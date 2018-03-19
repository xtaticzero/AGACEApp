package mx.gob.sat.siat.feagace.negocio.insumos;

import java.math.BigDecimal;

import mx.gob.sat.siat.base.BaseTest;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ConsultaAsignacionInsumoServiceTest extends BaseTest {

    @Autowired
    private ConsultaAsignacionInsumoService consultaAsignacionInsumoService;

    private FecetInsumo fecetInsumo;

    @Before
    public void init() {
        fecetInsumo = new FecetInsumo();
        fecetInsumo.setIdInsumo(new BigDecimal(999));
    }

    @Test
    @Ignore
    public void getSubAdministradoresACPPCE() {
        System.out.println("consultaAsignacionInsumoService.getSubAdministradoresACPPCE()");
    }

    @Test
    @Ignore
    public void guardarAsignacionSubadministrador() {
        consultaAsignacionInsumoService.guardarAsignacionSubadministrador(fecetInsumo);
    }

    @Test
    @Ignore
    public void getContadorInsumos() {
        System.out.println(consultaAsignacionInsumoService.getContadorInsumos());
    }

}
