package banco;

import java.util.Scanner;

public class Main {
    private static Banco banco;
    private static Scanner scanner;

    public static void main(String[] args) {
        banco = new Banco();
        scanner = new Scanner(System.in);
        
        while (true) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            try {
                procesarOpcion(opcion);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n=== BANCO ===");
        System.out.println("1. Crear cuenta");
        System.out.println("2. Depositar");
        System.out.println("3. Retirar");
        System.out.println("4. Transferir");
        System.out.println("5. Consultar saldo");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                crearCuenta();
                break;
            case 2:
                depositar();
                break;
            case 3:
                retirar();
                break;
            case 4:
                transferir();
                break;
            case 5:
                consultarSaldo();
                break;
            case 6:
                System.out.println("¡Gracias por usar nuestro banco!");
                System.exit(0);
            default:
                System.out.println("Opción inválida");
        }
    }

    private static void crearCuenta() {
        System.out.print("Ingrese el número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        System.out.print("Ingrese el nombre del titular: ");
        String titular = scanner.nextLine();
        System.out.print("Ingrese el saldo inicial: ");
        double saldoInicial = scanner.nextDouble();

        banco.crearCuenta(numeroCuenta, titular, saldoInicial);
        System.out.println("Cuenta creada exitosamente");
    }

    private static void depositar() {
        System.out.print("Ingrese el número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        System.out.print("Ingrese el monto a depositar: ");
        double monto = scanner.nextDouble();

        banco.depositar(numeroCuenta, monto);
        System.out.println("Depósito realizado exitosamente");
    }

    private static void retirar() {
        System.out.print("Ingrese el número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        System.out.print("Ingrese el monto a retirar: ");
        double monto = scanner.nextDouble();

        banco.retirar(numeroCuenta, monto);
        System.out.println("Retiro realizado exitosamente");
    }

    private static void transferir() {
        System.out.print("Ingrese el número de cuenta origen: ");
        String cuentaOrigen = scanner.nextLine();
        System.out.print("Ingrese el número de cuenta destino: ");
        String cuentaDestino = scanner.nextLine();
        System.out.print("Ingrese el monto a transferir: ");
        double monto = scanner.nextDouble();

        banco.transferir(cuentaOrigen, cuentaDestino, monto);
        System.out.println("Transferencia realizada exitosamente");
    }

    private static void consultarSaldo() {
        System.out.print("Ingrese el número de cuenta: ");
        String numeroCuenta = scanner.nextLine();

        double saldo = banco.consultarSaldo(numeroCuenta);
        System.out.println("El saldo de la cuenta es: $" + saldo);
    }
}
