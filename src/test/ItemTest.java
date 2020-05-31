package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zork.AccionItem;
import zork.Item;
import zork.ObjetivoItem;
import zork.TipoItem;
import zork.input.parametro.ItemInputParametro;

class ItemTest {
    private ItemInputParametro constructorItem;

    @BeforeEach
    void inicializarItem() {
	constructorItem = new ItemInputParametro("barreta");
    }

    @Test
    void testItems() {
	constructorItem.setGender('f');
	constructorItem.setNumber('s');
	Item barreta = new Item(constructorItem);
	assertEquals("la barreta", barreta.toString());
    }

    @Test
    void testObjetosValidos() {
	constructorItem
		.setAfecta(new ArrayList<ObjetivoItem>(Arrays.asList(ObjetivoItem.NPCS)));
	Item i = new Item(constructorItem);
	assertEquals(false, i.esObjetivoValido(ObjetivoItem.ITEM));
	assertEquals(true, i.esObjetivoValido(ObjetivoItem.NPCS));
    }

    @Test
    void testUsosValidos() {
	constructorItem.setAccionesValidas(
		new ArrayList<AccionItem>(Arrays.asList(AccionItem.TAKE)));
	Item i = new Item(constructorItem);
	assertEquals(false, i.esUsoValido(AccionItem.DROP));
	assertEquals(true, i.esUsoValido(AccionItem.TAKE));
    }

    @Test
    void testObjetoMale() {
	constructorItem.setNombre("palo");
	constructorItem.setGender('m');
	constructorItem.setNumber('s');
	Item i = new Item(constructorItem);
	assertEquals("el palo", i.toString());
    }

    @Test
    void testObjetoMalePlural() {
	constructorItem.setNombre("palos");
	constructorItem.setGender('m');
	constructorItem.setNumber('p');
	Item i = new Item(constructorItem);
	assertEquals("los palos", i.toString());
    }

    @Test
    void testObjetoFemalePlural() {
	constructorItem.setNombre("barretas");
	constructorItem.setGender('f');
	constructorItem.setNumber('p');
	Item i = new Item(constructorItem);
	assertEquals("las barretas", i.toString());
    }

    @Test
    void testGetters() {
	constructorItem.setPeso(10d);
	constructorItem.setPuntos(100);
	constructorItem.setTipo(TipoItem.ARMA);
	Item i = new Item(constructorItem);
	assertEquals("barreta", i.getNombre());
	assertEquals(10d, i.getPeso());
	assertEquals(TipoItem.ARMA, i.getTipo());
	assertEquals(100, i.getPoints());
    }

}
