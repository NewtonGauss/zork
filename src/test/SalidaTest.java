package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.NPC;
import zork.Room;
import zork.Salida;

class SalidaTest {
	
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
	 	"			\"enemy\": \"false\",\n" + 
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

	@Test //PRUEBO GENERAR UN ROOM, LE AGREGO UN NPC Y ME FIJO SI FUNCIONA EL METODO ISENEMYDEFEAT.
	void test1() {
		Room room1 = new Room(JsonParser.parseString(jsonRoom));
		Room room2 = new Room(JsonParser.parseString(jsonRoom2));
		NPC npc = new NPC(JsonParser.parseString(jsonNPC));
		Salida salida = new Salida(room2);
		npc.setEnemy(true);
		salida.addNPC(npc);
		room1.addSalida(salida);
		assertEquals(false, salida.isEnemyDefeated());
		npc.killNPC();
		assertEquals(true, salida.isEnemyDefeated());
	}
	
	@Test //PRUEBO GENERAR UN ROOM, LE AGREGO UNA SALIDA CON UN NPC Y ME FIJO SI DERROTAMOS AL NPC.
	void test2() {
		Room room1 = new Room(JsonParser.parseString(jsonRoom));
		Room room2 = new Room(JsonParser.parseString(jsonRoom2));
		NPC npc = new NPC(JsonParser.parseString(jsonNPC));
		Salida salida = new Salida(room2);
		npc.setEnemy(true);
		salida.addNPC(npc);
		room1.addSalida(salida);
		assertEquals(room2.getNombre(), room1.getSalida("barrio").getNombre());
		assertEquals(false, room1.getSalida("barrio").isEnemyDefeated());
		npc.killNPC();
		assertEquals(true , room1.getSalida("barrio").isEnemyDefeated());
	}
	
	/*
	 * Debido a que existe un trigger que remueve a un npc de una habitacion,
	 * pruebo si al hacer esto efectivamente me indica que el enemigo ha sido 
	 * derrotado
	 */
	@Test
	void testRemove() {
	    	Room room1 = new Room(JsonParser.parseString(jsonRoom));
		Room room2 = new Room(JsonParser.parseString(jsonRoom2));
		NPC npc = new NPC(JsonParser.parseString(jsonNPC));
		Salida salida = new Salida(room2);
		npc.setEnemy(true);
		salida.addNPC(npc);
		assertEquals(false, salida.isEnemyDefeated());
		salida.removeNPC(npc.getName());
		assertEquals(true, salida.isEnemyDefeated());
	}

	/*
	 * Pruebo una ejecucion comun
	 */
	@Test
	void testComun() {
		Room room1 = new Room(JsonParser.parseString(jsonRoom));
		Room room2 = new Room(JsonParser.parseString(jsonRoom2));
		NPC npc = new NPC(JsonParser.parseString(jsonNPC));
		Salida salida = new Salida(room2);
		room1.addSalida(salida, "north");
		room1.addObstacle(npc, "north");
		assertEquals(false, room1.getSalida("north").isEnemyDefeated());
		npc.ejecutarTrigger("item", "rociador con cerveza de raiz");
		assertTrue(room1.getSalida("north").isEnemyDefeated());
		assertEquals(room2, room1.getSalida("north").getRoom());
	}

}
