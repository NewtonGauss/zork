package zork;
import java.util.Hashtable;
import java.util.Iterator;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Room {

	private String nombre;
	private char gender;
	private char number;
	private String description;
	private Hashtable<String, Sitio> sitios;
	private Hashtable<String, Salida> salidas;

	public Room(JsonElement json) {
		JsonObject jobj = json.getAsJsonObject();
		nombre = jobj.get("name").getAsString();
		gender = jobj.get("gender").getAsString().equals("male") ? 'm' : 'f';
		number = jobj.get("number").getAsString().equals("singular") ? 's' : 'p';
		description = jobj.get("description").getAsString();
		sitios = new Hashtable<String, Sitio>();
		salidas = new Hashtable<String, Salida>();
	}
	
	public void addSitio(Sitio otro) {
		this.sitios.put(otro.getNombre(), otro);
	}
	
	public void addSalida(Salida newSalida) {
		this.salidas.put(newSalida.getNombre(), newSalida);
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
