package com.example.fileservice;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class RootHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        File file = new File("index.html");

        if (!file.exists()) {
            String response = "index.html not found";
            exchange.sendResponseHeaders(500, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
            return;
        }

        exchange.getResponseHeaders().add("Content-Type", "text/html");
        exchange.sendResponseHeaders(200, file.length());

        FileInputStream fis = new FileInputStream(file);
        OutputStream os = exchange.getResponseBody();

        fis.transferTo(os);

        fis.close();
        os.close();
    }
}
