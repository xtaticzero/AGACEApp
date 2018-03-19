package mx.gob.sat.siat.feagace.negocio.util.correo.campos;

public enum RetroalimentacionInsumo {
    NUM_OFICIO("num_oficio"),
    RFC("rfc");

    private String data;

    private RetroalimentacionInsumo(String data) {
        this.data = data;
    }


    public String toString() {
        return data;
    }
}
