package zork;

import java.util.Iterator;

public class ComandoInventario implements Comando{

	@Override
	public String ejecutar(Jugador jugador, String restoDelComando) {
		String listaInventario = "No tienes objetos en tu inventario\n";
		Iterator<Item> iterator = jugador.getItems().iterator();
		if (iterator.hasNext())
		    listaInventario = "";
		while(iterator.hasNext()) {
			listaInventario += iterator.next().getNombre() + '\n';
		}
		return listaInventario;
	}

}
