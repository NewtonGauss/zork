package test.comandos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.Habitacion;
import zork.Jugador;
import zork.NPC;
import zork.Salida;
import zork.TipoTrigger;
import zork.comandos.Comando;
import zork.comandos.IrComando;
import zork.input.TriggerInput;
import zork.input.parametro.NPCInputParametro;
import zork.input.parametro.TriggerInputParametro;

class IrComandoTest {

    String jsonMuelle = "{\n" + " \"name\": \"muelle\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" ,\n"
	    + " \"description\": \"Estas en un muelle\" }",
	    jsonBarrio = "{\n" + " \"name\": \"barrio\" ,\n" + " \"gender\": \"male\" ,\n"
		    + " \"number\": \"singular\" ,\n"
		    + " \"description\": \"Estas en un barrio\" }",
	    jsonPirata = "{\n" + "      \"name\": \"pirata fantasma\",\n"
		    + "      \"gender\": \"male\",\n"
		    + "      \"number\": \"singular\",\n"
		    + "      \"description\": \"- '¡No puedes pasar!' El pirata fantasma no te dejará pasar\",\n"
		    + "      \"talk\": \"¡No hay nada que me digas que me haga cambiar de opinión!\",\n"
		    + "			\"points\": \"100\",\n"
		    + "			\"enemy\": \"true\",\n"
		    + "			\"health\": \"100\",\n"
		    + "			\"inventory\": [],\n" + "      \"triggers\": [\n"
		    + "        {\n" + "          \"type\": \"item\",\n"
		    + "          \"thing\": \"rociador con cerveza de raiz\",\n"
		    + "          \"on_trigger\": \"- '¡Me encanta la cerveza de raiz!' El pirata fantasma se veía entusiasmado por tu ofrecimiento... sin embargo, cuando lo rociaste comenzó a desintegrarse. La mitad de arriba de su cuerpo se desvaneció, y las piernas inmediatamente echaron a correr.\",\n"
		    + "          \"after_trigger\": \"remove\"\n" + "        }\n"
		    + "      ]\n" + "    }",
	    jsonPlayer = "{\n" + " \"character\": \"Guybrush Threepwood\"  }";

    @Test
    void testSinObstaculo() {
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));

	Habitacion barrio = new Habitacion(JsonParser.parseString(jsonBarrio));
	Salida salidaBarrio = new Salida(barrio);
	muelle.addSalida(salidaBarrio, "norte");

	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	jugador.setHabitacionActual(muelle);

	Comando ir = new IrComando();

	assertEquals("Estas en un barrio", ir.ejecutar(jugador, "norte"));
    }

    @Test
    void testConObstaculo() {
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));

	Habitacion barrio = new Habitacion(JsonParser.parseString(jsonBarrio));
	Salida salidaBarrio = new Salida(barrio);
	muelle.addSalida(salidaBarrio, "norte");

	NPC pirata = initNPC();
	muelle.ponerObstaculo(pirata, "norte");

	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	jugador.setHabitacionActual(muelle);

	Comando ir = new IrComando();

	assertEquals("- '¡No puedes pasar!' El pirata fantasma no te dejará pasar",
		ir.ejecutar(jugador, "norte"));

	pirata.ejecutarTrigger(TipoTrigger.ITEM, "rociador con cerveza de raiz");
	assertEquals("Estas en un barrio", ir.ejecutar(jugador, "norte"));
    }
    
    private NPC initNPC() {
	NPCInputParametro input = new NPCInputParametro("pirata fantasma");
	input.setGender('m');
	input.setNumber('s');
	input.setDescripcion(
		"- '¡No puedes pasar!' El pirata fantasma no te dejará pasar");
	input.setCharla("¡No hay nada que me digas que me haga cambiar de opinión!");
	input.setEnemigo(true);
	TriggerInputParametro trigger = new TriggerInputParametro(TipoTrigger.ITEM);
	trigger.setAfterTrigger("remove");
	trigger.setMensaje(
		"- '¡Me encanta la cerveza de raiz!' El pirata fantasma se veía entusiasmado por tu ofrecimiento... sin embargo, cuando lo rociaste comenzó a desintegrarse. La mitad de arriba de su cuerpo se desvaneció, y las piernas inmediatamente echaron a correr.");
	trigger.setObjetoActivador("rociador con cerveza de raiz");
	input.setListaTriggers(new ArrayList<TriggerInput>(Arrays.asList(trigger)));
	return new NPC(input);
    }

    @Test
    void testSinSalida() {
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	jugador.setHabitacionActual(muelle);

	Comando ir = new IrComando();

	assertEquals("Hacia el norte no hay salida.", ir.ejecutar(jugador, "norte"));
	assertEquals("Hacia el este no hay salida.", ir.ejecutar(jugador, "este"));
	assertEquals("Hacia el oeste no hay salida.", ir.ejecutar(jugador, "oeste"));
	assertEquals("Hacia el sur no hay salida.", ir.ejecutar(jugador, "sur"));
	assertEquals("Hacia arriba no hay salida.", ir.ejecutar(jugador, "arriba"));
	assertEquals("Hacia abajo no hay salida.", ir.ejecutar(jugador, "abajo"));
    }

}
