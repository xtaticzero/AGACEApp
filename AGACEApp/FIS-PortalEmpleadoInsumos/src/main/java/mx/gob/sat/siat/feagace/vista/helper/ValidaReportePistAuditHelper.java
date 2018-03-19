package mx.gob.sat.siat.feagace.vista.helper;

import mx.gob.sat.siat.base.helper.BaseHelper;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.RepPistaAuditModel;

import org.springframework.stereotype.Component;

@Component
public class ValidaReportePistAuditHelper extends BaseHelper {
    @SuppressWarnings("compatibility:-3668688762056798810")
    private static final long serialVersionUID = -8549647830878861460L;
    
    private int contador = 0;
    private int contOrden = 0;
    private int contReg = 0;
    private int contRfcUser = 0;
    private int contNomUser = 0;
    
    private static final int CERO = 0;
    private static final int UNO = 1;
    private static final int DOS = 2;

    public ValidaReportePistAuditHelper() {
        super();
    }
    
    public int validaMes (int mes, int anio){
        int anioTMP = anio;
        if(mes == CERO || mes == UNO || mes == DOS) {
            anioTMP = anioTMP - UNO;
        }
        return anioTMP;
    }
    
    public boolean validaCampoReg(RepPistaAuditModel repPistAuditModel){
        if(repPistAuditModel.getIdRegistro() != null && !repPistAuditModel.getIdRegistro().trim().isEmpty()){
            repPistAuditModel.setCampoActivoOrden(true);
            repPistAuditModel.setCampoActivoRfcUser(true);
            repPistAuditModel.setCampoActivoUsuario(true);
            repPistAuditModel.setCampoActivoCalendar(true);
            contador ++;
            contReg ++;
            return true;
        }else{
            activaCampos(repPistAuditModel);
            return false;
        }
    }
    
    public boolean validaCampoOrden(RepPistaAuditModel repPistAuditModel){
        if(repPistAuditModel.getNumOrden() != null && !repPistAuditModel.getNumOrden().trim().isEmpty()){
            repPistAuditModel.setCampoActivoRegistro(true);
            repPistAuditModel.setCampoActivoRfcUser(true);
            repPistAuditModel.setCampoActivoUsuario(true);
            repPistAuditModel.setCampoActivoCalendar(true);
            contador ++;
            contOrden ++;
            return true;
        }else{
            activaCampos(repPistAuditModel);
            return false;
        }
    }
    
    public boolean validaCampoRfcUser(RepPistaAuditModel repPistAuditModel){
        if(repPistAuditModel.getRfcUsuario() != null && !repPistAuditModel.getRfcUsuario().trim().isEmpty()){
            repPistAuditModel.setCampoActivoRegistro(true);
            repPistAuditModel.setCampoActivoUsuario(true);
            repPistAuditModel.setCampoActivoOrden(true);
            contador ++;
            contRfcUser ++;
            return true;
        }else{
            activaCampos(repPistAuditModel);
            return false;
        }
    }
    
    public boolean validaCampoNomUser(RepPistaAuditModel repPistAuditModel){
        if(repPistAuditModel.getNomUsuario() != null && !repPistAuditModel.getNomUsuario().trim().isEmpty()){
            repPistAuditModel.setCampoActivoRegistro(true);
            repPistAuditModel.setCampoActivoRfcUser(true);
            repPistAuditModel.setCampoActivoOrden(true);
            contador ++;
            contNomUser ++;
            return true;
        }else{
            activaCampos(repPistAuditModel);
            return false;
        }
    }
    
    public void activaCampos(RepPistaAuditModel repPistAuditModel){
        repPistAuditModel.setCampoActivoOrden(false);
        repPistAuditModel.setCampoActivoRegistro(false);
        repPistAuditModel.setCampoActivoRfcUser(false);
        repPistAuditModel.setCampoActivoUsuario(false);
        repPistAuditModel.setCampoActivoCalendar(false);
        contador = 0;
        contOrden = 0;
        contReg = 0;
        contNomUser = 0;
        contRfcUser = 0;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public int getContador() {
        return contador;
    }

    public void setContOrden(int contOrden) {
        this.contOrden = contOrden;
    }

    public int getContOrden() {
        return contOrden;
    }

    public void setContReg(int contReg) {
        this.contReg = contReg;
    }

    public int getContReg() {
        return contReg;
    }

    public void setContRfcUser(int contRfcUser) {
        this.contRfcUser = contRfcUser;
    }

    public int getContRfcUser() {
        return contRfcUser;
    }

    public void setContNomUser(int contNomUser) {
        this.contNomUser = contNomUser;
    }

    public int getContNomUser() {
        return contNomUser;
    }
}
