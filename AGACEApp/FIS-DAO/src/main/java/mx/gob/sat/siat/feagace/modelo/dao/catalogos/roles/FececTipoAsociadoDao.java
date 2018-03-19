/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.roles;

import java.math.BigDecimal;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececTipoAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececTipoAsociadoPk;
import java.util.List;

public interface FececTipoAsociadoDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececTipoAsociadoPk
     */
    FececTipoAsociadoPk insert(FececTipoAsociado dto);

    /**
     * Updates a single row in the FECEC_TIPO_ASOCIADO table.
     */
    void update(FececTipoAsociadoPk pk, FececTipoAsociado dto);

    /**
     * Deletes a single row in the FECEC_TIPO_ASOCIADO table.
     */
    void delete(FececTipoAsociadoPk pk);

    /**
     * Returns all rows from the FECEC_TIPO_ASOCIADO table that match the
     * criteria 'ID_TIPO_ASOCIADO = :idTipoAsociado'.
     */
    FececTipoAsociado findByPrimaryKey(BigDecimal idTipoAsociado);

    /**
     * Returns all rows from the FECEC_TIPO_ASOCIADO table that match the
     * criteria ''.
     */
    List<FececTipoAsociado> findAll();

    /**
     * Returns all rows from the FECEC_TIPO_ASOCIADO table that match the
     * criteria 'ID_TIPO_ASOCIADO = :idTipoAsociado'.
     */
    List<FececTipoAsociado> findWhereIdTipoAsociadoEquals(BigDecimal idTipoAsociado);

    /**
     * Returns all rows from the FECEC_TIPO_ASOCIADO table that match the
     * criteria 'DESCRIPCION = :descripcion'.
     */
    List<FececTipoAsociado> findWhereDescripcionEquals(String descripcion);

    /**
     * Returns the rows from the FECEC_TIPO_ASOCIADO table that matches the
     * specified primary-key value.
     */
    FececTipoAsociado findByPrimaryKey(FececTipoAsociadoPk pk);

    List<FececTipoAsociado> getTipoAsociadoByContribuyente(String rfc);

}
