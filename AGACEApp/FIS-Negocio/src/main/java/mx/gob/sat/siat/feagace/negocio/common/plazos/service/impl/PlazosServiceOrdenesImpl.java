package mx.gob.sat.siat.feagace.negocio.common.plazos.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FeceaDocumentoAdmDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececEstatusDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.AgaceOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetProrrogaOficioDao;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleNyV;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.ActoAdministrativo;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.FececActosAdm;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.NotificableNyV;
import mx.gob.sat.siat.feagace.modelo.dto.common.tiempos.model.TiempoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCompulsas;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetSuspensionDTO;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorOficiosEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TiposOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.prorrogas.EstatusProrroga;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.seguimiento.EstatusOrdenes;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.ConsultaRegistraNyVService;
import mx.gob.sat.siat.feagace.negocio.common.plazos.constants.PlazosConstants;
import mx.gob.sat.siat.feagace.negocio.common.plazos.service.PlazosServiceOrdenes;
import mx.gob.sat.siat.feagace.negocio.common.tiempos.enums.TiemposClasificTiemposEnum;
import mx.gob.sat.siat.feagace.negocio.common.tiempos.enums.TiemposClaveEnum;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.ordenes.ProrrogaService;

@Service("plazosService")
public class PlazosServiceOrdenesImpl extends PlazosService implements PlazosServiceOrdenes {

    private static final long serialVersionUID = 1L;

    private static final double DIA_MILISEGUNDOS = 86400000d;

    private static final int NUM_40 = 40;

    private static final List<TiposOficiosOrdenesEnum> COMPULSAS = Arrays.asList(TiposOficiosOrdenesEnum.COMPULSA_TERCEROS,
            TiposOficiosOrdenesEnum.COMPULSA_INTERNACIONAL, TiposOficiosOrdenesEnum.OTRAS_AUTORIDADES);

    private static final List<BigDecimal> OFICIOS_AVISO_CIRCUNTANCIAL_ORG = Arrays.asList(TiposOficiosOrdenesEnum.OBSERVACIONES_REVISION_ESCRITORIO.getBigIdTipoOficio(),
            TiposOficiosOrdenesEnum.SIN_OBSERVACIONES.getBigIdTipoOficio());

    @Autowired
    private transient AgaceOrdenDao agaceOrdenDao;

    @Autowired
    private transient FecetOficioDao fecetOficioDao;

    @Autowired
    private transient FecetProrrogaOficioDao fecetProrrogaOficioDao;

    @Autowired
    private transient FececEstatusDao fececEstatusDao;

    @Autowired
    private transient ConsultaRegistraNyVService consultaRegistraNyVService;

    @Autowired
    private transient ProrrogaService prorrogaService;

    @Autowired
    private transient FeceaDocumentoAdmDao feceaDocumentoAdmDao;

    @Override
    public Date primerDiaHabil(Date fecha) {
        return getTiemposService().obtenerDiasHabiles(fecha, 1);
    }

    @Override
    public boolean validarPlazoFinCompulsa(AgaceOrden orden, TiposOficiosOrdenesEnum oficio) {
        List<FecetSuspensionDTO> listSuspension = getSuspensionService().buscarSuspensionIdTipoOficio(orden.getIdOrden(), oficio.getBigIdTipoOficio());
        if (listSuspension != null && !listSuspension.isEmpty()) {
            Date fechaCompulsa = listSuspension.get(0).getFechaInicioSuspension();
            TiempoDTO tiempoPlazoGeneracion = getTiemposService().obtenerTiempoPlazoOficio(orden.getIdMetodo(), oficio.getBigIdTipoOficio(),
                    TiemposClaveEnum.COM_GEN);
            if (tiempoPlazoGeneracion == null) {
                return true;
            }
            Date fecha = getTiemposService().sumarTiempo(tiempoPlazoGeneracion, fechaCompulsa);
            Calendar fechaMaximaGenCompulsa = Calendar.getInstance();
            fechaMaximaGenCompulsa.setTime(fecha);
            fechaMaximaGenCompulsa.add(Calendar.DAY_OF_YEAR, DIAS_RESOLVER_NYV);
            fecha = fechaMaximaGenCompulsa.getTime();
            return getTiemposHelper().comparaFechasSinTiempo(fecha, new Date()) > 0;
        }
        return true;
    }

    @Override
    public boolean validarPlazoCargaDocumentosRequeridosOrden(AgaceOrden orden) {
        return validarPlazoOrdenConProrroga(orden);
    }

    @Override
    public boolean validarPlazoCargaDocumentosRequeridosOficio(FecetOficio oficio) {
        return validarPlazoOficioConProrroga(oficio);
    }

    @Override
    public boolean validarPlazoCrearProrrogaOficio(FecetOficio oficio) {
        logger.debug("[validarPlazoCrearProrrogaOficio]");
        Date fechaPlazo = obtenerFechaMaxPlazoOficio(oficio);
        if (oficio.getFecetDetalleNyV() != null) {
            fechaPlazo = obtenerFechaFinalEmpalemeAC(oficio.getIdOrden(), oficio.getFecetDetalleNyV().getFecSurteEfectosNyV(), fechaPlazo,
                    TiemposClasificTiemposEnum.DIA);
        }
        return getTiemposHelper().comparaFechasSinTiempo(fechaPlazo, new Date()) >= 0;
    }

    @Override
    public boolean validarPlazoCrearProrrogaOrden(AgaceOrden orden) {
        Date fechaPlazo = obtenerFechaMaxPlazoOrden(orden);
        if (orden.getFecetDetalleNyV() != null) {
            fechaPlazo = obtenerFechaFinalEmpalemeAC(orden.getIdOrden(), orden.getFecetDetalleNyV().getFecSurteEfectosNyV(), fechaPlazo,
                    TiemposClasificTiemposEnum.DIA);
        }
        return getTiemposHelper().comparaFechasSinTiempo(fechaPlazo, new Date()) >= 0;
    }

    private boolean validarPlazoOrdenConProrroga(AgaceOrden orden) {
        Date fechaPlazo = obtenerFechaMaxPlazoOrdenConPorrroga(orden);
        return getTiemposHelper().comparaFechasSinTiempo(fechaPlazo, new Date()) >= 0;
    }

    private boolean validarPlazoOficioConProrroga(FecetOficio oficio) {
        Date fechaPlazo = obtenerFechaMaxPlazoOficioConProrroga(oficio);
        TiposOficiosOrdenesEnum oficioEnum = TiposOficiosOrdenesEnum.parse(oficio.getFecetTipoOficio().getIdTipoOficio().intValue());
        if (!COMPULSAS.contains(oficioEnum)) {
            return getTiemposHelper().comparaFechasSinTiempo(fechaPlazo, new Date()) >= 0;
        }
        return false;
    }

    private Date obtenerFechaMaxPlazoOrden(AgaceOrden orden) {
        asignarFechasNotificacion(orden);
        TiempoDTO tiempoPlazo = getTiemposService().obtenerTiempoPlazo(orden.getIdMetodo(), TiemposClaveEnum.CDO_PLA);
        if (orden.getFecetDetalleNyV().getFecSurteEfectosNyV() != null) {
            return getTiemposService().sumarTiempo(tiempoPlazo, orden.getFecetDetalleNyV().getFecSurteEfectosNyV());
        }
        Date fechaSurteEfectosDefault = getTiemposHelper().sumarDiasHabiles(orden.getFechaNotifNYV(), PlazosConstants.DIAS_MAX_SURTE_EFECTOS);
        return getTiemposService().sumarTiempo(tiempoPlazo, fechaSurteEfectosDefault);
    }

