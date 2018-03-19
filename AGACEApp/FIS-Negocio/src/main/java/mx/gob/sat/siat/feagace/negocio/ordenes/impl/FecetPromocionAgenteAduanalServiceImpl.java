/**
 * 
 */
package mx.gob.sat.siat.feagace.negocio.ordenes.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetPromocionAgenteAduanalDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionAgenteAduanal;
import mx.gob.sat.siat.feagace.negocio.ordenes.FecetPromocionAgenteAduanalService;
import mx.gob.sat.siat.feagace.negocio.rules.impl.ValidarMetodoOrdenAsociadoRule;

/**
 * @author sergio.vaca
 *
 */
@Service("fecetPromocionAgenteAduanalService")
public class FecetPromocionAgenteAduanalServiceImpl extends BaseBusinessServices implements FecetPromocionAgenteAduanalService {
    private static final long serialVersionUID = 1L;

    @Autowired
    private ValidarMetodoOrdenAsociadoRule validarMetodoOrdenAsociadoRule;

    @Autowired
    private transient FecetPromocionAgenteAduanalDao fecetPromocionAgenteAduanalDao;

    @Override
    public List<FecetPromocionAgenteAduanal> obtenerRegistrosByOrdenAsociado(AgaceOrden orden, ColaboradorVO colaborador) {
        List<FecetPromocionAgenteAduanal> registros = new ArrayList<FecetPromocionAgenteAduanal>();
        if (colaborador.getAsociado() != null) {
            List<String> leyendas = validarMetodoOrdenAsociadoRule.validaMetodoDespliegueAlegato(orden, null);
            String leyenda = leyendas.isEmpty() ? "" : leyendas.get(0);
            registros.addAll(fecetPromocionAgenteAduanalDao.obtenerRegistrosByOrdenAsociado(orden.getIdOrden(), colaborador.getAsociado().getIdAsociado()));
            for (FecetPromocionAgenteAduanal fecetPromocionAgenteAduanal : registros) {
                fecetPromocionAgenteAduanal.setTipoPromocion(leyenda);
            }
        }
        return registros;
    }

}
