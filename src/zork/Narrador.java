package zork;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.LinkedList;

import utilitarias.Cadena;

public class Narrador {
    private Jugador jugador;
    private Hashtable<String, Comando> comandos = new Hashtable<String, Comando>();
    private Collection<String> preposicionesArticulos = new LinkedList<String>();

    public Narrador(Jugador jugador) {
	this.jugador = jugador;
	cargarHashtable();
	cargarPreposiciones();
    }

    
    public String ejecutar(String comando) {
  	String newComando = comando;
  	if (comando.contains("hablar") || comando.contains("atacar"))  	
  	    newComando = removeWord(comando, "con");
  	String[] cadenaPartida = newComando.split(" ");
  	Comando accion = parsearComando(cadenaPartida[0]);
  	ArrayList<String> objetos = eliminarPreposicionesYArticulos(cadenaPartida);
  	if (accion.getClass() == AtacarConComando.class) {
	    String[] objetosString = objetos.get(0).split(" ");
	    if (objetosString.length > 1) {
		String aux = accion.ejecutar(jugador, objetosString[0] + ':' + objetosString[1]);
		return aux;
	}
	    else if (objetosString.length == 1)
		return accion.ejecutar(jugador, objetosString[0]);
	    else
		return accion.ejecutar(jugador, "");
	} else {
	    if (objetos.size() > 1)
		return accion.ejecutar(jugador, objetos.get(0) + ':' + objetos.get(1));
	    else if (objetos.size() == 1)
		return accion.ejecutar(jugador, objetos.get(0));
	    else
		return accion.ejecutar(jugador, "");
	}
    }
//    public String ejecutar(String comando) {
//	if (comando.contains("hablar") || comando.contains("atacar"))
//	    comando.replaceFirst(" con ", " ");
//	String[] cadenaPartida = comando.split(" ");
//	Comando accion = parsearComando(cadenaPartida[0]);
//	ArrayList<String> objetos = eliminarPreposicionesYArticulos(cadenaPartida);
//	if (objetos.size() > 1)
//	    return accion.ejecutar(jugador, objetos.get(0) + ':' + objetos.get(1));
//	else if (objetos.size() == 1)
//	    return accion.ejecutar(jugador, objetos.get(0));
//	else
//	    return accion.ejecutar(jugador, "");
//    }
    
    private Comando parsearComando(String orden) {
	Comando com = comandos.get(orden);
	return com == null ? comandos.get("default") : com;
    }
    
    private ArrayList<String> eliminarPreposicionesYArticulos(String[] comando) {
	ArrayList<String> cadenaFiltrada = new ArrayList<String>();
	String objeto = "";
	for (int i = 1; i < comando.length; i++) {
	    if (!preposicionesArticulos.contains(comando[i]))
		objeto += comando[i] + " ";
	    else if (!objeto.equals("")) {
		cadenaFiltrada.add(Cadena.replaceLast(objeto, " ", ""));
		objeto = "";
	    }
	}
	if (!objeto.equals(""))
	    cadenaFiltrada.add(Cadena.replaceLast(objeto, " ", ""));
	return cadenaFiltrada;
    }

    private void cargarHashtable() {
	comandos.put("usar", new UsarItemComando());
	comandos.put("tomar", new Tomar());
	comandos.put("agarrar", new Tomar());
	comandos.put("soltar", new SoltarComando());
	comandos.put("tirar", new SoltarComando());
	comandos.put("puntuacion", new Puntuacion());
	comandos.put("poner", new PonerComando());
	comandos.put("movimientos", new Movimiento());
	comandos.put("mirar", new MirarComando());
	comandos.put("ir", new IrComando());
	comandos.put("caminar", new IrComando());
	comandos.put("info", new InfoComando());
	comandos.put("hablar", new HablarComando());
	comandos.put("diagnostico", new Diagnostico());
	comandos.put("inventario", new ComandoInventario());
	comandos.put("atacar", new AtacarConComando());
	comandos.put("dar", new DarComando());
	comandos.put("default", new DefaultComando());
    }
    
    private void cargarPreposiciones() {
	preposicionesArticulos.add("a");
	preposicionesArticulos.add("al");
	preposicionesArticulos.add("la");
	preposicionesArticulos.add("el");
	preposicionesArticulos.add("los");
	preposicionesArticulos.add("las");
	preposicionesArticulos.add("en");
	preposicionesArticulos.add("hacia");
    }
    
    private static String removeWord(String string, String word) {	
	if (string.contains(word)) {
	    String tempWord = word + " ";
	    string = string.replaceAll(tempWord, "");
	    tempWord = " " + word;
	    string = string.replaceAll(tempWord, "");
	}
	return string;
    }
}

