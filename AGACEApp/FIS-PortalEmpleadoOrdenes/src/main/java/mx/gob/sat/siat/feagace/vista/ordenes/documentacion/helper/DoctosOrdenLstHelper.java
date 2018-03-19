/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.ordenes.documentacion.helper;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;
import mx.gob.sat.siat.feagace.modelo.dto.common.SolicitudContribuyenteAnexoVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexoPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosRechazoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetSuspensionDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetTipoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocumentoOrdenModel;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class DoctosOrdenLstHelper extends DoctosOrdenLstVOHelper {

    private static final long serialVersionUID = -282256694389307225L;

    private List<FecetPromocion> listaPromociones;
    private List<FecetProrrogaOrden> listaProrroga;
    private List<FecetProrrogaOrden> listaProrrogaHistorico;
    private List<FecetDocProrrogaOrden> listaDocumentosProrroga;
    private List<FecetAlegato> listaPruebasAlegatos;
    private List<FecetAnexosRechazoOficio> listaAnexosRechazoOficio;
    private List<FececMetodo> listaMetodosCambioMetodo;
    private List<FecetAnexosProrrogaOrden> listaDocumentosRechazoProrroga;
    private List<FecetAnexosProrrogaOrden> listaAnexosProrrogaOrden;
    private List<FecetAnexosProrrogaOrden> listaResolucionProrrogaOrden;
    private List<FecetAnexosProrrogaOrden> listaResolucionProrrogaOrdenRechazo;
    private List<FecetAnexosProrrogaOrden> listaAnexosProrrogaOrdenRechazo;
    private List<FecetSuspensionDTO> listaSuspensiones;
    private List<EmpleadoDTO> listaJefeDepartamento;
    private List<EmpleadoDTO> listaEnlace;
    private List<DocumentoOrdenModel> listaPapelesTrabajo;
    private List<DocumentoOrdenModel> listaPapelesTrabajoOficio;
    private List<FecetTipoOficio> listaOficiosMedidasApremio;
    private List<FecetPruebasPericiales> listaPruebasPericiales;
    private List<FecetPruebasPericiales> listaPruebasPericialesHistorico;
    private List<FecetDocPruebasPericiales> listaDocumentosPruebaPericial;
    private List<FecetAnexoPruebasPericiales> listaResolucionPruebasPericiales;

    public List<EmpleadoDTO> getListaJefeDepartamento() {
        return listaJefeDepartamento;
    }

    public void setListaJefeDepartamento(List<EmpleadoDTO> listaJefeDepartamento) {
        this.listaJefeDepartamento = listaJefeDepartamento;
    }

    public List<EmpleadoDTO> getListaEnlace() {
        return listaEnlace;
    }

    public void setListaEnlace(List<EmpleadoDTO> listaEnlace) {
        this.listaEnlace = listaEnlace;
    }

    public List<FecetAnexoPruebasPericiales> getListaResolucionPruebasPericiales() {
        return listaResolucionPruebasPericiales;
    }

    public void setListaResolucionPruebasPericiales(List<FecetAnexoPruebasPericiales> listaResolucionPruebasPericiales) {
        this.listaResolucionPruebasPericiales = listaResolucionPruebasPericiales;
    }

    public List<FecetDocPruebasPericiales> getListaDocumentosPruebaPericial() {
        return listaDocumentosPruebaPericial;
    }

    public void setListaDocumentosPruebaPericial(List<FecetDocPruebasPericiales> listaDocumentosPruebaPericial) {
        this.listaDocumentosPruebaPericial = listaDocumentosPruebaPericial;
    }

    public List<FecetPruebasPericiales> getListaPruebasPericiales() {
        return listaPruebasPericiales;
    }

    public void setListaPruebasPericiales(List<FecetPruebasPericiales> listaPruebasPericiales) {
        this.listaPruebasPericiales = listaPruebasPericiales;
    }

    public List<FecetPruebasPericiales> getListaPruebasPericialesHistorico() {
        return listaPruebasPericialesHistorico;
    }

    public void setListaPruebasPericialesHistorico(List<FecetPruebasPericiales> listaPruebasPericialesHistorico) {
        this.listaPruebasPericialesHistorico = listaPruebasPericialesHistorico;
    }

    public List<FecetTipoOficio> getListaOficiosMedidasApremio() {
        return listaOficiosMedidasApremio;
    }

    public void setListaOficiosMedidasApremio(List<FecetTipoOficio> listaOficiosMedidasApremio) {
        this.listaOficiosMedidasApremio = listaOficiosMedidasApremio;
    }

    public List<FecetAnexosProrrogaOrden> getListaResolucionProrrogaOrden() {
        return listaResolucionProrrogaOrden;
    }

    public void setListaResolucionProrrogaOrden(List<FecetAnexosProrrogaOrden> listaResolucionProrrogaOrden) {
        this.listaResolucionProrrogaOrden = listaResolucionProrrogaOrden;
    }

    public List<FecetAnexosProrrogaOrden> getListaResolucionProrrogaOrdenRechazo() {
        return listaResolucionProrrogaOrdenRechazo;
    }

    public void setListaResolucionProrrogaOrdenRechazo(
            List<FecetAnexosProrrogaOrden> listaResolucionProrrogaOrdenRechazo) {
        this.listaResolucionProrrogaOrdenRechazo = listaResolucionProrrogaOrdenRechazo;
    }

    public void setListaMetodosCambioMetodo(final List<FececMetodo> listaMetodosCambioMetodo) {
        this.listaMetodosCambioMetodo = listaMetodosCambioMetodo;
    }

    public List<FececMetodo> getListaMetodosCambioMetodo() {
        return listaMetodosCambioMetodo;
    }

    public void setListaAnexosRechazoOficio(final List<FecetAnexosRechazoOficio> listaAnexosRechazoOficio) {
        this.listaAnexosRechazoOficio = listaAnexosRechazoOficio;
    }

    public List<FecetAnexosRechazoOficio> getListaAnexosRechazoOficio() {
        return listaAnexosRechazoOficio;
    }

    public void setListaDocumentosProrroga(final List<FecetDocProrrogaOrden> listaDocumentosProrroga) {
        this.listaDocumentosProrroga = listaDocumentosProrroga;
    }

    public List<FecetDocProrrogaOrden> getListaDocumentosProrroga() {
        return listaDocumentosProrroga;
    }

    public void setListaDocumentosRechazoProrroga(List<FecetAnexosProrrogaOrden> listaDocumentosRechazoProrroga) {
        this.listaDocumentosRechazoProrroga = listaDocumentosRechazoProrroga;
    }

    public List<FecetAnexosProrrogaOrden> getListaDocumentosRechazoProrroga() {
        return listaDocumentosRechazoProrroga;
    }

    public void setListaProrrogaHistorico(final List<FecetProrrogaOrden> listaProrrogaHistorico) {
        this.listaProrrogaHistorico = listaProrrogaHistorico;
    }

    public List<FecetProrrogaOrden> getListaProrrogaHistorico() {
        return listaProrrogaHistorico;
    }

    public List<FecetProrrogaOrden> getListaProrroga() {
        return listaProrroga;
    }

    public void setListaPromociones(List<FecetPromocion> listaPromociones) {
        this.listaPromociones = listaPromociones;
    }

    public List<FecetPromocion> getListaPromociones() {
        return listaPromociones;
    }

    public void setListaPruebasAlegatos(List<FecetAlegato> listaPruebasAlegatos) {
        this.listaPruebasAlegatos = listaPruebasAlegatos;
    }

    public List<FecetAlegato> getListaPruebasAlegatos() {
        return listaPruebasAlegatos;
    }

    public List<FecetAnexosProrrogaOrden> getListaAnexosProrrogaOrden() {
        return listaAnexosProrrogaOrden;
    }

    public void setListaAnexosProrrogaOrden(List<FecetAnexosProrrogaOrden> listaAnexosProrrogaOrden) {
        this.listaAnexosProrrogaOrden = listaAnexosProrrogaOrden;
    }

    public List<FecetAnexosProrrogaOrden> getListaAnexosProrrogaOrdenRechazo() {
        return listaAnexosProrrogaOrdenRechazo;
    }

    public void setListaAnexosProrrogaOrdenRechazo(List<FecetAnexosProrrogaOrden> listaAnexosProrrogaOrdenRechazo) {
        this.listaAnexosProrrogaOrdenRechazo = listaAnexosProrrogaOrdenRechazo;
    }

    public void setListaProrroga(List<FecetProrrogaOrden> listaProrroga) {
        this.listaProrroga = listaProrroga;
    }

    public List<FecetSuspensionDTO> getListaSuspensiones() {
        return listaSuspensiones;
    }

    public void setListaSuspensiones(List<FecetSuspensionDTO> listaSuspensiones) {
        this.listaSuspensiones = listaSuspensiones;
    }

    public List<DocumentoOrdenModel> getListaPapelesTrabajo() {
        return listaPapelesTrabajo;
    }

    public void setListaPapelesTrabajo(List<DocumentoOrdenModel> listaPapelesTrabajo) {
        this.listaPapelesTrabajo = listaPapelesTrabajo;
    }

    public List<DocumentoOrdenModel> getListaPapelesTrabajoOficio() {
        return listaPapelesTrabajoOficio;
    }

    public void setListaPapelesTrabajoOficio(List<DocumentoOrdenModel> listaPapelesTrabajoOficio) {
        this.listaPapelesTrabajoOficio = listaPapelesTrabajoOficio;
    }

    public List<FecetAnexosProrrogaOrden> llenaAnexosProrrogaOrden(List<SolicitudContribuyenteAnexoVO> listaSolicitudContribuyenteAnexoVO) {
        List<FecetAnexosProrrogaOrden> lista = new ArrayList<FecetAnexosProrrogaOrden>();
        for (SolicitudContribuyenteAnexoVO solicitudAnexo : listaSolicitudContribuyenteAnexoVO) {
            lista.add(solicitudAnexo.getFecetAnexosProrrogaOrden());
        }
        return lista;
    }

    public List<FecetAnexoPruebasPericiales> llenaAnexoPruebasPericiales(List<SolicitudContribuyenteAnexoVO> listaSolicitudContribuyenteAnexoVOParam) {
        List<FecetAnexoPruebasPericiales> lista = new ArrayList<FecetAnexoPruebasPericiales>();
        List<SolicitudContribuyenteAnexoVO> listaSolicitudContribuyenteAnexoVO = listaSolicitudContribuyenteAnexoVOParam;
        if (listaSolicitudContribuyenteAnexoVO == null) {
            listaSolicitudContribuyenteAnexoVO = new ArrayList<SolicitudContribuyenteAnexoVO>();
        }
        for (SolicitudContribuyenteAnexoVO solicitudAnexo : listaSolicitudContribuyenteAnexoVO) {
            lista.add(solicitudAnexo.getFecetAnexoPruebasPericiales());
        }
        return lista;
    }

    public List<SolicitudContribuyenteAnexoVO> llenaAnexoSolicitudContribuyentePruebasPericiales(List<FecetAnexoPruebasPericiales> listaAnexoPruebasPericiales) {
        List<SolicitudContribuyenteAnexoVO> listaAnexoSolicitudContribuyente = new ArrayList<SolicitudContribuyenteAnexoVO>();
        for (FecetAnexoPruebasPericiales anexoPruebaPericial : listaAnexoPruebasPericiales) {
            SolicitudContribuyenteAnexoVO anexoSolicitudContribuyente = new SolicitudContribuyenteAnexoVO();
            anexoSolicitudContribuyente.setBlnActivo(anexoPruebaPericial.getBlnActivo());
            anexoSolicitudContribuyente.setFechaCreacion(anexoPruebaPericial.getFechaCreacion());
            anexoSolicitudContribuyente.setId(anexoPruebaPericial.getIdAnexoPruebasPericiales());
            anexoSolicitudContribuyente.setIdFlujo(anexoPruebaPericial.getIdFlujoPruebasPericiales());
            anexoSolicitudContribuyente.setNombreArchivo(anexoPruebaPericial.getNombreArchivo());
            anexoSolicitudContribuyente.setRutaArchivo(anexoPruebaPericial.getRutaArchivo());
            anexoSolicitudContribuyente.setFecetAnexoPruebasPericiales(anexoPruebaPericial);
            listaAnexoSolicitudContribuyente.add(anexoSolicitudContribuyente);
        }
        return listaAnexoSolicitudContribuyente;
    }

    public List<SolicitudContribuyenteAnexoVO> llenaAnexoSolicitudContribuyenteProrroga(List<FecetAnexosProrrogaOrden> listaAnexoProrroga) {
        List<SolicitudContribuyenteAnexoVO> listaAnexoSolicitudContribuyente = new ArrayList<SolicitudContribuyenteAnexoVO>();
        for (FecetAnexosProrrogaOrden anexoProrroga : listaAnexoProrroga) {
            SolicitudContribuyenteAnexoVO anexoSolicitudContribuyente = new SolicitudContribuyenteAnexoVO();
            anexoSolicitudContribuyente.setBlnActivo(anexoProrroga.getBlnActivo());
            anexoSolicitudContribuyente.setFechaCreacion(anexoProrroga.getFechaCreacion());
            anexoSolicitudContribuyente.setId(anexoProrroga.getIdAnexosProrrogaOrden());
            anexoSolicitudContribuyente.setIdFlujo(anexoProrroga.getIdFlujoProrrogaOrden());
            anexoSolicitudContribuyente.setNombreArchivo(anexoProrroga.getNombreArchivo());
            anexoSolicitudContribuyente.setRutaArchivo(anexoProrroga.getRutaArchivo());
            anexoSolicitudContribuyente.setFecetAnexosProrrogaOrden(anexoProrroga);
            listaAnexoSolicitudContribuyente.add(anexoSolicitudContribuyente);
        }
        return listaAnexoSolicitudContribuyente;
    }

}
