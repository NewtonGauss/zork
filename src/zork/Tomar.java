package zork;

import java.util.Iterator;

public class Tomar implements Comando {
	@Override
	public String ejecutar(Jugador jugador, String restoDelComando) {
		String retorno = "no hay ningun "+restoDelComando+" por aqui";
		Item itemTomado;
		Iterator<Sitio> iterator = jugador.getHabitacionActual().getSitios().iterator();
		while (iterator.hasNext()) {
			if ((itemTomado = iterator.next().getItem(restoDelComando)) != null) {
				if (jugador.addItem(itemTomado)) {
					retorno = restoDelComando + " tomad";
					if (itemTomado.getGender() == 'm')
						retorno += "o";
					else
						retorno += "a";
					if (itemTomado.getNumber() == 'p')
						retorno += "s";

				} else
					retorno = "no tienes mas espacio en tu inventario!";
			}
		}
		return retorno;
	}
}
