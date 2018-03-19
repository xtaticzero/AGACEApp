package mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model;

import java.io.Serializable;

import java.util.List;

public class ActoAdministrativo implements Serializable{
    private static final long serialVersionUID = 2170000753621288243L;
    
    private long id;
    private String nombre;
    private String descripcion;
    private String prefijoReferencia;
    private List<DocumentoActoAdministrativo> documento;


    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setPrefijoReferencia(String prefijoReferencia) {
        this.prefijoReferencia = prefijoReferencia;
    }

    public String getPrefijoReferencia() {
        return prefijoReferencia;
    }

    public void setDocumento(List<DocumentoActoAdministrativo> documento) {
        this.documento = documento;
    }

    public List<DocumentoActoAdministrativo> getDocumento() {
        return documento;
    }
}
