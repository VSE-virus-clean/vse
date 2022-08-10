package jksoft.com.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GsonUtils {
	
	public static void objectToJson(Object object) {
		Gson gson = new GsonBuilder().create();
		System.out.println(gson.toJson(object, object.getClass()));
	}
		
	public static String jsonToPrettyString(JsonObject jsonObject) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(jsonObject);
	}
	
	public static JsonObject stringToJsonObject(String jsonString) {
		JsonParser parser = new JsonParser();
		return parser.parse(jsonString).getAsJsonObject();
	}
}
