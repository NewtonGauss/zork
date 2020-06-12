package zork;

import java.util.*;

import zork.input.HabitacionInput;

public class Habitacion extends Enumerable {

    private String descripcion;
    private Hashtable<String, Sitio> sitios;
    private Hashtable<String, Salida> salidas;
    private Hashtable<String, NPC> npcs;

    public Habitacion(HabitacionInput input) {
	nombre = input.getNombre();
	gender = input.getGender();
	number = input.getNumber();
	descripcion = input.getDescripcion();
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
	for (Salida salida : salidas.values()) {
	    if (salida.sacarNPC(nombre))
		break;
	}
	return npcs.remove(nombre) != null;
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

}
