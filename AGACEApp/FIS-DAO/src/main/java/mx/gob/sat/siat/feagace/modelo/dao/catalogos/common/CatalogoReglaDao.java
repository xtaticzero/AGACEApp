/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common;

import java.math.BigDecimal;
import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.Regla;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public interface CatalogoReglaDao {

    String SQL_INSERT = "INSERT INTO FECEC_REGLA(ID_REGLA,CLAVE,DESCRIPCION,FECHA_INICIO,FECHA_FIN) \n"
            + "VALUES (FECEQ_REGLA.NEXTVAL,?,?,SYSDATE,?)";

    String SQL_DELETE = "UPDATE FECEC_REGLA \n"
            + "SET FECHA_FIN = SYSDATE\n"
            + "WHERE ID_REGLA = ?";

    String SQL_UPDATE = "UPDATE FECEC_REGLA \n"
            + "SET CLAVE = ?,\n"
            + "DESCRIPCION = ?,\n"
            + "FECHA_FIN = NULL\n"
            + "WHERE ID_REGLA = ?";

    String SQL_HEDER_FIND = "SELECT \n"
            + "REGLA.ID_REGLA       REG_ID_REGLA,\n"
            + "REGLA.CLAVE          REG_CLAVE,\n"
            + "REGLA.DESCRIPCION    REG_DESCRIPCION,\n"
            + "REGLA.FECHA_INICIO   REG_FECHA_INICIO,\n"
            + "REGLA.FECHA_FIN      REG_FECHA_FIN\n"
            + "FROM FECEC_REGLA REGLA";

    String SQL_WHERE = " WHERE ";

    String SQL_REGISTROS_ACTIVOS = " REGLA.FECHA_FIN IS NULL ";

    String SQL_PARAMETER_CLAVE = " REGLA.CLAVE = ? ";

    String SQL_PARAMETER_ID_REGLA = " REGLA.ID_REGLA = ? ";

    String SQL_ORDER_BY_IDREGLA = " ORDER BY REGLA.ID_REGLA ";

    String SQL_FIND_ALL = SQL_HEDER_FIND.concat(SQL_WHERE).concat(SQL_REGISTROS_ACTIVOS).concat(SQL_ORDER_BY_IDREGLA);

    String SQL_FIND_BY_CLAVE = SQL_HEDER_FIND.concat(SQL_WHERE).concat(SQL_PARAMETER_CLAVE);

    String SQL_FIND_BY_IDREGLA = SQL_HEDER_FIND.concat(SQL_WHERE).concat(SQL_PARAMETER_ID_REGLA);

    void insert(Regla regla);

    void delete(Regla regla);

    void update(Regla regla);

    List<Regla> findAll();

    Regla findByClave(String clave);

    Regla findByID(BigDecimal idRegla);
}
