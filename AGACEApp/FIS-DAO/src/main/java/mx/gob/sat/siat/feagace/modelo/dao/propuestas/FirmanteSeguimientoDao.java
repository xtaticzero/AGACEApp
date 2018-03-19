/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.propuestas;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;

public interface FirmanteSeguimientoDao {

    /**
     * Metodo getFirmarOrdenesSeguimiento
     *
     * @param idEmpleado
     * @return List AgaceOrden
     *
     */
    List<AgaceOrden> getFirmarOrdenesSeguimiento(final BigDecimal idEmpleado);

}
