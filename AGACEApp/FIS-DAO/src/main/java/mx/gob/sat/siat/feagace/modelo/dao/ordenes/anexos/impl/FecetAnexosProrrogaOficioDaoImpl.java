package mx.gob.sat.siat.feagace.modelo.dao.ordenes.anexos.impl;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.anexos.FecetAnexosProrrogaOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetAnexosProrrogaOficioMapper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetAnexosProrrogasOficioRelacionEmpleadoMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOficio;

import org.springframework.stereotype.Repository;

@Repository("fecetAnexosProrrogaOficioDao")
public class FecetAnexosProrrogaOficioDaoImpl extends BaseJDBCDao<FecetAnexosProrrogaOficio> implements FecetAnexosProrrogaOficioDao {

    @SuppressWarnings("compatibility:7224009203770158181")
    private static final long serialVersionUID = 1L;

    /**
     * Este atributo es un SELECT para seleccionar los datos de la tabla
     * FECET_ANEXOS_PRORROGA_Oficio
     */
    private static final StringBuilder SQL_SELECT
            = new StringBuilder("SELECT ID_ANEXOS_PRORROGA_OFICIO ,ID_FLUJO_PRORROGA_OFICIO, RUTA_ARCHIVO, FECHA_CREACION, BLN_ACTIVO, FECHA_FIN, TIPO_ANEXO FROM ");

    /**
     * Este atributo corresponde una funcion DUAL para un Query de la tabla
     * FECET_ANEXOS_PRORROGA_OFICIO
     */
    private static final StringBuilder SQL_DUAL
            = new StringBuilder("SELECT FECEQ_ANEXOS_PRORROGA_OFICIO.NEXTVAL FROM DUAL");

    /**
     * este atributo corresponde a una funcion UPDATE para modificar los datos
     * de la tabla FECET_ANEXOS_PRORROGA_OFICIO
     */
    private static final StringBuilder SQL_UPDATE = new StringBuilder(" UPDATE ");

    /**
     * este atributo corresponde a un complemento de funcion SELECT para buscar
     * por criterio de IdOficio
     */
    private static final StringBuilder SQL_INSERT = new StringBuilder("INSERT INTO ");

    private static final StringBuilder JOIN = new StringBuilder(" INNER JOIN ");

    private static final StringBuilder SQL_SELECT_JOIN = new StringBuilder("SELECT ANEXO.FECHA_CREACION, ANEXO.RUTA_ARCHIVO, ANEXO.ID_ANEXOS_PRORROGA_OFICIO, PRORROGA.ID_AUDITOR ID_EMPLEADO ");

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetAnexosProrrogaOficioPk
     */
    public BigDecimal insert(FecetAnexosProrrogaOficio dto) {

        StringBuilder query = new StringBuilder();
        if (dto.getIdAnexosProrrogaOficio() == null) {
            dto.setIdAnexosProrrogaOficio(getIdFecetAnexosProrrogaOficioPathDirectorio());
        }
        query.append(SQL_INSERT).append(getTableName());
        query.append(" ( ID_ANEXOS_PRORROGA_OFICIO ,ID_FLUJO_PRORROGA_OFICIO, RUTA_ARCHIVO, ").append(
                "FECHA_CREACION, BLN_ACTIVO, FECHA_FIN, TIPO_ANEXO )").append(
                        "VALUES ( ?, ?, ?, ?, ?, ?, ? )");
        int insert = getJdbcTemplateBase().update(query.toString(), dto.getIdAnexosProrrogaOficio(), dto.getIdFlujoProrrogaOficio(),
                dto.getRutaArchivo(), dto.getFechaCreacion(),
                dto.getBlnActivo(), dto.getFechaFin(), dto.getTipoAnexo());
        return new BigDecimal(insert);

    }

