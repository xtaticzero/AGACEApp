package mx.gob.sat.siat.feagace.negocio.ordenes.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.buzon.exception.BuzonNoDisponibleException;
import mx.gob.sat.siat.buzon.model.MedioComunicacion;
import mx.gob.sat.siat.buzon.service.BuzonTributarioService;
import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.roles.FececTipoAsociadoDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetAlegatoDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetAsociadoDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetCompulsasDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetOficioAnexosDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetPromocionDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos.FecetDocProrrogaOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos.FecetDocPruebasPericialesDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetProrrogaOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetProrrogaOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetPruebasPericialesDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececTipoAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCompulsas;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.enums.EstatusOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TiposOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.prorrogas.EstatusProrroga;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.pruebaspericiales.EstatusPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.seguimiento.EstatusOrdenes;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.bo.base.impl.ValidaMediosContactoBO;
import mx.gob.sat.siat.feagace.negocio.common.plazos.service.PlazosServiceOrdenes;
import mx.gob.sat.siat.feagace.negocio.helper.AsociadosHelper;
import mx.gob.sat.siat.feagace.negocio.ordenes.AsociadosService;
import mx.gob.sat.siat.feagace.negocio.rules.impl.ValidaMediosContactoRule;
import mx.gob.sat.siat.feagace.negocio.rules.impl.ValidarMetodoOficioAsociadoRule;
import mx.gob.sat.siat.feagace.negocio.rules.impl.ValidarOficioAdministrable;
import mx.gob.sat.siat.feagace.negocio.util.constantes.BusinessUtil;

@Service("asociadosService")
public class AsociadosServiceImpl extends OrdenesServiceBase implements AsociadosService {

    private static final long serialVersionUID = -713179547537277620L;

    @Autowired
    private transient FececTipoAsociadoDao fececTipoAsociadoDao;
    @Autowired
    private transient FecetAsociadoDao fecetAsociadoDao;
    @Autowired
    private transient FecetOficioDao fecetOficioDao;
    @Autowired
    private transient FecetAlegatoDao fecetAlegatoDao;
    @Autowired
    private transient FecetPromocionDao fecetPromocionDao;
    @Autowired
    private transient FecetProrrogaOrdenDao fecetProrrogaOrdenDao;
    @Autowired
    private transient FecetDocProrrogaOrdenDao fecetDocProrrogaOrdenDao;
    @Autowired
    private transient BuzonTributarioService buzonTributarioService;
    @Autowired
    private transient FecetCompulsasDao fecetCompulsasDao;
    @Autowired
    private transient PlazosServiceOrdenes plazosService;
    @Autowired
    private transient FecetOficioAnexosDao fecetOficioAnexosDao;
    @Autowired
    private transient FecetProrrogaOficioDao fecetProrrogaOficioDao;
    @Autowired
    private transient ValidarMetodoOficioAsociadoRule validaMetodoOficioAsociadoRule;
    @Autowired
    private transient FecetPruebasPericialesDao fecetPruebasPericialesDao;
    @Autowired
    private transient FecetDocPruebasPericialesDao fecetDocPruebasPericialesDao;
    @Autowired
    private transient ValidarOficioAdministrable validarOficioAdministrable;
    @Autowired
    private transient FecetDocOrdenDao fecetDocOrdenDao;

    private transient AsociadosHelper helper = new AsociadosHelper();

    /**
     *
     * @param rfc
     * @return
     */
    public List<FececTipoAsociado> construyeComboPerfil(String rfc) {
        List<FececTipoAsociado> lista;
        lista = fececTipoAsociadoDao.getTipoAsociadoByContribuyente(rfc);
        List<FececTipoAsociado> listaFull = new ArrayList<FececTipoAsociado>();
        listaFull.add(creaPerfilContribuyente());
        for (FececTipoAsociado lista1 : lista) {
            listaFull.add(lista1);
        }
        lista = listaFull;

        return lista;
    }

    /**
     *
     * @param rfc
     * @param idTipoAsociado
     * @return
     */
    @Override
    public List<String> construyeComboPerfilRFC(String rfc, BigDecimal idTipoAsociado) {
        return fecetAsociadoDao.getContribuyenteTipoAsociado(rfc, idTipoAsociado);
    }

