package test.comandos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.*;

import zork.*;
import zork.comandos.PonerComando;
import zork.input.parametro.*;

public class PonerComandoTest {
    private Item espada;
    private Habitacion muelle;
    
    @BeforeEach
    void inicializarItem() {
	ItemInputParametro constructorItem = new ItemInputParametro("espada");
	constructorItem.setGender('f');
	constructorItem.setNumber('s');
	constructorItem.setAccionesValidas(new ArrayList<AccionItem>(Arrays.asList(AccionItem.DROP)));
	espada = new Item(constructorItem);
    }
    
    @BeforeEach
    void initHabitacion() {
	muelle = new Habitacion(new HabitacionInputParametro("muelle",
		"Estas en un muelle"));
    }

    @Test
    void testExitoso() {

	Jugador jugador = new Jugador("Guybrush Threepwood");

	PonerComando poner = new PonerComando();

	jugador.ponerItem(espada);
	jugador.setHabitacionActual(muelle);

	assertEquals("la espada ahora se encuentra en el suelo",
		poner.ejecutar(jugador, "espada:suelo"));
    }

    @Test
    void testSitioInexistente() {

	Jugador jugador = new Jugador("Guybrush Threepwood");
	Sitio ventana = new Sitio(new SitioInputParametro("ventana", 'f', 's'));
	PonerComando poner = new PonerComando();

	jugador.ponerItem(espada);
	jugador.setHabitacionActual(muelle);
	muelle.addSitio(ventana);

	assertEquals("No hay un cofre en el muelle. Puede dejar la espada en la ventana o en el suelo.",
		poner.ejecutar(jugador, "espada:cofre"));
    }

    @Test
    void testObjetoInexistente() {

	Jugador jugador = new Jugador("Guybrush Threepwood");
	PonerComando poner = new PonerComando();
	jugador.setHabitacionActual(muelle);
	jugador.ponerItem(espada);

	assertEquals("No se encuentra barreta en el inventario",
		poner.ejecutar(jugador, "barreta:suelo"));
    }

}
