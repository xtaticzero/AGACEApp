package mx.gob.sat.siat.feagace.modelo.dao.ordenes.cifras;

import java.math.BigDecimal;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetParcialidadCifraDTO;

public interface FecetParcialidadCifraDAO {

    String SELECT_SEQUENCE = "select FECEQ_PARCIALIDAD_CIFRA.nextval from dual";

    String INSERTAR_PARCIALIDAD = "Insert into FECET_PARCIALIDAD_CIFRA (ID_PARCIALIDAD_CIFRA,"
            + "ID_CIFRA_IMPUESTO,FECHA_ALTA_PARCIALIDAD,NUMERO_PARCIALIDADES,ID_TIPO_PARCIALIDAD,"
            + "MONTO_TOTAL_CIFRA,FECHA_INICIO,FECHA_FIN,BLN_ACTIVO) values " + "(?,?,sysdate,?,?,?,?,?,1)";

    String OBTENER_PARCIALIDAD = "select fpc.ID_PARCIALIDAD_CIFRA, fpc.ID_CIFRA_IMPUESTO, fpc.FECHA_ALTA_PARCIALIDAD, "
            + "fpc.NUMERO_PARCIALIDADES, fpc.ID_TIPO_PARCIALIDAD, fpc.MONTO_TOTAL_CIFRA, fpc.FECHA_INICIO, "
            + "fpc.FECHA_FIN, ftp.TIPO_PARCIALIDAD from fecet_parcialidad_cifra fpc, fecec_tipo_parcialidad "
            + "ftp WHERE fpc.ID_CIFRA_IMPUESTO = ? and ftp.ID_TIPO_PARCIALIDAD = fpc.ID_TIPO_PARCIALIDAD";

    String ACTUALIZAR_PARCIALIDAD = "update fecet_parcialidad_cifra set FECHA_ALTA_PARCIALIDAD = sysdate, "
            + "NUMERO_PARCIALIDADES = ?, ID_TIPO_PARCIALIDAD = ?, MONTO_TOTAL_CIFRA = ?, "
            + "FECHA_INICIO = sysdate, BLN_ACTIVO = 1 where ID_CIFRA_IMPUESTO = ?";

    String ACTUALIZAR_ESTATUS = "update fecet_parcialidad_cifra set BLN_ACTIVO = ? where ID_CIFRA_IMPUESTO = ?";

    String EXISTE_PARCIALIDAD = "select count(1) from fecet_parcialidad_cifra where ID_CIFRA_IMPUESTO = ?";

    BigDecimal insertaParcialidad(FecetParcialidadCifraDTO parcialidad, BigDecimal idCifraImpuesto);

    FecetParcialidadCifraDTO obtenerParcialidadCifraImpuesto(BigDecimal idCifraImpuesto);

    BigDecimal actualizarParcialidad(FecetParcialidadCifraDTO parcialidad, BigDecimal idCifraImpuesto);

    BigDecimal actualizarEstatusParcialidad(BigDecimal idCifraImpuesto, BigDecimal estatus);

    boolean existeParcialidad(BigDecimal idCifraImpuesto);

}
