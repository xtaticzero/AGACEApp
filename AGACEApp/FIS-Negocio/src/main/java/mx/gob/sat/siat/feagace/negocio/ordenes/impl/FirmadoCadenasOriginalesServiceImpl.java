package mx.gob.sat.siat.feagace.negocio.ordenes.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FecetContribuyenteDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas.FececAuditorDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetDetalleNyVDAO;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleNyV;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FeceaRechazoOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.OrdenFirmada;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.OrdenPorFirmar;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.negocio.EmpleadoService;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.ordenes.FirmadoCadenasOriginalesService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.FirmadoDocumentos;

@Service("firmadoCadenasOriginalesService")
public class FirmadoCadenasOriginalesServiceImpl extends OrdenesServiceBase implements FirmadoCadenasOriginalesService {

    private static final long serialVersionUID = -7948017091575221062L;

    @Autowired
    private transient FececAuditorDao fececAuditorDao;
    @Autowired
    private transient FecetDocOrdenDao fecetDocOrdenDao;
    @Autowired
    private transient FecetContribuyenteDao fecetContribuyenteDao;
    @Autowired
    private transient FecetPropuestaDao fecetPropuestaDao;
    @Autowired
    private transient EmpleadoService empleadoService;
    @Autowired
    private transient FecetDetalleNyVDAO fecetDetalleNyVDAO;

    @Autowired
    private transient FecetOficioDao fecetOficioDao;

    private List<String> listaID = new ArrayList<String>();
    private FeceaRechazoOrden feceaRechazoOrden;
    private List<String> archivosExitosos;

    private boolean exitoEliminaArchivo;

    /**
     * Method for initializing the DTOs
     */
    @Override
    public void initializer() {
        feceaRechazoOrden = new FeceaRechazoOrden();
    }

    @Override
    public List<OrdenPorFirmar> getOrdenesPorFirmarPorMetodo(BigDecimal idEstatus, BigDecimal idMetodo, BigDecimal idEmpleado) {
        List<OrdenPorFirmar> ordenesPorFirmar = new ArrayList<OrdenPorFirmar>();
        List<AgaceOrden> ordenes = getAgaceOrdenDao().getOrdenesPorFirmarPorMetodo(idEstatus, idMetodo, idEmpleado);
        Map<BigDecimal, EmpleadoDTO> listaAuditor = new HashMap<BigDecimal, EmpleadoDTO>();
        if (!ordenes.isEmpty()) {
            for (AgaceOrden orden : ordenes) {
                OrdenPorFirmar opf = new OrdenPorFirmar();
                opf.setIdRegistro(orden.getIdRegistroPropuesta());
                opf.setIdPropuesta(orden.getIdPropuesta().toString());
                opf.setIdOrden(orden.getIdOrden().toString());
                opf.setNumeroOrden(orden.getNumeroOrden());
                opf.setNumeroOficio(orden.getFolioOficio());
                EmpleadoDTO empleado = null;
                if (!listaAuditor.containsKey(orden.getIdAuditor())) {
                    try {
                        empleado = empleadoService.getEmpleadoCompleto(orden.getIdAuditor().intValue());
                        opf.setNombreAuditor(empleado.getNombreCompleto());
                        listaAuditor.put(empleado.getIdEmpleado(), empleado);
                    } catch (EmpleadoServiceException e) {
                        logger.error("Error al obtener datos del servicio de empleado agace", e);
                        opf.setNombreAuditor("");
                    }
                } else {
                    empleado = listaAuditor.get(orden.getIdAuditor());
                    opf.setNombreAuditor(empleado.getNombreCompleto());
                }

                List<FecetDocOrden> docOrden = fecetDocOrdenDao.findByFecetOrdenActivo(orden.getIdOrden());
                if (!docOrden.isEmpty()) {
                    DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
                    opf.setFechaCarga(df.format((docOrden.get(0)).getFechaCreacion()));
                    opf.setRutaArchivo(docOrden.get(0).getRutaArchivo());
                    opf.setNombreArchivo((docOrden.get(0)).getNombreArchivo());
                }
                FecetContribuyente contribuyente = fecetContribuyenteDao.findByPrimaryKey(orden.getIdContribuyente());
                if (contribuyente != null) {
                    opf.setRfcContribuyente(contribuyente.getRfc());
                    opf.setNombreContribuyente(contribuyente.getNombre());
                }
                List<FecetPropuesta> propuesta = fecetPropuestaDao.findWhereIdPropuestaEquals(orden.getIdPropuesta());
                opf.setPrioridadSugerida(propuesta != null && !propuesta.isEmpty() ? propuesta.get(0).getPrioridadSugerida() : null);
                ordenesPorFirmar.add(opf);
            }
        }

        return ordenesPorFirmar;
    }

