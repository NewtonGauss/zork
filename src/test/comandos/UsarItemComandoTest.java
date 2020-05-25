package test.comandos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.Item;
import zork.Jugador;
import zork.NPC;
import zork.Room;
import zork.comandos.UsarItemComando;

class UsarItemComandoTest {
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
		    + "      \"gender\": \"female\",\n"
		    + "      \"number\": \"singular\",\n"
		    + "      \"description\": \"- '¡No puedes pasar!' El pirata fantasma no te dejará pasar\",\n"
		    + "      \"talk\": \"¡No hay nada que me digas que me haga cambiar de opinión!\",\n"
		    + "			\"points\": \"100\",\n"
		    + "			\"enemy\": \"true\",\n"
		    + "			\"health\": \"100\",\n"
		    + "			\"inventory\": [],\n" + "      \"triggers\": []\n"
		    + "    }",
	    barretaJson = "{\n" + "      \"name\": \"barreta\",\n"
		    + "      \"gender\": \"female\",\n"
		    + "      \"number\": \"singular\",\n"
		    + "			\"points\": \"100\",\n"
		    + "			\"weight\": \"10\",\n"
		    + "			\"type\": \"weapon\",\n"
		    + "      \"actions\": [\n" + "        \"dar\"\n" + "      ],\n"
		    + "      \"effects_over\": [\n" + "        \"npcs\",\n"
		    + "        \"self\",\n" + "        \"item\"\n" + "      ]\n"
		    + "    }",
	    rociadorJson = "{\n" + "      \"name\": \"rociador con cerveza de raiz\",\n"
		    + "      \"gender\": \"male\",\n"
		    + "      \"number\": \"singular\",\n"
		    + "			\"points\": \"100\",\n"
		    + "			\"weight\": \"10\",\n"
		    + "			\"type\": \"potion\",\n"
		    + "      \"actions\": [\n" + "        \"usar\"\n" + "      ],\n"
		    + "      \"effects_over\": [\n" + "        \"npcs\",\n"
		    + "        \"self\",\n" + "        \"item\"\n" + "      ]\n"
		    + "    }",
	    burbujaJson = "{\n" + "      \"name\": \"burbuja\",\n"
		    + "      \"gender\": \"male\",\n"
		    + "      \"number\": \"singular\",\n"
		    + "			\"points\": \"100\",\n"
		    + "			\"weight\": \"10\",\n"
		    + "			\"type\": \"vanilla\",\n"
		    + "      \"actions\": [\n" + "        \"usar\"\n" + "      ],\n"
		    + "      \"effects_over\": [\n" + "        \"npcs\",\n"
		    + "        \"self\",\n" + "        \"item\"\n" + "      ]\n"
		    + "    }",
	    calculadoraJson = "{\n" + "      \"name\": \"calculadora\",\n"
		    + "      \"gender\": \"male\",\n"
		    + "      \"number\": \"singular\",\n"
		    + "			\"points\": \"100\",\n"
		    + "			\"weight\": \"10\",\n"
		    + "			\"type\": \"potion\",\n"
		    + "      \"actions\": [\n" + "        \"usar\"\n" + "      ],\n"
		    + "      \"effects_over\": [\n" + "        \"npcs\",\n"
		    + "        \"item\"\n" + "      ]\n" + "    }",
	    venenoJson = "{\n" + "      \"name\": \"veneno\",\n"
		    + "      \"gender\": \"male\",\n"
		    + "      \"number\": \"singular\",\n"
		    + "			\"points\": \"100\",\n"
		    + "			\"weight\": \"10\",\n"
		    + "			\"type\": \"poison\",\n"
		    + "      \"actions\": [\n" + "        \"usar\"\n" + "      ],\n"
		    + "      \"effects_over\": [\n" + "        \"self\",\n"
		    + "        \"item\"\n" + "      ]\n" + "    }";

    @Test
    void testNpcConTrigger() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Room muelle = new Room(JsonParser.parseString(jsonMuelle));
	NPC pirata = new NPC(JsonParser.parseString(jsonPirata));
	Item rociador = new Item(JsonParser.parseString(rociadorJson));
	UsarItemComando usar = new UsarItemComando();

