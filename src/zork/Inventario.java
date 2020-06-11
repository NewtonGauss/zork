package zork;

import java.util.*;

public class Inventario {

	private Double pesoMax;
	private Double pesoActual = 0.0;
	private Hashtable<String, Item> inventario = new Hashtable<>();

	public Inventario() {
		this.pesoMax = 100d;
	}

	public Inventario(Double pesoMax) {
		this.pesoMax = pesoMax;
	}

	public boolean ponerItem(Item nuevoItem) {
		boolean bool = false;
		if (nuevoItem.getPeso() + this.pesoActual <= this.pesoMax) {
			this.pesoActual += nuevoItem.getPeso();
			inventario.put(nuevoItem.getNombre(), nuevoItem);
			bool = true;
		}
		return bool;
	}

	public Item sacarItem(Item sacar) {
		inventario.remove(sacar.getNombre());
		pesoActual -= sacar.getPeso();
		return sacar;
	}

	public Item getItem(String nombre) {
		return inventario.get(nombre);
	}

	public Collection<Item> getItems() {
		return inventario.values();
	}

	public Double getPesoActual() {
		return this.pesoActual;
	}
}
