package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.*;

import zork.*;
import zork.input.parametro.*;

class SitioTest {
    private Item espejo, rociador, barreta;
    
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
    void testCreacion() {
	Sitio s = new Sitio();
	assertEquals("el suelo", s.toString());
    }
    
    @Test
    void testItems() {
	Sitio s = new Sitio();
	s.addItem(espejo);
	s.addItem(barreta);
	s.addItem(rociador);

	/* Me da una lista de items. Pruebo que esten */
	assertTrue(s.getItems().contains(barreta));
	assertTrue(s.getItems().contains(espejo));
	assertTrue(s.getItems().contains(rociador));
	
	/* get saca los items del mapa */
	assertEquals(barreta, s.getItem("barreta"));
	assertEquals(espejo, s.getItem("espejo"));
	assertEquals(rociador, s.getItem("rociador con cerveza de raiz"));
	
	/* una vez que salio no deberia estar */
	assertEquals(null, s.getItem("espejo"));
    }
    
    @Test
    void testMalePluralSitio() {
	Sitio s = new Sitio(new SitioInputParametro("suelos", 'm', 'p'));
	assertEquals("los suelos", s.toString());
    }
    
    @Test
    void testFemalePluralSitio() {
	Sitio s = new Sitio(new SitioInputParametro("ventanas", 'f','p'));
	assertEquals("las ventanas", s.toString());
    }
    
    @Test
    void testFemaleSitio() {
	Sitio s = new Sitio(new SitioInputParametro("ventana", 'f', 's'));
	assertEquals("la ventana", s.toString());
    }
    
}
