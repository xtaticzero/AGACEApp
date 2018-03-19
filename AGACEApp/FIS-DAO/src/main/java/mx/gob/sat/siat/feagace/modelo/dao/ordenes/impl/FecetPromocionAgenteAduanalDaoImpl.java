/**
 *
 */
package mx.gob.sat.siat.feagace.modelo.dao.ordenes.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetPromocionAgenteAduanalDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetPromocionAgenteAduanalContadorAlegatosMapper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetPromocionAgenteAduanalMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionAgenteAduanal;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionAgenteAduanalPk;

/**
 * @author sergio.vaca
 *
 */
@Repository("fecetPromocionAgenteAduanalDao")
public class FecetPromocionAgenteAduanalDaoImpl extends BaseJDBCDao<FecetPromocionAgenteAduanal> implements FecetPromocionAgenteAduanalDao {

    private static final long serialVersionUID = 1L;

    /**
     * Este atributo corresponde una funcion DUAL para un Query de la tabla
     * FECET_PROMOCION_AGENTE_ADUANAL
     */
    private static final StringBuilder SQL_DUAL = new StringBuilder("SELECT FECEQ_PROMOCION_AGENTE_ADUANAL.NEXTVAL FROM DUAL");

    /**
     * Este atributo corresponde a una funcion INSERT INTO para insertar datos a
     * la tabla FECET_PROMOCION_AGENTE_ADUANAL
     */
    private static final StringBuilder SQL_INSERT = new StringBuilder("INSERT INTO ");

    /**
     * Este atributo corresponde a una condicion WHERE para obtener datos de la
     * tabla FECET_PORMOCION por un ID_PROMOCION_AGENTE_ADUANAL
     */
    private static final StringBuilder SQL_WHERE = new StringBuilder(" WHERE ID_PROMOCION_AGENTE_ADUANAL = ? ");

    /**
     * Este atributo es una condicion WHERE para obtener datos de la tabla
     * FECET_PROMOCION_AGENTE_ADUANAL por una ID_ORDEN
     */
    private static final StringBuilder SQL_WHERE_ORDER = new StringBuilder(" WHERE FECET_PROMOCION_AGENTE_ADUANAL.ID_ORDEN = ? ORDER BY FECET_PROMOCION_AGENTE_ADUANAL.ID_ORDEN ");

    private static final StringBuilder SQL_WHERE_ORDER_PROMO_AA = new StringBuilder(" WHERE PROMOAA.ID_ORDEN = ? ORDER BY PROMOAA.FECHA_CARGA, PROMOAA.ID_PROMOCION_AGENTE_ADUANAL ");

    /**
     * Este atributo es un funcion para insertar datos en las columnas
     * correspondientes
     */
    private static final StringBuilder SQL_INSERT_INTO
            = new StringBuilder(" ( ID_PROMOCION_AGENTE_ADUANAL, ID_ORDEN, FECHA_CARGA, RUTA_ARCHIVO, ID_ASOCIADO_CARGA, ID_EMPLEADO ) VALUES ( ?, ?, SYSDATE, ?, ?, ? )");

    /**
     * Este atributo es un SELECT para obtener los datos de la tabla
     * FECET_PROMOCION_AGENTE_ADUANAL
     */
    private static final StringBuilder SQL_SELECT
            = new StringBuilder("SELECT ID_PROMOCION_AGENTE_ADUANAL, ID_ORDEN, FECHA_CARGA, RUTA_ARCHIVO, ID_ASOCIADO_CARGA, ID_EMPLEADO FROM ").append(getTableName());

