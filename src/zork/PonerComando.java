package zork;

import java.util.Iterator;

public class PonerComando implements Comando{

	/*
	 * PARA QUE FUNCIONE EN EL PARAMETRO RESTODELCOMANDO SE TIENE QUE RECIBIR 
	 * "nombreItem:nombreSitio"
	 * 
	 * */
	
	@Override
	public String ejecutar(Jugador jugador, String restoDelComando) {
		
		String retorno = "";
		String[] aux = restoDelComando.split(":");
		
		if(jugador.getItem(aux[0]) != null) {
			if(jugador.getHabitacionActual().getSitio(aux[1]) != null) {
				retorno = "El item " + aux[0] + " ahora se encuentra en" ;
				if(jugador.getHabitacionActual().getSitio(aux[1]).getGender() == 'm') {
					retorno += " el " + aux[1] ;
				}else {
					retorno += " la " + aux[1] ;
				}
				Item item = jugador.getItem(aux[0]);
				jugador.getHabitacionActual().getSitio(aux[1]).addItem(item);
				jugador.removeItem(aux[0]);
			}else {
				retorno = aux[1] + " no es un sitio disponible. \n Los sitios disponibles son: " ;
				for(Iterator<Sitio> s = jugador.getHabitacionActual().getSitios().iterator(); s.hasNext();) {
					Sitio sitio = s.next();
					retorno += sitio.getNombre() + "\t" ;
				}
			}
		}else {
			retorno = "El item " + aux[0] + " no se encontro en el inventario" ;
		}
		
		return retorno;
	}

}
