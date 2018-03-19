package mx.gob.sat.siat.feagace.negocio.common;

import mx.gob.sat.siat.base.BaseTest;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class FestivosServiceTest extends BaseTest {
    
    @Autowired
    private FestivosService  festivosService;
    
    @Test
    @Ignore
    public void getTodosDiasFestivos(){
            Assert.assertNotNull(festivosService.getTodosDiasFestivos());
    }

}
