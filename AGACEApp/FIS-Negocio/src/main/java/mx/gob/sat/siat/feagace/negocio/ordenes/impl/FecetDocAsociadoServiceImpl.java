/**
 * 
 */
package mx.gob.sat.siat.feagace.negocio.ordenes.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos.FecetDocAsociadoDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocAsociado;
import mx.gob.sat.siat.feagace.negocio.ordenes.FecetDocAsociadoService;

/**
 * @author sergio.vaca
 *
 */
@Service("fecetDocAsociadoService")
public class FecetDocAsociadoServiceImpl extends BaseBusinessServices implements FecetDocAsociadoService {

    private static final long serialVersionUID = 1L;

    @Autowired
    private transient FecetDocAsociadoDao fecetDocAsociadoDao;

    @Override
    public List<FecetDocAsociado> obtenerDocumentosPorAsociado(BigDecimal idAsociado) {
        return fecetDocAsociadoDao.obtenerDocumentosPorAsociado(idAsociado);
    }

    @Override
    public void insertarDocAsociado(FecetDocAsociado documento) {
        fecetDocAsociadoDao.insertar(documento);
    }

}
