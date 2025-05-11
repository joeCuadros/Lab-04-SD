import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class GestorCuenta extends UnicastRemoteObject implements GestorCuentaInterface{
    private HashMap<String, CuentaInterface> cuentas;
    private double limite;

    public GestorCuenta(double limite)  throws RemoteException {
        super();
        this.cuentas = new HashMap<>();
        this.limite = limite;
    }

    @Override
    public void agregarCuenta(String nombre) throws Exception {
        CuentaInterface cuenta = this.cuentas.get(nombre);
        if (cuenta != null) throw new CuentaException("Ya existe cuenta con el nombre "+ nombre);
        this.cuentas.put(nombre, new Cuenta(limite));
    }

    @Override
    public String verEstadoCuenta(String nombre) throws Exception {
        String estado = this.obtenerCuenta(nombre).estado();
        return "Estado de cuenta de " + nombre + ":\n" + estado;
    }

    @Override
    public void comprar(double costo, String nombre) throws Exception {
        this.obtenerCuenta(nombre).comprar(costo);
    }

    @Override
    public void pagar(double abono, String nombre) throws Exception {
        this.obtenerCuenta(nombre).pagar(abono);
    }

    @Override
    public boolean validarCuenta(String nombre) throws Exception {
        return this.cuentas.containsKey(nombre);
    }

    private CuentaInterface obtenerCuenta (String nombre) throws Exception {
        CuentaInterface cuenta = this.cuentas.get(nombre);
        if (cuenta == null) throw new CuentaException("No existe cuenta con el nombre " + nombre);
        return cuenta;
    }

}
