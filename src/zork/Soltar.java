package zork;

import java.util.Iterator;

public class Soltar implements Comando {
	@Override
	public String ejecutar(Jugador jugador, String restoDelComando) {
		String retorno = "no tienes " + restoDelComando + " en tu inventario";
		boolean soltado=false;
		Item itemSoltado;
		Iterator<Item> iterator = jugador.getItems().iterator();
		Iterator<Sitio> iterator2;
		while (iterator.hasNext() && !soltado) {
			if (iterator.next().getNombre().equals(restoDelComando)) {
				itemSoltado=jugador.getItem(restoDelComando);
				jugador.removeItem(restoDelComando);
				retorno="soltaste "+restoDelComando;
				soltado=true;
				//dejo item en el primer sitio
				iterator2=jugador.getHabitacionActual().getSitios().iterator();
				iterator2.next().addItem(itemSoltado);
			}
		}
		return retorno;
	}
}
