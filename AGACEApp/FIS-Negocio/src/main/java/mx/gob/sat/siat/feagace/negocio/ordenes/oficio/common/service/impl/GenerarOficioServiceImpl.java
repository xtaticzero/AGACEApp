package mx.gob.sat.siat.feagace.negocio.ordenes.oficio.common.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetTipoOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetPruebasPericialesDao;
import mx.gob.sat.siat.feagace.modelo.dto.common.tiempos.model.TiempoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetTipoOficio;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorOficiosEnum;
import mx.gob.sat.siat.feagace.modelo.enums.EstatusOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAsociacionOficiosEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TiposOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.seguimiento.EstatusOrdenes;
import mx.gob.sat.siat.feagace.negocio.common.plazos.service.PlazosServiceOrdenes;
import mx.gob.sat.siat.feagace.negocio.common.tiempos.enums.TiemposClaveEnum;
import mx.gob.sat.siat.feagace.negocio.helper.GenerarOficioHelper;
import mx.gob.sat.siat.feagace.negocio.ordenes.oficio.common.rules.GenerarOficioRules;
import mx.gob.sat.siat.feagace.negocio.ordenes.oficio.common.service.GenerarOficioService;

@Service("generarOficioService")
public class GenerarOficioServiceImpl extends BaseBusinessServices implements GenerarOficioService {

    /**
     *
     */
    private static final long serialVersionUID = -1003559252675807839L;

    @Autowired
    private transient GenerarOficioRules generarOficioRules;

    @Autowired
    private transient FecetOficioDao fecetOficioDao;
    @Autowired
    private transient FecetTipoOficioDao fecetTipoOficioDao;
    @Autowired
    private transient PlazosServiceOrdenes plazosService;
    @Autowired
    private transient FecetPruebasPericialesDao fecetPruebasPericialesDao;

    @Autowired
    private transient GenerarOficioHelper generarOficioHelper;

    private static final List<BigDecimal> COMPULSAS = Arrays.asList(TiposOficiosOrdenesEnum.COMPULSA_INTERNACIONAL.getBigIdTipoOficio(),
            TiposOficiosOrdenesEnum.OTRAS_AUTORIDADES.getBigIdTipoOficio(), TiposOficiosOrdenesEnum.COMPULSA_TERCEROS.getBigIdTipoOficio());

    private static final List<BigDecimal> OFICIOS_SIN_VALIDACION = Arrays.asList(TiposOficiosOrdenesEnum.COMPULSA_INTERNACIONAL.getBigIdTipoOficio(),
            TiposOficiosOrdenesEnum.OTRAS_AUTORIDADES.getBigIdTipoOficio(), TiposOficiosOrdenesEnum.COMPULSA_TERCEROS.getBigIdTipoOficio(),
            TiposOficiosOrdenesEnum.AVISO_AL_CONTRIBUYENTE.getBigIdTipoOficio(), TiposOficiosOrdenesEnum.MULTA.getBigIdTipoOficio());

    @Override
    public List<TiposOficiosOrdenesEnum> obtenerOficiosGenerar(AgaceOrden orden) {
        boolean plazoCargaActivo = false;
        boolean plazoCargaRee = false;

        List<TiposOficiosOrdenesEnum> tiposOficios = new ArrayList<TiposOficiosOrdenesEnum>();
        if (!plazosService.validarPlazoCargaDocumentosRequeridosOrden(orden)) {
            List<FecetOficio> oficiosEnProceso = getOficiosPorOrdenEnProcesoOVencidos(orden.getIdOrden());
            generarOficioHelper.eliminaOficiosAdministrablesSinPlazos(oficiosEnProceso);
            if (!orden.getIdEstatus().equals(EstatusOrdenes.CONCLUIDA.getBigIdEstatus())
                    && !orden.getIdEstatus().equals(EstatusOrdenes.CONCLUIDA_POR_CAMBIO_METODO.getBigIdEstatus())
                    && !generarOficioRules.validaOficioTerminaProceso(oficiosEnProceso)) {

                for (FecetOficio oficio : oficiosEnProceso) {
                    FecetOficio oficioPrincipal = fecetOficioDao.getOficioById(oficio.getIdOficioPrincipal());
                    if (oficio.getFecetDetalleNyV() != null && oficio.getFecetDetalleNyV().getIdNyV() > 0) {
                        plazosService.obtenerFechasNotificacion(oficio);
                        TiempoDTO tiempoPlazo = plazosService.obtenerPlazoOficio(orden.getIdMetodo(), TiemposClaveEnum.CDOF_PLA,
                                oficio.getFecetTipoOficio().getIdTipoOficio());
                        if (tiempoPlazo != null && plazosService.validarPlazoCargaDocumentosRequeridosOficio(oficio)) {
                            plazoCargaActivo = true;
                            break;
                        }
                    } else if (!COMPULSAS.contains(oficio.getFecetTipoOficio().getIdTipoOficio())
                            && (oficioPrincipal != null && !COMPULSAS.contains(oficioPrincipal.getFecetTipoOficio().getIdTipoOficio()))) {
                        plazoCargaActivo = true;
                        plazoCargaRee = true;
                        break;
                    }
                }

                tiposOficios = getTabOficios(orden, plazoCargaActivo, plazoCargaRee, oficiosEnProceso);
            }
        }
        return tiposOficios;
    }

