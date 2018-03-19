package mx.gob.sat.siat.feagace.modelo.dao.ordenes.anexos.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.anexos.FecetAnexoPruebasPericialesDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetAnexoPruebasPericialesMapper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetAnexoPruebasPericialesRelacionEmpleadosMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexoPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexoPruebasPericialesPk;

@Repository("fecetAnexoPruebasPericialesDao")
public class FecetAnexoPruebasPericialesDaoImpl extends BaseJDBCDao<FecetAnexoPruebasPericiales>
        implements FecetAnexoPruebasPericialesDao {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final StringBuilder SQL_SELECT = new StringBuilder(
            "SELECT ID_ANEXO_PRUEBAS_PERICIALES ,ID_FLUJO_PRUEBAS_PERICIALES, RUTA_ARCHIVO, FECHA_CREACION, BLN_ACTIVO, FECHA_FIN, TIPO_ANEXO FROM ");

    /**
     * Este atributo corresponde una funcion DUAL para un Query de la tabla
     * FECET_ANEXOS_PRORROGA_ORDEN
     */
    private static final StringBuilder SQL_DUAL = new StringBuilder(
            "SELECT FECEQ_ANEXOS_PRORROGA_ORDEN.NEXTVAL FROM DUAL");

    /**
     * este atributo corresponde a una funcion UPDATE para modificar los datos
     * de la tabla FECET_ANEXOS_PRORROGA_ORDEN
     */
    private static final StringBuilder SQL_UPDATE = new StringBuilder(" UPDATE ");

    /**
     * este atributo corresponde a un complemento de funcion SELECT para buscar
     * por criterio de IdOrden
     */
    private static final StringBuilder SQL_INSERT = new StringBuilder("INSERT INTO ");

    private static final StringBuilder JOIN = new StringBuilder(" INNER JOIN ");

    private static final StringBuilder SQL_SELECT_JOIN = new StringBuilder(
            "SELECT ANEXO.ID_ANEXO_PRUEBAS_PERICIALES, ANEXO.FECHA_CREACION, ANEXO.RUTA_ARCHIVO,PP.ID_AUDITOR ID_EMPLEADO ");

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetAnexoPruebasPericialesPk
     */
    public FecetAnexoPruebasPericialesPk insert(FecetAnexoPruebasPericiales dto) {

        dto.setIdAnexoPruebasPericiales(getIdFecetAnexoPruebasPericialesPathDirectorio());
        StringBuilder query = new StringBuilder();
        query.append(SQL_INSERT).append(getTableName());
        query.append(" ( ID_ANEXO_PRUEBAS_PERICIALES, ID_FLUJO_PRUEBAS_PERICIALES, RUTA_ARCHIVO, ")
                .append("FECHA_CREACION, BLN_ACTIVO, FECHA_FIN, TIPO_ANEXO )")
                .append(" VALUES ( ?, ?, ?, ?, ?, ?, ? )");
        getJdbcTemplateBase().update(query.toString(), dto.getIdAnexoPruebasPericiales(), dto.getIdFlujoPruebasPericiales(),
                dto.getRutaArchivo(), dto.getFechaCreacion(), dto.getBlnActivo(), dto.getFechaFin(),
                dto.getTipoAnexo());
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECET_ANEXOS_PRORROGA_ORDEN table.
     */
    public void update(FecetAnexoPruebasPericialesPk pk, FecetAnexoPruebasPericiales dto) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_UPDATE).append(getTableName()).append(" SET ID_ANEXO_PRUEBAS_PERICIALES = ?, ")
                .append("ID_FLUJO_PRUEBAS_PERICIALES = ?, RUTA_ARCHIVO = ?, FECHA_CREACION = ?, BLN_ACTIVO = ?, ")
                .append("FECHA_FIN = ?, TIPO_ANEXO = ? WHERE ID_ANEXO_PRUEBAS_PERICIALES = ? ");

        getJdbcTemplateBase().update(query.toString(), dto.getIdAnexoPruebasPericiales(), dto.getIdFlujoPruebasPericiales(),
                dto.getRutaArchivo(), dto.getFechaCreacion(), dto.getBlnActivo(), dto.getFechaFin(), dto.getTipoAnexo(),
                pk.getIdAnexoPruebasPericiales());

    }

    /**
     * Metodo getIdFecetAnexoPruebasPericialesPathDirectorio
     *
     * @return BigDecimal @
     */
    public BigDecimal getIdFecetAnexoPruebasPericialesPathDirectorio() {
        return getJdbcTemplateBase().queryForObject(SQL_DUAL.toString(), BigDecimal.class);
    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_ANEXO_PRUEBAS_PERICIALES";
    }

    /**
     * Returns all rows from the FECET_ANEXOS_PRORROGA_ORDEN table that match
     * the criteria 'ID_ANEXO_PRUEBAS_PERICIALES = :idAnexosProrrogaOrden'.
     */
    public FecetAnexoPruebasPericiales findByPrimaryKey(BigDecimal idAnexosProrrogaOrden) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(getTableName()).append(" WHERE ID_ANEXO_PRUEBAS_PERICIALES = ?");
        List<FecetAnexoPruebasPericiales> list = getJdbcTemplateBase().query(query.toString(),
                new FecetAnexoPruebasPericialesMapper(), idAnexosProrrogaOrden);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECET_ANEXOS_PRORROGA_ORDEN table that match
     * the criteria ''.
     */
    public List<FecetAnexoPruebasPericiales> findAll() {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(getTableName()).append(" ORDER BY ID_ANEXO_PRUEBAS_PERICIALES");
        return getJdbcTemplateBase().query(query.toString(), new FecetAnexoPruebasPericialesMapper());

    }

    /**
     * Returns all rows from the FECET_ANEXOS_PRORROGA_ORDEN table that match
     * the criteria 'ID_ANEXO_PRUEBAS_PERICIALES = :idAnexosProrrogaOrden'.
     */
    public List<FecetAnexoPruebasPericiales> findWhereIdProrrogaEquals(BigDecimal idAnexosProrrogaOrden) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT).append(getTableName())
                .append(" WHERE ID_FLUJO_PRUEBAS_PERICIALES = ? ORDER BY ID_ANEXO_PRUEBAS_PERICIALES");
        logger.info("Anexos de resolucion de prorroga {} ", query.toString());
        return getJdbcTemplateBase().query(query.toString(), new FecetAnexoPruebasPericialesMapper(),
                idAnexosProrrogaOrden);

    }

    /**
     * Returns the rows from the FECET_ANEXOS_PRORROGA_ORDEN table that matches
     * the specified primary-key value.
     */
    public FecetAnexoPruebasPericiales findByPrimaryKey(FecetAnexoPruebasPericialesPk pk) {
        return findByPrimaryKey(pk.getIdAnexoPruebasPericiales());
    }

    /**
     * Returns all rows from the FECET_ANEXOS_PRORROGA_ORDEN table that match
     * the criteria 'ID_FLUJO_PRUEBAS_PERICIALES = :idFlujoProrrogaOrden'.
     */
    public List<FecetAnexoPruebasPericiales> findWhereIdFlujoPruebasPericialesEquals(final BigDecimal idFlujoPruebasPericialesOrden) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT).append(getTableName())
                .append(" WHERE ID_FLUJO_PRUEBAS_PERICIALES = ? ORDER BY ID_FLUJO_PRUEBAS_PERICIALES");
        return getJdbcTemplateBase().query(query.toString(), new FecetAnexoPruebasPericialesMapper(),
                idFlujoPruebasPericialesOrden);

    }

    /**
     * @param idAnexosProrrogaOrden
     * @return List anexos de prorroga agragados por el Auditor
     */
    public List<FecetAnexoPruebasPericiales> obtenerAnexosResolucionPruebaPericialAuditor(BigDecimal idAnexosPruebaPericial) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT_JOIN);
        query.append(" FROM ");
        query.append(getTableName());
        query.append(" ANEXO ");
        query.append(JOIN).append(" FECET_FLUJO_PRUEBAS_PERICIALES FLUJO ON FLUJO.ID_FLUJO_PRUEBAS_PERICIALES = ANEXO.ID_FLUJO_PRUEBAS_PERICIALES ");
        query.append(JOIN).append(" FECET_PRUEBAS_PERICIALES PP ON PP.ID_PRUEBAS_PERICIALES = FLUJO.ID_PRUEBAS_PERICIALES ");
        query.append(JOIN).append(" FECET_ORDEN ORDEN ON ORDEN.ID_ORDEN = PP.ID_ORDEN  ");

        query.append("  WHERE ANEXO.ID_FLUJO_PRUEBAS_PERICIALES = ? ORDER BY ANEXO.ID_ANEXO_PRUEBAS_PERICIALES ");

        logger.debug("SQL: {} ", query.toString());
        logger.debug("PARAMS: {} ", idAnexosPruebaPericial);
        return getJdbcTemplateBase().query(query.toString(), new FecetAnexoPruebasPericialesRelacionEmpleadosMapper(),
                idAnexosPruebaPericial);

    }

}
