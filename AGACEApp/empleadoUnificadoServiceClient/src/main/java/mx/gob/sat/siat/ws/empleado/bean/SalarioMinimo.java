/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.ws.empleado.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import mx.gob.sat.siat.ws.empleado.client.impl.EmpleadoClientHelper;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class SalarioMinimo implements Serializable {

    private static final long serialVersionUID = 6849204025685900036L;

    protected Date publicacion;
    protected BigDecimal valor;

    public Date getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Date publicacion) {
        this.publicacion = publicacion;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "SalarioMinimo{" + "publicacion=" + EmpleadoClientHelper.getFormattedDateDDMMYYYY(publicacion) + ", valor=" + valor + '}';
    }

}
