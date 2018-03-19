package mx.gob.sat.siat.feagace.negocio.propuestas.consulta.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetPapelesTrabajoDao;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocumentoOrdenModel;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PapelesTrabajo;
import mx.gob.sat.siat.feagace.negocio.propuestas.consulta.ConsultarPapelesTrabajoService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtil;

@Service("consultarPapelesTrabajoService")
public class ConsultarPapelesTrabajoServiceImpl implements ConsultarPapelesTrabajoService {

    @Autowired
    private FecetPapelesTrabajoDao fecetPapelesTrabajoDao;

    @Override
    public void insertaPapelesTrabajo(PapelesTrabajo dto) {
        fecetPapelesTrabajoDao.insert(dto);
    }

    @Override
    public List<PapelesTrabajo> getPapelesByIdPropuesta(BigDecimal idPropuesta) {

        return fecetPapelesTrabajoDao.getPapelesByIdPropuesta(idPropuesta);
    }

    @Override
    public List<PapelesTrabajo> getPapelesByIdPropuestaOrIdOrden(BigDecimal idPropuesta, BigDecimal idOrden) {
        return fecetPapelesTrabajoDao.getPapelesOfOrden(idPropuesta, idOrden);
    }

    @Override
    public List<PapelesTrabajo> getPapelesByIdOfcio(BigDecimal idOrden, BigDecimal idTipoOficio) {
        return fecetPapelesTrabajoDao.getPapelesOfOficio(idOrden, idTipoOficio);
    }

    @Override
    public void guardarPapelTrabajo(DocumentoOrdenModel papelTrabajo) throws IOException {
        CargaArchivoUtil.guardarArchivoPapelesTrabajoPropuesta(papelTrabajo);
    }

    @Override
    public void actualizarPapelTrabajo(PapelesTrabajo papelTrabajo) {
        fecetPapelesTrabajoDao.actualizar(papelTrabajo);
    }

    @Override
    public List<PapelesTrabajo> getPapelesOnlyIdOfcio(BigDecimal idOficio) {
        return fecetPapelesTrabajoDao.getPapelesOfOficioById(idOficio);
    }
}
