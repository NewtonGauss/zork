package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.Inventario;
import zork.Item;

class InventarioTest {
    private String jsonItem = " {\n" + "      \"name\": \"barreta\",\n" + "      \"gender\": \"female\",\n"
	    + "      \"number\": \"singular\",\n" + "      \"actions\": [\n" + "        \"usar\"\n" + "      ],\n"
	    + "      \"effects_over\": [\n" + "        \"npcs\",\n" + "        \"self\",\n" + "        \"item\"\n"
	    + "      ]\n" + "    }";

    @Test
    void test01() {
	Item item = new Item(JsonParser.parseString(jsonItem));
	jsonItem = jsonItem.replaceFirst("barreta", "barreta2");
	Item item2 = new Item(JsonParser.parseString(jsonItem));
	Inventario i = new Inventario(11d);
	assertTrue(i.addItem(item));
	assertFalse(i.addItem(item2));
    }

    @Test
    void test02() {
	Item item = new Item(JsonParser.parseString(jsonItem));
	jsonItem = jsonItem.replaceFirst("barreta", "barreta2");
	Item item2 = new Item(JsonParser.parseString(jsonItem));
	Inventario i = new Inventario(11d);
	i.addItem(item);
	i.removeItem(i.getItem("barreta"));
	assertTrue(i.addItem(item2));
    }
    
    @Test
    void test03() {
	Item item = new Item(JsonParser.parseString(jsonItem));
	jsonItem = jsonItem.replaceFirst("barreta", "barreta2");
	Item item2 = new Item(JsonParser.parseString(jsonItem));
	Inventario i = new Inventario(200d);
	i.addItem(item);
	i.addItem(item2);
	assertEquals("barreta", i.getItem("barreta").getNombre());
	assertEquals("barreta2", i.getItem("barreta2").getNombre());
    }

}
