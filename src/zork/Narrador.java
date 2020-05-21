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
	if (comando.contains("hablar") || comando.contains("atacar"))
	    comando.replaceFirst("con", "");
	String[] cadenaPartida = comando.split(" ");
	Comando accion = parsearComando(cadenaPartida[0]);
	ArrayList<String> objetos = eliminarPreposicionesYArticulos(cadenaPartida);
	if (objetos.size() > 1)
	    return accion.ejecutar(jugador, objetos.get(1) + ':' + objetos.get(2));
	else if (objetos.size() == 1)
	    return accion.ejecutar(jugador, objetos.get(1));
	else
	    return accion.ejecutar(jugador, "");
    }
    
    private Comando parsearComando(String orden) {
	Comando com = comandos.get(orden);
	return com == null ? comandos.get("default") : com;
    }
    
    private ArrayList<String> eliminarPreposicionesYArticulos(String[] comando) {
	ArrayList<String> cadenaFiltrada = new ArrayList<String>();
	String objeto = "";
	for (String palabra : comando) {
	    if (!preposicionesArticulos.contains(palabra))
		objeto += palabra + " ";
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
	comandos.put("soltar", new SoltarComando());
	comandos.put("puntuacion", new Puntuacion());
	comandos.put("poner", new PonerComando());
	comandos.put("movimientos", new Movimiento());
	comandos.put("mirar", new MirarComando());
	comandos.put("ir", new IrComando());
	comandos.put("info", new InfoComando());
	comandos.put("hablar", new HablarComando());
	comandos.put("diagnostico", new Diagnostico());
	comandos.put("inventario", new ComandoInventario());
	comandos.put("atacar", new ComandoInventario());
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
}
