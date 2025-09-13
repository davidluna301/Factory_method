public class Main {
    public static void main(String[] args) {
        IDocumentoFactory factory = new DocumentoFactory();
        GestorDocumentos gestor = new GestorDocumentos(factory);
        gestor.iniciar();
    }
}
