package test.comandos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.Item;
import zork.Jugador;
import zork.comandos.Comando;
import zork.comandos.InventarioComando;

class ComandoInventarioTest {

    String jsonPlayer = "{\n" + " \"character\": \"Guybrush Threepwood\"  }";

    String jsonBarreta = " {\n" + "      \"name\": \"barreta\",\n" + "      \"gender\": \"female\",\n"
	    + "      \"number\": \"singular\",\n" + "			\"points\": \"100\",\n"
	    + "			\"weight\": \"10\",\n" + "			\"type\": \"weapon\",\n"
	    + "      \"actions\": [\n" + "        \"usar\"\n" + "      ],\n" + "      \"effects_over\": [\n"
	    + "        \"npcs\",\n" + "        \"self\",\n" + "        \"item\"\n" + "      ]\n" + "    }";
    String jsonEspejo = "{\n" + "      \"name\": \"espejo\",\n" + "      \"gender\": \"male\",\n"
	    + "      \"number\": \"singular\",\n" + "			\"points\": \"100\",\n"
	    + "			\"weight\": \"10\",\n" + "			\"type\": \"vanilla\",\n"
	    + "      \"actions\": [\n" + "        \"usar\"\n" + "      ],\n" + "      \"effects_over\": [\n"
	    + "        \"npcs\",\n" + "        \"self\",\n" + "        \"item\"\n" + "      ]\n" + "    }";

    @Test
    void testDosItems() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Item barreta = new Item(JsonParser.parseString(jsonBarreta));
	Item espejo = new Item(JsonParser.parseString(jsonEspejo));
	jugador.ponerItem(espejo);
	jugador.ponerItem(barreta);

	Comando com = new InventarioComando();
	assertEquals("Tienes una barreta y un espejo.", com.ejecutar(jugador, ""));
    }

    @Test
    void testInventarioVacio() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));

	Comando com = new InventarioComando();
	assertEquals("No tienes objetos en tu inventario.", com.ejecutar(jugador, ""));
    }

}
