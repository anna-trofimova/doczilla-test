package com.example.fileservice;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.util.UUID;


public class UploadHandler implements HttpHandler {
    private final String storageDir = "storage";

    public UploadHandler() {
        File dir = new File(storageDir);
        if(!dir.exists()){
            dir.mkdir();
        }
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if(!exchange.getRequestMethod().equals("POST")) {
            exchange.sendResponseHeaders(405, -1);
            return;
        }

        //Generate ID for each file
        String fileId = UUID.randomUUID().toString();
        String filePath = storageDir + "/" + fileId;

        //Save infrmation to file
        try(InputStream is = exchange.getRequestBody();
            OutputStream os = new FileOutputStream(filePath)) {
            byte[] buffer = new byte[4096];
            int read;
            while((read = is.read(buffer)) != -1 ) {
                os.write(buffer, 0, read);
            }
        }

        //Create URL to download
        String response = "{\"id\": \"" + fileId + "\"}";
        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, response.length());

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
}