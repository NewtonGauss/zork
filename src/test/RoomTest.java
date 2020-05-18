package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.Item;
import zork.Room;
import zork.Salida;
import zork.Sitio;

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
	 	"			\"points\": \"100\",\n" + 
	 	"			\"weight\": \"10\",\n" + 
	 	"			\"type\": \"weapon\",\n" + 
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
		assertEquals(salida.getNombre(), room.getSalida("barrio").getNombre());
	}
	
	@Test //Pruebo agregando Sitio...
	void test3() {
		Room room = new Room(JsonParser.parseString(jsonRoom));
		Sitio sitio = new Sitio(JsonParser.parseString(jsonSitio));
		room.addSitio(sitio);
		assertEquals(sitio.getNombre(), room.getSitio("suelo").getNombre());
	}
	
	
	@Test //Pruebo obtener el item de un determinado sitio
	void test4() {
		Room room = new Room(JsonParser.parseString(jsonRoom));
		Sitio sitio = new Sitio(JsonParser.parseString(jsonSitio));
		Item item = new Item(JsonParser.parseString(jsonItem));
		sitio.addItem(item);
		room.addSitio(sitio);
		assertEquals(item, room.getSitio("suelo").getItem("barreta"));
	}
	

}
