package zork;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.LinkedList;

import utilitarias.Cadena;
import zork.comandos.AtacarConComando;
import zork.comandos.Comando;
import zork.comandos.DarComando;
import zork.comandos.DefaultComando;
import zork.comandos.DiagnosticoComando;
import zork.comandos.HablarComando;
import zork.comandos.InfoComando;
import zork.comandos.InventarioComando;
import zork.comandos.IrComando;
import zork.comandos.MirarComando;
import zork.comandos.MovimientosComando;
import zork.comandos.PonerComando;
import zork.comandos.SoltarComando;
import zork.comandos.TomarComando;
import zork.comandos.UsarItemComando;

public class Narrador {
    private Jugador jugador;
    private Hashtable<String, Comando> comandos = new Hashtable<String, Comando>();
    private Collection<String> preposicionesArticulos = new LinkedList<String>();
    private ArrayList<Endgame> finales = new ArrayList<Endgame>();

    public Narrador(Jugador jugador) {
	this.jugador = jugador;
	cargarHashtable();
	cargarPreposiciones();
    }

    public String ejecutar(String comando) {
	String retorno = null;
	if (comando.contains("hablar") || comando.contains("atacar"))
	    comando = comando.replaceFirst(" con ", " % ");
	String[] cadenaPartida = comando.split(" ");
	Comando accion = parsearComando(cadenaPartida[0]);
	ArrayList<String> objetos = eliminarPreposicionesYArticulos(cadenaPartida);
	String restoComando = "";
	for(int i = 0; i < 2 && i < objetos.size(); i++) {
	    if(i == 1) {
		restoComando += ":";
	    }
	    restoComando += objetos.get(i);
	}
	for (Endgame endgame : finales) {
	    if(endgame.esFinal(accion, comando)) {
		retorno = endgame.ejecutar(jugador, accion, restoComando);
	    }
	}
	if(retorno == null)
	    retorno = accion.ejecutar(jugador, restoComando);
	jugador.sumarMovimiento();
	return retorno;
    }

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
	comandos.put("tomar", new TomarComando());
	comandos.put("agarrar", new TomarComando());
	comandos.put("soltar", new SoltarComando());
	comandos.put("tirar", new SoltarComando());
	comandos.put("puntuacion", new Puntuacion());
	comandos.put("poner", new PonerComando());
	comandos.put("movimientos", new MovimientosComando());
	comandos.put("mirar", new MirarComando());
	comandos.put("ir", new IrComando());
	comandos.put("caminar", new IrComando());
	comandos.put("info", new InfoComando());
	comandos.put("hablar", new HablarComando());
	comandos.put("diagnostico", new DiagnosticoComando());
	comandos.put("inventario", new InventarioComando());
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
	/* token */
	preposicionesArticulos.add("%");
    }
    
    public void addEndgame(Endgame endgame) {
	this.finales.add(endgame);
    }

}
