public class Md extends Documento {
    public Md(String nombre) {
        super(nombre);
    }

    @Override
    public String getTipo() {
        return "MD";
    }
}
