package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.Item;
import zork.Jugador;
import zork.Room;
import zork.Sitio;
import zork.SoltarComando;

class SoltarComandoTest {

    private String jsonSitio = "{\n" + "          \"name\": \"suelo\",\n"
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
		    + "    }",

	    jsonRoom = "{\n" + " \"name\": \"muelle\" ,\n" + " \"gender\": \"male\" ,\n"
		    + " \"number\": \"singular\" ,\n"
		    + " \"description\": \"Estas en un muelle\" }",
	    jsonPlayer = "{\n" + " \"character\": \"Guybrush Threepwood\"  }";

    @Test
    void test() {
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Room room = new Room(JsonParser.parseString(jsonRoom));
	Sitio sitio = new Sitio(JsonParser.parseString(jsonSitio));
	Item item1 = new Item(JsonParser.parseString(barretaJson));
	Item item2 = new Item(JsonParser.parseString(espejoJson));
	Item item3 = new Item(JsonParser.parseString(rociadorJson));
	SoltarComando sc = new SoltarComando();
	
	j1.addItem(item1);
	j1.addItem(item2);
	j1.addItem(item3);
	room.addSitio(sitio);
	j1.setHabitacionActual(room);
	
	assertEquals("Se solto el item " + item1.getNombre() +" en el suelo", sc.ejecutar(j1, "barreta"));

	Iterator<Item> i = j1.getHabitacionActual().getSitio(sitio.getNombre()).getItems().iterator();
	while(i.hasNext()) {
	    System.out.println(i.next().toString());
	    
	    
	}
	
	System.out.println("\nDPS DE SOLTAR EL SEGUNDO ITEM\n");
	
	assertEquals("Se solto el item " + item2.getNombre() +" en el suelo", sc.ejecutar(j1, "espejo"));

	Iterator<Item> i2 = j1.getHabitacionActual().getSitio(sitio.getNombre()).getItems().iterator();
	while(i2.hasNext()) {
	    System.out.println(i2.next().toString());
	    
	    
	}
    }

}
