package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececConcepto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FeceaCifraImpuestoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FeceaCifraTipoCifraDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FeceaImpuestoConceptoDTO;

public class FecetCifraImpuestoConceptoMapper implements ParameterizedRowMapper<FeceaCifraImpuestoDTO> {

    public static final String COLUMN_ID_DETALLE_CIFRA = "id_detalle_cifra";

    public static final String COLUMN_ID_ORDEN = "id_orden";

    public static final String COLUMN_FECHA_INICIO = "FECHA_INICIO";

    public static final String COLUMN_FECHA_FIN = "FECHA_FIN";

    public static final String COLUMN_ID_CIFRA_IMPUESTO = "ID_CIFRA_IMPUESTO";

    public static final String COLUMN_ID_TIPO_IMPUESTO = "ID_TIPO_IMPUESTO";

    public static final String COLUMN_ID_IMPUESTO_CONCEPTO = "ID_CONCEPTO";

    public static final String COLUMN_ID_CIFRA_TIPO_CIFRA = "ID_CIFRA_TIPO_CIFRA";

    public static final String COLUMN_FECHA_PAGO = "FECHA_PAGO";

    public static final String COLUMN_IMPORTE = "IMPORTE";

    public static final String COLUMN_ACTUALIZACIONES = "ACTUALIZACIONES";

    public static final String COLUMN_MULTAS = "MULTAS";

    public static final String COLUMN_RECARGOS = "RECARGOS";

    public static final String COLUMN_OBSERVACIONES = "OBSERVACIONES";

    public static final String COLUMN_FECHA_INICIO_FCI = "FECHA_INICIO";

    public static final String COLUMN_FECHA_FIN_FCI = "FECHA_FIN";

    public static final String COLUMN_TOTAL = "TOTAL";

    public static final String COLUMN_DESCR = "DESCR";

    public static final String COLUMN_DESCR_IMPUESTO = "DESCR_IMPUESTO";

    public static final String COLUMN_DESCR_CONCEPTO = "DESCR_CONCEPTO";

    public static final String COLUMN_ANTECEDENTE = "ANTECEDENTE";

    @Override
    public FeceaCifraImpuestoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        FeceaCifraImpuestoDTO fecetImpuestoCifraDTO = new FeceaCifraImpuestoDTO();
        FeceaImpuestoConceptoDTO impuestoCocepto = new FeceaImpuestoConceptoDTO();
        FececTipoImpuesto impuesto = new FececTipoImpuesto();
        FececConcepto concepto = new FececConcepto();
        FeceaCifraTipoCifraDTO tipoCifra = new FeceaCifraTipoCifraDTO();
        fecetImpuestoCifraDTO.setIdCifraImpuesto(rs.getBigDecimal(COLUMN_ID_CIFRA_IMPUESTO));
        fecetImpuestoCifraDTO.setFechaInicio(rs.getDate(COLUMN_FECHA_INICIO));
        fecetImpuestoCifraDTO.setFechaFin(rs.getDate(COLUMN_FECHA_FIN));
        impuesto.setIdTipoImpuesto(rs.getBigDecimal(COLUMN_ID_TIPO_IMPUESTO));
        impuesto.setDescripcion(rs.getString(COLUMN_DESCR_IMPUESTO));
        concepto.setIdConcepto(rs.getInt(COLUMN_ID_IMPUESTO_CONCEPTO));
        concepto.setDescripcion(rs.getString(COLUMN_DESCR_CONCEPTO));
        impuestoCocepto.setImpuesto(impuesto);
        impuestoCocepto.setConcepto(concepto);
        fecetImpuestoCifraDTO.setImpuestoConcepto(impuestoCocepto);
        tipoCifra.setDescripcion(rs.getString(COLUMN_DESCR));
        tipoCifra.setIdCifra(rs.getBigDecimal(COLUMN_ID_CIFRA_TIPO_CIFRA));
        fecetImpuestoCifraDTO.setTipoCifra(tipoCifra);
        fecetImpuestoCifraDTO.setFechaPago(rs.getDate(COLUMN_FECHA_PAGO));
        fecetImpuestoCifraDTO.setImporte(rs.getBigDecimal(COLUMN_IMPORTE));
        fecetImpuestoCifraDTO.setActualizaciones(rs.getBigDecimal(COLUMN_ACTUALIZACIONES));
        fecetImpuestoCifraDTO.setMultas(rs.getBigDecimal(COLUMN_MULTAS));
        fecetImpuestoCifraDTO.setRecargos(rs.getBigDecimal(COLUMN_RECARGOS));
        fecetImpuestoCifraDTO.setObservaciones(rs.getString(COLUMN_OBSERVACIONES));
        fecetImpuestoCifraDTO.setTotal(rs.getBigDecimal(COLUMN_TOTAL));
        fecetImpuestoCifraDTO.setIdCifra(rs.getBigDecimal(COLUMN_ID_DETALLE_CIFRA));
        fecetImpuestoCifraDTO.setDerivaAntecedente(rs.getBigDecimal(COLUMN_ANTECEDENTE));
        return fecetImpuestoCifraDTO;
    }

}
