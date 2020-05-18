package test;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.AtacarConComando;
import zork.Item;
import zork.Jugador;
import zork.NPC;
import zork.Room;

class AtacarComandoTest {

	
	String jsonPlayer = "{\n" + " \"character\": \"Santi\"  }";
	String jsonRoom =  "{\n"
			+ " \"name\": \"muelle\" ,\n"
		    + " \"gender\": \"male\" ,\n"
		    + " \"number\": \"singular\" ,\n"
		    + " \"description\": \"Estas en un muelle\" }" ;
	
	String jsonNPC =  "{\n"
		      + "\"name\": \"Maxi Hiena\" , \n"
		      + "\"gender\": \"male\" , \n"
		      + "\"number\": \"singular\" , \n"
		      + "\"description\": \"Aqui no puedes pasar! El pirata fantasma no te dejara pasar\" , \n"
		      + "\"points\": \"100\" , \n"
		      + "\"enemy\": \"true\" , \n"
		      + "\"health\": \"100\" , \n"
		      + "\"talk\": \"No hay nada que me digas que me haga cambiar de opinion!\", \n"
			  + "\"triggers\": [{"
			  + " \"type\": \"attack\" ,\n"
			  + " \"thing\": \"espada\" ,\n"
			  + " \"on_trigger\": \"Uhhh me rompiste la gorra\" ,\n"
			  + " \"after_trigger\": \"kill\" } ] }" ;
	
	String jsonItem = "{\n" + 
		    	"      \"name\": \"espada\",\n" + 
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
	void test() {
		
			

			Jugador j1  = new Jugador(JsonParser.parseString(jsonPlayer));
			Room room = new Room(JsonParser.parseString(jsonRoom));
			NPC npc = new NPC(JsonParser.parseString(jsonNPC));
			Item item = new Item(JsonParser.parseString(jsonItem));
			j1.addItem(item);
			AtacarConComando acc = new AtacarConComando();
			room.addNPC(npc);
			j1.setHabitacionActual(room);
			
			System.out.println(acc.ejecutar(j1, item.getNombre()+":"+npc.getName()));
			
			
			
		}	

}
