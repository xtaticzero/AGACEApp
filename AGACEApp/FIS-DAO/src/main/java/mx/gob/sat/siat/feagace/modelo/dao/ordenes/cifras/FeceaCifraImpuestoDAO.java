package mx.gob.sat.siat.feagace.modelo.dao.ordenes.cifras;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FeceaCifraImpuestoDTO;

public interface FeceaCifraImpuestoDAO {

    String SELECT_SEQUENCE = "select FECEQ_CIFRA_IMPUESTO.nextval from dual";

    String INSERTA_CIFRA = "Insert into FECEA_CIFRA_IMPUESTO (ID_CIFRA_IMPUESTO,"
            + "ID_DETALLE_CIFRA,ID_IMPUESTO_CONCEPTO,ID_CIFRA_TIPO_CIFRA,FECHA_PAGO, "
            + "IMPORTE,ACTUALIZACIONES,MULTAS,RECARGOS,OBSERVACIONES,FECHA_INICIO,FECHA_FIN,BLN_ACTIVO,ANTECEDENTE) "
            + "values (?,?,(select id_impuesto_concepto from fecea_impuesto_concepto "
            + "where ID_TIPO_IMPUESTO = ? and ID_CONCEPTO = ?),?,?,?,?,?,?,?,?,?,1,?)";

    String OBTENER_CIFRAS_METODO = "select fc.id_detalle_cifra, fc.id_orden, fc.FECHA_INICIO fecha_inicio_cifra, "
            + "fc.FECHA_FIN fecha_fin_cifra, "
            + "fci.ID_CIFRA_IMPUESTO, fci.ID_IMPUESTO_CONCEPTO, fci.ID_CIFRA_TIPO_CIFRA, fci.FECHA_PAGO, fci.IMPORTE, "
            + "fci.ACTUALIZACIONES, fci.MULTAS, fci.RECARGOS, fci.OBSERVACIONES, fci.FECHA_INICIO, fci.FECHA_FIN, "
            + "fci.IMPORTE + fci.ACTUALIZACIONES + fci.MULTAS + fci.RECARGOS TOTAL, (select ID_TIPO_IMPUESTO FROM "
            + "fecea_impuesto_concepto where id_impuesto_concepto = fci.ID_IMPUESTO_CONCEPTO) ID_TIPO_IMPUESTO, "
            + "(select b.DESCRIPCION FROM fecea_impuesto_concepto a, fecec_tipo_impuesto b where "
            + "a.id_impuesto_concepto = fci.ID_IMPUESTO_CONCEPTO and b.ID_TIPO_IMPUESTO = a.ID_TIPO_IMPUESTO) "
            + "DESCR_IMPUESTO, (select ID_CONCEPTO FROM fecea_impuesto_concepto where id_impuesto_concepto = "
            + "fci.ID_IMPUESTO_CONCEPTO) ID_CONCEPTO, (select b.DESCRIPCION FROM fecea_impuesto_concepto a, "
            + "FECEC_CONCEPTOS b where a.id_impuesto_concepto = fci.ID_IMPUESTO_CONCEPTO and b.ID_CONCEPTO = "
            + "a.ID_CONCEPTO) DESCR_CONCEPTO, (select NOMBRE from fecec_tipo_cifra FTC, FECEA_CIFRA_TIPO_CIFRA "
            + "FCTC WHERE FCTC.ID_CIFRA_TIPO_CIFRA = fci.ID_CIFRA_TIPO_CIFRA AND FTC.ID_TIPO_CIFRA = FCTC.ID_TIPO_CIFRA) "
            + "DESCR, fci.ANTECEDENTE from fecet_cifra fc, fecea_cifra_impuesto fci where fc.ID_ORDEN = ? "
            + "and fci.ID_IMPUESTO_CONCEPTO = (select ID_IMPUESTO_CONCEPTO "
            + "from fecea_impuesto_concepto where ID_TIPO_IMPUESTO = ? and ID_CONCEPTO = ?) "
            + "and fci.ID_CIFRA_TIPO_CIFRA = ? and fci.ID_DETALLE_CIFRA = fc.ID_DETALLE_CIFRA and fc.BLN_ACTIVO = 1";

