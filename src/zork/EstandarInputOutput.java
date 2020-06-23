package zork;

import java.util.Scanner;

public class EstandarInputOutput {

    private String nombreJugador;
    private Scanner teclado;
    
    public EstandarInputOutput(String nombreJugador) {
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
