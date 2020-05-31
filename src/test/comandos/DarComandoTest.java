package test.comandos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.AccionItem;
import zork.Habitacion;
import zork.Item;
import zork.Jugador;
import zork.NPC;
import zork.comandos.DarComando;
import zork.input.parametro.ItemInputParametro;

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
		    + "      \"gender\": \"male\",\n"
		    + "      \"number\": \"singular\",\n"
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

    private Item itemDropeable;

    @BeforeEach
    void inicializarItem() {
	ItemInputParametro constructorItem = new ItemInputParametro("espada");
	constructorItem.setGender('f');
	constructorItem.setNumber('s');
	constructorItem.setAccionesValidas(
		new ArrayList<AccionItem>(Arrays.asList(AccionItem.DROP)));
	itemDropeable = new Item(constructorItem);
    }

    @Test
    void testExitoDarItem() {

	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion room = new Habitacion(JsonParser.parseString(jsonRoom));
	NPC pirata = new NPC(JsonParser.parseString(jsonPirata));
	DarComando dcc = new DarComando();

	j1.ponerItem(itemDropeable);
	room.addNPC(pirata);
	j1.setHabitacionActual(room);
	assertEquals("Le diste la espada al pirata fantasma.",
		dcc.ejecutar(j1, itemDropeable.getNombre() + ":" + pirata.getNombre()));
    }

    @Test
    void testNoTenesItem() {
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion room = new Habitacion(JsonParser.parseString(jsonRoom));
	NPC npc = new NPC(JsonParser.parseString(jsonNPC));
	DarComando dcc = new DarComando();
	room.addNPC(npc);
	j1.setHabitacionActual(room);
	assertEquals("No tienes espada en tu inventario.",
		dcc.ejecutar(j1, itemDropeable.getNombre() + ":" + npc.getNombre()));
    }

    @Test
    void testNoEstaNpcEnRoom() {
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion room = new Habitacion(JsonParser.parseString(jsonRoom));
	NPC npc = new NPC(JsonParser.parseString(jsonNPC));
	DarComando dcc = new DarComando();

	j1.ponerItem(itemDropeable);
	j1.setHabitacionActual(room);
	assertEquals("Maxi Hiena no se encuentra en el muelle.",
		dcc.ejecutar(j1, itemDropeable.getNombre() + ":" + npc.getNombre()));
    }

    @Test
    void testNoExisteNpc() {
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion room = new Habitacion(JsonParser.parseString(jsonRoom));
	DarComando dcc = new DarComando();
	j1.ponerItem(itemDropeable);
	j1.setHabitacionActual(room);
	assertEquals("Topo Malvado no se encuentra en el muelle.",
		dcc.ejecutar(j1, itemDropeable.getNombre() + ":Topo Malvado"));
    }
}
