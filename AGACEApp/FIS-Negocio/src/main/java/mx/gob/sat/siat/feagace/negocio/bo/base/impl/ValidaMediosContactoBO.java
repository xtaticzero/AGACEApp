package mx.gob.sat.siat.feagace.negocio.bo.base.impl;

import mx.gob.sat.siat.feagace.negocio.bo.base.BO;
import java.util.List;

import mx.gob.sat.siat.buzon.model.MedioComunicacion;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetDetalleContribuyenteDao;

public class ValidaMediosContactoBO extends BO<ValidaMediosContactoBO> {

    private String rfc;
    private boolean flag;
    private String message;
    private List<MedioComunicacion> mediosComunicacion;
    private boolean pPEE;
    private boolean amparado;
    private FecetDetalleContribuyenteDao fecetDetalleContribuyenteDao;
    private boolean validaSoloMediosContacto;

    public ValidaMediosContactoBO() {
        super();
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getRfc() {
        return rfc;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }   

    public List<MedioComunicacion> getMediosComunicacion() {
        return mediosComunicacion;
    }

    public void setMediosComunicacion(List<MedioComunicacion> mediosComunicacion) {
        this.mediosComunicacion = mediosComunicacion;
    }

    public boolean ispPEE() {
        return pPEE;
    }

    public void setpPEE(boolean pPEE) {
        this.pPEE = pPEE;
    }

    public void setPPEE(boolean pPEE) {
        this.pPEE = pPEE;
    }

    public boolean isPPEE() {
        return pPEE;
    }


    public void setAmparado(boolean amparado) {
        this.amparado = amparado;
    }

    public boolean isAmparado() {
        return amparado;
    }

    public void setFecetDetalleContribuyenteDao(FecetDetalleContribuyenteDao fecetDetalleContribuyenteDao) {
        this.fecetDetalleContribuyenteDao = fecetDetalleContribuyenteDao;
    }

    public FecetDetalleContribuyenteDao getFecetDetalleContribuyenteDao() {
        return fecetDetalleContribuyenteDao;
    }

    public void setValidaSoloMediosContacto(boolean validaSoloMediosContacto) {
        this.validaSoloMediosContacto = validaSoloMediosContacto;
    }

    public boolean isValidaSoloMediosContacto() {
        return validaSoloMediosContacto;
    }
}
