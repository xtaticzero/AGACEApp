package mx.gob.sat.siat.feagace.negocio.ordenes.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexoPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosRechazoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCambioMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.AsociadoFuncionarioDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.EstatusOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TiposOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.prorrogas.EstatusFlujoProrroga;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.prorrogas.EstatusProrroga;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.pruebaspericiales.EstatusFlujoPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.ConstantesError;
import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;
import mx.gob.sat.siat.feagace.negocio.bo.ordenes.rules.ReglasNegocioOrdenesBO;
import mx.gob.sat.siat.feagace.negocio.common.NotificacionService;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.ordenes.CargarFirmaPruebasPericialesService;
import mx.gob.sat.siat.feagace.negocio.ordenes.SeguimientoOrdenesListAbstract;
import mx.gob.sat.siat.feagace.negocio.ordenes.SeguimientoOrdenesService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.BusinessUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.SeguimientoOrdenesTipoGuardadoEnum;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;

@Service("seguimientoOrdenesService")
public class SeguimientoOrdenesServiceImpl extends SeguimientoOrdenesListAbstract implements SeguimientoOrdenesService {

    private static final long serialVersionUID = 4108842580255601351L;

    private static final BigDecimal AVISO_AIGNACION_CONSULTA = BigDecimal.valueOf(53);

    @Autowired
    private transient CargarFirmaPruebasPericialesService cargarFirmaPruebasPericialesService;

    @Autowired
    private transient NotificacionService notificacionService;

