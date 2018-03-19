/**
 *
 */
package mx.gob.sat.siat.feagace.negocio.insumos.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.FecetSuspensionInsumoDao;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetSuspensionInsumo;
import mx.gob.sat.siat.feagace.negocio.insumos.FecetSuspensionInsumoService;

/**
 * @author sergio.vaca
 *
 */
@Service("fecetSuspensionInsumoService")
public class FecetSuspensionInsumoServiceImpl extends BaseBusinessServices implements FecetSuspensionInsumoService {

    private static final long serialVersionUID = 1L;

    @Autowired
    private transient FecetSuspensionInsumoDao fecetSuspensionInsumoDao;

    @Override
    @PistaAuditoria
    public String insertReasignar(FecetInsumo insumo) {
        funcionActualizar(insumo);
        return insumo.getIdRegistro();
    }

    @Override
    @PistaAuditoria
    public String insertRetroalimentar(FecetInsumo insumo) {
        funcionActualizar(insumo);
        return insumo.getIdRegistro();
    }

    private void funcionActualizar(FecetInsumo insumo) {
        FecetSuspensionInsumo dto = new FecetSuspensionInsumo();
        dto.setIdInsumo(insumo.getIdInsumo());
        dto.setFechaCreacion(new Date());
        dto.setFechaInicioSuspension(new Date());
        if (obtenerUltimaSuspension(dto.getIdInsumo()) == null) {
            FecetSuspensionInsumo posibleSuspension = fecetSuspensionInsumoDao.obtenerSuspensionByIdAndFechaInicio(dto.getIdInsumo(),
                    dto.getFechaInicioSuspension());
            if (posibleSuspension != null) {
                fecetSuspensionInsumoDao.actualizarFechaFinSuspensionInsumo(null, posibleSuspension.getIdSuspensionInsumo());
            } else {
                fecetSuspensionInsumoDao.insert(dto);
            }
        }
    }

    @Override
    public void actualizarFechaFinSuspensionInsumo(Date fechaFinSuspension, BigDecimal idSuspensionInsumo) {
        fecetSuspensionInsumoDao.actualizarFechaFinSuspensionInsumo(fechaFinSuspension, idSuspensionInsumo);
    }

    @Override
    public List<FecetSuspensionInsumo> obtenerSuspensionByIdInsumo(BigDecimal idInsumo) {
        return fecetSuspensionInsumoDao.obtenerSuspensionByIdInsumo(idInsumo);
    }

    @Override
    public FecetSuspensionInsumo obtenerUltimaSuspension(BigDecimal idInsumo) {
        return fecetSuspensionInsumoDao.obtenerUltimaSuspension(idInsumo);
    }

}
