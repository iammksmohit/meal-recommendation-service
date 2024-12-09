package com.micro.mealrecommendservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.micro.mealrecommendservice.dto.RecipeDto;
import com.micro.mealrecommendservice.utils.ResponseValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class EdamamService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${edamam.api.key}")
    private String apiKey;

    @Value("${edamam.api.url}")
    private String apiUrl;

    public List<RecipeDto> fetchRecipes(String cuisine, int page) {
        String url = apiUrl + "?q=" + cuisine + "&app_key=" + apiKey + "&type=public&page=" + page;

        try {
            RestTemplate restTemplate = new RestTemplate();
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            List<Map<String, Object>> hits = (List<Map<String, Object>>) response.get("hits");
            return hits.stream()
                    .map(hit -> {
                        Map<String, Object> recipe = (Map<String, Object>) hit.get("recipe");
                        return new RecipeDto(
                                (String) recipe.get("label"),
                                String.valueOf(recipe.get("calories")),
                                (String) recipe.get("url")
                        );
                    })
                    .toList();

        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
