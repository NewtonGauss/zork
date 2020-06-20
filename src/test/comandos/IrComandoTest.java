package test.comandos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.*;

import zork.*;
import zork.comandos.*;
import zork.input.TriggerInput;
import zork.input.parametro.*;

class IrComandoTest {
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
    void testSinObstaculo() {
	Salida salidaBarrio = new Salida(barrio);
	muelle.addSalida(salidaBarrio, Direccion.NORTE);

	Jugador jugador = new Jugador("Guybrush Threepwood");
	jugador.setHabitacionActual(muelle);

	Comando ir = new IrComando();

	assertEquals("Estas en un barrio", ir.ejecutar(jugador, "norte"));
    }

    @Test
    void testConObstaculo() {
	Salida salidaBarrio = new Salida(barrio);
	muelle.addSalida(salidaBarrio, Direccion.NORTE);

	NPC pirata = initNPC();
	muelle.addObstaculo(pirata, Direccion.NORTE);

	Jugador jugador = new Jugador("Guybrush Threepwood");
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
	Jugador jugador = new Jugador("Guybrush Threepwood");
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
