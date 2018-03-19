package mx.gob.sat.siat.feagace.negocio.reportes;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececActividadPreponderante;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececCausaProgramacion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatus;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReporteCatalogosModel;


public interface ReportesCatalogosService {
    List<FececEstatus> getCatalogoEstatus();
    List<FececEstatus> getCatalogoEstatus(Integer moduloId);
    List<FececSubprograma> getCatalogoSubprograma();
    List<FececSector> getCatalogoSector();
    List<FececActividadPreponderante> getCatalogoActividadPreponderante();
    List<ReporteCatalogosModel> getCatalogoEntidadInsumos();
    List<ReporteCatalogosModel> getCatalogoEntidadPropuestas();
    List<ReporteCatalogosModel> getCatalogoEntidadOrdenes();
    List<ReporteCatalogosModel> getCatalogoMesesInicial();
    List<ReporteCatalogosModel> getCatalogoAnios(Date fechaMaximo);
    List<ReporteCatalogosModel> getCatalogoAniosMinimo(Date fechaMinimo, Date fechaMaximo);
    List<FececUnidadAdministrativa> getCatalogoUnidadAdministrativa();
    List<FececUnidadAdministrativa> getUnidadAdministrativa(BigDecimal idArace);
    List<FececMetodo> getCatalogoMetodos();
    List<FececTipoPropuesta> getCatalogoTipoPropuesta();
    List<FececRevision> getCatalogoTipoRevision();
    List<FececCausaProgramacion> getCatalogoCausaProgramacion();
}
