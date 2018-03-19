package mx.gob.sat.siat.ws.nyv.test.base;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/**/test-main-context.xml"})
public abstract class BaseTest {

    /**
     * Objeto del logger.
     */
    protected final Logger logger;

    public BaseTest() {
        logger = Logger.getLogger(getClass());
    }

    public Logger getLogger() {
        return logger;
    }
}
