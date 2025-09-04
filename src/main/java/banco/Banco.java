package banco;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Banco {
    private Map<String, Cuenta> cuentas;
    private static final String ARCHIVO_DATOS = "cuentas.dat";

    public Banco() {
        cuentas = new HashMap<>();
        cargarCuentas();
    }

    public void crearCuenta(String numeroCuenta, String titular, double saldoInicial) {
        if (cuentas.containsKey(numeroCuenta)) {
            throw new IllegalArgumentException("Ya existe una cuenta con ese número");
        }
        Cuenta nuevaCuenta = new Cuenta(numeroCuenta, titular, saldoInicial);
        cuentas.put(numeroCuenta, nuevaCuenta);
        guardarCuentas();
    }

    public void depositar(String numeroCuenta, double monto) {
        Cuenta cuenta = obtenerCuenta(numeroCuenta);
        cuenta.depositar(monto);
        guardarCuentas();
    }

    public void retirar(String numeroCuenta, double monto) {
        Cuenta cuenta = obtenerCuenta(numeroCuenta);
        cuenta.retirar(monto);
        guardarCuentas();
    }

    public void transferir(String cuentaOrigen, String cuentaDestino, double monto) {
        Cuenta origen = obtenerCuenta(cuentaOrigen);
        Cuenta destino = obtenerCuenta(cuentaDestino);
        origen.retirar(monto);
        destino.depositar(monto);
        guardarCuentas();
    }

    public double consultarSaldo(String numeroCuenta) {
        return obtenerCuenta(numeroCuenta).getSaldo();
    }

    private Cuenta obtenerCuenta(String numeroCuenta) {
        Cuenta cuenta = cuentas.get(numeroCuenta);
        if (cuenta == null) {
            throw new IllegalArgumentException("La cuenta no existe");
        }
        return cuenta;
    }

    @SuppressWarnings("unchecked")
    private void cargarCuentas() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_DATOS))) {
            cuentas = (Map<String, Cuenta>) ois.readObject();
        } catch (FileNotFoundException e) {
            cuentas = new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            cuentas = new HashMap<>();
        }
    }

    private void guardarCuentas() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_DATOS))) {
            oos.writeObject(cuentas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
