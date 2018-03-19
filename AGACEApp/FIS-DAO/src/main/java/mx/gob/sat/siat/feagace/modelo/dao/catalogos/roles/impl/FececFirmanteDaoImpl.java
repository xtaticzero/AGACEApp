/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.roles.impl;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececFirmanteMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.roles.FececFirmanteDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececFirmante;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececFirmantePk;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

import org.springframework.stereotype.Repository;

@Repository("fececFirmanteDao")
public class FececFirmanteDaoImpl extends BaseJDBCDao<FececFirmante> implements FececFirmanteDao {

    /**
     * Este atributo es un SELECT para seleccionar los datos de la tabla
     * FECEC_FIRMANTE
     */
    private static final String SQL_SELECT
            = "SELECT ID_FIRMANTE, RFC, NOMBRE, FECHA_CREACION, FECHA_BAJA, ID_ARACE, CORREO FROM ";

    /**
     * Este atributo es una condicion WHERE para seleccionar los datos de
     * acuerdo a un ID_FIRMANTE
     */
    private static final String SQL_WHERE = " WHERE ID_FIRMANTE = ? ";

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececFirmantePk
     */
    public FececFirmantePk insert(FececFirmante dto) {

        dto.setIdFirmante(getJdbcTemplateBase().queryForObject("SELECT FECEQ_FIRMANTE.NEXTVAL FROM DUAL", BigDecimal.class));

        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(getTableName());
        query.append(" ( ID_FIRMANTE, RFC, NOMBRE, FECHA_CREACION, FECHA_BAJA, ID_ARACE, CORREO ) VALUES ( ?, ?, ?, ?, ?, ?, ? )");
        getJdbcTemplateBase().update(query.toString(),
                dto.getIdFirmante(), dto.getRfc(), dto.getNombre(), dto.getFechaCreacion(),
                dto.getFechaBaja(), dto.getIdArace(), dto.getCorreo());
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECEC_FIRMANTE table.
     */
    public void update(FececFirmantePk pk, FececFirmante dto) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(" SET ID_FIRMANTE = ?, RFC = ?, NOMBRE = ?, FECHA_CREACION = ?, FECHA_BAJA = ?, ID_ARACE = ?, CORREO = ? WHERE ID_FIRMANTE = ?");
        getJdbcTemplateBase().update(query.toString(),
                dto.getIdFirmante(), dto.getRfc(), dto.getNombre(), dto.getFechaCreacion(),
                dto.getFechaBaja(), dto.getIdArace(), dto.getCorreo(), pk.getIdFirmante());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECEC_FIRMANTE";
    }

