package zork;

public class Jugador extends Personaje {

    private int movimientos;
    private int puntuacion;

    public Jugador(String nombre) {
	this.nombre = nombre;
	inventario = new Inventario();
	movimientos = 0;
	puntuacion = 0;
	salud = 100;
    }

    public void sumarMovimiento() {
	this.movimientos++;
    }

    public String getNombre() {
	return this.nombre;
    }

    public int getCantMovimientos() {
	return this.movimientos;
    }

    public int getPuntuacion() {
	return this.puntuacion;
    }


    public boolean mover(String direccion) {
	Salida salida = habitacionActual.getSalida(direccion);
	boolean seMovio = false;
	if (salida != null && salida.isEnemigoDerrotado()) {
	    setHabitacionActual(salida.getHabitacion());
	    seMovio = true;
	}
	return seMovio;
    }
    
    
    @Override
    public boolean ponerItem(Item nuevoItem) {
        boolean retorno = false;
        if(super.ponerItem(nuevoItem)) {
            this.puntuacion += nuevoItem.getPoints();
            retorno = true;
        }
        return retorno;
    }
}
