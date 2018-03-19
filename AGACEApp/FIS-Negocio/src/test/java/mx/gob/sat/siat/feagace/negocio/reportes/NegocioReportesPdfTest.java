/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.reportes;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.gob.sat.siat.base.BaseTest;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.negocio.EmpleadoService;
import mx.gob.sat.siat.feagace.negocio.common.reportes.JaspertReportsService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class NegocioReportesPdfTest extends BaseTest {

    @Autowired
    @Qualifier("jaspertReportsService")
    private JaspertReportsService jaspertReportsService;

    @Autowired
    private EmpleadoService empleadoService;

    private String nombreExcel;
    private String nombreExcel2007;
    private String nombrePdf;

    private Integer clavePerfil;
    private Integer claveModulo;
    private Integer claveALAF;
    private Integer cveSubModulo;

    private Map<String, Object> parametros;

    @Before
    public void init() {
        nombreExcel = "pruebaAgace.xls";
        nombreExcel2007 = "pruebaAgace.xlsx";
        nombrePdf = "pruebaAgace.pdf";

        clavePerfil = 2;
        claveModulo = 32;
        claveALAF = 1;
        cveSubModulo = 0;

        parametros = new HashMap<String, Object>();
        parametros.put("rfc", "RFC_DE_PRUEBA");
        parametros.put("razonSocial", "RAZON_SOCIAL_DE_PRUEBA");
    }

    @Test
    //@Ignore
    public void pruebaReporteContribuyentePDF() throws Exception {
        FileOutputStream fos;
        byte[] reporte;
        try {
            List<EmpleadoDTO> lstEmpleadosDTO = empleadoService.getEmpleadosXUnidadAdmva(claveALAF, clavePerfil, ClvSubModulosAgace.INSUMOS);

            for (int i = 0; i < lstEmpleadosDTO.size(); i++) {
                EmpleadoDTO emp = lstEmpleadosDTO.get(i);
                if(i==0){
                    emp.setRfc("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa \n sssssssssssssssssssssssssssssssssssaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa \n bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
                }else
                    emp.setRfc("asdadadsadasdasdasdasdadakljasñldjkañldjalhaFLHAñlaHFÑHFÑdhsdñfjhsdkfljasdhfklajshdflsdkjfskldbdfkjsdbfksdjfasdkjfnsddkflbjsdfkljasbdasdadadsadasdasdasdasdadakljasñldjkañldjalhaFLHAñlaHFÑHFÑdhsdñfjhsdkfljasdhfklajshdflsdkjfskldbdfkjsdbfksdjfasdkjfnsddkflbjsdfkljasbdfklsdjbfsdkldjfasdadadsadasdasdasdasdadakljasñldjkañldjalhaFLHAñlaHFÑHFÑdhsdñfjhsdkfljasdhfklajshdflsdkjfskldbdfkjsdbfksdjfasdkjfnsddkflbjsdfkljasbdfklsdjbfsdkldjfasdadadsadasdasdasdasdadakljasñldjkañldjalhaFLHAñlaHFÑHFÑdhsdñfjhsdkfljasdhfklajshdflsdkjfskldbdfkjsdbfksdjfasdkjfnsddkflbjsdfkljasbdfklsdjbfsdkldjffklsdjbfsdkldjfbsdklfjbsdfkljsdbfasdkljfbsdklfjsd \n sssssssssssssssssssssssssssssssssssaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa \n bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
            }
//List<EmpleadoDTO> lstEmpleadosDTO =  new ArrayList<EmpleadoDTO>();
            reporte = jaspertReportsService.makeReport("/siat/fece/configuracion/reportes/ReportePruebaAgace.jasper", nombreExcel, parametros, lstEmpleadosDTO);
            fos = new FileOutputStream("/siat/tmp/" + nombreExcel);
            fos.write(reporte);
            fos.close();
            reporte = jaspertReportsService.makeReport("/siat/fece/configuracion/reportes/ReportePruebaAgace.jasper", nombreExcel2007, parametros, lstEmpleadosDTO);
            fos = new FileOutputStream("/siat/tmp/" + nombreExcel2007);
            fos.write(reporte);
            fos.close();
            reporte = jaspertReportsService.makeReport("/siat/fece/configuracion/reportes/ReportePruebaAgace.jasper", nombrePdf, parametros, lstEmpleadosDTO);
            fos = new FileOutputStream("/siat/tmp/" + nombrePdf);
            fos.write(reporte);
            fos.close();
        } catch (Exception ex) {
            System.err.println("Error al obtener el recurso: "
                    + ex.getCause().getMessage());
        }
    }

}
