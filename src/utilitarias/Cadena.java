package utilitarias;

/**
 * Clase utilitaria que se encarga de ponerle articulos definidos o indefinidos
 * a los nombres, ya sea de npcs, items, habitaciones, etcetera.
 *
 * Tambien posee metodos de manejo de cadenas que no estan en la biblioteca
 * estandar.
 * 
 */
public abstract class Cadena {
    public static String replaceLast(String text, String regex, String replacement) {
	return text.replaceFirst("(?s)" + regex + "(?!.*?" + regex + ")", replacement);
    }

    /**
     * Cambia "a el" por "al".
     */
    public static String replaceAEl(String mensaje) {
	return mensaje.replaceFirst("a el", "al");
    }

    public static String normalizarFinalOracion(String mensajeSalida, int cantObjetivos) {
	mensajeSalida = replaceLast(mensajeSalida, ",", ".");
	if (cantObjetivos > 1)
	    mensajeSalida = replaceLast(mensajeSalida, ",", " y");
	return mensajeSalida;
    }
}
