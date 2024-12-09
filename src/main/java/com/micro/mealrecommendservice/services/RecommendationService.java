package com.micro.mealrecommendservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.micro.mealrecommendservice.dto.MealRecommendationResponse;
import com.micro.mealrecommendservice.dto.RecipeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class RecommendationService {

    @Value("${offline.fallback.file}")
    private Resource fallbackFile;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private DocumenuService documenuService;

    @Autowired
    private EdamamService edamamService;

    public MealRecommendationResponse getRecommendations(String city, String cuisine, int page,boolean isOffline) {
        long startTime = System.currentTimeMillis();
        logger.info("request to fetch recommendation data from api {} {} {} ", city, cuisine, page);
        try {
            if (isOffline) {
                return fetchFromOffline(city, cuisine, page, startTime);
            } else {
                return fetchFromOnlineOrFallback(city, cuisine, page, startTime);
            }
        } catch (Exception e) {
            logger.info("exception while fetching data returning offline data ", e);
            return fetchFromOffline(city, cuisine, page, startTime);
        }
    }

    private MealRecommendationResponse fetchFromOffline(String city, String cuisine, int page, long startTime) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> fallbackData = objectMapper.readValue(fallbackFile.getInputStream(), Map.class);

            List<String> restaurants = (List<String>) fallbackData.getOrDefault("restaurants", Collections.emptyList());
            List<Map<String, String>> recipeMaps = (List<Map<String, String>>) fallbackData.getOrDefault("recipes", Collections.emptyList());

            List<RecipeDto> recipes = recipeMaps.stream()
                    .map(recipe -> new RecipeDto(recipe.get("name"), recipe.get("calories"), recipe.get("url")))
                    .toList();

            return new MealRecommendationResponse(
                    "Offline Data",
                    restaurants.size() + recipes.size(),
                    city + ", " + cuisine,
                    page,
                    restaurants,
                    recipes,
                    System.currentTimeMillis() - startTime
            );

        } catch (IOException e) {
            throw new RuntimeException("Failed to read offline fallback data", e);
        }
    }

    private MealRecommendationResponse fetchFromOnlineOrFallback(String city, String cuisine, int page, long startTime) {
        List<String> restaurants;
        List<RecipeDto> recipes;

        try {
            restaurants = documenuService.fetchRestaurants(city, page);
        } catch (Exception e) {
            logger.info("exception while fetching online data from  docuemenu apis ", e);
            restaurants = fetchOfflineRestaurants();
        }

        try {
            recipes = edamamService.fetchRecipes(cuisine, page);
        } catch (Exception e) {
            logger.info("exception while fetching online data  from edamam apis ", e);
            recipes = fetchOfflineRecipes();
        }

        return new MealRecommendationResponse(
                "Documenu & EDAMAM (Fallback for failures)",
                restaurants.size() + recipes.size(),
                city + ", " + cuisine,
                page,
                restaurants,
                recipes,
                System.currentTimeMillis() - startTime
        );
    }

    private List<String> fetchOfflineRestaurants() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> fallbackData = objectMapper.readValue(fallbackFile.getInputStream(), Map.class);
            return (List<String>) fallbackData.getOrDefault("restaurants", Collections.emptyList());
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    private List<RecipeDto> fetchOfflineRecipes() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> fallbackData = objectMapper.readValue(fallbackFile.getInputStream(), Map.class);

            List<Map<String, String>> recipeMaps = (List<Map<String, String>>) fallbackData.getOrDefault("recipes", Collections.emptyList());
            return recipeMaps.stream()
                    .map(recipe -> new RecipeDto(recipe.get("name"), recipe.get("calories"), recipe.get("url")))
                    .toList();
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }


}
