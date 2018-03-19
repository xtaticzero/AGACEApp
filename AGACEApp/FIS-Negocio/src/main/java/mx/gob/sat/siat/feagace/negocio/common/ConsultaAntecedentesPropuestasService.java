package mx.gob.sat.siat.feagace.negocio.common;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.common.auditoria.constante.ConstantesAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuestoDescripcion;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;

public interface ConsultaAntecedentesPropuestasService {

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CONSULTA_ANTECEDENTES)
    List<FecetPropuesta> consultaSUIEFI(String rfc, Date periodoInicial, Date periodoFinal) throws NegocioException;

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CONSULTA_ANTECEDENTES)
    List<FecetPropuesta> consultaSICSEP(String rfc, Date periodoInicial, Date periodoFinal) throws NegocioException;

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CONSULTA_ANTECEDENTES)
    List<FecetPropuesta> consultaAGAFF(String rfc, Date periodoInicial, Date periodoFinal) throws NegocioException;

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CONSULTA_ANTECEDENTES)
    List<FecetPropuesta> consultaAGACE(String rfc, Date periodoInicial, Date periodoFinal) throws NegocioException;

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CONSULTA_ANTECEDENTES)
    List<FecetPropuesta> consultaAGACEPropuestas(String rfc, FecetPropuesta dto) throws NegocioException;

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CONSULTA_ANTECEDENTES)
    List<FecetPropuesta> consultaAGACEPropuestasPeriodoExacto(String rfc, FecetPropuesta dto) throws NegocioException;

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CONSULTA_ANTECEDENTES)
    List<FecetInsumo> consultaAGACEInsumos(String rfc, FecetPropuesta dto) throws NegocioException;

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CONSULTA_ANTECEDENTES)
    List<FecetInsumo> consultaAGACEInsumosPeriodoExacto(String rfc, FecetPropuesta dto) throws NegocioException;

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CREAR_EXCEL)
    HSSFWorkbook creaExcel(List<FecetPropuesta> revisionesAgace, List<FecetPropuesta> revisionesAgaff,
            List<FecetPropuesta> revisionesSUIEFIs, List<FecetPropuesta> revisionesSICSEP,
            List<FecetInsumo> revisionesInsumos, boolean muestraInsumo);

    String msgMediosContacto(String rfc);

    void getEmpleadoProgramador(String rfc) throws NegocioException;

    List<FecetImpuestoDescripcion> getImpuestos(BigDecimal idPropuesta);
}
