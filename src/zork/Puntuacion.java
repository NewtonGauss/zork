package zork;

public class Puntuacion implements Comando {
    @Override
    public String ejecutar(Jugador jugador, String restoDelComando) {
	return "Tu puntuacion es:" + Integer.toString(jugador.getScore());

    }
}
