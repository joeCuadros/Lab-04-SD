import java.rmi.Remote;

public interface CuentaInterface extends Remote {
    public String estado() throws Exception;
    public void comprar(double costo) throws Exception;
    public void pagar(double abono) throws Exception;
}