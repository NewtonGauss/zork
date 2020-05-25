package test.comandos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.Habitacion;
import zork.Item;
import zork.Jugador;
import zork.Sitio;
import zork.comandos.PonerComando;

public class PonerComandoTest {

    String jsonPlayer = "{\n" + " \"character\": \"Santi\"  }";
    String jsonRoom = "{\n" + " \"name\": \"muelle\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" ,\n"
	    + " \"description\": \"Estas en un muelle\" }";
    String jsonSitio = "{\n" + " \"name\": \"suelo\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" }";

    String jsonEspada = "{\n" + "      \"name\": \"espada\",\n"
	    + "      \"gender\": \"female\",\n" + "      \"number\": \"singular\",\n"
	    + "			\"points\": \"100\",\n"
	    + "			\"weight\": \"10\",\n"
	    + "			\"type\": \"weapon\",\n" + "      \"actions\": [\n"
	    + "        \"use\",\n" + "\"drop\"\n" + "      ],\n" + "      \"effects_over\": [\n"
	    + "        \"npcs\",\n" + "        \"self\",\n" + "        \"item\"\n"
	    + "      ]\n" + "    }";
    String jsonVentana = "{\n" + 
	    	"          \"name\": \"ventana\",\n" + 
	    	"          \"gender\": \"female\",\n" + 
	    	"          \"number\": \"singular\",\n" + 
	    	"          \"items\": [\n" + 
	    	"            \"barreta\",\n" + 
	    	"            \"rociador con cerveza de raiz\",\n" + 
	    	"            \"espejo\"\n" + 
	    	"          ]\n" + 
	    	"        }";

    @Test
    void testExitoso() { // esta el item en el inventario y el sitio existe

	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion room = new Habitacion(JsonParser.parseString(jsonRoom));
	Sitio suelo = new Sitio(JsonParser.parseString(jsonSitio));
	Item espada = new Item(JsonParser.parseString(jsonEspada));

	PonerComando poner = new PonerComando();

	jugador.ponerItem(espada);
	jugador.setHabitacionActual(room);
	room.addSitio(suelo);

	assertEquals("la espada ahora se encuentra en el suelo",
		poner.ejecutar(jugador, "espada:suelo"));
    }

    @Test
    void testSitioInexistente() { // el item esta en el invetario pero el sitio no existe

	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion room = new Habitacion(JsonParser.parseString(jsonRoom));
	Sitio suelo = new Sitio(JsonParser.parseString(jsonSitio));
	Sitio ventana = new Sitio(JsonParser.parseString(jsonVentana));
	Item espada = new Item(JsonParser.parseString(jsonEspada));
	PonerComando poner = new PonerComando();

	jugador.ponerItem(espada);
	jugador.setHabitacionActual(room);
	room.addSitio(suelo);
	room.addSitio(ventana);

	assertEquals("No hay un cofre en el muelle. Puede dejar la espada en la ventana o en el suelo.",
		poner.ejecutar(jugador, "espada:cofre"));
    }

    @Test
    void testObjetoInexistente() { // el item no esta en el invetario

	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion room = new Habitacion(JsonParser.parseString(jsonRoom));
	Sitio sitio = new Sitio(JsonParser.parseString(jsonSitio));
	Item espada = new Item(JsonParser.parseString(jsonEspada));
	PonerComando poner = new PonerComando();
	jugador.setHabitacionActual(room);
	jugador.ponerItem(espada);
	room.addSitio(sitio);

	assertEquals("No se encuentra barreta en el inventario",
		poner.ejecutar(jugador, "barreta:suelo"));
    }

}
