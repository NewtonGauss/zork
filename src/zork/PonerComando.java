package zork;

import java.util.Iterator;

public class PonerComando implements Comando {

    /**
     * Ejecuta un comando de Poner un {@link Item} en un {@link Sitio}.
     * Devuelve el mensaje que debera aparecer en pantalla.
     * 
     * @param restoDelComando es una cadena con la sintaxis nombreItem:nombreSitio
     * @return mensaje de salida por pantalla.
     */
    @Override
    public String ejecutar(Jugador jugador, String restoDelComando) {

	String retorno = "";
	String[] cadenaCortada = restoDelComando.split(":");
	Item item;
	String objeto = cadenaCortada[0], sitioObjetivo = cadenaCortada[1];
	
	if ((item = jugador.getItem(objeto)) != null) {
	    Room habitacionActual = jugador.getHabitacionActual();
	    Sitio sitio = habitacionActual.getSitio(sitioObjetivo);
	    
	    if (sitio != null) {
		retorno = item.toString() + " ahora se encuentra en " + sitio.toString();
		sitio.addItem(item);
		jugador.removeItem(objeto);
	    } else {
		retorno = sitioObjetivo + " no es un sitio disponible.\nLos sitios disponibles son:\n";
		for (Iterator<Sitio> s = habitacionActual.getSitios().iterator(); s.hasNext();) {
		    Sitio sitioExistente = s.next();
		    retorno += sitioExistente.getNombre() + "\n";
		}
	    }
	} else {
	    retorno = "No se encuentra " + objeto + " en el inventario";
	}

	return retorno;
    }

}
