package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.Item;
import zork.NPC;
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
	void testDescripcion() {
		Room room = new Room(JsonParser.parseString(jsonRoom));
		assertEquals("el muelle", room.toString());
	}
	
	@Test //Pruebo agregando Salida...
	void testSalidas() {
		Room room = new Room(JsonParser.parseString(jsonRoom));
		Room room2 = new Room(JsonParser.parseString(jsonRoom2));
		Salida salida = new Salida(room2);
		room.addSalida(salida, "sur");
		assertEquals(salida.getNombre(), room.getSalida("sur").getNombre());
	}
	
	@Test //Pruebo agregando Sitio...
	void testSitios() {
		Room room = new Room(JsonParser.parseString(jsonRoom));
		Sitio sitio = new Sitio(JsonParser.parseString(jsonSitio));
		room.addSitio(sitio);
		assertEquals(sitio.getNombre(), room.getSitio("suelo").getNombre());
	}
	
	
	@Test //Pruebo obtener el item de un determinado sitio
	void testItemsENSitios() {
		Room room = new Room(JsonParser.parseString(jsonRoom));
		Sitio sitio = new Sitio(JsonParser.parseString(jsonSitio));
		Item item = new Item(JsonParser.parseString(jsonItem));
		sitio.addItem(item);
		room.addSitio(sitio);
		assertEquals(item, room.getSitio("suelo").getItem("barreta"));
	}
	
	/*
	 * Pruebo que me encuentre todos los npcs
	 */
	@Test 
	void testNPCs() {
	    String jsonNPC =  "{\n" + 
		 	"      \"name\": \"pirata fantasma\",\n" + 
		 	"      \"gender\": \"male\",\n" + 
		 	"      \"number\": \"singular\",\n" + 
		 	"      \"description\": \"- '¡No puedes pasar!' El pirata fantasma no te dejará pasar\",\n" + 
		 	"      \"talk\": \"¡No hay nada que me digas que me haga cambiar de opinión!\",\n" + 
		 	"			\"points\": \"100\",\n" + 
		 	"			\"enemy\": \"true\",\n" + 
		 	"			\"health\": \"100\",\n" + 
		 	"			\"inventory\": [],\n" + 
		 	"      \"triggers\": [\n" + 
		 	"        {\n" + 
		 	"          \"type\": \"item\",\n" + 
		 	"          \"thing\": \"rociador con cerveza de raiz\",\n" + 
		 	"          \"on_trigger\": \"- '¡Me encanta la cerveza de raiz!' El pirata fantasma se veía entusiasmado por tu ofrecimiento... sin embargo, cuando lo rociaste comenzó a desintegrarse. La mitad de arriba de su cuerpo se desvaneció, y las piernas inmediatamente echaron a correr.\",\n" + 
		 	"          \"after_trigger\": \"remove\"\n" + 
		 	"        }\n" + 
		 	"      ]\n" + 
		 	"    }";
	    Room room = new Room(JsonParser.parseString(jsonRoom));
	    NPC npc = new NPC(JsonParser.parseString(jsonNPC));
	    room.addNPC(npc);
	    assertEquals(npc, room.getNPC(npc.getName()));
	    assertEquals(null, room.getNPC("Newton"));
	}
	

}
