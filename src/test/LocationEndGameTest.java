package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.Habitacion;
import zork.Jugador;
import zork.NPC;
import zork.Narrador;
import zork.Salida;
import zork.TipoTrigger;
import zork.endgame.FinalJuego;
import zork.endgame.HabitacionFinal;
import zork.input.TriggerInput;
import zork.input.parametro.NPCInputParametro;
import zork.input.parametro.TriggerInputParametro;

class LocationEndGameTest {
    String jsonMuelle = "{\n" + " \"name\": \"muelle\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" ,\n"
	    + " \"description\": \"Estas en un muelle\" }";

    String jsonBarrio = "{\n" + " \"name\": \"barrio\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" ,\n"
	    + " \"description\": \"Estas en un barrio\" }";
    
    String jsonLocationEndGame = "{\n" + 
	    	"      \"condition\": \"location\",\n" + 
	    	"      \"action\": \"hablar\",\n" + 
	    	"      \"thing\": \"barrio\",\n" + 
	    	"      \"description\": \"Has terminado el juego.\"" + 
	    	"    }";

    
    @Test
    void testLocationEndGame() {
	Jugador jugador = new Jugador("Guybrush Threepwood");
	NPC npc = initNPC();
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	Habitacion barrio = new Habitacion(JsonParser.parseString(jsonBarrio));
	Salida salida = new Salida(barrio);
	Narrador narrador = new Narrador(jugador);
	FinalJuego end = new HabitacionFinal(JsonParser.parseString(jsonLocationEndGame));
	muelle.addSalida(salida, "sur");
	muelle.ponerObstaculo(npc, "sur");
	jugador.setHabitacionActual(muelle);
	narrador.addEndgame(end);
	npc.matar();
	assertEquals("Has terminado el juego.", narrador.ejecutar("ir al sur"));
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

}
