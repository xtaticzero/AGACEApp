package mx.gob.sat.siat.feagace.negocio.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.AgaceOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetProrrogaOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetProrrogaOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetPruebasPericialesDao;
import mx.gob.sat.siat.feagace.negocio.common.ActualizacionNumeroReferenciaService;

@Service("actualizacionNumeroReferenciaService")
public class ActualizacionNumeroReferenciaServiceImpl extends BaseBusinessServices implements ActualizacionNumeroReferenciaService {

    private static final long serialVersionUID = 2372302700207854076L;
    @Autowired
    private transient FecetOficioDao fecetOficioDao;

    @Autowired
    private transient AgaceOrdenDao agaceOrdenDao;

    @Autowired
    private transient FecetProrrogaOficioDao fecetProrrogaOficioDao;

    @Autowired
    private transient FecetProrrogaOrdenDao fecetProrrogaOrdenDao;

    @Autowired
    private transient FecetPruebasPericialesDao fecetPruebasPericialesDao;

    @Override
    public void actualizaReferenciaOficio(String id, String numeroReferencia) {
        fecetOficioDao.updateNumeroReferenciaOficio(numeroReferencia, Long.valueOf(id));
    }

    @Override
    public void actualizaReferenciaOrden(String id, String numeroReferencia) {
        agaceOrdenDao.updateNumeroReferenciaOrden(numeroReferencia, Long.valueOf(id));
    }

    @Override
    public void actualizaReferenciaOficioProrroga(String id, String numeroReferencia) {
        fecetProrrogaOficioDao.updateNumeroReferenciaProrOfi(numeroReferencia, Long.valueOf(id));
    }

    @Override
    public void actualizaReferenciaOrdenProrroga(String id, String numeroReferencia) {
        fecetProrrogaOrdenDao.updateNumeroReferenciaProrOrden(numeroReferencia, Long.valueOf(id));
    }

    @Override
    public void actualizaReferenciaPruebaPericial(String id, String numeroReferencia) {
        fecetPruebasPericialesDao.updateNumeroReferenciaPruePer(numeroReferencia, Long.valueOf(id));
    }

    @Override
    public void actualizaTabla(String tipoTabla, String id, String numeroReferencia) {

        if (tipoTabla.equals("AgaceOficio")) {
            actualizaReferenciaOficio(id, numeroReferencia);
        }

        if (tipoTabla.equals("AgaceOrden")) {
            actualizaReferenciaOrden(id, numeroReferencia);
        }

        if (tipoTabla.equals("AgaceOficioProrroga")) {
            actualizaReferenciaOficioProrroga(id, numeroReferencia);
        }

        if (tipoTabla.equals("AgaceOrdenProrroga")) {
            actualizaReferenciaOrdenProrroga(id, numeroReferencia);
        }

        if (tipoTabla.equals("AgacePruebasPericiales")) {
            actualizaReferenciaPruebaPericial(id, numeroReferencia);
        }
    }

}
