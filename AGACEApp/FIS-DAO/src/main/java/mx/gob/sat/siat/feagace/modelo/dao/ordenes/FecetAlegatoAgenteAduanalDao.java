/**
 *
 */
package mx.gob.sat.siat.feagace.modelo.dao.ordenes;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegatoAgenteAduanalPk;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegatoAgenteAduanal;

/**
 * @author sergio.vaca
 *
 */
public interface FecetAlegatoAgenteAduanalDao {

    List<FecetAlegatoAgenteAduanal> obtenerAlegatosAgenteByIdPromocion(BigDecimal idPromocionAgente);

    FecetAlegatoAgenteAduanalPk insert(FecetAlegatoAgenteAduanal dto);

    FecetAlegatoAgenteAduanal findByPrimaryKey(BigDecimal idAlegatoAgenteAduanal);

    List<FecetAlegatoAgenteAduanal> findAll();

    List<FecetAlegatoAgenteAduanal> findWhereIdAlegatoEquals(BigDecimal idAlegatoAgenteAduanal);

    List<FecetAlegatoAgenteAduanal> findWhereIdPromocionEquals(BigDecimal idPromocionAgenteAduanal);

    List<FecetAlegatoAgenteAduanal> findWhereFechaCargaEquals(Date fechaCarga);

    FecetAlegatoAgenteAduanal findByPrimaryKey(FecetAlegatoAgenteAduanalPk pk);
}
