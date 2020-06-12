package zork.input.parametro;

import zork.input.HabitacionInput;

public class HabitacionInputParametro extends EnumerableInput implements HabitacionInput {
    private String descripcion;
    
    public HabitacionInputParametro(String nombre) {
	super(nombre);
	descripcion = "Estas en una habitacion";
    }
    
    public HabitacionInputParametro(String nombre, String descr) {
	super(nombre);
	descripcion = descr;
    }

    @Override
    public String getNombre() {
	return nombre;
    }

    @Override
    public String getDescripcion() {
	return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
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
