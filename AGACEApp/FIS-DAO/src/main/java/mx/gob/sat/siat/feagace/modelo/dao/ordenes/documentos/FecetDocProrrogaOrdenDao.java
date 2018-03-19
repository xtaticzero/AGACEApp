package mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrdenPk;

public interface FecetDocProrrogaOrdenDao {

    FecetDocProrrogaOrdenPk insert(FecetDocProrrogaOrden dto);

    void update(FecetDocProrrogaOrdenPk pk, FecetDocProrrogaOrden dto);

    void delete(FecetDocProrrogaOrdenPk pk);

    BigDecimal getIdFecetDocProrrogaOrdenPathDirectorio();

    FecetDocProrrogaOrden findByPrimaryKey(BigDecimal idProrroga);

    List<FecetDocProrrogaOrden> findAll();

    List<FecetDocProrrogaOrden> findWhereIdProrrogaEquals(BigDecimal idProrroga);

    List<FecetDocProrrogaOrden> findWhereIdDocProrrogaOrdenEquals(final BigDecimal idDocProrrogaOrden);

    List<FecetDocProrrogaOrden> findWhereIdProrrogaOrdenEquals(final BigDecimal idProrrogaOrden);

    FecetDocProrrogaOrden findByPrimaryKey(final FecetDocProrrogaOrdenPk pk);

}
