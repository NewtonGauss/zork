package test.comandos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.Habitacion;
import zork.Item;
import zork.Jugador;
import zork.NPC;
import zork.Salida;
import zork.Sitio;
import zork.TipoTrigger;
import zork.comandos.MirarComando;
import zork.input.TriggerInput;
import zork.input.parametro.ItemInputParametro;
import zork.input.parametro.NPCInputParametro;
import zork.input.parametro.TriggerInputParametro;

class MirarComandoTest {

    String jsonPlayer = "{\n" + " \"character\": \"Guybrush Threepwood\"  }";

    String jsonMuelle = "{\n" + " \"name\": \"muelle\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" ,\n"
	    + " \"description\": \"Estas en un muelle\" }",
	    jsonBarrio = "{\n" + " \"name\": \"barrio\" ,\n" + " \"gender\": \"male\" ,\n"
		    + " \"number\": \"singular\" ,\n"
		    + " \"description\": \"Estas en un barrio\" }",
	    jsonBahias = "{\n" + " \"name\": \"bahias\" ,\n"
		    + " \"gender\": \"female\" ,\n" + " \"number\": \"plural\" ,\n"
		    + " \"description\": \"Estas en un barrio\" }",
	    jsonTaberna = "{\n" + "      \"name\": \"taberna\",\n"
		    + "      \"gender\": \"female\",\n"
		    + "      \"number\": \"singular\",\n"
		    + "      \"description\": \"Estás en una sucia taberna\",\n"
		    + "      \"connections\": [\n" + "        {\n"
		    + "          \"direction\": \"north\",\n"
		    + "          \"location\": \"muelle\"\n" + "        }\n" + "      ]\n"
		    + "    }";

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
		    + "			\"inventory\": [],\n" + "      \"triggers\": [\n"
		    + "        {\n" + "          \"type\": \"item\",\n"
		    + "          \"thing\": \"rociador con cerveza de raiz\",\n"
		    + "          \"on_trigger\": \"- '¡Me encanta la cerveza de raiz!' El pirata fantasma se veía entusiasmado por tu ofrecimiento... sin embargo, cuando lo rociaste comenzó a desintegrarse. La mitad de arriba de su cuerpo se desvaneció, y las piernas inmediatamente echaron a correr.\",\n"
		    + "          \"after_trigger\": \"remove\"\n" + "        }\n"
		    + "      ]\n" + "    }",
	    jsonMamuts = "{\n" + "      \"name\": \"mamuts\",\n"
		    + "      \"gender\": \"male\",\n" + "      \"number\": \"plural\",\n"
		    + "      \"description\": \"- '¡No puedes pasar!' El pirata fantasma no te dejará pasar\",\n"
		    + "      \"talk\": \"¡No hay nada que me digas que me haga cambiar de opinión!\",\n"
		    + "			\"points\": \"100\",\n"
		    + "			\"enemy\": \"true\",\n"
		    + "			\"health\": \"100\",\n"
		    + "			\"inventory\": [],\n" + "      \"triggers\": [\n"
		    + "        {\n" + "          \"type\": \"item\",\n"
		    + "          \"thing\": \"rociador con cerveza de raiz\",\n"
		    + "          \"on_trigger\": \"- '¡Me encanta la cerveza de raiz!' El pirata fantasma se veía entusiasmado por tu ofrecimiento... sin embargo, cuando lo rociaste comenzó a desintegrarse. La mitad de arriba de su cuerpo se desvaneció, y las piernas inmediatamente echaron a correr.\",\n"
		    + "          \"after_trigger\": \"remove\"\n" + "        }\n"
		    + "      ]\n" + "    }",
	    jsonSuelo = "{\n" + "          \"name\": \"suelo\",\n"
		    + "          \"gender\": \"male\",\n"
		    + "          \"number\": \"singular\",\n" + "          \"items\": [\n"
		    + "            \"barreta\",\n"
		    + "            \"rociador con cerveza de raiz\",\n"
		    + "            \"espejo\"\n" + "          ]\n" + "        }";
    private Item espejo, rociador, barreta;
    private NPC pirata, abeja, mamuts;
    
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
	input.setNombre("mamuts");
	input.setNumber('p');
	mamuts = new NPC(input);
	input.setNombre("abeja fantasma");
	input.setGender('f');
	input.setNumber('s');
	abeja = new NPC(input);
    }
    
    @BeforeEach
    void inicializarItems() {
	ItemInputParametro constructorItems = new ItemInputParametro("espejo");
	constructorItems.setGender('m');
	constructorItems.setNumber('s');
	espejo = new Item(constructorItems);
	
	constructorItems.setNombre("rociador con cerveza de raiz");
	rociador = new Item(constructorItems);
	
	constructorItems.setNombre("barreta");
	constructorItems.setGender('f');
	barreta = new Item(constructorItems);
    }

    @Test
    void testExitosoHabitacion() {
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	Sitio suelo = new Sitio(JsonParser.parseString(jsonSuelo));
	muelle.addSitio(suelo);
	j1.setHabitacionActual(muelle);
	MirarComando c1 = new MirarComando();
	assertEquals("Estas en un muelle.", c1.ejecutar(j1, ""));
	assertEquals("Estas en un muelle.", c1.ejecutar(j1, "alrededor"));
	assertEquals("Estas en un muelle.", c1.ejecutar(j1, "muelle"));
    }

    @Test
    void testExitosoNPC() {
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion r1 = new Habitacion(JsonParser.parseString(jsonMuelle));
	MirarComando c1 = new MirarComando();

	r1.addNPC(pirata);
	j1.setHabitacionActual(r1);
	assertEquals("- '¡No puedes pasar!' El pirata fantasma no te dejará pasar",
		c1.ejecutar(j1, "pirata fantasma"));
    }

    @Test
    void testObjetivoInvalido() {
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion r1 = new Habitacion(JsonParser.parseString(jsonMuelle));
	MirarComando c1 = new MirarComando();

	r1.addNPC(pirata);
	j1.setHabitacionActual(r1);
	assertEquals("espejo no existe", c1.ejecutar(j1, "espejo"));
    }

    @Test
    void testEnumeracionItems() {

	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));

	Sitio suelo = new Sitio(JsonParser.parseString(jsonSuelo));
	suelo.addItem(espejo);
	suelo.addItem(barreta);
	suelo.addItem(rociador);
	muelle.addSitio(suelo);

	j1.setHabitacionActual(muelle);
	MirarComando c1 = new MirarComando();
	assertEquals(
		"Estas en un muelle. En el suelo hay una barreta, un rociador con cerveza de raiz y un espejo.",
		c1.ejecutar(j1, ""));
    }

    @Test
    void testEnumeracionUnSoloItem() {

	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));

	Sitio suelo = new Sitio(JsonParser.parseString(jsonSuelo));
	suelo.addItem(espejo);
	muelle.addSitio(suelo);

	j1.setHabitacionActual(muelle);
	MirarComando c1 = new MirarComando();
	assertEquals("Estas en un muelle. En el suelo hay un espejo.",
		c1.ejecutar(j1, ""));
    }

    @Test
    void testHabitacionConNPC() {
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion r1 = new Habitacion(JsonParser.parseString(jsonMuelle));
	MirarComando c1 = new MirarComando();

	r1.addNPC(pirata);
	j1.setHabitacionActual(r1);
	assertEquals("Estas en un muelle. Hay un pirata fantasma.", c1.ejecutar(j1, ""));
    }

    @Test
    void testHabitacionConDosNPC() {
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion r1 = new Habitacion(JsonParser.parseString(jsonMuelle));
	MirarComando c1 = new MirarComando();

	r1.addNPC(pirata);
	r1.addNPC(abeja);
	j1.setHabitacionActual(r1);
	assertEquals("Estas en un muelle. Hay una abeja fantasma y un pirata fantasma.",
		c1.ejecutar(j1, ""));
    }

    @Test
    void testUnaSalida() {
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	j1.setHabitacionActual(muelle);
	MirarComando c1 = new MirarComando();

	Habitacion barrio = new Habitacion(JsonParser.parseString(jsonBarrio));
	Salida salidaBarrio = new Salida(barrio);
	muelle.addSalida(salidaBarrio, "norte");

	assertEquals("Estas en un muelle. Al norte hay un barrio.", c1.ejecutar(j1, ""));
    }

    @Test
    void testDosSalida() {
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	j1.setHabitacionActual(muelle);
	MirarComando c1 = new MirarComando();

	Habitacion barrio = new Habitacion(JsonParser.parseString(jsonBarrio));
	Salida salidaBarrio = new Salida(barrio);
	muelle.addSalida(salidaBarrio, "arriba");
	Habitacion bahias = new Habitacion(JsonParser.parseString(jsonBahias));
	Salida salidaBahias = new Salida(bahias);
	muelle.addSalida(salidaBahias, "abajo");

	assertEquals("Estas en un muelle. Arriba hay un barrio. Abajo hay unas bahias.",
		c1.ejecutar(j1, ""));
    }

    @Test
    void testCompleto() {
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonMuelle));
	j1.setHabitacionActual(muelle);
	MirarComando c1 = new MirarComando();

	/* Items */
	Sitio suelo = new Sitio(JsonParser.parseString(jsonSuelo));
	suelo.addItem(espejo);
	suelo.addItem(barreta);
	suelo.addItem(rociador);
	muelle.addSitio(suelo);

	/* NPCs */
	muelle.addNPC(pirata);
	muelle.addNPC(abeja);
	muelle.addNPC(mamuts);

	/* Salidas */
	Habitacion barrio = new Habitacion(JsonParser.parseString(jsonBarrio));
	Salida salidaBarrio = new Salida(barrio);
	muelle.addSalida(salidaBarrio, "arriba");
	Habitacion bahias = new Habitacion(JsonParser.parseString(jsonBahias));
	Salida salidaBahias = new Salida(bahias);
	muelle.addSalida(salidaBahias, "abajo");
	Habitacion taberna = new Habitacion(JsonParser.parseString(jsonTaberna));
	Salida salidaTaberna = new Salida(taberna);
	muelle.addSalida(salidaTaberna, "sur");

	assertEquals("Estas en un muelle. En el suelo hay una barreta,"
		+ " un rociador con cerveza de raiz y un espejo."
		+ " Hay una abeja fantasma, unos mamuts y un pirata fantasma."
		+ " Al sur hay una taberna. Arriba hay un barrio."
		+ " Abajo hay unas bahias.", c1.ejecutar(j1, ""));
    }

}
