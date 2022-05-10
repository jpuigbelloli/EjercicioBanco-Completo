package ar.edu.unlam.pb2;

import java.util.LinkedList;
import java.util.List;

public class ListaVIP {

	static List<Cliente> clientesVIP = new LinkedList<>();

	public static void agregarClienteVIP(Cliente cliente) {

		clientesVIP.add(cliente);
	}

}
