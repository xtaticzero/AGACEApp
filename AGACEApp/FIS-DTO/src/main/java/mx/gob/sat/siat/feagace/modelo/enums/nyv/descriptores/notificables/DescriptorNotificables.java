package mx.gob.sat.siat.feagace.modelo.enums.nyv.descriptores.notificables;

import mx.gob.sat.siat.feagace.modelo.enums.ordenes.prorrogas.EstatusProrroga;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.seguimiento.EstatusOrdenes;
import mx.gob.sat.siat.feagace.modelo.enums.EstatusOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.pruebaspericiales.EstatusPruebasPericiales;

public enum DescriptorNotificables {

    ORDEN("FECET_ORDEN", "ID_ESTATUS", "ID_ORDEN", EstatusOrdenes.ENVIADO_NOTIFICACION_CONTRIBUYENTE.getIdEstatus(), EstatusOrdenes.NOTIFICADO_AL_CONTRIBUYENTE.getIdEstatus(), EstatusOrdenes.ERROR_AL_NOTIFICAR_AL_CONTRIBUYENTE.getIdEstatus(),EstatusOrdenes.CONCLUIDA.getIdEstatus()),
    OFICIO("FECET_OFICIO", "ID_ESTATUS", "ID_OFICIO", EstatusOficiosOrdenesEnum.OFICIO_FIRMADO_ENVIADO_NYV.getIdEstatus(), EstatusOficiosOrdenesEnum.OFICIO_NOTIFICADO_CONTRIBUYENTE.getIdEstatus(), 0,0),
    PRORROGA("FECET_PRORROGA_ORDEN", "ID_ESTATUS", "ID_PRORROGA_ORDEN", EstatusProrroga.RESOLUCION_PRORROGA_FIRMADA_ENVIADA_NYV.getIdEstatus(), EstatusProrroga.PRORROGA_NOTIFICADA_CONTRIBUYENTE.getIdEstatus(), EstatusProrroga.PRORROGA_ERROR_NOTIFICAR_CONTRIBUYENTE.getIdEstatus(),0),
    PRORROGA_OFICIO("FECET_PRORROGA_OFICIO", "ID_ESTATUS", "ID_PRORROGA_OFICIO", EstatusProrroga.RESOLUCION_PRORROGA_FIRMADA_ENVIADA_NYV.getIdEstatus(), EstatusProrroga.PRORROGA_NOTIFICADA_CONTRIBUYENTE.getIdEstatus(), EstatusProrroga.PRORROGA_ERROR_NOTIFICAR_CONTRIBUYENTE.getIdEstatus(),0),
    PRUEBAS_PERICIALES("FECET_PRUEBAS_PERICIALES", "ID_ESTATUS", "ID_PRUEBAS_PERICIALES", EstatusPruebasPericiales.RESOLUCION_PRUEBAS_PERICIALES_FIRMADA_ENVIADA_NYV.getIdEstatus(), EstatusPruebasPericiales.PRUEBAS_PERICIALES_NOTIFICADA_CONTRIBUYENTE.getIdEstatus(), EstatusPruebasPericiales.PRUEBAS_PERICIALES_ERROR_NOTIFICAR_CONTRIBUYENTE.getIdEstatus(),0);

    private final String nombreTabla;
    private final String campoEstatus;
    private final String id;
    private final int estadoEnvioNotificacion;
    private final int estadoNotificacionContribuyente;
    private final int estadoErrorNotificacion;
    private final int estadoConcluido;

    private DescriptorNotificables(String nombreTabla, String campoEstatus, String id, int estadoEnvioNotificacion, int estadoNotificacionContribuyente, int estadoErrorNotificacion, int estadoConcluido) {
        this.nombreTabla = nombreTabla;
        this.campoEstatus = campoEstatus;
        this.id = id;
        this.estadoEnvioNotificacion = estadoEnvioNotificacion;
        this.estadoNotificacionContribuyente = estadoNotificacionContribuyente;
        this.estadoErrorNotificacion = estadoErrorNotificacion;
        this.estadoConcluido = estadoConcluido;
    }

    public String getNombreTabla() {
        return nombreTabla;
    }

    public String getCampoEstatus() {
        return campoEstatus;
    }

    public String getId() {
        return id;
    }

    public int getEstadoEnvioNotificacion() {
        return estadoEnvioNotificacion;
    }

    public int getEstadoNotificacionContribuyente() {
        return estadoNotificacionContribuyente;
    }

    public int getEstadoErrorNotificacion() {
        return estadoErrorNotificacion;
    }

	public int getEstadoConcluido() {
		return estadoConcluido;
	}

}
