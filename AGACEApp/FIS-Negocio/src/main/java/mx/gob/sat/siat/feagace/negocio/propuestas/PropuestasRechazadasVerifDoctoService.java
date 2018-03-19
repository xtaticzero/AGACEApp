package mx.gob.sat.siat.feagace.negocio.propuestas;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;

public interface PropuestasRechazadasVerifDoctoService {

    List<FecetDocExpediente> buscarDoctosRechazoPorIdRechazo(BigDecimal idRechazo);

    List<FecetDocExpediente> buscarDoctosOrdenProp(BigDecimal idPropuesta);

    List<FecetDocExpediente> buscarDoctosExpedienteProp(BigDecimal idPropuesta);

    List<FecetRechazoPropuesta> buscarRechazosPorIdPropuesta(BigDecimal idPropuesta, BigDecimal idArace, BigDecimal idTipoEmpleado);

    List<FecetAsociado> getRepresentanteLegal(BigDecimal idPropuesta);

    List<FecetAsociado> getAgenteAduanal(BigDecimal idPropuesta);

    void actualizaEstatusPropuesta(BigDecimal idPropuesta);

    void enviaCorreo(String asunto, String idRegistro, ReportType reportType, String correo);

    String obtenerToCorreo(String rfc);

    String obtenerToCorreoIdEmpleado(BigDecimal idEmpleado);

    BigDecimal obtenerIdArace(BigDecimal idEmpleado);
    
    BigDecimal obtenerIdEmpleado(String rfc); 
}
