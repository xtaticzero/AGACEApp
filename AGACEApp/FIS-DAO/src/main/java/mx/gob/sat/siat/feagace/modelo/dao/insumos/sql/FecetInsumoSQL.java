/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.insumos.sql;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public interface FecetInsumoSQL {

    String PARAMETRO_QUERY = "?";

    String COLUM_TOTAL = "TOTAL";

    String SELECT = "SELECT \n";

    String FROM = " FROM ";

    String TABLE_NAME = "FECET_INSUMO";

    String HEDER_DETALLE_INSUMO = SELECT
            + "INSUMO.ID_INSUMO ID_INSUMO,\n"
            + "INSUMO.ID_CONTRIBUYENTE ID_CONTRIBUYENTE,\n"
            + "INSUMO.ID_UNIDAD_ADMINISTRATIVA ID_UNIDAD_ADMINISTRATIVA,\n"
            + "INSUMO.ID_SUBPROGRAMA ID_SUBPROGRAMA,\n"
            + "INSUMO.ID_SECTOR ID_SECTOR,\n"
            + "INSUMO.ID_REGISTRO ID_REGISTRO,\n"
            + "INSUMO.FECHA_INICIO_PERIODO FECHA_INICIO_PERIODO,\n"
            + "INSUMO.FECHA_FIN_PERIODO FECHA_FIN_PERIODO,\n"
            + "INSUMO.PRIORIDAD PRIORIDAD,\n"
            + "INSUMO.FECHA_CREACION FECHA_CREACION,\n"
            + "INSUMO.FECHA_BAJA FECHA_BAJA,\n"
            + "INSUMO.RFC_CREACION RFC_CREACION,\n"
            + "INSUMO.RFC_ADMINISTRADOR RFC_ADMINISTRADOR,\n"
            + "INSUMO.RFC_SUBADMINISTRADOR RFC_SUBADMINISTRADOR,\n"
            + "INSUMO.ID_ESTATUS ID_ESTATUS,\n"
            + "INSUMO.ID_PRIORIDAD ID_PRIORIDAD,\n"
            + "INSUMO.FECHA_INICIO_PLAZO FECHA_INICIO_PLAZO,\n"
            + "CONTRIBUYENTE.RFC CONTRIBUYENTE_RFC,\n"
            + "CONTRIBUYENTE.NOMBRE CONTRIBUYENTE_NOMBRE,\n"
            + "CONTRIBUYENTE.REGIMEN CONTRIBUYENTE_REGIMEN,\n"
            + "CONTRIBUYENTE.SITUACION CONTRIBUYENTE_SITUACION,\n"
            + "CONTRIBUYENTE.TIPO CONTRIBUYENTE_TIPO,\n"
            + "CONTRIBUYENTE.SITUACION_DOMICILIO SITUACION_DOMICILIO,\n"
            + "CONTRIBUYENTE.DOMICILIO_FISCAL DOMICILIO_FISCAL,\n"
            + "CONTRIBUYENTE.ACTIVIDAD_PREPONDERANTE ACTIVIDAD_PREPONDERANTE,\n"
            + "CONTRIBUYENTE.ENTIDAD CONTRIBUYENTE_ENTIDAD,\n"
            + "PRIORIDAD.VALOR PRIORIDAD_VALOR,\n"
            + "PRIORIDAD.FECHA_INICIO PRIORIDAD_FECHA_INICIO,\n"
            + "PRIORIDAD.FECHA_FIN PRIORIDAD_FECHA_FIN,\n"
            + "PRIORIDAD.BLN_ACTIVO PRIORIDAD_BLN_ACTIVO,\n"
            + "ESTATUS.DESCRIPCION ESTATUS_DESCRIPCION,\n"
            + "ESTATUS.MODULO ESTATUS_MODULO,\n"
            + "ESTATUS.ID_MODULO ESTATUS_ID_MODULO,\n"
            + "SECTOR.DESCRIPCION SECTOR_DESCRIPCION,\n"
            + "SUBPROGRAMA.CLAVE SUBPROGRAMA_CLAVE,\n"
            + "SUBPROGRAMA.DESCRIPCION SUBPROGRAMA_DESCRIPCION,\n"
            + "DOC_EXP_INSUMO.ID_DOC_EXP_INSUMO DOC_INSUMO_ID,\n"
            + "DOC_EXP_INSUMO.RUTA_ARCHIVO DOC_INSUMO_RUTA_ARCHIVO,\n"
            + "DOC_EXP_INSUMO.FECHA_CREACION DOC_INSUMO_FECHA_CREACION,\n"
            + "DOC_EXP_INSUMO.FECHA_FIN DOC_INSUMO_FECHA_FIN,\n"
            + "DOC_EXP_INSUMO.BLN_ACTIVO DOC_INSUMO_BLN_ACTIVO\n"
            + "FROM FECET_INSUMO                       INSUMO                \n"
            + "INNER JOIN FECET_CONTRIBUYENTE          CONTRIBUYENTE         ON INSUMO.ID_CONTRIBUYENTE = CONTRIBUYENTE.ID_CONTRIBUYENTE\n"
            + "INNER JOIN FECEC_PRIORIDAD              PRIORIDAD             ON PRIORIDAD.ID_PRIORIDAD = INSUMO.ID_PRIORIDAD\n"
            + "INNER JOIN FECEC_ESTATUS                ESTATUS               ON INSUMO.ID_ESTATUS = ESTATUS.ID_ESTATUS\n"
            + "INNER JOIN FECEC_SECTOR                 SECTOR                ON SECTOR.ID_SECTOR = INSUMO.ID_SECTOR\n"
            + "INNER JOIN FECEC_SUBPROGRAMA            SUBPROGRAMA           ON SUBPROGRAMA.ID_SUBPROGRAMA = INSUMO.ID_SUBPROGRAMA\n"
            + "INNER JOIN FECET_DOC_EXP_INSUMO         DOC_EXP_INSUMO        ON INSUMO.ID_INSUMO = DOC_EXP_INSUMO.ID_INSUMO\n"
            + "WHERE 1=1 \n";

    String RFC_CREACION = "{RFC_CREACION}";
    String ESTATUS_BLN_DOC_EXP_INSUMO = "{ESTATUS_BLN_DOC_EXP}";
    String FILTRO_RFC_CREACION = " AND INSUMO.RFC_CREACION = ".concat(RFC_CREACION);
    String FILTRO_RFC_CREACION_MULTIPLE = " AND INSUMO.RFC_CREACION IN ({RFC_CREACION}) ";
    String RFC_ADMINISTRADOR = "{RFC_ADMINISTRADOR}";
    String FILTRO_RFC_ADMINISTRADOR = " AND INSUMO.RFC_ADMINISTRADOR = ".concat(RFC_ADMINISTRADOR);
    String RFC_SUBADMINISTRADOR = "{RFC_SUBADMINISTRADOR}";
    String FILTRO_RFC_SUBADMINISTRADOR = " AND INSUMO.RFC_SUBADMINISTRADOR = ".concat(RFC_SUBADMINISTRADOR);
    String FILTRO_ESTATUS_DOC_EXP_INSUMO = " AND DOC_EXP_INSUMO.BLN_ACTIVO = {ESTATUS_BLN_DOC_EXP} ";

    String UNIDADES_ADMINISTRATIVAS = "{UNIDADES_ADMINISTRATIVAS}";
    String FILTRO_UNIDAD_ADMINISTRATIVA = " AND INSUMO.ID_UNIDAD_ADMINISTRATIVA IN (".concat(UNIDADES_ADMINISTRATIVAS).concat(")");
    String ESTATUS = "{ESTATUS}";
    String FILTRO_ESTATUS = " AND INSUMO.ID_ESTATUS IN (".concat(ESTATUS).concat(")");

    String FOOTER_DETALLE_INSUMO = " ORDER BY INSUMO.ID_INSUMO";

    String HEAD_CAMPO_RFC_ADMINISTRADOR = " INSUMO.RFC_ADMINISTRADOR RFC,\n";
    String HEAD_CAMPO_RFC_SUBADMINISTRADOR = " INSUMO.RFC_ADMINISTRADOR RFC,\n";
    String HEAD_CAMPO_RFC_CREACION = " INSUMO.RFC_ADMINISTRADOR RFC,\n";
    String HEAD_CAMPO_ID_ESTATUS = " INSUMO.ID_ESTATUS ESTATUS,\n";

    String CONDICION_RFC_ADMINISTRADOR = "AND INSUMO.RFC_ADMINISTRADOR = {RFC_ADMINISTRADOR} ";
    String CONDICION_RFC_SUBADMINISTRADOR = "AND INSUMO.RFC_SUBADMINISTRADOR = {RFC_SUBADMINISTRADOR} ";
    String CONDICION_RFC_CREACION = "AND INSUMO.RFC_CREACION = {RFC_CREACION} ";

    String HEADER_COUNT_INSUMOS = SELECT
            + "COUNT(*) TOTAL \n"
            + "FROM FECET_INSUMO INSUMO WHERE \n"
            + "1=1 \n"
            + "AND INSUMO.ID_ESTATUS IN (".concat(ESTATUS).concat(") \n");

    String CONDICION_UNIDAD_ADMINISTRATIVA = "AND INSUMO.ID_UNIDAD_ADMINISTRATIVA IN (".concat(UNIDADES_ADMINISTRATIVAS).concat(") \n");

    String SQL_RETROALIMENTACIONES_DE_INSUMO = SELECT
            + "RETRO_INSUMO.ID_RETROALIMENTACION_INSUMO ID_RETRO,\n"
            + "RETRO_INSUMO.ID_INSUMO ID_INSUMO,\n"
            + "RETRO_INSUMO.ID_MOTIVO ID_MOTIVO,\n"
            + "RETRO_INSUMO.MOTIVO_SUBADMINISTRADOR MOTIVO_SUBADMINISTRADOR,\n"
            + "RETRO_INSUMO.FECHA_CREACION RET_FECHA_CREACION,\n"
            + "RETRO_INSUMO.RFC_RETROALIMENTACION RET_RFC,\n"
            + "RETRO_INSUMO.FECHA_RETROALIMENTACION RET_FECHA_RETRO,\n"
            + "RETRO_INSUMO.ESTATUS RET_ESTATUS,\n"
            + "RETRO_INSUMO.RFC_RECHAZO RET_RFC_RECHAZO,\n"
            + "RETRO_INSUMO.FECHA_RECHAZO RET_FECHA_RECHAZO,\n"
            + "RETRO_INSUMO.DESCRIPCION_RECHAZO RET_DESC_RECHAZO,\n"
            + "RETRO_INSUMO.ID_UNIDAD_ADMINISTRATIVA RET_ID_UNIDAD_ADMIN,\n"
            + "RETRO_INSUMO.ID_SUBPROGRAMA RET_ID_SUBPROGRAMA,\n"
            + "RETRO_INSUMO.ID_SECTOR RET_ID_SECTOR,\n"
            + "RETRO_INSUMO.FECHA_INICIO_PERIODO RET_FECHA_INICIO_PERIODO,\n"
            + "RETRO_INSUMO.FECHA_FIN_PERIODO RET_FECHA_FIN_PERIODO,\n"
            + "RETRO_INSUMO.ID_PRIORIDAD RET_ID_PRIORIDAD,\n"
            + "RETRO_INSUMO.MOTIVO_ACIACE RET_MOTIVO_ACIACE,\n"
            + "PRIORIDAD.VALOR PRIORIDAD_VALOR,\n"
            + "PRIORIDAD.FECHA_INICIO PRIORIDAD_FECHA_INICIO,\n"
            + "PRIORIDAD.FECHA_FIN PRIORIDAD_FECHA_FIN,\n"
            + "PRIORIDAD.BLN_ACTIVO PRIORIDAD_BLN_ACTIVO,\n"
            + "MOTIVO.DESCRIPCION MOTIVO_DESCRIPCION,\n"
            + "MOTIVO.TIPO_MOTIVO TIPO_MOTIVO,\n"
            + "SECTOR.DESCRIPCION SECTOR_DESCRIPCION,\n"
            + "SUBPROGRAMA.CLAVE SUBPROGRAMA_CLAVE,\n"
            + "SUBPROGRAMA.DESCRIPCION SUBPROGRAMA_DESCRIPCION\n"
            + "FROM FECET_INSUMO INSUMO\n"
            + "INNER JOIN FECET_RETROALIMENTACION_INSUMO  RETRO_INSUMO ON RETRO_INSUMO.ID_INSUMO = INSUMO.ID_INSUMO\n"
            + "INNER JOIN FECEC_PRIORIDAD PRIORIDAD ON PRIORIDAD.ID_PRIORIDAD = INSUMO.ID_PRIORIDAD\n"
            + "LEFT  JOIN FECEC_MOTIVO MOTIVO ON RETRO_INSUMO.ID_MOTIVO = MOTIVO.ID_MOTIVO\n"
            + "INNER JOIN FECEC_PRIORIDAD              PRIORIDAD             ON PRIORIDAD.ID_PRIORIDAD = RETRO_INSUMO.ID_PRIORIDAD\n"
            + "INNER JOIN FECEC_SECTOR                 SECTOR                ON SECTOR.ID_SECTOR = RETRO_INSUMO.ID_SECTOR\n"
            + "INNER JOIN FECEC_SUBPROGRAMA            SUBPROGRAMA           ON SUBPROGRAMA.ID_SUBPROGRAMA = RETRO_INSUMO.ID_SUBPROGRAMA\n"
            + "WHERE 1=1\n"
            + "AND INSUMO.ID_INSUMO = ? \n"
            + "ORDER BY RET_FECHA_RETRO,ID_RETRO DESC";

    String SQL_RECHAZO_INSUMO = SELECT
            + " RECHAZO_INSUMO.ID_RECHAZO_INSUMO ID_RECHAZO_INSUMO,\n"
            + " INSUMO.ID_INSUMO ID_INSUMO,\n"
            + " RECHAZO_INSUMO.DESCRIPCION RECHAZO_INSUMO_DESCRIPCION,\n"
            + " RECHAZO_INSUMO.FECHA_RECHAZO RECHAZO_FECHA_RECHAZO,\n"
            + " RECHAZO_INSUMO.RFC_RECHAZO RECHAZO_RFC_RECHAZO,\n"
            + " DOC_RECHAZO.ID_DOCRECHAZOINSUMO DOC_RECHAZO_ID,\n"
            + " DOC_RECHAZO.RUTA_ARCHIVO DOC_RUTA_ARCHIVO,\n"
            + " DOC_RECHAZO.FECHA_CREACION DOC_FECHA_CREACION,\n"
            + " DOC_RECHAZO.FECHA_FIN DOC_FECHA_FIN,\n"
            + " DOC_RECHAZO.BLN_ACTIVO DOC_BLN_ACTIVO\n"
            + " FROM FECET_INSUMO INSUMO\n"
            + " INNER JOIN FECET_RECHAZO_INSUMO RECHAZO_INSUMO ON RECHAZO_INSUMO.ID_INSUMO = INSUMO.ID_INSUMO\n"
            + " INNER JOIN FECET_DOCRECHAZOINSUMO DOC_RECHAZO ON RECHAZO_INSUMO.ID_RECHAZO_INSUMO = DOC_RECHAZO.ID_RECHAZO_INSUMO\n"
            + " WHERE INSUMO.ID_INSUMO = ?";

    String SQL_ESTATUS_INSUMOS = "SELECT FECET_INSUMO.ID_INSUMO, FECET_INSUMO.ID_CONTRIBUYENTE, FECET_INSUMO.ID_UNIDAD_ADMINISTRATIVA,  FECET_INSUMO.ID_SUBPROGRAMA, FECET_INSUMO.ID_SECTOR, "
            + "\n FECET_INSUMO.ID_REGISTRO, FECET_INSUMO.FECHA_INICIO_PERIODO, FECET_INSUMO.FECHA_FIN_PERIODO,  FECET_INSUMO.PRIORIDAD,  FECET_INSUMO.FECHA_CREACION,  FECET_INSUMO.FECHA_BAJA,"
            + "\n FECET_INSUMO.RFC_CREACION,  FECET_INSUMO.RFC_ADMINISTRADOR,  FECET_INSUMO.RFC_SUBADMINISTRADOR,   FECET_INSUMO.ID_ESTATUS, FECET_INSUMO.ID_PRIORIDAD, FECET_INSUMO.FECHA_INICIO_PLAZO,FECET_INSUMO.ID_TIPO_INSUMO, FECET_INSUMO.JUSTIFICACION,"
            + "\n PRIORIDAD.VALOR VALOR_PRIORIDAD, PRIORIDAD.DESCRIPCION DESCRIPCION_PRIORIDAD,FECEC_SUBPROGRAMA.ID_SUBPROGRAMA,  FECEC_SUBPROGRAMA.CLAVE, "
            + "\n FECEC_SUBPROGRAMA.DESCRIPCION, FECEC_SUBPROGRAMA.DESCRIPCION AS DESCRIPCION_SUBPROGRAMA,  FECET_CONTRIBUYENTE.ID_CONTRIBUYENTE,  FECET_CONTRIBUYENTE.RFC,  "
            + "\n FECET_CONTRIBUYENTE.NOMBRE,  FECET_CONTRIBUYENTE.REGIMEN, FECET_CONTRIBUYENTE.SITUACION,  FECET_CONTRIBUYENTE.TIPO, FECET_CONTRIBUYENTE.SITUACION_DOMICILIO, FECET_CONTRIBUYENTE.DOMICILIO_FISCAL, "
            + "\n FECET_CONTRIBUYENTE.ACTIVIDAD_PREPONDERANTE,  FECET_CONTRIBUYENTE.ENTIDAD, FECEC_ESTATUS.ID_ESTATUS,   FECEC_ESTATUS.DESCRIPCION AS DESCRIPCION_ESTATUS,  FECEC_ESTATUS.MODULO,  FECEC_SECTOR.ID_SECTOR,"
            + "\n FECEC_SECTOR.DESCRIPCION AS DESCRIPCION_SECTOR, PRIORIDAD.ID_PRIORIDAD "
            + "\n FROM FECET_INSUMO "
            + "\n INNER JOIN FECET_CONTRIBUYENTE ON ( FECET_INSUMO.ID_CONTRIBUYENTE = FECET_CONTRIBUYENTE.ID_CONTRIBUYENTE) INNER JOIN   FECEC_SUBPROGRAMA ON (FECET_INSUMO.ID_SUBPROGRAMA = FECEC_SUBPROGRAMA.ID_SUBPROGRAMA) "
            + "\n INNER JOIN FECEC_ESTATUS ON (  FECET_INSUMO.ID_ESTATUS = FECEC_ESTATUS.ID_ESTATUS) INNER JOIN FECEC_SECTOR ON (   FECET_INSUMO.ID_SECTOR = FECEC_SECTOR.ID_SECTOR)"
            + "\n INNER JOIN FECEC_PRIORIDAD PRIORIDAD ON PRIORIDAD.ID_PRIORIDAD = FECET_INSUMO.ID_PRIORIDAD ";

    String SQL_SELECT_CONTRIBUYENTE = " SELECT P.ID_INSUMO, P.ID_CONTRIBUYENTE, P.ID_UNIDAD_ADMINISTRATIVA, P.ID_SUBPROGRAMA, P.ID_SECTOR, P.ID_REGISTRO, P.FECHA_INICIO_PERIODO, P.FECHA_FIN_PERIODO, P.PRIORIDAD, P.FECHA_CREACION, P.FECHA_BAJA, P.RFC_CREACION, P.RFC_ADMINISTRADOR, P.RFC_SUBADMINISTRADOR, P.ID_ESTATUS, "
            + "\n CONT.RFC, CONT.NOMBRE, CONT.REGIMEN, CONT.SITUACION, CONT.TIPO, CONT.SITUACION_DOMICILIO, CONT.DOMICILIO_FISCAL, CONT.ACTIVIDAD_PREPONDERANTE, CONT.ENTIDAD,  SP.CLAVE, SP.DESCRIPCION DESCRIPCION_SUBPROGRAMA, "
            + "\n SEC.DESCRIPCION, ESTATUS.MODULO, ESTATUS.DESCRIPCION DESCRIPCION_ESTATUS "
            + "\n FROM FECET_INSUMO P "
            + "\n INNER JOIN FECET_CONTRIBUYENTE CONT ON P.ID_CONTRIBUYENTE = CONT.ID_CONTRIBUYENTE "
            + "\n INNER JOIN FECEC_SUBPROGRAMA SP ON P.ID_SUBPROGRAMA = SP.ID_SUBPROGRAMA "
            + "\n INNER JOIN FECEC_SECTOR SEC ON P.ID_SECTOR = SEC.ID_SECTOR "
            + "\n INNER JOIN FECEC_ESTATUS ESTATUS ON P.ID_ESTATUS = ESTATUS.ID_ESTATUS ";

    String SQL_SELECT_INSUMO_CONTRIBUYENTE_SUBPROGRAMA_ESTATUS_SECTOR = " SELECT INSUMO.ID_INSUMO, INSUMO.ID_CONTRIBUYENTE, INSUMO.ID_UNIDAD_ADMINISTRATIVA, INSUMO.ID_SUBPROGRAMA, INSUMO.ID_SECTOR, INSUMO.ID_REGISTRO, INSUMO.FECHA_INICIO_PERIODO, "
            + "\n INSUMO.FECHA_FIN_PERIODO, INSUMO.PRIORIDAD, INSUMO.FECHA_CREACION, INSUMO.FECHA_BAJA, INSUMO.RFC_CREACION, INSUMO.RFC_ADMINISTRADOR, INSUMO.RFC_SUBADMINISTRADOR, INSUMO.ID_ESTATUS, INSUMO.ID_PRIORIDAD, INSUMO.FECHA_INICIO_PLAZO, INSUMO.ID_TIPO_INSUMO, INSUMO.JUSTIFICACION, "
            + "\n CONTRIBUYENTE.ID_CONTRIBUYENTE, CONTRIBUYENTE.RFC, CONTRIBUYENTE.NOMBRE, CONTRIBUYENTE.REGIMEN, CONTRIBUYENTE.SITUACION, CONTRIBUYENTE.TIPO, CONTRIBUYENTE.SITUACION_DOMICILIO, CONTRIBUYENTE.DOMICILIO_FISCAL, CONTRIBUYENTE.ACTIVIDAD_PREPONDERANTE, "
            + "\n CONTRIBUYENTE.ENTIDAD, SUBPROGRAMA.ID_SUBPROGRAMA, SUBPROGRAMA.DESCRIPCION, SUBPROGRAMA.CLAVE, ESTATUS.ID_ESTATUS, ESTATUS.DESCRIPCION DESCRIPCION_ESTATUS, ESTATUS.MODULO, SECTOR.ID_SECTOR, SECTOR.DESCRIPCION DESCRIPCION_SECTOR, FECEC_PRIORIDAD.VALOR VALOR_PRIORIDAD, FECEC_PRIORIDAD.DESCRIPCION DESCRIPCION_PRIORIDAD ,FECEC_TIPO_INSUMO.DESCRIPCION DESCRIPCION_TIPO_INSUMO ";

    String SQL_SELECT_INSUMO_CONTRIBUYENTE_SUBPROGRAMA_ESTATUS = SQL_SELECT_INSUMO_CONTRIBUYENTE_SUBPROGRAMA_ESTATUS_SECTOR
            + "\n FROM FECET_INSUMO INSUMO "
            + "\n INNER JOIN FECET_CONTRIBUYENTE CONTRIBUYENTE ON INSUMO.ID_CONTRIBUYENTE = CONTRIBUYENTE.ID_CONTRIBUYENTE "
            + "\n INNER JOIN FECEC_SUBPROGRAMA SUBPROGRAMA ON INSUMO.ID_SUBPROGRAMA = SUBPROGRAMA.ID_SUBPROGRAMA "
            + "\n INNER JOIN FECEC_ESTATUS ESTATUS ON INSUMO.ID_ESTATUS = ESTATUS.ID_ESTATUS "
            + "\n INNER JOIN FECEC_SECTOR SECTOR ON INSUMO.ID_SECTOR = SECTOR.ID_SECTOR "
            + "\n INNER JOIN FECEC_PRIORIDAD ON INSUMO.ID_PRIORIDAD = FECEC_PRIORIDAD.ID_PRIORIDAD "
            + "\n INNER JOIN FECEC_TIPO_INSUMO ON INSUMO.ID_TIPO_INSUMO = FECEC_TIPO_INSUMO.ID_TIPO_INSUMO ";

    String SQL_SELECT_INSUMOS_NO_ASIGNADOS = "SELECT FECET_INSUMO.ID_INSUMO, FECET_INSUMO.ID_CONTRIBUYENTE, FECET_INSUMO.ID_UNIDAD_ADMINISTRATIVA, FECET_INSUMO.ID_SUBPROGRAMA, FECET_INSUMO.ID_SECTOR, FECET_INSUMO.ID_REGISTRO, FECET_INSUMO.FECHA_INICIO_PERIODO, FECET_INSUMO.FECHA_FIN_PERIODO, "
            + "\n  FECET_INSUMO.PRIORIDAD, FECET_INSUMO.FECHA_CREACION, FECET_INSUMO.FECHA_BAJA, FECET_INSUMO.RFC_CREACION, FECET_INSUMO.RFC_ADMINISTRADOR, FECET_INSUMO.RFC_SUBADMINISTRADOR, FECET_INSUMO.ID_ESTATUS, FECET_INSUMO.ID_PRIORIDAD, FECEC_PRIORIDAD.VALOR VALOR_PRIORIDAD, FECEC_PRIORIDAD.DESCRIPCION DESCRIPCION_PRIORIDAD ,FECET_INSUMO.FECHA_INICIO_PLAZO, FECET_INSUMO.ID_TIPO_INSUMO, FECET_INSUMO.JUSTIFICACION, "
            + "\n FECEC_SUBPROGRAMA.CLAVE, FECEC_SUBPROGRAMA.DESCRIPCION, FECET_CONTRIBUYENTE.ID_CONTRIBUYENTE, FECET_CONTRIBUYENTE.RFC, FECET_CONTRIBUYENTE.NOMBRE, FECET_CONTRIBUYENTE.REGIMEN, FECET_CONTRIBUYENTE.SITUACION, "
            + "\n FECET_CONTRIBUYENTE.TIPO, FECET_CONTRIBUYENTE.SITUACION_DOMICILIO, FECET_CONTRIBUYENTE.DOMICILIO_FISCAL, FECET_CONTRIBUYENTE.ACTIVIDAD_PREPONDERANTE, FECET_CONTRIBUYENTE.ENTIDAD, "
            + "\n FECEC_SECTOR.ID_SECTOR, FECEC_SECTOR.DESCRIPCION DESCRIPCION_SECTOR, \n"
            + "\n FECEC_ESTATUS.DESCRIPCION AS DESCRIPCION_ESTATUS,  FECEC_ESTATUS.MODULO, REA.MOTIVO_RECHAZO, FECEC_TIPO_INSUMO.DESCRIPCION AS DESCRIPCION_TIPO_INSUMO  "
            + "\n FROM FECET_INSUMO ";

    String SQL_CONDICION = " INNER JOIN FECEC_SUBPROGRAMA ON FECEC_SUBPROGRAMA.ID_SUBPROGRAMA = FECET_INSUMO.ID_SUBPROGRAMA \n";
    String SQL_CONDICION2 = " INNER JOIN FECET_CONTRIBUYENTE ON FECET_CONTRIBUYENTE.ID_CONTRIBUYENTE = FECET_INSUMO.ID_CONTRIBUYENTE \n";
    String SQL_CONDICION3 = " INNER JOIN FECEC_SECTOR ON FECEC_SECTOR.ID_SECTOR = FECET_INSUMO.ID_SECTOR \n";
    String SQL_JOIN_PRIORIDAD = " INNER JOIN FECEC_PRIORIDAD ON FECEC_PRIORIDAD.ID_PRIORIDAD = FECET_INSUMO.ID_PRIORIDAD \n";
    String SQL_JOIN_ESTATUS = " INNER JOIN FECEC_ESTATUS ON  FECET_INSUMO.ID_ESTATUS = FECEC_ESTATUS.ID_ESTATUS \n ";
    String SQL_JOIN_TIPO_INSUMO = " INNER JOIN FECEC_TIPO_INSUMO ON FECET_INSUMO.ID_TIPO_INSUMO = FECEC_TIPO_INSUMO.ID_TIPO_INSUMO \n ";
    String SQL_JOIN_UA = " INNER JOIN FECEC_UNIDAD_ADMINISTRATIVA FUA ON FUA.ID_UNIDAD_ADMINISTRATIVA = FECET_INSUMO.ID_UNIDAD_ADMINISTRATIVA  \n";
    String SQL_JOIN_REASIGNACION = " LEFT JOIN FECET_REASIGNACION_INSUMO REA ON REA.ID_INSUMO = FECET_INSUMO.ID_INSUMO AND REA.BLN_ACTIVO=1 \n";

    String SQL_SELECT = "SELECT ID_INSUMO, ID_CONTRIBUYENTE, ID_UNIDAD_ADMINISTRATIVA, ID_SUBPROGRAMA, ID_SECTOR, ID_REGISTRO, FECHA_INICIO_PERIODO, FECHA_FIN_PERIODO, PRIORIDAD, FECHA_CREACION, FECHA_BAJA, RFC_CREACION, RFC_ADMINISTRADOR, RFC_SUBADMINISTRADOR, ID_ESTATUS, ID_PRIORIDAD, FECHA_INICIO_PLAZO, ID_TIPO_INSUMO, JUSTIFICACION ";

    String TOTAL_INSUMO_ADMINISTRADOR = " SELECT RFC_ADMINISTRADOR, COUNT(*) TOTAL_REGISTROS "
            + "\n FROM FECET_INSUMO "
            + "\n WHERE RFC_SUBADMINISTRADOR IS NULL "
            + "\n AND ID_ESTATUS IN (" + ESTATUS + ")) "
            + "\n AND RFC_ADMINISTRADOR IN (" + RFC_CREACION + ")"
            + "\n GROUP BY RFC_ADMINISTRADOR "
            + "\n ORDER BY TOTAL_REGISTROS ASC ";

    String SQL_INSUMO_ADMINISTRADOR_ENTIDAD = "SELECT \n"
            + "DISTINCT\n"
            + "P.ID_INSUMO, \n"
            + "P.ID_CONTRIBUYENTE, \n"
            + "P.ID_UNIDAD_ADMINISTRATIVA, \n"
            + "P.ID_SUBPROGRAMA, \n"
            + "P.ID_SECTOR, \n"
            + "P.ID_REGISTRO, \n"
            + "P.FECHA_INICIO_PERIODO, \n"
            + "P.FECHA_FIN_PERIODO, \n"
            + "P.PRIORIDAD, \n"
            + "P.FECHA_CREACION, \n"
            + "P.FECHA_BAJA, \n"
            + "P.RFC_CREACION, \n"
            + "P.RFC_ADMINISTRADOR, \n"
            + "P.RFC_SUBADMINISTRADOR, \n"
            + "P.ID_ESTATUS,\n"
            + "P.ID_PRIORIDAD,\n"
            + "P.ID_TIPO_INSUMO,\n"
            + "TI.DESCRIPCION AS TIPO_INSUMO_DESC,\n"
            + "FECEC_PRIORIDAD.VALOR AS VALOR_PRIORIDAD, \n"
            + "FECEC_PRIORIDAD.DESCRIPCION AS DESCRIPCION_PRIORIDAD, \n"
            + "P.FECHA_INICIO_PLAZO, \n"
            + "P.JUSTIFICACION, \n"
            + "CONT.RFC, \n"
            + "CONT.NOMBRE, \n"
            + "CONT.REGIMEN, \n"
            + "CONT.SITUACION, \n"
            + "CONT.TIPO, \n"
            + "CONT.SITUACION_DOMICILIO, \n"
            + "CONT.DOMICILIO_FISCAL, \n"
            + "CONT.ACTIVIDAD_PREPONDERANTE, \n"
            + "CONT.ENTIDAD,  \n"
            + "SP.CLAVE, \n"
            + "SP.DESCRIPCION DESCRIPCION_SUBPROGRAMA, \n"
            + "SEC.DESCRIPCION, \n"
            + "ESTATUS.MODULO, \n"
            + "ESTATUS.DESCRIPCION DESCRIPCION_ESTATUS\n"
            + "FROM FECET_INSUMO P \n"
            + "INNER JOIN FECET_CONTRIBUYENTE CONT ON P.ID_CONTRIBUYENTE = CONT.ID_CONTRIBUYENTE \n"
            + "INNER JOIN FECEC_SUBPROGRAMA SP ON P.ID_SUBPROGRAMA = SP.ID_SUBPROGRAMA \n"
            + "INNER JOIN FECEC_SECTOR SEC ON P.ID_SECTOR = SEC.ID_SECTOR \n"
            + "INNER JOIN FECEC_ESTATUS ESTATUS ON P.ID_ESTATUS = ESTATUS.ID_ESTATUS\n"
            + "INNER JOIN FECEC_PRIORIDAD ON P.ID_PRIORIDAD = FECEC_PRIORIDAD.ID_PRIORIDAD\n"
            + "INNER JOIN FECEC_TIPO_INSUMO TI ON P.ID_TIPO_INSUMO = TI.ID_TIPO_INSUMO\n"
            + "WHERE P.RFC_ADMINISTRADOR = ? \n"
            + "AND CONT.ENTIDAD = ? \n"
            + "AND P.ID_REGISTRO <> 'SIN FOLIO'\n"
            + "ORDER BY FECEC_PRIORIDAD.VALOR, P.FECHA_CREACION ASC";

    Object INSERT_RESUMEN_MASIVA = "INSERT INTO FECET_MASIVA (ID_MASIVA, ID_CARGA, TOTAL_REGISTROS, FECHA_CARGA, ID_ORIGEN) "
            + "VALUES(FECEQ_MASIVA.NEXTVAL, ?, ?, SYSDATE, ?)";
}
