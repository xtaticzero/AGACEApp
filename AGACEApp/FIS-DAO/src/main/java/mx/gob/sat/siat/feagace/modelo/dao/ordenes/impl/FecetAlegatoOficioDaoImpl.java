/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.ordenes.impl;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetAlegatoOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetAlegatoOficioMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAlegatoOficio;

import org.springframework.stereotype.Repository;

@Repository("fecetAlegatoOficioDao")
public class FecetAlegatoOficioDaoImpl extends BaseJDBCDao<FecetAlegatoOficio> implements FecetAlegatoOficioDao {

    private static final long serialVersionUID = 7500506646505525381L;
    /**
     * Este atributo es un SELECT para seleccionar los datos de las columnas de
     * la tabla FECET_ALEGATO_OFICIO
     */
    private static final StringBuilder SQL_SELECT
            = new StringBuilder("SELECT ID_ALEGATO_OFICIO, ID_PROMOCION_OFICIO, FECHA_CARGA, RUTA_ARCHIVO FROM ").append(getTableName());

    /**
     * Este atributo es una condicion WHERE para seleccionar los datos de
     * acuerdo a un ID_ALEGATO_OFICIO
     */
    private static final StringBuilder SQL_WHERE = new StringBuilder(" WHERE ID_ALEGATO_OFICIO = ? ");

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetAlegatoOficioPk
     */
    @Override
    public BigDecimal insert(FecetAlegatoOficio dto) {

        if (dto.getIdAlegatoOficio() == null) {
            dto.setIdAlegatoOficio(getJdbcTemplateBase().queryForObject("SELECT FECEQ_ALEGATO_OFICIO.NEXTVAL FROM DUAL",
                    BigDecimal.class));
        }
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(getTableName());
        query.append(" ( ID_ALEGATO_OFICIO, ID_PROMOCION_OFICIO, FECHA_CARGA, RUTA_ARCHIVO ) VALUES ( ?, ?, ?, ? )");
        getJdbcTemplateBase().update(query.toString(), dto.getIdAlegatoOficio(), dto.getIdPromocionOficio(), dto.getFechaCarga(),
                dto.getRutaArchivo());
        return dto.getIdAlegatoOficio();
    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_ALEGATO_OFICIO";
    }

    /**
     * Returns all rows from the FECET_ALEGATO_OFICIO table that match the
     * criteria 'ID_ALEGATO_OFICIO = :idAlegato'.
     */
    @Override
    public FecetAlegatoOficio findByPrimaryKey(BigDecimal idAlegatoOficio) {
        List<FecetAlegatoOficio> list
                = getJdbcTemplateBase().query(SQL_SELECT.append(SQL_WHERE).toString(), new FecetAlegatoOficioMapper(),
                        idAlegatoOficio);
        return list.isEmpty() ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECET_ALEGATO_OFICIO table that match the
     * criteria ''.
     */
    @Override
    public List<FecetAlegatoOficio> findAll() {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" ORDER BY ID_ALEGATO_OFICIO").toString(),
                new FecetAlegatoOficioMapper());
    }

    @Override
    public List<FecetAlegatoOficio> findWhereIdPromocionEquals(BigDecimal idPromocionOficio) {
        StringBuilder sql = new StringBuilder();

        sql.append(SQL_SELECT);
        sql.append(" WHERE ID_PROMOCION_OFICIO = ? ORDER BY ID_PROMOCION_OFICIO");

        return getJdbcTemplateBase().query(sql.toString(), new FecetAlegatoOficioMapper(), idPromocionOficio);

    }
}
