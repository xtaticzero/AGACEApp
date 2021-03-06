/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.propuestas.helper;

import org.primefaces.model.UploadedFile;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececEmpleado;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.ImpuestoVO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class PropuestaIndividualDTOHelper extends PropuestaIndividualDTOAbstract {

    private static final long serialVersionUID = -737247564668693085L;

    private FecetContribuyente contribuyente;
    private FecetPropuesta propuesta;
    private FecetDocExpInsumo documentoInsumoSeleccionado;
    private FececEmpleado fececEmpleado;
    private FecetInsumo fecetInsumo;
    private transient ImpuestoVO impuestoVO;
    private transient ImpuestoVO impuestoSeleccionado;
    private transient UploadedFile archivoCarga;
    private transient EmpleadoDTO empleadoDTO;

    public void setFecetInsumo(FecetInsumo fecetInsumo) {
        this.fecetInsumo = fecetInsumo;
    }

    public FecetInsumo getFecetInsumo() {
        return fecetInsumo;
    }

    public void setDocumentoInsumoSeleccionado(FecetDocExpInsumo documentoInsumoSeleccionado) {
        this.documentoInsumoSeleccionado = documentoInsumoSeleccionado;
    }

    public FecetDocExpInsumo getDocumentoInsumoSeleccionado() {
        return documentoInsumoSeleccionado;
    }

    public void setImpuestoVO(ImpuestoVO impuestoVO) {
        this.impuestoVO = impuestoVO;
    }

    public ImpuestoVO getImpuestoVO() {
        return impuestoVO;
    }

    public void setImpuestoSeleccionado(ImpuestoVO impuestoSeleccionado) {
        this.impuestoSeleccionado = impuestoSeleccionado;
    }

    public ImpuestoVO getImpuestoSeleccionado() {
        return impuestoSeleccionado;
    }

    public void setFececEmpleado(FececEmpleado fececEmpleado) {
        this.fececEmpleado = fececEmpleado;
    }

    public FececEmpleado getFececEmpleado() {
        return fececEmpleado;
    }

    public void setContribuyente(final FecetContribuyente contribuyente) {
        this.contribuyente = contribuyente;
    }

    public FecetContribuyente getContribuyente() {
        return contribuyente;
    }

    public void setPropuesta(FecetPropuesta propuesta) {
        this.propuesta = propuesta;
    }

    public FecetPropuesta getPropuesta() {
        return propuesta;
    }

    public void setArchivoCarga(final UploadedFile archivoCarga) {
        this.archivoCarga = archivoCarga;
    }

    public UploadedFile getArchivoCarga() {
        return archivoCarga;
    }

    public EmpleadoDTO getEmpleadoDTO() {
        return empleadoDTO;
    }

    public void setEmpleadoDTO(EmpleadoDTO empleadoDTO) {
        this.empleadoDTO = empleadoDTO;
    }

}
