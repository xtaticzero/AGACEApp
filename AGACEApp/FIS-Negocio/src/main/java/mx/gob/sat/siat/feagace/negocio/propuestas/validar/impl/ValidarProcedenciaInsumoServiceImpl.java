/*
 * Copyright (c) 2013 Servicio de Administracion Tributaria (SAT)
 * Todos los derechos reservados.
 * Este software contiene información confidencial propiedad de
 * la Servicio de Administracion Tributaria (SAT)
 * Por lo cual no puede ser reproducido, distribuido o
 * alterado sin el consentimiento previo del Servicio de Administracion Tributaria (SAT).
 */
package mx.gob.sat.siat.feagace.negocio.propuestas.validar.impl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececCausaProgramacionDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececUnidadAdministrativaDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.insumos.FececTipoImpuestoDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FececRevisionDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FecetContribuyenteDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas.FececTipoPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.roles.FececAdministradorDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FeceaMetodoDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetImpuestoDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetInsumoDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.FecetDocExpInsumoDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.FecetDocrechazoinsumoDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.FecetDocretroinsumoDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.FecetRechazoInsumoDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.FecetRetroalimentacionInsumoDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocExpedienteDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececCausaProgramacion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;
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
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.ConstantesError;
import mx.gob.sat.siat.feagace.negocio.common.plazos.service.PlazosServiceInsumos;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.insumos.ServicioInsumosAbstract;
import mx.gob.sat.siat.feagace.negocio.propuestas.validar.ValidarProcedenciaInsumoService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtil;

/**
 * Servicios para validar procedimiento de insumos.
 *
 * @author Alfredo Ramirez - 749972
 * @version 1.0
 *
 */
@Service("validarProcedenciaInsumoService")
public class ValidarProcedenciaInsumoServiceImpl extends ServicioInsumosAbstract implements ValidarProcedenciaInsumoService {

    private static final long serialVersionUID = 5966723032603780566L;

    @Autowired
    private transient FecetPropuestaDao fecetPropuestaDao;
    @Autowired
    private transient FecetInsumoDao fecetInsumoDao;
    @Autowired
    private transient FecetDocExpedienteDao fecetDocExpedienteDao;
    @Autowired
    private transient FecetDocExpInsumoDao fecetDocExpInsumoDao;
    @Autowired
    private transient FecetRechazoInsumoDao fecetRechazoInsumoDao;
    @Autowired
    private transient FecetRetroalimentacionInsumoDao fecetRetroalimentacionInsumoDao;
    @Autowired
    private transient FececUnidadAdministrativaDao fececUnidadAdministrativaDao;
    @Autowired
    private transient FeceaMetodoDao feceaMetodoDao;
    @Autowired
    private transient FececTipoPropuestaDao fececTipoPropuestaDao;
    @Autowired
    private transient FececCausaProgramacionDao fececCausaProgramacionDao;
    @Autowired
    private transient FececRevisionDao fececRevisionDao;
    @Autowired
    private transient FececTipoImpuestoDao fececTipoImpuestoDao;
    @Autowired
    private transient FecetImpuestoDao fecetImpuestoDao;
    @Autowired
    private transient FecetContribuyenteDao fecetContribuyenteDao;
    @Autowired
    private transient FececAdministradorDao fececAdministradorDao;
    @Autowired
    private transient FecetDocretroinsumoDao fecetDocretroinsumoDao;
    @Autowired
    private transient FecetDocrechazoinsumoDao fecetDocrechazoinsumoDao;
    @Autowired
    private transient PlazosServiceInsumos plazosInsumosService;

    private static final int ESTATUS_INSUMO_RECHAZADO = 11;

    /**
     *
     * @param rfcSubadministrador
     * @return
     * @throws NegocioException
     */
    @Override
    public List<FecetInsumo> obtenerInsumos(final String rfcSubadministrador, Map<BigDecimal, GrupoUnidadesAdminXGeneral> grupoUnidadesAdminXGeneral) {
        List<FecetInsumo> insumos = fecetInsumoDao.consultarInsumosContSubproEst(rfcSubadministrador);
        plazosInsumosService.inicializarInsumoConPlazos(insumos);
        llenarDetalleGrupoUnidadAdmin(grupoUnidadesAdminXGeneral, insumos);
        return insumos;
    }

