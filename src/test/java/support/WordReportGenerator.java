package support;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class WordReportGenerator {
    private XWPFDocument document;
    private String outputPath;

    public WordReportGenerator(String outputPath) {
        this.document = new XWPFDocument();
        this.outputPath = outputPath;
    }

    public void agregarPaso(String textoPaso) {
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setFontFamily("Arial");
        run.setFontSize(12);
        run.setBold(false);
        run.setText(textoPaso);
    }

    public void agregarPasoConEnlace(String textoNormal, String enlace) {
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun runTexto = paragraph.createRun();
        runTexto.setFontFamily("Arial");
        runTexto.setFontSize(12);
        runTexto.setBold(false);
        runTexto.setText(textoNormal);

        XWPFRun runEnlace = paragraph.createRun();
        runEnlace.setFontFamily("Arial");
        runEnlace.setFontSize(12);
        runEnlace.setBold(false);
        runEnlace.setColor("0070C0");
        runEnlace.setUnderline(UnderlinePatterns.SINGLE);
        runEnlace.setText(enlace);
    }

    public void agregarImagen(String rutaImagen) throws Exception {
        try (InputStream is = new FileInputStream(rutaImagen)) {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.addBreak();
            run.addPicture(is, Document.PICTURE_TYPE_PNG, rutaImagen, Units.toEMU(400), Units.toEMU(250));
        }
    }

    public void guardar() throws IOException {
        try (FileOutputStream out = new FileOutputStream(outputPath)) {
            document.write(out);
        }
    }
}
