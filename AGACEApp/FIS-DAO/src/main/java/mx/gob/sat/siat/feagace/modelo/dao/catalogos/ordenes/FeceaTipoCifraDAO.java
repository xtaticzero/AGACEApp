package mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FeceaCifraTipoCifraDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.TotalCifrasDTO;

public interface FeceaTipoCifraDAO {

    String TOTAL_TIPO_CIFRAS = "select fcc.id_cifra, fcc.cifra, "
            + "(case when sum(importe) is null then 0 else sum(importe) end) + "
            + "(case when sum(actualizaciones) is null then 0 else sum(actualizaciones) end) + "
            + "(case when sum(multas) is null then 0 else sum(multas) end) + "
            + "(case when sum(recargos) is null then 0 else  sum(recargos) end) TOTAL from fecet_cifra fc, "
            + "fecea_cifra_impuesto fci, fecea_cifra_tipo_cifra fctc, fecec_cifra fcc "
            + "where fci.id_detalle_cifra = fc.id_detalle_cifra "
            + "and fctc.ID_CIFRA_TIPO_CIFRA = fci.ID_CIFRA_TIPO_CIFRA and fcc.id_cifra = fctc.id_cifra "
            + "and fc.id_orden = ? and fc.BLN_ACTIVO = 1 and fci.BLN_ACTIVO = 1 group by fcc.id_cifra, fcc.cifra";

    String OBTENER_TIPO_CIFRA = "select fctc.ID_CIFRA_TIPO_CIFRA, ftc.nombre from fecec_tipo_cifra ftc, "
            + "fecec_cifra fc, fecea_cifra_tipo_cifra fctc where fc.id_cifra = ? "
            + "and fctc.id_cifra = fc.id_cifra and ftc.ID_TIPO_CIFRA = fctc.ID_TIPO_CIFRA";

    List<FeceaCifraTipoCifraDTO> obtenerCifrasPorTipo(int tipoCifra);

    List<TotalCifrasDTO> obtenerSumatoriaPorTipoCifraIdOrden(BigDecimal idOrden);

}
