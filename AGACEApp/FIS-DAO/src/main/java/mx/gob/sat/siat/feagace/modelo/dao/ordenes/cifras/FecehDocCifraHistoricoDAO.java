package mx.gob.sat.siat.feagace.modelo.dao.ordenes.cifras;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocCifraDTO;

public interface FecehDocCifraHistoricoDAO {

    String SELECT_SEQUENCE = "select FECEQ_DOC_CIFRA_HIS.nextval from dual";

    String INSERT_DOC_HIS_CIFRA = " insert into feceh_doc_cifra (ID_DOC_CIFRA_HISTORICO, "
            + "ID_CIFRA_IMPUESTO, RUTA_ARCHIVO, FECHA_CREACION, BLN_ACTIVO, FECHA_FIN) values (?, ?, ?, sysdate, 1, ?)";

    String OBTENER_DOCUMENTOS = "select ID_DOC_CIFRA_HISTORICO, ID_CIFRA_IMPUESTO, RUTA_ARCHIVO, FECHA_CREACION, "
            + "FECHA_FIN from feceh_doc_cifra WHERE ID_CIFRA_IMPUESTO = ? AND BLN_ACTIVO = 1";

    BigDecimal insertarRegistro(FecetDocCifraDTO documento, BigDecimal idCifraHistorico);

    List<FecetDocCifraDTO> obtenerDocumentos(BigDecimal idCifraImpuesto);

}
