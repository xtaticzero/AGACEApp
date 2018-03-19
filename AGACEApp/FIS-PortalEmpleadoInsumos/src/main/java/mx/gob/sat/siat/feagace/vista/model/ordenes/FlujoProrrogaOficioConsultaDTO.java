package mx.gob.sat.siat.feagace.vista.model.ordenes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class FlujoProrrogaOficioConsultaDTO implements Serializable {
    
    
    /**
     * 
     */
    private static final long serialVersionUID = -9001584664178235678L;
    private BigDecimal idFlujoProrrogaOficio;
    private String motivo;
    private Integer totalAnexos;
    private List<AnexoProrrogaOficioConsultaDTO> listAnexosProrrogaOficios;
    
    
    
    public String getMotivo() {
        return motivo;
    }
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    
    public BigDecimal getIdFlujoProrrogaOficio() {
        return idFlujoProrrogaOficio;
    }
    public void setIdFlujoProrrogaOficio(BigDecimal idFlujoProrrogaOficio) {
        this.idFlujoProrrogaOficio = idFlujoProrrogaOficio;
    }
       
    public List<AnexoProrrogaOficioConsultaDTO> getListAnexosProrrogaOficios() {
        return listAnexosProrrogaOficios;
    }
    public void setListAnexosProrrogaOficios(List<AnexoProrrogaOficioConsultaDTO> listAnexosProrrogaOficios) {
        this.listAnexosProrrogaOficios = listAnexosProrrogaOficios;
    }
    public Integer getTotalAnexos() {
        return totalAnexos;
    }
    public void setTotalAnexos(Integer totalAnexos) {
        this.totalAnexos = totalAnexos;
    }
    

    
}

