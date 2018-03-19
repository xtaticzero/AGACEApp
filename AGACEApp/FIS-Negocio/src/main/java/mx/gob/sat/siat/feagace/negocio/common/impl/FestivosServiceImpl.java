package mx.gob.sat.siat.feagace.negocio.common.impl;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FececFeriadosDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececFeriados;
import mx.gob.sat.siat.feagace.negocio.common.FestivosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("festivosService")
public class FestivosServiceImpl implements FestivosService {

    @Autowired
    private FececFeriadosDao fececFeriadosDao;

    /**
     * Metodo getListaDiasFestivos
     *
     * @param fecha
     * @param diasFestivos
     * @return List
     */
    @Override
    public List<FececFeriados> getListaDiasFestivos(final Date fecha,
            final int diasFestivos) {

        return fececFeriadosDao
                .buscarDiasFestivosContributenteCargaDocumentos(fecha,
                        diasFestivos);

    }

    /**
     * Metodo getTodosDiasFestivos
     *
     * @return List
     */
    @Override
    public List<FececFeriados> getTodosDiasFestivos() {
        return fececFeriadosDao.findAll();
    }

    /**
     * Metodo setFececFeriadosDao
     *
     * @param fececFeriadosDao
     */
    public void setFececFeriadosDao(final FececFeriadosDao fececFeriadosDao) {
        this.fececFeriadosDao = fececFeriadosDao;
    }

    /**
     * Metodo getFececFeriadosDao
     *
     * @return FececFeriadosDao
     */
    public FececFeriadosDao getFececFeriadosDao() {
        return fececFeriadosDao;
    }
}
