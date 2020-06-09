package test.comandos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.*;
import zork.input.TriggerInput;
import zork.input.parametro.ItemInputParametro;
import zork.input.parametro.NPCInputParametro;
import zork.input.parametro.TriggerInputParametro;

class NarradorTest {

    String jsonPlayer = "{\n" + " \"character\": \"Guybrush Threepwood\"  }";

    String jsonMuelle = "{\n" + " \"name\": \"muelle\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" ,\n"
	    + " \"description\": \"Estas en un muelle\" }";

    String jsonUnlam = "{\n" + " \"name\": \"unlam\" ,\n" + " \"gender\": \"female\" ,\n"
	    + " \"number\": \"singular\" ,\n"
	    + " \"description\": \"Estas en una universidad\" }";

    String jsonBarrio = "{\n" + " \"name\": \"barrio\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" ,\n"
	    + " \"description\": \"Estas en un barrio\" }";


    private Item veneno, rociador, calculadora, espada;
    private NPC pirata;

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
    void inicializarItems() {
	ArrayList<AccionItem> acciones = new ArrayList<AccionItem>();
	acciones.add(AccionItem.USE);
	acciones.add(AccionItem.TAKE);
	acciones.add(AccionItem.DROP);

	ArrayList<ObjetivoItem> objetivos = new ArrayList<ObjetivoItem>();
	objetivos.add(ObjetivoItem.ITEM);
	objetivos.add(ObjetivoItem.NPCS);
	objetivos.add(ObjetivoItem.SELF);

	ItemInputParametro constructorItems = new ItemInputParametro("veneno");
	constructorItems.setGender('m');
	constructorItems.setNumber('s');
	constructorItems.setTipo(TipoItem.VENENO);
	constructorItems.setAccionesValidas(acciones);
	constructorItems.setObjetivosValidos(objetivos);
	constructorItems.setSaludSumar(15);
	veneno = new Item(constructorItems);

	constructorItems.setNombre("rociador con cerveza de raiz");
	rociador = new Item(constructorItems);

	constructorItems.setGender('f');
	constructorItems.setNombre("calculadora");
	calculadora = new Item(constructorItems);

	constructorItems.setTipo(TipoItem.ARMA);
	constructorItems.setNombre("espada");
	espada = new Item(constructorItems);
    }

    @Test
    void testUsar() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	Narrador narrador = new Narrador(jugador);

