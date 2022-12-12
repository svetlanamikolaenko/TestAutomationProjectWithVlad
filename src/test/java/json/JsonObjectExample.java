package json;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonObjectExample {

    @Test
    public void jsonTest() {
        try {
            String str = new String(Files.readAllBytes(Paths.get("JsonExample.json"))).replaceAll("\\r\\n", "");
            JSONArray array = new JSONArray(str);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                System.out.println("id: " + object.getInt("id"));
                System.out.println("language: " + object.getString("language"));
                System.out.println("edition: " + object.getString("edition"));
                System.out.println("author: " + object.getString("author"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}