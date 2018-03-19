/*
 * Copyright (c) 2013 Servicio de Administracion Tributaria (SAT)
 * Todos los derechos reservados.
 * Este software contiene información confidencial propiedad de
 * la Servicio de Administracion Tributaria (SAT)
 * Por lo cual no puede ser reproducido, distribuido o
 * alterado sin el consentimiento previo del Servicio de Administracion Tributaria (SAT).
 */
package mx.gob.sat.siat.feagace.negocio.propuestas;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.common.auditoria.constante.ConstantesAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececCausaProgramacion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececConcepto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececPrioridad;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececAdministrador;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleContribuyentePk;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ProgramadorPropuestaAsignadaDTO;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;

/**
 * Servicios para registrar una propuesta individual.
 *
 * @author Alfredo Ramirez - 749972
 * @version 1.0
 *
 */
public interface RegistrarPropuestaIndividualService {
    
    List<AraceDTO> getCatalogoUnidad();

    List<FececSubprograma> getCatalogoSubprograma(BigDecimal idGeneral);

    List<FececMetodo> getCatalogoMetodo();

    List<FececTipoPropuesta> getCatalogoTipoPropuesta();

    List<FececCausaProgramacion> getCatalogoCausaProgramacion();

    List<FececSector> getCatalogoSector(BigDecimal idGeneral);

    List<FececRevision> getCatalogoRevision();

    List<FececTipoImpuesto> getCatalogoImpuesto();

    BigDecimal getConsecutivo() throws NegocioException;

    BigDecimal getConsecutivoAnio(Date fechaInicio, Date fechaFinal) throws NegocioException;

    BigDecimal insertarContribuyente(final FecetContribuyente dto);

    FecetDetalleContribuyentePk insertarDetalleContribuyente(FecetDetalleContribuyente dto);

    FecetDetalleContribuyente findByRfc(String rfc);

    FecetContribuyente findContribuyente(BigDecimal idContribuyente);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.REGISTRO_PROPUESTA_INDIVIDUAL)
    BigDecimal insertarPropuestaInsumos(final FecetPropuesta dto);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.REGISTRO_PROPUESTA_INDIVIDUAL)
    BigDecimal insertarPropuestaPropuestas(final FecetPropuesta dto);

    List<ProgramadorPropuestaAsignadaDTO> asignarPropuesta(BigDecimal idTipoEmpleado, BigDecimal idCentral);

    void insertarImpuesto(FecetImpuesto dto);

    void insertarDocumento(FecetDocExpediente dto);

    BigDecimal getIdRegistroConsecutivo();

    FececAdministrador getAdministradorById(BigDecimal id);

    void nuevofolioCambioMetodo(BigDecimal folio, BigDecimal orden);

    void enviarNotificacionCorreoPropuesta(FecetPropuesta propuesta, String destinatario);

    void enviarNotificacionCorreoPropuesta(final FecetPropuesta propuesta, Set<String> destinatarios);

    void enviarNotificacionCorreoPropuesta(final FecetPropuesta propuesta, Set<String> destinatarios, String rfcSesion);

    List<FececMetodo> getCatalogoMetodoOrigen();

    EmpleadoDTO getDatosEmpleado(BigDecimal idEmpleado);

    List<EmpleadoDTO> getDatosEmpleadoCentral(BigDecimal idTipoEmpleado, BigDecimal idArace);

    List<FececPrioridad> getCatalogoPrioridad();

    List<FececConcepto> getCatalogoConcepto();

    List<FececConcepto> getConceptoByImpuesto(BigDecimal idImpuesto);

    EmpleadoDTO validarUsuarioEntrante(String rfcSesion);
}