    private Date obtenerFechaMaxPlazoOrdenConPorrroga(AgaceOrden orden) {
        Date fechaPlazo = obtenerFechaMaxPlazoOrden(orden);
        asignarFechasNotificacion(orden);

        List<FecetProrrogaOrden> listProrrogaOrden = prorrogaService.obtenerProrrogasOrdenEstatus(orden,
                EstatusProrroga.RESOLUCION_PRORROGA_FIRMADA_ENVIADA_NYV.getBigIdEstatus(), EstatusProrroga.PRORROGA_NOTIFICADA_CONTRIBUYENTE.getBigIdEstatus());
        if (!listProrrogaOrden.isEmpty()) {
            asignarFechasNotificacion(listProrrogaOrden.get(0));
            TiempoDTO tiempoProrroga = getTiemposService().obtenerTiempoPlazo(orden.getIdMetodo(), TiemposClaveEnum.CDO_PRO);
            if (tiempoProrroga != null) {
                fechaPlazo = getPlazosHelper().sumaDiaNatural(fechaPlazo, 1);
                TiemposClasificTiemposEnum tipoTiempo = TiemposClasificTiemposEnum.parse(tiempoProrroga.getIdTipoPlazo().intValue());
                if (tipoTiempo == TiemposClasificTiemposEnum.DIA) {
                    fechaPlazo = primerDiaHabil(fechaPlazo);
                }
            }
            fechaPlazo = getTiemposService().sumarTiempo(tiempoProrroga, fechaPlazo);
        }
        if (orden.getFecetDetalleNyV() != null) {
            fechaPlazo = obtenerFechaFinalEmpalemeAC(orden.getIdOrden(), orden.getFecetDetalleNyV().getFecSurteEfectosNyV(), fechaPlazo,
                    TiemposClasificTiemposEnum.DIA);
        }
        return fechaPlazo;

    }

    /**
     * Metodo encargado de calcular a fecha limite maxima para cargar
     * documentacion de un oficio contemplando el plazo de las prorrogas.
     * Dependiendo del oficio, se obtiene el plazo para cargar documentacion.
     *
     * @param oficio
     * @return
     */
    private Date obtenerFechaMaxPlazoOficioConProrroga(FecetOficio oficio) {
        logger.debug("[obtenerFechaMaxOficioConProrroga]");
        AgaceOrden orden = agaceOrdenDao.findByPrimaryKey(oficio.getIdOrden());
        asignarFechasNotificacion(orden);
        Date fechaPlazo = obtenerFechaMaxPlazoOficio(oficio);
        List<FecetProrrogaOficio> listProrrogaOficio = fecetProrrogaOficioDao.getProrrogaPorOficioEstatus(oficio.getIdOficio(),
                Constantes.ESTATUS_PRORROGA_NOTIFICADA_CONTRIBUYENTE);

        if (listProrrogaOficio == null || listProrrogaOficio.isEmpty()) {
            listProrrogaOficio = fecetProrrogaOficioDao.getProrrogaPorOficioEstatus(oficio.getIdOficio(),
                    Constantes.ESTATUS_RESOLUCION_PRORROGA_FIRMADA_ENVIADA_NYV);
        }

        if (!listProrrogaOficio.isEmpty()) {
            FecetProrrogaOficio prorrogaOficioAprobada = obtenerProrrogaAprobada(listProrrogaOficio);
            if (prorrogaOficioAprobada != null) {
                asignarFechasNotificacion(prorrogaOficioAprobada);
                TiempoDTO tiempoProrrogaOficio = obtenerPlazoOficio(orden.getIdMetodo(), TiemposClaveEnum.CDOF_PRO,
                        oficio.getFecetTipoOficio().getIdTipoOficio());
                if (tiempoProrrogaOficio != null) {
                    fechaPlazo = getPlazosHelper().sumaDiaNatural(fechaPlazo, 1);
                    TiemposClasificTiemposEnum tipoTiempo = TiemposClasificTiemposEnum.parse(tiempoProrrogaOficio.getIdTipoPlazo().intValue());
                    if (tipoTiempo == TiemposClasificTiemposEnum.DIA) {
                        fechaPlazo = primerDiaHabil(fechaPlazo);
                    }
                    fechaPlazo = tiempoProrrogaOficio.getIdTiempo() != null ? getTiemposService().sumarTiempo(tiempoProrrogaOficio, fechaPlazo) : fechaPlazo;
                }
            }
        }
        if (oficio.getFecetDetalleNyV() != null) {
            fechaPlazo = obtenerFechaFinalEmpalemeAC(orden.getIdOrden(), oficio.getFecetDetalleNyV().getFecSurteEfectosNyV(), fechaPlazo,
                    TiemposClasificTiemposEnum.DIA);
        }

        return fechaPlazo;
    }

    private FecetProrrogaOficio obtenerProrrogaAprobada(List<FecetProrrogaOficio> listaProrrogas) {
        for (FecetProrrogaOficio prorrogaOficio : listaProrrogas) {
            if (prorrogaOficio.getAprobada()) {
                return prorrogaOficio;
            }
        }
        return null;
    }

    /**
     * Metodo encargado de calcular la fecha limite maxima para cargar
     * documentacion de un oficio sin contemplar las prorrogas. Dependiendo el
     * oficio, se obtiene el plazo para cargar documentacion.
     *
     * @param oficio
     * @return
     */
    private Date obtenerFechaMaxPlazoOficio(FecetOficio oficio) {
        logger.debug("[obtenerFechaMaxOficio]");
        AgaceOrden orden = agaceOrdenDao.findByPrimaryKey(oficio.getIdOrden());
        asignarFechasNotificacion(oficio);
        TiempoDTO tiempoPlazo = obtenerPlazoOficio(orden.getIdMetodo(), TiemposClaveEnum.CDOF_PLA, oficio.getFecetTipoOficio().getIdTipoOficio());
        if (oficio.getFecetDetalleNyV() != null && oficio.getFecetDetalleNyV().getFecSurteEfectosNyV() != null && tiempoPlazo != null) {
            return tiempoPlazo.getIdTipoTiempo() != null ? getTiemposService().sumarTiempo(tiempoPlazo, oficio.getFecetDetalleNyV().getFecSurteEfectosNyV())
                    : oficio.getFecetDetalleNyV().getFecSurteEfectosNyV();
        }
        return new Date();

    }

    @Override
    public Date obtenerFechaParaSolicitudOficio(FecetOficio oficio) {
        AgaceOrden orden = agaceOrdenDao.findByPrimaryKey(oficio.getIdOrden());
        asignarFechasNotificacion(orden);
        TiempoDTO tiempoPlazo = obtenerPlazoOficio(orden.getIdMetodo(), TiemposClaveEnum.SIDOF_PLA, oficio.getFecetTipoOficio().getIdTipoOficio());

        Date fecha;

        if ((tiempoPlazo != null) && (orden.getFecetDetalleNyV() != null)) {
            List<FecetOficio> listOficioAvCircuns = fecetOficioDao.getOficioPorIdTipoOficioYorden(oficio.getIdOrden(),
                    TiposOficiosOrdenesEnum.AVISO_CIRCUNSTANCIAL);
            if (listOficioAvCircuns != null && !listOficioAvCircuns.isEmpty()) {
                fecha = listOficioAvCircuns.get(0).getFechaFirma();
                fecha = tiempoPlazo.getIdTipoTiempo() != null ? getTiemposService().sumarTiempo(tiempoPlazo, fecha) : fecha;
                fecha = obtenerFechaFinalEmpalemeAC(orden.getIdOrden(), listOficioAvCircuns.get(0).getFechaFirma(), fecha, TiemposClasificTiemposEnum.DIA);
            } else {
                fecha = new Date();
                fecha = tiempoPlazo.getIdTipoTiempo() != null ? getTiemposService().sumarTiempo(tiempoPlazo, fecha) : fecha;
            }

            return fecha;
        }
        return null;
    }

