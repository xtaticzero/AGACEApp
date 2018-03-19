/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.common;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.AgaceIdc;
import mx.gob.sat.siat.feagace.modelo.dto.common.AgaceIdcPk;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAraceEnum;

public interface AgaceIdcDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return AgaceIdcPk
     */
    AgaceIdcPk insert(AgaceIdc dto);

    /**
     * Updates a single row in the AGACE_IDC table.
     */
    void update(AgaceIdcPk pk, AgaceIdc dto);

    /**
     * Returns all rows from the AGACE_IDC table that match the criteria 'ID_IDC
     * = :idIdc'.
     */
    AgaceIdc findByPrimaryKey(long idIdc);

    /**
     * Returns all rows from the AGACE_IDC table that match the criteria ''.
     */
    List<AgaceIdc> findAll();

    /**
     * Returns all rows from the AGACE_IDC table that match the criteria
     * 'ID_ORDEN = :idOrden'.
     */
    List<AgaceIdc> findByAgaceOrden(long idOrden);

    /**
     * Returns all rows from the AGACE_IDC table that match the criteria 'ID_IDC
     * = :idIdc'.
     */
    List<AgaceIdc> findWhereIdIdcEquals(long idIdc);

    /**
     * Returns all rows from the AGACE_IDC table that match the criteria
     * 'ID_ORDEN = :idOrden'.
     */
    List<AgaceIdc> findWhereIdOrdenEquals(long idOrden);

    /**
     * Returns all rows from the AGACE_IDC table that match the criteria
     * 'NOMBRE_AGENTE_ADUANAL = :nombreAgenteAduanal'.
     */
    List<AgaceIdc> findWhereNombreAgenteAduanalEquals(String nombreAgenteAduanal);

    /**
     * Returns all rows from the AGACE_IDC table that match the criteria
     * 'RFC_AGENTE_ADUANAL = :rfcAgenteAduanal'.
     */
    List<AgaceIdc> findWhereRfcAgenteAduanalEquals(String rfcAgenteAduanal);

    /**
     * Returns all rows from the AGACE_IDC table that match the criteria
     * 'EMAIL_AGENTE_ADUANAL = :emailAgenteAduanal'.
     */
    List<AgaceIdc> findWhereEmailAgenteAduanalEquals(String emailAgenteAduanal);

    /**
     * Returns all rows from the AGACE_IDC table that match the criteria
     * 'NOMBRE_CONTRIBUYENTE = :nombreContribuyente'.
     */
    List<AgaceIdc> findWhereNombreContribuyenteEquals(String nombreContribuyente);

    /**
     * Returns all rows from the AGACE_IDC table that match the criteria
     * 'RFC_CONTRIBUYENTE = :rfcContribuyente'.
     */
    List<AgaceIdc> findWhereRfcContribuyenteEquals(String rfcContribuyente);

    /**
     * Returns all rows from the AGACE_IDC table that match the criteria
     * 'EMAIL_CONTRIBUYENTE = :emailContribuyente'.
     */
    List<AgaceIdc> findWhereEmailContribuyenteEquals(String emailContribuyente);

    /**
     * Returns all rows from the AGACE_IDC table that match the criteria
     * 'NOMBRE_REPRESENTANTE_LEGAL = :nombreRepresentanteLegal'.
     */
    List<AgaceIdc> findWhereNombreRepresentanteLegalEquals(String nombreRepresentanteLegal);

    /**
     * Returns all rows from the AGACE_IDC table that match the criteria
     * 'RFC_REPRESENTANTE_LEGAL = :rfcRepresentanteLegal'.
     */
    List<AgaceIdc> findWhereRfcRepresentanteLegalEquals(String rfcRepresentanteLegal);

    /**
     * Returns all rows from the AGACE_IDC table that match the criteria
     * 'EMAIL_REPRESENTANTE_LEGAL = :emailRepresentanteLegal'.
     */
    List<AgaceIdc> findWhereEmailRepresentanteLegalEquals(String emailRepresentanteLegal);

    /**
     * Returns the rows from the AGACE_IDC table that matches the specified
     * primary-key value.
     */
    AgaceIdc findByPrimaryKey(AgaceIdcPk pk);

    TipoAraceEnum getIdAraceXNombre(String nobreArace);

}
