package ar.edu.unlam.pb2;

public interface CuentaBancaria {

	public abstract Double balanceDeLaCuentaBancariaDelCliente(Cliente cliente);

	public abstract String transaccionesDelCliente(Cliente cliente, TipoDeTransaccion transaccion, Double monto);

}
