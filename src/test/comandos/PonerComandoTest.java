package test.comandos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.*;

import com.google.gson.JsonParser;

import zork.*;
import zork.comandos.PonerComando;
import zork.input.parametro.*;

public class PonerComandoTest {
    String jsonRoom = "{\n" + " \"name\": \"muelle\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" ,\n"
	    + " \"description\": \"Estas en un muelle\" }";


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

	Jugador jugador = new Jugador("Guybrush Threepwood");
	Habitacion room = new Habitacion(JsonParser.parseString(jsonRoom));

	PonerComando poner = new PonerComando();

	jugador.ponerItem(espada);
	jugador.setHabitacionActual(room);

	assertEquals("la espada ahora se encuentra en el suelo",
		poner.ejecutar(jugador, "espada:suelo"));
    }

    @Test
    void testSitioInexistente() {

	Jugador jugador = new Jugador("Guybrush Threepwood");
	Habitacion room = new Habitacion(JsonParser.parseString(jsonRoom));
	Sitio ventana = new Sitio(new SitioInputParametro("ventana", 'f', 's'));
	PonerComando poner = new PonerComando();

	jugador.ponerItem(espada);
	jugador.setHabitacionActual(room);
	room.addSitio(ventana);

	assertEquals("No hay un cofre en el muelle. Puede dejar la espada en la ventana o en el suelo.",
		poner.ejecutar(jugador, "espada:cofre"));
    }

    @Test
    void testObjetoInexistente() {

	Jugador jugador = new Jugador("Guybrush Threepwood");
	Habitacion room = new Habitacion(JsonParser.parseString(jsonRoom));
	PonerComando poner = new PonerComando();
	jugador.setHabitacionActual(room);
	jugador.ponerItem(espada);

	assertEquals("No se encuentra barreta en el inventario",
		poner.ejecutar(jugador, "barreta:suelo"));
    }

}
