package mx.gob.sat.siat.feagace.modelo.dao.common.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ConsultaPropuestas;
import mx.gob.sat.siat.feagace.modelo.enums.TipoFechasComiteEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

public class ConsultaPropuestasMapper implements ParameterizedRowMapper<ConsultaPropuestas> {

    /**
     * Este atributo corresponde al nombre de la columna ID_PROPUESTA en la
     * tabla FECET_PROPUESTA
     */
    private static final String COLUMN_ID_PROPUESTA = "ID_PROPUESTA";

    /**
     * Este atributo corresponde al nombre de la columna ID_ARACE en la tabla
     * FECET_PROPUESTA
     */
    private static final String COLUMN_ID_ARACE = "ID_ARACE";

    /**
     * Este atributo corresponde al nombre de la columna ID_REGISTRO en la tabla
     * FECET_PROPUESTA
     */
    private static final String COLUMN_ID_REGISTRO = "ID_REGISTRO";

    /**
     * Este atributo corresponde al nombre de la columna RFC en la tabla
     * FECET_CONTRIBUYENTE
     */
    private static final String COLUMN_RFC = "RFC";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE en la tabla
     * FECET_CONTRIBUYENTE
     */
    private static final String COLUMN_NOMBRE_CONTRIBUYENTE = "NOMBRE_CONT";

    /**
     * Este atributo corresponde al nombre de la columna REGIMEN en la tabla
     * FECET_CONTRIBUYENTE
     */
    private static final String COLUMN_ESTATUS = "ESTATUS";

    /**
     * Este atributo corresponde al nombre de la columna ENTIDAD en la tabla
     * FECET_CONTRIBUYENTE
     */
    private static final String COLUMN_ENTIDAD = "ENTIDAD";

    /**
     * Este atributo corresponde al nombre de la columna TIPO_CONTRIBUYENTE en
     * la tabla FECET_CONTRIBUYENTE
     */
    private static final String COLUMN_TIPO_CONTRIBUYENTE = "TIPO_CONTRIBUYENTE";

    /**
     * Este atributo corresponde al nombre de la columna ACTIVIDAD_PREPONDERANTE
     * en la tabla FECET_CONTRIBUYENTE
     */
    private static final String COLUMN_ACTIVIDAD_PREPONDERANTE = "ACTIVIDAD_PREPONDERANTE";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_INFORME en la
     * tabla FECET_PROPUESTA
     */
    private static final String COLUMN_FECHA_INFORME = "FECHA_INFORME";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_PRESENTACION en
     * la tabla FECET_PROPUESTA, se pone esta fecha si la FECHA_INFORME es nula
     */
    private static final String COLUMN_FECHA_PRESENTACION = "FECHA_PRESENTACION";

    /**
     * Este atributo corresponde al nombre de la columna DESCRIPCION en la tabla
     * FECEC_MOTIVO
     */
    private static final String COLUMN_CAUSA = "CAUSA";

    /**
     * Este atributo corresponde al nombre de la columna REGIMEN en la tabla
     * FECET_CONTRIBUYENTE
     */
    private static final String COLUMN_REGIMEN = "REGIMEN";

    /**
     * Este atributo corresponde al nombre de la columna CLAVE en la tabla
     * FECEC_SUBPROGRAMA
     */
    private static final String COLUMN_CLAVE = "CLAVE";

    /**
     * Este atributo corresponde al nombre de la columna CLAVE en la tabla
     * FECEC_SUBPROGRAMA
     */
    private static final String COLUMN_SUBPROGRAMA_DESCRIPCION = "SUBPROGRAMA_DESCRIPCION";

    private static final String COLUMN_ID_PROGRAMADOR = "ID_PROGRAMADOR";

    public ConsultaPropuestas mapRow(ResultSet rs, int rowNum) throws SQLException {

        ConsultaPropuestas dto = new ConsultaPropuestas();
        BigDecimal idArace;

        dto.setIdPropuesta(rs.getBigDecimal(COLUMN_ID_PROPUESTA));
        dto.setIdRegistro(rs.getString(COLUMN_ID_REGISTRO));
        dto.setRfc(rs.getString(COLUMN_RFC));
        dto.setNombreContribuyente(rs.getString(COLUMN_NOMBRE_CONTRIBUYENTE));
        dto.setEstatus(rs.getString(COLUMN_ESTATUS));
        dto.setEntidad(rs.getString(COLUMN_ENTIDAD));
        dto.setTipoContribuyente(rs.getString(COLUMN_TIPO_CONTRIBUYENTE));
        dto.setActividadPreponderante(rs.getString(COLUMN_ACTIVIDAD_PREPONDERANTE));
        dto.setFechaInforme(rs.getDate(COLUMN_FECHA_INFORME));
        dto.setFechaPresentacion(rs.getDate(COLUMN_FECHA_PRESENTACION));
        dto.setCausaMotivo(rs.getString(COLUMN_CAUSA));
        dto.setRegimen(rs.getString(COLUMN_REGIMEN));
        dto.setIdProgramador(rs.getBigDecimal(COLUMN_ID_PROGRAMADOR));

        StringBuilder claveSubPrograma = new StringBuilder();
        claveSubPrograma.append(rs.getString(COLUMN_CLAVE));
        claveSubPrograma.append(". ");
        claveSubPrograma.append(rs.getString(COLUMN_SUBPROGRAMA_DESCRIPCION));
        dto.setSubprograma(claveSubPrograma.toString());

        idArace = rs.getBigDecimal(COLUMN_ID_ARACE);
        dto.setTipoComite(obtenerTipoComite(dto, idArace));

        return dto;
    }

    private TipoFechasComiteEnum obtenerTipoComite(ConsultaPropuestas consultaProp, BigDecimal idArace) {
        TipoFechasComiteEnum resultado = null;
        boolean regional = false;
        if (idArace != null) {
            regional = !Constantes.ACOECE.equals(idArace) && !Constantes.ACAOCE.equals(idArace);
        }
        if (consultaProp.getFechaInforme() != null && consultaProp.getFechaPresentacion() == null) {
            resultado = regional ? TipoFechasComiteEnum.FECHA_INFORME_COMITE_REGIONAL
                    : TipoFechasComiteEnum.FECHA_INFORME_COMITE;
        } else if (consultaProp.getFechaPresentacion() != null) {
            resultado = regional ? TipoFechasComiteEnum.FECHA_PRESENTACION_COMITE_REGIONAL
                    : TipoFechasComiteEnum.FECHA_PRESENTACION_COMITE;
        }

        return resultado;
    }

}
