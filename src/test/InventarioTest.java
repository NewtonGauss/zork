package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.*;

import zork.*;
import zork.input.parametro.ItemInputParametro;

class InventarioTest {
    private Item barreta, barreta2;
    
    @BeforeEach
    void inicializarItems() {
	ItemInputParametro constructorItem = new ItemInputParametro("barreta");
	barreta = new Item(constructorItem);
	constructorItem.setNombre("barreta2");
	barreta2 = new Item(constructorItem);
    }

    @Test
    void testPesoMaximoAlcanzado() {
	Inventario i = new Inventario(11d);
	assertTrue(i.ponerItem(barreta));
	assertFalse(i.ponerItem(barreta2));
    }

    @Test
    void testRemoveItem() {
	Inventario i = new Inventario(11d);
	i.ponerItem(barreta);
	i.sacarItem(i.getItem("barreta"));
	assertTrue(i.ponerItem(barreta2));
	assertEquals("barreta2", i.getItem("barreta2").getNombre());
	assertEquals(null, i.getItem("barreta"));
    }
    
    @Test
    void testEntradaDeVariosItems() {
	Inventario i = new Inventario(200d);
	i.ponerItem(barreta);
	i.ponerItem(barreta2);
	assertEquals("barreta", i.getItem("barreta").getNombre());
	assertEquals("barreta2", i.getItem("barreta2").getNombre());
	Iterator<Item> iterator = i.getItems().iterator();
	Item itemActual = iterator.next();
	assertEquals("barreta", itemActual.getNombre());
	itemActual = iterator.next();
	assertEquals("barreta2", itemActual.getNombre());
	assertEquals((Double) 20.0, i.getPesoActual());
    }

}
