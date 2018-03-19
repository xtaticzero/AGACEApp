/*
 * Copyright (c) 2013 Servicio de Administracion Tributaria (SAT)
 * Todos los derechos reservados.
 * Este software contiene información confidencial propiedad de
 * la Servicio de Administracion Tributaria (SAT)
 * Por lo cual no puede ser reproducido, distribuido o
 * alterado sin el consentimiento previo del Servicio de Administracion Tributaria (SAT).
 */
package mx.gob.sat.siat.feagace.negocio.propuestas.validar;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.common.auditoria.constante.ConstantesAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececCausaProgramacion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FecetDocumento;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoUnidadesAdminXGeneral;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececAdministrador;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.common.ImpuestoVO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetContadorInsumos;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocrechazoinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocretroinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRechazoInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRetroalimentacionInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;

/**
 * Servicios para validar procedimiento de insumos.
 *
 * @author Alfredo Ramirez - 749972
 * @version 1.0
 *
 */
public interface ValidarProcedenciaInsumoService {

    /**
     *
     * @param rfcSubadministrador
     * @param grupoUnidadesAdminXGeneral
     * @return
     */
    List<FecetInsumo> obtenerInsumos(final String rfcSubadministrador, Map<BigDecimal, GrupoUnidadesAdminXGeneral> grupoUnidadesAdminXGeneral);

    /**
     *
     * @param insumo
     * @return
     */
    @PistaAuditoria(idOperacion = ConstantesAuditoria.CONSULTAR_DETALLE_INSUMO_SUBADMINISTRADOR)
    List<FecetDocExpInsumo> obtenerDocumentos(FecetInsumo insumo);

    /**
     *
     * @param rechazoInsumo
     * @param archivo
     * @throws NegocioException
     */
    @PistaAuditoria(idOperacion = ConstantesAuditoria.INSUMO_RECHAZADO_SUBADMINISTRADOR)
    String rechazarInsumo(final FecetInsumo insumo, final FecetRechazoInsumo rechazoInsumo,
            final List<FecetDocrechazoinsumo> listaDocRechazoInsumo) throws NegocioException;

    @PistaAuditoria(idOperacion = ConstantesAuditoria.INSUMO_RECHAZADO_PLAZO_VENCIDO)
    String rechazarInsumoVencido(final FecetInsumo insumo, final FecetRechazoInsumo rechazoInsumo,
            final List<FecetDocrechazoinsumo> listaDocRechazoInsumo) throws NegocioException;

    /**
     *
     * @param retroalimentacion
     * @param listaDocRetroInsumo
     * @param archivo
     * @throws NegocioException
     * @param List<FecetDocretroinsumo>
     */
    @PistaAuditoria(idOperacion = ConstantesAuditoria.INSUMO_RETROALIMENTADO_SUBADMINISTRADOR)
    String retroalimentarInsumo(FecetRetroalimentacionInsumo retroalimentacion, List<FecetDocretroinsumo> listaDocRetroInsumo,
            FecetInsumo insumo, InputStream archivo, EmpleadoDTO empleadoDTO) throws NegocioException;

    List<FecetContadorInsumos> getInsumosRetroalimentacion(
            final BigDecimal idInsumo);

    /**
     *
     * @return @throws NegocioException
     */
    List<FececMetodo> getCatalogoMetodo();

    /**
     *
     * @param idRetroalimentacion
     * @return
     */
    List<FecetDocretroinsumo> getDocumentosRetroalimentados(
            final BigDecimal idRetroalimentacion);

    List<FececUnidadAdministrativa> getCatalogoUnidadInsumos();

    /**
     *
     * @return @throws NegocioException
     */
    List<FececTipoPropuesta> getCatalogoTipoPropuesta();

    /**
     *
     * @return
     */
    List<FececCausaProgramacion> getCatalogoCausaProgramacion();

    /**
     *
     * @return
     */
    List<FececRevision> getCatalogoRevision();

    /**
     *
     * @return
     */
    List<FececTipoImpuesto> getCatalogoImpuesto();

    /**
     *
     * @return @throws NegocioException
     */
    BigDecimal getConsecutivo();

    /**
     *
     * @return
     */
    BigDecimal getIdRegistroConsecutivo();

    /**
     *
     * @param dto
     * @return
     */
    BigDecimal insertarContribuyente(final FecetContribuyente dto);

    /**
     *
     * @param dto
     */
    void insertarPropuesta(final FecetPropuesta dto);

    /**
     *
     * @param dto
     */
    void insertarImpuesto(FecetImpuesto dto);

    /**
     *
     * @param dto
     */
    void insertarDocumento(FecetDocExpediente dto);

    /**
     *
     * @param idInsumo
     */
    void actualizarInsumoTerminado(final BigDecimal idInsumo);

    /**
     *
     * @param idAdministrador
     * @return
     */
    FececAdministrador getAdministradorById(final BigDecimal idAdministrador);

    /**
     *
     * @param idInsumo
     * @param propuesta
     * @param folio
     * @param listaImpuestos
     * @param listaDocumento
     * @throws NegocioException
     */
    void registrarPropuesta(final BigDecimal idInsumo, FecetPropuesta propuesta, final String folio,
            final List<ImpuestoVO> listaImpuestos,
            final List<FecetDocExpInsumo> listaDocumento) throws NegocioException;

    /**
     *
     * @return @throws NegocioException
     */
    BigDecimal getIdFecetRetroalimentacionInsumo();

    List<FecetDocretroinsumo> getDocumentosRetroalimentadosByIdRetroInsumo(
            final BigDecimal idRetroalimentacionInsumo, BigDecimal idTipoEmpleado);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.INSUMO_ACEPTADO_SUBADMINISTRADOR)
    String aceptarInsumo(final FecetInsumo insumo);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.INSUMO_ACEPTADO_PLAZO_VENCIDO)
    String aceptarInsumoVencido(final FecetInsumo insumo);

    
    List<FecetDocumento> obtenerDocumentosByIdInsumo(FecetInsumo insumo);
}
