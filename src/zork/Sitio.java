package zork;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Sitio {

    private String nombre;
    private char gender;
    private char number;
    private Hashtable<String, Item> items;

    public Sitio(JsonElement json) {
	JsonObject jobj = json.getAsJsonObject();
	nombre = jobj.get("name").getAsString();
	gender = jobj.get("gender").getAsString().equals("male") ? 'm' : 'f';
	number = jobj.get("number").getAsString().equals("singular") ? 's' : 'p';
	items = new Hashtable<String, Item>();
    }

    public void addItem(Item newItem) {
	items.put(newItem.getNombre(), newItem);
    }

    /* 
     * getItem remueve el item de la tabla. Para ver los 
     * items conviene usar getItem*s* que devuelve la coleccion
     * entera. Solo se va a querer tomar un solo item del sitio 
     * para sacarlo.
     */
    public Item getItem(String nombreItem) {
	return items.remove(nombreItem);
    }

    public Collection<Item> getItems() {
	return items.values();
    }
    
    @Override
    public String toString() {
	String fraseSitio = "";
	if (number == 's')
	    fraseSitio += gender == 'm' ? "el " : "la ";
	else
	    fraseSitio += gender == 'm' ? "los " : "las ";
	return fraseSitio + getNombre();
    }

    public String getNombre() {
	return nombre;
    }

}
