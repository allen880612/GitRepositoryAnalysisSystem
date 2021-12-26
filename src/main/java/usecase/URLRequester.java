package usecase;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public abstract class URLRequester {
    protected InputStream is;
    protected InputStreamReader isr;
    protected URLConnection connection;
    protected Map<String, String> properties;

    public URLRequester(){
        properties = new HashMap<>();
    }

    public URLRequester(URLConnection connection){
        properties = new HashMap<>();
        this.connection = connection;
    }

    public void addHTTPSGetProperty(String property, String value) {
        this.properties.put(property, value);
    }

    public abstract JSONArray httpsGet(String url) throws IOException;

    protected JSONArray getResponse() throws IOException {
        setConnectionProperty(connection);
        BufferedReader reader = getJSONUsingHttpsGet(connection);
        String completeContent = getCompleteContentString(reader);
        if(completeContent.charAt(0) != '[') completeContent = "[" + completeContent + "]";
        JSONArray jsonArray = new JSONArray(completeContent);
        closeAllConnection();
        return jsonArray;
    }

    protected abstract URLConnection getConnection(String url) throws IOException;

    protected abstract void closeAllConnection() throws IOException;

    protected void setConnectionProperty(URLConnection conn){
        for(String property : this.properties.keySet())
            conn.setRequestProperty(property, this.properties.get(property));
    }

    protected BufferedReader getJSONUsingHttpsGet(URLConnection connection) throws IOException {
        is = connection.getInputStream();
        isr = new InputStreamReader(is, "UTF-8");
        return new BufferedReader(isr);
    }
    
    protected String getCompleteContentString(BufferedReader reader) throws IOException {
        StringBuilder compeleteContent = new StringBuilder(0);
        String line = "";
        while ((line = reader.readLine()) != null) compeleteContent.append(line);
        return compeleteContent.toString();
    }
}
