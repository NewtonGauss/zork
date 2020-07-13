package zork.comandos;

import zork.*;
import zork.endgame.ComandoCondicion;

public class TomarComando implements Comando {
    @Override
    public String ejecutar(Jugador jugador, String nombreItem) {
	String retorno = "No hay ningun " + nombreItem + " por aqui.";
	if (nombreItem.equals(""))
	    retorno = "Debe elegir un objeto a tomar.";
	Item item = null;
	for(Item iterador : jugador.getHabitacionActual().getItems()) {
	    if(iterador.getNombre().equals(nombreItem)) {
		item = iterador;
		break;
	    }
	}
	
	if (item != null) {
	    if (item.esUsoValido(AccionItem.TAKE) && jugador.ponerItem(item)) {
		//Para eliminar el item de
		jugador.getHabitacionActual().getItem(nombreItem);

		retorno = "Tomaste " + item.toString() + '.';
	    }
	    else if (!item.esUsoValido(AccionItem.TAKE))
		retorno = "No puedes tomar " + item.toString() + '.';
	    else
		retorno = "No tienes mas espacio en tu inventario!";
	}
	return retorno;
    }

    @Override
    public boolean validar(Jugador jugador, String restoDelComando) {
	Habitacion habitacionActual = jugador.getHabitacionActual();
	Item item = habitacionActual.getItem(restoDelComando);
	return item != null;
    }

    @Override
    public ComandoCondicion getTipo() {
	return ComandoCondicion.TOMAR;
    }
}
