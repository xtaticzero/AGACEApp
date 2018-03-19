package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatus;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatusPk;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;

public interface FececEstatusDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececEstatusPk
     */
    FececEstatusPk insert(FececEstatus dto);

    /**
     * Updates a single row in the FECEC_ESTATUS table.
     */
    void update(FececEstatusPk pk, FececEstatus dto);

    /**
     * Deletes a single row in the FECEC_ESTATUS table.
     */
    void delete(FececEstatusPk pk);

    /**
     * Returns all rows from the FECEC_ESTATUS table that match the criteria
     * 'ID_ESTATUS = :idEstatus'.
     */
    FececEstatus findByPrimaryKey(BigDecimal idEstatus);

    /**
     * Returns all rows from the FECEC_ESTATUS table that match the criteria ''.
     */
    List<FececEstatus> findAll();

    /**
     * Returns all rows from the FECEC_ESTATUS table that match the criteria
     * 'ID_ESTATUS = :idEstatus'.
     */
    List<FececEstatus> findWhereIdEstatusEquals(BigDecimal idEstatus);

    /**
     * Returns all rows from the FECEC_ESTATUS table that match the criteria
     * 'DESCRIPCION = :descripcion'.
     */
    List<FececEstatus> findWhereDescripcionEquals(String descripcion);

    /**
     * Returns all rows from the FECEC_ESTATUS table that match the criteria
     * 'MODULO = :modulo'.
     */
    List<FececEstatus> findWhereModuloEquals(String modulo);

    /**
     * Returns all rows from the FECEC_ESTATUS table that match the criteria
     * 'ID_MODULO = :moduloId'.
     */
    List<FececEstatus> findWhereModuloId(Integer moduloId);

    /**
     * Returns the rows from the FECEC_ESTATUS table that matches the specified
     * primary-key value.
     */
    FececEstatus findByPrimaryKey(FececEstatusPk pk);

    /**
     * Returns the rows from the FECEC_ESTATUS table that matches the specified
     * ID_ESTATUS 64 -67.
     */
    List<FececEstatus> findOnlyRechazosPropuestas();

    List<FececEstatus> getEstatusByTipoEstatus(TipoEstatusEnum... enums);
}
