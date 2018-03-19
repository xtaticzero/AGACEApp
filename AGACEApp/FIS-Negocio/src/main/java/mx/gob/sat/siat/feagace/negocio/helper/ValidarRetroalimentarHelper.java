/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.helper;

import static mx.gob.sat.siat.feagace.modelo.util.Constantes.DIAGONAL;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.excepcion.ValidarRetroalimentarPropuestaException;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocRetroalimentacionDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropPendiente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroalimentacion;
import mx.gob.sat.siat.feagace.modelo.enums.EstadoBooleanodeRegistroEnum;
import mx.gob.sat.siat.feagace.modelo.enums.RutaArchivosEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAraceEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.rules.ValidarYRetroalimentarPropuestaRule;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtil;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
@Component("validarRetroalimentarHelper")
public class ValidarRetroalimentarHelper implements Serializable {

    private static final long serialVersionUID = 8705789539680808177L;

    private static final Logger LOG = Logger.getLogger(ValidarRetroalimentarHelper.class);

    private static final String ERROR_AL_OBTENER_ARCHIVO = "Error al obtener el archivo : {0}";

    @Autowired
    @Qualifier("validarYRetroalimentarPropuestaRule")
    private ValidarYRetroalimentarPropuestaRule validarYRetroalimentarPropuestaRule;

    public List<DocRetroalimentacionDTO> filtraDocRetroalimentacion(FecetPropuesta fecetPropuesta, List<DocRetroalimentacionDTO> lstDocRetroalimentacion) throws ValidarRetroalimentarPropuestaException {

        List<DocRetroalimentacionDTO> lstDocRetroalimentacionXActualizar = new ArrayList<DocRetroalimentacionDTO>();
        boolean docRepetido = false;

        String rutaDocRetroalimentacion = RutaArchivosUtil.generarRutaArchivoValidaPropuestas(
                RutaArchivosEnum.RUTA_DOCUMENTOS_RETROALIMENTACION_PROPUESTAS, fecetPropuesta);

        Date fechaCreacion = new Date();

        for (DocRetroalimentacionDTO docXActualiza : lstDocRetroalimentacion) {

            if (lstDocRetroalimentacionXActualizar.isEmpty()) {
                lstDocRetroalimentacionXActualizar.add(getDocRetroXActualizar(fecetPropuesta, docXActualiza, rutaDocRetroalimentacion, fechaCreacion));
                docRepetido = true;
            } else {
                for (DocRetroalimentacionDTO docTmp : lstDocRetroalimentacionXActualizar) {
                    docRepetido = docTmp.getNombreArchivo().equals(docXActualiza.getNombreArchivo());
                    if (docRepetido) {
                        break;
                    }
                }
            }

            if (!docRepetido) {
                lstDocRetroalimentacionXActualizar.add(getDocRetroXActualizar(fecetPropuesta, docXActualiza, rutaDocRetroalimentacion, fechaCreacion));
            }

        }

        return lstDocRetroalimentacionXActualizar;
    }

    public List<FecetPropuesta> combinatLstPropuesta(List<FecetPropuesta> lstPropuestasXValidar, List<FecetPropuesta> lstPropuestasCentralARegional) {
        List<FecetPropuesta> lstCompletaDePropuestasXValidar = new ArrayList<FecetPropuesta>();
        if (lstPropuestasXValidar != null && lstPropuestasCentralARegional != null) {
            for (FecetPropuesta propuestaXValidar : lstPropuestasXValidar) {
                lstCompletaDePropuestasXValidar.add(propuestaXValidar);
            }
            for (FecetPropuesta propuestaXValidar : lstPropuestasCentralARegional) {
                lstCompletaDePropuestasXValidar.add(propuestaXValidar);
            }
        }

                Collections.sort(lstCompletaDePropuestasXValidar, new Comparator<FecetPropuesta>() {

            @Override
            public int compare(FecetPropuesta lstCompletaDePropuestasXValidarA, FecetPropuesta lstCompletaDePropuestasXValidarB){
                return lstCompletaDePropuestasXValidarA.getPrioridadSugerida().compareTo(lstCompletaDePropuestasXValidarB.getPrioridadSugerida());
            }
        });
        
        return lstCompletaDePropuestasXValidar;

    }

