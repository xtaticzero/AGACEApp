package mx.gob.sat.siat.feagace.modelo.dao.propuestas;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetCancelacion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocCancelacion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropCancelada;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropCanceladaPk;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;

public interface FecetCancelacionDao {

    FecetPropCanceladaPk insert(FecetPropCancelada dto);

    FecetPropCanceladaPk insertDocCancelacion(FecetPropCancelada dto);

    void updateEstatusCancelacion(BigDecimal idPopuesta);

    void updateEstatusDocCancelacion(BigDecimal idCancelacion);

    List<FecetCancelacion> obtenerJustificacionCancelacion(BigDecimal idPropuesta, TipoEstatusEnum idEstatus,
            BigDecimal estatusCancelacion);

    List<FecetDocCancelacion> obtenerDoctosCancelacionById(BigDecimal idCancelacion, BigDecimal estatus);

    List<FecetDocCancelacion> obtenerDoctosAllCancelacionById(BigDecimal idCancelacion);

    BigDecimal getConsecutivoDocCancelacion();
}
