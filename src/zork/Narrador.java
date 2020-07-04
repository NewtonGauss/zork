package zork;

import java.util.*;

import utilitarias.Cadena;
import zork.comandos.*;
import zork.endgame.FinalJuego;

public class Narrador {
    private Jugador jugador;
    private Hashtable<String, Comando> comandos = new Hashtable<String, Comando>();
    private Collection<String> preposicionesArticulos = new LinkedList<String>();
    private ArrayList<FinalJuego> finales = new ArrayList<FinalJuego>();

    public Narrador(Jugador jugador) {
	this.jugador = jugador;
	cargarComandos();
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
	for (FinalJuego endgame : finales) {
	    if(endgame.esComandoFinal(accion, comando)) {
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

    private void cargarComandos() {
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
	comandos.put("ayuda", new AyudaComando());
	comandos.put("default", new DefaultComando());
	comandos.put("guardar", new GuardarComando());
    }

    private void cargarPreposiciones() {
	preposicionesArticulos.add("a");
	preposicionesArticulos.add("al");
	preposicionesArticulos.add("la");
	preposicionesArticulos.add("el");
	preposicionesArticulos.add("del");
	preposicionesArticulos.add("los");
	preposicionesArticulos.add("las");
	preposicionesArticulos.add("en");
	preposicionesArticulos.add("hacia");
	/* token */
	preposicionesArticulos.add("%");
    }
    
    public void addEndgame(FinalJuego endgame) {
	this.finales.add(endgame);
    }
    
    public void addEndgames(List<FinalJuego> endgames) {
	finales.addAll(endgames);
    }

}
