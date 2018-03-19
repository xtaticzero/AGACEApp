package mx.gob.sat.siat.feagace.negocio.common.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.negocio.common.ConsultaAntecedentesPropuestasService;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;

@Service("consultaAntecedentesService")
public class ConsultaAntecedentesPropuestasServiceImpl extends ConsultaAntecedentesService
        implements ConsultaAntecedentesPropuestasService {

    /**
     * Serial
     */
    private static final long serialVersionUID = -8342376609494165591L;
    
    @Value("${consulta.agaff.activo}")
    private String isConsultaAgaff;
    
    @Override
    @PistaAuditoria
    public List<FecetPropuesta> consultaSUIEFI(String rfc, Date periodoInicial, Date periodoFinal)
            throws NegocioException {
        return super.consultaSUIEFI(rfc, periodoInicial, periodoFinal);
    }

    @Override
    @PistaAuditoria
    public List<FecetPropuesta> consultaSICSEP(String rfc, Date periodoInicial, Date periodoFinal)
            throws NegocioException {
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
    public List<FecetPropuesta> consultaAGACE(String rfc, Date periodoInicial, Date periodoFinal)
            throws NegocioException {
        return super.consultaAGACE(rfc, periodoInicial, periodoFinal);
    }

    @Override
    @PistaAuditoria
    public List<FecetPropuesta> consultaAGACEPropuestas(String rfc, FecetPropuesta dto) throws NegocioException {
        return super.consultaAGACEPropuestas(rfc, dto);
    }

    @Override
    @PistaAuditoria
    public List<FecetPropuesta> consultaAGACEPropuestasPeriodoExacto(String rfc, FecetPropuesta dto)
            throws NegocioException {
        return super.consultaAGACEPropuestasPeriodoExacto(rfc, dto);
    }

    @Override
    @PistaAuditoria
    public List<FecetInsumo> consultaAGACEInsumos(String rfc, FecetPropuesta dto) throws NegocioException {
        return super.consultaAGACEInsumos(rfc, dto);
    }

    @Override
    @PistaAuditoria
    public List<FecetInsumo> consultaAGACEInsumosPeriodoExacto(String rfc, FecetPropuesta dto) throws NegocioException {
        return super.consultaAGACEInsumosPeriodoExacto(rfc, dto);
    }

    @Override
    @PistaAuditoria
    public HSSFWorkbook creaExcel(List<FecetPropuesta> revisionesAgace, List<FecetPropuesta> revisionesAgaff,
            List<FecetPropuesta> revisionesSUIEFIs, List<FecetPropuesta> revisionesSICSEP,
            List<FecetInsumo> revisionesInsumos, boolean muestraInsumo) {
        return super.creaExcel(revisionesAgace, revisionesAgaff, revisionesSUIEFIs, revisionesSICSEP, revisionesInsumos,
                muestraInsumo);
    }

    @Override
    public void getEmpleadoProgramador(final String rfc) throws NegocioException {
        try {
            if (!validarExistenciaTipoEmpleado(getEmpleadoCompleto(rfc),
                    TipoEmpleadoEnum.PROGRAMADOR_CONSULTA_ANTECEDENTES)) {
                throw new NegocioException("No se pudo obtener la informacion del usuario logueado");
            }
        } catch (EmpleadoServiceException ex) {
            logger.error(ex.getMessage(),ex);
            throw new NegocioException("No se pudo obtener la informacion del usuario logueado",ex);
        }
    }

}
