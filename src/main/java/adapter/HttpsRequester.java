package adapter;

import org.json.JSONArray;
import usecase.URLRequester;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class HttpsRequester extends URLRequester {
    @Override
    public JSONArray httpsGet(String url) throws IOException {
        connection = getConnection(url);
        ((HttpsURLConnection)connection).setRequestMethod("GET");
        return getResponse();
    }
    @Override
    protected URLConnection getConnection(String url) throws IOException {
        URL requestUrl = new URL(url);
        return (HttpsURLConnection) requestUrl.openConnection();
    }

    @Override
    protected void closeAllConnection() throws IOException {
        is.close();
        isr.close();
        ((HttpsURLConnection)connection).disconnect();
    }
}
