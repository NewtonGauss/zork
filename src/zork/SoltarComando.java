package zork;

public class SoltarComando implements Comando {

    @Override
    public String ejecutar(Jugador jugador, String nombreItem) {
	String retorno = "No tienes " + nombreItem + " en tu inventario.";
	Item itemSoltado = jugador.getItem(nombreItem);
	if (itemSoltado != null) {
	    jugador.getHabitacionActual().getSitio("suelo").addItem(itemSoltado);
	    jugador.removeItem(nombreItem);
	    retorno = "Se solto " + itemSoltado.toString() + " en el suelo.";
	}
	return retorno;
    }

}
