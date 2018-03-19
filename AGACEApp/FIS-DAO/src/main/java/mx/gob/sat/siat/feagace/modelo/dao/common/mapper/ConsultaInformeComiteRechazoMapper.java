package mx.gob.sat.siat.feagace.modelo.dao.common.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ConsultaInformeComiteRechazoPropuesta;

public class ConsultaInformeComiteRechazoMapper {

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
    private static final String COLUMN_NOMBRE = "NOMBRE";

    /**
     * Este atributo corresponde al nombre de la columna REGIMEN en la tabla
     * FECET_CONTRIBUYENTE
     */
    private static final String COLUMN_REGIMEN = "REGIMEN";

    /**
     * Este atributo corresponde al nombre de la columna CLAVE en la tabla
     * FECEC_SUBPROGRAMA
     */
    private static final String COLUMN_SUBPROGRAMA_CLAVE = "SUBPROGRAMA_CLAVE";

    /**
     * Este atributo corresponde al nombre de la columna CLAVE en la tabla
     * FECEC_SUBPROGRAMA
     */
    private static final String COLUMN_SUBPROGRAMA_DESCRIPCION = "SUBPROGRAMA_DESCRIPCION";

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

    public ConsultaInformeComiteRechazoPropuesta mapRow(ResultSet rs, int rowNum) throws SQLException {

        Date fechaInforme;

        ConsultaInformeComiteRechazoPropuesta dto = new ConsultaInformeComiteRechazoPropuesta();
        dto.setIdRegistro(rs.getString(COLUMN_ID_REGISTRO));
        dto.setRfc(rs.getString(COLUMN_RFC));
        dto.setNombre(rs.getString(COLUMN_NOMBRE));

        dto.setRegimen(rs.getString(COLUMN_REGIMEN));

        StringBuilder claveSubPrograma = new StringBuilder();
        claveSubPrograma.append(rs.getString(COLUMN_SUBPROGRAMA_CLAVE));
        claveSubPrograma.append(". ");
        claveSubPrograma.append(rs.getString(COLUMN_SUBPROGRAMA_DESCRIPCION));
        dto.setSubprograma(claveSubPrograma.toString());

        dto.setEntidad(rs.getString(COLUMN_ENTIDAD));
        dto.setTipoContribuyente(rs.getString(COLUMN_TIPO_CONTRIBUYENTE));
        dto.setActividadPreponderante(rs.getString(COLUMN_ACTIVIDAD_PREPONDERANTE));

        if (null != rs.getDate(COLUMN_FECHA_INFORME)) {
            fechaInforme = rs.getDate(COLUMN_FECHA_INFORME);
        } else {
            fechaInforme = rs.getDate(COLUMN_FECHA_PRESENTACION);
        }
        dto.setFechaInforme(fechaInforme);

        return dto;
    }
}
