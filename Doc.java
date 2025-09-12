public class Doc extends Documento {
    public Doc(String nombre) {
        super(nombre);
    }

    @Override
    public String getTipo() {
        return "DOC";
    }
}
