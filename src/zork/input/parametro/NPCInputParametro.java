package zork.input.parametro;

import java.util.List;

import zork.input.*;

public class NPCInputParametro extends EnumerableInput implements NPCInput {
    private String descripcion, charla;
    private float salud;
    private boolean enemigo;
    private List<TriggerInput> listaTriggers;
    
    public NPCInputParametro(String nombre) {
	super(nombre);
	salud = 100;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCharla(String charla) {
        this.charla = charla;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setNumber(char number) {
        this.number = number;
    }

    public void setSalud(float salud) {
        this.salud = salud;
    }

    public void setEnemigo(boolean enemigo) {
        this.enemigo = enemigo;
    }

    public void setListaTriggers(List<TriggerInput> listaTriggers) {
        this.listaTriggers = listaTriggers;
    }
    
    @Override
    public String getNombre() {
	return nombre;
    }

    @Override
    public String getDescripcion() {
	return descripcion;
    }

    @Override
    public String getCharla() {
	return charla;
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
    public float getSalud() {
	return salud;
    }

    @Override
    public boolean isEnemigo() {
	return enemigo;
    }

    @Override
    public List<TriggerInput> getListaTrigger() {
	return listaTriggers;
    }

    @Override
    public String getSpritePath() {
	
	return null;
    }

}
