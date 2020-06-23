package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import zork.Enumerable;

class EnumerableTest {

    @Test
    void testSustImpropio() {
	Enumerable muelle = new Enumerable("muelle", 'm', 's') { };
	assertEquals("el muelle", muelle.toString());
	assertEquals("un muelle", muelle.articuloIndefinido());
    }
    
    @Test
    void testSustantivoPropio() {
	Enumerable ramos = new Enumerable("Ramos Mejia") { };
	assertEquals("Ramos Mejia", ramos.toString());
	// no tiene sentido el articulo indefinido de un sust propio...
    }
}
