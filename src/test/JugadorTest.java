package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.Item;
import zork.Jugador;

class JugadorTest {

	String jsonPlayer = "{\n" + " \"character\": \"Guybrush Threepwood\"  }";

	String jsonItem = " {\n" + "      \"name\": \"barreta\",\n" + "      \"gender\": \"female\",\n"
			+ "      \"number\": \"singular\",\n" + "      \"actions\": [\n" + "        \"usar\"\n" + "      ],\n"
			+ "      \"effects_over\": [\n" + "        \"npcs\",\n" + "        \"self\",\n" + "        \"item\"\n"
			+ "      ]\n" + "    }";

	@Test // Testeo el constructor de Jugador
	void test() {
		Jugador player = new Jugador(JsonParser.parseString(jsonPlayer));
		assertEquals("Guybrush Threepwood", player.getName());
	}

	@Test // Testeo sumar la cantidad de movimientos del jugador
	void test1() {
		Jugador player = new Jugador(JsonParser.parseString(jsonPlayer));
		assertEquals(0, player.getCantMovimientos());
		player.sumarMovimiento();
		assertEquals(1, player.getCantMovimientos());
	}

	@Test // Testeo obtener el score del jugador
	void test2() {
		Jugador player = new Jugador(JsonParser.parseString(jsonPlayer));
		assertEquals(0, player.getScore());
	}

	@Test // Le agrego un item al jugador y  lo devuelvo.
	void test3() {
		Jugador player = new Jugador(JsonParser.parseString(jsonPlayer));
		Item item = new Item(JsonParser.parseString(jsonItem));
		assertEquals(true, player.addItem(item));
		Iterator<Item> i = player.getItems();
		while(i.hasNext()) {
			Item aux = i.next();
			assertEquals("barreta", aux.getNombre());
		}
	}

}
