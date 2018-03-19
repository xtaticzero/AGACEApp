package mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececMotivo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececMotivoPk;

public interface FececMotivoDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececMotivoPk
     */
    FececMotivoPk insert(FececMotivo dto);

    /**
     * Updates a single row in the FECEC_MOTIVO table.
     */
    void update(FececMotivoPk pk, FececMotivo dto);

    /**
     * Deletes a single row in the FECEC_MOTIVO table.
     */
    void delete(FececMotivoPk pk);

    /**
     * Returns all rows from the FECEC_MOTIVO table that match the criteria
     * 'ID_MOTIVO = :idMotivo'.
     */
    FececMotivo findByPrimaryKey(BigDecimal idMotivo);

    /**
     * Returns all rows from the FECEC_MOTIVO table that match the criteria ''.
     */
    List<FececMotivo> findAll();

    /**
     * Returns all rows from the FECEC_MOTIVO table that match the criteria
     * 'ID_MOTIVO = :idMotivo'.
     */
    List<FececMotivo> findWhereIdMotivoEquals(BigDecimal idMotivo);

    /**
     * Returns all rows from the FECEC_MOTIVO table that match the criteria
     * 'DESCRIPCION = :descripcion'.
     */
    List<FececMotivo> findWhereDescripcionEquals(String descripcion);

    /**
     * Returns all rows from the FECEC_MOTIVO table that match the criteria
     * 'TIPO_MOTIVO = :tipoMotivo'.
     */
    List<FececMotivo> findWhereTipoMotivoEquals(String tipoMotivo);

    /**
     * Returns the rows from the FECEC_MOTIVO table that matches the specified
     * primary-key value.
     */
    FececMotivo findByPrimaryKey(FececMotivoPk pk);

}
