package zork;

import java.util.Hashtable;

public class Mapa {

	Room actual;
	Hashtable<String, Room> habitaciones = new Hashtable<String, Room>();
	
	public Room getHabitacionActual() {
		return this.actual;
	}
	
	public boolean moverPlayer(String direction) {
		Hashtable<String, Salida> connections = actual.getSalidasTable();
		Salida nueva = connections.get(direction);
		boolean rta = false;
		if(nueva.isEnemyDefeated()) {
			actual = nueva.getRoom();
			rta = true;
		}	
		return rta;
	}
	
	
}
