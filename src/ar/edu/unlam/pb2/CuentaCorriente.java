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
		if (super.getDue�oDeLaCuenta().getDineroTotal() >= dineroQueDeseaExtraer) {
			super.getDue�oDeLaCuenta().setDineroTotal(TipoDeTransaccion.EXTRACCION_DINERO, dineroQueDeseaExtraer);
			return true;
		} else {
			Double giroRequerido = dineroQueDeseaExtraer - super.getDue�oDeLaCuenta().getDineroTotal();
			Double gastoExtra = calcularComisionPorUsoDeGiro(giroRequerido);
			totalDeuda = giroRequerido + gastoExtra;

			if ((dineroQueDeseaExtraer + gastoExtra) <= super.getDue�oDeLaCuenta().getDineroTotal()
					+ giroEnDescubiertoHabilitado) {
				super.getDue�oDeLaCuenta().setDineroTotal(TipoDeTransaccion.EXTRACCION_DINERO, dineroQueDeseaExtraer);
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
