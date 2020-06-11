package test.comandos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import zork.comandos.SoltarComando;
import zork.input.parametro.ItemInputParametro;

class SoltarComandoTest {

    private String jsonSitio = "{\n" + "          \"name\": \"suelo\",\n"
	    + "          \"gender\": \"male\",\n" + "          \"number\": \"singular\"\n"
	    + "        }",
	    jsonRoom = "{\n" + " \"name\": \"muelle\" ,\n" + " \"gender\": \"male\" ,\n"
		    + " \"number\": \"singular\" ,\n"
		    + " \"description\": \"Estas en un muelle\" }";
    
    private Item espejo, barreta, rociador;
    
    @BeforeEach
    void inicializarItem() {
	ItemInputParametro constructorItem = new ItemInputParametro("espejo");
	constructorItem.setGender('m');
	constructorItem.setNumber('s');
	constructorItem.setAccionesValidas(new ArrayList<AccionItem>(Arrays.asList(AccionItem.DROP)));
	espejo = new Item(constructorItem);
	
	constructorItem.setNombre("rociador con cerveza de raiz");
	rociador = new Item(constructorItem);
	
	constructorItem.setNombre("barreta");
	constructorItem.setGender('f');
	barreta = new Item(constructorItem);
    }
    
    @Test
    void testExito() {
	Jugador jugador = new Jugador("Guybrush Threepwood");
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonRoom));
	Sitio suelo = new Sitio(JsonParser.parseString(jsonSitio));
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
	Jugador jugador = new Jugador("Guybrush Threepwood");
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonRoom));
	Sitio suelo = new Sitio(JsonParser.parseString(jsonSitio));
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
	Jugador jugador = new Jugador("Guybrush Threepwood");
	Habitacion muelle = new Habitacion(JsonParser.parseString(jsonRoom));
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
