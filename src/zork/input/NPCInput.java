package zork.input;

import java.util.List;

public interface NPCInput {
    public String getNombre();
    public String getDescripcion();
    public String getCharla();
    public char getGender();
    public char getNumber();
    public float getSalud();
    public boolean isEnemigo();
    public String getSpritePath();
    public List<TriggerInput> getListaTrigger();
}
