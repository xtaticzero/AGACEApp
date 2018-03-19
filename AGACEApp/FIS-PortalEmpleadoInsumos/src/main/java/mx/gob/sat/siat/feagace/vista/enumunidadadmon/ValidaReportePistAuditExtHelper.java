package mx.gob.sat.siat.feagace.vista.enumunidadadmon;

import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.helper.BaseHelper;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.RepPistaAuditExtModel;

@Component("validaReportePistAuditExtHelper")
public class ValidaReportePistAuditExtHelper extends BaseHelper {
   
    private static final long serialVersionUID = -3032761938342715484L;

    private int contador = 0;
    private int contadorRfcContribuyente = 0;
    private int contadorRfcRepLegal = 0;
    private int contadorRfcApodLegal = 0;
    private int contadorRfcAgentAduanal = 0;
    private int contadorRfcApodLegalRepLegal = 0;
    private int contadorNumOreden = 0;
    private int contadorIdRegistro = 0;

    private static final int CERO = 0;
    private static final int UNO = 1;
    private static final int DOS = 2;

    public ValidaReportePistAuditExtHelper() {
        super();
    }

    public int validaMes(int mes, int anio) {
        int anioResultado = anio;

        if (mes == CERO || mes == UNO || mes == DOS) {
            anioResultado = anioResultado - UNO;
        }
        return anioResultado;
    }

    public boolean validaCampoReg(RepPistaAuditExtModel repPistaAuditExtModel) {
        if (repPistaAuditExtModel.getIdRegistro() != null && !repPistaAuditExtModel.getIdRegistro().trim().isEmpty()) {
            repPistaAuditExtModel.setCampoActivoRfcContribuyente(true);
            repPistaAuditExtModel.setCampoActivoRfcRepLegal(true);
            repPistaAuditExtModel.setCampoActivoRfcApodLegal(true);
            repPistaAuditExtModel.setCampoActivoRfcAgentAduanal(true);
            repPistaAuditExtModel.setCampoActivoRfcApodLegalRepLegal(true);
            repPistaAuditExtModel.setCampoActivoNumOreden(true);
            repPistaAuditExtModel.setCampoActivoCanlendar(true);
            contador++;
            contadorIdRegistro++;
            return true;
        } else {
            activaCampos(repPistaAuditExtModel);
            return false;
        }
    }

    public boolean validaCampoRfcContry(RepPistaAuditExtModel repPistaAuditExtModel) {
        if (repPistaAuditExtModel.getRfcContribuyente() != null
                && !repPistaAuditExtModel.getRfcContribuyente().trim().isEmpty()) {
            repPistaAuditExtModel.setCampoActivoRfcRepLegal(true);
            repPistaAuditExtModel.setCampoActivoRfcApodLegal(true);
            repPistaAuditExtModel.setCampoActivoRfcAgentAduanal(true);
            repPistaAuditExtModel.setCampoActivoRfcApodLegalRepLegal(true);
            repPistaAuditExtModel.setCampoActivoNumOreden(true);
            repPistaAuditExtModel.setCampoActivoIdRegistro(true);
            contador++;
            contadorRfcContribuyente++;
            return true;
        } else {
            activaCampos(repPistaAuditExtModel);
            return false;
        }
    }

    public boolean validaCampoRfcRepLegal(RepPistaAuditExtModel repPistaAuditExtModel) {
        if (repPistaAuditExtModel.getRfcRepLegal() != null
                && !repPistaAuditExtModel.getRfcRepLegal().trim().isEmpty()) {
            repPistaAuditExtModel.setCampoActivoRfcContribuyente(true);
            repPistaAuditExtModel.setCampoActivoRfcApodLegal(true);
            repPistaAuditExtModel.setCampoActivoRfcAgentAduanal(true);
            repPistaAuditExtModel.setCampoActivoRfcApodLegalRepLegal(true);
            repPistaAuditExtModel.setCampoActivoNumOreden(true);
            repPistaAuditExtModel.setCampoActivoIdRegistro(true);
            contador++;
            contadorRfcRepLegal++;
            return true;
        } else {
            activaCampos(repPistaAuditExtModel);
            return false;
        }
    }

    public boolean validaCampoRfcApodLegal(RepPistaAuditExtModel repPistaAuditExtModel) {
        if (repPistaAuditExtModel.getRfcApodLegal() != null
                && !repPistaAuditExtModel.getRfcApodLegal().trim().isEmpty()) {
            repPistaAuditExtModel.setCampoActivoRfcContribuyente(true);
            repPistaAuditExtModel.setCampoActivoRfcRepLegal(true);
            repPistaAuditExtModel.setCampoActivoRfcAgentAduanal(true);
            repPistaAuditExtModel.setCampoActivoRfcApodLegalRepLegal(true);
            repPistaAuditExtModel.setCampoActivoNumOreden(true);
            repPistaAuditExtModel.setCampoActivoIdRegistro(true);
            contador++;
            contadorRfcApodLegal++;
            return true;
        } else {
            activaCampos(repPistaAuditExtModel);
            return false;
        }

    }

