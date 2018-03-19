/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.common.impl;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetBitacoraDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.mapper.FecetBitacoraMapper;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetBitacora;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetBitacoraPk;

import org.springframework.stereotype.Repository;

@Repository("fecetBitacoraDao")
public class FecetBitacoraDaoImpl extends BaseJDBCDao<FecetBitacora> implements FecetBitacoraDao {

    @SuppressWarnings("compatibility:-5225730822911194494")
    private static final long serialVersionUID = 6484932043064169874L;

    private static final StringBuilder SQL_SELECT
            = new StringBuilder("SELECT ID_BITACORA, ID_OPERACION, ID_INTERNO, ID_REGISTRO, IPMAQUINA, NOMBREMAQUINA, RFC_USUARIO, RFC_APODERADO_LEGAL, RFC_AGENTE_ADUANAL, RFC_REPRESENTANTE_LEGAL, FECHA, DESCRIPCION FROM ");

    /**
     * Method 'getIdConsecutivo'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdConsecutivo() {

        return getJdbcTemplateBase().queryForObject("SELECT FECEQ_BITACORA.NEXTVAL FROM DUAL", BigDecimal.class);

    }

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetBitacoraPk
     */
    public FecetBitacoraPk insert(FecetBitacora dto) {

        if (dto.getIdBitacora() == null) {
            dto.setIdBitacora(getIdConsecutivo());
        }

        getJdbcTemplateBase().update("INSERT INTO " + getTableName()
                + " ( ID_BITACORA, ID_OPERACION, ID_INTERNO, ID_REGISTRO, IPMAQUINA, NOMBREMAQUINA, RFC_USUARIO, RFC_APODERADO_LEGAL, RFC_AGENTE_ADUANAL, RFC_REPRESENTANTE_LEGAL, FECHA, DESCRIPCION ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )",
                dto.getIdBitacora(), dto.getIdOperacion(), dto.getIdInterno(), dto.getIdRegistro(),
                dto.getIpmaquina(), dto.getNombremaquina(), dto.getRfcUsuario(),
                dto.getRfcApoderadoLegal(), dto.getRfcAgenteAduanal(), dto.getRfcRepresentanteLegal(),
                dto.getFecha(), dto.getDescripcion());
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECET_BITACORA table.
     */
    public void update(FecetBitacoraPk pk, FecetBitacora dto) {

        getJdbcTemplateBase().update("UPDATE " + getTableName()
                + " SET ID_BITACORA = ?, ID_OPERACION = ?, ID_INTERNO = ?, ID_REGISTRO = ?, IPMAQUINA = ?, NOMBREMAQUINA = ?, RFC_USUARIO = ?, RFC_APODERADO_LEGAL = ?, RFC_AGENTE_ADUANAL = ?, RFC_REPRESENTANTE_LEGAL = ?, FECHA = ?, DESCRIPCION = ? WHERE ID_BITACORA = ?",
                dto.getIdBitacora(), dto.getIdOperacion(), dto.getIdInterno(), dto.getIdRegistro(),
                dto.getIpmaquina(), dto.getNombremaquina(), dto.getRfcUsuario(),
                dto.getRfcApoderadoLegal(), dto.getRfcAgenteAduanal(), dto.getRfcRepresentanteLegal(),
                dto.getFecha(), dto.getDescripcion(), pk.getIdBitacora());

    }

    /**
     * Deletes a single row in the FECET_BITACORA table.
     */
    public void delete(FecetBitacoraPk pk) {

        getJdbcTemplateBase().update("DELETE FROM " + getTableName() + " WHERE ID_BITACORA = ?", pk.getIdBitacora());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_BITACORA";
    }

    /**
     * Returns all rows from the FECET_BITACORA table that match the criteria
     * 'ID_BITACORA = :idBitacora'.
     */
    public FecetBitacora findByPrimaryKey(BigDecimal idBitacora) {

        List<FecetBitacora> list
                = getJdbcTemplateBase().query(SQL_SELECT.append(getTableName()).append(" WHERE ID_BITACORA = ?").toString(),
                        new FecetBitacoraMapper(),
                        idBitacora);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECET_BITACORA table that match the criteria
     * ''.
     */
    public List<FecetBitacora> findAll() {

        return getJdbcTemplateBase().query(SQL_SELECT.append(getTableName()).append(" ORDER BY ID_BITACORA").toString(),
                new FecetBitacoraMapper());

    }

    /**
     * Returns all rows from the FECET_BITACORA table that match the criteria
     * 'ID_OPERACION = :idOperacion'.
     */
    public List<FecetBitacora> findByFeceaOperaciones(BigDecimal idOperacion) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(getTableName()).append(" WHERE ID_OPERACION = ?").toString(),
                new FecetBitacoraMapper(), idOperacion);

    }

    /**
     * Returns all rows from the FECET_BITACORA table that match the criteria
     * 'ID_BITACORA = :idBitacora'.
     */
    public List<FecetBitacora> findWhereIdBitacoraEquals(BigDecimal idBitacora) {

        return getJdbcTemplateBase().query(SQL_SELECT + getTableName() + " WHERE ID_BITACORA = ? ORDER BY ID_BITACORA",
                new FecetBitacoraMapper(), idBitacora);

    }

