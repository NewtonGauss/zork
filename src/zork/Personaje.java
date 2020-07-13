package zork;

import java.util.*;

public abstract class Personaje extends Enumerable {

    protected Inventario inventario;
    protected float salud;
    protected Habitacion habitacionActual;
    
    public Personaje(String nombre) {
	super(nombre);
    }

    public Personaje(String nombre, char gender, char number) {
	super(nombre, gender, number);
    }

    public boolean ponerItem(Item nuevoItem) {
	return inventario.ponerItem(nuevoItem);
    }
    
    public boolean sacarItem(String nombreItem) {
	boolean bool = false;
	Item i = inventario.getItem(nombreItem);
	if (i != null) {
	    inventario.sacarItem(i);
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

    public void sumarSalud(float saludSumar) {
	if (!estaMuerto()) {
	    this.salud += saludSumar;
	}
    }

    public void restarSalud(float saludRestar) {
	if (this.salud - saludRestar <= 0 || estaMuerto()) {
	    matar();
	} else {
	    this.salud -= saludRestar;
	}
    }

    public boolean estaMuerto() {
	return this.salud == 0 ? true : false;
    }

    public Habitacion getHabitacionActual() {
	return this.habitacionActual;
    }

    public void setHabitacionActual(Habitacion habitacionActual) {
	this.habitacionActual = habitacionActual;
    }

    public void aplicarItem(Item item) {
	switch (item.getTipo()) {
	case POCION:
	    sumarSalud(item.getSaludSumar());
	    break;
	case ARMA:
	case VENENO:
	    restarSalud(item.getSaludSumar());
	    break;
	default:
	    break;
	}
    }

    public float getSalud() {
	return this.salud;
    }

    public void matar() {
	this.salud = 0;
	if (habitacionActual != null) {
	    Iterator<Item> items = inventario.getItems().iterator();
	    Sitio suelo = habitacionActual.getSitio("suelo");
	    while (items.hasNext()) {
		Item item = items.next();
		suelo.addItem(item);
	    }
	}
    };

}
