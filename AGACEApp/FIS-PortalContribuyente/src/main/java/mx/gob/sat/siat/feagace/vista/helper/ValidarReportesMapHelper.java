package mx.gob.sat.siat.feagace.vista.helper;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.sat.siat.base.helper.BaseHelper;
import mx.gob.sat.siat.feagace.modelo.dto.common.ReportePistaAuditoriaExternaDTO;

import org.springframework.stereotype.Component;

@Component("validarReportesMapHelper")
public class ValidarReportesMapHelper extends BaseHelper {

    @SuppressWarnings("compatibility:-7373250036175565389")
    private static final long serialVersionUID = -9020273470241574564L;

    private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/M/yyyy");

    public ValidarReportesMapHelper() {
        super();
    }

    public List<Map<String, ?>> listaPistaAudit(List<ReportePistaAuditoriaExternaDTO> listRepExterno) {
        List<Map<String, ?>> pistaAudit = new ArrayList<Map<String, ?>>();
        for (ReportePistaAuditoriaExternaDTO dto : listRepExterno) {
            Map<String, Object> mapDto = new HashMap<String, Object>();

            mapDto.put("fechaYHora", dto.getFechaYHora() != null ? this.getFormatoFecha().format(dto.getFechaYHora()) : "");
            mapDto.put("rfcContribuyente", dto.getRfcContribuyente() != null ? dto.getRfcContribuyente() : "");
            mapDto.put("rfcRepLegal", dto.getRfcRepLegal() != null ? dto.getRfcRepLegal() : "");
            mapDto.put("rfcApodLegal", dto.getRfcApodLegal() != null ? dto.getRfcApodLegal() : "");
            mapDto.put("rfcAgentAduanal", dto.getRfcAgentAduanal() != null ? dto.getRfcAgentAduanal() : "");
            mapDto.put("rfcApodLegalRepLegal", dto.getRfcApodLegalRepLegal() != null ? dto.getRfcApodLegalRepLegal() : "");
            mapDto.put("ipMaquina", dto.getIpMaquina() != null ? dto.getIpMaquina() : "");
            mapDto.put("modIngreso", dto.getModIngreso() != null ? dto.getModIngreso() : "");
            mapDto.put("operRealizada", dto.getOperRealizada() != null ? dto.getOperRealizada() : "");
            pistaAudit.add(mapDto);
        }
        return pistaAudit;
    }

    public void setFormatoFecha(SimpleDateFormat formatoFecha) {
        this.formatoFecha = formatoFecha;
    }

    public SimpleDateFormat getFormatoFecha() {
        return formatoFecha;
    }
}
