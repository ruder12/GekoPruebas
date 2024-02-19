package com.Geko.pruebas.Assets;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static String Home_path = "";
    public static final String[] AUTH_WHITELIST = {
            "/authenticate",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/v2/api-docs/**",
            "/v3/api-docs/**",
            "/webjars/**"
    };
    public static String DescriccionApi = "autenticación de usuarios, administración de productos, empresas, inventario y usuarios.\n" +
            "\n" +
            "Funcionalidades principales:\n" +
            "\n" +
            "Autenticación de usuarios:\n" +
            "\n" +
            "Permite a los usuarios iniciar sesión y obtener un token JWT para acceder a las funcionalidades protegidas de la API.\n" +
            "Proporciona un mecanismo para cerrar sesión y actualizar el token de acceso.\n" +
            "Gestión de productos:\n" +
            "\n" +
            "Permite la creación, lectura, actualización y eliminación de productos en el sistema.\n" +
            "Proporciona endpoints para obtener listas de productos y detalles de productos individuales.\n" +
            "Gestión de empresas:\n" +
            "\n" +
            "Permite la creación, lectura, actualización y eliminación de empresas asociadas con los productos.\n" +
            "Proporciona endpoints para obtener listas de empresas y detalles de empresas individuales.\n" +
            "Gestión de inventario:\n" +
            "\n" +
            "Permite la obtención de información sobre el inventario de productos disponibles.\n" +
            "Proporciona endpoints para obtener listas de productos en el inventario y detalles de inventario por producto.\n" +
            "Gestión de usuarios:\n" +
            "\n" +
            "Permite la obtención de información sobre los usuarios del sistema.\n" +
            "Proporciona endpoints para obtener detalles de usuarios individuales y validar tokens de acceso.\n" +
            "Tecnologías utilizadas:\n" +
            "\n" +
            "La API está desarrollada en Java utilizando el framework Spring Boot, lo que proporciona un entorno de desarrollo rápido y fácil de configurar.\n" +
            "Se utiliza Spring Security para la autenticación y autorización de usuarios, con tokens JWT para la seguridad de las solicitudes.\n" +
            "Se emplea Swagger para la documentación de la API, facilitando su comprensión y uso por parte de los desarrolladores.\n" +
            "La API está diseñada para ser consumida por aplicaciones cliente, como aplicaciones web desarrolladas en React, proporcionando endpoints RESTful para la comunicación.\n";
    public static String Moneda ="USD";

    public static String DateNow() {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
        return timeStamp;
    }

    public static String DateTimeNow() {
        String fechahora = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        return fechahora;
    }

    public static String TimeNow() {
        String hora = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
        return hora;
    }

    public static Calendar CalendarNow() {
        Calendar cal = Calendar.getInstance();
        return cal;
    }

    @SuppressWarnings("static-access")
    public static String DateFormat(Calendar cal) {
        int ano = 0, mes = 0, dia = 0;
        if (cal != null) {
            ano = cal.get(cal.YEAR);
            mes = cal.get(cal.MONTH) + 1;
            dia = cal.get(cal.DATE);
        }

        String fecha = ano + "-" + mes + "-" + dia;
        return fecha;
    }

    @SuppressWarnings("static-access")
    public static String DateTimeFormat(Calendar cal) {
        int ano = cal.get(cal.YEAR);
        int mes = cal.get(cal.MONTH);
        int dia = cal.get(cal.DATE);
        int hora = cal.get(cal.HOUR);
        int min = cal.get(cal.MINUTE);
        int sec = cal.get(cal.SECOND);
        String fecha = ano + "-" + (mes + 1) + "-" + dia + " " + hora + ":" + min + ":" + sec;
        return fecha;
    }

    public static String generadorPass() {
        String password = "";
        try {
            String[] synbolos = { "0", "1", "-", "*", "%", "$", "a", "b", "c" };
            int lenght = 10;
            Random random;
            random = SecureRandom.getInstanceStrong();
            StringBuilder sb = new StringBuilder(lenght);
            for (int i = 0; i < lenght; i++) {
                int indexrandom = random.nextInt(synbolos.length);
                sb.append(synbolos[indexrandom]);
            }
            password = sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return password;
    }

    public static String ValidarEmail(String email) {
        String regx = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            return email;
        }
        return null;
    }

    public static List<String> ValidarEmail(List<String> email) {
        List<String> ReturnEmails = new ArrayList<String>();

        String regx = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regx);
        for (String email1 : email) {
            Matcher matcher = pattern.matcher(email1);
            if (matcher.matches()) {
                ReturnEmails.add(email1);
            }
        }
        return ReturnEmails;
    }

}
