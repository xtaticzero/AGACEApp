package mx.gob.sat.siat.feagace.negocio.consulta.ordenes;

import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FiltroOrdenes;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorEstatusOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;
import mx.gob.sat.siat.feagace.negocio.bo.consulta.ConsultaEjecutivaOrdenesBO;
import mx.gob.sat.siat.feagace.negocio.consulta.ordenes.filtro.FiltroConsultaOrdenes;
import mx.gob.sat.siat.feagace.negocio.exception.consulta.ConsultaEjecutivaOrdenesServiceException;
import mx.gob.sat.siat.feagace.negocio.util.constantes.SemaforoEnum;

public interface ConsultaEjecutivaOrdenesService {
    
    String MSG_ERROR_REGISTRO_EMPLEADO = "registro.empleado";
    String MSG_ERROR_USUARIO_SIN_PRIVILEGIOS = "sin.privilegios";
    String MSG_ERROR_PARAMETRO_FILTRO = "parametros.filtro";
    String MSG_ERROR_PLAZOS = "inicializar.plazos";
    
    ConsultaEjecutivaOrdenesBO getAccesoEmpleadoAConsultaOrdenes(EmpleadoDTO empleado) throws ConsultaEjecutivaOrdenesServiceException;
    
    ConsultaEjecutivaOrdenesBO consultarOrdenes(ConsultaEjecutivaOrdenesBO consultaBO, FiltroConsultaOrdenes filtroDeBusqueda) 
            throws ConsultaEjecutivaOrdenesServiceException;
    
    ConsultaEjecutivaOrdenesBO consultarOrdenesXEstatusoGrupo(ConsultaEjecutivaOrdenesBO consultaBO, FiltroConsultaOrdenes filtroDeBusqueda, 
            AgrupadorEstatusOrdenesEnum grupoSeleccionado) throws ConsultaEjecutivaOrdenesServiceException;
    
    ConsultaEjecutivaOrdenesBO getOrdenesXSemaforoMetodo(ConsultaEjecutivaOrdenesBO consultaBO, SemaforoEnum tipoSemaforo, SemaforoEnum tipoSemaforoFiltrado,
            TipoMetodoEnum tipoMetodo, AgrupadorEstatusOrdenesEnum grupoEstatusSeleccionado, FiltroOrdenes filtro, boolean flgEmpleado);
    
    FecetPropuesta consultaPropuestaDetalle(String idRegistro);
    
    ConsultaEjecutivaOrdenesBO getOrdenesXEmpleadoSemaforoMetodo(ConsultaEjecutivaOrdenesBO consultaBO, FiltroConsultaOrdenes filtroConsultaOrdenes,
            List<SemaforoEnum> lstSemaforosFiltro);
    
    boolean tieneOficios(BigDecimal idOrden);
    
    HSSFWorkbook creaExcel(List<AgaceOrden> listaOrdenes);

}
