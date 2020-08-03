package com.example.redisdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JsonRequest {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonRequest.class);

    public JsonRequest() throws IOException {
        String baseUrl = "http://localhost:8081/actuator";

        List<String> endpoints = new ArrayList<String>() {
            {
                /**
                 * 应用配置类
                 */
                //add("autoconfig");
                add("configprops");
                add("beans");
                add("mappings");
                add("env");

                /**
                 * 度量指标类
                 */
                add("metrics");
                add("health");
                //add("dump");
                //add("trace");

                add("caches");
                add("conditions");
                add("scheduledtasks");
                add("threaddump");

            }
        };


        for (String endpoint : endpoints) {
            URL url = new URL(baseUrl + "/" + endpoint);

            URLConnection urlConnection = url.openConnection();
            InputStream is = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            String text = br.readLine();
            LOGGER.info(text);
            //System.out.println(text);

            System.out.println();
        }


    }
}
