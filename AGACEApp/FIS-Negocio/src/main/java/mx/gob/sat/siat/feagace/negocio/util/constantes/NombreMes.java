package mx.gob.sat.siat.feagace.negocio.util.constantes;

public final class NombreMes {

    private static final int ENERO = 0;
    private static final int FEBRERO = 1;
    private static final int MARZO = 2;
    private static final int ABRIL = 3;
    private static final int MAYO = 4;
    private static final int JUNIO = 5;
    private static final int JULIO = 6;
    private static final int AGOSTO = 7;
    private static final int SEPTIEMBRE = 8;
    private static final int OCTUBRE = 9;
    private static final int NOVIEMBRE = 10;
    private static final int DICIEMBRE = 11;

    private NombreMes() {

    }

    public static String obtenerNombre(int mes) {
        String result = "";
        switch (mes) {
        case ENERO:
            {
                result = "Enero";
                break;
            }
        case FEBRERO:
            {
                result = "Febrero";
                break;
            }
        case MARZO:
            {
                result = "Marzo";
                break;
            }
        case ABRIL:
            {
                result = "Abril";
                break;
            }
        case MAYO:
            {
                result = "Mayo";
                break;
            }
        case JUNIO:
            {
                result = "Junio";
                break;
            }
        case JULIO:
            {
                result = "Julio";
                break;
            }
        case AGOSTO:
            {
                result = "Agosto";
                break;
            }
        case SEPTIEMBRE:
            {
                result = "Septiembre";
                break;
            }
        case OCTUBRE:
            {
                result = "Octubre";
                break;
            }
        case NOVIEMBRE:
            {
                result = "Noviembre";
                break;
            }
        case DICIEMBRE:
            {
                result = "Diciembre";
                break;
            }
        default:
            {
                result = "Error";
                break;
            }
        }
        return result;
    }

}
