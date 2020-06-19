package zork.input.parametro;

import zork.endgame.ComandoCondicion;
import zork.input.FinalJuegoInput;

public class FinalJuegoInputParametro implements FinalJuegoInput {
    private String descripcion, objetivo;
    private ComandoCondicion accion;
    
    public FinalJuegoInputParametro(String descripcion, String objetivo,
	    ComandoCondicion accion) {
	this.descripcion = descripcion;
	this.objetivo = objetivo;
	this.accion = accion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public ComandoCondicion getAccion() {
        return accion;
    }

    public void setAccion(ComandoCondicion accion) {
        this.accion = accion;
    }
}
