package mx.gob.sat.siat.feagace.negocio.ordenes.pruebaspericiales.firma.service;

import java.math.BigDecimal;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.negocio.common.firma.service.FirmaService;

public interface FirmaPruebaPericialService extends FirmaService {

    EmpleadoDTO findFirmante(BigDecimal idFirmante);

}
