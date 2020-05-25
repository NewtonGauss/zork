package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.AyudaComando;
import zork.Jugador;

class AyudaTest {

    String jsonPlayer = "{\n" + " \"character\": \"Guybrush Threepwood\"  }";
    
    @Test
    void testDeAyuda() {
	Jugador j = new Jugador(JsonParser.parseString(jsonPlayer));
	AyudaComando a=new AyudaComando();
	//System.out.print(a.ejecutar(j,""));
	assertFalse(a.ejecutar(j,"").equals("No se encontro el archivo de Ayuda"));
    }

}