    /**
     * Metodo firmarDocumentos
     *
     * @param id
     * @param cadena
     * @param firma Llama al metodo que realiza la firma del oficio
     */
    public void firmarDocumentos(String id, String cadena, String firma) {
        FirmadoDocumentos firmadoDocumentos = new FirmadoDocumentos();
        firmadoDocumentos.firmandoDocumento(id, cadena, firma);
    }

    @Override
    public int findOrdenRechazoEstatus(final AgaceOrden rechazoSeleccionado) {

        return fececAuditorDao.findOrdenRechazoEstatus(rechazoSeleccionado.getIdOrden());

    }

    /**
     * Metodo que cambia el estatus de la orden firmada, se cambian a los
     * valores VALIDAR_IND=1 FIRMAR_IND = 1
     *
     * @param rechazoDescripcion
     * @return
     */
    @Transactional
    @Override
    public BigDecimal enviarRechazoOrdenNew(final AgaceOrden rechazoSeleccionado,
            final String rechazoDescripcion) {
        BigDecimal folioRechazo;

        this.feceaRechazoOrden = new FeceaRechazoOrden();

        Date sysdate = new Date();
        feceaRechazoOrden.setDescripcion(rechazoDescripcion);
        feceaRechazoOrden.setFechaInicio(sysdate);
        feceaRechazoOrden.setEstatus("0");
        feceaRechazoOrden.setIdOrden(rechazoSeleccionado.getIdOrden());
        //Poner el RFC desde el OBJ del Contribuyente
        folioRechazo = fececAuditorDao.insertFecetRechazoOrden(feceaRechazoOrden);

        return folioRechazo;
    }

    /**
     * Metodo setListaID
     *
     * @param listaID
     */
    public void setListaID(List<String> listaID) {
        this.listaID = listaID;
    }

    /**
     * Metodo getListaID
     *
     * @return List
     */
    public List<String> getListaID() {
        return listaID;
    }

    /**
     * Metodo setFececAuditorDao
     *
     * @param fececAuditorDao
     */
    public void setFececAuditorDao(FececAuditorDao fececAuditorDao) {
        this.fececAuditorDao = fececAuditorDao;
    }

    /**
     * Metodo getFececAuditorDao
     *
     * @return Object
     */
    public FececAuditorDao getFececAuditorDao() {
        return fececAuditorDao;
    }

