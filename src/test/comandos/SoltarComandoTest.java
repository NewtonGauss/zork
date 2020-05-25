package test.comandos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.Habitacion;
import zork.Item;
import zork.Jugador;
import zork.Sitio;
import zork.comandos.SoltarComando;

class SoltarComandoTest {

    private String jsonSitio = "{\n" + "          \"name\": \"suelo\",\n"
	    + "          \"gender\": \"male\",\n" + "          \"number\": \"singular\"\n"
	    + "        }",
	    barretaJson = "{\n" + "      \"name\": \"barreta\",\n"
		    + "      \"gender\": \"female\",\n"
		    + "      \"number\": \"singular\",\n"
		    + "			\"points\": \"100\",\n"
		    + "			\"weight\": \"10\",\n"
		    + "			\"type\": \"weapon\",\n"
		    + "      \"actions\": [\n" + "        \"drop\"\n" + "      ],\n"
		    + "      \"effects_over\": [\n" + "        \"npcs\",\n"
		    + "        \"self\",\n" + "        \"item\"\n" + "      ]\n"
		    + "    }",
	    rociadorJson = "{\n" + "      \"name\": \"rociador con cerveza de raiz\",\n"
		    + "      \"gender\": \"male\",\n"
		    + "      \"number\": \"singular\",\n"
		    + "			\"points\": \"100\",\n"
		    + "			\"weight\": \"10\",\n"
		    + "			\"type\": \"vanilla\",\n"
		    + "      \"actions\": [\n" + "        \"drop\"\n" + "      ],\n"
		    + "      \"effects_over\": [\n" + "        \"npcs\",\n"
		    + "        \"self\",\n" + "        \"item\"\n" + "      ]\n"
		    + "    }",
	    espejoJson = "{\n" + "      \"name\": \"espejo\",\n"
		    + "      \"gender\": \"male\",\n"
		    + "      \"number\": \"singular\",\n"
		    + "			\"points\": \"100\",\n"
		    + "			\"weight\": \"10\",\n"
		    + "			\"type\": \"vanilla\",\n"
		    + "      \"actions\": [\n" + "        \"drop\"\n" + "      ],\n"
		    + "      \"effects_over\": [\n" + "        \"npcs\",\n"
		    + "        \"self\",\n" + "        \"item\"\n" + "      ]\n"
		    + "    }",

	    jsonRoom = "{\n" + " \"name\": \"muelle\" ,\n" + " \"gender\": \"male\" ,\n"
		    + " \"number\": \"singular\" ,\n"
		    + " \"description\": \"Estas en un muelle\" }",
	    jsonPlayer = "{\n" + " \"character\": \"Guybrush Threepwood\"  }";

    @Test
    void testExito() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonRoom));
	Sitio suelo = new Sitio(JsonParser.parseString(jsonSitio));
	Item barreta = new Item(JsonParser.parseString(barretaJson));
	Item espejo = new Item(JsonParser.parseString(espejoJson));
	Item rociador = new Item(JsonParser.parseString(rociadorJson));
	SoltarComando sc = new SoltarComando();

	jugador.ponerItem(barreta);
	jugador.ponerItem(espejo);
	jugador.ponerItem(rociador);
	muelle.addSitio(suelo);
	jugador.setHabitacionActual(muelle);

	assertEquals(barreta, jugador.getItem(barreta.getNombre()));
	assertEquals(espejo, jugador.getItem(espejo.getNombre()));
	assertEquals(rociador, jugador.getItem(rociador.getNombre()));

	/* Me aseguro que suelo no tenga nada */
	assertTrue(suelo.getItems().isEmpty());

	assertEquals("Se solto la barreta en el suelo.", sc.ejecutar(jugador, "barreta"));
	assertEquals("Se solto el espejo en el suelo.", sc.ejecutar(jugador, "espejo"));

	/* Una vez soltados no los debe tener */
	assertEquals(null, jugador.getItem(barreta.getNombre()));
	assertEquals(null, jugador.getItem(espejo.getNombre()));
	assertEquals(rociador, jugador.getItem(rociador.getNombre()));

	/* Deben estar en el suelo */
	assertEquals(barreta, suelo.getItem(barreta.getNombre()));
	assertEquals(espejo, suelo.getItem(espejo.getNombre()));
    }
    
    @Test
    void testSinObjeto() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonRoom));
	Sitio suelo = new Sitio(JsonParser.parseString(jsonSitio));
	Item barreta = new Item(JsonParser.parseString(barretaJson));
	Item espejo = new Item(JsonParser.parseString(espejoJson));
	Item rociador = new Item(JsonParser.parseString(rociadorJson));
	SoltarComando sc = new SoltarComando();
	
	jugador.ponerItem(barreta);
	jugador.ponerItem(espejo);
	jugador.ponerItem(rociador);
	muelle.addSitio(suelo);
	jugador.setHabitacionActual(muelle);
	
	assertEquals(barreta, jugador.getItem(barreta.getNombre()));
	assertEquals(espejo, jugador.getItem(espejo.getNombre()));
	assertEquals(rociador, jugador.getItem(rociador.getNombre()));
	
	/* Me aseguro que suelo no tenga nada */
	assertTrue(suelo.getItems().isEmpty());
	
	assertEquals("No tienes canicas en tu inventario.", sc.ejecutar(jugador, "canicas"));
	
	/* Me aseguro de que no solto nada */
	assertEquals(barreta, jugador.getItem(barreta.getNombre()));
	assertEquals(espejo, jugador.getItem(espejo.getNombre()));
	assertEquals(rociador, jugador.getItem(rociador.getNombre()));
	
	/* Suelo debe seguir sin nada */
	assertTrue(suelo.getItems().isEmpty());
    }
    

    @Test
    void testSitioPorDefecto() {
	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonRoom));
	Item barreta = new Item(JsonParser.parseString(barretaJson));
	Item espejo = new Item(JsonParser.parseString(espejoJson));
	Item rociador = new Item(JsonParser.parseString(rociadorJson));
	SoltarComando sc = new SoltarComando();

	jugador.ponerItem(barreta);
	jugador.ponerItem(espejo);
	jugador.ponerItem(rociador);
	jugador.setHabitacionActual(muelle);

	assertEquals(barreta, jugador.getItem(barreta.getNombre()));
	assertEquals(espejo, jugador.getItem(espejo.getNombre()));
	assertEquals(rociador, jugador.getItem(rociador.getNombre()));

	/* Me aseguro que suelo no tenga nada */
	Sitio suelo = muelle.getSitio("suelo"); // sitio por defecto
	assertTrue(suelo.getItems().isEmpty());

	assertEquals("Se solto la barreta en el suelo.", sc.ejecutar(jugador, "barreta"));
	assertEquals("Se solto el espejo en el suelo.", sc.ejecutar(jugador, "espejo"));

	/* Una vez soltados no los debe tener */
	assertEquals(null, jugador.getItem(barreta.getNombre()));
	assertEquals(null, jugador.getItem(espejo.getNombre()));
	assertEquals(rociador, jugador.getItem(rociador.getNombre()));

	/* Deben estar en el suelo */
	assertEquals(barreta, suelo.getItem(barreta.getNombre()));
	assertEquals(espejo, suelo.getItem(espejo.getNombre()));
    }

}
