package zork.input;

import java.util.List;

import zork.AccionItem;
import zork.ObjetivoItem;
import zork.TipoItem;

public interface ItemInput {
    public String getNombre();
    public Double getPeso();
    public int getPuntos();
    public float getSaludSumar();
    public TipoItem getTipo();
    public char getGender();
    public char getNumber();
    public List<AccionItem> getAccionesValidas();
    public List<ObjetivoItem> getObjetivosValidos();
}
