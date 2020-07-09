package zork.comandos;

import java.io.*;
import java.util.List;

import zork.*;
import zork.endgame.ComandoCondicion;

public class GuardarComando implements Comando {

    @Override
    public String ejecutar(Jugador jugador, String nombreArchivo) {
	String retorno;
	File directorio = new File("partidas/");
	if (!directorio.exists())
	    directorio.mkdir();
	
	File guardado = new File(directorio.getName() + '/' + nombreArchivo + ".save");
	try {
	    guardado.createNewFile();
	    BufferedWriter bf = new BufferedWriter(new FileWriter(guardado, false));
	    List<String> historial = Juego.getInstancia().getHistorialInstrucciones();
	    
	    bf.write(jugador.getPathHistoria());
	    bf.newLine();
	    for (String instruccion : historial) {
		bf.write(instruccion);
		bf.newLine();
	    }
	    
	    retorno = "Guardado exitoso!";
	    bf.close();
	} catch (IOException e) {
	    retorno = "No se pudo crear el archivo de guardado, intente con otro nombre";
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
