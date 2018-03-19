package mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOficio;

public interface FecetDocProrrogaOficioDao {

    BigDecimal insert(FecetDocProrrogaOficio dto);

    void update(FecetDocProrrogaOficio dto);

    BigDecimal getIdFecetDocProrrogaOficioPathDirectorio();

    List<FecetDocProrrogaOficio> findAll();

    List<FecetDocProrrogaOficio> findWhereIdProrrogaEquals(BigDecimal idProrroga);

    List<FecetDocProrrogaOficio> findWhereIdDocProrrogaOficioEquals(final BigDecimal idDocProrrogaOficio);

    List<FecetDocProrrogaOficio> findWhereIdProrrogaOficioEquals(final BigDecimal idProrrogaOficio);

    List<FecetDocProrrogaOficio> findWhereNombreAcuseEquals(final String nombreArchivo);

    List<FecetDocProrrogaOficio> findWhereRutaAcuseEquals(final String rutaArchivo);

    FecetDocProrrogaOficio findByPrimaryKey(final FecetDocProrrogaOficio dto);

}