    String OBTENER_CIFRAS_ORDEN_TIPO = "select fc.id_detalle_cifra, fc.id_orden, fc.FECHA_INICIO, fc.FECHA_FIN, "
            + "fci.ID_CIFRA_IMPUESTO, fci.ID_IMPUESTO_CONCEPTO, fci.ID_CIFRA_TIPO_CIFRA, fci.FECHA_PAGO, fci.IMPORTE, "
            + "fci.ACTUALIZACIONES, fci.MULTAS, fci.RECARGOS, fci.OBSERVACIONES, fci.FECHA_INICIO, fci.FECHA_FIN, "
            + "fci.IMPORTE + fci.ACTUALIZACIONES + fci.MULTAS + fci.RECARGOS TOTAL, (select ID_TIPO_IMPUESTO FROM "
            + "fecea_impuesto_concepto where id_impuesto_concepto = fci.ID_IMPUESTO_CONCEPTO) ID_TIPO_IMPUESTO, (select "
            + "b.DESCRIPCION FROM fecea_impuesto_concepto a, fecec_tipo_impuesto b where a.id_impuesto_concepto = "
            + "fci.ID_IMPUESTO_CONCEPTO and b.ID_TIPO_IMPUESTO = a.ID_TIPO_IMPUESTO) DESCR_IMPUESTO,"
            + "(select ID_CONCEPTO FROM fecea_impuesto_concepto where id_impuesto_concepto = fci.ID_IMPUESTO_CONCEPTO) "
            + "ID_CONCEPTO, (select b.DESCRIPCION FROM fecea_impuesto_concepto a, FECEC_CONCEPTOS b where "
            + "a.id_impuesto_concepto = fci.ID_IMPUESTO_CONCEPTO and b.ID_CONCEPTO = a.ID_CONCEPTO) DESCR_CONCEPTO, "
            + "(select NOMBRE from fecec_tipo_cifra FTC, FECEA_CIFRA_TIPO_CIFRA FCTC WHERE "
            + "FCTC.ID_CIFRA_TIPO_CIFRA = fci.ID_CIFRA_TIPO_CIFRA AND FTC.ID_TIPO_CIFRA = FCTC.ID_TIPO_CIFRA) DESCR, fci.ANTECEDENTE "
            + "from fecet_cifra fc, fecea_cifra_impuesto fci, fecea_cifra_tipo_cifra fctc, fecec_cifra ftc where fc.ID_ORDEN = ? "
            + "and fci.ID_DETALLE_CIFRA = fc.ID_DETALLE_CIFRA and fctc.ID_CIFRA_TIPO_CIFRA = fci.ID_CIFRA_TIPO_CIFRA "
            + "and ftc.ID_CIFRA = fctc.ID_CIFRA and fci.ID_CIFRA_TIPO_CIFRA = ? and fc.BLN_ACTIVO = 1";

