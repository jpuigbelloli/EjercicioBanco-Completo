package ar.edu.unlam.pb2;

public class CuentaSueldo extends Cuenta {

	public CuentaSueldo(Cliente cliente) {
		super(cliente);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean extraccionDeDinero(Double dineroQueDeseaExtraer) {
		if (super.getDue�oDeLaCuenta().getDineroTotal() >= dineroQueDeseaExtraer) {
			super.getDue�oDeLaCuenta().setDineroTotal(TipoDeTransaccion.EXTRACCION_DINERO, dineroQueDeseaExtraer);
			return true;
		}
		return false;
	}
}
