package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.*;

import zork.*;
import zork.input.TriggerInput;
import zork.input.parametro.*;

class HabitacionTest {
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

    @Test // Pruebo la creacion de la habitacion
    void testDescripcion() {
	assertEquals("el muelle", muelle.toString());
    }

    @Test // Pruebo agregando Salida...
    void testSalidas() {
	Salida salida = new Salida(barrio);
	muelle.addSalida(salida, Direccion.SUR);
	assertEquals(salida.getNombre(), muelle.getSalida(Direccion.SUR).getNombre());
    }

    @Test // Pruebo agregando Sitio...
    void testSitios() {
	Sitio sitio = new Sitio(new SitioInputParametro("ventana", 'f', 's'));
	muelle.addSitio(sitio);
	assertEquals("ventana", muelle.getSitio("ventana").getNombre());
    }

    @Test // Pruebo obtener el item de un determinado sitio
    void testItemsENSitios() {
	Sitio sitio = new Sitio();
	Item barreta = inicializarItems();
	sitio.addItem(barreta);
	muelle.addSitio(sitio);
	assertEquals(barreta, muelle.getSitio("suelo").getItem("barreta"));
    }

    private Item inicializarItems() {
	ItemInputParametro constructorItem = new ItemInputParametro("barreta");
	return new Item(constructorItem);
    }

    /*
     * Pruebo que me encuentre todos los npcs
     */
    @Test
    void testNPCs() {
	NPC npc = initNPC();
	muelle.addNPC(npc);
	assertEquals(npc, muelle.getNPC(npc.getNombre()));
	assertEquals(null, muelle.getNPC("Newton"));
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
