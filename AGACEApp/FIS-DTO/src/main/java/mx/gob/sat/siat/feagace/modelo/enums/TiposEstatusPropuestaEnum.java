package mx.gob.sat.siat.feagace.modelo.enums;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sergio.vaca
 *
 */
public enum TiposEstatusPropuestaEnum {
    CANCELADAS_VALIDAR(1, BigDecimal.valueOf(29), BigDecimal.valueOf(28), TipoOpcion.CANCELACION, "Propuestas canceladas pendientes de validaci\u00f3n", "Aprobar cancelaci\u00f3n", "No Aprobar cancelaci\u00f3n", true, null, BigDecimal.valueOf(143)),
    CANCELADADAS_NO_APROBAR(2, BigDecimal.valueOf(29), BigDecimal.valueOf(28), TipoOpcion.CANCELACION, "Propuestas canceladas pendientes de validaci\u00f3n", "Aprobar cancelaci\u00f3n", "No Aprobar cancelaci\u00f3n", true, BigDecimal.valueOf(22), BigDecimal.valueOf(143)),
    CANCELADAS_EMISION(3, BigDecimal.valueOf(29), BigDecimal.valueOf(28), TipoOpcion.CANCELACION, "Propuestas canceladas pendientes de validaci\u00f3n", "Aprobar cancelaci\u00f3n", "No Aprobar cancelaci\u00f3n", true, BigDecimal.valueOf(14), BigDecimal.valueOf(143)),
    RECHAZADAS_VALIDAR(4, BigDecimal.valueOf(26), BigDecimal.valueOf(38), TipoOpcion.RECHAZO, "Propuestas rechazadas pendientes de validaci\u00f3n", "Aprobar rechazo", "No Aprobar rechazo", true, null, BigDecimal.valueOf(62)),
    RECHAZADAS_NO_APROBAR(5, BigDecimal.valueOf(26), BigDecimal.valueOf(38), TipoOpcion.RECHAZO, "Propuestas rechazadas pendientes de validaci\u00f3n", "Aprobar rechazo", "No Aprobar rechazo", true, BigDecimal.valueOf(22), BigDecimal.valueOf(62)),
    RECHAZADAS_EMISION(6, BigDecimal.valueOf(26), BigDecimal.valueOf(38), TipoOpcion.RECHAZO, "Propuestas rechazadas pendientes de validaci\u00f3n", "Aprobar rechazo", "No Aprobar rechazo", true, BigDecimal.valueOf(14), BigDecimal.valueOf(62)),
    TRANSFERIDAS_VALIDAR(7, BigDecimal.valueOf(27), BigDecimal.valueOf(41), TipoOpcion.TRANSFERENCIA, "Propuestas transferidas pendientes de validaci\u00f3n", "Aprobar transferencia", "No Aprobar transferencia", true, null, BigDecimal.valueOf(120)),
    TRANSFERIDAS_NO_APROBAR(8, BigDecimal.valueOf(27), BigDecimal.valueOf(41), TipoOpcion.TRANSFERENCIA, "Propuestas transferidas pendientes de validaci\u00f3n", "Aprobar transferencia", "No Aprobar transferencia", true, BigDecimal.valueOf(22), BigDecimal.valueOf(120)),
    TRANSFERIDAS_EMISION(9, BigDecimal.valueOf(27), BigDecimal.valueOf(41), TipoOpcion.TRANSFERENCIA, "Propuestas transferidas pendientes de validaci\u00f3n", "Aprobar transferencia", "No Aprobar transferencia", true, BigDecimal.valueOf(14), BigDecimal.valueOf(120)),
    RETROALIMENTADAS_VALIDAR(10, BigDecimal.valueOf(35), BigDecimal.valueOf(40), TipoOpcion.RETRO, "Propuestas retroalimentadas pendientes de validaci\u00f3n", "Aprobar retroalimentaci\u00f3n", "No Aprobar retroalimentaci\u00f3n", true, null, BigDecimal.valueOf(145)),
    RETROALIMENTADAS_NO_APROBAR(11, BigDecimal.valueOf(35), BigDecimal.valueOf(40), TipoOpcion.RETRO, "Propuestas retroalimentadas pendientes de validaci\u00f3n", "Aprobar retroalimentaci\u00f3n", "No Aprobar retroalimentaci\u00f3n", true, BigDecimal.valueOf(22), BigDecimal.valueOf(145)),
    RETROALIMENTADAS_EMISION(12, BigDecimal.valueOf(35), BigDecimal.valueOf(40), TipoOpcion.RETRO, "Propuestas retroalimentadas pendientes de validaci\u00f3n", "Aprobar retroalimentaci\u00f3n", "No Aprobar retroalimentaci\u00f3n", true, BigDecimal.valueOf(14), BigDecimal.valueOf(145)),
    RETROALIMENTADAS_INFORMADAS(13, null, null, TipoOpcion.RETRO, "Propuestas en atenci\u00f3n de retroalimentaci\u00f3n por programaci\u00f3n", "", "", false, null, BigDecimal.valueOf(52), BigDecimal.valueOf(72)),
    REVISION_NO_APROBADA(14, null, null, null, "Propuestas con Revisi\u00f3n No aprobadas por Firmante", "", "", false, BigDecimal.valueOf(22), BigDecimal.valueOf(57)),
    REVISION_EMISION(15, null, null, null, "Propuestas con Revisi\u00f3n Retroalimentadas por Emisi\u00f3n de \u00d3rdenes", "", "", false, BigDecimal.valueOf(14), BigDecimal.valueOf(57)),
    REVISION_INFORMADA(16, null, null, null, "Propuestas en Atenci\u00f3n", "", "", false, null, BigDecimal.valueOf(61));
    
