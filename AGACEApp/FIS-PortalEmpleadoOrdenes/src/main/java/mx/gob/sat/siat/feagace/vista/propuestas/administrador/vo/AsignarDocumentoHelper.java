package mx.gob.sat.siat.feagace.vista.propuestas.administrador.vo;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececEmpleado;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

import org.primefaces.model.UploadedFile;

public abstract class AsignarDocumentoHelper {
    
    private static final long N_4196000L = 4194304L;
    
    public static List<PropuestaVO> llenarListaDeDto(List<FecetPropuesta> lista){
        List<PropuestaVO> listaProps = new ArrayList<PropuestaVO>();
        
        for(FecetPropuesta fecProp : lista){
            PropuestaVO prop = new PropuestaVO();
            prop.setIdPropuesta(fecProp.getIdPropuesta());
            prop.setIdRegistro(fecProp.getIdRegistro());
            prop.setMetodo(fecProp.getIdMetodo().toString());
            prop.setPrioridad(fecProp.getPrioridad().toString());
            prop.setSubprograma(fecProp.getFececSubprograma()!=null?fecProp.getFececSubprograma().getClave().concat("-").concat(fecProp.getFececSubprograma().getDescripcion()):"");
            prop.setMetodo(fecProp.getFeceaMetodo()!=null?fecProp.getFeceaMetodo().getAbreviatura():"");
            prop.setRfcContribuyente(fecProp.getFecetContribuyente()!=null?fecProp.getFecetContribuyente().getRfc():"");
            prop.setIdContribuyente(fecProp.getIdContribuyente());
            
            listaProps.add(prop);
            
        }
        return listaProps;
    }
    
    public static List<SubAdministradorVO> obtenerSubAministradores(List<FececEmpleado> listaSubs){
        
        List<SubAdministradorVO> listaSubAdmin = new ArrayList<SubAdministradorVO>();
        for(FececEmpleado empleado: listaSubs){
            SubAdministradorVO sub = new SubAdministradorVO();
            sub.setIdSubAdmin(empleado.getIdEmpleado());
            sub.setNombre(empleado.getNombre());
            sub.setRfc(empleado.getRfc());
            sub.setCorreo(empleado.getCorreo());
            
            listaSubAdmin.add(sub);
        }
        
        return listaSubAdmin;
    }
    
    public static String crearMensaje1(int numeroRegs, String nombre){
        String mensaje = FacesUtil.getMessageResourceString("mensaje.dialog.msg1");        
        
        return String.format(mensaje, numeroRegs, nombre);
    }
    
    public static String crearMensaje2(int numeroRegs, String nombre){
        String mensaje = FacesUtil.getMessageResourceString("mensaje.dialog.msg2");        
        
        return String.format(mensaje, numeroRegs, nombre);
    }
    
    public static String crearMensajeAprobacion1(String idRegistro){
        String mensaje = FacesUtil.getMessageResourceString("mensaje.dialog.aprobar.msg1");        
        
        return String.format(mensaje, idRegistro);
    }
    
    public static String crearMensajeAprobacion2(String idRegistro){
        String mensaje = FacesUtil.getMessageResourceString("mensaje.dialog.aprobar.msg2");        
        
        return String.format(mensaje, idRegistro);
    }
    public static String crearMensajeRechazo1(){
        String mensaje = FacesUtil.getMessageResourceString("mensaje.dialog.rechazar.msg1");        
        
        return mensaje;
    }
    public static String crearMensajeRechazo2(){
        String mensaje = FacesUtil.getMessageResourceString("mensaje.dialog.rechazar.msg2");        
        
        return mensaje;
    }
    
    public static SubAdministradorVO obtenerSubAdmin(BigDecimal idSub, List<SubAdministradorVO> listaSubs){
        SubAdministradorVO subAdministrador = null;
        for(SubAdministradorVO sub: listaSubs){
            if(sub.getIdSubAdmin().longValue() == idSub.longValue()){
                subAdministrador = sub;
            }
        }
        return subAdministrador;
        
    }
    
    public static boolean validaNombreArchivo(List<FecetRechazoPropuesta> listaRechazo, String nombre) {
        for (FecetRechazoPropuesta rechazoPropuesta : listaRechazo) {
            if (rechazoPropuesta.getNombreArchivo().equals(nombre)) {
                return true;
            }
        }

        return false;
    }
    
    public static Boolean validaTamanoArchivo(final UploadedFile archivo) {
        if (archivo.getSize() > 0L && archivo.getSize() <= N_4196000L) {
            return true;
        } else {
            if (archivo.getSize() >= N_4196000L) {
                FacesUtil.addErrorMessage(null, "Error al cargar el archivo.",
                                "El archivo es demasiado grande, lo m\u00e1ximo permitido son 4MB.");
            } else {
                FacesUtil.addErrorMessage(null, "Error al cargar el archivo.",
                        "El archivo es demasiado chico");
            }
        }

        return false;
    }
    
    public static Boolean validaArchivoCargaInsumoPropuesta(final UploadedFile archivo) {
        if (archivo.getFileName()
                .endsWith(Constantes.ARCHIVO_WORD_DESPUES_2007)
                || archivo.getFileName().endsWith(Constantes.ARCHIVO_PDF)
                || archivo.getFileName().endsWith(
                        Constantes.ARCHIVO_EXCEL_DESPUES_2007)) {

            if (validaTamanoArchivo(archivo)) {
                return true;
            }
        } else {
            FacesUtil
                    .addErrorMessage(null, "Archivo invalido",
                            "Solo se aceptan archivos WORD, EXCEL o PDF versi\u00f3n 2007 o superior");
        }

        return false;
    }
}
