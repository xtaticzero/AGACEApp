/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.ordenes.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetAlegatoAgenteAduanalDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetAlegatoAgenteAduanalMapper;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegatoAgenteAduanal;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegatoAgenteAduanalPk;

@Repository("fecetAlegatoAgenteAduanalDao")
public class FecetAlegatoAgenteAduanalDaoImpl extends BaseJDBCDao<FecetAlegatoAgenteAduanal> implements FecetAlegatoAgenteAduanalDao {

    private static final long serialVersionUID = 1L;

    private static final StringBuilder SQL_SELECT
            = new StringBuilder("SELECT ID_ALEGATO_AGENTE_ADUANAL, ID_PROMOCION_AGENTE_ADUANAL, FECHA_CARGA, RUTA_ARCHIVO, BLN_ACTIVO, FECHA_FIN FROM ").append(getTableName());

    /**
     * Este atributo es una condicion WHERE para seleccionar los datos de
     * acuerdo a un ID_ALEGATO
     */
    private static final StringBuilder SQL_WHERE = new StringBuilder(" WHERE ID_ALEGATO_AGENTE_ADUANAL = ? ");

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetAlegatoPk
     */
    public FecetAlegatoAgenteAduanalPk insert(FecetAlegatoAgenteAduanal dto) {
        if (dto.getIdAlegatoAgenteAduanal() == null) {
            dto.setIdAlegatoAgenteAduanal(getJdbcTemplateBase().queryForObject("SELECT FECEQ_ALEGATO_AGENTE_ADUANAL.NEXTVAL FROM DUAL",
                    BigDecimal.class));
        }
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(getTableName());
        query.append(" ( ID_ALEGATO_AGENTE_ADUANAL, ID_PROMOCION_AGENTE_ADUANAL, FECHA_CARGA, RUTA_ARCHIVO, BLN_ACTIVO, FECHA_FIN ) VALUES ( ?, ?, ?, ?, ?, ? )");
        getJdbcTemplateBase().update(query.toString(), dto.getIdAlegatoAgenteAduanal(), dto.getIdPromocionAgeneteAduanal(), dto.getFechaCarga(),
                dto.getRutaArchivo(), dto.getBlnActivo(), null);
        return dto.createPk();

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_ALEGATO_AGENTE_ADUANAL";
    }

    /**
     * Returns all rows from the FECET_ALEGATO table that match the criteria
     * 'ID_ALEGATO_AGENTE_ADUANAL = :idAlegato'.
     */
    public FecetAlegatoAgenteAduanal findByPrimaryKey(BigDecimal idAlegatoAgenteAduanal) {
        List<FecetAlegatoAgenteAduanal> list
                = getJdbcTemplateBase().query(SQL_SELECT.append(SQL_WHERE).toString(), new FecetAlegatoAgenteAduanalMapper(), idAlegatoAgenteAduanal);
        return list.isEmpty() ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECET_ALEGATO table that match the criteria ''.
     */
    public List<FecetAlegatoAgenteAduanal> findAll() {
        return getJdbcTemplateBase().query(SQL_SELECT.append(" ORDER BY ID_ALEGATO_AGENTE_ADUANAL").toString(),
                new FecetAlegatoAgenteAduanalMapper());

    }

    /**
     * Returns all rows from the FECET_ALEGATO table that match the criteria
     * 'ID_ALEGATO_AGENTE_ADUANAL = :idAlegato'.
     */
    public List<FecetAlegatoAgenteAduanal> findWhereIdAlegatoEquals(BigDecimal idAlegatoAgenteAduanal) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" WHERE ID_ALEGATO_AGENTE_ADUANAL = ? ORDER BY ID_ALEGATO_AGENTE_ADUANAL").toString(),
                new FecetAlegatoAgenteAduanalMapper(), idAlegatoAgenteAduanal);

    }

    /**
     * Returns all rows from the FECET_ALEGATO table that match the criteria
     * 'ID_PROMOCION_AGENTE_ADUANAL = :idPromocion'.
     */
    public List<FecetAlegatoAgenteAduanal> findWhereIdPromocionEquals(BigDecimal idPromocionAgenteAduanal) {
        StringBuilder sql = new StringBuilder();

        sql.append(SQL_SELECT);
        sql.append(" WHERE ID_PROMOCION_AGENTE_ADUANAL = ? ORDER BY ID_PROMOCION_AGENTE_ADUANAL");
        logger.debug("get Pruebas y alegatos {} " + sql);
        return getJdbcTemplateBase().query(sql.toString(), new FecetAlegatoAgenteAduanalMapper(), idPromocionAgenteAduanal);

    }

    /**
     * Returns all rows from the FECET_ALEGATO table that match the criteria
     * 'FECHA_CARGA = :fechaCarga'.
     */
    public List<FecetAlegatoAgenteAduanal> findWhereFechaCargaEquals(Date fechaCarga) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" WHERE FECHA_CARGA = ? ORDER BY FECHA_CARGA").toString(),
                new FecetAlegatoAgenteAduanalMapper(), fechaCarga);

    }

    /**
     * Returns the rows from the FECET_ALEGATO table that matches the specified
     * primary-key value.
     */
    public FecetAlegatoAgenteAduanal findByPrimaryKey(FecetAlegatoAgenteAduanalPk pk) {
        return findByPrimaryKey(pk.getIdAlegatoAgenteAduanal());

    }

    @Override
    public List<FecetAlegatoAgenteAduanal> obtenerAlegatosAgenteByIdPromocion(BigDecimal idPromocion) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT ID_ALEGATO_AGENTE_ADUANAL, ID_PROMOCION_AGENTE_ADUANAL, FECHA_CARGA, ");
        query.append(" RUTA_ARCHIVO, BLN_ACTIVO, FECHA_FIN ");
        query.append("\n FROM FECET_ALEGATO_AGENTE_ADUANAL ");
        query.append("\n WHERE ID_PROMOCION_AGENTE_ADUANAL = ? ");
        query.append("\n AND BLN_ACTIVO = 1 ");
        return getJdbcTemplateBase().query(query.toString(), new FecetAlegatoAgenteAduanalMapper(), idPromocion);
    }
}
