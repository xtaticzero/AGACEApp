/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.ws.empleado.client.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import mx.gob.sat.siat.ws.empleado.bean.DetalleEmpleado;
import mx.gob.sat.siat.ws.empleado.bean.DetalleEmpleadoXml;
import mx.gob.sat.siat.ws.empleado.bean.Empleado;
import mx.gob.sat.siat.ws.empleado.bean.EmpleadoXml;
import mx.gob.sat.siat.ws.empleado.serialization.arrays.ArrayOfIntParameter;
import mx.gob.sat.siat.ws.empleado.serialization.arrays.ArrayOfint;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public final class EmpleadoClientHelper {

    private EmpleadoClientHelper() {
    }

    public static List<Empleado> getLstEmpleadoFromLstEmpleadoXml(List<EmpleadoXml> lstEmpleadoXml) {
        if (lstEmpleadoXml != null) {
            List<Empleado> lstEmpleados = new ArrayList<Empleado>();
            for (EmpleadoXml empleadoXml : lstEmpleadoXml) {
                lstEmpleados.add(getEmpleadoFromEmpleadoXml(empleadoXml));
            }
            return lstEmpleados;
        }
        return null;
    }

    public static Empleado getEmpleadoFromEmpleadoXml(EmpleadoXml empleadoXml) {
        if (empleadoXml != null) {
            Empleado empleado = new Empleado();

            empleado.setRfc(empleadoXml.getRfc().getValue());
            empleado.setNombre(empleadoXml.getNombre().getValue());
            empleado.setApellidoPaterno(empleadoXml.getApellidoPaterno().getValue());
            empleado.setApellidoMaterno(empleadoXml.getApellidoMaterno().getValue());
            empleado.setCorreo(empleadoXml.getCorreo().getValue());
            empleado.setDescripcionPerfil(empleadoXml.getDescripcionPerfil().getValue());
            empleado.setEstatusEmpleado(empleadoXml.getEstatusEmpleado().getValue());
            empleado.setFechaBaja(EmpleadoClientHelper.getDateFromXMLGregorianCalendar(empleadoXml.getFechaBaja()));
            empleado.setFechaCreacion(EmpleadoClientHelper.getDateFromXMLGregorianCalendar(empleadoXml.getFechaCreacion()));
            empleado.setIdSuplencia(empleadoXml.getIdSuplencia());
            empleado.setNumeroEmpleado(empleadoXml.getNumeroEmpleado());
            empleado.setNumeroJefeInmediato(empleadoXml.getNumeroJefeInmediato());
            empleado.setIdAdmGral(empleadoXml.getIdAdmGral());
            empleado.setAdmGral(empleadoXml.getAdmGral().getValue());
            try {
                empleado.setIdAdmCentral(empleadoXml.getIdAdmCentral() != null ? Integer.parseInt(empleadoXml.getIdAdmCentral().getValue()) : null);
            } catch (Exception e) {
                empleado.setIdAdmCentral(null);
            }
            empleado.setIdArea(empleadoXml.getIdArea());
            empleado.setArea(empleadoXml.getArea().getValue());

            return empleado;
        }
        return null;
    }

    public static List<DetalleEmpleado> getLstDetalleEmpleadoFromLstDetalleXml(List<DetalleEmpleadoXml> lstDetalleEmpleadoXml) {
        if (lstDetalleEmpleadoXml != null) {
            List<DetalleEmpleado> lstDetalleEmpleado = new ArrayList<DetalleEmpleado>();

            for (DetalleEmpleadoXml detalleXml : lstDetalleEmpleadoXml) {
                DetalleEmpleado detalleEmpleado = new DetalleEmpleado();
                detalleEmpleado.setIdCentral(detalleXml.getIdCentral());
                detalleEmpleado.setIdPerfil(detalleXml.getIdPerfil());
                detalleEmpleado.setIdPuesto(detalleXml.getIdPuesto());
                detalleEmpleado.setIdRol(detalleXml.getIdRol());
                detalleEmpleado.setIdUnidadAdmin(detalleXml.getIdUnidadAdmin());

                lstDetalleEmpleado.add(detalleEmpleado);
            }
            return lstDetalleEmpleado;
        }
        return null;
    }

    public static ArrayOfint getArrayOfintFromArrayInt(Integer[] numerosEmpleadosArray) {

        if (numerosEmpleadosArray != null) {
            ArrayOfIntParameter arrayOfint = new ArrayOfIntParameter(numerosEmpleadosArray);
            return arrayOfint;
        }

        return null;
    }

    public static Date getDateFromXMLGregorianCalendar(XMLGregorianCalendar dateToChange) {
        if (dateToChange != null) {
            return dateToChange.toGregorianCalendar().getTime();
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

    public static String getFormattedDateDDMMYYYY(Date unformattedDate) {
        if (unformattedDate != null) {
            DateFormat dateFormat;
            dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.format(unformattedDate);
        } else {
            return "";
        }
    }

    public static Date getDateFromDDMMYYYY(String stringDate) {
        if (stringDate != null && !stringDate.isEmpty()) {
            try {
                DateFormat dateFormat;
                dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                return dateFormat.parse(stringDate);
            } catch (ParseException ex) {
                return null;
            }
        } else {
            return null;
        }
    }
}
