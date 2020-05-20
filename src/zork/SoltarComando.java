package zork;

public class SoltarComando implements Comando {

    @Override
    public String ejecutar(Jugador jugador, String restoDelComando) {
	String retorno = "";
	Item item = jugador.getItem(restoDelComando);
	if(item != null) {
	    jugador.habitacionActual.getSitio("suelo").addItem(item);
	    jugador.removeItem(item.getNombre());
	    retorno = "Se solto el item " + item.getNombre() + " en el suelo";
	}
	else
	    retorno = "Usted no posee ese item en el inventario.";
	return retorno;
    }

}
