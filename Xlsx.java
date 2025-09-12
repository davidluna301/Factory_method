public class Xlsx extends Documento {
    public Xlsx(String nombre) {
        super(nombre);
    }

    @Override
    public String getTipo() {
        return "XLSX";
    }
}
