package test.comandos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.AccionItem;
import zork.Habitacion;
import zork.Item;
import zork.Jugador;
import zork.NPC;
import zork.ObjetivoItem;
import zork.TipoItem;
import zork.comandos.UsarItemComando;
import zork.input.parametro.ItemInputParametro;

class UsarItemComandoTest {
    String jsonPlayer = "{\n" + " \"character\": \"Guybrush Threepwood\"  }";

    String jsonMuelle = "{\n" + " \"name\": \"muelle\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" ,\n"
	    + " \"description\": \"Estas en un muelle\" }";

    String jsonPirata = "{\n" + "      \"name\": \"pirata fantasma\",\n"
	    + "      \"gender\": \"male\",\n" + "      \"number\": \"singular\",\n"
	    + "      \"description\": \"- '¡No puedes pasar!' El pirata fantasma no te dejará pasar\",\n"
	    + "      \"talk\": \"¡No hay nada que me digas que me haga cambiar de opinión!\",\n"
	    + "			\"points\": \"100\",\n"
	    + "			\"enemy\": \"true\",\n"
	    + "			\"health\": \"100\",\n"
	    + "			\"inventory\": [],\n" + "      \"triggers\": [\n"
	    + "        {\n" + "          \"type\": \"item\",\n"
	    + "          \"thing\": \"rociador con cerveza de raiz\",\n"
	    + "          \"on_trigger\": \"- '¡Me encanta la cerveza de raiz!' El pirata fantasma se veía entusiasmado por tu ofrecimiento... sin embargo, cuando lo rociaste comenzó a desintegrarse. La mitad de arriba de su cuerpo se desvaneció, y las piernas inmediatamente echaron a correr.\",\n"
	    + "          \"after_trigger\": \"remove\"\n" + "        }\n" + "      ]\n"
	    + "    }",
	    jsonAbeja = "{\n" + "      \"name\": \"abeja fantasma\",\n"
		    + "      \"gender\": \"female\",\n"
		    + "      \"number\": \"singular\",\n"
		    + "      \"description\": \"- '¡No puedes pasar!' El pirata fantasma no te dejará pasar\",\n"
		    + "      \"talk\": \"¡No hay nada que me digas que me haga cambiar de opinión!\",\n"
		    + "			\"points\": \"100\",\n"
		    + "			\"enemy\": \"true\",\n"
		    + "			\"health\": \"100\",\n"
		    + "			\"inventory\": [],\n" + "      \"triggers\": []\n"
		    + "    }";

    private Item barreta, rociador, burbuja, calculadora, veneno;

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
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	NPC pirata = new NPC(JsonParser.parseString(jsonPirata));
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
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	NPC abeja = new NPC(JsonParser.parseString(jsonAbeja));
	UsarItemComando usar = new UsarItemComando();

	muelle.addNPC(abeja);
	jugador.setHabitacionActual(muelle);
	jugador.ponerItem(barreta);
	assertEquals("Eso no ha servido de nada.",
		usar.ejecutar(jugador, "barreta:abeja fantasma"));

    }

    @Test
    void testNpcSinTrigger() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	NPC abeja = new NPC(JsonParser.parseString(jsonAbeja));
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
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	NPC abeja = new NPC(JsonParser.parseString(jsonAbeja));
	UsarItemComando usar = new UsarItemComando();

	muelle.addNPC(abeja);
	jugador.setHabitacionActual(muelle);
	jugador.ponerItem(rociador);
	assertEquals("abeja no se encuentra en el muelle.",
		usar.ejecutar(jugador, "rociador con cerveza de raiz:abeja"));
    }

    @Test
    void testObjetoNoAplicableEnNpc() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	NPC abeja = new NPC(JsonParser.parseString(jsonAbeja));
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
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	NPC abeja = new NPC(JsonParser.parseString(jsonAbeja));
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
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	NPC abeja = new NPC(JsonParser.parseString(jsonAbeja));
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
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	NPC abeja = new NPC(JsonParser.parseString(jsonAbeja));
	UsarItemComando usar = new UsarItemComando();

	muelle.addNPC(abeja);
	jugador.setHabitacionActual(muelle);
	jugador.ponerItem(calculadora);
	assertEquals("Eso no ha servido de nada.", usar.ejecutar(jugador, "calculadora"));
    }

    @Test
    void testPocionSobreJugador() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	NPC abeja = new NPC(JsonParser.parseString(jsonAbeja));
	UsarItemComando usar = new UsarItemComando();

	muelle.addNPC(abeja);
	jugador.setHabitacionActual(muelle);
	jugador.ponerItem(rociador);
	assertEquals("Se utilizo el rociador con cerveza de raiz sobre ti.",
		usar.ejecutar(jugador, "rociador con cerveza de raiz"));
    }

    @Test
    void testVenenoSobreJugador() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	NPC abeja = new NPC(JsonParser.parseString(jsonAbeja));
	UsarItemComando usar = new UsarItemComando();

	muelle.addNPC(abeja);
	jugador.setHabitacionActual(muelle);
	jugador.ponerItem(veneno);
	assertEquals("Se utilizo el veneno sobre ti.", usar.ejecutar(jugador, "veneno"));
	assertEquals(80, jugador.getSalud());
    }

    @Test
    void testObjetoNoInventario() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	NPC abeja = new NPC(JsonParser.parseString(jsonAbeja));
	UsarItemComando usar = new UsarItemComando();

	muelle.addNPC(abeja);
	jugador.setHabitacionActual(muelle);
	jugador.ponerItem(veneno);
	assertEquals("No cuenta con invento en el inventario.",
		usar.ejecutar(jugador, "invento"));
    }

}
