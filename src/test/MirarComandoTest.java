package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.Jugador;
import zork.MirarComando;
import zork.NPC;
import zork.Room;

class MirarComandoTest {

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
    void testExitosoHabitacion() {
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Room muelle = new Room(JsonParser.parseString(jsonMuelle));
	j1.setHabitacionActual(muelle);
	MirarComando c1 = new MirarComando();
	assertEquals("Estas en un muelle", c1.ejecutar(j1, ""));
	assertEquals("Estas en un muelle", c1.ejecutar(j1, "alrededor"));
	assertEquals("Estas en un muelle", c1.ejecutar(j1, "muelle"));
    }

    @Test
    void testExitosoNPC() {
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Room r1 = new Room(JsonParser.parseString(jsonMuelle));
	NPC pirata = new NPC(JsonParser.parseString(jsonPirata));
	MirarComando c1 = new MirarComando();

	r1.addNPC(pirata);
	j1.setHabitacionActual(r1);
	assertEquals("- '¡No puedes pasar!' El pirata fantasma no te dejará pasar",
		c1.ejecutar(j1, "pirata fantasma"));
    }
    
    @Test
    void testObjetivoInvalido() {
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Room r1 = new Room(JsonParser.parseString(jsonMuelle));
	NPC pirata = new NPC(JsonParser.parseString(jsonPirata));
	MirarComando c1 = new MirarComando();
	
	r1.addNPC(pirata);
	j1.setHabitacionActual(r1);
	assertEquals("espejo no existe",
		c1.ejecutar(j1, "espejo"));
    }

}
