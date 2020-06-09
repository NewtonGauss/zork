package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.*;
import zork.input.TriggerInput;
import zork.input.parametro.ItemInputParametro;
import zork.input.parametro.NPCInputParametro;
import zork.input.parametro.TriggerInputParametro;

class JugadorTest {

    String jsonPlayer = "{\n" + " \"character\": \"Guybrush Threepwood\"  }";

    String jsonRoom = "{\n" + " \"name\": \"muelle\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" ,\n"
	    + " \"description\": \"Estas en un muelle\" }";
    String jsonRoom2 = "{\n" + " \"name\": \"barrio\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" ,\n"
	    + " \"description\": \"Estas en un barrio\" }";

    private Item barreta;
    
    @BeforeEach
    void inicializarItems() {
	ItemInputParametro constructorItem = new ItemInputParametro("barreta");
	barreta = new Item(constructorItem);
    }

    @Test
    void testNombreNPC() {
	Jugador player = new Jugador(JsonParser.parseString(jsonPlayer));
	assertEquals("Guybrush Threepwood", player.getNombre());
    }

    @Test
    void testMovimientosPlayer() {
	Jugador player = new Jugador(JsonParser.parseString(jsonPlayer));
	assertEquals(0, player.getCantMovimientos());
	player.sumarMovimiento();
	assertEquals(1, player.getCantMovimientos());
    }

    @Test
    void testGetScore() {
	Jugador player = new Jugador(JsonParser.parseString(jsonPlayer));
	assertEquals(0, player.getPuntuacion());
    }

    @Test
    void testAgarrarYSoltarItems() {
	Personaje player = new Jugador(JsonParser.parseString(jsonPlayer));
	assertEquals(true, player.ponerItem(barreta));
	assertEquals(barreta, player.getItem(barreta.getNombre()));
	assertTrue(player.sacarItem(barreta.getNombre()));
	assertFalse(player.sacarItem(barreta.getNombre()));
    }

    @Test
    void testSet() {
	Habitacion r = new Habitacion(JsonParser.parseString(jsonRoom));
	Personaje jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	jugador.setHabitacionActual(r);
	assertEquals(r, jugador.getHabitacionActual());
    }

    @Test
    void testMover() {
	Habitacion room1 = new Habitacion(JsonParser.parseString(jsonRoom));
	Habitacion room2 = new Habitacion(JsonParser.parseString(jsonRoom2));
	NPC npc = initNPC();
	Salida salida = new Salida(room2);
	npc.setEnemigo(true);
	salida.addNPC(npc);
	room1.addSalida(salida, "norte");
	assertEquals(false, salida.isEnemigoDerrotado());
	npc.matar();
	assertEquals(true, salida.isEnemigoDerrotado());
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	jugador.setHabitacionActual(room1);
	assertTrue(jugador.mover("norte"));
	assertEquals(room2, jugador.getHabitacionActual());
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
    void testSalud() {
	Personaje jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	assertEquals(100, jugador.getSalud());
	jugador.restarSalud(10);
	assertEquals(90, jugador.getSalud());
	jugador.sumarSalud(5);
	assertEquals(95, jugador.getSalud());
	jugador.restarSalud(100);
	assertEquals(0, jugador.getSalud());
	jugador.sumarSalud(100);
	assertEquals(0, jugador.getSalud());
    }

}
