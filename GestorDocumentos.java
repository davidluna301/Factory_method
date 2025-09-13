import java.util.*;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GestorDocumentos {
    private IDocumentoFactory factory;
    private List<Documento> documentos;
    private final List<String> PAISES_VALIDOS = Arrays.asList("Colombia", "Argentina", "Mexico", "Chile");

    public GestorDocumentos(IDocumentoFactory factory) {
        this.factory = factory;
        this.documentos = new ArrayList<>();
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        validarPais(scanner);

        int opcion;
        do {
            mostrarMenu();
            opcion = obtenerOpcion(scanner);

            switch (opcion) {
                case 1:
                    List<String> archivosSeleccionados = seleccionarArchivosConDialogo();
                    subirDocumentos(archivosSeleccionados);
                    break;
                case 2:
                    mostrarDocumentos();
                    break;
                case 0:
                    System.out.println("👋 Gracias por usar GlobalDocsSolutions.");
                    break;
                default:
                    System.out.println("❌ Opción no válida.");
            }

        } while (opcion != 0);

        scanner.close();
    }

    private void validarPais(Scanner scanner) {
        String pais;
        do {
            System.out.print("🌎 Ingrese su país (Colombia, Argentina, Mexico, Chile): ");
            pais = scanner.nextLine().trim();
            if (!PAISES_VALIDOS.contains(pais)) {
                System.out.println("❗ País no válido. Intente nuevamente.");
            }
        } while (!PAISES_VALIDOS.contains(pais));
    }

    private void mostrarMenu() {
        System.out.println("\n📋 Menú:");
        System.out.println("1. Subir archivos");
        System.out.println("2. Ver archivos cargados");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private int obtenerOpcion(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Ingrese un número válido: ");
            scanner.next();
        }
        int opcion = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer
        return opcion;
    }

    private List<String> seleccionarArchivosConDialogo() {
        configurarLookAndFeel();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        FileNameExtensionFilter filtro = new FileNameExtensionFilter(
            "Archivos válidos (.doc, .pdf, .docx, .md, .csv, .txt, .xlsx)",
            "doc", "pdf", "docx", "md", "csv", "txt", "xlsx"
        );
        fileChooser.setFileFilter(filtro);
        fileChooser.setAcceptAllFileFilterUsed(false);

        List<String> nombres = new ArrayList<>();
        int resultado = fileChooser.showOpenDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File[] archivos = fileChooser.getSelectedFiles();
            for (File f : archivos) {
                nombres.add(f.getName());
            }
        } else {
            System.out.println("🚫 No se seleccionaron archivos.");
        }

        return nombres;
    }

    private void configurarLookAndFeel() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName()) || "GTK+".equals(info.getName()) || "Mac OS X".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Ignorar si falla
        }
    }

    public void subirDocumentos(List<String> nombresArchivos) {
        for (String nombre : nombresArchivos) {
            Documento doc = factory.crearDocumento(nombre);
            if (doc != null) {
                documentos.add(doc);
                System.out.println("✅ Archivo subido: " + nombre);
            } else {
                System.out.println("❌ No se pudo subir: " + nombre + " (formato no soportado)");
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
