package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class ContadorPropuestasAsignadas extends BaseModel{

    @SuppressWarnings("compatibility:-2679467640201925024")
    private static final long serialVersionUID = 1L;
    
    /**
     * Este atributo mapea el valor de la columna TOTAL_ASIGNADAS_FIRMANTE obtenido de un conteo de 
     * propuestas asignadas al auditor para el estatus Registro Asignado a Auditor
     */
    private Long totalAsignadasFirmante;
    
    /**
     * Este atributo mapea el valor de la columna TOTAL_RETROALIMENTADAS_ATENDER obtenido de un conteo de 
     * propuestas asignadas al auditor para los estatus Retroalimentada y .
     */
    private Long totalRetroAtender;
    
    /**
     * Este atributo mapea el valor de la columna TOTAL_FIRMANTE_ATENDER obtenido de un conteo de 
     * propuestas asignadas al auditor para el estatus Propuesta Rechazada para adecuar por auditor.
     */
    private Long totalFirmanteAtender;
    
    /**
     * Este atributo mapea el valor de la columna TOTAL_EMISION_ATENDER obtenido de un conteo de 
     * propuestas asignadas al auditor para el estatus Propuesta rechazada Pendiente Verificacion de Procedencia
     * de Documento Electronico.
     */
    private Long totalEmisionAtender;
    
    /**
     * Este atributo mapea el valor de la columna TOTAL_CANCELADAS_ATENDER obtenido de un conteo de 
     * propuestas asignadas al auditor para el estatus Cancelacion no Aprobada.
     */
    private Long totalCanceladasAtender;
    
    /**
     * Este atributo mapea el valor de la columna TOTAL_TRANSFERIDAS_ATENDER obtenido de un conteo de 
     * propuestas asignadas al auditor para los estatus Transferencia no Aprobada y Transferida.
     */
    private Long totalTransferidasAtender;
    
    /**
     * Este atributo mapea el valor de la columna TOTAL_RECHAZADAS_ATENDER obtenido de un conteo de 
     * propuestas asignadas al auditor para el estatus No aprobado el rechazo.
     */
    private Long totalRechazadasAtender;
    
    /**
     * Este atributo mapea el valor de la columna TOTAL_RETRO_VALIDACION obtenido de un conteo de 
     * propuestas asignadas al auditor para el estatus Retroalimentadas Pendientes de Validacion.
     */
    private Long totalRetroValidacion;
    
    /**
     * Este atributo mapea el valor de la columna TOTAL_FIRMANTE_VALIDACION obtenido de un conteo de 
     * propuestas asignadas al auditor para el estatus Pendiente de Validacion.
     */
    private Long totalFirmanteValidacion;
    
    /**
     * Este atributo mapea el valor de la columna TOTAL_EMISION_VALIDACION obtenido de un conteo de 
     * propuestas asignadas al auditor para los estatus Enviada para verificacion de Procedencia y
     * Registro asignado a Subadministrador de Emision de Ordenes.
     */
    private Long totalEmisionValidacion;
    
    /**
     * Este atributo mapea el valor de la columna TOTAL_CANCELADAS_VALIDACION obtenido de un conteo de 
     * propuestas asignadas al auditor para el estatus Cancelacion Pendiente de Validacion.
     */
    private Long totalCancelacionValidacion;
    
    /**
     * Este atributo mapea el valor de la columna TOTAL_TRANSFERIDAS_VALIDACION obtenido de un conteo de 
     * propuestas asignadas al auditor para el estatus Registro Enviado a Aprobacion de Transferido.
     */
    private Long totalTransferenciaValidacion;
    
    /**
     * Este atributo mapea el valor de la columna TOTAL_RECHAZADAS_VALIDACION obtenido de un conteo de 
     * propuestas asignadas al auditor para el estatus Propuesta Rechazada pendiente de Validacion.
     */
    private Long totalRechazoValidacion;
    
    /**
     * Este atributo mapea el valor de la columna TOTAL_PROGRAMACION_VALIDACION obtenido de un conteo de 
     * propuestas asignadas al auditor para el estatus Registro en Retroalimentacion.
     */
    private Long totalProgramacionValidacion;
    
    /**
     * Este atributo mapea el valor de la columna TOTAL_CONCLUIDAS_CANCELACION obtenido de un conteo de 
     * propuestas asignadas al auditor para el estatus Propuesta Concluida y la accion fue concluida
     * por la Cancelacion de la Propuesta.
     */
    private Long totalConcluidasCancelacion;
    
    /**
     * Este atributo mapea el valor de la columna TOTAL_CONCLUIDAS_RECHAZO obtenido de un conteo de 
     * propuestas asignadas al auditor para el estatus Propuesta Concluida y la accion fue concluida
     * por la aprobacion del Rechazo de la Propuesta.
     */
    private Long totalConcluidasRechazo;
    
    /**
     * Este atributo mapea el valor de la columna ID_PROPUESTA en la tabla FECET_PROPUESTA
     */
    private BigDecimal idPropuesta;
    
    /**
     * Este atributo mapea el valor de la columna ID_ARACE en la tabla FECET_PROPUESTA.
     */
    private BigDecimal idArace;
    
    /**
     * Este atributo mapea el valor de la ID_ESTATUS en la tabla FECET_PROPUESTA.
     */
    private BigDecimal idEstatus;

    /**
     * Metodo setTotalAsignadasFirmante
     * @param totalAsignadasFirmante
     */
    public void setTotalAsignadasFirmante(Long totalAsignadasFirmante) {
        this.totalAsignadasFirmante = totalAsignadasFirmante;
    }

    /**
    * Metodo getTotalAsignadasFirmante
    * @return Long
    */
    public Long getTotalAsignadasFirmante() {
        return totalAsignadasFirmante;
    }

    /**
     * Metodo setTotalRetroAtender
     * @param totalRetroAtender
     */
    public void setTotalRetroAtender(Long totalRetroAtender) {
        this.totalRetroAtender = totalRetroAtender;
    }

    /**
    * Metodo getTotalRetroAtender
    * @return Long
    */
    public Long getTotalRetroAtender() {
        return totalRetroAtender;
    }

    /**
     * Metodo setTotalFirmanteAtender
     * @param totalFirmanteAtender
     */
    public void setTotalFirmanteAtender(Long totalFirmanteAtender) {
        this.totalFirmanteAtender = totalFirmanteAtender;
    }

    /**
    * Metodo getTotalFirmanteAtender
    * @return Long
    */
    public Long getTotalFirmanteAtender() {
        return totalFirmanteAtender;
    }

    /**
     * Metodo setTotalEmisionAtender
     * @param totalEmisionAtender
     */
    public void setTotalEmisionAtender(Long totalEmisionAtender) {
        this.totalEmisionAtender = totalEmisionAtender;
    }

    /**
    * Metodo getTotalEmisionAtender
    * @return Long
    */
    public Long getTotalEmisionAtender() {
        return totalEmisionAtender;
    }

    /**
     * Metodo setTotalCanceladasAtender
     * @param totalCanceladasAtender
     */
    public void setTotalCanceladasAtender(Long totalCanceladasAtender) {
        this.totalCanceladasAtender = totalCanceladasAtender;
    }

    /**
    * Metodo getTotalCanceladasAtender
    * @return Long
    */
    public Long getTotalCanceladasAtender() {
        return totalCanceladasAtender;
    }

    /**
     * Metodo setTotalTransferidasAtender
     * @param totalTransferidasAtender
     */
    public void setTotalTransferidasAtender(Long totalTransferidasAtender) {
        this.totalTransferidasAtender = totalTransferidasAtender;
    }

    /**
    * Metodo getTotalTransferidasAtender
    * @return Long
    */
    public Long getTotalTransferidasAtender() {
        return totalTransferidasAtender;
    }

    /**
     * Metodo setTotalRechazadasAtender
     * @param totalRechazadasAtender
     */
    public void setTotalRechazadasAtender(Long totalRechazadasAtender) {
        this.totalRechazadasAtender = totalRechazadasAtender;
    }

    /**
    * Metodo getTotalRechazadasAtender
    * @return Long
    */
    public Long getTotalRechazadasAtender() {
        return totalRechazadasAtender;
    }

    /**
     * Metodo setTotalRetroValidacion
     * @param totalRetroValidacion
     */
    public void setTotalRetroValidacion(Long totalRetroValidacion) {
        this.totalRetroValidacion = totalRetroValidacion;
    }

    /**
    * Metodo getTotalRetroValidacion
    * @return Long
    */
    public Long getTotalRetroValidacion() {
        return totalRetroValidacion;
    }

    /**
     * Metodo setTotalFirmanteValidacion
     * @param totalFirmanteValidacion
     */
    public void setTotalFirmanteValidacion(Long totalFirmanteValidacion) {
        this.totalFirmanteValidacion = totalFirmanteValidacion;
    }

    /**
    * Metodo getTotalFirmanteValidacion
    * @return Long
    */
    public Long getTotalFirmanteValidacion() {
        return totalFirmanteValidacion;
    }

    /**
     * Metodo setTotalEmisionValidacion
     * @param totalEmisionValidacion
     */
    public void setTotalEmisionValidacion(Long totalEmisionValidacion) {
        this.totalEmisionValidacion = totalEmisionValidacion;
    }

    /**
    * Metodo getTotalEmisionValidacion
    * @return Long
    */
    public Long getTotalEmisionValidacion() {
        return totalEmisionValidacion;
    }

    /**
     * Metodo setTotalCancelacionValidacion
     * @param totalCancelacionValidacion
     */
    public void setTotalCancelacionValidacion(Long totalCancelacionValidacion) {
        this.totalCancelacionValidacion = totalCancelacionValidacion;
    }

    /**
    * Metodo getTotalCancelacionValidacion
    * @return Long
    */
    public Long getTotalCancelacionValidacion() {
        return totalCancelacionValidacion;
    }

    /**
     * Metodo setTotalTransferenciaValidacion
     * @param totalTransferenciaValidacion
     */
    public void setTotalTransferenciaValidacion(Long totalTransferenciaValidacion) {
        this.totalTransferenciaValidacion = totalTransferenciaValidacion;
    }

    /**
    * Metodo getTotalTransferenciaValidacion
    * @return Long
    */
    public Long getTotalTransferenciaValidacion() {
        return totalTransferenciaValidacion;
    }

    /**
     * Metodo setTotalRechazoValidacion
     * @param totalRechazoValidacion
     */
    public void setTotalRechazoValidacion(Long totalRechazoValidacion) {
        this.totalRechazoValidacion = totalRechazoValidacion;
    }

    /**
    * Metodo getTotalRechazoValidacion
    * @return Long
    */
    public Long getTotalRechazoValidacion() {
        return totalRechazoValidacion;
    }

    /**
     * Metodo setTotalProgramacionValidacion
     * @param totalProgramacionValidacion
     */
    public void setTotalProgramacionValidacion(Long totalProgramacionValidacion) {
        this.totalProgramacionValidacion = totalProgramacionValidacion;
    }

    /**
    * Metodo getTotalProgramacionValidacion
    * @return Long
    */
    public Long getTotalProgramacionValidacion() {
        return totalProgramacionValidacion;
    }

    /**
     * Metodo setTotalConcluidasCancelacion
     * @param totalConcluidasCancelacion
     */
    public void setTotalConcluidasCancelacion(Long totalConcluidasCancelacion) {
        this.totalConcluidasCancelacion = totalConcluidasCancelacion;
    }

    /**
    * Metodo getTotalConcluidasCancelacion
    * @return Long
    */
    public Long getTotalConcluidasCancelacion() {
        return totalConcluidasCancelacion;
    }

    /**
     * Metodo setTotalConcluidasRechazo
     * @param totalConcluidasRechazo
     */
    public void setTotalConcluidasRechazo(Long totalConcluidasRechazo) {
        this.totalConcluidasRechazo = totalConcluidasRechazo;
    }

    /**
    * Metodo getTotalConcluidasRechazo
    * @return Long
    */
    public Long getTotalConcluidasRechazo() {
        return totalConcluidasRechazo;
    }

    /**
     * Metodo setIdPropuesta
     * @param idPropuesta
     */
    public void setIdPropuesta(BigDecimal idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    /**
    * Metodo getIdPropuesta
    * @return BigDecimal
    */
    public BigDecimal getIdPropuesta() {
        return idPropuesta;
    }

    /**
     * Metodo setIdArace
     * @param idArace
     */
    public void setIdArace(BigDecimal idArace) {
        this.idArace = idArace;
    }

    /**
    * Metodo getIdArace
    * @return BigDecimal
    */
    public BigDecimal getIdArace() {
        return idArace;
    }

    /**
     * Metodo setIdEstatus
     * @param idEstatus
     */
    public void setIdEstatus(BigDecimal idEstatus) {
        this.idEstatus = idEstatus;
    }

    /**
    * Metodo getIdEstatus
    * @return BigDecimal
    */
    public BigDecimal getIdEstatus() {
        return idEstatus;
    }
}
