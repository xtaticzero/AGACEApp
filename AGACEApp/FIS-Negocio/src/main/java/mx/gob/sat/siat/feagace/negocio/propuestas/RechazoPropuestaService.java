package mx.gob.sat.siat.feagace.negocio.propuestas;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatus;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececEmpleado;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ConsultaInformeComiteRechazoPropuesta;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public interface RechazoPropuestaService {
    
    List <FececEstatus> construyeComboEstatus();
    List <ConsultaInformeComiteRechazoPropuesta> buscarRechazosPropuesta(String rfc, String idEntidad, String idActividadPreponderante, BigDecimal idEstatus, FececEmpleado empleado);
    HSSFWorkbook exportarRechazosPropuesta(List<ConsultaInformeComiteRechazoPropuesta> listaRechazosPropuesta);
}