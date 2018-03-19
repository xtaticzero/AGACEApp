package mx.gob.sat.siat.feagace.negocio.rules.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.sat.siat.feagace.modelo.dto.insumos.ContadorAsignadosAdministrador;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.ContadorAsignadosAdministradorEstado;
import mx.gob.sat.siat.feagace.negocio.bo.base.impl.InsumosAsignadosAdministradorBO;
import mx.gob.sat.siat.feagace.negocio.rules.Rule;

public enum InsumosAsignadosAdministradorRule implements Rule<InsumosAsignadosAdministradorBO> {

    VALIDA_EXISTENCIA_INSUMOS_ASIGNADOS {
        @Override
        public boolean process(InsumosAsignadosAdministradorBO insumosAsignadosAdministradorBO) {
            if (insumosAsignadosAdministradorBO.getIterator().hasNext()) {
                insumosAsignadosAdministradorBO.setRule(PROCESA_REGISTRO);
                return true;
            } else {
                insumosAsignadosAdministradorBO.setRule(PROCESA_MAP);
                return true;
            }
        }
    },
    PROCESA_REGISTRO {
        @Override
        public boolean process(InsumosAsignadosAdministradorBO insumosAsignadosAdministradorBO) {
            ContadorAsignadosAdministrador contadorAsignadosAdministrador =
                insumosAsignadosAdministradorBO.getIterator().next();
            if (insumosAsignadosAdministradorBO.getData() == null) {
                insumosAsignadosAdministradorBO.setData(new HashMap<String, List<ContadorAsignadosAdministrador>>());
            }
            if (insumosAsignadosAdministradorBO.getData().get(contadorAsignadosAdministrador.getNombreAdministrador()) ==
                null) {
                List<ContadorAsignadosAdministrador> list = new ArrayList<ContadorAsignadosAdministrador>();
                list.add(contadorAsignadosAdministrador);
                insumosAsignadosAdministradorBO.getData().put(contadorAsignadosAdministrador.getNombreAdministrador(),
                                                              list);
            } else {
                insumosAsignadosAdministradorBO.getData().get(contadorAsignadosAdministrador.getNombreAdministrador()).add(contadorAsignadosAdministrador);
            }
            insumosAsignadosAdministradorBO.setRule(VALIDA_EXISTENCIA_INSUMOS_ASIGNADOS);
            return true;
        }
    },
    PROCESA_MAP {
        @Override
        public boolean process(InsumosAsignadosAdministradorBO insumosAsignadosAdministradorBO) {
            if (insumosAsignadosAdministradorBO.getContadorAsignadosAdministradorEstados() == null) {
                List<ContadorAsignadosAdministradorEstado> list =
                    new ArrayList<ContadorAsignadosAdministradorEstado>();
                insumosAsignadosAdministradorBO.setContadorAsignadosAdministradorEstados(list);
            }
            for (Map.Entry<String, List<ContadorAsignadosAdministrador>> data :
                 insumosAsignadosAdministradorBO.getData().entrySet()) {
                ContadorAsignadosAdministradorEstado contadorAsignadosAdministradorEstado =
                    new ContadorAsignadosAdministradorEstado();
                contadorAsignadosAdministradorEstado.setNombreAdministrador(data.getKey());
                contadorAsignadosAdministradorEstado.setContadorAsignadosAdministrador(data.getValue());

                insumosAsignadosAdministradorBO.getContadorAsignadosAdministradorEstados().add(contadorAsignadosAdministradorEstado);
            }
            return false;
        }
    };

}
