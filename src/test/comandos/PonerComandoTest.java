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
import zork.Sitio;
import zork.comandos.PonerComando;
import zork.input.parametro.ItemInputParametro;

public class PonerComandoTest {

    String jsonPlayer = "{\n" + " \"character\": \"Santi\"  }";
    String jsonRoom = "{\n" + " \"name\": \"muelle\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" ,\n"
	    + " \"description\": \"Estas en un muelle\" }";
    String jsonSitio = "{\n" + " \"name\": \"suelo\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" }";


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
    private Item espada;
    
    @BeforeEach
    void inicializarItem() {
	ItemInputParametro constructorItem = new ItemInputParametro("espada");
	constructorItem.setGender('f');
	constructorItem.setNumber('s');
	constructorItem.setAccionesValidas(new ArrayList<AccionItem>(Arrays.asList(AccionItem.DROP)));
	espada = new Item(constructorItem);
    }

    @Test
    void testExitoso() {

	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion room = new Habitacion(JsonParser.parseString(jsonRoom));
	Sitio suelo = new Sitio(JsonParser.parseString(jsonSitio));

	PonerComando poner = new PonerComando();

	jugador.ponerItem(espada);
	jugador.setHabitacionActual(room);
	room.addSitio(suelo);

	assertEquals("la espada ahora se encuentra en el suelo",
		poner.ejecutar(jugador, "espada:suelo"));
    }

    @Test
    void testSitioInexistente() {

	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion room = new Habitacion(JsonParser.parseString(jsonRoom));
	Sitio suelo = new Sitio(JsonParser.parseString(jsonSitio));
	Sitio ventana = new Sitio(JsonParser.parseString(jsonVentana));
	PonerComando poner = new PonerComando();

	jugador.ponerItem(espada);
	jugador.setHabitacionActual(room);
	room.addSitio(suelo);
	room.addSitio(ventana);

	assertEquals("No hay un cofre en el muelle. Puede dejar la espada en la ventana o en el suelo.",
		poner.ejecutar(jugador, "espada:cofre"));
    }

    @Test
    void testObjetoInexistente() {

	Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion room = new Habitacion(JsonParser.parseString(jsonRoom));
	Sitio sitio = new Sitio(JsonParser.parseString(jsonSitio));
	PonerComando poner = new PonerComando();
	jugador.setHabitacionActual(room);
	jugador.ponerItem(espada);
	room.addSitio(sitio);

	assertEquals("No se encuentra barreta en el inventario",
		poner.ejecutar(jugador, "barreta:suelo"));
    }

}
