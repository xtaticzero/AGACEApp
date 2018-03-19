package mx.gob.sat.siat.feagace.idc.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.softtek.idc.constants.IDCConstants;
import com.softtek.idc.model.IdCInterno;
import com.softtek.idc.service.IDCService;

import mx.gob.sat.siat.base.BaseTest;

public class IDCServiceTest extends BaseTest {

    @Autowired
    private IDCService idcService;

    String secciones[] = { IDCConstants.IDENTIFICACION, IDCConstants.PATENTE,
            IDCConstants.DATOS_COMPLEMENTARIOS };

    private static final String rfc = "FOGI720331SIA";

    @Test
    public void obtenerInformacionContribuyente() {
        try {
            IdCInterno idCInterno = idcService.obtenerInformacionContribuyente(
                    rfc, secciones);
            Assert.assertNotNull(idCInterno);
        } catch (Exception e) {
            Assert.fail();
        }
    }
}