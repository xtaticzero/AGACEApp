package mx.gob.sat.siat.feagace.negocio.common.carga;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececCausaProgramacion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececConcepto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececPrioridad;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.CargaMasivaPropuestasDTO;
import mx.gob.sat.siat.feagace.negocio.util.carga.constantes.TipoListaCargaPropuestaEnum;
import mx.gob.sat.siat.feagace.negocio.util.carga.exception.CargaRegistroXExcelException;

public interface CargaMasivaXExcelService {

    String ERROR_CARGA_ARCHIVO = "acceso.archivo.excel";

    Map<TipoListaCargaPropuestaEnum, List<CargaMasivaPropuestasDTO>> validarPropuestasDeExcel(InputStream archivoExcel,
            EmpleadoDTO empleado, String folioCarga) throws CargaRegistroXExcelException;

    List<FececMetodo> obtenerListaMetodo();

    List<FececRevision> obtenerListaRevision();

    List<FececSubprograma> obtenerListaSubPrograma(BigDecimal idGeneral);

    List<FececTipoPropuesta> obtenerListaTipoPropuesta();

    List<FececCausaProgramacion> obtenerListaCausaProgramacion();

    List<FececSector> obtenerListaSector(BigDecimal idGeneral);

    List<FececTipoImpuesto> obtenerListaImpuesto();

    Map<BigDecimal, List<FececConcepto>> obtenerListaConcepto(List<FececTipoImpuesto> listaTipoImpuesto);

    List<FececPrioridad> obtenerListaPrioridad();

}
