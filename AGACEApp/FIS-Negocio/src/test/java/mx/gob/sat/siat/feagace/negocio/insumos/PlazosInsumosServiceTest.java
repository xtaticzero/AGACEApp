package mx.gob.sat.siat.feagace.negocio.insumos;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import junit.framework.Assert;
import mx.gob.sat.siat.base.BaseTest;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.AgaceOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetOficioDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetTipoOficio;
import mx.gob.sat.siat.feagace.negocio.common.plazos.exception.PlazosException;
import mx.gob.sat.siat.feagace.negocio.common.plazos.service.PlazosServiceOrdenes;
import mx.gob.sat.siat.feagace.negocio.common.tiempos.helper.TiemposHelper;

/**
 * Clase para pruebas unitarias de plazos
 *
 * @author eolf
 */
@Transactional
public class PlazosInsumosServiceTest extends BaseTest {

    private static final Logger LOG = Logger.getLogger(PlazosInsumosServiceTest.class.getName());

    @Autowired
    private PlazosServiceOrdenes plazosService;

    @Autowired
    private FecetOficioDao fecetOficioDao;

    @Autowired
    private AgaceOrdenDao agaceOrdenDao;

    @Autowired
    private TiemposHelper tiemposHelper;

    static {
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.DEBUG);
        rootLogger.addAppender(new ConsoleAppender(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} [%-5p] %C.%M():%L - %m%n")));
    }

    @Ignore
    @Test
    public void validarPlazoCargaDocumentosRequeridosOrdenT1() throws PlazosException {
        AgaceOrden orden = new AgaceOrden();
        orden.setIdOrden(BigDecimal.ONE);
        orden.setIdMetodo(BigDecimal.ONE);
        orden.setFolioNYV("1234");
        orden.setFechaNotifCont(new Date());
        orden.setFechaSurteEfectos(new Date());
        Assert.assertEquals(true, plazosService.validarPlazoCargaDocumentosRequeridosOrden(orden));
        // Assert.assertEquals(true, plazosService.validarPlazoResolucionOrden(orden));
    }

    @Ignore
    @Rollback(value = true)
    @Test
    public void validarPlazoCargaDocumentosRequeridosOrdenConProrroga() throws PlazosException, ParseException {
        AgaceOrden orden = new AgaceOrden();
        orden.setIdOrden(new BigDecimal("9999999999999999999999"));
        orden.setIdMetodo(BigDecimal.valueOf(2));
        orden.setFolioNYV("1234");
        String dateStr = "12/12/2015";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        orden.setFechaNotifCont(sdf.parse(dateStr));
        orden.setFechaSurteEfectos(sdf.parse(dateStr));
        Assert.assertEquals(false, plazosService.validarPlazoCargaDocumentosRequeridosOrden(orden));

    }

    @Ignore
    @Rollback(value = true)
    @Test
    public void validarPlazoCargaDocumentosRequeridosOficioT1() throws PlazosException {
        LOG.debug("[validarPlazoCargaDocumentosRequeridosOficioT1]");
        final String idOficioS = "99999999";
        BigDecimal idOficio = new BigDecimal(idOficioS);
        FecetOficio oficio = fecetOficioDao.getOficioById(idOficio);
        FecetTipoOficio tipoOficio = new FecetTipoOficio();
        tipoOficio.setIdTipoOficio(BigDecimal.ONE);
        oficio.setFecetTipoOficio(tipoOficio);
        Assert.assertEquals(false, plazosService.validarPlazoCargaDocumentosRequeridosOficio(oficio));
    }

}
