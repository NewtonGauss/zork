package zork;

import java.util.LinkedList;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Jugador extends Character {

    private int movimientos;
    private int score;
    private LinkedList<String> listaAplicables;

    public Jugador(JsonElement json) {
	JsonObject jobj = json.getAsJsonObject();
	this.nombre = jobj.get("character").getAsString();
	inventario = new Inventario();
	movimientos = 0;
	score = 0;
	salud = 100;
	listaAplicables = new LinkedList<String>();
	listaAplicables.add("pocion");
	listaAplicables.add("veneno");
    }

    public void sumarMovimiento() {
	this.movimientos++;
    }

    public String getName() {
	return this.nombre;
    }

    public float getSalud() {
	return this.salud;
    }

    public int getCantMovimientos() {
	return this.movimientos;
    }

    public int getScore() {
	return this.score;
    }

    public void setHabitacionActual(Room room) {
	this.habitacionActual = room;
    }

    
    
    public boolean isItemAplicable(String item) {
	return listaAplicables.contains(item);
    }
    
    
    public void aplicarItem(String item) {
	switch (item) {
	case "pocion":
	    sumarSalud(20);
	    break;

	case "veneno":
	    restarSalud(15);
	    break;
	}
    }
    
    public boolean mover(String direction) {
	Salida salida = habitacionActual.getSalida(direction);
	boolean seMovio = false;
	if (salida != null && salida.isEnemyDefeated()) {
	    setHabitacionActual(salida.getRoom());
	    seMovio = true;
	}
	return seMovio;
    }
}
