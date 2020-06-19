package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.*;

import zork.*;
import zork.input.TriggerInput;
import zork.input.parametro.*;

class SalidaTest {
    private NPC npc, npcDefeat;
    private Habitacion muelle, barrio;

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
	npc = new NPC(input);
	trigger.setAfterTrigger("defeat");
	input.setListaTriggers(new ArrayList<TriggerInput>(Arrays.asList(trigger)));
	npcDefeat = new NPC(input);
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

    @Test // PRUEBO GENERAR UN ROOM, LE AGREGO UN NPC Y ME FIJO SI FUNCIONA EL METODO
	  // ISENEMYDEFEAT.
    void test1() {
	Salida salida = new Salida(barrio);
	npc.setEnemigo(true);
	salida.addNPC(npc);
	muelle.addSalida(salida, Direccion.SUR);
	assertEquals(false, salida.isEnemigoDerrotado());
	npc.matar();
	assertEquals(true, salida.isEnemigoDerrotado());
    }

    @Test // PRUEBO GENERAR UN ROOM, LE AGREGO UNA SALIDA CON UN NPC Y ME FIJO SI
	  // DERROTAMOS AL NPC.
    void test2() {
	Salida salida = new Salida(barrio);
	npc.setEnemigo(true);
	salida.addNPC(npc);
	muelle.addSalida(salida, Direccion.SUR);
	assertEquals(barrio.getNombre(), muelle.getSalida(Direccion.SUR).getNombre());
	assertEquals(false, muelle.getSalida(Direccion.SUR).isEnemigoDerrotado());
	npc.matar();
	assertEquals(true, muelle.getSalida(Direccion.SUR).isEnemigoDerrotado());
    }

    /*
     * Debido a que existe un trigger que remueve a un npc de una habitacion, pruebo
     * si al hacer esto efectivamente me indica que el enemigo ha sido derrotado
     */
    @Test
    void testRemove() {
	Salida salida = new Salida(barrio);
	npc.setEnemigo(true);
	salida.addNPC(npc);
	assertEquals(false, salida.isEnemigoDerrotado());
	salida.sacarNPC(npc.getNombre());
	assertEquals(true, salida.isEnemigoDerrotado());
    }

    /*
     * Pruebo una ejecucion comun
     */
    @Test
    void testComun() {
	Salida salida = new Salida(barrio);
	muelle.addSalida(salida, Direccion.NORTE);
	muelle.ponerObstaculo(npc, Direccion.NORTE);
	assertEquals(false, muelle.getSalida(Direccion.NORTE).isEnemigoDerrotado());
	npc.ejecutarTrigger(TipoTrigger.ITEM, "barreta");
	assertFalse(muelle.getSalida(Direccion.NORTE).isEnemigoDerrotado());
	npc.ejecutarTrigger(TipoTrigger.ITEM, "rociador con cerveza de raiz");
	assertTrue(muelle.getSalida(Direccion.NORTE).isEnemigoDerrotado());
	assertEquals(barrio, muelle.getSalida(Direccion.NORTE).getHabitacion());
    }

    /*
     * Pruebo una ejecucion comun con defeat en vez de remove
     */
    @Test
    void testComunDefeat() {
	Salida salida = new Salida(barrio);
	muelle.addSalida(salida, Direccion.NORTE);
	muelle.ponerObstaculo(npcDefeat, Direccion.NORTE);
	assertEquals(false, muelle.getSalida(Direccion.NORTE).isEnemigoDerrotado());
	npcDefeat.ejecutarTrigger(TipoTrigger.ITEM, "rociador con cerveza de raiz");
	assertTrue(muelle.getSalida(Direccion.NORTE).isEnemigoDerrotado());
	assertEquals(barrio, muelle.getSalida(Direccion.NORTE).getHabitacion());
    }

}
