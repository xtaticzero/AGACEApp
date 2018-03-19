package mx.gob.sat.siat.feagace.vista.propuestas.carga.helper;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAlegatoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficio;

public final class CargaDocumentosFunctionHelper {

    private CargaDocumentosFunctionHelper() {
    }

    public static boolean checarDuplicadoPruebasAlegato(String nombreArchivo, List<FecetAlegato> listaPruebasAlegatosCargadas) {
        boolean duplicado = false;
        if (listaPruebasAlegatosCargadas != null) {
            for (FecetAlegato alegato : listaPruebasAlegatosCargadas) {
                if (alegato.getNombreArchivo().equals(nombreArchivo)) {
                    duplicado = true;
                    break;
                }
            }
        }
        return duplicado;
    }

    public static boolean checarDuplicadoPruebasAlegatoOficio(String nombreArchivo, List<FecetAlegatoOficio> listaPruebasAlegatosOficioCargadas) {
        boolean duplicado = false;
        if (listaPruebasAlegatosOficioCargadas != null) {
            for (FecetAlegatoOficio alegatoOficio : listaPruebasAlegatosOficioCargadas) {
                if (alegatoOficio.getNombreArchivo().equals(nombreArchivo)) {
                    duplicado = true;
                    break;
                }
            }
        }
        return duplicado;
    }

    public static boolean validaArchivosCargados(List<FecetPromocion> listaPromocionesCargadas) {
        boolean boolArchivoCargado = false;

        if (listaPromocionesCargadas != null && !listaPromocionesCargadas.isEmpty()) {
            boolArchivoCargado = true;
        }

        return boolArchivoCargado;
    }

    public static boolean validaArchivosOficioCargados(List<FecetPromocionOficio> listaPromocionesCargadas) {
        boolean boolArchivoCargado = false;

        if (listaPromocionesCargadas != null && !listaPromocionesCargadas.isEmpty()) {
            boolArchivoCargado = true;
        }

        return boolArchivoCargado;
    }

    public static boolean validaProrrogasOrdenCargados(List<FecetDocProrrogaOrden> listaDocsProrrogasOrden) {
        boolean boolArchivoCargado = false;

        if (listaDocsProrrogasOrden != null && !listaDocsProrrogasOrden.isEmpty()) {
            boolArchivoCargado = true;
        }

        return boolArchivoCargado;
    }

    public static boolean checarDuplicadoProrrogas(String nombreArchivo, List<FecetDocProrrogaOrden> listaDocsProrrogasOrden) {
        boolean duplicado = false;
        if (listaDocsProrrogasOrden != null) {
            for (FecetDocProrrogaOrden prorroga : listaDocsProrrogasOrden) {
                if (prorroga.getNombreArchivo().equals(nombreArchivo)) {
                    duplicado = true;
                    break;
                }
            }
        }
        return duplicado;
    }

    public static boolean checarDuplicadoProrrogasOficio(String nombreArchivo, List<FecetDocProrrogaOficio> listaDocsProrrogasOficio) {
        boolean duplicado = false;
        if (listaDocsProrrogasOficio != null) {
            for (FecetDocProrrogaOficio prorrogaOficio : listaDocsProrrogasOficio) {
                if (prorrogaOficio.getNombreArchivo().equals(nombreArchivo)) {
                    duplicado = true;
                    break;
                }
            }
        }
        return duplicado;
    }

    public static boolean validaProrrogasOficioCargados(List<FecetDocProrrogaOficio> listaDocsProrrogasOficio) {
        boolean boolArchivoCargado = false;
        if (!listaDocsProrrogasOficio.isEmpty()) {
            boolArchivoCargado = true;
        }
        return boolArchivoCargado;
    }

    public static boolean validaListaPruebasAlegatosCargadas(List<FecetAlegato> lista) {
        return lista == null;
    }

    public static boolean validaLista(List lista) {
        return lista == null;
    }

}
