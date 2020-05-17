package zork;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ActionEndGame implements Endgame{
	
	private String descripcion;
	private String objetivo;
	private String accion;

	
	public ActionEndGame(JsonElement json) {
		JsonObject jobj = json.getAsJsonObject();
		descripcion = jobj.get("description").getAsString();
		objetivo = jobj.get("thing").getAsString();
		accion = jobj.get("action").getAsString();
		
	}
	
	@Override
	public boolean esFinal(String comando) {
		boolean rta = false;
		if(accion == comando)
			rta = true;
		return rta;
	}

	@Override
	public String getDescripcion() {
		return this.descripcion;
	}

}
