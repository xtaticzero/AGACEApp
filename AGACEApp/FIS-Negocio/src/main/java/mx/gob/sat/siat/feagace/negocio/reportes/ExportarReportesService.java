package mx.gob.sat.siat.feagace.negocio.reportes;

import java.io.File;

import java.util.List;
import java.util.Map;

public interface ExportarReportesService {

    void crearHeaderReporte(String titulo, String nombreReporte);

    void agregarRegistrosReporte(List<Map<String, ?>> registros);

    void crearArchivo(String nombre, String tipo);

    File getFile();

    void setPathJasper(String pathJasper);
}
