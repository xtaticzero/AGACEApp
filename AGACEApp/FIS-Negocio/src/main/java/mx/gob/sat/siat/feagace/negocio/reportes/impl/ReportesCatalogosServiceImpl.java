package mx.gob.sat.siat.feagace.negocio.reportes.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececActividadPreponderanteDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececCausaProgramacionDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececEstatusDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececSectorDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececSubprogramaDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FececRevisionDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FecetContribuyenteDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas.FececTipoPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FeceaMetodoDao;
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
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.ServiceAbstract;
import mx.gob.sat.siat.feagace.negocio.reportes.ReportesCatalogosService;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesReportes;

/**
 * Servicios para consultar los catalogos para generar reportes de Insumos.
 *
 * @author Domingo Fernandez
 * @version 1.0
 *
 */
@Service("reportesCatalogosService")
public class ReportesCatalogosServiceImpl extends ServiceAbstract implements ReportesCatalogosService {

    private static final long serialVersionUID = 5776613014311438932L;

    @Autowired
    private transient FececEstatusDao fececEstatusDao;

    @Autowired
    private transient FececSubprogramaDao fececSubprogramaDao;

    @Autowired
    private transient FececSectorDao fececSectorDao;

    @Autowired
    private transient FececActividadPreponderanteDao fececActividadPreponderanteDao;

    @Autowired
    private transient FecetContribuyenteDao fecetContribuyenteDao;

    @Autowired
    private transient FeceaMetodoDao feceaMetodoDao;

    @Autowired
    private transient FececTipoPropuestaDao fececTipoPropuestaDao;

    @Autowired
    private transient FececRevisionDao fececRevisionDao;

    @Autowired
    private transient FececCausaProgramacionDao fececCausaProgramacionDao;

    @Override
    public List<FececEstatus> getCatalogoEstatus() {
        List<FececEstatus> listaEstatus = null;
        listaEstatus = this.getFececEstatusDao().findAll();
        return listaEstatus;
    }

    @Override
    public List<FececEstatus> getCatalogoEstatus(Integer moduloId) {
        List<FececEstatus> listaEstatus = null;
        listaEstatus = this.getFececEstatusDao().findWhereModuloId(moduloId);
        return listaEstatus;
    }

    @Override
    public List<FececSubprograma> getCatalogoSubprograma() {
        List<FececSubprograma> listaSubprograma = null;
        listaSubprograma = this.getFececSubprogramaDao().findAll();
        return listaSubprograma;
    }

    @Override
    public List<FececSector> getCatalogoSector() {
        List<FececSector> listaSector = null;
        listaSector = this.getFececSectorDao().findAll();
        return listaSector;
    }

    @Override
    public List<FececActividadPreponderante> getCatalogoActividadPreponderante() {
        List<FececActividadPreponderante> listaActividad = null;
        listaActividad = this.getFececActividadPreponderanteDao().findAll();
        return listaActividad;
    }

    @Override
    public List<ReporteCatalogosModel> getCatalogoEntidadInsumos() {
        List<ReporteCatalogosModel> listaEntidad = null;
        listaEntidad = this.getFecetContribuyenteDao().findEntidadInsumosAll();
        return listaEntidad;
    }

    @Override
    public List<ReporteCatalogosModel> getCatalogoEntidadPropuestas() {
        List<ReporteCatalogosModel> listaEntidad = null;
        listaEntidad = this.getFecetContribuyenteDao().findEntidadPropuestasAll();
        return listaEntidad;
    }

    @Override
    public List<ReporteCatalogosModel> getCatalogoEntidadOrdenes() {
        List<ReporteCatalogosModel> listaEntidad = null;
        listaEntidad = this.getFecetContribuyenteDao().findEntidadOrdenesAll();
        return listaEntidad;
    }

    @Override
    public List<ReporteCatalogosModel> getCatalogoMesesInicial() {
        List<ReporteCatalogosModel> listaMeses = new ArrayList<ReporteCatalogosModel>();
        List<ReporteCatalogosModel> lista = this.generarMesesAnio();
        for (int i = 1; i <= ConstantesReportes.N_12; i++) {
            for (ReporteCatalogosModel mes : lista) {
                if (mes.getCatalogoId() == i) {
                    listaMeses.add(mes);
                }
            }
        }
        return listaMeses;
    }

