package mx.gob.sat.siat.feagace.negocio.common.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtek.idc.constants.IDCConstants;
import com.softtek.idc.model.IdCInterno;
import com.softtek.idc.model.Rep_legal;
import com.softtek.idc.model.Ubicacion;
import com.softtek.idc.service.IDCService;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FecetContribuyenteDao;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.RepLegalVO;
import mx.gob.sat.siat.feagace.negocio.common.ContribuyenteService;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteContribuyenteException;

@Service("contribuyenteService")
public class ContribuyenteServiceImpl extends BaseBusinessServices implements ContribuyenteService {

    /**
     * Serial
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    private transient IDCService iDCService;
    @Autowired
    private transient FecetContribuyenteDao contribuyenteDao;

    public FecetContribuyente getContribuyenteIDC(final String rfcContribuyente) throws NoExisteContribuyenteException {
        IdCInterno contribuyenteIDC = null;
        FecetContribuyente contribuyente = new FecetContribuyente();

        String secciones[] = {IDCConstants.IDENTIFICACION, IDCConstants.UBICACION, IDCConstants.REPRESENTANTES_LEGALES};

        contribuyenteIDC = iDCService.obtenerInformacionContribuyente(rfcContribuyente, secciones);

        if (contribuyenteIDC.getIdentificacion() == null) {
            throw new NoExisteContribuyenteException("no.existe.contribuyente.idc");
        } else {
            String nombreCompleto = contribuyenteIDC.getIdentificacion().getNombre();
            String apellidoPaterno = contribuyenteIDC.getIdentificacion().getAp_Paterno();
            String apellidoMaterno = contribuyenteIDC.getIdentificacion().getAp_Materno();

            if (nombreCompleto == null && apellidoPaterno == null && apellidoMaterno == null) {
                contribuyente.setNombre("Sin nombre definido");
            } else {
                contribuyente.setNombre(nombreCompleto + " " + apellidoPaterno + " " + apellidoMaterno);
            }

            contribuyente.setRfc(rfcContribuyente.toUpperCase());
            if (contribuyenteIDC.getIdentificacion() != null) {
                contribuyente.setSituacion(contribuyenteIDC.getIdentificacion().getD_Sit_Cont() != null
                        ? contribuyenteIDC.getIdentificacion().getD_Sit_Cont().trim() : "Suspendido");
                contribuyente.setDomicilioFiscal(generaDireccionContribuyente(contribuyenteIDC.getUbicacion()));
                contribuyente.setSituacionDomicilio(contribuyenteIDC.getIdentificacion().getD_Sit_Dom() != null
                        ? contribuyenteIDC.getIdentificacion().getD_Sit_Dom().trim() : "Sin detalle");

                contribuyente.setTipo(contribuyenteIDC.getIdentificacion().getT_persona() != null
                        ? contribuyenteIDC.getIdentificacion().getT_persona().trim().equals("M") ? "PERSONA MORAL" : "PERSONA FISICA" : "Persona Fisica");
            }
            contribuyente
                    .setActividadPreponderante(contribuyenteIDC.getActividades() != null
                                    ? (contribuyenteIDC.getActividades().get(0) != null && contribuyenteIDC.getActividades().get(0).getD_Actividad() != null)
                                            ? contribuyenteIDC.getActividades().get(0).getD_Actividad().trim() : "Sin actividad preponderante"
                                    : "Sin actividad preponderante");
            contribuyente.setEntidad((contribuyenteIDC.getUbicacion() != null)
                    ? contribuyenteIDC.getUbicacion().getD_Ent_Fed() != null ? contribuyenteIDC.getUbicacion().getD_Ent_Fed() : "Sin Entidad" : "Sin Entidad");
            contribuyente.setRegimen(contribuyenteIDC.getRegimenes() != null
                    ? (contribuyenteIDC.getRegimenes().get(0) != null && contribuyenteIDC.getRegimenes().get(0).getD_Regimen() != null)
                            ? contribuyenteIDC.getRegimenes().get(0).getD_Regimen().trim() : "Sin regimen"
                    : "Sin regimen");

            contribuyente.setRepLegal(obtenerListaRepLegal(contribuyenteIDC));
        }

        return contribuyente;
    }

    private String generaDireccionContribuyente(final Ubicacion ubicacion) {
        StringBuilder direccion = new StringBuilder();
        if (ubicacion != null) {
            direccion.append((ubicacion.getCalle() != null) ? ubicacion.getCalle().trim() : "").append(" # ");
            direccion.append((ubicacion.getN_Exterior() != null) ? ubicacion.getN_Exterior().trim() : "").append(", ");
            direccion.append((ubicacion.getD_Colonia() != null) ? ubicacion.getD_Colonia().trim() : "").append(", ");
            direccion.append((ubicacion.getD_Municipio() != null) ? ubicacion.getD_Municipio().trim() : "").append(", ");
            direccion.append((ubicacion.getD_Ent_Fed() != null) ? ubicacion.getD_Ent_Fed().trim() : "").append(", C.P. ");
            direccion.append((ubicacion.getCp() != null) ? ubicacion.getCp().trim() : "");
        }
        return direccion.toString();
    }

    private List<RepLegalVO> obtenerListaRepLegal(IdCInterno contribuyenteIDC) {
        List<RepLegalVO> repLegalLista;
        if (contribuyenteIDC.getRep_Legales() != null) {
            repLegalLista = new ArrayList<RepLegalVO>();
            for (Rep_legal repLegal : contribuyenteIDC.getRep_Legales()) {
                RepLegalVO repLegalVO = new RepLegalVO();
                repLegalVO.setCurp(repLegal.getCURP());
                repLegalVO.setFechaFin(repLegal.getF_Fin());
                repLegalVO.setFechaInicio(repLegal.getF_Inicio());
                repLegalVO.setNombre(repLegal.getNombre());
                repLegalVO.setRfc(repLegal.getRFC());
                repLegalVO.setTipo(repLegal.getTipo());
                repLegalLista.add(repLegalVO);
            }
        } else {
            repLegalLista = null;
        }
        return repLegalLista;

    }

    public FecetContribuyente obtenerContribuyente(BigDecimal idContribuyente) throws NoExisteContribuyenteException {
        List<FecetContribuyente> listaContribuyente = contribuyenteDao.findWhereIdContribuyenteEquals(idContribuyente);

        if (listaContribuyente != null && !listaContribuyente.isEmpty()) {
            return listaContribuyente.get(0);
        } else {
            throw new NoExisteContribuyenteException("no.existe.contribuyente.idc");
        }

    }

}
