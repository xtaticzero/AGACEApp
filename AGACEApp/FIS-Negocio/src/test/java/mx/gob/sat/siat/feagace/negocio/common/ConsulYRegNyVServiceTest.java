package mx.gob.sat.siat.feagace.negocio.common;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.sat.siat.base.BaseTest;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.FececActosAdm;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;

@Transactional
public class ConsulYRegNyVServiceTest extends BaseTest {
    
    @Autowired
    private ConsultaRegistraNyVService consulYRegNyVService;
            
    
    @Ignore
    //@Rollback(value = true)
    @Test
    public void validaRegistroActoAdmin(){
        AgaceOrden orden = new AgaceOrden();
        FececActosAdm actoAdmin = new FececActosAdm();
        try {
            consulYRegNyVService.registrarActoAdministrativo(orden, new Date(), actoAdmin);
        } catch (NegocioException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
