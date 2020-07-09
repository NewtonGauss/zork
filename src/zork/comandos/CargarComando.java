package zork.comandos;

import java.io.*;

import zork.*;
import zork.endgame.ComandoCondicion;

public class CargarComando implements Comando {

    @Override
    public String ejecutar(Jugador jugador, String nombreArchivo) {
	String retorno;
	File guardado = new File("partidas/" + nombreArchivo + ".save");
	try {
	    BufferedReader br = new BufferedReader(new FileReader(guardado));
	    if (br.readLine().equals(jugador.getPathHistoria())) {
		String linea;
		Juego j = Juego.getInstancia();
		while ( (linea = br.readLine()) != null)
		    j.ejecutarInstruccion(linea);
		retorno = "Partida cargada.";
		br.close();
	    } else 
		retorno = "El archivo que se intenta cargar es de otra aventura";
	} catch (IOException e) {
	    retorno = "No se pudo leer el archivo de guardado.";
	}
	return retorno;
    }

    @Override
    public boolean validar(Jugador jugador, String restoDelComando) {
	return false;
    }

    @Override
    public ComandoCondicion getTipo() {
	return ComandoCondicion.DEFAULT;
    }
}
