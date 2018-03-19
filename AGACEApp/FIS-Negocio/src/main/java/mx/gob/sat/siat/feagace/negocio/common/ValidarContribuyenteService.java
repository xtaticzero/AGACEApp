package mx.gob.sat.siat.feagace.negocio.common;

import java.util.List;

import mx.gob.sat.siat.buzon.model.MedioComunicacion;


public interface ValidarContribuyenteService {
    void validaEstatusContacto(String rfc);
    void procesaStatusMediosContacto(List<MedioComunicacion> listMedioCom, String rfc);
    int validaPPEE(String rfc);
    String getEstatusDeContacto();
    String getMsjContactoAmparadoPPEE();
}
