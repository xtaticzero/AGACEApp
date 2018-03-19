/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common;

import java.math.BigDecimal;
import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.ModuloUnidadAdmin;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public interface UnidadAdminModuloDao {

    String SQL_CONDITION_MODULO = "{MODULO}";

    String SQL_CONDITION_COUNT = "REGISTROS";

    String SQL_MODULO = " AND ADC_MOD.ID_MODULO = ? ";

    String SQL_INSERT = "INSERT INTO "
            + "FECEA_ADACE_MODULO(ID_ADACE_MODULO,"
            + "ID_GRUPO_ADMINISTRACION,"
            + "ID_MODULO,ID_GENERAL,"
            + "FECHA_INICIO,"
            + "FECHA_FIN,"
            + "BLN_ACTIVO) "
            + "VALUES(FECEQ_ADACE_MODULO.NEXTVAL,?,?,?,SYSDATE,NULL,?)";

    String SQL_SELECT_COUNT = "SELECT COUNT(ID_ADACE_MODULO) REGISTROS FROM FECEA_ADACE_MODULO WHERE ID_MODULO = ? \n"
            + "AND ID_GENERAL = ? AND ID_GRUPO_ADMINISTRACION = ?";

    String SQL_UPDATE = "UPDATE FECEA_ADACE_MODULO SET ID_MODULO = ?,ID_GRUPO_ADMINISTRACION=?,BLN_ACTIVO = ?,FECHA_FIN = ? WHERE ID_ADACE_MODULO = ?";

    String SQL_DELETE = "UPDATE FECEA_ADACE_MODULO SET BLN_ACTIVO = 0,FECHA_FIN=SYSDATE WHERE ID_ADACE_MODULO = ?";

    String SQL_FIND_ALL = "SELECT \n"
            + "ADC_MOD.ID_ADACE_MODULO,\n"
            + "ADC_MOD.ID_GRUPO_ADMINISTRACION,\n"
            + "ADC_MOD.ID_MODULO,\n"
            + "ADC_MOD.ID_GENERAL,\n"
            + "ADC_MOD.FECHA_INICIO,\n"
            + "ADC_MOD.FECHA_FIN,\n"
            + "ADC_MOD.BLN_ACTIVO,\n"
            + "MODULOS.NOMBRE,\n"
            + "MODULOS.DESCRIPCION,\n"
            + "MODULOS.FECHA,\n"
            + "MODULOS.FECHA_BAJA\n"
            + "FROM FECEA_ADACE_MODULO ADC_MOD\n"
            + "INNER JOIN FECEC_MODULOS MODULOS ON MODULOS.ID_MODULO = ADC_MOD.ID_MODULO\n"
            + "WHERE \n"
            + "ADC_MOD.ID_GENERAL = ? \n"
            + "{MODULO}"
            + "AND ADC_MOD.FECHA_FIN IS NULL\n"
            + "AND ADC_MOD.BLN_ACTIVO = 1\n"
            + "ORDER BY ADC_MOD.ID_ADACE_MODULO,ADC_MOD.ID_GRUPO_ADMINISTRACION";

    String SQL_ID_TABLE = "SELECT \n"
            + "ID_ADACE_MODULO\n"
            + "FROM FECEA_ADACE_MODULO \n"
            + "WHERE ID_MODULO = ? \n"
            + "AND ID_GENERAL = ? \n"
            + "AND ID_GRUPO_ADMINISTRACION = ? ";

    void insert(ModuloUnidadAdmin unidadModulo);

    void delete(ModuloUnidadAdmin unidadModulo);

    void update(ModuloUnidadAdmin unidadModulo);

    Integer count(ModuloUnidadAdmin unidadModulo);

    ModuloUnidadAdmin getIdModuloUnidad(ModuloUnidadAdmin unidadModulo);

    List<ModuloUnidadAdmin> findAll(BigDecimal idUnidadGeneral);

    List<ModuloUnidadAdmin> findByModulo(BigDecimal idUnidadGeneral, BigDecimal idModulo);
}