    @Override
    public List<FecetTipoOficio> obtenerOficiosGenerarPorIdAgrupacionEstatus(int idAgrupacion, BigDecimal idOrden) {
        return fecetTipoOficioDao.getTipoOficioByIdAgrupacionEstatus(idAgrupacion, idOrden, EstatusOficiosOrdenesEnum.OFICIO_RECHAZADO.getBigIdEstatus());
    }

    @Override
    public List<FecetOficio> getOficiosPorOrdenEnProcesoOVencidos(final BigDecimal idOrden) {
        return fecetOficioDao.getOficiosPorOrden(idOrden, EstatusOficiosOrdenesEnum.OFICIO_PENDIENTE_FIRMA.getBigIdEstatus(),
                EstatusOficiosOrdenesEnum.OFICIO_FIRMADO_ENVIADO_NYV.getBigIdEstatus(),
                EstatusOficiosOrdenesEnum.OFICIO_NOTIFICADO_CONTRIBUYENTE.getBigIdEstatus());
    }

    @Override
    public boolean getOficiosPorOrdenEnProcesoDeFirma(final BigDecimal idOrden) {
        List<FecetOficio> oficiosEnProcesoDeFirma = fecetOficioDao.getOficiosPorOrden(idOrden,
                EstatusOficiosOrdenesEnum.OFICIO_PENDIENTE_FIRMA.getBigIdEstatus());

        return generarOficioHelper.isCompulsa(oficiosEnProcesoDeFirma);
    }

    private boolean getOficiosPorOrdenEnProcesoDeFirmaSinCompulsa(final BigDecimal idOrden) {
        List<FecetOficio> oficiosEnProcesoDeFirma = fecetOficioDao.getOficiosPorOrden(idOrden,
                EstatusOficiosOrdenesEnum.OFICIO_PENDIENTE_FIRMA.getBigIdEstatus());

        return oficiosEnProcesoDeFirma.isEmpty() ? false : true;
    }

    public List<TiposOficiosOrdenesEnum> getTabsMostrarCompulsas(AgaceOrden orden) {
        List<TiposOficiosOrdenesEnum> oficiosTabsMostrar = new ArrayList<TiposOficiosOrdenesEnum>();

        if (generarOficioRules.validarVisualizacionTabsCompulsas(orden)) {
            oficiosTabsMostrar.add(TiposOficiosOrdenesEnum.COMPULSA_TERCEROS);
            oficiosTabsMostrar.add(TiposOficiosOrdenesEnum.COMPULSA_INTERNACIONAL);
            oficiosTabsMostrar.add(TiposOficiosOrdenesEnum.OTRAS_AUTORIDADES);

            for (int i = 0; i < oficiosTabsMostrar.size(); i++) {
                if (!generarOficioRules.validarVisulizarSolicitudOficio(orden, oficiosTabsMostrar.get(i))) {
                    oficiosTabsMostrar.remove(oficiosTabsMostrar.get(i));
                    i--;
                }
            }
        }

        return oficiosTabsMostrar;
    }

    public List<TiposOficiosOrdenesEnum> getTabsMostrarAvisoContribuyenteMulta() {
        List<TiposOficiosOrdenesEnum> oficiosTabsMostrar = new ArrayList<TiposOficiosOrdenesEnum>();
        oficiosTabsMostrar.add(TiposOficiosOrdenesEnum.AVISO_AL_CONTRIBUYENTE);
        oficiosTabsMostrar.add(TiposOficiosOrdenesEnum.MULTA);

        return oficiosTabsMostrar;
    }

