package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.Ayuda;
import zork.Jugador;

class AyudaTest {

    String jsonPlayer = "{\n" + " \"character\": \"Guybrush Threepwood\"  }";
    
    @Test
    void test() {
	Jugador j = new Jugador(JsonParser.parseString(jsonPlayer));
	Ayuda a=new Ayuda();
	//System.out.print(a.ejecutar(j,""));
	assertFalse(a.ejecutar(j,"").equals("No se encontro el archivo de Ayuda"));
    }

}
