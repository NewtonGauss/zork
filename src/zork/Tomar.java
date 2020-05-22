package zork;

import java.util.Iterator;

public class Tomar implements Comando {
    @Override
    public String ejecutar(Jugador jugador, String nombreItem) {
	String retorno = "No hay ningun " + nombreItem + " por aqui";
	if(nombreItem.equals(""))
	    retorno = "Debe elegir un objeto a tomar.";
	Item itemTomado;
	Iterator<Sitio> iterator = jugador.getHabitacionActual().getSitios().iterator();
	while (iterator.hasNext()) {
	    if ((itemTomado = iterator.next().getItem(nombreItem)) != null) {
		if (jugador.addItem(itemTomado))
		    retorno = "Tomaste " + itemTomado.toString() + '.';
		else
		    retorno = "No tienes mas espacio en tu inventario!";
	    }
	}
	return retorno;
    }
}