    @Override
    public Date obtenerFechaMaxSolicitudOficio(FecetOficio oficio) {
        AgaceOrden orden = agaceOrdenDao.findByPrimaryKey(oficio.getIdOrden());
        asignarFechasNotificacion(orden);
        TiempoDTO tiempoPlazo = obtenerPlazoOficio(orden.getIdMetodo(), TiemposClaveEnum.SDOF_PLA, oficio.getFecetTipoOficio().getIdTipoOficio());

        if ((tiempoPlazo != null) && (orden.getFecetDetalleNyV() != null)) {
            TiempoDTO tiempoInicio = getTiemposService().obtenerTiempoPlazo(orden.getIdMetodo(), TiemposClaveEnum.PIO_PLA);
            // OBTENER LA FECHA FIRMA CUANDO ES OFICIO DE OBSERVACIONES O RESOLUCION DEFINITIVA PARA ORG Y REE RESPECTIVAMENTE
            Date fecha = orden.getFecetDetalleNyV().getFecSurteEfectosNyV();
            if (tiempoInicio != null) {
                fecha = getTiemposService().sumarTiempo(tiempoInicio, fecha);
                fecha = getPlazosHelper().sumaDiaNatural(fecha, 1);
                TiemposClasificTiemposEnum tipoTiempo = TiemposClasificTiemposEnum.parse(tiempoPlazo.getIdTipoPlazo().intValue());
                if (tipoTiempo == TiemposClasificTiemposEnum.DIA) {
                    fecha = primerDiaHabil(fecha);
                }
            }

            fecha = tiempoPlazo.getIdTipoTiempo() != null ? getTiemposService().sumarTiempo(tiempoPlazo, fecha) : fecha;
            if (orden.getFecetDetalleNyV() != null) {
                fecha = obtenerFechaFinalEmpalemeAC(orden.getIdOrden(), orden.getFecetDetalleNyV().getFecSurteEfectosNyV(), fecha,
                        TiemposClasificTiemposEnum.DIA);
            }
            return fecha;
        }
        return null;
    }

    @Override
    public boolean validarPlazoSolicitarOficio(AgaceOrden orden, BigDecimal idTipoOficio) {
        FecetOficio oficio = getPlazosHelper().crearOficio(orden, idTipoOficio);
        Date fechaPlazo;

        if ((orden.getIdMetodo().equals(Constantes.ORG)
                && OFICIOS_AVISO_CIRCUNTANCIAL_ORG.contains(idTipoOficio))
                || (orden.getIdMetodo().equals(Constantes.REE)
                && oficio.getFecetTipoOficio().getIdTipoOficio().equals(Constantes.OFICIO_RESOLUCION_DEFINITIVA))) {
            fechaPlazo = obtenerFechaParaSolicitudOficio(oficio);
            if (fechaPlazo != null) {
                return getTiemposHelper().comparaFechasSinTiempo(fechaPlazo, new Date()) < 0;
            } else {
                return false;
            }

        } else {
            fechaPlazo = obtenerFechaMaxSolicitudOficio(oficio);
            if (fechaPlazo != null) {
                return getTiemposHelper().comparaFechasSinTiempo(fechaPlazo, new Date()) >= 0;
            }

        }

        return true;
    }

    @Override
    public TiempoDTO obtenerPlazoOficio(BigDecimal idMetodo, TiemposClaveEnum clave, BigDecimal idTipoOficio) {
        logger.debug("[obtenerPlazo]");
        return getTiemposService().obtenerTiempoPlazoOficio(idMetodo, idTipoOficio, clave);
    }

    @Override
    public void asignarFechasNotificacion(NotificableNyV notificable) {
        if (notificable.getFecetDetalleNyV() != null) {
            consultaRegistraNyVService.asignarFechaNotificable(notificable);
        }
        if (new BigDecimal(notificable.getDescriptor().getEstadoEnvioNotificacion()).intValue() == notificable.getIdEstatus().intValue()) {
            getPlazosDAO().actualizarEstatusNotificable(notificable);
            notificable.setIdEstatus(new BigDecimal(notificable.getDescriptor().getEstadoNotificacionContribuyente()));
            notificable.setFececEstatus(fececEstatusDao.findByPrimaryKey(notificable.getIdEstatus()));
        }
    }

    public void concluirOrdenOficioConcusivo(AgaceOrden orden, FecetOficio oficio) {
        BigDecimal idTipoAsociacionConclusivo = AgrupadorOficiosEnum.CONCLUSIVOS.getBigIdTipoAsociacionOficios();
        if (oficio.getFecetTipoOficio().getAgrupador() != null
                && oficio.getFecetTipoOficio().getAgrupador().getBigIdTipoAsociacionOficios().equals(idTipoAsociacionConclusivo)) {
            getPlazosDAO().concluirNotificable(orden);
        }
    }

    @Override
    public void suspenderPlazoCompulsa(FecetCompulsas compulsa) {
        FecetOficio oficio = compulsa.getOficio();
        AgaceOrden ordenAuditada = agaceOrdenDao.findByPrimaryKey(compulsa.getIdOrdenAuditada());
        if (ordenAuditada.getIdMetodo().intValue() == TipoMetodoEnum.REE.getId()) {
            final Date hoy = new Date();
            asignarFechasNotificacion(oficio);
            Date fechaFinPlazoCompulsa = obtenerFechaMaximaSuspencionCompulsa(compulsa, ordenAuditada);
            getSuspensionService().iniciaSuspensionOrden(ordenAuditada, oficio, hoy, fechaFinPlazoCompulsa);
            logger.debug(String.format("ORDEN [%s] FECHA INICIO SUSPENSION [%s] FECHA FIN SUSPENSION [%s]", oficio.getIdOrden(), hoy, fechaFinPlazoCompulsa));
        }
    }

    @Override
    public void suspenderPlazoOf(AgaceOrden orden, FecetOficio oficio) {
        if (orden.getIdMetodo().intValue() == TipoMetodoEnum.REE.getId()) {
            final Date fechaInicioOficio = new Date();
            asignarFechasNotificacion(oficio);
            Date fechaFinOficio = obtenerFechaMaximaSuspencionOficio(orden, oficio);
            getSuspensionService().iniciaSuspensionOrden(orden, oficio, fechaInicioOficio, fechaFinOficio);
        }
    }

    private Date obtenerFechaMaximaSuspencionCompulsa(FecetCompulsas compulsa, AgaceOrden ordenAuditada) {
        FecetOficio oficio = compulsa.getOficio();
        Date fechaFinal = null;
        List<FecetSuspensionDTO> listSuspension = getSuspensionService().buscarAllSuspensionIdTipoOficio(ordenAuditada.getIdOrden(),
                oficio.getFecetTipoOficio().getIdTipoOficio());
        if (listSuspension.isEmpty()) {
            TiempoDTO plazoCompulsa = getTiemposService().obtenerTiempoPlazoOficio(ordenAuditada.getIdMetodo(), oficio.getFecetTipoOficio().getIdTipoOficio(),
                    TiemposClaveEnum.COM_PLA);
            if (plazoCompulsa != null) {
                fechaFinal = getTiemposService().sumarTiempo(plazoCompulsa, compulsa.getFechaCreacion());
            }
        } else {
            fechaFinal = listSuspension.get(0).getFechaFinSuspension();
        }
        return fechaFinal;
    }

