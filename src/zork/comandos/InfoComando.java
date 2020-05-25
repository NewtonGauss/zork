package zork.comandos;

import zork.Jugador;

public class InfoComando implements Comando {

    /**
     * Otorga informacion de la aventura.
     * Devuelve la cadena enviada.
     * El metodo está implementado para mantener el mismo 
     * criterio que con los otros comandos.
     * 
     * @param info la info de los settings del json.
     * @return mensaje de salida por pantalla.
     */
    @Override
    public String ejecutar(Jugador jugador, String info) {
	return info;
    }

    @Override
    public boolean validar(Jugador jugador, String restoDelComando) {
	// TODO Auto-generated method stub
	return false;
    }

}