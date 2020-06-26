package test.comandos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.*;

import zork.*;
import zork.comandos.TomarComando;
import zork.input.parametro.*;

class TomarTest {
    private Item rociador, espejo;
    private Habitacion muelle;
    
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
    
    @BeforeEach
    void initHabitacion() {
	muelle = new Habitacion(new HabitacionInputParametro("muelle",
		"Estas en un muelle"));
    }

    @Test
    void testTomarEspejo() {
	Jugador j = new Jugador("Guybrush Threepwood");
	Sitio s1 = new Sitio();
	Sitio s2 = new Sitio(new SitioInputParametro("pasarela"));
	Sitio s3 = new Sitio(new SitioInputParametro("mesa"));
	muelle.addSitio(s1);
	muelle.addSitio(s2);
	muelle.addSitio(s3);
	s1.addItem(rociador);
	s3.addItem(espejo);
	j.setHabitacionActual(muelle);
	TomarComando c = new TomarComando();
	assertEquals(null, j.getItem("espejo"));
	assertEquals("Tomaste el espejo.", c.ejecutar(j, "espejo"));
	assertEquals("espejo", j.getItem("espejo").getNombre());
	assertEquals("No hay ningun espejo por aqui.", c.ejecutar(j, "espejo"));
	assertEquals(rociador, s1.getItem("rociador con cerveza de raiz"));
    }

    @Test
    void testNoHayItem() {
	Jugador j = new Jugador("Guybrush Threepwood");
	j.setHabitacionActual(muelle);
	TomarComando c = new TomarComando();
	assertEquals("No hay ningun neumatico por aqui.", c.ejecutar(j, "neumatico"));
    }

    @Test
    void testNoHayEspacio() {
	Jugador j = new Jugador("Guybrush Threepwood");
	Sitio s1 = new Sitio();
	muelle.addSitio(s1);
	s1.addItem(rociador);
	s1.addItem(espejo);
	j.setHabitacionActual(muelle);
	j.ponerItem(espejo);
	TomarComando c = new TomarComando();
	assertEquals("No tienes mas espacio en tu inventario!",
		c.ejecutar(j, "rociador con cerveza de raiz"));
    }


}
