/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.common;

import java.math.BigDecimal;

public interface UtilidadesDao {

    /**
     * @return consecutivo de carga masiva de documentos
     */
    BigDecimal getConsecutivoCargaMasiva();

    /**
     * @return consecutivo de documentacion carga masiva de documentos insumos
     */
    BigDecimal getConsecutivoDoctoCargaMasivaInsumos();

    /**
     * @return consecutivo de carga masiva de documentos insumos
     */
    BigDecimal getConsecutivoCargaMasivaInsumos();
}
