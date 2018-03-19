package mx.gob.sat.siat.feagace.modelo.dao.catalogos.insumos;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuestoPk;

public interface FececTipoImpuestoDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececTipoImpuestoPk
     */
    FececTipoImpuestoPk insert(FececTipoImpuesto dto);

    /**
     * Updates a single row in the FECEC_TIPO_IMPUESTO table.
     */
    void update(FececTipoImpuestoPk pk, FececTipoImpuesto dto);

    /**
     * Deletes a single row in the FECEC_TIPO_IMPUESTO table.
     */
    void delete(FececTipoImpuestoPk pk);

    /**
     * Returns all rows from the FECEC_TIPO_IMPUESTO table that match the
     * criteria 'ID_TIPO_IMPUESTO = :idTipoImpuesto'.
     */
    FececTipoImpuesto findByPrimaryKey(BigDecimal idTipoImpuesto);

    /**
     * Returns all rows from the FECEC_TIPO_IMPUESTO table that match the
     * criteria ''.
     */
    List<FececTipoImpuesto> findAll();

    /**
     * Returns all rows from the FECEC_TIPO_IMPUESTO table that match the
     * criteria 'ID_TIPO_IMPUESTO = :idTipoImpuesto'.
     */
    List<FececTipoImpuesto> findWhereIdTipoImpuestoEquals(BigDecimal idTipoImpuesto);

    /**
     * Returns all rows from the FECEC_TIPO_IMPUESTO table that match the
     * criteria 'DESCRIPCION = :descripcion'.
     */
    List<FececTipoImpuesto> findWhereDescripcionEquals(String descripcion);

    /**
     * Returns all rows from the FECEC_TIPO_IMPUESTO table that match the
     * criteria 'ABREVIATURA = :abreviatura'.
     */
    List<FececTipoImpuesto> findWhereAbreviaturaEquals(String abreviatura);

    /**
     * Returns the rows from the FECEC_TIPO_IMPUESTO table that matches the
     * specified primary-key value.
     */
    FececTipoImpuesto findByPrimaryKey(FececTipoImpuestoPk pk);

}