    /**
     * Este metodo es un SELECT para obtener los datos de un contador del numero
     * de pruebas y alegatos
     */
    private StringBuilder getSqlSelectContadorPruebasAlegatos() {
        StringBuilder query = new StringBuilder();

        query.append(" ");
        query.append(" SELECT \n");
        query.append("    PROMOAA.ID_PROMOCION_AGENTE_ADUANAL, \n");
        query.append("    PROMOAA.ID_ORDEN, \n");
        query.append("    PROMOAA.FECHA_CARGA, \n");
        query.append("    PROMOAA.RUTA_ARCHIVO,\n");
        query.append("    PROMOAA.ID_ASOCIADO_CARGA, \n");
        query.append("    PROMOAA.ID_EMPLEADO, \n");
        query.append("    (SELECT COUNT(0) FROM ");
        query.append(FecetAlegatoAgenteAduanalDaoImpl.getTableName());
        query.append(" ALEGATO WHERE PROMOAA.ID_PROMOCION_AGENTE_ADUANAL = ALEGATO.ID_PROMOCION_AGENTE_ADUANAL) TOTAL_PRUEBAS_ALEGATOS, \n");
        query.append("    ASOCIADO.ID_ASOCIADO, \n");
        query.append("    ASOCIADO.RFC_CONTRIBUYENTE, \n");
        query.append("    ASOCIADO.RFC, \n");
        query.append("    ASOCIADO.ID_ORDEN, \n");
        query.append("    ASOCIADO.ID_TIPO_ASOCIADO, \n");
        query.append("    ASOCIADO.NOMBRE, \n");
        query.append("    ASOCIADO.CORREO, \n");
        query.append("    ASOCIADO.TIPO_ASOCIADO, \n");
        query.append("    ASOCIADO.FECHA_BAJA, \n");
        query.append("    ASOCIADO.FECHA_ULTIMA_MOD, \n");
        query.append("    ASOCIADO.FECHA_ULTIMA_MOD_IDC, \n");
        query.append("    ASOCIADO.MEDIO_CONTACTO, \n");
        query.append("    ASOCIADO.ESTATUS \n");
        query.append("FROM ");
        query.append(getTableName());
        query.append(" PROMOAA \n");
        query.append(" LEFT JOIN FECET_ASOCIADO ASOCIADO \n");
        query.append("   ON PROMOAA.ID_ASOCIADO_CARGA = ASOCIADO.ID_ASOCIADO \n");
        return query;

    }

    @Override
    public BigDecimal getIdPromocionAgenteAduanal() {
        return getJdbcTemplateBase().queryForObject(SQL_DUAL.toString(), BigDecimal.class);
    }

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetPromocionPk
     */
    @Override
    public FecetPromocionAgenteAduanalPk insert(FecetPromocionAgenteAduanal dto) {

        if (dto.getIdPromocionAgenteAduanal() == null) {
            dto.setIdPromocionAgenteAduanal(getIdPromocionAgenteAduanal());
        }

        getJdbcTemplateBase().update(SQL_INSERT.append(getTableName()).append(SQL_INSERT_INTO).toString(),
                dto.getIdPromocionAgenteAduanal(), dto.getIdOrden(), dto.getFechaCarga(),
                dto.getRutaArchivo(), dto.getIdAsociadoCarga(), dto.getIdEmpleado());

        return dto.createPk();

    }

    @Override
    public void update(FecetPromocionAgenteAduanalPk pk, FecetPromocionAgenteAduanal dto) {

        getJdbcTemplateBase().update("UPDATE " + getTableName()
                + " SET ID_PROMOCION_AGENTE_ADUANAL = ?, ID_ORDEN = ?, FECHA_CARGA = ?,  RUTA_ARCHIVO = ?, ID_ASOCIADO_CARGA = ?, ID_EMPLEADO = ? WHERE ID_PROMOCION_AGENTE_ADUANAL = ?",
                dto.getIdPromocionAgenteAduanal(), dto.getIdOrden(), dto.getFechaCarga(),
                dto.getRutaArchivo(), dto.getIdAsociadoCarga(),
                dto.getIdEmpleado(), pk.getIdPromocionAgenteAduanal());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_PROMOCION_AGENTE_ADUANAL";
    }

    /**
     * Returns all rows from the FECET_PROMOCION_AGENTE_ADUANAL table that match
     * the criteria 'ID_PROMOCION_AGENTE_ADUANAL = :idPromocion'.
     */
    @Override
    public FecetPromocionAgenteAduanal findByPrimaryKey(BigDecimal idPromocionAgenteAduanal) {

        List<FecetPromocionAgenteAduanal> list
                = getJdbcTemplateBase().query(SQL_SELECT.append(SQL_WHERE).toString(), new FecetPromocionAgenteAduanalMapper(),
                        idPromocionAgenteAduanal);
        return list.isEmpty() ? null : list.get(0);

    }

