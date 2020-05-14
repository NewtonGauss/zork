package zork;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Jugador extends Character {
	
	private int movimientos;
	private int score;
	
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
	
	public int getCantMovimientos() {
		return this.movimientos;
	}
	
	public int getScore() {
		return this.score;
	}
}
