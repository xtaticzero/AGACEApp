package mx.gob.sat.siat.feagace.vista.insumos.helper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.common.ImpuestoVO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocrechazoinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocretroinsumo;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.vista.insumos.validar.helper.ValidarProcedenciaInsumoDTOHelper;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

public class ValidarProcedenciaInsumoHelper implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public boolean validaNombreArchivoRechazo(List<FecetDocrechazoinsumo> listaDocumento, String nombre) {
        Boolean archivoDuplicado = false;
        for (FecetDocrechazoinsumo fecetDocrechazoinsumo : listaDocumento) {
            if (fecetDocrechazoinsumo.getNombreArchivo().equals(nombre)) {
                archivoDuplicado = true;
                break;
            }
        }
        return archivoDuplicado;
    }
    
    public boolean validaNombreArchivoRetroalimentacion(List<FecetDocretroinsumo> listaDocumento,
            String nombre) {
        Boolean archivoDuplicado = false;
        for (FecetDocretroinsumo fecetDocretroinsumo : listaDocumento) {
            if (fecetDocretroinsumo.getNombreArchivo().equals(nombre)) {
                archivoDuplicado = true;
                break;
            }
        }
        return archivoDuplicado;
    }
    
    public boolean validarImpuesto(ValidarProcedenciaInsumoDTOHelper validarProcedenciaInsumoDTOHelper) {
        boolean datosValidos = true;

        for (ImpuestoVO impuestos : validarProcedenciaInsumoDTOHelper.getListaImpuestos()) {
            if (impuestos.getDescripcion().equals(buscarDescripcionImpuesto(validarProcedenciaInsumoDTOHelper.getImpuestoVO().getIdImpuesto(), validarProcedenciaInsumoDTOHelper))) {
                FacesUtil.addErrorMessage("formValidarInsumo:cmbImpuesto", "El impuesto ya habia sido registrado");
                datosValidos = false;
            }
        }

        if (validarProcedenciaInsumoDTOHelper.getImpuestoVO().getIdImpuesto() == null) {
            FacesUtil.addErrorMessage("formValidarInsumo:cmbImpuesto", Constantes.CAMPO_OBLIGATORIO_IMPUESTO);
            datosValidos = false;
        }
        if (validarProcedenciaInsumoDTOHelper.getImpuestoVO().getMonto() == null) {
            FacesUtil.addErrorMessage("formValidarInsumo:txtMontoImpuesto", Constantes.CAMPO_OBLIGATORIO_IMPUESTO);
            datosValidos = false;
        }
        if (validarProcedenciaInsumoDTOHelper.getImpuestoVO().getFechaInicial() == null
                || validarProcedenciaInsumoDTOHelper.getImpuestoVO().getFechaFin() == null) {
            FacesUtil.addErrorMessage(Constantes.FORM_VALIDAR_INSUMO_CAMPO_PERIODO, "Campo(s) Obligatorio(s) *");
            datosValidos = false;
        } else {
            if (validarProcedenciaInsumoDTOHelper.getImpuestoVO().getFechaInicial().before(validarProcedenciaInsumoDTOHelper.getInsumoSeleccionado().getFechaInicioPeriodo())) {
                FacesUtil.addErrorMessage(Constantes.FORM_VALIDAR_INSUMO_CAMPO_PERIODO, "La fecha Perido Del no puede ser menor a la fecha Periodo Propuesto Del");
                datosValidos = false;
            }
            if (validarProcedenciaInsumoDTOHelper.getImpuestoVO().getFechaFin().after(validarProcedenciaInsumoDTOHelper.getInsumoSeleccionado().getFechaFinPeriodo())) {
                FacesUtil.addErrorMessage(Constantes.FORM_VALIDAR_INSUMO_CAMPO_PERIODO, "La fecha Perido Al no puede ser mayor a la fecha Periodo Propuesto Al");
                datosValidos = false;
            }
            if (validarProcedenciaInsumoDTOHelper.getImpuestoVO().getFechaInicial().after(validarProcedenciaInsumoDTOHelper.getImpuestoVO().getFechaFin())) {
                FacesUtil.addErrorMessage(Constantes.FORM_VALIDAR_INSUMO_CAMPO_PERIODO, "La fecha final no puede ser menor a la inicial");
                datosValidos = false;
            }
        }
        return datosValidos;
    }
    
    public String buscarDescripcionImpuesto(final BigDecimal idTipoImpuesto, ValidarProcedenciaInsumoDTOHelper validarProcedenciaInsumoDTOHelper) {
        String descripcion = "";
        for (FececTipoImpuesto impuesto : validarProcedenciaInsumoDTOHelper.getListaTipoImpuesto()) {
            if (impuesto.getIdTipoImpuesto().equals(idTipoImpuesto)) {
                descripcion = impuesto.getDescripcion();
                break;
            }
        }
        return descripcion;
    }
}
