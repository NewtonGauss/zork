package zork;

import java.util.*;

import zork.input.HabitacionInput;

public class Habitacion extends Enumerable {

    private String descripcion;
    private Hashtable<String, Sitio> sitios;
    private Hashtable<Direccion, Salida> salidas;
    private Hashtable<String, NPC> npcs;
    private String spritePath;

    public Habitacion(HabitacionInput input) {
	super(input.getNombre(), input.getGender(), input.getNumber());
	descripcion = input.getDescripcion();
	sitios = new Hashtable<String, Sitio>();
	salidas = new Hashtable<Direccion, Salida>();
	npcs = new Hashtable<String, NPC>();
	sitios.put("suelo", new Sitio());
	spritePath = input.getSpritePath();
    }

    public void addSitio(Sitio nuevoSitio) {
	this.sitios.put(nuevoSitio.getNombre(), nuevoSitio);
    }

    public void addSalida(Salida nuevaSalida, Direccion direccion) {
	this.salidas.put(direccion, nuevaSalida);

    }

    public void addNPC(NPC nuevoNpc) {
	nuevoNpc.setHabitacionActual(this);
	this.npcs.put(nuevoNpc.getNombre(), nuevoNpc);
    }

    public boolean sacarNPC(String nombre) {
	for (Salida salida : salidas.values()) {
	    if (salida.sacarNPC(nombre))
		break;
	}
	return npcs.remove(nombre) != null;
    }

    public String getDescripcion() {
	return this.descripcion;
    }
    
    public String getSpritePath() {
	return spritePath;
    }

    public Salida getSalida(Direccion direccion) {
	return salidas.get(direccion);
    }
    
//    public void addItem(Item item) {
//	this.sitios.get(0).addItem(item);
//    }
    
    public Direccion getSalida(String nombreSalida) {
	Direccion[] direcciones = Direccion.values();
	Direccion dirSalida = null;
	for (Direccion dir: direcciones) {
	    Salida salida = salidas.get(dir);
	    if (salida != null && salida.getNombre().equals(nombreSalida)) {
		dirSalida = dir;
		break;
	    }
	}
	return dirSalida;
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

    public void addObstaculo(NPC obstaculo, Direccion direction) {
	addNPC(obstaculo);
	Salida salida = salidas.get(direction);
	if (salida != null)
	    salida.addNPC(obstaculo);
    }

    public Collection<NPC> getNpcs() {
	return npcs.values();
    }
    
    public Item getItem(String nombreItem) {
	Item item = null;
	Iterator<Sitio> iterator = sitios.values().iterator();
	while (iterator.hasNext()) {
	    Sitio sitio = iterator.next();
	    item = sitio.getItem(nombreItem);
	    if (item != null)
		break;
	}
	return item;
    }
    
    public Collection<Item> getItems() {
	List<Item> items = new ArrayList<Item>();
	Iterator<Sitio> iterator = sitios.values().iterator();
	while (iterator.hasNext()) {
	    Sitio sitio = iterator.next();
	    items.addAll(sitio.getItems());
	}
	return items;
    }
    
    public Collection<Salida> getSalidas() {
	return salidas.values();
    }

}
