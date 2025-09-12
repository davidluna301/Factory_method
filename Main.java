import java.util.*;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class Main {
    private static final List<String> PAISES_VALIDOS = Arrays.asList("Colombia", "Argentina", "Mexico", "Chile");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IDocumentoFactory factory = new DocumentoFactory();
        GestorDocumentos gestor = new GestorDocumentos(factory);

        System.out.println("🌎 Bienvenido a GlobalDocsSolutions");
        String pais = "";

        while (true) {
            System.out.print("Ingrese su país (Colombia, Argentina, Mexico, Chile): ");
            pais = scanner.nextLine().trim();
            if (PAISES_VALIDOS.contains(pais)) {
                break;
            } else {
                System.out.println("❗ País no válido.");
            }
        }

        int opcion;
        do {
            System.out.println("\n📋 Menú:");
            System.out.println("1. Subir archivos");
            System.out.println("2. Ver archivos cargados");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            // si el usuario ingresa algo que no es número puede generar error; se podría envolver con try-catch
            while (!scanner.hasNextInt()) {
                System.out.print("Por favor ingrese un número: ");
                scanner.next();
            }
            opcion = scanner.nextInt();
            scanner.nextLine();  // limpiar buffer

            switch (opcion) {
                case 1:
                    List<String> listaNombres = abrirDialogoSeleccionArchivos();
                    gestor.subirDocumentos(listaNombres);
                    break;
                case 2:
                    gestor.mostrarDocumentos();
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

    private static List<String> abrirDialogoSeleccionArchivos() {
        // Forzar Look and Feel del sistema para que JFileChooser se vea más nativo (opcional)
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName()) || "GTK+".equals(info.getName()) || "Mac OS X".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // ignorar si falla, seguir con el look por defecto
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Filtro para extensiones válidas
        FileNameExtensionFilter filtro = new FileNameExtensionFilter(
            "Documentos (.doc, .pdf, .docx, .md, .csv, .txt, .xlsx)",
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
            System.out.println("No se seleccionaron archivos.");
        }

        return nombres;
    }
}
