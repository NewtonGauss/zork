package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.Item;
import zork.Jugador;
import zork.MirarComando;
import zork.NPC;
import zork.Room;
import zork.Sitio;

class MirarComandoTest {

    String jsonPlayer = "{\n" + " \"character\": \"Guybrush Threepwood\"  }";

    String jsonMuelle = "{\n" + " \"name\": \"muelle\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" ,\n"
	    + " \"description\": \"Estas en un muelle\" }";

    String jsonPirata = "{\n" + "      \"name\": \"pirata fantasma\",\n"
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
	    + "    }",
	    jsonAbeja = "{\n" + "      \"name\": \"abeja fantasma\",\n"
		    + "      \"gender\": \"female\",\n" + "      \"number\": \"singular\",\n"
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
		    + "    }",
	    jsonSuelo = "{\n" + "          \"name\": \"suelo\",\n"
		    + "          \"gender\": \"male\",\n"
		    + "          \"number\": \"singular\",\n" + "          \"items\": [\n"
		    + "            \"barreta\",\n"
		    + "            \"rociador con cerveza de raiz\",\n"
		    + "            \"espejo\"\n" + "          ]\n" + "        }",
	    barretaJson = "{\n" + "      \"name\": \"barreta\",\n"
		    + "      \"gender\": \"female\",\n"
		    + "      \"number\": \"singular\",\n"
		    + "			\"points\": \"100\",\n"
		    + "			\"weight\": \"10\",\n"
		    + "			\"type\": \"weapon\",\n"
		    + "      \"actions\": [\n" + "        \"usar\"\n" + "      ],\n"
		    + "      \"effects_over\": [\n" + "        \"npcs\",\n"
		    + "        \"self\",\n" + "        \"item\"\n" + "      ]\n"
		    + "    }",
	    rociadorJson = "{\n" + "      \"name\": \"rociador con cerveza de raiz\",\n"
		    + "      \"gender\": \"male\",\n"
		    + "      \"number\": \"singular\",\n"
		    + "			\"points\": \"100\",\n"
		    + "			\"weight\": \"10\",\n"
		    + "			\"type\": \"vanilla\",\n"
		    + "      \"actions\": [\n" + "        \"usar\"\n" + "      ],\n"
		    + "      \"effects_over\": [\n" + "        \"npcs\",\n"
		    + "        \"self\",\n" + "        \"item\"\n" + "      ]\n"
		    + "    }",
	    espejoJson = "{\n" + "      \"name\": \"espejo\",\n"
		    + "      \"gender\": \"male\",\n"
		    + "      \"number\": \"singular\",\n"
		    + "			\"points\": \"100\",\n"
		    + "			\"weight\": \"10\",\n"
		    + "			\"type\": \"vanilla\",\n"
		    + "      \"actions\": [\n" + "        \"usar\"\n" + "      ],\n"
		    + "      \"effects_over\": [\n" + "        \"npcs\",\n"
		    + "        \"self\",\n" + "        \"item\"\n" + "      ]\n"
		    + "    }";

    @Test
    void testExitosoHabitacion() {
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Room muelle = new Room(JsonParser.parseString(jsonMuelle));
	Sitio suelo = new Sitio(JsonParser.parseString(jsonSuelo));
	muelle.addSitio(suelo);
	j1.setHabitacionActual(muelle);
	MirarComando c1 = new MirarComando();
	assertEquals("Estas en un muelle.", c1.ejecutar(j1, ""));
	assertEquals("Estas en un muelle.", c1.ejecutar(j1, "alrededor"));
	assertEquals("Estas en un muelle.", c1.ejecutar(j1, "muelle"));
    }

    @Test
    void testExitosoNPC() {
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Room r1 = new Room(JsonParser.parseString(jsonMuelle));
	NPC pirata = new NPC(JsonParser.parseString(jsonPirata));
	MirarComando c1 = new MirarComando();

	r1.addNPC(pirata);
	j1.setHabitacionActual(r1);
	assertEquals("- '¡No puedes pasar!' El pirata fantasma no te dejará pasar",
		c1.ejecutar(j1, "pirata fantasma"));
    }

    @Test
    void testObjetivoInvalido() {
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Room r1 = new Room(JsonParser.parseString(jsonMuelle));
	NPC pirata = new NPC(JsonParser.parseString(jsonPirata));
	MirarComando c1 = new MirarComando();

	r1.addNPC(pirata);
	j1.setHabitacionActual(r1);
	assertEquals("espejo no existe", c1.ejecutar(j1, "espejo"));
    }

    @Test
    void testEnumeracionItems() {

	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Room muelle = new Room(JsonParser.parseString(jsonMuelle));

	Sitio suelo = new Sitio(JsonParser.parseString(jsonSuelo));
	Item barreta = new Item(JsonParser.parseString(barretaJson)),
		rociador = new Item(JsonParser.parseString(rociadorJson)),
		espejo = new Item(JsonParser.parseString(espejoJson));
	suelo.addItem(espejo);
	suelo.addItem(barreta);
	suelo.addItem(rociador);
	muelle.addSitio(suelo);

	j1.setHabitacionActual(muelle);
	MirarComando c1 = new MirarComando();
	assertEquals(
		"Estas en un muelle. En el suelo hay una barreta, un rociador con cerveza de raiz y un espejo.",
		c1.ejecutar(j1, ""));
    }
    
    @Test
    void testEnumeracionUnSoloItem() {
	
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Room muelle = new Room(JsonParser.parseString(jsonMuelle));
	
	Sitio suelo = new Sitio(JsonParser.parseString(jsonSuelo));
	Item barreta = new Item(JsonParser.parseString(barretaJson)),
		rociador = new Item(JsonParser.parseString(rociadorJson)),
		espejo = new Item(JsonParser.parseString(espejoJson));
	suelo.addItem(espejo);
	muelle.addSitio(suelo);
	
	j1.setHabitacionActual(muelle);
	MirarComando c1 = new MirarComando();
	assertEquals(
		"Estas en un muelle. En el suelo hay un espejo.",
		c1.ejecutar(j1, ""));
    }
    
    @Test
    void testHabitacionConNPC() {
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Room r1 = new Room(JsonParser.parseString(jsonMuelle));
	NPC pirata = new NPC(JsonParser.parseString(jsonPirata));
	MirarComando c1 = new MirarComando();

	r1.addNPC(pirata);
	j1.setHabitacionActual(r1);
	assertEquals("Estas en un muelle. Hay un pirata fantasma.",
		c1.ejecutar(j1, ""));
    }
    
    @Test
    void testHabitacionConDosNPC() {
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Room r1 = new Room(JsonParser.parseString(jsonMuelle));
	NPC pirata = new NPC(JsonParser.parseString(jsonPirata));
	NPC abeja = new NPC(JsonParser.parseString(jsonAbeja));
	MirarComando c1 = new MirarComando();
	
	r1.addNPC(pirata);
	r1.addNPC(abeja);
	j1.setHabitacionActual(r1);
	assertEquals("Estas en un muelle. Hay una abeja fantasma y un pirata fantasma.",
		c1.ejecutar(j1, ""));
    }

}