    String OBTENER_CIFRAS_ORDEN_TIPO_CIFRA = "select fc.id_detalle_cifra, fc.id_orden, fc.FECHA_INICIO, fc.FECHA_FIN, "
            + "fci.ID_CIFRA_IMPUESTO, fci.ID_IMPUESTO_CONCEPTO, fci.ID_CIFRA_TIPO_CIFRA, fci.FECHA_PAGO, fci.IMPORTE, "
            + "fci.ACTUALIZACIONES, fci.MULTAS, fci.RECARGOS, fci.OBSERVACIONES, fci.FECHA_INICIO, fci.FECHA_FIN, "
            + "(case when fci.importe is null then 0 else fci.importe end) + (case when fci.actualizaciones is null "
            + "then 0 else fci.actualizaciones end) + (case when fci.multas is null then 0 else fci.multas end) + "
            + "(case when fci.recargos is null then 0 else fci.recargos end) TOTAL, (select ID_TIPO_IMPUESTO FROM "
            + "fecea_impuesto_concepto where id_impuesto_concepto = fci.ID_IMPUESTO_CONCEPTO) ID_TIPO_IMPUESTO, (select "
            + "b.DESCRIPCION FROM fecea_impuesto_concepto a, fecec_tipo_impuesto b where a.id_impuesto_concepto = "
            + "fci.ID_IMPUESTO_CONCEPTO and b.ID_TIPO_IMPUESTO = a.ID_TIPO_IMPUESTO) DESCR_IMPUESTO,"
            + "(select ID_CONCEPTO FROM fecea_impuesto_concepto where id_impuesto_concepto = fci.ID_IMPUESTO_CONCEPTO) "
            + "ID_CONCEPTO, (select b.DESCRIPCION FROM fecea_impuesto_concepto a, FECEC_CONCEPTOS b where "
            + "a.id_impuesto_concepto = fci.ID_IMPUESTO_CONCEPTO and b.ID_CONCEPTO = a.ID_CONCEPTO) DESCR_CONCEPTO, "
            + "(select NOMBRE from fecec_tipo_cifra FTC, FECEA_CIFRA_TIPO_CIFRA FCTC WHERE "
            + "FCTC.ID_CIFRA_TIPO_CIFRA = fci.ID_CIFRA_TIPO_CIFRA AND FTC.ID_TIPO_CIFRA = FCTC.ID_TIPO_CIFRA) DESCR, fci.ANTECEDENTE "
            + "from fecet_cifra fc, fecea_cifra_impuesto fci, fecea_cifra_tipo_cifra fctc, fecec_cifra ftc where fc.ID_ORDEN = ? "
            + "and fci.ID_DETALLE_CIFRA = fc.ID_DETALLE_CIFRA and fctc.ID_CIFRA_TIPO_CIFRA = fci.ID_CIFRA_TIPO_CIFRA "
            + "and ftc.ID_CIFRA = fctc.ID_CIFRA and fc.BLN_ACTIVO = 1 and fci.BLN_ACTIVO = 1 and fci.id_cifra_tipo_cifra = ?";

    String EXISTE_CIFRA = "select count(1) from fecet_cifra fc, fecea_cifra_impuesto fci where fc.ID_ORDEN = ? "
            + "and fci.ID_IMPUESTO_CONCEPTO = (select ID_IMPUESTO_CONCEPTO "
            + "from fecea_impuesto_concepto where ID_TIPO_IMPUESTO = ? and ID_CONCEPTO = ?) "
            + "and fci.ID_CIFRA_TIPO_CIFRA = ? and fci.ID_DETALLE_CIFRA = fc.ID_DETALLE_CIFRA and fc.BLN_ACTIVO = 1 "
            + "and fci.BLN_ACTIVO = 1";

    String ACTUALIZAR_CIFRA = "UPDATE FECEA_CIFRA_IMPUESTO SET FECHA_PAGO = ?, IMPORTE = ?, "
            + "ACTUALIZACIONES = ?, MULTAS = ?, RECARGOS = ?, OBSERVACIONES = ?, FECHA_INICIO = ?, "
            + "FECHA_FIN = ? WHERE ID_CIFRA_IMPUESTO = ?";

    String ACTUALIZAR_ESTATUS_CIFRA = "update fecea_cifra_impuesto set BLN_ACTIVO = ? where ID_DETALLE_CIFRA = ?";

    String ACTUALIZAR_ESTATUS_IMPUESTO = "update fecea_cifra_impuesto set BLN_ACTIVO = ? where ID_CIFRA_IMPUESTO = ?";

    BigDecimal insertaCifra(FeceaCifraImpuestoDTO cifra, BigDecimal idCifra);

    List<FeceaCifraImpuestoDTO> obtenerCifrasPorOrdenCifraImpuestoConcepto(BigDecimal idOrden, BigDecimal tipoCifra,
            BigDecimal impuesto, BigDecimal concepto);

    List<FeceaCifraImpuestoDTO> obtenerCifrasPorOrden(BigDecimal idOrden, BigDecimal tipoCifra);

    boolean existeCifra(BigDecimal idOrden, BigDecimal tipoCifra, BigDecimal impuesto, int concepto);

    List<FeceaCifraImpuestoDTO> obtenerCifrasPorOrdenCifraTipoCifra(BigDecimal idOrden, BigDecimal tipoCifraTipoCifra);

    BigDecimal actualizarCifra(FeceaCifraImpuestoDTO cifra);

    BigDecimal actualizarEstatusCifra(BigDecimal idCifra, BigDecimal idEstatus);

    BigDecimal actualizarEstatusImpuesto(BigDecimal idCifraImpuesto, BigDecimal idEstatus);
}
