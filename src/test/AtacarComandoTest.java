package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.AtacarConComando;
import zork.Item;
import zork.Jugador;
import zork.NPC;
import zork.Room;

class AtacarComandoTest {

    String jsonPlayer = "{\n" + " \"character\": \"Santi\"  }";
    String jsonRoom = "{\n" + " \"name\": \"muelle\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" ,\n" + " \"description\": \"Estas en un muelle\" }";

    String jsonNPC = "{\n" + "\"name\": \"Maxi Hiena\" , \n" + "\"gender\": \"male\" , \n"
	    + "\"number\": \"singular\" , \n"
	    + "\"description\": \"Aqui no puedes pasar! El pirata fantasma no te dejara pasar\" , \n"
	    + "\"points\": \"100\" , \n" + "\"enemy\": \"true\" , \n" + "\"health\": \"100\" , \n"
	    + "\"talk\": \"No hay nada que me digas que me haga cambiar de opinion!\", \n" + "\"triggers\": [{"
	    + " \"type\": \"attack\" ,\n" + " \"thing\": \"espada\" ,\n"
	    + " \"on_trigger\": \"Uhhh me rompiste la gorra\" ,\n" + " \"after_trigger\": \"kill\" } ] }";

    String jsonItem = "{\n" + "      \"name\": \"espada\",\n" + "      \"gender\": \"female\",\n"
	    + "      \"number\": \"singular\",\n" + "			\"points\": \"100\",\n"
	    + "			\"weight\": \"10\",\n" + "			\"type\": \"weapon\",\n"
	    + "      \"actions\": [\n" + "        \"usar\"\n" + "      ],\n" + "      \"effects_over\": [\n"
	    + "        \"npcs\",\n" + "        \"self\",\n" + "        \"item\"\n" + "      ]\n" + "    }";

    @Test
    void testAtacarConComando() {

	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Room room = new Room(JsonParser.parseString(jsonRoom));
	NPC npc = new NPC(JsonParser.parseString(jsonNPC));
	Item item = new Item(JsonParser.parseString(jsonItem));
	AtacarConComando acc = new AtacarConComando();

	j1.addItem(item);
	room.addNPC(npc);
	j1.setHabitacionActual(room);
	assertEquals("Maxi Hiena: Uhhh me rompiste la gorra.", acc.ejecutar(j1, npc.getName() + ":" + item.getNombre()));
    }
    
    @Test
    void testNpcInvalido() {
	
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Room room = new Room(JsonParser.parseString(jsonRoom));
	NPC npc = new NPC(JsonParser.parseString(jsonNPC));
	Item item = new Item(JsonParser.parseString(jsonItem));
	AtacarConComando acc = new AtacarConComando();
	
	j1.addItem(item);
	room.addNPC(npc);
	j1.setHabitacionActual(room);
	assertEquals("pirata no se encuentra en el muelle.", acc.ejecutar(j1,"pirata:" + item.getNombre()));
    }

    @Test
    void testAtacarSinItems() {

	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Room room = new Room(JsonParser.parseString(jsonRoom));
	NPC npc = new NPC(JsonParser.parseString(jsonNPC));
	AtacarConComando acc = new AtacarConComando();

	room.addNPC(npc);
	j1.setHabitacionActual(room);
	assertEquals("No tiene armas para atacar. ¡Busque una!", acc.ejecutar(j1, npc.getName() + ":manos"));
    }

    @Test
    void testAtacarSinArmasConItems() {
	String espejoJson = "{\n" + "      \"name\": \"espejo\",\n" + "      \"gender\": \"male\",\n"
		+ "      \"number\": \"singular\",\n" + "			\"points\": \"100\",\n"
		+ "			\"weight\": \"10\",\n" + "			\"type\": \"vanilla\",\n"
		+ "      \"actions\": [\n" + "        \"usar\"\n" + "      ],\n" + "      \"effects_over\": [\n"
		+ "        \"npcs\",\n" + "        \"self\",\n" + "        \"item\"\n" + "      ]\n" + "    }",
		canicasJson = "{\n" + "      \"name\": \"canicas\",\n" + "      \"gender\": \"female\",\n"
			+ "      \"number\": \"plural\",\n" + "			\"points\": \"100\",\n"
			+ "			\"weight\": \"10\",\n"
			+ "			\"type\": \"vanilla\",\n" + "      \"actions\": [\n"
			+ "        \"usar\"\n" + "      ],\n" + "      \"effects_over\": [\n" + "        \"npcs\",\n"
			+ "        \"self\",\n" + "        \"item\"\n" + "      ]\n" + "    }";
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Room room = new Room(JsonParser.parseString(jsonRoom));
	NPC npc = new NPC(JsonParser.parseString(jsonNPC));
	Item espejo = new Item(JsonParser.parseString(espejoJson));
	Item canicas = new Item(JsonParser.parseString(canicasJson));
	AtacarConComando acc = new AtacarConComando();

	j1.addItem(espejo);
	j1.addItem(canicas);
	room.addNPC(npc);
	j1.setHabitacionActual(room);
	assertEquals("No tiene armas para atacar. ¡Busque una!", acc.ejecutar(j1, npc.getName() + ":" + espejo.getNombre()));
    }
    
    @Test
    void testItemEquivocado() {
	String espejoJson = "{\n" + "      \"name\": \"espejo\",\n" + "      \"gender\": \"male\",\n"
		+ "      \"number\": \"singular\",\n" + "			\"points\": \"100\",\n"
		+ "			\"weight\": \"10\",\n" + "			\"type\": \"vanilla\",\n"
		+ "      \"actions\": [\n" + "        \"usar\"\n" + "      ],\n" + "      \"effects_over\": [\n"
		+ "        \"npcs\",\n" + "        \"self\",\n" + "        \"item\"\n" + "      ]\n" + "    }",
		canicasJson = "{\n" + "      \"name\": \"canicas\",\n" + "      \"gender\": \"female\",\n"
			+ "      \"number\": \"plural\",\n" + "			\"points\": \"100\",\n"
			+ "			\"weight\": \"10\",\n"
			+ "			\"type\": \"vanilla\",\n" + "      \"actions\": [\n"
			+ "        \"usar\"\n" + "      ],\n" + "      \"effects_over\": [\n" + "        \"npcs\",\n"
			+ "        \"self\",\n" + "        \"item\"\n" + "      ]\n" + "    }";
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Room room = new Room(JsonParser.parseString(jsonRoom));
	NPC npc = new NPC(JsonParser.parseString(jsonNPC));
	Item espejo = new Item(JsonParser.parseString(espejoJson));
	Item canicas = new Item(JsonParser.parseString(canicasJson));
	Item espada = new Item(JsonParser.parseString(jsonItem));
	AtacarConComando acc = new AtacarConComando();

	j1.addItem(espejo);
	j1.addItem(canicas);
	j1.addItem(espada);
	room.addNPC(npc);
	j1.setHabitacionActual(room);
	assertEquals("Utilice uno de los siguientes items para atacar: \nespada\n", acc.ejecutar(j1, npc.getName() + ":" + espejo.getNombre()));
    }

}
