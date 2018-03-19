package mx.gob.sat.siat.feagace.negocio.rules;

public interface Rule<T> {
    boolean process(T t);
}