    /**
     * Returns all rows from the FECET_BITACORA table that match the criteria
     * 'ID_OPERACION = :idOperacion'.
     */
    public List<FecetBitacora> findWhereIdOperacionEquals(BigDecimal idOperacion) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(
                getTableName()).append(" WHERE ID_OPERACION = ? ORDER BY ID_OPERACION").toString(),
                new FecetBitacoraMapper(), idOperacion);

    }

    /**
     * Returns all rows from the FECET_BITACORA table that match the criteria
     * 'ID_INTERNO = :idInterno'.
     */
    public List<FecetBitacora> findWhereIdInternoEquals(final BigDecimal idInterno) {

        return getJdbcTemplateBase().query(
                SQL_SELECT.append(getTableName()).append(" WHERE ID_INTERNO = ? ORDER BY ID_INTERNO").toString(),
                new FecetBitacoraMapper(), idInterno);

    }

    /**
     * Returns all rows from the FECET_BITACORA table that match the criteria
     * 'ID_REGISTRO = :idRegistro'.
     */
    public List<FecetBitacora> findWhereIdRegistroEquals(BigDecimal idRegistro) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(
                getTableName()).append(" WHERE ID_REGISTRO = ? ORDER BY ID_REGISTRO").toString(),
                new FecetBitacoraMapper(), idRegistro);

    }

    /**
     * Returns all rows from the FECET_BITACORA table that match the criteria
     * 'IPMAQUINA = :ipmaquina'.
     */
    public List<FecetBitacora> findWhereIpmaquinaEquals(String ipmaquina) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(
                getTableName()).append(" WHERE IPMAQUINA = ? ORDER BY IPMAQUINA").toString(),
                new FecetBitacoraMapper(), ipmaquina);

    }

    /**
     * Returns all rows from the FECET_BITACORA table that match the criteria
     * 'NOMBREMAQUINA = :nombremaquina'.
     */
    public List<FecetBitacora> findWhereNombremaquinaEquals(String nombremaquina) {

        return getJdbcTemplateBase().query(
                SQL_SELECT.append(getTableName()).append(" WHERE NOMBREMAQUINA = ? ORDER BY NOMBREMAQUINA").toString(),
                new FecetBitacoraMapper(), nombremaquina);

    }

    /**
     * Returns all rows from the FECET_BITACORA table that match the criteria
     * 'RFC_USUARIO = :rfcUsuario'.
     */
    public List<FecetBitacora> findWhereRfcUsuarioEquals(String rfcUsuario) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(
                getTableName()).append(" WHERE RFC_USUARIO = ? ORDER BY RFC_USUARIO").toString(),
                new FecetBitacoraMapper(), rfcUsuario);

    }

    /**
     * Returns all rows from the FECET_BITACORA table that match the criteria
     * 'RFC_APODERADO_LEGAL = :rfcApoderadoLegal'.
     */
    public List<FecetBitacora> findWhereRfcApoderadoLegalEquals(String rfcApoderadoLegal) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(
                getTableName()).append(" WHERE RFC_APODERADO_LEGAL = ? ORDER BY RFC_APODERADO_LEGAL").toString(),
                new FecetBitacoraMapper(), rfcApoderadoLegal);

    }

    /**
     * Returns all rows from the FECET_BITACORA table that match the criteria
     * 'RFC_AGENTE_ADUANAL = :rfcAgenteAduanal'.
     */
    public List<FecetBitacora> findWhereRfcAgenteAduanalEquals(String rfcAgenteAduanal) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(
                getTableName()).append(" WHERE RFC_AGENTE_ADUANAL = ? ORDER BY RFC_AGENTE_ADUANAL").toString(),
                new FecetBitacoraMapper(), rfcAgenteAduanal);

    }

    /**
     * Returns all rows from the FECET_BITACORA table that match the criteria
     * 'RFC_REPRESENTANTE_LEGAL = :rfcRepresentanteLegal'.
     */
    public List<FecetBitacora> findWhereRfcRepresentanteLegalEquals(String rfcRepresentanteLegal) {

        return getJdbcTemplateBase().query(
                SQL_SELECT.append(getTableName()).append(" WHERE RFC_REPRESENTANTE_LEGAL = ? ORDER BY RFC_REPRESENTANTE_LEGAL").toString(),
                new FecetBitacoraMapper(), rfcRepresentanteLegal);

    }

    /**
     * Returns all rows from the FECET_BITACORA table that match the criteria
     * 'FECHA = :fecha'.
     */
    public List<FecetBitacora> findWhereFechaEquals(Date fecha) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(
                getTableName()).append(" WHERE FECHA = ? ORDER BY FECHA").toString(),
                new FecetBitacoraMapper(), fecha);

    }

    /**
     * Returns all rows from the FECET_BITACORA table that match the criteria
     * 'DESCRIPCION = :descripcion'.
     */
    public List<FecetBitacora> findWhereDescripcionEquals(final String descripcion) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(
                getTableName()).append(" WHERE DESCRIPCION = ? ORDER BY DESCRIPCION").toString(),
                new FecetBitacoraMapper(), descripcion);

    }

    /**
     * Returns the rows from the FECET_BITACORA table that matches the specified
     * primary-key value.
     */
    public FecetBitacora findByPrimaryKey(FecetBitacoraPk pk) {
        return findByPrimaryKey(pk.getIdBitacora());
    }
}
