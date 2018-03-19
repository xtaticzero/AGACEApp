/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.consulta.insumos;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.common.auditoria.constante.ConstantesAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoUnidadesAdminXGeneral;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.negocio.bo.consulta.ConsultaEjecutivaInsumosBO;
import mx.gob.sat.siat.feagace.negocio.consulta.insumos.filtro.FiltroConsultaInsumos;
import mx.gob.sat.siat.feagace.negocio.exception.NoSeGeneroReporteException;
import mx.gob.sat.siat.feagace.negocio.exception.consulta.ConsultaEjecutivaInsumosServiceException;
import mx.gob.sat.siat.feagace.negocio.util.constantes.SemaforoEnum;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public interface ConsultaEjecutivaInsumosService {

    String MSG_ERROR_REGISTRO_EMPLEADO = "registro.empleado";
    String MSG_ERROR_USUARIO_SIN_PRIVILEGIOS = "sin.privilegios";
    String MSG_ERROR_PARAMETRO_FILTRO = "parametros.filtro";
    String MSG_ERROR_PLAZOS = "inicializar.plazos";

    ConsultaEjecutivaInsumosBO getAccesoEmpleadoAConsultaInsumos(EmpleadoDTO empleado, Map<BigDecimal, GrupoUnidadesAdminXGeneral> mapGruposUnidades) throws ConsultaEjecutivaInsumosServiceException;

    ConsultaEjecutivaInsumosBO consultarInsumos(ConsultaEjecutivaInsumosBO consultaBO, FiltroConsultaInsumos filtroDeBusqueda) throws ConsultaEjecutivaInsumosServiceException;

    ConsultaEjecutivaInsumosBO getInsumosXSemaforoEstatus(ConsultaEjecutivaInsumosBO consultaBO, SemaforoEnum tipoSemaforo, TipoEstatusEnum tipoEstatus);

    ConsultaEjecutivaInsumosBO getInsumosPlazoXConcluir(ConsultaEjecutivaInsumosBO consultaBO, Integer numeroDias);

    ConsultaEjecutivaInsumosBO getInsumosXEmpleadoSemaforoEstadoPlazo(
            ConsultaEjecutivaInsumosBO consultaBO,
            List<AraceDTO> unidadesAdmin,
            EmpleadoDTO empleado,
            List<SemaforoEnum> lstSemaforos,
            List<TipoEstatusEnum> lstEstatus,
            Integer numeroDias);

    ConsultaEjecutivaInsumosBO consultaHistoricoInsumos(ConsultaEjecutivaInsumosBO consultaBO, FecetInsumo insumoAConsultar);
    
    void generarReporte(List<FecetInsumo> registros, Map<String, Object> parametros, OutputStream salida) throws NoSeGeneroReporteException;
    @PistaAuditoria(idOperacion=ConstantesAuditoria.CONSULTA_ESTATUS_INSUMO)
    String injectarPistaDetalleInsumo(FecetInsumo insumoAConsultar);
}
