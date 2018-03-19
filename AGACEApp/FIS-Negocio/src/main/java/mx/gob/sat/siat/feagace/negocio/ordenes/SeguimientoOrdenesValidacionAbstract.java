/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.ordenes;

import java.math.BigDecimal;
import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FecetContribuyenteDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetAsociadoDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetCompulsasDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetOficioAnexosDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetProrrogaOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetProrrogaOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetPruebasPericialesDao;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexoPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.negocio.bo.ordenes.rules.ReglasNegocioOrdenesBO;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class SeguimientoOrdenesValidacionAbstract extends ServicioOrdenesAbstract {

    @Autowired
    private transient FecetOficioDao fecetOficioDao;
    @Autowired
    private transient FecetOficioAnexosDao fecetOficioAnexosDao;
    @Autowired
    private transient FecetContribuyenteDao fecetContribuyenteDao;
    @Autowired
    private transient FecetAsociadoDao fecetAsociadoDao;
    @Autowired
    private transient FecetCompulsasDao fecetCompulsasDao;
    @Autowired
    private transient FecetProrrogaOrdenDao fecetProrrogaOrdenDao;
    @Autowired
    private transient FecetProrrogaOficioDao fecetProrrogaOficioDao;
    @Autowired
    private transient FecetPruebasPericialesDao fecetPruebasPericialesDao;

    private static final long serialVersionUID = -8792756586168746120L;

    public static Boolean validaStringNullVacio(final String texto) {
        return (texto == null || texto.isEmpty());
    }

    public void validaCargaSegundoRequerimiento(final List<FecetOficio> listaOficio) throws NegocioException {
        if (listaOficio.isEmpty()) {
            throw new NegocioException("Es obligatorio que adjunte el Oficio de Segundo Requerimiento solicitado");
        }
    }

    public void validaCargaImposicionMulta(ReglasNegocioOrdenesBO reglasNegocioOrdenesBO) throws NegocioException {
        if (!reglasNegocioOrdenesBO.getListaAnexosDependiente1().isEmpty()
                && reglasNegocioOrdenesBO.getListaOfDependiente1().isEmpty()) {
            throw new NegocioException("No se cargo el oficio de Multa");
        }
    }

    public void validaCargaSuspencionPadron(final ReglasNegocioOrdenesBO reglasNegocioOrdenesBO) throws NegocioException {
        if (!reglasNegocioOrdenesBO.getListaAnexosDependiente2().isEmpty()
                && reglasNegocioOrdenesBO.getListaOfDependiente2().isEmpty()) {
            throw new NegocioException("No se cargo el oficio de Suspencion en Padron de Importadores y Exportadores");
        }
    }

    public void validaCargaResolucionDefinitiva(final List<FecetOficio> listaOficio) throws NegocioException {
        if (listaOficio.isEmpty()) {
            throw new NegocioException("No se cargo el oficio de Resolucion Definitiva");
        }
    }

    public void validaCargaResolucionDefinitivaObservaciones(final BigDecimal idOrden) throws NegocioException {

        if (fecetOficioDao.getOficioObservaciones(idOrden) == null) {
            throw new NegocioException("Su solicitud no es procedente, ya que el sistema no tiene registrado el oficio con Observaciones");
        }
    }

    public void validaCargaPruebasPericiales(final List<FecetOficio> listaOficio) throws NegocioException {
        if (listaOficio.isEmpty()) {
            throw new NegocioException("No se cargo el oficio de Pruebas Periciales");
        }
    }

    public void validaCargaAvisoContribuyente(ReglasNegocioOrdenesBO reglasNegocioOrdenesBO) throws NegocioException {
        if (!reglasNegocioOrdenesBO.getListaAnexosDependiente1().isEmpty()
                && reglasNegocioOrdenesBO.getListaOfDependiente1().isEmpty()) {
            throw new NegocioException("No se cargo el oficio de Aviso al Contribuyente");
        }
    }

    public void validaCargaAvisoContribuyente(final List<FecetOficio> listaOficio) throws NegocioException {
        if (listaOficio.isEmpty()) {
            throw new NegocioException("No se cargo el oficio de Aviso al Contribuyente");
        }
    }

    public void validaCargaPruebasDesahogo(final List<FecetOficio> listaOficio) throws NegocioException {
        if (listaOficio.isEmpty()) {
            throw new NegocioException("No se cargo el oficio de Pruebas de Desahogo");
        }
    }

    public void validaCargaConclusionRevision(final List<FecetOficio> listaOficio) throws NegocioException {
        if (listaOficio.isEmpty()) {
            throw new NegocioException("No se cargo el oficio de Conclusion de Revision de Escrito por Imposibilidad Material para su Desahogo");
        }
    }

    public void validaCargaCancelacionOrden(final List<FecetOficio> listaOficio) throws NegocioException {
        if (listaOficio.isEmpty()) {
            throw new NegocioException("No se cargo el oficio de Cancelacion de la Orden");
        }
    }

    public void validaCargaSinObservaciones(final List<FecetOficio> listaOficio) throws NegocioException {
        if (listaOficio.isEmpty()) {
            throw new NegocioException("No se cargo el oficio de Conclusion sin Observaciones");
        }
    }

    public void validaCargaMulta(final List<FecetOficio> listaOficio) throws NegocioException {
        if (listaOficio.isEmpty()) {
            throw new NegocioException("No se cargo el oficio de Multa");
        }
    }

    public void validaCargaAvisoContribuyenteOrden(final List<FecetOficio> listaOficio) throws NegocioException {
        if (listaOficio.isEmpty()) {
            throw new NegocioException("No se cargo el oficio de Aviso del contribuyente");
        }
    }

    public void validaCargaConObservaciones(final List<FecetOficio> listaOficio) throws NegocioException {
        if (listaOficio.isEmpty()) {
            throw new NegocioException("No se cargo el oficio de Observaciones en Revision de Escritorio");
        }
    }

    public void validaCargaRequerimientoReincidencia(final List<FecetOficio> listaOficio) throws NegocioException {
        if (listaOficio.isEmpty()) {
            throw new NegocioException("No se cargo el oficio de Requerimiento por Reincidencia");
        }
    }

    public void validaCargaAnexos(final List<FecetAnexosProrrogaOrden> listaAnexos) throws NegocioException {
        if (listaAnexos.isEmpty()) {
            throw new NegocioException("Es obligatorio que adjunte el oficio solicitado");
        }
    }

    public void validaCargaAnexosPruebasPericiales(final List<FecetAnexoPruebasPericiales> listaAnexos) throws NegocioException {
        if (listaAnexos.isEmpty()) {
            throw new NegocioException("Es obligatorio que adjunte el oficio solicitado");
        }
    }

    public void validaCargaResolucion(final List<FecetAnexosProrrogaOrden> listaAnexos) throws NegocioException {
        if (listaAnexos.isEmpty()) {
            throw new NegocioException("Es obligatorio que adjunte la resoluci\u00F3n");
        }
    }

    public void validaCargaAnexosOficio(final List<FecetAnexosProrrogaOficio> listaAnexos) throws NegocioException {
        if (listaAnexos.isEmpty()) {
            throw new NegocioException("Es obligatorio que adjunte el oficio solicitado");
        }
    }

    public void validaCargaResolucionOficio(final List<FecetAnexosProrrogaOficio> listaAnexos) throws NegocioException {
        if (listaAnexos.isEmpty()) {
            throw new NegocioException("Es obligatorio que adjunte la resoluci\u00F3n");
        }
    }

    public void validaJustificacionProrrogaAceptada(final String justificacion) throws NegocioException {
        if (justificacion == null || justificacion.trim().equals("")) {
            throw new NegocioException("No se ha ingresado un motivo de aceptacion de la prorroga");
        }
    }

    public void validaJustificacionProrrogaRechazada(final String justificacion) throws NegocioException {
        if (justificacion == null || justificacion.trim().equals("")) {
            throw new NegocioException("No se ha ingresado un motivo de rechazo de la prorroga");
        }
    }

    public void validaSeleccionNuevoCambioMetodo(final BigDecimal idMetodoNuevo) throws NegocioException {
        if (idMetodoNuevo == null || idMetodoNuevo.equals(new BigDecimal(-1L))) {
            throw new NegocioException("No se selecciono el nuevo metodo para realizar el Cambio de Metodo");
        }
    }

    public void validaCargaCambioMetodo(final List<FecetOficio> listaOficio) throws NegocioException {
        if (listaOficio.isEmpty()) {
            throw new NegocioException("No se cargo el oficio de Cambio de Metodo");
        }
    }

    public void validaCargaCompulsaInternacional(final List<FecetOficio> listaOficio) throws NegocioException {
        if (listaOficio.isEmpty()) {
            throw new NegocioException("No se cargo el oficio de Compulsa Internacional");
        }
    }

    public void validaCargaCompulsaTerceroOtrasAutoridades(final List<FecetOficio> listaOficio,
            final Integer tipoCompulsa) throws NegocioException {
        if (tipoCompulsa == 1 && listaOficio.isEmpty()) {
            throw new NegocioException("No se cargo el oficio de Otras Autoridades");
        } else if (tipoCompulsa == 2 && listaOficio.isEmpty()) {
            throw new NegocioException("No se cargo el oficio de Compulsa con Terceros");
        }
    }

    public void validaAutoridadCompulsada(String autoridadCompulsada) throws NegocioException {
        if (autoridadCompulsada == null || autoridadCompulsada.trim().equals("")) {
            throw new NegocioException("No se cargo el campo autoridad compulsado");
        }
    }

    public void validaContribuyenteCompulsado(final FecetContribuyente contribuyenteCompulsado) throws NegocioException {
        if (contribuyenteCompulsado.getRfc() == null || contribuyenteCompulsado.getRfc().equals("")) {
            throw new NegocioException("Se debe ingresar un contribuyente al cual se asignara la compulsa");
        }
    }

    public FecetOficioDao getFecetOficioDao() {
        return fecetOficioDao;
    }

    public FecetOficioAnexosDao getFecetOficioAnexosDao() {
        return fecetOficioAnexosDao;
    }

    public FecetContribuyenteDao getFecetContribuyenteDao() {
        return fecetContribuyenteDao;
    }

    public FecetAsociadoDao getFecetAsociadoDao() {
        return fecetAsociadoDao;
    }

    public FecetCompulsasDao getFecetCompulsasDao() {
        return fecetCompulsasDao;
    }

    public FecetProrrogaOrdenDao getFecetProrrogaOrdenDao() {
        return fecetProrrogaOrdenDao;
    }

    public FecetProrrogaOficioDao getFecetProrrogaOficioDao() {
        return fecetProrrogaOficioDao;
    }

    public FecetPruebasPericialesDao getFecetPruebasPericialesDao() {
        return fecetPruebasPericialesDao;
    }

}
