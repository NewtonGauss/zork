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
import zork.comandos.TomarComando;
import zork.input.parametro.ItemInputParametro;

class TomarTest {
    String jsonPlayer = "{\n" + " \"character\": \"Guybrush Threepwood\"  }";

    String jsonRoom = "{\n" + " \"name\": \"muelle\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" ,\n"
	    + " \"description\": \"Estas en un muelle\" }";

    String jsonSitio1 = "{\n" + " \"name\": \"suelo\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" }";
    String jsonSitio2 = "{\n" + " \"name\": \"pasarela\" ,\n"
	    + " \"gender\": \"female\" ,\n" + " \"number\": \"singular\" }";
    String jsonSitio3 = "{\n" + " \"name\": \"mesa\" ,\n" + " \"gender\": \"female\" ,\n"
	    + " \"number\": \"singular\" }";

    private Item rociador, espejo;
    
    @BeforeEach
    void inicializarItems() {
	ItemInputParametro constructorItem = new ItemInputParametro("espejo");
	constructorItem.setGender('m');
	constructorItem.setNumber('s');
	constructorItem.setAccionesValidas(new ArrayList<AccionItem>(Arrays.asList(AccionItem.TAKE)));
	espejo = new Item(constructorItem);
	
	constructorItem.setNombre("rociador con cerveza de raiz");
	constructorItem.setPeso(95d);
	rociador = new Item(constructorItem);
    }

    @Test
    void testTomarEspejo() {
	Jugador j = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion r = new Habitacion(JsonParser.parseString(jsonRoom));
	Sitio s1 = new Sitio(JsonParser.parseString(jsonSitio1));
	Sitio s2 = new Sitio(JsonParser.parseString(jsonSitio2));
	Sitio s3 = new Sitio(JsonParser.parseString(jsonSitio3));
	r.addSitio(s1);
	r.addSitio(s2);
	r.addSitio(s3);
	s1.addItem(rociador);
	s3.addItem(espejo);
	j.setHabitacionActual(r);
	TomarComando c = new TomarComando();
	assertEquals("Tomaste el espejo.", c.ejecutar(j, "espejo"));
	assertEquals("No hay ningun espejo por aqui.", c.ejecutar(j, "espejo"));
	assertEquals(rociador, s1.getItem("rociador con cerveza de raiz"));
    }

    @Test
    void testNoHayItem() {
	Jugador j = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion r = new Habitacion(JsonParser.parseString(jsonRoom));
	j.setHabitacionActual(r);
	TomarComando c = new TomarComando();
	assertEquals("No hay ningun neumatico por aqui.", c.ejecutar(j, "neumatico"));
    }

    @Test
    void testNoHayEspacio() {
	Jugador j = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion r = new Habitacion(JsonParser.parseString(jsonRoom));
	Sitio s1 = new Sitio(JsonParser.parseString(jsonSitio1));
	r.addSitio(s1);
	s1.addItem(rociador);
	s1.addItem(espejo);
	j.setHabitacionActual(r);
	j.ponerItem(espejo);
	TomarComando c = new TomarComando();
	assertEquals("No tienes mas espacio en tu inventario!",
		c.ejecutar(j, "rociador con cerveza de raiz"));
    }


}
