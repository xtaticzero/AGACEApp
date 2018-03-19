package mx.gob.sat.siat.feagace.negocio.ordenes.oficio.promocion.firma.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.FirmaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAlegatoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PromocionDocsVO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.firma.helper.FirmaHelper;
import mx.gob.sat.siat.feagace.negocio.util.constantes.BusinessUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CadenaFirmadoUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.DateUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.FormatosFechasEnum;

import org.springframework.stereotype.Component;

@Component
public class FirmaPromocionOficioHelper extends FirmaHelper {

    @SuppressWarnings("compatibility:-5397113692631904149")
    private static final long serialVersionUID = 1L;

    @Override
    public List<FirmaDTO> armarCadena(Object objDatos, String rfc) {
        logger.debug(rfc);
        PromocionDocsVO docs = (PromocionDocsVO)objDatos;
        List<FirmaDTO> firmas = new ArrayList<FirmaDTO>();

        Date date = new Date();
        StringBuilder nombreArchivos = new StringBuilder();
        nombreArchivos.append(docs.getNombreArchivo());
        nombreArchivos.append(" ");
        for (FecetAlegatoOficio alegato : docs.getListaPruebasAlegatosOficioCargadas()) {
            nombreArchivos.append(alegato.getNombreArchivo());
            nombreArchivos.append(" ");
        }
        int totalDocs = docs.getListaPruebasAlegatosOficioCargadas().size() + 1;
        String rfcTmp =
            docs.getPerfil().getRfcContribuyente() == null ? docs.getPerfil().getRfc() : docs.getPerfil().getRfcContribuyente();
        String nombreRol =
            docs.getPerfil().getNombreRol().equals(Constantes.CONTRIBUYENTE_COMBO) ? " " : docs.getPerfil().getNombreRol();

        final CadenaFirmadoUtil cadenasFirmado =
            new CadenaFirmadoUtil(docs.getOrdenSeleccionado().getIdOrden().toString(),
                                  docs.getOrdenSeleccionado().getNumeroOrden().toString() + "|" +
                                  docs.getOficioSeleccionado().getIdOficio() + "|" +
                                  docs.getOficioSeleccionado().getFecetTipoOficio().getNombre() + "|" +
                                  DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY, date) +
                                  "|" + DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_24H_HHMM, date) + "|" +
                                  docs.getPerfil().getRfc() + "|" +
                                  BusinessUtil.getTipoPromocionPorTipoOficioMetodo(docs.getOficioSeleccionado().getFecetTipoOficio().getIdTipoOficio()) +
                                  "|" + nombreArchivos.toString() + "|" + nombreRol + "|" + rfcTmp + "|" + totalDocs);

        FirmaDTO firmaDTO = new FirmaDTO();
        firmaDTO.setId(String.valueOf(docs.getOficioSeleccionado().getIdOficio()));
        firmaDTO.setCadena(cadenasFirmado.getCadena());
        firmas.add(firmaDTO);

        return firmas;
    }
}
