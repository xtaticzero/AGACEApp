/**
 * 
 */
package mx.gob.sat.siat.feagace.vista.insumos.validar.helper;

import java.io.File;
import java.io.Serializable;

import org.primefaces.model.UploadedFile;
import org.springframework.stereotype.Component;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.vista.util.ConstantesPropuestasMasivas;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

/**
 * @author sergio.vaca
 *
 */
@Component
public class CargaMasivaInsumosMBHelper implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public boolean validateSizeFile(final UploadedFile archivo) {
        boolean resultado = archivo.getSize() > 0L && archivo.getSize() <= Constantes.FILE_SIZE;
        if (archivo.getSize() >= Constantes.FILE_SIZE) {
            FacesUtil.addErrorMessage(null, ConstantesPropuestasMasivas.MSJ_ERROR_ARCHIVO, ConstantesPropuestasMasivas.MSJ_ARCHIVO_GRANDE);
        }
        return resultado;
    }

    public boolean validaExistenciaFolioDoc(String folioDoc) {
        String directorioFolio = Constantes.DIRECTORIO_CARGA_DOCUMENTOS_INSUMOS.concat(folioDoc).concat("/");
        File carpetaArchivos = new File(directorioFolio);
        return carpetaArchivos.exists() && carpetaArchivos.isDirectory();
    }
    
    public String obtenerRutaDocumentos(String folioDoc) {
        return Constantes.DIRECTORIO_CARGA_DOCUMENTOS_INSUMOS.concat(folioDoc).concat("/");
    }

}
