package test.comandos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.*;

import zork.*;
import zork.comandos.DarComando;
import zork.input.TriggerInput;
import zork.input.parametro.*;

class DarComandoTest {
    private Item itemDropeable;
    private Habitacion muelle;

    @BeforeEach
    void inicializarItem() {
	ItemInputParametro constructorItem = new ItemInputParametro("espada");
	constructorItem.setGender('f');
	constructorItem.setNumber('s');
	constructorItem.setAccionesValidas(
		new ArrayList<AccionItem>(Arrays.asList(AccionItem.DROP)));
	itemDropeable = new Item(constructorItem);
    }
    
    @BeforeEach
    void initHabitacion() {
	muelle = new Habitacion(new HabitacionInputParametro("muelle",
		"Estas en un muelle"));
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

	Jugador j1 = new Jugador("Guybrush Threepwood");
	NPC pirata = initNPC();
	DarComando dcc = new DarComando();

	j1.ponerItem(itemDropeable);
	muelle.addNPC(pirata);
	j1.setHabitacionActual(muelle);
	assertEquals("Le diste la espada al pirata fantasma.",
		dcc.ejecutar(j1, itemDropeable.getNombre() + ":" + pirata.getNombre()));
    }

    @Test
    void testNoTenesItem() {
	Jugador j1 = new Jugador("Guybrush Threepwood");
	NPC npc = initNPC();
	DarComando dcc = new DarComando();
	muelle.addNPC(npc);
	j1.setHabitacionActual(muelle);
	assertEquals("No tienes espada en tu inventario.",
		dcc.ejecutar(j1, "espada:pirata fantasma"));
    }

    @Test
    void testNoEstaNpcEnRoom() {
	Jugador j1 = new Jugador("Guybrush Threepwood");
	DarComando dcc = new DarComando();

	j1.ponerItem(itemDropeable);
	j1.setHabitacionActual(muelle);
	assertEquals("Maxi Hiena no se encuentra en el muelle.",
		dcc.ejecutar(j1, "espada:Maxi Hiena"));
    }

    @Test
    void testNoExisteNpc() {
	Jugador j1 = new Jugador("Guybrush Threepwood");
	DarComando dcc = new DarComando();
	j1.ponerItem(itemDropeable);
	j1.setHabitacionActual(muelle);
	assertEquals("Topo Malvado no se encuentra en el muelle.",
		dcc.ejecutar(j1, "espada:Topo Malvado"));
    }
}
