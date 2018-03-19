package mx.gob.sat.siat.feagace.common;

import mx.gob.sat.siat.base.BaseTest;
import mx.gob.sat.siat.sise.service.SiseService;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SiseServiceTest extends BaseTest {

    @Autowired
    private SiseService siseService;

    @Test
    public void verInformacion() {
        Assert.assertEquals(siseService.verInformacion("PEPA520828I46"),0);
    }

}
