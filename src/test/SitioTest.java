package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.Item;
import zork.Sitio;
import zork.input.json.ItemInputJson;

class SitioTest {
    
    private String json = "{\n" + 
    	"          \"name\": \"suelo\",\n" + 
    	"          \"gender\": \"male\",\n" + 
    	"          \"number\": \"singular\",\n" + 
    	"          \"items\": [\n" + 
    	"            \"barreta\",\n" + 
    	"            \"rociador con cerveza de raiz\",\n" + 
    	"            \"espejo\"\n" + 
    	"          ]\n" + 
    	"        }",
    	barretaJson = "{\n" + 
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
    		"    }",
    	rociadorJson = "{\n" + 
    		"      \"name\": \"rociador con cerveza de raiz\",\n" + 
    		"      \"gender\": \"male\",\n" + 
    		"      \"number\": \"singular\",\n" + 
    		"			\"points\": \"100\",\n" + 
    		"			\"weight\": \"10\",\n" + 
    		"			\"type\": \"vanilla\",\n" +
    		"      \"actions\": [\n" + 
    		"        \"usar\"\n" + 
    		"      ],\n" + 
    		"      \"effects_over\": [\n" + 
    		"        \"npcs\",\n" + 
    		"        \"self\",\n" + 
    		"        \"item\"\n" + 
    		"      ]\n" + 
    		"    }",
    	espejoJson = "{\n" + 
    		"      \"name\": \"espejo\",\n" + 
    		"      \"gender\": \"male\",\n" + 
    		"      \"number\": \"singular\",\n" + 
    		"			\"points\": \"100\",\n" + 
    		"			\"weight\": \"10\",\n" + 
    		"			\"type\": \"vanilla\",\n" + 
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
    void testCreacion() {
	Sitio s = new Sitio(JsonParser.parseString(json));
	assertEquals("el suelo", s.toString());
    }
    
    @Test
    void testItems() {
	Sitio s = new Sitio(JsonParser.parseString(json));
	Item barreta = new Item(new ItemInputJson(barretaJson)),
		rociador = new Item(new ItemInputJson(rociadorJson)),
		espejo = new Item(new ItemInputJson(espejoJson));
	s.addItem(espejo);
	s.addItem(barreta);
	s.addItem(rociador);

	/* Me da una lista de items. Pruebo que esten */
	assertTrue(s.getItems().contains(barreta));
	assertTrue(s.getItems().contains(espejo));
	assertTrue(s.getItems().contains(rociador));
	
	/* get saca los items del mapa */
	assertEquals(barreta, s.getItem("barreta"));
	assertEquals(espejo, s.getItem("espejo"));
	assertEquals(rociador, s.getItem("rociador con cerveza de raiz"));
	
	/* una vez que salio no deberia estar */
	assertEquals(null, s.getItem("espejo"));
    }
    
    @Test
    void testMalePluralSitio() {
	String males = "{\n" + 
	    	"          \"name\": \"suelos\",\n" + 
	    	"          \"gender\": \"male\",\n" + 
	    	"          \"number\": \"plural\",\n" + 
	    	"          \"items\": [\n" + 
	    	"            \"barreta\",\n" + 
	    	"            \"rociador con cerveza de raiz\",\n" + 
	    	"            \"espejo\"\n" + 
	    	"          ]\n" + 
	    	"        }";
	Sitio s = new Sitio(JsonParser.parseString(males));
	assertEquals("los suelos", s.toString());
    }
    
    @Test
    void testFemalePluralSitio() {
	String females = "{\n" + 
	    	"          \"name\": \"ventanas\",\n" + 
	    	"          \"gender\": \"female\",\n" + 
	    	"          \"number\": \"plural\",\n" + 
	    	"          \"items\": [\n" + 
	    	"            \"barreta\",\n" + 
	    	"            \"rociador con cerveza de raiz\",\n" + 
	    	"            \"espejo\"\n" + 
	    	"          ]\n" + 
	    	"        }";
	Sitio s = new Sitio(JsonParser.parseString(females));
	assertEquals("las ventanas", s.toString());
    }
    
    @Test
    void testFemaleSitio() {
	String female = "{\n" + 
	    	"          \"name\": \"ventana\",\n" + 
	    	"          \"gender\": \"female\",\n" + 
	    	"          \"number\": \"singular\",\n" + 
	    	"          \"items\": [\n" + 
	    	"            \"barreta\",\n" + 
	    	"            \"rociador con cerveza de raiz\",\n" + 
	    	"            \"espejo\"\n" + 
	    	"          ]\n" + 
	    	"        }";
	Sitio s = new Sitio(JsonParser.parseString(female));
	assertEquals("la ventana", s.toString());
    }
    
}
