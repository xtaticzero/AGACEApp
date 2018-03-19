package mx.gob.sat.siat.feagace.modelo.dto.common;

import java.io.Serializable;

public class JSonFirma implements Serializable{
    private static final long serialVersionUID = -1624410667123280665L;
    
    private int id;
    private String  cadena;
    private String  firma;
    
    public int getId() {
          return id;
     }
     public void setId(int id) {
          this.id = id;
     }
     public String getCadena() {
          return cadena;
     }
     public void setCadena(String cadena) {
          this.cadena = cadena;
     }
     public String getFirma() {
          return firma;
     }
     public void setFirma(String firma) {
          this.firma = firma;
     }     
}