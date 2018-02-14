package com.example.tenzind.ominitiator;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by TenzinD on 11/6/2017.
 * Util class.
 */

public enum Utility {
    INSTANCE;
    public HttpURLConnection getRestConnection(String finalUrl) throws IOException {
        URL url = new URL(finalUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        return connection;
    }
}

