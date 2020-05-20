package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.HablarComando;
import zork.Jugador;
import zork.NPC;
import zork.Room;

class HablarComandoTest {

    
    String jsonPlayer = "{\n" + " \"character\": \"Guybrush Threepwood\"  }";
    
    String jsonRoom =  "{\n"
		+ " \"name\": \"muelle\" ,\n"
	    + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" ,\n"
	    + " \"description\": \"Estas en un muelle\" }" ;
    
    String jsonNPC =  "{\n" + 
	 	"      \"name\": \"pirata fantasma\",\n" + 
	 	"      \"gender\": \"male\",\n" + 
	 	"      \"number\": \"singular\",\n" + 
	 	"      \"description\": \"- '¡No puedes pasar!' El pirata fantasma no te dejará pasar\",\n" + 
	 	"      \"talk\": \"¡No hay nada que me digas que me haga cambiar de opinión!\",\n" + 
	 	"			\"points\": \"100\",\n" + 
	 	"			\"enemy\": \"true\",\n" + 
	 	"			\"health\": \"100\",\n" + 
	 	"			\"inventory\": [],\n" + 
	 	"      \"triggers\": [\n" + 
	 	"        {\n" + 
	 	"          \"type\": \"item\",\n" + 
	 	"          \"thing\": \"rociador con cerveza de raiz\",\n" + 
	 	"          \"on_trigger\": \"- '¡Me encanta la cerveza de raiz!' El pirata fantasma se veía entusiasmado por tu ofrecimiento... sin embargo, cuando lo rociaste comenzó a desintegrarse. La mitad de arriba de su cuerpo se desvaneció, y las piernas inmediatamente echaron a correr.\",\n" + 
	 	"          \"after_trigger\": \"remove\"\n" + 
	 	"        }\n" + 
	 	"      ]\n" + 
	 	"    }";
    @Test
    void test() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Room room = new Room(JsonParser.parseString(jsonRoom));
	NPC npc = new NPC(JsonParser.parseString(jsonNPC));
	HablarComando hc = new HablarComando();
	room.addNPC(npc);
	jugador.setHabitacionActual(room);
	assertEquals(npc.hablar(), hc.ejecutar(jugador, "pirata fantasma"));
	
	System.out.println(hc.ejecutar(jugador, "pirata fantasma"));
	
	assertEquals(npc.hablar(), hc.ejecutar(jugador, "pirata"));
	System.out.println("\n" + hc.ejecutar(jugador, "pirata"));
    }

}
