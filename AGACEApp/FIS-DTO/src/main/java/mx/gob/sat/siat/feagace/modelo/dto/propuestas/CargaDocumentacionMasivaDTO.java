package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.io.Serializable;
import java.util.List;

public class CargaDocumentacionMasivaDTO implements Serializable {

    private static final long serialVersionUID = 295702921934675781L;

    private String nombreDocumento;
    private String rutaDocumento;
    private List<String> documentos;
    private String folioDocumento;
    private String estatusCarga;

    private int row;

    public void setRutaDocumento(String rutaDocumento) {
        this.rutaDocumento = rutaDocumento;
    }

    public String getRutaDocumento() {
        return rutaDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setFolioDocumento(String folioDocumento) {
        this.folioDocumento = folioDocumento;
    }

    public String getFolioDocumento() {
        return folioDocumento;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getRow() {
        return row;
    }

    public void setDocumentos(List<String> documentos) {
        this.documentos = documentos;
    }

    public List<String> getDocumentos() {
        return documentos;
    }

    public void setEstatusCarga(String estatusCarga) {
        this.estatusCarga = estatusCarga;
    }

    public String getEstatusCarga() {
        return estatusCarga;
    }
}
