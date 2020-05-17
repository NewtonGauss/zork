package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.Item;

class ItemTest {
    private String json = " {\n" + "      \"name\": \"barreta\",\n" + "      \"gender\": \"female\",\n"
	    + "      \"number\": \"singular\",\n" + "\t\t\t\"weight\": \"10\",\n" + "\t\t\t\"type\": \"weapon\",\n" 
    	+"      \"actions\": [\n" + "        \"usar\"\n" + "      ],\n"
	    + "      \"effects_over\": [\n" + "        \"npcs\",\n" + "        \"self\",\n" + "        \"item\"\n"
	    + "      ]\n" + "    }";

    @Test
    void test01() {
	Item i = new Item(JsonParser.parseString(json));
	assertEquals("la barreta", i.toString());
    }

    @Test
    void test02() {
	Item i = new Item(JsonParser.parseString(json));
	assertEquals(false, i.esObjetivoValido("room"));
    }

    @Test
    void test03() {
	Item i = new Item(JsonParser.parseString(json));
	assertEquals(false, i.esUsoValido("atacar"));
    }   

}
