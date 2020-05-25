package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.Habitacion;
import zork.NPC;
import zork.Salida;
import zork.Trigger;

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

	@Test //PRUEBO GENERAR UN ROOM, LE AGREGO UN NPC Y ME FIJO SI FUNCIONA EL METODO ISENEMYDEFEAT.
	void test1() {
		Habitacion room1 = new Habitacion(JsonParser.parseString(jsonRoom));
		Habitacion room2 = new Habitacion(JsonParser.parseString(jsonRoom2));
		NPC npc = new NPC(JsonParser.parseString(jsonNPC));
		Salida salida = new Salida(room2);
		npc.setEnemigo(true);
		salida.addNPC(npc);
		room1.addSalida(salida, "sur");
		assertEquals(false, salida.isEnemigoDerrotado());
		npc.matar();
		assertEquals(true, salida.isEnemigoDerrotado());
	}
	
	@Test //PRUEBO GENERAR UN ROOM, LE AGREGO UNA SALIDA CON UN NPC Y ME FIJO SI DERROTAMOS AL NPC.
	void test2() {
		Habitacion room1 = new Habitacion(JsonParser.parseString(jsonRoom));
		Habitacion room2 = new Habitacion(JsonParser.parseString(jsonRoom2));
		NPC npc = new NPC(JsonParser.parseString(jsonNPC));
		Salida salida = new Salida(room2);
		npc.setEnemigo(true);
		salida.addNPC(npc);
		room1.addSalida(salida, "sur");
		assertEquals(room2.getNombre(), room1.getSalida("sur").getNombre());
		assertEquals(false, room1.getSalida("sur").isEnemigoDerrotado());
		npc.matar();
		assertEquals(true , room1.getSalida("sur").isEnemigoDerrotado());
	}
	
	/*
	 * Debido a que existe un trigger que remueve a un npc de una habitacion,
	 * pruebo si al hacer esto efectivamente me indica que el enemigo ha sido 
	 * derrotado
	 */
	@Test
	void testRemove() {
	    	Habitacion room1 = new Habitacion(JsonParser.parseString(jsonRoom));
		Habitacion room2 = new Habitacion(JsonParser.parseString(jsonRoom2));
		NPC npc = new NPC(JsonParser.parseString(jsonNPC));
		Salida salida = new Salida(room2);
		npc.setEnemigo(true);
		salida.addNPC(npc);
		assertEquals(false, salida.isEnemigoDerrotado());
		salida.sacarNPC(npc.getNombre());
		assertEquals(true, salida.isEnemigoDerrotado());
	}

	/*
	 * Pruebo una ejecucion comun
	 */
	@Test
	void testComun() {
		Habitacion room1 = new Habitacion(JsonParser.parseString(jsonRoom));
		Habitacion room2 = new Habitacion(JsonParser.parseString(jsonRoom2));
		NPC npc = new NPC(JsonParser.parseString(jsonNPC));
		Salida salida = new Salida(room2);
		room1.addSalida(salida, "north");
		room1.ponerObstaculo(npc, "north");
		assertEquals(false, room1.getSalida("north").isEnemigoDerrotado());
		npc.ejecutarTrigger(Trigger.ITEM, "barreta");
		assertFalse(room1.getSalida("north").isEnemigoDerrotado());
		npc.ejecutarTrigger(Trigger.ITEM, "rociador con cerveza de raiz");
		assertTrue(room1.getSalida("north").isEnemigoDerrotado());
		assertEquals(room2, room1.getSalida("north").getHabitacion());
	}
	
	/*
	 * Pruebo una ejecucion comun con defeat en vez de remove
	 */
	@Test
	void testComunDefeat() {
	    String npcDefeat =  "{\n" + 
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
		 	"          \"type\": \"talk\",\n" + 
		 	"          \"thing\": \"\",\n" + 
		 	"          \"on_trigger\": \"- '¡Me encanta la cerveza de raiz!' El pirata fantasma se veía entusiasmado por tu ofrecimiento... sin embargo, cuando lo rociaste comenzó a desintegrarse. La mitad de arriba de su cuerpo se desvaneció, y las piernas inmediatamente echaron a correr.\",\n" + 
		 	"          \"after_trigger\": \"defeat\"\n" + 
		 	"        }\n" + 
		 	"      ]\n" + 
		 	"    }";
		Habitacion room1 = new Habitacion(JsonParser.parseString(jsonRoom));
		Habitacion room2 = new Habitacion(JsonParser.parseString(jsonRoom2));
		NPC npc = new NPC(JsonParser.parseString(npcDefeat));
		Salida salida = new Salida(room2);
		room1.addSalida(salida, "north");
		room1.ponerObstaculo(npc, "north");
		assertEquals(false, room1.getSalida("north").isEnemigoDerrotado());
		npc.ejecutarTrigger(Trigger.TALK, "");
		assertTrue(room1.getSalida("north").isEnemigoDerrotado());
		assertEquals(room2, room1.getSalida("north").getHabitacion());
	}

}
