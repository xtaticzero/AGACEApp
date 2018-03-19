package mx.gob.sat.siat.feagace.modelo.dao.ordenes.cifras;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FeceaCifraImpuestoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCifraDTO;

public interface FecehHistoricoCifraDAO {

    String SELECT_SEQUENCE = "select FECEQ_CIFRA_HIS.nextval from dual";

    String SELECT_CONSECUTIVO = "SELECT (CASE WHEN MAX(CONSECUTIVO) IS NULL THEN 0 ELSE "
            + "MAX(CONSECUTIVO) END + 1) FROM FECEH_CIFRA WHERE ID_CIFRA_IMPUESTO = ?";

    String INSERT_HIS_CIFRA = "insert into FECEH_CIFRA (ID_HISTORICO_CIFRA, FECHA_ACTUALIZACION, "
            + "ID_CIFRA_IMPUESTO, FECHA_PAGO, IMPORTE, ACTUALIZACIONES, MULTAS, RECARGOS, "
            + "TOTAL_IMPUESTO, OBSERVACIONES, FECHA_INICIO, FECHA_FIN, BLN_ACTIVO, CONSECUTIVO) "
            + "values(?, sysdate, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1, ?)";

    String OBTENER_CIFRAS_HISTORICO_ENCABEZADO = "select ftc.NOMBRE, (case when sum(fch.importe) is null then 0 else sum(fch.importe) end) + "
            + "(case when sum(fch.actualizaciones) is null then 0 else sum(fch.actualizaciones) end) +  "
            + "(case when sum(fch.multas) is null then 0 else sum(fch.multas) end) + "
            + "(case when sum(fch.recargos) is null then 0 else  sum(fch.recargos) end) TOTAL,"
            + "fctc.ID_CIFRA_TIPO_CIFRA, fch.consecutivo "
            + "from fecea_cifra_impuesto fci, fecea_cifra_tipo_cifra fctc, fecec_tipo_cifra ftc, "
            + "feceh_cifra fch, fecet_cifra fc where fci.ID_CIFRA_TIPO_CIFRA = ? and fch.ID_CIFRA_IMPUESTO = fci.ID_CIFRA_IMPUESTO "
            + "and fctc.ID_CIFRA_TIPO_CIFRA = fci.ID_CIFRA_TIPO_CIFRA and ftc.ID_TIPO_CIFRA = fctc.ID_TIPO_CIFRA "
            + "and fci.BLN_ACTIVO = 1 and fc.id_orden = ? and fci.ID_DETALLE_CIFRA = "
            + "fc.ID_DETALLE_CIFRA group by ftc.NOMBRE, fch.consecutivo, fctc.ID_CIFRA_TIPO_CIFRA";

    String OBTENER_CIFRAS_IMPUESTOS = "select fci.ID_CIFRA_IMPUESTO, fci.ID_IMPUESTO_CONCEPTO, fci.ID_CIFRA_TIPO_CIFRA, "
            + "fch.FECHA_PAGO, fch.IMPORTE, fch.ACTUALIZACIONES, fch.MULTAS, fch.RECARGOS, fch.OBSERVACIONES, "
            + "fch.FECHA_INICIO, fch.FECHA_FIN, (case when fch.importe is null then 0 else fch.importe end) + "
            + "(case when fch.actualizaciones is null then 0 else fch.actualizaciones end) + (case when fch.multas "
            + "is null then 0 else fch.multas end) + (case when fch.recargos is null then 0 else fch.recargos end) TOTAL, "
            + "(select ID_TIPO_IMPUESTO FROM fecea_impuesto_concepto where id_impuesto_concepto = fci.ID_IMPUESTO_CONCEPTO) "
            + "ID_TIPO_IMPUESTO, (select b.DESCRIPCION FROM fecea_impuesto_concepto a, fecec_tipo_impuesto b "
            + "where a.id_impuesto_concepto = fci.ID_IMPUESTO_CONCEPTO and b.ID_TIPO_IMPUESTO = a.ID_TIPO_IMPUESTO) "
            + "DESCR_IMPUESTO, (select ID_CONCEPTO FROM fecea_impuesto_concepto where id_impuesto_concepto = "
            + "fci.ID_IMPUESTO_CONCEPTO) ID_CONCEPTO, (select b.DESCRIPCION FROM fecea_impuesto_concepto a, "
            + "FECEC_CONCEPTOS b where a.id_impuesto_concepto = fci.ID_IMPUESTO_CONCEPTO and b.ID_CONCEPTO = a.ID_CONCEPTO) "
            + "DESCR_CONCEPTO, (select NOMBRE from fecec_tipo_cifra FTC, FECEA_CIFRA_TIPO_CIFRA FCTC WHERE "
            + "FCTC.ID_CIFRA_TIPO_CIFRA = fci.ID_CIFRA_TIPO_CIFRA AND FTC.ID_TIPO_CIFRA = FCTC.ID_TIPO_CIFRA) DESCR, "
            + "fci.ANTECEDENTE, fch.ID_HISTORICO_CIFRA from fecea_cifra_impuesto fci, fecea_cifra_tipo_cifra fctc, fecec_tipo_cifra ftc, "
            + "feceh_cifra fch, fecet_cifra fc where fci.ID_CIFRA_TIPO_CIFRA = ? and fch.ID_CIFRA_IMPUESTO = fci.ID_CIFRA_IMPUESTO "
            + "and fctc.ID_CIFRA_TIPO_CIFRA = fci.ID_CIFRA_TIPO_CIFRA and ftc.ID_TIPO_CIFRA = fctc.ID_TIPO_CIFRA and fci.BLN_ACTIVO = 1 "
            + "and fci.BLN_ACTIVO = 1 and fci.ID_DETALLE_CIFRA = fc.ID_DETALLE_CIFRA and fch.CONSECUTIVO = ? and fc.id_orden = ? ";

    BigDecimal insertarRegistro(FeceaCifraImpuestoDTO cifraImpuesto, BigDecimal consecutivo);

    List<FecetCifraDTO> obtenerEncabezadoHistorico(BigDecimal idCifra, BigDecimal idOrden);

    BigDecimal obtenerConsecutivo(BigDecimal idCifraImpuesto);

    List<FeceaCifraImpuestoDTO> obtenerCifrasPorOrdenCifraTipoCifraHist(BigDecimal idOrden,
            BigDecimal tipoCifraTipoCifra, BigDecimal consecutivo);

}
