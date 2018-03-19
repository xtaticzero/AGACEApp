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

    MENU_I2("i2 Registrar Insumos", "/faces/insumos/aciace/indexCrear.jsf"),
    MENU_I3("i3 Seguimiento Insumos", "/faces/insumos/aciace/indexSeguimiento.jsf"),
    MENU_I4("i4 Consultar Insumos Asignados a Administradores", "/faces/insumos/central/indexAsignados.jsf"),
    MENU_I5("i5 Consultar y Asignar Insumos a Subadministradores",
            "/faces/insumos/administrador/indexAsignarSubadministrador.jsf"),
    MENU_I6("i6 Consultar y Validar Procedencia de Insumo", "/faces/insumos/subadministrador/indexValidar.jsf"),
    MENU_O6("o6 Consultar y Reimprimir Documentos", "/faces/consultarDocumentos/consultarReimprimirDocBusqueda.jsf"),
    MENU_P1("p1 Consulta de Antecedentes", "/faces/propuestas/programador/indexConsultaAntecedentes.jsf"),
    MENU_P2("p2 Registro de Propuesta Individual", "/faces/faces/propuestas/programador/indexCrear.jsf"),
    MENU_P3("p3 Carga Masiva de Propuestas", "/faces/propuestas/programador/indexCargaMasiva.jsf"),
    MENU_P4("p4 Carga de Cartas Invitacion Masivas (MCAs)",
            "/faces/propuestas/programador/cartaInvitacion/indexCargaMasiva.jsf"),
    MENU_P5("p5 Validacion y Retroalimentacion de Propuestas",
            "/faces/propuestas/programador/validarYRetroalimentar/indexValidarRetroalimentar.jsf"),
    MENU_P6("p6 Rechazo de Propuestas", "/faces/propuestas/auditor/rechazosPropuestas.jsf"),
    MENU_P7("p7 Informe a Comite", "/faces/propuestas/programador/informeComite.jsf"),
    MENU_P8("p8 Consulta Prop Origen Central Regional",
            "/faces/propuestas/programador/indexOrigenCentralRegional.jsf"),
    MENU_P9("p9 Asignar Propuestas a Firmante", "/faces/propuestas/central/indexAsignarFirmante.jsf"),
    MENU_P10("p10 Asignar Propuestas a Auditor", "/faces/propuestas/firmante/indexAsignarAuditor.jsf"),
    MENU_P11("p11 Consultar Propuestas Asignadas", "/faces/propuestas/auditor/indexAsignadas.jsf"),
    MENU_P12("p12 Carga Documento Electronico", "/faces/propuestas/docElectronico/generarRevision.jsf"),
    MENU_P13("p13 Asignar Doc Elec Verificacion Procedencia",
             "/faces/propuestas/administrador/asignarDocElectronico.jsf"),
    MENU_P14("p14 Verificar Procedencia de Documento Electronico",
             "/faces/propuestas/subadministrador/verificarProcedencia.jsf"),
    MENU_P15("p15 Validar y Firmar Documento Electronico",
             "/faces/firmante/validarFirmarDocElectronico/firmanteSuplente.jsf"),
    MENU_P16("p16 Asignar suplencia firmante", "/faces/propuestas/central/indexAsignarSuplenciaAFirmante.jsf"),
    MENU_O1("o1 Seguimiento Ordenes (Auditor)", "/faces/auditor/seguimiento/indexDocumentacion.jsf"),
    MENU_O2("o2 Firmante Seguimiento Ordenes (Firmante)",
            "/faces/firmante/validarFirmarDocSeguimiento/indexValidarFirmarDocumentos.jsf"),
    MENU_O13("o13 Consulta Propuestas y Ordenes", "/faces/consultarDocumentos/consultaPropuestasOrdenes.jsf"),
    MENU_O15("o15 Consulta", "/faces/consulta/consultaAdministrativa.jsf"),
    MENU_R3("r3 Reporte de Ordenes", "/faces/reportes/reportesOrdenes.jsf"),
    MENU_R9("r9 Reporte pistas auditoria", "/faces/reportes/consultaPistaAuditInterno.jsf");

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
