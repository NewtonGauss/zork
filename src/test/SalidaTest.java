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
	
	 String jsonNPC =  "{\n"
		      + "\"name\": \"pirata fantasma\" , \n"
		      + "\"gender\": \"male\" , \n"
		      + "\"number\": \"singular\" , \n"
		      + "\"description\": \"Aqui no puedes pasar! El pirata fantasma no te dejara pasar\" , \n"
		      + "\"talk\": \"No hay nada que me digas que me haga cambiar de opinion!\"}";

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


}
