/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.ws.nyv.test.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import mx.gob.sat.siat.ws.nyv.client.NyVServiceClient;
import mx.gob.sat.siat.ws.nyv.client.dto.ActoAdministrativoVO;
import mx.gob.sat.siat.ws.nyv.client.dto.ConsultaNumeroReferenciaVO;
import mx.gob.sat.siat.ws.nyv.client.dto.DocumentoGeneradoVO;
import mx.gob.sat.siat.ws.nyv.client.dto.RegistroActoAdministrativo;
import mx.gob.sat.siat.ws.nyv.client.dto.ResponseConsultaVO;
import mx.gob.sat.siat.ws.nyv.client.dto.ResponseRegistroVO;
import mx.gob.sat.siat.ws.nyv.test.base.BaseTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class NyVServiceClientTest extends BaseTest {

    @Autowired
    private NyVServiceClient nyvServiceClient;

    private String unidadAdmin;
    private String folioActoAdministrativo;
    private int clvActoAdmin;

    @Before
    public void init() {
        //Parametros
        unidadAdmin = "111";
        folioActoAdministrativo = "318103AR0000741";
        clvActoAdmin = 6378;
        //clvActoAdmin = 6378;
        //Servicio
        assert nyvServiceClient != null;
    }

    @Test
    //@Ignore
    public void fueraHorarioLaboralTest() {
        try {
            logger.info("==============fueraHorarioLaboralTest===============");
            logger.info(nyvServiceClient.getUrlWsdlNyv());
            logger.info(nyvServiceClient.getVrsionNyV());
            logger.info(nyvServiceClient.fueraHorarioLaboral());
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

    }

    @Test
    //@Ignore
    public void consultarListaActosAdminTest() {
        try {
            List<ActoAdministrativoVO> lstActos = nyvServiceClient.consultarListaActosAdmin(unidadAdmin);
            logger.info("==============consultarListaActosAdminTest===============");
            for (ActoAdministrativoVO acto : lstActos) {
                logger.info(acto);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

    }

    @Test
    //@Ignore
    public void consultarActoAdministrativoTest() {
        try {
            ResponseConsultaVO acto = nyvServiceClient.consultarActoAdministrativo("318103AR");
            logger.info("==============consultarActoAdministrativoTest===============");
            logger.info(acto);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

    }
    
    @Test
    //@Ignore
    public void registrarActoAdministrativoTest() {
        try {
            RegistroActoAdministrativo raa = new RegistroActoAdministrativo();
            
            raa.setClaveActoAdmin(clvActoAdmin);
            raa.setClaveUnidadAdmin("111");
            //raa.setFechaLimiteDiligencia(new Date());
            raa.setNumeroReferencia("111-E1701131824049");
            raa.setProcesoOrigen("ACTO DE FISCALIZACIÓN");
            raa.setNumeroReferenciaOrigen("E-110-00-00-00-00-2017-132-AFC000");
            raa.setRfcDestinatario("AFC000526BJ2");
            raa.setNumeroEmpleado("00000065423");
                        
            raa.setDocumentos(new ArrayList<DocumentoGeneradoVO>());
            
            DocumentoGeneradoVO docto = new DocumentoGeneradoVO();
            docto.setEstatusDocumento("GENERADO CON FIRMA");
            docto.setUrlDocumento("/siat/anexos/51207.pdf");
            docto.setCadenaOriginal("U7hAh80VFDyhZbmjATUYAEa2EijZPuZy5/r1iy+k81eEqMjw6kLAvZLNj1BxvvxH8o2FIdK9gaM9gb0hDYo0XCD0dGvZhK0rMAPAvxjpqyXKCuq3qyaBJjGd00vccNIau4IJC5xrdOPf11av9o7holmjBGIE4qOFwGH1P4Z7JsA57/OgNSx0jaCmvmvpkNGUcOTwPzlHtkon7SGX7Wv8H9iB6xW3iMtIMpcAXQJFzB/UfXu/tQYH8i");
            docto.setFechaDocumento(new Date());
            docto.setCadenaOriginal("EREE4100127-17|132|Oficio Aviso Circunstancial|13/01/2017|18:23|AFC000526BJ2|41|REHS7304178L2");
            docto.setTipoDocumento("CARTA DE DERECHOS DEL CONTRIBUYENTE");
            docto.setFolioSifen(0);
            docto.setRfcFirmante("REHS7304178L2");
            docto.setNombreFirmante("Sergio Roberto Reyes Henderson");
            docto.setPuestoFirmante("Firmante");
            docto.setFechaVigenciaFiel(new Date());
            raa.getDocumentos().add(docto);
            docto = new DocumentoGeneradoVO();
            docto.setEstatusDocumento("GENERADO CON FIRMA");
            docto.setUrlDocumento("/siat/anexos/51213.pdf");
            docto.setCadenaOriginal("U7hAh80VFDyhZbmjATUYAEa2EijZPuZy5/r1iy+k81eEqMjw6kLAvZLNj1BxvvxH8o2FIdK9gaM9gb0hDYo0XCD0dGvZhK0rMAPAvxjpqyXKCuq3qyaBJjGd00vccNIau4IJC5xrdOPf11av9o7holmjBGIE4qOFwGH1P4Z7JsA57/OgNSx0jaCmvmvpkNGUcOTwPzlHtkon7SGX7Wv8H9iB6xW3iMtIMpcAXQJFzB/UfXu/tQYH8i");
            docto.setFechaDocumento(new Date());
            docto.setCadenaOriginal("EREE4100127-17|132|Oficio Aviso Circunstancial|13/01/2017|18:23|AFC000526BJ2|41|REHS7304178L2");
            docto.setTipoDocumento("FOLLETO ANTICORRUPCION");
            docto.setFolioSifen(0);
            docto.setRfcFirmante("REHS7304178L2");
            docto.setNombreFirmante("Sergio Roberto Reyes Henderson");
            docto.setPuestoFirmante("Firmante");
            docto.setFechaVigenciaFiel(new Date());
            raa.getDocumentos().add(docto);
            
            ResponseRegistroVO response = nyvServiceClient.registrarActoAdministrativo(raa);
            
            logger.info("==============registrarActoAdministrativoTest===============");
            logger.info(response);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

    }
    
    @Test
    //@Ignore
    public void consultarNumeroRefTest() {
        try {
            ConsultaNumeroReferenciaVO acto = nyvServiceClient.consultarNumeroReferencia("318103AR");
            logger.info("==============consultarNumeroRefTest===============");
            logger.info(acto);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

    }

}
