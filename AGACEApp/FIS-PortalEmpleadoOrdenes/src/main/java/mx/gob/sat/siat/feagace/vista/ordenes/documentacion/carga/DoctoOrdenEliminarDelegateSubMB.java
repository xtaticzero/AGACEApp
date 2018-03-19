package mx.gob.sat.siat.feagace.vista.ordenes.documentacion.carga;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.SolicitudContribuyenteAnexoVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;

public class DoctoOrdenEliminarDelegateSubMB extends DoctoOrdenEliminarDelegateMB {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public void eliminarAnexoOficioConclusionRevision() {
        eliminarAnexoOficioConclusionRevision(getLstOficioAnexoHelper().getListaAnexosConclusionRevision());
    }

    public void eliminarAnexoOficioConclusionRevision(final List<FecetOficioAnexos> listaAnexos) {
        for (Iterator<FecetOficioAnexos> iter = listaAnexos.iterator(); iter.hasNext();) {
            final FecetOficioAnexos anexo = iter.next();
            if (anexo == getDtoHelper().getAnexoSeleccionado()) {
                iter.remove();
            }
        }
    }

    public void eliminarAnexoOficioResolucionDefinitiva() {
        eliminarAnexoOficioCancelacionOrden(getLstOficioAnexoHelper().getListaAnexosResolucionDefinitiva());
    }
    
    public void eliminarAnexoOficioPruebasDesahogo() {
        eliminarAnexoOficioCancelacionOrden(getLstOficioAnexoHelper().getListaAnexosPruebasDesahogo());
    }

    public void eliminarAnexoOficioResolucionDefinitiva(final List<FecetOficioAnexos> listaAnexos) {
        for (Iterator<FecetOficioAnexos> iter = listaAnexos.iterator(); iter.hasNext();) {
            final FecetOficioAnexos anexo = iter.next();
            if (anexo == getDtoHelper().getAnexoSeleccionado()) {
                iter.remove();
            }
        }
    }

    public void eliminarAnexoOficioCambioMetodoUCAMCA() {
        eliminarDocumentoIdenticoAnexosLista(getLstOficioAnexoHelper().getListaAnexosCambioMetodoUCAMCA());
    }

    public void eliminarAnexoConObservaciones() {
        eliminarDocumentoIdenticoAnexosLista(getLstOficioAnexoHelper().getListaAnexosConObservaciones());
    }

    public void eliminarAnexoRequerimientoReincidencia() {
        eliminarDocumentoIdenticoAnexosLista(getLstOficioAnexoHelper().getListaAnexosRequerimientoReincidencia());
    }

    public void eliminarListaAnexoProrrogaOrden() {
        getLstHelper().setListaAnexosProrrogaOrden(new ArrayList<FecetAnexosProrrogaOrden>());
    }
    
    public void eliminarListaResolucionProrrogaOrden() {
        getLstHelper().setListaResolucionProrrogaOrden(new ArrayList<FecetAnexosProrrogaOrden>());
        getLstHelper().setListaResolucionProrrogaOrdenRechazo(new ArrayList<FecetAnexosProrrogaOrden>());
        eliminarListaAnexoProrrogaOrden();
        eliminarListaAnexosProrrogaOrdenRechazo();
    }

    public void eliminarListaAnexosProrrogaOrdenRechazo() {
        getLstHelper().setListaAnexosProrrogaOrdenRechazo(new ArrayList<FecetAnexosProrrogaOrden>());
    }

    public void eliminarAnexoProrrogaOrden() {
        eliminarDocumentoIdenticoAnexosListaProrrogaOrden(getLstHelper().getListaAnexosProrrogaOrden());
    }

    public void eliminarDocumentoIdenticoAnexosListaProrrogaOrden(final List<FecetAnexosProrrogaOrden> listaAnexos) {
        for (Iterator<FecetAnexosProrrogaOrden> iter = listaAnexos.iterator(); iter.hasNext();) {
            final FecetAnexosProrrogaOrden anexo = iter.next();
            if (anexo == getDtoHelper().getAnexoProrrogaOrdenSeleccionado()) {
                iter.remove();
            }
        }
    }
    
    public void eliminarAnexoSolicitudContr() {
        for (Iterator<SolicitudContribuyenteAnexoVO> iter = getLstHelper().getListaSolicitudContribuyenteAnexoVO().iterator(); iter.hasNext();) {
            final SolicitudContribuyenteAnexoVO anexo = iter.next();
            if (anexo == getDtoHelper().getSolicitudContribuyenteAnexoSeleccionado()) {
                iter.remove();
            }
        }
    }

    public void eliminarResolucionProrrogaOrden() {
        eliminarDocumentoIdenticoAnexosListaProrrogaOrden(getLstHelper().getListaResolucionProrrogaOrden());
    }
    
    public void eliminarDocumentoSolicitudContribuyenteResolucion() {
        for (Iterator<SolicitudContribuyenteAnexoVO> iter = getLstHelper().getListaSolicitudContribuyenteResolucionVO().iterator(); iter.hasNext();) {
            final SolicitudContribuyenteAnexoVO anexo = iter.next();
            if (anexo == getDtoHelper().getSolicitudContribuyenteResolucionSeleccionado()) {
                iter.remove();
            }
        }
    }
    