	muelle.addNPC(pirata);
	jugador.setHabitacionActual(muelle);
	jugador.ponerItem(rociador);
	assertEquals(
		"Se utilizo el rociador con cerveza de raiz sobre el pirata fantasma.\n"
			+ "- '¡Me encanta la cerveza de raiz!' El pirata fantasma se veía"
			+ " entusiasmado por tu ofrecimiento... sin embargo, cuando lo rociaste"
			+ " comenzó a desintegrarse. La mitad de arriba de su cuerpo se desvaneció,"
			+ " y las piernas inmediatamente echaron a correr.",
		narrador.ejecutar(
			"usar el rociador con cerveza de raiz en el pirata fantasma"));
	assertEquals("Se utilizo el rociador con cerveza de raiz sobre ti.",
		narrador.ejecutar("usar rociador con cerveza de raiz"));
	assertEquals("No cuenta con burbujas en el inventario.",
		narrador.ejecutar("usar burbujas"));
    }

    @Test
    void testIr() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	Habitacion unlam = new Habitacion(JsonParser.parseString(jsonUnlam));
	Habitacion barrio = new Habitacion(JsonParser.parseString(jsonBarrio));
	Salida salidaUnlam = new Salida(unlam);
	Salida salidaBarrio = new Salida(barrio);
	Salida salidaMuelle = new Salida(muelle);
	Narrador narrador = new Narrador(jugador);

	muelle.addSalida(salidaUnlam, "norte");

	unlam.addSalida(salidaBarrio, "norte");
	unlam.addSalida(salidaMuelle, "sur");

	barrio.addSalida(salidaUnlam, "sur");

	jugador.setHabitacionActual(muelle);

	assertEquals("Estas en una universidad", narrador.ejecutar("ir al norte"));
	assertEquals("Estas en un barrio", narrador.ejecutar("ir al norte"));
	assertEquals("Estas en una universidad", narrador.ejecutar("ir hacia el sur"));
	assertEquals("Estas en un muelle", narrador.ejecutar("ir al sur"));
	assertEquals("Hacia el sur no hay salida.", narrador.ejecutar("ir al sur"));
    }

    @Test
    void testPuntuacion() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Narrador narrador = new Narrador(jugador);

	assertEquals("Tu puntuacion es: 0", narrador.ejecutar("puntuacion"));

	jugador.ponerItem(calculadora);

	assertEquals("Tu puntuacion es: 100", narrador.ejecutar("puntuacion"));

	jugador.ponerItem(veneno);

	assertEquals("Tu puntuacion es: 200", narrador.ejecutar("puntuacion"));
    }

    @Test
    void testMovimientos() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	Habitacion unlam = new Habitacion(JsonParser.parseString(jsonUnlam));
	Salida surUnlam = new Salida(unlam);
	Narrador narrador = new Narrador(jugador);
	muelle.addSalida(surUnlam, "sur");

	jugador.setHabitacionActual(muelle);

	assertEquals("0", narrador.ejecutar("movimientos"));

	narrador.ejecutar("caminar al sur");

	/*
	 * El comando de movimientos tambien suma movimientos ya que consideramos
	 * movimientos a los comandos
	 */

	assertEquals("2", narrador.ejecutar("movimientos"));

	narrador.ejecutar("dar espejo al loco");
	narrador.ejecutar("dar espejo al loco");

	assertEquals("5", narrador.ejecutar("movimientos"));
    }

    @Test
    void testInventario() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Narrador narrador = new Narrador(jugador);

	assertEquals("No tienes objetos en tu inventario.",
		narrador.ejecutar("inventario"));

	jugador.ponerItem(calculadora);

	assertEquals("Tienes una calculadora.", narrador.ejecutar("inventario"));

	jugador.ponerItem(veneno);

	assertEquals("Tienes una calculadora y un veneno.",
		narrador.ejecutar("inventario"));
    }

    @Test
    void testDiagnostico() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Narrador narrador = new Narrador(jugador);

	assertEquals(
		"Tu estado de salud es perfecto (100), solo te podria matar una seria herida",
		narrador.ejecutar("diagnostico"));

	jugador.ponerItem(veneno);

	narrador.ejecutar("usar veneno");

	assertEquals("Tu estado de salud es bueno (85)",
		narrador.ejecutar("diagnostico"));
    }

    @Test
    void testMirar() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	Habitacion unlam = new Habitacion(JsonParser.parseString(jsonUnlam));
	Habitacion barrio = new Habitacion(JsonParser.parseString(jsonBarrio));
	Salida salidaUnlam = new Salida(unlam);
	Salida salidaBarrio = new Salida(barrio);
	Narrador narrador = new Narrador(jugador);
	muelle.addNPC(pirata);
	muelle.addSalida(salidaUnlam, "sur");
	unlam.addSalida(salidaBarrio, "sur");

	jugador.setHabitacionActual(muelle);

	assertEquals("Estas en un muelle. Hay un pirata fantasma. Al sur hay una unlam.",
		narrador.ejecutar("mirar"));
	assertEquals("- '¡No puedes pasar!' El pirata fantasma no te dejará pasar",
		narrador.ejecutar("mirar al pirata fantasma"));

	narrador.ejecutar("ir al sur");

	assertEquals("Estas en una universidad. Al sur hay un barrio.",
		narrador.ejecutar("mirar alrededor"));

	narrador.ejecutar("ir al sur");

	assertEquals("Estas en un barrio.", narrador.ejecutar("mirar"));
    }

    @Test
    void testAtacarCon() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion unlam = new Habitacion(JsonParser.parseString(jsonUnlam));
	NPC yoshi = initYoshi();
	Narrador narrador = new Narrador(jugador);
	jugador.ponerItem(espada);

	jugador.setHabitacionActual(unlam);
	assertEquals("Yoshi no se encuentra en la unlam.",
		narrador.ejecutar("atacar a Yoshi con la espada"));

	unlam.addNPC(yoshi);
	assertEquals("Yoshi: Uhhh.", narrador.ejecutar("atacar a Yoshi con la espada"));
    }

    private NPC initYoshi() {
	NPCInputParametro input = new NPCInputParametro("Yoshi");
	input.setGender('m');
	input.setNumber('s');
	input.setDescripcion("Aqui no puedes pasar! Yoshi no te dejara pasar");
	input.setCharla("No hay nada que me digas que me haga cambiar de opinion!");
	input.setEnemigo(true);
	TriggerInputParametro trigger = new TriggerInputParametro(TipoTrigger.ATAQUE);
	trigger.setAfterTrigger("kill");
	trigger.setMensaje("Uhhh");
	trigger.setObjetoActivador("espada");
	input.setListaTriggers(new ArrayList<TriggerInput>(Arrays.asList(trigger)));
	return new NPC(input);
    }

    @Test
    void testDar() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	Narrador narrador = new Narrador(jugador);

	muelle.addNPC(pirata);
	jugador.setHabitacionActual(muelle);
	jugador.ponerItem(espada);

	assertEquals("yoshi no se encuentra en el muelle.",
		narrador.ejecutar("dar espada a yoshi"));

	assertEquals("Le diste la espada al pirata fantasma.",
		narrador.ejecutar("dar espada al pirata fantasma"));
    }

    @Test
    void testAgarrar() {

	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	Narrador narrador = new Narrador(jugador);
	Sitio suelo = new Sitio();
	suelo.addItem(espada);
	muelle.addSitio(suelo);
	jugador.setHabitacionActual(muelle);

	assertEquals("Tomaste la espada.", narrador.ejecutar("agarrar la espada"));

	narrador.ejecutar("soltar espada");

	assertEquals("Tomaste la espada.", narrador.ejecutar("tomar la espada"));

	assertEquals("Debe elegir un objeto a tomar.", narrador.ejecutar("tomar"));
    }

    @Test
    void testPoner() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	Narrador narrador = new Narrador(jugador);
	Sitio suelo = new Sitio();
	suelo.addItem(espada);
	muelle.addSitio(suelo);
	jugador.setHabitacionActual(muelle);

	assertEquals("Tomaste la espada.", narrador.ejecutar("agarrar la espada"));
	assertEquals("la espada ahora se encuentra en el suelo",
		narrador.ejecutar("poner la espada en el suelo"));
	assertEquals("Tomaste la espada.", narrador.ejecutar("agarrar la espada"));
	assertEquals("No hay un cofre en el muelle. Puede dejar la espada en el suelo.",
		narrador.ejecutar("poner la espada en el cofre"));

    }

    @Test
    void testSoltar() {

	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	Narrador narrador = new Narrador(jugador);
	Sitio suelo = new Sitio();
	jugador.ponerItem(espada);
	muelle.addSitio(suelo);
	jugador.setHabitacionActual(muelle);

	assertEquals("Se solto la espada en el suelo.",
		narrador.ejecutar("soltar espada"));

	assertEquals("Tomaste la espada.", narrador.ejecutar("tomar la espada"));
    }

    @Test
    void testHablarConNPC() {

	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	Narrador narrador = new Narrador(jugador);
	muelle.addNPC(pirata);
	jugador.setHabitacionActual(muelle);

	assertEquals("¡No hay nada que me digas que me haga cambiar de opinión!",
		narrador.ejecutar("hablar a pirata fantasma"));
	assertEquals("¡No hay nada que me digas que me haga cambiar de opinión!",
		narrador.ejecutar("hablar con pirata fantasma"));
    }

    @Test
    void testHablarSinNPC() {

	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	Narrador narrador = new Narrador(jugador);
	jugador.setHabitacionActual(muelle);

	assertEquals("pirata fantasma no se encuentra en el muelle.",
		narrador.ejecutar("hablar a pirata fantasma"));
	assertEquals("pirata fantasma no se encuentra en el muelle.",
		narrador.ejecutar("hablar con pirata fantasma"));
    }

    @Test
    void testComandoNoReconocido() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	Narrador narrador = new Narrador(jugador);
	jugador.setHabitacionActual(muelle);

	assertEquals("No puedo reconocer esa orden.",
		narrador.ejecutar("desensamblar la enciclopedia"));
	assertEquals("No puedo reconocer esa orden.",
		narrador.ejecutar("cortar en dos al pirata fantasma"));
	assertEquals("No puedo reconocer esa orden.",
		narrador.ejecutar("aniquilar a la abeja fantasma"));
    }

}
