package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.AccionItem;
import zork.Item;
import zork.Sitio;
import zork.input.parametro.ItemInputParametro;

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
    	"        }";
    private Item espejo, rociador, barreta;
    
    @BeforeEach
    void inicializarItem() {
	ItemInputParametro constructorItem = new ItemInputParametro("espejo");
	constructorItem.setGender('m');
	constructorItem.setNumber('s');
	constructorItem.setAccionesValidas(new ArrayList<AccionItem>(Arrays.asList(AccionItem.DROP)));
	espejo = new Item(constructorItem);
	
	constructorItem.setNombre("rociador con cerveza de raiz");
	rociador = new Item(constructorItem);
	
	constructorItem.setNombre("barreta");
	constructorItem.setGender('f');
	barreta = new Item(constructorItem);
    }

    @Test
    void testCreacion() {
	Sitio s = new Sitio(JsonParser.parseString(json));
	assertEquals("el suelo", s.toString());
    }
    
    @Test
    void testItems() {
	Sitio s = new Sitio(JsonParser.parseString(json));
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
