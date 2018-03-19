package mx.gob.sat.siat.feagace.modelo.dao.ordenes.impl;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetPromocionOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetPromocionOficioContadorAlegatosMapper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetPromocionOficioMapper;
import mx.gob.sat.siat.feagace.modelo.dto.common.FirmaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficio;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficioPk;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PromocionDocsVO;

import org.springframework.stereotype.Repository;

@Repository("fecetPromocionOficioDao")
public class FecetPromocionOficioDaoImpl extends BaseJDBCDao<FecetPromocionOficio> implements FecetPromocionOficioDao {

    /**
     * Este atributo corresponde una funcion DUAL para un Query de la tabla
     * FECET_PROMOCION_OFICIO
     */
    private static final StringBuilder SQL_DUAL = new StringBuilder("SELECT FECEQ_PROMOCION_OFICIO.NEXTVAL FROM DUAL");

    /**
     * Este atributo corresponde a una funcion INSERT INTO para insertar datos a
     * la tabla FECET_PROMOCION_OFICIO
     */
    private static final StringBuilder SQL_INSERT = new StringBuilder("INSERT INTO ");

    /**
     * Este atributo es una condicion WHERE para obtener datos de la tabla
     * FECET_PROMOCION_OFICIO por una ID_OFICIO
     */
    private static final StringBuilder SQL_WHERE_ORDER
            = new StringBuilder(" WHERE FECET_PROMOCION_OFICIO.ID_OFICIO = ? ORDER BY FECET_PROMOCION_OFICIO.ID_OFICIO ");

    private static final StringBuilder SQL_WHERE_ORDER_PROMO
            = new StringBuilder(" WHERE PROMO.ID_OFICIO = ? ORDER BY PROMO.ID_OFICIO ");

    /**
     * Este atributo es un funcion para insertar datos en las columnas
     * correspondientes
     */
    private static final StringBuilder SQL_INSERT_INTO
            = new StringBuilder(" ( ID_PROMOCION_OFICIO, ID_OFICIO, FECHA_CARGA, RUTA_ARCHIVO, ID_ASOCIADO_CARGA, RUTA_ACUSE, EXTEMPORANEA, CADENA_ORIGINAL, FIRMA_ELECTRONICA ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? )");

    /**
     * Este atributo es un SELECT para obtener los datos de la tabla
     * FECET_PROMOCION_OFICIO
     */
    private static final StringBuilder SQL_SELECT
            = new StringBuilder("SELECT ID_PROMOCION_OFICIO, ID_OFICIO, FECHA_CARGA, RUTA_ARCHIVO, ID_ASOCIADO_CARGA, RUTA_ACUSE, EXTEMPORANEA, CADENA_ORIGINAL, FIRMA_ELECTRONICA FROM ").append(getTableName());
    @SuppressWarnings("compatibility:1392777445340452819")
    private static final long serialVersionUID = 1L;

