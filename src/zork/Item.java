package zork;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Item
{

	private String nombre;
	private Double peso;
	private char gender;
	private char number;
	private int points;
	private List<String> accionesValidas = new ArrayList<String>();
	private List<String> afecta = new ArrayList<String>();

	/*
	 * Crea el objeto desde un elemento JSON.
	 */
	public Item(JsonElement json) {
		JsonObject jobject = json.getAsJsonObject();
		nombre = jobject.get("name").getAsString();
		peso = jobject.get("weight").getAsDouble();
		points = jobject.get("points").getAsInt();
		gender = jobject.get("gender").getAsString().equals("male") ? 'm' : 'f';
		number = jobject.get("number").getAsString().equals("singular") ? 's' : 'p';
		for (JsonElement accion : jobject.getAsJsonArray("actions"))
			accionesValidas.add(accion.getAsString());
		for (JsonElement effectOn : jobject.getAsJsonArray("effects_over"))
			afecta.add(effectOn.getAsString());
	}

	public Double getPeso() { return this.peso; }

	public String getNombre() { return this.nombre; }

	@Override
	public String toString() {
		String fraseItem = "";
		if (number == 's')
			fraseItem += gender == 'm' ? "el " : "la ";
		else
			fraseItem += gender == 'm' ? "los " : "las ";
		return fraseItem + nombre;
	}

	public boolean esUsoValido(String uso) {

		return accionesValidas.contains(uso);
	}

	public boolean esObjetivoValido(String objetivo) {
		return afecta.contains(objetivo);
	}

	public int getPoints() {
	    return points;
	}
}
