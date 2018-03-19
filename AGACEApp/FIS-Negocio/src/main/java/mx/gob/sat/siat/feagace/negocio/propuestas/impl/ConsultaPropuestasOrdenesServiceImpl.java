package mx.gob.sat.siat.feagace.negocio.propuestas.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.AgaceOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.plazos.service.PlazosServiceOrdenes;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.propuestas.ConsultaPropuestasOrdenesService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.BusinessUtil;

@Service("consultaPropuestasOrdenesService")
public class ConsultaPropuestasOrdenesServiceImpl extends BaseBusinessServices implements ConsultaPropuestasOrdenesService {

    /**
     *
     */
    private static final long serialVersionUID = 4645895653817915632L;
    @Autowired
    private transient AgaceOrdenDao agaceOrdenDao;
    @Autowired
    private transient PlazosServiceOrdenes plazosService;
    @Autowired
    private transient FecetPropuestaDao fecetPropuestaDao;

    @Override
    public List<AgaceOrden> consultarPropuestasOrdenes(final BigDecimal idEmpleado) throws NegocioException {
        List<AgaceOrden> listaOrdenes = this.agaceOrdenDao.getPropuestasOrdenesPorAsociado(idEmpleado);
        List<AgaceOrden> lstPropuestas = new ArrayList<AgaceOrden>();

        for (AgaceOrden orden : listaOrdenes) {
            if (orden.getFecetDetalleNyV().getIdNyV() == Constantes.ENTERO_CERO) {
                lstPropuestas.add(orden);
            }
        }

        listaOrdenes.removeAll(lstPropuestas);
        listaOrdenes = plazosService.filtraOrdenPorFecha(listaOrdenes);
        listaOrdenes.addAll(lstPropuestas);

        for (AgaceOrden orden : listaOrdenes) {
            if (orden.getPrioridadSugerida() == null) {
                FecetPropuesta propuesta = fecetPropuestaDao.findWhereIdPropuestaEquals(orden.getIdPropuesta()).get(0);
                orden.setPrioridadSugerida(propuesta.getPrioridadSugerida());
            }
            if (orden.getFirmaElectronica() != null) {
                orden.setImagenSemaforo(BusinessUtil.obtenerImagenSemaforo(orden.getSemaforo()));
            } else {
                orden.setImagenSemaforo("");
            }
        }

        return listaOrdenes;
    }

}
