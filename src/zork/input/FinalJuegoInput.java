package zork.input;

import zork.endgame.ComandoCondicion;

public interface FinalJuegoInput {
    public String getDescripcion();
    public String getObjetivo();
    public ComandoCondicion getAccion();
}
