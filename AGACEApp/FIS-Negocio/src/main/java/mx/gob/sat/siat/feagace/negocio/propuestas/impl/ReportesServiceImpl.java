package mx.gob.sat.siat.feagace.negocio.propuestas.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import mx.gob.sat.siat.base.service.BaseBusinessServices;

import mx.gob.sat.siat.feagace.modelo.dao.common.FeceaMetodoDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.ReportesDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececArace;
import mx.gob.sat.siat.feagace.modelo.dto.common.ReporteEjecutivoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.ReporteGerencialDTO;
import mx.gob.sat.siat.feagace.negocio.propuestas.ReportesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("reportesService")
public class ReportesServiceImpl extends BaseBusinessServices implements ReportesService {

    private static final long serialVersionUID = -5327213240312535720L;

    @Autowired
    private transient FeceaMetodoDao feceaMetodoDao;
    @Autowired
    private transient ReportesDao reportesDao;

    @Override
    public List<FececMetodo> getMetodos() {
        return feceaMetodoDao.findAll();
    }

    @Override
    public List<FececArace> getAreas() {
        return null;
    }

    @Override
    public List<ReporteEjecutivoDTO> consultaReporteEjecutivo(Date rangoInicio, Date rangoFin, BigDecimal idMetodo,
            BigDecimal idArea,
            BigDecimal idEstatus) {

        return reportesDao.consultaReporteEjecutivo(rangoInicio, rangoFin, idMetodo, idArea, idEstatus);

    }

    @Override
    public List<ReporteGerencialDTO> consultaReporteGerencial(BigDecimal metodo, BigDecimal status,
            BigDecimal area) {

        return reportesDao.consultaReporteGerencial(metodo, status, area);

    }

    @Override
    public List<ReporteGerencialDTO> consultaReporteGerencial(String cveOrden) {

        return reportesDao.consultaReporteGerencialOrden(cveOrden);

    }
}
