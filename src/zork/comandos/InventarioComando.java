package zork.comandos;

import java.util.Iterator;

import utilitarias.Cadena;
import zork.*;

public class InventarioComando implements Comando {

    @Override
    public String ejecutar(Jugador jugador, String restoDelComando) {
	String listaInventario = "No tienes objetos en tu inventario.";
	Iterator<Item> iterator = jugador.getItems().iterator();
	if (iterator.hasNext()) {
	    listaInventario = "Tienes";
	    int cantItems;
	    for (cantItems = 0; iterator.hasNext(); cantItems++) {
		Item itemActual = iterator.next();
		listaInventario += ' ' + itemActual.articuloIndefinido() + ',';
	    }
	    listaInventario = Cadena.normalizarFinalOracion(listaInventario, cantItems);
	}

	return listaInventario;
    }

    @Override
    public boolean validar(Jugador jugador, String restoDelComando) {
	// TODO Auto-generated method stub
	return false;
    }

}
