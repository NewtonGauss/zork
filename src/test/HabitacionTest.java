package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.*;
import zork.input.TriggerInput;
import zork.input.parametro.ItemInputParametro;
import zork.input.parametro.NPCInputParametro;
import zork.input.parametro.TriggerInputParametro;

class HabitacionTest {

    String jsonRoom = "{\n" + " \"name\": \"muelle\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" ,\n"
	    + " \"description\": \"Estas en un muelle\" }";

    String jsonRoom2 = "{\n" + " \"name\": \"barrio\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" ,\n"
	    + " \"description\": \"Estas en un barrio\" }";

    String jsonSitio = "{\n" + " \"name\": \"suelo\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" }";

    @Test // Pruebo la creacion de la habitacion
    void testDescripcion() {
	Habitacion room = new Habitacion(JsonParser.parseString(jsonRoom));
	assertEquals("el muelle", room.toString());
    }

    @Test // Pruebo agregando Salida...
    void testSalidas() {
	Habitacion room = new Habitacion(JsonParser.parseString(jsonRoom));
	Habitacion room2 = new Habitacion(JsonParser.parseString(jsonRoom2));
	Salida salida = new Salida(room2);
	room.addSalida(salida, "sur");
	assertEquals(salida.getNombre(), room.getSalida("sur").getNombre());
    }

    @Test // Pruebo agregando Sitio...
    void testSitios() {
	Habitacion room = new Habitacion(JsonParser.parseString(jsonRoom));
	Sitio sitio = new Sitio(JsonParser.parseString(jsonSitio));
	room.addSitio(sitio);
	assertEquals(sitio.getNombre(), room.getSitio("suelo").getNombre());
    }

    @Test // Pruebo obtener el item de un determinado sitio
    void testItemsENSitios() {
	Habitacion room = new Habitacion(JsonParser.parseString(jsonRoom));
	Sitio sitio = new Sitio(JsonParser.parseString(jsonSitio));
	Item barreta = inicializarItems();
	sitio.addItem(barreta);
	room.addSitio(sitio);
	assertEquals(barreta, room.getSitio("suelo").getItem("barreta"));
    }

    private Item inicializarItems() {
	ItemInputParametro constructorItem = new ItemInputParametro("barreta");
	return new Item(constructorItem);
    }

    /*
     * Pruebo que me encuentre todos los npcs
     */
    @Test
    void testNPCs() {
	NPC npc = initNPC();
	Habitacion room = new Habitacion(JsonParser.parseString(jsonRoom));
	room.addNPC(npc);
	assertEquals(npc, room.getNPC(npc.getNombre()));
	assertEquals(null, room.getNPC("Newton"));
    }

    private NPC initNPC() {
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
	return new NPC(input);
    }

}
