package zork;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Jugador extends Character {
	
	private int movimientos;
	private int score;
	private Room habitacionActual;
	
	public Jugador(JsonElement json) {
		JsonObject jobj = json.getAsJsonObject();
		this.nombre = jobj.get("character").getAsString();
		inventario = new Inventario();
		movimientos = 0;
		score = 0;
		salud = 100;
		
		
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
	
	public void setHabitacionActual(Room room)
	{
		this.habitacionActual=room;
	}
	
	public Room getHabitacionActual()
	{
		return this.habitacionActual;
	}
	//vamos a cambiar la forma de mostrar la habitacion actula pasandole esa responsabilidad al jugador
	public boolean mover(String direction)
	{	
		try {
			Room raux=habitacionActual.getSalidasTable().get(direction).getRoom();
			if(habitacionActual.getSalidasTable().get(direction).isEnemyDefeated())
				{habitacionActual=raux;
					return true;
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	//tambien le pasamos la responsabilidad de moverse al jugador y se la quitamos a mapa, mapa es una clase que esta quedando sin responsabilidad, deberiamos quitarla
}
