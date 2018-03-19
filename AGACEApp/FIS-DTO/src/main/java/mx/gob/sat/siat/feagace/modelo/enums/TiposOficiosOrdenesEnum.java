package mx.gob.sat.siat.feagace.modelo.enums;

import java.math.BigDecimal;

public enum TiposOficiosOrdenesEnum {

    COMPULSA_TERCEROS(1, "Oficio Compulsa con Terceros"),
    COMPULSA_INTERNACIONAL(2, "Oficio Compulsa Internacional"),
    SEGUNDO_REQUERIMIENTO(3, "Oficio de Segundo Requerimiento"),
    REQUERIMIENTO_REINCIDENCIA(4, "Oficio de Requerimiento por Reincidencia"),
    OBSERVACIONES_REVISION_ESCRITORIO(5, "Oficio de Observaciones en Revision de Escritorio"),
    SEGUNDA_UNICA_CARTA_INVITACION(6, "Oficio Segunda Unica Carta Invitacion"),
    SEGUNDA_UNICA_CARTA_INVITACION_MASIVA(7, "Oficio Segunda Carta Invitacion Masiva"),
    PRUEBAS_DESAHOGO(8, "Oficio de Pruebas de Desahogo"),
    MULTA(9, "Oficio de Multa"),
    CONCLUSION_REVISION_ESCRITOS_IMP(10, "Oficio de Conclusion de Revision de Escritos por Imposibilidad Material para su Desahogo"),
    CANCELACION_ORDEN(11, "Oficio de Cancelacion de la Orden"),
    SUSPENSION_EN_PADRON_IMP_EXP(12, "Oficio de Suspension en Padron de Importadores y Exportadores"),
    AVISO_AL_CONTRIBUYENTE(13, "Oficio de Aviso al Contribuyente"),
    SIN_OBSERVACIONES(14, "Oficio sin Observaciones"),
    RESOLUCION_DEFINITIVA(15, "Oficio de Resolucion Definitiva"),
    OTRAS_AUTORIDADES(16, "Oficio de Otras Autoridades"),
    CONCLUSION(17, "Oficio de Conclusion"),
    PRUEBAS_PERICIALES_DESAHOGO(18, "Oficio de Pruebas Periciales para su Desahogo"),
    CAMBIO_METODO(19, "Oficio de Cambio de Metodo"),
	PRELIQUIDACION(20, "Oficio de Preliquidacion"),
	AVISO_CIRCUNSTANCIAL(21, "Aviso Cinrcunstancial"),
	MEDIDAS_DE_APREMIO(29, "Medidas de Apremio");


    private final int idTipoOficio;
    private final String nombre;

    private TiposOficiosOrdenesEnum(int idTipoOficio, String nombre) {
        this.idTipoOficio = idTipoOficio;
        this.nombre = nombre;
    }

    public int getIdTipoOficio() {
        return idTipoOficio;
    }

    public String getNombre() {
        return nombre;
    }

    public BigDecimal getBigIdTipoOficio() {
        return BigDecimal.valueOf(idTipoOficio);
    }

    public static TiposOficiosOrdenesEnum parse(int idTipoOficio) {
        for (TiposOficiosOrdenesEnum tipoOficio : TiposOficiosOrdenesEnum.values()) {
            if (tipoOficio.getIdTipoOficio() == idTipoOficio) {
                return tipoOficio;
            }
        }
        return null;
    }

}
