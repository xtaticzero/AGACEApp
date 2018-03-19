package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.math.BigDecimal;

public class ClaveFolioOficioDTO {
    private BigDecimal idArace;
    private String claveAdministracionGeneral;
    private String claveArace;
    private String claveAdministracionArea;
    private String claveSubadmistracionArea;
    private String claveJefaturaDepto;
    
    public ClaveFolioOficioDTO(){
        
    }


    public void setIdArace(BigDecimal idArace) {
        this.idArace = idArace;
    }

    public BigDecimal getIdArace() {
        return idArace;
    }

    public void setClaveAdministracionGeneral(String claveAdministracionGeneral) {
        this.claveAdministracionGeneral = claveAdministracionGeneral;
    }

    public String getClaveAdministracionGeneral() {
        return claveAdministracionGeneral;
    }

    public void setClaveArace(String claveArace) {
        this.claveArace = claveArace;
    }

    public String getClaveArace() {
        return claveArace;
    }

    public void setClaveAdministracionArea(String claveAdministracionArea) {
        this.claveAdministracionArea = claveAdministracionArea;
    }

    public String getClaveAdministracionArea() {
        return claveAdministracionArea;
    }

    public void setClaveSubadmistracionArea(String claveSubadmistracionArea) {
        this.claveSubadmistracionArea = claveSubadmistracionArea;
    }

    public String getClaveSubadmistracionArea() {
        return claveSubadmistracionArea;
    }

    public void setClaveJefaturaDepto(String claveJefaturaDepto) {
        this.claveJefaturaDepto = claveJefaturaDepto;
    }

    public String getClaveJefaturaDepto() {
        return claveJefaturaDepto;
    }
}
