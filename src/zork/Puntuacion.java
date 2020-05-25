package zork;

import zork.comandos.Comando;

public class Puntuacion implements Comando {
    @Override
    public String ejecutar(Jugador jugador, String restoDelComando) {
	return "Tu puntuacion es: " + jugador.getScore();

    }

    @Override
    public boolean validar(Jugador jugador, String restoDelComando) {
	// TODO Auto-generated method stub
	return false;
    }
}
