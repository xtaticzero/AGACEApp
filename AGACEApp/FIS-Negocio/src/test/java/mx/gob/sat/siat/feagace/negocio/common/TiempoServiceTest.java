package mx.gob.sat.siat.feagace.negocio.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import mx.gob.sat.siat.base.BaseTest;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.AgaceOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dto.common.tiempos.model.TiempoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.negocio.common.tiempos.enums.TiemposClaveEnum;
import mx.gob.sat.siat.feagace.negocio.common.tiempos.service.TiemposService;
import mx.gob.sat.siat.ws.nyv.client.NyVServiceClient;
import mx.gob.sat.siat.ws.nyv.client.dto.ActoAdministrativoVO;
import mx.gob.sat.siat.ws.nyv.client.dto.BitacoraVO;
import mx.gob.sat.siat.ws.nyv.client.dto.ConsultaNumeroReferenciaVO;
import mx.gob.sat.siat.ws.nyv.client.dto.ResponseConsultaVO;
import mx.gob.sat.siat.ws.nyv.client.dto.TipoProcesoVO;

public class TiempoServiceTest extends BaseTest {

    private static final Logger LOG = Logger.getLogger(TiempoServiceTest.class.getName());

    @Autowired
    private TiemposService tiemposService;

    @Autowired
    @Qualifier("nyvServiceClient")
    private NyVServiceClient nyvServiceClient;

    @Autowired
    private AgaceOrdenDao agaceOrdenDao;

    static {
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.DEBUG);
        rootLogger.addAppender(new ConsoleAppender(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} [%-5p] %C.%M():%L - %m%n")));
    }

    @Ignore
    @Test
    public void testObtenerPlazo() {
        LOG.debug("[testObtenerPlazo]");
        final BigDecimal idOrden = new BigDecimal("9999999999999999999999");
        AgaceOrden orden = obtenerOrden(idOrden);
        try {
            TiempoDTO plazo = tiemposService.obtenerTiempoPlazo(orden.getIdMetodo(), TiemposClaveEnum.CDO_PLA);
            LOG.debug(String.format("\nPLAZO [%s]", plazo.toString()));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private AgaceOrden obtenerOrden(BigDecimal idOrden) {
        LOG.debug("[obtenerOrden]");
        AgaceOrden orden = new AgaceOrden();
        try {
            orden = agaceOrdenDao.findByPrimaryKey(idOrden);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return orden;
    }

    @Ignore
    @Test
    public void testNyVService() {
        LOG.debug("[testNyVService]");
        final BigDecimal idOrden = new BigDecimal("9999999999999999999999");
        try {
            AgaceOrden orden = obtenerOrden(idOrden);
            ResponseConsultaVO responseConsulta = nyvServiceClient.consultarActoAdministrativo(orden.getFolioNYV());
            if (responseConsulta != null) {
                LOG.debug(String.format("\nResponseConsulta [%s] ", methodGetName(responseConsulta)));
                List<BitacoraVO> bitacoras = responseConsulta.getBitacora();
                if (bitacoras != null) {
                    for (BitacoraVO bitacora : bitacoras) {
                        LOG.debug(String.format("\nBitacoraVO [%s] ", methodGetName(bitacora)));
                    }
                }
            }

            List<ActoAdministrativoVO> lstActos = nyvServiceClient.consultarListaActosAdmin(orden.getFolioNYV());
            if (lstActos != null && !lstActos.isEmpty()) {
                for (ActoAdministrativoVO acto : lstActos) {
                    LOG.debug(String.format("\nActoAdministrativoVO [%s] ", methodGetName(acto)));
                }
            }

            List<TipoProcesoVO> lstTiposProcesos = nyvServiceClient.consultarListaTiposProceso(orden.getFolioNYV());
            if (lstTiposProcesos != null && !lstTiposProcesos.isEmpty()) {
                for (TipoProcesoVO tipoProceso : lstTiposProcesos) {
                    LOG.debug(String.format("\nTipoProcesoVO [%s] ", methodGetName(tipoProceso)));
                }
            }

            ConsultaNumeroReferenciaVO numeroReferencia = nyvServiceClient.consultarNumeroReferencia(orden.getFolioNYV());
            if (numeroReferencia != null) {
                LOG.debug(String.format("\nResponseConsultaNumeroReferencia [%s] ", methodGetName(numeroReferencia)));
            }

            LOG.debug(String.format("\nESTATUS [%s] FOLIO [%s] FECHA NOTIFICACION EFECTIVA [%s]",
                    responseConsulta.getEstatus(), responseConsulta.getFolioActo(), responseConsulta.getFechaNotificacionEfectiva()));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Metodo para obtener los nombres de los distintos metodos de una clase
     * especifica
     *
     * @author eolf
     * @param obj
     * @return
     */
    private String methodGetName(Object obj) throws InstantiationException, IllegalAccessException,
            InvocationTargetException, NoSuchMethodException {
        StringBuilder sb = new StringBuilder();
        final String metodoPrueba = "getFolioActo";
        Class clase = obj.getClass();
        Method[] metodos = clase.getDeclaredMethods();
        for (Method metodo : metodos) {
            if (metodo.getName().compareTo(metodoPrueba) == 0) {
                Object folioActo = metodo.invoke(obj, null);
                if (folioActo instanceof String) {
                    LOG.debug(String.format("\nFOLIO [%s]", folioActo));
                }
            }

            sb.append("\n");
            sb.append(metodo.getName());
        }
        return sb.toString();
    }

    @Ignore
    @Test
    public void testnyVService() {
        LOG.debug("[testnyVService]");
        final BigDecimal idOrden = new BigDecimal("9999999999999999999999");
        try {
            AgaceOrden orden = obtenerOrden(idOrden);
            ResponseConsultaVO responseConsulta = nyvServiceClient.consultarActoAdministrativo(orden.getFolioNYV());
            LOG.debug(String.format("\nESTATUS [%s] FECHA NOTIFICACION [%s] FECHA NOTIFICACION EFECTIVA [%s] ",
                    responseConsulta.getEstatus(), responseConsulta.getFechaNotificacion(), responseConsulta.getFechaNotificacionEfectiva()));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

}
