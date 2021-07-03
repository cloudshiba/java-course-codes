package java0.nio01.client;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class OkHttpClient1 {
    private static final Logger logger = LoggerFactory.getLogger(OkHttpClient1.class);
    private static final String URL = "http://localhost:8801";

    private final OkHttpClient client = new OkHttpClient();

    public Response run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            logger.info("HttpGet-Status ({})-Body ({})", response.code(), response.body().string());
            return response;
        }
    }

    public static void main(String[] args) throws IOException {
        logger.info("Executing request {} {}", "GET", URL);

        OkHttpClient1 okHttpClient1 = new OkHttpClient1();
        Response response = okHttpClient1.run(URL);
    }
}
