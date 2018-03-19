/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.common;

import java.io.Serializable;

import java.math.BigDecimal;


public class FecetContribuyenteVO implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final BigDecimal ENTERO_UNO = BigDecimal.ONE;
    
    public FecetContribuyente getContribuyenteVO(final String rfc) {
        FecetContribuyente contribuyente = new FecetContribuyente();

        contribuyente.setNombre("PRUEBA BLANCA MARTINEZ SOLANO");
        contribuyente.setIdContribuyente(ENTERO_UNO);
        contribuyente.setRfc("COZR860812PC4");
        contribuyente.setSituacion("SUSPENDIDO");
        contribuyente.setDomicilioFiscal("PASEO DEL BOSQUE # 110, BENITO JUAREZ, NEZAHUALCOYOTL, MEXICO, C.P. 57000");
        contribuyente.setSituacionDomicilio("SIN DETALLE - DOMICILIO SIN VERIFICAR");
        contribuyente.setTipo("FISICA");
        contribuyente.setActividadPreponderante("Sin actividades preponderantes");
        contribuyente.setEntidad("MEXICO");
        contribuyente.setRegimen("Sin regimen");



        return contribuyente;
    }
}
