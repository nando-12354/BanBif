package tools;

//import io.cucumber.java.AfterAll;

import org.json.JSONArray;
import org.json.JSONObject;
import support.Correo;
import support.GoogleDrive;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;

public class ResumenFinal {
    public static void enviarResumenFinal() {
        boolean hayErrores = false;
        String rutaJson = "target/reporte-cucumber/cucumber.json";
        File jsonFile = new File(rutaJson);

        if (jsonFile.exists()) {
            try {
                String contenido = new String(Files.readAllBytes(Paths.get(rutaJson)));
                JSONArray features = new JSONArray(contenido);

                for (int i = 0; i < features.length(); i++) {
                    JSONObject feature = features.getJSONObject(i);
                    JSONArray scenarios = feature.optJSONArray("elements");

                    if (scenarios != null) {
                        for (int j = 0; j < scenarios.length(); j++) {
                            JSONObject scenario = scenarios.getJSONObject(j);
                            JSONArray steps = scenario.optJSONArray("steps");

                            if (steps != null) {
                                for (int k = 0; k < steps.length(); k++) {
                                    JSONObject step = steps.getJSONObject(k);
                                    JSONObject result = step.optJSONObject("result");

                                    if (result != null && "failed".equalsIgnoreCase(result.optString("status"))) {
                                        hayErrores = true;
                                        break;
                                    }
                                }
                            }
                            if (hayErrores) break;
                        }
                    }
                    if (hayErrores) break;
                }
            } catch (Exception e) {
                System.out.println("⚠️ Error al leer el archivo JSON: " + e.getMessage());
            }
        }

        // Buscar la carpeta Cluecumber mas reciente
        File reporteRoot = new File("target/reporte-html");
        File[] subCarpetas = reporteRoot.listFiles(File::isDirectory);
        File ultimaCarpeta = null;

        if (subCarpetas != null && subCarpetas.length > 0) {
            ultimaCarpeta = Arrays.stream(subCarpetas)
                    .max(Comparator.comparingLong(File::lastModified))
                    .orElse(null);
        }

        String driveLink = (ultimaCarpeta != null)
                ? GoogleDrive.subirCarpetaYObtenerLink(ultimaCarpeta)
                : "No se encontró reporte Cluecumber";

        String asunto = hayErrores
                ? "❌ Reporte Final: Se detectaron errores en algun(os) escenario(s)"
                : "✅ Reporte Final: Todas las pruebas pasaron correctamente";

        String mensaje = "Las pruebas han finalizado. Puedes revisar el reporte completo en:\n\n"
                + driveLink + "\n\n"
                + (hayErrores ? "🔴 Se encontraron fallos en uno o más escenarios." : "🟢 Todos los escenarios pasaron exitosamente.");

        Correo.enviarCorreo(asunto, mensaje, !hayErrores);
    }
}