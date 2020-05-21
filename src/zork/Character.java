package zork;

import java.util.Collection;

public abstract class Character {

	protected Inventario inventario;
	protected float salud;
	protected String nombre;
	protected Room habitacionActual;

	public boolean addItem(Item nuevoItem) {
		return inventario.addItem(nuevoItem);
	}

	public boolean removeItem(String nombreItem) {
		boolean bool = false;
		Item i = inventario.getItem(nombreItem);
		if (i != null) {
			inventario.removeItem(i);
			bool = true;
		}
		return bool;
	}
	
	public Item getItem(String item) {
	    return inventario.getItem(item);
	}

	public Collection<Item> getItems() {
		return inventario.getItems();
	}

	public void sumarSalud(float healthup) {
		if (!isDead()) {
			this.salud += healthup;
		}
	}

	public void restarSalud(float healthdown) {
		if (this.salud - healthdown <= 0 && isDead() == false) {
			this.salud = 0;
		} else {
			this.salud -= healthdown;
		}
	}

	public boolean isDead() {
		return this.salud == 0 ? true : false;
	}

	public Room getHabitacionActual() {
		return this.habitacionActual;
	}

	public void setHabitacionActual(Room habitacionActual) {
	    this.habitacionActual = habitacionActual;
	}

	public void aplicarItem(String item) {
	    switch (item) {
	    case "potion":
	        sumarSalud(20);
	        break;
	
	    case "poison":
	        restarSalud(15);
	        break;
	    }
	}

	public float getSalud() {
	    return this.salud;
	};
	
	

}