    /**
     *
     * @param idInsumo
     * @return
     * @throws NegocioException
     */
    @Override
    @PistaAuditoria
    public List<FecetDocExpInsumo> obtenerDocumentos(FecetInsumo insumo) {
        plazosInsumosService.inicializarPlazo(insumo.getIdInsumo());
        List<FecetDocExpInsumo> documentos = fecetDocExpInsumoDao.findWhereIdInsumoEquals(insumo.getIdInsumo());
        for (FecetDocExpInsumo fecetDocExpInsumo : documentos) {
            fecetDocExpInsumo.setIdRegistroInsumo(insumo.getIdRegistro());
        }
        return documentos;
    }

    /**
     *
     * @param rechazoInsumo
     * @param archivo
     * @throws NegocioException
     */
    @Override
    @PistaAuditoria
    public String rechazarInsumo(final FecetInsumo insumo, final FecetRechazoInsumo rechazoInsumo, final List<FecetDocrechazoinsumo> listaDocRechazoInsumo)
            throws NegocioException {
        funcionRechazarInsumo(insumo, rechazoInsumo, listaDocRechazoInsumo);
        return insumo.getIdRegistro();
    }

    @Override
    @PistaAuditoria
    public String rechazarInsumoVencido(final FecetInsumo insumo, final FecetRechazoInsumo rechazoInsumo, final List<FecetDocrechazoinsumo> listaDocRechazoInsumo)
            throws NegocioException {
        funcionRechazarInsumo(insumo, rechazoInsumo, listaDocRechazoInsumo);
        return insumo.getIdRegistro();
    }

    private void funcionRechazarInsumo(final FecetInsumo insumo, final FecetRechazoInsumo rechazoInsumo, final List<FecetDocrechazoinsumo> listaDocRechazoInsumo)
            throws NegocioException {
        try {
            final BigDecimal idRechazoInsumo = fecetRechazoInsumoDao.insert(rechazoInsumo).getIdRechazoInsumo();
            fecetInsumoDao.actualizarEstatus(rechazoInsumo.getIdInsumo(), ESTATUS_INSUMO_RECHAZADO);

            for (FecetDocrechazoinsumo fecetDocrechazoinsumo : listaDocRechazoInsumo) {
                fecetDocrechazoinsumo.setIdRechazoInsumo(idRechazoInsumo);
                fecetDocrechazoinsumo.setRutaArchivo((RutaArchivosUtil.armarRutaDestinoInsumo(insumo) + "RechazoInsumo/" + "Rechazo_" + idRechazoInsumo + "/"));
                fecetDocrechazoinsumoDao.insert(fecetDocrechazoinsumo);
                CargaArchivoUtil.guardarArchivo(fecetDocrechazoinsumo.getArchivo(),
                        fecetDocrechazoinsumo.getRutaArchivo() + fecetDocrechazoinsumo.getNombreArchivo(), fecetDocrechazoinsumo.getNombreArchivo());
            }
        } catch (IOException e) {
            throw new NegocioException(ConstantesError.ERROR_GUARDAR_ARCHIVO, e);
        }
    }

