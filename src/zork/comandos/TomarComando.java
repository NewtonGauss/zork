package zork.comandos;

import zork.*;
import zork.endgame.ComandoCondicion;

public class TomarComando implements Comando {
    @Override
    public String ejecutar(Jugador jugador, String nombreItem) {
	String retorno = "No hay ningun " + nombreItem + " por aqui.";
	if (nombreItem.equals(""))
	    retorno = "Debe elegir un objeto a tomar.";
	Item itemTomado = jugador.getHabitacionActual().getItem(nombreItem);
	if (itemTomado != null) {
	    if (itemTomado.esUsoValido(AccionItem.TAKE) && jugador.ponerItem(itemTomado))
		retorno = "Tomaste " + itemTomado.toString() + '.';
	    else if (!itemTomado.esUsoValido(AccionItem.TAKE))
		retorno = "No puedes tomar " + itemTomado.toString() + '.';
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
