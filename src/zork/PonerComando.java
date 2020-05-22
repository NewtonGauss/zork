package zork;

import java.util.Iterator;

import utilitarias.Cadena;

public class PonerComando implements Comando {

    /**
     * Ejecuta un comando de Poner un {@link Item} en un {@link Sitio}. Devuelve el
     * mensaje que debera aparecer en pantalla.
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
		retorno = "No hay un " + sitioObjetivo + " en "
			+ habitacionActual.toString() + ". Puede dejar " + item.toString();
		int cantSitios = 0;
		for (Iterator<Sitio> s = habitacionActual.getSitios().iterator(); s
			.hasNext(); cantSitios++) {
		    Sitio sitioExistente = s.next();
		    retorno += " en " + sitioExistente.toString() + ",";
		}
		retorno = Cadena.normalizarFinalOracion(retorno, cantSitios);
		retorno = Cadena.replaceLast(retorno, " y ", " o ");
	    }
	} else {
	    retorno = "No se encuentra " + objeto + " en el inventario";
	}

	return retorno;
    }

    @Override
    public boolean validar(Jugador jugador, String restoDelComando) {
	// TODO Auto-generated method stub
	return false;
    }

}
