import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TessInventario {

	@Test
	void test1() {

		Inventario inventario = new Inventario();
		Item item1 = new Item("Manzana");
		Item item2 = new Item("Huevo Kinder");
		Item item3 = new Item("Mierda");
		assertEquals(true, inventario.addItem(item1));
		assertEquals(true, inventario.addItem(item2));
		assertEquals(false, inventario.addItem(item3));
		inventario.getItems();
		System.out.println(inventario.pesoActual);
		assertEquals(68.6, inventario.getPesoActual());
	}

}
