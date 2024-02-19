
package com.Geko.pruebas.Configuration;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;

/**
 * Clase encargada de generar informes PDF utilizando JasperReports.
 */
@Component
public class JaspertReportManager {

    /**
     * Constructor por defecto de la clase JaspertReportManager.
     */
    public JaspertReportManager() {
    }

    /**
     * Genera un archivo PDF a partir de un archivo de informe Jasper y parámetros proporcionados.
     * @param fileName Nombre del archivo Jasper (sin la extensión .jasper) ubicado en la carpeta "static" del classpath.
     * @param params Mapa de parámetros a pasar al informe.
     * @param con Conexión a la base de datos utilizada para llenar el informe.
     * @return Un ByteArrayOutputStream que contiene los datos del archivo PDF generado.
     * @throws IOException Si ocurre un error de entrada/salida al leer el archivo Jasper.
     * @throws JRException Si ocurre un error durante la generación del informe Jasper.
     */
    public ByteArrayOutputStream GenerarPDF(String fileName, Map<String, Object> params, Connection con) throws IOException, JRException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        // Obtiene la ruta del archivo Jasper ubicado en la carpeta "static"
        ClassPathResource resource = new ClassPathResource("static" + File.separator + fileName + ".jasper");
        InputStream inputStream = resource.getInputStream();
        // Llena el informe Jasper con los parámetros y la conexión a la base de datos
        JasperPrint print = JasperFillManager.fillReport(inputStream, params, con);
        // Exporta el informe Jasper a formato PDF y lo guarda en un ByteArrayOutputStream
        JasperExportManager.exportReportToPdfStream(print, stream);
        return stream;
    }
}
