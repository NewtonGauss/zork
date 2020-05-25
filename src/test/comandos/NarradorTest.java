package test.comandos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.Item;
import zork.Jugador;
import zork.NPC;
import zork.Narrador;
import zork.Room;
import zork.Salida;
import zork.Sitio;

class NarradorTest {

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
		    + "    }",
	    barretaJson = "{\n" + "      \"name\": \"barreta\",\n"
		    + "      \"gender\": \"female\",\n"
		    + "      \"number\": \"singular\",\n"
		    + "			\"points\": \"100\",\n"
		    + "			\"weight\": \"10\",\n"
		    + "			\"type\": \"weapon\",\n"
		    + "      \"actions\": [\n" + "        \"dar\"\n" + "      ],\n"
		    + "      \"effects_over\": [\n" + "        \"npcs\",\n"
		    + "        \"self\",\n" + "        \"item\"\n" + "      ]\n"
		    + "    }",
	    rociadorJson = "{\n" + "      \"name\": \"rociador con cerveza de raiz\",\n"
		    + "      \"gender\": \"male\",\n"
		    + "      \"number\": \"singular\",\n"
		    + "			\"points\": \"100\",\n"
		    + "			\"weight\": \"10\",\n"
		    + "			\"type\": \"potion\",\n"
		    + "      \"actions\": [\n" + "        \"usar\"\n" + "      ],\n"
		    + "      \"effects_over\": [\n" + "        \"npcs\",\n"
		    + "        \"self\",\n" + "        \"item\"\n" + "      ]\n"
		    + "    }",
	    burbujaJson = "{\n" + "      \"name\": \"burbuja\",\n"
		    + "      \"gender\": \"male\",\n"
		    + "      \"number\": \"singular\",\n"
		    + "			\"points\": \"100\",\n"
		    + "			\"weight\": \"10\",\n"
		    + "			\"type\": \"vanilla\",\n"
		    + "      \"actions\": [\n" + "        \"usar\"\n" + "      ],\n"
		    + "      \"effects_over\": [\n" + "        \"npcs\",\n"
		    + "        \"self\",\n" + "        \"item\"\n" + "      ]\n"
		    + "    }",
	    calculadoraJson = "{\n" + "      \"name\": \"calculadora\",\n"
		    + "      \"gender\": \"female\",\n"
		    + "      \"number\": \"singular\",\n"
		    + "			\"points\": \"100\",\n"
		    + "			\"weight\": \"10\",\n"
		    + "			\"type\": \"potion\",\n"
		    + "      \"actions\": [\n" + "        \"usar\"\n" + "       ],\n"
		    + "      \"effects_over\": [\n" + "        \"npcs\",\n"
		    + "        \"item\"\n" + "      ]\n" + "    }",
	    venenoJson = "{\n" + "      \"name\": \"veneno\",\n"
		    + "      \"gender\": \"male\",\n"
		    + "      \"number\": \"singular\",\n"
		    + "			\"points\": \"100\",\n"
		    + "			\"weight\": \"10\",\n"
		    + "			\"type\": \"poison\",\n"
		    + "      \"actions\": [\n" + "        \"usar\"\n" + "      ],\n"
		    + "      \"effects_over\": [\n" + "        \"self\",\n"
		    + "        \"item\"\n" + "      ]\n" + "    }";

    String jsonUnlam = "{\n" + " \"name\": \"unlam\" ,\n" + " \"gender\": \"female\" ,\n"
	    + " \"number\": \"singular\" ,\n"
	    + " \"description\": \"Estas en una universidad\" }";

    String jsonBarrio = "{\n" + " \"name\": \"barrio\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" ,\n"
	    + " \"description\": \"Estas en un barrio\" }";

