/**
 *
 */
package mx.gob.sat.siat.ws.nyv.client.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import mx.gob.sat.siat.ws.nyv.client.dto.ActoAdministrativoVO;
import mx.gob.sat.siat.ws.nyv.client.dto.BitacoraVO;
import mx.gob.sat.siat.ws.nyv.client.dto.ConsultaNumeroReferenciaVO;
import mx.gob.sat.siat.ws.nyv.client.dto.RegistroActoAdministrativo;
import mx.gob.sat.siat.ws.nyv.client.dto.ResponseConsultaVO;
import mx.gob.sat.siat.ws.nyv.client.dto.ResponseRegistroVO;
import mx.gob.sat.siat.ws.nyv.client.dto.TipoDocumentoVO;
import mx.gob.sat.siat.ws.nyv.client.dto.TipoProcesoVO;
import mx.gob.sat.siat.ws.nyv.client.repository.NyVRepositoryMockService;

import org.apache.log4j.Logger;

/**
 * @author Ing. Emmanuel Estrada Gonzalez
 *
 */
public class NyVRepositoryMockServiceImpl implements NyVRepositoryMockService {

    protected static final Logger LOG = Logger.getLogger(NyVRepositoryMockServiceImpl.class);
    private static final int DIA_MENOS = -1;
    private static final int DOS_DIAS_MENOS = -2;

    @Override
    public ResponseRegistroVO registrarActoAdministrativo(RegistroActoAdministrativo registroActoAdministrativo) {
        ResponseRegistroVO responseRegistro = new ResponseRegistroVO();
        responseRegistro.setCodigoRespuesta("OK");
        responseRegistro.setFolio("0987654321");
        responseRegistro.setMensajeRespuesta("Respuesta Mock");
        return responseRegistro;
    }

    @Override
    public ResponseConsultaVO consultarActoAdministrativo(String folioActoAdministrativo) {
        ResponseConsultaVO responseConsulta = new ResponseConsultaVO();
        responseConsulta.setFolioActo("0987654321");
        responseConsulta.setCodigoRespuesta("OK");
        responseConsulta.setRespuesta("1 MOCK");
        Calendar cal = Calendar.getInstance();
        responseConsulta.setFechaNotificacionEfectiva(cal.getTime());
        cal.add(Calendar.DAY_OF_MONTH, DIA_MENOS);
        responseConsulta.setFechaNotificacion(cal.getTime());
        cal.add(Calendar.DAY_OF_MONTH, DOS_DIAS_MENOS);
        responseConsulta.setFechaEmail(cal.getTime());
        responseConsulta.setEstatus("NOTIFICADO");
        responseConsulta.setCodigoRespuesta("OK");
        responseConsulta.setMensajeRespuesta("La consulta fué exitosa.");
        responseConsulta.setUrlAvisoNotificacion("/siat/notificacion/2017/03/31/D/O/DOC100524763/318103AR0000741/AVISO.pdf");
        responseConsulta.setUrlAcuseNotificacion("/siat/notificacion/2017/03/31/D/O/DOC100524763/318103AR0000741/acuseNotificacion_111-EREE41001511703311121027.pdf");
        List<BitacoraVO> lstBit = new ArrayList<BitacoraVO>();
        BitacoraVO bitVo = new BitacoraVO();
        bitVo.setEstatus("DILIGENCIA");
        bitVo.setFechaAlta(cal.getTime());
        bitVo.setNumeroEmpleado("00000066154");
        lstBit.add(bitVo);
        bitVo = new BitacoraVO();
        bitVo.setEstatus("NOTIFICADO");
        bitVo.setFechaAlta(cal.getTime());
        bitVo.setNumeroEmpleado("00000000000");
        lstBit.add(bitVo);
        responseConsulta.setBitacora(lstBit);
        return responseConsulta;
    }

    @Override
    public List<ActoAdministrativoVO> consultarListaActosAdmin(String unidadAdministrativa) {
        List<ActoAdministrativoVO> lstActoAdministrativo = new ArrayList<ActoAdministrativoVO>();
        List<TipoDocumentoVO> lstTipoDoc = new ArrayList<TipoDocumentoVO>();

        ActoAdministrativoVO acto = new ActoAdministrativoVO();
        TipoDocumentoVO tipoDoc = new TipoDocumentoVO();

        acto.setDescripcion("Mock");
        acto.setId(6378);
        acto.setNombre("2A CARTA INVITACIÓN MASIVA (MCA)");
        acto.setPrefijoReferencia("110-");

        tipoDoc.setId(47694);
        tipoDoc.setTipoDocumento("2A CARTA INVITACION MASIVA (MCA)");
        tipoDoc.setResolucion(true);
        lstTipoDoc.add(tipoDoc);

        tipoDoc = new TipoDocumentoVO();
        tipoDoc.setId(70167);
        tipoDoc.setTipoDocumento("INVITACION PARA VERIFICAR O CORREGIR SU SITUACION FISCAL, CON RELACION AL AVISO DE COMPENSACION");
        tipoDoc.setResolucion(true);
        lstTipoDoc.add(tipoDoc);

        acto.setDocumento(lstTipoDoc);

        lstActoAdministrativo.add(acto);

        return lstActoAdministrativo;
    }

    @Override
    public List<TipoProcesoVO> consultarListaTiposProceso(String unidadAdministrativa) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ConsultaNumeroReferenciaVO consultarNumeroReferencia(String numeroReferencia) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean fueraHorarioLaboral() {
        return true;
    }

}
