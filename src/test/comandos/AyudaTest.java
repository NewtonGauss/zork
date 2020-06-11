package test.comandos;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.Jugador;
import zork.comandos.AyudaComando;

class AyudaTest {
    @Test
    void testDeAyuda() {
	Jugador j = new Jugador("Guybrush Threepwood");
	AyudaComando a=new AyudaComando();
	assertFalse(a.ejecutar(j,"").equals("No se encontro el archivo de Ayuda"));
    }

}