    public boolean esRutaConArchivo(String pathFile) {
        return !(Constantes.DIAGONAL.equals(String.valueOf(pathFile.charAt(pathFile.length() - 1))));
    }

    
    
    
    public List<FecetPropPendiente> getDocumentosLstPendiente(List<FecetPropPendiente> lstDocPendiente) {
        for (FecetPropPendiente documento : lstDocPendiente) {

            try {

                if (esRutaConArchivo(documento.getRutaArchivo())) {
                    documento.setArchivo(new FileInputStream(documento.getRutaArchivo()));
                } else {
                    documento.setArchivo(new FileInputStream(documento.getRutaArchivo().
                            concat(documento.getNombreArchivo())));
                }

            } catch (FileNotFoundException ex) {
                LOG.error(ERROR_AL_OBTENER_ARCHIVO.concat(documento.getNombreArchivo()), ex);
                return lstDocPendiente;
            }

        }
        return lstDocPendiente;
    }
    
    
    public List<FecetRechazoPropuesta> getDocumentosLstRechazo(List<FecetRechazoPropuesta> lstDocRechazo) {
        for (FecetRechazoPropuesta documento : lstDocRechazo) {

            try {

                if (esRutaConArchivo(documento.getRutaArchivo())) {
                    documento.setArchivo(new FileInputStream(documento.getRutaArchivo()));
                } else {
                    documento.setArchivo(new FileInputStream(documento.getRutaArchivo().
                            concat(documento.getNombreArchivo())));
                }

            } catch (FileNotFoundException ex) {
                LOG.error(ERROR_AL_OBTENER_ARCHIVO.concat(documento.getNombreArchivo()), ex);
                return lstDocRechazo;
            }

        }
        return lstDocRechazo;
    }

    public List<FecetRetroalimentacion> getDocumentosLstRetro(List<FecetRetroalimentacion> lstDocRetroalimentacion) {
        for (FecetRetroalimentacion documento : lstDocRetroalimentacion) {

            try {

                if (esRutaConArchivo(documento.getRutaArchivo())) {
                    documento.setArchivo(new FileInputStream(documento.getRutaArchivo()));
                } else {
                    documento.setArchivo(new FileInputStream(documento.getRutaArchivo().
                            concat(documento.getNombreArchivo())));
                }

            } catch (FileNotFoundException ex) {
                LOG.error(ERROR_AL_OBTENER_ARCHIVO.concat(documento.getNombreArchivo()), ex);
                return lstDocRetroalimentacion;
            }

        }
        return lstDocRetroalimentacion;
    }

    public DocRetroalimentacionDTO getDocRetroXActualizar(FecetPropuesta propuesta, DocRetroalimentacionDTO docAgregado, String rutaDocRetroalimentacion, Date fechaCreacion) {
        DocRetroalimentacionDTO docRetro = new DocRetroalimentacionDTO();

        docRetro.setIdPropuesta(propuesta.getIdPropuesta());
        docRetro.setFechaCreacion(fechaCreacion);
        docRetro.setRutaArchivo(rutaDocRetroalimentacion.concat(docAgregado.getNombreArchivo()));
        docRetro.setNombreArchivo(docAgregado.getNombreArchivo());
        docRetro.setArchivo(docAgregado.getArchivo());
        docRetro.setBlnEstatus(EstadoBooleanodeRegistroEnum.ACTIVO);

        return docRetro;

    }

    public void guardarDocRetroalimentacion(List<DocRetroalimentacionDTO> lstDocRetroalimentacion) throws IOException {

        StringBuilder rutaArchivo = new StringBuilder();
        String[] carpetas;

        for (DocRetroalimentacionDTO doc : lstDocRetroalimentacion) {
            carpetas = doc.getRutaArchivo().split(DIAGONAL);

            if (carpetas.length > 0) {
                for (int i = 0; i < carpetas.length - 1; i++) {
                    rutaArchivo.append(carpetas[i]);
                    rutaArchivo.append(DIAGONAL);
                }
            }

            break;
        }

        for (DocRetroalimentacionDTO doc : lstDocRetroalimentacion) {

            if (doc.getArchivo() == null) {
                throw new IOException();
            }

            CargaArchivoUtil.guardarArchivo(doc.getArchivo(),
                    rutaArchivo.toString(),
                    doc.getNombreArchivo());
        }

    }

