package test.comandos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.*;

import com.google.gson.JsonParser;

import zork.*;
import zork.comandos.UsarItemComando;
import zork.input.TriggerInput;
import zork.input.parametro.*;

class UsarItemComandoTest {
    String jsonMuelle = "{\n" + " \"name\": \"muelle\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" ,\n"
	    + " \"description\": \"Estas en un muelle\" }";

    private Item barreta, rociador, burbuja, calculadora, veneno;
    private NPC pirata, abeja;

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
	input.setListaTriggers(new ArrayList<TriggerInput>());
	input.setNombre("abeja fantasma");
	input.setGender('f');
	input.setNumber('s');
	abeja = new NPC(input);
    }

    @BeforeEach
    void inicializarItems() {
	ItemInputParametro constructorItem = new ItemInputParametro("barreta");
	constructorItem.setGender('f');
	constructorItem.setNumber('s');
	constructorItem.setAccionesValidas(
		new ArrayList<AccionItem>(Arrays.asList(AccionItem.DROP)));
	constructorItem.setObjetivosValidos(new ArrayList<ObjetivoItem>(
		Arrays.asList(ObjetivoItem.NPCS, ObjetivoItem.SELF)));
	constructorItem.setTipo(TipoItem.ARMA);
	constructorItem.setSaludSumar(20f);
	barreta = new Item(constructorItem);

	constructorItem.setNombre("calculadora");
	constructorItem.setGender('m');
	constructorItem.setObjetivosValidos(
		new ArrayList<ObjetivoItem>(Arrays.asList(ObjetivoItem.NPCS)));
	constructorItem.setAccionesValidas(
		new ArrayList<AccionItem>(Arrays.asList(AccionItem.USE)));
	constructorItem.setTipo(TipoItem.POCION);
	calculadora = new Item(constructorItem);

	constructorItem.setNombre("rociador con cerveza de raiz");
	constructorItem.setObjetivosValidos(new ArrayList<ObjetivoItem>(
		Arrays.asList(ObjetivoItem.SELF, ObjetivoItem.NPCS)));
	rociador = new Item(constructorItem);

	constructorItem.setNombre("burbuja");
	constructorItem.setTipo(TipoItem.VANILLA);
	burbuja = new Item(constructorItem);

	constructorItem.setNombre("veneno");
	constructorItem.setTipo(TipoItem.VENENO);
	constructorItem.setObjetivosValidos(
		new ArrayList<ObjetivoItem>(Arrays.asList(ObjetivoItem.SELF)));
	veneno = new Item(constructorItem);
    }

    @Test
    void testNpcConTrigger() {
	Jugador jugador = new Jugador("Guybrush Threepwood");
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	UsarItemComando usar = new UsarItemComando();

	muelle.addNPC(pirata);
	jugador.setHabitacionActual(muelle);
	jugador.ponerItem(rociador);
	assertEquals(
		"Se utilizo el rociador con cerveza de raiz sobre el pirata fantasma.\n"
			+ "- '¡Me encanta la cerveza de raiz!' El pirata fantasma se veía"
			+ " entusiasmado por tu ofrecimiento... sin embargo, cuando lo rociaste"
			+ " comenzó a desintegrarse. La mitad de arriba de su cuerpo se desvaneció,"
			+ " y las piernas inmediatamente echaron a correr.",
		usar.ejecutar(jugador, "rociador con cerveza de raiz:pirata fantasma"));
	assertEquals(100, pirata.getSalud());
    }

    @Test
    void testObjetoInvalido() {
	Jugador jugador = new Jugador("Guybrush Threepwood");
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	UsarItemComando usar = new UsarItemComando();

	muelle.addNPC(abeja);
	jugador.setHabitacionActual(muelle);
	jugador.ponerItem(barreta);
	assertEquals("Eso no ha servido de nada.",
		usar.ejecutar(jugador, "barreta:abeja fantasma"));

    }

    @Test
    void testNpcSinTrigger() {
	Jugador jugador = new Jugador("Guybrush Threepwood");
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	UsarItemComando usar = new UsarItemComando();

	muelle.addNPC(abeja);
	jugador.setHabitacionActual(muelle);
	jugador.ponerItem(rociador);
	assertEquals(
		"Se utilizo el rociador con cerveza de raiz sobre la abeja fantasma.\n",
		usar.ejecutar(jugador, "rociador con cerveza de raiz:abeja fantasma"));
	assertEquals(120, abeja.getSalud());
    }

    @Test
    void testNpcNoSeEncuentra() {
	Jugador jugador = new Jugador("Guybrush Threepwood");
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	UsarItemComando usar = new UsarItemComando();

	muelle.addNPC(abeja);
	jugador.setHabitacionActual(muelle);
	jugador.ponerItem(rociador);
	assertEquals("abeja no se encuentra en el muelle.",
		usar.ejecutar(jugador, "rociador con cerveza de raiz:abeja"));
    }

    @Test
    void testObjetoNoAplicableEnNpc() {
	Jugador jugador = new Jugador("Guybrush Threepwood");
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	UsarItemComando usar = new UsarItemComando();

	muelle.addNPC(abeja);
	jugador.setHabitacionActual(muelle);
	jugador.ponerItem(veneno);
	assertEquals("Eso no ha servido de nada.",
		usar.ejecutar(jugador, "veneno:abeja fantasma"));
    }

    /*
     * No se puede usar por ser objeto vanilla, que no causa ningun trigger sobre el
     * npc
     */
    @Test
    void testVanillaSinTrigger() {
	Jugador jugador = new Jugador("Guybrush Threepwood");
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	UsarItemComando usar = new UsarItemComando();

	muelle.addNPC(abeja);
	jugador.setHabitacionActual(muelle);
	jugador.ponerItem(burbuja);
	assertEquals("Eso no ha servido de nada.",
		usar.ejecutar(jugador, "burbuja:abeja fantasma"));
    }

    /*
     * No se puede usar por ser objeto vanilla
     */
    @Test
    void testObjetoNoAplicableSobreJugador() {
	Jugador jugador = new Jugador("Guybrush Threepwood");
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	UsarItemComando usar = new UsarItemComando();

	muelle.addNPC(abeja);
	jugador.setHabitacionActual(muelle);
	jugador.ponerItem(burbuja);
	assertEquals("Eso no ha servido de nada.", usar.ejecutar(jugador, "burbuja"));
    }

    /*
     * No se puede usar por no tener opcion self aunque es una potion
     */
    @Test
    void testObjetoNoAplicableSobreJugador2() {
	Jugador jugador = new Jugador("Guybrush Threepwood");
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	UsarItemComando usar = new UsarItemComando();

	muelle.addNPC(abeja);
	jugador.setHabitacionActual(muelle);
	jugador.ponerItem(calculadora);
	assertEquals("Eso no ha servido de nada.", usar.ejecutar(jugador, "calculadora"));
    }

    @Test
    void testPocionSobreJugador() {
	Jugador jugador = new Jugador("Guybrush Threepwood");
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	UsarItemComando usar = new UsarItemComando();

	muelle.addNPC(abeja);
	jugador.setHabitacionActual(muelle);
	jugador.ponerItem(rociador);
	assertEquals("Se utilizo el rociador con cerveza de raiz sobre ti.",
		usar.ejecutar(jugador, "rociador con cerveza de raiz"));
    }

    @Test
    void testVenenoSobreJugador() {
	Jugador jugador = new Jugador("Guybrush Threepwood");
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	UsarItemComando usar = new UsarItemComando();

	muelle.addNPC(abeja);
	jugador.setHabitacionActual(muelle);
	jugador.ponerItem(veneno);
	assertEquals("Se utilizo el veneno sobre ti.", usar.ejecutar(jugador, "veneno"));
	assertEquals(80, jugador.getSalud());
    }

    @Test
    void testObjetoNoInventario() {
	Jugador jugador = new Jugador("Guybrush Threepwood");
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	UsarItemComando usar = new UsarItemComando();

	muelle.addNPC(abeja);
	jugador.setHabitacionActual(muelle);
	jugador.ponerItem(veneno);
	assertEquals("No cuenta con invento en el inventario.",
		usar.ejecutar(jugador, "invento"));
    }

}
