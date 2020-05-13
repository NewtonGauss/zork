import java.util.ArrayList;
import java.util.Hashtable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Room {

	String nombre;
	char gender;
	char number;
	String description;
	Hashtable<String, Sitio> sitios;
	Hashtable<String, Salida> salidas;

	public Room(JsonElement json) {
		JsonObject jobj = json.getAsJsonObject();
		nombre = jobj.get("name").getAsString();
		gender = jobj.get("gender").getAsString().equals("male") ? 'm' : 'f';
		number = jobj.get("number").getAsString().equals("singular") ? 's' : 'p';
		description = jobj.get("description").getAsString();
		Hashtable<String, Sitio> sitios = new Hashtable<String, Sitio>();
		Hashtable<String, Salida> salidas = new Hashtable<String, Salida>();
	}
	
	public void addSitio(Sitio otro) {
		this.sitios.put(otro.getNombre(), otro);
	}
	
	public void addSalida(Salida newSalida) {
		this.salidas.put(newSalida.getNombre(), newSalida);
	}
	
	public char getGender() {
		return this.gender;
	}
	
	public char getNumber() {
		return this.number;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public Hashtable<String, Salida> getSalidas() {
		return this.salidas;
	}
	
	public Hashtable<String, Sitio> getSitios() {
		return this.sitios;
	}
	
	@Override
	public String toString() {
		String retorno;
		if(number == 's') 
			retorno = gender=='m'? "El " + this.nombre : "La " + this.nombre;
		
		else
			retorno = gender=='m'? "Los " + this.nombre : "Las " + this.nombre;
		
		return retorno;

	}
}
