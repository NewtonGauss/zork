package zork;

import java.io.*;
import java.util.*;

import com.google.gson.*;

import zork.endgame.*;
import zork.input.json.*;

public class CargadorHistoria {
    private JsonObject settings;
    private JsonArray habitacionesJsonArray, npcsJsonArray, itemsJsonArray,
	    finalesJsonArray;
    private Map<String, Item> items; 
    private Map<String, NPC> npcs;
    private Map<String, Habitacion> habitaciones;

    public CargadorHistoria(String path) throws IOException {
	byte[] texto = leerArchivo(path);
	JsonObject jobj = JsonParser.parseString(new String(texto)).getAsJsonObject();
	settings = jobj.get("settings").getAsJsonObject();
	habitacionesJsonArray = jobj.get("locations").getAsJsonArray();
	npcsJsonArray = jobj.get("npcs").getAsJsonArray();
	itemsJsonArray = jobj.get("items").getAsJsonArray();
	finalesJsonArray = jobj.get("endgames").getAsJsonArray();
    }

    private byte[] leerArchivo(String path) throws FileNotFoundException, IOException {
	File archivo = new File(path);
	FileInputStream fis = new FileInputStream(archivo);
	byte[] texto = new byte[(int) archivo.length()];
	fis.read(texto);
	fis.close();
	return texto;
    }

    public Jugador cargarHistoria() {
	Jugador jugador = new Jugador(settings.get("character").getAsString());
	cargarHabitaciones(jugador);
	return jugador;
    }

    private void cargarHabitaciones(Jugador jugador) {
	cargarItems();
	cargarNPCs();
	habitaciones = new HashMap<String, Habitacion>();

	JsonObject primerHabitacion = habitacionesJsonArray.get(0).getAsJsonObject();
	String nombreHabitacionInicial = primerHabitacion.get("name").getAsString();
	for (JsonElement habitacionJson : habitacionesJsonArray)
	    cargarHabitacion(habitacionJson);

	jugador.setHabitacionActual(habitaciones.get(nombreHabitacionInicial));
	conectarHabitaciones();
    }

    private void cargarItems() {
	items = new HashMap<String, Item>();
	for (JsonElement itemJson : itemsJsonArray) {
	    Item item = new Item(new ItemInputJson(itemJson.toString()));
	    items.put(item.getNombre(), item);
	}
    }

    private void cargarNPCs() {
	npcs = new HashMap<String, NPC>();
	for (JsonElement npcJson : npcsJsonArray) {
	    NPC npc = new NPC(new NPCInputJson(npcJson.toString()));
	    npcs.put(npc.getNombre(), npc);
	}
    }

    private void cargarHabitacion(JsonElement habitacionJson) {
	Habitacion habitacion = new Habitacion(
		new HabitacionInputJson(habitacionJson.toString()));
	ponerSitioEnHabitacion(habitacion, habitacionJson.getAsJsonObject());
	ponerNPCsEnHabitacion(habitacion, habitacionJson.getAsJsonObject());
	habitaciones.put(habitacion.getNombre(), habitacion);
    }

    private void ponerSitioEnHabitacion(Habitacion habitacion,
	    JsonObject habitacionJson) {
	JsonElement sitiosElement = habitacionJson.get("places");
	if (sitiosElement != null) {
	    JsonArray sitios = sitiosElement.getAsJsonArray();
	    for (JsonElement sitioJson : sitios) {
		Sitio sitio = new Sitio(new SitioInputJson(sitioJson.toString()));
		ponerItemsEnSitio(sitio, sitioJson.getAsJsonObject());
		habitacion.addSitio(sitio);
	    }
	}
    }

    private void ponerItemsEnSitio(Sitio sitio, JsonObject sitioJson) {
	JsonElement itemsElement = sitioJson.get("items");
	if (itemsElement != null) {
	    JsonArray itemsArray = itemsElement.getAsJsonArray();
	    for (JsonElement itemJson : itemsArray) {
		sitio.addItem(items.get(itemJson.getAsString()));
	    }
	}
    }

    private void ponerNPCsEnHabitacion(Habitacion habitacion, JsonObject habitacionJson) {
	JsonElement npcsElement = habitacionJson.get("npcs");
	if (npcsElement != null) {
	    JsonArray npcsArray = npcsElement.getAsJsonArray();
	    for (JsonElement npcJson : npcsArray) {
		habitacion.addNPC(npcs.get(npcJson.getAsString()));
	    }
	}
    }

    private void conectarHabitaciones() {
	for (JsonElement habitacionElement : habitacionesJsonArray) {
	    JsonObject habitacionObject = habitacionElement.getAsJsonObject();
	    JsonArray conexiones = habitacionObject.get("connections").getAsJsonArray();
	    String nombreHab = habitacionObject.get("name").getAsString();
	    Habitacion habitacionOrigen = habitaciones.get(nombreHab);
	    addConexiones(conexiones, habitacionOrigen);
	}
    }

    private void addConexiones(JsonArray conexiones, Habitacion habitacionOrigen) {
	for (JsonElement conexion : conexiones) {
	    JsonObject conexionJson = conexion.getAsJsonObject();
	    Habitacion habitacionSalida = habitaciones
		    .get(conexionJson.get("location").getAsString());
	    Salida salida = new Salida(habitacionSalida);
	    Direccion direccion = Direccion
		    .stringToDireccion(conexionJson.get("direction").getAsString());
	    habitacionOrigen.addSalida(salida, direccion);

	    JsonElement obstaculoJson = conexionJson.get("obstacles");
	    if (obstaculoJson != null) {
		NPC obstaculo = npcs.get(obstaculoJson.getAsString());
		habitacionOrigen.addObstaculo(obstaculo, direccion);
	    }
	}
    }

    public List<FinalJuego> cargarFinales() {
	List<FinalJuego> finales = new LinkedList<FinalJuego>();
	for (JsonElement finalJsonElement : finalesJsonArray) {
	    JsonObject finalJson = finalJsonElement.getAsJsonObject();
	    FinalJuego finalJuego;
	    if (finalJson.get("condition").getAsString().equals("location"))
		finalJuego = new HabitacionFinal(
			new FinalJuegoInputJson(finalJsonElement.toString()));
	    else
		finalJuego = new AccionFinal(
			new FinalJuegoInputJson(finalJsonElement.toString()));
	    finales.add(finalJuego);
	}
	return finales;
    }

    public String getBienvenida() {
	return settings.get("welcome").getAsString();
    }
}
