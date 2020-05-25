package test.comandos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.Item;
import zork.Jugador;
import zork.NPC;
import zork.Room;
import zork.comandos.DarComando;

class DarComandoTest {
    String jsonPlayer = "{\n" + " \"character\": \"Santi\"  }";
    String jsonRoom = "{\n" + " \"name\": \"muelle\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" ,\n"
	    + " \"description\": \"Estas en un muelle\" }";

    String jsonNPC = "{\n" + "\"name\": \"Maxi Hiena\" , \n" + "\"gender\": \"male\" , \n"
	    + "\"number\": \"singular\" , \n"
	    + "\"description\": \"Aqui no puedes pasar! El pirata fantasma no te dejara pasar\" , \n"
	    + "\"points\": \"100\" , \n" + "\"enemy\": \"true\" , \n"
	    + "\"health\": \"100\" , \n"
	    + "\"talk\": \"No hay nada que me digas que me haga cambiar de opinion!\", \n"
	    + "\"triggers\": [{" + " \"type\": \"attack\" ,\n"
	    + " \"thing\": \"espada\" ,\n"
	    + " \"on_trigger\": \"Uhhh me rompiste la gorra\" ,\n"
	    + " \"after_trigger\": \"kill\" } ] }",
    jsonPirata = "{\n" + "      \"name\": \"pirata fantasma\",\n"
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
	    + "          \"after_trigger\": \"remove\"\n" + "        }\n" + "      ]\n"
	    + "    }";

    String jsonItem="{\n"+"      \"name\": \"espada\",\n"+"      \"gender\": \"female\",\n"+"      \"number\": \"singular\",\n"+"			\"points\": \"100\",\n"+"			\"weight\": \"10\",\n"+"			\"type\": \"weapon\",\n"+"      \"actions\": [\n"+"        \"usar\"\n"+"      ],\n"+"      \"effects_over\": [\n"+"        \"npcs\",\n"+"        \"self\",\n"+"        \"item\"\n"+"      ]\n"+"    }";

    @Test
    void testExitoDarItem() {

	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Room room = new Room(JsonParser.parseString(jsonRoom));
	NPC pirata = new NPC(JsonParser.parseString(jsonPirata));
	Item item = new Item(JsonParser.parseString(jsonItem));
	DarComando dcc = new DarComando();

	j1.addItem(item);
	room.addNPC(pirata);
	j1.setHabitacionActual(room);
	assertEquals("Le diste la espada al pirata fantasma.", dcc.ejecutar(j1, item.getNombre() + ":" + pirata.getName()));
    }

    @Test
    void testNoTenesItem() {
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Room room = new Room(JsonParser.parseString(jsonRoom));
	NPC npc = new NPC(JsonParser.parseString(jsonNPC));
	Item item = new Item(JsonParser.parseString(jsonItem));
	DarComando dcc = new DarComando();
	room.addNPC(npc);
	j1.setHabitacionActual(room);
	assertEquals("No tienes espada en tu inventario.",
		dcc.ejecutar(j1, item.getNombre() + ":" + npc.getName()));
    }

    @Test
    void testNoEstaNpcEnRoom() {
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Room room = new Room(JsonParser.parseString(jsonRoom));
	NPC npc = new NPC(JsonParser.parseString(jsonNPC));
	Item item = new Item(JsonParser.parseString(jsonItem));
	DarComando dcc = new DarComando();

	j1.addItem(item);
	j1.setHabitacionActual(room);
	assertEquals("Maxi Hiena no se encuentra en el muelle.",
		dcc.ejecutar(j1, item.getNombre() + ":" + npc.getName()));
    }

    @Test
    void testNoExisteNpc() {
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Room room = new Room(JsonParser.parseString(jsonRoom));
	Item item = new Item(JsonParser.parseString(jsonItem));
	DarComando dcc = new DarComando();
	j1.addItem(item);
	j1.setHabitacionActual(room);
	assertEquals("Topo Malvado no se encuentra en el muelle.",
		dcc.ejecutar(j1, item.getNombre() + ":Topo Malvado"));
    }
}
