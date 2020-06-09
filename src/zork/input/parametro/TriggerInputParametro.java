package zork.input.parametro;

import zork.TipoTrigger;
import zork.input.TriggerInput;

public class TriggerInputParametro implements TriggerInput {
    private TipoTrigger tipo;
    private String objetoActivador;
    private String mensaje;
    private String afterTrigger;
    
    public TriggerInputParametro(TipoTrigger tipoTrigger) {
	tipo = tipoTrigger;
    }
    
    public void setTipo(TipoTrigger tipo) {
        this.tipo = tipo;
    }

    public void setObjetoActivador(String objetoActivador) {
        this.objetoActivador = objetoActivador;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setAfterTrigger(String afterTrigger) {
        this.afterTrigger = afterTrigger;
    }

    @Override
    public TipoTrigger getTipo() {
	return tipo;
    }

    @Override
    public String getObjetoActivador() {
	return objetoActivador;
    }

    @Override
    public String getMensaje() {
	return mensaje;
    }

    @Override
    public String getAfterTrigger() {
	return afterTrigger;
    }

}