    @Transactional
    @Override
    public void guardarConclusionUCAMCA(final AgaceOrden orden, final List<FecetOficio> listaOfConclusionUCAMCA,
            final List<FecetOficioAnexos> listaAnexosConclusionUCAMCA) throws NegocioException {
        try {

            if (listaOfConclusionUCAMCA.isEmpty()) {
                throw new NegocioException("No se cargo el oficio de Conclusion");
            }

            FecetOficio oficio = listaOfConclusionUCAMCA.get(0);
            oficio.setIdOficio(getFecetOficioDao().getIdOficio());
            oficio.setDocumentoPdf("0");
            oficio.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoConclusionUCAMCA(orden, oficio));

            oficio.setSuspencionPlazo("0");

            ArchivoOrdenUtil.guardarArchivoOficio(oficio);

            String nombreArchivo = "", nombre = "", ruta = "";
            if (oficio.getRutaArchivo() != null) {
                nombreArchivo = oficio.getNombreArchivo();
                ruta = oficio.getRutaArchivo();
                oficio.setRutaArchivo(ruta.replace(nombreArchivo, "").concat(nombreArchivo));
            }

            if (oficio.getRutaAcuseNyv() != null) {
                nombre = oficio.getNombreAcuseNyv();
                ruta = oficio.getRutaAcuseNyv();
                oficio.setRutaAcuseNyv(ruta.replace(nombre, "").concat(nombre));
            }

            oficio.setIdAdminOficio(Constantes.ADMIN_OFICIO_111);
            oficio.getFecetTipoOficio().setBlnActivo(new BigDecimal(1L));
            oficio.setBlnActivo(true);

            getFecetOficioDao().insert(oficio);

            for (FecetOficioAnexos anexo : listaAnexosConclusionUCAMCA) {
                anexo.setIdOficioAnexos(this.getFecetOficioAnexosDao().getIdFecetOficioAnexosPathDirectorio());
                anexo.setIdOficio(oficio.getIdOficio());
                anexo.setRutaArchivo(RutaArchivosUtil.armarRutaAnexosOficio(oficio.getRutaArchivo().replace(nombreArchivo, "")));

                ArchivoOrdenUtil.guardarArchivoOficioAnexo(anexo);

                if (anexo.getRutaArchivo() != null) {
                    nombre = anexo.getNombreArchivo();
                    ruta = anexo.getRutaArchivo();
                    anexo.setRutaArchivo(ruta.replace(nombre, "").concat(nombre));
                }

                getFecetOficioAnexosDao().insert(anexo);
            }
            envioCorreoOficio(orden, oficio);
        } catch (IOException e) {
            logger.error(ConstantesError.ERROR_GUARDAR_ARCHIVO, e);
            throw new NegocioException(ConstantesError.ERROR_GUARDAR_ARCHIVO + e.getCause(), e);
        }
    }

    @Transactional
    @Override
    public void guardarCambioMetodoUCAMCA(ReglasNegocioOrdenesBO reglasNegocioOrdenesBO, FecetCambioMetodo fecetCambioMetodo) throws NegocioException {
        try {
            validaSeleccionNuevoCambioMetodo(fecetCambioMetodo.getIdMetodoNuevo());
            validaCargaCambioMetodo(reglasNegocioOrdenesBO.getListaOfPrincipal());

            FecetOficio oficio = reglasNegocioOrdenesBO.getListaOfPrincipal().get(0);
            oficio.setIdOficio(this.getFecetOficioDao().getIdOficio());
            oficio.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoCambioMetodoUCAMCA(reglasNegocioOrdenesBO.getOrden(),
                    oficio));
            oficio.setIdAdminOficio(Constantes.ADMIN_OFICIO_111);
            oficio.getFecetTipoOficio().setBlnActivo(new BigDecimal(1L));
            insertarOficioAnexosArchivos(oficio, reglasNegocioOrdenesBO.getListaAnexosPrincipal());

            fecetCambioMetodo.setFechaCreacion(oficio.getFechaCreacion());
            fecetCambioMetodo.setIdEstatus(Constantes.ESTATUS_CAMBIO_METODO_REGISTRADO);
            fecetCambioMetodo.setIdOficio(oficio.getIdOficio());
            fecetCambioMetodo.setIdOrdenOrigen(reglasNegocioOrdenesBO.getOrden().getIdOrden());

            this.getFecetCambioMetodoDao().insert(fecetCambioMetodo);
            envioCorreoOficioMetodo(reglasNegocioOrdenesBO.getOrden(), oficio, fecetCambioMetodo);
        } catch (NegocioException e) {
            logger.error(ERROR_NEGOCIO + e.getMessage());
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @Override
    @PistaAuditoria
    public BigDecimal guardar(SeguimientoOrdenesTipoGuardadoEnum tipoGuardado, ReglasNegocioOrdenesBO reglasNegocioOrdenesBO) throws NegocioException {
        int type = tipoGuardado.getTipoGuardado();

        switch (type) {
            case CASE_PRUEBAS_PERICIALES:
                guardarPruebasPericiales(reglasNegocioOrdenesBO);
                break;
            case CASE_SEGUNDO_REQUERIMIENTO:
                guardarSegundoRequerimiento(reglasNegocioOrdenesBO);
                break;
            case CASE_RESOLUCION_DEFINITIVA:
                guardarResolucionDefinitiva(reglasNegocioOrdenesBO);
                break;
            case CASE_PRUEBAS_DESAHOGO:
                guardarPruebasDesahogo(reglasNegocioOrdenesBO);
                break;
            case CASE_CONCLUSION_REVISION:
                guardarConclusionRevision(reglasNegocioOrdenesBO);
                break;
            case CASE_SIN_OBSERVACIONES:
                guardarSinObservaciones(reglasNegocioOrdenesBO);
                break;
            case CASE_CON_OBSERVACIONES:
                guardarConObservaciones(reglasNegocioOrdenesBO);
                break;
            case CASE_REQUERIMIENTO_REINCIDENCIA:
                guardarRequerimientoReincidencia(reglasNegocioOrdenesBO);
                break;
            case CASE_COMP_INTERNACIONAL:
                guardarCompInternacional(reglasNegocioOrdenesBO);
                break;
            case CASE_COMPULSA_TERCERO_OTRAS_AUTORIDADES:
                guardarCompulsaTerceroOtrasAutoridades(reglasNegocioOrdenesBO);
                break;
            case CASE_COMPULSA_OTRAS_AUTORIDADES:
                guardarCompulsaOtrasAutoridades(reglasNegocioOrdenesBO);
                break;
            case CASE_AVISO_CIRCUNSTANCIAL:
                guardarAvisoCircunstancial(reglasNegocioOrdenesBO);
                break;
            case CASE_MULTA:
                guardarMulta(reglasNegocioOrdenesBO);
                break;
            case CASE_AVISO_CONTRIBUYENTE:
                guardarAvisoContribuyenteOrden(reglasNegocioOrdenesBO);
                break;
            default:
                throw new NegocioException("Metodo de guardado no implementado");
        }
        return reglasNegocioOrdenesBO.getOrden().getId();
    }

    @Override
    public Date integrarExpediente(final AgaceOrden orden) {
        Calendar fechaDiaHabil = Calendar.getInstance();
        fechaDiaHabil.setTime(new Date());
        fechaDiaHabil.add(Calendar.DAY_OF_YEAR, 1);
        Date plazosFechaExp = getPlazosService().primerDiaHabil(fechaDiaHabil.getTime());
        getAgaceOrdenDao().integraExpediente(orden, plazosFechaExp);
        return plazosFechaExp;
    }

    @Override
    public void reactivaPlazoOficio(final BigDecimal idOrden, final BigDecimal idOficio) {
        getSuspensionDAO().reactivaPlazoOficio(idOrden, idOficio);
    }

    @Override
    public void reactivarPlazoAcuerdoConclusivo(final AgaceOrden orden) {
        getAgaceOrdenDao().reactivaPlazoAcuerdoConclusivo(orden);
    }

    @Override
    public List<FececMetodo> getOpcionesCambioMetodo() {
        return getFeceaMetodoDao().getOpcionesCambioMetodo();
    }

    @Override
    public List<FecetOficio> getOficiosPorOrdenEnProcesoOVencidos(final BigDecimal idOrden) {
        List<FecetOficio> oficios = getFecetOficioDao().getOficiosPorOrden(idOrden, EstatusOficiosOrdenesEnum.OFICIO_NOTIFICADO_CONTRIBUYENTE.getBigIdEstatus());
        AgaceOrden orden = getAgaceOrdenDao().findByPrimaryKey(idOrden);
        getPlazosService().asignarFechasNyVOficio(orden, oficios);
        return oficios;
    }

    @Override
    public List<AgaceOrden> cargarListaPorValidar(final BigDecimal idEmpleado) throws NegocioException {
        List<AgaceOrden> listaOrdenes = this.getAgaceOrdenDao().getOrdenesSeguimientoAuditor(idEmpleado);
        listaOrdenes = getPlazosService().filtraOrdenPorFecha(listaOrdenes);
        for (AgaceOrden orden : listaOrdenes) {
            orden.setImagenSemaforo(BusinessUtil.obtenerImagenSemaforo(orden.getSemaforo()));
            orden.setDescripcionSemaforo(BusinessUtil.obtenerDescripcionSemaforo(orden.getSemaforo()));
            orden.setMostrarNumeroOrden(getPlazosService().tieneAcuerdoConclusivo(orden));
        }

        return listaOrdenes;
    }

    @Override
    public List<FecetOficio> getOficiosFirmados(final BigDecimal idOrden) {
        List<FecetOficio> oficiosFirmados = getFecetOficioDao().getOficioByIdOrdenIdEstatus(idOrden, EstatusOficiosOrdenesEnum.OFICIO_FIRMADO_ENVIADO_NYV.getBigIdEstatus(), EstatusOficiosOrdenesEnum.OFICIO_NOTIFICADO_CONTRIBUYENTE.getBigIdEstatus());
        AgaceOrden orden = getAgaceOrdenDao().findByPrimaryKey(idOrden);
        getPlazosService().asignarFechasNyVOficio(orden, oficiosFirmados);
        return oficiosFirmados;
    }

    @Override
    public List<FecetOficio> getOficiosRechazados(final BigDecimal idOrden) {
        List<FecetOficio> oficiosRechazados = getFecetOficioDao().getOficioByIdOrdenRechazados(idOrden);
        AgaceOrden orden = getAgaceOrdenDao().findByPrimaryKey(idOrden);
        getPlazosService().asignarFechasNyVOficio(orden, oficiosRechazados);
        return oficiosRechazados;
    }

    @Override
    public List<FecetAnexosProrrogaOrden> getAnexosRechazoProrrogaOrden(final BigDecimal idProrrogaOrden) {
        return getFecetAnexosProrrogaOrdenDao().findWhereIdProrrogaEquals(idProrrogaOrden);
    }

    @Override
    public List<FecetAnexoPruebasPericiales> getAnexosRechazoPruebasPericiales(final BigDecimal idPruebaPericial) {
        return getFecetAnexoPruebasPericialesDao().findWhereIdProrrogaEquals(idPruebaPericial);
    }

    @Override
    public List<FecetAnexosProrrogaOficio> getAnexosRechazoProrrogaOficio(final BigDecimal idProrrogaOficio) {
        return getFecetAnexosProrrogaOficioDao().findWhereIdFlujoProrrogaOficioEquals(idProrrogaOficio);
    }

    @Override
    public List<FecetDocProrrogaOrden> getDocumentacionProrrogaContribuyente(final BigDecimal idProrrogaOrden) {
        return getFecetDocProrrogaOrdenDao().findWhereIdProrrogaOrdenEquals(idProrrogaOrden);
    }

    @Override
    public List<FecetDocProrrogaOficio> getDocumentacionContribuyenteProrrogaOficio(final BigDecimal idProrrogaOficio) {
        return getFecetDocProrrogaOficioDao().findWhereIdProrrogaOficioEquals(idProrrogaOficio);
    }

    @Override
    public List<FecetOficioAnexos> getAnexosOficioRechazo(final BigDecimal idOficio) {
        return getFecetOficioAnexosDao().getAnexosRechazo(idOficio);
    }

    @Transactional
    @Override
    public void guardar2aCartaInvitacion(final AgaceOrden orden, final List<FecetOficio> listaOf2aCartaInvitacion,
            final List<FecetOficioAnexos> listaAnexos2aCartaInvitacion) throws NegocioException {
        try {

            if (listaOf2aCartaInvitacion.isEmpty()) {
                throw new NegocioException("No se cargo el oficio de 2a Carta Invitacion");
            }

            FecetOficio oficio = listaOf2aCartaInvitacion.get(0);
            oficio.setIdOficio(this.getFecetOficioDao().getIdOficio());
            oficio.setDocumentoPdf("0");
            oficio.setRutaArchivo(RutaArchivosUtil.armarRutaDestino2aCartaInvitacion(orden, oficio));

            oficio.setSuspencionPlazo("0");

            ArchivoOrdenUtil.guardarArchivoOficio(oficio);

            String nombreArchivo = "", nombre = "", ruta = "";
            if (oficio.getRutaArchivo() != null) {
                nombreArchivo = oficio.getNombreArchivo();
                ruta = oficio.getRutaArchivo();
                oficio.setRutaArchivo(ruta.replace(nombreArchivo, "").concat(nombreArchivo));
            }

            if (oficio.getRutaAcuseNyv() != null) {
                nombre = oficio.getNombreAcuseNyv();
                ruta = oficio.getRutaAcuseNyv();
                oficio.setRutaAcuseNyv(ruta.replace(nombre, "").concat(nombre));
            }

            oficio.setIdAdminOficio(Constantes.ADMIN_OFICIO_111);
            oficio.getFecetTipoOficio().setBlnActivo(new BigDecimal(1L));
            oficio.setBlnActivo(true);

            getFecetOficioDao().insert(oficio);

            for (FecetOficioAnexos anexo : listaAnexos2aCartaInvitacion) {
                anexo.setIdOficioAnexos(this.getFecetOficioAnexosDao().getIdFecetOficioAnexosPathDirectorio());
                anexo.setIdOficio(oficio.getIdOficio());
                anexo.setRutaArchivo(RutaArchivosUtil.armarRutaAnexosOficio(oficio.getRutaArchivo().replace(nombreArchivo, "")));

                ArchivoOrdenUtil.guardarArchivoOficioAnexo(anexo);

                if (anexo.getRutaArchivo() != null) {
                    nombre = anexo.getNombreArchivo();
                    ruta = anexo.getRutaArchivo();
                    anexo.setRutaArchivo(ruta.replace(nombre, "").concat(nombre));
                }

                getFecetOficioAnexosDao().insert(anexo);
            }
            envioCorreoOficio(orden, oficio);
        } catch (IOException e) {
            logger.error(ConstantesError.ERROR_GUARDAR_ARCHIVO, e);
            throw new NegocioException(ConstantesError.ERROR_GUARDAR_ARCHIVO + e.getCause(), e);
        }
    }

    @Transactional
    @Override
    @PistaAuditoria
    public BigDecimal guardarMedidaApremio(ReglasNegocioOrdenesBO reglasNegocioOrdenesBO) throws NegocioException {
        AgaceOrden orden;
        try {
            List<FecetOficio> oficios = reglasNegocioOrdenesBO.getListaOfPrincipal();
            List<FecetOficioAnexos> anexos = reglasNegocioOrdenesBO.getListaAnexosPrincipal();
            orden = reglasNegocioOrdenesBO.getOrden();
            if (oficios.isEmpty()) {
                throw new NegocioException("No se cargo el oficio de Medida de Apremio");
            }

            FecetOficio oficio = oficios.get(0);
            setIdOficioPapel(this.getFecetOficioDao().getIdOficio());

            oficio.setIdOficio(getIdOficioPapel());
            oficio.setDocumentoPdf("0");
            oficio.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoMedidaApremio(orden, oficio));

            oficio.setSuspencionPlazo("0");

            ArchivoOrdenUtil.guardarArchivoOficio(oficio);

            String nombreArchivo = "", nombre = "", ruta = "";
            if (oficio.getRutaArchivo() != null) {
                nombreArchivo = oficio.getNombreArchivo();
                ruta = oficio.getRutaArchivo();
                oficio.setRutaArchivo(ruta.replace(nombreArchivo, "").concat(nombreArchivo));
            }

            if (oficio.getRutaAcuseNyv() != null) {
                nombre = oficio.getNombreAcuseNyv();
                ruta = oficio.getRutaAcuseNyv();
                oficio.setRutaAcuseNyv(ruta.replace(nombre, "").concat(nombre));
            }
            oficio.setIdAdminOficio(Constantes.ADMIN_OFICIO_111);
            oficio.getFecetTipoOficio().setBlnActivo(new BigDecimal(1L));
            getFecetOficioDao().insert(oficio);

            for (FecetOficioAnexos anexo : anexos) {
                anexo.setIdOficioAnexos(this.getFecetOficioAnexosDao().getIdFecetOficioAnexosPathDirectorio());
                anexo.setIdOficio(oficio.getIdOficio());
                anexo.setRutaArchivo(RutaArchivosUtil.armarRutaAnexosOficio(oficio.getRutaArchivo().replace(nombreArchivo, "")));

                ArchivoOrdenUtil.guardarArchivoOficioAnexo(anexo);

                if (anexo.getRutaArchivo() != null) {
                    nombre = anexo.getNombreArchivo();
                    ruta = anexo.getRutaArchivo();
                    anexo.setRutaArchivo(ruta.replace(nombre, "").concat(nombre));
                }

                getFecetOficioAnexosDao().insert(anexo);
            }
            envioCorreoOficio(orden, oficio);
        } catch (IOException e) {
            logger.error(ConstantesError.ERROR_GUARDAR_ARCHIVO, e);
            throw new NegocioException(ConstantesError.ERROR_GUARDAR_ARCHIVO + e.getCause(), e);
        }
        return orden.getIdOrden();
    }

    @Transactional
    @Override
    public void guardar2aCartaInvitacionMasiva(final AgaceOrden orden,
            final List<FecetOficio> listaOf2aCartaInvitacionMasiva,
            final List<FecetOficioAnexos> listaAnexos2aCartaInvitacionMasiva) throws NegocioException {
        try {

            if (listaOf2aCartaInvitacionMasiva.isEmpty()) {
                throw new NegocioException("No se cargo el oficio de 2a Carta Invitacion Masiva");
            }

            FecetOficio oficio = listaOf2aCartaInvitacionMasiva.get(0);
            oficio.setIdOficio(this.getFecetOficioDao().getIdOficio());
            oficio.setDocumentoPdf("0");
            oficio.setRutaArchivo(RutaArchivosUtil.armarRutaDestino2aCartaInvitacionMasiva(orden, oficio));

            oficio.setSuspencionPlazo("0");

            ArchivoOrdenUtil.guardarArchivoOficio(oficio);

            String nombreArchivo = "", nombre = "", ruta = "";
            if (oficio.getRutaArchivo() != null) {
                nombreArchivo = oficio.getNombreArchivo();
                ruta = oficio.getRutaArchivo();
                oficio.setRutaArchivo(ruta.replace(nombreArchivo, "").concat(nombreArchivo));
            }

            if (oficio.getRutaAcuseNyv() != null) {
                nombre = oficio.getNombreAcuseNyv();
                ruta = oficio.getRutaAcuseNyv();
                oficio.setRutaAcuseNyv(ruta.replace(nombre, "").concat(nombre));
            }

            oficio.setIdAdminOficio(Constantes.ADMIN_OFICIO_111);
            oficio.getFecetTipoOficio().setBlnActivo(new BigDecimal(1L));
            oficio.setBlnActivo(true);

            getFecetOficioDao().insert(oficio);

            for (FecetOficioAnexos anexo : listaAnexos2aCartaInvitacionMasiva) {
                anexo.setIdOficioAnexos(this.getFecetOficioAnexosDao().getIdFecetOficioAnexosPathDirectorio());
                anexo.setIdOficio(oficio.getIdOficio());
                anexo.setRutaArchivo(RutaArchivosUtil.armarRutaAnexosOficio(oficio.getRutaArchivo().replace(nombreArchivo, "")));

                ArchivoOrdenUtil.guardarArchivoOficioAnexo(anexo);

                if (anexo.getRutaArchivo() != null) {
                    nombre = anexo.getNombreArchivo();
                    ruta = anexo.getRutaArchivo();
                    anexo.setRutaArchivo(ruta.replace(nombre, "").concat(nombre));
                }

                getFecetOficioAnexosDao().insert(anexo);
            }
            envioCorreoOficio(orden, oficio);
        } catch (IOException e) {
            logger.error(ConstantesError.ERROR_GUARDAR_ARCHIVO, e);
            throw new NegocioException(ConstantesError.ERROR_GUARDAR_ARCHIVO.concat(e.getCause().toString()), e);
        }
    }

    @Override
    public List<FecetPromocionOficio> getPromocionContadorPruebasAlegatosOficio(final FecetOficio oficio) {
        List<FecetPromocionOficio> listaPromociones
                = getFecetPromocionOficioDao().getPromocionContadorPruebasAlegatosOficio(oficio.getIdOficio());

        for (FecetPromocionOficio promocion : listaPromociones) {
            promocion.setDescripcionTipoPromocion(BusinessUtil.getTipoPromocionPorTipoOficioMetodo(oficio.getFecetTipoOficio().getIdTipoOficio()));
            if (oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.OTRAS_AUTORIDADES.getBigIdTipoOficio()) || oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.COMPULSA_INTERNACIONAL.getBigIdTipoOficio())) {
                promocion.setExtemporanea("0");
            } else {
                if (getValidarOficioAdministrable().esAdministrable(oficio)) {
                    if (getValidarOficioAdministrable().tienePlazos(oficio)) {
                        promocion.setExtemporanea(getPlazosService().esDocumentoExtemporaneoOficio(oficio, promocion.getFechaCarga()) ? "1" : "0");
                    } else {
                        promocion.setExtemporanea("0");
                    }
                } else {
                    promocion.setExtemporanea(getPlazosService().esDocumentoExtemporaneoOficio(oficio, promocion.getFechaCarga()) ? "1" : "0");
                }
            }
        }

        return listaPromociones;
    }

    @Override
    public void guardarAvisoContribuyente(ReglasNegocioOrdenesBO reglasNegocioOrdenesBO) throws NegocioException {
        try {

            validaCargaAvisoContribuyente(reglasNegocioOrdenesBO.getListaOfPrincipal());

            FecetOficio oficioAvisoCont = reglasNegocioOrdenesBO.getListaOfPrincipal().get(0);
            oficioAvisoCont.setIdOficio(this.getFecetOficioDao().getIdOficio());
            oficioAvisoCont.setRutaArchivo(RutaArchivosUtil.armarRutaDestinoConObservaciones(reglasNegocioOrdenesBO.getOrden(),
                    oficioAvisoCont));
            insertarOficioAnexosArchivos(oficioAvisoCont, reglasNegocioOrdenesBO.getListaAnexosPrincipal());
            envioCorreoOficio(reglasNegocioOrdenesBO.getOrden(), oficioAvisoCont);

        } catch (NegocioException e) {
            logger.error(ERROR_NEGOCIO + e.getMessage());
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @Override
    public void guardarFlujoProrrogaOrdenAprobadaAuditor(final FecetProrrogaOrden prorrogaOrden,
            String justificacion,
            final List<FecetAnexosProrrogaOrden> listaAnexos) throws NegocioException {
        try {
            FecetFlujoProrrogaOrden flujoProrrogaOrden = new FecetFlujoProrrogaOrden();
            flujoProrrogaOrden.setJustificacion(justificacion);
            validaCargaAnexos(listaAnexos);
            validaJustificacionProrrogaAceptada(flujoProrrogaOrden.getJustificacion());

            flujoProrrogaOrden.setIdFlujoProrrogaOrden(this.getFecetFlujoProrrogaOrdenDao().getIdFecetFlujoProrrogaOrdenPathDirectorio());
            flujoProrrogaOrden.setAprobada(true);
            flujoProrrogaOrden.setFechaCreacion(new Date());
            flujoProrrogaOrden.setIdEstatus(EstatusFlujoProrroga.PRORROGA_RESUELTA_AUDITOR.getBigIdEstatus());
            flujoProrrogaOrden.setIdProrrogaOrden(prorrogaOrden.getIdProrrogaOrden());

            final String pathAnexosAprobadoAuditor
                    = RutaArchivosUtil.armarRutaAnexosProrrogaAprobadaAuditor(
                            ArchivoOrdenUtil.getPathFromAbsolutePath(prorrogaOrden.getRutaAcuse()),
                            flujoProrrogaOrden.getIdFlujoProrrogaOrden());

            insertaFlujoProrrogaOrdenAuditor(getFecetFlujoProrrogaOrdenDao(), getFecetAnexosProrrogaOrdenDao(), flujoProrrogaOrden, listaAnexos, pathAnexosAprobadoAuditor, "1");
        } catch (NegocioException e) {
            logger.error(ERROR_NEGOCIO + e.getMessage());
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @Override
    public void actuallizarResolucion(final FecetProrrogaOrden prorrogaOrden,
            final List<FecetAnexosProrrogaOrden> listaResolucion) throws NegocioException {
        validaCargaResolucion(listaResolucion);
        String pathAnexosAprobadoAuditor
                = RutaArchivosUtil.armarRutaResolucionProrrogaAuditor(
                        ArchivoOrdenUtil.getPathFromAbsolutePath(prorrogaOrden.getRutaAcuse()),
                        prorrogaOrden.getId());
        actuallizarResolucion(prorrogaOrden,
                listaResolucion, pathAnexosAprobadoAuditor);
    }

    @Override
    public void actuallizarResolucionPruebasPericiales(final FecetPruebasPericiales pruebaPercial,
            final List<FecetAnexoPruebasPericiales> listaResolucion) throws NegocioException {
        validaCargaAnexosPruebasPericiales(listaResolucion);
        String pathAnexosAprobadoAuditor
                = RutaArchivosUtil.armarRutaResolucionPruebaPericialAuditor(
                        ArchivoOrdenUtil.getPathFromAbsolutePath(pruebaPercial.getRutaAcuse()),
                        pruebaPercial.getId());
        actuallizarResolucionPruebaPericial(pruebaPercial,
                listaResolucion, pathAnexosAprobadoAuditor);
    }

    @Override
    public void actuallizarResolucionOficio(final FecetProrrogaOficio prorrogaOrden,
            final List<FecetAnexosProrrogaOficio> listaResolucion) throws NegocioException {
        validaCargaResolucionOficio(listaResolucion);
        String pathAnexosAprobadoAuditor
                = RutaArchivosUtil.armarRutaResolucionProrrogaAuditor(
                        ArchivoOrdenUtil.getPathFromAbsolutePath(prorrogaOrden.getRutaAcuse()),
                        prorrogaOrden.getId());
        actuallizarResolucionOficio(prorrogaOrden,
                listaResolucion, pathAnexosAprobadoAuditor);
    }

    @Override
    public void enviarCorreoProrrogaAprobada(AgaceOrden orden, FecetProrrogaOrden prorroga) {
        envioCorreoProrroga(orden, prorroga.getId(),
                APROBADA);
    }

    @Override
    public void enviarCorreoPruebaPericialAprobada(AgaceOrden orden, FecetPruebasPericiales pruebaPericial) {
        pruebaPericial.setOrden(orden);
        cargarFirmaPruebasPericialesService.enviarCorreoPruebasPericiales(pruebaPericial, Constantes.AUDITOR, APROBADA);
    }

    @Override
    public void enviarCorreoPruebaPericialRechazada(AgaceOrden orden, FecetPruebasPericiales pruebaPericial) {
        pruebaPericial.setOrden(orden);
        cargarFirmaPruebasPericialesService.enviarCorreoPruebasPericiales(pruebaPericial, Constantes.AUDITOR, RECHAZADA);
    }

    @Override
    public void enviarCorreoProrrogaAprobada(AgaceOrden orden, FecetProrrogaOficio prorroga) {
        envioCorreoProrroga(orden, prorroga.getId(),
                APROBADA);
    }

    @Override
    public void enviarCorreoProrrogaRechazada(AgaceOrden orden, FecetProrrogaOrden prorroga) {
        envioCorreoProrroga(orden, prorroga.getId(),
                RECHAZADA);
    }

    @Override
    public void enviarCorreoProrrogaRechazada(AgaceOrden orden, FecetProrrogaOficio prorroga) {
        envioCorreoProrroga(orden, prorroga.getId(),
                RECHAZADA);
    }

    @Override
    public void guardarFlujoProrrogaOficioRechazadaAuditor(final FecetProrrogaOficio prorrogaOficio,
            FecetFlujoProrrogaOficio flujoProrroga,
            final List<FecetAnexosProrrogaOficio> listaAnexos) throws NegocioException {
        try {
            validaCargaAnexosOficio(listaAnexos);
            validaJustificacionProrrogaRechazada(flujoProrroga.getJustificacion());

            flujoProrroga.setIdFlujoProrrogaOficio(this.getFecetFlujoProrrogaOficioDao().getIdFecetFlujoProrrogaOficioPathDirectorio());
            flujoProrroga.setAprobada(false);
            flujoProrroga.setFechaCreacion(new Date());
            flujoProrroga.setIdEstatus(EstatusFlujoProrroga.PRORROGA_RESUELTA_AUDITOR.getBigIdEstatus());
            flujoProrroga.setIdProrrogaOficio(prorrogaOficio.getIdProrrogaOficio());

            final String pathAnexosRechazadoAuditor
                    = RutaArchivosUtil.armarRutaAnexosProrrogaRechazadaAuditor(
                            ArchivoOrdenUtil.getPathFromAbsolutePath(prorrogaOficio.getRutaAcuse()),
                            flujoProrroga.getIdFlujoProrrogaOficio());

            insertaFlujoProrrogaOficioAuditor(flujoProrroga, listaAnexos, pathAnexosRechazadoAuditor, "2");
        } catch (NegocioException e) {
            logger.error(ERROR_NEGOCIO + e.getMessage());
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @Override
    public List<FecetAnexosProrrogaOficio> getAnexosProrrogaOficioHistorico(final BigDecimal idFlujoProrrogaOficio) {
        List<FecetAnexosProrrogaOficio> listaAnexoProrrogas = getFecetAnexosProrrogaOficioDao().findWhereIdFlujoProrrogaOficioEquals(idFlujoProrrogaOficio);

        if (listaAnexoProrrogas != null && !listaAnexoProrrogas.isEmpty()) {
            EmpleadoDTO empleadoDTO;
            try {
                empleadoDTO = getEmpleadoCompleto(listaAnexoProrrogas.get(0).getFececEmpleado().getIdEmpleado().intValue());
                for (FecetAnexosProrrogaOficio anexo : listaAnexoProrrogas) {
                    anexo.getFececEmpleado().setNombre(empleadoDTO.getNombreCompleto());
                    anexo.getFececEmpleado().setCorreo(empleadoDTO.getCorreo());
                    anexo.getFececEmpleado().setRfc(empleadoDTO.getRfc());
                }
                if (empleadoDTO == null) {
                    listaAnexoProrrogas = null;
                }
            } catch (EmpleadoServiceException e) {
                // TODO Auto-generated catch block
                listaAnexoProrrogas = null;
            }

        }

        return listaAnexoProrrogas;
    }

    @Override
    public void guardarFlujoProrrogaOficioAprobadaAuditor(final FecetProrrogaOficio prorrogaOficio,
            FecetFlujoProrrogaOficio flujoProrroga,
            final List<FecetAnexosProrrogaOficio> listaAnexos) throws NegocioException {
        try {
            flujoProrroga.setIdFlujoProrrogaOficio(this.getFecetFlujoProrrogaOficioDao().getIdFecetFlujoProrrogaOficioPathDirectorio());
            flujoProrroga.setAprobada(true);
            flujoProrroga.setFechaCreacion(new Date());
            flujoProrroga.setIdEstatus(EstatusFlujoProrroga.PRORROGA_RESUELTA_AUDITOR.getBigIdEstatus());
            flujoProrroga.setIdProrrogaOficio(prorrogaOficio.getIdProrrogaOficio());

            final String pathAnexosAprobadoAuditor
                    = RutaArchivosUtil.armarRutaAnexosProrrogaAprobadaAuditor(
                            ArchivoOrdenUtil.getPathFromAbsolutePath(prorrogaOficio.getRutaAcuse()),
                            flujoProrroga.getIdFlujoProrrogaOficio());

            insertaFlujoProrrogaOficioAuditor(flujoProrroga, listaAnexos, pathAnexosAprobadoAuditor, "1");
        } catch (NegocioException e) {
            logger.error(ERROR_NEGOCIO + e.getMessage());
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @Override
    public void guardarFlujoProrrogaOrdenRechazadaAuditor(final FecetProrrogaOrden prorrogaOrden,
            String justificacionRechazo,
            final List<FecetAnexosProrrogaOrden> listaAnexos) throws NegocioException {
        try {
            FecetFlujoProrrogaOrden flujoProrroga = new FecetFlujoProrrogaOrden();
            flujoProrroga.setJustificacion(justificacionRechazo);

            flujoProrroga.setIdFlujoProrrogaOrden(this.getFecetFlujoProrrogaOrdenDao().getIdFecetFlujoProrrogaOrdenPathDirectorio());
            flujoProrroga.setAprobada(false);
            flujoProrroga.setFechaCreacion(new Date());
            flujoProrroga.setIdEstatus(EstatusFlujoProrroga.PRORROGA_RESUELTA_AUDITOR.getBigIdEstatus());
            flujoProrroga.setIdProrrogaOrden(prorrogaOrden.getIdProrrogaOrden());

            final String pathAnexosRechazadoAuditor
                    = RutaArchivosUtil.armarRutaAnexosProrrogaRechazadaAuditor(
                            ArchivoOrdenUtil.getPathFromAbsolutePath(prorrogaOrden.getRutaAcuse()),
                            flujoProrroga.getIdFlujoProrrogaOrden());

            insertaFlujoProrrogaOrdenAuditor(getFecetFlujoProrrogaOrdenDao(), getFecetAnexosProrrogaOrdenDao(), flujoProrroga, listaAnexos, pathAnexosRechazadoAuditor, "2");

        } catch (NegocioException e) {
            logger.error(ERROR_NEGOCIO + e.getMessage());
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @Override
    public List<FecetAnexosProrrogaOrden> getAnexosProrrogaOrdenHistorico(final BigDecimal idFlujoProrrogaOrden) {
        return getFecetAnexosProrrogaOrdenDao().findWhereIdFlujoProrrogaOrdenEquals(idFlujoProrrogaOrden);
    }

    @Override
    public List<FecetAnexoPruebasPericiales> getAnexosPruebasPericialesHistorico(final BigDecimal idFlujoPruebaPercial) {
        return getFecetAnexoPruebasPericialesDao().findWhereIdFlujoPruebasPericialesEquals(idFlujoPruebaPercial);
    }

    @Override
    public List<FecetProrrogaOficio> getHistoricoProrrogaOficio(final BigDecimal idOficio) {
        List<FecetProrrogaOficio> historicoProrrogas = getFecetProrrogaOficioDao().getHistoricoProrrogaPorOficio(idOficio);

        for (FecetProrrogaOficio prorroga : historicoProrrogas) {
            if (prorroga.getIdEstatus().equals(Constantes.ESTATUS_PRORROGA_NOTIFICADA_CONTRIBUYENTE)) {
                prorroga.setDescripcionEstado(prorroga.getFececEstatus().getDescripcion());
            } else {
                prorroga.setDescripcionEstado(this.getFececEstatusDao().findWhereIdEstatusEquals(prorroga.getFecetFlujoProrrogaOficio().getIdEstatus()).get(0).getDescripcion());
            }
        }

        return historicoProrrogas;
    }

    @Override
    public FecetPropuesta getDetallePropuestaOrden(final String idRegistro) {
        FecetPropuesta detallePropuesta = getFecetPropuestaDao().buscarDetalleOficio(idRegistro).get(0);
        if (detallePropuesta == null) {
            detallePropuesta = new FecetPropuesta();
        } else {
            detallePropuesta.setUnidadAdministrativa(fillUnidadAdministrativa(detallePropuesta.getIdArace().intValue()));
            logger.debug("Detalle [{}]", detallePropuesta);
        }
        return detallePropuesta;
    }

    @Override
    public List<FecetOficio> getOficiosDependientesByIdEstatus(final BigDecimal idOficio, final BigDecimal idEstatus) {
        return getFecetOficioDao().getOficiosDependientesPorIdEstatus(idOficio, idEstatus);
    }

    @Override
    public List<FecetOficio> getOficiosDependientesFirmados(final BigDecimal idOficio) {
        return getFecetOficioDao().getOficiosDependientesFirmados(idOficio);
    }

    @Override
    public List<FecetOficioAnexos> getAnexosOficio(final BigDecimal idOficio) {
        return getFecetOficioAnexosDao().getAnexosByIdOficio(idOficio);
    }

    @Override
    public List<FecetAnexosRechazoOficio> getAnexosRechazoOficio(final BigDecimal idRechazoOficio) {
        return getFecetAnexosRechazoOficioDao().getAnexosRechazoOficioByIdRechazoOficio(idRechazoOficio);
    }

    @Override
    public void setListaOficiosEnProcesoVencidos(final List<FecetOficio> listaOficiosEnProcesoVencidos) {
        getSegOrdsCondicionesHelp().setListaOficiosEnProcesoVencidos(listaOficiosEnProcesoVencidos);
    }

    @Override
    public List<FecetProrrogaOrden> getProrrogasPendientesAprobPorOrden(BigDecimal idOrden) {
        return getFecetProrrogaOrdenDao().getProrrogaPendienteAprobPorOrden(idOrden);
    }

    @Override
    public boolean getProrrogasPendientesAprobacionPorOficio(List<FecetOficio> listaOficios) {
        for (FecetOficio oficio : listaOficios) {
            List<FecetProrrogaOficio> prorrogasPendientes = getFecetProrrogaOficioDao().getProrrogaPorOficioEstatus(oficio.getIdOficio(), EstatusProrroga.PRORROGA_PENDIENTE_APROBACION.getBigIdEstatus());
            if (prorrogasPendientes != null && !prorrogasPendientes.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<FecetOficio> getOficiosAdministrables(final BigDecimal idOrden) {
        return getFecetOficioDao().getOficiosAdministrablesPorIdOrden(idOrden);
    }

    @Override
    public BigDecimal obtenerIdOficioPadre() {
        return getIdOficioPapel();
    }

    @Override
    public FecetDocOrden obtenerExpedienteOrden(AgaceOrden orden) {
        FecetDocOrden expedinete = null;
        List<FecetDocOrden> documentos = getFecetDocOrdenDao().findByFecetOrdenPdf(orden.getIdOrden());
        if (documentos != null && !documentos.isEmpty()) {
            for (FecetDocOrden fecetDocOrden : documentos) {
                if (fecetDocOrden.getRutaArchivo().contains(orden.getNumeroOrden())) {
                    expedinete = fecetDocOrden;
                    break;
                }
            }
        }
        return expedinete;
    }

    @Override
    public void guardarFlujoPruebasPericialesAprobadaAuditor(final FecetPruebasPericiales pruebaPericial,
            String justificacion,
            final List<FecetAnexoPruebasPericiales> listaAnexos) throws NegocioException {
        try {
            FecetFlujoPruebasPericiales flujoPruebasPericiales = new FecetFlujoPruebasPericiales();
            flujoPruebasPericiales.setJustificacion(justificacion);

            flujoPruebasPericiales.setIdFlujoPruebasPericiales(getFecetFlujoPruebasPericialesDao().getIdFecetFlujoPruebasPericialesPathDirectorio());
            flujoPruebasPericiales.setAprobada(true);
            flujoPruebasPericiales.setFechaCreacion(new Date());
            flujoPruebasPericiales.setIdEstatus(EstatusFlujoPruebasPericiales.PRUEBA_PERICIAL_RESUELTA_AUDITOR.getBigIdEstatus());
            flujoPruebasPericiales.setIdPruebasPericiales(pruebaPericial.getIdPruebasPericiales());

            final String pathAnexosAprobadoAuditor
                    = RutaArchivosUtil.armarRutaAnexosPruebaPericialAprobadaAuditor(
                            ArchivoOrdenUtil.getPathFromAbsolutePath(pruebaPericial.getRutaAcuse()),
                            flujoPruebasPericiales.getIdFlujoPruebasPericiales());

            insertaFlujoPruebaPericialAuditor(getFecetFlujoPruebasPericialesDao(), getFecetAnexoPruebasPericialesDao(), flujoPruebasPericiales, listaAnexos, pathAnexosAprobadoAuditor, "1");
        } catch (NegocioException e) {
            logger.error(ERROR_NEGOCIO + e.getMessage());
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @Override
    public void guardarFlujoPruebasPericialesRechazadaAuditor(final FecetPruebasPericiales pruebasPericiales,
            String justificacion,
            final List<FecetAnexoPruebasPericiales> listaAnexos) throws NegocioException {
        try {
            FecetFlujoPruebasPericiales flujoPruebasPericiales = new FecetFlujoPruebasPericiales();
            flujoPruebasPericiales.setJustificacion(justificacion);

            flujoPruebasPericiales.setIdFlujoPruebasPericiales(getFecetFlujoPruebasPericialesDao().getIdFecetFlujoPruebasPericialesPathDirectorio());
            flujoPruebasPericiales.setAprobada(false);
            flujoPruebasPericiales.setFechaCreacion(new Date());
            flujoPruebasPericiales.setIdEstatus(EstatusFlujoPruebasPericiales.PRUEBA_PERICIAL_RESUELTA_AUDITOR.getBigIdEstatus());
            flujoPruebasPericiales.setIdPruebasPericiales(pruebasPericiales.getIdPruebasPericiales());

            final String pathAnexosRechazadoAuditor
                    = RutaArchivosUtil.armarRutaAnexosPruebasPericialesRechazadaAuditor(
                            ArchivoOrdenUtil.getPathFromAbsolutePath(pruebasPericiales.getRutaAcuse()),
                            flujoPruebasPericiales.getIdFlujoPruebasPericiales());

            insertaFlujoPruebaPericialAuditor(getFecetFlujoPruebasPericialesDao(), getFecetAnexoPruebasPericialesDao(), flujoPruebasPericiales, listaAnexos, pathAnexosRechazadoAuditor, "2");

        } catch (NegocioException e) {
            logger.error(ERROR_NEGOCIO + e.getMessage());
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @Override
    public List<EmpleadoDTO> obtenerJefeEnlace(BigDecimal tipoEmpleado, BigDecimal idArace) {

        List<EmpleadoDTO> jefeDepartamento = new ArrayList<EmpleadoDTO>();

        try {
            jefeDepartamento = getEmpleadosXUnidadAdmva(idArace.intValue(), tipoEmpleado.intValue(),
                    ClvSubModulosAgace.PROPUESTAS);

            for (EmpleadoDTO empleado : jefeDepartamento) {
                empleado.setNombre(empleado.getNombreCompleto());
            }

        } catch (EmpleadoServiceException e) {
            logger.error("No se encontro el Empleado.");
        }

        return jefeDepartamento;
    }

    @Override
    public void insertarAsociadoFuncionario(AsociadoFuncionarioDTO dto, AgaceOrden orden) {
        try {
            getAsociadoFuncionarioDao().insertar(dto);
            Set<String> destinatarios = new TreeSet<String>();
            destinatarios.add(getEmpleadoCompleto(dto.getIdEmpleado().intValue()).getCorreo());

            notificacionService.obtenerCorreoEmpleado(orden.getIdAuditor(), Constantes.USUARIO_AUDITOR,
                    destinatarios, ClvSubModulosAgace.PROPUESTAS);

            Map<String, String> data = notificacionService.obtenerDatosCorreo(AVISO_AIGNACION_CONSULTA);

            data.put(Constantes.METODO_ORDEN, TipoMetodoEnum.getById(orden.getIdMetodo().longValue()).getDescripcion());
            data.put(Constantes.NUMERO_ORDEN, orden.getNumeroOrden());
            data.put("ID_Registro_NúmeroOrden", orden.getNumeroOrden());

            enviarNotificacionInterna(data, ReportType.AVISOS_ORDEN_GENERICO, destinatarios);

        } catch (EmpleadoServiceException e) {
            logger.error("El Empleado No Existe");
        }
    }

    private void enviarNotificacionInterna(Map<String, String> data, ReportType pantalla, Set<String> destinatarios) {

        try {
            notificacionService.enviarNotificacionGenerico(data, pantalla, destinatarios);
        } catch (BusinessException e) {
            logger.error("No se pudo enviar el correo de notificacion [{}]", e.getCause(), e);
        }
    }

}
