package com.news.services;

import com.google.gson.Gson;
import com.news.model.response.ApiClimaResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ApiClimaService {

     //private static final HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
     private static final String URL = "http://api.openweathermap.org/data/2.5/weather?q=";
     private static final String appid = "6a46eaa9125c91f18fb51bdc88c6333d";
     private static final String appid2 =  "0b276339c5d58e7b1b70cbcecf6451c6";
     private String city;
     
     @CircuitBreaker(name = "ApiClima", fallbackMethod = "fallback")
    public ApiClimaResponse callAPI(String cityf) throws IOException, InterruptedException {
        this.city = cityf;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL+city+"&appid="+appid))
                .header("accept", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

         
        final ApiClimaResponse apiClimaResponse = new Gson().fromJson(response.body(), ApiClimaResponse.class);

        if (RandomUtils.nextBoolean()) {
            throw new IOException("Probando Circuit Breaker");
        }
        
        System.out.println("API ---- 1");
        
        return apiClimaResponse;
    }
    
     private ApiClimaResponse fallback(final Throwable e) throws IOException, InterruptedException{
        log.error(e.getStackTrace().toString());
        return callAPI2();
    }
     
     
     @CircuitBreaker(name = "ApiClima", fallbackMethod = "fallback2")
     public ApiClimaResponse callAPI2() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL+city+"&appid="+appid2))
                .header("accept", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

         
        final ApiClimaResponse apiClimaResponse = new Gson().fromJson(response.body(), ApiClimaResponse.class);

        

        if (RandomUtils.nextBoolean()) {
            throw new IOException("Probando Circuit Breaker");
        }
        System.out.println("API ---- 2");
        return apiClimaResponse;
    }
     
     private ApiClimaResponse fallback2(final Throwable e) throws IOException, InterruptedException{
         System.out.println("ERROR EN LAS DOS API");
        //log.error(e.getStackTrace().toString());
        return ApiClimaResponse.builder().build();
    }
      
     
}
