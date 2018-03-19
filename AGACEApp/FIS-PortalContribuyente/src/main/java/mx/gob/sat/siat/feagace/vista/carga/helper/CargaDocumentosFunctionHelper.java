package mx.gob.sat.siat.feagace.vista.carga.helper;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAlegatoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocPruebasPericiales;
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
        return listaPromocionesCargadas != null && !listaPromocionesCargadas.isEmpty();
    }

    public static boolean validaArchivosOficioCargados(List<FecetPromocionOficio> listaPromocionesCargadas) {
        return listaPromocionesCargadas != null && !listaPromocionesCargadas.isEmpty();
    }

    public static boolean validaProrrogasOrdenCargados(List<FecetDocProrrogaOrden> listaDocsProrrogasOrden) {
        return listaDocsProrrogasOrden != null && !listaDocsProrrogasOrden.isEmpty();

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
        return listaDocsProrrogasOficio != null && !listaDocsProrrogasOficio.isEmpty();
    }

    public static boolean validaListaPruebasAlegatosCargadas(List<FecetAlegato> lista) {
        return lista == null;
    }

    public static boolean validaLista(List<?> lista) {
        return lista == null;
    }

    public static boolean checarDuplicadoPruebasPericiales(String nombreArchivo, List<FecetDocPruebasPericiales> listaDocsPruebasPericiales) {
        boolean duplicado = false;
        if (listaDocsPruebasPericiales != null) {
            for (FecetDocPruebasPericiales pruebaPericial : listaDocsPruebasPericiales) {
                if (pruebaPericial.getNombreArchivo().equals(nombreArchivo)) {
                    duplicado = true;
                    break;
                }
            }
        }
        return duplicado;
    }

    public static boolean validaPruebasPericialesCargados(List<FecetDocPruebasPericiales> listaDocsPruebasPericiales) {
        return listaDocsPruebasPericiales != null && !listaDocsPruebasPericiales.isEmpty();
    }
}
