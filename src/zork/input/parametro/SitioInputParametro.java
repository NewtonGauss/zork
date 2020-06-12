package zork.input.parametro;

import zork.input.SitioInput;

public class SitioInputParametro extends EnumerableInput implements SitioInput {
    public SitioInputParametro() {
	nombre = "suelo";
	gender = 'm';
	number = 's';
    }
    
    public SitioInputParametro(String nombre) {
	super(nombre);
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
