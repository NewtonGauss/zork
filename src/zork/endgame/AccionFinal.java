package zork.endgame;

import zork.Jugador;
import zork.comandos.Comando;
import zork.input.FinalJuegoInput;

public class AccionFinal extends FinalJuego {
    private ComandoCondicion accion;

    public AccionFinal(FinalJuegoInput input) {
	descripcion = input.getDescripcion();
	objetivo = input.getObjetivo();
	accion = input.getAccion();
    }

    @Override
    public boolean esComandoFinal(Comando comando, String restoComando) {
	return accion.equals(comando) && restoComando.contains(objetivo);
    }

    @Override
    public String ejecutar(Jugador jugador, Comando comando, String restoComando) {
	return comando.validar(jugador, restoComando) ? this.descripcion : null;
    }
}