    String jsonYoshi = "{\n" + "\"name\": \"Yoshi\" , \n" + "\"gender\": \"male\" , \n"
	    + "\"number\": \"singular\" , \n"
	    + "\"description\": \"Aqui no puedes pasar! Yoshi no te dejara pasar\" , \n"
	    + "\"points\": \"100\" , \n" + "\"enemy\": \"true\" , \n"
	    + "\"health\": \"100\" , \n"
	    + "\"talk\": \"No hay nada que me digas que me haga cambiar de opinion!\", \n"
	    + "\"triggers\": [{" + " \"type\": \"attack\" ,\n"
	    + " \"thing\": \"espada\" ,\n" + " \"on_trigger\": \"Uhhh\" ,\n"
	    + " \"after_trigger\": \"kill\" } ] }";

    String jsonEspada = "{\n" + "      \"name\": \"espada\",\n"
	    + "      \"gender\": \"female\",\n" + "      \"number\": \"singular\",\n"
	    + "			\"points\": \"100\",\n"
	    + "			\"weight\": \"10\",\n"
	    + "			\"type\": \"weapon\",\n" + "      \"actions\": [\n"
	    + "        \"usar\"\n" + "      ],\n" + "      \"effects_over\": [\n"
	    + "        \"npcs\",\n" + "        \"self\",\n" + "        \"item\"\n"
	    + "      ]\n" + "    }";

    String piratajson = "{\n" + "      \"name\": \"pirata fantasma\",\n"
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
	    + "    }";
    
  

    @Test
    void testUsar() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Room muelle = new Room(JsonParser.parseString(jsonMuelle));
	NPC pirata = new NPC(JsonParser.parseString(jsonPirata));
	Item rociador = new Item(JsonParser.parseString(rociadorJson));
	Narrador narrador = new Narrador(jugador);

	muelle.addNPC(pirata);
	jugador.setHabitacionActual(muelle);
	jugador.addItem(rociador);
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
	Room muelle = new Room(JsonParser.parseString(jsonMuelle));
	Room unlam = new Room(JsonParser.parseString(jsonUnlam));
	Room barrio = new Room(JsonParser.parseString(jsonBarrio));
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
	Item item = new Item(JsonParser.parseString(calculadoraJson));
	Item item2 = new Item(JsonParser.parseString(venenoJson));

	assertEquals("Tu puntuacion es: 0", narrador.ejecutar("puntuacion"));

	jugador.addItem(item);

	assertEquals("Tu puntuacion es: 100", narrador.ejecutar("puntuacion"));

	jugador.addItem(item2);

