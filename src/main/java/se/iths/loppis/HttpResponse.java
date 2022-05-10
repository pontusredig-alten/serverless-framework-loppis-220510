package se.iths.loppis;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpResponse {

    private Map<String, String> headers = new HashMap<>();
    private String body;
    private String statusCode = "200";

    public HttpResponse() {
        this.headers.put("Content-Type", "application/json");
    }

    public HttpResponse(Item item) {
        this();
        Gson gson = new Gson();
        this.body = gson.toJson(item);
    }

    public HttpResponse(List<Item> items) {
        this();
        Gson gson = new Gson();
        this.body = gson.toJson(items);
    }

    public HttpResponse(String statusCode) {
        this();
        this.statusCode = statusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