    private List<ReporteCatalogosModel> generarMesesAnio() {
        List<ReporteCatalogosModel> listaMeses = new ArrayList<ReporteCatalogosModel>();
        ReporteCatalogosModel enero = new ReporteCatalogosModel();
        ReporteCatalogosModel febrero = new ReporteCatalogosModel();
        ReporteCatalogosModel marzo = new ReporteCatalogosModel();
        ReporteCatalogosModel abril = new ReporteCatalogosModel();
        ReporteCatalogosModel mayo = new ReporteCatalogosModel();
        ReporteCatalogosModel junio = new ReporteCatalogosModel();
        ReporteCatalogosModel julio = new ReporteCatalogosModel();
        ReporteCatalogosModel agosto = new ReporteCatalogosModel();
        ReporteCatalogosModel septiembre = new ReporteCatalogosModel();
        ReporteCatalogosModel octubre = new ReporteCatalogosModel();
        ReporteCatalogosModel noviembre = new ReporteCatalogosModel();
        ReporteCatalogosModel diciembre = new ReporteCatalogosModel();
        enero.setCatalogoId(ConstantesReportes.N_1);
        enero.setCatalogoNombre("Enero");
        febrero.setCatalogoId(ConstantesReportes.N_2);
        febrero.setCatalogoNombre("Febrero");
        marzo.setCatalogoId(ConstantesReportes.N_3);
        marzo.setCatalogoNombre("Marzo");
        abril.setCatalogoId(ConstantesReportes.N_4);
        abril.setCatalogoNombre("Abril");
        mayo.setCatalogoId(ConstantesReportes.N_5);
        mayo.setCatalogoNombre("Mayo");
        junio.setCatalogoId(ConstantesReportes.N_6);
        junio.setCatalogoNombre("Junio");
        julio.setCatalogoId(ConstantesReportes.N_7);
        julio.setCatalogoNombre("Julio");
        agosto.setCatalogoId(ConstantesReportes.N_8);
        agosto.setCatalogoNombre("Agosto");
        septiembre.setCatalogoId(ConstantesReportes.N_9);
        septiembre.setCatalogoNombre("Septiembre");
        octubre.setCatalogoId(ConstantesReportes.N_10);
        octubre.setCatalogoNombre("Octubre");
        noviembre.setCatalogoId(ConstantesReportes.N_11);
        noviembre.setCatalogoNombre("Noviembre");
        diciembre.setCatalogoId(ConstantesReportes.N_12);
        diciembre.setCatalogoNombre("Diciembre");
        listaMeses.add(enero);
        listaMeses.add(febrero);
        listaMeses.add(marzo);
        listaMeses.add(abril);
        listaMeses.add(mayo);
        listaMeses.add(junio);
        listaMeses.add(julio);
        listaMeses.add(agosto);
        listaMeses.add(septiembre);
        listaMeses.add(octubre);
        listaMeses.add(noviembre);
        listaMeses.add(diciembre);
        return listaMeses;
    }

    @Override
    public List<ReporteCatalogosModel> getCatalogoAnios(Date fechaMaximo) {
        List<ReporteCatalogosModel> listaAnios = new ArrayList<ReporteCatalogosModel>();
        Calendar calMaximo = Calendar.getInstance();
        calMaximo.setTime(fechaMaximo);
        int anioFin = calMaximo.get(Calendar.YEAR);
        for (int i = anioFin; i >= ConstantesReportes.MINIMO_ANIOS; i--) {
            ReporteCatalogosModel obj = new ReporteCatalogosModel();
            obj.setCatalogoId(i);
            obj.setCatalogoNombre(String.valueOf(i));
            listaAnios.add(obj);
        }
        return listaAnios;
    }

    @Override
    public List<ReporteCatalogosModel> getCatalogoAniosMinimo(Date fechaMinimo, Date fechaMaximo) {
        List<ReporteCatalogosModel> listaAnios = new ArrayList<ReporteCatalogosModel>();
        Calendar calMinimo = Calendar.getInstance();
        Calendar calMaximo = Calendar.getInstance();
        calMinimo.setTime(fechaMinimo);
        calMaximo.setTime(fechaMaximo);
        int anioFin = calMaximo.get(Calendar.YEAR);
        int anioInicial = calMinimo.get(Calendar.YEAR);
        for (int i = anioFin; i >= anioInicial; i--) {
            ReporteCatalogosModel obj = new ReporteCatalogosModel();
            obj.setCatalogoId(i);
            obj.setCatalogoNombre(String.valueOf(i));
            listaAnios.add(obj);
        }
        return listaAnios;
    }

