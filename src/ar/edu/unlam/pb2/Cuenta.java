package ar.edu.unlam.pb2;

public abstract class Cuenta {
	private Cliente dueñoDeLaCuenta;

	public Cuenta(Cliente cliente) {
		dueñoDeLaCuenta = cliente;
	}

	public abstract Boolean extraccionDeDinero(Double dineroQueDeseaExtraer);

	public Cliente getDueñoDeLaCuenta() {
		return dueñoDeLaCuenta;
	}

}
