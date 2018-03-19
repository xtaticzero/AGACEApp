package mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuestaPk;

public interface FececTipoPropuestaDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececTipoPropuestaPk
     */
    FececTipoPropuestaPk insert(FececTipoPropuesta dto);

    /**
     * Updates a single row in the FECEC_TIPO_PROPUESTA table.
     */
    void update(FececTipoPropuestaPk pk, FececTipoPropuesta dto);

    /**
     * Deletes a single row in the FECEC_TIPO_PROPUESTA table.
     */
    void delete(FececTipoPropuestaPk pk);

    /**
     * Returns all rows from the FECEC_TIPO_PROPUESTA table that match the
     * criteria 'ID_TIPO_PROPUESTA = :idTipoPropuesta'.
     */
    FececTipoPropuesta findByPrimaryKey(BigDecimal idTipoPropuesta);

    /**
     * Returns all rows from the FECEC_TIPO_PROPUESTA table that match the
     * criteria ''.
     */
    List<FececTipoPropuesta> findAll();

    /**
     * Returns all rows from the FECEC_TIPO_PROPUESTA table that match the
     * criteria 'ID_TIPO_PROPUESTA = :idTipoPropuesta'.
     */
    List<FececTipoPropuesta> findWhereIdTipoPropuestaEquals(BigDecimal idTipoPropuesta);

    /**
     * Returns all rows from the FECEC_TIPO_PROPUESTA table that match the
     * criteria 'DESCRIPCION = :descripcion'.
     */
    List<FececTipoPropuesta> findWhereDescripcionEquals(String descripcion);

    /**
     * Returns the rows from the FECEC_TIPO_PROPUESTA table that matches the
     * specified primary-key value.
     */
    FececTipoPropuesta findByPrimaryKey(FececTipoPropuestaPk pk);

}