    /**
     * Este metodo es un SELECT para obtener los datos de un contador del numero
     * de pruebas y alegatos
     */
    private StringBuilder getSqlSelectContadorPruebasAlegatos() {
        StringBuilder query = new StringBuilder();

        query.append(" ");
        query.append(" SELECT \n");
        query.append("    PROMO.ID_PROMOCION_OFICIO, \n");
        query.append("    PROMO.ID_OFICIO, \n");
        query.append("    PROMO.FECHA_CARGA, \n");
        query.append("    PROMO.RUTA_ARCHIVO,\n");
        query.append("    PROMO.ID_ASOCIADO_CARGA, \n");
        query.append("    PROMO.RUTA_ACUSE, \n");
        query.append("    PROMO.EXTEMPORANEA, \n");
        query.append("    PROMO.CADENA_ORIGINAL, \n");
        query.append("    PROMO.FIRMA_ELECTRONICA, \n");
        query.append("    (SELECT COUNT(*) FROM ");
        query.append(FecetAlegatoOficioDaoImpl.getTableName());
        query.append(" ALEGATO WHERE PROMO.ID_PROMOCION_OFICIO = ALEGATO.ID_PROMOCION_OFICIO) TOTAL_PRUEBAS_ALEGATOS, \n");
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

    public BigDecimal getIdPromocionOficio() {
        return getJdbcTemplateBase().queryForObject(SQL_DUAL.toString(), BigDecimal.class);
    }

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetPromocionPk
     */
    public BigDecimal insert(FecetPromocionOficio dto) {

        if (dto.getIdPromocionOficio() == null) {
            dto.setIdPromocionOficio(getIdPromocionOficio());
        }

        getJdbcTemplateBase().update(SQL_INSERT.append(getTableName()).append(SQL_INSERT_INTO).toString(),
                dto.getIdPromocionOficio(), dto.getIdOficio(), dto.getFechaCarga(),
                dto.getRutaArchivo(), dto.getIdAsociadoCarga(),
                dto.getRutaAcuse(), dto.getExtemporanea(),
                dto.getCadenaOriginal(), dto.getFirmaElectronica());

        return dto.getIdPromocionOficio();

    }

    /**
     * Updates a single row in the FECET_PROMOCION_OFICIO table.
     */
    public void update(FecetPromocionOficio dto) {

        getJdbcTemplateBase().update("UPDATE " + getTableName()
                + " SET ID_PROMOCION_OFICIO = ?, ID_OFICIO = ?, FECHA_CARGA = ?, RUTA_ARCHIVO = ?, ID_ASOCIADO_CARGA = ?, RUTA_ACUSE = ?, EXTEMPORANEA = ?, CADENA_ORIGINAL = ?, FIRMA_ELECTRONICA = ? WHERE ID_PROMOCION_OFICIO = ?",
                dto.getIdPromocionOficio(), dto.getIdOficio(), dto.getFechaCarga(),
                dto.getRutaArchivo(), dto.getIdAsociadoCarga(),
                dto.getRutaAcuse(), dto.getExtemporanea(),
                dto.getCadenaOriginal(), dto.getFirmaElectronica(), dto.getIdPromocionOficio());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_PROMOCION_OFICIO";
    }

    /**
     * Returns all rows from the FECET_PROMOCION_OFICIO table that match the
     * criteria ''.
     */
    public List<FecetPromocionOficio> findAll() {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" ORDER BY ID_PROMOCION_OFICIO").toString(),
                new FecetPromocionOficioMapper());

    }

    /**
     * Returns all rows from the FECET_PROMOCION_OFICIO table that match the
     * criteria 'ID_PROMOCION_OFICIO = :idPromocion'.
     */
    public List<FecetPromocionOficio> findWhereIdPromocionEquals(BigDecimal idPromocion) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" WHERE ID_PROMOCION_OFICIO = ? ORDER BY ID_PROMOCION_OFICIO").toString(),
                new FecetPromocionOficioMapper(), idPromocion);

    }

    /**
     * Returns all rows from the FECET_PROMOCION_OFICIO table that match the
     * criteria 'ID_OFICIO = :idOrden'.
     */
    public List<FecetPromocionOficio> findWhereIdOficioEquals(BigDecimal idOrden) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(SQL_WHERE_ORDER).toString(),
                new FecetPromocionOficioMapper(), idOrden);

    }

    /**
     * Returns all rows from the FECET_PROMOCION_OFICIO table that match the
     * criteria and count all items asociated to FECET_PROMOCION_OFICIO in to
     * FECET_ALEGATO where 'ID_OFICIO = :idOrden'.
     */
    public List<FecetPromocionOficio> getPromocionContadorPruebasAlegatosOficio(final BigDecimal idOrden) {
        StringBuilder sql = new StringBuilder();
        sql.append(getSqlSelectContadorPruebasAlegatos()).append(SQL_WHERE_ORDER_PROMO);

        return getJdbcTemplateBase().query(sql.toString(), new FecetPromocionOficioContadorAlegatosMapper(), idOrden);
    }

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetPromocionOficioPk
     */
    public FecetPromocionOficioPk insertarRegistroGuardarArchivoPromocionOficio(FecetPromocionOficio dto) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_INSERT);
        sql.append(getTableName());
        sql.append(SQL_INSERT_INTO);

        getJdbcTemplateBase().update(sql.toString(),
                dto.getIdPromocionOficio(), dto.getIdOficio(), dto.getFechaCarga(),
                dto.getRutaArchivo(), dto.getIdAsociadoCarga(),
                dto.getRutaAcuse(), dto.getExtemporanea(),
                dto.getCadenaOriginal(), dto.getFirmaElectronica());

        return dto.createPk();

    }

    @Override
    public void guardarFirma(FirmaDTO firma) {
        PromocionDocsVO dto = (PromocionDocsVO) firma.getObjectoFirma();

        if (dto.getPromocionOficioSeleccionado().getIdPromocionOficio() == null) {
            dto.getPromocionOficioSeleccionado().setIdPromocionOficio(getIdPromocionOficio());
        }
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_INSERT);
        sql.append(getTableName());
        sql.append(SQL_INSERT_INTO);

        getJdbcTemplateBase().update(sql.toString(),
                dto.getPromocionOficioSeleccionado().getIdPromocionOficio(), dto.getPromocionOficioSeleccionado().getIdOficio(), dto.getPromocionOficioSeleccionado().getFechaCarga(),
                dto.getPromocionOficioSeleccionado().getRutaArchivo(), dto.getPromocionOficioSeleccionado().getIdAsociadoCarga(),
                dto.getPromocionOficioSeleccionado().getRutaAcuse(), dto.getPromocionOficioSeleccionado().getExtemporanea(),
                dto.getPromocionOficioSeleccionado().getCadenaOriginal(), dto.getPromocionOficioSeleccionado().getFirmaElectronica());
    }
}
