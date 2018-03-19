package mx.gob.sat.siat.feagace.negocio.common.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.negocio.common.ConsultaAntecedentesInsumoService;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;

@Service("consultaAntecedentesInsumoService")
public class ConsultaAntecedentesInsumoServiceImpl extends ConsultaAntecedentesService implements ConsultaAntecedentesInsumoService {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @Value("${consulta.agaff.activo}")
    private String isConsultaAgaff;

    @Override
    @PistaAuditoria
    public List<FecetPropuesta> consultaSUIEFI(String rfc, Date periodoInicial, Date periodoFinal) throws NegocioException {
        return super.consultaSUIEFI(rfc, periodoInicial, periodoFinal);
    }

    @Override
    @PistaAuditoria
    public List<FecetPropuesta> consultaSICSEP(String rfc, Date periodoInicial, Date periodoFinal) throws NegocioException {
        return super.consultaSICSEP(rfc, periodoInicial, periodoFinal);
    }

    @Override
    @PistaAuditoria
    public List<FecetPropuesta> consultaAGAFF(String rfc, Date periodoInicial, Date periodoFinal) throws NegocioException {
        if (Boolean.parseBoolean(isConsultaAgaff)) {
            return super.consultaAGAFF(rfc, periodoInicial, periodoFinal);
        } else {
            return new ArrayList<FecetPropuesta>();
        }
    }

    @Override
    @PistaAuditoria
    public List<FecetPropuesta> consultaAGACE(String rfc, Date periodoInicial, Date periodoFinal) throws NegocioException {
        return super.consultaAGACE(rfc, periodoInicial, periodoFinal);
    }

    @Override
    @PistaAuditoria
    public List<FecetPropuesta> consultaAGACEPropuestasPeriodoExacto(String rfc, FecetPropuesta dto) throws NegocioException {
        return super.consultaAGACEPropuestasPeriodoExacto(rfc, dto);
    }

    @Override
    @PistaAuditoria
    public List<FecetInsumo> consultaAGACEInsumosPeriodoExacto(String rfc, FecetPropuesta dto) throws NegocioException {

        return super.consultaAGACEInsumosPeriodoExacto(rfc, dto);
    }

}
