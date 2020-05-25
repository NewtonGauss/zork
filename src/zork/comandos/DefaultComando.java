package zork.comandos;

import zork.Jugador;

public class DefaultComando implements Comando {

    @Override
    public String ejecutar(Jugador jugador, String restoDelComando) {
	return "No puedo reconocer esa orden.";
    }

    @Override
    public boolean validar(Jugador jugador, String restoDelComando) {
	// TODO Auto-generated method stub
	return false;
    }

}
