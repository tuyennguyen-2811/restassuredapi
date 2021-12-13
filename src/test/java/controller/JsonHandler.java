package controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonHandler {

    public JSONObject parseStringToJsonObject(String stringToParse) throws ParseException {

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(stringToParse);
        return json;

    }

    public JSONArray parseStringToJsonArray(String stringToParse) throws ParseException {

        JSONParser parser = new JSONParser();
        JSONArray json = (JSONArray) parser.parse(stringToParse);
        return json;

    }
}
