package zork;
import java.util.Hashtable;
import java.util.Iterator;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Room {//locations

	private String nombre;
	private char gender;
	private char number;
	private String description;
	private Hashtable<String, Sitio> sitios;//place
	private Hashtable<String, Salida> salidas;//connections
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
	}
	
	public void addSitio(Sitio newSitio) {
		this.sitios.put(newSitio.getNombre(), newSitio);
	}
	
	public void addSalida(Salida newSalida, String direccion) {
		this.salidas.put(direccion, newSalida);
	}
	
	public void addSalida(Salida newSalida) {
		this.salidas.put(newSalida.getNombre(), newSalida);
	}
	
	public void addNPC(NPC newNPC) {
		this.npcs.put(newNPC.getName(), newNPC);
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
	
	public Hashtable<String, Salida> getSalidasTable() {
		return this.salidas;
	}
	
	public Iterator<Salida> getSalidas() {
		return this.salidas.values().iterator();
	}
	
	public Iterator<Sitio> getSitios() {
		return this.sitios.values().iterator();
	}
	
	@Override
	public String toString() {
		String retorno;
		if(number == 's') 
			retorno = gender=='m'? "el " + this.nombre : "la " + this.nombre;
		
		else
			retorno = gender=='m'? "los " + this.nombre : "las " + this.nombre;
		
		return retorno;

	}
	

}
