package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.common.mapper.FecetAsociadoMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionAgenteAduanal;

public class FecetPromocionAgenteAduanalContadorAlegatosMapper extends FecetPromocionAgenteAduanalMapper {

    private static final String COLUMN_TOTAL_PRUEBAS_ALEGATOS = "TOTAL_PRUEBAS_ALEGATOS";

    /**
     * Metodo mapRow Hace un mapeo de los datos en el contador
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    @Override
    public FecetPromocionAgenteAduanal mapRow(ResultSet rs, int rowNum) throws SQLException {

        FecetPromocionAgenteAduanal promocionAgenteAduanal = super.mapRow(rs, rowNum);

        if (promocionAgenteAduanal.getIdAsociadoCarga() != null) {
            FecetAsociado asociado = new FecetAsociadoMapper().mapRow(rs, rowNum);
            promocionAgenteAduanal.setAsociado(asociado);
        } else {
            promocionAgenteAduanal.setAsociado(null);
        }
        promocionAgenteAduanal.setContadorPruebasAlegatos(rs.getInt(COLUMN_TOTAL_PRUEBAS_ALEGATOS));
        return promocionAgenteAduanal;
    }
}