    public List<FecetPropuesta> filtroLstPropuestas(List<FecetPropuesta> lstPropuestas) {
        List<FecetPropuesta> lstPropuestasFiltradas = new ArrayList<FecetPropuesta>();
        for (FecetPropuesta propuesta : lstPropuestas) {
            if (validarYRetroalimentarPropuestaRule.validarFolioEsPropuestasXValidar(propuesta.getIdRegistro())) {
                lstPropuestasFiltradas.add(propuesta);
            }
        }
        return lstPropuestasFiltradas;
    }

    public List<FecetPropuesta> filtroLstCentralARegional(List<FecetPropuesta> lstPropuestas) {
        List<FecetPropuesta> lstPropuestasFiltradas = new ArrayList<FecetPropuesta>();

        for (FecetPropuesta propuesta : lstPropuestas) {
            if (validarYRetroalimentarPropuestaRule.validarFolioPropuestasCentralARegional(propuesta.getIdRegistro())) {
                lstPropuestasFiltradas.add(propuesta);
            }
        }

        return lstPropuestasFiltradas;
    }

    public List<FecetPropuesta> fitroFoliosPropuestaXVaslidar(List<FecetPropuesta> lstPropuestas) {
        List<FecetPropuesta> lstPropuestasFiltradas = new ArrayList<FecetPropuesta>();

        for (FecetPropuesta propuesta : lstPropuestas) {
            if ((validarYRetroalimentarPropuestaRule.validarFolioPropuestasCentralARegional(propuesta.getIdRegistro())) || (validarYRetroalimentarPropuestaRule.validarFolioEsPropuestasXValidar(propuesta.getIdRegistro()))) {
                lstPropuestasFiltradas.add(propuesta);
            }
        }

        return lstPropuestasFiltradas;
    }

    public List<FecetPropuesta> filtroLstConfirmRegional(List<FecetPropuesta> lstPropuestas) {
        List<FecetPropuesta> lstPropuestasFiltradas = new ArrayList<FecetPropuesta>();
        for (FecetPropuesta propuesta : lstPropuestas) {

            BigDecimal idEstatus = propuesta.getIdEstatus();
            for (TipoEstatusEnum estatusActual : TipoEstatusEnum.values()) {
                if (estatusActual.getId() == idEstatus.longValue()) {
                    if (validarYRetroalimentarPropuestaRule.validaFolioEstatusConfirmXRegional(propuesta.getRfcCreacion(), getTipoArace(propuesta.getIdArace()), propuesta.getIdRegistro(), estatusActual)) {
                        lstPropuestasFiltradas.add(propuesta);
                    } else {
                        LOG.debug(propuesta.getIdRegistro());
                    }
                }
            }

        }
        return lstPropuestasFiltradas;
    }

    public List<FecetPropuesta> obtenerArchivosPropuesta(List<FecetPropuesta> lstPropuestas) {
        for (FecetPropuesta propuesta : lstPropuestas) {
            for (FecetDocExpediente documento : propuesta.getListaDocumentos()) {
                try {

                    if (esRutaConArchivo(documento.getRutaArchivo())) {
                        documento.setArchivo(new FileInputStream(documento.getRutaArchivo()));
                    } else {
                        documento.setArchivo(new FileInputStream(documento.getRutaArchivo().
                                concat(documento.getNombre())));
                    }

                } catch (FileNotFoundException ex) {
                    LOG.error(ERROR_AL_OBTENER_ARCHIVO.concat(documento.getNombre()), ex);
                }
            }
        }

        return lstPropuestas;
    }

    public TipoAraceEnum getTipoArace(BigDecimal idArace) {
        if (idArace != null) {
            for (TipoAraceEnum tipoArace : TipoAraceEnum.values()) {
                if (tipoArace.getId() == idArace.longValue()) {
                    return tipoArace;
                }
            }
        }
        return null;
    }

}