    /**
     *
     * @param retroalimentacion
     * @param archivo
     * @throws NegocioException
     */
    @Override
    @PistaAuditoria
    public String retroalimentarInsumo(FecetRetroalimentacionInsumo retroalimentacion, List<FecetDocretroinsumo> listaDocRetroInsumo, FecetInsumo insumo,
            InputStream archivo, EmpleadoDTO empleadoDTO) throws NegocioException {
        try {
            retroalimentacion.setIdUnidadAdministrativa(insumo.getIdArace());
            retroalimentacion.setIdSubprograma(insumo.getIdSubprograma());
            retroalimentacion.setFechaInicioPeriodo(insumo.getFechaInicioPeriodo());
            retroalimentacion.setFechaFinPeriodo(insumo.getFechaFinPeriodo());
            retroalimentacion.setIdPrioridad(insumo.getIdPrioridad());
            retroalimentacion.setIdSector(insumo.getFececSector().getIdSector());
            FecetInsumo insumoNuevo = fecetInsumoDao.findByPrimaryKey(insumo.getIdInsumo());
            plazosInsumosService.validarSuspensionPlazoRetroalimentar(insumoNuevo);
            fecetRetroalimentacionInsumoDao.insert(retroalimentacion);
            for (FecetDocretroinsumo fecetDocretroinsumo : listaDocRetroInsumo) {
                fecetDocretroinsumo.setTipoDocumento("1");
                fecetDocretroinsumo.setIdRetroalimentacionInsumo(retroalimentacion.getIdRetroalimentacionInsumo());
                fecetDocretroinsumo.setRutaArchivo((RutaArchivosUtil.armarRutaDestinoInsumo(insumo) + "RetroalimentacionesInsumo/" + "Retroalimentacion_"
                        + retroalimentacion.getIdRetroalimentacionInsumo() + "/"));
                fecetDocretroinsumoDao.insert(fecetDocretroinsumo, empleadoDTO);
                CargaArchivoUtil.guardarArchivo(fecetDocretroinsumo.getArchivo(), fecetDocretroinsumo.getRutaArchivo() + fecetDocretroinsumo.getNombreArchivo(),
                        fecetDocretroinsumo.getNombreArchivo());
            }

            fecetInsumoDao.actualizarEstatus(retroalimentacion.getIdInsumo(), Constantes.ENTERO_DOCE);

        } catch (IOException e) {
            throw new NegocioException(ConstantesError.ERROR_GUARDAR_ARCHIVO, e);
        }
        return insumo.getIdRegistro();
    }

    /**
     *
     * @param idInsumo
     * @return @throws NegocioException
     */
    @Override
    public List<FecetContadorInsumos> getInsumosRetroalimentacion(final BigDecimal idInsumo) {
        return fecetRetroalimentacionInsumoDao.getContadorInsumosRetroalimentados(idInsumo);
    }

    /**
     *
     * @param idRetroalimentacion
     * @return @throws NegocioException
     */
    /**
     *
     * @return @throws NegocioException
     */
    @Override
    public List<FececUnidadAdministrativa> getCatalogoUnidadInsumos() {

        return fececUnidadAdministrativaDao.getUnidadesParaAsignarPropuesta();

    }

    /**
     *
     * @return @throws NegocioException
     */
    @Override
    public List<FececMetodo> getCatalogoMetodo() {
        return feceaMetodoDao.findPropuestas();
    }

    /**
     *
     * @param idAdministrador
     * @return
     * @throws NegocioException
     */
    public FececAdministrador getAdministradorById(final BigDecimal idAdministrador) {

        return fececAdministradorDao.findByPrimaryKey(idAdministrador);

    }

    /**
     *
     * @return @throws NegocioException
     */
    @Override
    public List<FececTipoPropuesta> getCatalogoTipoPropuesta() {
        return fececTipoPropuestaDao.findAll();
    }

    /**
     *
     * @return
     */
    @Override
    public List<FececCausaProgramacion> getCatalogoCausaProgramacion() {
        return fececCausaProgramacionDao.findAll();
    }

    /**
     *
     * @return
     */
    @Override
    public List<FececRevision> getCatalogoRevision() {
        return fececRevisionDao.findAll();
    }

    /**
     *
     * @return @throws NegocioException
     */
    @Override
    public List<FececTipoImpuesto> getCatalogoImpuesto() {
        return fececTipoImpuestoDao.findAll();
    }

    /**
     *
     * @return @throws NegocioException
     */
    @Override
    public BigDecimal getConsecutivo() {
        return fecetPropuestaDao.getConsecutivo();
    }

    /**
     *
     * @return
     */
    @Override
    public BigDecimal getIdRegistroConsecutivo() {
        return fecetPropuestaDao.getIdRegistroConsecutivo();
    }

    /**
     *
     * @param dto
     * @return
     */
    @Override
    public BigDecimal insertarContribuyente(FecetContribuyente dto) {
        return fecetContribuyenteDao.insert(dto).getIdContribuyente();
    }

    /**
     *
     * @param dto
     */
    @Override
    public void insertarPropuesta(final FecetPropuesta dto) {
        fecetPropuestaDao.insert(dto);
    }

    /**
     *
     * @param dto
     */
    @Override
    public void insertarImpuesto(FecetImpuesto dto) {
        fecetImpuestoDao.insert(dto).getIdImpuesto();
    }