    private Date obtenerFechaMaximaSuspencionOficio(AgaceOrden ordenAuditada, FecetOficio oficio) {
        Date fechaFinal = null;
        boolean isCompulsa = false;
        List<FecetSuspensionDTO> listSuspension = new ArrayList<FecetSuspensionDTO>();
        if (oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.COMPULSA_INTERNACIONAL.getBigIdTipoOficio())
                || oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.OTRAS_AUTORIDADES.getBigIdTipoOficio())) {
            listSuspension = getSuspensionService().buscarAllSuspensionIdTipoOficio(ordenAuditada.getIdOrden(), oficio.getFecetTipoOficio().getIdTipoOficio());
            isCompulsa = true;
        }

        if (listSuspension.isEmpty()) {
            TiempoDTO plazoOficio;
            TiemposClaveEnum tiemposClaveEnum;
            if (isCompulsa) {
                tiemposClaveEnum = TiemposClaveEnum.COM_PLA;
            } else {
                tiemposClaveEnum = TiemposClaveEnum.SUDOF_PLA;
            }
            plazoOficio = getTiemposService().obtenerTiempoPlazoOficio(ordenAuditada.getIdMetodo(), oficio.getFecetTipoOficio().getIdTipoOficio(),
                    tiemposClaveEnum);

            if (plazoOficio != null) {
                TiemposClasificTiemposEnum tipoTiempo = TiemposClasificTiemposEnum.parse(plazoOficio.getIdTipoPlazo().intValue());
                if (tipoTiempo == TiemposClasificTiemposEnum.DIA) {
                    plazoOficio.setTiempo(plazoOficio.getTiempo());
                }
                fechaFinal = getTiemposService().sumarTiempo(plazoOficio, oficio.getFechaCreacion());
                if (tipoTiempo == TiemposClasificTiemposEnum.DIA && fechaFinal != null) {
                    Calendar fechaHoy = Calendar.getInstance();
                    fechaHoy.setTime(fechaFinal);
                    fechaHoy.add(Calendar.DAY_OF_YEAR, 1);
                    fechaFinal = fechaHoy.getTime();
                }
            }
        } else {
            fechaFinal = listSuspension.get(0).getFechaFinSuspension();
        }
        return fechaFinal;
    }

    @Override
    public boolean esDocumentoExtemporaneoOrden(AgaceOrden orden, final Date fechaCargaDocumento) {
        AgaceOrden ordenBD = agaceOrdenDao.findByPrimaryKey(orden.getIdOrden());
        Date fechaPlazoOrden = obtenerFechaMaxPlazoOrdenConPorrroga(ordenBD);
        return getTiemposHelper().comparaFechasSinTiempo(fechaPlazoOrden, fechaCargaDocumento) < 0;
    }

    @Override
    public boolean esDocumentoExtemporaneoOficio(FecetOficio oficio, final Date fechaCargaDocumento) {

        Date fechaPlazoOficio = obtenerFechaMaxPlazoOficioConProrroga(oficio);
        logger.error(fechaPlazoOficio + "");
        logger.error(fechaCargaDocumento + "");
        return getTiemposHelper().comparaFechasSinTiempo(fechaPlazoOficio, fechaCargaDocumento) < 0;
    }

    @Override
    public List<AgaceOrden> filtraOrdenPorFecha(List<AgaceOrden> listOrden) {
        logger.debug("[filtraOrdenPorFecha]");
        List<AgaceOrden> nuevaLista = new ArrayList<AgaceOrden>();
        for (AgaceOrden orden : listOrden) {
            if (consultaRegistraNyVService.filtrarNotificablePorFecha(orden)) {
                FecetDetalleNyV detalleNyV = orden.getFecetDetalleNyV();
                asignarFechasNotificacion(orden);
                AgaceOrden ordenBD = agaceOrdenDao.findByPrimaryKey(orden.getIdOrden());
                ordenBD.setFecetDetalleNyV(detalleNyV);
                ordenBD.setFeceaMetodo(orden.getFeceaMetodo());
                ordenBD.setFecetContribuyente(orden.getFecetContribuyente());
                ordenBD.setFececEstatus(orden.getFececEstatus());
                ordenBD.setFecetAsociado(orden.getFecetAsociado());
                inicializarOrdenConPlazos(ordenBD);
                nuevaLista.add(ordenBD);
            }
        }
        return nuevaLista;
    }

    @Override
    public void inicializarOrdenConPlazos(AgaceOrden orden) {
        TiempoDTO tiempoPlazo = getTiemposService().obtenerTiempoPlazo(orden.getIdMetodo(), TiemposClaveEnum.CDO_PLA);
        orden.setDiasRestantesDocumentos(obtenerDiasRestantes(orden, tiempoPlazo));
        orden.setDescripcionDiasRestanteDocumentos(getPlazosHelper().convertirDiasRestantesTexto(orden.getDiasRestantesDocumentos(), tiempoPlazo));
        tiempoPlazo = getTiemposService().obtenerTiempoPlazo(orden.getIdMetodo(), TiemposClaveEnum.PTO_PLA);
        orden.setSemaforo(asignarSemaforo(orden, tiempoPlazo));
        orden.setDiasRestantesPlazo(obtenerDiasRestantes(orden, tiempoPlazo));
        orden.setDescripcionPlazoRestante(getPlazosHelper().convertirDiasRestantesTexto(orden.getDiasRestantesPlazo(), tiempoPlazo));
    }

    public void inicializarOficiosConPlazos(AgaceOrden orden, FecetOficio oficio) {
        TiempoDTO tiempoPlazo = getTiemposService().obtenerTiempoPlazoOficio(orden.getIdMetodo(), oficio.getFecetTipoOficio().getIdTipoOficio(),
                TiemposClaveEnum.CDOF_PLA);
        if (tiempoPlazo != null) {
            oficio.setDiasRestantesDocumentos(new BigDecimal(obtenerDiasRestantesOficio(orden, oficio, tiempoPlazo)));
            oficio.setDescripcionPlazoRestante(getPlazosHelper().convertirDiasRestantesTexto(oficio.getDiasRestantesDocumentos().intValueExact(), tiempoPlazo));
            esDocumentoExtemporaneoOficio(oficio, oficio.getFechaCreacion());
        }
    }

    @Override
    public void registrarActoAdministrativo(NotificableNyV notificable, FececActosAdm actoBD) throws NegocioException {
        logger.debug("[registraActoAdministrativoOficio]");

        FecetDetalleNyV detalleNyV = consultaRegistraNyVService.registrarActoAdministrativo(notificable,
                getTiemposService().obtenerDiasHabiles(new Date(), DIAS_HABILES_DILIGENCIA), actoBD);

        if (notificable.getDatosNotificable().isInsertaNyV()) {
            notificable.setFecetDetalleNyV(detalleNyV);
            getPlazosDAO().actualizaNotificableIdNyV(notificable);
        }
    }

    @Override
    public List<FecetOficio> filtarOficiosPorFecha(AgaceOrden orden, List<FecetOficio> listOficio) {
        List<FecetOficio> nuevaListOficio = new ArrayList<FecetOficio>();
        for (FecetOficio oficio : listOficio) {
            if (consultaRegistraNyVService.filtrarNotificablePorFecha(oficio)) {
                asignarFechasNotificacion(orden);
                asignarFechasNotificacion(oficio);
                final List<FecetOficio> listaOficiosDependientes = fecetOficioDao.getOficiosDependientesByIdOficioPrincipal(oficio.getIdOficio());
                for (FecetOficio oficioDependiente : listaOficiosDependientes) {
                    asignarFechasNotificacion(oficioDependiente);
                }
                inicializarOficiosConPlazos(orden, oficio);
                concluirOrdenOficioConcusivo(orden, oficio);
                nuevaListOficio.add(oficio);
            }
        }
        return nuevaListOficio;
    }

    @Override
    public List<FecetOficio> filtarOficios(List<FecetOficio> listOficio) {
        List<FecetOficio> nuevaListOficio = new ArrayList<FecetOficio>();
        for (FecetOficio oficio : listOficio) {
            if (consultaRegistraNyVService.filtrarNotificablePorFecha(oficio)) {
                nuevaListOficio.add(oficio);
            }
        }

        return nuevaListOficio;
    }

    @Override
    public void asignarFechasNyVOficio(AgaceOrden orden, List<FecetOficio> listOficio) {
        for (FecetOficio oficio : listOficio) {
            consultaRegistraNyVService.asignarFechaNotificable(oficio);
            asignarFechasNotificacion(orden);
            asignarFechasNotificacion(oficio);
            inicializarOficiosConPlazos(orden, oficio);
        }
    }

    @Override
    public List<FecetCompulsas> filtarCompulsasPorFecha(List<FecetCompulsas> listCompulsas) {
        logger.debug("[filtraOficioPorFecha]");
        List<FecetCompulsas> nuevaListCompulsas = new ArrayList<FecetCompulsas>();
        for (FecetCompulsas compulsa : listCompulsas) {
            if (consultaRegistraNyVService.filtrarNotificablePorFecha(compulsa.getOficio())) {
                asignarFechasNotificacion(compulsa.getOrden());
                asignarFechasNotificacion(compulsa.getOficio());
                inicializarOficiosConPlazos(compulsa.getOrden(), compulsa.getOficio());
                nuevaListCompulsas.add(compulsa);
            }
        }
        return nuevaListCompulsas;
    }

    private int obtenerDiasRestantes(AgaceOrden orden, TiempoDTO tiempoPlazo) {
        Date fecha = this.obetenFechaDiasRestantes(orden, tiempoPlazo);
        return regresaDiasRestantes(fecha, tiempoPlazo);
    }

    private int obtenerDiasRestantesOficio(AgaceOrden orden, FecetOficio oficio, TiempoDTO tiempoPlazo) {
        if (oficio.getFecetDetalleNyV() != null && (tiempoPlazo != null && oficio.getFecetDetalleNyV().getFecSurteEfectosNyV() != null)) {
            Date fecha = getTiemposService().sumarTiempo(tiempoPlazo, oficio.getFecetDetalleNyV().getFecSurteEfectosNyV());
            fecha = sumarDiasProrrogaOficio(fecha, orden, oficio);

            return regresaDiasRestantes(fecha, tiempoPlazo);
        }
        return CERO;
    }

    private int asignarSemaforo(AgaceOrden orden, TiempoDTO tipo) {
        if (orden.getIdEstatus().intValue() == EstatusOrdenes.CONCLUIDA.getIdEstatus()
                || orden.getIdEstatus().intValue() == EstatusOrdenes.CONCLUIDA_POR_CAMBIO_METODO.getIdEstatus()) {
            return SEIS;
        }
        if (orden.getIdMetodo().intValue() == TRES || orden.getIdMetodo().intValue() == CINCO) {
            // si es UCA o MCA id_metodo(3,5) semaforo en gris SEMG=7
            return SIETE;
        }
        if (suspensionOrden(orden)) {

            // TODO si esta suspendida semaforo cafe = 5
            return CINCO;
        }

        int diasrestantes = this.obtenerDiasRestantes(orden, tipo);
        if (diasrestantes == CERO && orden.getIdEstatus().intValue() == EstatusOrdenes.CONCLUIDA.getIdEstatus()) {
            // TODO semaforo en azul
            return SEIS;
        }
        if (diasrestantes == CERO && orden.getIdEstatus().intValue() != EstatusOrdenes.CONCLUIDA.getIdEstatus()) {
            return OCHO;
        }

        Date fechaFin = this.obetenFechaDiasRestantes(orden, tipo);
        return obtenerColorSemaforo(fechaFin, orden.getIdMetodo());
    }

    private Date sumaDiasSuspension(Date fechaParam, AgaceOrden orden, TiempoDTO tiempoPlazo) {
        Date fecha = fechaParam;

        List<FecetSuspensionDTO> suspensiones = getSuspensionService().buscarSuspensionesOrden(orden.getIdOrden());
        FecetSuspensionDTO suspensionAc = getSuspensionService().buscarSuspensionAc(orden.getIdOrden());
        TiemposClasificTiemposEnum tipoTiempo = TiemposClasificTiemposEnum.parse(tiempoPlazo.getIdTipoPlazo().intValue());

        if (suspensiones != null && !suspensiones.isEmpty()) {
            sumarTiempoEmpalmadoAc(suspensiones, suspensionAc);
            int sumaDiasSuspension = 0;
            Date fechaFinSuspension = getPlazosHelper().obtenerUltimaSuspensionTerminada(suspensiones);
            if (fechaFinSuspension != null) {
                fecha = getTiemposService().sumarTiempo(tiempoPlazo, fechaFinSuspension);
                getPlazosHelper().eliminarTiempoSuspensionTerminada(suspensiones, fechaFinSuspension);
            }
            suspensiones = getPlazosHelper().elimarFechasSuspensionRepetidas(suspensiones);
            for (FecetSuspensionDTO suspensionDTO : suspensiones) {
                if (getTiemposHelper().comparaFechasSinTiempo(new Date(), suspensionDTO.getFechaInicioSuspension()) >= 0) {
                    Calendar fechaInicio = Calendar.getInstance();
                    Calendar fechaHoy = Calendar.getInstance();
                    fechaInicio.setTime(suspensionDTO.getFechaInicioSuspension());
                    getTiemposHelper().reseteaHoraFecha(fechaInicio);
                    if (getTiemposHelper().comparaFechasSinTiempo(new Date(), suspensionDTO.getFechaFinSuspension()) >= 0) {
                        fechaHoy.setTime(suspensionDTO.getFechaFinSuspension());
                        // fechaHoy.add(Calendar.DAY_OF_YEAR,-1)
                    }
                    getTiemposHelper().reseteaHoraFecha(fechaHoy);

                    switch (tipoTiempo) {
                        case DIA:
                            sumaDiasSuspension += getTiemposService().obtenerDiasRestantesHabiles(fechaInicio.getTime(), fechaHoy.getTime());
                            break;
                        default:
                            sumaDiasSuspension += getTiemposService().obtenerDiasRestantesNaturales(fechaInicio.getTime(), fechaHoy.getTime());
                    }
                }
            }
            if (sumaDiasSuspension > 0) {
                switch (tipoTiempo) {
                    case DIA:
                        Date fechaRee = getTiemposService().obtenerDiasHabiles(fecha, sumaDiasSuspension);
                        if (orden.getIdMetodo().equals(new BigDecimal(TipoMetodoEnum.REE.getId()))) {
                            int sumaDias = getTiemposService().obtenerDiasRestantesHabiles(new Date(), fechaRee);
                            if (sumaDias > NUM_40) {
                                fechaRee = getTiemposService().sumarTiempo(tiempoPlazo, new Date());
                            }
                        }
                        return fechaRee;
                    default:
                        return getTiemposService().obtenerDiasNaturales(fecha, sumaDiasSuspension);
                }
            }
            return fecha;
        } else {
            return fecha;
        }
    }

    private void sumarTiempoEmpalmadoAc(List<FecetSuspensionDTO> suspensiones, FecetSuspensionDTO acuerdoConclusivo) {
        if (suspensiones != null && !suspensiones.isEmpty() && acuerdoConclusivo != null
                && getTiemposHelper().comparaFechasSinTiempo(acuerdoConclusivo.getFechaInicioSuspension(), acuerdoConclusivo.getFechaFinSuspension()) != 0) {
            int sumaTiempoEmpalmado = 0;
            Calendar calendarFechaAc = Calendar.getInstance();
            Calendar calendarFechaSuspension = Calendar.getInstance();
            for (FecetSuspensionDTO suspension : suspensiones) {
                if (getTiemposHelper().comparaFechasSinTiempo(suspension.getFechaFinSuspension(), acuerdoConclusivo.getFechaInicioSuspension()) > 0
                        && suspension.getIdObjeto() != null && suspension.getFechaBaja() == null) {
                    if (getTiemposHelper().comparaFechasSinTiempo(acuerdoConclusivo.getFechaInicioSuspension(), suspension.getFechaInicioSuspension()) >= 0
                            && getTiemposHelper().comparaFechasSinTiempo(suspension.getFechaFinSuspension(), acuerdoConclusivo.getFechaFinSuspension()) >= 0) {

                        calendarFechaAc.setTime(acuerdoConclusivo.getFechaFinSuspension());
                        calendarFechaAc.add(Calendar.DAY_OF_YEAR, -1);

                        sumaTiempoEmpalmado = getTiemposService().obtenerDiasRestantesHabiles(acuerdoConclusivo.getFechaInicioSuspension(),
                                calendarFechaAc.getTime());

                        calendarFechaSuspension.setTime(suspension.getFechaFinSuspension());
                        calendarFechaSuspension.add(Calendar.DAY_OF_YEAR, -1);
                        calendarFechaSuspension.setTime(getTiemposService().obtenerDiasHabiles(calendarFechaSuspension.getTime(), sumaTiempoEmpalmado));
                        calendarFechaSuspension.add(Calendar.DAY_OF_YEAR, 1);
                        suspension.setFechaFinSuspension(calendarFechaSuspension.getTime());
                    } else {
                        if (getTiemposHelper().comparaFechasSinTiempo(acuerdoConclusivo.getFechaInicioSuspension(), suspension.getFechaInicioSuspension()) >= 0
                                && getTiemposHelper().comparaFechasSinTiempo(acuerdoConclusivo.getFechaFinSuspension(),
                                        suspension.getFechaFinSuspension()) >= 0) {
                            calendarFechaSuspension.setTime(suspension.getFechaFinSuspension());
                            calendarFechaSuspension.add(Calendar.DAY_OF_YEAR, -1);

                            sumaTiempoEmpalmado = getTiemposService().obtenerDiasRestantesHabiles(acuerdoConclusivo.getFechaInicioSuspension(),
                                    calendarFechaSuspension.getTime());
                            calendarFechaAc.setTime(acuerdoConclusivo.getFechaFinSuspension());
                            calendarFechaAc.add(Calendar.DAY_OF_YEAR, -1);

                            calendarFechaSuspension.setTime(getTiemposService().obtenerDiasHabiles(calendarFechaAc.getTime(), sumaTiempoEmpalmado));
                            calendarFechaSuspension.add(Calendar.DAY_OF_YEAR, 1);
                            suspension.setFechaFinSuspension(calendarFechaSuspension.getTime());
                        }
                    }
                }
            }
        }
    }

    private Date sumaDiasProrrogaOrden(Date fechaParam, AgaceOrden orden, TiempoDTO tiempoPlazo) {
        Date fecha = fechaParam;
        List<FecetProrrogaOrden> listProrrogaOrden = prorrogaService.obtenerProrrogasOrdenEstatus(orden,
                EstatusProrroga.RESOLUCION_PRORROGA_FIRMADA_ENVIADA_NYV.getBigIdEstatus(), EstatusProrroga.PRORROGA_NOTIFICADA_CONTRIBUYENTE.getBigIdEstatus());

        if (!tiempoPlazo.getClave().equals(TiemposClaveEnum.PTO_PLA.getClave()) && !listProrrogaOrden.isEmpty()) {
            fecha = getPlazosHelper().sumaDiaNatural(fecha, 1);
            TiemposClasificTiemposEnum tipoTiempo = TiemposClasificTiemposEnum.parse(tiempoPlazo.getIdTipoPlazo().intValue());
            if (tipoTiempo == TiemposClasificTiemposEnum.DIA) {
                fecha = primerDiaHabil(fecha);
            }
            TiempoDTO tiempoProrroga = getTiemposService().obtenerTiempoPlazo(orden.getIdMetodo(), TiemposClaveEnum.CDO_PRO);
            return getTiemposService().sumarTiempo(tiempoProrroga, fecha);
        }
        return fecha;
    }

    private Date sumarDiasProrrogaOficio(Date fechaParam, AgaceOrden orden, FecetOficio oficio) {
        Date fecha = fechaParam;
        List<FecetProrrogaOficio> listProrrogaOficio = fecetProrrogaOficioDao.getProrrogaPorOficioEstatus(oficio.getIdOficio(),
                EstatusProrroga.PRORROGA_NOTIFICADA_CONTRIBUYENTE.getBigIdEstatus());
        if (listProrrogaOficio.isEmpty()) {
            listProrrogaOficio = fecetProrrogaOficioDao.getProrrogaPorOficioEstatus(oficio.getIdOficio(),
                    EstatusProrroga.RESOLUCION_PRORROGA_FIRMADA_ENVIADA_NYV.getBigIdEstatus());
        }

        if (!listProrrogaOficio.isEmpty()) {
            TiempoDTO tiempoProrrogaOficio = obtenerPlazoOficio(orden.getIdMetodo(), TiemposClaveEnum.CDOF_PRO, oficio.getFecetTipoOficio().getIdTipoOficio());
            return tiempoProrrogaOficio.getIdTiempo() != null ? getTiemposService().sumarTiempo(tiempoProrrogaOficio, fecha) : fecha;
        }

        return fecha;
    }

    @Override
    public void obtenerFechasNotificacion(FecetOficio oficio) {
        consultaRegistraNyVService.asignarFechaNotificable(oficio);
    }

    private Date obetenFechaDiasRestantes(AgaceOrden orden, TiempoDTO tiempoPlazo) {
        TiempoDTO tiempoInicio = getTiemposService().obtenerTiempoPlazo(orden.getIdMetodo(), TiemposClaveEnum.PIO_PLA);
        Date fechaDiasRestantes = getTiemposService().sumarTiempo(tiempoInicio, orden.getFecetDetalleNyV().getFecSurteEfectosNyV());

        if (orden.getFeceaMetodo().getIdMetodo().compareTo(new BigDecimal(TipoMetodoEnum.EHO.getId())) == 0
                && tiempoPlazo.getClave().equals(TiemposClaveEnum.PTO_PLA.getClave())) {
            if (orden.getFechaIntegraExp() == null) {
                fechaDiasRestantes = getTiemposService().sumarTiempo(tiempoPlazo, new Date());
            } else {
                fechaDiasRestantes = getTiemposService().sumarTiempo(tiempoPlazo, orden.getFechaIntegraExp());
            }
            fechaDiasRestantes = sumaDiasSuspension(fechaDiasRestantes, orden, tiempoPlazo);
        } else {
            if (!tiempoPlazo.getClave().equals(TiemposClaveEnum.CDO_PLA.getClave())) {
                if (getTiemposHelper().comparaFechasSinTiempo(fechaDiasRestantes, new Date()) >= 0) {
                    fechaDiasRestantes = getTiemposService().sumarTiempo(tiempoPlazo, new Date());
                } else {
                    if (tiempoInicio != null) {
                        fechaDiasRestantes = getPlazosHelper().sumaDiaNatural(fechaDiasRestantes, 1);
                        TiemposClasificTiemposEnum tipoTiempo = TiemposClasificTiemposEnum.parse(tiempoPlazo.getIdTipoPlazo().intValue());
                        if (tipoTiempo == TiemposClasificTiemposEnum.DIA) {
                            fechaDiasRestantes = primerDiaHabil(fechaDiasRestantes);
                        }
                    }
                    fechaDiasRestantes = getTiemposService().sumarTiempo(tiempoPlazo, fechaDiasRestantes);
                    fechaDiasRestantes = sumaDiasSuspension(fechaDiasRestantes, orden, tiempoPlazo);
                }
            } else {
                fechaDiasRestantes = getTiemposService().sumarTiempo(tiempoPlazo, orden.getFecetDetalleNyV().getFecSurteEfectosNyV());

                if (orden.getFecetDetalleNyV() != null) {
                    fechaDiasRestantes = obtenerFechaFinalEmpalemeAC(orden.getIdOrden(), orden.getFecetDetalleNyV().getFecSurteEfectosNyV(), fechaDiasRestantes,
                            TiemposClasificTiemposEnum.DIA);
                }
            }
        }

        fechaDiasRestantes = extensionFechaTipoOficio(orden, fechaDiasRestantes, tiempoPlazo);
        return sumaDiasProrrogaOrden(fechaDiasRestantes, orden, tiempoPlazo);
    }

    private Date obtenerFechaFinalEmpalemeAC(BigDecimal idOrden, Date fechaInicio, Date fechaFinParam, TiemposClasificTiemposEnum tipoTiempo) {
        FecetSuspensionDTO suspensionAc = getSuspensionService().buscarSuspensionAc(idOrden);
        Date fechaFin = fechaFinParam;
        int sumaTiempoEmpalmado = 0;
        Calendar calendarFechaAc = Calendar.getInstance();

        if (suspensionAc != null && getTiemposHelper().comparaFechasSinTiempo(fechaFin, suspensionAc.getFechaInicioSuspension()) >= 0 && fechaInicio != null
                && fechaFin != null) {
            if (getTiemposHelper().comparaFechasSinTiempo(suspensionAc.getFechaInicioSuspension(), fechaInicio) >= 0
                    && getTiemposHelper().comparaFechasSinTiempo(fechaFin, suspensionAc.getFechaFinSuspension()) >= 0) {

                calendarFechaAc.setTime(suspensionAc.getFechaFinSuspension());

                switch (tipoTiempo) {
                    case DIA:
                        calendarFechaAc.add(Calendar.DAY_OF_YEAR, -1);
                        sumaTiempoEmpalmado = getTiemposService().obtenerDiasRestantesHabiles(suspensionAc.getFechaInicioSuspension(), calendarFechaAc.getTime());
                        if (sumaTiempoEmpalmado > 0) {
                            calendarFechaAc.setTime(fechaFin);
                            calendarFechaAc.add(Calendar.DAY_OF_YEAR, 1);
                            fechaFin = calendarFechaAc.getTime();
                        }

                        return getTiemposService().obtenerDiasHabiles(fechaFin, sumaTiempoEmpalmado);
                    default:
                        sumaTiempoEmpalmado = getTiemposService().obtenerDiasRestantesNaturales(suspensionAc.getFechaInicioSuspension(), calendarFechaAc.getTime());
                        return getTiemposService().obtenerDiasNaturales(fechaFin, sumaTiempoEmpalmado);
                }
            } else {
                if (getTiemposHelper().comparaFechasSinTiempo(suspensionAc.getFechaInicioSuspension(), fechaInicio) >= 0
                        && getTiemposHelper().comparaFechasSinTiempo(suspensionAc.getFechaFinSuspension(), fechaInicio) >= 0) {

                    switch (tipoTiempo) {
                        case DIA:
                            sumaTiempoEmpalmado = getTiemposService().obtenerDiasRestantesHabiles(suspensionAc.getFechaInicioSuspension(), fechaFin);
                            return getTiemposService().obtenerDiasHabiles(suspensionAc.getFechaFinSuspension(), sumaTiempoEmpalmado);
                        default:
                            sumaTiempoEmpalmado = getTiemposService().obtenerDiasRestantesNaturales(suspensionAc.getFechaInicioSuspension(), fechaFin);
                            return getTiemposService().obtenerDiasNaturales(suspensionAc.getFechaFinSuspension(), sumaTiempoEmpalmado);
                    }
                }

            }

        }

        return fechaFin;
    }

    @Override
    public boolean suspensionPruebasPericiliales(AgaceOrden orden) {
        return getSuspensionService().estaSuspendidaPorPruebasPericiales(orden.getIdOrden());
    }

    @Override
    public boolean suspensionOrden(AgaceOrden orden) {
        List<FecetSuspensionDTO> suspensiones = getSuspensionService().buscarSuspensionesOrden(orden.getIdOrden());
        FecetSuspensionDTO suspensionAc = getSuspensionService().buscarSuspensionAc(orden.getIdOrden());
        if (orden.getIdMetodo().equals(new BigDecimal(TipoMetodoEnum.REE.getId())) && suspensiones != null && !suspensiones.isEmpty()) {
            sumarTiempoEmpalmadoAc(suspensiones, suspensionAc);
        }
        if (suspensiones != null && !suspensiones.isEmpty()) {
            for (FecetSuspensionDTO suspension : suspensiones) {
                if (getTiemposHelper().comparaFechasSinTiempo(new Date(), suspension.getFechaInicioSuspension()) >= 0
                        && getTiemposHelper().comparaFechasSinTiempo(suspension.getFechaFinSuspension(), new Date()) > 0) {
                    return true;

                }
            }
        }

        return false;
    }

    @Override
    public boolean suspensionOrdenReactivacion(AgaceOrden orden) {
        return getSuspensionService().estaSuspendidaReactivacion(orden.getIdOrden());
    }

    private Date extensionFechaTipoOficio(AgaceOrden orden, Date fechaDiasRestantesParam, TiempoDTO tiempoPlazo) {
        Date fechaDiasRestantes = fechaDiasRestantesParam;

        if (tiempoPlazo.getClave().equals(TiemposClaveEnum.PTO_PLA.getClave())) {
            if (isOrdenOrg(orden)) {
                return extensionTiempoOrg(fechaDiasRestantes, orden);
            }
            if (isOrdenUcaMca(orden)) {
                fechaDiasRestantes = obtenerFechaMaxPlazoTotalOrdenConPorrroga(orden);
                List<FecetOficio> listaOficioSCIM;
                if (orden.getIdMetodo().equals(new BigDecimal(TipoMetodoEnum.UCA.getId()))) {
                    listaOficioSCIM = fecetOficioDao.getOficioPorIdTipoOficioYorden(orden.getIdOrden(), TiposOficiosOrdenesEnum.SEGUNDA_UNICA_CARTA_INVITACION);
                } else {
                    listaOficioSCIM = fecetOficioDao.getOficioPorIdTipoOficioYorden(orden.getIdOrden(),
                            TiposOficiosOrdenesEnum.SEGUNDA_UNICA_CARTA_INVITACION_MASIVA);
                }

                if (listaOficioSCIM != null && !listaOficioSCIM.isEmpty()) {
                    FecetOficio oficio = listaOficioSCIM.get(0);
                    TiempoDTO tiempoOficio = getTiemposService().obtenerTiempoPlazoOficio(orden.getIdMetodo(), oficio.getFecetTipoOficio().getIdTipoOficio(),
                            TiemposClaveEnum.CDOF_PLA);
                    fechaDiasRestantes = getPlazosHelper().sumaDiaNatural(fechaDiasRestantes, 1);
                    fechaDiasRestantes = getTiemposService().sumarTiempo(tiempoOficio, fechaDiasRestantes);
                    List<FecetProrrogaOficio> listProrrogaOficio = fecetProrrogaOficioDao.getProrrogaPorOficioEstatus(oficio.getIdOficio(),
                            Constantes.ESTATUS_PRORROGA_NOTIFICADA_CONTRIBUYENTE);

                    if (listProrrogaOficio == null || listProrrogaOficio.isEmpty()) {
                        listProrrogaOficio = fecetProrrogaOficioDao.getProrrogaPorOficioEstatus(oficio.getIdOficio(),
                                Constantes.ESTATUS_RESOLUCION_PRORROGA_FIRMADA_ENVIADA_NYV);
                    }

                    if (listProrrogaOficio != null && !listProrrogaOficio.isEmpty()) {
                        TiempoDTO tiempoOficioProrroga = getTiemposService().obtenerTiempoPlazoOficio(orden.getIdMetodo(),
                                oficio.getFecetTipoOficio().getIdTipoOficio(), TiemposClaveEnum.CDOF_PRO);
                        fechaDiasRestantes = getPlazosHelper().sumaDiaNatural(fechaDiasRestantes, 1);
                        fechaDiasRestantes = getTiemposService().sumarTiempo(tiempoOficioProrroga, fechaDiasRestantes);
                    }
                }

                return fechaDiasRestantes;
            }
        }
        return fechaDiasRestantes;
    }

    private boolean isOrdenUcaMca(AgaceOrden orden) {
        if (orden.getIdMetodo().equals(new BigDecimal(TipoMetodoEnum.UCA.getId())) || orden.getIdMetodo().equals(new BigDecimal(TipoMetodoEnum.MCA.getId()))) {
            return true;
        }
        return false;
    }

    private boolean isOrdenOrg(AgaceOrden orden) {
        if (orden.getIdMetodo().equals(new BigDecimal(TipoMetodoEnum.ORG.getId()))) {
            return true;
        }
        return false;
    }

    private Date extensionTiempoOrg(Date fechaDiasRestantesParam, AgaceOrden orden) {
        TiempoDTO tiempoExtension = getTiemposService().obtenerTiempoPlazoOficio(orden.getIdMetodo(),
                TiposOficiosOrdenesEnum.OBSERVACIONES_REVISION_ESCRITORIO.getBigIdTipoOficio(), TiemposClaveEnum.EXT);
        List<FecetOficio> listaOficioObservaciones = fecetOficioDao.getOficioPorIdTipoOficioYorden(orden.getIdOrden(),
                TiposOficiosOrdenesEnum.OBSERVACIONES_REVISION_ESCRITORIO);
        Date fechaDiasRestantes = fechaDiasRestantesParam;
        if (listaOficioObservaciones != null && !listaOficioObservaciones.isEmpty()) {
            FecetOficio oficio = listaOficioObservaciones.get(0);
            asignarFechasNotificacion(oficio);
            Date fechaPlazo = obtenerFechaMaxPlazoOficioConProrroga(oficio);
            if (getTiemposHelper().comparaFechasSinTiempo(fechaPlazo, new Date()) < 0) {
                fechaPlazo = getPlazosHelper().sumaDiaNatural(fechaPlazo, 1);
                fechaDiasRestantes = getTiemposService().sumarTiempo(tiempoExtension, fechaPlazo);
                fechaDiasRestantes = obtenerFechaFinalEmpalemeAC(orden.getIdOrden(), fechaPlazo, fechaDiasRestantes, TiemposClasificTiemposEnum.MES);
                return fechaDiasRestantes;
            } else {
                if (getTiemposHelper().comparaFechasSinTiempo(oficio.getFechaFirma(), new Date()) < 0) {
                    Calendar fechaInicio = Calendar.getInstance();
                    Calendar fechaHoy = Calendar.getInstance();
                    fechaInicio.setTime(oficio.getFechaFirma());
                    fechaHoy.setTime(new Date());
                    getTiemposHelper().reseteaHoraFecha(fechaInicio);
                    getTiemposHelper().reseteaHoraFecha(fechaHoy);
                    double diferencia = fechaHoy.getTimeInMillis() - fechaInicio.getTimeInMillis();
                    double dias = Math.floor(diferencia / DIA_MILISEGUNDOS);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(fechaDiasRestantes);
                    FecetSuspensionDTO suspensionAc = getSuspensionService().buscarSuspensionAc(orden.getIdOrden());
                    if (isEmpalmeOrgAc(suspensionAc, oficio.getFechaFirma(), fechaPlazo)) {
                        int ac = getTiemposService().obtenerDiasRestantesNaturales(oficio.getFechaFirma(), new Date());
                        int oficioDias = getTiemposService().obtenerDiasRestantesNaturales(suspensionAc.getFechaInicioSuspension(),
                                suspensionAc.getFechaFinSuspension());

                        if (ac != oficioDias) {
                            int numeroDiasTotal = ac - oficioDias;
                            calendar.add(Calendar.DAY_OF_YEAR, numeroDiasTotal);
                        }

                        return calendar.getTime();
                    } else {
                        calendar.add(Calendar.DAY_OF_YEAR, (int) dias);
                        return calendar.getTime();
                    }

                }
            }
        }
        TiempoDTO tiempoExtensionCompulsa = getTiemposService().obtenerTiempoPlazoOficio(orden.getIdMetodo(),
                TiposOficiosOrdenesEnum.COMPULSA_INTERNACIONAL.getBigIdTipoOficio(), TiemposClaveEnum.EXT);
        List<FecetOficio> listaCompulsaInternacional = fecetOficioDao.getOficioPorIdTipoOficioYorden(orden.getIdOrden(),
                TiposOficiosOrdenesEnum.COMPULSA_INTERNACIONAL);
        if (!listaCompulsaInternacional.isEmpty() && tiempoExtensionCompulsa != null) {
            fechaDiasRestantes = getTiemposService().sumarTiempo(tiempoExtensionCompulsa, listaCompulsaInternacional.get(0).getFechaFirma());
            return obtenerFechaFinalEmpalemeAC(orden.getIdOrden(), listaCompulsaInternacional.get(0).getFechaFirma(), fechaDiasRestantes,
                    TiemposClasificTiemposEnum.MES);
        }
        return fechaDiasRestantes;
    }

    private boolean isEmpalmeOrgAc(FecetSuspensionDTO suspensionAc, Date fechaInicio, Date fechaFin) {

        if (suspensionAc != null && getTiemposHelper().comparaFechasSinTiempo(suspensionAc.getFechaInicioSuspension(), fechaInicio) >= 0
                && getTiemposHelper().comparaFechasSinTiempo(fechaFin, suspensionAc.getFechaInicioSuspension()) >= 0) {
            return true;
        }

        return false;
    }

    private Date obtenerFechaMaxPlazoTotalOrdenConPorrroga(AgaceOrden orden) {
        Date fechaPlazo = obtenerFechaMaxPlazoTotalOrden(orden);

        List<FecetProrrogaOrden> listProrrogaOrden = prorrogaService.obtenerProrrogasOrdenEstatus(orden,
                EstatusProrroga.RESOLUCION_PRORROGA_FIRMADA_ENVIADA_NYV.getBigIdEstatus(), EstatusProrroga.PRORROGA_NOTIFICADA_CONTRIBUYENTE.getBigIdEstatus());
        if (listProrrogaOrden != null && !listProrrogaOrden.isEmpty()) {
            asignarFechasNotificacion(listProrrogaOrden.get(0));
            TiempoDTO tiempoProrroga = getTiemposService().obtenerTiempoPlazo(orden.getIdMetodo(), TiemposClaveEnum.CDO_PRO);
            if (tiempoProrroga != null) {
                fechaPlazo = getPlazosHelper().sumaDiaNatural(fechaPlazo, 1);
                TiemposClasificTiemposEnum tipoTiempo = TiemposClasificTiemposEnum.parse(tiempoProrroga.getIdTipoPlazo().intValue());
                if (tipoTiempo == TiemposClasificTiemposEnum.DIA) {
                    fechaPlazo = primerDiaHabil(fechaPlazo);
                }
            }
            fechaPlazo = getTiemposService().sumarTiempo(tiempoProrroga, fechaPlazo);
        }

        return fechaPlazo;
    }

    private Date obtenerFechaMaxPlazoTotalOrden(AgaceOrden orden) {
        asignarFechasNotificacion(orden);
        TiempoDTO tiempoPlazo = getTiemposService().obtenerTiempoPlazo(orden.getIdMetodo(), TiemposClaveEnum.PTO_PLA);
        if (orden.getFecetDetalleNyV().getFecSurteEfectosNyV() != null) {
            return getTiemposService().sumarTiempo(tiempoPlazo, orden.getFecetDetalleNyV().getFecSurteEfectosNyV());
        }
        Date fechaSurteEfectosDefault = getTiemposHelper().sumarDiasHabiles(orden.getFechaNotifNYV(), PlazosConstants.DIAS_MAX_SURTE_EFECTOS);
        return getTiemposService().sumarTiempo(tiempoPlazo, fechaSurteEfectosDefault);
    }

    @Override
    public ActoAdministrativo obtenerActoAdministrativo(String claveUnidadAdmin, Long idActoNyv) {
        return consultaRegistraNyVService.obtenerActoAdministrativo(claveUnidadAdmin, idActoNyv);
    }

    @Override
    public boolean validarPlazoCrearPruebasPericiales(AgaceOrden orden) {
        Date fechaPlazo = obtenerFechaMaxPlazoOrden(orden);
        return getTiemposHelper().comparaFechasSinTiempo(fechaPlazo, new Date()) >= 0;
    }

    @Override
    public boolean tieneAcuerdoConclusivo(AgaceOrden orden) {
        FecetSuspensionDTO suspensionAc = getSuspensionService().buscarSuspensionAc(orden.getIdOrden());
        return suspensionAc != null && getTiemposHelper().comparaFechasSinTiempo(suspensionAc.getFechaFinSuspension(), new Date()) > 0;
    }

    @Override
    public void obtenerDocumentosActoAdmin(FececActosAdm acto) {
        acto.setDocumentosActo(feceaDocumentoAdmDao.obtenerDocumentosActoAdm(acto));
    }

}
