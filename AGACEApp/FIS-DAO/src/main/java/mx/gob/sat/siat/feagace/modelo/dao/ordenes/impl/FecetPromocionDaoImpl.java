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

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetPromocionDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetPromocionContadorAlegatosMapper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetPromocionMapper;
import mx.gob.sat.siat.feagace.modelo.dto.common.FirmaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionPk;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PromocionDocsVO;

import org.springframework.stereotype.Repository;

@Repository("fecetPromocionDao")
public class FecetPromocionDaoImpl extends BaseJDBCDao<FecetPromocion> implements FecetPromocionDao {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Este atributo corresponde una funcion DUAL para un Query de la tabla
     * FECET_PROMOCION
     */
    private static final StringBuilder SQL_DUAL = new StringBuilder("SELECT FECEQ_PROMOCION.NEXTVAL FROM DUAL");

    /**
     * Este atributo corresponde a una funcion INSERT INTO para insertar datos a
     * la tabla FECET_PROMOCION
     */
    private static final StringBuilder SQL_INSERT = new StringBuilder("INSERT INTO ");

    /**
     * Este atributo corresponde a una condicion WHERE para obtener datos de la
     * tabla FECET_PORMOCION por un ID_PROMOCION
     */
    private static final StringBuilder SQL_WHERE = new StringBuilder(" WHERE ID_PROMOCION = ? ");

    /**
     * Este atributo es una condicion WHERE para obtener datos de la tabla
     * FECET_PROMOCION por una ID_ORDEN
     */
    private static final StringBuilder SQL_WHERE_ORDER = new StringBuilder(" WHERE FECET_PROMOCION.ID_ORDEN = ? ORDER BY FECET_PROMOCION.ID_ORDEN ");

    private static final StringBuilder SQL_WHERE_ORDER_PROMO = new StringBuilder(" WHERE PROMO.ID_ORDEN = ? ORDER BY PROMO.FECHA_CARGA, PROMO.ID_PROMOCION ");

    /**
     * Este atributo es un funcion para insertar datos en las columnas
     * correspondientes
     */
    private static final StringBuilder SQL_INSERT_INTO
            = new StringBuilder(" ( ID_PROMOCION, ID_ORDEN, FECHA_CARGA, RUTA_ARCHIVO, ID_ASOCIADO_CARGA, RUTA_ACUSE, EXTEMPORANEA, CADENA_ORIGINAL, FIRMA_ELECTRONICA ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? )");

    /**
     * Este atributo es un SELECT para obtener los datos de la tabla
     * FECET_PROMOCION
     */
    private static final StringBuilder SQL_SELECT
            = new StringBuilder("SELECT ID_PROMOCION, ID_ORDEN, FECHA_CARGA, RUTA_ARCHIVO, ID_ASOCIADO_CARGA, RUTA_ACUSE, EXTEMPORANEA, CADENA_ORIGINAL, FIRMA_ELECTRONICA FROM ").append(getTableName());

    /**
     * Este metodo es un SELECT para obtener los datos de un contador del numero
     * de pruebas y alegatos
     */
    private StringBuilder getSqlSelectContadorPruebasAlegatos() {
        StringBuilder query = new StringBuilder();

        query.append(" ");
        query.append(" SELECT \n");
        query.append("    PROMO.ID_PROMOCION, \n");
        query.append("    PROMO.ID_ORDEN, \n");
        query.append("    PROMO.FECHA_CARGA, \n");
        query.append("    PROMO.RUTA_ARCHIVO,\n");
        query.append("    PROMO.ID_ASOCIADO_CARGA, \n");
        query.append("    PROMO.RUTA_ACUSE, \n");
        query.append("    PROMO.EXTEMPORANEA, \n");
        query.append("    PROMO.CADENA_ORIGINAL, \n");
        query.append("    PROMO.FIRMA_ELECTRONICA, \n");
        query.append("    (SELECT COUNT(0) FROM ");
        query.append(FecetAlegatoDaoImpl.getTableName());
        query.append(" ALEGATO WHERE PROMO.ID_PROMOCION = ALEGATO.ID_PROMOCION) TOTAL_PRUEBAS_ALEGATOS, \n");
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
        query.append(" PROMO \n");
        query.append(" LEFT JOIN FECET_ASOCIADO ASOCIADO \n");
        query.append("   ON PROMO.ID_ASOCIADO_CARGA = ASOCIADO.ID_ASOCIADO \n");
        return query;

    }

    @Override
    public BigDecimal getIdPromocion() {
        return getJdbcTemplateBase().queryForObject(SQL_DUAL.toString(), BigDecimal.class);
    }

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetPromocionPk
     */
    @Override
    public FecetPromocionPk insert(FecetPromocion dto) {

        if (dto.getIdPromocion() == null) {
            dto.setIdPromocion(getIdPromocion());
        }

        getJdbcTemplateBase().update(SQL_INSERT.append(getTableName()).append(SQL_INSERT_INTO).toString(),
                dto.getIdPromocion(), dto.getIdOrden(), dto.getFechaCarga(),
                dto.getRutaArchivo(), dto.getIdAsociadoCarga(),
                dto.getRutaAcuse(), dto.getExtemporanea(),
                dto.getCadenaOriginal(), dto.getFirmaElectronica());

        return dto.createPk();

    }

