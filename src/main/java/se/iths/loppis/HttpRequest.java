package se.iths.loppis;

import java.util.Map;

public class HttpRequest {

    Map<String, String> pathParameters;
    String body;

    public HttpRequest(Map<String, String> pathParameters, String body) {
        this.pathParameters = pathParameters;
        this.body = body;
    }

    public HttpRequest() {}

    public Map<String, String> getPathParameters() {
        return pathParameters;
    }

    public void setPathParameters(Map<String, String> pathParameters) {
        this.pathParameters = pathParameters;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "pathParameters=" + pathParameters +
                ", body='" + body + '\'' +
                '}';
    }
}