    public List<TiposOficiosOrdenesEnum> getTabsMedidasApremio() {
        List<TiposOficiosOrdenesEnum> oficiosTabsMostrar = new ArrayList<TiposOficiosOrdenesEnum>();
        oficiosTabsMostrar.add(TiposOficiosOrdenesEnum.MEDIDAS_DE_APREMIO);

        return oficiosTabsMostrar;
    }

    private List<TiposOficiosOrdenesEnum> getTabOficios(AgaceOrden orden, boolean plazoCargaActivo, boolean plazoCargaRee, List<FecetOficio> oficiosEnProceso) {
        List<TiposOficiosOrdenesEnum> tiposOficios = new ArrayList<TiposOficiosOrdenesEnum>();
        boolean entrarCompulsa = true;

        if (!plazoCargaActivo && checkOficiosProcesoVencido(oficiosEnProceso)) {
            tiposOficios.addAll(getTabsMostrar(orden, oficiosEnProceso));
        }

        if (generarOficioHelper.isMetodoRee(orden) && !plazoCargaRee) {
            tiposOficios.addAll(getTabsMostrar(orden, oficiosEnProceso));
            if (!generarOficioRules.validarOficioAvisoCircunstancial(oficiosEnProceso)) {
                tiposOficios.addAll(getTabsMostrarCompulsas(orden));
                tiposOficios.addAll(getTabsMedidasApremio());
                tiposOficios.addAll(getTabsMostrarAvisoContribuyenteMulta());
            }
            entrarCompulsa = false;
        }

        if (entrarCompulsa) {
            if (!generarOficioHelper.isMetodoRee(orden) && !generarOficioHelper.isMetodoUca(orden) && !generarOficioHelper.isMetodoMca(orden)) {
                if (!generarOficioRules.validarOficioAvisoCircunstancial(oficiosEnProceso)) {
                    if (generarOficioHelper.isMetodoEho(orden)) {
                        if ((orden.getFechaIntegraExp() == null)) {
                            tiposOficios.addAll(getTabsMostrarCompulsas(orden));
                        }
                        tiposOficios.addAll(getTabsMostrarAvisoContribuyenteMulta());
                        tiposOficios.addAll(getTabsMedidasApremio());

                    } else {
                        tiposOficios.addAll(getTabsMostrarCompulsas(orden));
                        tiposOficios.addAll(getTabsMostrarAvisoContribuyenteMulta());
                        tiposOficios.addAll(getTabsMedidasApremio());
                    }
                }
            } else {
                boolean validacion = getOficiosPorOrdenEnProcesoDeFirma(orden.getIdOrden());
                if (validacion) {
                    validacion = !generarOficioRules.validarOficioAvisoCircunstancial(oficiosEnProceso);
                    if (validacion) {
                        validacion = generarOficioHelper.isMetodoEho(orden);
                        if (validacion) {
                            validacion = (orden.getFechaIntegraExp() == null);
                            if (validacion) {
                                tiposOficios.addAll(getTabsMostrarCompulsas(orden));
                            }
                            tiposOficios.addAll(getTabsMostrarAvisoContribuyenteMulta());
                            tiposOficios.addAll(getTabsMedidasApremio());
                        } else {
                            tiposOficios.addAll(getTabsMostrarCompulsas(orden));
                            tiposOficios.addAll(getTabsMostrarAvisoContribuyenteMulta());
                            tiposOficios.addAll(getTabsMedidasApremio());
                        }
                    }
                }
            }
        }
        return tiposOficios;
    }

    private boolean checkOficiosProcesoVencido(List<FecetOficio> oficiosEnProceso) {
        boolean flag = true;
        for (FecetOficio oficio : oficiosEnProceso) {
            if (oficio.getIdEstatus().equals(EstatusOficiosOrdenesEnum.OFICIO_PENDIENTE_FIRMA.getBigIdEstatus())
                    && !OFICIOS_SIN_VALIDACION.contains(oficio.getFecetTipoOficio().getIdTipoOficio())) {
                if (oficio.getFecetTipoOficio().getAgrupador() != null) {
                    if (!oficio.getFecetTipoOficio().getAgrupador().equals(AgrupadorOficiosEnum.MEDIDAS_APREMIO)) {
                        flag = false;
                        break;
                    }
                } else {
                    flag = false;
                    break;
                }
            }
        }
        return flag;

    }

