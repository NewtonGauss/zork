package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.Character;
import zork.Item;
import zork.Jugador;
import zork.NPC;
import zork.Room;
import zork.Salida;

class JugadorTest {

	String jsonPlayer = "{\n" + " \"character\": \"Guybrush Threepwood\"  }";

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

	@Test // Testeo el constructor de Jugador
	void testNombreNPC() {
		Jugador player = new Jugador(JsonParser.parseString(jsonPlayer));
		assertEquals("Guybrush Threepwood", player.getName());
	}

	@Test // Testeo sumar la cantidad de movimientos del jugador
	void testMovimientosPlayer() {
		Jugador player = new Jugador(JsonParser.parseString(jsonPlayer));
		assertEquals(0, player.getCantMovimientos());
		player.sumarMovimiento();
		assertEquals(1, player.getCantMovimientos());
	}

	@Test // Testeo obtener el score del jugador
	void testGetScore() {
		Jugador player = new Jugador(JsonParser.parseString(jsonPlayer));
		assertEquals(0, player.getScore());
	}

	@Test // Le agrego un item al jugador y  lo devuelvo.
	void testAgarrarYSoltarItems() {
		Character player = new Jugador(JsonParser.parseString(jsonPlayer));
		Item item = new Item(JsonParser.parseString(jsonItem));
		assertEquals(true, player.addItem(item));
		assertEquals(item, player.getItem(item.getNombre()));
		assertTrue(player.removeItem(item.getNombre()));
		assertFalse(player.removeItem(item.getNombre()));
	}
	
	@Test
	void testSet() {
		Room r = new Room(JsonParser.parseString(jsonRoom));
		Character jugador = new Jugador(JsonParser.parseString(jsonPlayer));
		jugador.setHabitacionActual(r);
		assertEquals(r, jugador.getHabitacionActual());
	}
	
	@Test
	void testMover() {
		Room room1 = new Room(JsonParser.parseString(jsonRoom));
		Room room2 = new Room(JsonParser.parseString(jsonRoom2));
		NPC npc = new NPC(JsonParser.parseString(jsonNPC));
		Salida salida = new Salida(room2);
		npc.setEnemy(true);
		salida.addNPC(npc);
		room1.addSalida(salida,"norte");
		assertEquals(false, salida.isEnemyDefeated());
		npc.killNPC();
		assertEquals(true, salida.isEnemyDefeated());
		Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
		jugador.setHabitacionActual(room1);
		assertTrue(jugador.mover("norte"));
		assertEquals(room2, jugador.getHabitacionActual());
	}
	
	@Test
	void testSalud() {
	    Character jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	    assertEquals(100, jugador.getSalud());
	    jugador.restarSalud(10);
	    assertEquals(90, jugador.getSalud());
	    jugador.sumarSalud(5);
	    assertEquals(95, jugador.getSalud());
	    jugador.restarSalud(100);
	    assertEquals(0, jugador.getSalud());
	    jugador.sumarSalud(100);
	    assertEquals(0, jugador.getSalud());
	    
	}

}
