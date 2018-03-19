package mx.gob.sat.siat.feagace.modelo.dao.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececConcepto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuestoDescripcion;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetImpuestoDescripcionMapper implements ParameterizedRowMapper<FecetImpuestoDescripcion> {

    public static final String FECET_IMPUESTO_MONTO = "MONTO";
    public static final String FECET_IMPUESTO_PERIODO_INICIAL = "PERIODO_INICIAL";
    public static final String FECET_IMPUESTO_PERIODO_FINAL = "PERIODO_FINAL";
    public static final String FECEC_TIPO_IMPUESTO_DESCRIPCION = "DESCRIPCION";
    public static final String FECEC_CONCEPTOS_DESCRIPCION = "DESCRIPCION_CONCEPTO";
    public static final String ID_PROPUESTA = "ID_PROPUESTA";

    @Override
    public FecetImpuestoDescripcion mapRow(ResultSet rs, int rowNum)
            throws SQLException {
        FecetImpuestoDescripcion impuestoDescripcion = new FecetImpuestoDescripcion();
        FecetImpuesto impuesto = new FecetImpuesto();
        FececTipoImpuesto tipoImpuesto = new FececTipoImpuesto();
        FececConcepto concepto = new FececConcepto();

        impuesto.setMonto(rs.getBigDecimal(FECET_IMPUESTO_MONTO));
        tipoImpuesto.setDescripcion(rs.getString(FECEC_TIPO_IMPUESTO_DESCRIPCION));
        concepto.setDescripcion(rs.getString(FECEC_CONCEPTOS_DESCRIPCION));

        impuesto.setFececConcepto(concepto);

        if (UtileriasMapperDao.existeColumna(rs, ID_PROPUESTA)) {
            impuesto.setIdPropuesta(rs.getBigDecimal(ID_PROPUESTA));
        }

        impuestoDescripcion.setFecetImpuesto(impuesto);
        impuestoDescripcion.setFececTipoImpuesto(tipoImpuesto);

        return impuestoDescripcion;
    }

}
