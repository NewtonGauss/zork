package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.Room;
import zork.Salida;

class RoomTest {

	String jsonRoom =  "{\n"
			+ " \"name\": \"muelle\" ,\n"
		    + " \"gender\": \"male\" ,\n"
		    + " \"number\": \"singular\" ,\n"
		    + " \"description\": \"Estas en un muelle\" }" ;
	
	String jsonRoom2 =  "{\n"
			+ " \"name\": \"barrio\" ,\n"
		    + " \"gender\": \"male\" ,\n"
		    + " \"number\": \"singular\" ,\n"
		    + " \"description\": \"Estas en un barrio\" }" ;
	
	String jsonSitio = "{\n"
			+ " \"name\": \"suelo\" ,\n"
		    + " \"gender\": \"male\" ,\n"
		    + " \"number\": \"singular\" }" ;
			
	 String jsonItem = " {\n" + 
			"      \"name\": \"barreta\",\n" + 
			"      \"gender\": \"female\",\n" + 
			"      \"number\": \"singular\",\n" + 
			"      \"actions\": [\n" + 
			"        \"usar\"\n" + 
			"      ],\n" + 
			"      \"effects_over\": [\n" + 
			"        \"npcs\",\n" + 
			"        \"self\",\n" + 
			"        \"item\"\n" + 
			"      ]\n" + 
			"    }";

			
	@Test // Pruebo la creacion de la habitacion
	void test1() {
		Room room = new Room(JsonParser.parseString(jsonRoom));
		assertEquals("el muelle", room.toString());
	}
	
	@Test //Pruebo agregando Salida...
	void test2() {
		Room room = new Room(JsonParser.parseString(jsonRoom));
		Room room2 = new Room(JsonParser.parseString(jsonRoom2));
		Salida salida = new Salida(room2);
		room.addSalida(salida);
		assertEquals(salida.getClass(), room.getSalida("barrio").getClass());
	}

}
