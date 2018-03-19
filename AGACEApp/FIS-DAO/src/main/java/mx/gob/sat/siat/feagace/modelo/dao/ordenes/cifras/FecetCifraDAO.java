package mx.gob.sat.siat.feagace.modelo.dao.ordenes.cifras;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCifraDTO;

public interface FecetCifraDAO {

    String SELECT_SEQUENCE = "select FECEQ_CIFRA_TRA.nextval from dual";

    String INSERTAR_CIFRA = "Insert into FECET_CIFRA (ID_DETALLE_CIFRA,"
            + "ID_ORDEN,FECHA_INICIO,FECHA_FIN,BLN_ACTIVO) values (?,?,?,?,1)";

    String CONSULTA_CIFRAS_ORDEN_TIPO = "select fci.ID_CIFRA_TIPO_CIFRA, (case when sum(importe) is null then 0 "
            + "else sum(importe) end) + (case when sum(actualizaciones) is null "
            + "then 0 else sum(actualizaciones) end) + (case when sum(multas) is "
            + "null then 0 else sum(multas) end) + (case when sum(recargos) is null "
            + "then 0 else  sum(recargos) end) TOTAL, ftc.NOMBRE "
            + "from fecet_cifra fc, fecea_cifra_impuesto fci, fecea_cifra_tipo_cifra "
            + "fctc, fecec_tipo_cifra ftc, fecec_cifra fcc where fci.ID_DETALLE_CIFRA = fc.ID_DETALLE_CIFRA "
            + "and fctc.ID_CIFRA_TIPO_CIFRA = fci.ID_CIFRA_TIPO_CIFRA and ftc.ID_TIPO_CIFRA = "
            + "fctc.ID_TIPO_CIFRA and fc.id_orden = ? and fcc.ID_CIFRA = ? and fctc.ID_CIFRA = "
            + "fcc.ID_CIFRA and fc.BLN_ACTIVO = 1 and fci.BLN_ACTIVO = 1 group by fci.ID_CIFRA_TIPO_CIFRA, ftc.NOMBRE";

    String ACTUALIZA_ESTATUS = "update fecet_cifra set bln_activo = ? where ID_DETALLE_CIFRA = ?";

    String OBTENER_ID_DETALLE_CIFRA = "select fc.ID_DETALLE_CIFRA from fecet_cifra fc, fecea_cifra_impuesto fci "
            + "where fci.ID_DETALLE_CIFRA = fc.ID_DETALLE_CIFRA and fci.ID_CIFRA_TIPO_CIFRA = ? "
            + "and fc.ID_ORDEN = ? and fc.BLN_ACTIVO = 1 group by fc.ID_DETALLE_CIFRA";

    BigDecimal insertarCifra(FecetCifraDTO cifras, BigDecimal idOrden);

    List<FecetCifraDTO> obtenerCifrasPorOrden(BigDecimal idOrden, BigDecimal tipoCifra);

    BigDecimal actualizarEstatusCifra(BigDecimal idDetalleCifra, BigDecimal estatus);

    BigDecimal obtenerIdDetalleCifra(BigDecimal idOrden, BigDecimal tipoCifra);

}
