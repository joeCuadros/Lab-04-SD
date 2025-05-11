import java.rmi.Remote;

public interface GestorCuentaInterface extends Remote {
    public void agregarCuenta(String nombre) throws Exception;
    public boolean validarCuenta(String nombre) throws Exception;
    public String verEstadoCuenta(String nombre) throws Exception;
    public void comprar(double costo, String nombre) throws Exception;
    public void pagar(double abono, String nombre) throws Exception;
}