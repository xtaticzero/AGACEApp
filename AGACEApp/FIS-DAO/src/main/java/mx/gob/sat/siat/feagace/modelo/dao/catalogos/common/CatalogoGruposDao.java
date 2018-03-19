/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common;

import java.math.BigDecimal;
import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoAdministracion;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public interface CatalogoGruposDao {

    String SQL_INSERT = "INSERT INTO FECEC_GRUPO_ADMINISTRACION(ID_GRUPO,NOMBRE,DESCRIPCION,CENTRAL,FECHA_INICIO) \n"
            + "VALUES (FECEQ_GRUPO_ADMINISTRACION.NEXTVAL,?,?,?,SYSDATE)";

    String SQL_DELETE = "UPDATE FECEC_GRUPO_ADMINISTRACION \n"
            + "SET FECHA_FIN = SYSDATE \n"
            + "WHERE ID_GRUPO = ?";

    String SQL_UPDATE = "UPDATE FECEC_GRUPO_ADMINISTRACION \n"
            + "SET NOMBRE = ?,DESCRIPCION = ?,CENTRAL = ?,  FECHA_FIN = NULL \n"
            + "WHERE ID_GRUPO = ?";

    String SQL_HEDER_FIND = "SELECT \n"
            + "GRUPO.ID_GRUPO GRUP_ID_GRUPO,\n"
            + "GRUPO.NOMBRE GRUP_NOMBRE,\n"
            + "GRUPO.DESCRIPCION GRUP_DESCRIPCION,\n"
            + "GRUPO.CENTRAL GRUP_CENTRAL,\n"
            + "GRUPO.FECHA_INICIO GRUP_FECHA_INICIO,\n"
            + "GRUPO.FECHA_FIN GRUP_FECHA_FIN\n"
            + "FROM FECEC_GRUPO_ADMINISTRACION GRUPO ";

    String SQL_WHERE = " WHERE ";

    String SQL_REGISTROS_ACTIVOS = " GRUPO.FECHA_FIN IS NULL ";

    String SQL_FIND_PARAMETER_NAME = " UPPER(GRUPO.NOMBRE) = UPPER(?) ";
    
    String SQL_PARAMETER_NOMBRE = " GRUPO.NOMBRE = ? ";

    String SQL_PARAMETER_ID_GRUPO = " GRUPO.ID_GRUPO = ? ";

    String SQL_ORDER_BY_IDGRUPO = " ORDER BY GRUPO.ID_GRUPO ";

    String SQL_FIND_ALL = SQL_HEDER_FIND.concat(SQL_WHERE).concat(SQL_REGISTROS_ACTIVOS).concat(SQL_ORDER_BY_IDGRUPO);

    String SQL_FIND_BY_NOMBRE = SQL_HEDER_FIND.concat(SQL_WHERE).concat(SQL_FIND_PARAMETER_NAME);

    String SQL_FIND_BY_IDGRUPO = SQL_HEDER_FIND.concat(SQL_WHERE).concat(SQL_PARAMETER_ID_GRUPO);

    void insert(GrupoAdministracion grupo);

    void delete(GrupoAdministracion grupo);

    void update(GrupoAdministracion grupo);

    List<GrupoAdministracion> findAll();

    GrupoAdministracion findByNombre(String nombre);

    GrupoAdministracion findByID(BigDecimal idGrupo);
}
