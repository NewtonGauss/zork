package zork;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import utilitarias.Cadena;

public class Item {

    private String nombre;
    private Double peso;
    private char gender;
    private char number;
    private int puntos;
    private List<String> accionesValidas = new ArrayList<String>();
    private List<String> afecta = new ArrayList<String>();
    private TipoItem tipo;
    private float saludSumar;


    public Item(JsonElement json) {
	JsonObject jobject = json.getAsJsonObject();
	nombre = jobject.get("name").getAsString();
	peso = jobject.get("weight").getAsDouble();
	puntos = jobject.get("points").getAsInt();
	setTipo(jobject.get("type").getAsString());
	gender = jobject.get("gender").getAsString().equals("male") ? 'm' : 'f';
	number = jobject.get("number").getAsString().equals("singular") ? 's' : 'p';
	for (JsonElement accion : jobject.getAsJsonArray("actions"))
	    accionesValidas.add(accion.getAsString());
	for (JsonElement effectOn : jobject.getAsJsonArray("effects_over"))
	    afecta.add(effectOn.getAsString());
    }

    private void setTipo(String tipo) {
	switch (tipo) {
	case "potion":
	    this.tipo = TipoItem.POCION;
	    /* hardcodeado, hay que ponerlo en el formato */
	    saludSumar = 20f; 
	    break;
	case "poison":
	    this.tipo = TipoItem.VENENO;
	    saludSumar = 15f;
	    break;
	case "weapon":
	    this.tipo = TipoItem.ARMA;
	    saludSumar = 15f;
	    break;
	case "vanilla":
	default:
	    this.tipo = TipoItem.VANILLA;
	    break;
	}
    }

    public Double getPeso() {
	return this.peso;
    }

    public String getNombre() {
	return this.nombre;
    }

    public TipoItem getTipo() {
	return this.tipo;
    }

    public int getPoints() {
	return puntos;
    }

    public boolean esUsoValido(String uso) {

	return accionesValidas.contains(uso);
    }

    public boolean esObjetivoValido(String objetivo) {
	return afecta.contains(objetivo);
    }

    @Override
    public String toString() {
	return Cadena.articuloDefinido(nombre, gender, number);
    }
    
    public String articuloIndefinido() {
	return Cadena.articuloIndefinido(nombre, gender, number);
    }

    public float getSaludSumar() {
	return saludSumar;
    }

}
