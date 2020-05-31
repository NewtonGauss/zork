package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.Habitacion;
import zork.Jugador;
import zork.NPC;
import zork.Narrador;
import zork.endgame.AccionFinal;
import zork.endgame.FinalJuego;

class ActiondEndGameTest {

    String jsonPlayer = "{\n" + " \"character\": \"Guybrush Threepwood\"  }";

    String jsonMuelle = "{\n" + " \"name\": \"muelle\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" ,\n"
	    + " \"description\": \"Estas en un muelle\" }";

    String piratajson = "{\n" + "      \"name\": \"pirata fantasma\",\n"
	    + "      \"gender\": \"male\",\n" + "      \"number\": \"singular\",\n"
	    + "      \"description\": \"- '¡No puedes pasar!' El pirata fantasma no te dejará pasar\",\n"
	    + "      \"talk\": \"¡No hay nada que me digas que me haga cambiar de opinión!\",\n"
	    + "			\"points\": \"100\",\n"
	    + "			\"enemy\": \"true\",\n"
	    + "			\"health\": \"100\",\n"
	    + "			\"inventory\": [],\n" + "      \"triggers\": [\n"
	    + "        {\n" + "          \"type\": \"item\",\n"
	    + "          \"thing\": \"rociador con cerveza de raiz\",\n"
	    + "          \"on_trigger\": \"- '¡Me encanta la cerveza de raiz!' El pirata fantasma se veía entusiasmado por tu ofrecimiento... sin embargo, cuando lo rociaste comenzó a desintegrarse. La mitad de arriba de su cuerpo se desvaneció, y las piernas inmediatamente echaron a correr.\",\n"
	    + "          \"after_trigger\": \"remove\"\n" + "        }\n" + "      ]\n"
	    + "    }";
    
    String jsonActionEndGame = "{\n" + 
	    	"      \"condition\": \"action\",\n" + 
	    	"      \"action\": \"hablar\",\n" + 
	    	"      \"thing\": \"pirata fantasma\",\n" + 
	    	"      \"description\": \"Has terminado el juego.\"" + 
	    	"    }";

    
    @Test
    void testActionEndGame() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	NPC npc = new NPC(JsonParser.parseString(piratajson));
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	Narrador narrador = new Narrador(jugador);
	FinalJuego end = new AccionFinal(JsonParser.parseString(jsonActionEndGame));
	muelle.addNPC(npc);
	jugador.setHabitacionActual(muelle);
	narrador.addEndgame(end);
	assertEquals("Has terminado el juego.", narrador.ejecutar("hablar al pirata fantasma"));
    }

}