    /**
     *
     * @return
     */
    public FececTipoAsociado creaPerfilContribuyente() {
        return helper.creaPerfilContribuyente();
    }

    /**
     *
     * @param rfc
     * @param idTipoAsociado
     * @return
     */
    public List<AgaceOrden> obtenerOrdenesContribuyente(String rfc, BigDecimal idTipoAsociado) {
        List<AgaceOrden> listaOrdenes = getAgaceOrdenDao().getOrdenesContribuyente(rfc,
                EstatusOrdenes.ENVIADO_NOTIFICACION_CONTRIBUYENTE.getBigIdEstatus());
        listaOrdenes.addAll(getAgaceOrdenDao().getOrdenesContribuyente(rfc,
                EstatusOrdenes.NOTIFICADO_AL_CONTRIBUYENTE.getBigIdEstatus()));
        listaOrdenes = plazosService.filtraOrdenPorFecha(listaOrdenes);
        actualizarEstatusNotificacionProrroga(listaOrdenes);
        List<AgaceOrden> listaOrdenesConcluidas = new ArrayList<AgaceOrden>();
        listaOrdenesConcluidas
                .addAll(getAgaceOrdenDao().getOrdenesContribuyente(rfc, EstatusOrdenes.CONCLUIDA.getBigIdEstatus()));
        listaOrdenesConcluidas.addAll(getAgaceOrdenDao().getOrdenesContribuyente(rfc,
                EstatusOrdenes.CONCLUIDA_POR_CAMBIO_METODO.getBigIdEstatus()));
        listaOrdenesConcluidas = plazosService.filtraOrdenPorFecha(listaOrdenesConcluidas);
        listaOrdenes.addAll(listaOrdenesConcluidas);
        for (AgaceOrden orden : listaOrdenes) {
            orden.setMostrarNumeroOrden(plazosService.tieneAcuerdoConclusivo(orden));
        }
        return listaOrdenes;

    }

    public List<AgaceOrden> obtenerOrdenesAsociado(String rfcAsociado, BigDecimal idTipoAsociado,
            String rfcContribuyente) {
        List<AgaceOrden> listaOrdenes = getAgaceOrdenDao().getOrdenesAsociado(rfcAsociado, idTipoAsociado, rfcContribuyente);
        listaOrdenes = plazosService.filtraOrdenPorFecha(listaOrdenes);

        return listaOrdenes;

    }

    /**
     *
     * @param rfc
     * @param apoderadoLegal
     * @return
     */
    public ColaboradorVO obtenerApoderadoLegalContribuyente(String rfc, ColaboradorVO apoderadoLegal) {
        return (helper.setApoderadoLegal(apoderadoLegal,
                fecetAsociadoDao.getApoderadoLegalContribuyente(rfc, apoderadoLegal.getTipoAsociado())));

    }

    /**
     *
     * @param colaborador
     * @param contribuyenteIDC
     * @return
     */
    public ColaboradorVO configuraColaborador(ColaboradorVO colaborador, FecetContribuyente contribuyenteIDC) {
        return helper.configuraColaborador(colaborador, contribuyenteIDC);
    }

    /**
     *
     * @param colaborador
     * @return
     */
    public ColaboradorVO nuevaBusquedaColaborador(ColaboradorVO colaborador) {
        return helper.nuevaBusquedaColaborador(colaborador);
    }

    /**
     *
     * @param colaborador
     * @return
     */
    public ColaboradorVO sinColaborador(ColaboradorVO colaborador) {
        return helper.ordenSinColaborador(colaborador);
    }

    /**
     *
     * @param rfcContribuyente
     * @param colaborador
     * @param orden
     * @return
     */
    public String asociaColaborador(String rfcContribuyente, ColaboradorVO colaborador, AgaceOrden orden) {
        FecetAsociado nuevoAsociado = helper.llenaColaborador(rfcContribuyente, colaborador, orden);
        BigDecimal idTipoAsociado = colaborador.getTipoAsociado();
        if (idTipoAsociado.equals(Constantes.ID_APODERADO_LEGAL)) {
            eliminaApoderadoLegalContribuyente(rfcContribuyente, colaborador.getTipoAsociado());
        } else {
            eliminaColaboradorContribuyente(orden.getIdOrden(), colaborador.getTipoAsociado());
        }

        if (nuevoAsociado != null) {
            insertaColaborador(nuevoAsociado);
        }

        return helper.contruyeMensajeDesplegar(colaborador.getNombreTipoAsociado());
    }

