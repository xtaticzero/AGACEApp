/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.common.catalogs;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mx.gob.sat.siat.base.BaseTest;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.CatalogoGrupoDeUnidadAdminDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececModulosDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.UnidadAdminModuloDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececModulos;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoUnidadesAdminXGeneral;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.ModuloUnidadAdmin;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.enums.ReglaEnum;
import mx.gob.sat.siat.feagace.modelo.enums.catalogos.ListasAsignacionEnum;
import mx.gob.sat.siat.feagace.negocio.EmpleadoService;
import mx.gob.sat.siat.feagace.negocio.ServiceCatGrupoDeUnidadAdmin;
import mx.gob.sat.siat.feagace.negocio.ServiceCatUnidadAdminXModulo;
import mx.gob.sat.siat.feagace.negocio.exception.CatalogosServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class CatalogosTest extends BaseTest {

    @Autowired
    @Qualifier("modulosDaoImpl")
    private FececModulosDao modulosDaoImpl;

    @Autowired
    @Qualifier("unidadAdminModuloDao")
    private UnidadAdminModuloDao unidadAdminModuloDao;

    @Autowired
    @Qualifier("serviceCatUnidadAdminXModulo")
    private ServiceCatUnidadAdminXModulo serviceCatUnidadAdminXModulo;

    @Autowired
    @Qualifier("serviceCatGrupoDeUnidadAdmin")
    private ServiceCatGrupoDeUnidadAdmin serviceCatGrupoDeUnidadAdmin;

    @Autowired
    @Qualifier("catGrupoDeUnidadAdminDao")
    private CatalogoGrupoDeUnidadAdminDao catGrupoDeUnidadAdminDao;

    @Autowired
    @Qualifier("empleadoService")
    private EmpleadoService empleadoService;

    private String rfcEmpleado;
    private BigDecimal idGeneral;
    private EmpleadoDTO empleado;
    private List<AraceDTO> lstSrc;
    private List<AraceDTO> lstTrgt;
    private List<AraceDTO> lstUnidadesAdminCompleta;

    @Before
    public void init() {

        try {
            idGeneral = new BigDecimal(19);
            assert modulosDaoImpl != null;
            assert unidadAdminModuloDao != null;
            assert serviceCatUnidadAdminXModulo != null;
            assert empleadoService != null;
            assert catGrupoDeUnidadAdminDao != null;
            assert serviceCatGrupoDeUnidadAdmin != null;
//            rfcEmpleado = "GUAR590116V62";
            rfcEmpleado = "RAMA660502SP6";

            empleado = empleadoService.getEmpleadoCompleto(rfcEmpleado);

            lstUnidadesAdminCompleta = empleadoService.getUnidadesAdministrativasXGeneral(empleado);

        } catch (EmpleadoServiceException ex) {
            logger.error(ex.getCause(), ex);
            assert false;
        }
    }

    @Test
    @Ignore
    @Transactional(rollbackFor = Exception.class)
    public void getModulos() {
        List<FececModulos> lstModulos = modulosDaoImpl.findAll();
        assert lstModulos != null && !lstModulos.isEmpty();
        System.out.println("===================getModulos==================");
        for (FececModulos modulo : lstModulos) {
            logger.error(modulo);
            System.out.println(modulo);
        }
//        System.out.println("===================update==================");
//        for (FececModulos modulo : lstModulos) {
//            modulo.setDescripcion("preba1_".concat(modulo.getIdModulo().toString()));
//            modulosDaoImpl.update(modulo);
//            System.out.println(modulo);
//            modulo.setDescripcion(null);
//            modulosDaoImpl.update(modulo);
//        }
//
//        List<ModuloUnidadAdmin> lst = unidadAdminModuloDao.findAll(idGeneral);
//        for (ModuloUnidadAdmin unidadMod : lst) {
//            System.out.println(unidadMod);
//        }

    }

    @Test
    @Ignore
    public void getLstUnidadModuloGeneralTest() throws CatalogosServiceException {
        List<FececModulos> lstModulos = serviceCatUnidadAdminXModulo.getLstModulos(empleado);
        Map<Integer, AraceDTO> mapUnidades = serviceCatUnidadAdminXModulo.init(empleado);
        System.out.println("=====================getLstUnidadModuloGeneralTest===================");

        List<ModuloUnidadAdmin> lstModuloUnidad = serviceCatUnidadAdminXModulo.getLstUnidaesAdminXGeneralModulo(idGeneral);

        if (lstModuloUnidad != null) {
            System.out.println("tamaño lst actual : " + lstModuloUnidad.size());
            for (FececModulos modulo : lstModulos) {
                System.out.println("El modulo seleccionado: ".concat(modulo.toString()));

                lstModuloUnidad = serviceCatUnidadAdminXModulo.getLstUnidaesAdminXGeneralModulo(idGeneral, modulo.getIdModulo());

                Map<ListasAsignacionEnum, List<AraceDTO>> mapLst = serviceCatUnidadAdminXModulo.getListasDeAsignacion(lstModuloUnidad, lstUnidadesAdminCompleta);

                lstSrc = mapLst.get(ListasAsignacionEnum.LST_X_ASIGNAR);
                lstTrgt = mapLst.get(ListasAsignacionEnum.LST_ASIGNADA);

                int indice = 0;

//                Iterator<AraceDTO> iterLstSrc = lstSrc.iterator();
//                while (iterLstSrc.hasNext()&&indice<2) {
//                    AraceDTO next = iterLstSrc.next();
//                    lstTrgt.add(next);
//                    iterLstSrc.remove();
//                    indice++;
//                }
                serviceCatUnidadAdminXModulo.guardarRelacionUnidadModulo(lstSrc, lstTrgt, idGeneral.intValue(), modulo.getIdModulo().intValue());

                return;

            }

            if (mapUnidades != null) {
                for (Map.Entry<Integer, AraceDTO> unidadAdminKey : mapUnidades.entrySet()) {
                    System.out.println("id: ".concat(unidadAdminKey.getKey().toString()).concat(" Nombre: ").concat(unidadAdminKey.getValue().getNombre()));
                }
            }
        }

    }

    @Test
    @Ignore
    public void addModulo() {
        try {
            FececModulos modulo = new FececModulos();
            modulo.setNombre("seguimiento");
            serviceCatUnidadAdminXModulo.agregarModulo(modulo);
        } catch (CatalogosServiceException ex) {
            logger.error(ex);
        }
    }

    @Test
    @Ignore
    public void catGrupoDeUnidadAdminDaoTest() {
        try {
            List<GrupoUnidadesAdminXGeneral> lstGrupo;
            lstGrupo = catGrupoDeUnidadAdminDao.findByIdAdmGralIdGrupoIdRegla(idGeneral, BigDecimal.ONE, new BigDecimal(37));
            if (lstGrupo != null && !lstGrupo.isEmpty()) {
                for (GrupoUnidadesAdminXGeneral grup : lstGrupo) {
                    System.out.println(grup.getGrupo().getIdGrupo());
                    System.out.println(grup.getIdAdmGral().intValue());
                }

            }
        } catch (Exception ex) {
            logger.error(ex);
        }
    }

    @Test
    public void CatalogoGrupoDeUnidadAdminServiceTest() {
        try {
            List<GrupoUnidadesAdminXGeneral> lstGrupos = serviceCatGrupoDeUnidadAdmin.getLstGruposXGeneralXRegla(empleado, ReglaEnum.RNA037);
            assert lstGrupos != null && !lstGrupos.isEmpty();
            for (GrupoUnidadesAdminXGeneral grupo : lstGrupos) {
                System.out.println(grupo);
            }
            lstGrupos = serviceCatGrupoDeUnidadAdmin.getLstGruposXGeneralXRegla(empleado, ReglaEnum.RNA037);
        } catch (Exception e) {
            logger.error("error CatalogoGrupoDeUnidadAdminServiceTest", e);
        }
    }

}
