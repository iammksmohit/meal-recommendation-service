package com.micro.mealrecommendservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MealRecommendationResponse {

    private String sourceApi;

    private int totalResults;

    private String searchKeyword;

    private int currentPage;

    private List<String> restaurants;

    private List<RecipeDto> recipes;

    private long timeTakenMs;

    public MealRecommendationResponse(String sourceApi, int totalResults, String searchKeyword, int currentPage, List<String> restaurants, List<RecipeDto> recipes, long timeTakenMs) {
        this.sourceApi = sourceApi;
        this.totalResults = totalResults;
        this.searchKeyword = searchKeyword;
        this.currentPage = currentPage;
        this.restaurants = restaurants;
        this.recipes = recipes;
        this.timeTakenMs = timeTakenMs;
    }

    public String getSourceApi() {
        return sourceApi;
    }

    public void setSourceApi(String sourceApi) {
        this.sourceApi = sourceApi;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<String> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<String> restaurants) {
        this.restaurants = restaurants;
    }

    public List<RecipeDto> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeDto> recipes) {
        this.recipes = recipes;
    }

    public long getTimeTakenMs() {
        return timeTakenMs;
    }

    public void setTimeTakenMs(long timeTakenMs) {
        this.timeTakenMs = timeTakenMs;
    }
}
