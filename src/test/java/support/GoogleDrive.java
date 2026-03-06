package support;

import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class GoogleDrive {
    private static final String APPLICATION_NAME = "Uploader de Reportes";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE_FILE);
    //private static final String CREDENTIALS_FILE_PATH = "src/test/resources/credentials/credentials.json";
    private static final String CREDENTIALS_FILE_PATH = "";
    private static final String CARPETA_PADRE_ID = "1-MaLdG_ZBnnC7u0kPxkFHtZt-IpwoB_C"; // carpeta publica

    private static Drive getDriveService() throws IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        InputStream in = new FileInputStream(CREDENTIALS_FILE_PATH);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();

        return new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY,
                new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user"))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    private static String crearCarpetaEnDrive(String nombre, String parentId, Drive service) throws IOException {
        File fileMetadata = new File();
        fileMetadata.setName(nombre);
        fileMetadata.setMimeType("application/vnd.google-apps.folder");
        fileMetadata.setParents(Collections.singletonList(parentId));

        File carpetaCreada = service.files().create(fileMetadata)
                .setFields("id")
                .execute();

        return carpetaCreada.getId();
    }

    private static void subirRecursivo(java.io.File archivoLocal, String parentDriveId, Drive service) throws IOException {
        if (archivoLocal.isDirectory()) {
            String nuevaCarpetaId = crearCarpetaEnDrive(archivoLocal.getName(), parentDriveId, service);
            for (java.io.File archivo : Objects.requireNonNull(archivoLocal.listFiles())) {
                subirRecursivo(archivo, nuevaCarpetaId, service);
            }
        } else {
            com.google.api.client.http.FileContent mediaContent =
                    new com.google.api.client.http.FileContent(null, archivoLocal);

            File fileMetadata = new File();
            fileMetadata.setName(archivoLocal.getName());
            fileMetadata.setParents(Collections.singletonList(parentDriveId));

            service.files().create(fileMetadata, mediaContent)
                    .setFields("id")
                    .execute();
        }
    }

    public static String subirCarpetaYObtenerLink(java.io.File carpeta) {
        //try {
        //    Drive service = getDriveService();
        //    String idCarpetaSubida = crearCarpetaEnDrive(carpeta.getName(), CARPETA_PADRE_ID, service);
        //    subirRecursivo(carpeta, idCarpetaSubida, service);
        //    return "https://drive.google.com/drive/folders/" + idCarpetaSubida;
        //} catch (Exception e) {
        //    e.printStackTrace();
        //    System.out.println("Error al subir carpeta a Drive: " + e.getMessage());
        //    return "Error subiendo a Drive";
        //}
        return ""; //Comentar luego y descomentar el bloque de arriba
    }
}