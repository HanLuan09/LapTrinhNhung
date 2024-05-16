package com.example.ltn_admin.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;

import com.example.ltn_admin.entity.Notification;
import com.google.gson.Gson;

public class Utils {
	public static void sendNotification(Notification notification) {
		Gson gson = new Gson();
        String json = gson.toJson(notification);

        // Tạo HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Tạo HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:4444/etc/notification"))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(json))
                .build();

        // Gửi yêu cầu và xử lý phản hồi
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
	}
}
