package mx.gob.sat.siat.feagace.negocio.propuestas;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececEmpleado;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ConsultaInformeComiteRechazoPropuesta;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public interface InformeComiteService {
        
    List<ConsultaInformeComiteRechazoPropuesta> buscarInformeComite(String rfc, String idEntidad, String idActividadPreponderante, FececEmpleado empleado, Date fechaInicio, Date fechaFin, String idRegistro);
    HSSFWorkbook exportarInformeComitePropuesta(List<ConsultaInformeComiteRechazoPropuesta> listaRechazosPropuesta);
}
