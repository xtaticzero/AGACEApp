package mx.gob.sat.siat.feagace.negocio.common.firma.helper;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import mx.gob.sat.siat.base.helper.BaseHelper;
import mx.gob.sat.siat.feagace.modelo.dto.common.FirmaDTO;

public abstract class FirmaHelper extends BaseHelper {

    private static final long serialVersionUID = -7532336414090321256L;

    public abstract List<FirmaDTO> armarCadena(Object objDatos,String rfc);
    
    @Value("${firma.url.js}")
    private String urlFirmadoJS;

    public String crearCadenaImprimible(String cadena, String firma, String selloDigital) {
        StringBuilder cadenaImprimible = new StringBuilder();
        cadenaImprimible.append("Cadena Original: ").append(cadena).append("/n/");
        cadenaImprimible.append("Firma : ").append(firma).append("/n/");
        cadenaImprimible.append("Sello Digital : ").append(selloDigital).append("/n/");
        return cadenaImprimible.toString();
    }

    public String getUrlFirmadoJS() {
        return urlFirmadoJS;
    }
}
