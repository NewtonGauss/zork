package test.comandos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import zork.Jugador;
import zork.comandos.*;

class DiagnosticoTest {
    @Test
    void testDiagosticoEnPelea() {
	Jugador player = new Jugador("Guybrush Threepwood");
	Comando com = new DiagnosticoComando();
	assertEquals("Tu estado de salud es perfecto (100), "
		+ "solo te podria matar una seria herida", com.ejecutar(player, ""));
	player.restarSalud(20);
	assertEquals("Tu estado de salud es bueno (80)", com.ejecutar(player, ""));
	player.restarSalud(20);
	assertEquals("Tu estado de salud no es muy bueno (60),"
		+ " sera mejor que encuentres algo para curarte", com.ejecutar(player, ""));
	player.restarSalud(50);
	assertEquals("Tu estado de salud es bajo (10),"
		+ " hasta un rasguno podria ser peligroso!", com.ejecutar(player, ""));
	player.sumarSalud(10);
	assertEquals("Tu estado de salud es bajo (20),"
		+ " hasta un rasguno podria ser peligroso!", com.ejecutar(player, ""));
	player.restarSalud(50);
	assertEquals("Estas muerto", com.ejecutar(player, ""));
	player.sumarSalud(10);
	assertEquals("Estas muerto", com.ejecutar(player, ""));
    }

}
