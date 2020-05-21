package zork;

public class DefaultComando implements Comando {

    @Override
    public String ejecutar(Jugador jugador, String restoDelComando) {
	return "No puedo reconocer esa orden.";
    }

}