    /**
     *
     * @param dto
     * @throws NegocioException
     */
    @Override
    public void insertarDocumento(FecetDocExpediente dto) {
        fecetDocExpedienteDao.insert(dto);
    }

    /**
     *
     * @param idInsumo
     */
    @Override
    public void actualizarInsumoTerminado(final BigDecimal idInsumo) {
        fecetInsumoDao.actualizarEstatus(idInsumo, Constantes.INSUMO_ACEPTADO.intValue());
    }

    @Override
    @PistaAuditoria
    public String aceptarInsumo(final FecetInsumo insumo) {
        fecetInsumoDao.actualizarEstatusFechaAprobacion(insumo.getIdInsumo(), new Date(), Constantes.INSUMO_ACEPTADO.intValue());
        return insumo.getIdRegistro();
    }

    @Override
    @PistaAuditoria
    public String aceptarInsumoVencido(final FecetInsumo insumo) {
        fecetInsumoDao.actualizarEstatusFechaAprobacion(insumo.getIdInsumo(), new Date(), Constantes.INSUMO_ACEPTADO.intValue());
        return insumo.getIdRegistro();
    }

    /**
     *
     * @param idInsumo
     * @param propuesta
     * @param folio
     * @param listaImpuestos
     * @param listaDocumento
     * @throws NegocioException
     */
    public void registrarPropuesta(final BigDecimal idInsumo, FecetPropuesta propuesta, final String folio, final List<ImpuestoVO> listaImpuestos,
            final List<FecetDocExpInsumo> listaDocumento) throws NegocioException {

        final BigDecimal numero21BD = new BigDecimal(21L);

        BigDecimal idContri = this.insertarContribuyente(propuesta.getFecetContribuyente());
        propuesta.setIdContribuyente(idContri);
        propuesta.setIdRegistro(folio);
        propuesta.setIdEstatus(numero21BD);
        propuesta.setFechaCreacion(new Date());

        this.insertarPropuesta(propuesta);

        for (ImpuestoVO impuestoItem : listaImpuestos) {
            FecetImpuesto impuesto = new FecetImpuesto();
            impuesto.setIdPropuesta(propuesta.getIdPropuesta());
            impuesto.setIdTipoImpuesto(impuestoItem.getIdImpuesto());
            impuesto.setPeriodoInicial(impuestoItem.getFechaInicial());
            impuesto.setPeriodoFinal(impuestoItem.getFechaFin());
            impuesto.setMonto(impuestoItem.getMonto());
            this.insertarImpuesto(impuesto);
        }

        for (FecetDocExpInsumo docInsumo : listaDocumento) {
            docInsumo.setFechaCreacion(new Date());

            String rutaArchivo = RutaArchivosUtil.armarRutaDestinoPropuesta(propuesta);

            CargaArchivoUtil.duplicarDocumentoInsumoPropuesta(docInsumo, rutaArchivo);

            FecetDocExpediente docExpItem = new FecetDocExpediente();
            docExpItem.setIdPropuesta(propuesta.getIdPropuesta());
            docExpItem.setArchivo(docInsumo.getArchivo());
            docExpItem.setFechaCreacion(docInsumo.getFechaCreacion());
            docExpItem.setNombre(docInsumo.getNombre());
            docExpItem.setRutaArchivo(rutaArchivo);
            this.insertarDocumento(docExpItem);
        }
        this.actualizarInsumoTerminado(idInsumo);
    }

    public BigDecimal getIdFecetRetroalimentacionInsumo() {
        return fecetRetroalimentacionInsumoDao.getIdFecetRetroalimentacionInsumo();
    }

    public BigDecimal getIdFecetRechazoInsumo() {
        return fecetRetroalimentacionInsumoDao.getIdFecetRetroalimentacionInsumo();
    }

    @Override
    public List<FecetDocretroinsumo> getDocumentosRetroalimentados(BigDecimal idRetroalimentacion) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<FecetDocretroinsumo> getDocumentosRetroalimentadosByIdRetroInsumo(BigDecimal idRetroalimentacionInsumo, BigDecimal idTipoEmpleado) {
        // TODO Auto-generated method stub
        return fecetDocretroinsumoDao.findByFecetRetroalimentacionInsumo(idRetroalimentacionInsumo, idTipoEmpleado);
    }

}
