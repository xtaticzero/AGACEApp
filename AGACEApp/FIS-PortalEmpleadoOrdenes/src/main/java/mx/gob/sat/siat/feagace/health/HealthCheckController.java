package mx.gob.sat.siat.feagace.health;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.feagace.health.dto.HealthDTO;
import mx.gob.sat.siat.feagace.health.service.HealthCheckService;

@ViewScoped
@ManagedBean(name = "healthCheckController")
public class HealthCheckController extends BaseManagedBean {

    private static final long serialVersionUID = -3790405310555686166L;

    @ManagedProperty(value = "#{healthCheckService}")
    private transient HealthCheckService healthCheckService;

    private HealthDTO healthDTO = new HealthDTO();

    private String correo;

    private String rfc;

    private String rfcSise;

    private String cadenaOriginal;

    private String rfcMedios;

    private String folioActoAdministrativo;
    
    private String tipo;
    
    private String central;
    
    private String arace;

    @PostConstruct
    public void init() {

    }

    public void validarMail() {
        healthDTO = healthCheckService.validarCorreo(correo);
    }

    public void validarIDC() {
        healthDTO = healthCheckService.validarIDC(rfc);
    }

    public void validarSISE() {
        healthDTO = healthCheckService.validarSISE(rfcSise);
    }

    public void validarBuzonSelladora() {
        healthDTO = healthCheckService.validarBuzonSelladora(cadenaOriginal);
    }

    public void validarEmpleadoPorTipoCentralArace() {
        healthDTO = healthCheckService.validarEmpleadoPorTipoCentralArace(tipo, central,arace);
    }

    public void validarMediosComunicacion() {
        healthDTO = healthCheckService.validarMediosComunicacion(rfcMedios);
    }

    public void validarNyVConsultarActoAdministrativo() {
        healthDTO = healthCheckService
                .validarNyVConsultarActoAdministrativo(folioActoAdministrativo);
    }

    public HealthCheckService getHealthCheckService() {
        return healthCheckService;
    }

    public void setHealthCheckService(HealthCheckService healthCheckService) {
        this.healthCheckService = healthCheckService;
    }

    public HealthDTO getHealthDTO() {
        return healthDTO;
    }

    public void setHealthDTO(HealthDTO healthDTO) {
        this.healthDTO = healthDTO;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getRfcSise() {
        return rfcSise;
    }

    public String getRfcMedios() {
        return rfcMedios;
    }

    public void setRfcMedios(String rfcMedios) {
        this.rfcMedios = rfcMedios;
    }

    public void setRfcSise(String rfcSise) {
        this.rfcSise = rfcSise;
    }

    public String getCadenaOriginal() {
        return cadenaOriginal;
    }

    public void setCadenaOriginal(String cadenaOriginal) {
        this.cadenaOriginal = cadenaOriginal;
    }

    public String getFolioActoAdministrativo() {
        return folioActoAdministrativo;
    }

    public void setFolioActoAdministrativo(String folioActoAdministrativo) {
        this.folioActoAdministrativo = folioActoAdministrativo;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getCentral() {
        return central;
    }

    public void setCentral(String central) {
        this.central = central;
    }
    
    public String getArace() {
        return arace;
    }

    public void setArace(String arace) {
        this.arace = arace;
    }

}