    public boolean validaCampoRfcAgentAduanal(RepPistaAuditExtModel repPistaAuditExtModel) {
        if (repPistaAuditExtModel.getRfcAgentAduanal() != null
                && !repPistaAuditExtModel.getRfcAgentAduanal().trim().isEmpty()) {
            repPistaAuditExtModel.setCampoActivoRfcContribuyente(true);
            repPistaAuditExtModel.setCampoActivoRfcRepLegal(true);
            repPistaAuditExtModel.setCampoActivoRfcApodLegal(true);
            repPistaAuditExtModel.setCampoActivoRfcApodLegalRepLegal(true);
            repPistaAuditExtModel.setCampoActivoNumOreden(true);
            repPistaAuditExtModel.setCampoActivoIdRegistro(true);
            contador++;
            contadorRfcAgentAduanal++;
            return true;
        } else {
            activaCampos(repPistaAuditExtModel);
            return false;
        }

    }

    public boolean validaCampoRfcApodLegalRepLegal(RepPistaAuditExtModel repPistaAuditExtModel) {
        if (repPistaAuditExtModel.getRfcApodLegalRepLegal() != null
                && !repPistaAuditExtModel.getRfcApodLegalRepLegal().trim().isEmpty()) {
            repPistaAuditExtModel.setCampoActivoRfcContribuyente(true);
            repPistaAuditExtModel.setCampoActivoRfcRepLegal(true);
            repPistaAuditExtModel.setCampoActivoRfcApodLegal(true);
            repPistaAuditExtModel.setCampoActivoRfcAgentAduanal(true);
            repPistaAuditExtModel.setCampoActivoNumOreden(true);
            repPistaAuditExtModel.setCampoActivoIdRegistro(true);
            contador++;
            contadorRfcApodLegalRepLegal++;
            return true;
        } else {
            activaCampos(repPistaAuditExtModel);
            return false;
        }
    }

    public boolean validaCampoOrden(RepPistaAuditExtModel repPistaAuditExtModel) {
        if (repPistaAuditExtModel.getNumOrden() != null && !repPistaAuditExtModel.getNumOrden().trim().isEmpty()) {
            repPistaAuditExtModel.setCampoActivoRfcContribuyente(true);
            repPistaAuditExtModel.setCampoActivoRfcRepLegal(true);
            repPistaAuditExtModel.setCampoActivoRfcApodLegal(true);
            repPistaAuditExtModel.setCampoActivoRfcAgentAduanal(true);
            repPistaAuditExtModel.setCampoActivoRfcApodLegalRepLegal(true);
            repPistaAuditExtModel.setCampoActivoIdRegistro(true);
            repPistaAuditExtModel.setCampoActivoCanlendar(true);
            contador++;
            contadorNumOreden++;
            return true;
        } else {
            activaCampos(repPistaAuditExtModel);
            return false;
        }
    }

    public void activaCampos(RepPistaAuditExtModel repPistaAuditExtModel) {

        repPistaAuditExtModel.setCampoActivoRfcContribuyente(false);
        repPistaAuditExtModel.setCampoActivoRfcRepLegal(false);
        repPistaAuditExtModel.setCampoActivoRfcApodLegal(false);
        repPistaAuditExtModel.setCampoActivoRfcAgentAduanal(false);
        repPistaAuditExtModel.setCampoActivoRfcApodLegalRepLegal(false);
        repPistaAuditExtModel.setCampoActivoNumOreden(false);
        repPistaAuditExtModel.setCampoActivoIdRegistro(false);
        repPistaAuditExtModel.setCampoActivoCanlendar(false);
        contador = 0;
        contadorRfcContribuyente = 0;
        contadorRfcRepLegal = 0;
        contadorRfcApodLegal = 0;
        contadorRfcAgentAduanal = 0;
        contadorRfcApodLegalRepLegal = 0;
        contadorNumOreden = 0;
        contadorIdRegistro = 0;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public int getContador() {
        return contador;
    }

    public void setContadorRfcContribuyente(int contadorRfcContribuyente) {
        this.contadorRfcContribuyente = contadorRfcContribuyente;
    }

    public int getContadorRfcContribuyente() {
        return contadorRfcContribuyente;
    }

    public void setContadorRfcRepLegal(int contadorRfcRepLegal) {
        this.contadorRfcRepLegal = contadorRfcRepLegal;
    }

    public int getContadorRfcRepLegal() {
        return contadorRfcRepLegal;
    }

    public void setContadorRfcApodLegal(int contadorRfcApodLegal) {
        this.contadorRfcApodLegal = contadorRfcApodLegal;
    }

    public int getContadorRfcApodLegal() {
        return contadorRfcApodLegal;
    }

    public void setContadorRfcAgentAduanal(int contadorRfcAgentAduanal) {
        this.contadorRfcAgentAduanal = contadorRfcAgentAduanal;
    }

    public int getContadorRfcAgentAduanal() {
        return contadorRfcAgentAduanal;
    }

    public void setContadorRfcApodLegalRepLegal(int contadorRfcApodLegalRepLegal) {
        this.contadorRfcApodLegalRepLegal = contadorRfcApodLegalRepLegal;
    }

    public int getContadorRfcApodLegalRepLegal() {
        return contadorRfcApodLegalRepLegal;
    }

    public void setContadorNumOreden(int contadorNumOreden) {
        this.contadorNumOreden = contadorNumOreden;
    }

    public int getContadorNumOreden() {
        return contadorNumOreden;
    }

    public void setContadorIdRegistro(int contadorIdRegistro) {
        this.contadorIdRegistro = contadorIdRegistro;
    }

    public int getContadorIdRegistro() {
        return contadorIdRegistro;
    }
}
