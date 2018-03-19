package mx.gob.sat.siat.feagace.negocio.util.correo.campos;

public enum AvisoContribuyentePPEEAmparado {
    RFC("rfc"),
    NOMBRE("nombre"),
    ESTATUS("estatus"),
    AMPARADO("PPEE y/o Amparado");
    
    private String fieldName;

    private AvisoContribuyentePPEEAmparado(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String toString() {
        return fieldName;
    }
}