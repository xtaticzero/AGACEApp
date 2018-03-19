package mx.gob.sat.siat.feagace.modelo.dao.ordenes.cifras;

import java.math.BigDecimal;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetParcialidadCifraDTO;

public interface FecehParcialidadHistoricoDAO {

    String SELECT_SEQUENCE = "select FECEQ_PARCIALIDAD_HIS.nextval from dual";

    String INSERT_DOC_HIS_CIFRA = "insert into FECEH_PARCIALIDAD (ID_PARCIALIDAD_HISTORICO, ID_CIFRA_IMPUESTO, "
            + "FECHA_ALTA_PARCIALIDAD, NUMERO_PARCIALIDADES, ID_TIPO_PARCIALIDAD, "
            + "MONTO_TOTAL_CIFRA, FECHA_INICIO, FECHA_FIN, BLN_ACTIVO) SELECT "
            + "?, ?, FECHA_ALTA_PARCIALIDAD, "
            + "NUMERO_PARCIALIDADES, ID_TIPO_PARCIALIDAD, MONTO_TOTAL_CIFRA, FECHA_INICIO, "
            + "FECHA_FIN, 1 from fecet_parcialidad_cifra where ID_CIFRA_IMPUESTO = ?";

    String OBTENER_PARCIALIDAD = "select fpc.ID_PARCIALIDAD_HISTORICO, fpc.ID_CIFRA_IMPUESTO, fpc.FECHA_ALTA_PARCIALIDAD, "
            + "fpc.NUMERO_PARCIALIDADES, fpc.ID_TIPO_PARCIALIDAD, fpc.MONTO_TOTAL_CIFRA, fpc.FECHA_INICIO, fpc.FECHA_FIN, "
            + "ftp.TIPO_PARCIALIDAD from feceh_parcialidad fpc, fecec_tipo_parcialidad ftp WHERE fpc.ID_CIFRA_IMPUESTO = ? "
            + "and ftp.ID_TIPO_PARCIALIDAD = fpc.ID_TIPO_PARCIALIDAD";

    BigDecimal insertarRegistro(BigDecimal idCifra, BigDecimal idCifraHistorico);

    FecetParcialidadCifraDTO obtenerParcialidadCifraImpuesto(BigDecimal idCifraImpuesto);

}
