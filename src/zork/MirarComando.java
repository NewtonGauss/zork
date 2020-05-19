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
	    mensajeSalida = habitacionActual.getDescription() + "."
		    + enumerarSitios(habitacionActual) + enumerarNPCs(habitacionActual);
	} else if ((objetivoDescribir = habitacionActual.getNPC(objetivo)) != null)
	    mensajeSalida = objetivoDescribir.getDescripcion();
	else
	    mensajeSalida = objetivo + " no existe";
	return mensajeSalida;
    }

    private String enumerarNPCs(Room habitacionActual) {
	String mensajeSalida = "";
	int cantNPCs = 0;
	Iterator<NPC> npcs = habitacionActual.getNpcs().iterator();
	if (npcs.hasNext()) {
	    mensajeSalida = " Hay";
	    for (; npcs.hasNext(); cantNPCs++) {
		NPC npc = npcs.next();
		mensajeSalida += ' ' + npc.articuloIndefinido() + ',';
	    }
	    mensajeSalida = normalizarFinalOracion(mensajeSalida, cantNPCs);
	}
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
	    mensajeSalida = " En " + sitioActual.toString() + " hay";
	    int cantItems;
	    for (cantItems = 0; items.hasNext(); cantItems++) {
		Item itemActual = items.next();
		mensajeSalida += ' ' + itemActual.articuloIndefinido() + ',';
	    }
	    mensajeSalida = normalizarFinalOracion(mensajeSalida, cantItems);
	}
	return mensajeSalida;
    }

    private String normalizarFinalOracion(String mensajeSalida, int cantObjetivos) {
	mensajeSalida = replaceLast(mensajeSalida, ",", ".");
	if (cantObjetivos > 1)
	    mensajeSalida = replaceLast(mensajeSalida, ",", " y");
	return mensajeSalida;
    }

    private static String replaceLast(String text, String regex, String replacement) {
	return text.replaceFirst("(?s)" + regex + "(?!.*?" + regex + ")", replacement);
    }

}
