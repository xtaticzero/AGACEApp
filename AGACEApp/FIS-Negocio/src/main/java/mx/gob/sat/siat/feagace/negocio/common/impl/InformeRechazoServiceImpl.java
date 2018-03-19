package mx.gob.sat.siat.feagace.negocio.common.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececActividadPreponderanteDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.roles.FececAraceDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececActividadPreponderante;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececArace;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececEmpleado;
import mx.gob.sat.siat.feagace.negocio.common.InformeRechazoService;
import mx.gob.sat.siat.feagace.negocio.helper.InformeComiteRechazoHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("informeRechazoService")
public class InformeRechazoServiceImpl extends BaseBusinessServices implements InformeRechazoService {

    @Autowired
    private FececActividadPreponderanteDao fececActividadPreponderanteDao;

    @Autowired
    private FececAraceDao fececAraceDao;
    @Autowired
    private InformeComiteRechazoHelper helper;


    public List<String> construyeComboEntidad(FececEmpleado empleado) {
        FececArace fececArace;
        List<String> listaEntidades = new ArrayList<String>();
        

        if(helper.listaIdAraceRegional().contains(empleado.getFecetDetalleEmpleado().getIdCentral())){
            fececArace = fececAraceDao.findByPrimaryKey(empleado.getFecetDetalleEmpleado().getIdCentral());       
            if(fececArace!=null){
                StringTokenizer tokenEntidades;
                tokenEntidades = new StringTokenizer(fececArace.getSede(), ",");
                while (tokenEntidades.hasMoreTokens()) {
                    listaEntidades.add(tokenEntidades.nextToken().trim());
                }
                Collections.sort(listaEntidades);
            }
        }
        return listaEntidades;
    }

    public List<FececActividadPreponderante> construyeComboActividadPreponderante() {
        return getFececActividadPreponderanteDao().findAll();
    }

    public void setFececActividadPreponderanteDao(FececActividadPreponderanteDao fececActividadPreponderanteDao) {
        this.fececActividadPreponderanteDao = fececActividadPreponderanteDao;
    }

    public FececActividadPreponderanteDao getFececActividadPreponderanteDao() {
        return fececActividadPreponderanteDao;
    }

}
