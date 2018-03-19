/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.ws.empleado.bean;

import java.io.Serializable;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class UnidadAdministrativa implements Serializable {

    private static final long serialVersionUID = -7393649366372209087L;

    private Boolean central;
    private String clave;
    private Integer id;
    private String nombre;
    private String sede;

    public Boolean getCentral() {
        return central;
    }

    public void setCentral(Boolean central) {
        this.central = central;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    @Override
    public String toString() {
        return "UnidadAdministrativa{" + "central=" + central + ", clave=" + clave + ", id=" + id + ", nombre=" + nombre + ", sede=" + sede + '}';
    }

}
