package mx.gob.sat.siat.feagace.negocio.common;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececActividadPreponderante;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececEmpleado;

public interface InformeRechazoService {
    List<String> construyeComboEntidad(FececEmpleado empleado);

    List<FececActividadPreponderante> construyeComboActividadPreponderante();
}
