/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import mx.gob.sat.siat.feagace.modelo.dao.common.mapper.FecetAsociadoMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficio;

public class FecetPromocionOficioContadorAlegatosMapper extends FecetPromocionOficioMapper {

    /**
     * Este atributo corresponde al nombre de la columna TOTAL_PRUEBAS_ALEGATOS
     * del contador de pruebas y alegatos
     */
    private static final String COLUMN_TOTAL_PRUEBAS_ALEGATOS = "TOTAL_PRUEBAS_ALEGATOS";

    /**
     * Metodo mapRow Hace un mapeo de los datos en el contador
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FecetPromocionOficio mapRow(ResultSet rs, int rowNum) throws SQLException {

        FecetPromocionOficio promocion = super.mapRow(rs, rowNum);
        if (promocion.getIdAsociadoCarga() != null) {
            FecetAsociado asociado = new FecetAsociadoMapper().mapRow(rs, rowNum);
            promocion.setAsociado(asociado);
        } else {
            promocion.setAsociado(null);
        }

        promocion.setContadorPruebasAlegatos(rs.getLong(COLUMN_TOTAL_PRUEBAS_ALEGATOS));

        return promocion;
    }
}
