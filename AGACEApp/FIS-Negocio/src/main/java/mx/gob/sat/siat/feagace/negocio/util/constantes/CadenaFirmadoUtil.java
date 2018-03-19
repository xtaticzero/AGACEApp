package mx.gob.sat.siat.feagace.negocio.util.constantes;

public class CadenaFirmadoUtil {
    private String id;
    private String cadena;
    
    public CadenaFirmadoUtil () {      
    }
    
    public CadenaFirmadoUtil (String idDoc, String cadenaOriginal) {
        id = idDoc;
        cadena = cadenaOriginal;
    }

      public String getId() {
        return id;
    }

    public String getCadena() {
        return cadena;
    }
}