    public void eliminarResolucionSolicitudContribuyente(){
        for (Iterator<SolicitudContribuyenteAnexoVO> iter = getLstHelper().getListaSolicitudContribuyenteAnexoVO().iterator(); iter.hasNext();) {
            final SolicitudContribuyenteAnexoVO anexo = iter.next();
            if (anexo == getDtoHelper().getSolicitudContribuyenteAnexoSeleccionado()) {
                iter.remove();
            }
        }
    }

    public void eliminarDocumentoIdenticoResolucionListaProrrogaOrden(final List<FecetAnexosProrrogaOrden> listaAnexos) {
        for (Iterator<FecetAnexosProrrogaOrden> iter = listaAnexos.iterator(); iter.hasNext();) {
            final FecetAnexosProrrogaOrden anexo = iter.next();
            if (anexo == getDtoHelper().getAnexoProrrogaOrdenSeleccionado()) {
                iter.remove();
            }
        }
    }

    public void eliminarResolucionProrrogaOrdenRechazo() {
        eliminarResolucionListaProrrogaOrdenRechazo(getLstHelper().getListaResolucionProrrogaOrdenRechazo());
    }

    public void eliminarResolucionListaProrrogaOrdenRechazo(final List<FecetAnexosProrrogaOrden> listaAnexos) {
        for (Iterator<FecetAnexosProrrogaOrden> iter = listaAnexos.iterator(); iter.hasNext();) {
            final FecetAnexosProrrogaOrden anexo = iter.next();
            if (anexo == getDtoHelper().getAnexoProrrogaOrdenSeleccionadoRechazo()) {
                iter.remove();
            }
        }
    }
    
    public void eliminarResolucionListaSolicitudContribuyenteRechazo() {
        for (Iterator<SolicitudContribuyenteAnexoVO> iter = getLstHelper().getListaSolicitudContribuyenteResolucionRechazoVO().iterator(); iter.hasNext();) {
            final SolicitudContribuyenteAnexoVO anexo = iter.next();
            if (anexo == getDtoHelper().getSolicitudContribuyenteResolucionRechazoSeleccionado()) {
                iter.remove();
            }
        }
    }
    
    

    public void eliminarAnexoProrrogaOrdenRechazo() {
        eliminarDocumentoIdenticoAnexosListaProrrogaOrdenRechazo(getLstHelper().getListaAnexosProrrogaOrdenRechazo());
    }

    public void eliminarDocumentoIdenticoAnexosListaProrrogaOrdenRechazo(final List<FecetAnexosProrrogaOrden> listaAnexos) {
        for (Iterator<FecetAnexosProrrogaOrden> iter = listaAnexos.iterator(); iter.hasNext();) {
            final FecetAnexosProrrogaOrden anexo = iter.next();
            if (anexo == getDtoHelper().getAnexoProrrogaOrdenSeleccionadoRechazo()) {
                iter.remove();
            }
        }
    }
    
    public void eliminarAnexoAvisoCircunstancial() {
        eliminarDocumentoIdenticoAnexosLista(getLstOficioAnexoHelper().getListaAnexosAvisoCircunstancial());
    }
    
    public void eliminarListaResolucionSolicitudContrVO() {
        getLstHelper().setListaSolicitudContribuyenteResolucionVO(new ArrayList<SolicitudContribuyenteAnexoVO>());
        getLstHelper().setListaSolicitudContribuyenteAnexoVO(new ArrayList<SolicitudContribuyenteAnexoVO>());
        getAttributeHelper().setJustificacion("");
    }
    
    public void eliminarListaAnexoSolicitudContrVO() {
        getLstHelper().setListaSolicitudContribuyenteAnexoVO(new ArrayList<SolicitudContribuyenteAnexoVO>());
    }
    
    public void eliminarAnexoListaSolicitudContribuyenteRechazo() {
        for (Iterator<SolicitudContribuyenteAnexoVO> iter = getLstHelper().getListaSolicitudContribuyenteAnexoRechazoVO().iterator(); iter.hasNext();) {
            final SolicitudContribuyenteAnexoVO anexo = iter.next();
            if (anexo == getDtoHelper().getSolicitudContribuyenteAnexoRechazoSeleccionado()) {
                iter.remove();
            }
        }
    }
    
    public void eliminaDocAprobarSolicitudContribuyente(){
        getLstHelper().setListaSolicitudContribuyenteResolucionVO(new ArrayList<SolicitudContribuyenteAnexoVO>());
        getLstHelper().setListaSolicitudContribuyenteAnexoVO(new ArrayList<SolicitudContribuyenteAnexoVO>());
        getAttributeHelper().setJustificacion("");
    }
    
    public void eliminaDocRechazarSolicitudContribuyente(){
        getLstHelper().setListaSolicitudContribuyenteResolucionRechazoVO(new ArrayList<SolicitudContribuyenteAnexoVO>());
        getLstHelper().setListaSolicitudContribuyenteAnexoRechazoVO(new ArrayList<SolicitudContribuyenteAnexoVO>());
        getAttributeHelper().setJustificacionRechazo("");
    }

}