    private int idRegistro;
    private BigDecimal idLeyendaAprobada;
    private BigDecimal idLeyendaNoAprobada;
    private boolean link;
    private String descGrid;
    private String btnAceptar;
    private String btnCancelar;
    private BigDecimal idAccionOrigen;
    private List<BigDecimal> estatus;
    private TipoOpcion opcion;

    TiposEstatusPropuestaEnum(int idRegistro, BigDecimal idLeyendaAprobada, BigDecimal idLeyendaNoAprobada,
            TipoOpcion opcion, String descGrid, String btnAceptar, String btnCancelar, boolean link,
            BigDecimal idAccionOrigen, BigDecimal... estatus) {
        this.idRegistro = idRegistro;
        this.idLeyendaAprobada = idLeyendaAprobada;
        this.idLeyendaNoAprobada = idLeyendaNoAprobada;
        this.idAccionOrigen = idAccionOrigen;
        this.estatus = new ArrayList<BigDecimal>();
        this.link = link;
        this.descGrid = descGrid;
        this.btnAceptar = btnAceptar;
        this.btnCancelar = btnCancelar;
        this.opcion = opcion;
        if (estatus != null && estatus.length > 0) {
            this.estatus.addAll(Arrays.asList(estatus));
        }
    }

    public enum TipoOpcion {
        CANCELACION("\u00bfEst\u00e1s seguro que deseas Cancelar el registro ", "?",
                "\u00bfEst\u00e1s seguro que No deseas aprobar la solicitud de cancelaci\u00f3n del registro ", "?",
                "El Registro %s ha sido cancelado.", "La cancelaci\u00f3n del Registro %s no ha sido aprobada."),

        RECHAZO("\u00bfEst\u00e1s seguro que deseas aprobar el rechazo del registro ", "?",
                "\u00bfEst\u00e1s seguro que No deseas aprobar la solicitud de rechazo del registro ", "?",
                "El rechazo del Registro %s ha sido aprobado y enviado al \u00e1rea correspondiente para su seguimiento.",
                "El rechazo del Registro %s No ha sido aprobado y ha sido enviado al \u00e1rea correspondiente."),

        TRANSFERENCIA("\u00bfEst\u00e1s seguro que deseas aprobar la transferencia del registro ", "?",
                "\u00bfEst\u00e1s seguro que No deseas aprobar la transferencia del registro ", "?",
                "La transferencia del Registro %s ha sido aprobada y enviada al \u00e1rea correspondiente para su seguimiento.",
                "La transferencia del Registro %s No ha sido aprobada y ser\u00e1 enviada al \u00e1rea correspondiente."),

        RETRO("\u00bfEst\u00e1s seguro que deseas aprobar la solicitud de retroalimentaci\u00f3n del registro ", "?",
                "\u00bfEst\u00e1s seguro que No deseas aprobar la solicitud de retroalimentaci\u00f3n del registro ",
                "?", "El Registro %s ha sido enviado a retroalimentaci\u00f3n.",
                "La solicitud de retroalimentaci\u00f3n del Registro %s ha sido rechazada y enviada al \u00e1rea correspondiente.");

        private String msgAceptar1;
        private String msgAceptar2;
        private String msgCancelar1;
        private String msgCancelar2;
        private String msgExitoAprobar;
        private String msgExitoNoAprobar;

        TipoOpcion(String msgAceptar1, String msgAceptar2, String msgCancelar1, String msgCancelar2,
                String msgExitoAprobar, String msgExitoNoAprobar) {
            this.msgAceptar1 = msgAceptar1;
            this.msgAceptar2 = msgAceptar2;
            this.msgCancelar1 = msgCancelar1;
            this.msgCancelar2 = msgCancelar2;
            this.msgExitoAprobar = msgExitoAprobar;
            this.msgExitoNoAprobar = msgExitoNoAprobar;
        }

        public String getMsgAceptar1() {
            return msgAceptar1;
        }

        public String getMsgAceptar2() {
            return msgAceptar2;
        }

        public String getMsgCancelar1() {
            return msgCancelar1;
        }

        public String getMsgCancelar2() {
            return msgCancelar2;
        }

        public String getMsgExitoAprobar() {
            return msgExitoAprobar;
        }

        public String getMsgExitoNoAprobar() {
            return msgExitoNoAprobar;
        }

    }

    public BigDecimal getIdAccionOrigen() {
        return idAccionOrigen;
    }

    public List<BigDecimal> getEstatus() {
        return estatus;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public String getDescGrid() {
        return descGrid;
    }

    public boolean isLink() {
        return link;
    }

    public String getBtnAceptar() {
        return btnAceptar;
    }

    public String getBtnCancelar() {
        return btnCancelar;
    }

    public TipoOpcion getOpcion() {
        return opcion;
    }

    public BigDecimal getIdLeyendaAprobada() {
        return idLeyendaAprobada;
    }

    public BigDecimal getIdLeyendaNoAprobada() {
        return idLeyendaNoAprobada;
    }

    public static TiposEstatusPropuestaEnum obtenerEnumById(int idOpcion) {
        TiposEstatusPropuestaEnum resultado = null;
        for (TiposEstatusPropuestaEnum elemento : TiposEstatusPropuestaEnum.values()) {
            if (elemento.getIdRegistro() != idOpcion) {
                continue;
            }
            resultado = elemento;
            break;
        }
        return resultado;
    }
}
