/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.util.constantes;

import static mx.gob.sat.siat.feagace.modelo.util.Constantes.DIAGONAL;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class RutaArchivosUtilOrdenes extends RutaArchivosUtil {

    public static String armarRutaDestinoAvisoCircunstancial(final AgaceOrden orden, final FecetOficio oficio) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_ORDENES);

        rutaDestino.append(orden.getNumeroOrden().replaceAll(DIAGONAL, "").toUpperCase()).append(DIAGONAL);
        rutaDestino.append("SAT/avisoCircunstancial/").append(oficio.getIdOficio()).append(DIAGONAL);

        return rutaDestino.toString();
    }

}
