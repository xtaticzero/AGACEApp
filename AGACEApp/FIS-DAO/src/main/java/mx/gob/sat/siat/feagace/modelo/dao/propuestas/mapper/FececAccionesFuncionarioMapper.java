package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecebAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FececAccionesFuncionario;

public class FececAccionesFuncionarioMapper implements ParameterizedRowMapper<FecebAccionPropuesta> {

    private static final String COLUMN_ID_ACCION = "ID_ACCION";
    private static final String COLUMN_DESCRIPCION = "DESCRIPCION";
    private static final String COLUMN_ID_TIPO_EMPLEADO = "ID_TIPO_EMPLEADO";
    private static final String COLUMN_FECHA_INICIO = "FECHA_INICIO";
    private static final String COLUMN_FECHA_FIN = "FECHA_FIN";
    private static final String COLUMN_BLN_ESTATUS = "BLN_ESTATUS";

    @Override
    public FecebAccionPropuesta mapRow(ResultSet rs, int i) throws SQLException {
        FececAccionesFuncionario fececAccionesFuncionario = new FececAccionesFuncionario();
        FecebAccionPropuesta fecebAccionPropuesta = new FecebAccionPropuesta();

        fececAccionesFuncionario.setDescripcion(rs.getString(COLUMN_DESCRIPCION));
        fececAccionesFuncionario.setIdTipoEmpleado(rs.getBigDecimal(COLUMN_ID_TIPO_EMPLEADO));
        fececAccionesFuncionario.setFechaInicio(rs.getDate(COLUMN_FECHA_INICIO));
        fececAccionesFuncionario.setFechaFin(rs.getDate(COLUMN_FECHA_FIN));
        fececAccionesFuncionario.setBlnEstatus(rs.getInt(COLUMN_BLN_ESTATUS));

        if (UtileriasMapperDao.existeColumna(rs, "ACCION_ID_ACCION")) {
            fececAccionesFuncionario.setIdAccion(rs.getBigDecimal("ACCION_ID_ACCION"));
        } else {
            fececAccionesFuncionario.setIdAccion(rs.getBigDecimal(COLUMN_ID_ACCION));
        }

        fecebAccionPropuesta.setFececAccionesFuncionario(fececAccionesFuncionario);

        return fecebAccionPropuesta;
    }

}
