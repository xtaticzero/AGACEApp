package mx.gob.sat.siat.feagace.vista.util;

public enum SessionAttributeOrdenesEnum {
    
    MSG_ERROR_SESSION("mensaje");

    private final String attributeName;

    private SessionAttributeOrdenesEnum(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeName() {
        return attributeName;
    }

}
