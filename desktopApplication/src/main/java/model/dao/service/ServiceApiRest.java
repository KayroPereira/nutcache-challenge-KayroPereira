package model.dao.service;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import exception.ExceptionAPICommunicationError;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ServiceApiRest {

    public HttpRequest.Builder createRequestBoby(String url, String id){
        return HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .headers("Accept", "application/json")
                .uri(URI.create(url+id));
    }

    public String postRequest(String url, String postData) throws ExceptionAPICommunicationError {
        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> responseOfString = null;

        try {
            HttpRequest requestBody = createRequestBoby(url, "")
                    .POST(HttpRequest.BodyPublishers.ofString(postData))
                    .build();

            responseOfString = client.send(requestBody, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new ExceptionAPICommunicationError("Error Communication");
        }
        return responseOfString.body();
    }

    public String find(String url, String id) throws ExceptionAPICommunicationError {
        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> responseOfString = null;
        try {
            HttpRequest requestBody = createRequestBoby(url, id)
                    .GET()
                    .build();

            responseOfString = client.send(requestBody, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new ExceptionAPICommunicationError("Error Communication");
        }
        return responseOfString.body();
    }

    public String putRequest(String url, String putData, String id) throws ExceptionAPICommunicationError {
        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> responseOfString = null;
        try {
            HttpRequest requestBody = createRequestBoby(url, id)
                    .PUT(HttpRequest.BodyPublishers.ofString(putData))
                    .build();

            responseOfString = client.send(requestBody, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new ExceptionAPICommunicationError("Error Communication");
        }
        return responseOfString.body();
    }

    public String patchRequest(String url, String patchData, String id) throws ExceptionAPICommunicationError {
        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> responseOfString = null;
        try {
            HttpRequest requestBody = createRequestBoby(url, id)
                    .method("PATCH", HttpRequest.BodyPublishers.ofString(patchData))
                    .build();

            responseOfString = client.send(requestBody, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new ExceptionAPICommunicationError("Error Communication");
        }
        return responseOfString.body();
    }

    public String deleteRequest(String url, String id) throws ExceptionAPICommunicationError {
        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> responseOfString = null;
        try {
            HttpRequest requestBody = createRequestBoby(url, id)
                    .DELETE()
                    .build();

            responseOfString = client.send(requestBody, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new ExceptionAPICommunicationError("Error Communication");
        }
        return responseOfString.body();
    }

    public <T> String mapperEntityToJson(T entityGeneric) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(entityGeneric);
    }

    public <T> List<T> mapperObjectEntityList(String dataRequest, Class<T> generic) throws ExceptionAPICommunicationError {

        List<T> listGenerics = new ArrayList<>();

        try {
            JSONArray jsonArr = new JSONArray(dataRequest);

            for (int i = 0; i < jsonArr.length(); i++) {
                JSONObject objectJson = jsonArr.getJSONObject(i);
                ObjectMapper mapper = new ObjectMapper();
                T entityFormat = null;
                entityFormat = mapper.readValue(objectJson.toString(), generic);
                listGenerics.add(entityFormat);
            }
        } catch (Exception e) {
            throw new ExceptionAPICommunicationError("Error data format");
        }
        return listGenerics;
    }

    public <T> T mapperObjectEntity(String dataRequest, Class<T> generic) throws ExceptionAPICommunicationError {
        Object objectJSON = new JSONObject(dataRequest);

        ObjectMapper mapper = new ObjectMapper();
        T entityFormat = null;
        try {
            entityFormat = mapper.readValue(objectJSON.toString(), generic);
        } catch (IOException e) {
            throw new ExceptionAPICommunicationError("Error data format");
        }
        return entityFormat;
    }

    public String requestError(String response) throws ExceptionAPICommunicationError {

        String status = "";
        String message = "";

        if(response.isEmpty() || response == null){
            throw new ExceptionAPICommunicationError("Error reading data");
        }else {
            Object objectJSON = new JSONObject(response);

            status = ((JSONObject) objectJSON).get("status").toString();

            if (((JSONObject) objectJSON).has("message")) {
                message = ((JSONObject) objectJSON).getString("message");
            } else {
                message = ((JSONObject) objectJSON).getString("error");
            }
        }

        return String.format("Status: %s - Message: %s", status, message);
    }
}