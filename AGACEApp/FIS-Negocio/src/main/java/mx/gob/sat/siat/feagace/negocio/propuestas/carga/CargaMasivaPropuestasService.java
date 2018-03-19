package mx.gob.sat.siat.feagace.negocio.propuestas.carga;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.common.auditoria.constante.ConstantesAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececPrioridad;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.CargaMasivaPropuestasDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ResumenCargaMasivaDTO;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;

public interface CargaMasivaPropuestasService {

    List<CargaMasivaPropuestasDTO> procesaCargaMasivaPropuestas(InputStream inputStream) throws NegocioException;

    BigDecimal validaSubprograma(String subprograma) throws NegocioException;

    BigDecimal validaTipoPropuesta(String tipoPropuestas) throws NegocioException;

    BigDecimal validaCausaProgramacion(BigDecimal causaProgramacion) throws NegocioException;

    BigDecimal validaCausaProgramacion(String causaProgramacion) throws NegocioException;

    BigDecimal validaMetodo(String metodo) throws NegocioException;

    BigDecimal validaSector(BigDecimal sector) throws NegocioException;

    BigDecimal validaSector(String sector) throws NegocioException;

    BigDecimal validaRevision(String revision) throws NegocioException;

    BigDecimal validaImpuesto(String impuesto) throws NegocioException;

    BigDecimal insertContribuyente(FecetContribuyente fecetContribuyente);

    BigDecimal insertPropuesta(CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO, EmpleadoDTO fececEmpleado);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CARGA_MASIVA_PROPUESTA_ALTA_REGISTROS)
    BigDecimal insertPropuestaMasiva(CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO, EmpleadoDTO fececEmpleado);

    boolean validaTipoRespuesta(String tipoRespuesta) throws NegocioException;

    boolean validaTransferencia(String tranferencia) throws NegocioException;

    FececSubprograma getSubprogramaById(BigDecimal idSubprograma);

    FececMetodo getMetodoById(BigDecimal idMetodo) throws NegocioException;

    boolean insertarDocumento(FecetDocExpediente dto);

    CargaMasivaPropuestasDTO validaRFC(CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CARGA_MASIVA_PROPUESTA_VALIDAR_COINCIDENCIA)
    CargaMasivaPropuestasDTO validaAntecedentes(CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CARGA_MASIVA_PROPUESTA_ALTA_REGISTROS)
    String insertaResumen(ResumenCargaMasivaDTO resumenDTO) throws NegocioException;

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CARGA_MASIVA_CARTAS_ALTA_REGISTROS)
    String insertaResumenCarta(ResumenCargaMasivaDTO resumenDTO) throws NegocioException;

    boolean validaConceptoImpuesto(String tipoImpuesto, String concepto);

    BigDecimal getidConcepto(String descripcionConcepto);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CARGA_MASIVA_PROPUESTA_CARGA_FORMATO)
    HSSFSheet obtenerHojaExcel(HSSFWorkbook workbook);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CARGA_MASIVA_CARTAS_CARGA_FORMATO)
    HSSFSheet obtenerHojaExcelCartas(HSSFWorkbook workbook);
    
    List<FececPrioridad> validaPrioridad(String prioridad);
}
