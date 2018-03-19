/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.util.constantes;

import mx.gob.sat.siat.feagace.modelo.util.Constantes;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public enum SemaforoEnum {

    SEMAFORO_VERDE(1, Constantes.SEMAFORO_VERDE, "Verde", BusinessUtil.obtenerDescripcionSemaforoInsumo(1)),
    SEMAFORO_AMARILLO(2, Constantes.SEMAFORO_AMARILLO, "Amarillo", BusinessUtil.obtenerDescripcionSemaforoInsumo(2)),
    SEMAFORO_NARANJA(3, Constantes.SEMAFORO_NARANJA, "Naranja", BusinessUtil.obtenerDescripcionSemaforoInsumo(3)),
    SEMAFORO_ROJO(4, Constantes.SEMAFORO_ROJO, "Rojo", BusinessUtil.obtenerDescripcionSemaforoInsumo(4)),
    SEMAFORO_CAFE(5, Constantes.SEMAFORO_CAFE, "Café", BusinessUtil.obtenerDescripcionSemaforoInsumo(5)),
    SEMAFORO_AZUL(6, Constantes.SEMAFORO_AZUL, "Azul", BusinessUtil.obtenerDescripcionSemaforoInsumo(6)),
    SEMAFORO_GRIS(7, Constantes.SEMAFORO_GRIS, "Gris", BusinessUtil.obtenerDescripcionSemaforoInsumo(7)),
    SEMAFORO_NEGRO(8, Constantes.SEMAFORO_NEGRO, "Negro", BusinessUtil.obtenerDescripcionSemaforoInsumo(8)),
    SEMAFORO_BEIGE(9, Constantes.SEMAFORO_BEIGE, "Beige", BusinessUtil.obtenerDescripcionSemaforoInsumo(9)),
    SEMAFORO_BLANCO(10, Constantes.SEMAFORO_BLANCO, "Blanco", BusinessUtil.obtenerDescripcionSemaforoInsumo(10)),
    SEMAFORO_MORADO(11, Constantes.SEMAFORO_MORADO, "Morado", BusinessUtil.obtenerDescripcionSemaforoInsumo(11));

    private final int valor;
    private final String textoImagenSemaforo;
    private final String nombre;
    private final String descripcionSemaforoInsumos;

    private SemaforoEnum(int valor, String textoImagenSemaforo, String nombre, String descripcionSemaforoInsumos) {
        this.valor = valor;
        this.textoImagenSemaforo = textoImagenSemaforo;
        this.nombre = nombre;
        this.descripcionSemaforoInsumos = descripcionSemaforoInsumos;
    }

    public int getValor() {
        return valor;
    }

    public String getTextoImagenSemaforo() {
        return textoImagenSemaforo;
    }

    public String getNombre() {
        return nombre;
    }

    public final String getDescripcionSemaforoInsumos() {
        return descripcionSemaforoInsumos;
    }

    public static SemaforoEnum obtenerSemaforoById(int id) {
        SemaforoEnum resultado = null;
        if (id > 0) {
            for(SemaforoEnum entry : SemaforoEnum.values()) {
                if (entry.getValor() == id) {
                    resultado = entry;
                    break;
                }
            }
        }
        return resultado;
    }
}
