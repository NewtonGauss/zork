package test.comandos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.Jugador;
import zork.NPC;
import zork.Room;
import zork.comandos.HablarComando;

class HablarComandoTest {

    String jsonPlayer = "{\n" + " \"character\": \"Guybrush Threepwood\"  }";

    String jsonMuelle = "{\n" + " \"name\": \"muelle\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" ,\n"
	    + " \"description\": \"Estas en un muelle\" }";

    String jsonPirata = "{\n" + "      \"name\": \"pirata fantasma\",\n"
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

    @Test
    void testNpcPresente() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Room muelle = new Room(JsonParser.parseString(jsonMuelle));
	NPC pirata = new NPC(JsonParser.parseString(jsonPirata));
	HablarComando hc = new HablarComando();
	
	muelle.addNPC(pirata);
	jugador.setHabitacionActual(muelle);
	
	assertEquals(pirata.hablar(), hc.ejecutar(jugador, "pirata fantasma"));
    }
    
    @Test
    void testNpcAusente() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Room muelle = new Room(JsonParser.parseString(jsonMuelle));
	NPC pirata = new NPC(JsonParser.parseString(jsonPirata));
	HablarComando hc = new HablarComando();
	
	muelle.addNPC(pirata);
	jugador.setHabitacionActual(muelle);
	
	assertEquals("pirata no se encuentra en el muelle.", hc.ejecutar(jugador, "pirata"));
    }

}
