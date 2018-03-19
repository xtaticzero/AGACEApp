package mx.gob.sat.siat.feagace.negocio.common.impl;

import java.util.List;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.buzon.exception.BuzonNoDisponibleException;
import mx.gob.sat.siat.buzon.model.MedioComunicacion;
import mx.gob.sat.siat.buzon.service.BuzonTributarioService;
import mx.gob.sat.siat.feagace.negocio.common.ValidarContribuyenteService;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesPropuestas;
import mx.gob.sat.siat.sise.service.SiseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("validarContribuyenteService")
public class ValidarContribuyenteServiceImpl extends BaseBusinessServices implements ValidarContribuyenteService {

    @SuppressWarnings("compatibility:512985842619694088")
    private static final long serialVersionUID = 1965072983622518408L;
    
    private String estatusDeContacto;
    private String msjContactoAmparadoPPEE;
        
    @Autowired
    private transient BuzonTributarioService buzonTributarioService;
    
    @Autowired
    private transient SiseService siseService;
    
    @Override
    public void validaEstatusContacto(String rfc){ 
        this.setMsjContactoAmparadoPPEE(null);
        logger.info("--- Va a consultar medio contacto rfc:" + rfc);
        List<MedioComunicacion> listaMedios =null;
        try {
            listaMedios = buzonTributarioService.obtenerMediosComunicacion(rfc);
        } catch (BuzonNoDisponibleException e) {
            logger.error("No se pudo acceder al buzon de notificaciones:" + e);
            msjContactoAmparadoPPEE = ConstantesPropuestas.ERROR_CONSULTAMEDIOS;
            return;
        }
        procesaStatusMediosContacto(listaMedios, rfc);
        logger.info("--- listaMedios size:" + listaMedios.size());        
    }
    
    @Override
    public void procesaStatusMediosContacto(List<MedioComunicacion> listMedioCom, String rfc){
        this.setEstatusDeContacto(null);
        int valido = 0;
        for(MedioComunicacion medioComunicacion : listMedioCom){
                if(medioComunicacion.getDescMedio() != null && !medioComunicacion.getDescMedio().trim().equals("") && medioComunicacion.getDescMedio().toUpperCase().contains(ConstantesPropuestas.CORREO)){
                    logger.info("--- Descripcion medio comunicacion:" + medioComunicacion.getDescMedio());
                    if(medioComunicacion.getMedio() != null && !medioComunicacion.getMedio().trim().equals("")){
                        logger.info("--- Medio comunicacion:" + medioComunicacion.getMedio());
                        estatusDeContacto = ConstantesPropuestas.CON_MEDIOS;
                        logger.info("--- Amparado:" + medioComunicacion.getAmparado());
                        if(medioComunicacion.getAmparado() == 0){
                            valido = validaPPEE(rfc);
                            if(valido == -1){
                                msjContactoAmparadoPPEE = ConstantesPropuestas.ERROR_CONSULTAMEDIOS;
                            } else if(valido == 0){
                                msjContactoAmparadoPPEE = "lbl.propuestas.contribuyente.ppee";
                            }
                        } else {
                            msjContactoAmparadoPPEE = "lbl.propuestas.contribuyente.amparado";
                        }                
                    }else{
                        estatusDeContacto = ConstantesPropuestas.SIN_MEDIOS;
                    }
                }else{
                        estatusDeContacto = ConstantesPropuestas.SIN_MEDIOS;
                }
        }
    } 
    
    @Override
    public int validaPPEE(String rfc){
        logger.info("--- va a validar ppee");
        int info;
        try{
            info = siseService.verInformacion(rfc);
        }catch(Exception e){
            logger.error("No se pudo acceder al servicio SISE:" + e);
            return -1;
        }      
        logger.info("--- PPEE:" + info);
        return info;
    }
    
    public void setEstatusDeContacto(String estatusDeContacto) {
        this.estatusDeContacto = estatusDeContacto;
    }

    @Override
    public String getEstatusDeContacto() {
        return estatusDeContacto;
    }

    public void setMsjContactoAmparadoPPEE(String msjContactoAmparadoPPEE) {
        this.msjContactoAmparadoPPEE = msjContactoAmparadoPPEE;
    }

    @Override
    public String getMsjContactoAmparadoPPEE() {
        return msjContactoAmparadoPPEE;
    }

    public void setBuzonTributarioService(BuzonTributarioService buzonTributarioService) {
        this.buzonTributarioService = buzonTributarioService;
    }

    public BuzonTributarioService getBuzonTributarioService() {
        return buzonTributarioService;
    }

    public void setSiseService(SiseService siseService) {
        this.siseService = siseService;
    }

    public SiseService getSiseService() {
        return siseService;
    }
}