	assertEquals("Tu puntuacion es: 200", narrador.ejecutar("puntuacion"));
    }

    @Test
    void testMovimientos() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Room muelle = new Room(JsonParser.parseString(jsonMuelle));
	Room unlam = new Room(JsonParser.parseString(jsonUnlam));
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
	Item calculadora = new Item(JsonParser.parseString(calculadoraJson));
	Item veneno = new Item(JsonParser.parseString(venenoJson));

	assertEquals("No tienes objetos en tu inventario.",
		narrador.ejecutar("inventario"));

	jugador.addItem(calculadora);

	assertEquals("Tienes una calculadora.", narrador.ejecutar("inventario"));

	jugador.addItem(veneno);

	assertEquals("Tienes una calculadora y un veneno.",
		narrador.ejecutar("inventario"));
    }

    @Test
    void testDiagnostico() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Narrador narrador = new Narrador(jugador);
	Item veneno = new Item(JsonParser.parseString(venenoJson));

	assertEquals(
		"Tu estado de salud es perfecto (100), solo te podria matar una seria herida",
		narrador.ejecutar("diagnostico"));

	jugador.addItem(veneno);

	narrador.ejecutar("usar veneno");

	assertEquals("Tu estado de salud es bueno (85)",
		narrador.ejecutar("diagnostico"));
    }

    @Test
    void testMirar() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Room muelle = new Room(JsonParser.parseString(jsonMuelle));
	Room unlam = new Room(JsonParser.parseString(jsonUnlam));
	Room barrio = new Room(JsonParser.parseString(jsonBarrio));
	NPC pirata = new NPC(JsonParser.parseString(jsonPirata));
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
	Room unlam = new Room(JsonParser.parseString(jsonUnlam));
	NPC yoshi = new NPC(JsonParser.parseString(jsonYoshi));
	Item espada = new Item(JsonParser.parseString(jsonEspada));
	Narrador narrador = new Narrador(jugador);
	jugador.addItem(espada);

	jugador.setHabitacionActual(unlam);
	assertEquals("Yoshi no se encuentra en la unlam.",
		narrador.ejecutar("atacar a Yoshi con la espada"));

	unlam.addNPC(yoshi);
	assertEquals("Yoshi: Uhhh.", narrador.ejecutar("atacar a Yoshi con la espada"));
    }

    @Test
    void testDar() {

	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Room muelle = new Room(JsonParser.parseString(jsonMuelle));
	NPC pirata = new NPC(JsonParser.parseString(piratajson));
	Item espada = new Item(JsonParser.parseString(jsonEspada));
	Narrador narrador = new Narrador(jugador);

	muelle.addNPC(pirata);
	jugador.setHabitacionActual(muelle);
	jugador.addItem(espada);

	assertEquals("yoshi no se encuentra en el muelle.",
		narrador.ejecutar("dar espada a yoshi"));

	assertEquals("Le diste la espada al pirata fantasma.",
		narrador.ejecutar("dar espada al pirata fantasma"));
    }

    @Test
    void testAgarrar() {

	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Room muelle = new Room(JsonParser.parseString(jsonMuelle));
	Item espada = new Item(JsonParser.parseString(jsonEspada));
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
	Room muelle = new Room(JsonParser.parseString(jsonMuelle));
	Item espada = new Item(JsonParser.parseString(jsonEspada));
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
	Room muelle = new Room(JsonParser.parseString(jsonMuelle));
	Item espada = new Item(JsonParser.parseString(jsonEspada));
	Narrador narrador = new Narrador(jugador);
	Sitio suelo = new Sitio();
	jugador.addItem(espada);
	muelle.addSitio(suelo);
	jugador.setHabitacionActual(muelle);

	assertEquals("Se solto la espada en el suelo.",
		narrador.ejecutar("soltar espada"));

	assertEquals("Tomaste la espada.", narrador.ejecutar("tomar la espada"));
    }

    @Test
    void testHablarConNPC() {

	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Room muelle = new Room(JsonParser.parseString(jsonMuelle));
	NPC pirata = new NPC(JsonParser.parseString(piratajson));
	Item rociador = new Item(JsonParser.parseString(jsonEspada));
	Narrador narrador = new Narrador(jugador);
	muelle.addNPC(pirata);
	jugador.setHabitacionActual(muelle);
	jugador.addItem(rociador);

	assertEquals("¡No hay nada que me digas que me haga cambiar de opinión!",
		narrador.ejecutar("hablar a pirata fantasma"));
	assertEquals("¡No hay nada que me digas que me haga cambiar de opinión!",
		narrador.ejecutar("hablar con pirata fantasma"));
    }

    @Test
    void testHablarSinNPC() {

	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Room muelle = new Room(JsonParser.parseString(jsonMuelle));
	Item rociador = new Item(JsonParser.parseString(jsonEspada));
	Narrador narrador = new Narrador(jugador);
	jugador.setHabitacionActual(muelle);
	jugador.addItem(rociador);

	assertEquals("pirata fantasma no se encuentra en el muelle.",
		narrador.ejecutar("hablar a pirata fantasma"));
	assertEquals("pirata fantasma no se encuentra en el muelle.",
		narrador.ejecutar("hablar con pirata fantasma"));
    }

    @Test
    void testComandoNoReconocido() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Room muelle = new Room(JsonParser.parseString(jsonMuelle));
	Item rociador = new Item(JsonParser.parseString(jsonEspada));
	Narrador narrador = new Narrador(jugador);
	jugador.setHabitacionActual(muelle);
	jugador.addItem(rociador);

	assertEquals("No puedo reconocer esa orden.",
		narrador.ejecutar("desensamblar la enciclopedia"));
	assertEquals("No puedo reconocer esa orden.",
		narrador.ejecutar("cortar en dos al pirata fantasma"));
	assertEquals("No puedo reconocer esa orden.",
		narrador.ejecutar("aniquilar a la abeja fantasma"));
    }
    

}
