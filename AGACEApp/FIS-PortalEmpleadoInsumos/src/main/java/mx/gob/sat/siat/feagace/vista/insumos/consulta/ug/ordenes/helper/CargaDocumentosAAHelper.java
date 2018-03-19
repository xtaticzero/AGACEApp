package mx.gob.sat.siat.feagace.vista.insumos.consulta.ug.ordenes.helper;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegatoAgenteAduanal;

public final class CargaDocumentosAAHelper {

    private CargaDocumentosAAHelper() {

    }

    public static boolean checarDuplicadoPruebasAlegatoAgenteAduanal(String nombreArchivo, List<FecetAlegatoAgenteAduanal> listaPruebasAlegatosCargadas) {
        boolean duplicado = false;
        if (listaPruebasAlegatosCargadas != null) {
            for (FecetAlegatoAgenteAduanal alegato : listaPruebasAlegatosCargadas) {
                if (alegato.getNombreArchivo().equals(nombreArchivo)) {
                    duplicado = true;
                    break;
                }
            }
        }
        return duplicado;
    }
}
