package mx.gob.sat.siat.feagace.negocio.ordenes.impl;

import java.io.IOException;

import java.math.BigDecimal;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Formatter;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FececAgteAduanalDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FececRevisionDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.roles.FececRepLegalDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FeceaMetodoDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrdenPk;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCambioMetodo;
import mx.gob.sat.siat.feagace.modelo.util.ConstantesError;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.ordenes.CargarOrdenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("cargarOrdenService")
public class CargarOrdenServiceImpl extends OrdenesServiceBase implements CargarOrdenService {

    private static final long serialVersionUID = 8162280842172537028L;

    @Autowired
    private transient FeceaMetodoDao feceaMetodoDao;
    @Autowired
    private transient FececRevisionDao fececRevisionDao;
    @Autowired
    private transient FececRepLegalDao fececRepLegalDao;
    @Autowired
    private transient FececAgteAduanalDao fececAgteAduanalDao;

    /**
     * strCerosIzq.
     */
    private static final String STR4CEROSIZQ = "%05d";

    /**
     * Metodo guardarOrdenesLista
     *
     * @param listaOrdenes
     */
    @Override
    public void guardarOrdenesLista(List<AgaceOrden> listaOrdenes) {

        for (AgaceOrden orden : listaOrdenes) {
            getAgaceOrdenDao().insert(orden);
        }

    }

    /**
     * Metodo getMetodos
     *
     * @return List
     */
    @Override
    public List<FececMetodo> getMetodos() {

        return feceaMetodoDao.findAll();

    }

    /**
     * Metodo subirArchivoServidor
     *
     * @param orden
     * @return boolean
     * @throws NegocioException
     */
    @Override
    public boolean subirArchivoServidor(final AgaceOrden orden) throws NegocioException {
        boolean cargoArchivo = false;
        if (!ArchivoOrdenUtil.verificarExistenciaCveOrden(orden)) {
            try {
                ArchivoOrdenUtil.guardarArchivoOrden(orden);
                cargoArchivo = true;
            } catch (IOException e) {
                logger.error(ConstantesError.ERROR_CARGAR_ARCHIVO + e.getCause(), e);
            }
        } else {
            throw new NegocioException(ConstantesError.ERROR_EXISTE_NUMERO_ORDEN);
        }

        return cargoArchivo;
    }

    /**
     * Metodo guardarOrden
     *
     * @param orden
     * @return BigDecimal
     */
    @Transactional
    @Override
    public BigDecimal guardarOrden(final AgaceOrden orden) {
        //Se debe insertar los nuevos Asociados a la tabla FECET_ASOCIADO
        return getAgaceOrdenDao().insert(orden).getIdOrden();

    }

    /**
     * Metodo getListaAuditor
     *
     * @param rfcAuditor
     * @return List
     */
    @Override
    public List<AgaceOrden> getListaAuditor(final String rfcAuditor) {

        return getAgaceOrdenDao().getListaAuditor(rfcAuditor);

    }

    /**
     * Metodo borrarOrdenAuditor
     *
     * @param orden
     */
    @Override
    public void borrarOrdenAuditor(final AgaceOrden orden) {

        getAgaceOrdenDao().updateFechaBaja(new AgaceOrdenPk(orden.getIdOrden()));

    }

    /**
     * Metodo enviarOrdenes
     *
     * @param lista
     */
    @Transactional
    @Override
    public void enviarOrdenes(final List<AgaceOrden> lista) {

        for (AgaceOrden orden : lista) {
            //Indicar que estatus es el que pertenecera para no tener validacion ni firma
            getAgaceOrdenDao().update(new AgaceOrdenPk(orden.getIdOrden()), orden);
        }
    }

    /**
     * Metodo setFeceaMetodoDao
     *
     * @param feceaMetodoDao
     */
    public void setFeceaMetodoDao(FeceaMetodoDao feceaMetodoDao) {
        this.feceaMetodoDao = feceaMetodoDao;
    }

    /**
     * Metodo getFeceaMetodoDao
     *
     * @return FeceaMetodoDao
     */
    public FeceaMetodoDao getFeceaMetodoDao() {
        return feceaMetodoDao;
    }

    /**
     * Metodo acutalizarOrden
     *
     * @param pk
     * @param dto
     */
    @Override
    public void acutalizarOrden(AgaceOrdenPk pk, AgaceOrden dto) {
        getAgaceOrdenDao().update(pk, dto);
    }

    /**
     * Metodo getOrdenById
     *
     * @param idOrden
     * @return AgaceOrden
     */
    @Override
    public AgaceOrden getOrdenById(BigDecimal idOrden) {

        return getAgaceOrdenDao().findByPrimaryKey(idOrden);

    }

    /**
     * Metodo getClaveOrden
     *
     * @param idArace
     * @param cambioMetodo
     * @return String
     */
    @Override
    public String getClaveOrden(final BigDecimal idArace, final FecetCambioMetodo cambioMetodo) {
        Formatter fmt;
        StringBuilder homoclaveRevision = new StringBuilder();

        StringBuilder cveOrden = new StringBuilder();
        Date fechaActual = new Date();
        String anioDosDigitos = new SimpleDateFormat("yy").format(fechaActual);
        fmt = new Formatter();

        cveOrden.append(homoclaveRevision.toString());
        cveOrden.append(String.valueOf(fmt.format(STR4CEROSIZQ, getAgaceOrdenDao().getClaveOrden())));
        cveOrden.append("/");
        cveOrden.append(anioDosDigitos);

        return cveOrden.toString();

    }

    public void setFececRevisionDao(final FececRevisionDao fececRevisionDao) {
        this.fececRevisionDao = fececRevisionDao;
    }

    public FececRevisionDao getFececRevisionDao() {
        return fececRevisionDao;
    }

    public void setFececRepLegalDao(FececRepLegalDao fececRepLegalDao) {
        this.fececRepLegalDao = fececRepLegalDao;
    }

    public FececRepLegalDao getFececRepLegalDao() {
        return fececRepLegalDao;
    }

    public void setFececAgteAduanalDao(FececAgteAduanalDao fececAgteAduanalDao) {
        this.fececAgteAduanalDao = fececAgteAduanalDao;
    }

    public FececAgteAduanalDao getFececAgteAduanalDao() {
        return fececAgteAduanalDao;
    }
}
