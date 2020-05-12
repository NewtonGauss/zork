import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class pruebaSantiGSON {
	

	public static void main(String[] args) throws JSONException {
		
		JSONObject jsonLine = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject item = new JSONObject();
		item.put("welcome", "Te encuentras en un muelle. Es de noche pero la luna ilumina todo el lugar. En el suelo hay algunos objetos, y sientes muchas ganas de ir hacia una taberna.");
		item.put("character", "Guybrush Threepwood");
		array.put(item);
		jsonLine.put("settings", array);
		String msg = jsonLine.toString();
		System.out.println(msg);
		
		
	    
	}
	
}