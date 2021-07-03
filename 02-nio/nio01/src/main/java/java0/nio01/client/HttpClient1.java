package java0.nio01.client;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

public class HttpClient1 {
    private static final Logger logger = LoggerFactory.getLogger(HttpClient1.class);
    private static final String URL = "http://localhost:8801";

    public static void main(String[] args) throws URISyntaxException, ParseException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(URL);

            logger.info("Executing request {} {}", httpGet.getMethod(), httpGet.getUri());
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                HttpEntity entity = response.getEntity();
                String responseString = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                logger.info("HttpGet-Status ({})-Body ({})", response.getCode(), responseString);
                EntityUtils.consume(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
