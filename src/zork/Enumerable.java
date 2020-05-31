package zork;

public abstract class Enumerable {
    protected String nombre;
    protected char gender;
    protected char number;
    
    @Override
    public String toString() {
	String retorno;
	if (number == 's')
	    retorno = gender == 'm' ? "el ": "la ";
	else
	    retorno = gender == 'm' ? "los ": "las ";
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
