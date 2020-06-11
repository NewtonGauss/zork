package zork.input.json;

import java.util.*;

import com.google.gson.*;

import zork.*;
import zork.input.ItemInput;

public class ItemInputJson implements ItemInput {
    private String nombre;
    private Double peso;
    private char gender;
    private char number;
    private int puntos;
    private List<AccionItem> accionesValidas = new ArrayList<AccionItem>();
    private List<ObjetivoItem> objetivosValidos = new ArrayList<ObjetivoItem>();
    private TipoItem tipo;
    private float saludSumar;

    public ItemInputJson(String jsonString) {
	JsonElement json = JsonParser.parseString(jsonString);
	JsonObject jobject = json.getAsJsonObject();
	nombre = jobject.get("name").getAsString();
	peso = jobject.get("weight").getAsDouble();
	puntos = jobject.get("points").getAsInt();
	tipo = TipoItem.stringToTipo(jobject.get("type").getAsString());
	gender = jobject.get("gender").getAsString().equals("male") ? 'm' : 'f';
	number = jobject.get("number").getAsString().equals("singular") ? 's' : 'p';
	for (JsonElement accion : jobject.getAsJsonArray("actions"))
	    accionesValidas.add(AccionItem.stringToAccionItem(accion.getAsString()));
	for (JsonElement effectOn : jobject.getAsJsonArray("effects_over"))
	    objetivosValidos.add(ObjetivoItem.stringTObjetivoItem(effectOn.getAsString()));
	
	/* Hardcodeado hasta que lo pongamos en el formato */
	if (tipo.equals(TipoItem.POCION))
	    saludSumar = 20f;
	else
	    saludSumar = 15f;
    }

    @Override
    public String getNombre() {
	return nombre;
    }

    @Override
    public Double getPeso() {
	return peso;
    }

    @Override
    public int getPuntos() {
	return puntos;
    }

    @Override
    public TipoItem getTipo() {
	return tipo;
    }

    @Override
    public char getGender() {
	return gender;
    }

    @Override
    public char getNumber() {
	return number;
    }

    @Override
    public List<AccionItem> getAccionesValidas() {
	return accionesValidas;
    }

    @Override
    public List<ObjetivoItem> getObjetivosValidos() {
	return objetivosValidos;
    }

    @Override
    public float getSaludSumar() {
	return saludSumar;
    }

}
