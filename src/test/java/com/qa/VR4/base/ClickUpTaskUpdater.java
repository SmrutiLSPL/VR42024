package com.qa.VR4.base;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject;

public class ClickUpTaskUpdater {
    private static final String API_URL = "https://api.clickup.com/api/v2/task/";
    private static final String API_TOKEN = "pk_55459893_2E0GOLCA791IM1HGX42TGJKC2V0F31A7"; // Replace with your API token
    public static String TEST_CASE_PASS_STATUS= "Pass";
    public static String TEST_CASE_FAIL_STATUS= "fail";



    public static void updateTaskStatus(String taskId, String status, String s) {

            try {

                URL url = new URL(API_URL + taskId);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("PUT");
                conn.setRequestProperty("Authorization", API_TOKEN);
                conn.setRequestProperty("Content-Type", "application/json");

                if (status.equals("pass") && !status.equals("fail")) {
                    throw new IllegalArgumentException("Status must be either 'pass' or 'fail'.");
                }

                JSONObject payload = new JSONObject();
                payload.put("status", status);


                try (OutputStream os = conn.getOutputStream()) {
                    byte[] input = payload.toString().getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }


                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    System.out.println("Task " + taskId + " updated to " + status);
                } else {
                    System.out.println("Failed to update task " + taskId + ". Response code: " + responseCode);
                }

                conn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
