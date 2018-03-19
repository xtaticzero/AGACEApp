package mx.gob.sat.siat.feagace.negocio.ordenes.suspension;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.base.BaseTest;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetSuspensionDTO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.tiempos.helper.TiemposHelper;
import mx.gob.sat.siat.feagace.negocio.ordenes.suspension.helper.SuspensionHelper;
import mx.gob.sat.siat.feagace.negocio.ordenes.suspension.service.SuspensionService;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import org.junit.Ignore;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;


public class SuspensionServiceTest extends BaseTest {

    private static final Logger LOG = Logger.getLogger(SuspensionServiceTest.class.getName());
    
    @Autowired
    private SuspensionService suspensionService;

    @Autowired
    private TiemposHelper tiemposHelper;

    @Autowired
    private SuspensionHelper suspenisonHelper;

    static {
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.DEBUG);
        rootLogger.addAppender(new ConsoleAppender(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} [%-5p] %C.%M():%L - %m%n")));
    }

    @Ignore
    @Test
    public void testBuscarSuspensionCompulsa() {
        LOG.debug("[testBuscarSuspensionCompulsa]");
        final long idOrdenL = 3L;
        final BigDecimal idOrden = BigDecimal.valueOf(idOrdenL);
        try {
            List <FecetSuspensionDTO> suspensiones = suspensionService.buscarSuspensionIdTipoOficio(idOrden, Constantes.ID_OFICIO_COMPULSA_TERCEROS);
            suspenisonHelper.validarLista(suspensiones, "suspensiones");
            for(FecetSuspensionDTO suspension : suspensiones) {
                LOG.debug(String.format("\nSUSPENSION [%s] ", suspension.toString()));
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Ignore
    @Test
    public void testIniciaSuspensionOrden() {
        LOG.debug("[testIniciaSuspensionOrden]");
        final long idOrdenL = 3L;
        final int diasSuamr = 5;
        final BigDecimal idOrden = BigDecimal.valueOf(idOrdenL);
        AgaceOrden orden = new AgaceOrden();
        orden.setIdOrden(idOrden);
        final Date hoy = new Date();
        try {
            Date fechaSumada = tiemposHelper.sumarDiasHabiles(hoy, diasSuamr);
            suspensionService.iniciaSuspensionOrden(orden, hoy, fechaSumada);
            LOG.debug(String.format("\nFECHA INICIO SUSPENSION [%s] FECHA FIN SUSPENSION [%s] ", hoy, fechaSumada));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