    @Override
    public void update(FecetPromocionPk pk, FecetPromocion dto) {

        getJdbcTemplateBase().update("UPDATE " + getTableName()
                + " SET ID_PROMOCION = ?, ID_ORDEN = ?, FECHA_CARGA = ?,  RUTA_ARCHIVO = ?, ID_ASOCIADO_CARGA = ?, RUTA_ACUSE = ?, EXTEMPORANEA = ?, CADENA_ORIGINAL = ?, FIRMA_ELECTRONICA = ? WHERE ID_PROMOCION = ?",
                dto.getIdPromocion(), dto.getIdOrden(), dto.getFechaCarga(),
                dto.getRutaArchivo(), dto.getIdAsociadoCarga(),
                dto.getRutaAcuse(), dto.getExtemporanea(),
                dto.getCadenaOriginal(), dto.getFirmaElectronica(), pk.getIdPromocion());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_PROMOCION";
    }

    /**
     * Returns all rows from the FECET_PROMOCION table that match the criteria
     * 'ID_PROMOCION = :idPromocion'.
     */
    @Override
    public FecetPromocion findByPrimaryKey(BigDecimal idPromocion) {

        List<FecetPromocion> list
                = getJdbcTemplateBase().query(SQL_SELECT.append(SQL_WHERE).toString(), new FecetPromocionMapper(),
                        idPromocion);
        return list.isEmpty() ? null : list.get(0);

    }

    @Override
    public List<FecetPromocion> findAll() {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" ORDER BY ID_PROMOCION").toString(),
                new FecetPromocionMapper());

    }

    @Override
    public List<FecetPromocion> findWhereIdPromocionEquals(BigDecimal idPromocion) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" WHERE ID_PROMOCION = ? ORDER BY ID_PROMOCION").toString(),
                new FecetPromocionMapper(), idPromocion);

    }

    @Override
    public List<FecetPromocion> findWhereIdOrdenEquals(BigDecimal idOrden) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(SQL_WHERE_ORDER).toString(), new FecetPromocionMapper(),
                idOrden);

    }

    @Override
    public List<FecetPromocion> findWhereFechaCargaEquals(Date fechaCarga) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" WHERE FECHA_CARGA = ? ORDER BY FECHA_CARGA").toString(),
                new FecetPromocionMapper(), fechaCarga);

    }

    @Override
    public List<FecetPromocion> findWhereRutaArchivoEquals(String rutaArchivo) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" WHERE RUTA_ARCHIVO = ? ORDER BY RUTA_ARCHIVO").toString(),
                new FecetPromocionMapper(), rutaArchivo);

    }

    @Override
    public FecetPromocion findByPrimaryKey(FecetPromocionPk pk) {

        return findByPrimaryKey(pk.getIdPromocion());

    }

    @Override
    public List<FecetPromocion> getPromocionContadorPruebasAlegatos(final BigDecimal idOrden) {
        StringBuilder query = new StringBuilder();

        query.append(getSqlSelectContadorPruebasAlegatos());
        query.append(SQL_WHERE_ORDER_PROMO);
        return getJdbcTemplateBase().query(query.toString(), new FecetPromocionContadorAlegatosMapper(), idOrden);

    }

    @Override
    public List<FecetPromocion> buscarPromocionRfcUsuario(String rfcUsuario) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" WHERE RFC_USUARIO = ? ORDER BY RFC_USUARIO").toString(),
                new FecetPromocionMapper(), rfcUsuario);

    }

    @Override
    public List<FecetPromocion> buscarPromocionNombreUsuario(String nombreUsuario) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" WHERE NOMBRE_USUARIO = ? ORDER BY NOMBRE_USUARIO").toString(),
                new FecetPromocionMapper(), nombreUsuario);

    }

    @Override
    public List<FecetPromocion> buscarPromocionExtemporenea(String extemporanea) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" WHERE EXTEMPORANEA = ? ORDER BY ID_PROMOCION").toString(),
                new FecetPromocionMapper(), extemporanea);

    }

    @Override
    public BigDecimal getIdFecetPromocionPathDirectorio() {

        return getJdbcTemplateBase().queryForObject(SQL_DUAL.toString(), BigDecimal.class);

    }

    @Override
    public FecetPromocionPk insertarRegistroGuardarArchivoPromocion(FecetPromocion dto) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_INSERT);
        sql.append(getTableName());
        sql.append(SQL_INSERT_INTO);

        getJdbcTemplateBase().update(sql.toString(),
                dto.getIdPromocion(), dto.getIdOrden(), dto.getFechaCarga(),
                dto.getRutaArchivo(), dto.getIdAsociadoCarga(),
                dto.getRutaAcuse(), dto.getExtemporanea(),
                dto.getCadenaOriginal(), dto.getFirmaElectronica());

        return dto.createPk();

    }

    @Override
    public void guardarFirma(FirmaDTO firma) {
        StringBuilder sqlPromo = new StringBuilder();
        sqlPromo.append(SQL_INSERT);
        sqlPromo.append(getTableName());
        sqlPromo.append(SQL_INSERT_INTO);

        PromocionDocsVO dto = (PromocionDocsVO) firma.getObjectoFirma();

        if (dto.getPromocionSeleccionado().getIdPromocion() == null) {
            dto.getPromocionSeleccionado().setIdPromocion(getIdPromocion());
        }

        getJdbcTemplateBase().update(sqlPromo.toString(),
                dto.getPromocionSeleccionado().getIdPromocion(), dto.getPromocionSeleccionado().getIdOrden(), dto.getPromocionSeleccionado().getFechaCarga(),
                dto.getPromocionSeleccionado().getRutaArchivo(), dto.getPromocionSeleccionado().getIdAsociadoCarga(),
                dto.getPromocionSeleccionado().getRutaAcuse(), dto.getPromocionSeleccionado().getExtemporanea(),
                dto.getPromocionSeleccionado().getCadenaOriginal(), dto.getPromocionSeleccionado().getFirmaElectronica());

    }
}
