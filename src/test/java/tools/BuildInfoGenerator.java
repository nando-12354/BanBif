package tools;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

public class BuildInfoGenerator {
    public static void main(String[] args) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy_HHmmss");
            sdf.setTimeZone(TimeZone.getTimeZone("America/Lima"));
            String formattedDate = sdf.format(new Date());

            Properties props = new Properties();
            props.setProperty("buildNumber", formattedDate);

            try (OutputStream output = new FileOutputStream("build.properties")) {
                props.store(output, "Build Info with Lima Timezone");
            }

            System.out.println("buildNumber generado: " + formattedDate);
        } catch (Exception e) {
            System.err.println("Error generando buildNumber: " + e.getMessage());
        }
    }
}