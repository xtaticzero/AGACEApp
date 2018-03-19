package mx.gob.sat.siat.feagace.negocio.ordenes;

import java.io.File;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececApodLegal;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AcuseRevisionElectronica;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;

public interface DocumentacionContribuyenteService {

    FececApodLegal getApoderadoLegal(final FececApodLegal fececApodLegal);

    BigDecimal guardarApoderadoLegal(final FececApodLegal apoderadoLegal);

    void actualizaApoderadoLegal(final FececApodLegal apoderadoLegal);

    void enviarFirmarDocumentos(String jsonFirmado);

    File generaAcuseRecivoPDF(AcuseRevisionElectronica acuse) throws NegocioException;

    List<String> getOrdenCadenasJson(AgaceOrden ordenSelecciona);
}
