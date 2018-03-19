/**
 * Todos los Derechos Reservados 2013 SAT. Servicio de Administracion Tributaria
 * (SAT). Este software contiene informacion propiedad exclusiva del SAT
 * considerada Confidencial. Queda totalmente prohibido su uso o divulgacion en
 * forma parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.insumos;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroalimentacion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroalimentacionPk;
import mx.gob.sat.siat.feagace.modelo.enums.EstadoBooleanodeRegistroEnum;

public interface FecetRetroalimentacionDao {

    /**
     * Method 'insertarRetroalimentacion'
     *
     * @param dto
     * @return FecetRetroalimentacionPk
     */
    FecetRetroalimentacion insertarRetroalimentacion(FecetRetroalimentacion dto);

    /**
     * Method 'insertarRetroalimentacion'
     *
     * @param dto
     * @return FecetRetroalimentacionPk
     */
    FecetRetroalimentacion insertarRetroalimentacionHistorial(FecetRetroalimentacion dto);

    /**
     * Method 'insertarRetroalimentacion'
     *
     * @param dto
     * @return FecetRetroalimentacionPk
     */
    FecetRetroalimentacionPk insertarAsociaRetro(FecetRetroalimentacion dto);

    /**
     * Metodo getIdFecetRetroalimentacion
     *
     * @return BigDecimal
     * @
     */
    BigDecimal getIdFecetRetroalimentacion();

    /**
     * Returns all rows from the FECET_RETROALIMENTACION table that match the
     * criteria 'ID_RETROALIMENTACION = :idRetroalimentacion'.
     */
    FecetRetroalimentacion findByPrimaryKey(BigDecimal idRetroalimentacion);

    /**
     * Method 'insertarDocRetroalimentacion'
     *
     * @param dto
     * @return FecetRetroalimentacionPk
     */
    FecetRetroalimentacionPk insertarDocRetroalimentacion(FecetRetroalimentacion dto);

    /**
     * Metodo getDocRetroalimetarByIdPropuesta
     *
     * Returns all rows from the FECET_DOC_RETRO_PROPUESTA table that match the
     * criteria 'ID_PROPUESTA = :idPropuesta'.
     */
    List<FecetRetroalimentacion> getDocRetroalimetarByIdPropuesta(final BigDecimal idPropuesta);

    /**
     * Metodo getDocRetroalimetarByIdRetroaliemntacion
     *
     * Returns all rows from the FECET_DOC_RETRO_PROPUESTA table that match the
     * criteria 'ID_RETROALIMENTACION = :idRetroalimentacion'.
     */
    List<FecetRetroalimentacion> getDocRetroalimetarByIdRetroaliemntacion(final BigDecimal idRetroalimentacion);

    /**
     * Metodo getConsecutivoDocRetroalimentar
     *
     * Returns the next number of the sequence
     */
    BigDecimal getConsecutivoDocRetroalimentar();

    /**
     * Metodo updateEstatusRetroalimentacion
     *
     * @param idPropuesta
     */
    void updateEstatusRetroalimentacion(final BigDecimal idPropuesta);

    BigDecimal getIdRetroalimentacionXEstadoIdPropuesta(final BigDecimal idPropuesta, final EstadoBooleanodeRegistroEnum estado);

    int updateEstadoRetroalimentacion(BigDecimal idRetroalimentacion, EstadoBooleanodeRegistroEnum estado);

    /**
     * Metodo updateEstatusDocRetroalimentacion
     *
     * @param idPropuesta
     */
    void updateEstatusDocRetroalimentacion(final BigDecimal idPropuesta);

    List<FecetRetroalimentacion> getDocRetroalimetarHistorialByIdPropuesta(final BigDecimal idPropuesta);

    BigDecimal getConsecutivoRetroalimentarPro();

    List<FecetRetroalimentacion> getDocRetroalimetarByOrigen(BigDecimal idRetroOrigen);

    void retroAtendida(BigDecimal idRetroalimentacion);
}
