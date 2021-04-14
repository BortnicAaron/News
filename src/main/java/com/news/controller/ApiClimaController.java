package com.news.controller;

import com.news.model.response.ApiClimaResponse;
import com.news.services.ApiClimaService;
import io.swagger.v3.oas.annotations.Operation;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/apiClima")
@RestController
public class ApiClimaController {
    @Autowired
    ApiClimaService apiClimaService;
    
    @GetMapping("/{city}")
    @Operation(summary = "API CLIMA")
    public ApiClimaResponse callAPI(@PathVariable String city) {
        try {
            return apiClimaService.callAPI(city);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;

    }
}
