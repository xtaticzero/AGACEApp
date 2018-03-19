package mx.gob.sat.siat.feagace.modelo.dao.propuestas;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FeceaPropuestaAccion;

public interface FeceaPropuestaAccionDao {

    void updateAccionIdPropuesta(BigDecimal idPropuesta, BigDecimal idAccion, BigDecimal idAccionOrigen);

    void insert(FeceaPropuestaAccion propuestaAccion);

    List<FeceaPropuestaAccion> getAccionByIdPropuesta(BigDecimal idPropuesta);

    List<FeceaPropuestaAccion> getAccionExistente(BigDecimal idPropuesta);
}
