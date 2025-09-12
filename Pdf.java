public class Pdf extends Documento {
    public Pdf(String nombre) {
        super(nombre);
    }

    @Override
    public String getTipo() {
        return "PDF";
    }
}
