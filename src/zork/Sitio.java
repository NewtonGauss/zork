package zork;
import java.util.ArrayList;
import java.util.Hashtable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Sitio {
	
	String nombre;
	char gender;
	char number;
	Hashtable<String, Item> items;
	
	public Sitio(JsonElement json) {
		JsonObject jobj = json.getAsJsonObject();
		nombre = jobj.get("name").getAsString();
		gender = jobj.get("gender").getAsString().equals("male") ? 'm' : 'f';
		number = jobj.get("number").getAsString().equals("singular") ? 's' : 'p';
		}

	public void addItem(Item newItem) {
		items.put(newItem.getNombre(), newItem);
	}
	public String getNombre() {
		return this.nombre;
	}


	public char getGender() {
		return this.gender;
	}


	public char getNumber() {
		return this.number;
	}

	
	public Hashtable<String, Item> getItems() {
		return items;
	}
	
	
}
