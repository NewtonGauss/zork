package test.comandos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.*;

import zork.*;
import zork.comandos.AtacarConComando;
import zork.input.TriggerInput;
import zork.input.parametro.*;

class AtacarComandoTest {
    private Item espada, espejo, canicas;
    private NPC pirata, pirataSinTrigger;
    private Habitacion muelle;

    @BeforeEach
    void inicializarItems() {
	ItemInputParametro constructor = new ItemInputParametro("espada");
	constructor.setTipo(TipoItem.ARMA);
	espada = new Item(constructor);

	constructor.setNombre("espejo");
	constructor.setTipo(TipoItem.VANILLA);
	espejo = new Item(constructor);

	constructor.setNombre("canicas");
	canicas = new Item(constructor);
    }

    @BeforeEach
    void initNPC() {
	NPCInputParametro input = new NPCInputParametro("pirata fantasma");
	input.setGender('m');
	input.setNumber('s');
	input.setDescripcion(
		"- '¡No puedes pasar!' El pirata fantasma no te dejará pasar");
	input.setCharla("¡No hay nada que me digas que me haga cambiar de opinión!");
	input.setEnemigo(true);
	TriggerInputParametro trigger = new TriggerInputParametro(TipoTrigger.ATAQUE);
	trigger.setAfterTrigger("nothing");
	trigger.setMensaje("eso no te sirve de nada");
	trigger.setObjetoActivador("espada");
	input.setListaTriggers(new ArrayList<TriggerInput>(Arrays.asList(trigger)));
	pirata = new NPC(input);
	input.setListaTriggers(new ArrayList<TriggerInput>());
	pirataSinTrigger = new NPC(input);
    }

    @BeforeEach
    void initHabitacion() {
	muelle = new Habitacion(
		new HabitacionInputParametro("muelle", "Estas en un muelle"));
    }

    @Test
    void testAtacarConComando() {
	Jugador j1 = new Jugador("Guybrush Threepwood");
	AtacarConComando acc = new AtacarConComando();

	j1.ponerItem(espada);
	muelle.addNPC(pirata);
	j1.setHabitacionActual(muelle);
	assertEquals("pirata fantasma: eso no te sirve de nada.",
		acc.ejecutar(j1, "pirata fantasma:espada"));
    }

    @Test
    void testEnemigoSinTrigger() {
	Jugador j1 = new Jugador("Indiana Jones");
	AtacarConComando acc = new AtacarConComando();

	j1.ponerItem(espada);
	muelle.addNPC(pirataSinTrigger);
	j1.setHabitacionActual(muelle);
	assertEquals("Atacaste al pirata fantasma con la espada.\n",
		acc.ejecutar(j1, "pirata fantasma:espada"));
    }

    @Test
    void testNpcInvalido() {
	Jugador j1 = new Jugador("Guybrush Threepwood");
	AtacarConComando acc = new AtacarConComando();

	j1.ponerItem(espada);
	muelle.addNPC(pirata);
	j1.setHabitacionActual(muelle);
	assertEquals("pirata no se encuentra en el muelle.",
		acc.ejecutar(j1, "pirata:" + espada.getNombre()));
    }

    @Test
    void testAtacarSinItems() {
	Jugador j1 = new Jugador("Guybrush Threepwood");
	AtacarConComando acc = new AtacarConComando();

	muelle.addNPC(pirata);
	j1.setHabitacionActual(muelle);
	assertEquals("No tiene armas para atacar. ¡Busque una!",
		acc.ejecutar(j1, pirata.getNombre() + ":manos"));
    }

    @Test
    void testAtacarSinArmasConItems() {
	Jugador j1 = new Jugador("Guybrush Threepwood");
	AtacarConComando acc = new AtacarConComando();

	j1.ponerItem(espejo);
	j1.ponerItem(canicas);
	muelle.addNPC(pirata);
	j1.setHabitacionActual(muelle);
	assertEquals("No tiene armas para atacar. ¡Busque una!",
		acc.ejecutar(j1, pirata.getNombre() + ":" + espejo.getNombre()));
    }

    @Test
    void testItemEquivocado() {
	Jugador j1 = new Jugador("Guybrush Threepwood");
	AtacarConComando acc = new AtacarConComando();

	j1.ponerItem(espejo);
	j1.ponerItem(canicas);
	j1.ponerItem(espada);
	muelle.addNPC(pirata);
	j1.setHabitacionActual(muelle);
	assertEquals("Utilice uno de los siguientes items para atacar:\nla espada",
		acc.ejecutar(j1, pirata.getNombre() + ":" + espejo.getNombre()));
    }

}
