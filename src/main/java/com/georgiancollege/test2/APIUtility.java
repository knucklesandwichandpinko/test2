package com.georgiancollege.test2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class APIUtility {

    private static final String JSON_URL = "https://lamp.computerstudi.es/~PriyanshPriyansh001/java/test2/users.json";

    public static List<User> fetchUserDataFromAPI() {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(JSON_URL))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            Gson gson = new Gson();

            // Directly parse the JSON array into a list of User objects
            Type userListType = new TypeToken<List<User>>(){}.getType();
            return gson.fromJson(json, userListType);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }
}
