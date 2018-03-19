package mx.gob.sat.siat.feagace.negocio.common;

import java.util.List;

public interface CopiarDocumentosService {
    
    void copiarAnexos(String urlOrigen, String urlDestino);
    
    void eliminarAnexos(List<String> rutasAnexos);

}
