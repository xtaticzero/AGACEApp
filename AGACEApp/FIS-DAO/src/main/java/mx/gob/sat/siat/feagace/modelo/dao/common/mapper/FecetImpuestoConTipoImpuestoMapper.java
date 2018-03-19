package mx.gob.sat.siat.feagace.modelo.dao.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececTipoImpuestoMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececConcepto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;

public class FecetImpuestoConTipoImpuestoMapper extends FecetImpuestoMapper {

    /**
     * Metodo mapRow Hace un mapeo de los datos en el contador
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    @Override
    public FecetImpuesto mapRow(ResultSet rs, int rowNum) throws SQLException {

        FecetImpuesto impuesto;
        impuesto = super.mapRow(rs, rowNum);
        FececConcepto concepto = new FececConcepto();
        concepto.setIdConcepto(rs.getInt("ID_CONCEPTO"));
        concepto.setDescripcion(rs.getString("CONCEPTO"));
        impuesto.setFececConcepto(concepto);
        if (impuesto.getIdTipoImpuesto() != null) {
            try {
                FececTipoImpuesto fececTipoImpuesto = new FececTipoImpuestoMapper().mapRow(rs, rowNum);
                fececTipoImpuesto.setDescripcion(rs.getString("DESCRIPCION"));
                impuesto.setFececTipoImpuesto(fececTipoImpuesto);
            } catch (Exception e) {
                impuesto.setFececTipoImpuesto(null);
            }
        }

        return impuesto;
    }
}