    public List<TiposOficiosOrdenesEnum> getTabsMostrar(AgaceOrden orden, List<FecetOficio> oficios) {
        List<TiposOficiosOrdenesEnum> oficiosTabsMostrar = new ArrayList<TiposOficiosOrdenesEnum>();
        List<FecetTipoOficio> listaTipoOficios = fecetTipoOficioDao.getTipoOficioByIdMetodo(orden.getFeceaMetodo().getIdMetodo());
        for (FecetTipoOficio tipoOficios : listaTipoOficios) {
            List<FecetPruebasPericiales> existeSolicitudPruebaPericial = new ArrayList<FecetPruebasPericiales>();
            List<FecetPruebasPericiales> existeSolicitudAprobada = new ArrayList<FecetPruebasPericiales>();

            if (tipoOficios.getAgrupador() == null || !tipoOficios.getAgrupador().equals(AgrupadorOficiosEnum.ADMINISTRABLES)) {
                TiposOficiosOrdenesEnum tipoOficioEnum = TiposOficiosOrdenesEnum.parse(tipoOficios.getIdTipoOficio().intValue());
                if (tipoOficioEnum.equals(TiposOficiosOrdenesEnum.PRUEBAS_PERICIALES_DESAHOGO)) {
                    existeSolicitudPruebaPericial = fecetPruebasPericialesDao.existeSolicitudPruebaPericial(orden.getIdOrden());
                    existeSolicitudAprobada = fecetPruebasPericialesDao.existeSolicitudAprobada(orden.getIdOrden());
                }

                if (generarOficioRules.validarVisualizacionTab(orden, tipoOficioEnum, oficios) && generarOficioRules
                        .validarVisualizacionTabPruebasPericiales(orden, tipoOficioEnum, existeSolicitudPruebaPericial, existeSolicitudAprobada)) {
                    boolean validacion = !(tipoOficioEnum.equals(TiposOficiosOrdenesEnum.COMPULSA_INTERNACIONAL)
                            || tipoOficioEnum.equals(TiposOficiosOrdenesEnum.COMPULSA_TERCEROS)
                            || tipoOficioEnum.equals(TiposOficiosOrdenesEnum.OTRAS_AUTORIDADES));
                    if (validacion) {
                        validacion = tipoOficioEnum.equals(TiposOficiosOrdenesEnum.RESOLUCION_DEFINITIVA) && generarOficioHelper.isMetodoEho(orden);
                        if (validacion) {
                            validacion = (orden.getFechaIntegraExp() != null);
                            if (validacion) {
                                oficiosTabsMostrar.add(tipoOficioEnum);
                            }
                        } else {
                            oficiosTabsMostrar.add(tipoOficioEnum);
                        }

                    }
                }

            }
        }

        removePruebaDesahogoEHO(oficiosTabsMostrar, orden);

        removerOficiosRelacionados(oficiosTabsMostrar, oficios, orden.getFeceaMetodo().getIdMetodo());

        if (generarOficioRules.validarOficioAvisoCircunstancial(oficios)) {
            avisoCircunstancialRee(orden, oficiosTabsMostrar);
            avisoCircunstancialOrg(orden, oficios, oficiosTabsMostrar);
        }

        if (oficiosTabsMostrar.contains(TiposOficiosOrdenesEnum.AVISO_CIRCUNSTANCIAL) && generarOficioHelper.isMetodoRee(orden)
                && (getOficiosPorOrdenEnProcesoDeFirmaSinCompulsa(orden.getIdOrden()) || plazosService.suspensionOrden(orden))) {
            oficiosTabsMostrar.remove(TiposOficiosOrdenesEnum.AVISO_CIRCUNSTANCIAL);
        }

        return oficiosTabsMostrar;
    }

    private void removePruebaDesahogoEHO(List<TiposOficiosOrdenesEnum> oficiosTabsMostrar, AgaceOrden orden) {
        if (generarOficioHelper.isMetodoEho(orden) && oficiosTabsMostrar.contains(TiposOficiosOrdenesEnum.RESOLUCION_DEFINITIVA)) {
            oficiosTabsMostrar.remove(TiposOficiosOrdenesEnum.PRUEBAS_DESAHOGO);
        }
    }

