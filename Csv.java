public class Csv extends Documento {
    public Csv(String nombre) {
        super(nombre);
    }

    @Override
    public String getTipo() {
        return "CSV";
    }
}
