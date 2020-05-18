package zork;

import java.util.Iterator;

public class ComandoInventario implements Comando{

	@Override
	public String ejecutar(Jugador jugador, String restoDelComando) {
		String listaInventario = "";
		Iterator<Item> iterator = jugador.getItems().iterator();
		while(iterator.hasNext()) {
			listaInventario += iterator.next().getNombre() + '\n';
		}
		return listaInventario;
	}

}
