public class Docx extends Documento {
    public Docx(String nombre) {
        super(nombre);
    }

    @Override
    public String getTipo() {
        return "DOCX";
    }
}
