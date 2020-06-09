package test.comandos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.*;
import zork.comandos.DarComando;
import zork.input.TriggerInput;
import zork.input.parametro.*;

class DarComandoTest {
    String jsonPlayer = "{\n" + " \"character\": \"Santi\"  }";
    String jsonRoom = "{\n" + " \"name\": \"muelle\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" ,\n"
	    + " \"description\": \"Estas en un muelle\" }";
    private Item itemDropeable;

    @BeforeEach
    void inicializarItem() {
	ItemInputParametro constructorItem = new ItemInputParametro("espada");
	constructorItem.setGender('f');
	constructorItem.setNumber('s');
	constructorItem.setAccionesValidas(
		new ArrayList<AccionItem>(Arrays.asList(AccionItem.DROP)));
	itemDropeable = new Item(constructorItem);
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
    void testExitoDarItem() {

	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion room = new Habitacion(JsonParser.parseString(jsonRoom));
	NPC pirata = initNPC();
	DarComando dcc = new DarComando();

	j1.ponerItem(itemDropeable);
	room.addNPC(pirata);
	j1.setHabitacionActual(room);
	assertEquals("Le diste la espada al pirata fantasma.",
		dcc.ejecutar(j1, itemDropeable.getNombre() + ":" + pirata.getNombre()));
    }

    @Test
    void testNoTenesItem() {
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion room = new Habitacion(JsonParser.parseString(jsonRoom));
	NPC npc = initNPC();
	DarComando dcc = new DarComando();
	room.addNPC(npc);
	j1.setHabitacionActual(room);
	assertEquals("No tienes espada en tu inventario.",
		dcc.ejecutar(j1, "espada:pirata fantasma"));
    }

    @Test
    void testNoEstaNpcEnRoom() {
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion room = new Habitacion(JsonParser.parseString(jsonRoom));
	DarComando dcc = new DarComando();

	j1.ponerItem(itemDropeable);
	j1.setHabitacionActual(room);
	assertEquals("Maxi Hiena no se encuentra en el muelle.",
		dcc.ejecutar(j1, "espada:Maxi Hiena"));
    }

    @Test
    void testNoExisteNpc() {
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion room = new Habitacion(JsonParser.parseString(jsonRoom));
	DarComando dcc = new DarComando();
	j1.ponerItem(itemDropeable);
	j1.setHabitacionActual(room);
	assertEquals("Topo Malvado no se encuentra en el muelle.",
		dcc.ejecutar(j1, "espada:Topo Malvado"));
    }
}
