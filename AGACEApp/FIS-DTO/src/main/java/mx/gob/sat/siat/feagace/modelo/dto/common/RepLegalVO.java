package mx.gob.sat.siat.feagace.modelo.dto.common;



public class RepLegalVO{


    @SuppressWarnings("compatibility:4934671604909580546")
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String rfc;
    private String curp;
    private String tipo;
    private String fechaInicio;
    private String fechaFin;
    
    public RepLegalVO(){
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getRfc() {
        return rfc;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getCurp() {
        return curp;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getFechaFin() {
        return fechaFin;
    }
}
