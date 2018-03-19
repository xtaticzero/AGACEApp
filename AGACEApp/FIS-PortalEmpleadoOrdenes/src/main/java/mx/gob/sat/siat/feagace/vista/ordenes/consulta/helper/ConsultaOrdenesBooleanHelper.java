package mx.gob.sat.siat.feagace.vista.ordenes.consulta.helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.DetalleEmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilOrdenes;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtilOrdenes;
import mx.gob.sat.siat.feagace.negocio.util.validacion.ValidacionParametrosUtil;
import mx.gob.sat.siat.feagace.vista.model.OrdenConsultaDTO;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class ConsultaOrdenesBooleanHelper implements Serializable {
    
    private static final long serialVersionUID = -4106414519265981409L;
    
    private boolean flgMostrarUnidadesDesahogo;
    private boolean flgMostrarTlbCategorias;
    private boolean flgMostrarTlbSubordinados;
    private boolean flgMostrarTlbSubordinadoAuditor;
    private boolean flgMostrarTlbOrdenes;
    private boolean flgMostrarTlbMetodo;
    private boolean flgMostrarTlbEstatus;
    private boolean flgMostrarTlbCifras;
    private boolean flgMostrarDetallePropuesta;
    private boolean flgMostrarDetalleOrden;
    private boolean flgMostrarEstatusSemaforo;
    private boolean flgPaginaEstatusSemaforos;
    private boolean flgMostrarComboRoles;
    private boolean flgRegresarASubordinado;
    private boolean flgPantallaGrupoEstatus;
    private boolean esRedirect;
    private boolean flgVienePropOrdenes;
    
    public boolean isFlgMostrarUnidadesDesahogo() {
        return flgMostrarUnidadesDesahogo;
    }
    
    public void setFlgMostrarUnidadesDesahogo(boolean flgMostrarUnidadesDesahogo) {
        this.flgMostrarUnidadesDesahogo = flgMostrarUnidadesDesahogo;
    }
    
    public boolean isFlgMostrarTlbCategorias() {
        return flgMostrarTlbCategorias;
    }
    
    public void setFlgMostrarTlbCategorias(boolean flgMostrarTlbCategorias) {
        this.flgMostrarTlbCategorias = flgMostrarTlbCategorias;
    }
    
    public boolean isFlgMostrarTlbSubordinados() {
        return flgMostrarTlbSubordinados;
    }
    
    public void setFlgMostrarTlbSubordinados(boolean flgMostrarTlbSubordinados) {
        this.flgMostrarTlbSubordinados = flgMostrarTlbSubordinados;
    }
    
    public boolean isFlgMostrarTlbSubordinadoAuditor() {
        return flgMostrarTlbSubordinadoAuditor;
    }
    
    public void setFlgMostrarTlbSubordinadoAuditor(boolean flgMostrarTlbSubordinadoAuditor) {
        this.flgMostrarTlbSubordinadoAuditor = flgMostrarTlbSubordinadoAuditor;
    }
    
    public boolean isFlgMostrarTlbOrdenes() {
        return flgMostrarTlbOrdenes;
    }
    
    public void setFlgMostrarTlbOrdenes(boolean flgMostrarTlbOrdenes) {
        this.flgMostrarTlbOrdenes = flgMostrarTlbOrdenes;
    }
    
    public boolean isFlgMostrarTlbMetodo() {
        return flgMostrarTlbMetodo;
    }
    
    public void setFlgMostrarTlbMetodo(boolean flgMostrarTlbMetodo) {
        this.flgMostrarTlbMetodo = flgMostrarTlbMetodo;
    }
    
    public boolean isFlgMostrarTlbEstatus() {
        return flgMostrarTlbEstatus;
    }
    
    public void setFlgMostrarTlbEstatus(boolean flgMostrarTlbEstatus) {
        this.flgMostrarTlbEstatus = flgMostrarTlbEstatus;
    }
    
    public boolean isFlgMostrarTlbCifras() {
        return flgMostrarTlbCifras;
    }
    
    public void setFlgMostrarTlbCifras(boolean flgMostrarTlbCifras) {
        this.flgMostrarTlbCifras = flgMostrarTlbCifras;
    }
    
    public boolean isFlgMostrarDetallePropuesta() {
        return flgMostrarDetallePropuesta;
    }
    
    public void setFlgMostrarDetallePropuesta(boolean flgMostrarDetallePropuesta) {
        this.flgMostrarDetallePropuesta = flgMostrarDetallePropuesta;
    }
    
    public boolean isFlgMostrarDetalleOrden() {
        return flgMostrarDetalleOrden;
    }
    
    public void setFlgMostrarDetalleOrden(boolean flgMostrarDetalleOrden) {
        this.flgMostrarDetalleOrden = flgMostrarDetalleOrden;
    }
    
    public boolean isFlgMostrarEstatusSemaforo() {
        return flgMostrarEstatusSemaforo;
    }
    
    public void setFlgMostrarEstatusSemaforo(boolean flgMostrarEstatusSemaforo) {
        this.flgMostrarEstatusSemaforo = flgMostrarEstatusSemaforo;
    }
    
    public boolean isFlgPaginaEstatusSemaforos() {
        return flgPaginaEstatusSemaforos;
    }
    
    public void setFlgPaginaEstatusSemaforos(boolean flgPaginaEstatusSemaforos) {
        this.flgPaginaEstatusSemaforos = flgPaginaEstatusSemaforos;
    }
    
    public boolean isFlgMostrarComboRoles() {
        return flgMostrarComboRoles;
    }
    
    public void setFlgMostrarComboRoles(boolean flgMostrarComboRoles) {
        this.flgMostrarComboRoles = flgMostrarComboRoles;
    }
    
    public boolean isFlgRegresarASubordinado() {
        return flgRegresarASubordinado;
    }
    
    public void setFlgRegresarASubordinado(boolean flgRegresarASubordinado) {
        this.flgRegresarASubordinado = flgRegresarASubordinado;
    }
    
    public boolean isFlgPantallaGrupoEstatus() {
        return flgPantallaGrupoEstatus;
    }
    
    public void setFlgPantallaGrupoEstatus(boolean flgPantallaGrupoEstatus) {
        this.flgPantallaGrupoEstatus = flgPantallaGrupoEstatus;
    }
    
    public boolean isEsRedirect() {
        return esRedirect;
    }
    
    public void setEsRedirect(boolean esRedirect) {
        this.esRedirect = esRedirect;
    }
    
    public boolean isFlgVienePropOrdenes() {
        return flgVienePropOrdenes;
    }
    
    public void setFlgVienePropOrdenes(boolean flgVienePropOrdenes) {
        this.flgVienePropOrdenes = flgVienePropOrdenes;
    }
    
    public StreamedContent getArchivoDescarga(final String url, final String nombreDescarga) {
        StreamedContent archivo = null;
        if (url != null && nombreDescarga != null) {
            try {
                archivo = new DefaultStreamedContent(new FileInputStream(CargaArchivoUtilOrdenes.limpiarPathArchivo(url)),
                        CargaArchivoUtilOrdenes.obtenContentTypeArchivo(nombreDescarga), CargaArchivoUtilOrdenes.aplicarCodificacionTexto(nombreDescarga));
            } catch (FileNotFoundException e) {
                FacesUtil.addErrorMessage(null, "No se encontro el documento seleccionado", "");
            }
        } else {
            FacesUtil.addErrorMessage(null, "No se encontro el documento seleccionado", "");
        }
        return archivo;
    }

    public OrdenConsultaDTO convertToOrdenConsulta(AgaceOrden agaceOrden) {
        OrdenConsultaDTO ordenConsultaDTO = new OrdenConsultaDTO();
        ordenConsultaDTO.setIdOrden(agaceOrden.getIdOrden());
        ordenConsultaDTO.setIdPropuesta(agaceOrden.getIdPropuesta());
        ordenConsultaDTO.setIdRegistro(agaceOrden.getIdRegistroPropuesta());
        ordenConsultaDTO.setNumeroOrden(agaceOrden.getNumeroOrden());
        ordenConsultaDTO.setRutaArchivo(RutaArchivosUtilOrdenes.armarRutaDocOrden(agaceOrden));
        ordenConsultaDTO.setNombreArchivo(CargaArchivoUtilOrdenes.getNombreArchivoOrdenPdf(agaceOrden));
        ordenConsultaDTO.setIdMetodo(agaceOrden.getIdMetodo());
        ordenConsultaDTO.setMetodo(TipoMetodoEnum.getById(agaceOrden.getIdMetodo().longValue()).getDescripcion());
        ordenConsultaDTO.setMetodoSiglas(TipoMetodoEnum.getById(agaceOrden.getIdMetodo().longValue()).getSiglas());
        ordenConsultaDTO.setRfc(agaceOrden.getFecetContribuyente().getRfc());
        ordenConsultaDTO.setNombreContribuyente(agaceOrden.getFecetContribuyente().getNombre());
        ordenConsultaDTO.setEstatus(agaceOrden.getFececEstatus().getDescripcion());
        ordenConsultaDTO.setPlazoRestante(agaceOrden.getDiasRestantesPlazo());
        ordenConsultaDTO.setDescripcionPlazoRestante(agaceOrden.getDescripcionPlazoRestante());
        ordenConsultaDTO.setIdFirmante(agaceOrden.getIdFirmante());
        ordenConsultaDTO.setIdAuditor(agaceOrden.getIdAuditor());
        ordenConsultaDTO.setIdContribuyente(agaceOrden.getIdContribuyente());
        return ordenConsultaDTO;
    }
    
    public List<TipoEmpleadoEnum> numeroRolesValidos(List<DetalleEmpleadoDTO> lstDetalleEmpleado) {
        List<TipoEmpleadoEnum> rolesValidos = new ArrayList<TipoEmpleadoEnum>();
        for (DetalleEmpleadoDTO detalle : lstDetalleEmpleado) {
            if (ValidacionParametrosUtil.seCumpleAlgunaCondicion(new boolean[] {
                    detalle.getTipoEmpleado().getBigId().equals(TipoEmpleadoEnum.CENTRAL.getBigId()),
                    detalle.getTipoEmpleado().getBigId().equals(TipoEmpleadoEnum.FIRMANTE.getBigId()),
                    detalle.getTipoEmpleado().getBigId().equals(TipoEmpleadoEnum.AUDITOR.getBigId()) })) {
                rolesValidos.add(detalle.getTipoEmpleado());
            }
        }
        return rolesValidos;
    }
    
}
