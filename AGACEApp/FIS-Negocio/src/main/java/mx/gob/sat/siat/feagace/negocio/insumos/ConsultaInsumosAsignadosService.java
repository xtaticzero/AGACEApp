package mx.gob.sat.siat.feagace.negocio.insumos;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.common.auditoria.constante.ConstantesAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FecetDocumento;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoUnidadesAdminXGeneral;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.ContadorAsignadosAdministradorEstado;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocretroinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.enums.ReglaEnum;
import mx.gob.sat.siat.feagace.negocio.exception.NoSeGeneroReporteException;

public interface ConsultaInsumosAsignadosService {

    boolean validarUnidadAdministrativaXRegla(EmpleadoDTO empleadoDto, ReglaEnum regla, List<GrupoUnidadesAdminXGeneral> lstGruposXRegla);

    Map<BigDecimal, GrupoUnidadesAdminXGeneral> obtenerMapGruposDeUnidades(EmpleadoDTO empleadoDto);

    void llenarDetalleGrupoUnidadAdmin(Map<BigDecimal, GrupoUnidadesAdminXGeneral> mapGrupos, List<FecetInsumo> lstInsumos);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CONSULTAR_INSUMOS_ADMINISTRADORES)
    List<ContadorAsignadosAdministradorEstado> getContadorInsumosAsignados(EmpleadoDTO empleadoDto);

    List<FecetInsumo> getInsumosAsignadosAuditor(final String rfcAdministrador);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.CONSULTAR_LISTA_INSUMOS_ADMINISTRADORES)
    List<FecetInsumo> getInsumosAsignadosAuditorEntidad(String rfcAdministrador, String entidad, Map<BigDecimal, GrupoUnidadesAdminXGeneral> mapGrupos);

    List<FecetDocExpInsumo> getDocumentosPropuesta(final BigDecimal idPropuesta);

    void generarReporte(List<FecetInsumo> registros, OutputStream salida) throws NoSeGeneroReporteException;

    List<FecetDocretroinsumo> getDocumentosRetroalimentadosByIdRetroInsumo(BigDecimal idRetroalimentacionInsumo, BigDecimal usuarioAciace);
    
    List<FecetDocumento> obtenerDocumentosByIdInsumo(FecetInsumo insumo);
}
