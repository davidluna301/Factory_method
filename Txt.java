public class Txt extends Documento {
    public Txt(String nombre) {
        super(nombre);
    }

    @Override
    public String getTipo() {
        return "TXT";
    }
}
