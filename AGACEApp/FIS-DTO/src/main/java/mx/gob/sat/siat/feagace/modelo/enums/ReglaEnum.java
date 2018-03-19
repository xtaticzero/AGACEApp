/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.enums;

import java.math.BigDecimal;

/**
 *
 * @author jose.aguilar
 */
public enum ReglaEnum {

    RNA001(new BigDecimal(1), "Si el certificado (.CER) no es vigente entonces - El sistema no permite ingresar al sistema De lo contrario si el certificado (.CER) es vigente entonces - El sistema continua con las demás validaciones"),
    RNA002(new BigDecimal(2), "Si la contraseña de clave privada no es válida entonces - El sistema no permite ingresar al sistema De lo contrario si la contraseña de clave privada es válida entonces - El sistema continua con las demás validaciones"),
    RNA003(new BigDecimal(3), "Si el certificado (.CER) no corresponde a la clave privada (.KEY) entonces- El sistema no permite ingresar al sistemaDe lo contrario si el certificado (.CER) corresponde a la clave privada (.KEY) entonces- El sistema continua con las demás validaciones"),
    RNA004(new BigDecimal(4), "Si al ingresar el RFC del contribuyente, el sistema encuentra información relacionada entonces El sistema traerá los siguientes datos de IdC: - Nombre/Razón social - Régimen - Situación del Contribuyente - Tipo de contribuyente - Situación del Domicilio - Domicilio Fiscal del Contribuyente - Actividad Preponderante Nota: Se colocará la de mayor porcentaje, si no existe porcentaje se colocará la primera, de lo contrario si no trae actividad preponderante se colocará el mensaje: Sin Actividad preponderante - "),
    RNA006(new BigDecimal(6), "Se deshabilitan las fechas mayores a la actual Si la fecha de fin es menor que la fecha de Inicio entonces El sistema presentará el mensaje: La fecha de fin no puede ser menor que la inicial. De lo contrario si las fecha de Inicio y fin son válidas entonces Continúa con el proceso de registro."),
    RNA007(new BigDecimal(7), "Se podrán adjuntar de 1 a50 documentos.Cada uno de los documentos que se adjunten, deben ser mayor a 1 KB y menor que 4 MB en caso contrario el sistema mandará el siguiente mensaje: Error al cargar el archivo, debe ser mayor a 1 KB y menor que 4 MBLas extensiones de los archivos permitidos a cargar serán: Word, Excel y PDF versión 2007 o superior. En caso de que no se cumpla con dichas extensiones el sistema presentará el mensaje: Solo se aceptan archivos WORD, EXCEL o PDF versión 2007 o superiorEl sistema no permitirá cargar un archivo con el mismo nombreSi es el mismo nombre entonces"),
    RNA008(new BigDecimal(8), "Para el aplicativo de Registro y Seguimiento de Insumos únicamente podrán ingresar usuarios que se encuentran adscritos a cada una de las Centrales."),
    RNA009(new BigDecimal(9), "A cada Id Registro, se podrá agregar mínimo 1 RFC sin importar el límite de RFC que se carguen, y esto se hará hasta que el  Usuario Insumos, seleccione la opción de Registrar."),
    RNA010(new BigDecimal(10), "Para AGACE, la asignación de los RFC´s registrados a un número de Oficio dependerán de la Entidad Federativa en donde se encuentre registrado el Domicilio Fiscal del Contribuyente, y este será asignado al Rol Asignador Insumos de la siguiente manera:"),
    RNA011(new BigDecimal(11), "El sistema validará que no existan registros duplicados en la tabla temporal."),
    RNA012(new BigDecimal(12), "Para el caso de AGACE, el sistema debe validar que de manera automática los insumos sean registrados por la ACIACE o ACAOCE o ACOECE o ACCAICE, al Administrador de ACPPCE o ADACE’s correspondientes de acuerdo a la Entidad Federativa en donde se encuentre el domicilio fiscal del Contribuyente."),
    RNA013(new BigDecimal(13), "Si el RFC ingresado cuenta con Medio de Contacto entonces"),
    RNA014(new BigDecimal(14), "Si en alguna parte del proceso de Fiscalización Integral el contribuyente se vuelve PPEE o Amparado, al momento de ingresar al registro de dicho Contribuyente, se verificará si el servicio de validación de PPEE o Amparado se encuentra disponible:"),
    RNA015(new BigDecimal(15), "El sistema validará la estructura del RFC al capturarlo y seleccionar la opción de Buscar, conforme a lo siguiente:"),
    RNA016(new BigDecimal(16), "El sistema mostrará únicamente los registros creados por el usuario logueado."),
    RNA017(new BigDecimal(17), "El ordenamiento de los insumos a visualizar será por: Prioridad sugerida (al principio los de valor 1-Alta, después los de valor 2-Media), después se ordenarán por Fecha en que fueron ingresados y solo se verán los insumos asignados (Ascendente)"),
    RNA018(new BigDecimal(18), "Cada insumo contará con un ID de registro único."),
    RNA019(new BigDecimal(19), "Todo insumo creado iniciará con un semáforo en color Blanco. Lo cual representa que no ha iniciado el plazo y se indicará los días que ha cumplido el insumo sin ser atendido, ejemplo: 4 días sin atender."),
    RNA020(new BigDecimal(20), "El sistema permitirá la reasignación de insumos a otro Asignador Insumos únicamente durante los primeros 5 días contados a partir de que el Asignador Insumos o Validador Insumos consulte el insumo correspondiente."),
    RNA021(new BigDecimal(21), "Las Unidades Administrativas que se muestran en el Excel, serán administradas por el usuario."),
    RNA022(new BigDecimal(22), "Cuando un estatus tenga en su Total el valor de cero, el sistema deberá mostrar el valor sin ningún hipervínculo ni subrayado."),
    RNA023(new BigDecimal(23), "Si el  Usuario Insumos, consultor Insumos, Asignador Insumos, Validador Insumos, consultor Insumos por Validar que ingresa al sistema selecciona la opción de Insumos asignados a ésta Unidad Administrativa, se mostrarán los semáforos siguientes:"),
    RNA024(new BigDecimal(24), "No se podrá acceder al detalle de los insumos cuando éstos no hayan iniciado su plazo."),
    RNA025(new BigDecimal(25), "El plazo de atención de un insumo se suspenderá automáticamente y cambiará el color de semáforo a Morado en los siguientes casos:"),
    RNA026(new BigDecimal(26), "Cuando se valide la estructura de un nuevo registro y éste ya se encuentre registrado en el formato de carga; este registro será marcado como incorrecto con la inconsistencia de El registro esta duplicado en el formato de carga"),
    RNA027(new BigDecimal(27), "El sistema envía correo de la cuenta fiscalizacionintegral@sat.gob.mx a:"),
    RNA028(new BigDecimal(28), "Se mostrarán los insumos de acuerdo a lo siguiente:"),
    RNA029(new BigDecimal(29), "No se mostrará esta opción para el Consultor Insumos"),
    RNA030(new BigDecimal(30), "Se podrán adjuntar de 1 a 50 documentos."),
    RNA031(new BigDecimal(31), "¿ Se permitirá adjuntar el archivo de carga proporcionado por el sistema (Excel 2003 o anterior), en caso de que el archivo que se adjunte sea distinto al permitido, el sistema mostrará el mensaje: Archivo inválido. Solo se aceptan archivos xls. "),
    RNA032(new BigDecimal(32), "El Sistema deberá validar que los valores ingresados en el campo Tipo de Insumo, sean los siguientes."),
    RNA033(new BigDecimal(33), "Los valores a visualizar en los catálogos:"),
    RNA034(new BigDecimal(34), "1. El sistema deberá validar:coincidente, continúa con la siguiente validación."),
    RNA035(new BigDecimal(35), "Las opciones que serán visualizadas al entrar al Menú Principal, deberán mostrarse de acuerdo al Usuario autenticado en la aplicación, como se indica en la siguiente tabla:Ver RNA037 para el detalle de los roles que ingresan al sistema de Fiscalización Integral."),
    RNA036(new BigDecimal(36), "Los roles que conforman al flujo de Insumos, dependerán de la administración a la que se encuentren adscritos de acuerdo a la siguiente tabla: "),
    RNA037(new BigDecimal(37), "Los roles que conforman al flujo de Insumos, dependerán de la administración a la que se encuentren adscritos "),
    RNA038(new BigDecimal(38), "Es obligatorio ingresar de forma correcta, todos valores para los campos: RFC, Unidad Administrativa, Subprograma, Tipo de Insumo, Sector, Prioridad y Periodo propuesto (Fecha inicio y Fecha fin) así como el nombre de cada uno de los Documentos adjuntos. "),
    RNA039(new BigDecimal(39), "Se solicita la captura de la justificación en caso de proceder al registro de insumos con información coincidente, acompañado de un documento.");

    private BigDecimal numRegla;
    private String descripcion;

    ReglaEnum(BigDecimal numRegla, String descripcion) {
        this.numRegla = numRegla;
        this.descripcion = descripcion;
    }

    public BigDecimal getNumRegla() {
        return numRegla;
    }

    public void setNumRegla(BigDecimal numRegla) {
        this.numRegla = numRegla;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public static ReglaEnum parse(int idAccion) {
        for (ReglaEnum reglaEnum : ReglaEnum.values()) {
            if (reglaEnum.getNumRegla().intValue() == idAccion) {
                return reglaEnum;
            }
        }
        return null;
    }

}
