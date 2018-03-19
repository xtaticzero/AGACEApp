package mx.gob.sat.siat.feagace.modelo.dao.catalogos.roles;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececRepLegal;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececRepLegalPk;

public interface FececRepLegalDao {

    /**
     * Metodo 'insert'
     *
     * @param dto
     * @return FececRepLegalPk
     * @
     */
    FececRepLegalPk insert(final FececRepLegal dto);

    /**
     * Metodo 'update'
     *
     * @param pk
     * @param dto
     * @
     */
    void update(final FececRepLegalPk pk, final FececRepLegal dto);

    /**
     * Regresa una lista con todos los datos de la tabla FECEC_REP_LEGAL con el
     * criterio de ID_REP_LEGAL = :idRepLegal
     */
    FececRepLegal findByPrimaryKey(final BigDecimal idRepLegal);

    /**
     * Regresa una lista con todos los datos de la tabla FECEC_REP_LEGAL
     */
    List<FececRepLegal> findAll();

    /**
     * Regresa una lista con los datos de la tabla FECEC_REP_LEGAL bajo el
     * criterio de ID_REP_LEGAL = idrepLegal
     */
    List<FececRepLegal> findWhereIdRepLegalEquals(final BigDecimal idRepLegal);

    /**
     * Regresa una lista con los datos de la tabla FECEC_REP_LEGAL bajo el
     * criterio de RFC = rfc
     */
    List<FececRepLegal> findWhereRfcEquals(final String rfc);

    /**
     * Regresa una lista con los datos de la tabla FECEC_REP_LEGAL bajo el
     * criterio NOMBRE = nombre
     */
    List<FececRepLegal> findWhereNombreEquals(final String nombre);

    /**
     * Metodo 'findByPrimaryKey'
     *
     * @param pk
     * @return FececRepLegal
     * @
     */
    FececRepLegal findByPrimaryKey(final FececRepLegalPk pk);
}
