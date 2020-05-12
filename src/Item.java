import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Item
{

	private String nombre;
	private Double peso;
	private char gender;
	private char number;
	private List<String> accionesValidas = new ArrayList<String>();
	private List<String> afecta = new ArrayList<String>();
	Hashtable<String, Double> info = new Hashtable<String, Double>() {
		{
			put("Espada Larga", 25.3);
			put("Manzana", 2.0);
			put("Huevo Kinder", 66.6);
		}
	};

	public Item(String nombre) {
		this.nombre = nombre;
		this.peso = info.get(nombre);
	}

	/*
	 * Crea el objeto desde un elemento JSON.
	 */
	public Item(JsonElement json) {
		JsonObject jobject = json.getAsJsonObject();
		nombre = jobject.get("name").getAsString();
		peso = 10d; // TODO poner el peso en el .zork
		gender = jobject.get("gender").getAsString() == "male" ? 'm' : 'f';
		number = jobject.get("number").getAsString() == "singular" ? 's' : 'p';
		for (JsonElement accion : jobject.getAsJsonArray("actions"))
			accionesValidas.add(accion.getAsString());
		for (JsonElement effectOn : jobject.getAsJsonArray("effects_over"))
			afecta.add(effectOn.getAsString());
	}

	public Double getPeso() { return this.peso; }

	public String getNombre() { return this.nombre; }
}
