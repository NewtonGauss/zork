package zork;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import utilitarias.Cadena;

public class Habitacion {

    private String nombre;
    private char gender;
    private char number;
    private String descripcion;
    private Hashtable<String, Sitio> sitios;
    private Hashtable<String, Salida> salidas;
    private Hashtable<String, NPC> npcs;

    public Habitacion(JsonElement json) {
	JsonObject jobj = json.getAsJsonObject();
	nombre = jobj.get("name").getAsString();
	gender = jobj.get("gender").getAsString().equals("male") ? 'm' : 'f';
	number = jobj.get("number").getAsString().equals("singular") ? 's' : 'p';
	descripcion = jobj.get("description").getAsString();
	sitios = new Hashtable<String, Sitio>();
	salidas = new Hashtable<String, Salida>();
	npcs = new Hashtable<String, NPC>();
	sitios.put("suelo", new Sitio());
    }

    public void addSitio(Sitio nuevoSitio) {
	this.sitios.put(nuevoSitio.getNombre(), nuevoSitio);
    }

    public void addSalida(Salida nuevaSalida, String direccion) {
	this.salidas.put(direccion, nuevaSalida);

    }

    public void addNPC(NPC nuevoNpc) {
	nuevoNpc.setHabitacionActual(this);
	this.npcs.put(nuevoNpc.getNombre(), nuevoNpc);
    }

    public boolean sacarNPC(String nombre) {
	boolean rta = false;
	for (Iterator<Salida> it = salidas.values().iterator(); it.hasNext();) {
	    Salida salida = it.next();
	    if (salida.sacarNPC(nombre)) {
		rta = true;
		break;
	    }
	}
	return npcs.remove(nombre) != null || rta;
    }

    public String getNombre() {
	return this.nombre;
    }

    public String getDescripcion() {
	return this.descripcion;
    }

    public Salida getSalida(String direccion) {
	return salidas.get(direccion);
    }

    public Sitio getSitio(String nombreSitio) {
	return sitios.get(nombreSitio);
    }

    public Collection<Sitio> getSitios() {
	return this.sitios.values();
    }

    public NPC getNPC(String nombre) {
	return npcs.get(nombre);
    }

    public void ponerObstaculo(NPC obstaculo, String direction) {
	addNPC(obstaculo);
	Salida salida = salidas.get(direction);
	if (salida != null)
	    salida.addNPC(obstaculo);
    }

    public Collection<NPC> getNpcs() {
	return npcs.values();
    }

    @Override
    public String toString() {
	return Cadena.articuloDefinido(nombre, gender, number);
    }

    public String articuloIndefinido() {
	return Cadena.articuloIndefinido(nombre, gender, number);
    }

}
