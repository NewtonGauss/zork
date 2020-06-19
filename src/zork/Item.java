package zork;

import java.util.List;

import zork.input.ItemInput;

public class Item extends Enumerable {

    private Double peso;
    private int puntos;
    private List<AccionItem> accionesValidas;
    private List<ObjetivoItem> objetivosValidos;
    private TipoItem tipo;
    private float saludSumar;

    public Item(ItemInput input) {
	super(input.getNombre(), input.getGender(), input.getNumber());
	peso = input.getPeso();
	puntos = input.getPuntos();
	accionesValidas = input.getAccionesValidas();
	objetivosValidos = input.getObjetivosValidos();
	tipo = input.getTipo();
	saludSumar = input.getSaludSumar();
    }

    public Double getPeso() {
	return this.peso;
    }

    public TipoItem getTipo() {
	return this.tipo;
    }

    public int getPoints() {
	return puntos;
    }

    public boolean esUsoValido(AccionItem uso) {
	return accionesValidas.contains(uso);
    }

    public boolean esObjetivoValido(ObjetivoItem objetivo) {
	return objetivosValidos.contains(objetivo);
    }

    public float getSaludSumar() {
	return saludSumar;
    }

}
