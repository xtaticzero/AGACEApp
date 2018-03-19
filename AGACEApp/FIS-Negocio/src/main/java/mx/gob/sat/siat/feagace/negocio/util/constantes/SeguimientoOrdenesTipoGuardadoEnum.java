package mx.gob.sat.siat.feagace.negocio.util.constantes;

public enum SeguimientoOrdenesTipoGuardadoEnum {
    
    PRUEBAS_PERICIALES(1, "SeguimientoOrdenes guardarPruebasPericiales"),
    SEGUNDO_REQUERIMIENTO(2, "SeguimientoOrdenes guardarSegundoRequerimiento"),
    RESOLUCION_DEFINITIVA(3, "SeguimientoOrdenes guardarResolucionDefinitiva"),
    PRUEBAS_DESAHOGO(4, "SeguimientoOrdenes guardarPruebasDesahogo"),
    CONCLUSION_REVISION(5, "SeguimientoOrdenes guardarConclusionRevision"),
    SIN_OBSERVACIONES(6, "SeguimientoOrdenes guardarSinObservaciones"),
    CON_OBSERVACIONES(7, "SeguimientoOrdenes guardarConObservaciones"),
    REQUERIMIENTO_REINCIDENCIA(8, "SeguimientoOrdenes guardarRequerimientoReincidencia"),
    COMP_INTERNACIONAL(9, "SeguimientoOrdenes guardarCompInternacional"),
    COMPULSA_TERCERO_OTRAS_AUTORIDADES(10, "SeguimientoOrdenes guardarCompulsaTerceroOtrasAutoridades"),
    COMPULSA_OTRAS_AUTORIDADES(11, "SeguimientoOrdenes guardarCompulsaOtrasAutoridades"),
    AVISO_CIRCUNSTANCIAL(12, "SeguimientoOrdenes guardarAvisoCircunstancial"),
    EMBARGO_CUENTAS_BANCARIAS(22, "Oficio de Embargo de Cuentas Bancarias"),
    SUSPENSION_DE_PADRON(23, "Oficio de Suspensión de Padrón de Importadores y Exportadores"),
    MULTA(13, "Oficio de Multa"),
    AVISO_CONTRIBUYENTE(14,"Oficio Aviso Contribuyente");
    
    private final int tipoGuardado;
    private final String descripcion;

    private SeguimientoOrdenesTipoGuardadoEnum(int tipoGuardado, String descripcion) {
        this.tipoGuardado = tipoGuardado;
        this.descripcion = descripcion;
    }

    public int getTipoGuardado() {
        return tipoGuardado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static SeguimientoOrdenesTipoGuardadoEnum parse(int idTipoOficio) {
        for (SeguimientoOrdenesTipoGuardadoEnum tipoOficio : SeguimientoOrdenesTipoGuardadoEnum.values()) {
            if (tipoOficio.getTipoGuardado() == idTipoOficio) {
                return tipoOficio;
            }
        }
        return null;
    }
}