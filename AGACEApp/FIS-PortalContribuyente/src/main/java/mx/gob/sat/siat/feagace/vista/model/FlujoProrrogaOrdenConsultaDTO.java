package mx.gob.sat.siat.feagace.vista.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class FlujoProrrogaOrdenConsultaDTO implements Serializable{
    private static final long serialVersionUID = -2485326686938124383L;

    private BigDecimal idFlujoProrrogaOrden;
    private String motivo;
    private Integer totalAnexos;
    private List<AnexoProrrogaOrdenConsultaDTO> listAnexosProrrogaOrden;
    public BigDecimal getIdFlujoProrrogaOrden() {
        return idFlujoProrrogaOrden;
    }
    public void setIdFlujoProrrogaOrden(BigDecimal idFlujoProrrogaOrden) {
        this.idFlujoProrrogaOrden = idFlujoProrrogaOrden;
    }
    public String getMotivo() {
        return motivo;
    }
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    public Integer getTotalAnexos() {
        return totalAnexos;
    }
    public void setTotalAnexos(Integer totalAnexos) {
        this.totalAnexos = totalAnexos;
    }
    public List<AnexoProrrogaOrdenConsultaDTO> getListAnexosProrrogaOrden() {
        return listAnexosProrrogaOrden;
    }
    public void setListAnexosProrrogaOrden(List<AnexoProrrogaOrdenConsultaDTO> listAnexosProrrogaOrden) {
        this.listAnexosProrrogaOrden = listAnexosProrrogaOrden;
    }
    
   
   
    
    
    
}
