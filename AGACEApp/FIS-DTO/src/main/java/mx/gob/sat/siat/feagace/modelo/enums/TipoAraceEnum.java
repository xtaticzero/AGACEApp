/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.enums;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public enum TipoAraceEnum {

    ACPPCE(1L, "Administración Central de Planeación y Programación de Comercio Exterior", "ACPPCE"),
    ADACE_CENTRO(12L, "ADACE Centro", "HIDALGO, MEXICO, MORELOS, QUERÉTARO, GUANAJUATO, MICHOACÁN, SAN LUIS POTOSI, GUERRERO, DISTRITO FEDERAL"),
    ACIACE(17L, "Administración Central de Investigación y Análisis de Comercio Exterior", "ACIACE"),
    ACOECE(19L, "Administración Central de Operaciones Especiales de Comercio Exterior", "ACOECE"),
    ACAOCE(20L, "Administración Central de Auditoria de Operaciones de Comercio Exterior", "ACAOCE"),    
    ADACE_SUR(25L, "ADACE Sur", "VERACRUZ, TLAXCALA, PUEBLA, CHIAPAS, OAXACA, TABASCO, YUCATÁN, QUINTANA ROO, CAMPECHE"),
    ADACE_NOROESTE(31L, "ADACE Noroeste", "NUEVO LEÓN, TAMAULIPAS"),
    ADACE_PACIFICO_NORTE(41L, "ADACE Pacífico Norte", "BAJA CALIFORNIA, BAJA CALIFORNIA SUR, SONORA"),
    ADACE_NORTE_CENTRO(51L, "ADACE Norte Centro", "CHIHUAHUA, COAHUILA, DURANGO, ZACATECAS"),
    ADACE_OCCIDENTE(67L, "ADACE Occidente", "SINALOA, JALISCO, AGUASCALIENTES, NAYARIT, COLIMA"),
    PPCE(98L, "PPCE", "Planeación y Programación de Comercio Exterior");
    
    /**
     * ID_ARACE
     */
    private final long id;

    /**
     * nombre
     */
    private final String nombre;

    /**
     * sede
     */
    private final String sede;

    private TipoAraceEnum(long id, String nombre, String sede) {
        this.id = id;
        this.nombre = nombre;
        this.sede = sede;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSede() {
        return sede;
    }

}
