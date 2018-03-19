/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FecetDocumentoDao;

import org.apache.commons.io.IOUtils;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoUnidadesAdminXGeneral;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececArace;
import mx.gob.sat.siat.feagace.modelo.dto.common.DetalleEmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.enums.ReglaEnum;
import mx.gob.sat.siat.feagace.negocio.util.validacion.ValidacionParametrosUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class ServiceAbstract extends EmpleadoServiceImpl {

    private static final long serialVersionUID = -4804127901359593312L;

    private static final int SIZE_INIT = 50;

    protected static final Map<Integer, AraceDTO> MAP_UNIDAD_ADMINISTRATIVA = new HashMap<Integer, AraceDTO>(SIZE_INIT);
    @Autowired
    @Qualifier("serviceCatGrupoDeUnidadAdmin")
    private ServiceCatGrupoDeUnidadAdmin serviceCatGrupoDeUnidadAdmin;

    @Autowired
    @Qualifier("fecetDocumentoDao")
    private FecetDocumentoDao fecetDocumentoDao;

    protected static void getMapAraces(List<AraceDTO> lstUnidadesAdministrativas) {
        if (lstUnidadesAdministrativas != null && !lstUnidadesAdministrativas.isEmpty()) {
            for (AraceDTO unidadesAdministrativa : lstUnidadesAdministrativas) {
                MAP_UNIDAD_ADMINISTRATIVA.put(unidadesAdministrativa.getIdArace(), unidadesAdministrativa);
            }
        }
    }

    protected FececArace getAraceFromAraceDTO(AraceDTO araceDto) {
        if (araceDto != null) {
            FececArace fececArace = new FececArace();
            fececArace.setIdArace(new BigDecimal(araceDto.getIdArace()));
            fececArace.setCentral(araceDto.getCentral().isValue());
            fececArace.setNombre(araceDto.getNombre());
            fececArace.setSede(araceDto.getSede());
            return fececArace;
        }
        return null;
    }

    protected FececUnidadAdministrativa fillUnidadAdministrativa(Integer idUnidad) {
        FececUnidadAdministrativa unidad = null;
        if (idUnidad != null && MAP_UNIDAD_ADMINISTRATIVA.get(idUnidad) != null) {
            AraceDTO actualArace = MAP_UNIDAD_ADMINISTRATIVA.get(idUnidad);
            unidad = new FececUnidadAdministrativa();
            unidad.setIdUnidadAdministrativa(new BigDecimal(idUnidad));
            unidad.setNombre(actualArace.getNombre());
            unidad.setDescripcion(actualArace.getSede());
        }
        return unidad;
    }

    protected AraceDTO fillAraceDto(Integer idUnidad) {
        if (idUnidad != null && MAP_UNIDAD_ADMINISTRATIVA.get(idUnidad) != null) {
            return MAP_UNIDAD_ADMINISTRATIVA.get(idUnidad);
        }
        return null;
    }

    protected byte[] consultarArchivo(String rutaArchivo) {
        FileInputStream fileInputStream = null;
        try {
            File file = new File(rutaArchivo);
            byte[] bFile = new byte[(int) file.length()];
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            logger.info("convert file into array of bytes");
            return bFile;
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
            return null;
        } finally {
            try {
                if (fileInputStream != null) {
                    IOUtils.closeQuietly(fileInputStream);
                }
            } catch (Exception ex) {
                logger.error("No se pudo cerrar el archivo correctamente ", ex);
            }
        }
    }

    protected AraceDTO getIdCentralFromEmpleadoDto(EmpleadoDTO empleado) {
        if (empleado != null && empleado.getDetalleEmpleado() != null) {
            for (DetalleEmpleadoDTO detalle : empleado.getDetalleEmpleado()) {
                return detalle.getCentral();
            }
        }
        return null;
    }

    public boolean validarUnidadAdministrativaXRegla(EmpleadoDTO empleadoDto, ReglaEnum regla, List<GrupoUnidadesAdminXGeneral> lstGruposXRegla) {
        if (empleadoDto != null && empleadoDto.getIdAdmGral() != null && regla != null) {
            try {
                List<GrupoUnidadesAdminXGeneral> lstGrupos = lstGruposXRegla!=null&&!lstGruposXRegla.isEmpty()? lstGruposXRegla:getServiceCatGrupoDeUnidadAdmin().getLstGruposXGeneralXRegla(empleadoDto, regla);
                if (lstGrupos != null && !lstGrupos.isEmpty()) {
                    for (GrupoUnidadesAdminXGeneral grupo : lstGrupos) {
                        boolean[] condiciones = {!grupo.getLstUnidadesAdministrativas().isEmpty(), !empleadoDto.getDetalleEmpleado().isEmpty()};
                        if (ValidacionParametrosUtil.seCumplenTodasLasCondicion(condiciones)) {
                            for (AraceDTO unidadDeGrupo : grupo.getLstUnidadesAdministrativas()) {
                                for (DetalleEmpleadoDTO detalleEmp : empleadoDto.getDetalleEmpleado()) {
                                    if ((detalleEmp.getCentral().getIdArace() - (unidadDeGrupo.getIdArace())) == 0) {
                                        logger.info("detalleEmp.getCentral().getIdArace()==(unidadDeGrupo.getIdArace())" + true);
                                        return true;
                                    }
                                    logger.info("detalleEmp.getCentral().getIdArace()==(unidadDeGrupo.getIdArace())" + false);
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        return false;
    }

    public ServiceCatGrupoDeUnidadAdmin getServiceCatGrupoDeUnidadAdmin() {
        return serviceCatGrupoDeUnidadAdmin;
    }

    public void setServiceCatGrupoDeUnidadAdmin(ServiceCatGrupoDeUnidadAdmin serviceCatGrupoDeUnidadAdmin) {
        this.serviceCatGrupoDeUnidadAdmin = serviceCatGrupoDeUnidadAdmin;
    }

    public FecetDocumentoDao getFecetDocumentoDao() {
        return fecetDocumentoDao;
    }

    public void setFecetDocumentoDao(FecetDocumentoDao fecetDocumentoDao) {
        this.fecetDocumentoDao = fecetDocumentoDao;
    }

}