    private void removerOficiosRelacionados(List<TiposOficiosOrdenesEnum> oficiosTabsMostrar, List<FecetOficio> oficios, BigDecimal idMetodo) {
        for (int i = 0; i < oficiosTabsMostrar.size(); i++) {
            List<FecetTipoOficio> listaTipoOficio = fecetTipoOficioDao.getTipoOficioByIdDependiente(oficiosTabsMostrar.get(i).getBigIdTipoOficio(), idMetodo,
                    TipoAsociacionOficiosEnum.RELACION);
            if (listaTipoOficio == null || listaTipoOficio.isEmpty()) {
                listaTipoOficio = fecetTipoOficioDao.getTipoOficioByIdDependiente(oficiosTabsMostrar.get(i).getBigIdTipoOficio(), idMetodo,
                        TipoAsociacionOficiosEnum.DEPENDENCIA);
            }
            if (listaTipoOficio != null && !listaTipoOficio.isEmpty() && (!generarOficioHelper.validarExistenciaOficioDependiente(listaTipoOficio, oficios))) {
                oficiosTabsMostrar.remove(i--);
            }
        }
    }

    private void avisoCircunstancialRee(AgaceOrden orden, List<TiposOficiosOrdenesEnum> oficiosTabsMostrar) {
        if (generarOficioHelper.isMetodoRee(orden)) {
            if (oficiosTabsMostrar.contains(TiposOficiosOrdenesEnum.RESOLUCION_DEFINITIVA)) {
                oficiosTabsMostrar.clear();
                oficiosTabsMostrar.add(TiposOficiosOrdenesEnum.RESOLUCION_DEFINITIVA);
            } else {
                oficiosTabsMostrar.clear();
            }
        }
    }

    private void avisoCircunstancialOrg(AgaceOrden orden, List<FecetOficio> oficios, List<TiposOficiosOrdenesEnum> oficiosTabsMostrar) {
        List<TiposOficiosOrdenesEnum> oficiosNoRemover = new ArrayList<TiposOficiosOrdenesEnum>();
        if (generarOficioHelper.isMetodoOrg(orden)) {
            if (generarOficioRules.validaOficioORE(oficios)) {
                if (generarOficioRules.validaOficioOREFirmada(oficios)) {
                    if (oficiosTabsMostrar.contains(TiposOficiosOrdenesEnum.RESOLUCION_DEFINITIVA)) {
                        oficiosNoRemover.add(TiposOficiosOrdenesEnum.RESOLUCION_DEFINITIVA);
                        removerOficiosLista(oficiosTabsMostrar, oficiosNoRemover);
                    }
                } else {
                    oficiosTabsMostrar.clear();
                }
            } else {
                if (oficiosTabsMostrar.contains(TiposOficiosOrdenesEnum.OBSERVACIONES_REVISION_ESCRITORIO)) {
                    oficiosNoRemover.add(TiposOficiosOrdenesEnum.OBSERVACIONES_REVISION_ESCRITORIO);
                    oficiosNoRemover.add(TiposOficiosOrdenesEnum.SIN_OBSERVACIONES);
                    removerOficiosLista(oficiosTabsMostrar, oficiosNoRemover);
                } else {
                    oficiosTabsMostrar.clear();
                }
            }
        }
    }

    private void removerOficiosLista(List<TiposOficiosOrdenesEnum> oficiosTabsMostrar, List<TiposOficiosOrdenesEnum> oficiosNoRemover) {
        if (oficiosTabsMostrar != null && !oficiosTabsMostrar.isEmpty()) {
            for (int i = 0; i < oficiosTabsMostrar.size(); i++) {
                if (!oficiosNoRemover.contains(oficiosTabsMostrar.get(i))) {
                    oficiosTabsMostrar.remove(i);
                    i--;
                }
            }
        }
    }

    @Override
    public boolean validarVisualizarIntegraExpediente(AgaceOrden orden) {
        return generarOficioRules.validarVisualizacionIntegraExpediente(orden);
    }

    @Override
    public boolean validarVisualizarReactivarPlazo(AgaceOrden orden) {
        List<FecetOficio> listOficio = getOficiosPorOrdenEnProcesoOVencidos(orden.getIdOrden());
        plazosService.asignarFechasNyVOficio(orden, listOficio);
        return generarOficioRules.validarVisualizacionReactivarPlazo(orden, listOficio);
    }

    @Override
    public boolean validarVisualizarReactivarPlazoAcuerdo(AgaceOrden orden) {
        return generarOficioRules.validarVisualizacionReactivarPlazoAcuerdoConclusivo(orden);
    }

    @Override
    public boolean validarVisualizacionTabAvisoContribuyenteOpcional(AgaceOrden orden, FecetOficio oficio) {
        return generarOficioRules.validarVisualizarTabAvisoContribuyenteOpcional(oficio, getOficiosPorOrdenEnProcesoOVencidos(orden.getIdOrden()));
    }

}
