package mx.gob.sat.siat.feagace.negocio.util.constantes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececFeriados;


public class Festivos {
    private List<Date> festivos = new ArrayList();
    private boolean festivo;

    public Festivos(final List<FececFeriados> diasFestivos) {

        for (FececFeriados diaFestivo : diasFestivos) {
            festivos.add(diaFestivo.getFecha());
        }
    }

    /**
     * @return the festivos
     */
    public List<Date> getFestivos() {
        return festivos;
    }

    /**
     * @param festivos the festivos to set
     */
    public void setFestivos(List<Date> festivos) {
        this.festivos = festivos;
    }

    /**
     * @return the festivo
     */
    public boolean isFestivo(Date dia) {
        for (int i = 0; i < festivos.size(); i++) {
            if (DateUtil.diasDeDiferencia(festivos.get(i), dia) == 0) {
                festivo = true;
                break;
            } else {
                festivo = false;
            }

        }
        return festivo;
    }

    /**
     * @param festivo the festivo to set
     */
    public void setFestivo(boolean festivo) {
        this.festivo = festivo;
    }
}
