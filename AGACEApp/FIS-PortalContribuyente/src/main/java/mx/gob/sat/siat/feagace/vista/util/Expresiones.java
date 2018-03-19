package mx.gob.sat.siat.feagace.vista.util;

public abstract class Expresiones {
    
    public static final int RFC_LONGITUD =13;
    public static final int RFCM_LONGITUD =12;
    public static final String RFCM_PATTERN_1BLOQUE = "^[A-ZÑ&]{3}.*$";
    public static final String RFC_PATTERN_1BLOQUE = "^[A-ZÑ&]{4}.*$";
    public static final String RFC_PATTERN_11BLOQUE = "^[A-ZÑ&]{1}.*$";
    public static final String RFC_PATTERN_12BLOQUE = "^.{1}[AEIOU]{1}.*$";
    public static final String RFC_PATTERN_13BLOQUE = "^.{2}[A-ZÑ&]{2}.*$";
    public static final String RFC_PATTERN_2BLOQUE =
           "^.{4}(((\\d{2})(0[13578]|1[02])(0[1-9]|[12]\\d|3[01]))|((\\d{2})(0[13456789]|1[012])(0[1-9]|[12]\\d|3[0]))|((\\d{2})02(0[1-9]|1\\d|2[0-8]))|(((0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00)0229)).*$";
    public static final String RFCM_PATTERN_2BLOQUE =
           "^.{3}(((\\d{2})(0[13578]|1[02])(0[1-9]|[12]\\d|3[01]))|((\\d{2})(0[13456789]|1[012])(0[1-9]|[12]\\d|3[0]))|((\\d{2})02(0[1-9]|1\\d|2[0-8]))|(((0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00)0229)).*$";
    public static final String RFC_PATTERN_3BLOQUE = "^.{10}[A-Z[0-9]]{2}.*$";
    public static final String RFC_PATTERN_4BLOQUE = "^.{12}[\\dA]$";
    public static final String RFCM_PATTERN_3BLOQUE = "^.{9}[A-Z[0-9]]{2}.*$";
    public static final String RFCM_PATTERN_4BLOQUE = "^.{11}[\\dA]$";
}
