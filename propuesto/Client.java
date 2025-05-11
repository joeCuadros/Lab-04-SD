import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.Scanner;
import java.rmi.RemoteException;

public class Client {
    public static void main(String [] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        String nombre;  
        try {
            GestorCuentaInterface cuentas = (GestorCuentaInterface) Naming.lookup("rmi://localhost:1099/CuentaService");  
            // opcion de colocar java Client [nombre] o java Client
            if (args.length == 0){
                System.out.println("Coloque su nombre de usuario");
                nombre = sc.next();
            } else {
                nombre = args[0];
            }
            // crear cuenta si es necesario
            if (!cuentas.validarCuenta(nombre)){
                System.out.println("Quiere crear una nueva cuenta con el nombre proporcionado ["
                                    + nombre + "] ? (S = si)");
                String opcionBase = sc.next();
                if (opcionBase.equalsIgnoreCase("S")){
                    cuentas.agregarCuenta(nombre);
                } else {
                    sc.close();
                    return;
                }
            }
            System.out.println("Bienvenido usuario"+ nombre 
                                + "\n1. Ver estado de cuenta"
                                + "\n2. Compra con tarjeta"
                                + "\n3. Pagar deuda");
            System.out.print("Opcion: ");
            int opcion = sc.nextInt();                    
            if (opcion == 1){
                String estado = cuentas.verEstadoCuenta(nombre);
                System.out.println(estado);    
            } else if (opcion == 2){
                System.out.println("Coloque el costo del producto");
                double monto = sc.nextDouble();
                cuentas.comprar(monto, nombre);
            } else if (opcion == 3){
                System.out.println("Coloque el monto de deuda a pagar");
                double deuda = sc.nextDouble();
                cuentas.pagar(deuda, nombre);
            } else {
                System.out.println("Opcion incorrecta");
            }
            

        } catch (CuentaException e) {
            System.out.println("Error de cuenta: " + e.getMessage());    
        } catch (RemoteException e) {
            System.out.println("Error remoto: " + e.getMessage());
        } catch (NotBoundException e) {
            System.out.println("Servicio no encontrado en el registro RMI: " + e.getMessage()); 
        } catch (Exception e) {
            System.out.println("Error desconocido: " + e.getMessage()); 
        } finally {
            sc.close();
        }
        
    }
}
