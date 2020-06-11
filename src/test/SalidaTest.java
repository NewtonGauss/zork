package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.*;

import com.google.gson.JsonParser;

import zork.*;
import zork.input.TriggerInput;
import zork.input.parametro.*;

class SalidaTest {

    String jsonRoom = "{\n" + " \"name\": \"muelle\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" ,\n"
	    + " \"description\": \"Estas en un muelle\" }";

    String jsonRoom2 = "{\n" + " \"name\": \"barrio\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" ,\n"
	    + " \"description\": \"Estas en un barrio\" }";
    private NPC npc, npcDefeat;

    @BeforeEach
    void initNPC() {
	NPCInputParametro input = new NPCInputParametro("pirata fantasma");
	input.setGender('m');
	input.setNumber('s');
	input.setDescripcion(
		"- '¡No puedes pasar!' El pirata fantasma no te dejará pasar");
	input.setCharla("¡No hay nada que me digas que me haga cambiar de opinión!");
	input.setEnemigo(true);
	TriggerInputParametro trigger = new TriggerInputParametro(TipoTrigger.ITEM);
	trigger.setAfterTrigger("remove");
	trigger.setMensaje(
		"- '¡Me encanta la cerveza de raiz!' El pirata fantasma se veía entusiasmado por tu ofrecimiento... sin embargo, cuando lo rociaste comenzó a desintegrarse. La mitad de arriba de su cuerpo se desvaneció, y las piernas inmediatamente echaron a correr.");
	trigger.setObjetoActivador("rociador con cerveza de raiz");	
	input.setListaTriggers(new ArrayList<TriggerInput>(Arrays.asList(trigger)));
	npc = new NPC(input);
	trigger.setAfterTrigger("defeat");
	input.setListaTriggers(new ArrayList<TriggerInput>(Arrays.asList(trigger)));
	npcDefeat = new NPC(input);
    }

    @Test // PRUEBO GENERAR UN ROOM, LE AGREGO UN NPC Y ME FIJO SI FUNCIONA EL METODO
	  // ISENEMYDEFEAT.
    void test1() {
	Habitacion room1 = new Habitacion(JsonParser.parseString(jsonRoom));
	Habitacion room2 = new Habitacion(JsonParser.parseString(jsonRoom2));
	Salida salida = new Salida(room2);
	npc.setEnemigo(true);
	salida.addNPC(npc);
	room1.addSalida(salida, "sur");
	assertEquals(false, salida.isEnemigoDerrotado());
	npc.matar();
	assertEquals(true, salida.isEnemigoDerrotado());
    }

    @Test // PRUEBO GENERAR UN ROOM, LE AGREGO UNA SALIDA CON UN NPC Y ME FIJO SI
	  // DERROTAMOS AL NPC.
    void test2() {
	Habitacion room1 = new Habitacion(JsonParser.parseString(jsonRoom));
	Habitacion room2 = new Habitacion(JsonParser.parseString(jsonRoom2));
	Salida salida = new Salida(room2);
	npc.setEnemigo(true);
	salida.addNPC(npc);
	room1.addSalida(salida, "sur");
	assertEquals(room2.getNombre(), room1.getSalida("sur").getNombre());
	assertEquals(false, room1.getSalida("sur").isEnemigoDerrotado());
	npc.matar();
	assertEquals(true, room1.getSalida("sur").isEnemigoDerrotado());
    }

    /*
     * Debido a que existe un trigger que remueve a un npc de una habitacion, pruebo
     * si al hacer esto efectivamente me indica que el enemigo ha sido derrotado
     */
    @Test
    void testRemove() {
	Habitacion room2 = new Habitacion(JsonParser.parseString(jsonRoom2));
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
	Salida salida = new Salida(room2);
	room1.addSalida(salida, "north");
	room1.ponerObstaculo(npc, "north");
	assertEquals(false, room1.getSalida("north").isEnemigoDerrotado());
	npc.ejecutarTrigger(TipoTrigger.ITEM, "barreta");
	assertFalse(room1.getSalida("north").isEnemigoDerrotado());
	npc.ejecutarTrigger(TipoTrigger.ITEM, "rociador con cerveza de raiz");
	assertTrue(room1.getSalida("north").isEnemigoDerrotado());
	assertEquals(room2, room1.getSalida("north").getHabitacion());
    }

    /*
     * Pruebo una ejecucion comun con defeat en vez de remove
     */
    @Test
    void testComunDefeat() {
	Habitacion room1 = new Habitacion(JsonParser.parseString(jsonRoom));
	Habitacion room2 = new Habitacion(JsonParser.parseString(jsonRoom2));
	Salida salida = new Salida(room2);
	room1.addSalida(salida, "north");
	room1.ponerObstaculo(npcDefeat, "north");
	assertEquals(false, room1.getSalida("north").isEnemigoDerrotado());
	npcDefeat.ejecutarTrigger(TipoTrigger.ITEM, "rociador con cerveza de raiz");
	assertTrue(room1.getSalida("north").isEnemigoDerrotado());
	assertEquals(room2, room1.getSalida("north").getHabitacion());
    }

}
