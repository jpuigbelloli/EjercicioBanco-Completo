package ar.edu.unlam.pb2;

public class CajaDeAhorro extends Cuenta {

	private Integer cantidadDeExtraccionesRealizadas;
	private Double recargo;

	public CajaDeAhorro(Cliente cliente) {
		super(cliente);
		cantidadDeExtraccionesRealizadas = 0;
		recargo = 6.0;
	}

	@Override
	public Boolean extraccionDeDinero(Double dineroQueDeseaExtraer) {
		if (cantidadDeExtraccionesRealizadas < 5) {
			if (super.getDueñoDeLaCuenta().getDineroTotal() >= dineroQueDeseaExtraer) {
				super.getDueñoDeLaCuenta().setDineroTotal(TipoDeTransaccion.EXTRACCION_DINERO, dineroQueDeseaExtraer);
				cantidadDeExtraccionesRealizadas++;
				return true;
			}
		} else {
			if (super.getDueñoDeLaCuenta().getDineroTotal() >= (dineroQueDeseaExtraer + recargo)) {
				super.getDueñoDeLaCuenta().setDineroTotal(TipoDeTransaccion.EXTRACCION_DINERO,
						(dineroQueDeseaExtraer + recargo));
				cantidadDeExtraccionesRealizadas++;
				return true;
			}
		}
		return false;
	}

	public Double getRecargo() {
		return recargo;
	}

	public void setRecargo(Double recargo) {
		this.recargo = recargo;
	}
}
