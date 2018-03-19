/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.insumos.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.FecetDocretroinsumoDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper.FecetDocretroinsumoMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocretroinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocretroinsumoPk;

@Repository("fecetDocretroinsumoDao")
public class FecetDocretroinsumoDaoImpl extends
        BaseJDBCDao<FecetDocretroinsumo> implements FecetDocretroinsumoDao {

    /**
     * Serial
     */
    private static final long serialVersionUID = -1452663506877534518L;

    private static final String SQL_SELECT = "SELECT ID_DOCRETROINSUMO, ID_RETROALIMENTACION_INSUMO, RUTA_ARCHIVO, FECHA_CREACION, ID_TIPO_EMPLEADO FROM ";

    @Override
    public FecetDocretroinsumoPk insert(FecetDocretroinsumo dto, EmpleadoDTO empleado) {

        if (empleado != null && empleado.getDetalleEmpleado() != null) {

            dto.setIdDocretroinsumo(getJdbcTemplateBase().queryForObject(
                    "SELECT FECEQ_DOCRETROINSUMO.NEXTVAL FROM DUAL",
                    BigDecimal.class));

            getJdbcTemplateBase()
                    .update("INSERT INTO "
                            + getTableName()
                            + " ( ID_DOCRETROINSUMO, ID_RETROALIMENTACION_INSUMO, RUTA_ARCHIVO, FECHA_CREACION,ID_TIPO_EMPLEADO ) VALUES ( ?, ?, ?, ?, ? )",
                            dto.getIdDocretroinsumo(),
                            dto.getIdRetroalimentacionInsumo(),
                            dto.getRutaArchivo().concat(dto.getNombreArchivo()),
                            dto.getFechaCreacion(), empleado.getDetalleEmpleado().get(0).getTipoEmpleado().getId());

            return dto.createPk();
        }

        return null;
    }

    public FecetDocretroinsumoPk insert(FecetDocretroinsumo dto) {

        dto.setIdDocretroinsumo(getJdbcTemplateBase().queryForObject(
                "SELECT FECEQ_DOCRETROINSUMO.NEXTVAL FROM DUAL",
                BigDecimal.class));

        getJdbcTemplateBase()
                .update("INSERT INTO "
                        + getTableName()
                        + " ( ID_DOCRETROINSUMO, ID_RETROALIMENTACION_INSUMO, RUTA_ARCHIVO, FECHA_CREACION,ID_TIPO_EMPLEADO ) VALUES ( ?, ?, ?, ?, ? )",
                        dto.getIdDocretroinsumo(),
                        dto.getIdRetroalimentacionInsumo(),
                        dto.getRutaArchivo() + dto.getNombreArchivo(),
                        dto.getFechaCreacion(), dto.getIdTipoEmpleado());
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECET_DOCRETROINSUMO table.
     */
    public void update(FecetDocretroinsumoPk pk, FecetDocretroinsumo dto) {

        getJdbcTemplateBase()
                .update("UPDATE "
                        + getTableName()
                        + " SET ID_DOCRETROINSUMO = ?, ID_RETROALIMENTACION_INSUMO = ?, RUTA_ARCHIVO = ?, FECHA_CREACION = ?, ID_TIPO_EMPLEADO = ? WHERE ID_DOCRETROINSUMO = ?",
                        dto.getIdDocretroinsumo(),
                        dto.getIdRetroalimentacionInsumo(),
                        dto.getRutaArchivo() + dto.getNombreArchivo(), dto.getFechaCreacion(),
                        dto.getIdTipoEmpleado(), pk.getIdDocretroinsumo());

    }

    /**
     * Deletes a single row in the FECET_DOCRETROINSUMO table.
     */
    public void delete(FecetDocretroinsumoPk pk) {

        getJdbcTemplateBase().update(
                "DELETE FROM " + getTableName()
                + " WHERE ID_DOCRETROINSUMO = ?",
                pk.getIdDocretroinsumo());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_DOCRETROINSUMO";
    }

    /**
     * Returns all rows from the FECET_DOCRETROINSUMO table that match the
     * criteria 'ID_DOCRETROINSUMO = :idDocretroinsumo'.
     */
    public FecetDocretroinsumo findByPrimaryKey(BigDecimal idDocretroinsumo) {

        List<FecetDocretroinsumo> list = getJdbcTemplateBase().query(
                SQL_SELECT + getTableName() + " WHERE ID_DOCRETROINSUMO = ?",
                new FecetDocretroinsumoMapper(), idDocretroinsumo);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECET_DOCRETROINSUMO table that match the
     * criteria ''.
     */
    public List<FecetDocretroinsumo> findAll() {

        return getJdbcTemplateBase().query(
                SQL_SELECT + getTableName() + " ORDER BY ID_DOCRETROINSUMO",
                new FecetDocretroinsumoMapper());

    }

    /**
     * Returns all rows from the FECET_DOCRETROINSUMO table that match the
     * criteria 'ID_RETROALIMENTACION_INSUMO = :idRetroalimentacionInsumo'.
     */
    public List<FecetDocretroinsumo> findByFecetRetroalimentacionInsumo(
            BigDecimal idRetroalimentacionInsumo, BigDecimal idTipoEmpleado) {

        return getJdbcTemplateBase().query(
                SQL_SELECT + getTableName()
                + " WHERE ID_RETROALIMENTACION_INSUMO = ? AND ID_TIPO_EMPLEADO = ?",
                new FecetDocretroinsumoMapper(), idRetroalimentacionInsumo, idTipoEmpleado);

    }

    /**
     * Returns all rows from the FECET_DOCRETROINSUMO table that match the
     * criteria 'ID_DOCRETROINSUMO = :idDocretroinsumo'.
     */
    public List<FecetDocretroinsumo> findWhereIdDocretroinsumoEquals(
            BigDecimal idDocretroinsumo) {

        return getJdbcTemplateBase()
                .query(SQL_SELECT
                        + getTableName()
                        + " WHERE ID_DOCRETROINSUMO = ? ORDER BY ID_DOCRETROINSUMO",
                        new FecetDocretroinsumoMapper(), idDocretroinsumo);

    }

    /**
     * Returns all rows from the FECET_DOCRETROINSUMO table that match the
     * criteria 'ID_RETROALIMENTACION_INSUMO = :idRetroalimentacionInsumo'.
     */
    public List<FecetDocretroinsumo> findWhereIdRetroalimentacionInsumoEquals(
            BigDecimal idRetroalimentacionInsumo) {

        return getJdbcTemplateBase()
                .query(SQL_SELECT
                        + getTableName()
                        + " WHERE ID_RETROALIMENTACION_INSUMO = ? ORDER BY ID_RETROALIMENTACION_INSUMO",
                        new FecetDocretroinsumoMapper(),
                        idRetroalimentacionInsumo);

    }

    /**
     * Returns all rows from the FECET_DOCRETROINSUMO table that match the
     * criteria 'RUTA_ARCHIVO = :rutaArchivo'.
     */
    public List<FecetDocretroinsumo> findWhereRutaArchivoEquals(
            String rutaArchivo) {

        return getJdbcTemplateBase().query(
                SQL_SELECT + getTableName()
                + " WHERE RUTA_ARCHIVO = ? ORDER BY RUTA_ARCHIVO",
                new FecetDocretroinsumoMapper(), rutaArchivo);

    }

    /**
     * Returns all rows from the FECET_DOCRETROINSUMO table that match the
     * criteria 'FECHA_CREACION = :fechaCreacion'.
     */
    public List<FecetDocretroinsumo> findWhereFechaCreacionEquals(
            Date fechaCreacion) {

        return getJdbcTemplateBase().query(
                SQL_SELECT + getTableName()
                + " WHERE FECHA_CREACION = ? ORDER BY FECHA_CREACION",
                new FecetDocretroinsumoMapper(), fechaCreacion);

    }

    /**
     * Returns the rows from the FECET_DOCRETROINSUMO table that matches the
     * specified primary-key value.
     */
    public FecetDocretroinsumo findByPrimaryKey(FecetDocretroinsumoPk pk) {
        return findByPrimaryKey(pk.getIdDocretroinsumo());
    }

    @Override
    public List<FecetDocretroinsumo> obtenerRetroalimentacionByTipoEmpleado(BigDecimal idRetroalimentacionInsumo,
            BigDecimal tipoEmpleado) {
        StringBuilder query = new StringBuilder(SQL_SELECT);
        query.append(getTableName());
        query.append("\n WHERE ID_RETROALIMENTACION_INSUMO = ?");
        query.append("\n AND ID_TIPO_EMPLEADO = ? ");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocretroinsumoMapper(), idRetroalimentacionInsumo, tipoEmpleado);
    }
}
