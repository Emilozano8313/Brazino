package banco;

import java.io.Serializable;

public class Cuenta implements Serializable {
    private static final long serialVersionUID = 1L;
    private String numeroCuenta;
    private String titular;
    private double saldo;

    public Cuenta(String numeroCuenta, String titular, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
        } else {
            throw new IllegalArgumentException("El monto a depositar debe ser positivo");
        }
    }

    public void retirar(double monto) {
        if (monto > 0) {
            if (saldo >= monto) {
                saldo -= monto;
            } else {
                throw new IllegalArgumentException("Saldo insuficiente");
            }
        } else {
            throw new IllegalArgumentException("El monto a retirar debe ser positivo");
        }
    }
}
