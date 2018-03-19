package mx.gob.sat.siat.feagace.negocio.util.correo.campos;

public enum AsignacionPropuesta {
    NUM_ID("num_id"),
    RFC("rfc");

    private String data;

    private AsignacionPropuesta(String data) {
        this.data = data;
    }


    public String toString() {
        return data;
    }
}
