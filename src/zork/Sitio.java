package zork;

import java.util.*;

import zork.input.SitioInput;


public class Sitio extends Enumerable{

    private Hashtable<String, Item> items;
    
    /*
     * Sitio por defecto de cada habitacion: suelo
     */
    public Sitio() {
	super("suelo", 'm', 's');
	items = new Hashtable<String, Item>();
    }

    public Sitio(SitioInput input) {
	super(input.getNombre(), input.getGender(), input.getNumber());
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
}
