package mx.gob.sat.siat.feagace.negocio.propuestas.carga;

import java.io.InputStream;

import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;

public interface CargaDocumentacionMasivaService {

    String getConsecutivo();

    /**
     * @param destino
     * @param is
     * @param nombreArchivo
     * @throws NegocioException
     */
    
    String cargaDocumento(String destino, InputStream is, String nombreArchivo) throws NegocioException;
}
