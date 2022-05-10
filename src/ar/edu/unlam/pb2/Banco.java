package ar.edu.unlam.pb2;

import java.util.Arrays;

public class Banco implements CuentaBancaria {
	private String nombre;
	private Cliente clientes[];
	private Cuenta cuentas[];
	private Integer cantidadDeClientes, cantidadDeCuentas;

	public Banco(String nombre) {
		this.nombre = nombre;
		this.clientes = new Cliente[10];
		this.cuentas = new Cuenta[50];
		this.cantidadDeClientes = 0;
		this.cantidadDeCuentas = 0;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCantidadDeClientes() {
		return cantidadDeClientes;
	}

	public Integer getCantidadDeCuentas() {
		return cantidadDeCuentas;
	}

	@Override
	public String toString() {
		return "Banco=" + nombre + ", clientes= " + clientes.length + ", cuentas= " + cuentas.length;
	}

	public Cliente nuevoCliente(Double montoInicial) {
		for (Cliente cliente : clientes) {
			if (cliente == null) {
				cliente = new Cliente(montoInicial);
				cantidadDeClientes++;
				return cliente;
			}
		}
		return null;
	}

	public Cuenta nuevaCuenta(TipoDeCuenta tipoDeCuenta, Cliente cliente) {

		if (tipoDeCuenta.equals(TipoDeCuenta.CUENTA_SUELDO)) {
			for (Cuenta cuenta : cuentas) {
				if (cuenta == null) {
					cuenta = new CuentaSueldo(cliente);
					cliente.agregarCuentaAlCliente(cuenta);
					cantidadDeCuentas++;
					return cuenta;
				}
			}
			return null;
		} else if (tipoDeCuenta.equals(TipoDeCuenta.CAJA_DE_AHORRO)) {
			for (Cuenta cuenta : cuentas) {
				if (cuenta == null) {
					cuenta = new CajaDeAhorro(cliente);
					cliente.agregarCuentaAlCliente(cuenta);
					cantidadDeCuentas++;
					return cuenta;
				}
			}
			return null;
		} else if (tipoDeCuenta.equals(TipoDeCuenta.CUENTA_CORRIENTE)) {
			for (Cuenta cuenta : cuentas) {
				if (cuenta == null) {
					cuenta = new CuentaCorriente(cliente);
					cliente.agregarCuentaAlCliente(cuenta);
					cantidadDeCuentas++;
					return cuenta;
				}
			}
			return null;
		} else
			return null;
	}

	public Double balanceDeLaCuentaBancariaDelCliente(Cliente cliente) {
		return cliente.getDineroTotal();
	}

	public String transaccionesDelCliente(Cliente cliente, TipoDeTransaccion transaccion, Double monto) {
		cliente.setDineroTotal(transaccion, monto);
		return "Luego de esta transaccion le queda " + cliente.getDineroTotal() + " de dinero en total.";
	}

}
