package mx.gob.sat.siat.feagace.modelo.dao.propuestas;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ConsultaInformeComiteRechazoPropuesta;

public interface InformeComiteDao {

    List<ConsultaInformeComiteRechazoPropuesta> buscarInformeComite(String rfc, String idEntidad,
            String idActividadPreponderante, String condicionEmpleado, Date fechaInicio, Date fechaFin,
            String idRegistro);

}
