/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.common;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;

public interface FeceaMetodoDao {

    /**
     * Metodo findAll()
     *
     * @return List
     * @
     */
    List<FececMetodo> findAll();

    /**
     * Metodo getMetodosCambioMetodo() Obtiene todos los datos de la tabla
     * FECET_ORDEN sin UCA la cual cambia de metodo a EHO, ORG o REE
     *
     * @return List
     * @
     */
    List<FececMetodo> getOpcionesCambioMetodo();

    /**
     * Metodo getAbreviaturaMetodo() Obtiene la abreviatura del metodo a EHO,
     * ORG, REE, UCA
     *
     * @return String
     * @
     */
    String getAbreviaturaMetodo(final BigDecimal idMetodo);

    /**
     * Metodo findWhereIdMetodo() Obtiene los datos del regristro que
     * corresponden al idMetodo que se le manda
     *
     * @return String
     * @
     */
    List<FececMetodo> findWhereIdMetodo(final BigDecimal idMetodo);

    /**
     * Metodo findWhereAbreviaturaEquals() Obtiene los registros de la tabla
     * FeceaMetodo que tengan una abreviacion especifica.
     *
     * @return List<FeceaMetodo>
     * @
     */
    List<FececMetodo> findWhereAbreviaturaEquals(final String abreviatura);

    /**
     * Metodo findPropuestas() Obtiene los registros de la tabla FeceaMetodo
     * excepto MCA.
     *
     * @return List<FeceaMetodo>
     * @
     */
    List<FececMetodo> findPropuestas();
}
