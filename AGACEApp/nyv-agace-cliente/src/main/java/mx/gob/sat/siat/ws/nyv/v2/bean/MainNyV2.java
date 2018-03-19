/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.ws.nyv.v2.bean;

import mx.gob.sat.siat.ws.nyv.client.impl.WSNyVV2Sistema;
import mx.gob.sat.siat.ws.nyv.client.repository.WSNyVV2Repository;
import mx.gob.sat.siat.ws.nyv.client.impl.DisableSSLCertificateCheckUtil;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class MainNyV2 {

    public static void main(String[] args) {
        try {

            try {
                System.out.println("Adding trust to certificates");
                System.setProperty("javax.net.ssl.trustStore ", "com.pmarlen.digifactws20160707.NaiveTrustManager");
                System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true");
                System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
                System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dump", "true");
                System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");
                DisableSSLCertificateCheckUtil.disableChecks();
                System.out.println("Setting javax.xml.bind.JAXBContext <- com.sun.xml.internal.bind.v2.ContextFactory");
                System.setProperty("javax.xml.bind.JAXBContext", "com.sun.xml.internal.bind.v2.ContextFactory");

                WSNyVV2Sistema wsNyVSistema = new WSNyVV2Sistema();
                WSNyVV2Repository wsNyVInterfa = wsNyVSistema.getWSNyVSistemaPort();
                ResponseConsultaActosAdmin lstActo = wsNyVInterfa.consultarListaActosAdmin("111");

                if (lstActo != null) {
                    for (ActoAdministrativoV2VOXml acto : lstActo.getActoAdmin()) {
                        System.out.println(acto.getDescripcion() + " ID " + acto.getId());
                    }
                }
            } catch (Exception e) {
                System.err.println("Exception :" + e);
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.err.println(e);
        }

    }
}
