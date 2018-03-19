/**
 * 
 */
package mx.gob.sat.siat.feagace.negocio.insumos;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.primefaces.model.UploadedFile;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.common.auditoria.constante.ConstantesAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.RegistroInsumosDto;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.ReporteIncorrectoDto;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ResumenCargaMasivaDTO;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.exception.NoSeGeneroReporteException;

/**
 * @author sergio.vaca
 *
 */
public interface CargaMasivaInsumosService {

    String getConsecutivoArchivo();

    @PistaAuditoria(idOperacion=ConstantesAuditoria.CARGA_MASIVA_INSUMOS_FOLIO_CARGA)
    String cargaDocumento(String destino, InputStream is, String nombreArchivo) throws NegocioException;

    void validarRegistros(UploadedFile archivoCarga, List<FecetInsumo> listRegistrosCorrectos, List<FecetInsumo> listRegistrosIncorrectos, Map<Integer,List<ReporteIncorrectoDto>> mapReporte, EmpleadoDTO empleadoSession, String rutaDocumetos) throws NegocioException;

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CARGA_MASIVA_INSUMO_ALTA_REGISTROS)
    String insertaResumen(ResumenCargaMasivaDTO resumen);

    RegistroInsumosDto insertarRegistrosMasivos(List<FecetInsumo> listRegistrosCorrectos);

    byte[] generarReporte(String rfcSession, Date fechaActual, String folioCarga, List<FecetInsumo> registros) throws NoSeGeneroReporteException;
    
    byte[] generarReporteExcel(String jasper, String nombreExcel, List<ReporteIncorrectoDto> listaErrores); 
    
}