    /**
     *
     * @param colaborador
     * @param flag
     * @return
     */
    public ColaboradorVO cambiaEmailInputText(ColaboradorVO colaborador, boolean flag) {
        return helper.validaEmailInputText(colaborador, flag);
    }

    /**
     *
     * @param colaborador
     * @param flag
     * @return
     */
    public ColaboradorVO cambiaCamposTxt(ColaboradorVO colaborador, boolean flag) {
        return helper.seteaCamposTxt(colaborador, flag);
    }

    /**
     *
     * @param colaborador
     * @param orden
     * @return
     */
    public ColaboradorVO cargaColaborador(ColaboradorVO colaborador, AgaceOrden orden) {
        return helper.setColaborador(colaborador,
                obtenerColaboradorContribuyente(colaborador.getTipoAsociado(), orden.getIdOrden()));
    }

    /**
     *
     * @param colaborador
     */
    public void insertaColaborador(FecetAsociado colaborador) {
        fecetAsociadoDao.insert(colaborador);
    }

    /**
     *
     * @param rfcContribuyente
     * @param idTipoAsociado
     */
    private void eliminaApoderadoLegalContribuyente(String rfcContribuyente, BigDecimal idTipoAsociado) {
        fecetAsociadoDao.eliminaApoderadoLegal(rfcContribuyente, idTipoAsociado);
    }

    /**
     *
     * @param idTipoAsociado
     * @param idOrden
     * @return
     */
    private List<FecetAsociado> obtenerColaboradorContribuyente(BigDecimal idTipoAsociado, BigDecimal idOrden) {
        return fecetAsociadoDao.getColaboradorContribuyente(idTipoAsociado, idOrden);
    }

    /**
     *
     * @param idOrden
     * @param idTipoAsociado
     */
    public void eliminaColaboradorContribuyente(BigDecimal idOrden, BigDecimal idTipoAsociado) {
        fecetAsociadoDao.eliminaColaborador(idOrden, idTipoAsociado);
    }

    /**
     *
     * @param apoderadoLegal
     * @param idOrden
     */
    public void actualizaColaboradorContribuyente(FecetAsociado apoderadoLegal, BigDecimal idOrden) {
        fecetAsociadoDao.actualizaColaborador(apoderadoLegal, idOrden);
    }

    /**
     *
     * @param rfc
     * @param idTipoAsociado
     * @param rfcContribuyente
     * @return
     */
    public BigDecimal obtenerIdAsociado(String rfc, BigDecimal idTipoAsociado, String rfcContribuyente) {
        List<BigDecimal> lista = fecetAsociadoDao.getIdAsociado(rfc, idTipoAsociado, rfcContribuyente);
        return (!lista.isEmpty()) ? lista.get(0) : null;
    }

    public void confirmarAsociado(String rfcContribuyente, String rfc, BigDecimal idTipoAsociado, BigDecimal idOrden) {
        fecetAsociadoDao.confirmaColaborador(rfcContribuyente, rfc, idTipoAsociado, idOrden);
    }

