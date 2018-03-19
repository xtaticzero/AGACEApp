package mx.gob.sat.siat.feagace.vista.util;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


public final class ReportesArchivosUtil{
    private static Logger logger = Logger.getLogger(ReportesArchivosUtil.class);
    private static final String NOARCHIVO = ".svn";
    private static final String README = "readme";
    private ReportesArchivosUtil() {
    }
    
    public static void eliminarArchivo(String path) {
        logger.debug("Eliminar: " + path);
        try{
            File fichero = new File(path);
            String rutaArchivo=path;
            String[] lista = fichero.list();
            if (lista != null){
                for (int x=0;x<lista.length;x++){
                    if(lista[x].indexOf(NOARCHIVO)==-1 && lista[x].indexOf(README)==-1){
                        File archivo = new File(rutaArchivo + lista[x]);
                        if (archivo.delete()){
                           logger.info("Archivo eliminado: " + archivo.getName());
                        }else{
                           logger.info("El archivo no puede eliminado " + archivo.getName());
                        }
                    }
                }              
            }
        } catch (Exception e) { 
            logger.error("Metodo eliminarArchivo: [{}]",e);
        }
    }
    
    public static List<String> listarArchivos(String path){
        logger.debug("Lista: " + path);
        List<String> listaArchivos =new ArrayList<String>();
        try{
            File fichero = new File(path); 
            String[] lista = fichero.list();
            if (lista != null){
                for (int x=0;x<lista.length;x++){
                    if(lista[x].indexOf(NOARCHIVO)==-1 && lista[x].indexOf(README)==-1){
                        listaArchivos.add(lista[x]);
                    }
                    
                }
            }
        } catch (Exception e) { 
            logger.error("Metodo listarArchivos: [{}]",e);
        }
        return listaArchivos;
    }
}
