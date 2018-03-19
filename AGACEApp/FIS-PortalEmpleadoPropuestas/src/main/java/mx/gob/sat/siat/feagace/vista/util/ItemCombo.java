/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.util;

import java.io.Serializable;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class ItemCombo implements Serializable {

    private static final int HASH_CONSTANT = 67;
    private static final int HASH = 5;
    private static final long serialVersionUID = -7693942397193370570L;
    private String descripcion;
    private int valor;

    public ItemCombo(String descripcion, int valor) {
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public ItemCombo() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = HASH;
        hash = HASH_CONSTANT * hash + (this.descripcion != null ? this.descripcion.hashCode() : 0);
        hash = HASH_CONSTANT * hash + this.valor;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemCombo other = (ItemCombo) obj;
        if ((this.descripcion == null) ? (other.descripcion != null) : !this.descripcion.equals(other.descripcion)) {
            return false;
        }
        if (this.valor != other.valor) {
            return false;
        }
        return true;
    }

    public int compareTo(String string) {
        return descripcion.compareTo(string);
    }

}
