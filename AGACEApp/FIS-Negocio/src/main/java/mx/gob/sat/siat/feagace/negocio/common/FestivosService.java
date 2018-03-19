package mx.gob.sat.siat.feagace.negocio.common;

import java.util.Date;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececFeriados;

public interface FestivosService {

    List<FececFeriados> getListaDiasFestivos(final Date fecha, final int diasFestivos);

    List<FececFeriados> getTodosDiasFestivos();
}
