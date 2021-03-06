package zork.input.parametro;

import java.util.*;

import zork.*;
import zork.input.ItemInput;

public class ItemInputParametro extends EnumerableInput implements ItemInput {
    private Double peso;
    private int puntos;
    private List<AccionItem> accionesValidas = new ArrayList<AccionItem>();
    private List<ObjetivoItem> objetivosValidos = new ArrayList<ObjetivoItem>();
    private TipoItem tipo;
    private float saludSumar;

    public ItemInputParametro(String nombre) {
	super(nombre);
	peso = 10d;
	puntos = 100;
    }

    public void setPeso(Double peso) {
	this.peso = peso;
    }

    public void setPuntos(int puntos) {
	this.puntos = puntos;
    }

    public void setAccionesValidas(List<AccionItem> accionesValidas) {
	this.accionesValidas = accionesValidas;
    }

    public void setObjetivosValidos(List<ObjetivoItem> afecta) {
	this.objetivosValidos = afecta;
    }

    public void setTipo(TipoItem tipo) {
	this.tipo = tipo;
    }

    public void setSaludSumar(float saludSumar) {
	this.saludSumar = saludSumar;
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

    @Override
    public String getSpritePath() {
	
	return null;
    }

}
