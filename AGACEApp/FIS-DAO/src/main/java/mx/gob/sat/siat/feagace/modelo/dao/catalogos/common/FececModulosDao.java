/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececModulos;

public interface FececModulosDao {

    String SQL_ORDER_BY = " ORDER BY MODULO.ID_MODULO";

    String SQL_INSERT = "INSERT INTO FECEC_MODULOS(ID_MODULO,NOMBRE,DESCRIPCION,FECHA,FECHA_BAJA) \n"
            + "VALUES ((SELECT (MAX(ID_MODULO)+1) ID_MODULO FROM FECEC_MODULOS),?,?,SYSDATE,NULL)";

    String SQL_UPDATE_HEADER = "UPDATE FECEC_MODULOS MODULO SET ";
    String SQL_UPDATE_FOOTER = " WHERE ID_MODULO = ? ";
    String SQL_PARAMETER_NAME = " MODULO.NOMBRE = ? ";
    String SQL_FIND_PARAMETER_NAME = " UPPER(MODULO.NOMBRE) = UPPER(?) ";
    String SQL_UPDATE_DESC = " MODULO.DESCRIPCION = ?";
    String SQL_UPDATE_FECHA_BAJA = " MODULO.FECHA_BAJA = ? ";

    String SQL_DELETE = "UPDATE FECEC_MODULOS MODUL\n"
            + "  SET MODUL.FECHA_BAJA = SYSDATE WHERE ID_MODULO = ?";

    String SQL_ACTIVOS = " WHERE MODULO.FECHA_BAJA IS NULL ";

    String SQL_SELECT = "SELECT MODULO.ID_MODULO,"
            + "MODULO.NOMBRE,"
            + "MODULO.DESCRIPCION,"
            + "MODULO.FECHA,MODULO.FECHA_BAJA "
            + "FROM FECEC_MODULOS MODULO ";

    String SQL_SELECT_FULL = SQL_SELECT.concat(SQL_ACTIVOS).concat(SQL_ORDER_BY);

    String SQL_SELECT_BY_ID = SQL_SELECT + " WHERE MODULO.ID_MODULO = ?";

    /**
     * Method 'insert'
     *
     * @param dto
     */
    void insert(FececModulos dto);

    /**
     * Updates a single row in the FECEC_MODULOS table.
     *
     * @param dto
     */
    void update(FececModulos dto);

    /**
     * Deletes a single row in the FECEC_MODULOS table.
     *
     * @param dto
     */
    void delete(FececModulos dto);

    /**
     * Returns all rows from the FECEC_MODULOS table that match the criteria
     * 'ID_MODULO = :idModulo'.
     *
     * @param idModulo
     * @return
     */
    FececModulos findByPrimaryKey(BigDecimal idModulo);

    /**
     * Returns all rows from the FECEC_MODULOS table that match the criteria ''.
     *
     * @return
     */
    List<FececModulos> findAll();

    /**
     * Metodo para obtener el modulo con el mismo nombre
     *
     * @param modulo
     * @return La concidencia del modulo por nombre
     */
    FececModulos getModuloXNombre(FececModulos modulo);

}
