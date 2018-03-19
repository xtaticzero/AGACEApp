/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.insumos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RegistroInsumosDto implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private Map<BigDecimal, FoliosProcesadosDto> folios;
    private List<FecetInsumo> insumosRegistrados = new ArrayList<FecetInsumo>();
    private List<FecetInsumo> insumosNoRegistrados = new ArrayList<FecetInsumo>();

    public void setInsumosRegistrados(List<FecetInsumo> insumosRegistrados) {
        this.insumosRegistrados = insumosRegistrados;
    }

    public List<FecetInsumo> getInsumosRegistrados() {
        return insumosRegistrados;
    }

    public void setInsumosNoRegistrados(List<FecetInsumo> insumosNoRegistrados) {
        this.insumosNoRegistrados = insumosNoRegistrados;
    }

    public List<FecetInsumo> getInsumosNoRegistrados() {
        return insumosNoRegistrados;
    }

    public Map<BigDecimal, FoliosProcesadosDto> getFolios() {
        return folios;
    }

    public void setFolios(Map<BigDecimal, FoliosProcesadosDto> folios) {
        this.folios = folios;
    }
}
