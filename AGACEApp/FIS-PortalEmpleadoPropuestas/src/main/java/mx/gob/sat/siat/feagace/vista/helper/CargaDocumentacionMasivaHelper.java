/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.helper;

import java.io.Serializable;
import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.CargaDocumentacionMasivaDTO;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class CargaDocumentacionMasivaHelper implements Serializable{
    private static final long serialVersionUID = 1400883532273959485L;
    
    private transient List<UploadedFile> uploadedFiles;
    private List<String> documentosIncorrectos;
    private transient CargaDocumentacionMasivaDTO cargaDocumentacionMasivaDto;
    private transient List<CargaDocumentacionMasivaDTO> cargaDocumentacionMasivaDtoList;
    
    public List<UploadedFile> getUploadedFiles() {
        return uploadedFiles;
    }

    public void setUploadedFiles(List<UploadedFile> uploadedFiles) {
        this.uploadedFiles = uploadedFiles;
    }

    public CargaDocumentacionMasivaDTO getCargaDocumentacionMasivaDto() {
        return cargaDocumentacionMasivaDto;
    }

    public void setCargaDocumentacionMasivaDto(CargaDocumentacionMasivaDTO cargaDocumentacionMasivaDto) {
        this.cargaDocumentacionMasivaDto = cargaDocumentacionMasivaDto;
    }

    public List<CargaDocumentacionMasivaDTO> getCargaDocumentacionMasivaDtoList() {
        return cargaDocumentacionMasivaDtoList;
    }

    public void setCargaDocumentacionMasivaDtoList(List<CargaDocumentacionMasivaDTO> cargaDocumentacionMasivaDtoList) {
        this.cargaDocumentacionMasivaDtoList = cargaDocumentacionMasivaDtoList;
    }

    public List<String> getDocumentosIncorrectos() {
        return documentosIncorrectos;
    }

    public void setDocumentosIncorrectos(List<String> documentosIncorrectos) {
        this.documentosIncorrectos = documentosIncorrectos;
    }   
}
