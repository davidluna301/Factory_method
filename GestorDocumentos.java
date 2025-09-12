import java.util.ArrayList;
import java.util.List;

public class GestorDocumentos {
    private List<Documento> documentos;
    private IDocumentoFactory factory;

    public GestorDocumentos(IDocumentoFactory factory) {
        this.factory = factory;
        this.documentos = new ArrayList<>();
    }

    public void subirDocumentos(List<String> nombresArchivos) {
        for (String nombre : nombresArchivos) {
            Documento doc = factory.crearDocumento(nombre);
            if (doc != null) {
                documentos.add(doc);
                System.out.println("Archivo subido: " + nombre);
            } else {
                System.out.println("❌ No se pudo subir el archivo: " + nombre + " (formato no soportado)");
            }
        }
    }

    public void mostrarDocumentos() {
        if (documentos.isEmpty()) {
            System.out.println("📁 No hay documentos cargados.");
        } else {
            System.out.println("📄 Documentos cargados:");
            for (Documento doc : documentos) {
                System.out.println("- " + doc.getNombre() + " [" + doc.getTipo() + "]");
            }
        }
    }
}
