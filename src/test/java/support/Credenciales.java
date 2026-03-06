package support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Credenciales {
    private static final Properties properties = new Properties();
    static {
        try (InputStream inputStream = Credenciales.class
                .getClassLoader()
                .getResourceAsStream("credentials/credenciales.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new RuntimeException("No se encontró el archivo Credenciales.properties");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar el archivo de propiedades", e);
        }
    }

    public static String obtenerDocumento() {
        return properties.getProperty("documento");
    }
    public static String enmascarar(String valor) {
        if (valor == null || valor.length() < 4) return "****";
        return valor.substring(0, 4) + "*".repeat(valor.length() - 4);
    }
}