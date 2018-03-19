package mx.gob.sat.siat.feagace.negocio.insumos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.common.auditoria.constante.ConstantesAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FecetDocumento;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoUnidadesAdminXGeneral;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetContadorInsumos;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetContadorInsumosRechazados;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocrechazoinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocretroinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRechazoInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRetroalimentacionInsumo;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;

public interface SeguimientoInsumosService {

    List<FecetInsumo> getListaInsumoCreado() throws NegocioException;

    List<FecetInsumo> getListaInsumoPorRetroalimentar() throws NegocioException;

    List<FecetInsumo> getListaInsumoRechazado() throws NegocioException;
    
    List<FecetDocExpInsumo> obtenerDocumentos(BigDecimal idPropuesta);
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.CONSULTA_INSUMOS_ASIGNADOS)
    List<FecetDocExpInsumo> obtenerDocumentosAsignados(FecetInsumo insumo);
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.CONSULTAR_INSUMO_RECHAZADO)
    List<FecetDocExpInsumo> obtenerDocumentosRechazados(FecetInsumo insumo);
        
    List<FecetDocExpInsumo> obtenerDocumentosRtroalimentados(BigDecimal idPropuesta);
        
    FecetRechazoInsumo getFecetRechazoByInsumo(BigDecimal idPropuesta);

    FecetRetroalimentacionInsumo getRetroalimentacionInsumo(final BigDecimal idInsumo);

    void actualizaInsumoEstatus(FecetInsumo insumoSeleccionado) throws NegocioException;

    void actualizaRechazoInsumo(FecetRechazoInsumo fecetRechazoInsumo);
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.ATENDER_RETROALIMENTACION)
    String actualizaEstatusRetroalimentacionInsumo(final FecetInsumo insumo, final BigDecimal idRetroalimentacionInsumo, String motivoAciace, Date fechaRetro);

    void guardarDocumentosFaltantes(List<FecetDocretroinsumo> listaDocumento) throws NegocioException;

    List<FecetContadorInsumos>
            getInsumosPorRetroalimentar(final BigDecimal idInsumo, final BigDecimal tipoEmpleado);

    List<FecetContadorInsumosRechazados> getContadorRechazo(final BigDecimal idInsumo);
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.HISTORICO_RETROALIMENTACION_ACIACE)
    List<FecetRetroalimentacionInsumo> getInsumosRetroalimentados(final FecetInsumo fecetInsumo);
    
    List<FecetRetroalimentacionInsumo> getInsumosRetroalimentadosFlujoPrincipal(final FecetInsumo fecetInsumo);

    List<FecetDocretroinsumo> getDocumentosRetroalimentados(final BigDecimal idRetroalimentacion);

    List<FecetDocretroinsumo> getDocumentosRetroRechazos(final BigDecimal idRetroalimentacion);

    List<FecetDocrechazoinsumo> getDocumentosRechazados(final BigDecimal idRechazo);

    List<FecetInsumo> getListaSeguimientoInsumoEstatusRFCCreacion(BigDecimal estatus, String rfc, Map<BigDecimal, GrupoUnidadesAdminXGeneral> grupoUnidadesAdminXGeneral);

    List<FecetInsumo> getListaSeguimientoInsumoRFCCreacion(String rfc, Map<BigDecimal, GrupoUnidadesAdminXGeneral> grupoUnidadesAdminXGeneral,BigDecimal... estatus);
        
    List<FecetDocretroinsumo> getDocumentosRetroalimentadosEstatus(BigDecimal idRetroalimentacionInsumo,
            BigDecimal tipoEmpleado);

    boolean validarDuplicado(FecetInsumo insumoSeleccionado, String rfcContribuyente);

    List<FecetDocretroinsumo> getDocumentosRetroalimentadosByIdRetroInsumo(BigDecimal idRetroalimentacionInsumo,
            BigDecimal usuarioAciace);

    void actualizaInsumo(FecetInsumo insumoSeleccionado);
    
    List<FecetDocumento> buscarDocumentoJustificacion(FecetInsumo insumo);
}