    @Override
    public List<OrdenFirmada> obtenerOrdenesFirmadas(BigDecimal idEmpleado, BigDecimal idMetodo) {
        List<OrdenFirmada> ordenesFirmadas = new ArrayList<OrdenFirmada>();
        List<AgaceOrden> ordenes = getAgaceOrdenDao().obtenerOrdenesFirmadas(new BigDecimal(TipoEstatusEnum.FIRMANTE_REGISTRO_FIRMADO_ENVIADO_NOTIFICACION_CONTRIBUYENTE.getId()),
                idEmpleado, idMetodo);
        Map<BigDecimal, EmpleadoDTO> listaAuditor = new HashMap<BigDecimal, EmpleadoDTO>();

        //Se descartan las mini Ordes creadas por las compulsas de la lista de Ordenes Firmadas
        filtraMiniOrdenes(ordenes);

        if (ordenes != null && !ordenes.isEmpty()) {
            for (AgaceOrden orden : ordenes) {
                FecetDetalleNyV detalleNyV = new FecetDetalleNyV();
                if (orden.getIdNyV() != null) {
                    detalleNyV.setIdNyV(orden.getIdNyV());
                    detalleNyV = fecetDetalleNyVDAO.buscaDetalleNyVPorId(detalleNyV);
                    if (detalleNyV == null) {
                        detalleNyV = new FecetDetalleNyV();
                    }
                }
                orden.setFecetDetalleNyV(detalleNyV);
            }

            DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
            for (AgaceOrden orden : ordenes) {
                OrdenFirmada ordenFirmada = new OrdenFirmada();
                ordenFirmada.setNumeroOrden(orden.getNumeroOrden());
                ordenFirmada.setNumeroOficio(orden.getFolioOficio());
                ordenFirmada.setFolioNyV(orden.getFecetDetalleNyV().getFolioNyV());
                ordenFirmada.setFechaEmision(df.format(orden.getFechaCreacion()));
                FecetContribuyente contribuyente = fecetContribuyenteDao.findByPrimaryKey(orden.getIdContribuyente());
                if (contribuyente != null) {
                    ordenFirmada.setRfcContribuyente(contribuyente.getRfc());
                    ordenFirmada.setNombreContribuyente(contribuyente.getNombre());
                }
                EmpleadoDTO empleado = null;

                if (!listaAuditor.containsKey(orden.getIdAuditor())) {
                    try {
                        empleado = empleadoService.getEmpleadoCompleto(orden.getIdAuditor().intValue());
                        ordenFirmada.setNombreAuditor(empleado.getNombreCompleto());
                        listaAuditor.put(empleado.getIdEmpleado(), empleado);
                    } catch (EmpleadoServiceException e) {
                        logger.error("Error al obtener datos del servicio de empleado agace", e);
                        ordenFirmada.setNombreAuditor("");
                    }
                } else {
                    empleado = listaAuditor.get(orden.getIdAuditor());
                    ordenFirmada.setNombreAuditor(empleado.getNombreCompleto());
                }

                List<FecetDocOrden> docOrden = fecetDocOrdenDao.findByFecetOrdenPdf(orden.getIdOrden());
                if (!docOrden.isEmpty()) {
                    ordenFirmada.setRutaArchivo((docOrden.get(0)).getRutaArchivo());
                    ordenFirmada.setNombreArchivo((docOrden.get(0)).getNombreArchivo());
                    ordenFirmada.setFechaDocOrden(docOrden.get(0).getFechaCreacion());
                }
                ordenFirmada.setOficiosFirmados(fecetOficioDao.getOficioByIdOrden(orden.getIdOrden()));
                ordenesFirmadas.add(ordenFirmada);

            }
        }
        return ordenesFirmadas;
    }

    private void filtraMiniOrdenes(List<AgaceOrden> lstOrdenesFirmadas) {
        List<AgaceOrden> lstMiniOrdenes = new ArrayList<AgaceOrden>();

        if (lstOrdenesFirmadas != null) {
            for (AgaceOrden miniOrden : lstOrdenesFirmadas) {
                if (miniOrden.isBlnCompulsa()) {
                    lstMiniOrdenes.add(miniOrden);
                }
            }
            lstOrdenesFirmadas.removeAll(lstMiniOrdenes);
        }

    }

    public void setFeceaRechazoOrden(FeceaRechazoOrden feceaRechazoOrden) {
        this.feceaRechazoOrden = feceaRechazoOrden;
    }

    public FeceaRechazoOrden getFeceaRechazoOrden() {
        return feceaRechazoOrden;
    }

    public void setArchivosExitosos(List<String> archivosExitosos) {
        this.archivosExitosos = archivosExitosos;
    }

    public List<String> getArchivosExitosos() {
        return archivosExitosos;
    }

    public void setExitoEliminaArchivo(boolean exitoEliminaArchivo) {
        this.exitoEliminaArchivo = exitoEliminaArchivo;
    }

    public boolean isExitoEliminaArchivo() {
        return exitoEliminaArchivo;
    }

    public FecetPropuestaDao getFecetPropuestaDao() {
        return fecetPropuestaDao;
    }

    public void setFecetPropuestaDao(FecetPropuestaDao fecetPropuestaDao) {
        this.fecetPropuestaDao = fecetPropuestaDao;
    }

}
