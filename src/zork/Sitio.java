package zork;

import java.util.Collection;
import java.util.Hashtable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import utilitarias.Cadena;

public class Sitio {

    private String nombre;
    private char gender;
    private char number;
    private Hashtable<String, Item> items;
    
    /*
     * Sitio por defecto de cada habitacion: suelo
     */
    public Sitio() {
	nombre = "suelo";
	gender = 'm';
	number = 's';
	items = new Hashtable<String, Item>();
    }

    public Sitio(JsonElement json) {
	JsonObject jobj = json.getAsJsonObject();
	nombre = jobj.get("name").getAsString();
	gender = jobj.get("gender").getAsString().equals("male") ? 'm' : 'f';
	number = jobj.get("number").getAsString().equals("singular") ? 's' : 'p';
	items = new Hashtable<String, Item>();
    }

    public void addItem(Item nuevoItem) {
	items.put(nuevoItem.getNombre(), nuevoItem);
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
	return Cadena.articuloDefinido(nombre, gender, number);
    }

    public String getNombre() {
	return nombre;
    }

}
