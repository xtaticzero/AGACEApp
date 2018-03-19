package mx.gob.sat.siat.feagace.modelo.dao.ordenes.impl;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetOficioAnexosDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetOficioAnexosMapper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetOficioPendienteAnexosMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;

import org.springframework.stereotype.Repository;

@Repository("fecetOficioAnexosDao")
public class FecetOficioAnexosDaoImpl extends BaseJDBCDao<FecetOficioAnexos> implements FecetOficioAnexosDao {

    /**
     * Este atributo es un SELECT para seleccionar los datos de la tabla
     * FECET_OFICIO_ANEXOS JOIN
     */
    private static final String SELECT_ANEXOS_OFICIO = "SELECT FOA.ID_OFICIO_ANEXOS, FOA.ID_OFICIO, FOA.RUTA_ARCHIVO, FOA.FECHA_CREACION FROM ";

    private static final String JOINS_ANEXOS_OFICIO = "INNER JOIN FECET_OFICIO OFICIO ON FOA.ID_OFICIO = OFICIO.ID_OFICIO\n"
            + "INNER JOIN FECET_ORDEN ORDEN ON ORDEN.ID_ORDEN = OFICIO.ID_ORDEN\n";
    /**
     * Este atributo es un SELECT para seleccionar los datos de la tabla
     * FECET_OFICIO_ANEXOS
     */
    private static final StringBuilder SQL_SELECT
            = new StringBuilder("SELECT ID_OFICIO_ANEXOS ,ID_OFICIO, RUTA_ARCHIVO, FECHA_CREACION FROM ");

    /**
     * Este atributo corresponde una funcion DUAL para un Query de la tabla
     * FECET_OFICIO_ANEXOS
     */
    private static final StringBuilder SQL_DUAL
            = new StringBuilder("SELECT FECEQ_OFICIO_ANEXOS.NEXTVAL FROM DUAL");

    /**
     * este atributo corresponde a una funcion UPDATE para modificar los datos
     * de la tabla FECET_OFICIO_ANEXOS
     */
    private static final StringBuilder SQL_UPDATE = new StringBuilder(" UPDATE ");

    /**
     * este atributo corresponde a un complemento de funcion SELECT para buscar
     * por criterio de IdOrden
     */
    private static final StringBuilder SQL_INSERT = new StringBuilder("INSERT INTO ");

    /**
     * este atributo corresponde a un complemento de funcion DELETE para
     * eliminar por criterio de IdOrden
     */
    private static final StringBuilder SQL_DELETE = new StringBuilder("DELETE FROM ");
    @SuppressWarnings("compatibility:6866413700133826376")
    private static final long serialVersionUID = 1L;

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetOficioAnexos
     */
    public FecetOficioAnexos insert(FecetOficioAnexos dto) {

        StringBuilder query = new StringBuilder();

        if (dto.getIdOficioAnexos() == null) {
            dto.setIdOficioAnexos(getIdFecetOficioAnexosPathDirectorio());
        }

        query.append(SQL_INSERT).append(getTableName());
        query.append(" ( ID_OFICIO_ANEXOS ,ID_OFICIO, RUTA_ARCHIVO, ").append(
                "FECHA_CREACION )").append(
                        "VALUES ( ?, ?, ?, ? )");
        getJdbcTemplateBase().update(query.toString(), dto.getIdOficioAnexos(), dto.getIdOficio(),
                dto.getRutaArchivo(), dto.getFechaCreacion());
        return dto;
    }

    /**
     * Updates a single row in the FECET_OFICIO_ANEXOS table.
     */
    public void update(FecetOficioAnexos dto) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_UPDATE).append(getTableName()).append(" SET ID_OFICIO = ?, ").append(
                "  RUTA_ARCHIVO = ?, FECHA_CREACION = ? WHERE ID_OFICIO_ANEXOS = ? ");

        getJdbcTemplateBase().update(query.toString(), dto.getIdOficio(), dto.getRutaArchivo(),
                dto.getFechaCreacion(), dto.getIdOficioAnexos());

    }

    /**
     * Deletes a single row in the FECET_OFICIO_ANEXOS table.
     */
    public void delete(FecetOficioAnexos pk) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_DELETE).append(getTableName()).append(" WHERE ID_PRORROGA_ORDEN = ?");
        getJdbcTemplateBase().update(query.toString(), pk.getIdOficioAnexos());

    }

    /**
     * Metodo getIdFecetOficioAnexosPathDirectorio
     *
     * @return BigDecimal
     * @
     */
    public BigDecimal getIdFecetOficioAnexosPathDirectorio() {

        return getJdbcTemplateBase().queryForObject(SQL_DUAL.toString(), BigDecimal.class);

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_OFICIO_ANEXOS";
    }

    /**
     * Returns all rows from the FECET_OFICIO_ANEXOS table that match the
     * criteria 'ID_OFICIO = :idOficio'.
     */
    public List<FecetOficioAnexos> getAnexosRechazo(final BigDecimal idOficio) {
        StringBuilder query = new StringBuilder();
        query.append(SELECT_ANEXOS_OFICIO)
                .append(getTableName()).append(" FOA ")
                .append(JOINS_ANEXOS_OFICIO)
                .append(" WHERE OFICIO.ID_OFICIO = ? ORDER BY OFICIO.ID_OFICIO");
        return getJdbcTemplateBase().query(query.toString(), new FecetOficioPendienteAnexosMapper(), idOficio);
    }

    /**
     * Returns all rows from the FECET_OFICIO_ANEXOS table that match the
     * criteria 'ID_OFICIO = :idOficio'.
     */
    public List<FecetOficioAnexos> getAnexosByIdOficio(final BigDecimal idOficio) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(getTableName()).append(" WHERE ID_OFICIO = ? ORDER BY ID_OFICIO");
        return getJdbcTemplateBase().query(query.toString(), new FecetOficioAnexosMapper(), idOficio);

    }
}
