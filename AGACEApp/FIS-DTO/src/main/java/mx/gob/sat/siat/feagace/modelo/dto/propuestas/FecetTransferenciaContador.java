/**
 * 
 */
package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;

/**
 * @author Sergio.vaca
 *
 */
public class FecetTransferenciaContador extends FecetTransferencia {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private int totalDocumentos;
    
    private FececUnidadAdministrativa fececUnidadAdministrativa;

    public int getTotalDocumentos() {
        return totalDocumentos;
    }

    public void setTotalDocumentos(int totalDocumentos) {
        this.totalDocumentos = totalDocumentos;
    }

    public FececUnidadAdministrativa getFececUnidadAdministrativa() {
        return fececUnidadAdministrativa;
    }

    public void setFececUnidadAdministrativa(FececUnidadAdministrativa fececUnidadAdministrativa) {
        this.fececUnidadAdministrativa = fececUnidadAdministrativa;
    }
}
