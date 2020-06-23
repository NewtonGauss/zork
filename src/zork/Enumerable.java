package zork;

public abstract class Enumerable {
    private String nombre;
    private char gender;
    private char number;
    
    public Enumerable(String nombre) {
	this.nombre = nombre;
    }
    
    public Enumerable(String nombre, char gender, char number) {
	super();
	this.nombre = nombre;
	this.gender = gender;
	this.number = number;
    }
    
    public String getNombre() {
	return nombre;
    }

    @Override
    public String toString() {
	String retorno = "";
	if (!Character.isUpperCase(nombre.charAt(0))) {
	    if (number == 's')
		retorno = gender == 'm' ? "el ": "la ";
	    else
		retorno = gender == 'm' ? "los ": "las ";
	}
	return retorno + nombre;
    }

    public String articuloIndefinido() {
	String retorno = "";
	if (number == 's')
	    retorno += gender == 'm' ? "un " : "una ";
	else
	    retorno += gender == 'm' ? "unos " : "unas ";
	return retorno + nombre;
    }
}
