package zork;

public class Puntuacion implements Comando{
@Override
public String ejecutar(Jugador jugador) {
	String puntuacion="Tu puntuacion es:"+Integer.toString(jugador.getScore());
	return puntuacion;
}
}
