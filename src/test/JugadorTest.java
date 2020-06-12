package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.*;

import zork.*;
import zork.input.TriggerInput;
import zork.input.parametro.*;

class JugadorTest {
    private Item barreta;
    private Habitacion muelle, barrio;
    
    @BeforeEach
    void inicializarItems() {
	ItemInputParametro constructorItem = new ItemInputParametro("barreta");
	barreta = new Item(constructorItem);
    }
    
    @BeforeEach
    void initHabitaciones() {
	HabitacionInputParametro hab = new HabitacionInputParametro("muelle",
		"Estas en un muelle");
	muelle = new Habitacion(hab);
	hab.setNombre("barrio");
	hab.setDescripcion("Estas en un barrio");
	barrio = new Habitacion(hab);
    }

    @Test
    void testNombreNPC() {
	Jugador player = new Jugador("Guybrush Threepwood");
	assertEquals("Guybrush Threepwood", player.getNombre());
    }

    @Test
    void testMovimientosPlayer() {
	Jugador player = new Jugador("Guybrush Threepwood");
	assertEquals(0, player.getCantMovimientos());
	player.sumarMovimiento();
	assertEquals(1, player.getCantMovimientos());
    }

    @Test
    void testGetScore() {
	Jugador player = new Jugador("Guybrush Threepwood");
	assertEquals(0, player.getPuntuacion());
    }

    @Test
    void testAgarrarYSoltarItems() {
	Personaje player = new Jugador("Guybrush Threepwood");
	assertEquals(true, player.ponerItem(barreta));
	assertEquals(barreta, player.getItem(barreta.getNombre()));
	assertTrue(player.sacarItem(barreta.getNombre()));
	assertFalse(player.sacarItem(barreta.getNombre()));
    }

    @Test
    void testSet() {
	Personaje jugador = new Jugador("Guybrush Threepwood");
	jugador.setHabitacionActual(muelle);
	assertEquals(muelle, jugador.getHabitacionActual());
    }

    @Test
    void testMover() {
	NPC npc = initNPC();
	Salida salida = new Salida(barrio);
	npc.setEnemigo(true);
	salida.addNPC(npc);
	muelle.addSalida(salida, "norte");
	assertEquals(false, salida.isEnemigoDerrotado());
	npc.matar();
	assertEquals(true, salida.isEnemigoDerrotado());
	Jugador jugador = new Jugador("Guybrush Threepwood");
	jugador.setHabitacionActual(muelle);
	assertTrue(jugador.mover("norte"));
	assertEquals(barrio, jugador.getHabitacionActual());
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
	Personaje jugador = new Jugador("Guybrush Threepwood");
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
