package mx.gob.sat.siat.feagace.modelo.dao.ordenes.anexos.impl;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.anexos.FecetAnexosRechazoOficioDao;

import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetAnexosRechazoOficioMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosRechazoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;

import org.springframework.stereotype.Repository;

@Repository("fecetAnexosRechazoOficioDao")
public class FecetAnexosRechazoOficioDaoImpl extends BaseJDBCDao<FecetOficioAnexos> implements FecetAnexosRechazoOficioDao {

    /**
     * Este atributo es un SELECT para seleccionar los datos de la tabla
     * FECET_ANEXOS_PRORROGA_Oficio
     */
    private static final StringBuilder SQL_SELECT
            = new StringBuilder("SELECT ID_ANEXOS_RECHAZO_OFICIO, ID_RECHAZO_OFICIO, RUTA_ARCHIVO, FECHA_CARGA, ID_EMPLEADO FROM ");

    /**
     * Este atributo corresponde una funcion DUAL para un Query de la tabla
     * FECET_ANEXOS_PRORROGA_OFICIO
     */
    private static final StringBuilder SQL_DUAL
            = new StringBuilder("SELECT FECEQ_ANEXOS_RECHAZO_OFICIO.NEXTVAL FROM DUAL");

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

    /**
     * Metodo getIdFecetOficioAnexosPathDirectorio
     *
     * @return BigDecimal
     * @
     */
    public BigDecimal getIdFecetAnexosRechazoPathDirectorio() {
        return getJdbcTemplateBase().queryForObject(SQL_DUAL.toString(), BigDecimal.class);
    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_ANEXOS_RECHAZO_OFICIO";
    }

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetAnexosProrrogaOficioPk
     */
    public FecetAnexosRechazoOficio insert(FecetAnexosRechazoOficio dto) {

        StringBuilder query = new StringBuilder();

        if (dto.getIdAnexosRechazoOficio() == null) {
            dto.setIdAnexosRechazoOficio(getIdFecetAnexosRechazoPathDirectorio());
        }

        query.append(SQL_INSERT).append(getTableName());
        query.append(" ( ID_ANEXOS_RECHAZO_OFICIO, ID_RECHAZO_OFICIO, RUTA_ARCHIVO, FECHA_CARGA, ID_EMPLEADO )").append(
                "VALUES ( ?, ?, ?, ?, ? )");
        getJdbcTemplateBase().update(query.toString(), dto.getIdAnexosRechazoOficio(), dto.getIdRechazoOficio(),
                dto.getRutaArchivo(), dto.getFechaCarga(), dto.getIdEmpleado());
        return dto;

    }

    /**
     * Updates a single row in the FECET_ANEXOS_PRORROGA_OFICIO table.
     */
    public void update(FecetAnexosRechazoOficio dto) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_UPDATE).append(getTableName()).append(" SET ID_RECHAZO_OFICIO = ?, RUTA_ARCHIVO = ?, FECHA_CARGA = ?, ID_EMPLEADO = ? WHERE ID_ANEXOS_RECHAZO_OFICIO = ? ");

        getJdbcTemplateBase().update(query.toString(), dto.getIdRechazoOficio(),
                dto.getRutaArchivo(), dto.getFechaCarga(), dto.getIdEmpleado(), dto.getIdAnexosRechazoOficio());
    }

    /**
     * Returns all rows from the FECET_ANEXOS_RECHAZO_OFICIO table that match
     * the criteria 'ID_RECHAZO_OFICIO = :idRechazoOficio'.
     */
    public List<FecetAnexosRechazoOficio> getAnexosRechazoOficioByIdRechazoOficio(final BigDecimal idRechazoOficio) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(getTableName()).append(" WHERE ID_RECHAZO_OFICIO = ? ");
        return getJdbcTemplateBase().query(query.toString(), new FecetAnexosRechazoOficioMapper(), idRechazoOficio);

    }
}
