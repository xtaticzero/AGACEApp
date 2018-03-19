package mx.gob.sat.siat.feagace.negocio.ordenes.impl;

import java.io.File;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos.FecetDocTerceroDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AcuseCompulsaTercero;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocTercero;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.ordenes.CompulsaContribuyenteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("compulsaContribuyenteService")
public class CompulsaContribuyenteServiceImpl implements CompulsaContribuyenteService {

    @Autowired
    private FecetDocTerceroDao fecetDocTerceroDao;

    private List<String> jsonCompulsa = new ArrayList<String>();

    @Override
    public File generaAcuseRecivoPDF(final AcuseCompulsaTercero acuse) throws NegocioException {
        byte[] archivoPDF = ArchivoOrdenUtil.getArregloAcuseCompulsaTercero(acuse);
        ArchivoOrdenUtil.crearRutaDestino(acuse.getRutaAcuse().replace(Constantes.NOMBRE_ACUSE_RECIBO_COMPULSA_TERCERO,
                ""));
        return ArchivoOrdenUtil.byteToFile(acuse.getRutaAcuse(), archivoPDF);
    }

    @Override
    public BigDecimal guardarDocumentoCompulsaTercero(final FecetDocTercero documento) {

        return this.fecetDocTerceroDao.insert(documento).getIdDocTercero();

    }

    public void setFecetDocTerceroDao(final FecetDocTerceroDao fecetDocTerceroDao) {
        this.fecetDocTerceroDao = fecetDocTerceroDao;
    }

    public FecetDocTerceroDao getFecetDocTerceroDao() {
        return fecetDocTerceroDao;
    }

    public void setJsonCompulsa(List<String> jsonCompulsa) {
        this.jsonCompulsa = jsonCompulsa;
    }

    public List<String> getJsonCompulsa() {
        return jsonCompulsa;
    }
}
