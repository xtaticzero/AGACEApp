/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles;

import java.math.BigDecimal;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatusEmpleado;
import mx.gob.sat.siat.feagace.modelo.dto.common.DetalleEmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.enums.EstadoBooleanodeRegistroEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class EmpleadoDTO extends BaseModel implements Cloneable {

    private static final long serialVersionUID = 6087702146987487971L;

    private BigDecimal idEmpleado;
    private BigDecimal idSuplencia;
    private String nombre;
    private String apellidoMaterno;
    private String apellidoPaterno;
    private String rfc;
    private String correo;
    private EstadoBooleanodeRegistroEnum estatusEmpleado;
    private Date fechaCreacion;
    private Date fechaBaja;
    private FececEstatusEmpleado fececEstatusEmpleado;
    private List<DetalleEmpleadoDTO> detalleEmpleado;
    private Map<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>> subordinados;
    private Map<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>> lstJefesInmediatos;
    private Integer idArea;
    private String area;
    private Integer idSede;
    private String sede;
    private Integer idAdmCentral;
    private String admCentral;
    private Integer idAdmGral;
    private String admGral;
    private int totalInsumosAsignados;
    private int totalPropuestaAsignados;
    private int totalOrdenesAsignados;
    private String nombreCompleto;

    private EmpleadoDTO(BigDecimal idEmpleado, BigDecimal idSuplencia, String nombre, String apellidoPaterno, String apellidoMaterno, String rfc, String correo, EstadoBooleanodeRegistroEnum estatusEmpleado, Date fechaCreacion, Date fechaBaja, FececEstatusEmpleado fececEstatusEmpleado, List<DetalleEmpleadoDTO> detalleEmpleado, Map<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>> subordinados, Map<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>> lstJefesInmediatos, int totalInsumosAsignados, int totalPropuestaAsignados, int totalOrdenesAsignados, String nombreCompleto) throws CloneNotSupportedException {

        this.idEmpleado = idEmpleado;
        this.idSuplencia = idSuplencia;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.rfc = rfc;
        this.correo = correo;
        this.estatusEmpleado = estatusEmpleado;
        this.fechaCreacion = fechaCreacion;
        this.fechaBaja = fechaBaja;
        this.fececEstatusEmpleado = fececEstatusEmpleado;
        this.detalleEmpleado = detalleEmpleado;
        this.subordinados = subordinados;
        this.lstJefesInmediatos = lstJefesInmediatos;
        this.totalInsumosAsignados = totalInsumosAsignados;
        this.totalPropuestaAsignados = totalPropuestaAsignados;
        this.totalOrdenesAsignados = totalOrdenesAsignados;
        this.nombreCompleto = nombreCompleto;
    }

    public EmpleadoDTO() {
        subordinados = new EnumMap<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>>(TipoEmpleadoEnum.class);
    }

    public BigDecimal getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(BigDecimal idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public BigDecimal getIdSuplencia() {
        return idSuplencia;
    }

    public void setIdSuplencia(BigDecimal idSuplencia) {
        this.idSuplencia = idSuplencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public EstadoBooleanodeRegistroEnum getEstatusEmpleado() {
        return estatusEmpleado;
    }

    public void setEstatusEmpleado(EstadoBooleanodeRegistroEnum estatusEmpleado) {
        this.estatusEmpleado = estatusEmpleado;
    }

    public Date getFechaCreacion() {
        return (fechaCreacion != null) ? (Date) fechaCreacion.clone() : null;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion != null ? new Date(fechaCreacion.getTime()) : null;
    }

    public Date getFechaBaja() {
        return (fechaBaja != null) ? (Date) fechaBaja.clone() : null;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja != null ? new Date(fechaBaja.getTime()) : null;
    }

    public FececEstatusEmpleado getFececEstatusEmpleado() {
        return fececEstatusEmpleado;
    }

    public void setFececEstatusEmpleado(FececEstatusEmpleado fececEstatusEmpleado) {
        this.fececEstatusEmpleado = fececEstatusEmpleado;
    }

    public List<DetalleEmpleadoDTO> getDetalleEmpleado() {
        return detalleEmpleado;
    }

    public void setDetalleEmpleado(List<DetalleEmpleadoDTO> detalleEmpleado) {
        this.detalleEmpleado = detalleEmpleado;
    }

    public Map<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>> getSubordinados() {
        return subordinados;
    }

    public void setSubordinados(Map<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>> subordinados) {
        this.subordinados = subordinados;
    }

    public Map<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>> getLstJefesInmediatos() {
        return lstJefesInmediatos;
    }

    public void setLstJefesInmediatos(Map<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>> lstJefesInmediatos) {
        this.lstJefesInmediatos = lstJefesInmediatos;
    }

    public int getTotalInsumosAsignados() {
        return totalInsumosAsignados;
    }

    public void setTotalInsumosAsignados(int totalInsumosAsignados) {
        this.totalInsumosAsignados = totalInsumosAsignados;
    }

    public int getTotalPropuestaAsignados() {
        return totalPropuestaAsignados;
    }

    public void setTotalPropuestaAsignados(int totalPropuestaAsignados) {
        this.totalPropuestaAsignados = totalPropuestaAsignados;
    }

    public int getTotalOrdenesAsignados() {
        return totalOrdenesAsignados;
    }

    public void setTotalOrdenesAsignados(int totalOrdenesAsignados) {
        this.totalOrdenesAsignados = totalOrdenesAsignados;
    }

    public String getNombreCompleto() {
        if (nombre != null) {
            this.nombreCompleto = nombre.concat(" ").concat(apellidoPaterno).concat(" ").concat(apellidoMaterno);
        }
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    @Override
    public EmpleadoDTO clone() throws CloneNotSupportedException {

        EmpleadoDTO clon = new EmpleadoDTO(this.idEmpleado,
                this.idSuplencia,
                this.nombre,
                this.apellidoPaterno,
                this.apellidoMaterno,
                this.rfc,
                this.correo,
                this.estatusEmpleado,
                this.fechaCreacion,
                this.fechaBaja,
                this.fececEstatusEmpleado,
                this.detalleEmpleado,
                this.subordinados,
                this.lstJefesInmediatos,
                this.totalInsumosAsignados,
                this.totalPropuestaAsignados,
                this.totalOrdenesAsignados,
                this.nombreCompleto);
        return clon;

    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getIdSede() {
        return idSede;
    }

    public void setIdSede(Integer idSede) {
        this.idSede = idSede;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public Integer getIdAdmCentral() {
        return idAdmCentral;
    }

    public void setIdAdmCentral(Integer idAdmCentral) {
        this.idAdmCentral = idAdmCentral;
    }

    public String getAdmCentral() {
        return admCentral;
    }

    public void setAdmCentral(String admCentral) {
        this.admCentral = admCentral;
    }

    public Integer getIdAdmGral() {
        return idAdmGral;
    }

    public void setIdAdmGral(Integer idAdmGral) {
        this.idAdmGral = idAdmGral;
    }

    public String getAdmGral() {
        return admGral;
    }

    public void setAdmGral(String admGral) {
        this.admGral = admGral;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.idEmpleado != null ? this.idEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EmpleadoDTO other = (EmpleadoDTO) obj;
        if (this.idEmpleado != other.idEmpleado && (this.idEmpleado == null || !this.idEmpleado.equals(other.idEmpleado))) {
            return false;
        }
        return true;
    }

}
