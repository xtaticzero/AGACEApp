package mx.gob.sat.siat.feagace.negocio.ordenes.impl;

import com.google.gson.Gson;

import java.io.File;

import java.math.BigDecimal;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FececApodLegalDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececApodLegal;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececApodLegalPk;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AcuseRevisionElectronica;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.ConstantesError;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.ordenes.DocumentacionContribuyenteService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CadenaFirmadoUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("documentacionContribuyenteService")
public class DocumentacionContribuyenteServiceImpl extends OrdenesServiceBase implements DocumentacionContribuyenteService {

    private static final long serialVersionUID = -4106472721614478181L;

    @Autowired
    private transient FececApodLegalDao fececApodLegalDao;
    private List<String> listaID = new ArrayList<String>();
    private final List<String> json = new ArrayList<String>();
    private final transient Gson gson = new Gson();

    private static final int CUATRO = 4;
    private static final int SEIS = 6;
    private static final int UNO = 1;
    private static final int DOS = 2;
    private static final int TRES = 3;
    private static final int NUEVE = 9;
    private static final int DIEZ = 10;

    private String cadena;
    private String firma;

    @Override
    public FececApodLegal getApoderadoLegal(final FececApodLegal fececApodLegal) {
        List<FececApodLegal> apoderadoLegal;
        apoderadoLegal = fececApodLegalDao.findWhereRfcContribuyenteEquals(fececApodLegal.getRfcContribuyente());
        return apoderadoLegal.isEmpty() ? fececApodLegal : apoderadoLegal.get(0);
    }

    @Override
    public BigDecimal guardarApoderadoLegal(final FececApodLegal apoderadoLegal) {
        return fececApodLegalDao.insert(apoderadoLegal).getIdApodLegal();
    }

    @Override
    public void actualizaApoderadoLegal(final FececApodLegal apoderadoLegal) {
        fececApodLegalDao.update(new FececApodLegalPk(apoderadoLegal.getIdApodLegal()), apoderadoLegal);
    }

    /**
     * Metodo getListaCadenasJson
     *
     * @param ordenesSeleccionadas
     * @return List Metodo que genera una lista de cadenas de acuerdo a las
     * ordenes seleccionadas
     */
    @Override
    public List<String> getOrdenCadenasJson(AgaceOrden ordenSelecciona) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new Date();
        /**
         * TRAER ESTE DATO DEL OBJ DEL
         * CONTRIBordenSelecciona.getRfcContribuyente() +
         */
        final CadenaFirmadoUtil cadenasFirmado
                = new CadenaFirmadoUtil(ordenSelecciona.getIdOrden().toString(), "|"
                        + "|" + ordenSelecciona.getNumeroOrden() + "|" + ordenSelecciona.getIdOrden().toString()
                        + "|" + sdf.format(date) + "|"
                        + ordenSelecciona.getNumeroOrden().substring(CUATRO, SEIS) + "|" + "" + "|"
                        + sdf.format(date));
        json.add(gson.toJson(cadenasFirmado));
        return json;
    }

    /**
     * Metodo getListaOrdenCve
     *
     * @return List Genera una lista con CVE_ORDEN a partir del ID_ORDEN de la
     * orden seleccionada
     */
    List<String> getListaOrdenCve() {
        List<AgaceOrden> listaOrden;
        List<String> listaCveOrden = new ArrayList<String>();
        for (int i = 0; i < this.listaID.size(); i++) {
            Long orden = Long.parseLong(listaID.get(i));

            listaOrden = getAgaceOrdenDao().findWhereIdOrdenEquals(orden);
            listaCveOrden.add(listaOrden.get(0).getNumeroOrden());

        }
        return listaCveOrden;
    }

    /**
     * Metodo getListaCadenaOriginal
     *
     * @param ordenesSeleccionadas
     * @return List Metodo que genera una lista con los ID de las ordenes
     * seleccionadas
     */
    List<String> getListaCadenaOriginal(List<AgaceOrden> ordenesSeleccionadas) {
        if (ordenesSeleccionadas != null && !ordenesSeleccionadas.isEmpty()) {
            for (AgaceOrden lista : ordenesSeleccionadas) {
                listaID.add(lista.getIdOrden().toString());
            }
            return listaID;
        } else {
            logger.error(ConstantesError.ERROR_LISTA_VACIA);
            listaID = null;
            return listaID;
        }
    }

    public void setFececApodLegalDao(FececApodLegalDao fececApodLegalDao) {
        this.fececApodLegalDao = fececApodLegalDao;
    }

    public FececApodLegalDao getFececApodLegalDao() {
        return fececApodLegalDao;
    }

    /**
     * Metodo enviarFirmarDocumentos
     *
     * @param jsonFirmado Metodo que envía la cadena y firma de una orden para
     * que el oficio sea firmado
     */
    public void enviarFirmarDocumentos(String jsonFirmado) {
        List<String> listaCadenasFirmasJson = Arrays.asList(jsonFirmado.split(","));
        cadena = listaCadenasFirmasJson.get(UNO);

        firma = listaCadenasFirmasJson.get(DOS);
        cadena = cadena.substring(DIEZ, cadena.length() - UNO);
        firma = firma.substring(NUEVE, firma.length() - TRES);
    }

    @Override
    public File generaAcuseRecivoPDF(AcuseRevisionElectronica acuse) throws NegocioException {

        acuse.setTituloGeneral(Constantes.TITULO_GENERAL);
        byte[] archivoPDF = ArchivoOrdenUtil.getArregloReportePdf(acuse);
        return ArchivoOrdenUtil.byteToFile(acuse.getRutaAcuse(), archivoPDF);
    }

    public void setListaID(List<String> listaID) {
        this.listaID = listaID;
    }

    public List<String> getListaID() {
        return listaID;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public String getCadena() {
        return cadena;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getFirma() {
        return firma;
    }
}
