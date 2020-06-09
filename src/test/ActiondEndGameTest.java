package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.*;

import zork.endgame.AccionFinal;
import zork.endgame.FinalJuego;
import zork.input.TriggerInput;
import zork.input.parametro.NPCInputParametro;
import zork.input.parametro.TriggerInputParametro;

class ActiondEndGameTest {

    String jsonPlayer = "{\n" + " \"character\": \"Guybrush Threepwood\"  }";

    String jsonMuelle = "{\n" + " \"name\": \"muelle\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" ,\n"
	    + " \"description\": \"Estas en un muelle\" }";
    String jsonActionEndGame = "{\n" + "      \"condition\": \"action\",\n"
	    + "      \"action\": \"talk\",\n" + "      \"thing\": \"pirata fantasma\",\n"
	    + "      \"description\": \"Has terminado el juego.\"" + "    }";

    private static NPC pirata;

    @BeforeEach
    void initNPC() {
	NPCInputParametro input = new NPCInputParametro("pirata fantasma");
	input.setGender('m');
	input.setNumber('s');
	input.setDescripcion("- '¡No puedes pasar!' El pirata fantasma no te dejará pasar");
	input.setCharla("¡No hay nada que me digas que me haga cambiar de opinión!");
	input.setEnemigo(true);
	TriggerInputParametro trigger = new TriggerInputParametro(TipoTrigger.ITEM);
	trigger.setAfterTrigger("remove");
	trigger.setMensaje("- '¡Me encanta la cerveza de raiz!' El pirata fantasma se veía entusiasmado por tu ofrecimiento... sin embargo, cuando lo rociaste comenzó a desintegrarse. La mitad de arriba de su cuerpo se desvaneció, y las piernas inmediatamente echaron a correr.");
	trigger.setObjetoActivador("rociador con cerveza de raiz");
	input.setListaTriggers(new ArrayList<TriggerInput>(Arrays.asList(trigger)));
	pirata = new NPC(input);
    }

    @Test
    void testActionEndGame() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	Narrador narrador = new Narrador(jugador);
	FinalJuego end = new AccionFinal(JsonParser.parseString(jsonActionEndGame));
	muelle.addNPC(pirata);
	jugador.setHabitacionActual(muelle);
	narrador.addEndgame(end);
	assertEquals("Has terminado el juego.",
		narrador.ejecutar("hablar al pirata fantasma"));
    }

}
