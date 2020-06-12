package test.comandos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.*;

import zork.*;
import zork.comandos.SoltarComando;
import zork.input.parametro.*;

class SoltarComandoTest {
    private Item espejo, barreta, rociador;
    private Habitacion muelle;
    
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
    
    @BeforeEach
    void initHabitacion() {
	muelle = new Habitacion(new HabitacionInputParametro("muelle",
		"Estas en un muelle"));
    }
    
    @Test
    void testExito() {
	Jugador jugador = new Jugador("Guybrush Threepwood");
	Sitio suelo = new Sitio();
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
	Sitio suelo = new Sitio();
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
