package mx.gob.sat.siat.feagace.negocio.common.firma.service;

import java.io.File;
import java.util.Map;

import mx.gob.sat.siat.feagace.modelo.dto.common.FirmaDTO;

public interface FirmaService {

    File guardarFirma(Map<String, FirmaDTO> firmas, Object obj, String rfcFirmante);
    
    void procesaPostCondiciones(Map<String, FirmaDTO> firmas, Object obj, String rfcFirmante);

}
