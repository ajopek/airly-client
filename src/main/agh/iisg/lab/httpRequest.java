package agh.iisg.lab;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class httpRequest {
    // create, execute
    private HttpURLConnection connection;
    private HashMap<ParameterType, String> requestParameters;

    public httpRequest(String textUrl,  HashMap<ParameterType, String> paremeters) {
        URL builtUrl = buildUrl(textUrl);
        connect(builtUrl);
        this.requestParameters = paremeters;
    }

    private URL buildUrl(String url) {
        URL builtUrl = null;
        try {
            builtUrl = new URL(url);
        } catch (MalformedURLException e) {
            System.err.println(url + " is no a valid url");
            System.err.print(e.getMessage());
            System.exit(1);
        }
        return builtUrl;
    }
    private void connect(URL url) {
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            System.err.println("Unable to establish connection with " + url);
            System.err.print(e.getMessage());
            System.exit(1);
        }
    }

    private void setMethod(String method) {
        
    }

    private void addParameters() {

    }

    private void getResponseConent() {

    }
}
