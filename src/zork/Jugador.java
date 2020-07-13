package zork;

public class Jugador extends Personaje {

    private int movimientos;
    private int puntuacion;
    private String info;
    private String pathHistoria;

    public Jugador(String nombre) {
	super(nombre);
	inventario = new Inventario();
	movimientos = 0;
	puntuacion = 0;
	salud = 100;
    }

    public Double getPesoInventario() {
	return this.inventario.getPesoActual();
    }
    public void sumarMovimiento() {
	this.movimientos++;
    }

    public int getCantMovimientos() {
	return this.movimientos;
    }
    
    public String getPathHistoria() {
	return pathHistoria;
    }

    public void setPathHistoria(String pathHistoria) {
	this.pathHistoria = pathHistoria;
    }

    public void setInfo(String info) {
	this.info = info;
    }
    
    public String getInfo() {
	return info;
    }

    public int getPuntuacion() {
	return this.puntuacion;
    }


    public boolean mover(Direccion direccion) {
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
