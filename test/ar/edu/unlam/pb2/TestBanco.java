package ar.edu.unlam.pb2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestBanco {

	@Test
	public void queSePuedaCrearUnBanco() {
		Banco galicia = new Banco("Galicia");

		assertNotNull(galicia);
	}

	@Test
	public void queUnBancoPuedaTenerClientes() {
		Banco galicia = new Banco("Galicia");
		final Integer CANTIDAD_DE_CLIENTES_ESPERADOS = 1;

		assertNotNull(galicia.nuevoCliente(500.0));
		assertEquals(CANTIDAD_DE_CLIENTES_ESPERADOS, galicia.getCantidadDeClientes());
	}

	@Test
	public void queUnBancoRegistreElBalance() {
		Banco galicia = new Banco("Galicia");
		final Double BALANCE_ESPERADO = 8.8;
		Cliente juan = galicia.nuevoCliente(BALANCE_ESPERADO);

		assertEquals(BALANCE_ESPERADO, galicia.balanceDeLaCuentaBancariaDelCliente(juan));
	}

	@Test
	public void queUnBancoRegistreTransaccionesDelCliente() {
		Banco galicia = new Banco("Galicia");
		final Double DINERO_DE_NUEVA_CUENTA = 9000.0;
		final Double DINERO_A_RETIRAR = 6000.0;
		TipoDeTransaccion transaccionRetiro = TipoDeTransaccion.EXTRACCION_DINERO;
		String mensajeEsperado = "Luego de esta transaccion le queda 3000.0 de dinero en total.";
		Cliente juan = galicia.nuevoCliente(DINERO_DE_NUEVA_CUENTA);

		assertEquals(mensajeEsperado, galicia.transaccionesDelCliente(juan, transaccionRetiro, DINERO_A_RETIRAR));
	}

	@Test
	public void queUnBancoPuedaCrearCuentaSueldo() {
		Banco galicia = new Banco("Galicia");
		Cliente jose = galicia.nuevoCliente(350000.0);
		final Integer CANTIDAD_DE_CUENTAS_ESPERADOS = 1;

		assertNotNull(galicia.nuevaCuenta(TipoDeCuenta.CUENTA_SUELDO, jose));
		assertEquals(CANTIDAD_DE_CUENTAS_ESPERADOS, galicia.getCantidadDeCuentas());
	}

	@Test
	public void queCuentaSueldoNoPuedaRetirarMasDeLoQuePosea() {
		Banco galicia = new Banco("Galicia");
		Cliente jose = galicia.nuevoCliente(350000.0);
		Cuenta cuentaJose = galicia.nuevaCuenta(TipoDeCuenta.CUENTA_SUELDO, jose);

		assertFalse(cuentaJose.extraccionDeDinero(350000.1));
	}

	@Test
	public void queUnBancoPuedaCrearCajaDeAhorro() {
		Banco galicia = new Banco("Galicia");
		Cliente jose = galicia.nuevoCliente(350000.0);
		final Integer CANTIDAD_DE_CUENTAS_ESPERADOS = 1;

		assertNotNull(galicia.nuevaCuenta(TipoDeCuenta.CAJA_DE_AHORRO, jose));
		assertEquals(CANTIDAD_DE_CUENTAS_ESPERADOS, galicia.getCantidadDeCuentas());
	}

	@Test
	public void queCajaDeAhorroLuegoDeLaQuintaExtraccionCobreSeisPesos() {
		Banco galicia = new Banco("Galicia");
		Cliente juan = galicia.nuevoCliente(36.0);
		Cuenta cuentaJuan = galicia.nuevaCuenta(TipoDeCuenta.CAJA_DE_AHORRO, juan);
		final Double RESTO_ESPERADO = 0.0;

		assertTrue(cuentaJuan.extraccionDeDinero(5.0));
		assertTrue(cuentaJuan.extraccionDeDinero(5.0));
		assertTrue(cuentaJuan.extraccionDeDinero(5.0));
		assertTrue(cuentaJuan.extraccionDeDinero(5.0));
		assertTrue(cuentaJuan.extraccionDeDinero(5.0));
		assertTrue(cuentaJuan.extraccionDeDinero(5.0));

		assertEquals(RESTO_ESPERADO, cuentaJuan.getDueñoDeLaCuenta().getDineroTotal());
	}

	@Test
	public void queCajaDeAhorroLuegoDeLaQuintaExtraccionCobreSeisPesosYNoPuedaRetirarMasDelLimite() {
		Banco galicia = new Banco("Galicia");
		Cliente juan = galicia.nuevoCliente(35.0);
		Cuenta cuentaJuan = galicia.nuevaCuenta(TipoDeCuenta.CAJA_DE_AHORRO, juan);

		assertTrue(cuentaJuan.extraccionDeDinero(5.0));
		assertTrue(cuentaJuan.extraccionDeDinero(5.0));
		assertTrue(cuentaJuan.extraccionDeDinero(5.0));
		assertTrue(cuentaJuan.extraccionDeDinero(5.0));
		assertTrue(cuentaJuan.extraccionDeDinero(5.0));

		// cobra el recargo de 6$
		assertFalse(cuentaJuan.extraccionDeDinero(5.0));
	}

	@Test
	public void queUnBancoPuedaCrearCuentaCorriente() {
		Banco galicia = new Banco("Galicia");
		Cliente jose = galicia.nuevoCliente(350000.0);
		final Integer CANTIDAD_DE_CUENTAS_ESPERADOS = 1;

		assertNotNull(galicia.nuevaCuenta(TipoDeCuenta.CUENTA_CORRIENTE, jose));
		assertEquals(CANTIDAD_DE_CUENTAS_ESPERADOS, galicia.getCantidadDeCuentas());
	}

	@Test
	public void queCuentaCorrienteCumplaElEjemploDelEnunciado() {
		Banco galicia = new Banco("Galicia");
		Cliente maria = galicia.nuevoCliente(100.0);
		Cuenta cuentaMaria = galicia.nuevaCuenta(TipoDeCuenta.CUENTA_CORRIENTE, maria);
		CuentaCorriente cuentaCorrienteMaria = (CuentaCorriente) cuentaMaria;
		cuentaCorrienteMaria.setgiroEnDescubiertoHabilitado(150.0);
		cuentaCorrienteMaria.extraccionDeDinero(200.0);

		final Double DEUDA_ESPERADA = 105.0;

		assertEquals(DEUDA_ESPERADA, cuentaCorrienteMaria.getTotalDeuda());
	}

	@Test
	public void queUnClientePuedaTenerMasDeUnaCuenta() {
		Banco galicia = new Banco("Galicia");
		Cliente maria = galicia.nuevoCliente(100.0);
		Cuenta primerCuenta = galicia.nuevaCuenta(TipoDeCuenta.CUENTA_CORRIENTE, maria);
		Cuenta segundaCuenta = galicia.nuevaCuenta(TipoDeCuenta.CUENTA_SUELDO, maria);
		Cuenta tercerCuenta = galicia.nuevaCuenta(TipoDeCuenta.CAJA_DE_AHORRO, maria);
		final Integer CANTIDAD_DE_CUENTAS_ESPERADAS = 3;

		assertEquals(CANTIDAD_DE_CUENTAS_ESPERADAS, maria.cantidadDeCuentasDelCliente());

	}

	@Test
	public void queLosClientesConSumatoriaMayorAUnMillonSeanVIP() {
		Banco galicia = new Banco("Galicia");
		Cliente german = galicia.nuevoCliente(1000000.1);

		assertTrue(german.saberSiElClienteEsVip());
	}

}
