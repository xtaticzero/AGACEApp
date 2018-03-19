package mx.gob.sat.siat.feagace.negocio.insumos;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.common.auditoria.constante.ConstantesAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FecetDocumento;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoUnidadesAdminXGeneral;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.AdministradorReasignacion;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.ContadorInsumosSubAdmin;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;

public interface ConsultaAsignacionInsumoService {

    List<FecetInsumo> traeInsumosSinAsignar(final String rfcAdministrador, Map<BigDecimal, GrupoUnidadesAdminXGeneral> grupoUnidadesAdminXGeneral);

    @PistaAuditoria(idOperacion=ConstantesAuditoria.CONSULTAR_DETALLE_INSUMO_ADMINISTRADOR)
    List<FecetDocExpInsumo> traeExpedientesCargadosAdmon(final FecetInsumo insumo, final int tipo);
    
    List<FecetDocExpInsumo> traeExpedientesCargados(final BigDecimal idPropuesta, final int tipo);
    
    List<FecetDocumento> traeDocumentoJustificacionById(BigDecimal idInsumo);

    List<AdministradorReasignacion> getAdministradoresACPPCE(final EmpleadoDTO empleado);
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.ASIGNAR_INSUMO_SUBADMINISTRADOR)
    String guardarAsignacionSubadministrador(FecetInsumo insumo);
    
    List<ContadorInsumosSubAdmin> getContadorInsumos();

    List<ContadorInsumosSubAdmin> getContadorInsumos(List<EmpleadoDTO> list);
    
    List<EmpleadoDTO> getSubAdministradoresACPPCE(EmpleadoDTO empleado);    
    
}
