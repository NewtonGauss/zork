package test.comandos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.Item;
import zork.Jugador;
import zork.comandos.Comando;
import zork.comandos.InventarioComando;
import zork.input.parametro.ItemInputParametro;

class ComandoInventarioTest {
    private Item barreta, espejo;
    
    @BeforeEach
    void inicializarItems() {
	ItemInputParametro constructorItem = new ItemInputParametro("barreta");
	constructorItem.setGender('f');
	constructorItem.setNumber('s');
	barreta = new Item(constructorItem);
	
	constructorItem.setNombre("espejo");
	constructorItem.setGender('m');
	espejo = new Item(constructorItem);
    }
    
    @Test
    void testDosItems() {
	Jugador jugador = new Jugador("Guybrush Threepwood");
	jugador.ponerItem(espejo);
	jugador.ponerItem(barreta);

	Comando com = new InventarioComando();
	assertEquals("Tienes una barreta y un espejo.", com.ejecutar(jugador, ""));
    }

    @Test
    void testInventarioVacio() {
	Jugador jugador = new Jugador("Guybrush Threepwood");

	Comando com = new InventarioComando();
	assertEquals("No tienes objetos en tu inventario.", com.ejecutar(jugador, ""));
    }

}
