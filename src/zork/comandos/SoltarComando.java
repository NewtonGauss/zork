
package zork.comandos;

import zork.*;

public class SoltarComando implements Comando {

    /**
     * Suelta el item en el suelo (sitio por defecto de las habitaciones)
     * 
     * @param nombreItem cadena con el nombre exacto del item.
     * @return mensaje de salida por pantalla.
     */
    @Override
    public String ejecutar(Jugador jugador, String nombreItem) {
	String retorno = "No tienes " + nombreItem + " en tu inventario.";
	Item itemSoltado = jugador.getItem(nombreItem);
	if (itemSoltado != null && itemSoltado.esUsoValido(AccionItem.DROP)) {
	    jugador.getHabitacionActual().getSitio("suelo").addItem(itemSoltado);
	    jugador.sacarItem(nombreItem);
	    retorno = "Se solto " + itemSoltado.toString() + " en el suelo.";
	} else if (itemSoltado != null)
	    retorno = "No puedes soltar " + itemSoltado.toString() + '.';
	return retorno;
    }

    @Override
    public boolean validar(Jugador jugador, String restoDelComando) {
	// TODO Auto-generated method stub
	return false;
    }

}
