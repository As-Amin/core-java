package com.corejava.packages.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONParser {
    private File file; // The file which contains the JSON code to parse

    /**
     * Constructor which sets the file inputted on creation of the object to the file to parse
     * variable created in the class. This is then used to succesfully perform functions to parse
     * the JSON file
     * 
     * @param file The JSON file to perform actions on
     */
    public JSONParser(File file) {
        this.file = file;
    }

    /**
     * Generates a JSONObject from the JSON file. Every line of the JSON file is read and parsed by
     * a BufferedReader and is packed into an object.
     * 
     * @return JSONObject The JSONObject created whilst parsing the JSON file.
     */
    public JSONObject GenerateJSONObject() throws IOException {
        BufferedReader br = null;
        try {
            br = Files.newBufferedReader(file.toPath());
        } catch (NullPointerException NPE) {
            throw new NullPointerException();
        }
        // Parse every line of the JSON file until the end
        String json = "";
        String line = "";
        while ((line = br.readLine()) != null) {
            json = json + line;
        }
        br.close(); // Close the buffered reader to avoid memory leaks
        JSONObject obj = new JSONObject(json); // Create the JSONObject based on the lines parsed
        return obj;
    }

    /**
     * Reads the JSONArray passed into the function and reads the properties and contents of the
     * line which corresponds to the key passed in
     * 
     * @param jsonArray The JSONArray to read and convert into a list
     * @param key The key of the line to parse in the JSONArray
     * @return List<String> The JSONArray converted into a list of strings to perform actions with
     */
    public List<String> ReadArray(JSONArray jsonArray, String key) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(jsonArray.getJSONObject(i).getString(key));
        }
        return list;
    }

    /**
     * Reads a single object from a JSONArray utilising the index of the object to find and the key
     * of the property
     * 
     * @param jsonArray The JSONArray to read and find the object in
     * @param key The key of the line to parse in the JSONArray
     * @param index The index of the object to read from the JSONArray
     * @return String The single object from the JSONArray converted into a string
     */
    public String ReadSingle(JSONArray jsonArray, String key, int index) {
        return jsonArray.getJSONObject(index).getString(key);
    }
}
