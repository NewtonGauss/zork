package zork;

import java.util.Iterator;

public class Tomar implements Comando {
    @Override
    public String ejecutar(Jugador jugador, String restoDelComando) {
	String retorno = "No hay ningun " + restoDelComando + " por aqui";
	Item itemTomado;
	Iterator<Sitio> iterator = jugador.getHabitacionActual().getSitios().iterator();
	while (iterator.hasNext()) {
	    if ((itemTomado = iterator.next().getItem(restoDelComando)) != null) {
		if (jugador.addItem(itemTomado))
		    retorno = "Tomaste " + itemTomado.toString();
		else
		    retorno = "No tienes mas espacio en tu inventario!";
	    }
	}
	return retorno;
    }
}
