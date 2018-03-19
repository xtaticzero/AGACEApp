/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.enums;

import java.math.BigDecimal;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public enum TipoEmpleadoEnum {

    CONSULTOR_INSUMOS(1L, "Consultor Insumos"),
    ASIGNADOR_INSUMOS(2L, "Asignador Insumos"),
    PROGRAMADOR(3L, "Programador"),
    VALIDADOR_INSUMOS(4L, "Validador Insumos"),
    USUARIO_INSUMOS(5L, "Usuario Insumos"),
    AUDITOR(6L, "Auditor"),
    FIRMANTE(7L, "Firmante"),
    PROGRAMADOR_CONSULTA_ANTECEDENTES(8L, "Programador de Consulta de Antecedentes"),
    PROGRAMACION(9L, "Programacion"),
    USUARIO_SUBADMINISTRADOR_AREA(10L, "Usuario Subadministrador de Area"),
    ADMINISTRADOR_EMISION_ORDENES(11L, "Administrador de Emision de Ordenes"),
    SUBADMINISTRADOR_EMISION_ORDENES(12L, "Subadministrador de Emision de Ordenes"),
    GENERADOR_REPORTES_EJECUTIVO(13L, "Generador de Reportes Ejecutivo"),
    GENERADOR_REPORTES_GERENCIAL(14L, "Generador de Reportes Gerencial"),
    JEFE_DE_DEPARTAMENTO(15L, "Jefe de Departamento"),
    ENLACE(16L, "Enlace");

    /**
     * ID_TIPO_EMPLEADO
     */
    private final long id;

    /**
     * DESCRIPCION
     */
    private final String descripcion;

    private TipoEmpleadoEnum(long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public long getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public BigDecimal getBigId() {
        return BigDecimal.valueOf(id);
    }

    public static TipoEmpleadoEnum parse(int idTipoEmpleado) {
        for (TipoEmpleadoEnum tipoPlazo : TipoEmpleadoEnum.values()) {
            if (tipoPlazo.getId() == idTipoEmpleado) {
                return tipoPlazo;
            }
        }
        return null;
    }

}
