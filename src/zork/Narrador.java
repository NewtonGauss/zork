package zork;

import java.util.Hashtable;

public class Narrador {
    private Jugador jugador;
    private Hashtable<String, Comando> comandos = new Hashtable<String, Comando>();

    public Narrador(Jugador jugador) {
	this.jugador = jugador;
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
    }
    
    
}
