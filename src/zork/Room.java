package zork;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import utilitarias.Cadena;

public class Room {// locations

    private String nombre;
    private char gender;
    private char number;
    private String description;
    private Hashtable<String, Sitio> sitios;// place
    private Hashtable<String, Salida> salidas;// connections
    private Hashtable<String, NPC> npcs;

    public Room(JsonElement json) {
	JsonObject jobj = json.getAsJsonObject();
	nombre = jobj.get("name").getAsString();
	gender = jobj.get("gender").getAsString().equals("male") ? 'm' : 'f';
	number = jobj.get("number").getAsString().equals("singular") ? 's' : 'p';
	description = jobj.get("description").getAsString();
	sitios = new Hashtable<String, Sitio>();
	salidas = new Hashtable<String, Salida>();
	npcs = new Hashtable<String, NPC>();
	sitios.put("suelo", new Sitio());
    }
    
    public void addSitio(Sitio newSitio) {
	this.sitios.put(newSitio.getNombre(), newSitio);
    }

    public void addSalida(Salida newSalida, String direccion) {
	this.salidas.put(direccion, newSalida);
	
    }

    public void addNPC(NPC newNPC) {
	newNPC.setHabitacionActual(this);
	this.npcs.put(newNPC.getName(), newNPC);
    }

    public boolean removeNPC(String nombre) {
	boolean rta = false;
	for (Iterator<Salida> it = salidas.values().iterator(); it.hasNext();) {
	    Salida salida = (Salida) it.next();
	    if (salida.removeNPC(nombre))
		break;
	}
	return npcs.remove(nombre) != null || rta;
    }

    public String getNombre() {
	return this.nombre;
    }

    public String getDescription() {
	return this.description;
    }

    public Salida getSalida(String nombreSalida) {
	return salidas.get(nombreSalida);
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

    
    public void addObstacle(NPC obstacle, String direction) {
	obstacle.setHabitacionActual(this);
	Salida salida = salidas.get(direction);
	if (salida != null)
	    salida.addNPC(obstacle);
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