    @Override
    public List<FecetPromocionAgenteAduanal> findAll() {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" ORDER BY ID_PROMOCION_AGENTE_ADUANAL").toString(),
                new FecetPromocionAgenteAduanalMapper());

    }

    @Override
    public List<FecetPromocionAgenteAduanal> findWhereIdPromocionEquals(BigDecimal idPromocionAgenteAduanal) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" WHERE ID_PROMOCION_AGENTE_ADUANAL = ? ORDER BY ID_PROMOCION_AGENTE_ADUANAL").toString(),
                new FecetPromocionAgenteAduanalMapper(), idPromocionAgenteAduanal);

    }

    @Override
    public List<FecetPromocionAgenteAduanal> findWhereIdOrdenEquals(BigDecimal idOrden) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(SQL_WHERE_ORDER).toString(), new FecetPromocionAgenteAduanalMapper(),
                idOrden);

    }

    @Override
    public List<FecetPromocionAgenteAduanal> findWhereFechaCargaEquals(Date fechaCarga) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" WHERE FECHA_CARGA = ? ORDER BY FECHA_CARGA").toString(),
                new FecetPromocionAgenteAduanalMapper(), fechaCarga);

    }

    @Override
    public FecetPromocionAgenteAduanal findByPrimaryKey(FecetPromocionAgenteAduanalPk pk) {
        return findByPrimaryKey(pk.getIdPromocionAgenteAduanal());
    }

    @Override
    public List<FecetPromocionAgenteAduanal> getPromocionAgenteAduanalContadorPruebasAlegatos(final BigDecimal idOrden) {
        StringBuilder query = new StringBuilder();

        query.append(getSqlSelectContadorPruebasAlegatos());
        query.append(SQL_WHERE_ORDER_PROMO_AA);
        return getJdbcTemplateBase().query(query.toString(), new FecetPromocionAgenteAduanalContadorAlegatosMapper(), idOrden);

    }

    @Override
    public BigDecimal getIdFecetPromocionAgenteAduanalPathDirectorio() {
        return getJdbcTemplateBase().queryForObject(SQL_DUAL.toString(), BigDecimal.class);
    }

    @Override
    public FecetPromocionAgenteAduanalPk insertarRegistroGuardarArchivoPromocionAgenteAduanal(FecetPromocionAgenteAduanal dto) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_INSERT);
        sql.append(getTableName());
        sql.append(SQL_INSERT_INTO);

        if (dto.getIdPromocionAgenteAduanal() == null) {
            dto.setIdPromocionAgenteAduanal(getIdPromocionAgenteAduanal());
        }

        getJdbcTemplateBase().update(sql.toString(),
                dto.getIdPromocionAgenteAduanal(), dto.getIdOrden(),
                dto.getRutaArchivo(), dto.getIdAsociadoCarga(),
                dto.getIdEmpleado());

        return dto.createPk();

    }

    @Override
    public List<FecetPromocionAgenteAduanal> obtenerRegistrosByOrdenAsociado(BigDecimal idOrden, BigDecimal idAsociado) {
        StringBuilder query = new StringBuilder();
        //CAMBIAR A LA TABLA FECET_ALEGATO_PROMOCION
        query.append(" SELECT PROMO.ID_PROMOCION_AGENTE_ADUANAL, PROMO.ID_ORDEN, PROMO.FECHA_CARGA, PROMO.RUTA_ARCHIVO, ");
        query.append(" PROMO.ID_ASOCIADO_CARGA, PROMO.ID_EMPLEADO, ");
        query.append("\n (SELECT COUNT(*) FROM FECET_ALEGATO_AGENTE_ADUANAL ALEGATO WHERE ALEGATO.ID_PROMOCION_AGENTE_ADUANAL  = PROMO.ID_PROMOCION_AGENTE_ADUANAL) TOTAL_DOCUMENTOS ");
        query.append("\n FROM FECET_PROMOCION_AGENTE_ADUANAL PROMO ");
        query.append("\n WHERE ID_ORDEN = ? ");
        query.append("\n AND ID_ASOCIADO_CARGA = ? ");
        return getJdbcTemplateBase().query(query.toString(), new FecetPromocionAgenteAduanalMapper(), idOrden, idAsociado);
    }
}
