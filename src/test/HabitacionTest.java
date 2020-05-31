package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.Habitacion;
import zork.Item;
import zork.NPC;
import zork.Salida;
import zork.Sitio;
import zork.input.parametro.ItemInputParametro;

class HabitacionTest {

    String jsonRoom = "{\n" + " \"name\": \"muelle\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" ,\n"
	    + " \"description\": \"Estas en un muelle\" }";

    String jsonRoom2 = "{\n" + " \"name\": \"barrio\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" ,\n"
	    + " \"description\": \"Estas en un barrio\" }";

    String jsonSitio = "{\n" + " \"name\": \"suelo\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" }";

    
    private Item barreta;

    @BeforeEach
    void inicializarItems() {
	ItemInputParametro constructorItem = new ItemInputParametro("barreta");
	barreta = new Item(constructorItem);
    }

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
	sitio.addItem(barreta);
	room.addSitio(sitio);
	assertEquals(barreta, room.getSitio("suelo").getItem("barreta"));
    }

    /*
     * Pruebo que me encuentre todos los npcs
     */
    @Test
    void testNPCs() {
	String jsonNPC = "{\n" + "      \"name\": \"pirata fantasma\",\n"
		+ "      \"gender\": \"male\",\n" + "      \"number\": \"singular\",\n"
		+ "      \"description\": \"- '¡No puedes pasar!' El pirata fantasma no te dejará pasar\",\n"
		+ "      \"talk\": \"¡No hay nada que me digas que me haga cambiar de opinión!\",\n"
		+ "			\"points\": \"100\",\n"
		+ "			\"enemy\": \"true\",\n"
		+ "			\"health\": \"100\",\n"
		+ "			\"inventory\": [],\n" + "      \"triggers\": [\n"
		+ "        {\n" + "          \"type\": \"item\",\n"
		+ "          \"thing\": \"rociador con cerveza de raiz\",\n"
		+ "          \"on_trigger\": \"- '¡Me encanta la cerveza de raiz!' El pirata fantasma se veía entusiasmado por tu ofrecimiento... sin embargo, cuando lo rociaste comenzó a desintegrarse. La mitad de arriba de su cuerpo se desvaneció, y las piernas inmediatamente echaron a correr.\",\n"
		+ "          \"after_trigger\": \"remove\"\n" + "        }\n"
		+ "      ]\n" + "    }";
	Habitacion room = new Habitacion(JsonParser.parseString(jsonRoom));
	NPC npc = new NPC(JsonParser.parseString(jsonNPC));
	room.addNPC(npc);
	assertEquals(npc, room.getNPC(npc.getNombre()));
	assertEquals(null, room.getNPC("Newton"));
    }

}
