package mx.gob.sat.siat.feagace.negocio.ordenes;

import mx.gob.sat.siat.base.BaseTest;
import mx.gob.sat.siat.feagace.negocio.ordenes.semaforo.service.SemaforoService;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import org.junit.Ignore;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;


public class SemaforoServiceTest extends BaseTest {

    @Autowired
    private SemaforoService semaforoService;

    private static final Logger LOG = Logger.getLogger(SemaforoServiceTest.class.getName());

    static {
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.DEBUG);
        rootLogger.addAppender(new ConsoleAppender(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} [%-5p] %C.%M():%L - %m%n")));
    }

    @Ignore
    @Test
    public void testSemaforoPlazos() {
        LOG.debug("[testSemaforoPlazos]");
        try {
            semaforoService.semaforoPlazos();
            LOG.debug("\nSEMAFORO PLAZOS");
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
