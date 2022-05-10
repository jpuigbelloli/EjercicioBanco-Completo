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
			if (super.getDue�oDeLaCuenta().getDineroTotal() >= dineroQueDeseaExtraer) {
				super.getDue�oDeLaCuenta().setDineroTotal(TipoDeTransaccion.EXTRACCION_DINERO, dineroQueDeseaExtraer);
				cantidadDeExtraccionesRealizadas++;
				return true;
			}
		} else {
			if (super.getDue�oDeLaCuenta().getDineroTotal() >= (dineroQueDeseaExtraer + recargo)) {
				super.getDue�oDeLaCuenta().setDineroTotal(TipoDeTransaccion.EXTRACCION_DINERO,
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
