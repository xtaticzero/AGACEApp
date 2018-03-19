package mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececAgteAduanal;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececAgteAduanalPk;

public interface FececAgteAduanalDao {

    /**
     * Metodo 'insert'
     *
     * @param dto
     * @return FececAgteAduanalPk
     * @
     */
    FececAgteAduanalPk insert(final FececAgteAduanal dto);

    /**
     * Metodo 'update'
     *
     * @param pk
     * @param dto
     * @
     */
    void update(final FececAgteAduanalPk pk, final FececAgteAduanal dto);

    /**
     * Metodo 'findByPrimaryKey'
     *
     * @param idAgteAduanal
     * @return
     * @
     */
    FececAgteAduanal findByPrimaryKey(final BigDecimal idAgteAduanal);

    /**
     * Metodo 'findAll'
     *
     * @return List
     * @
     */
    List<FececAgteAduanal> findAll();

    /**
     * Metodo 'findWhereIdAgteAduanal'
     *
     * @param idAgteAduanal
     * @return List
     * @
     */
    List<FececAgteAduanal> findWhereIdAgteAduanalEquals(final BigDecimal idAgteAduanal);

    /**
     * Metodo 'findWhereRfcEquals'
     *
     * @param rfc
     * @return List
     * @
     */
    List<FececAgteAduanal> findWhereRfcEquals(final String rfc);

    /**
     * Metodo 'findWhereNombreEquals'
     *
     * @param nombre
     * @return List
     * @
     */
    List<FececAgteAduanal> findWhereNombreEquals(final String nombre);

    /**
     * Metodo findByPrimaryKey
     *
     * @param pk
     * @return FececRepLegal
     * @
     */
    FececAgteAduanal findByPrimaryKey(final FececAgteAduanalPk pk);
}
