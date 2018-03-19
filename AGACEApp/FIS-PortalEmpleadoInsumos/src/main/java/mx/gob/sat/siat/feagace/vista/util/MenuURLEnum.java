/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.util;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public enum MenuURLEnum {

    MENU_I2("i2 Registrar Insumos", "/faces/insumos/usrInsumos/indexCrear.jsf"),
    MENU_I3("i3 Seguimiento Insumos", "/faces/insumos/usrInsumos/indexSeguimiento.jsf"),
    MENU_I4("i4 Consultar Insumos Asignados a Asignador", "/faces/insumos/consultor/indexAsignados.jsf"),
    MENU_I5("i5 Consultar y Asignar Insumos a Subadministradores",
            "/faces/insumos/asignador/indexAsignarSubadministrador.jsf"),
    MENU_I6("i6 Consultar y Validar Procedencia de Insumo", "/faces/insumos/validador/indexValidar.jsf"),
    MENU_I7("i7 Consultar Estatus", "/faces/insumos/consultaAdministrativa.jsf"),
    MENU_I8("i8 Registro Masivo de Insumos", "/faces/insumos/masiva/cargaMasivaInsumos.jsf"),
    MENU_G1("g1 Consultar Estatus Usuario General", "/faces/consultaGeneral/inicioConsultaGeneral.xhtml"),
    MENU_C1("c1 Administracion CAT Unidad Administrativa Modulo", "/faces/catalogos/unidadesAdministrativas/adminCatUnidadAdminModulo.jsf"),
    MENU_R1("r1 Reporte de Insumos", "/faces/reportes/reportesInsumos.jsf"),
    MENU_R2("r9 Reporte pistas auditoria", "/faces/reportes/consultaPistaAuditInterno.jsf");
    
    

    /**
     * Etiqueta
     */
    private final String etiqueta;

    /**
     * URL
     */
    private final String url;

    private MenuURLEnum(String etiqueta, String descripcion) {
        this.etiqueta = etiqueta;
        this.url = descripcion;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public String getUrl() {
        return url;
    }
}
