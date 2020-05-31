package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import zork.AccionItem;
import zork.Item;
import zork.ObjetivoItem;
import zork.TipoItem;
import zork.input.json.ItemInputJson;

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
    	"        \"self\"\n" +
    	"      ]\n" + 
    	"    }";

    @Test
    void testItems() {
	Item i = new Item(new ItemInputJson(json));
	assertEquals("la barreta", i.toString());
    }

    @Test
    void testObjetosValidos() {
	Item i = new Item(new ItemInputJson(json));
	assertEquals(false, i.esObjetivoValido(ObjetivoItem.ITEM));
    }

    @Test
    void testUsosValidos() {
	Item i = new Item(new ItemInputJson(json));
	assertEquals(false, i.esUsoValido(AccionItem.DROP));
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
	Item i = new Item(new ItemInputJson(json));
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
	Item i = new Item(new ItemInputJson(json));
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
	Item i = new Item(new ItemInputJson(json));
	assertEquals("las barretas", i.toString());
    }
    
    @Test
    void testGetters() {
	Item i = new Item(new ItemInputJson(json));
	assertEquals("barreta", i.getNombre());
	assertEquals((Double) 10.0, i.getPeso());
	assertEquals(TipoItem.ARMA, i.getTipo());
	assertEquals(100, i.getPoints());
    }

}