    @Override
    public List<FececUnidadAdministrativa> getCatalogoUnidadAdministrativa() {
        List<FececUnidadAdministrativa> listaUnidad = new ArrayList<FececUnidadAdministrativa>();
        List<Integer> noValidas = new ArrayList<Integer>();
        noValidas.add(Constantes.ACIACE.intValue());
        noValidas.add(Constantes.ACPPCE.intValue());
        noValidas.add(Constantes.PPCE.intValue());
        for (Integer key : MAP_UNIDAD_ADMINISTRATIVA.keySet()) {
            if (noValidas.contains(key)) {
                continue;
            }
            listaUnidad.add(fillUnidadAdministrativa(key));
        }
        return listaUnidad;
    }

    @Override
    public List<FececUnidadAdministrativa> getUnidadAdministrativa(BigDecimal idArace) {
        List<FececUnidadAdministrativa> listaUnidad = new ArrayList<FececUnidadAdministrativa>();
        FececUnidadAdministrativa elemento = fillUnidadAdministrativa(idArace.intValue());
        if (elemento != null) {
            listaUnidad.add(elemento);
        }
        return listaUnidad;
    }

    @Override
    public List<FececMetodo> getCatalogoMetodos() {
        return this.getFeceaMetodoDao().findAll();
    }

    @Override
    public List<FececTipoPropuesta> getCatalogoTipoPropuesta() {
        return this.getFececTipoPropuestaDao().findAll();
    }

    @Override
    public List<FececRevision> getCatalogoTipoRevision() {
        return this.getFececRevisionDao().findAll();
    }

    @Override
    public List<FececCausaProgramacion> getCatalogoCausaProgramacion() {
        return this.getFececCausaProgramacionDao().findAll();
    }

    public void setFececEstatusDao(FececEstatusDao fececEstatusDao) {
        this.fececEstatusDao = fececEstatusDao;
    }

    public FececEstatusDao getFececEstatusDao() {
        return fececEstatusDao;
    }

    public void setFececSubprogramaDao(FececSubprogramaDao fececSubprogramaDao) {
        this.fececSubprogramaDao = fececSubprogramaDao;
    }

    public FececSubprogramaDao getFececSubprogramaDao() {
        return fececSubprogramaDao;
    }

    public void setFececSectorDao(FececSectorDao fececSectorDao) {
        this.fececSectorDao = fececSectorDao;
    }

    public FececSectorDao getFececSectorDao() {
        return fececSectorDao;
    }

    public void setFececActividadPreponderanteDao(FececActividadPreponderanteDao fececActividadPreponderanteDao) {
        this.fececActividadPreponderanteDao = fececActividadPreponderanteDao;
    }

    public FececActividadPreponderanteDao getFececActividadPreponderanteDao() {
        return fececActividadPreponderanteDao;
    }

    public void setFecetContribuyenteDao(FecetContribuyenteDao fecetContribuyenteDao) {
        this.fecetContribuyenteDao = fecetContribuyenteDao;
    }

    public FecetContribuyenteDao getFecetContribuyenteDao() {
        return fecetContribuyenteDao;
    }

    public void setFeceaMetodoDao(FeceaMetodoDao feceaMetodoDao) {
        this.feceaMetodoDao = feceaMetodoDao;
    }

    public FeceaMetodoDao getFeceaMetodoDao() {
        return feceaMetodoDao;
    }

    public void setFececTipoPropuestaDao(FececTipoPropuestaDao fececTipoPropuestaDao) {
        this.fececTipoPropuestaDao = fececTipoPropuestaDao;
    }

    public FececTipoPropuestaDao getFececTipoPropuestaDao() {
        return fececTipoPropuestaDao;
    }

    public void setFececRevisionDao(FececRevisionDao fececRevisionDao) {
        this.fececRevisionDao = fececRevisionDao;
    }

    public FececRevisionDao getFececRevisionDao() {
        return fececRevisionDao;
    }

    public void setFececCausaProgramacionDao(FececCausaProgramacionDao fececCausaProgramacionDao) {
        this.fececCausaProgramacionDao = fececCausaProgramacionDao;
    }

    public FececCausaProgramacionDao getFececCausaProgramacionDao() {
        return fececCausaProgramacionDao;
    }

}
