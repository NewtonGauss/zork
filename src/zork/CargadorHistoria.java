package zork;

import java.io.*;
import java.util.*;

import com.google.gson.*;

import zork.input.json.*;

public class CargadorHistoria {
    private JsonObject settings;
    private JsonArray habitaciones, npcs, items;
    private Map<String, Item> itemMap;
    private Map<String, NPC> npcsMap;
    private Map<String, Habitacion> habitacionesMap;

    public CargadorHistoria(String path) throws IOException {
	byte[] texto = leerArchivo(path);
	JsonObject jobj = JsonParser.parseString(new String(texto)).getAsJsonObject();
	settings = jobj.get("settings").getAsJsonObject();
	habitaciones = jobj.get("locations").getAsJsonArray();
	npcs = jobj.get("npcs").getAsJsonArray();
	items = jobj.get("items").getAsJsonArray();
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
	habitacionesMap = new HashMap<String, Habitacion>();

	JsonObject primerHabitacion = habitaciones.get(0).getAsJsonObject();
	String nombreHabitacionInicial = primerHabitacion.get("name").getAsString();
	for (JsonElement habitacionJson : habitaciones)
	    cargarHabitacion(habitacionJson);

	jugador.setHabitacionActual(habitacionesMap.get(nombreHabitacionInicial));
	conectarHabitaciones();
    }

    private void cargarItems() {
	itemMap = new HashMap<String, Item>();
	for (JsonElement itemJson : items) {
	    Item item = new Item(new ItemInputJson(itemJson.toString()));
	    itemMap.put(item.getNombre(), item);
	}
    }

    private void cargarNPCs() {
	npcsMap = new HashMap<String, NPC>();
	for (JsonElement npcJson : npcs) {
	    NPC npc = new NPC(new NPCInputJson(npcJson.toString()));
	    npcsMap.put(npc.getNombre(), npc);
	}
    }

    private void cargarHabitacion(JsonElement habitacionJson) {
	Habitacion habitacion = new Habitacion(
		new HabitacionInputJson(habitacionJson.toString()));
	ponerSitioEnHabitacion(habitacion, habitacionJson.getAsJsonObject());
	ponerNPCsEnHabitacion(habitacion, habitacionJson.getAsJsonObject());
	habitacionesMap.put(habitacion.getNombre(), habitacion);
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
		sitio.addItem(itemMap.get(itemJson.getAsString()));
	    }
	}
    }

    private void ponerNPCsEnHabitacion(Habitacion habitacion, JsonObject habitacionJson) {
	JsonElement npcsElement = habitacionJson.get("npcs");
	if (npcsElement != null) {
	    JsonArray npcsArray = npcsElement.getAsJsonArray();
	    for (JsonElement npcJson : npcsArray) {
		habitacion.addNPC(npcsMap.get(npcJson.getAsString()));
	    }
	}
    }

    private void conectarHabitaciones() {
	for (JsonElement habitacionElement : habitaciones) {
	    JsonObject habitacionObject = habitacionElement.getAsJsonObject();
	    JsonArray conexiones = habitacionObject.get("connections").getAsJsonArray();
	    String nombreHab = habitacionObject.get("name").getAsString();
	    Habitacion habitacionOrigen = habitacionesMap.get(nombreHab);
	    addConexiones(conexiones, habitacionOrigen);
	}
    }

    private void addConexiones(JsonArray conexiones, Habitacion habitacionOrigen) {
	for (JsonElement conexion : conexiones) {
	    JsonObject conexionJson = conexion.getAsJsonObject();
	    Habitacion habitacionSalida = habitacionesMap
		    .get(conexionJson.get("location").getAsString());
	    Salida salida = new Salida(habitacionSalida);
	    Direccion direccion = Direccion
		    .stringToDireccion(conexionJson.get("direction").getAsString());
	    habitacionOrigen.addSalida(salida, direccion);

	    JsonElement obstaculoJson = conexionJson.get("obstacles");
	    if (obstaculoJson != null) {
		NPC obstaculo = npcsMap.get(obstaculoJson.getAsString());
		habitacionOrigen.addObstaculo(obstaculo, direccion);
	    }
	}
    }

}
