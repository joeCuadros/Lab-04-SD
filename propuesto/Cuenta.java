import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Cuenta extends UnicastRemoteObject implements CuentaInterface {
    private double saldoDisponible;
    private final double limiteCredito;

    public Cuenta(double limiteCredito) throws RemoteException {
        super();
        this.limiteCredito = limiteCredito;
        this.saldoDisponible = limiteCredito;
    }

    @Override
    public String estado() throws Exception {
        return "Saldo disponible: $" + this.saldoDisponible + 
            "\nDeuda: $" + (this.limiteCredito - this.saldoDisponible);
    }

    @Override
    public void comprar(double costo) throws Exception {
        if (costo < 0) throw new CuentaException("No existe costo negativo");
        if (costo > this.saldoDisponible) throw new CuentaException("No saldo disponible ");
        saldoDisponible -= costo;
    }

    @Override
    public void pagar(double abono) throws Exception {
        if (abono < 0) throw new CuentaException("No existe abono negativo");
        if (this.saldoDisponible + abono > this.limiteCredito) throw new CuentaException("El abono excede el limite de credito");
        saldoDisponible += abono; 
    }
}
