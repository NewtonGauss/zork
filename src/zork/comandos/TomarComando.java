package zork.comandos;

import java.util.Iterator;

import zork.AccionItem;
import zork.Item;
import zork.Jugador;
import zork.Sitio;

public class TomarComando implements Comando {
    @Override
    public String ejecutar(Jugador jugador, String nombreItem) {
	String retorno = "No hay ningun " + nombreItem + " por aqui.";
	if(nombreItem.equals(""))
	    retorno = "Debe elegir un objeto a tomar.";
	Item itemTomado;
	Iterator<Sitio> iterator = jugador.getHabitacionActual().getSitios().iterator();
	while (iterator.hasNext()) {
	    if ((itemTomado = iterator.next().getItem(nombreItem)) != null) {
		if (itemTomado.esUsoValido(AccionItem.TAKE) && jugador.ponerItem(itemTomado))
		    retorno = "Tomaste " + itemTomado.toString() + '.';
		else if(!itemTomado.esUsoValido(AccionItem.TAKE))
		    retorno = "No puedes tomar " + itemTomado.toString() + '.';
		else
		    retorno = "No tienes mas espacio en tu inventario!";
	    }
	}
	return retorno;
    }

    @Override
    public boolean validar(Jugador jugador, String restoDelComando) {
	// TODO Auto-generated method stub
	return false;
    }
}
