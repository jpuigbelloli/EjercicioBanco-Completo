package ar.edu.unlam.pb2;

public abstract class Cuenta {
	private Cliente due�oDeLaCuenta;

	public Cuenta(Cliente cliente) {
		due�oDeLaCuenta = cliente;
	}

	public abstract Boolean extraccionDeDinero(Double dineroQueDeseaExtraer);

	public Cliente getDue�oDeLaCuenta() {
		return due�oDeLaCuenta;
	}

}
