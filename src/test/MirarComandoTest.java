package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.Jugador;
import zork.MirarComando;
import zork.Room;

class MirarComandoTest {

	String jsonPlayer = "{\n" + " \"character\": \"Guybrush Threepwood\"  }";

	
	String jsonRoom =  "{\n"
			+ " \"name\": \"muelle\" ,\n"
		    + " \"gender\": \"male\" ,\n"
		    + " \"number\": \"singular\" ,\n"
		    + " \"description\": \"Estas en un muelle\" }" ;

	 
	@Test
	void test() {
		Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
		Room r1 = new Room(JsonParser.parseString(jsonRoom));
		j1.setHabitacionActual(r1);
		MirarComando c1 = new MirarComando();
		assertEquals("Estas en un muelle", c1.ejecutar(j1, ""));
	}

}
