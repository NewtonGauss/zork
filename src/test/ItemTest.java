package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.Item;

class ItemTest {
    private String json = "{\n" + 
    	"      \"name\": \"barreta\",\n" + 
    	"      \"gender\": \"female\",\n" + 
    	"      \"number\": \"singular\",\n" + 
    	"			\"points\": \"100\",\n" + 
    	"			\"weight\": \"10\",\n" + 
    	"			\"type\": \"weapon\",\n" + 
    	"      \"actions\": [\n" + 
    	"        \"usar\"\n" + 
    	"      ],\n" + 
    	"      \"effects_over\": [\n" + 
    	"        \"npcs\",\n" + 
    	"        \"self\",\n" + 
    	"        \"item\"\n" + 
    	"      ]\n" + 
    	"    }";

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
    
    @Test
    void testObjetoMale() {
	String json = "{\n" + 
	    	"      \"name\": \"palo\",\n" + 
	    	"      \"gender\": \"male\",\n" + 
	    	"      \"number\": \"singular\",\n" + 
	    	"			\"points\": \"100\",\n" + 
	    	"			\"weight\": \"10\",\n" + 
	    	"			\"type\": \"weapon\",\n" + 
	    	"      \"actions\": [\n" + 
	    	"        \"usar\"\n" + 
	    	"      ],\n" + 
	    	"      \"effects_over\": [\n" + 
	    	"        \"npcs\",\n" + 
	    	"        \"self\",\n" + 
	    	"        \"item\"\n" + 
	    	"      ]\n" + 
	    	"    }";
	Item i = new Item(JsonParser.parseString(json));
	assertEquals("el palo", i.toString());
    }
    
    @Test
    void testObjetoMalePlural() {
	String json = "{\n" + 
	    	"      \"name\": \"palos\",\n" + 
	    	"      \"gender\": \"male\",\n" + 
	    	"      \"number\": \"plural\",\n" + 
	    	"			\"points\": \"100\",\n" + 
	    	"			\"weight\": \"10\",\n" + 
	    	"			\"type\": \"weapon\",\n" + 
	    	"      \"actions\": [\n" + 
	    	"        \"usar\"\n" + 
	    	"      ],\n" + 
	    	"      \"effects_over\": [\n" + 
	    	"        \"npcs\",\n" + 
	    	"        \"self\",\n" + 
	    	"        \"item\"\n" + 
	    	"      ]\n" + 
	    	"    }";
	Item i = new Item(JsonParser.parseString(json));
	assertEquals("los palos", i.toString());
    }
    
    @Test
    void testObjetoFemalePlural() {
	String json = "{\n" + 
	    	"      \"name\": \"barretas\",\n" + 
	    	"      \"gender\": \"female\",\n" + 
	    	"      \"number\": \"plural\",\n" + 
	    	"			\"points\": \"100\",\n" + 
	    	"			\"weight\": \"10\",\n" + 
	    	"			\"type\": \"weapon\",\n" + 
	    	"      \"actions\": [\n" + 
	    	"        \"usar\"\n" + 
	    	"      ],\n" + 
	    	"      \"effects_over\": [\n" + 
	    	"        \"npcs\",\n" + 
	    	"        \"self\",\n" + 
	    	"        \"item\"\n" + 
	    	"      ]\n" + 
	    	"    }";
	Item i = new Item(JsonParser.parseString(json));
	assertEquals("las barretas", i.toString());
    }
    
    @Test
    void testGetters() {
	Item i = new Item(JsonParser.parseString(json));
	assertEquals("barreta", i.getNombre());
	assertEquals((Double) 10.0, i.getPeso());
	assertEquals("weapon", i.getTipo());
	assertEquals(100, i.getPoints());
    }

}
