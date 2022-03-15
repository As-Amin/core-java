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
    private File file;

    public JSONParser(File file) {
        this.file = file;
    }

    public JSONObject GenerateJSONObject() throws IOException {
        BufferedReader br = null;
        try {
            br = Files.newBufferedReader(file.toPath());
        } catch (NullPointerException NPE) {
            throw new NullPointerException();
        }
        String json = "";
        String line = "";
        while ((line = br.readLine()) != null) {
            json = json + line;
        }
        br.close();
        JSONObject obj = new JSONObject(json);
        return obj;
    }

    public String ReadSingle(JSONArray jsonArray, String key, int index) {
        return jsonArray.getJSONObject(index).getString(key);
    }

    public List<String> ReadArray(JSONArray jsonArray, String key) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(jsonArray.getJSONObject(i).getString(key));
        }
        return list;
    }
}
