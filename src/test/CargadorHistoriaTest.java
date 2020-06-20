package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.*;

import zork.*;

class CargadorHistoriaTest {
    private Jugador jugador;
    private Habitacion habitacionInicial;
    private NPC pirata;

    @BeforeEach
    void cargarHistoria() throws IOException {
	CargadorHistoria ch = new CargadorHistoria("aventuras/mi.zork");
	jugador = ch.cargarHistoria();
	habitacionInicial = jugador.getHabitacionActual();
	pirata = habitacionInicial.getNPC("pirata fantasma");
    }

    @Test
    void testJugador() {
	assertEquals("Guybrush Threepwood", jugador.getNombre());

    }

    @Test
    void testHabitaciones() {
	assertEquals("muelle", habitacionInicial.getNombre());
	Habitacion taberna = habitacionInicial.getSalida(Direccion.SUR).getHabitacion();
	assertEquals("taberna", taberna.getNombre());
	assertEquals(habitacionInicial, taberna.getSalida(Direccion.NORTE).getHabitacion());
	
	assertEquals(null, habitacionInicial.getSalida(Direccion.NORTE));
	assertEquals(null, habitacionInicial.getSalida(Direccion.ESTE));
	assertEquals(null, habitacionInicial.getSalida(Direccion.OESTE));
	assertEquals(null, habitacionInicial.getSalida(Direccion.ABAJO));
	assertEquals(null, habitacionInicial.getSalida(Direccion.ARRIBA));
    }

    @Test
    void testNPC() {
	assertEquals("pirata fantasma", pirata.getNombre());
	assertEquals(
		"- '¡Me encanta la cerveza de raiz!' El pirata fantasma se veía entusiasmado"
		+ " por tu ofrecimiento... sin embargo, cuando lo rociaste comenzó a"
		+ " desintegrarse. La mitad de arriba de su cuerpo se desvaneció,"
		+ " y las piernas inmediatamente echaron a correr.",
		pirata.ejecutarTrigger(TipoTrigger.ITEM, "rociador con cerveza de raiz"));
	assertEquals(null, pirata.ejecutarTrigger(TipoTrigger.ATAQUE, "espada"));
    }
    
    @Test
    void testSitios() {
	Sitio suelo = habitacionInicial.getSitio("suelo");
	assertEquals("suelo", suelo.getNombre());
	assertEquals("barreta", suelo.getItem("barreta").getNombre());
	assertEquals("rociador con cerveza de raiz", suelo.getItem("rociador con cerveza de raiz").getNombre());
	assertEquals("espejo", suelo.getItem("espejo").getNombre());
    }
    
    @Test
    void testCargaObstaculo() {
	assertEquals(false, jugador.mover(Direccion.SUR));
	pirata.matar();
	assertEquals(true, jugador.mover(Direccion.SUR));
    }
}
