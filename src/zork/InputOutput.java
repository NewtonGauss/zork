package zork;

import java.util.Scanner;

public class InputOutput {

    private String nombreJugador;
    private Scanner teclado;
    
    public InputOutput(String nombreJugador) {
	super();
	this.nombreJugador = nombreJugador;
    }

    public String leerComando() {
	String comando;
	System.out.print(nombreJugador + " > ");
	teclado = new Scanner(System.in);
	comando = teclado.nextLine();
	return comando;
    }

    public void imprimir(String mensaje) {
	System.out.println(mensaje);
    }

}
