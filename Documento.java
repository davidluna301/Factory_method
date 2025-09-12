public abstract class Documento {
    protected String nombre;

    public Documento(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract String getTipo();
}
