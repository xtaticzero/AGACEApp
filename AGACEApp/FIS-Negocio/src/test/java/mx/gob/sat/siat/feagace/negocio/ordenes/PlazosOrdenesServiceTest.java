package mx.gob.sat.siat.feagace.negocio.ordenes;

import java.util.Calendar;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import junit.framework.Assert;
import mx.gob.sat.siat.base.BaseTest;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.negocio.common.plazos.exception.PlazosException;
import mx.gob.sat.siat.feagace.negocio.common.plazos.service.PlazosServiceInsumos;


/**
 * Clase para pruebas unitarias de plazos
 *
 * @author eolf
 */
@Transactional
public class PlazosOrdenesServiceTest extends BaseTest {

    private static final Logger LOG = Logger.getLogger(PlazosOrdenesServiceTest.class.getName());

  
    @Autowired
    private PlazosServiceInsumos plazosService;


    static {
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.DEBUG);
        rootLogger.addAppender(new ConsoleAppender(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} [%-5p] %C.%M():%L - %m%n")));
    }
       
    @Test
    @Ignore
    public void validarPlazoCargaDocumentosRequeridosOrdenT1() throws PlazosException{
	   LOG.debug("Inicializando prueba de oficio");
       FecetInsumo insumo = new FecetInsumo();
       insumo.setFechaInicioPlazo(Calendar.getInstance().getTime());
       //plazosService.inicializarInsumoConPlazos(insumo);
       Assert.assertEquals(true, insumo.getDiasDespuesDePlazo() > 0);   
    }   
}
