package com.qa.testtrailmanager;

import java.net.*;
import java.net.http.*;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws Exception {
        var httpClient = HttpClient.newBuilder().build();

        var payload = String.join("\n"
                , "{"
                , " \"id\": \"86cvytrn9\","
                , " \"name\": \"Updated Task Name\","
                , " \"text_content\": \"Updated Task Content\","
                , " \"description\": \"Updated Task Content\","
                , " \"markdown_description\": \"Updates Task Content\","
                , " \"status\": \"PASS\""
                , "}"
                , "{"
                , " \"id\": \"86cvytrn9\","
                , " \"name\": \"Updated Task Name\","
                , " \"text_content\": \"Updated Task Content\","
                , " \"description\": \"Updated Task Content\","
                , " \"markdown_description\": \"Updates Task Content\","
                , " \"status\": \"PASS\""
                , "}"
        );

        HashMap<String, String> params = new HashMap<>();
        params.put("custom_task_ids", "true");
        params.put("team_id", "123");

        var query = params.keySet().stream()
                .map(key -> key + "=" + URLEncoder.encode(params.get(key), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));

        var host = "https://api.clickup.com";
        var taskId = "86cvytrn9";
        var pathname = "/api/v2/task/%7Btask_id%7D";
        var request = HttpRequest.newBuilder()
                .PUT(HttpRequest.BodyPublishers.ofString(payload))
                .uri(URI.create(host + pathname + '?' + query))
                .header("Content-Type", "application/json")
                .header("Authorization", "pk_55459893_2E0GOLCA791IM1HGX42TGJKC2V0F31A7")
                .build();

        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
}