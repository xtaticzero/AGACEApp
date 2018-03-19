package mx.gob.sat.siat.feagace.negocio.ordenes.semaforo.helper;

import java.io.Serializable;

import java.util.List;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Component;


@Component
public class SemaforoHelper implements Serializable {

    @SuppressWarnings("compatibility:-8973728437431508448")
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = Logger.getLogger(SemaforoHelper.class.getName());

    private static final String ERROR = "no puede ser null.";

    /**
     * Metodo para validar una lista de objetos
     * @author eolf
     * @param lista
     * @throws IllegalArgumentException
     */
    public void validarLista(final List<?> lista, final String extra) {
        LOG.debug("[validarLista]");
        if (lista == null || lista.isEmpty()) {
            throw new IllegalArgumentException("La lista de '" + extra + "' " + ERROR);
        }
    }
}