    @PistaAuditoria
    public List<FecetOficio> getOficiosFirmados(AgaceOrden orden) {
        List<FecetOficio> lista = fecetOficioDao.getOficioByIdOrdenIdEstatus(orden.getIdOrden(),
                EstatusOficiosOrdenesEnum.OFICIO_FIRMADO_ENVIADO_NYV.getBigIdEstatus(),
                EstatusOficiosOrdenesEnum.OFICIO_NOTIFICADO_CONTRIBUYENTE.getBigIdEstatus());

        List<FecetOficio> listaOficiosAdminSinPlazos = new ArrayList<FecetOficio>();
        for (FecetOficio oficio : lista) {
            if (validarOficioAdministrable.esAdministrable(oficio) && !validarOficioAdministrable.tienePlazos(oficio)) {
                listaOficiosAdminSinPlazos.add(oficio);
            }
        }
        validarOficioAdministrable.eliminaOficiosAdministrablesSinPlazos(lista);

        lista = helper.eliminaOficioMedidasApremio(lista);

        lista = plazosService.filtarOficiosPorFecha(orden, lista);

        for (FecetOficio oficioSinPlazos : listaOficiosAdminSinPlazos) {
            lista.add(oficioSinPlazos);
        }

        if (!lista.isEmpty()) {
            lista = removerOficiosCompulsas(lista);
            lista = plazosService.filtarOficios(lista);
            for (FecetOficio oficio : lista) {
                if (validarOficioAdministrable.esAdministrable(oficio)
                        && validarOficioAdministrable.tieneDocReq(oficio)) {
                    if (validarOficioAdministrable.tieneDocReq(oficio)) {
                        oficio.setMostrarIdOficio(false);
                    } else {
                        oficio.setMostrarIdOficio(true);
                    }

                } else {
                    oficio.setMostrarIdOficio(validaMetodoOficioAsociadoRule.validaMetodoOficioDocNoReq(orden,
                            oficio.getFecetTipoOficio().getIdTipoOficio()));
                }
                oficio.setRutaAcuseNyv(oficio.getFecetDetalleNyV().getRutaAcuseNyv());
                if (oficio.getRutaAcuseNyv() != null && !oficio.getRutaAcuseNyv().equals("")) {
                    oficio.setNombreAcuseNyv(UtileriasMapperDao.getNameFileFromPath(oficio.getRutaAcuseNyv()));
                }
            }
        }
        llenarRfcANombreOficio(lista);
        return lista;
    }

    private List<FecetOficio> removerOficiosCompulsas(List<FecetOficio> lista) {
        List<FecetOficio> listaNueva = new ArrayList<FecetOficio>();
        for (FecetOficio oficio : lista) {
            if (oficio.getFecetTipoOficio().getIdTipoOficio().equals(Constantes.OFICIO_COMPULSA_TERCERO)
                    || oficio.getFecetTipoOficio().getIdTipoOficio().equals(Constantes.OFICIO_COMPULSA_INTERNACIONAL)
                    || oficio.getFecetTipoOficio().getIdTipoOficio().equals(Constantes.OFICIO_OTRAS_AUTORIDADES)) {
                List<FecetOficio> avisos = obtenerOficiosDependientes(oficio.getIdOficio());
                for (FecetOficio aviso : avisos) {
                    plazosService.obtenerFechasNotificacion(aviso);
                    listaNueva.add(aviso);
                }
            }
        }

        List<FecetOficio> remove = new ArrayList<FecetOficio>();
        for (FecetOficio oficio : lista) {
            if (oficio.getFecetTipoOficio().getIdTipoOficio().equals(Constantes.OFICIO_COMPULSA_TERCERO)
                    || oficio.getFecetTipoOficio().getIdTipoOficio().equals(Constantes.OFICIO_COMPULSA_INTERNACIONAL)
                    || oficio.getFecetTipoOficio().getIdTipoOficio().equals(Constantes.OFICIO_OTRAS_AUTORIDADES)) {
                remove.add(oficio);
            }
        }

        lista.removeAll(remove);

        for (FecetOficio oficio : listaNueva) {
            lista.add(oficio);
        }

        return lista;

    }

    public List<FecetAlegato> getPruebasAlegatosPromocion(final BigDecimal idPromocion) {
        return fecetAlegatoDao.findWhereIdPromocionEquals(idPromocion);
    }

    @PistaAuditoria
    public List<FecetPromocion> getPromocionContadorPruebasAlegatos(AgaceOrden orden) {
        List<FecetPromocion> listaPromociones = fecetPromocionDao
                .getPromocionContadorPruebasAlegatos(orden.getIdOrden());

        for (FecetPromocion promocion : listaPromociones) {
            promocion.setDescripcionTipoPromocion(BusinessUtil.getTipoPromocionOrdenPorMetodo(orden.getIdMetodo()));
            promocion.setExtemporanea(
                    plazosService.esDocumentoExtemporaneoOrden(orden, promocion.getFechaCarga()) ? "1" : "0");
        }
        return listaPromociones;
    }

