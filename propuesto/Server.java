import java.rmi.Naming;

public class Server {
    public static void main(String [] args) throws Exception {
        GestorCuenta gestor = new GestorCuenta(1000);
        gestor.agregarCuenta("joe");
        gestor.agregarCuenta("misael");
        gestor.agregarCuenta("rodrigo");
        gestor.agregarCuenta("piero");
        Naming.rebind("rmi://localhost:1099/CuentaService", gestor);
        System.out.println("Server listo");
    }
}
