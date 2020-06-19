package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.*;

import zork.*;
import zork.endgame.*;
import zork.input.TriggerInput;
import zork.input.parametro.*;

class LocationEndGameTest {
    private Habitacion muelle, barrio;

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
    void testLocationEndGame() {
	Jugador jugador = new Jugador("Guybrush Threepwood");
	NPC npc = initNPC();
	Salida salida = new Salida(barrio);
	Narrador narrador = new Narrador(jugador);
	FinalJuego end = new HabitacionFinal(
		new FinalJuegoInputParametro("Has terminado el juego.", "barrio", null));
	muelle.addSalida(salida, Direccion.SUR);
	muelle.ponerObstaculo(npc, Direccion.SUR);
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
