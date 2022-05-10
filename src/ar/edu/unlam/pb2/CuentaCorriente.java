package ar.edu.unlam.pb2;

public class CuentaCorriente extends Cuenta {

	private Double giroEnDescubiertoHabilitado;
	private Double comisionCobradaPorElBanco;
	private Double totalDeuda;

	public CuentaCorriente(Cliente cliente) {
		super(cliente);
		this.giroEnDescubiertoHabilitado = 200.0;
		comisionCobradaPorElBanco = 5.0;
		totalDeuda = 0.0;
	}

	@Override
	public Boolean extraccionDeDinero(Double dineroQueDeseaExtraer) {
		if (super.getDueñoDeLaCuenta().getDineroTotal() >= dineroQueDeseaExtraer) {
			super.getDueñoDeLaCuenta().setDineroTotal(TipoDeTransaccion.EXTRACCION_DINERO, dineroQueDeseaExtraer);
			return true;
		} else {
			Double giroRequerido = dineroQueDeseaExtraer - super.getDueñoDeLaCuenta().getDineroTotal();
			Double gastoExtra = calcularComisionPorUsoDeGiro(giroRequerido);
			totalDeuda = giroRequerido + gastoExtra;

			if ((dineroQueDeseaExtraer + gastoExtra) <= super.getDueñoDeLaCuenta().getDineroTotal()
					+ giroEnDescubiertoHabilitado) {
				super.getDueñoDeLaCuenta().setDineroTotal(TipoDeTransaccion.EXTRACCION_DINERO, dineroQueDeseaExtraer);
				return true;
			}
		}
		return false;
	}

	public Double calcularComisionPorUsoDeGiro(Double giroRequerido) {
		Double totalPorcentajeCobrado = ((comisionCobradaPorElBanco / 100) * giroRequerido);
		return totalPorcentajeCobrado;
	}

	public Double getgiroEnDescubiertoHabilitado() {
		return giroEnDescubiertoHabilitado;
	}

	public void setgiroEnDescubiertoHabilitado(Double giroEnDescubiertoHabilitado) {
		this.giroEnDescubiertoHabilitado = giroEnDescubiertoHabilitado;
	}

	public Double getTotalDeuda() {
		return totalDeuda;
	}

}
