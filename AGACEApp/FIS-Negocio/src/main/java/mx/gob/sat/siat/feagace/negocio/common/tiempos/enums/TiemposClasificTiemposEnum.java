package mx.gob.sat.siat.feagace.negocio.common.tiempos.enums;

public enum TiemposClasificTiemposEnum {
    ANIO(1,"Anios"),
    MES(2,"Meses"),
    DIA(3,"Dias");
    
    private final int idTipoPlazo;
    
    private final String descripcion;
    
    private TiemposClasificTiemposEnum(int idTipoPlazo, String descripcion) {
        this.idTipoPlazo = idTipoPlazo;
        this.descripcion = descripcion;
    }

    public int getIdTipoPlazo() {
        return idTipoPlazo;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    public static TiemposClasificTiemposEnum parse(int idTipoPlazo){
        for(TiemposClasificTiemposEnum tipoPlazo:TiemposClasificTiemposEnum.values()){
            if(tipoPlazo.getIdTipoPlazo() == idTipoPlazo){
               return tipoPlazo;
            }
        }
        return null;
    }
    
}
