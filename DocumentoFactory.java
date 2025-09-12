public class DocumentoFactory implements IDocumentoFactory {

    @Override
    public Documento crearDocumento(String nombreArchivo) {
        String extension = obtenerExtension(nombreArchivo);

        switch (extension.toLowerCase()) {
            case "doc": return new Doc(nombreArchivo);
            case "pdf": return new Pdf(nombreArchivo);
            case "txt": return new Txt(nombreArchivo);
            case "csv": return new Csv(nombreArchivo);
            case "docx": return new Docx(nombreArchivo);
            case "md": return new Md(nombreArchivo);
            case "xlsx": return new Xlsx(nombreArchivo);
            default: return null;
        }
    }

    private String obtenerExtension(String archivo) {
        int index = archivo.lastIndexOf(".");
        return index != -1 ? archivo.substring(index + 1) : "";
    }
}
