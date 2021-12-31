package adapter;

import org.json.JSONArray;
import usecase.URLRequester;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

public class HttpRequester extends URLRequester {
    public HttpRequester() {
        properties = new HashMap<>();
    }

    @Override
    public JSONArray httpsGet(String url) throws IOException {
        connection = getConnection(url);
        ((HttpURLConnection)connection).setRequestMethod("GET");
        return getResponse();
    }
    @Override
    protected URLConnection getConnection(String url) throws IOException {
        URL requestUrl = new URL(url);
        return (HttpURLConnection) requestUrl.openConnection();
    }

    @Override
    protected void closeAllConnection() throws IOException {
        is.close();
        isr.close();
        ((HttpURLConnection)connection).disconnect();
    }
}
