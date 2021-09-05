package com.example.blue1;


import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;


@Slf4j
class Blue1ApplicationTests {
    private String ACCESS_TOKEN;

    //testing and logging requests and responses for various inputs and calls
    @Test
    void testBlueApp() throws IOException {

        //request with no token
        log.info("request with no token");
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.233.1:8080/api/homes")
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        log.info("curl --location --request GET 'http://192.168.233.1:8080/api/homes' \\\n" +
                "--data-raw ''");
        log.info("\n"+response.headers().toString());
        log.info("\n" + response.body().string()+"\n\n");
//////////
        //request with expired token
        log.info("request with expired token");
        client = new OkHttpClient().newBuilder()
                .build();
        request = new Request.Builder()
                .url("http://192.168.233.1:8080/api/homes")
                .method("GET", null)
                .addHeader("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vMTkyLjE2OC4yMzMuMTo4MDgwL3NlY3VyaXR5L3Rva2VucGxlYXNlIiwiZXhwIjoxNjMwODUyMzEwLCJ1c2VybmFtZSI6ImJhY2tlbmQtU1ctRW5naW5lZXItQ2FuZGlkYXRlIn0.bY0Itt-nAJm6orRO2uXq1BPK7BrrdnJ0yV_Vpjsp0F4")
                .build();
        response = client.newCall(request).execute();
        log.info("curl --location --request GET 'http://192.168.233.1:8080/api/homes' \\\n" +
                "--header 'token: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vMTkyLjE2OC4yMzMuMTo4MDgwL3NlY3VyaXR5L3Rva2VucGxlYXNlIiwiZXhwIjoxNjMwODUyMzEwLCJ1c2VybmFtZSI6ImJhY2tlbmQtU1ctRW5naW5lZXItQ2FuZGlkYXRlIn0.bY0Itt-nAJm6orRO2uXq1BPK7BrrdnJ0yV_Vpjsp0F4' \\\n" +
                "--data-raw ''");
        log.info("\n" + response.headers().toString());
        log.info("\n" + response.body().string()+"\n\n");
        ///////
        //request token
        log.info("request token");
        client = new OkHttpClient().newBuilder()
                .build();

        request = new Request.Builder()
                .url("http://192.168.233.1:8080/security/tokenplease")
                .method("GET", null)
                .build();
        response = client.newCall(request).execute();

        log.info("curl --location --request GET 'http://192.168.233.1:8080/security/tokenplease' \\\n" +
                "--data-raw ''");
        log.info("\n" + response.headers().toString());
        log.info("\n" + response.body().string()+"\n\n");
        ACCESS_TOKEN = response.header("access_token");
        log.info("token: " + ACCESS_TOKEN);
        /////////

        //get requests for homes
        log.info("get requests for homes");
        client = new OkHttpClient().newBuilder()
                .build();
        request = new Request.Builder()
                .url("http://192.168.233.1:8080/api/homes")
                .method("GET", null)
                .addHeader("token", ACCESS_TOKEN)
                .build();

        response = client.newCall(request).execute();

        log.info("curl --location --request GET 'http://192.168.233.1:8080/api/homes' \\\n" +
                "--header 'token: "+ACCESS_TOKEN+  "' \\\n" +
                "--data-raw ''");
        log.info("\n" + response.headers().toString());
        log.info("\n" + response.body().string()+"\n\n");

        //
        log.info("POST a home");
        client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"name\":\"paraga\", \"type\": \"PRIVATE\"}");
        request = new Request.Builder()
                .url("http://192.168.233.1:8080/api/homes")
                .method("POST", body)
                .addHeader("token", ACCESS_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();
        response = client.newCall(request).execute();

        log.info("curl --location --request POST 'http://192.168.233.1:8080/api/homes' \\\n" +
                "--header 'token: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vMTkyLjE2OC4yMzMuMTo4MDgwL3NlY3VyaXR5L3Rva2VucGxlYXNlIiwiZXhwIjoxNjMwODYwNTc4LCJ1c2VybmFtZSI6ImJhY2tlbmQtU1ctRW5naW5lZXItQ2FuZGlkYXRlIn0.T41F79aUid7O_0611Z9LxnKJcSC0OIOLiuhV2qfIemI' \\\n" +
                "--header 'Content-Type: application/json' \\\n" +
                "--data-raw '{\"name\":\"paraga\", \"type\": \"PRIVATE\"}'");
        log.info("\n" + response.headers().toString());
        log.info("\n" + response.body().string() + "\n\n");




    }

}