    /**
     * Updates a single row in the FECET_ANEXOS_PRORROGA_OFICIO table.
     */
    public void update(BigDecimal pk, FecetAnexosProrrogaOficio dto) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_UPDATE).append(getTableName()).append(" SET ID_ANEXOS_PRORROGA_OFICIO = ?, ").append(
                "ID_FLUJO_PRORROGA_OFICIO = ?, RUTA_ARCHIVO = ?, FECHA_CREACION = ?, BLN_ACTIVO = ?, ").append(
                        "FECHA_FIN = ?, TIPO_ANEXO = ? WHERE ID_ANEXOS_PRORROGA_OFICIO = ? ");

        getJdbcTemplateBase().update(query.toString(), dto.getIdAnexosProrrogaOficio(), dto.getIdFlujoProrrogaOficio(),
                dto.getRutaArchivo(), dto.getFechaCreacion(),
                dto.getBlnActivo(), dto.getFechaFin(), dto.getTipoAnexo(), pk);

    }

    /**
     * Metodo getIdFecetAnexosProrrogaOficioPathDirectorio
     *
     * @return BigDecimal
     * @
     */
    public BigDecimal getIdFecetAnexosProrrogaOficioPathDirectorio() {
        return getJdbcTemplateBase().queryForObject(SQL_DUAL.toString(), BigDecimal.class);
    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_ANEXOS_PRORROGA_OFICIO";
    }

    /**
     * Returns all rows from the FECET_ANEXOS_PRORROGA_OFICIO table that match
     * the criteria 'ID_PRORROGA_OFICIO = :idProrroga'.
     */
    public FecetAnexosProrrogaOficio findByPrimaryKey(BigDecimal idProrroga) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(getTableName()).append(" WHERE ID_ANEXOS_PRORROGA_OFICIO = ?");
        List<FecetAnexosProrrogaOficio> list = getJdbcTemplateBase().query(query.toString(), new FecetAnexosProrrogaOficioMapper(), idProrroga);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECET_ANEXOS_PRORROGA_OFICIO table that match
     * the criteria ''.
     */
    public List<FecetAnexosProrrogaOficio> findAll() {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(getTableName()).append(" ORDER BY ID_ANEXOS_PRORROGA_OFICIO");
        return getJdbcTemplateBase().query(query.toString(), new FecetAnexosProrrogaOficioMapper());

    }

    /**
     * Returns all rows from the FECET_ANEXOS_PRORROGA_OFICIO table that match
     * the criteria 'ID_FLUJO_PRORROGA_OFICIO = :idFlujoProrrogaOficio'.
     */
    public List<FecetAnexosProrrogaOficio> findWhereIdFlujoProrrogaOficioEquals(final BigDecimal idFlujoProrrogaOficio) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT_JOIN);
        query.append("FROM FECET_ANEXOS_PRORROGA_OFICIO ANEXO ");
        query.append(JOIN).append(" FECET_FLUJO_PRORROGA_OFICIO FLUJO ON FLUJO.ID_FLUJO_PRORROGA_OFICIO = ANEXO.ID_FLUJO_PRORROGA_OFICIO ");
        query.append(JOIN).append(" FECET_PRORROGA_OFICIO PRORROGA ON PRORROGA.ID_PRORROGA_OFICIO = FLUJO.ID_PRORROGA_OFICIO  ");
        query.append(JOIN).append(" FECET_OFICIO OFICIO ON OFICIO.ID_OFICIO = PRORROGA.ID_OFICIO  ");

        query.append("  WHERE ANEXO.ID_FLUJO_PRORROGA_OFICIO = ? ORDER BY ANEXO.ID_ANEXOS_PRORROGA_OFICIO ");
        logger.debug("[Anexos de resolucion de prorroga agregados por el auditor]");
        logger.debug("SQL: {} ", query.toString());
        logger.debug("PARAMS: {} ", idFlujoProrrogaOficio);
        return getJdbcTemplateBase().query(query.toString(), new FecetAnexosProrrogasOficioRelacionEmpleadoMapper(), idFlujoProrrogaOficio);

    }
}
