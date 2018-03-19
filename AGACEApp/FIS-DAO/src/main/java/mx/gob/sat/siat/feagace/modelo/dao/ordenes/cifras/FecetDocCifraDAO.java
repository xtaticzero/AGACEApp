package mx.gob.sat.siat.feagace.modelo.dao.ordenes.cifras;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocCifraDTO;

public interface FecetDocCifraDAO {

    String SELECT_SEQUENCE = "select FECEQ_DOC_CIFRA.nextval from dual";

    String INSERTA_DOCUMENTO = "Insert into FECET_DOC_CIFRA " + "(ID_DOC_CIFRA,ID_CIFRA_IMPUESTO,RUTA_ARCHIVO,"
            + "FECHA_CREACION,BLN_ACTIVO,FECHA_FIN) values " + "(?,?,?,sysdate,1,?)";

    String OBTENER_DOCUMENTOS = "select ID_DOC_CIFRA, ID_CIFRA_IMPUESTO, "
            + "RUTA_ARCHIVO, FECHA_CREACION, FECHA_FIN from fecet_doc_cifra WHERE ID_CIFRA_IMPUESTO = ? "
            + "AND BLN_ACTIVO = 1";

    String ACTUALIZAR_ESTATUS = "update fecet_doc_cifra set BLN_ACTIVO = ? where ID_CIFRA_IMPUESTO = ? and BLN_ACTIVO = 1";

    BigDecimal insertaDocumento(FecetDocCifraDTO documento, BigDecimal idFeceaCifraImpuesto);

    List<FecetDocCifraDTO> obtenerDocumentos(BigDecimal idCifraImpuesto);

    BigDecimal actualizarEstatusDocumento(BigDecimal idCifraImpuesto, BigDecimal estatus);

}
