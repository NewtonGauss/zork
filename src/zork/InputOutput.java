package zork;

import java.util.Scanner;

public class InputOutput {
	
	private String nombreJugador;
	public String leerComando(){
		String comando;
		try (Scanner teclado = new Scanner(System.in)) {
			comando=teclado.nextLine();
		}
		return comando;
	}
	public void imprimir(String mensaje) {
		System.out.println(mensaje);
	}
//	public static void main(String[] args) {
//		InputOutput io=new InputOutput();
//			String comando;
//			comando=io.leerComando();
//			io.imprimir(comando);
//	}
}
