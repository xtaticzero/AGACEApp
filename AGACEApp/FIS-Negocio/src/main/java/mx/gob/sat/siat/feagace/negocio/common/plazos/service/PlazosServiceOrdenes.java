package mx.gob.sat.siat.feagace.negocio.common.plazos.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.ActoAdministrativo;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.FececActosAdm;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.NotificableNyV;
import mx.gob.sat.siat.feagace.modelo.dto.common.tiempos.model.TiempoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCompulsas;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.enums.TiposOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.negocio.common.tiempos.enums.TiemposClaveEnum;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;

/**
 * Interface para funcionalidad de servico de Plazos
 *
 * @author eolf
 */
public interface PlazosServiceOrdenes {

    /**
     * Metodo que valida el plazo para cargar documentos requeridos de la orden, contemplando en caso de existir, el plazo de las prorrogas.
     *
     * @param orden
     * @return
     */
    boolean validarPlazoCargaDocumentosRequeridosOrden(AgaceOrden orden);

    /**
     * Metodo que valida el plazo para cargar documentos al oficio como parametro contemplando en caso de existir, el plazo de las
     * prorrogas. RNC002
     * 
     * @param oficio
     * @return
     */
    boolean validarPlazoCargaDocumentosRequeridosOficio(FecetOficio oficio);

    /**
     * Metodo encargado de validar que el oficio aun esta en el plazo para cargar prorroga,es decir en el plazo de Cargar Documentacion.
     * 
     * @param oficio
     * @return
     */
    boolean validarPlazoCrearProrrogaOficio(FecetOficio oficio);

    /**
     * Metodo encargado de validar que la orden aun este en el plazo par cargar prorrogas, es decir en el plazo de Cargar Documentacion.
     * 
     * @to Contribuyente
     * @param orden
     * @return
     */
    boolean validarPlazoCrearProrrogaOrden(AgaceOrden orden);

    /**
     * Metodo para suspender el plazo del oficio. Este metodo consulta al servicio de NYV para obtener la fecha_notif_cont, se calcula la
     * fecha_surte_efectos y se persiste. Si NYV no envia fecha, se calcula la fecha surte efectos y no se persiste. Se registra la
     * suspension solicitada.
     * 
     * @author eolf
     * @to Empleado
     * @param compulsa
     * @param orden
     * @return
     */
    void suspenderPlazoCompulsa(FecetCompulsas compulsa);

    /**
     * Metodo para suspender el plazo del oficio. Este metodo consulta al servicio de NYV para obtener la fecha_notif_cont, se calcula la
     * fecha_surte_efectos y se persiste. Si NYV no envia fecha, se calcula la fecha surte efectos y no se persiste. Se registra la
     * suspension solicitada.
     * 
     * @author eolf
     * @to Empleado
     * @param oficio
     * @param orden
     * @return
     */
    void suspenderPlazoOf(AgaceOrden orden, FecetOficio oficio);

    /**
     * Metodo para saber si un documento de orden es extemporaneo
     * 
     * @author eolf
     * @to Contribuyente
     * @param orden
     * @param fechaCargaDocumento
     * @return
     */
    boolean esDocumentoExtemporaneoOrden(AgaceOrden orden, final Date fechaCargaDocumento);

    /**
     * Metodo para saber si un documento de oficio es extemporaneo
     * 
     * @author eolf
     * @param oficio
     * @param fechaCargaDocumento
     * @return
     */
    boolean esDocumentoExtemporaneoOficio(FecetOficio oficio, final Date fechaCargaDocumento);

    /**
     * Metodo que busca y filtra las ordenes por fecha(fechaSurteefectos) menor o igual a la fecha actual para ser mostradas con forme a sus
     * palzos
     * 
     * @param listOrden
     * @return
     */
    List<AgaceOrden> filtraOrdenPorFecha(List<AgaceOrden> listOrden);

    void registrarActoAdministrativo(NotificableNyV notificable, FececActosAdm actoBD) throws NegocioException;

    List<FecetOficio> filtarOficiosPorFecha(AgaceOrden orden, List<FecetOficio> listOficio);

    void inicializarOrdenConPlazos(AgaceOrden orden);

    Date obtenerFechaMaxSolicitudOficio(FecetOficio oficio);

    boolean validarPlazoSolicitarOficio(AgaceOrden orden, BigDecimal idTipoOficio);

    void asignarFechasNotificacion(NotificableNyV notificable);

    List<FecetCompulsas> filtarCompulsasPorFecha(List<FecetCompulsas> listCompulsas);

    void asignarFechasNyVOficio(AgaceOrden orden, List<FecetOficio> listOficio);

    void obtenerFechasNotificacion(FecetOficio oficio);

    boolean suspensionOrden(AgaceOrden orden);

    boolean suspensionPruebasPericiliales(AgaceOrden orden);

    boolean validarPlazoFinCompulsa(AgaceOrden orden, TiposOficiosOrdenesEnum oficio);

    Date primerDiaHabil(Date fecha);

    TiempoDTO obtenerPlazoOficio(BigDecimal idMetodo, TiemposClaveEnum clave, BigDecimal idTipoOficio);

    boolean suspensionOrdenReactivacion(AgaceOrden orden);

    ActoAdministrativo obtenerActoAdministrativo(String claveUnidadAdmin, Long idActoNyv);

    boolean validarPlazoCrearPruebasPericiales(AgaceOrden orden);

    Date obtenerFechaParaSolicitudOficio(FecetOficio oficio);
    
    boolean tieneAcuerdoConclusivo(AgaceOrden orden);
    
    void obtenerDocumentosActoAdmin(FececActosAdm acto);
    
    List<FecetOficio> filtarOficios(List<FecetOficio> listOficio);
}
