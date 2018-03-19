package mx.gob.sat.siat.feagace.negocio.ordenes.pruebaspericiales.firma.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import mx.gob.sat.siat.feagace.modelo.dto.common.FirmaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PruebasPericialesDocsVO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.firma.helper.FirmaHelper;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CadenaFirmadoUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.DateUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.FormatosFechasEnum;

@Component
public class FirmaPruebasPericialesContribuyenteHelper extends FirmaHelper {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public List<FirmaDTO> armarCadena(Object objDatos, String rfc) {
        logger.info(rfc);
        PruebasPericialesDocsVO docs = (PruebasPericialesDocsVO)objDatos;
        List<FirmaDTO> firmas = new ArrayList<FirmaDTO>();
        Date date = new Date();
        StringBuilder nombreArchivos = new StringBuilder();
        StringBuilder tamanioArchivos = new StringBuilder();
        for (FecetDocPruebasPericiales doc : docs.getListaDocsPruebasPericiales()) {
            nombreArchivos.append(doc.getNombreArchivo());
            nombreArchivos.append(" ");
            tamanioArchivos.append(doc.getTamanioArchivo());
            tamanioArchivos.append("K ");
        }
        int totalDocs = docs.getListaDocsPruebasPericiales().size();
        String rfcTmp = docs.getPerfil().getRfcContribuyente() == null ? " " : docs.getPerfil().getRfcContribuyente();
        String nombreRol =
            docs.getPerfil().getNombreRol().equals(Constantes.CONTRIBUYENTE_COMBO) ? " " : docs.getPerfil().getNombreRol();

        final CadenaFirmadoUtil cadenasFirmado =
            new CadenaFirmadoUtil(docs.getOrdenSeleccionado().getIdOrden().toString(),
                                  docs.getOrdenSeleccionado().getNumeroOrden() + "| | |" +
                                  DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY, date) +
                                  "|" + DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_24H_HHMM, date) + "|" +
                                  docs.getPerfil().getRfc() + "|" + nombreArchivos.toString() + "|" +
                                  tamanioArchivos.toString() + "|" + nombreRol + "|" + rfcTmp + "|" + totalDocs);

        FirmaDTO firmaDTO = new FirmaDTO();
        firmaDTO.setId(String.valueOf(docs.getOrdenSeleccionado().getIdOrden()));
        firmaDTO.setCadena(cadenasFirmado.getCadena());
        firmas.add(firmaDTO);
        return firmas;
    }

}
