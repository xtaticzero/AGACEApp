/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.ordenes;

import java.math.BigDecimal;
import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegatoAgenteAduanal;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexoPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosRechazoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionAgenteAduanal;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.AsociadoFuncionarioDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public interface SeguimientoOrden {

    List<AgaceOrden> cargarListaPorValidar(final BigDecimal idEmpleado) throws NegocioException;

    List<FecetPromocion> getPromocionContadorPruebasAlegatos(final AgaceOrden orden);

    List<FecetAlegato> getPruebasAlegatosPromocion(final BigDecimal idPromocion);

    List<FecetProrrogaOrden> getProrrogaPorOrdenEstatusPendienteAuditor(final BigDecimal idOrden);

    List<FecetProrrogaOrden> getHistoricoProrrogaOrden(final BigDecimal idOrden);

    List<FececMetodo> getOpcionesCambioMetodo();

    List<FecetDocProrrogaOrden> getDocumentacionProrrogaContribuyente(final BigDecimal idProrrogaOrden);

    List<FecetAnexosProrrogaOrden> getAnexosRechazoProrrogaOrden(final BigDecimal idProrrogaOrden);

    List<FecetOficio> getOficiosRechazados(final BigDecimal idOrden);

    List<FecetOficioAnexos> getAnexosOficioRechazo(final BigDecimal idOficio);

    List<FecetOficio> getOficiosPorOrdenEnProcesoOVencidos(final BigDecimal idOrden);

    List<FecetPromocionOficio> getPromocionContadorPruebasAlegatosOficio(final FecetOficio oficio);

    List<FecetOficio> getOficiosDependientesByIdEstatus(final BigDecimal idOficio, final BigDecimal idEstatus);

    List<FecetOficioAnexos> getAnexosOficio(final BigDecimal idOficio);

    List<FecetAnexosRechazoOficio> getAnexosRechazoOficio(final BigDecimal idRechazoOficio);

    List<FecetAnexosProrrogaOrden> getAnexosProrrogaOrdenHistorico(final BigDecimal idFlujoProrrogaOrden);

    List<FecetProrrogaOficio> getProrrogaPorOficioEstatusPendienteAuditor(final BigDecimal idOficio);

    List<FecetDocProrrogaOficio> getDocumentacionContribuyenteProrrogaOficio(final BigDecimal idProrrogaOficio);

    List<FecetProrrogaOrden> getProrrogasPendientesAprobPorOrden(BigDecimal idOrden);

    List<FecetAnexosProrrogaOficio> getAnexosRechazoProrrogaOficio(final BigDecimal idProrrogaOficio);

    List<FecetOficio> getOficiosDependientesFirmados(final BigDecimal idOficio);

    List<FecetOficio> getOficiosAdministrables(final BigDecimal idOrden);

    List<FecetPruebasPericiales> getPruebaPericialPorOrdenEstatusPendienteAuditor(final BigDecimal idOrden);

    List<FecetPruebasPericiales> getHistoricoPruebasPericiales(final BigDecimal idOrden);

    List<FecetDocPruebasPericiales> getDocumentacionPruebaPericialContribuyente(final BigDecimal idPruebasPericiales);

    List<FecetAnexoPruebasPericiales> getAnexosPruebasPericialesHistorico(final BigDecimal idFlujoPruebaPercial);

    List<FecetAnexoPruebasPericiales> getAnexosRechazoPruebasPericiales(final BigDecimal idPruebaPericial);

    List<FecetPromocionAgenteAduanal> getPromocionAgenteAduanalContadorPruebasAlegatos(BigDecimal idOrden);

    List<FecetAlegatoAgenteAduanal> getAlegatosAgenteAduanal(final BigDecimal idPromocion);
    
    List<EmpleadoDTO> obtenerJefeEnlace(BigDecimal tipoEmpleado, BigDecimal idArace);
    
    FecetPropuesta getPropuestaOrden( BigDecimal idPropuesta);
    
    List<AsociadoFuncionarioDTO> buscarAsociadoFuncionarioActivoByOrden(AsociadoFuncionarioDTO dto);
    
    void actualizarAsociadoFuncionario(AsociadoFuncionarioDTO dto);
    
    void insertarAsociadoFuncionario(AsociadoFuncionarioDTO dto, AgaceOrden orden);
    
    EmpleadoDTO obtenerDatosAuditor(String rfcAuditor);

}
