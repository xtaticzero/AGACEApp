/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common;

import java.math.BigDecimal;
import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoUnidadesAdminXGeneral;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public interface CatalogoGrupoDeUnidadAdminDao {

    String SQL_INSERT = "INSERT INTO FECEA_GRUPO_UNIDAD_GENERAL(ID_GRUPO_UNIDAD_GENERAL,ID_UNIDAD_ADMINISTRATIVA,ID_GENERAL,ID_REGLA,ID_GRUPO,FECHA_INICIO)\n"
            + "VALUES (FECEQ_GRUPO_UNIDAD_GENERAL.NEXTVAL,?,?,?,?,SYSDATE)";

    String SQL_DELETE = "UPDATE FECEA_GRUPO_UNIDAD_GENERAL \n"
            + "SET FECHA_FIN = SYSDATE \n"
            + "WHERE ID_GRUPO_UNIDAD_GENERAL = ?";

    String SQL_ACTIVAR = "UPDATE FECEA_GRUPO_UNIDAD_GENERAL \n"
            + "SET FECHA_FIN = NULL \n"
            + "WHERE ID_GRUPO_UNIDAD_GENERAL = ?";

    String SQL_UPDATE = "UPDATE FECEA_GRUPO_UNIDAD_GENERAL \n"
            + "SET ID_GRUPO = ?,\n"
            + "ID_REGLA = ?,\n"
            + "ID_UNIDAD_ADMINISTRATIVA = ?,\n"
            + "FECHA_FIN = NULL\n"
            + "WHERE ID_GRUPO_UNIDAD_GENERAL = ? ";

    String SQL_HEDER_FIND = "SELECT \n"
            + "GRP_UNID.ID_GRUPO_UNIDAD_GENERAL GRP_UNID_ID,\n"
            + "GRP_UNID.ID_UNIDAD_ADMINISTRATIVA GRP_UNID_ID_UNIDAD_ADMIN,\n"
            + "GRP_UNID.ID_GENERAL GRP_UNID_ID_GENERAL,\n"
            + "GRP_UNID.ID_GRUPO GRP_UNID_ID_GRUPO,\n"
            + "GRP_UNID.ID_REGLA GRP_UNID_ID_REGLA,\n"
            + "GRP_UNID.FECHA_INICIO GRP_UNID_FECHA_INICIO,\n"
            + "GRP_UNID.FECHA_FIN GRP_UNID_FECHA_FIN,\n"
            + "GRUPO.ID_GRUPO GRUP_ID_GRUPO,\n"
            + "GRUPO.NOMBRE GRUP_NOMBRE,\n"
            + "GRUPO.DESCRIPCION GRUP_DESCRIPCION,\n"
            + "GRUPO.CENTRAL GRUP_CENTRAL,\n"
            + "GRUPO.FECHA_INICIO GRUP_FECHA_INICIO,\n"
            + "GRUPO.FECHA_FIN GRUP_FECHA_FIN,\n"
            + "REGLA.ID_REGLA REG_ID_REGLA,\n"
            + "REGLA.CLAVE REG_CLAVE,\n"
            + "REGLA.DESCRIPCION REG_DESCRIPCION,\n"
            + "REGLA.FECHA_INICIO REG_FECHA_INICIO,\n"
            + "REGLA.FECHA_FIN REG_FECHA_FIN\n"
            + "FROM FECEA_GRUPO_UNIDAD_GENERAL GRP_UNID\n"
            + "INNER JOIN FECEC_REGLA REGLA ON REGLA.ID_REGLA = GRP_UNID.ID_REGLA\n"
            + "INNER JOIN FECEC_GRUPO_ADMINISTRACION GRUPO ON GRUPO.ID_GRUPO = GRP_UNID.ID_GRUPO ";
    
    String SQL_LST_UNIDADES = "SELECT\n"
            + "GRP_UNID.ID_UNIDAD_ADMINISTRATIVA GRP_UNID_ID_UNIDAD_ADMIN\n"
            + "FROM FECEA_GRUPO_UNIDAD_GENERAL GRP_UNID\n"
            + "INNER JOIN FECEC_REGLA REGLA ON REGLA.ID_REGLA = GRP_UNID.ID_REGLA\n"
            + "INNER JOIN FECEC_GRUPO_ADMINISTRACION GRUPO ON GRUPO.ID_GRUPO = GRP_UNID.ID_GRUPO  \n"
            + "WHERE  GRP_UNID.ID_GENERAL = ?\n"            
            + "AND GRP_UNID.ID_GRUPO = ?\n"
            + "AND GRP_UNID.ID_REGLA = ?\n"
            + "AND GRP_UNID.FECHA_FIN IS NULL  \n"
            + "ORDER BY GRP_UNID.ID_GRUPO_UNIDAD_GENERAL,GRP_UNID.ID_GRUPO,GRP_UNID.ID_REGLA";
    
    String GRP_UNID_ID_UNIDAD_ADMIN = "GRP_UNID_ID_UNIDAD_ADMIN";

    String SQL_WHERE = " WHERE ";

    String SQL_AND = " AND ";

    String SQL_REGISTROS_ACTIVOS = " GRP_UNID.FECHA_FIN IS NULL ";

    String SQL_PARAMETER_ID_GRUPO = " GRP_UNID.ID_GRUPO = ? ";

    String SQL_PARAMETER_ID_UNIDAD_ADMIN = " GRP_UNID.ID_UNIDAD_ADMINISTRATIVA = ? ";

    String SQL_PARAMETER_ID_GENERAL = " GRP_UNID.ID_GENERAL = ? ";

    String SQL_PARAMETER_ID_REGLA = " GRP_UNID.ID_REGLA = ? ";

    String SQL_PARAMETER_ID_GRUPOUNIDADGENERAL = " GRP_UNID.ID_GRUPO_UNIDAD_GENERAL = ? ";

    String SQL_ORDER_BY_IDGRUPO_UNIDAD_GENERAL = " ORDER BY GRP_UNID.ID_GRUPO_UNIDAD_GENERAL,GRP_UNID.ID_GRUPO,GRP_UNID.ID_REGLA ";

    String SQL_CONDITION_COUNT = "REGISTROS";

    void insert(GrupoUnidadesAdminXGeneral grupo);

    void delete(GrupoUnidadesAdminXGeneral grupo);

    void update(GrupoUnidadesAdminXGeneral grupo);

    List<GrupoUnidadesAdminXGeneral> findByIdAdmGralIdGrupoIdRegla(BigDecimal idAdmGral, BigDecimal idGrupo, BigDecimal idRegla);

    boolean asignacionesActivas(BigDecimal idAdmGral, BigDecimal idGrupo, BigDecimal idRegla);
}
