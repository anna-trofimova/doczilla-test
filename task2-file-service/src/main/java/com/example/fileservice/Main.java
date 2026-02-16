package com.example.fileservice;

import com.sun.net.httpserver.HttpServer;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(
                new InetSocketAddress(8080),
                0
        );

        server.createContext("/", new RootHandler());
        server.createContext("/upload", new UploadHandler());
        server.createContext("/download", new DownloadHandler());

        server.start();

        System.out.println("Server started on http://localhost:8080");
    }
}
