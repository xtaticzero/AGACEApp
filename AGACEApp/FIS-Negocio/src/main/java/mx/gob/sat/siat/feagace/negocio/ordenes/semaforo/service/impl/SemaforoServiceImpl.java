package mx.gob.sat.siat.feagace.negocio.ordenes.semaforo.service.impl;

import java.util.Collections;
import java.util.List;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.AgaceOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.negocio.ordenes.semaforo.helper.SemaforoHelper;
import mx.gob.sat.siat.feagace.negocio.ordenes.semaforo.service.SemaforoService;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("semaforoService")
public class SemaforoServiceImpl extends BaseBusinessServices implements SemaforoService {

    @SuppressWarnings("compatibility:8062775542959572277")
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private SemaforoHelper semaforoHelper;

    @Autowired
    private transient AgaceOrdenDao agaceOrdenDao;

    private static final Logger LOG = Logger.getLogger(SemaforoServiceImpl.class.getName());

    private static final String ESPACIO = "\n";
    
    public SemaforoServiceImpl() {
        super();
    }

    @Override
    public void semaforoPlazos() {
        LOG.debug("[semaforoPlazos]");
        final int paginador = 5;
        final int iteracionInicial = 1;
        List<Integer> idsOrdenesQuitar = Collections.emptyList();
        List<AgaceOrden> listOrdenes = Collections.emptyList();
        int limiteInicial = 0;
        int limiteFinal = 0;
        int iteraciones = 0;
        int particion = 0;
        int restante = 0;
        long cantidadMaximaOrdenesNotificadas = agaceOrdenDao.getCantidadMaximaOrdenesNotificadas();
        if (cantidadMaximaOrdenesNotificadas > paginador) {
            particion = (int)cantidadMaximaOrdenesNotificadas / paginador;
            restante = (int)cantidadMaximaOrdenesNotificadas - (paginador * particion);

        } else {
            iteraciones = iteracionInicial;
            limiteFinal = (int)cantidadMaximaOrdenesNotificadas;
            listOrdenes = agaceOrdenDao.getOrdenesNotificadasPaginado(limiteInicial, limiteFinal, idsOrdenesQuitar);
            semaforoHelper.validarLista(listOrdenes, "ordenes");
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug(ESPACIO +
                      String.format("CANTIDAD MAXIMA ORDENES NOTIFICADAS [%s] ORDENES [%s] PARTICION [%s] RESTANTE [%s] ITERACIONES [%s]",
                                    cantidadMaximaOrdenesNotificadas, listOrdenes, particion, restante, iteraciones));
        }
        //TODO: 2016/01/15 Se pausa continuacion en desarrollo de metodo
        //1. Traer todas las ordenes, paginadas con fecha_surte_efectos != null
        //2. Obtener el plazo del semaforo por orden (generar un map para almacenar por idmetodo la lista de dichos plazos porque como estara en un ciclo iria n veces a la bd)
        //3.
    }
}
