package mx.gob.sat.siat.feagace.health.service;

import mx.gob.sat.siat.feagace.health.dto.HealthDTO;


public interface HealthCheckService {
    
    HealthDTO validarCorreo(final String correo);
    
    HealthDTO validarIDC(final String rfc);
    
    HealthDTO validarSISE(final String rfc);
    
    HealthDTO validarBuzonSelladora(final String cadenaOriginal);

    HealthDTO validarEmpleadoPorTipoCentralArace(final String tipo, final String central, final String arace);
    
    HealthDTO validarMediosComunicacion(final String rfc);
    
    HealthDTO validarNyVConsultarActoAdministrativo(String folioActoAdministrativo);
}
