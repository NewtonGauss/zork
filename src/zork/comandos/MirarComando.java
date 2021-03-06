package zork.comandos;

import java.util.Iterator;

import utilitarias.Cadena;
import zork.*;
import zork.endgame.ComandoCondicion;

public class MirarComando implements Comando {

    /**
     * Ejecuta el comando de mirar. Devuelve el mensaje que debera aparecer en
     * pantalla
     * 
     * @param objetivo si es una cadena vacia, dice el nombre de la habitacion o
     *                 dice 'alrededor' se muestra una detallada descripcion del
     *                 lugar. Si el objetivo es el nombre de un npc, se mostrara su
     *                 descripcion.
     * @return mensaje de salida por pantalla.
     */
    @Override
    public String ejecutar(Jugador jugador, String objetivo) {
	String mensajeSalida = "";
	Habitacion habitacionActual = jugador.getHabitacionActual();
	NPC objetivoDescribir;

	if (objetivo.equals("") || objetivo.equals("alrededor")
		|| objetivo.equals(habitacionActual.getNombre())) {
	    mensajeSalida = habitacionActual.getDescripcion() + "."
		    + enumerarSitios(habitacionActual) + enumerarNPCs(habitacionActual)
		    + enumerarSalidas(habitacionActual);
	} else if ((objetivoDescribir = habitacionActual.getNPC(objetivo)) != null)
	    mensajeSalida = objetivoDescribir.getDescripcion();
	else
	    mensajeSalida = objetivo + " no existe";
	return mensajeSalida;
    }

    private String enumerarSalidas(Habitacion habitacionActual) {
	String mensajeSalida = "";
	Direccion[] direcciones = new Direccion[] { Direccion.NORTE, Direccion.SUR,
		Direccion.ESTE, Direccion.OESTE };
	Salida salida;
	for (Direccion direccion : direcciones) {
	    if ((salida = habitacionActual.getSalida(direccion)) != null)
		mensajeSalida += " Al " + direccion.toString().toLowerCase() + " hay "
			+ salida.getHabitacion().articuloIndefinido() + ".";
	}
	if ((salida = habitacionActual.getSalida(Direccion.ARRIBA)) != null)
	    mensajeSalida += " Arriba hay " + salida.getHabitacion().articuloIndefinido()
		    + ".";
	if ((salida = habitacionActual.getSalida(Direccion.ABAJO)) != null)
	    mensajeSalida += " Abajo hay " + salida.getHabitacion().articuloIndefinido()
		    + ".";
	return mensajeSalida;
    }

    private String enumerarNPCs(Habitacion habitacionActual) {
	String mensajeSalida = "";
	int cantNPCs = 0;
	Iterator<NPC> npcs = habitacionActual.getNpcs().iterator();
	if (npcs.hasNext()) {
	    mensajeSalida = " Hay";
	    for (; npcs.hasNext(); cantNPCs++) {
		NPC npc = npcs.next();
		mensajeSalida += ' ' + npc.articuloIndefinido() + ',';
	    }
	    mensajeSalida = Cadena.normalizarFinalOracion(mensajeSalida, cantNPCs);
	}
	return mensajeSalida;
    }

    private String enumerarSitios(Habitacion habitacionActual) {
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
	    mensajeSalida = Cadena.normalizarFinalOracion(mensajeSalida, cantItems);
	}
	return mensajeSalida;
    }

    @Override
    public boolean validar(Jugador jugador, String restoDelComando) {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public ComandoCondicion getTipo() {
	return ComandoCondicion.MIRAR;
    }

}