    @PistaAuditoria
    public List<FecetProrrogaOrden> getHistoricoProrrogaOrden(final BigDecimal idOrden) {
        return fecetProrrogaOrdenDao.getProrrogaContadorDoc(idOrden);
    }

    public List<FecetDocProrrogaOrden> getDocsProrrogaOrden(final BigDecimal idProrrogaOrden) {
        return fecetDocProrrogaOrdenDao.findWhereIdProrrogaEquals(idProrrogaOrden);
    }

    public ValidaMediosContactoBO validaMediosContactoAsociado(ValidaMediosContactoBO validaMediosContactoBO) {
        try {
            validaMediosContactoBO.setMediosComunicacion(
                    buzonTributarioService.obtenerMediosComunicacion(validaMediosContactoBO.getRfc()));
            validaMediosContactoBO.setRule(ValidaMediosContactoRule.CUENTA_MEDIO_CONTACTO);
            while (validaMediosContactoBO.getRule().process(validaMediosContactoBO)) {
                logger.info("Validando medios de contacto.");
            }
        } catch (BuzonNoDisponibleException e) {
            validaMediosContactoBO.setMessage(Constantes.MESANJE_ERROR_MEDIOS_CONTACTO);
            validaMediosContactoBO.setFlag(false);
            return validaMediosContactoBO;
        }
        return validaMediosContactoBO;
    }

    public ColaboradorVO cargaListaMediosContacto(ColaboradorVO colaborador,
            ValidaMediosContactoBO validaMediosContactoBO) {
        colaborador.setListaMediosContacto(new ArrayList<String>());
        for (MedioComunicacion correo : validaMediosContactoBO.getMediosComunicacion()) {
            colaborador.getListaMediosContacto().add(correo.getMedio());
        }
        return colaborador;
    }

    public List<FecetCompulsas> obtenerCompulsasContribuyente(String rfcContribuyente) {
        List<FecetCompulsas> compulsas = fecetCompulsasDao.getCompulsasOrden(rfcContribuyente);
        plazosService.filtarCompulsasPorFecha(compulsas);
        for (FecetCompulsas compulsa : compulsas) {
            compulsa.setMostrarNumeroOrden(plazosService
                    .tieneAcuerdoConclusivo(getAgaceOrdenDao().obtenerOrden(compulsa.getIdOrdenAuditada()).get(0)));
        }
        return compulsas;
    }

    public FecetOficio obtenerOficioCompulsa(BigDecimal idOrdenAuditada, BigDecimal idOrdenCompulsa) {
        return fecetOficioDao.getOficioCompulsa(idOrdenAuditada, idOrdenCompulsa);
    }

    @PistaAuditoria
    public List<FecetOficio> obtenerOficiosDependientes(BigDecimal idOficio) {
        return fecetOficioDao.getOficiosDependientesPorIdEstatusContribuyente(idOficio);
    }

    public List<FecetOficioAnexos> obtenerOficioAnexos(BigDecimal idOficio) {
        return fecetOficioAnexosDao.getAnexosByIdOficio(idOficio);
    }

    public void actualizarEstatusNotificacionProrroga(List<AgaceOrden> listaOrdenes) {
        for (AgaceOrden orden : listaOrdenes) {
            List<FecetProrrogaOrden> prorrogasFirmadasOrden = fecetProrrogaOrdenDao.findWhereIdEstatusIdOrdenEquals(
                    EstatusProrroga.RESOLUCION_PRORROGA_FIRMADA_ENVIADA_NYV.getBigIdEstatus(), orden.getIdOrden());
            for (FecetProrrogaOrden prorrogaOrden : prorrogasFirmadasOrden) {
                plazosService.asignarFechasNotificacion(prorrogaOrden);
            }
            List<FecetOficio> oficiosOrden = fecetOficioDao.getOficioByIdOrden(orden.getId());
            for (FecetOficio oficio : oficiosOrden) {
                List<FecetProrrogaOficio> prorrogasFirmadasOficio = fecetProrrogaOficioDao.getProrrogaPorOficioEstatus(
                        oficio.getIdOficio(),
                        EstatusProrroga.RESOLUCION_PRORROGA_FIRMADA_ENVIADA_NYV.getBigIdEstatus());
                for (FecetProrrogaOficio prorrogaOficio : prorrogasFirmadasOficio) {
                    plazosService.asignarFechasNotificacion(prorrogaOficio);
                }
            }
        }
    }