    /**
     * Returns all rows from the FECEC_FIRMANTE table that match the criteria
     * 'ID_FIRMANTE = :idFirmante'.
     */
    public FececFirmante findByPrimaryKey(BigDecimal idFirmante) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(SQL_WHERE);
        List<FececFirmante> list
                = getJdbcTemplateBase().query(query.toString(), new FececFirmanteMapper(), idFirmante);
        return list.isEmpty() ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECEC_FIRMANTE table that match the criteria
     * ''.
     */
    public List<FececFirmante> findAll() {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" ORDER BY ID_FIRMANTE");
        return getJdbcTemplateBase().query(query.toString(),
                new FececFirmanteMapper());

    }

    /**
     * Returns all rows from the FECEC_FIRMANTE table that match the criteria
     * 'ID_FIRMANTE = :idFirmante'.
     */
    public List<FececFirmante> findWhereIdFirmanteEquals(BigDecimal idFirmante) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_FIRMANTE = ? ORDER BY ID_FIRMANTE");
        return getJdbcTemplateBase().query(query.toString(),
                new FececFirmanteMapper(), idFirmante);

    }

    /**
     * Returns all rows from the FECEC_FIRMANTE table that match the criteria
     * 'RFC = :rfc'.
     */
    public List<FececFirmante> findWhereRfcEquals(String rfc) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE RFC = ? ORDER BY RFC");
        return getJdbcTemplateBase().query(query.toString(),
                new FececFirmanteMapper(), rfc);

    }

    /**
     * Returns all rows from the FECEC_FIRMANTE table that match the criteria
     * 'NOMBRE = :nombre'.
     */
    public List<FececFirmante> findWhereNombreEquals(String nombre) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE NOMBRE = ? ORDER BY NOMBRE");
        return getJdbcTemplateBase().query(query.toString(),
                new FececFirmanteMapper(), nombre);

    }

    /**
     * Returns all rows from the FECEC_FIRMANTE table that match the criteria
     * 'FECHA_CREACION = :fechaCreacion'.
     */
    public List<FececFirmante> findWhereFechaCreacionEquals(Date fechaCreacion) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE FECHA_CREACION = ? ORDER BY FECHA_CREACION");
        return getJdbcTemplateBase().query(query.toString(), new FececFirmanteMapper(),
                fechaCreacion);

    }

    /**
     * Returns all rows from the FECEC_FIRMANTE table that match the criteria
     * 'FECHA_BAJA = :fechaBaja'.
     */
    public List<FececFirmante> findWhereFechaBajaEquals(Date fechaBaja) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE FECHA_BAJA = ? ORDER BY FECHA_BAJA");
        return getJdbcTemplateBase().query(query.toString(),
                new FececFirmanteMapper(), fechaBaja);

    }

    /**
     * Returns all rows from the FECEC_FIRMANTE table that match the criteria
     * 'ID_ARACE = :idArace'.
     */
    public List<FececFirmante> findWhereIdAraceEquals(BigDecimal idArace) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_ARACE = ? ORDER BY ID_ARACE");
        return getJdbcTemplateBase().query(query.toString(),
                new FececFirmanteMapper(), idArace);

    }

    /**
     * Returns all rows from the FECEC_FIRMANTE table that match the criteria
     * 'CORREO = :correo'.
     */
    public List<FececFirmante> findWhereCorreoEquals(String correo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE CORREO = ? ORDER BY CORREO");
        return getJdbcTemplateBase().query(query.toString(),
                new FececFirmanteMapper(), correo);

    }

    /**
     * Returns the rows from the FECEC_FIRMANTE table that matches the specified
     * primary-key value.
     */
    public FececFirmante findByPrimaryKey(FececFirmantePk pk) {
        return findByPrimaryKey(pk.getIdFirmante());
    }

    @Override
    public List<FececFirmante> findWhereFirmanteAuditor(BigDecimal idFirmante) {

        StringBuilder query = new StringBuilder();

        query.append("SELECT FECEC_EMPLEADO.ID_EMPLEADO AS ID_FIRMANTE, FECEC_EMPLEADO.RFC, FECEC_EMPLEADO.CORREO, FECEC_EMPLEADO.FECHA_CREACION, ");
        query.append("FECEC_EMPLEADO.FECHA_BAJA, FECEC_EMPLEADO.NOMBRE, FECET_DETALLE_EMPLEADO.ID_ARACE, FECEC_RELACION_EMPLEADO.ID_TIPO_EMPLEADO FROM FECEC_RELACION_EMPLEADO ");
        query.append("INNER JOIN FECEC_EMPLEADO ON FECEC_RELACION_EMPLEADO.ID_EMPLEADO = FECEC_EMPLEADO.ID_EMPLEADO ");
        query.append("INNER JOIN FECET_DETALLE_EMPLEADO ON FECEC_EMPLEADO.ID_EMPLEADO = FECET_DETALLE_EMPLEADO.ID_EMPLEADO ");
        query.append("WHERE FECEC_RELACION_EMPLEADO.ID_SUBORDINADO = ? ");
        query.append(" AND FECEC_RELACION_EMPLEADO.ID_TIPO_SUBORDINADO = ").append(Constantes.EMPLEADO_AUDITOR);
        query.append(" AND FECEC_RELACION_EMPLEADO.ID_SUBMODULO = ").append(Constantes.ENTERO_DOCE);
        query.append(" AND FECET_DETALLE_EMPLEADO.ID_TIPO_EMPLEADO = ").append(Constantes.USUARIO_FIRMANTE);

        return getJdbcTemplateBase().query(query.toString(), new FececFirmanteMapper(), idFirmante);
    }
}
