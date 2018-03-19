package mx.gob.sat.siat.feagace.negocio.insumos;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.common.auditoria.constante.ConstantesAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetReasignacionInsumo;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;

public interface ReasignacionInsumosService {

    List<FecetReasignacionInsumo> getReasignacionInsumoByAdministradorEstatus(EmpleadoDTO empleado, BigDecimal estatus);
    
    List<FecetInsumo> getReasignacionInsumoByAdministradorEstatusExt(String administradorDestino,
                BigDecimal estatus);
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.REASIGNAR_INSUMO_ADMINISTRADOR)
    String insert(FecetReasignacionInsumo dto);

    void update(FecetReasignacionInsumo dto) throws NegocioException;
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.ACEPTAR_RECHAZAR_REASIGNACION_INSUMO)
    String aceptaReasignacion(FecetReasignacionInsumo fecetReasignacionInsumo, FecetInsumo fecetInsumo);
    
    @PistaAuditoria(idOperacion=ConstantesAuditoria.ACEPTAR_RECHAZAR_REASIGNACION_INSUMO)
    String rechazaReasignacionInsumo(FecetReasignacionInsumo fecetReasignacionInsumo, FecetInsumo fecetInsumo);

    FecetInsumo getReasignacionInsumoByInsumo(BigDecimal idDecimal);
}