    public List<FecetOficio> getOficiosAdministrables(final BigDecimal idOrden) {
        return fecetOficioDao.getOficiosAdministrablesPorIdOrden(idOrden);
    }

    @PistaAuditoria
    public List<FecetPruebasPericiales> getHistoricoPruebasPericiales(final BigDecimal idOrden) {
        return fecetPruebasPericialesDao.getPruebasPericialesContadorDoc(idOrden);
    }

    public void actualizarEstatusNotificacionPruebasPericiales(List<AgaceOrden> listaOrdenes) {
        for (AgaceOrden orden : listaOrdenes) {
            List<FecetPruebasPericiales> pruebasPericialesFirmadasOrden = fecetPruebasPericialesDao
                    .findWhereIdEstatusIdOrdenEquals(
                            EstatusPruebasPericiales.RESOLUCION_PRUEBAS_PERICIALES_FIRMADA_ENVIADA_NYV
                            .getBigIdEstatus(),
                            orden.getIdOrden());
            for (FecetPruebasPericiales pruebasPericiales : pruebasPericialesFirmadasOrden) {
                plazosService.asignarFechasNotificacion(pruebasPericiales);
            }
        }
    }

    public List<FecetDocPruebasPericiales> getDocsPruebasPericiales(final BigDecimal idPruebaPericiales) {
        return fecetDocPruebasPericialesDao.findWhereIdPruebasPericialesEquals(idPruebaPericiales);
    }

    private void llenarRfcANombreOficio(List<FecetOficio> listaOficio) {
        if (listaOficio != null) {
            for (FecetOficio oficio : listaOficio) {
                TiposOficiosOrdenesEnum oficioEnum = TiposOficiosOrdenesEnum
                        .parse(oficio.getFecetTipoOficio().getIdTipoOficio().intValue());

                if (oficioEnum != null) {
                    StringBuilder nombre = new StringBuilder();

                    if (oficio.getIdOficioPrincipal() != null) {
                        FecetOficio oficioPrincipal = fecetOficioDao.getOficioByIdOficio(oficio.getIdOficioPrincipal())
                                .get(0);
                        if (oficioPrincipal != null) {
                            nombre.append(oficio.getFecetTipoOficio().getNombre());
                            nombre.append(llenarNombreOficio(oficioPrincipal));
                        }
                    } else {
                        nombre.append(llenarNombreOficioCompulsa(oficio));
                    }
                    oficio.getFecetTipoOficio().setNombre(nombre.toString());
                }
            }
        }
    }

    private String llenarNombreOficio(FecetOficio oficio) {
        StringBuilder nombre = new StringBuilder();

        if (oficio.getRfcCompulsado() != null && !oficio.getRfcCompulsado().trim().equals("")) {
            nombre.append(" (").append(oficio.getFecetTipoOficio().getNombre()).append(" ")
                    .append(oficio.getRfcCompulsado()).append(" )");
        }
        if (oficio.getNombreCompulsado() != null && !oficio.getNombreCompulsado().trim().equals("")) {
            nombre.append(" (").append(oficio.getFecetTipoOficio().getNombre()).append(" ")
                    .append(oficio.getNombreCompulsado()).append(" )");
        }

        return nombre.toString();
    }

    private String llenarNombreOficioCompulsa(FecetOficio oficio) {
        StringBuilder nombre = new StringBuilder(oficio.getFecetTipoOficio().getNombre());

        if (oficio.getRfcCompulsado() != null && !oficio.getRfcCompulsado().trim().equals("")) {
            nombre.append(" (").append(oficio.getRfcCompulsado()).append(" )");
        }
        if (oficio.getNombreCompulsado() != null && !oficio.getNombreCompulsado().trim().equals("")) {
            nombre.append(" (").append(oficio.getNombreCompulsado()).append(" )");
        }

        return nombre.toString();
    }

    public List<FecetDocOrden> obtenerDocExpediente(BigDecimal idOrden) {
        return fecetDocOrdenDao.findByFecetOrdenPdf(idOrden);
    }

}
