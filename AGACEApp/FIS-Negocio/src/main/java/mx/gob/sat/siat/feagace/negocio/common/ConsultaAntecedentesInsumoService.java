package mx.gob.sat.siat.feagace.negocio.common;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.common.auditoria.constante.ConstantesAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;

public interface ConsultaAntecedentesInsumoService {

    @PistaAuditoria(idOperacion = ConstantesAuditoria.VALIDAR_COINCIDENCIA)
    List<FecetPropuesta> consultaSUIEFI(String rfc, Date periodoInicial, Date periodoFinal) throws NegocioException;

    @PistaAuditoria(idOperacion = ConstantesAuditoria.VALIDAR_COINCIDENCIA)
    List<FecetPropuesta> consultaSICSEP(String rfc, Date periodoInicial, Date periodoFinal) throws NegocioException;

    @PistaAuditoria(idOperacion = ConstantesAuditoria.VALIDAR_COINCIDENCIA)
    List<FecetPropuesta> consultaAGAFF(String rfc, Date periodoInicial, Date periodoFinal) throws NegocioException;

    @PistaAuditoria(idOperacion = ConstantesAuditoria.VALIDAR_COINCIDENCIA)
    List<FecetPropuesta> consultaAGACE(String rfc, Date periodoInicial, Date periodoFinal) throws NegocioException;

    @PistaAuditoria(idOperacion = ConstantesAuditoria.VALIDAR_COINCIDENCIA)
    List<FecetPropuesta> consultaAGACEPropuestasPeriodoExacto(String rfc, FecetPropuesta dto) throws NegocioException;

    @PistaAuditoria(idOperacion = ConstantesAuditoria.VALIDAR_COINCIDENCIA)
    List<FecetInsumo> consultaAGACEInsumosPeriodoExacto(String rfc, FecetPropuesta dto) throws NegocioException;

}
