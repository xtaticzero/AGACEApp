package mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.documentos.dao;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrdenPk;

public interface DocumentoProrrogaDAO {

    FecetAnexosProrrogaOrden insert(FecetAnexosProrrogaOrden dto);

    void update(FecetDocProrrogaOrdenPk pk, FecetDocProrrogaOrden dto);

    void delete(FecetDocProrrogaOrdenPk pk);

    BigDecimal getIdFecetDocProrrogaOrdenPathDirectorio();

    FecetDocProrrogaOrden findByPrimaryKey(BigDecimal idProrroga);

    List<FecetDocProrrogaOrden> findAll();

    List<FecetDocProrrogaOrden> findWhereIdProrrogaEquals(BigDecimal idProrroga);

    List<FecetDocProrrogaOrden> findWhereIdDocProrrogaOrdenEquals(final BigDecimal idDocProrrogaOrden);

    List<FecetDocProrrogaOrden> findWhereIdProrrogaOrdenEquals(final BigDecimal idProrrogaOrden);

    List<FecetDocProrrogaOrden> findWhereNombreAcuseEquals(final String nombreArchivo);

    List<FecetDocProrrogaOrden> findWhereRutaAcuseEquals(final String rutaArchivo);

    FecetDocProrrogaOrden findByPrimaryKey(final FecetDocProrrogaOrdenPk pk);

}
