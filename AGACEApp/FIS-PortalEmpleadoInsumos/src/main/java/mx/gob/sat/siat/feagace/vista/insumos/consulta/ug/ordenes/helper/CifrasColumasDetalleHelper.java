package mx.gob.sat.siat.feagace.vista.insumos.consulta.ug.ordenes.helper;

public class CifrasColumasDetalleHelper {
    
    public boolean isActualizacionesVisible() {
        return actualizacionesVisible;
    }

    public void setActualizacionesVisible(boolean actualizacionesVisible) {
        this.actualizacionesVisible = actualizacionesVisible;
    }

    public boolean isMultasVisible() {
        return multasVisible;
    }

    public void setMultasVisible(boolean multasVisible) {
        this.multasVisible = multasVisible;
    }

    public boolean isRecargosVisible() {
        return recargosVisible;
    }

    public void setRecargosVisible(boolean recargosVisible) {
        this.recargosVisible = recargosVisible;
    }

    public boolean isTotalVisible() {
        return totalVisible;
    }

    public void setTotalVisible(boolean totalVisible) {
        this.totalVisible = totalVisible;
    }

    public boolean isParcialidadVisible() {
        return parcialidadVisible;
    }

    public void setParcialidadVisible(boolean parcialidadVisible) {
        this.parcialidadVisible = parcialidadVisible;
    }

    private boolean actualizacionesVisible;
    
    private boolean multasVisible;
    
    private boolean recargosVisible;
    
    private boolean totalVisible;
    
    private boolean parcialidadVisible;
}
