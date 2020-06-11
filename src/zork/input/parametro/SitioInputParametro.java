package zork.input.parametro;

import zork.input.SitioInput;

public class SitioInputParametro implements SitioInput {
    private String nombre;
    private char gender, number;

    public SitioInputParametro() {
	nombre = "suelo";
	gender = 'm';
	number = 's';
    }
    
    public SitioInputParametro(String nombre) {
	this.nombre = nombre;
	this.gender = nombre.endsWith("a") || nombre.endsWith("as") ? 'f' : 'm';
	this.number = nombre.endsWith("s") ? 'p' : 's';
    }

    public SitioInputParametro(String nombre, char gender, char number) {
	this.nombre = nombre;
	this.gender = gender;
	this.number = number;
    }

    @Override
    public String getNombre() {
	return nombre;
    }

    @Override
    public char getGender() {
	return gender;
    }

    @Override
    public char getNumber() {
	return number;
    }

}
