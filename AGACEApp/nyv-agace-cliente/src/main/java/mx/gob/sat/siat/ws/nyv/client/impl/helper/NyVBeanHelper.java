/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.ws.nyv.client.impl.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import mx.gob.sat.siat.ws.nyv.client.dto.ActoAdministrativoVO;
import mx.gob.sat.siat.ws.nyv.client.dto.ResponseConsultaVO;
import mx.gob.sat.siat.ws.nyv.client.dto.ResponseRegistroVO;
import mx.gob.sat.siat.ws.nyv.client.dto.TipoProcesoVO;
import mx.gob.sat.siat.ws.nyv.v1.bean.ActoAdministrativoV1VOXml;
import mx.gob.sat.siat.ws.nyv.v1.bean.ResponseRegistro;
import mx.gob.sat.siat.ws.nyv.v2.bean.ActoAdministrativoV2VOXml;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class NyVBeanHelper implements Serializable {

    private static final long serialVersionUID = 3617602354215862751L;

    public static List<ActoAdministrativoVO> convert(mx.gob.sat.siat.ws.nyv.v1.bean.ResponseConsultaActosAdmin responsV1) {
        List<ActoAdministrativoVO> lstActoAdministrativoVO = new ArrayList<ActoAdministrativoVO>();
        if (responsV1 != null) {
            for (ActoAdministrativoV1VOXml lstActoAdminVO1Xml : responsV1.getActoAdmin()) {
                lstActoAdministrativoVO.add(new ActoAdministrativoVO(lstActoAdminVO1Xml));
            }
            return lstActoAdministrativoVO;
        }
        return lstActoAdministrativoVO;
    }

    public static List<ActoAdministrativoVO> convert(mx.gob.sat.siat.ws.nyv.v2.bean.ResponseConsultaActosAdmin responsV1) {
        List<ActoAdministrativoVO> lstActoAdministrativoVO = new ArrayList<ActoAdministrativoVO>();
        if (responsV1 != null) {
            for (ActoAdministrativoV2VOXml lstActoAdminVO2Xml : responsV1.getActoAdmin()) {
                lstActoAdministrativoVO.add(new ActoAdministrativoVO(lstActoAdminVO2Xml));
            }
            return lstActoAdministrativoVO;
        }
        return lstActoAdministrativoVO;
    }

    public static List<TipoProcesoVO> convertTipo1(List<mx.gob.sat.siat.ws.nyv.v1.bean.TipoProcesoVOXml> lstTipoProceso) {
        List<TipoProcesoVO> lstTipo = new ArrayList<TipoProcesoVO>();
        if (lstTipoProceso != null && !lstTipoProceso.isEmpty()) {
            for (mx.gob.sat.siat.ws.nyv.v1.bean.TipoProcesoVOXml tipo : lstTipoProceso) {
                lstTipo.add(new TipoProcesoVO(tipo));
            }
        }
        return lstTipo;
    }

    public static List<TipoProcesoVO> convertTipo2(List<mx.gob.sat.siat.ws.nyv.v2.bean.TipoProcesoVOXml> lstTipoProceso) {
        List<TipoProcesoVO> lstTipo = new ArrayList<TipoProcesoVO>();
        if (lstTipoProceso != null && !lstTipoProceso.isEmpty()) {
            for (mx.gob.sat.siat.ws.nyv.v2.bean.TipoProcesoVOXml tipo : lstTipoProceso) {
                lstTipo.add(new TipoProcesoVO(tipo));
            }
        }
        return lstTipo;
    }

    public static ResponseConsultaVO convert(mx.gob.sat.siat.ws.nyv.v1.bean.ResponseConsulta reponse) {
        if (reponse != null) {
            return new ResponseConsultaVO(reponse);
        }
        return null;
    }

    public static ResponseConsultaVO convert(mx.gob.sat.siat.ws.nyv.v2.bean.ResponseConsulta reponse) {
        if (reponse != null) {
            return new ResponseConsultaVO(reponse);
        }
        return null;
    }

    public static XMLGregorianCalendar getXMLGregorianCalendarFromDate(Date dateToChange) throws DatatypeConfigurationException {
        if (dateToChange != null) {
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(dateToChange);
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        }
        return null;
    }

    public static ResponseRegistroVO convert(mx.gob.sat.siat.ws.nyv.v1.bean.ResponseRegistro response) {
        if (response != null) {
            ResponseRegistroVO responseRegistroVO = new ResponseRegistroVO();

            responseRegistroVO.setCodigoRespuesta(response.getCodigoRespuesta());
            responseRegistroVO.setFolio(response.getFolio());
            responseRegistroVO.setMensajeRespuesta(response.getMensajeRespuesta());

            return responseRegistroVO;
        }
        return null;
    }

    public static ResponseRegistroVO convert(mx.gob.sat.siat.ws.nyv.v2.bean.ResponseRegistro response) {
        if (response != null) {
            ResponseRegistroVO responseRegistroVO = new ResponseRegistroVO();

            responseRegistroVO.setCodigoRespuesta(response.getCodigoRespuesta());
            responseRegistroVO.setFolio(response.getFolio());
            responseRegistroVO.setMensajeRespuesta(response.getMensajeRespuesta());

            return responseRegistroVO;
        }
        return null;
    }
}
