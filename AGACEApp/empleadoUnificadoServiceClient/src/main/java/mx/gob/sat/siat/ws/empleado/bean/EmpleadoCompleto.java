/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.ws.empleado.bean;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class EmpleadoCompleto implements Serializable {

    private static final long serialVersionUID = 7724793608334986337L;

    private Empleado empleado;
    private List<Empleado> listaJefesSuperiores;
    private List<Empleado> listaSubordinados;
    private List<DetalleEmpleado> listaDetalleEmpleado;

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<Empleado> getListaJefesSuperiores() {
        return listaJefesSuperiores;
    }

    public void setListaJefesSuperiores(List<Empleado> listaJefesSuperiores) {
        this.listaJefesSuperiores = listaJefesSuperiores;
    }

    public List<Empleado> getListaSubordinados() {
        return listaSubordinados;
    }

    public void setListaSubordinados(List<Empleado> listaSubordinados) {
        this.listaSubordinados = listaSubordinados;
    }

    public List<DetalleEmpleado> getListaDetalleEmpleado() {
        return listaDetalleEmpleado;
    }

    public void setListaDetalleEmpleado(List<DetalleEmpleado> listaDetalleEmpleado) {
        this.listaDetalleEmpleado = listaDetalleEmpleado;
    }

    @Override
    public String toString() {
        return "EmpleadoCompleto{" + "empleado=" + empleado + ", listaJefesSuperiores=" + listaJefesSuperiores + ", listaSubordinados=" + listaSubordinados + ", listaDetalleEmpleado=" + listaDetalleEmpleado + '}';
    }

}
