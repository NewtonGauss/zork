package test.comandos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.*;

import zork.*;
import zork.comandos.HablarComando;
import zork.input.TriggerInput;
import zork.input.parametro.*;

class HablarComandoTest {
    private NPC pirata;
    private Habitacion muelle;

    @BeforeEach
    void initNPC() {
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
	pirata = new NPC(input);
    }
    
    @BeforeEach
    void initHabitacion() {
	muelle = new Habitacion(new HabitacionInputParametro("muelle",
		"Estas en un muelle"));
    }

    @Test
    void testNpcPresente() {
	Jugador jugador = new Jugador("Guybrush Threepwood");
	HablarComando hc = new HablarComando();
	
	muelle.addNPC(pirata);
	jugador.setHabitacionActual(muelle);
	
	assertEquals(pirata.hablar(), hc.ejecutar(jugador, "pirata fantasma"));
    }
    
    @Test
    void testNpcAusente() {
	Jugador jugador = new Jugador("Guybrush Threepwood");
	HablarComando hc = new HablarComando();
	
	muelle.addNPC(pirata);
	jugador.setHabitacionActual(muelle);
	
	assertEquals("pirata no se encuentra en el muelle.", hc.ejecutar(jugador, "pirata"));
    }

}
