package mx.gob.sat.siat.feagace.vista.ordenes;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.negocio.ordenes.ReimpresionDocumentosService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtilOrdenes;
import mx.gob.sat.siat.feagace.vista.common.AbstractManagedBean;
import mx.gob.sat.siat.feagace.vista.util.MetodosGenericos;

import org.primefaces.model.StreamedContent;

/**
 *
 *
 * @author 874347
 * @version 1.0
 * @since 2014-05-13
 *
 * Programa para la visualización de datos en la parte, de la vista de la
 * aplicación.
 */
@ManagedBean(name = "reimpresionDocumentosMB")
@SessionScoped
public class ReimpresionDocumentosMB extends AbstractManagedBean {

    @SuppressWarnings("compatibility:9088854246416384528")
    private static final long serialVersionUID = 1L;

    /**
     * En esta parte son declarados los Objetos, y la inyeccion de dependencia
     * para usar los metodos, correspondientes de las capas
     *
     * @param seguimientoNotificacionService objeto de inyección, para acceder
     * al metodo de consultadato().
     * @param List<AgaceOrden> Declaracion de la Lista de tipo Objeto, donde se
     * alamacenara la consulta de los datos para devolcerlos en la vizta.
     */
    @ManagedProperty(value = "#{reimpresionDocumentosService}")
    private transient ReimpresionDocumentosService reimpresionDocumentosService;
    private List<AgaceOrden> listaOrdenesParaReimprimir;
    private AgaceOrden ordenSeleccionada;
    private StreamedContent archivoSeleccionOrdenes;

    /**
     * @param Inicia el metodo de la clase en esta parte se inicia el metodo
     * para visualizar los datos solicitados.
     */
    @PostConstruct
    public void init() {
        this.listaOrdenesParaReimprimir = new ArrayList<AgaceOrden>();

        cargaOrdenes();
    }

    public void cargaOrdenes() {

        this.listaOrdenesParaReimprimir = reimpresionDocumentosService.getOrdenesReimprimirDocumentacion();

    }

    public void setListaOrdenesParaReimprimir(List<AgaceOrden> listaOrdenesParaReimprimir) {
        this.listaOrdenesParaReimprimir = listaOrdenesParaReimprimir;
    }

    public List<AgaceOrden> getListaOrdenesParaReimprimir() {
        return listaOrdenesParaReimprimir;
    }

    public void setOrdenSeleccionada(AgaceOrden ordenSeleccionada) {
        this.ordenSeleccionada = ordenSeleccionada;
    }

    public AgaceOrden getOrdenSeleccionada() {
        return ordenSeleccionada;
    }

    public void setArchivoSeleccionOrdenes(StreamedContent archivoSeleccionOrdenes) {
        this.archivoSeleccionOrdenes = archivoSeleccionOrdenes;
    }

    /**
     *
     * @return
     */
    public StreamedContent getArchivoSeleccionOrdenes() {
        String documentosDescargas;
        documentosDescargas = (String) MetodosGenericos.getParametro("documentosDescargas");
        archivoSeleccionOrdenes
                = getDescargaArchivo(RutaArchivosUtilOrdenes.armarRutaDestinoOrden(this.ordenSeleccionada) + documentosDescargas
                        + ".pdf", documentosDescargas + ".pdf");
        return archivoSeleccionOrdenes;
    }

    public void setReimpresionDocumentosService(ReimpresionDocumentosService reimpresionDocumentosService) {
        this.reimpresionDocumentosService = reimpresionDocumentosService;
    }

    public ReimpresionDocumentosService getReimpresionDocumentosService() {
        return reimpresionDocumentosService;
    }
}
