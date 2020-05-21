package utilitarias;


/**
 * Clase utilitaria que se encarga de ponerle articulos
 * definidos o indefinidos a los nombres, ya sea de npcs,
 * items, habitaciones, etcetera.
 *
 * Tambien posee metodos de manejo de cadenas que no estan
 * en la biblioteca estandar.
 * 
 */
public abstract class Cadena {

    public static String articuloDefinido(String nombre, char gender, char number) {
	String retorno;
	if (number == 's')
	    retorno = gender == 'm' ? "el ": "la ";
	else
	    retorno = gender == 'm' ? "los ": "las ";
	return retorno + nombre;
    }
    
    public static String articuloIndefinido(String nombre, char gender, char number) {
	String retorno = "";
	if (number == 's')
	    retorno += gender == 'm' ? "un " : "una ";
	else
	    retorno += gender == 'm' ? "unos " : "unas ";
	return retorno + nombre;
    }
    
    public static String replaceLast(String text, String regex, String replacement) {
	return text.replaceFirst("(?s)" + regex + "(?!.*?" + regex + ")", replacement);
    }
    
    /**
     * Cambia "a el" por "al". 
     * @param mensaje
     * @return mensaje sin a el.
     */
    public static String replaceAEl(String mensaje) {
	return mensaje.replaceFirst("a el", "al");
    }
}
