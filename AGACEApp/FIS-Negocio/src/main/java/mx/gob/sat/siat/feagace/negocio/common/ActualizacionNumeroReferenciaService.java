package mx.gob.sat.siat.feagace.negocio.common;

public interface ActualizacionNumeroReferenciaService {

    void actualizaReferenciaOficio(String id, String numeroReferencia);

    void actualizaReferenciaOrden(String id, String numeroReferencia);

    void actualizaReferenciaOficioProrroga(String id, String numeroReferencia);

    void actualizaReferenciaOrdenProrroga(String id, String numeroReferencia);

    void actualizaReferenciaPruebaPericial(String id, String numeroReferencia);

    void actualizaTabla(String tipoTabla, String id, String numeroReferencia);
}
