package zork;

import java.io.*;



public class Ayuda implements Comando {

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
    
}
