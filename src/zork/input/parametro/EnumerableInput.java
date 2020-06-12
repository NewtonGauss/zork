package zork.input.parametro;

public abstract class EnumerableInput {
    protected String nombre;
    protected char gender;
    protected char number;

    public EnumerableInput() {
    }

    public EnumerableInput(String nombre) {
	this.nombre = nombre;
	this.gender = nombre.endsWith("a") || nombre.endsWith("as") ? 'f' : 'm';
	this.number = nombre.endsWith("s") ? 'p' : 's';
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setNumber(char number) {
        this.number = number;
    }

}
