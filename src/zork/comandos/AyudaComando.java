package zork.comandos;

import java.io.*;

import zork.Jugador;
import zork.endgame.ComandoCondicion;



public class AyudaComando implements Comando {

    @Override
    public String ejecutar(Jugador jugador, String restoDelComando) {
	String retorno="No se encontro el archivo de Ayuda";
	    File f=new File("ayuda.txt");
	    FileInputStream fis=null;
	try {
	   f=new File("ayuda.txt");
	   fis=new FileInputStream(f);
	   byte[] texto =new byte[(int)f.length()];
	   fis.read(texto);
	   retorno = new String(texto);
	   fis.close();
	} catch (Exception e) {
	    
	    e.printStackTrace();
	}
	
	return retorno;
    }

    @Override
    public boolean validar(Jugador jugador, String restoDelComando) {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public ComandoCondicion getTipo() {
	return null;
    }
    
}