	muelle.addNPC(pirata);
	jugador.setHabitacionActual(muelle);
	jugador.addItem(rociador);
	assertEquals(
		"Se utilizo el rociador con cerveza de raiz sobre el pirata fantasma.\n"
			+ "- '¡Me encanta la cerveza de raiz!' El pirata fantasma se veía"
			+ " entusiasmado por tu ofrecimiento... sin embargo, cuando lo rociaste"
			+ " comenzó a desintegrarse. La mitad de arriba de su cuerpo se desvaneció,"
			+ " y las piernas inmediatamente echaron a correr.",
		usar.ejecutar(jugador, "rociador con cerveza de raiz:pirata fantasma"));
	assertEquals(100, pirata.getSalud());
    }

    @Test
    void testObjetoInvalido() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Room muelle = new Room(JsonParser.parseString(jsonMuelle));
	NPC abeja = new NPC(JsonParser.parseString(jsonAbeja));
	Item barreta = new Item(JsonParser.parseString(barretaJson));
	UsarItemComando usar = new UsarItemComando();

	muelle.addNPC(abeja);
	jugador.setHabitacionActual(muelle);
	jugador.addItem(barreta);
	assertEquals("Eso no ha servido de nada.",
		usar.ejecutar(jugador, "barreta:abeja fantasma"));

    }

    @Test
    void testNpcSinTrigger() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Room muelle = new Room(JsonParser.parseString(jsonMuelle));
	NPC abeja = new NPC(JsonParser.parseString(jsonAbeja));
	Item rociador = new Item(JsonParser.parseString(rociadorJson));
	UsarItemComando usar = new UsarItemComando();

	muelle.addNPC(abeja);
	jugador.setHabitacionActual(muelle);
	jugador.addItem(rociador);
	assertEquals(
		"Se utilizo el rociador con cerveza de raiz sobre la abeja fantasma.\n",
		usar.ejecutar(jugador, "rociador con cerveza de raiz:abeja fantasma"));
	/* valor de sumar salud hardcodeado */
	assertEquals(120, abeja.getSalud());
    }

    @Test
    void testNpcNoSeEncuentra() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Room muelle = new Room(JsonParser.parseString(jsonMuelle));
	NPC abeja = new NPC(JsonParser.parseString(jsonAbeja));
	Item rociador = new Item(JsonParser.parseString(rociadorJson));
	UsarItemComando usar = new UsarItemComando();

	muelle.addNPC(abeja);
	jugador.setHabitacionActual(muelle);
	jugador.addItem(rociador);
	assertEquals("abeja no se encuentra en el muelle.",
		usar.ejecutar(jugador, "rociador con cerveza de raiz:abeja"));
    }

    @Test
    void testObjetoNoAplicableEnNpc() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Room muelle = new Room(JsonParser.parseString(jsonMuelle));
	NPC abeja = new NPC(JsonParser.parseString(jsonAbeja));
	Item veneno = new Item(JsonParser.parseString(venenoJson));
	UsarItemComando usar = new UsarItemComando();

	muelle.addNPC(abeja);
	jugador.setHabitacionActual(muelle);
	jugador.addItem(veneno);
	assertEquals("Eso no ha servido de nada.",
		usar.ejecutar(jugador, "veneno:abeja fantasma"));
    }

    /*
     * No se puede usar por ser objeto vanilla, que no causa ningun trigger
     * sobre el npc
     */
    @Test
    void testVanillaSinTrigger() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Room muelle = new Room(JsonParser.parseString(jsonMuelle));
	NPC abeja = new NPC(JsonParser.parseString(jsonAbeja));
	Item burbuja = new Item(JsonParser.parseString(burbujaJson));
	UsarItemComando usar = new UsarItemComando();
	
	muelle.addNPC(abeja);
	jugador.setHabitacionActual(muelle);
	jugador.addItem(burbuja);
	assertEquals("Eso no ha servido de nada.", usar.ejecutar(jugador, "burbuja:abeja fantasma"));
    }
    
    /*
     * No se puede usar por ser objeto vanilla
     */
    @Test
    void testObjetoNoAplicableSobreJugador() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Room muelle = new Room(JsonParser.parseString(jsonMuelle));
	NPC abeja = new NPC(JsonParser.parseString(jsonAbeja));
	Item burbuja = new Item(JsonParser.parseString(burbujaJson));
	UsarItemComando usar = new UsarItemComando();

	muelle.addNPC(abeja);
	jugador.setHabitacionActual(muelle);
	jugador.addItem(burbuja);
	assertEquals("Eso no ha servido de nada.", usar.ejecutar(jugador, "burbuja"));
    }

    /*
     * No se puede usar por no tener opcion self aunque es una potion
     */
    @Test
    void testObjetoNoAplicableSobreJugador2() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Room muelle = new Room(JsonParser.parseString(jsonMuelle));
	NPC abeja = new NPC(JsonParser.parseString(jsonAbeja));
	Item calculadora = new Item(JsonParser.parseString(calculadoraJson));
	UsarItemComando usar = new UsarItemComando();

	muelle.addNPC(abeja);
	jugador.setHabitacionActual(muelle);
	jugador.addItem(calculadora);
	assertEquals("Eso no ha servido de nada.", usar.ejecutar(jugador, "calculadora"));
    }

    @Test
    void testPocionSobreJugador() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Room muelle = new Room(JsonParser.parseString(jsonMuelle));
	NPC abeja = new NPC(JsonParser.parseString(jsonAbeja));
	Item rociador = new Item(JsonParser.parseString(rociadorJson));
	UsarItemComando usar = new UsarItemComando();

	muelle.addNPC(abeja);
	jugador.setHabitacionActual(muelle);
	jugador.addItem(rociador);
	assertEquals("Se utilizo el rociador con cerveza de raiz sobre ti.",
		usar.ejecutar(jugador, "rociador con cerveza de raiz"));
    }
    
    @Test
    void testVenenoSobreJugador() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Room muelle = new Room(JsonParser.parseString(jsonMuelle));
	NPC abeja = new NPC(JsonParser.parseString(jsonAbeja));
	Item veneno = new Item(JsonParser.parseString(venenoJson));
	UsarItemComando usar = new UsarItemComando();
	
	muelle.addNPC(abeja);
	jugador.setHabitacionActual(muelle);
	jugador.addItem(veneno);
	assertEquals("Se utilizo el veneno sobre ti.",
		usar.ejecutar(jugador, "veneno"));
	/* Valor hardcodeado en character */
	assertEquals(85, jugador.getSalud());
    }
    
    @Test
    void testObjetoNoInventario() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Room muelle = new Room(JsonParser.parseString(jsonMuelle));
	NPC abeja = new NPC(JsonParser.parseString(jsonAbeja));
	Item veneno = new Item(JsonParser.parseString(venenoJson));
	UsarItemComando usar = new UsarItemComando();
	
	muelle.addNPC(abeja);
	jugador.setHabitacionActual(muelle);
	jugador.addItem(veneno);
	assertEquals("No cuenta con invento en el inventario.",
		usar.ejecutar(jugador, "invento"));
    }

}
