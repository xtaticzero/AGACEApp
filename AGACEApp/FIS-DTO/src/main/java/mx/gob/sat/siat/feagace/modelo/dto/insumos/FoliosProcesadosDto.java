package mx.gob.sat.siat.feagace.modelo.dto.insumos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author sergio.vaca
 *
 */
public class FoliosProcesadosDto implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private Map<String, List<String>> foliosAdministrador;
    private List<String> folios;
    
    public List<String> getFolios() {
        if (folios == null) {
            folios = new ArrayList<String>();
        }
        return folios;
    }

    public Map<String, List<String>> getFoliosAdministrador() {
        return foliosAdministrador;
    }

    public void setFoliosAdministrador(Map<String, List<String>> foliosAdministrador) {
        this.foliosAdministrador = foliosAdministrador;
    }
}
