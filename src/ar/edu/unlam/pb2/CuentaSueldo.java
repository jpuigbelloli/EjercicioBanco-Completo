package ar.edu.unlam.pb2;

public class CuentaSueldo extends Cuenta {

	public CuentaSueldo(Cliente cliente) {
		super(cliente);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean extraccionDeDinero(Double dineroQueDeseaExtraer) {
		if (super.getDueñoDeLaCuenta().getDineroTotal() >= dineroQueDeseaExtraer) {
			super.getDueñoDeLaCuenta().setDineroTotal(TipoDeTransaccion.EXTRACCION_DINERO, dineroQueDeseaExtraer);
			return true;
		}
		return false;
	}
}
