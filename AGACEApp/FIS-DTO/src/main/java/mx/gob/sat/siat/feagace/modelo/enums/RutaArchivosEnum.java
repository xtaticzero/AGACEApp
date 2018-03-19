/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.enums;

import mx.gob.sat.siat.feagace.modelo.RutaUtil;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public enum RutaArchivosEnum {
    RUTA_DOCUMENTOS_EXPEDIENTE_PROPUESTAS("/fece/archivosExpedientesPropuestas/"),
    RUTA_DOCUMENTOS_RECHAZO_PROPUESTAS("/fece/archivosExpedientesPropuestas/RechazosPropuesta/"),
    RUTA_DOCUMENTOS_TRANSFERIDAS_PROPUESTAS("/fece/archivosExpedientesPropuestas/PropuestaTransferidas/"),
    RUTA_DOCUMENTOS_RETROALIMENTACION_PROPUESTAS("/fece/archivosExpedientesPropuestas/PropuestaRetroalimentacion/"),
	RUTA_DOCUMENTOS_PENDIENTE_PROPUESTAS("/fece/archivosExpedientesPropuestas/PropuestaPendiente/"),
    RUTA_DOCUMENTOS_CANCELACION_PROPUESTAS("/fece/archivosExpedientesPropuestas/PropuestaCancelada/"),
    RUTA_DOCUMENTOS_PAPELES_TRABAJO_PROPUESTA("/fece/archivosExpedientesPropuestas/PapelesTrabajo/"),
    RUTA_DOCUMENTOS_PAPELES_TRABAJO_ORDENES("/fece/archivosCargadosOrdenes/"),
    RUTA_DOCUMENTOS_RL_CONTRIBUYENTE("/fece/archivosContribuyente/"),
	RUTA_DOCUMENTOS_AA_CONTRIBUYENTE("/fece/archivosContribuyente/");
	
    private final String pathFile;
    

    private RutaArchivosEnum(String pathFile) {
        this.pathFile = RutaUtil.getOrigenRuta().concat(pathFile);
    }

    public String getPathFile() {
        return pathFile;
    }
}
