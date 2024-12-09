package com.micro.mealrecommendservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class DocumenuService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${documenu.api.key}")
    private String apiKey;

    @Value("${documenu.api.url}")
    private String apiUrl;

    public List<String> fetchRestaurants(String city, int page) {
        String url = apiUrl + "?key=" + apiKey + "&city=" + city + "&page=" + page;

        try {
            RestTemplate restTemplate = new RestTemplate();
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            List<Map<String, Object>> restaurants = (List<Map<String, Object>>) response.get("data");
            return restaurants.stream()
                    .map(restaurant -> (String) restaurant.get("restaurant_name"))
                    .toList();

        } catch (Exception e) {
            // Fallback for API failure
            return Collections.emptyList();
        }
    }

}
