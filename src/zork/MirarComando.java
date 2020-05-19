package zork;

import java.util.Iterator;

public class MirarComando implements Comando {

    @Override
    public String ejecutar(Jugador jugador, String objetivo) {
	String mensajeSalida = "";
	Room habitacionActual = jugador.getHabitacionActual();
	NPC objetivoDescribir;

	if (objetivo.equals("") || objetivo.equals("alrededor")
		|| objetivo.equals(habitacionActual.getNombre())) {
	    mensajeSalida = habitacionActual.getDescription() + ". "
		    + enumerarSitios(habitacionActual);
	} else if ((objetivoDescribir = habitacionActual.getNPC(objetivo)) != null)
	    mensajeSalida = objetivoDescribir.getDescripcion();
	else
	    mensajeSalida = objetivo + " no existe";
	return mensajeSalida;
    }

    private String enumerarSitios(Room habitacionActual) {
	String mensajeSalida = "";
	for (Iterator<Sitio> sitios = habitacionActual.getSitios().iterator(); sitios
		.hasNext();) {
	    Sitio sitioActual = sitios.next();
	    mensajeSalida += enumerarItems(sitioActual);
	}
	return mensajeSalida;
    }

    private String enumerarItems(Sitio sitioActual) {
	String mensajeSalida = "";
	Iterator<Item> items = sitioActual.getItems().iterator();
	if (items.hasNext()) {
	    mensajeSalida = "En " + sitioActual.toString() + " hay";
	    int cantItems;
	    for (cantItems = 0; items.hasNext(); cantItems++) {
		Item itemActual = items.next();
		mensajeSalida += escribirItem(itemActual);
	    }
	    mensajeSalida = replaceLast(mensajeSalida, ",", ".");
	    if (cantItems > 1)
		mensajeSalida = replaceLast(mensajeSalida, ",", " y");
	}
	return mensajeSalida;
    }

    private String escribirItem(Item itemActual) {
	String mensajeSalida = "";
	if (itemActual.getGender() == 'm')
	    mensajeSalida += " un ";
	else
	    mensajeSalida += " una ";
	mensajeSalida += itemActual.getNombre() + ",";
	return mensajeSalida;
    }

    private static String replaceLast(String text, String regex, String replacement) {
	return text.replaceFirst("(?s)" + regex + "(?!.*?" + regex + ")", replacement);
    }

}
