package zork.input.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import zork.TipoTrigger;
import zork.input.TriggerInput;

public class TriggerInputJson implements TriggerInput {
    private TipoTrigger tipo;
    private String objetoActivador;
    private String mensaje;
    private String afterTrigger;
    
    public TriggerInputJson(String jsonString) {
	JsonElement json = JsonParser.parseString(jsonString);
	JsonObject jobject = json.getAsJsonObject();
	tipo = TipoTrigger.stringToTipoTrigger(jobject.get("type").getAsString());
	objetoActivador = jobject.get("thing").getAsString();
	mensaje = jobject.get("on_trigger").getAsString();
	afterTrigger = jobject.get("after_trigger").getAsString();
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
