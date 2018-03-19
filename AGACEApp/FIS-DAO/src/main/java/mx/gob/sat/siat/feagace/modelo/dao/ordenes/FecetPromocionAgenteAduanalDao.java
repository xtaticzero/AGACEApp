/**
 *
 */
package mx.gob.sat.siat.feagace.modelo.dao.ordenes;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionAgenteAduanal;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionAgenteAduanalPk;

/**
 * @author sergio.vaca
 *
 */
public interface FecetPromocionAgenteAduanalDao {

    List<FecetPromocionAgenteAduanal> obtenerRegistrosByOrdenAsociado(BigDecimal idOrden, BigDecimal idAsociado);

    BigDecimal getIdPromocionAgenteAduanal();

    FecetPromocionAgenteAduanalPk insert(FecetPromocionAgenteAduanal dto);

    FecetPromocionAgenteAduanal findByPrimaryKey(BigDecimal idPromocionAgenteAduanal);

    List<FecetPromocionAgenteAduanal> findAll();

    List<FecetPromocionAgenteAduanal> findWhereIdPromocionEquals(BigDecimal idPromocionAgenteAduanal);

    List<FecetPromocionAgenteAduanal> findWhereIdOrdenEquals(BigDecimal idOrden);

    List<FecetPromocionAgenteAduanal> findWhereFechaCargaEquals(Date fechaCarga);

    List<FecetPromocionAgenteAduanal> getPromocionAgenteAduanalContadorPruebasAlegatos(BigDecimal idOrden);

    BigDecimal getIdFecetPromocionAgenteAduanalPathDirectorio();

    FecetPromocionAgenteAduanalPk insertarRegistroGuardarArchivoPromocionAgenteAduanal(FecetPromocionAgenteAduanal dto);

    void update(FecetPromocionAgenteAduanalPk pk, FecetPromocionAgenteAduanal dto);

    FecetPromocionAgenteAduanal findByPrimaryKey(FecetPromocionAgenteAduanalPk pk);

}
